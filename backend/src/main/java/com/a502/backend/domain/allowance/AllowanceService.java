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
import com.fasterxml.jackson.databind.node.NullNode;
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
//response = "{\"version\":\"V2\",\"requestId\":\"93a92ba8-def1-4215-be54-08b3dfbbf949\",\"timestamp\":1711513552242,\"images\":[{\"receipt\":{\"meta\":{\"estimatedLanguage\":\"ko\"},\"result\":{\"storeInfo\":{\"name\":{\"text\":\"Frame676\",\"formatted\":{\"value\":\"Frame676\"},\"keyText\":\"\",\"confidenceScore\":0.95594,\"boundingPolys\":[{\"vertices\":[{\"x\":451.0,\"y\":214.0},{\"x\":873.0,\"y\":222.0},{\"x\":872.0,\"y\":275.0},{\"x\":450.0,\"y\":266.0}]}],\"maskingPolys\":[]},\"bizNum\":{\"text\":\"6132627858\",\"formatted\":{\"value\":\"613-26-27858\"},\"keyText\":\"사업자번호 :\",\"confidenceScore\":0.61607,\"boundingPolys\":[{\"vertices\":[{\"x\":444.0,\"y\":452.0},{\"x\":723.0,\"y\":457.0},{\"x\":722.0,\"y\":518.0},{\"x\":443.0,\"y\":513.0}]}],\"maskingPolys\":[]},\"addresses\":[{\"text\":\"서울 동작구 시흥대로 676 1층\",\"formatted\":{\"value\":\"서울 동작구 시흥대로 676 1층\"},\"keyText\":\"주소 :\",\"confidenceScore\":0.80108,\"boundingPolys\":[{\"vertices\":[{\"x\":290.0,\"y\":508.0},{\"x\":403.0,\"y\":508.0},{\"x\":403.0,\"y\":575.0},{\"x\":290.0,\"y\":575.0}]},{\"vertices\":[{\"x\":422.0,\"y\":508.0},{\"x\":589.0,\"y\":510.0},{\"x\":587.0,\"y\":579.0},{\"x\":421.0,\"y\":577.0}]},{\"vertices\":[{\"x\":605.0,\"y\":511.0},{\"x\":821.0,\"y\":514.0},{\"x\":820.0,\"y\":585.0},{\"x\":604.0,\"y\":582.0}]},{\"vertices\":[{\"x\":835.0,\"y\":519.0},{\"x\":928.0,\"y\":519.0},{\"x\":928.0,\"y\":580.0},{\"x\":835.0,\"y\":580.0}]},{\"vertices\":[{\"x\":965.0,\"y\":519.0},{\"x\":1052.0,\"y\":519.0},{\"x\":1052.0,\"y\":584.0},{\"x\":965.0,\"y\":584.0}]}],\"maskingPolys\":[]}],\"tel\":[{\"text\":\"07088076046\",\"formatted\":{\"value\":\"07088076046\"},\"keyText\":\"전화 :\",\"confidenceScore\":0.87398,\"boundingPolys\":[{\"vertices\":[{\"x\":309.0,\"y\":633.0},{\"x\":618.0,\"y\":636.0},{\"x\":617.0,\"y\":702.0},{\"x\":308.0,\"y\":699.0}]}]}]},\"paymentInfo\":{\"date\":{\"text\":\"2024-03-26\",\"formatted\":{\"year\":\"2024\",\"month\":\"03\",\"day\":\"26\"},\"keyText\":\"일자 :\",\"confidenceScore\":0.6404,\"boundingPolys\":[{\"vertices\":[{\"x\":311.0,\"y\":699.0},{\"x\":590.0,\"y\":702.0},{\"x\":589.0,\"y\":761.0},{\"x\":310.0,\"y\":758.0}]}],\"maskingPolys\":[]},\"time\":{\"text\":\"11: 58: 55\",\"formatted\":{\"hour\":\"11\",\"minute\":\"58\",\"second\":\"55\"},\"keyText\":\"\",\"confidenceScore\":0.89634,\"boundingPolys\":[{\"vertices\":[{\"x\":606.0,\"y\":706.0},{\"x\":684.0,\"y\":706.0},{\"x\":684.0,\"y\":760.0},{\"x\":606.0,\"y\":760.0}]},{\"vertices\":[{\"x\":680.0,\"y\":706.0},{\"x\":761.0,\"y\":706.0},{\"x\":761.0,\"y\":760.0},{\"x\":680.0,\"y\":760.0}]},{\"vertices\":[{\"x\":756.0,\"y\":706.0},{\"x\":824.0,\"y\":706.0},{\"x\":824.0,\"y\":763.0},{\"x\":756.0,\"y\":763.0}]}]}},\"subResults\":[{\"items\":[{\"name\":{\"text\":\"빵추가\",\"formatted\":{\"value\":\"빵추가\"},\"keyText\":\"\",\"confidenceScore\":0.95356,\"boundingPolys\":[{\"vertices\":[{\"x\":126.0,\"y\":939.0},{\"x\":292.0,\"y\":939.0},{\"x\":292.0,\"y\":1005.0},{\"x\":126.0,\"y\":1005.0}]}],\"maskingPolys\":[]},\"count\":{\"text\":\"1\",\"formatted\":{\"value\":\"1\"},\"keyText\":\"\",\"confidenceScore\":0.94926,\"boundingPolys\":[{\"vertices\":[{\"x\":909.0,\"y\":950.0},{\"x\":939.0,\"y\":950.0},{\"x\":939.0,\"y\":1002.0},{\"x\":909.0,\"y\":1002.0}]}]},\"price\":{\"price\":{\"text\":\"3,500\",\"formatted\":{\"value\":\"3500\"},\"keyText\":\"\",\"confidenceScore\":0.90772,\"boundingPolys\":[{\"vertices\":[{\"x\":1033.0,\"y\":947.0},{\"x\":1176.0,\"y\":949.0},{\"x\":1175.0,\"y\":1008.0},{\"x\":1032.0,\"y\":1005.0}]}]},\"unitPrice\":{\"text\":\"3,500\",\"formatted\":{\"value\":\"3500\"},\"keyText\":\"\",\"confidenceScore\":0.93931,\"boundingPolys\":[{\"vertices\":[{\"x\":649.0,\"y\":946.0},{\"x\":795.0,\"y\":946.0},{\"x\":795.0,\"y\":1007.0},{\"x\":649.0,\"y\":1007.0}]}]}}},{\"name\":{\"text\":\"PA - 알리올리오\",\"formatted\":{\"value\":\"PA-알리올리오\"},\"keyText\":\"\",\"confidenceScore\":0.76275,\"boundingPolys\":[{\"vertices\":[{\"x\":122.0,\"y\":1007.0},{\"x\":192.0,\"y\":1007.0},{\"x\":192.0,\"y\":1061.0},{\"x\":122.0,\"y\":1061.0}]},{\"vertices\":[{\"x\":213.0,\"y\":1026.0},{\"x\":237.0,\"y\":1026.0},{\"x\":237.0,\"y\":1042.0},{\"x\":213.0,\"y\":1042.0}]},{\"vertices\":[{\"x\":261.0,\"y\":1005.0},{\"x\":529.0,\"y\":1005.0},{\"x\":529.0,\"y\":1066.0},{\"x\":261.0,\"y\":1066.0}]}],\"maskingPolys\":[]},\"count\":{\"text\":\"1\",\"formatted\":{\"value\":\"1\"},\"keyText\":\"\",\"confidenceScore\":0.95509,\"boundingPolys\":[{\"vertices\":[{\"x\":909.0,\"y\":1013.0},{\"x\":937.0,\"y\":1013.0},{\"x\":937.0,\"y\":1061.0},{\"x\":909.0,\"y\":1061.0}]}]},\"price\":{\"price\":{\"text\":\"15,000\",\"formatted\":{\"value\":\"15000\"},\"keyText\":\"\",\"confidenceScore\":0.93741,\"boundingPolys\":[{\"vertices\":[{\"x\":1011.0,\"y\":1013.0},{\"x\":1174.0,\"y\":1013.0},{\"x\":1174.0,\"y\":1065.0},{\"x\":1011.0,\"y\":1065.0}]}]},\"unitPrice\":{\"text\":\"15,000\",\"formatted\":{\"value\":\"15000\"},\"keyText\":\"\",\"confidenceScore\":0.93641,\"boundingPolys\":[{\"vertices\":[{\"x\":627.0,\"y\":1007.0},{\"x\":791.0,\"y\":1007.0},{\"x\":791.0,\"y\":1063.0},{\"x\":627.0,\"y\":1063.0}]}]}}},{\"name\":{\"text\":\"R - 로제해산물\",\"formatted\":{\"value\":\"R-로제해산물\"},\"keyText\":\"\",\"confidenceScore\":0.76523,\"boundingPolys\":[{\"vertices\":[{\"x\":126.0,\"y\":1068.0},{\"x\":170.0,\"y\":1068.0},{\"x\":170.0,\"y\":1122.0},{\"x\":126.0,\"y\":1122.0}]},{\"vertices\":[{\"x\":181.0,\"y\":1083.0},{\"x\":209.0,\"y\":1083.0},{\"x\":209.0,\"y\":1103.0},{\"x\":181.0,\"y\":1103.0}]},{\"vertices\":[{\"x\":231.0,\"y\":1061.0},{\"x\":503.0,\"y\":1061.0},{\"x\":503.0,\"y\":1127.0},{\"x\":231.0,\"y\":1127.0}]}],\"maskingPolys\":[]},\"count\":{\"text\":\"1\",\"formatted\":{\"value\":\"1\"},\"keyText\":\"\",\"confidenceScore\":0.95516,\"boundingPolys\":[{\"vertices\":[{\"x\":907.0,\"y\":1070.0},{\"x\":939.0,\"y\":1070.0},{\"x\":939.0,\"y\":1122.0},{\"x\":907.0,\"y\":1122.0}]}]},\"price\":{\"price\":{\"text\":\"16,500\",\"formatted\":{\"value\":\"16500\"},\"keyText\":\"\",\"confidenceScore\":0.93102,\"boundingPolys\":[{\"vertices\":[{\"x\":1007.0,\"y\":1068.0},{\"x\":1174.0,\"y\":1068.0},{\"x\":1174.0,\"y\":1126.0},{\"x\":1007.0,\"y\":1126.0}]}]},\"unitPrice\":{\"text\":\"16,500\",\"formatted\":{\"value\":\"16500\"},\"keyText\":\"\",\"confidenceScore\":0.93231,\"boundingPolys\":[{\"vertices\":[{\"x\":625.0,\"y\":1066.0},{\"x\":791.0,\"y\":1066.0},{\"x\":791.0,\"y\":1126.0},{\"x\":625.0,\"y\":1126.0}]}]}}}]}],\"totalPrice\":{\"price\":{\"text\":\"35,000\",\"formatted\":{\"value\":\"35000\"},\"keyText\":\"청구금액:\",\"confidenceScore\":0.90829,\"boundingPolys\":[{\"vertices\":[{\"x\":991.0,\"y\":1658.0},{\"x\":1157.0,\"y\":1658.0},{\"x\":1157.0,\"y\":1713.0},{\"x\":991.0,\"y\":1713.0}]}]}},\"subTotal\":[{\"taxPrice\":[{\"text\":\"3,182\",\"formatted\":{\"value\":\"3182\"},\"keyText\":\"부 가 세:\",\"confidenceScore\":0.91834,\"boundingPolys\":[{\"vertices\":[{\"x\":1018.0,\"y\":1480.0},{\"x\":1161.0,\"y\":1480.0},{\"x\":1161.0,\"y\":1540.0},{\"x\":1018.0,\"y\":1540.0}]}]}]}]}},\"uid\":\"649d5f87f49a40cc86df8fc38ea896e4\",\"name\":\"receiptImage\",\"inferResult\":\"SUCCESS\",\"message\":\"SUCCESS\",\"validationResult\":{\"result\":\"NO_REQUESTED\"}}]}\n";

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final ReceiptService receiptService;

    public ReceiptDto convert(MultipartFile file) {

        return receiptService.saveReceiptFromImage(file);
    }


}
