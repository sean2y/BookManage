package com.epoint.bookmanage_test01.dao.bookinfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.epoint.bookmanage_test01.service.bookinfo.domin.BookInfo;
import com.epoint.bookmanage_test01.utils.JDBCUtil;


/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月14日 下午3:12:14
 * @version 1.0.0
 */
public class BookInfoDaoImpl implements BookInfoDao {

	QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public void addBook(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		String uuidStr = UUID.randomUUID().toString().replace("-", "");
		try {
			String sql = "insert into bookinfo(book_id, book_name, publisher, author, book_type, remain) "
					+ "values(?,?,?,?,?,?)";
			runner.update(sql,uuidStr,bookInfo.getBookName(),bookInfo.getPublisher(),bookInfo.getAuthor(),bookInfo.getBookType(),bookInfo.getRemain());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delBook(String bookId) {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from bookinfo where book_id = ?";
			runner.update(sql, bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Long getTotal(String key) {
		// TODO Auto-generated method stub
		Long total = 0l;
		String sql = "select count(*) from bookinfo";
		if(key != null && !"".equals(key)) {
			sql+=" where book_name like ?";
		}
		try {
			if(key != null && !"".equals(key)) {
				key = "%"+key+"%";
				total = (Long) runner.query(sql, new ScalarHandler(),key);
			}else {
				total = (Long) runner.query(sql, new ScalarHandler());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<BookInfo> getAllBooks(String key, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int start = pageIndex * pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select book_id as bookId, book_name as bookName, publisher, author, book_type as bookType, remain from bookinfo");
		if(key != null && !"".equals(key)) {
			sql.append(" where book_name like ?");
		}
		sql.append(" limit ? ,?");
		List<BookInfo> list = new ArrayList<>();
		try {
			if(key != null && !"".equals(key)) {
				key = "%"+key+"%";
				list = runner.query(sql.toString(), new BeanListHandler<>(BookInfo.class),key,start,pageSize);
			}else {
				list = runner.query(sql.toString(), new BeanListHandler<>(BookInfo.class),start,pageSize);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookInfo> listBook() {
		// TODO Auto-generated method stub
		List<BookInfo> list = new ArrayList<>();
		try {
			String sql = "select book_id as bookId, book_name as bookName, publisher, author, book_type as bookType, remain from bookinfo";
			list = runner.query(sql, new BeanListHandler<>(BookInfo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long findBookByName(String bookName,String author) {
		// TODO Auto-generated method stub
		Long findTotal = 0l;
		try {
			String sql = "select count(*) from bookinfo where book_name = ? and author = ?";
			findTotal = (Long) runner.query(sql, new ScalarHandler(),bookName,author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findTotal;
	}

	@Override
	public void updBook(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		try {
			String sql = "update bookinfo set book_type = ?, publisher = ? where book_id = ?";
			runner.update(sql, bookInfo.getBookType(),bookInfo.getPublisher(),bookInfo.getBookId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getRemain(String bookId) {
		// TODO Auto-generated method stub
		int remain = 0;
		try {
			String sql = "select remain from bookinfo where book_id = ?";
			remain = (int) runner.query(sql, new ScalarHandler(), bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remain;
	}

	@Override
	public void reduceRemain(String bookId) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE bookinfo SET remain = remain-1 WHERE book_id = ?";
			runner.update(sql, bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void incrementRemain(String bookId) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE bookinfo SET remain = remain+1 WHERE book_id = ?";
			runner.update(sql, bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
