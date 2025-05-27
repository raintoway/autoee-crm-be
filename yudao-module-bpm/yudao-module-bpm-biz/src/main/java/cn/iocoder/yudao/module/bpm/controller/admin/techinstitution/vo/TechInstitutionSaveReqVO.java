package cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 科技制度属性管理新增/修改 Request VO")
@Data
public class TechInstitutionSaveReqVO {

	@Schema(description = "请假表单主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4269")
	private Long id;

	@Schema(description = "申请人", requiredMode = Schema.RequiredMode.REQUIRED, example = "21247")
	@NotNull(message = "申请人不能为空")
	private Long userId;

	// @Schema(description = "制度编号", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "制度编号不能为空")
	private String institutionCode;

	@Schema(description = "制度名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
	@NotEmpty(message = "制度名称不能为空")
	private String institutionName;

	// @Schema(description = "制度年度", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "制度年度不能为空")
	private String institutionYear;

	// @Schema(description = "制度等级", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "制度等级不能为空")
	private String institutionLevel;

	// @Schema(description = "技术领域", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "技术领域不能为空")
	private String techField;
	//
	// @Schema(description = "批准单位", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "批准单位不能为空")
	private String approvalUnit;

	// @Schema(description = "制度文号", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "制度文号不能为空")
	private String documentNumber;

	// @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotNull(message = "发布时间不能为空")
	private LocalDateTime publishTime;

	// @Schema(description = "执行时间", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotNull(message = "执行时间不能为空")
	private LocalDateTime executeTime;

	// @Schema(description = "制度密级", requiredMode = Schema.RequiredMode.REQUIRED)
	// @NotEmpty(message = "制度密级不能为空")
	private String securityLevel;

	@Schema(description = "PDF/扫描件附件")
	private List<String> attachment;

	/**
	 * 流程实例的编号
	 */
	private String processInstanceId;

}
