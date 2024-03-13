package com.a502.backend.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name", length = 10)
    private String name;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "password", length = 30)
    private String password;

    @Column(name = "gender", length = 4)
    private String gender;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "address2", length = 100)
    private String address2;

    @Column(name = "type")
    private Integer type;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(name = "failed")
    private Integer failed;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "modified_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

    @Column(name = "is_deleted")
    private Boolean deleted;

}
