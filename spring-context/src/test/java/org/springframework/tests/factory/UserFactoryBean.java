package org.springframework.tests.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.domain.User;

/**
 * @author kyushu
 * @date 2020/2/24 18:44
 * @description
 */
public class UserFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		return User.createUser();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
