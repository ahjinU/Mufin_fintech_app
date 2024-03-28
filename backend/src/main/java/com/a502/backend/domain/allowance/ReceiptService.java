package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.Receipt;
import com.a502.backend.application.entity.ReceiptDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.allowance.OcrDto.OrderItem;
import com.a502.backend.domain.allowance.OcrDto.PaymentInfo;
import com.a502.backend.domain.allowance.OcrDto.ReceiptDto;
import com.a502.backend.domain.allowance.OcrDto.StoreInfo;
import com.a502.backend.domain.allowance.response.ReceiptResponseDto;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    @Value("${receipt.apiURL}")
    String apiURL;
    @Value("${receipt.secretKey}")
    String secretKey;

    private final ReceiptRepository receiptRepository;
    private final ReceiptDetailRepository receiptDetailRepository;
    private final UserService userService;


    /**
     * 이미지로부터 변환한 텍스트를 Entity로 저장
     *
     * @param file
     * @return
     */
    @Transactional
    public ReceiptResponseDto saveReceiptFromImage(MultipartFile file) {

        ReceiptResponseDto receiptResponseDto = null;

        try {
            String response = connectionToOcr(file);

            ReceiptDto receipt = parseReceipt(response);

            try {
                Receipt registerReceipt = saveReceiptAndDetails(receipt);
                Receipt registeredReceipt = receiptRepository.findById(registerReceipt.getId()).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_NOT_RECEIPT));
                receiptResponseDto = ReceiptResponseDto.convertFromEntity(registeredReceipt);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            throw BusinessException.of(ErrorCode.API_ERROR_RECEIPT_FAIL_CONVERT_TO_TEXT);
        }

        return receiptResponseDto;
    }

    @Transactional
    public Receipt saveReceiptAndDetails(ReceiptDto receiptDto) {
        Receipt receipt = Receipt.createReceipt(receiptDto);
        Receipt savedReceipt = receiptRepository.save(receipt);

        saveReceiptDetails(receiptDto.getOrderItems(), savedReceipt);

        return savedReceipt;


    }

    private void saveReceiptDetails(List<OrderItem> orderItems, Receipt receipt) {
        for (OrderItem item : orderItems) {
            ReceiptDetail detail = ReceiptDetail.builder()
                    .item(item.getItem())
                    .unitPrice(item.getUnitPrice())
                    .cnt(item.getCnt())
                    .total(item.getTotal())
                    .receipt(receipt)
                    .build();

            receipt.addReceiptDetail(detail);
            receiptDetailRepository.save(detail);
        }
    }

    public static ReceiptDto parseReceipt(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);

        return ReceiptDto.builder()
                .storeInfo(parseStoreInfo(rootNode))
                .paymentInfo(parsePaymentInfo(rootNode))
                .orderItems(parseOrderItems(rootNode))
                .build();
    }

    private static StoreInfo parseStoreInfo(JsonNode rootNode) {
        JsonNode storeInfoNode = findNode(rootNode, "images/0/receipt/result/storeInfo");
        return StoreInfo.builder()
                .name(getText(storeInfoNode, "name/text"))
                .address(getText(storeInfoNode, "addresses/0/text"))
                .tel(getText(storeInfoNode, "tel/0/text"))
                .build();
    }

    private static PaymentInfo parsePaymentInfo(JsonNode rootNode) {
        JsonNode paymentInfoNode = findNode(rootNode, "images/0/receipt/result/paymentInfo");
        int totalPrice = parseSafeInt(getText(rootNode, "images/0/receipt/result/totalPrice/price/text").replace(",", ""), 0);
        PaymentInfo pay = PaymentInfo.builder()
                .date(getText(paymentInfoNode, "date/text"))
                .time(getText(paymentInfoNode, "time/text"))

                .price(totalPrice)
                .build();
        return pay;
    }

    private static List<OrderItem> parseOrderItems(JsonNode rootNode) {
        List<OrderItem> orderItems = new ArrayList<>();
        JsonNode itemsNode = findNode(rootNode, "images/0/receipt/result/subResults/0/items");
        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {

                int cnt = parseSafeInt(getText(itemNode, "count/text"), 1);
                int unitPrice = parseSafeInt(getText(itemNode, "price/price/text").replace(",", ""), 0);
                orderItems.add(OrderItem.builder()
                        .item(getText(itemNode, "name/text"))
                        .cnt(cnt)
                        .unitPrice(unitPrice)
                        .total(cnt * unitPrice)
                        .build());
            }
        }
        return orderItems;
    }

    // Utility methods for JSON parsing and safe integer parsing
    private static JsonNode findNode(JsonNode rootNode, String path) {
        String[] parts = path.split("/");
        JsonNode currentNode = rootNode;
        for (String part : parts) {
            if (part.matches("\\d+")) { // Array index
                currentNode = currentNode.get(Integer.parseInt(part));
            } else {
                currentNode = currentNode.path(part);
            }
            if (currentNode.isMissingNode()) {
                throw BusinessException.of(ErrorCode.API_ERROR_NOT_RECEIPT);
            }
        }
        return currentNode;
    }

    private static String getText(JsonNode node, String path) {
        return findNode(node, path).asText("");
    }

    private static int parseSafeInt(String value, int defaultValue) {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String connectionToOcr(MultipartFile file) throws IOException {
        HttpURLConnection con = null;
        try {
            con = createConnection();
            sendRequest(con, file);
            return readResponse(con);
        } catch (IOException e) {
            throw BusinessException.of(ErrorCode.API_ERROR_OCR_CONNECTION);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-OCR-SECRET", secretKey);
        return con;
    }

    private void sendRequest(HttpURLConnection con, MultipartFile file) throws IOException {
        JSONObject json = initRequestBody(file);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(json.toString().getBytes());
        }
    }

    private String readResponse(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                responseCode == 200 ? con.getInputStream() : con.getErrorStream()))) {

            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

    private String encodeFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    private JSONObject createImageInfo(String encodedFile, String format, String name) {

        JSONObject image = new JSONObject();

        image.put("format", format);
        image.put("name", name);
        image.put("data", encodedFile);

        return image;
    }

    private JSONObject initJsonObject() {

        JSONObject json = new JSONObject();

        json.put("version", "V2");
        json.put("requestId", UUID.randomUUID().toString());
        json.put("timestamp", System.currentTimeMillis());

        return json;
    }

    public JSONObject initRequestBody(MultipartFile file) throws IOException {

        JSONObject json = initJsonObject();

        String encodedFile = encodeFileToBase64(file);
        String fileExtension = getExtension(file);

        JSONObject imageInfo = createImageInfo(encodedFile, fileExtension, "receiptImage");

        JSONArray images = new JSONArray();
        images.put(imageInfo);

        json.put("images", images);

        return json;
    }

    private String getExtension(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || !originalFileName.contains(".")) {
            throw BusinessException.of(ErrorCode.API_ERROR_RECEIPT_NOT_KNOWN_EXTENSION);
        }

        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg")) {
            throw BusinessException.of(ErrorCode.API_ERROR_UNSUPPORTED_FILE_EXTENSION);
        }

        return extension;
    }
}
