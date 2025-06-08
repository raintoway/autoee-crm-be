package cn.iocoder.yudao.module.crm.enums.contract;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CrmTripStatusEnum implements IntArrayValuable {
    NO_TRAVEL(0, "待出行"),

    DURING_TRIP(1, "行程中"),

    END(2, "已结束"),
    CANCEL(3, "已取消");
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CrmTripStatusEnum::getType).toArray();


    private final Integer type;

    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
