package com.generator.core;


import com.generator.constant.GeneratorConstant;
import com.generator.entity.template.TableTemplate;
import com.generator.enums.DBTypeEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

/**
 * <p>Description:[核心生成器]</p>
 * Create on 2019/5/6
 *
 * @author learrings
 */
@Component
public class GeneratorComponent extends GeneratorTransform {

	@Resource
	protected GeneratorRepository generatorRepository;

	@Resource
	private DataSourceProperties dataSourceProperties;

	private Configuration configuration;

	@PostConstruct
	public void run() {
		try {
			System.out.println("===>代码生成器运行开始。");
			this.checkConfig();
			this.initConfig();
			this.getData();
			this.renderTemplates();
			this.autoOpenDir();
			System.out.println("===>代码生成器运行结束。");
		} catch (Exception e) {
			System.err.println("===>代码生成器运行异常：" + e.getMessage() + "，结束。");
		}
	}

	private void checkConfig() throws Exception {
		System.out.println("===>代码生成器运行中：No.1 检查配置中...");

		if (generatorProperties == null || dataSourceProperties == null) {
			throw new Exception("generator/spring配置文件未生效，请检查application.yml");
		}

		if (StringUtils.isEmpty(generatorProperties.getSchema())
				|| StringUtils.isEmpty(generatorProperties.getBasePackage())
				|| StringUtils.isEmpty(generatorProperties.getModule())
				|| StringUtils.isEmpty(generatorProperties.getAuthor())
				|| StringUtils.isEmpty(generatorProperties.getOutDir())
		) {
			throw new Exception("generator.schema/basePackage/module/author/outDir 未配置，请检查application.yml");
		}

		if (StringUtils.isEmpty(dataSourceProperties.getUrl())
				|| StringUtils.isEmpty(dataSourceProperties.getDriverClassName())
				|| StringUtils.isEmpty(dataSourceProperties.getUsername())
				|| StringUtils.isEmpty(dataSourceProperties.getPassword())
		) {
			throw new Exception("spring.datasource.url/driver-class-name/username/password 未配置，请检查application.yml");
		}

		dbTypeEnum = DBTypeEnum.getDBType(dataSourceProperties.getDriverClassName());
		if (dbTypeEnum == null) {
			throw new Exception("spring.driver-class-name 无法识别，请在com.generator.core.GeneratorTransform.DBTypeEnum中扩展");
		}
	}

	private void initConfig() throws Exception {
		System.out.println("===>代码生成器运行中：No.2 初始化配置中...");

		System.out.println("\n\t初始化信息如下：" + generatorProperties.toString());

		//模板引擎
		configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setDefaultEncoding(GeneratorConstant.DEFAULT_ENCODING);
		configuration.setDirectoryForTemplateLoading(
				new File(this.getClass().getResource(GeneratorConstant.DEFAULT_ROOT).getFile(), generatorProperties.getTemplateDir() + GeneratorConstant.DEFAULT_ROOT));

		// 输出目录
		File dir = new File(generatorProperties.getOutDir());
		FileUtils.deleteDirectory(dir);
		if (!dir.mkdir()) {
			throw new Exception("重新创建模板目录异常|1.请关闭生成目录文件 2.目录是否可创建|");
		}
	}

	private void getData() throws Exception {
		System.out.println("===>代码生成器运行中：No.3 获取表、列信息中...");
		List tableList = generatorRepository.findTableList(dbTypeEnum);
		List columnList = generatorRepository.findColumnList(dbTypeEnum);
		if (CollectionUtils.isEmpty(tableList) || CollectionUtils.isEmpty(columnList)) {
			throw new Exception("未查询到需要代码生成的表结构");
		}
		super.getTemplateParam(tableList, columnList);
	}

	private void renderTemplates() throws Exception {
		System.out.println("===>代码生成器运行中：No.4 装配文件中...");

		StringBuilder errorMsg = new StringBuilder();
		Collection<File> fileList = FileUtils.listFiles(new File(this.getClass().getResource(GeneratorConstant.DEFAULT_ROOT).getFile(),
				generatorProperties.getTemplateDir() + GeneratorConstant.DEFAULT_ROOT), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (TableTemplate templateModel : templateModelList) {
			fileList.forEach((File file) -> {
				try {
					StringWriter fileName = new StringWriter();
					Template fileTemplate = new Template("渲染文件名称...", new StringReader(file.getName()), configuration);
					fileTemplate.process(templateModel, fileName);
					Template template = configuration.getTemplate(file.getName());
					Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(generatorProperties.getOutDir() + File.separator + fileName),
							GeneratorConstant.DEFAULT_ENCODING));
					template.process(templateModel, out);
					System.out.println("\t[" + fileName + "]装配成功");
				} catch (IOException | TemplateException e) {
					errorMsg.append(e.getMessage());
					e.printStackTrace();
				}
			});
		}

		if (errorMsg.length() > 0) {
			throw new Exception(errorMsg.insert(0, "装配文件失败：").toString());
		}
	}

	private void autoOpenDir() throws Exception {
		System.out.println("===>代码生成器运行中：No.5 打开文件夹中...");
		String osName = System.getProperty("os.name");
		if (StringUtils.isEmpty(osName)) {
			throw new Exception("无法识别系统，请自行打开文件夹");
		}

		if (osName.contains("Mac")) {
			Runtime.getRuntime().exec("open " + generatorProperties.getOutDir());
		} else if (osName.contains("Windows")) {
			Runtime.getRuntime().exec("cmd /c start " + generatorProperties.getOutDir());
		}
	}
}
