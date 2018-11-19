package com.epoint.bookmanage_test01.service.borrowinfo;

import java.util.List;

import com.epoint.bookmanage_test01.dao.borrowinfo.BorrowInfoDaoImpl;
import com.epoint.bookmanage_test01.service.ChartVo;
import com.epoint.bookmanage_test01.service.borrowinfo.domin.BorrowInfo;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月16日 上午10:41:33
 * @version 1.0.0
 */
public class BorrowInfoServiceImpl implements BorrowInfoService {

	@Override
	public void addBorrow(BorrowInfo borrowInfo) {
		// TODO Auto-generated method stub
		new BorrowInfoDaoImpl().addBorrow(borrowInfo);
	}

	@Override
	public void updBorrow(BorrowInfo borrowInfo) {
		// TODO Auto-generated method stub
		new BorrowInfoDaoImpl().updBorrow(borrowInfo);
	}

	@Override
	public List<BorrowInfo> getAllBorrowInfos( int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return new BorrowInfoDaoImpl().getAllBorrowInfos(pageIndex, pageSize);
	}

	@Override
	public Long getTotal() {
		// TODO Auto-generated method stub
		return new BorrowInfoDaoImpl().getTotal();
	}

	@Override
	public String getBorrowId() {
		// TODO Auto-generated method stub
		return new BorrowInfoDaoImpl().getBorrowId();
	}


	@Override
	public BorrowInfo getBorrowInfo(String borrowId) {
		// TODO Auto-generated method stub
		return new BorrowInfoDaoImpl().getBorrowInfo(borrowId);
	}

	@Override
	public List<ChartVo> showCount(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return new BorrowInfoDaoImpl().showCount(pageIndex, pageSize);
	}

	@Override
	public Long getShowCountTotal() {
		// TODO Auto-generated method stub
		return new BorrowInfoDaoImpl().getShowCountTotal();
	}

	

}
