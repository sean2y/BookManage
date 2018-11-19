package com.epoint.bookmanage_test01.action.borrowinfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epoint.bookmanage_test01.service.ChartVo;
import com.epoint.bookmanage_test01.service.bookinfo.BookInfoService;
import com.epoint.bookmanage_test01.service.bookinfo.BookInfoServiceImpl;
import com.epoint.bookmanage_test01.service.borrowinfo.BorrowInfoService;
import com.epoint.bookmanage_test01.service.borrowinfo.BorrowInfoServiceImpl;
import com.epoint.bookmanage_test01.service.borrowinfo.domin.BorrowInfo;
import com.epoint.bookmanage_test01.utils.BorrowInfoUtil;

/**
 * Servlet implementation class BorrowInfoServlet
 */
@WebServlet("/BorrowInfoServlet")
public class BorrowInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if("listBorrowInfos".equals(type)) {
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			BorrowInfoService service = new BorrowInfoServiceImpl();
			List<BorrowInfo> list = service.getAllBorrowInfos(pageIndex, pageSize);
			Long total = service.getTotal();
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("data", list);
			response.getWriter().write(JSONObject.toJSONStringWithDateFormat(json, "yyyy-MM-dd"));
		}
		if("addBorrowInfo".equals(type)) {
			String jsonStr = request.getParameter("data");
			JSONArray jsonArray = JSON.parseArray(jsonStr);
			BorrowInfoService service = new BorrowInfoServiceImpl();
			BookInfoService bookInfoService = new BookInfoServiceImpl();
			String borrowId = BorrowInfoUtil.getBorrowId();
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject row = jsonArray.getJSONObject(i);
				String js = row.toJSONString();
				BorrowInfo borrowInfo = JSON.parseObject(js.trim(),BorrowInfo.class);
				borrowInfo.setBorrowId(borrowId);
				if(bookInfoService.getRemain(borrowInfo.getBookId()) > 0) {
					service.addBorrow(borrowInfo);
					bookInfoService.reduceRemain(borrowInfo.getBookId());
				}else {
					response.getWriter().write(JSONObject.toJSONString("剩余数量不足，请重新选择！"));
				}
			}
		}
		if("showBorrowId".equals(type)) {
			String borrowId = BorrowInfoUtil.getBorrowId();
			response.getWriter().write(JSON.toJSONString(borrowId));
		}
		if("returnBorrowInfo".equals(type)) {
			String borrowId = request.getParameter("borrowId");
			BorrowInfoService service = new BorrowInfoServiceImpl();
			BookInfoService bookInfoService = new BookInfoServiceImpl();
			BorrowInfo borrowInfo = service.getBorrowInfo(borrowId);
			if("0".equals(borrowInfo.getReturnFlag())) {
				service.updBorrow(borrowInfo);
				bookInfoService.incrementRemain(borrowInfo.getBookId());
			}else {
				response.getWriter().write(JSONObject.toJSONString("该图书已归还，请不要重新操作！"));
			}
		}
		if("showCount".equals(type)) {
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			BorrowInfoService service = new BorrowInfoServiceImpl();
			List<ChartVo> list = service.showCount(pageIndex, pageSize);
			Long countTotal = service.getShowCountTotal();
			JSONObject json = new JSONObject();
			json.put("total", countTotal);
			json.put("data", list);
			response.getWriter().write(JSONObject.toJSONStringWithDateFormat(json, "yyyy-MM-dd"));
			
		}
	}

}
