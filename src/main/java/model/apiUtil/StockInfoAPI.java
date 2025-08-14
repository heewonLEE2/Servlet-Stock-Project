package model.apiUtil;

import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.StockInfoModel;
import model.apiUtil.constant.ApiConstant;
import model.vo.StockInfoVO;
import okhttp3.Request;
import okhttp3.Response;

// 주식 시세 정보를 호출하는 API
public class StockInfoAPI {

	// 주식 배당금 api에서 사용할 isin 코드를 반환하는 메서드
	public static String getIsinCd(String itmsNm) {

		Request request = new Request.Builder().url(ApiConstant.STOCKINFO_API + itmsNm).build();
		String isinCd = null;
		Response response = null;

		try {
			response = ApiConstant.client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			JsonObject jsonObj = ApiConstant.gson.fromJson(json, JsonObject.class);
			JsonArray itemArray = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
					.getAsJsonArray("item");

			isinCd = itemArray.get(0).getAsJsonObject().get("isinCd").getAsString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response.body() != null && response != null) {
				response.body().close();
			}
		}

		return isinCd;

	} // getIsinCd

	// 주식 시세 정보에서 시가총액, isinCode, 시가, 종가, 고가, 저가 데이터를 담는 listModel 반환하는 메서드
	public static DefaultListModel<StockInfoVO> getStockInfo(String itmsNm) {

		Request request = new Request.Builder().url(ApiConstant.STOCKINFO_API + itmsNm).build();
		Response response = null;
		try {
			response = ApiConstant.client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			JsonObject jsonObj = ApiConstant.gson.fromJson(json, JsonObject.class);
			JsonArray itemArray = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
					.getAsJsonArray("item");

			for (JsonElement ele : itemArray) {
				JsonObject obj = ele.getAsJsonObject();

				StockInfoVO stockInfoVO = new StockInfoVO(obj.get("mrktTotAmt").getAsString(), // 시가총액
						obj.get("isinCd").getAsString(), // isinCode
						obj.get("mkp").getAsString(), // 시가
						obj.get("clpr").getAsString(), // 종가
						obj.get("hipr").getAsString(), // 고가
						obj.get("lopr").getAsString() // 저가
				);

				StockInfoModel.getStockInfoList().addElement(stockInfoVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response.body() != null && response != null) {
				response.body().close();
			}
		}

		return StockInfoModel.getStockInfoList();

	} // getStockInfo

}
