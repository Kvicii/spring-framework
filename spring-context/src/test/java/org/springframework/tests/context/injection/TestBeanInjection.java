package org.springframework.tests.context.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.repository.UserRepository;

/**
 * @author kyushu
 * @date 2020/2/16 13:08
 * @description 依赖的注入
 */
public class TestBeanInjection {

	public static void main(String[] args) {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/services/beans-injection.xml");
		UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
//		System.out.println(userRepository.getUsers());
//		依赖注入
		System.out.println(userRepository.getBeanFactory());
		System.out.println(beanFactory);
		System.out.println(userRepository.getBeanFactory() == beanFactory);
//		依赖查找
//		System.out.println(beanFactory.getBean(BeanFactory.class));

		ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
		System.out.println(objectFactory.getObject());
		System.out.println(objectFactory.getObject() == beanFactory);

//		容器内建Bean
		Environment environment = beanFactory.getBean(Environment.class);
		System.out.println(environment);
	}
}
