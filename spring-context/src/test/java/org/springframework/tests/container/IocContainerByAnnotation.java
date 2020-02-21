package org.springframework.tests.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.domain.User;

import java.util.Map;

/**
 * @author kyushu
 * @date 2020/2/19 21:24
 * @description 通过xml方式获取Bean
 */
public class IocContainerByAnnotation {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(IocContainerByAnnotation.class);
		applicationContext.refresh();
		findCollectionBeanByType(applicationContext);
	}

	@Bean
	public User user() {
		User user = new User();
		user.setUserId(1L);
		user.setName("Kyushu");
		return user;
	}

	private static void findCollectionBeanByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
			System.out.println("查找对象集合" + userMap);
		}
	}
}
