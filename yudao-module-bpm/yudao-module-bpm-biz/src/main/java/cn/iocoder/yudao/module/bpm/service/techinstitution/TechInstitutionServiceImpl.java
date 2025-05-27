package cn.iocoder.yudao.module.bpm.service.techinstitution;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.bpm.dal.dataobject.oa.BpmOALeaveDO;
import cn.iocoder.yudao.module.bpm.enums.task.BpmTaskStatusEnum;
import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.module.bpm.controller.admin.techinstitution.vo.*;
import cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution.TechInstitutionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.bpm.dal.mysql.techinstitution.TechInstitutionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.bpm.enums.ErrorCodeConstants.*;

/**
 * 科技制度属性管理 Service 实现类
 * @author 芋道源码
 */
@Service
@Validated
public class TechInstitutionServiceImpl implements TechInstitutionService {

	@Resource
	private TechInstitutionMapper techInstitutionMapper;
	@Resource
	private BpmProcessInstanceApi processInstanceApi;
	@Resource
	private NotifyMessageSendApi notifyMessageSendApi;

	@Override
	public Long createTechInstitution(Long userId, TechInstitutionSaveReqVO createReqVO) {
		// 插入
		TechInstitutionDO techInstitution = BeanUtils.toBean(createReqVO, TechInstitutionDO.class)
				.setUserId(userId).setStatus(BpmTaskStatusEnum.NOT_START.getStatus());

		String institutionName = techInstitution.getInstitutionName();
		if (checkInstitutionNameExists(institutionName, -1)) {
			throw exception(new ErrorCode(1_009_015_000, "制度名称[" + institutionName + "]已经存在！请进行修改。"));
		}

		String year = DateUtil.format(new Date(), "yyyy");
		// 获取并递增当年序号
		Integer currentSeq = techInstitutionMapper.selectCurrentSeq(year);
		currentSeq = (currentSeq == null) ? 1 : currentSeq + 1;
		techInstitutionMapper.incrementCurrentSeq(year);
		// 格式化序号并生成机构代码
		String seqPart = StrUtil.fillBefore(currentSeq + "", '0', 3);
		techInstitution.setInstitutionCode("KZD" + year + seqPart);

		techInstitutionMapper.insert(techInstitution);

		// 返回
		return techInstitution.getId();
	}

	@Override
	public Long createAndSubmitTechInstitution(Long userId, TechInstitutionSaveReqVO createReqVO) {
		// 插入
		TechInstitutionDO techInstitution = BeanUtils.toBean(createReqVO, TechInstitutionDO.class)
				.setUserId(userId).setStatus(BpmTaskStatusEnum.RUNNING.getStatus());

		String institutionName = techInstitution.getInstitutionName();
		if (checkInstitutionNameExists(institutionName, -1)) {
			throw exception(new ErrorCode(1_009_015_000, "制度名称[" + institutionName + "]已经存在！请进行修改。"));
		}

		String year = DateUtil.format(new Date(), "yyyy");
		// 获取并递增当年序号
		Integer currentSeq = techInstitutionMapper.selectCurrentSeq(year);
		currentSeq = (currentSeq == null) ? 1 : currentSeq + 1;
		techInstitutionMapper.incrementCurrentSeq(year);
		// 格式化序号并生成机构代码
		String seqPart = StrUtil.fillBefore(currentSeq + "", '0', 3);
		techInstitution.setInstitutionCode("KZD" + year + seqPart);

		techInstitutionMapper.insert(techInstitution);

		// 发起 BPM 流程
		Map<String, Object> processInstanceVariables = new HashMap<>();
		String processInstanceId = processInstanceApi.createProcessInstance(userId,
				new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey("TechInstitutionFlow")
						.setVariables(processInstanceVariables).setBusinessKey(String.valueOf(techInstitution.getId()))
				// .setStartUserSelectAssignees(createReqVO.getStartUserSelectAssignees())
		                                                                   );

		// 将工作流的编号，更新到 OA 请假单中
		techInstitutionMapper.updateById(new TechInstitutionDO().setId(techInstitution.getId()).setProcessInstanceId(processInstanceId));

		// // 1、构造消息
		// Map<String, Object> msgMap = new HashMap<>(2);
		// // msgMap.put("orderId", reqBO.getOrderId());
		// // msgMap.put("deliveryMessage", reqBO.getMessage());
		// // TODO 看下模版
		// // 2、发送站内信
		// notifyMessageSendApi.sendSingleMessageToAdmin(
		// 		new NotifySendSingleToUserReqDTO()
		// 				.setUserId(1L)
		// 				.setTemplateCode("techInstitutionAuditNotify")
		// 				.setTemplateParams(msgMap));

		// 返回
		return techInstitution.getId();
	}

