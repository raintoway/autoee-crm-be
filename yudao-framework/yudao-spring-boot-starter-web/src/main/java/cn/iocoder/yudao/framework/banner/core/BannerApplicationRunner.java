package cn.iocoder.yudao.framework.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 * @author 芋道源码
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {
	@Autowired // 通过自动注入获取 Environment
	private Environment env; // 直接注入 Environment 对象

	@Override
	public void run(ApplicationArguments args) {
		ThreadUtil.execute(() -> {

			String ip = null;
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			}
			String port = env.getProperty("server.port");
			String activeEnv = env.getProperty("spring.profiles.active");
			String path = env.getProperty("server.servlet.context-path");
			String dbUrl = env.getProperty("spring.datasource.dynamic.datasource.master.url");
			dbUrl = StrUtil.subBefore(dbUrl, "?", false);
			ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
			log.info("\n    ----------------------------------------------------------\n\t" +
					"项目启动成功！\n\t" +
					"当前环境：" + activeEnv + "\n\t" +
					"Local: \t\thttp://localhost:" + port + path + "\n\t" +
					"External: \thttp://" + ip + ":" + port + path + "\n\t" +
					"----------------------------------------------------------\n\t" +
					"masterDbUrl: \t" + dbUrl + "\n\t" +
					"----------------------------------------------------------");

		});
	}

	private static boolean isNotPresent(String className) {
		return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
	}

}
