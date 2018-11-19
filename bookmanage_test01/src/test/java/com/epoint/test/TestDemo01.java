package com.epoint.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.epoint.bookmanage_test01.service.bookinfo.BookInfoService;
import com.epoint.bookmanage_test01.service.bookinfo.BookInfoServiceImpl;
import com.epoint.bookmanage_test01.service.bookinfo.domin.BookInfo;
import com.epoint.bookmanage_test01.service.borrowinfo.BorrowInfoService;
import com.epoint.bookmanage_test01.service.borrowinfo.BorrowInfoServiceImpl;
import com.epoint.bookmanage_test01.utils.BorrowInfoUtil;
import com.epoint.bookmanage_test01.utils.DateUtil;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月15日 上午10:34:06
 * @version 1.0.0
 */
public class TestDemo01 {

	@Test
	public void demo01() {
		BookInfoService service = new BookInfoServiceImpl();
		Long checkTotal = service.findBookByName("dsadasd","");
		System.out.println(checkTotal);
	}
	
	@Test
	public void demo02() {
		String borrowId = BorrowInfoUtil.getBorrowId();
		System.out.println(JSON.toJSONString(borrowId));
	}
	@Test
	public void demo03() {
		BookInfoService service = new BookInfoServiceImpl();
		List<BookInfo> listBook = service.listBook();
		System.out.println(JSON.toJSONString(listBook));
	}
	@Test
	public void demo04() {
		System.out.println(new Date());
	}
}
