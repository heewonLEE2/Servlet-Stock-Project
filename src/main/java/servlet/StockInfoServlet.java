package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.apiUtil.constant.ApiConstant;
import model.service.StockInfoService;
import model.service.impl.StockInfoServiceImpl;
import model.vo.StockInfoVO;

public class StockInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StockInfoService stockInfoService = new StockInfoServiceImpl();
		List<StockInfoVO> stockInfoList = new ArrayList<StockInfoVO>();

		String stockName = req.getParameter("name");
		System.out.println(stockName);
		
		
		if(stockName !=null) {
			
			stockInfoList = stockInfoService.getStockInfoList(stockName);
			
			stockInfoList.stream().forEach(System.out::println);
			
			String json = ApiConstant.gson.toJson(stockInfoList);

			resp.setContentType("application/json; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.append(json);
			pw.flush();
			pw.close();
			
		}else {
			System.out.println("서버 오류 발생!");
		}


	} // doGet
} // class














