package com.generator.core;

import com.generator.constant.GeneratorConstant;
import com.generator.enums.DBTypeEnum;
import com.generator.properties.GeneratorProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:[执行sql]</p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Repository
class GeneratorRepository {

	@Resource
	JdbcTemplate jdbcTemplate;
	@Resource
	private GeneratorProperties generatorProperties;

	List<Map<String, Object>> findList(DBTypeEnum dbTypeEnum, boolean isTable) {
		return jdbcTemplate.queryForList(this.createSql(dbTypeEnum, isTable));
	}

	private String createSql(DBTypeEnum dbTypeEnum, boolean isTable) {
		StringBuilder sb = new StringBuilder(isTable ? dbTypeEnum.getTableInfoSql() : dbTypeEnum.getTableColumnSql());
		sb.append(" WHERE ").append(dbTypeEnum.getSchemaName()).append("='").append(generatorProperties.getSchema()).append("'");

		if (!StringUtils.isEmpty(generatorProperties.getGenTables())) {
			sb.append(" AND  ").append(dbTypeEnum.getTableName()).append(" IN (");
			String[] genTables = generatorProperties.getGenTables().split(GeneratorConstant.SPLIT_COMMA_TAG);
			for (String genTable : genTables) {
				sb.append("'").append(genTable).append("',");
			}
			sb.append("'')");
		}

		return sb.toString();
	}
}
