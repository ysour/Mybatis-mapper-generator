<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${table.packageName}.mappers.${table.entityName}Mapper">

	<resultMap type="${table.entityName}" id="${table.entityName?uncap_first}ResultMap">
		<id property="${table.pkColumn.name}" column="${table.pkColumn.dbName}" />
		<#if (table.columns)??>
		<#list table.columns as column>
        <result property="${column.name}" column="${column.dbName}" />
       	</#list>
       	</#if>
       	<#if (associations)??>
		<#list associations as association>
		<association property="${association.property}" javaType="${association.aTable.entityName}"
				columnPrefix="${association.alias}_" resultMap="${association.aTable.packageName}.mappers.${association.aTable.entityName}Mapper.${association.aTable.entityName?uncap_first}ResultMap" />
		</#list>
		</#if>
	</resultMap>
	
	<select id="findById" parameterType="${table.pkColumn.classType}" resultMap="${table.entityName?uncap_first}ResultMap">
		select ${table.pkColumn.dbName}<#if (table.columns)??><#list table.columns as column>, ${column.dbName}</#list></#if>
		from ${table.tableName}
		where ${table.tableName}.${table.pkColumn.dbName} = ${r"#{"}${table.pkColumn.name}${r"}"}
	</select>
	
	<select id="listAll" resultMap="${table.entityName?uncap_first}ResultMap">
		select ${table.pkColumn.dbName}<#if (table.columns)??><#list table.columns as column>, ${column.dbName}</#list></#if>
		from ${table.tableName}
	</select>
	
	<update id="insertOrUpdate" parameterType="${table.entityName}">
		SET FOREIGN_KEY_CHECKS = 0;
		replace into ${table.tableName} set
		${table.pkColumn.dbName}=${r"#{"}${table.pkColumn.name}${r"}"}<#if (table.columns)??><#list table.columns as column>, ${column.dbName}=${r"#{"}${column.name}${r"}"}</#list></#if>;
		SET FOREIGN_KEY_CHECKS = 1;
	</update>
	
	<update id="batchInsertOrUpdate" parameterType="${table.entityName}">
		SET FOREIGN_KEY_CHECKS = 0;
		<foreach collection="${table.entityName?uncap_first}s" item="${table.entityName}?uncap_first" separator=";" index="index">
		replace into ${table.tableName} set ${table.pkColumn.dbName}=${r"#{"}${table.entityName?uncap_first}.${table.pkColumn.name}${r"}"}<#if (table.columns)??><#list table.columns as column>, ${column.dbName}=${r"#{"}${table.entityName}.${column.name}${r"}"}</#list></#if>;
		</foreach>
		SET FOREIGN_KEY_CHECKS = 1;
	</update>
	
	<delete id="delete" parameterType="${table.pkColumn.classType}">
		delete from ${table.tableName}
		where ${table.tableName}.${table.pkColumn.dbName} = ${r"#{"}${table.pkColumn.name}${r"}"}
	</delete>
	
	<delete id="batchDelete" parameterType="${table.pkColumn.classType}">
		delete from ${table.tableName}
		where ${table.tableName}.${table.pkColumn.dbName} in <foreach item="${table.pkColumn.name}" index="index" collection="${table.pkColumn.name}s" open="(" separator="," close=")">${r"#{id}"}</foreach>
	</delete>
	
	<select id="countAll" resultType="long">
		select count(*) from ${table.tableName}
	</select>
	
	<#if (querys)??>
	<#list querys as query>
	<select id="<#if (query.funcName)??>${query.funcName}<#else>query${query_index}</#if>" <#if (query.count)>resultType<#else>resultMap</#if>="<#if (query.count)>long<#else>${query.table.entityName?uncap_first}ResultMap</#if>">
		select 
		<#if (query.count)>
		count(${query.table.tableName}.${query.table.pkColumn.dbName})
		<#else>
		${query.table.pkColumn.dbName}<#if (query.table.columns)??><#list query.table.columns as column>, ${column.dbName}</#list></#if>
		<#if (query.associations)??>
		<#list query.associations as association>
		, ${association.alias}.${association.aTable.pkColumn.dbName} as ${association.alias}_${association.aTable.pkColumn.dbName}
		<#if (association.aTable.columns)??>
		<#list association.aTable.columns as aColumn>, ${association.alias}.${aColumn.dbName} as ${association.alias}_${aColumn.dbName}</#list>
		</#if>
		</#list>
		</#if>
		</#if>
		from ${table.tableName}
		<#if (query.associations)??>
		<#list query.associations as association>
		left join ${association.aTable.tableName} ${association.alias} on ${table.tableName}.${association.joinDbName} = ${association.alias}.${association.aTable.pkColumn.dbName}
		</#list>
		</#if>
		where 1=1
		<#if (query.conditions)??>
		<#list query.conditions as condition>
		<if test="${condition.column.name} != null">
		<#if (condition.and)>and<#else>or</#if> <#if condition.type == 0>${table.tableName}.${condition.column.dbName} like ${r"concat('%',#{"}${condition.column.name}${r"},'%')"}</#if><#if condition.type == 1>${table.tableName}.${condition.column.dbName} = ${r"#{"}${condition.column.name}${r"}"}</#if>
		</if>
		</#list>
		</#if>
		<#if (query.associations)??>
		<#list query.associations as association>
		<#if (association.conditions)??>
		<#list association.conditions as condition>
		<if test="${condition.column.name} != null">
		<#if (condition.and)>and<#else>or</#if> <#if condition.type == 0>${association.alias}.${condition.column.dbName} like ${r"concat('%',#{"}${condition.column.name}${r"},'%')"}</#if><#if condition.type == 1>${association.alias}.${condition.column.dbName} = ${r"#{"}${condition.column.name}${r"}"}</#if>
		</if>
		</#list>
		</#if>
		</#list>
		</#if>
		<#if (!query.count)>
		<#if (query.needOrderBy)>
		<if test="${r"orderSortName != null &amp;&amp; orderAsc != null"}">
			order by user.${r"${orderSortName}"}
			<choose>
				<when test="${r"orderAsc == true"}">
					asc
				</when>
				<otherwise>
					desc
				</otherwise>
			</choose>
		</if>
		</#if>
		<#if (query.needPaging)>
		<if test="${r"offset != null &amp;&amp; limit != null"}">
			limit ${r"${offset},${limit}"}
		</if>
		</#if>
		</#if>
	</select>
	
	</#list>
	</#if>
</mapper>