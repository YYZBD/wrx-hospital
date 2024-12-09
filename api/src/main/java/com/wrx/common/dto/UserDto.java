package com.wrx.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {

	private Long id;
	private String username;
	private String avatar;
	private LocalDateTime createTime;

}
