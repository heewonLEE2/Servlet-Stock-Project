package model.dao;

import java.io.IOException;
import java.util.List;

import model.vo.StockJsonVO;

public interface StockJsonDao {

	
	public abstract List<StockJsonVO> getStockJsonList() throws IOException;
	
	public abstract void putJson(String id, Boolean isLike) throws IOException;
}
