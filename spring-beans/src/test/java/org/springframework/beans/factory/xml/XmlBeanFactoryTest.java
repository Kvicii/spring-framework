package org.springframework.beans.factory.xml;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author kyushu
 * @date 2019/11/9 17:58
 * @description XmlBeanFactory测试类
 */
public class XmlBeanFactoryTest {

	@Test
	public void useXmlBeanFacory() {
		ClassPathResource resource = new ClassPathResource("beans.xml");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(resource);
	}
}
