package com.generator.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * <p>Description [生成器配置属性]</p>
 * Create on 2019/5/6
 *
 * @author learrings
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "generator")
public class GeneratorProperties {

	/**
	 * 模板路径
	 */
	private String templateDir;
	/**
	 * 数据库schema
	 */
	private String schema;
	/**
	 * 生成目标表，多个用逗号隔开
	 */
	private String genTables;
	/**
	 *  基础包路径
	 */
	private String basePackage;
	/**
	 * 模块
	 */
	private String module;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 输出目录，如果存在则删除
	 */
	private String outDir;

	@Override
	public  String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n\t\t数据库schema：").append(this.schema);
		if (StringUtils.isEmpty(this.genTables)) {
			sb.append("\n\t\t执行表：全部 ");
		} else {
			sb.append("\n\t\t执行表：").append(this.genTables);
		}
		sb.append("\n\t\tpackage路径：").append(this.basePackage)
				.append(".").append(this.module);

		sb.append("\n\t\t作者：").append(this.author);
		sb.append("\n\t\t输出目录：").append(this.outDir).append("\n");
		return sb.toString();
	}
}
