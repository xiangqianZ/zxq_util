package com.zxq.mybatis;

import com.zxq.empty.IsEmpty;

import java.lang.reflect.Field;

public class SqlDriver<T> extends BaseSqlDriver<T> {
	
	public String insert(T t) throws Exception {
		before(t, "insert into ");
		for(Field field : fields) {
			if(modifierIsStatic(field)) continue;
			boolean InsertValue = IsEmpty.isEmpty(field.get(t));
			if(field.isAnnotationPresent(Primery.class) || InsertValue) continue;
			String fieldName = field.getName();
			appendFieldKey(fieldName);
			appendFieldValue(appendFieldValue,fieldName, ",",false);
		}
		deleteEndChar(appendFieldKey,appendFieldValue);
		sqlSbKey.append(c.getSimpleName()).append(" (").append(appendFieldKey).append(") value (")
		.append(appendFieldValue).append(")");
		return sqlSbKey.toString();
	}

	public String queryList(T t) throws Exception {
		before(t, "select ");
		for(Field field : fields) {
			if(modifierIsStatic(field)) continue;
			String fieldName = field.getName();
			appendFieldKey(fieldName);
			if(IsEmpty.isEmpty(field.get(t))) continue;
			appendFieldValue(appendFieldValue,fieldName, " and ",true);
		}
		deleteEndChar(appendFieldKey,appendFieldValue);
		sqlSbKey.append(appendFieldKey).append(" from ").append(c.getSimpleName()).append(" where ")
		.append(appendFieldValue).append(" 1=1");
		return sqlSbKey.toString();
	}

	public String update(T t) throws Exception {
		before(t, "update ");
		for(Field field : fields) {
			if(modifierIsStatic(field) || IsEmpty.isEmpty(field.get(t))) continue;
			String fieldName = field.getName();
			if(field.isAnnotationPresent(Primery.class)) {
				appendFieldValue(wherePrimery,fieldName, " and ",true);
				continue;
			}
			appendFieldValue(appendFieldValue,fieldName, ",",true);
		}
		deleteEndChar(appendFieldValue);
		sqlSbKey.append(c.getSimpleName()).append(" set ").append(appendFieldValue)
		.append(wherePrimery).append(" 1=1");
		return sqlSbKey.toString();
	}
	
	public String delete(T t) throws Exception{
		before(t, "delete from ");
		for(Field field : fields) {
			if(modifierIsStatic(field) || IsEmpty.isEmpty(field.get(t))) continue;
			String fieldName = field.getName();
			appendFieldValue(appendFieldValue,fieldName, " and ",true);
		}
		deleteEndChar(appendFieldValue);
		sqlSbKey.append(c.getSimpleName()).append(" where ").append(appendFieldValue).append(" 1=1 ");
		return sqlSbKey.toString();
	}
}
