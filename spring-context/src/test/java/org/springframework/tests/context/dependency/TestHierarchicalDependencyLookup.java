package org.springframework.tests.context.dependency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kyushu
 * @date 2020/3/13 22:12
 * @description 层次性依赖查找
 */
public class TestHierarchicalDependencyLookup {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory
		ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

		HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
		beanFactory.setParentBeanFactory(parentBeanFactory);
		System.out.println("当前BeanFactory的父BeanFactory:" + beanFactory.getParentBeanFactory());

		displayLocalBean(beanFactory, "user");
		displayLocalBean(parentBeanFactory, "user");

		displayContainsBean(beanFactory, "user");
		displayContainsBean(parentBeanFactory, "user");
		applicationContext.refresh();

		applicationContext.close();
	}

	private static HierarchicalBeanFactory createParentBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("classpath:/org/springframework/tests.context/beans-look-up.xml");
		return beanFactory;
	}

	private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
		System.out.printf("当前BeanFactory [%s] 是否包含bean[name: %s ]: %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
	}

	private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		System.out.printf("当前BeanFactory [%s] 是否包含bean[name: %s ]: %s\n", beanFactory, beanName, containsBean(beanFactory, beanName));
	}

	private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
		if (parentBeanFactory instanceof HierarchicalBeanFactory) {
			HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
			if (containsBean(hierarchicalBeanFactory, beanName)) {
				return true;
			}
		}
		return beanFactory.containsLocalBean(beanName);
	}
}
