package cn.iocoder.yudao.module.crm.enums.trip;

import java.util.Arrays;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CrmTripType implements IntArrayValuable {
    // 跨境
    CROSS_BORDER(0, "跨境"),
    PICK_UP_AIRPORT(1, "接机"),

    // 送机
    SEND_AIRPORT(2, "送机"),
    // 包车
    PACKAGE_CAR(3, "包车");
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CrmTripType::getType).toArray();


    private final Integer type;

    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
