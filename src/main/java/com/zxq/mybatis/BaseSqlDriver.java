package com.zxq.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BaseSqlDriver<T> {
	
	StringBuffer sqlSbKey;
	StringBuffer appendFieldKey;
	StringBuffer appendFieldValue;
	StringBuffer wherePrimery;
	
	Class<? extends Object> c;
	Field[] fields;
	
	void sqlBefore(Class<? extends Object> c,String type) {
		sqlSbKey = new StringBuffer(type);
		appendFieldKey = new StringBuffer("");
		appendFieldValue = new StringBuffer(" ");
		wherePrimery = new StringBuffer(" where ");
	}
	
	void appendFieldKey(String fieldName) {
		appendFieldKey.append(fieldName).append(",");
	}
	
	void appendFieldValue(StringBuffer sb,String fieldName,String type,boolean flag) {
		sb.append(flag?fieldName+"=":"").append("#{").append(fieldName).append("} ").append(type);
	}
	
	void deleteEndChar(StringBuffer...buffers) {
		for(StringBuffer sb : buffers) {
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	public void before(T t,String type) {
		c = t.getClass();
		fields = c.getDeclaredFields();
		Field.setAccessible(fields, true);
		
		sqlBefore(c, type);
	}
	
	boolean modifierIsStatic(Field field) {
		return Modifier.isStatic(field.getModifiers()) || field.isAnnotationPresent(NoColumn.class);
	}
}
