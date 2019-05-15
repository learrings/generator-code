package com.generator.entity.columns;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * <p>Description:[数据列信息]</p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
@Entity
public class ColumnMysqlEntity extends ColumnEntity {
	/**
	 * 表schema
	 */
	@Id
	@Column(name = "TABLE_SCHEMA")
	private String tableSchema;
	/**
	 * 表名
	 */
	@Id
	@Column(name = "TABLE_NAME")
	private String tableName;
	/**
	 * 列名
	 */
	@Id
	@Column(name = "COLUMN_NAME")
	private String columnName;
	/**
	 * 列顺序，从1开始
	 */
	@Column(name = "ORDINAL_POSITION")
	private Integer ordinalPosition;
	/**
	 * 列默认值，可空
	 */
	@Column(name = "COLUMN_DEFAULT")
	private String columnDefault;
	/**
	 * 列是否为空，NO(不可)/YES(可空)
	 */
	@Column(name = "IS_NULLABLE")
	private String isNullable;
	/**
	 * 列类型
	 */
	@Column(name = "DATA_TYPE")
	private String dataType;
	/**
	 * 列字符长度，非二进制类型为NULL
	 */
	@Column(name = "CHARACTER_MAXIMUM_LENGTH")
	private Integer characterMaximumLength;
	/**
	 * 列整型部分最大精度[ 如 int(8)->10；float(9,3)->9]，非数字类型为NULL
	 */
	@Column(name = "NUMERIC_PRECISION")
	private Integer numericPrecision;
	/**
	 * 列小数部分最大精度，非数字类型为NULL
	 */
	@Column(name = "NUMERIC_SCALE")
	private Integer numericScale;
	/**
	 * 列字段字符集名称[如 utf8]
	 */
	@Column(name = "CHARACTER_SET_NAME")
	private String characterSetName;
	/**
	 * 列字符集排序规则[如 utf8_general_ci]
	 */
	@Column(name = "COLLATION_NAME")
	private String collationName;
	/**
	 * 列字段类型[如 varchar(50)]
	 */
	@Column(name = "COLUMN_TYPE")
	private String columnType;
	/**
	 * 列索引类型[如  PRI(主键),UNI(唯一),MUL(可重复)]
	 */
	@Column(name = "COLUMN_KEY")
	private String columnKey;
	/**
	 * 列其他信息[如 auto_increment代表主键自增]
	 */
	@Column(name = "EXTRA")
	private String extra;
	/**
	 * 列权限[如 SELECT(查询), INSERT(插入), UPDATE(修改), REFERENCES(外键权限)]
	 */
	@Column(name = "PRIVILEGES")
	private String privileges;
	/**
	 * 列字段注释
	 */
	@Column(name = "COLUMN_COMMENT")
	private String columnComment;

	@Override
	public void setTableName(String tableName) {
		super.setTableName(tableName);
		this.tableName = tableName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ColumnMysqlEntity that = (ColumnMysqlEntity) o;
		return Objects.equals(tableSchema, that.tableSchema) &&
				Objects.equals(tableName, that.tableName) &&
				Objects.equals(columnName, that.columnName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tableSchema, tableName, columnName);
	}
}
