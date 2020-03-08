package org.springframework.beans.domain;

/**
 * @author kyushu
 * @date 2020/2/16 13:10
 * @description 测试Bean的查找
 */
public class User {

	private Long userId;

	private String name;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				'}';
	}
}
