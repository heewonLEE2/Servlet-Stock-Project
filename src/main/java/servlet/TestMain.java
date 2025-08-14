package servlet;

import java.io.IOException;

import model.service.JsonService;
import model.service.StockInfoService;
import model.service.StockService;
import model.service.impl.JsonServiceImpl;
import model.service.impl.StockInfoServiceImpl;
import model.service.impl.StockServiceImpl;

public class TestMain {
	
	public static void main(String[] args) {

		StockService stockService = new StockServiceImpl();
		JsonService jsonService = new JsonServiceImpl();
		StockInfoService stockInfoService = new StockInfoServiceImpl();

		try {
			
//			stockService.getStockDiviList("삼성전자").stream().forEach(System.out::println);
			
//			 jsonService.getStockJsonList().stream().forEach(System.out::println);
			
			stockInfoService.getStockInfoList("삼성전자").stream().forEach(System.out::println);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	}
}
