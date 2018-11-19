package com.epoint.bookmanage_test01.action.bookinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epoint.bookmanage_test01.service.bookinfo.BookInfoService;
import com.epoint.bookmanage_test01.service.bookinfo.BookInfoServiceImpl;
import com.epoint.bookmanage_test01.service.bookinfo.domin.BookInfo;


/**
 * Servlet implementation class BookInfoServlet
 */
@WebServlet("/BookInfoServlet")
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoServlet() {
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
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		if("listBooks".equals(type)) {
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String key = request.getParameter("key");
			BookInfoService service = new BookInfoServiceImpl();
			List<BookInfo> list = service.getAllBooks(key, pageIndex, pageSize);
			Long total = service.getTotal(key);
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("data", list);
			
			response.getWriter().write(JSON.toJSONString(json)); 
		}
		if("addBook".equals(type)) {
			String jsonStr = request.getParameter("data");
			JSONArray jsonArray = JSON.parseArray(jsonStr);
			BookInfoService service = new BookInfoServiceImpl();
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject row = jsonArray.getJSONObject(i);
				String js = row.toJSONString();
				BookInfo bookInfo = JSON.parseObject(js.trim(), BookInfo.class);
				String rowState = row.get("_state") != null ? row.get("_state").toString() : "";
				if("add".equals(state)) {
					service.addBook(bookInfo);
				}
				if("modified".equals(rowState) || "".equals(rowState)) {
					service.updBook(bookInfo);
				}
			}
		}
		if("delBook".equals(type)) {
			String bookId = request.getParameter("bookId");
			BookInfoService service = new BookInfoServiceImpl();
			service.delBook(bookId);
		}
		if("checkBook".equals(type)) {
			String bookName = request.getParameter("bookName");
			String author = request.getParameter("author");
			BookInfoService service = new BookInfoServiceImpl();
			Long checkTotal = service.findBookByName(bookName,author);
			PrintWriter writer = response.getWriter();
			if(checkTotal > 0) {
				writer.print(true);
			}else {
				writer.print(false);
			}
			
		}
		if("listJson".equals(type)) {
			BookInfoService service = new BookInfoServiceImpl();
			List<BookInfo> listBook = service.listBook();
			response.getWriter().write(JSON.toJSONString(listBook)); 
		}
	}

}
