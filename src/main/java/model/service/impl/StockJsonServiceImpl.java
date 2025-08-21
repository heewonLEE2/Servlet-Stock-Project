package model.service.impl;

import java.io.IOException;
import java.util.List;

import model.dao.StockJsonDao;
import model.dao.impl.StockJsonDaoImpl;
import model.service.StockJsonService;
import model.vo.StockJsonVO;

public class StockJsonServiceImpl implements StockJsonService{

	
	StockJsonDao jsonDao;
	
	public StockJsonServiceImpl() {
		jsonDao = new StockJsonDaoImpl();
	}
	
	@Override
	public List<StockJsonVO> getStockJsonList() throws IOException {
		return jsonDao.getStockJsonList();
	}
	
	@Override
	public void putJson(String id, Boolean isLike) throws IOException {
		jsonDao.putJson(id, isLike);
	}
	
}



















