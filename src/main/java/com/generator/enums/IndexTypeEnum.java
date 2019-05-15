package com.generator.enums;

import lombok.Getter;

/**
 * <p>Description:[索引类型枚举]</p>
 * Create on 2019/5/14
 *
 * @author learrings
 */
@Getter
public enum IndexTypeEnum {
	/**
	 * 主键
	 */
	PRI,
	/**
	 * 唯一
	 */
	UNI,
	/**
	 * 其他
	 */
	OTHER;
}
