package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.RankingDetail;
import com.a502.backend.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RankService {

    private final RedisTemplate<String, String> redisTemplate;
    private final String RANKING_KEY = "ranking";

    public void deleteRanking(){
        redisTemplate.delete(RANKING_KEY);
    }
    public void addUserScore(User user, double score) {
        redisTemplate.opsForZSet().add(RANKING_KEY, user.getName(), score);
    }

    public Set<ZSetOperations.TypedTuple<String>> getTop10Users() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(RANKING_KEY, 0, 9);
    }

    public double getUserScore(User user) {
        return redisTemplate.opsForZSet().score(RANKING_KEY, user.getName());
    }

    public Long getUserRank(User user) {
        return redisTemplate.opsForZSet().reverseRank(RANKING_KEY, user.getName());
    }

    public List<RankingDetail> getTop10UserRankings() {
        Set<ZSetOperations.TypedTuple<String>> top10UsersWithScores = getTop10Users();
        List<RankingDetail> userRankings = new ArrayList<>();
        int rank = 1;
        int prevRank = 1;
        int prevBalance = 0;
        for (ZSetOperations.TypedTuple<String> userWithScore : top10UsersWithScores) {
            if (prevBalance == 0)
                prevBalance = userWithScore.getScore().intValue();
            int balance = userWithScore.getScore().intValue();
            int curRank = (balance == prevBalance) ? prevRank : rank;
            RankingDetail userRanking = RankingDetail.builder()
                    .childName(userWithScore.getValue())
                    .rank(curRank)
                    .balance(balance)
                    .build();
            userRankings.add(userRanking);

            prevBalance = balance;
            prevRank = curRank;
            rank++;
        }
        return userRankings;
    }
}
