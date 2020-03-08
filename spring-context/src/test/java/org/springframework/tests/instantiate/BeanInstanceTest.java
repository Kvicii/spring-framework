package org.springframework.tests.instantiate;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.tests.factory.DefaultUserFactory;
import org.springframework.tests.factory.UserFactory;

/**
 * @author kyushu
 * @date 2020/2/24 18:29
 * @description Bean的实例化
 */
public class BeanInstanceTest {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/services/beans-instance.xml");

		AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
		/**
		 * 静态方法实例化
		 */
		// User user = applicationContext.getBean("instance-user", User.class);
		/**
		 * 工厂实例化Bean
		 */
		// User user = applicationContext.getBean("user-factory", User.class);
		/**
		 * factoryBean方式实例化
		 */
		//User user = applicationContext.getBean("factory-user", User.class);
		/**
		 * 特殊方式实例化Bean
		 * 1.ServiceLoader
		 * 2.AutowireCapableBeanFactory
		 */
		// ServiceLoader<UserFactory> userServiceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
//		ServiceLoader<UserFactory> serviceLoader = applicationContext.getBean("user-factory-service-loader", ServiceLoader.class);
//		Iterator<UserFactory> iterator = serviceLoader.iterator();
//		while (iterator.hasNext()) {
//			UserFactory userFactory = iterator.next();
//			System.out.println(userFactory.createUser());
//		}
		// 参数不能是接口
		UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
		System.out.println(userFactory.createUser());

		// System.out.println(user);
	}
}
