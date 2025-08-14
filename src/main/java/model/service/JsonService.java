package model.service;

import java.io.IOException;
import java.util.List;

import model.vo.StockDividendInfoVO;
import model.vo.StockJsonVO;

public interface JsonService {

	
	public abstract List<StockJsonVO> getStockJsonList() throws IOException;
	
}
