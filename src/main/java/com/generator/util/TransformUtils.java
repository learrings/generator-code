package com.generator.util;

import com.generator.annotation.TransformAnnotation;
import com.generator.constant.GeneratorConstant;
import com.generator.entity.ColumnTemplate;
import com.generator.entity.TableTemplate;
import com.generator.enums.DBTypeEnum;
import com.generator.properties.GeneratorProperties;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Description:[代码生成器转换工具]</p>
 * Create on 2019/5/6
 *
 * @author learrings
 */
@Slf4j
public class TransformUtils {

	private static Map<String, String> tableAnnotationMap = Maps.newHashMap();

	private static Map<String, String> columnAnnotationMap = Maps.newHashMap();

	private static String RELEVANT_KEY;

	public static List<TableTemplate> getTemplateParam(List<Map<String, Object>> tableList, List<Map<String, Object>> columnList,
													   GeneratorProperties generatorProperties, DBTypeEnum dbTypeEnum) {
		annotationValue(TableTemplate.class, tableAnnotationMap, dbTypeEnum);
		annotationValue(ColumnTemplate.class, columnAnnotationMap, dbTypeEnum);

		List<TableTemplate> templateModelList = Lists.newArrayList();

		Map<String, List<Map<String, Object>>> columnMap = columnList.stream().collect(Collectors.groupingBy(column -> column.get(RELEVANT_KEY).toString()));

		for (Map<String, Object> table : tableList) {
			TableTemplate templateTable = new TableTemplate(generatorProperties);
			copyObj(table, templateTable, tableAnnotationMap, dbTypeEnum);
			List<ColumnTemplate> templateColumnList = columnMap.get(templateTable.getTableName()).stream().map(column -> {
				ColumnTemplate templateColumn = new ColumnTemplate(dbTypeEnum);
				copyObj(column, templateColumn, columnAnnotationMap, dbTypeEnum);
				return templateColumn;
			}).collect(Collectors.toList());

			templateTable.setColumnList(templateColumnList);

			templateModelList.add(templateTable);
		}

		return templateModelList;
	}

	/**
	 * <p>Description:[根据注解，对象转换]</p>
	 * Create on 2019/5/13
	 *
	 * @param oldObj  - 数据库对象
	 * @param newObj  - 模板对象
	 * @param isTable - 是否表
	 */
	private static void copyObj(Map<String, Object> dataObj, Object templateObj, Map<String, String> annotationMap, DBTypeEnum dbTypeEnum) {
		String newFieldName;
		Method newMethod;
		try {
			for (Map.Entry<String, Object> data : dataObj.entrySet()) {
				newFieldName = annotationMap.get(dbTypeEnum.name() + GeneratorConstant.SPLIT_COLON_TAG + data.getKey());
				if (StringUtils.isEmpty(newFieldName) || data.getValue() == null) {
					continue;
				}

				newMethod = templateObj.getClass().getMethod("set" + newFieldName.substring(0, 1).toUpperCase() + newFieldName.substring(1)
						, String.class);

				newMethod.invoke(templateObj, String.valueOf(data.getValue()));
			}
		} catch (Exception e) {
			log.error("对象转换异常：{}", e.getMessage());
		}
	}

	/**
	 * <p>Description:[获取转换注解]</p>
	 * Create on 2019/5/13
	 *
	 * @param annotationMap -是否表对象
	 */
	private static void annotationValue(Class clazz, Map<String, String> annotationMap, DBTypeEnum dbTypeEnum) {
		annotationMap.clear();

		Field[] fields = FieldUtils.getAllFields(clazz);
		for (Field field : fields) {
			if (!field.isAnnotationPresent(TransformAnnotation.class)) {
				continue;
			}
			TransformAnnotation annotation = field.getAnnotation(TransformAnnotation.class);
			Stream.of(annotation.infos()).filter(info -> info.contains(dbTypeEnum.name() + GeneratorConstant.SPLIT_COLON_TAG)).forEach(info -> {
				annotationMap.put(info, field.getName());
				if (annotation.relevantKey() && StringUtils.isEmpty(RELEVANT_KEY)) {
					RELEVANT_KEY = info.replace(dbTypeEnum.name() + GeneratorConstant.SPLIT_COLON_TAG, "");
				}
			});
		}
	}
}
