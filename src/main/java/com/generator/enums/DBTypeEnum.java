package com.generator.enums;

import com.generator.entity.columns.ColumnMysqlEntity;
import com.generator.entity.tables.TableMysqlEntity;
import lombok.Getter;

/**
 * <p>Description:[查询数据库表结构sql枚举]</p>
 * Create on 2019/5/14
 *
 * @author learrings
 */
@Getter
public enum DBTypeEnum {
	/**
	 * MySql数据库
	 */
	MYSQL("com.mysql.cj.jdbc.Driver"
			, "SELECT TABLE_SCHEMA,TABLE_NAME,TABLE_TYPE,TABLE_ROWS,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES"
			, "SELECT TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,ORDINAL_POSITION,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE" +
			",CHARACTER_MAXIMUM_LENGTH,NUMERIC_PRECISION,NUMERIC_SCALE,CHARACTER_SET_NAME,COLLATION_NAME,COLUMN_TYPE,COLUMN_KEY" +
			",EXTRA,PRIVILEGES,COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS"
			, "TABLE_SCHEMA"
			, "TABLE_NAME"
			, TableMysqlEntity.class
			, ColumnMysqlEntity.class),
	/**
	 * Oracle数据库
	 */
	ORACLE("oracle.jdbc.driver.OracleDriver"
			, ""
			, ""
			, ""
			, ""
			, null
			, null),
	/**
	 * 其他数据库
	 */
	OTHER(""
			, ""
			, ""
			, ""
			, ""
			, null
			, null);

	String driverClassName;
	String tableInfoSql;
	String tableColumnSql;
	String schemaName;
	String tableName;
	Class tableClass;
	Class columnClass;

	DBTypeEnum(String driverClassName, String tableInfoSql, final String tableColumnSql,
			   final String schemaName, final String tableName, Class tableClass, Class columnClass) {
		this.driverClassName = driverClassName;
		this.tableInfoSql = tableInfoSql;
		this.tableColumnSql = tableColumnSql;
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.tableClass = tableClass;
		this.columnClass = columnClass;
	}

	public static DBTypeEnum getDBType(String driverClassName) {
		for (DBTypeEnum db : values()) {
			if (driverClassName.equals(db.getDriverClassName())) {
				return db;
			}
		}
		return null;
	}
}
