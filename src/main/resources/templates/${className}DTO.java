package ${basePackage}.${module};

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>Description:[${tableComment}]</p>
 * Create on ${.now?string["yyyy/MM/dd"]}
 *
 * @author ${author}
 */
@Getter
@Setter
public class ${className}DTO  {

<#list columnList as column>
	/**
	 * ${column.columnComment}
	 */
	private ${column.javaType} ${column.attrName};
</#list>

<#list columnList as column>
	<#if column.javaType =='Date'>
	/**
	 * ${column.columnComment}开始时间
	 */
	private ${column.javaType} ${column.attrName}Begin;
	/**
	* ${column.columnComment}结束时间
	*/
	private ${column.javaType} ${column.attrName}End;
	</#if>
</#list>
}


