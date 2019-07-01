package com.generator.entity;

import com.generator.annotation.TransformAnnotation;
import com.generator.properties.GeneratorProperties;
import com.generator.util.CamelCaseUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>Description:[模板信息-表]</p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
public class TableTemplate extends CommonTemplate {

	public TableTemplate(GeneratorProperties generatorProperties) {
		this.basePackage = generatorProperties.getBasePackage();
		this.module = generatorProperties.getModule();
		this.author = generatorProperties.getAuthor();
	}


	@TransformAnnotation(desc = "表描述", infos = {"MYSQL:TABLE_COMMENT", "ORACLE:COMMENTS"})
	private String tableComment;
	@TransformAnnotation(desc = "表类型", infos = {"MYSQL:TABLE_TYPE", "ORACLE:TABLE_TYPE"})
	private String tableType;

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
		return CamelCaseUtils.toUpperCamelCase(super.getTableName());
	}

}
