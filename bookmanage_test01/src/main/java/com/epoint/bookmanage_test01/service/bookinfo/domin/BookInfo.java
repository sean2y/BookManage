package com.epoint.bookmanage_test01.service.bookinfo.domin;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月14日 上午8:43:17
 * @version 1.0.0
 */
public class BookInfo {

	private String bookId;
	private String bookName;
	private String publisher;
	private String author;
	private Integer bookType;
	private Integer remain;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getBookType() {
		return bookType;
	}
	public void setBookType(Integer bookType) {
		this.bookType = bookType;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	
	
}
