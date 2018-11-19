package com.epoint.bookmanage_test01.dao.borrowinfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.epoint.bookmanage_test01.service.ChartVo;
import com.epoint.bookmanage_test01.service.borrowinfo.domin.BorrowInfo;
import com.epoint.bookmanage_test01.utils.JDBCUtil;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月16日 上午10:42:48
 * @version 1.0.0
 */
public class BorrowInfoDaoImpl implements BorrowInfoDao {

	QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public void addBorrow(BorrowInfo borrowInfo) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into borrowinfo(borrow_id, book_id, borrower, phone, borrow_time, return_time) "
					+ "values(?,?,?,?,?,?)";
			runner.update(sql, borrowInfo.getBorrowId(),borrowInfo.getBookId(),borrowInfo.getBorrower(),
					borrowInfo.getPhone(),borrowInfo.getBorrowTime(),borrowInfo.getReturnTime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updBorrow(BorrowInfo borrowInfo) {
		// TODO Auto-generated method stub
		try {
			String sql = "update borrowinfo set return_time = ?, return_flag = ? where borrow_id = ?";
			runner.update(sql, new Date(),"1",borrowInfo.getBorrowId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<BorrowInfo> getAllBorrowInfos(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<BorrowInfo> list = new ArrayList<>();
		try {
			String sql = "SELECT bw.borrow_id AS borrowId,bk.book_name AS bookName, bw.borrower,bw.phone,bw.borrow_time AS borrowTime, bw.return_time AS returnTime "
					+ "FROM borrowinfo bw LEFT JOIN bookinfo bk ON bw.book_id = bk.book_id";
			list = runner.query(sql, new BeanListHandler<>(BorrowInfo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long getTotal() {
		// TODO Auto-generated method stub
		Long total = 0l;
		try {
			String sql = "select count(*) from borrowinfo";
			total = (Long) runner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public String getBorrowId() {
		// TODO Auto-generated method stub
		String borrowIdStr = null;
		try {
			String sql = "SELECT borrow_id FROM borrowinfo ORDER BY borrow_id DESC LIMIT 1 ";
			borrowIdStr = (String) runner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowIdStr;
	}


	@Override
	public BorrowInfo getBorrowInfo(String borrowId) {
		// TODO Auto-generated method stub
		BorrowInfo borrowInfo = null;
		try {
			String sql = "select borrow_id as borrowId, book_id as bookId, borrower, phone, borrow_time as borrowTime, return_time as returnTime, return_flag as returnFlag "
							+ "from borrowinfo where borrow_id = ?";
			borrowInfo = runner.query(sql, new BeanHandler<>(BorrowInfo.class),borrowId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowInfo;
	}

	@Override
	public List<ChartVo> showCount(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<ChartVo> list = new ArrayList<>();
		try {
			String sql = "SELECT bw.borrow_time as time, bk.book_type as type, COUNT(bk.book_type) as count from borrowinfo bw "
					+ "LEFT JOIN bookinfo bk on bw.book_id=bk.book_id GROUP BY bk.book_type,bw.borrow_time";
			list = runner.query(sql, new BeanListHandler<>(ChartVo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long getShowCountTotal() {
		// TODO Auto-generated method stub
		Long total = 0l;
		try {
			String sql = "SELECT COUNT(*) from "
					+ "(SELECT bw.borrow_time as time, bk.book_type as type,COUNT(bk.book_type) as count "
					+ "from borrowinfo bw LEFT JOIN bookinfo bk on bw.book_id=bk.book_id GROUP BY bk.book_type,bw.borrow_time) as chartvo";
			total = (Long) runner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

}
