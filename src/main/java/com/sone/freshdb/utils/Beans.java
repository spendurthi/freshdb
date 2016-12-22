package com.sone.freshdb.utils;

import org.springframework.beans.BeanUtils;

public class Beans {
	public static void copyProperties(Object src, Object tgt){
		BeanUtils.copyProperties(src, tgt);
	}
}
