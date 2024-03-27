package com.a502.backend.application.controller;
import com.a502.backend.domain.allowance.AllowanceService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allowance")
public class AllowanceController {
    @Value("${receipt.apiURL}")
    String apiURL;
    @Value("${receipt.secretKey}")
    String secretKey ;

    private final AllowanceService allowanceService;

    @PostMapping("/receipt/convert")
    public ResponseEntity<ApiResponse<String>> convert(@RequestParam MultipartFile file) {

        System.out.println("[controller] /receipt/convert 진입");

        allowanceService.convert(file);

        ApiResponse<String> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_CONVERT_IMAGE);
        return ResponseEntity.ok().body(apiResponse);

    }
