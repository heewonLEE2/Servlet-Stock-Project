package model.service.impl;

import java.io.IOException;
import java.util.List;

import model.dao.JsonDao;
import model.dao.impl.JsonDaoImpl;
import model.service.JsonService;
import model.vo.StockJsonVO;

public class JsonServiceImpl implements JsonService{

	
	JsonDao jsonDao;
	
	public JsonServiceImpl() {
		jsonDao = new JsonDaoImpl();
	}
	
	@Override
	public List<StockJsonVO> getStockJsonList() throws IOException {
		return jsonDao.getStockJsonList();
	}
	
}



















