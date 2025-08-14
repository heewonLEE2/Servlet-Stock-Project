package model.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.apiUtil.constant.ApiConstant;
import model.dao.StockInfoDao;
import model.vo.StockInfoVO;
import okhttp3.Request;
import okhttp3.Response;

public class StockInfoDaoImpl implements StockInfoDao {

	public StockInfoDaoImpl() {
	}

	@Override
	public List<StockInfoVO> getStockInfoList(String itmsNm) throws IOException {

		Request request = new Request.Builder().url(ApiConstant.STOCKINFO_API + itmsNm).build();
		Response response = null;
		response = ApiConstant.client.newCall(request).execute();
		String json = Objects.requireNonNull(response.body()).string();
		JsonObject jsonObj = ApiConstant.gson.fromJson(json, JsonObject.class);
		JsonArray itemArray = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
				.getAsJsonArray("item");

		List<StockInfoVO> stockInfoList = new ArrayList<StockInfoVO>();

		for (JsonElement ele : itemArray) {
			JsonObject obj = ele.getAsJsonObject();

			StockInfoVO stockInfoVO = new StockInfoVO(
					obj.get("mrktTotAmt").getAsString(), // 시가총액
					obj.get("isinCd").getAsString(), // isinCode
					obj.get("mkp").getAsString(), // 시가
					obj.get("clpr").getAsString(), // 종가
					obj.get("hipr").getAsString(), // 고가
					obj.get("lopr").getAsString() // 저가
			);
			stockInfoList.add(stockInfoVO);

		}

		return stockInfoList;
	}

}
