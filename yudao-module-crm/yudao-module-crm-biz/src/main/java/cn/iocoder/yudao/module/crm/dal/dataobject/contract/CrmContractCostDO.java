package cn.iocoder.yudao.module.crm.dal.dataobject.contract;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@TableName(value = "crm_contract_cost", autoResultMap = true)
@KeySequence("crm_contract_cost_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmContractCostDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 合同id
     */
    private Long contractId;

    /**
     * 订单编号
     */
    private String no;
    /**
     * 
     */
    private String tripInfo;

    private Integer type;

    private String flightNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    private Integer passengerNum;

    private String carModel;

    private BigDecimal price;

    private String remark;

    private Integer costType;

    private BigDecimal costPrice;

    private String costRemark;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> costImages;
    
}
