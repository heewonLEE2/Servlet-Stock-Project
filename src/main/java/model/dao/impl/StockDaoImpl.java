package model.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.apiUtil.StockInfoAPI;
import model.apiUtil.constant.ApiConstant;
import model.dao.StockDao;
import model.vo.StockDividendInfoVO;
import okhttp3.Request;
import okhttp3.Response;

public class StockDaoImpl implements StockDao{
	
	
	public StockDaoImpl() {
	}

	@Override
	public List<StockDividendInfoVO> getStockDiviList(String itmsNm) throws IOException{
		String isinCd = StockInfoAPI.getIsinCd(itmsNm);

		if (isinCd == null || isinCd.isEmpty()) {
			System.out.println("유효하지 않은 종목명입니다. " + itmsNm);
			return null;
		}

		// request 요청 객체 만들기
		Request request = new Request.Builder().url(ApiConstant.STOCK_API + isinCd).build();
		Response response = null;
			// client response
			response = ApiConstant.client.newCall(request).execute();
			// 응답 요청 json에 저장
			String json = Objects.requireNonNull(response.body()).string();
			
			// 아래에서 getAsJson으로 item 추출하기 위해 json 객체로 변환
			JsonObject jsonObj = ApiConstant.gson.fromJson(json, JsonObject.class);
			// item 키를 jsonarray로 변경
			JsonArray itemArray = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
					.getAsJsonArray("item");
			
			List<StockDividendInfoVO> diviList = new ArrayList<StockDividendInfoVO>();

			for (JsonElement ele : itemArray) {
				JsonObject obj = ele.getAsJsonObject();
				
				StockDividendInfoVO stockDividendInfoVO = new StockDividendInfoVO(
					obj.get("isinCdNm").getAsString(), // 회사이름
					obj.get("dvdnBasDt").getAsString(), // 배당기준일
					obj.get("cashDvdnPayDt").getAsString(), // 현급지급일
					obj.get("stckDvdnRcdNm").getAsString(), // 배당 타입
					obj.get("stckGenrDvdnAmt").getAsString(), // 한 주당 배당금
					obj.get("stckGenrCashDvdnRt").getAsString() // 현금배당률
				);
					diviList.add(stockDividendInfoVO);

				}
			return diviList;
	}
	
} // class
