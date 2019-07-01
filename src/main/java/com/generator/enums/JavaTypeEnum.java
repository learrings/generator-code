package com.generator.enums;

import com.generator.constant.GeneratorConstant;
import lombok.Getter;

import java.util.Arrays;

/**
 * <p>Description:[数据库类型转换为java类型枚举]</p>
 * Create on 2019/5/14
 *
 * @author learrings
 */
@Getter
public enum JavaTypeEnum {
	/**
	 *
	 */
	Byte("bit,blob,longblob,mediumblob,tinyblob,varbinary", ""),
	/**
	 *
	 */
	Long("bigint,mediumint", "NUMBER(Long)"),
	/**
	 *
	 */
	String("binary,char,json,linestring,longtext,mediumtext,multilinestring,text,tinytext,varchar", "CHAR,VARCHAR2,LONG,BLOB,CLOB"),
	/**
	 *
	 */
	Integer("int,integer,multipoint,smallint,tinyint,year", "NUMBER(Integer)"),
	/**
	 *
	 */
	Date("date,datetime,time,timestamp", "DATE,TIMESTAMP(6)"),
	/**
	 *
	 */
	Double("double,numeric", "NUMBER(Double)"),
	/**
	 *
	 */
	Float("float", ""),
	/**
	 *
	 */
	BigDecimal("decimal", ""),
	/**
	 *
	 */
	Other("", ""),
	;

	String mysqlType;
	String oracleType;

	JavaTypeEnum(String mysqlType, String oracleType) {
		this.mysqlType = mysqlType;
		this.oracleType = oracleType;
	}

	public static JavaTypeEnum getJavaType(DBTypeEnum dbTypeEnum, String columnType, Integer dataPrecision, Integer dataScale) {
		columnType = createColumnType(dbTypeEnum, columnType, dataPrecision, dataScale);
		for (JavaTypeEnum javaType : values()) {
			switch (dbTypeEnum) {
				case MYSQL:
					if (Arrays.asList(javaType.getMysqlType().split(GeneratorConstant.SPLIT_COMMA_TAG)).contains(columnType)) {
						return javaType;
					}
				case ORACLE:
					if (Arrays.asList(javaType.getOracleType().split(GeneratorConstant.SPLIT_COMMA_TAG)).contains(columnType)) {
						return javaType;
					}
				default:
			}

		}
		return JavaTypeEnum.Other;
	}

	private static String createColumnType(DBTypeEnum dbTypeEnum, String columnType, Integer dataPrecision, Integer dataScale) {
		if (dbTypeEnum == DBTypeEnum.ORACLE && "NUMBER".equals(columnType)) {
			if (dataScale != null && dataScale > 0) {
				return "NUMBER(Double)";
			}
			if (dataPrecision > 11) {
				return "NUMBER(Long)";
			}
			return "NUMBER(Integer)";
		}
		return columnType;
	}
}
