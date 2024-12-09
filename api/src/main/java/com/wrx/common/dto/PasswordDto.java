package com.wrx.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class PasswordDto implements Serializable {

	@NotBlank(message = "旧密码不能为空")
	private String oldPass;

	@NotBlank(message = "新密码不能为空")
	@Length(min = 6, max = 16, message = "密码长度不能少于6位，不能大于16位")
	private String newPass;

}
