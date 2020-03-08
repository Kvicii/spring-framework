package org.springframework.tests.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.domain.User;

/**
 * @author kyushu
 * @date 2020/2/24 18:44
 * @description
 */
public class UserFactoryBean implements FactoryBean<User> {

	private String userInfo;

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public User getObject() throws Exception {

		String[] strings = userInfo.split(",");
		User user = new User();
		user.setUserId(Long.valueOf(strings[0]));
		user.setName(strings[1]);
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
