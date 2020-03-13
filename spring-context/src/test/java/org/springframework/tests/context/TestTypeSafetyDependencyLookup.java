package org.springframework.tests.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.domain.User;

/**
 * @author kyushu
 * @date 2020/3/13 22:12
 * @description 层次性依赖查找
 */
public class TestTypeSafetyDependencyLookup {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.refresh();

		// displayBeanFactoryGetBean(applicationContext);
		// displayObjectFactoryGetObject(applicationContext);
		// displayObjectProviderIfAvailable(applicationContext);
		// displayListableBeanFactoryGetBeansOfType(applicationContext);
		displayListableBeanFactoryStreamOps(applicationContext);

		applicationContext.close();
	}

	// --------------------------------------单类型查找--------------------------------------

	/**
	 * 验证BeanFactory#getBean的安全性-->不安全
	 *
	 * @param beanFactory
	 */
	private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {

		printBeansException(() -> beanFactory.getBean(User.class));
	}

	/**
	 * 验证ObjectFactory#getObject的安全性-->不安全
	 *
	 * @param applicationContext
	 */
	private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {

		ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
		printBeansException(() -> objectFactory.getObject());
	}

	/**
	 * 验证ObjectProvider#getIfAvailable的安全性-->安全
	 *
	 * @param beanFactory
	 */
	private static void displayObjectProviderIfAvailable(BeanFactory beanFactory) {
		ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
		printBeansException(() -> objectProvider.getIfAvailable());
	}

	// --------------------------------------集合类型查找--------------------------------------

	/**
	 * 安全
	 *
	 * @param applicationContext
	 */
	private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {

		printBeansException(() -> applicationContext.getBeansOfType(User.class));
	}

	/**
	 * 安全
	 *
	 * @param applicationContext
	 */
	private static void displayListableBeanFactoryStreamOps(AnnotationConfigApplicationContext applicationContext) {

		ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
		printBeansException(() -> objectProvider.forEach(System.out::println));
	}

	private static void printBeansException(Runnable runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
