package com.linkin.models;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {

	private static final long serialVersionUID = -2487389024387667323L;

	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 实体名
	 */
	private String entityName;

	/**
	 * db表名
	 */
	private String tableName;

	/**
	 * 主键信息
	 */
	private PkColumn pkColumn;

	/**
	 * 普通列信息
	 */
	private List<Column> columns;

	/**
	 * 增强查询
	 */
	private Querys querys;

	public Table() {
		super();
	}

	public Table(String packageName, String entityName, String tableName,
			PkColumn pkColumn, List<Column> columns) {
		super();
		this.packageName = packageName;
		this.entityName = entityName;
		this.tableName = tableName;
		this.pkColumn = pkColumn;
		this.columns = columns;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public PkColumn getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(PkColumn pkColumn) {
		this.pkColumn = pkColumn;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Querys getQuerys() {
		return querys;
	}

	public void setQuerys(Querys querys) {
		this.querys = querys;
	}

}
