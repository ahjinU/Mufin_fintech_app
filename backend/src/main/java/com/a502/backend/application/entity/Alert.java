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
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Integer alertId;

    @Column(name = "alert_uuid")
    private byte[] alertUuid;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Alert(Integer alertId, byte[] alertUuid, Date createdAt, Boolean isDeleted, String content, String type, User user) {
        this.alertId = alertId;
        this.alertUuid = alertUuid;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.content = content;
        this.type = type;
        this.user = user;
    }
}
