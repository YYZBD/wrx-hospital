package com.markerhub.controller.admin;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.base.BaseController;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.MedicalCard;
import com.markerhub.service.MedicalCardService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/medicalCard")
public class AdminMedicalCardController extends BaseController {

	@Resource
    MedicalCardService medicalCardService;

	@GetMapping("/page")
	public Result page(MedicalCard medicalCard) {
		Page<MedicalCard> pageData = medicalCardService.page(getPage(), queryLike(medicalCard));
		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {
        MedicalCard medicalCard =medicalCardService.getById(id);
		return Result.success(medicalCard);
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody MedicalCard medicalCard) {
		if (medicalCard.getId() == null) {
            medicalCard.setCreateTime(LocalDateTime.now());
		}
		medicalCardService.saveOrUpdate(medicalCard);
		return Result.success();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids) {
		Assert.isTrue(ArrayUtil.isNotEmpty(ids), "请选择删除选项");
		medicalCardService.removeBatchByIds(List.of(ids));
		return Result.success();
	}
}
