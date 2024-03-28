package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.AccountDetailService;
import com.a502.backend.domain.account.CashDetailService;
import com.a502.backend.domain.allowance.request.CalendarDTO;
import com.a502.backend.domain.allowance.response.CalendarSummary;
import com.a502.backend.domain.allowance.response.ReceiptResponseDto;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final ReceiptService receiptService;
    private final UserService userService;
    private final CashDetailService cashDetailService;
    private final AccountDetailService accountDetailService;

    public ReceiptResponseDto convert(MultipartFile file) {

        ReceiptResponseDto receiptResponseDto = receiptService.saveReceiptFromImage(file);

        return receiptService.saveReceiptFromImage(file);
    }


    public List<CalendarSummary> getTransactionsForPeriod(CalendarDTO calendarDTO) {

        List<CalendarSummary> summary = new ArrayList<>();

        User holderUser= findHolderUser(calendarDTO.getChildUuid());
        if (holderUser != null) {
            List<AccountDetail> accountDetails = accountDetailService.findAccountDetailsForUserAndPeriod(holderUser, calendarDTO.getStartDate(), calendarDTO.getEndDate());
            summaries.addAll(convertFromEntitys(accountDetails));

            List<CashDetail> cashDetails = cashDetailService.findCashDetailsForUserAndPeriod(holderUser, calendarDTO.getStartDate(), calendarDTO.getEndDate());
            summaries.addAll(CashDetail.convertFromEntitys(cashDetails));
        }

        return summary;
    }

    private User findHolderUser(String childUuid){
        if(childUuid==null)
            return userService.userFindByEmail();

        return userService.findByUserUuid(childUuid);
    }
}
