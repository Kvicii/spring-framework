package org.springframework.domain;

import org.springframework.context.annotation7.Super;

/**
 * @author kyushu
 * @date 2020/2/16 14:02
 * @description
 */
@Super
public class SuperUser extends User {

	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}



	@Override
	public String toString() {
		return "SuperUser{" +
				"level='" + level + '\'' +
				'}';
	}
}
