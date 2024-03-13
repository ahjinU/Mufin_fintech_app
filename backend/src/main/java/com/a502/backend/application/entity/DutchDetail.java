package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "dutch_details")
public class DutchDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dutch_detail_id")
    private Integer dutchDetailId;

    @Column(name = "dutch_detail_uuid")
    private byte[] dutchDetailUuid;

    @Column(name = "account_detail_id")
    private Integer accountDetailId2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dutch_id", referencedColumnName = "dutch_id")
    private Dutch dutch;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder

    public DutchDetail(Integer dutchDetailId, byte[] dutchDetailUuid, Integer accountDetailId2, Dutch dutch, Boolean isDeleted) {
        this.dutchDetailId = dutchDetailId;
        this.dutchDetailUuid = dutchDetailUuid;
        this.accountDetailId2 = accountDetailId2;
        this.dutch = dutch;
        this.isDeleted = isDeleted;
    }
}
