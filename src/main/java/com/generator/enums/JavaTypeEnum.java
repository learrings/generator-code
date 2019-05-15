package com.generator.enums;

import com.generator.constant.GeneratorConstant;
import com.generator.entity.columns.ColumnMysqlEntity;
import com.generator.entity.tables.TableMysqlEntity;
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
	Long("bigint,mediumint", ""),
	/**
	 *
	 */
	String("binary,char,json,linestring,longtext,mediumtext,multilinestring,text,tinytext,varchar", ""),
	/**
	 *
	 */
	Integer("int,integer,multipoint,smallint,tinyint,year", ""),
	/**
	 *
	 */
	Date("date,datetime,time,timestamp", ""),
	/**
	 *
	 */
	Double("double,numeric", ""),
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
	Other("enum,geometry,geometrycollection,multipolygon,point,polygon,real,set", ""),
	;

	String mysqlType;
	String oracleType;

	JavaTypeEnum(String mysqlType, String oracleType) {
		this.mysqlType = mysqlType;
		this.oracleType = oracleType;
	}

	public static JavaTypeEnum getJavaType(DBTypeEnum dbTypeEnum, String columnType) {
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
		return null;
	}
}
