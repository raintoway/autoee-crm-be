package cn.iocoder.yudao.module.crm.controller.admin.contract.vo.contract;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.module.crm.framework.operatelog.core.CrmBusinessParseFunction;
import cn.iocoder.yudao.module.crm.framework.operatelog.core.CrmContactParseFunction;
import cn.iocoder.yudao.module.crm.framework.operatelog.core.CrmCustomerParseFunction;
import cn.iocoder.yudao.module.crm.framework.operatelog.core.SysAdminUserParseFunction;

import cn.iocoder.yudao.module.crm.enums.DictTypeConstants;
import com.mzt.logapi.starter.annotation.DiffLogField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM 合同创建/更新 Request VO")
@Data
public class CrmContractSaveReqVO {

    @Schema(description = "合同编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10430")
    private Long id;

    @Schema(description = "合同名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @DiffLogField(name = "合同名称")
    @NotNull(message = "合同名称不能为空")
    private String name;

    @Schema(description = "客户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "18336")
    @DiffLogField(name = "客户", function = CrmCustomerParseFunction.NAME)
    @NotNull(message = "客户编号不能为空")
    private Long customerId;

    @Schema(description = "商机编号", example = "10864")
    @DiffLogField(name = "商机", function = CrmBusinessParseFunction.NAME)
    private Long businessId;

    @Schema(description = "负责人的用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17144")
    @DiffLogField(name = "负责人", function = SysAdminUserParseFunction.NAME)
    @NotNull(message = "负责人不能为空")
    private Long ownerUserId;

    @Schema(description = "下单日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @DiffLogField(name = "下单日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotNull(message = "下单日期不能为空")
    private LocalDateTime orderDate;

    @Schema(description = "开始时间")
    @DiffLogField(name = "开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @DiffLogField(name = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    @Schema(description = "整单折扣", requiredMode = Schema.RequiredMode.REQUIRED, example = "55.00")
    @DiffLogField(name = "整单折扣")
    @NotNull(message = "整单折扣不能为空")
    private BigDecimal discountPercent;

    @Schema(description = "合同金额", example = "5617")
    @DiffLogField(name = "合同金额")
    private BigDecimal totalPrice;

    @Schema(description = "客户签约人编号", example = "18546")
    @DiffLogField(name = "客户签约人", function = CrmContactParseFunction.NAME)
    private Long signContactId;

    @Schema(description = "公司签约人", example = "14036")
    @DiffLogField(name = "公司签约人", function = SysAdminUserParseFunction.NAME)
    private Long signUserId;

    @Schema(description = "备注", example = "你猜")
    @DiffLogField(name = "备注")
    private String remark;

    @DictFormat(DictTypeConstants.CRM_TRIP_STATUS)
    private Integer tripStatus;

    @DictFormat(DictTypeConstants.CRM_PAYMENT_STATUS)
    private Integer paymentStatus;

    @Schema(description = "产品列表")
    private List<Product> products;

    @Schema(description = "行程列表")
    private List<Trip> trips;

    @Schema(description = "费用列表")
    private List<Cost> costs;

    @Schema(description = "产品列表")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20529")
        @NotNull(message = "产品编号不能为空")
        private Long productId;

        @Schema(description = "产品单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "123.00")
        @NotNull(message = "产品单价不能为空")
        private BigDecimal productPrice;

        @Schema(description = "合同价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "123.00")
        @NotNull(message = "合同价格不能为空")
        private BigDecimal contractPrice;

        @Schema(description = "产品数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "8911")
        @NotNull(message = "产品数量不能为空")
        private Integer count;

    }


    @Schema(description = "行程列表")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Trip {

        private  Long id;

        private Long contractId;

        @Schema(description = "行程信息", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @NotNull(message = "行程信息不能为空")
        private String tripInfo;

        @Schema(description = "行程类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @NotNull(message = "行程类型不能为空")
        @DictFormat(DictTypeConstants.CRM_TRIP_TYPE)
        private Integer type;

        @Schema(description = "航班号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private String flightNo;

        @Schema(description = "出发时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
        private LocalDateTime startTime;

        @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
        private LocalDateTime endTime;

        @Schema(description = "乘客人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Integer passengerNum;

        private BigDecimal price;

        @Schema(description = "车辆型号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private String carModel;
        
        @Schema(description = "备注", example = "你猜")
        private String remark;
    }



    @Schema(description = "费用列表")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cost extends Trip {
        private BigDecimal costPrice;

        @DictFormat(DictTypeConstants.CRM_COST_TYPE)
        private Integer costType;

        private  String costRemark;

        private List<String> costImages;
    }
}
