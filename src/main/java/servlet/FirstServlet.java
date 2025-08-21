package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.apiUtil.constant.ApiConstant;
import model.service.StockDiviService;
import model.service.impl.StockServiceImpl;
import model.vo.StockDividendInfoVO;

public class FirstServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		StockDiviService stockService = new StockServiceImpl();
		
		List<StockDividendInfoVO> list = stockService.getStockDiviList("삼성전자");
		
		if(list.isEmpty()) {
			System.out.println("데이터 없음");

		} else {
			stockService.getStockDiviList("삼성전자").stream().forEach(System.out::println);
			
			String json = ApiConstant.gson.toJson(list);
			
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter pw = resp.getWriter(); 
			pw.append(json);
			pw.flush();
			pw.close();
			
		}
	}
	
	
} // class
















