package command;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.apiUtil.constant.ApiConstant;
import model.vo.StockInfoVO;

public class StockInfoCommand extends AbstractStockInfo{

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		List<StockInfoVO> stockInfoList = new ArrayList<StockInfoVO>();
		
		String stockName = req.getParameter("name");
		
		// System.out.println(stockName);
		
		if(stockName !=null) {
			
			stockInfoList = stockInfoService.getStockInfoList(stockName);
			
			//stockInfoList.stream().forEach(System.out::println);
			
			String json = ApiConstant.gson.toJson(stockInfoList);

			resp.setContentType("application/json; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.append(json);
			pw.flush();
			pw.close();
			
		}else {
			System.out.println("서버 오류 발생!");
		}
	}
	
}
