package cn.iocoder.yudao.module.bpm.service.techinstitution;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo.*;
import cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution.TechInstitutionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 科技制度属性管理 Service 接口
 *
 * @author 芋道源码
 */
public interface TechInstitutionService {

    /**
     * 创建科技制度属性管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTechInstitution(Long userId, @Valid TechInstitutionSaveReqVO createReqVO);
    Long createAndSubmitTechInstitution(Long userId, @Valid TechInstitutionSaveReqVO createReqVO);

    /**
     * 更新科技制度属性管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTechInstitution(@Valid TechInstitutionSaveReqVO updateReqVO);
    void updateAndSubmitTechInstitution(Long userId, @Valid TechInstitutionSaveReqVO updateReqVO);

    /**
     * 删除科技制度属性管理
     *
     * @param id 编号
     */
    void deleteTechInstitution(Long id);

    /**
     * 获得科技制度属性管理
     *
     * @param id 编号
     * @return 科技制度属性管理
     */
    TechInstitutionDO getTechInstitution(Long id);

    /**
     * 获得科技制度属性管理分页
     *
     * @param pageReqVO 分页查询
     * @return 科技制度属性管理分页
     */
    PageResult<TechInstitutionDO> getTechInstitutionPage(TechInstitutionPageReqVO pageReqVO);

}
