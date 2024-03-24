package com.a502.backend.domain.numberimg;

import com.a502.backend.application.entity.NumberImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public List<NumberImage> saveNumberImageList(String user){
        if (redisTemplate.hasKey(user)){
            redisTemplate.delete(user);
        }
        List<NumberImage> images = numberImageRepository.findAll();
        Collections.shuffle(images);
        List<String> numbers = new ArrayList<>();
        images.forEach(value -> numbers.add(value.getImageUrl()));
        saveNumberList(user, numbers);
        return images;
    }

    public String decodePassword(String user, List<Integer> sequence){
        StringBuilder sb = new StringBuilder();
        // redis 에서 추출
        List<String> numberSequence = getNumberList(user);
        for(int num : sequence){
            sb.append(numberSequence.get(num).charAt(6));
        }
        return sb.toString();
    }

    private void saveNumberList(String user, List<String> numbers) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        numbers.forEach(value -> listOps.rightPush(user, value));
        redisTemplate.expire(user, 500, TimeUnit.MINUTES);
    }

    private List<String> getNumberList(String user) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.range(user, 0, -1); // 리스트의 전체 범위를 가져옴
    }

}
/**
 * 처음 비밀번호 등록할 때
 * // "결제 키패드 주기" -> (value :키패드 순서 & key : 유저 email) 에 넣기
 * // 비밀번호 입력 후 -> "비밀번호 해석"
 * 저장
 *
 *
 * 결제
 * // "결제 키패드 주기" -> (value :키패드 순서 & key : 유저 email) 에 넣기
 * // 비밀번호 입력 후 -> "비밀번호 해석"
 * 비밀번호 일치하는지 확인
 * :일치 Good 오류 회수 0 으로 초기화 후 pass
 * :불일치 -> 다시 결제 키패드 주거나 cnt==5 이면 계좌 정지
 */
