package org.springframework.beans.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author kyushu
 * @date 2020/2/20 23:23
 * @description Bean的构建
 */
public class BeanDefinitionCreation {

	public static void main(String[] args) {

		// 1.通过BeanDefinitionBuilder构建
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		// 属性设置
		beanDefinitionBuilder.addPropertyValue("name", "Kyushu");
		beanDefinitionBuilder.addPropertyValue("userId", 26);
		// get
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		// BeanDefinition并为Bean的终态 可以修改

		// 2.通过AbstractBeanDefinition以及派生类构建
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		// propertyValues.addPropertyValue("name", "Kyushu");
		// propertyValues.addPropertyValue("userId", 26);
		// 通过builder模式
		propertyValues.add("name", "Kyushu")
				.add("userId", 26);
		genericBeanDefinition.setPropertyValues(propertyValues);
	}
}
