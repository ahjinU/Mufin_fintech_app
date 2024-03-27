package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.Receipt;
import com.a502.backend.domain.allowance.response.OrderItem;
import com.a502.backend.domain.allowance.response.PaymentInfo;
import com.a502.backend.domain.allowance.response.ReceiptDto;
import com.a502.backend.domain.allowance.response.StoreInfo;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AllowanceService {

    private final AllowanceRepository allowanceRepository;
    @Value("${receipt.apiURL}")
    String apiURL;
    @Value("${receipt.secretKey}")
    String secretKey;

    public void convert(MultipartFile file) {
        String response;
        try {
            response = postFileForConversion(file);
            System.out.println("response: ");
            System.out.println(response);
            System.out.println("===================");
        } catch (Exception e) {
            throw BusinessException.of(ErrorCode.API_ERROR_RECEIPT_FAIL_CONVERT_TO_TEXT);
        }
        ReceiptDto receipt;

        try {
            receipt = parseReceipt(response);
            // 여기서 receipt 객체 사용
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(receipt.toString());


    }

    public static ReceiptDto parseReceipt(String jsonInput) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonInput);

        // 가게 정보 추출
        JsonNode storeInfoNode = rootNode.path("storeInfo");
        StoreInfo storeInfo = StoreInfo.builder()
                .name(storeInfoNode.path("name").asText())
                .address(storeInfoNode.path("address").asText())
                .tel(storeInfoNode.path("tel").asText())
                .build();

        // 결제 정보 추출
        JsonNode paymentInfoNode = rootNode.path("paymentInfo");
        PaymentInfo paymentInfo = PaymentInfo.builder()
                .date(paymentInfoNode.path("date").asText())
                .time(paymentInfoNode.path("time").asText())
                .price(paymentInfoNode.path("price").asInt())
                .build();

        // 주문 항목 추출
        JsonNode orderItemsNode = rootNode.path("orderItems");
        List<OrderItem> orderItems = new ArrayList<>();
        for (JsonNode itemNode : orderItemsNode) {
            OrderItem item = OrderItem.builder()
                    .name(itemNode.path("name").asText())
                    .count(itemNode.path("count").asInt())
                    .unitPrice(itemNode.path("unitPrice").asInt())
                    .build();
            orderItems.add(item);
        }

        // 최종 ReceiptDto 생성
        return ReceiptDto.builder()
                .storeInfo(storeInfo)
                .paymentInfo(paymentInfo)
                .orderItems(orderItems)
                .build();
    }

    private String postFileForConversion(MultipartFile file) throws IOException {
        HttpURLConnection con = createConnection();
        sendRequest(con, file);
        return readResponse(con);
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

        if (!extension.equals("jpg") && !extension.equals("png")) {
            throw BusinessException.of(ErrorCode.UNSUPPORTED_FILE_EXTENSION);
        }

        return extension;
    }
}
