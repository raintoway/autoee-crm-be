package cn.iocoder.yudao.module.bpm.controller.admin.techinstitution;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.bpm.enums.task.BpmTaskStatusEnum;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo.*;
import cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution.TechInstitutionDO;
import cn.iocoder.yudao.module.bpm.service.techinstitution.TechInstitutionService;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;

@Tag(name = "管理后台 - 科技制度属性管理")
@RestController
@RequestMapping("/bpm/tech-institution")
@Validated
@DataPermission(enable = false)
public class TechInstitutionController {

	@Resource
	private TechInstitutionService techInstitutionService;

	@Resource
	private PermissionApi permissionApi;

	@PostMapping("/create")
	@Operation(summary = "创建科技制度属性管理")
	@PreAuthorize("@ss.hasPermission('bpm:tech-institution:create')")
	public CommonResult<Long> createTechInstitution(@Valid @RequestBody TechInstitutionSaveReqVO createReqVO) {
		return success(techInstitutionService.createTechInstitution(getLoginUserId(), createReqVO));
	}

	@PostMapping("/createAndSubmit")
	@Operation(summary = "创建科技制度属性管理")
	@PreAuthorize("@ss.hasPermission('bpm:tech-institution:create')")
	public CommonResult<Long> createAndSubmitTechInstitution(@Valid @RequestBody TechInstitutionSaveReqVO createReqVO) {
		return success(techInstitutionService.createAndSubmitTechInstitution(getLoginUserId(), createReqVO));
	}

	@PutMapping("/update")
	@Operation(summary = "更新科技制度属性管理")
	@PreAuthorize("@ss.hasPermission('bpm:tech-institution:update')")
	public CommonResult<Boolean> updateTechInstitution(@Valid @RequestBody TechInstitutionSaveReqVO updateReqVO) {
		techInstitutionService.updateTechInstitution(updateReqVO);
		return success(true);
	}

	@PutMapping("/updateAndSubmit")
	@Operation(summary = "更新科技制度属性管理")
	@PreAuthorize("@ss.hasPermission('bpm:tech-institution:update')")
	public CommonResult<Boolean> updateAndSubmitTechInstitution(@Valid @RequestBody TechInstitutionSaveReqVO updateReqVO) {
		techInstitutionService.updateAndSubmitTechInstitution(getLoginUserId(), updateReqVO);
		return success(true);
	}

	@DeleteMapping("/delete")
	@Operation(summary = "删除科技制度属性管理")
	@Parameter(name = "id", description = "编号", required = true)
	@PreAuthorize("@ss.hasPermission('bpm:tech-institution:delete')")
	public CommonResult<Boolean> deleteTechInstitution(@RequestParam("id") Long id) {
		techInstitutionService.deleteTechInstitution(id);
		return success(true);
	}

	@GetMapping("/get")
	@Operation(summary = "获得科技制度属性管理")
	@Parameter(name = "id", description = "编号", required = true, example = "1024")
	// @PreAuthorize("@ss.hasPermission('bpm:tech-institution:query')")
	public CommonResult<TechInstitutionRespVO> getTechInstitution(@RequestParam("id") Long id) {
		TechInstitutionDO techInstitution = techInstitutionService.getTechInstitution(id);

		boolean techCommUserRole = permissionApi.hasAnyRoles(getLoginUserId(), "techCommUserRole");
		if (techCommUserRole) {
			if (StrUtil.equals(techInstitution.getSecurityLevel(), "210") || StrUtil.equals(techInstitution.getSecurityLevel(), "220")) {
				techInstitution.setAttachment(null);
			}
		}
		return success(BeanUtils.toBean(techInstitution, TechInstitutionRespVO.class));
	}

	@GetMapping("/page")
	@Operation(summary = "获得科技制度属性管理分页")
	// @PreAuthorize("@ss.hasPermission('bpm:tech-institution:query')")
	@DataPermission(enable = true)
	public CommonResult<PageResult<TechInstitutionRespVO>> getTechInstitutionPage(@Valid TechInstitutionPageReqVO pageReqVO) {
		Long loginUserId = getLoginUserId();
		boolean techAdminRole = permissionApi.hasAnyRoles(loginUserId, "techAdminRole");
		if (techAdminRole) {
			pageReqVO.setUserId(loginUserId);
		}

		boolean techCommUserRole = permissionApi.hasAnyRoles(loginUserId, "techCommUserRole");
		if (techCommUserRole) {
			pageReqVO.setStatus(BpmTaskStatusEnum.APPROVE.getStatus());
		}
		PageResult<TechInstitutionDO> pageResult = techInstitutionService.getTechInstitutionPage(pageReqVO);
		if (techCommUserRole) {
			for (TechInstitutionDO techInstitutionDO : pageResult.getList()) {
				if (StrUtil.equals(techInstitutionDO.getSecurityLevel(), "210") || StrUtil.equals(techInstitutionDO.getSecurityLevel(), "220")) {
					techInstitutionDO.setAttachment(null);
				}
			}
		}

		return success(BeanUtils.toBean(pageResult, TechInstitutionRespVO.class));
	}

	@GetMapping("/export-excel")
	@Operation(summary = "导出科技制度属性管理 Excel")
	@PreAuthorize("@ss.hasPermission('bpm:tech-institution:export')")
	@ApiAccessLog(operateType = EXPORT)
	@DataPermission(enable = true)
	public void exportTechInstitutionExcel(@Valid TechInstitutionPageReqVO pageReqVO,
	                                       HttpServletResponse response) throws IOException {
		pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);

		Long loginUserId = getLoginUserId();
		boolean techAdminRole = permissionApi.hasAnyRoles(loginUserId, "techAdminRole");
		if (techAdminRole) {
			pageReqVO.setUserId(loginUserId);
		}

		boolean techCommUserRole = permissionApi.hasAnyRoles(loginUserId, "techCommUserRole");
		if (techCommUserRole) {
			pageReqVO.setStatus(BpmTaskStatusEnum.APPROVE.getStatus());
		}

		List<TechInstitutionDO> list = techInstitutionService.getTechInstitutionPage(pageReqVO).getList();
		// 导出 Excel
		ExcelUtils.write(response, "科技制度属性管理.xls", "数据", TechInstitutionRespVO.class,
				BeanUtils.toBean(list, TechInstitutionRespVO.class));
	}

}
