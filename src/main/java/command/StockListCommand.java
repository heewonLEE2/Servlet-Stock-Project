package command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.apiUtil.constant.ApiConstant;
import model.vo.StockJsonVO;

public class StockListCommand extends AbstractStockJson{

	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
	      List<StockJsonVO> jsonList = stockJsonService.getStockJsonList();
	      
	      if(jsonList.isEmpty()) {
	         System.out.println("json 데이터 불러오기 오류!! ");
	      }else {
	         String json = ApiConstant.gson.toJson(jsonList);
	         
	         resp.setContentType("application/json; charset=utf-8");
	         PrintWriter pw = resp.getWriter(); 
	         pw.append(json);
	         pw.flush();
	         pw.close();
	      }
		
		
	}
	
} // class
