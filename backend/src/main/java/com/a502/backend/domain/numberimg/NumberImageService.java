package com.a502.backend.domain.numberimg;

import com.a502.backend.application.entity.NumberImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NumberImageService {
    private final NumberImageRepository numberImageRepository;

    public NumberImage saveImage(String image){
        return numberImageRepository.save(NumberImage.builder()
                .imageUrl(image)
                .build());
    }

}
