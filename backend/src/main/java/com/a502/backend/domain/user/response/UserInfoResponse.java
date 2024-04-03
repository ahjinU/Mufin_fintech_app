package com.a502.backend.domain.user.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResponse {
	String userUuid;
	String name;
	String email;
	@JsonProperty(value = "isParent")
	Boolean isParent;
	Date createdAt;
	String address;
	String address2;
	String telephone;
	String accountNumber;
	String accountUuid;

	@Builder
	public UserInfoResponse(String userUuid, String name, String email, Boolean isParent, Date createdAt, String address, String address2, String telephone, String accountUuid, String accountNumber) {
		this.userUuid = userUuid;
		this.name = name;
		this.email = email;
		this.isParent = isParent;
		this.createdAt = createdAt;
		this.address = address;
		this.address2 = address2;
		this.telephone = telephone;
		this.accountUuid = accountUuid;
		this.accountNumber = accountNumber;
	}
}
