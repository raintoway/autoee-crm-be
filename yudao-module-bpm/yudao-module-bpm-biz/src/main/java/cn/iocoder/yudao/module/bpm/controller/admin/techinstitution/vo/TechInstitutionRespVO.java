package cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 科技制度属性管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TechInstitutionRespVO {

	@Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4269")
	// @ExcelProperty("主键")
	private Long id;

	@Schema(description = "申请人", requiredMode = Schema.RequiredMode.REQUIRED, example = "21247")
	// @ExcelProperty("申请人")
	private Long userId;

	@Schema(description = "制度编号", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("制度编号")
	private String institutionCode;

	@Schema(description = "制度名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
	@ExcelProperty("制度名称")
	private String institutionName;

	@Schema(description = "制度年度", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("制度年度")
	private String institutionYear;

	@Schema(description = "制度等级", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty(value = "制度等级", converter = DictConvert.class)
	@DictFormat("institution_level") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
	private String institutionLevel;

	@Schema(description = "技术领域", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("技术领域")
	private String techField;

	@Schema(description = "批准单位", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("批准单位")
	private String approvalUnit;

	@Schema(description = "制度文号", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("制度文号")
	private String documentNumber;

	@Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("发布时间")
	private LocalDateTime publishTime;

	@Schema(description = "执行时间", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("执行时间")
	private LocalDateTime executeTime;

	@Schema(description = "制度密级", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty(value = "制度密级", converter = DictConvert.class)
	@DictFormat("security_level") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
	private String securityLevel;

	@Schema(description = "PDF/扫描件附件")
	@ExcelProperty("PDF/扫描件附件")
	private List<String> attachment;

	@Schema(description = "审批结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Integer status; // 参见 BpmProcessInstanceStatusEnum 枚举

	@Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
	@ExcelProperty("创建时间")
	private LocalDateTime createTime;

	/**
	 * 流程实例的编号
	 */
	private String processInstanceId;

}
