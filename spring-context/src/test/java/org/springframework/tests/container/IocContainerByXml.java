package org.springframework.tests.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation7.Super;
import org.springframework.domain.User;

import java.util.Map;

/**
 * @author kyushu
 * @date 2020/2/19 21:24
 * @description 通过xml方式获取Bean
 */
public class IocContainerByXml {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		int num = reader.loadBeanDefinitions("classpath:/org/springframework/tests.context/beans-look-up.xml");
		System.out.println("bean的个数：" + num);
		findCollectionBeanByType(beanFactory);
	}

	private static void findCollectionBeanByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
			System.out.println("查找对象集合" + userMap);
		}
	}
}
