package model.apiUtil;

import java.io.IOException;

import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.StockDividendInfoModel;
import model.apiUtil.constant.ApiConstant;
import model.vo.StockDividendInfoVO;
import okhttp3.Request;
import okhttp3.Response;

// 주식 배당금 정보를 호출하는 API
public class StockDividendInfoAPI {

	public StockDividendInfoAPI() {
	}

	// 주식 배당금 api에서 회사이름, 배당기준일, 현급지급일, 배당타입, 한 주당 배당금, 현금 배당률를 listModel를 반환하는 메서드
	public static DefaultListModel<StockDividendInfoVO> getApi(String itmsNm) {

		String isinCd = StockInfoAPI.getIsinCd(itmsNm);

		if (isinCd == null || isinCd.isEmpty()) {
			System.out.println("유효하지 않은 종목명입니다. " + itmsNm);
			return null;
		}

		// request 요청 객체 만들기
		Request request = new Request.Builder().url(ApiConstant.STOCK_API + isinCd).build();
		Response response = null;
		try {
			// client response
			response = ApiConstant.client.newCall(request).execute();
			// 응답 요청 json에 저장
			String json = Objects.requireNonNull(response.body()).string();
//				System.out.println(request.url());
			// 아래에서 getAsJson으로 item 추출하기 위해 json 객체로 변환
			JsonObject jsonObj = ApiConstant.gson.fromJson(json, JsonObject.class);
			// item 키를 jsonarray로 변경
			JsonArray itemArray = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
					.getAsJsonArray("item");

			for (JsonElement ele : itemArray) {
				JsonObject obj = ele.getAsJsonObject();

				StockDividendInfoVO stockDividendInfoVO = new StockDividendInfoVO(obj.get("isinCdNm").getAsString(), // 회사이름
						obj.get("dvdnBasDt").getAsString(), // 배당기준일
						obj.get("cashDvdnPayDt").getAsString(), // 현급지급일
						obj.get("stckDvdnRcdNm").getAsString(), // 배당 타입
						obj.get("stckGenrDvdnAmt").getAsString(), // 한 주당 배당금
						obj.get("stckGenrCashDvdnRt").getAsString() // 현금배당률
				);

				StockDividendInfoModel.getStockDividendList().addElement(stockDividendInfoVO);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (response.body() != null && response != null) {
				ApiConstant.client.dispatcher().executorService().shutdown();
				ApiConstant.client.connectionPool().evictAll();
				response.body().close();
			}
		}

		return StockDividendInfoModel.getStockDividendList();
	} // getApi

}
