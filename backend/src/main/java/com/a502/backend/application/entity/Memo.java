package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memos")
public class Memo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Integer memoId;

    @Column(name = "memo_uuid")
    private byte[] memoUuid;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder
    public Memo(Integer memoId, byte[] memoUuid, String content, String category, Boolean isDeleted) {
        this.memoId = memoId;
        this.memoUuid = memoUuid;
        this.content = content;
        this.category = category;
        this.isDeleted = isDeleted;
    }
}
