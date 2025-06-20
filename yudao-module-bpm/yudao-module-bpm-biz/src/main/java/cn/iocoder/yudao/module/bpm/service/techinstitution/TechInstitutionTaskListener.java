package cn.iocoder.yudao.module.bpm.service.techinstitution;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * 类型为 class 的 TaskListener 监听器示例
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class TechInstitutionTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("[execute][task({}) 被调用]", delegateTask.getId());
        log.info("[execute][task({}) 被调用]-传入参数={}", delegateTask.getId(), JSONUtil.toJsonStr(delegateTask));
    }

}
