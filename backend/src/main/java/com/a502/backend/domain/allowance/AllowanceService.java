package com.a502.backend.domain.allowance;

import com.a502.backend.domain.allowance.response.ReceiptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final ReceiptService receiptService;

    public ReceiptResponseDto convert(MultipartFile file) {

        ReceiptResponseDto receiptResponseDto = receiptService.saveReceiptFromImage(file);

        return receiptService.saveReceiptFromImage(file);
    }


}
