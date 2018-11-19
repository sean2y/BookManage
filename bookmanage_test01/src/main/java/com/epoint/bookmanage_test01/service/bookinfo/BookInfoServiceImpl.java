package com.epoint.bookmanage_test01.service.bookinfo;

import java.util.List;

import com.epoint.bookmanage_test01.dao.bookinfo.BookInfoDaoImpl;
import com.epoint.bookmanage_test01.service.bookinfo.domin.BookInfo;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月15日 上午9:31:48
 * @version 1.0.0
 */
public class BookInfoServiceImpl implements BookInfoService {

	@Override
	public void addBook(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		new BookInfoDaoImpl().addBook(bookInfo);
	}

	@Override
	public void delBook(String bookId) {
		// TODO Auto-generated method stub
		new BookInfoDaoImpl().delBook(bookId);
	}

	@Override
	public Long getTotal(String key) {
		// TODO Auto-generated method stub
		return new BookInfoDaoImpl().getTotal(key);
	}

	@Override
	public List<BookInfo> getAllBooks(String key, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return new BookInfoDaoImpl().getAllBooks(key, pageIndex, pageSize);
	}

	@Override
	public List<BookInfo> listBook() {
		// TODO Auto-generated method stub
		return new BookInfoDaoImpl().listBook();
	}

	@Override
	public Long findBookByName(String bookName,String author) {
		// TODO Auto-generated method stub
		return new BookInfoDaoImpl().findBookByName(bookName,author);
	}

	@Override
	public void updBook(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		new BookInfoDaoImpl().updBook(bookInfo);
	}

	@Override
	public int getRemain(String bookId) {
		// TODO Auto-generated method stub
		return new BookInfoDaoImpl().getRemain(bookId);
	}

	@Override
	public void reduceRemain(String bookId) {
		// TODO Auto-generated method stub
		new BookInfoDaoImpl().reduceRemain(bookId);
	}

	@Override
	public void incrementRemain(String bookId) {
		// TODO Auto-generated method stub
		new BookInfoDaoImpl().incrementRemain(bookId);
	}

}
