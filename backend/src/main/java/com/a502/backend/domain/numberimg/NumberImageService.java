package com.a502.backend.domain.numberimg;

import com.a502.backend.application.entity.NumberImage;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NumberImageService {
    private final NumberImageRepository numberImageRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public NumberImage saveImage(String image){
        return numberImageRepository.save(NumberImage.builder()
                .imageUrl(image)
                .build());
    }

    public List<String> getKeypadList(String uuid){
        List<NumberImage> images = numberImageRepository.findAll();
        Collections.shuffle(images);
        List<String> numbers = new ArrayList<>();
        images.forEach(value -> numbers.add(value.getImageUrl()));
        saveNumberList(uuid, numbers);
        return numbers;
    }

    public String decodePassword(String uuid, List<Integer> inputSequence){
        StringBuilder sb = new StringBuilder();
        List<String> numberSequence = getNumberList(uuid);
        for(int num : inputSequence){
            sb.append(numberSequence.get(num).charAt(60));
        }
        return sb.toString();
    }

    private void saveNumberList(String uuid, List<String> numbers) {
        if (redisTemplate.hasKey(uuid)){
            redisTemplate.delete(uuid);
        }
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        numbers.forEach(value -> listOps.rightPush(uuid, value));
        redisTemplate.expire(uuid, 500, TimeUnit.MINUTES);
    }

    private List<String> getNumberList(String uuid) {
        if (!redisTemplate.hasKey(uuid))
            throw BusinessException.of(ErrorCode.API_ERROR_KEYPAD_TIMEOUT);
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        List<String> result = listOps.range(uuid, 0, -1);
        redisTemplate.delete(uuid);
        return result;
    }

}

