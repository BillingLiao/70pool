package com.ant.common.validator;

import cn.hutool.core.bean.BeanUtil;
import com.ant.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * 
 * @author chenshun
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

	public static void isBlank(String str, String message) {
		if (StringUtils.isBlank(str)) {
			throw new RRException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new RRException(message);
		}
	}

	public static void notEmpty(Object t) {
		try {
			org.springframework.util.Assert.notEmpty(BeanUtil.beanToMap(t, false, true), "数据不能都为空");

		} catch (Exception e) {
			// TODO: handle exception
			throw new RRException(e.getMessage());

		}
	}

}
