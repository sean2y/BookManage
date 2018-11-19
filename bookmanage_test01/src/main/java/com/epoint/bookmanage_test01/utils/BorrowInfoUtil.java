package com.epoint.bookmanage_test01.utils;

import com.epoint.bookmanage_test01.service.borrowinfo.BorrowInfoService;
import com.epoint.bookmanage_test01.service.borrowinfo.BorrowInfoServiceImpl;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月16日 下午2:13:34
 * @version 1.0.0
 */
public class BorrowInfoUtil {

	public static String getBorrowId() {
		BorrowInfoService service = new BorrowInfoServiceImpl();
		String borrowIdStr = null;
		Long total = service.getTotal();
		if(total > 0) {
			String lastIdStr = service.getBorrowId();
			int lastIdNum = Integer.parseInt(lastIdStr.substring(8));
			String strNum = String.format("%04d", lastIdNum+1);
			borrowIdStr = "BOOK" + DateUtil.getCurrentYear() + strNum;
		}else {
			String strNum = String.format("%04d", 1);
			borrowIdStr = "BOOK" + DateUtil.getCurrentYear() + strNum;
		}
		return borrowIdStr;
	}
	
	
}
