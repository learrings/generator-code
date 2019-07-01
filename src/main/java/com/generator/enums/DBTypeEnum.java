package com.generator.enums;

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
			, "SELECT TABLE_SCHEMA,TABLE_NAME,TABLE_TYPE,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES"
			, "SELECT TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,ORDINAL_POSITION,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT" +
			",CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS"
			, "TABLE_SCHEMA"
			, "TABLE_NAME"),
	/**
	 * Oracle数据库
	 */
	ORACLE("oracle.jdbc.OracleDriver"
			, "SELECT A.OWNER,A.TABLE_NAME,A.TABLE_TYPE,A.COMMENTS FROM ALL_TAB_COMMENTS A"
			, "SELECT A.OWNER,A.TABLE_NAME,A.COLUMN_NAME,A.COLUMN_ID,A.DATA_TYPE,DECODE(C.POSITION,'1','PRI') AS COLUMN_KEY" +
			",B.COMMENTS,A.DATA_PRECISION, A.DATA_LENGTH-A.CHAR_LENGTH AS DATA_LENGTH,A.DATA_SCALE,A.NULLABLE" +
			",A.DATA_DEFAULT FROM ALL_TAB_COLUMNS A INNER JOIN ALL_COL_COMMENTS B ON A.TABLE_NAME=B.TABLE_NAME AND A.COLUMN_NAME=B.COLUMN_NAME" +
			" LEFT JOIN ALL_CONSTRAINTS D ON D.TABLE_NAME=A.TABLE_NAME  AND D.CONSTRAINT_TYPE='P' LEFT JOIN ALL_CONS_COLUMNS C ON C.CONSTRAINT_NAME=D.CONSTRAINT_NAME" +
			" AND C.COLUMN_NAME=A.COLUMN_NAME"
			, "A.OWNER"
			, "A.TABLE_NAME"),
	/**
	 * 其他数据库
	 */
	OTHER(""
			, ""
			, ""
			, ""
			, "");

	String driverClassName;
	String tableInfoSql;
	String tableColumnSql;
	String schemaName;
	String tableName;
	Class tableClass;
	Class columnClass;

	DBTypeEnum(String driverClassName, String tableInfoSql, final String tableColumnSql,
			   final String schemaName, final String tableName) {
		this.driverClassName = driverClassName;
		this.tableInfoSql = tableInfoSql;
		this.tableColumnSql = tableColumnSql;
		this.schemaName = schemaName;
		this.tableName = tableName;
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
