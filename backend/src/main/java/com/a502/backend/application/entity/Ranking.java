package com.a502.backend.application.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "ranking")
public class Ranking {

    @Id
    int id;

    List<RankingDetail> ranking;

    @TimeToLive
    private long ttl;

}
