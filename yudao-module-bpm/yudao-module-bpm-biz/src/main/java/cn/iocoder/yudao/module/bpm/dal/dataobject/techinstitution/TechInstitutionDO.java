package cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution;

import cn.iocoder.yudao.framework.mybatis.core.type.StringListTypeHandler;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 科技制度属性管理 DO
 *
 * @author 芋道源码
 */
@TableName(value ="bpm_tech_institution", autoResultMap = true)
@KeySequence("bpm_tech_institution_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechInstitutionDO extends BaseDO {

    /**
     * 请假表单主键
     */
    @TableId
    private Long id;
    /**
     * 申请人
     */
    private Long userId;
    /**
     * 制度编号
     */
    private String institutionCode;
    /**
     * 制度名称
     */
    private String institutionName;
    /**
     * 制度年度
     */
    private String institutionYear;
    /**
     * 制度等级
     *
     * 枚举 {@link TODO institution_level 对应的类}
     */
    private String institutionLevel;
    /**
     * 技术领域
     */
    private String techField;
    /**
     * 批准单位
     */
    private String approvalUnit;
    /**
     * 制度文号
     */
    private String documentNumber;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 执行时间
     */
    private LocalDateTime executeTime;
    /**
     * 制度密级
     *
     * 枚举 {@link TODO security_level 对应的类}
     */
    private String securityLevel;
    /**
     * PDF/扫描件附件
     */
	@TableField(typeHandler = StringListTypeHandler.class)
    private List<String> attachment;
    /**
     * 审批结果
     */
    private Integer status;
    /**
     * 流程实例的编号
     */
    private String processInstanceId;

}
