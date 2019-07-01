package com.generator.entity;

import com.generator.annotation.TransformAnnotation;
import com.generator.enums.DBTypeEnum;
import com.generator.enums.IndexTypeEnum;
import com.generator.enums.JavaTypeEnum;
import com.generator.util.CamelCaseUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * <p>Description:[模板信息-列]</p>
 * <p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
public class ColumnTemplate extends CommonTemplate {

	public ColumnTemplate(DBTypeEnum dbTypeEnum) {
		this.dbTypeEnum = dbTypeEnum;
	}

	@TransformAnnotation(desc = "列名", infos = {"MYSQL:COLUMN_NAME", "ORACLE:COLUMN_NAME"})
	private String columnName;

	@TransformAnnotation(desc = "列顺序，从1开始", infos = {"MYSQL:ORDINAL_POSITION", "ORACLE:COLUMN_ID"})
	private Integer index;
	public void setIndex(String index) {
		if(!StringUtils.isEmpty(index)){
			this.index = Integer.parseInt(index);
		}
	}

	@TransformAnnotation(desc = "java类型", infos = {"MYSQL:DATA_TYPE", "ORACLE:DATA_TYPE"})
	private String javaType;
	public String getJavaType() {
		return JavaTypeEnum.getJavaType(dbTypeEnum, javaType, attrLength, dataScale).name();
	}

	@TransformAnnotation(desc = "列索引类型", infos = {"MYSQL:COLUMN_KEY", "ORACLE:COLUMN_KEY"})
	private IndexTypeEnum indexType;
	public String getIndexType() {
		return indexType == null ? null : indexType.name();
	}
	public void setIndexType(String columnKey) {
		for (IndexTypeEnum indexType : IndexTypeEnum.values()) {
			if (indexType.name().equals(columnKey)) {
				this.indexType = indexType;
				break;
			}
		}
	}

	@TransformAnnotation(desc = "列注释", infos = {"MYSQL:COLUMN_COMMENT", "ORACLE:COMMENTS"})
	private String columnComment;

	@TransformAnnotation(desc = "属性设置长度(整数部分),非二进制为空", infos = {"MYSQL:CHARACTER_MAXIMUM_LENGTH", "ORACLE:DATA_PRECISION"})
	private Integer attrLength;
	public void setAttrLength(String dataPrecision) {
		if(!StringUtils.isEmpty(dataPrecision)){
			this.attrLength = Integer.parseInt(dataPrecision);
		}
	}

	@TransformAnnotation(desc = "属性设置长度(小数部分),非二进制为空", infos = {"MYSQL:NUMERIC_SCALE", "ORACLE:DATA_SCALE"})
	private Integer dataScale;
	public void setDataScale(String dataScale) {
		if(!StringUtils.isEmpty(dataScale)){
			this.dataScale = Integer.parseInt(dataScale);
		}
	}

	@TransformAnnotation(desc = "列是否可空，N(不可)/Y(可空)", infos = {"MYSQL:IS_NULLABLE", "ORACLE:NULLABLE"})
	private String isNullable;
	public void setIsNullable(String isNullable) {
		this.isNullable = "YES".equals(isNullable) ? "Y" : ("NO".equals(isNullable) ? "N" : isNullable);
	}

	@TransformAnnotation(desc = "列默认值，可空", infos = {"MYSQL:COLUMN_DEFAULT", "ORACLE:DATA_DEFAULT"})
	private String dataDefault;

	private DBTypeEnum dbTypeEnum;

	/**
	 * 扩展属性：dBTypeEnum
	 */
	public String getDbTypeEnum() {
		return dbTypeEnum == null ? null : dbTypeEnum.name();
	}

	/**
	 * 扩展属性：attrName
	 */
	public String getAttrName() {
		return CamelCaseUtils.toLowerCamelCase(this.columnName);
	}
}
