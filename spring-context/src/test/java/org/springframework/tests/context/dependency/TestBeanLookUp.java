package org.springframework.tests.context.dependency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation7.Super;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.domain.User;

import java.util.Map;

/**
 * @author kyushu
 * @date 2020/2/16 13:08
 * @description 依赖的查找
 */
public class TestBeanLookUp {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/org/springframework/tests.context/beans-look-up.xml");

//		findBeanByName(beanFactory);
//		findBeanByLazyTime(beanFactory);
//		findBeanByType(beanFactory);
//		findCollectionBeanByType(beanFactory);
		findBeanByAnnotation(beanFactory);
	}

	private static void findBeanByAnnotation(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			Map<String, User> userMap = (Map) ((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Super.class);
			System.out.println(userMap);
		}
	}

	private static void findCollectionBeanByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
			System.out.println("查找对象集合" + userMap);
		}
	}

	private static void findBeanByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		System.out.println("类型查找：" + user);
	}

	public static void findBeanByName(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean("user");
		System.out.println("实时查找：" + user);
	}

	public static void findBeanByLazyTime(BeanFactory beanFactory) {
		ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
		User user = objectFactory.getObject();
		System.out.println("延迟查找：" + user);
	}
}
