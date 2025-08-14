package model.dao;

import java.io.IOException;
import java.util.List;

import model.vo.StockJsonVO;

public interface JsonDao {

	
	public abstract List<StockJsonVO> getStockJsonList() throws IOException;
	
}
