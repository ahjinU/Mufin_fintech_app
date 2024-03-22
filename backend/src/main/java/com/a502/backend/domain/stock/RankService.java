package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Ranking;
import com.a502.backend.application.entity.RankingDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.common.RedisUtils;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RankService {
    private final RedisUtils redisUtils;
    private final RankingRepository rankingRepository;

    public List<RankingDetail> getRankingList(){
        Ranking rank = (Ranking) redisUtils.getData("ranks");
        return rank.getRanking();
    }

    public RankingDetail getRanking(User user){
        Ranking rank = (Ranking) redisUtils.getData("ranks");

        for(RankingDetail detail: rank.getRanking()){
            if (detail.getChildName().equals(user.getName()))
                return detail;
        }
        throw BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST);
    }

    public void addRankingList(List<RankingDetail> ranks){
        redisUtils.deleteData("ranks");
        redisUtils.setData("ranks", ranks, 1000L * 60 * 60 * 24);
    }
}
