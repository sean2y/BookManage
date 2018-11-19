package com.epoint.bookmanage_test01.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月16日 上午11:05:41
 * @version 1.0.0
 */
public class DateUtil {

	public static String getCurrentYear () {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
	}
}
