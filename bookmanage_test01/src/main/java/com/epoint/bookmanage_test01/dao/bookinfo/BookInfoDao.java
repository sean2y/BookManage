package com.epoint.bookmanage_test01.dao.bookinfo;

import java.util.List;
import java.util.Map;

import com.epoint.bookmanage_test01.service.bookinfo.domin.BookInfo;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月14日 上午9:42:39
 * @version 1.0.0
 */
public interface BookInfoDao {

	public void addBook(BookInfo bookInfo);
	
	public void delBook(String bookId);
	
	public Long findBookByName(String bookName,String author);
	
	public void updBook(BookInfo bookInfo);
	
	public Long getTotal(String key);
	
	public List<BookInfo> getAllBooks(String key, int pageIndex, int pageSize);
	
	public List<BookInfo> listBook();
	
	public int getRemain(String bookId);
	
	public void reduceRemain(String bookId);
	
	public void incrementRemain(String bookId);
	
}
