package com.generator.entity.tables;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description:[数据表基础信息]</p>
 * <p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
public class TableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 表名
	 */
	private String tableName;
}
