package cn.iocoder.yudao.module.bpm.dal.mysql.techinstitution;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution.TechInstitutionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 科技制度属性管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TechInstitutionMapper extends BaseMapperX<TechInstitutionDO> {

    default PageResult<TechInstitutionDO> selectPage(TechInstitutionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TechInstitutionDO>()
                .eqIfPresent(TechInstitutionDO::getUserId, reqVO.getUserId())
                .eqIfPresent(TechInstitutionDO::getInstitutionCode, reqVO.getInstitutionCode())
                .likeIfPresent(TechInstitutionDO::getInstitutionName, reqVO.getInstitutionName())
                .eqIfPresent(TechInstitutionDO::getInstitutionYear, reqVO.getInstitutionYear())
                .eqIfPresent(TechInstitutionDO::getInstitutionLevel, reqVO.getInstitutionLevel())
                .eqIfPresent(TechInstitutionDO::getTechField, reqVO.getTechField())
                .eqIfPresent(TechInstitutionDO::getApprovalUnit, reqVO.getApprovalUnit())
                .eqIfPresent(TechInstitutionDO::getDocumentNumber, reqVO.getDocumentNumber())
                .betweenIfPresent(TechInstitutionDO::getPublishTime, reqVO.getPublishTime())
                .betweenIfPresent(TechInstitutionDO::getExecuteTime, reqVO.getExecuteTime())
                .eqIfPresent(TechInstitutionDO::getSecurityLevel, reqVO.getSecurityLevel())
                .eqIfPresent(TechInstitutionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(TechInstitutionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TechInstitutionDO::getId));
    }


	@Select("SELECT current_seq FROM institution_code_year_seq WHERE year = #{year}")
    Integer selectCurrentSeq(@Param("year") String year);

    @Update("UPDATE institution_code_year_seq SET current_seq = current_seq + 1 WHERE year = #{year}")
    void incrementCurrentSeq(@Param("year") String year);

}
