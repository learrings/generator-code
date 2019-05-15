**代码生成器使用说明**       
==============                           
　支持数据库扩展，模板参数扩展，目前只编写了`MYSQL`部分的实现

## **目录**
* [代码结构](#代码结构)
* [基础配置](#基础配置)
* [程序运行](#程序运行)
* [模板配置/扩展](#模板配置/扩展)
* [扩展数据库类型](#扩展数据库类型)


## 代码结构
|-com.generator<br/>
　　|-annotation　　　　　　数据库字段转换为模板参数注解包<br/>
　　|-constant　 　 　 　　　常量包<br/>
　　|-core 　 　　　　　　 　转换核心包<br/>
　　|-entity 　 　 　　 　 　　数据库对象、模板对象<br/>
　　|-enums 　 　 　　　　　枚举包<br/>
　　|-properties 　 　　　　　yml参数包<br/>
　　|-util 　 　　　　　　　　工具包<br/>
　　|-GeneratorApplication 　 springboot启动类<br/>

## 基础配置(yml)
　（1）数据库修改项：spring.datasource.url/username/password/driver-class-name<br/>
　（2）自定义修改行：<br/>
　　　　generator<br/>
　　　　　　template-dir：模板位置[resource/templates]<br/>
　　　　　　schema：数据库<br/>
　　　　　　gen-tables：多个用逗号隔开；不配代表数据库下所有表<br/>
　　　　　　base-package：${basePackage} 动态参数，一般用于package路径<br/>
　　　　　　module：${module} 动态参数，一般用于package路径<br/>
　　　　　　author：${author} 动态参数，一般用于作者注释<br/>
　　　　　　out-dir：代码生成后的输出路径，输出路径会<font color=Red>先物理删除，切记!!!</font><br/>

## 程序运
　GeneratorApplication-GeneratorComponent.run

## 模板配置/扩展
　（1）模板路径：resource/templates下<br/>
　（2）遵守freemarker语法规范<br/>
　（3）动态参数可使用值：${TableTemplate/ColumnTemplate.get方法}，可自行扩展<br/>
　（4）新增数据类型的转换关系<br/>

## 扩展数据库类型
　（1）新增枚举<br/>
 　 　 　com.generator.enums.DBTypeEnum<br/>
　（2）新增数据库对象<br/>
 　 　 　com.generator.entity.columns/com.generator.entity.tables<br/>
　　　　　　I.新增并继承 ColumnEntity/TableEntity<br/>
　　　　　　II. 实现 super.setTableName(tableName)<br/>
　（3）注解实现新增数据类型转换<br/>
 　 　 　com.generator.entity.template.TableTemplate/ColumnTemplate <br/>
　（4）新增数据类型的转换关系<br/>
  　 　 　com.generator.enums.JavaTypeEnum<br/>
