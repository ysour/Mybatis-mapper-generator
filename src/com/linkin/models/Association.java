package com.linkin.models;

import java.util.ArrayList;
import java.util.List;

public class Association {

	private String property;
	private String joinProperty;
	private String joinDbName;
	private Table aTable;
	private String alias;
	private List<Condition> conditions;

	/**
	 * 
	 * @param property
	 *            entity属性名
	 * @param joinProperty
	 *            关联id的entity属性名
	 * @param aTable
	 *            关联的表
	 */
	public Association(String property, String joinProperty, Table aTable) {
		super();
		this.property = property;
		this.joinProperty = joinProperty;
		this.aTable = aTable;
	}

	public void addCondition(Condition condition) {
		if (conditions == null)
			conditions = new ArrayList<Condition>();

		String property = condition.getProperty();

		boolean hasProperty = false;
		if (aTable.getPkColumn().getName().equals(property)) {
			condition.setColumn(new Column(aTable.getPkColumn().getName(),
					aTable.getPkColumn().getDbName()));
			hasProperty = true;
		} else {
			for (Column column : aTable.getColumns()) {
				if (column.getName().equals(property)) {
					condition.setColumn(column);
					hasProperty = true;
					break;
				}
			}
		}
		if (!hasProperty)
			throw new RuntimeException("找不到" + aTable.getEntityName() + "["
					+ Column.class.getSimpleName() + "][" + property + "]");

		conditions.add(condition);
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getJoinProperty() {
		return joinProperty;
	}

	public void setJoinProperty(String joinProperty) {
		this.joinProperty = joinProperty;
	}

	public String getJoinDbName() {
		return joinDbName;
	}

	public void setJoinDbName(String joinDbName) {
		this.joinDbName = joinDbName;
	}

	public Table getaTable() {
		return aTable;
	}

	public void setaTable(Table aTable) {
		this.aTable = aTable;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Association other = (Association) obj;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

}
