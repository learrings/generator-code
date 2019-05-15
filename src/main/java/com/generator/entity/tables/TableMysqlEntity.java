package com.generator.entity.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>Description:[数据表信息]</p>
 * <p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
@Entity
public class TableMysqlEntity extends TableEntity {
	/**
	 * 表schema
	 */
	@Column(name = "TABLE_SCHEMA")
	private String tableSchema;
	/**
	 * 表名
	 */
	@Id
	@Column(name = "TABLE_NAME")
	private String tableName;
	/**
	 * 表类型
	 */
	@Column(name = "TABLE_TYPE")
	private String tableType;
	/**
	 * 表数据量
	 */
	@Column(name = "TABLE_ROWS")
	private Long tableRows;
	/**
	 * 表描述
	 */
	@Column(name = "TABLE_COMMENT")
	private String tableComment;

	@Override
	public void setTableName(String tableName) {
		super.setTableName(tableName);
		this.tableName = tableName;
	}
}
