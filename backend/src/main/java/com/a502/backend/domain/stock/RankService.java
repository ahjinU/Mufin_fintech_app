package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.RankingDetail;
import com.a502.backend.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RankService {

    private final RedisTemplate<String, String> redisTemplate;
    private final String RANKING_KEY = "ranking";

    public void deleteRanking(){
        redisTemplate.delete(RANKING_KEY);
    }
    public void addUserScore(User user, double score) {
        redisTemplate.opsForZSet().add(RANKING_KEY, user.getUserUuid().toString(), score);
        redisTemplate.expire(RANKING_KEY, 1, TimeUnit.HOURS);
    }

    public Set<ZSetOperations.TypedTuple<String>> getTop10Users() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(RANKING_KEY, 0, 9);
    }

    public double getUserScore(User user) {
        return redisTemplate.opsForZSet().score(RANKING_KEY, user.getUserUuid().toString());
    }

    public Long getUserRank(User user) {
        return redisTemplate.opsForZSet().reverseRank(RANKING_KEY, user.getUserUuid().toString());
    }

}
