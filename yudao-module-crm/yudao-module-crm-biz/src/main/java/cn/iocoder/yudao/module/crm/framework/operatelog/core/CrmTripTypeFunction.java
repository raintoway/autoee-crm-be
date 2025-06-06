package cn.iocoder.yudao.module.crm.framework.operatelog.core;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.module.crm.enums.DictTypeConstants;

import com.mzt.logapi.service.IParseFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CrmTripTypeFunction implements IParseFunction {

    public static final String NAME = "getTripType";

    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }
        return DictFrameworkUtils.getDictDataLabel(DictTypeConstants.CRM_TRIP_TYPE, value.toString());
    }

    @Override
    public String functionName() {
        return NAME;
    }


}