package cn.iocoder.yudao.module.bpm.convert.definition;

import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionRespVO;
import cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmProcessDefinitionInfoDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T19:00:33+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 1.8.0_432 (Tencent)"
)
public class BpmProcessDefinitionConvertImpl implements BpmProcessDefinitionConvert {

    @Override
    public void copyTo(BpmProcessDefinitionInfoDO from, BpmProcessDefinitionRespVO to) {
        if ( from == null ) {
            return;
        }

        to.setIcon( from.getIcon() );
        to.setDescription( from.getDescription() );
        to.setModelType( from.getModelType() );
        to.setFormType( from.getFormType() );
        to.setFormId( from.getFormId() );
        to.setFormConf( from.getFormConf() );
        if ( to.getFormFields() != null ) {
            List<String> list = from.getFormFields();
            if ( list != null ) {
                to.getFormFields().clear();
                to.getFormFields().addAll( list );
            }
            else {
                to.setFormFields( null );
            }
        }
        else {
            List<String> list = from.getFormFields();
            if ( list != null ) {
                to.setFormFields( new ArrayList<String>( list ) );
            }
        }
        to.setFormCustomCreatePath( from.getFormCustomCreatePath() );
        to.setFormCustomViewPath( from.getFormCustomViewPath() );
        to.setSimpleModel( from.getSimpleModel() );
        to.setSort( from.getSort() );
    }
}
