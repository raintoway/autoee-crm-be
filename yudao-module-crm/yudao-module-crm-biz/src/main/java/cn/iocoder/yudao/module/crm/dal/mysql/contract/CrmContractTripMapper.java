package cn.iocoder.yudao.module.crm.dal.mysql.contract;


import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.crm.dal.dataobject.contract.CrmContractProductDO;
import cn.iocoder.yudao.module.crm.dal.dataobject.contract.CrmContractTripDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 合同行程 Mapper
 *
 * @author HUIHUI
 */
@Mapper
public interface CrmContractTripMapper extends BaseMapperX<CrmContractTripDO> {


    default List<CrmContractTripDO> selectListByContractId(Long contractId) {
        return selectList(new LambdaQueryWrapperX<CrmContractTripDO>().eq(CrmContractTripDO::getContractId, contractId));
    }

}
