package ${table.packageName}.mappers;

import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import ${table.packageName}.entities.${table.entityName};

public interface ${table.entityName}Mapper {

	public ${table.entityName} findById(${table.pkColumn.classType} ${table.pkColumn.name});
	
	public List<${table.entityName}> listAll();

	public void insertOrUpdate(${table.entityName} ${table.entityName?uncap_first});
	
	public void batchInsertOrUpdate(
			@Param("${table.entityName?uncap_first}s") List<${table.entityName}> ${table.entityName?uncap_first}s);

	public void delete(${table.pkColumn.classType} ${table.pkColumn.name});
	
	public void batchDelete(Set<${table.pkColumn.classType}> ${table.pkColumn.name}s);

	public long countAll();
	
	<#if (querys)??>
	<#list querys as query>
	public <#if (query.count)>long<#else>List<${query.table.entityName}></#if> <#if (query.funcName)??>${query.funcName}<#else>query${query_index}</#if>(<#assign hasCondition=false><#if (query.conditions)??><#list query.conditions as condition><#if (hasCondition)>, </#if>@Param("${condition.column.name}") String ${condition.column.name}<#assign hasCondition=true></#list></#if><#if (query.associations)??><#list query.associations as association><#if (association.conditions)??><#list association.conditions as condition><#if (hasCondition)>, </#if>@Param("${condition.column.name}") String ${condition.column.name}<#assign hasCondition=true></#list></#if></#list></#if><#if (!query.count)><#if (query.needOrderBy)><#if (hasCondition)>, </#if><#assign hasCondition=true>@Param("orderSortName") String orderSortName, @Param("orderAsc") boolean orderAsc</#if><#if (query.needPaging)><#if (hasCondition)>, </#if><#assign hasCondition=true>@Param("offset") Integer offset, @Param("limit") Integer limit</#if></#if>);
	
	</#list>
	</#if>
}
