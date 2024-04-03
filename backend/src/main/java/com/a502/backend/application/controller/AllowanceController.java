package com.a502.backend.application.controller;
import com.a502.backend.application.entity.Memo;
import com.a502.backend.application.facade.AllowanceFacade;
import com.a502.backend.domain.allowance.AllowanceService;
import com.a502.backend.domain.allowance.OcrDto.ReceiptDto;
import com.a502.backend.domain.allowance.request.*;
import com.a502.backend.domain.allowance.response.*;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/allowance")
public class AllowanceController {
    @Value("${receipt.apiURL}")
    String apiURL;
    @Value("${receipt.secretKey}")
    String secretKey;

    private final AllowanceService allowanceService;
    private final AllowanceFacade allowanceFacade;


    @PostMapping("/calender")
    public ResponseEntity<ApiResponse<CalendarSummary>> calender(@RequestBody CalendarDTO calendarDTO) {
       CalendarSummary summary = allowanceFacade.getTransactionsForPeriod(calendarDTO);
       ApiResponse<CalendarSummary> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_GET_BY_MONTH, summary);
        return ResponseEntity.ok().body(apiResponse);
    }




    @PostMapping("/calender-detail")
    public ResponseEntity<ApiResponse<CalendarDetailSummary>> calenderDetail(@RequestBody CalendarDTO calendarDTO) {

        CalendarDetailSummary summary = allowanceFacade.getTransactionsDetailForMonth(calendarDTO);
        ApiResponse<CalendarDetailSummary> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_GET_BY_MONTH_DETAIL, summary);


        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/receipt/save")
    public ResponseEntity<ApiResponse<ReceiptResponseDto>> convert(@ModelAttribute ReceiptRequestDto receiptRequestDto) {

        ReceiptResponseDto receiptResponseDto = allowanceFacade.convert(receiptRequestDto);

        ApiResponse<ReceiptResponseDto> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_CONVERT_IMAGE, receiptResponseDto);
        return ResponseEntity.ok().body(apiResponse);

    }


    @PostMapping("/day")
    public ResponseEntity<ApiResponse<DaySummary>> day(@RequestBody DayDto dayDto) {

        DaySummary summary = allowanceFacade.getTransactionsDetailForDay(dayDto);

        ApiResponse<DaySummary> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_GET_BY_DAY, summary);
        return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<TransactionDetailDto>> detail(@RequestBody DetailDto detailDto) {

        TransactionDetailDto transaction = allowanceFacade.getTransactionsDetailByUuid(detailDto);

        ApiResponse<TransactionDetailDto> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_GET_BY_DETAIL, transaction);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/cash")
    public ResponseEntity<ApiResponse<TransactionDetailDto>> cash(@RequestBody CashDto cashDto) {

        TransactionDetailDto transaction = allowanceFacade.insertCashTransaction(cashDto);

        ApiResponse<TransactionDetailDto> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_INSERT_CASH, transaction);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/memo")
    public ResponseEntity<ApiResponse<TransactionDetailDto>> memo(@RequestBody MemoDto memoDto) {

        TransactionDetailDto transaction = allowanceFacade.insertMemo(memoDto);

        ApiResponse<TransactionDetailDto> apiResponse = new ApiResponse<>(ResponseCode.API_SUCCESS_ALLOWANCE_INSERT_MEMO, transaction);
        return ResponseEntity.ok().body(apiResponse);
    }
}