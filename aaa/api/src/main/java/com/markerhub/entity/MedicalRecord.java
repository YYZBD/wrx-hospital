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
 * 就诊记录表
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Getter
@Setter
  @TableName("app_medical_record")
public class MedicalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 就诊编号
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
     * 预约ID
     */
      private Long bookingId;

    private String bookingSn;

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
     * 诊断结果
     */
      private String diagnosis;

      /**
     * 治疗方案
     */
      private String treatment;

      /**
     * 就诊日期
     */
      private LocalDateTime recordTime;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;
}
