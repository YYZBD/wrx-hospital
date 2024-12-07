package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 就诊卡表
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Getter
@Setter
  @TableName("app_medical_card")
public class MedicalCard implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 用户ID
     */
      private Long userId;

      /**
     * 卡号
     */
      private String sn;

      /**
     * 真实名字
     */
      private String realName;

      /**
     * 身份证
     */
      private String cardNumber;

    private String phone;

      /**
     * 性别
     */
      private String gender;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;
}
