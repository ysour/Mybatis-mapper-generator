package com.linkin;

import java.util.Arrays;
import java.util.List;

import com.linkin.models.Association;
import com.linkin.models.Column;
import com.linkin.models.Condition;
import com.linkin.models.PkColumn;
import com.linkin.models.Querys;
import com.linkin.models.Table;
import com.linkin.models.Querys.Query;
import com.linkin.utils.FreemarkerUtil;

public class MybatisGenerator {

	public static void main(String[] args) {
		//--------------------User表--------------------
		//设定主键信息
		PkColumn pkColumn = new PkColumn("id", "id", "String");
		//设定列信息
		List<Column> columns = Arrays.asList(
				new Column("name", "name"),
				new Column("avatar", "avatar_id")
		);
		//设定表信息
		Table table = new Table("com.test.web", "User", "user", pkColumn,
				columns);

		//--------------------Equipment表--------------------
		//设定主键信息
		PkColumn pkColumn2 = new PkColumn("id", "id", "String");
		//设定列信息
		List<Column> columns2 = Arrays.asList(
				new Column("equipmentName", "equipment_name"),
				new Column("userId", "user_id"),
				new Column("adminId", "admin_id")
		);
		//设定表信息
		Table table2 = new Table("com.test.web", "Equipment", "equipment",
				pkColumn2, columns2);
		//创建关联查询集合，设定所有外键关联Association
		Querys querys = new Querys(table2, 
				new Association("owner", "userId", table), new Association("admin", "adminId", table)
		);
		
		//创建关联查询1(关联一张表数据，1个条件查询，需要排序接口和分页接口)
		Query query1 = querys.buildQuery("owner");
		query1.addCondition(Condition.ofAndEqual("userId"));
		query1.setNeedOrderBy(true);
		query1.setNeedPaging(true);
		
		//创建关联查询2(关联两张表数据，计算总数，3个条件查询)
		Query query2 = querys.buildQuery("owner", "admin");
		query2.setCount(true);
		query2.addCondition(Condition.ofAndLike("equipmentName"));
		query2.getAssociation("owner").addCondition(Condition.ofAndLike("avatar"));
		query2.getAssociation("admin").addCondition(Condition.ofAndLike("name"));
		query2.setNeedOrderBy(true);
		query2.setNeedPaging(true);
		
		//创建非关联查询3(2个条件查询，指定方法名)
		Query query3 = querys.buildQuery();
		query3.addCondition(Condition.ofAndLike("equipmentName"));
		query3.setNeedOrderBy(true);
		query3.setNeedPaging(true);
		query3.setFuncName("queryByEquipmentName");
	
		table2.setQuerys(querys);
		FreemarkerUtil.output(table, table2);
	}
}
