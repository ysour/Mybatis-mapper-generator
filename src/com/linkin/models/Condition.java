package com.linkin.models;

public class Condition {

	private boolean and;
	private int type;
	private String property;
	private Column column;

	private Condition() {
	}

	public static Condition ofAndLike(String property) {
		Condition condition = new Condition();
		condition.and = true;
		condition.type = 0;
		condition.property = property;
		return condition;
	}

	public static Condition ofAndEqual(String property) {
		Condition condition = new Condition();
		condition.and = true;
		condition.type = 1;
		condition.property = property;
		return condition;
	}

	public static Condition ofOrLike(String property) {
		Condition condition = new Condition();
		condition.and = false;
		condition.type = 0;
		condition.property = property;
		return condition;
	}

	public static Condition ofOrEqual(String property) {
		Condition condition = new Condition();
		condition.and = false;
		condition.type = 1;
		condition.property = property;
		return condition;
	}

	public boolean isAnd() {
		return and;
	}

	public void setAnd(boolean and) {
		this.and = and;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

}
