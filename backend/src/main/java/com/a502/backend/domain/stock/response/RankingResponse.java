package com.a502.backend.domain.stock.response;

import com.a502.backend.application.entity.RankingDetail;

import java.util.List;

public record RankingResponse (List<RankingDetail> ranks) {
    public static RankingResponse of(List<RankingDetail> ranks){
        return new RankingResponse(ranks);
    }

    public RankingResponse(List<RankingDetail> ranks){
        this.ranks = ranks;
    }
}
