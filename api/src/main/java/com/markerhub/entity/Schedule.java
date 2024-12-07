package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 医生排班表
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Getter
@Setter
  @TableName("app_schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 医生ID
     */
      private Long doctorId;

      /**
     * 科室ID
     */
      private Long departmentId;

      /**
     * 排班日期
     */
      private LocalDate scheduleDate;

      /**
     * 上午或下午
     */
      private String moment;

      /**
     * 就诊数量
     */
      private Integer quantity;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;
}
