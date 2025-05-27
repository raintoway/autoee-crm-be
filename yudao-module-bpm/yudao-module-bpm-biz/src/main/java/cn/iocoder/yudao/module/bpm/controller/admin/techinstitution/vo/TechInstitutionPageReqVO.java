package cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 科技制度属性管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TechInstitutionPageReqVO extends PageParam {

    @Schema(description = "申请人", example = "21247")
    private Long userId;

    @Schema(description = "制度编号")
    private String institutionCode;

    @Schema(description = "制度名称", example = "李四")
    private String institutionName;

    @Schema(description = "制度年度")
    private String institutionYear;

    @Schema(description = "制度等级")
    private String institutionLevel;

    @Schema(description = "技术领域")
    private String techField;

    @Schema(description = "批准单位")
    private String approvalUnit;

    @Schema(description = "制度文号")
    private String documentNumber;

    @Schema(description = "发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishTime;

    @Schema(description = "执行时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] executeTime;

    @Schema(description = "制度密级")
    private String securityLevel;

    @Schema(description = "审批结果", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

}
