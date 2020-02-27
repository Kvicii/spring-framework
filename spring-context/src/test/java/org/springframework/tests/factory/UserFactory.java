package org.springframework.tests.factory;

import org.springframework.domain.User;

/**
 * @author kyushu
 * @date 2020/2/24 18:44
 * @description
 */
public interface UserFactory {

	default User createUser() {
		return User.createUser();
	}
}
