package com.zsbatech.baasKettleManager.util;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

public class VOUtils {

	private static final Logger LOG = LoggerFactory.getLogger(VOUtils.class);

	public static <V> V po2vo(Object po, Class<V> clazz) {
		V vo = null;
		try {
			if (ObjectUtils.isEmpty(po)) {
				return vo;
			}
			vo = clazz.newInstance();
			BeanUtils.copyProperties( po,vo);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return vo;
	}

	

	public static <P, V> List<V> po2vo(List<P> content, Class<V> clazz) {
		List<V> newContent = new ArrayList<V>();
		if (content != null && content.size() > 0) {
			for (P po : content) {
				V vo = po2vo(po, clazz);
				newContent.add(vo);
			}
		}
		return newContent;
	}

	

}
