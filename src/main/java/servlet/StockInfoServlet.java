package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		List<StockInfoVO> stockInfoList = stockInfoService.getStockInfoList("삼성전자");
		
		if(stockInfoList.isEmpty()) {
			System.out.println("데이터 없음");
		} else {
			
			String json = ApiConstant.gson.toJson(stockInfoList);
			
			PrintWriter pw = resp.getWriter();
			pw.append(json);
			pw.flush();
			pw.close();
			
		}
	
	}	

}
