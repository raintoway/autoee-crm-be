package cn.iocoder.yudao.module.crm.dal.mysql.contract;


import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.crm.dal.dataobject.contract.CrmContractCostDO;
import cn.iocoder.yudao.module.crm.dal.dataobject.contract.CrmContractTripDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 合同行程 Mapper
 *
 * @author HUIHUI
 */
@Mapper
public interface CrmContractCostMapper extends BaseMapperX<CrmContractCostDO> {


    default List<CrmContractCostDO> selectListByContractId(Long contractId) {
        return selectList(new LambdaQueryWrapperX<CrmContractCostDO>().eq(CrmContractCostDO::getContractId, contractId));
    }

}
