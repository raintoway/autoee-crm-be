package cn.iocoder.yudao.module.crm.enums.contract;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CrmPaymentStatusEnum implements IntArrayValuable {
    NO_PAY(0, "未付款"),

    DEPOSIT(1, "已付定金"),

    RETAINAGE(2, "已付尾款"),
    FULL_PAYMENT(3, "已全款"),
    Refund(4, "已退款");
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CrmPaymentStatusEnum::getType).toArray();


    private final Integer type;

    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
