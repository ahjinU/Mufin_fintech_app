package com.a502.backend.application.entity;


import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@ToString
@RedisHash(value = "ranking")
public class RankingDetail {
    String childName;
    int balance;
    int rank;
}
