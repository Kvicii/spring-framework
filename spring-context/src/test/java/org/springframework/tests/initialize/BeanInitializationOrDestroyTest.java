package org.springframework.tests.initialize;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.tests.factory.DefaultUserFactory;
import org.springframework.tests.factory.UserFactory;

/**
 * @author kyushu
 * @date 2020/2/27 14:33
 * @description Bean的初始化
 */
@Configuration
public class BeanInitializationOrDestroyTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册Configuration配置类
		applicationContext.register(BeanInitializationOrDestroyTest.class);
		// 启动上下文
		applicationContext.refresh();
		// 非延迟加载在上下文启动完成被初始化
		System.out.println("上下文启动完成...");
		UserFactory userFactory = applicationContext.getBean(UserFactory.class);
		System.out.println(userFactory);
		// 关闭上下文
		System.out.println("上下文准备关闭...");
		applicationContext.close();
		System.out.println("上下文关闭...");
	}

	@Lazy(false)
	@Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
//	@Bean
	public UserFactory userFactory() {
		return new DefaultUserFactory();
	}
}
