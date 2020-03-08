package org.springframework.tests.factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kyushu
 * @date 2020/2/29 19:57
 * @description 测试FactoryBean
 */
public class FactoryBeanTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:org/springframework/tests.context/beans-look-up.xml");

		System.out.println(applicationContext.getBean("&userFactoryBean"));

	}
}
