package model.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.apiUtil.StockInfoAPI;
import model.apiUtil.constant.ApiConstant;
import model.dao.StockDiviDao;
import model.dao.impl.StockDiviDaoImpl;
import model.service.StockDiviService;
import model.vo.StockDividendInfoVO;
import okhttp3.Request;
import okhttp3.Response;

public class StockServiceImpl implements StockDiviService {

	StockDiviDao stockDao;

	public StockServiceImpl() {
		stockDao = new StockDiviDaoImpl();
	}

	@Override
	public List<StockDividendInfoVO> getStockDiviList(String itmsNm) throws IOException {
		return stockDao.getStockDiviList(itmsNm);
	}
	

}








