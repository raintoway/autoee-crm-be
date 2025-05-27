package cn.iocoder.yudao.module.bpm.datapermission.config;

import cn.iocoder.yudao.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;

import cn.iocoder.yudao.module.bpm.dal.dataobject.techinstitution.TechInstitutionDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据权限 Configuration
 */
@Configuration(proxyBeanMethods = false)
public class TechInstitutionDataPermissionConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer techInstitutionDataPermissionRuleCustomizer() {
        return rule -> {
            // dept
            // rule.addDeptColumn(AdminUserDO.class);
            // rule.addDeptColumn(DeptDO.class, "id");
            // user
            rule.addUserColumn(TechInstitutionDO.class, "user_id");
        };
    }

}
