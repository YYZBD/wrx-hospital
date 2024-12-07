package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 预约挂号表
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Getter
@Setter
  @TableName("app_booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 预约编号
     */
      private String sn;

      /**
     * 用户ID
     */
      private Long userId;

      /**
     * 医生ID
     */
      private Long doctorId;

      /**
     * 科室ID
     */
      private Long departmentId;

      /**
     * 就诊卡ID
     */
      private Long medicalCardId;

      /**
     * 就诊卡号
     */
      private String medicalCardSn;

      /**
     * 就诊人名称
     */
      private String medicalCardName;

      /**
     * 排班ID
     */
      private Long scheduleId;

      /**
     * 预约时间
     */
      private LocalDate scheduleDate;

      /**
     * 上午或下午
     */
      private String scheduleMoment;

      /**
     * 挂号费
     */
      private BigDecimal bookingFee;

      /**
     * 预约状态
     */
      private Integer status;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;
}
