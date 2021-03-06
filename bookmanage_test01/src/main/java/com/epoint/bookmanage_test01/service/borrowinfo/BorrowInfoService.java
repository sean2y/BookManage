package com.epoint.bookmanage_test01.service.borrowinfo;

import java.util.List;

import com.epoint.bookmanage_test01.service.ChartVo;
import com.epoint.bookmanage_test01.service.borrowinfo.domin.BorrowInfo;

/**
 * @Description TODO
 * @author 杨斌
 * @Date 2018年11月16日 上午10:41:09
 * @version 1.0.0
 */
public interface BorrowInfoService {
	
	public void addBorrow(BorrowInfo borrowInfo);
	
	public void updBorrow(BorrowInfo borrowInfo);
	
	public List<BorrowInfo> getAllBorrowInfos(int pageIndex, int pageSize);
	
	public BorrowInfo getBorrowInfo(String borrowId);
	
	public Long getTotal();
	
	public String getBorrowId();
	
	public List<ChartVo> showCount(int pageIndex, int pageSize);
	
	public Long getShowCountTotal();
	
}
