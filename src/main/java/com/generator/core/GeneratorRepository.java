package com.generator.core;

import com.generator.constant.GeneratorConstant;
import com.generator.enums.DBTypeEnum;
import com.generator.properties.GeneratorProperties;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * <p>Description:[执行sql]</p>
 * Create on 2019/5/7
 *
 * @author learrings
 */
@Repository
class GeneratorRepository {

	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private GeneratorProperties generatorProperties;

	List findTableList(DBTypeEnum dbTypeEnum) {
		Query query = entityManager.createNativeQuery(this.createSql(dbTypeEnum, true), dbTypeEnum.getTableClass());
		List list = query.getResultList();
		entityManager.close();
		return list;
	}

	List findColumnList(DBTypeEnum dbTypeEnum) {
		Query query = entityManager.createNativeQuery(this.createSql(dbTypeEnum, false), dbTypeEnum.getColumnClass());
		List list = query.getResultList();
		entityManager.close();
		return list;
	}

	private String createSql(DBTypeEnum dbTypeEnum, boolean isTable) {
		StringBuilder sb = new StringBuilder(isTable ? dbTypeEnum.getTableInfoSql() : dbTypeEnum.getTableColumnSql());
		sb.append(" WHERE ").append(dbTypeEnum.getSchemaName()).append("='").append(generatorProperties.getSchema()).append("'");
		if (StringUtils.isEmpty(generatorProperties.getGenTables())) {
			return sb.toString();
		}

		sb.append(" AND  ").append(dbTypeEnum.getTableName()).append(" IN (''");
		String[] genTables = generatorProperties.getGenTables().split(GeneratorConstant.SPLIT_COMMA_TAG);
		for (String genTable : genTables) {
			sb.append(",'").append(genTable).append("'");
		}
		sb.append(")");
		return sb.toString();
	}
}
