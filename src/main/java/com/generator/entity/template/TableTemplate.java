package com.generator.entity.template;

import com.generator.annotation.TransformAnnotation;
import com.generator.util.CamelCaseUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>Description:[模板信息]</p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
public class TableTemplate {

	@TransformAnnotation(desc = "表schema", infos = {"MYSQL:tableSchema", "ORACLE:"})
	private String tableSchema;
	@TransformAnnotation(desc = "表名", infos = {"MYSQL:tableName", "ORACLE:"})
	private String tableName;
	@TransformAnnotation(desc = "表描述", infos = {"MYSQL:tableComment", "ORACLE:"})
	private String tableComment;
	@TransformAnnotation(desc = "表类型", infos = {"MYSQL:tableType", "ORACLE:"})
	private String tableType;
	@TransformAnnotation(desc = "表数据量", infos = {"MYSQL:tableRows", "ORACLE:"})
	private Long tableRows;
	/**
	 * 下属列组
	 */
	private List<ColumnTemplate> columnList;
	/**
	 * yml属性：basePackage
	 */
	private String basePackage;
	/**
	 * yml属性：module
	 */
	private String module;
	/**
	 * yml属性：author
	 */
	private String author;

	/**
	 * 扩展属性：className
	 */
	public String getClassName() {
		return CamelCaseUtils.toUpperCamelCase(this.tableName);
	}

}
