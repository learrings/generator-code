package com.generator.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransformAnnotation {
	/**
	 *  字段说明
	 */
	String desc();
	/**
	 *  注解匹配值 格式：DBTypeEnum.name:ColumnEntity/TableEntity继承类中的属性名
	 */
	String[] infos();
}
