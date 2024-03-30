package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.CashDetailService;
import com.a502.backend.domain.allowance.request.CalendarDTO;
import com.a502.backend.domain.allowance.response.CalendarSummary;
import com.a502.backend.domain.allowance.response.DailySummary;
import com.a502.backend.domain.allowance.response.ReceiptResponseDto;
import com.a502.backend.domain.allowance.response.TransactionDto;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final ReceiptService receiptService;
    private final UserService userService;
    private final CashDetailService cashDetailService;
    private final AccountDetailService accountDetailService;


}
