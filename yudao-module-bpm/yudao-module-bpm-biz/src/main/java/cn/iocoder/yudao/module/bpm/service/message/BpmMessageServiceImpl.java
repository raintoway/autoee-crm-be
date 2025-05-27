package cn.iocoder.yudao.module.bpm.service.message;

import cn.iocoder.yudao.framework.web.config.WebProperties;
import cn.iocoder.yudao.module.bpm.convert.message.BpmMessageConvert;
import cn.iocoder.yudao.module.bpm.enums.message.BpmMessageEnum;
import cn.iocoder.yudao.module.bpm.service.message.dto.BpmMessageSendWhenProcessInstanceApproveReqDTO;
import cn.iocoder.yudao.module.bpm.service.message.dto.BpmMessageSendWhenProcessInstanceRejectReqDTO;
import cn.iocoder.yudao.module.bpm.service.message.dto.BpmMessageSendWhenTaskCreatedReqDTO;
import cn.iocoder.yudao.module.bpm.service.message.dto.BpmMessageSendWhenTaskTimeoutReqDTO;
import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import cn.iocoder.yudao.module.system.api.sms.SmsSendApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * BPM 消息通知 流程审批通知 Service 实现类
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class BpmMessageServiceImpl implements BpmMessageService {

	@Resource
	private SmsSendApi smsSendApi;
	@Resource
	private NotifyMessageSendApi notifyMessageSendApi;

	@Resource
	private WebProperties webProperties;

	@Override
	public void sendMessageWhenProcessInstanceApprove(BpmMessageSendWhenProcessInstanceApproveReqDTO reqDTO) {
		Map<String, Object> templateParams = new HashMap<>();
		templateParams.put("processInstanceName", reqDTO.getProcessInstanceName());
		templateParams.put("detailUrl", getProcessInstanceDetailUrl(reqDTO.getProcessInstanceId()));

		Long startUserId = reqDTO.getStartUserId();
		// 发短信
		// smsSendApi.sendSingleSmsToAdmin(BpmMessageConvert.INSTANCE.convert(startUserId,
		// 		BpmMessageEnum.PROCESS_INSTANCE_APPROVE.getSmsTemplateCode(), templateParams));

		// 1、构造消息
		Map<String, Object> msgMap = new HashMap<>(2);
		// msgMap.put("orderId", reqBO.getOrderId());
		// msgMap.put("deliveryMessage", reqBO.getMessage());
		// 2、发送站内信
		notifyMessageSendApi.sendSingleMessageToAdmin(
				new NotifySendSingleToUserReqDTO()
						.setUserId(startUserId)
						.setTemplateCode(BpmMessageEnum.PROCESS_INSTANCE_APPROVE.getSmsTemplateCode())
						.setTemplateParams(templateParams));

	}

	@Override
	public void sendMessageWhenProcessInstanceReject(BpmMessageSendWhenProcessInstanceRejectReqDTO reqDTO) {
		Map<String, Object> templateParams = new HashMap<>();
		templateParams.put("processInstanceName", reqDTO.getProcessInstanceName());
		templateParams.put("reason", reqDTO.getReason());
		templateParams.put("detailUrl", getProcessInstanceDetailUrl(reqDTO.getProcessInstanceId()));

		Long startUserId = reqDTO.getStartUserId();
		// smsSendApi.sendSingleSmsToAdmin(BpmMessageConvert.INSTANCE.convert(startUserId,
		// 		BpmMessageEnum.PROCESS_INSTANCE_REJECT.getSmsTemplateCode(), templateParams));

		// 1、构造消息
		// Map<String, Object> msgMap = new HashMap<>(2);
		// msgMap.put("orderId", reqBO.getOrderId());
		// msgMap.put("deliveryMessage", reqBO.getMessage());
		// 2、发送站内信
		notifyMessageSendApi.sendSingleMessageToAdmin(
				new NotifySendSingleToUserReqDTO()
						.setUserId(startUserId)
						.setTemplateCode(BpmMessageEnum.PROCESS_INSTANCE_REJECT.getSmsTemplateCode())
						.setTemplateParams(templateParams));
	}

	@Override
	public void sendMessageWhenTaskAssigned(BpmMessageSendWhenTaskCreatedReqDTO reqDTO) {
		Long assigneeUserId = reqDTO.getAssigneeUserId();

		Map<String, Object> templateParams = new HashMap<>();
		templateParams.put("processInstanceName", reqDTO.getProcessInstanceName());
		templateParams.put("taskName", reqDTO.getTaskName());
		templateParams.put("startUserNickname", reqDTO.getStartUserNickname());
		templateParams.put("detailUrl", getProcessInstanceDetailUrl(reqDTO.getProcessInstanceId()));

		// smsSendApi.sendSingleSmsToAdmin(BpmMessageConvert.INSTANCE.convert(assigneeUserId,
		// 		BpmMessageEnum.TASK_ASSIGNED.getSmsTemplateCode(), templateParams));

		// 1、构造消息
		// Map<String, Object> msgMap = new HashMap<>(2);
		// msgMap.put("orderId", reqBO.getOrderId());
		// msgMap.put("deliveryMessage", reqBO.getMessage());
		// 2、发送站内信
		notifyMessageSendApi.sendSingleMessageToAdmin(
				new NotifySendSingleToUserReqDTO()
						.setUserId(assigneeUserId)
						.setTemplateCode(BpmMessageEnum.TASK_ASSIGNED.getSmsTemplateCode())
						.setTemplateParams(templateParams));
	}

	@Override
	public void sendMessageWhenTaskTimeout(BpmMessageSendWhenTaskTimeoutReqDTO reqDTO) {
		Map<String, Object> templateParams = new HashMap<>();
		templateParams.put("processInstanceName", reqDTO.getProcessInstanceName());
		templateParams.put("taskName", reqDTO.getTaskName());
		templateParams.put("detailUrl", getProcessInstanceDetailUrl(reqDTO.getProcessInstanceId()));
		smsSendApi.sendSingleSmsToAdmin(BpmMessageConvert.INSTANCE.convert(reqDTO.getAssigneeUserId(),
				BpmMessageEnum.TASK_TIMEOUT.getSmsTemplateCode(), templateParams));
	}

	private String getProcessInstanceDetailUrl(String taskId) {
		return   "/bpm/process-instance/detail?id=" + taskId;
		// 短信
		// return webProperties.getAdminUi().getUrl() + "/bpm/process-instance/detail?id=" + taskId;
	}

}
