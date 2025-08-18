package servlet;

import java.io.IOError;
import java.io.IOException;

import model.service.StockInfoService;
import model.service.impl.StockInfoServiceImpl;

public class TestMain2 {

	
	public static void main(String[] args) {
		
		StockInfoService stockInfoService = new StockInfoServiceImpl();
		
		
		try {
			
			stockInfoService.getStockInfoList("삼성전자").forEach(System.out::println);
			
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
