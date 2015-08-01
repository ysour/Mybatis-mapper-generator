package com.linkin.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.linkin.utils.DeepCopy;

public class Querys {

	public static class Query {

		private final Table table;
		private List<Association> associations;
		private Map<String, Association> associationsMap;
		private List<Condition> conditions;
		private String funcName;
		private boolean count;
		private boolean needOrderBy;
		private boolean needPaging;

		public Query(Table table) {
			this.table = table;
		}

		public Association getAssociation(String property) {
			return associationsMap.get(property);
		}

		private void addAssociation(Association association) {
			if (associations == null)
				associations = new ArrayList<Association>();

			if (associationsMap == null)
				associationsMap = new HashMap<String, Association>();

			Association copy = DeepCopy.copyAssociation(association);
			associations.add(copy);
			associationsMap.put(copy.getProperty(), copy);
		}

		public void addCondition(Condition condition) {
			if (conditions == null)
				conditions = new ArrayList<Condition>();

			String property = condition.getProperty();

			boolean hasProperty = false;
			if (table.getPkColumn().getName().equals(property)) {
				condition.setColumn(new Column(table.getPkColumn().getName(),
						table.getPkColumn().getDbName()));
				hasProperty = true;
			} else {
				for (Column column : table.getColumns()) {
					if (column.getName().equals(property)) {
						condition.setColumn(column);
						hasProperty = true;
						break;
					}
				}
			}
			if (!hasProperty)
				throw new RuntimeException("找不到" + table.getEntityName() + "["
						+ Column.class.getSimpleName() + "][" + property + "]");

			conditions.add(condition);
		}

		public String getFuncName() {
			return funcName;
		}

		public void setFuncName(String funcName) {
			this.funcName = funcName;
		}

		public boolean isCount() {
			return count;
		}

		public void setCount(boolean count) {
			this.count = count;
		}

		public boolean isNeedOrderBy() {
			return needOrderBy;
		}

		public void setNeedOrderBy(boolean needOrderBy) {
			this.needOrderBy = needOrderBy;
		}

		public boolean isNeedPaging() {
			return needPaging;
		}

		public void setNeedPaging(boolean needPaging) {
			this.needPaging = needPaging;
		}

		public List<Association> getAssociations() {
			return associations;
		}

		public List<Condition> getConditions() {
			return conditions;
		}

		public Table getTable() {
			return table;
		}

	}

	private final Table table;

	/**
	 * 所有关联字段
	 */
	private final Map<String, Association> associationsMap;

	private List<Query> querys;

	public Querys(Table table, Association... associations) {
		this.table = table;
		associationsMap = new HashMap<String, Association>();
		if (associations != null) {
			for (Association association : associations) {
				associationsMap.put(association.getProperty(), association);

				String joinProperty = association.getJoinProperty();

				if (table.getPkColumn().getName().equals(joinProperty)) {
					association.setJoinDbName(table.getPkColumn().getDbName());
				} else {
					for (Column col : table.getColumns()) {
						if (col.getName().equals(joinProperty)) {
							association.setJoinDbName(col.getDbName());
						}
					}
				}
			}

			int index = 0;
			for (Entry<String, Association> entry : associationsMap.entrySet()) {
				Association association = entry.getValue();
				Table aTable = association.getaTable();
				association.setAlias(aTable.getTableName() + index++);
			}
		}
	}

	public Query buildQuery(String... associationProperties) {
		Query query = new Query(table);
		if (associationProperties != null) {
			for (String associationProperty : associationProperties) {
				Association association = associationsMap
						.get(associationProperty);
				if (association == null)
					throw new RuntimeException(
							Association.class.getSimpleName() + "["
									+ associationProperty + "]不存在");

				query.addAssociation(association);
			}
		}

		if (querys == null)
			querys = new ArrayList<Query>();

		querys.add(query);
		return query;
	}

	public Collection<Association> getAssociations() {
		return associationsMap.values();
	}

	public List<Query> getQuerys() {
		return querys;
	}

}
