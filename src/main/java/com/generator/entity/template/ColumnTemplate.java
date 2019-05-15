package com.generator.entity.template;

import com.generator.annotation.TransformAnnotation;
import com.generator.enums.DBTypeEnum;
import com.generator.enums.IndexTypeEnum;
import com.generator.enums.JavaTypeEnum;
import com.generator.util.CamelCaseUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Description:[模板列信息]</p>
 * <p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
public class ColumnTemplate {

	private DBTypeEnum dBTypeEnum;

	public ColumnTemplate(DBTypeEnum dBTypeEnum) {
		this.dBTypeEnum = dBTypeEnum;
	}

	@TransformAnnotation(desc = "表schema", infos = {"MYSQL:tableSchema", "ORACLE:"})
	private String tableSchema;
	@TransformAnnotation(desc = "表名", infos = {"MYSQL:tableName", "ORACLE:"})
	private String tableName;
	@TransformAnnotation(desc = "列名", infos = {"MYSQL:columnName", "ORACLE:"})
	private String columnName;
	@TransformAnnotation(desc = "列顺序，从1开始", infos = {"MYSQL:ordinalPosition", "ORACLE:"})
	private Integer index;
	@TransformAnnotation(desc = "java类型", infos = {"MYSQL:dataType", "ORACLE:"})
	private JavaTypeEnum javaType;
	@TransformAnnotation(desc = "列索引类型", infos = {"MYSQL:columnKey", "ORACLE:"})
	private IndexTypeEnum indexType;
	@TransformAnnotation(desc = "列注释", infos = {"MYSQL:columnComment", "ORACLE:"})
	private String columnComment;
	@TransformAnnotation(desc = "属性设置长度,非二进制为空", infos = {"MYSQL:characterMaximumLength", "ORACLE:"})
	private Integer attrLength;

	public String getJavaType() {
		return javaType == null ? null : javaType.name();
	}

	public void setJavaType(String dataType) {
		this.javaType = JavaTypeEnum.getJavaType(dBTypeEnum, dataType);
	}

	public String getIndexType() {
		return indexType == null ? null : indexType.name();
	}

	public void setIndexType(String columnKey) {
		for (IndexTypeEnum indexType : IndexTypeEnum.values()) {
			if(indexType.name().equals(columnKey)){
				this.indexType = indexType;
				break;
			}
		}
	}

	/**
	 * 扩展属性：dBTypeEnum
	 */
	public String getDBTypeEnum() {
		return dBTypeEnum == null ? null : dBTypeEnum.name();
	}

	/**
	 * 扩展属性：attrName
	 */
	public String getAttrName() {
		return CamelCaseUtils.toLowerCamelCase(this.columnName);
	}
}
