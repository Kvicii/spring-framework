package org.springframework.tests.initialize;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kyushu
 * @date 2020/2/27 15:31
 * @description Bean的垃圾回收
 */
public class BeanGarbageCollectionTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册Configuration配置类
		applicationContext.register(BeanInitializationOrDestroyTest.class);
		// 启动上下文
		applicationContext.refresh();
		// 关闭上下文
		applicationContext.close();

		System.gc();
	}
}
