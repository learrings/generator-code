package com.generator.entity;

import com.generator.annotation.TransformAnnotation;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description:[模板信息-公共]</p>
 * <p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Getter
@Setter
class CommonTemplate {

	@TransformAnnotation(desc = "表schema", infos = {"MYSQL:TABLE_SCHEMA", "ORACLE:OWNER"})
	private String tableSchema;
	@TransformAnnotation(desc = "表名", infos = {"MYSQL:TABLE_NAME", "ORACLE:TABLE_NAME"}, relevantKey = true)
	private String tableName;
}
