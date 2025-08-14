package model.service.impl;

import java.io.IOException;
import java.util.List;

import model.dao.StockInfoDao;
import model.dao.impl.StockInfoDaoImpl;
import model.service.StockInfoService;
import model.vo.StockInfoVO;

public class StockInfoServiceImpl implements StockInfoService {
	
	StockInfoDao stockInfoDao;
	
	public StockInfoServiceImpl() {
		stockInfoDao = new StockInfoDaoImpl();
	}

	@Override
	public List<StockInfoVO> getStockInfoList(String itmsNm) throws IOException {
		return stockInfoDao.getStockInfoList(itmsNm);
	}

}
