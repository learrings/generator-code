
                                                            代码生成器使用说明
 1.新增数据库类型方案
  （1）com.generator.enums.DBTypeEnum
             I. 新增枚举
  （2）com.generator.entity.columnscom.generator.entity.tables
             I. 新增并继承 ColumnEntityTableEntity
             II. 实现 super.setTableName(tableName)
  （3）com.generator.entity.template
             I. TableTemplateColumnTemplate 注解实现新增数据类型
  （4）com.generator.enums.JavaTypeEnum
             I. 实现新增数据类型的转换关系
 2.模板文件
  （1）路径：resourcetemplates下
  （2）遵守freemarker语法规范
  （3）动态参数可使用值：${TableTemplateColumnTemplate.get方法}，可自行扩展
 3.application.yml
   （1）数据库修改项：spring.datasource.urlusernamepassworddriver-class-name
   （2）generator
                  template-dir:模板位置[resourcetemplates]
                  schema:数据库
                  gen-tables:多个用逗号隔开；不配代表数据库下所有表
                  base-package:${basePackage} 动态参数，一般用于package路径
                  module:${module} 动态参数，一般用于package路径
                  author:${author} 动态参数，一般用于作者注释
                  out-dir:代码生成后的输出路径

