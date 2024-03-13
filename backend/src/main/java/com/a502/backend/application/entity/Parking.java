package com.a502.backend.application.entity;

import com.a502.backend.application.controller.ParkingController;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "parkings")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parking_id")
    private int id;

    @Column(name="parking_uuid")
    private byte[] parkingUuid;

    @Column()
    private int balance;

    @Column()
    private int interest;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    @Builder
    public Parking(int id,byte[] parkingUuid, int balance, int interest, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted, User user){
        this.id = id;
        this.parkingUuid = parkingUuid;
        this.balance = balance;
        this.interest = interest;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.user = user;
    }
}
