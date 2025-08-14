package model.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.gson.reflect.TypeToken;

import model.StockJsonModel;
import model.apiUtil.constant.ApiConstant;
import model.dao.JsonDao;
import model.vo.StockDividendInfoVO;
import model.vo.StockJsonVO;
import okhttp3.Request;
import okhttp3.Response;

public class JsonDaoImpl implements JsonDao {

	public JsonDaoImpl() {
	}

	@Override
	public List<StockJsonVO> getStockJsonList() throws IOException {
		
		Request request = new Request.Builder().url("http://localhost:3000/stock").build();
		Response response = null;
		List<StockJsonVO> stockJsonList = new ArrayList<StockJsonVO>();
		
		
		response = ApiConstant.client.newCall(request).execute();
		String json = Objects.requireNonNull(response.body()).string();
		List<Map<String, Object>> data = ApiConstant.gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
		}.getType());

		
		for (Map<String, Object> item : data) {
			int id = Integer.parseInt(item.get("id").toString());
			String name = (String) item.get("기업명");
			String category = (String) item.get("업종");
			String stockNum = (String) item.get("상장주식수(주)");
			boolean isLike = Boolean.parseBoolean(item.get("isLike").toString());
			int count = ((Number) data.get(0).get("count")).intValue();

			StockJsonVO vo = new StockJsonVO(id, name, category, stockNum, isLike, count);
			stockJsonList.add(vo);
		}
		return stockJsonList;
	}
} // class






