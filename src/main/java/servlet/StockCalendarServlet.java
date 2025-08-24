package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.service.StockService;
import model.service.impl.StockServiceImpl;
import model.vo.StockDividendInfoVO;

public class StockCalendarServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StockService stockService = new StockServiceImpl();
		
		String itmsName = req.getParameter("itmsName");
		
		List<StockDividendInfoVO> stockList 
			= stockService.getStockDiviList(itmsName);
		
		List<String> stockDividendList = new ArrayList<String>();
		for(StockDividendInfoVO stock : stockList) {
			stockDividendList.add(
				String.valueOf(Integer.parseInt(stock.getDvdnBasDt()) - 2)
			);
		}
		
		String json = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		json = gson.toJson(stockDividendList);
		
		resp.getWriter().append(json);
	}
	

}