	private boolean checkInstitutionNameExists(String institutionName, long id) {
		List<TechInstitutionDO> list = techInstitutionMapper.selectList("institution_Name", institutionName);
		if (list.size() > 0) {
			if (-1 != id) { // 修改操作排除掉自己的名称
				if (list.get(0).getId() != id) {
					return true;
				}
			} else {
				// 新增操作
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateTechInstitution(TechInstitutionSaveReqVO updateReqVO) {
		// 校验存在
		validateTechInstitutionExists(updateReqVO.getId());

		String institutionName = updateReqVO.getInstitutionName();
		if (checkInstitutionNameExists(institutionName, updateReqVO.getId())) {
			throw exception(new ErrorCode(1_009_015_000, "制度名称[" + institutionName + "]已经存在！请进行修改。"));
		}

		// 更新
		TechInstitutionDO updateObj = BeanUtils.toBean(updateReqVO, TechInstitutionDO.class);
		if (null == updateObj.getStatus()) {
			updateObj.setStatus(BpmTaskStatusEnum.NOT_START.getStatus());
		}
		techInstitutionMapper.updateById(updateObj);
	}

	@Override
	public void updateAndSubmitTechInstitution(Long userId, TechInstitutionSaveReqVO updateReqVO) {
		// 校验存在
		validateTechInstitutionExists(updateReqVO.getId());

		String institutionName = updateReqVO.getInstitutionName();
		if (checkInstitutionNameExists(institutionName, updateReqVO.getId())) {
			throw exception(new ErrorCode(1_009_015_000, "制度名称[" + institutionName + "]已经存在！请进行修改。"));
		}

		// 更新
		TechInstitutionDO updateObj = BeanUtils.toBean(updateReqVO, TechInstitutionDO.class);
		techInstitutionMapper.updateById(updateObj);

		// 发起 BPM 流程
		Map<String, Object> processInstanceVariables = new HashMap<>();
		String processInstanceId = processInstanceApi.createProcessInstance(userId,
				new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey("TechInstitutionFlow")
						.setVariables(processInstanceVariables).setBusinessKey(String.valueOf(updateReqVO.getId()))
				// .setStartUserSelectAssignees(createReqVO.getStartUserSelectAssignees())
		                                                                   );

		// 将工作流的编号，更新到 OA 请假单中
		techInstitutionMapper.updateById(new TechInstitutionDO()
				.setId(updateReqVO.getId())
				.setProcessInstanceId(processInstanceId)
				.setStatus(BpmTaskStatusEnum.RUNNING.getStatus()));

	}

	@Override
	public void deleteTechInstitution(Long id) {
		// 校验存在
		validateTechInstitutionExists(id);
		// 逻辑删除
		// techInstitutionMapper.deleteById(id);
		// 修改为物理删除
		techInstitutionMapper.deleteById(id, false);
	}

	private void validateTechInstitutionExists(Long id) {
		if (techInstitutionMapper.selectById(id) == null) {
			throw exception(TECH_INSTITUTION_NOT_EXISTS);
		}
	}

	@Override
	public TechInstitutionDO getTechInstitution(Long id) {
		return techInstitutionMapper.selectById(id);
	}

	@Override
	public PageResult<TechInstitutionDO> getTechInstitutionPage(TechInstitutionPageReqVO pageReqVO) {
		return techInstitutionMapper.selectPage(pageReqVO);
	}

}
