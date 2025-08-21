package servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import model.service.StockJsonService;
import model.service.StockInfoService;
import model.service.StockDiviService;
import model.service.impl.StockJsonServiceImpl;
import model.service.impl.StockInfoServiceImpl;
import model.service.impl.StockServiceImpl;
import model.vo.StockJsonVO;

public class TestMain {
	
	public static void main(String[] args) {

		StockDiviService stockService = new StockServiceImpl();
		StockJsonService jsonService = new StockJsonServiceImpl();
		StockInfoService stockInfoService = new StockInfoServiceImpl();

		try {
//			List<StockDividendInfoVO> stockDivi = stockService.getStockDiviList("삼성전자");
//			int listSize = stockDivi.size();
//			
//			System.out.println(stockDivi.get(listSize-1));
			
			
				stockInfoService.getStockInfoList("BGF").forEach(System.out::println);
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	}
}
