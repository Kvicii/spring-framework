package org.springframework.repository;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.domain.User;

import java.util.Collection;

/**
 * @author kyushu
 * @date 2020/2/16 14:45
 * @description
 */
public class UserRepository {

	private Collection<User> users;	// 自定义Bean

	private BeanFactory beanFactory;	// 内建依赖

	private ObjectFactory<ApplicationContext> objectFactory;

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public ObjectFactory<ApplicationContext> getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
		this.objectFactory = objectFactory;
	}
}
