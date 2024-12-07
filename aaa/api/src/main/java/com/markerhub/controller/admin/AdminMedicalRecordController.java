package com.markerhub.controller.admin;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.base.BaseController;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.MedicalRecord;
import com.markerhub.service.MedicalRecordService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/medicalRecord")
public class AdminMedicalRecordController extends BaseController {

	@Resource
    MedicalRecordService medicalRecordService;

	@GetMapping("/page")
	public Result page(MedicalRecord medicalRecord) {
		Page<MedicalRecord> pageData = medicalRecordService.page(getPage(), queryLike(medicalRecord));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
        MedicalRecord medicalRecord =medicalRecordService.getById(id);
		return Result.success(medicalRecord);
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody MedicalRecord medicalRecord) {
		if (medicalRecord.getId() == null) {
            medicalRecord.setCreateTime(LocalDateTime.now());
		}
		medicalRecordService.saveOrUpdate(medicalRecord);
		return Result.success();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids) {
		Assert.isTrue(ArrayUtil.isNotEmpty(ids), "请选择删除选项");
		medicalRecordService.removeBatchByIds(List.of(ids));
		return Result.success();
	}
}
