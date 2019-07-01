package com.generator.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description:[com.generator.entity.template 使用注解]</p>
 * Create on 2019/5/6
 *
 * @author learrings
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransformAnnotation {
	/**
	 *  字段说明
	 */
	String desc();
	/**
	 *  注解匹配值 格式：com.generator.enums.DBTypeEnum.name:数据库字段
	 */
	String[] infos();

	/**
	 *  关联key
	 */
	boolean relevantKey() default false;
}
