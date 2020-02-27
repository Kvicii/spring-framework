package org.springframework.tests.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @author kyushu
 * @date 2020/2/19 21:24
 * @description 通过xml方式获取Bean
 */
@Import(IocContainerByAnnotation.Config.class)    // 通过@Import的方式定义Bean
public class IocContainerByAnnotation {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(IocContainerByAnnotation.class);

		registerUserBeanDefinition(applicationContext, "user-test");
		registerUserBeanDefinition(applicationContext);

		applicationContext.refresh();

		System.out.println("Config类型的所有Bean" + applicationContext.getBeansOfType(Config.class));
		System.out.println("User类型的所有Bean" + applicationContext.getBeansOfType(User.class));

		applicationContext.close();
		// findCollectionBeanByType(applicationContext);
	}

	@Component    // 通过@Component的方式定义Bean
	public static class Config {
		@Bean(value = {"user", "kyushu-user"})    // 通过@Bean的方式定义Bean
		public User user() {
			User user = new User();
			user.setUserId(1L);
			user.setName("Kyushu");
			return user;
		}
	}

	/**
	 * @param beanDefinitionRegistry
	 * @param beanName
	 */
	public static void registerUserBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, String beanName) {

		BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class).addPropertyValue("userId", 2).addPropertyValue("name", "Lili");

		if (StringUtils.hasText(beanName)) {
			// 命名方式注册Bean
			beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		} else {
			// 非命名方式注册Bean
			BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanDefinitionRegistry);
		}
	}

	public static void registerUserBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry) {
		registerUserBeanDefinition(beanDefinitionRegistry, null);
	}

	private static void findCollectionBeanByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
			System.out.println("查找对象集合" + userMap);
		}
	}
}
