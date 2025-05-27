package cn.iocoder.yudao.module.bpm.service.techinstitution;

import cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution.TechInstitutionDO;
import cn.iocoder.yudao.module.bpm.dal.mysql.techinstitution.TechInstitutionMapper;
import cn.iocoder.yudao.module.bpm.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.event.BpmProcessInstanceStatusEventListener;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 结果的监听器实现类
 * 流程设计中直接配置：代理表达式=${techInstitutionStatusListener}
 */
@Component
@Slf4j
public class TechInstitutionStatusListener extends BpmProcessInstanceStatusEventListener implements JavaDelegate {
	@Resource
	private TechInstitutionService techInstitutionService;
	@Resource
	private TechInstitutionMapper techInstitutionMapper;

	@Override
	protected String getProcessDefinitionKey() {
		return "TechInstitutionFlow";
	}

	@Override
	protected void onEvent(BpmProcessInstanceStatusEvent event) {
		log.info("[开始]-处理流程事件-业务id={}-流程实例id={}-审核状态={}", event.getBusinessKey(), event.getId(), event.getStatus());
		techInstitutionMapper.updateById(new TechInstitutionDO().setId(Long.parseLong(event.getBusinessKey())).setStatus(event.getStatus()));
		log.info("[完成]-处理流程事件-业务id={}-流程实例id={}-审核状态={}", event.getBusinessKey(), event.getId(), event.getStatus());
	}

	@Override
	public void execute(DelegateExecution delegateExecution) {

	}
}
