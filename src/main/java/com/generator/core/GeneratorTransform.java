package com.generator.core;

import com.generator.annotation.TransformAnnotation;
import com.generator.constant.GeneratorConstant;
import com.generator.entity.columns.ColumnEntity;
import com.generator.entity.tables.TableEntity;
import com.generator.entity.template.ColumnTemplate;
import com.generator.entity.template.TableTemplate;
import com.generator.enums.DBTypeEnum;
import com.generator.properties.GeneratorProperties;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Description:[代码生成器转换类]</p>
 * Create on 2019/5/6
 *
 * @author learrings
 */
@Slf4j
public class GeneratorTransform {

	@Resource
	protected GeneratorProperties generatorProperties;

	DBTypeEnum dbTypeEnum;

	List<TableTemplate> templateModelList;

	private Map<String, String> tableAnnotationMap;

	private Map<String, String> columnAnnotationMap;


	void getTemplateParam(List<TableEntity> tableList, List<ColumnEntity> columnList) {

		templateModelList = Lists.newArrayList();

		Map<String, List<ColumnEntity>> columnMap = columnList.stream().collect(Collectors.groupingBy(ColumnEntity::getTableName));

		for (TableEntity table : tableList) {
			TableTemplate templateTable = new TableTemplate();
			this.copyObj(table, templateTable, true);
			List<ColumnTemplate> templateColumnList = columnMap.get(table.getTableName()).stream().map(column -> {
				ColumnTemplate templateColumn = new ColumnTemplate(dbTypeEnum);
				this.copyObj(column, templateColumn, false);
				return templateColumn;
			}).collect(Collectors.toList());

			templateTable.setColumnList(templateColumnList);
			templateTable.setBasePackage(generatorProperties.getBasePackage());
			templateTable.setModule(generatorProperties.getModule());
			templateTable.setAuthor(generatorProperties.getAuthor());
			templateModelList.add(templateTable);
		}
	}

	/**
	 * <p>Description:[根据注解，对象转换]</p>
	 * Create on 2019/5/13
	 *
	 * @param oldObj  - 数据库对象
	 * @param newObj  - 模板对象
	 * @param isTable - 是否表
	 */
	private void copyObj(Object dataObj, Object templateObj, boolean isTable) {
		String newFieldName;
		Method newMethod;
		Field[] oldFields;
		try {
			oldFields = dataObj.getClass().getDeclaredFields();
			for (Field oldField : oldFields) {
				oldField.setAccessible(true);
				newFieldName = this.annotationValue(isTable).get(dbTypeEnum.name() + GeneratorConstant.SPLIT_COLON_TAG + oldField.getName());
				if (StringUtils.isEmpty(newFieldName) || oldField.get(dataObj) == null) {
					continue;
				}

				newMethod = templateObj.getClass().getMethod("set" + newFieldName.substring(0, 1).toUpperCase() + newFieldName.substring(1)
						, oldField.getType());
				newMethod.invoke(templateObj, oldField.get(dataObj));
			}
		} catch (Exception e) {
			log.error("对象转换异常：{}", e.getMessage());
		}
	}

	/**
	 * <p>Description:[获取转换注解]</p>
	 * Create on 2019/5/13
	 *
	 * @param isTable -是否表对象
	 */
	private Map<String, String> annotationValue(boolean isTable) {
		if (isTable && tableAnnotationMap != null) {
			return tableAnnotationMap;
		} else if (!isTable && columnAnnotationMap != null) {
			return columnAnnotationMap;
		}

		Field[] fields = isTable ? TableTemplate.class.getDeclaredFields() : ColumnTemplate.class.getDeclaredFields();

		Map<String, String> annotationMap = Maps.newHashMap();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(TransformAnnotation.class)) {
				continue;
			}
			TransformAnnotation annotation = field.getAnnotation(TransformAnnotation.class);
			Stream.of(annotation.infos()).forEach(info -> annotationMap.put(info, field.getName()));
		}

		if (isTable) {
			tableAnnotationMap = annotationMap;
		} else {
			columnAnnotationMap = annotationMap;
		}
		return annotationMap;
	}
}
