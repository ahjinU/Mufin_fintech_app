package com.a502.backend.application.controller;
import com.a502.backend.domain.allowance.AllowanceService;
import com.a502.backend.domain.allowance.OcrDto.ReceiptDto;
import com.a502.backend.domain.allowance.request.CalendarDTO;
import com.a502.backend.domain.allowance.response.CalendarSummary;
import com.a502.backend.domain.allowance.response.ReceiptResponseDto;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allowance")
public class AllowanceController {
    @Value("${receipt.apiURL}")
    String apiURL;
    @Value("${receipt.secretKey}")
    String secretKey;

    private final AllowanceService allowanceService;

    @PostMapping("/allowance/calender")
    public ResponseEntity<ApiResponse<List<CalendarSummary>>> calender(@RequestBody CalendarDTO calendarDTO) {

        List<CalendarSummary> summary = allowanceService.getTransactionsForPeriod(calendarDTO);

        ApiResponse<List<CalendarSummary>> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_GET_BY_MONTH, summary);

        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/receipt/convert")
    public ResponseEntity<ApiResponse<ReceiptResponseDto>> convert(@RequestParam MultipartFile file) {

        ReceiptResponseDto receiptResponseDto = allowanceService.convert(file);

        ApiResponse<ReceiptResponseDto> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_CONVERT_IMAGE, receiptResponseDto);
        return ResponseEntity.ok().body(apiResponse);

    }
}