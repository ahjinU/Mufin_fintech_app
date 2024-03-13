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
@Table(name = "savings")
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saving_id")
    private Integer savingId;

    @Column(name = "saving_uuid")
    private byte[] savingUuid;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "period")
    private Integer period;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "modified_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Savings(Integer savingId, byte[] savingUuid, Double interest, Integer period, String name, Date createdAt, Date modifiedAt, Boolean isDeleted, User user) {
        this.savingId = savingId;
        this.savingUuid = savingUuid;
        this.interest = interest;
        this.period = period;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.user = user;
    }
}
