package com.zxq.empty;

import java.util.Collection;
import java.util.Map;

@SuppressWarnings("all")
public class IsEmpty {

	public static boolean isEmpty(Object obj) {
		if(obj == null) return true;
//		if(obj instanceof Integer && (Integer)obj == null) return true;
		if(obj instanceof String && String.valueOf(obj).trim().length()==0) return true;
		if(obj instanceof Collection && ((Collection) obj).isEmpty()) return true;
		if(obj instanceof Map && ((Map) obj).isEmpty()) return true;
		return false;
	}
	public static boolean isEmpty(Object... objects) {
		if(objects == null) return true;
		for(Object obj : objects) {
			if(isEmpty(obj))
				return true;
		}
		return false;
	}
}
