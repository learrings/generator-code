package com.generator.entity.columns;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description:[数据列基础信息]</p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
public class ColumnEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 表名
	 */
	private String tableName;
}
