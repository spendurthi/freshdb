package com.sone.freshdb.utils;

import org.springframework.beans.BeanUtils;

public class Beans {
	public static void copyProperties(Object src, Object tgt){
		if (src!=null && tgt!=null)
			BeanUtils.copyProperties(src, tgt);
	}
}
