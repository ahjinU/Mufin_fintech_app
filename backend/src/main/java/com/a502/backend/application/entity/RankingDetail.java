package com.a502.backend.application.entity;


import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@RedisHash(value = "ranking")
public class RankingDetail {
    String childName;
    int balance;
    int rank;
}
