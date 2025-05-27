package cn.iocoder.yudao.module.bpm.convert.message;

import cn.iocoder.yudao.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T19:00:33+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 1.8.0_432 (Tencent)"
)
public class BpmMessageConvertImpl implements BpmMessageConvert {

    @Override
    public SmsSendSingleToUserReqDTO convert(Long userId, String templateCode, Map<String, Object> templateParams) {
        if ( userId == null && templateCode == null && templateParams == null ) {
            return null;
        }

        SmsSendSingleToUserReqDTO smsSendSingleToUserReqDTO = new SmsSendSingleToUserReqDTO();

        smsSendSingleToUserReqDTO.setUserId( userId );
        smsSendSingleToUserReqDTO.setTemplateCode( templateCode );
        Map<String, Object> map = templateParams;
        if ( map != null ) {
            smsSendSingleToUserReqDTO.setTemplateParams( new LinkedHashMap<String, Object>( map ) );
        }

        return smsSendSingleToUserReqDTO;
    }
}
