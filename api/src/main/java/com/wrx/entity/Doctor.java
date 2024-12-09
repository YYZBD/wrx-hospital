package com.wrx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 医生表
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Getter
@Setter
  @TableName("app_doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 医生姓名
     */
      private String name;

      /**
     * 头像
     */
      private String avatar;

      /**
     * 所属科室ID
     */
      private Long departmentId;

      /**
     * 职称
     */
      private String position;

      /**
     * 挂号费
     */
      private BigDecimal bookingFee;

      /**
     * 介绍
     */
      private String introduce;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;
}
