package org.springframework.beans.domain;


import org.springframework.beans.bean.annotation.Super;

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
