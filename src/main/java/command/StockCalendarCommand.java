package command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.service.StockDiviService;
import model.service.impl.StockDiviServiceImpl;
import model.vo.StockDividendInfoVO;

public class StockCalendarCommand extends AbstractStockDivi {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		StockDiviService stockService = new StockDiviServiceImpl();

		String itmsName = req.getParameter("itmsName");
		System.out.println(itmsName);

		List<StockDividendInfoVO> stockList = null;
		if (itmsName != null) {
			stockList = stockService.getStockDiviList(itmsName);
		}

		List<String> stockDividendList = new ArrayList<String>();
		for (StockDividendInfoVO stock : stockList) {
			stockDividendList.add(String.valueOf(Integer.parseInt(stock.getDvdnBasDt()) - 2));
		}

		String json = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		json = gson.toJson(stockDividendList);

		resp.getWriter().append(json);
	}

}
