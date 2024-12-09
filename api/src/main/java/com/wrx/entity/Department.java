package com.wrx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 科室表
 * </p>
 *
 * @author 一只会喵的布偶猫
 * @since 2024-12-07
 */
@Getter
@Setter
  @TableName("app_department")
public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 上级科室
     */
      private Long parentId;

      /**
     * 科室名称
     */
      private String name;

      /**
     * 科室介绍
     */
      private String introduce;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      // 把当前科室的子集全部放在children中
      // 形成点击上面对应的科室，下面展示该科室对应的子科室效果
      @TableField(exist = false)
      List<Department> children = new ArrayList<>();
}
