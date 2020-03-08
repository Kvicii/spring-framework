package org.springframework.tests.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author kyushu
 * @date 2020/2/24 18:44
 * @description
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("implements InitializingBean:UserFactory初始化中...");
	}

	// PostConstruct初始化
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct:UserFactory初始化中...");
	}

	public void initUserFactory() {
		System.out.println("@Bean-->initMethod:UserFactory初始化中...");
	}

	@PreDestroy
	public void destroyAnnotation() {
		System.out.println("@PreDestroy:UserFactory销毁中...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("implements DisposableBean:UserFactory销毁中...");
	}

	public void destroyUserFactory() {
		System.out.println("@Bean-->destroyUserFactory:UserFactory销毁中...");
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("UserFactory GC");
	}
}
