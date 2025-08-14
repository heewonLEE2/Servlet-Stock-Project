package model.apiUtil;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.common.reflect.TypeToken;

import model.StockJsonModel;
import model.apiUtil.constant.ApiConstant;
import model.vo.StockJsonVO;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// 주식 제목, 시가, 종가 등을 호출하는 json
public class StockJson {

	
	public static DefaultListModel<StockJsonVO> getJson() {
		// request 요청 객체 만들기
		Request request = new Request.Builder().url("http://localhost:3000/stock").build();
		Response response = null;
		try {

			response = ApiConstant.client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			List<Map<String, Object>> data = ApiConstant.gson.fromJson(json,
					new TypeToken<List<Map<String, Object>>>() {
					}.getType());

			for (Map<String, Object> item : data) {
				int id = Integer.parseInt(item.get("id").toString());
				String name = (String) item.get("기업명");
				String category = (String) item.get("업종");
				String stockNum = (String) item.get("상장주식수(주)");
				boolean isLike = Boolean.parseBoolean(item.get("isLike").toString());
				int count = ((Number) data.get(0).get("count")).intValue();

				StockJsonVO vo = new StockJsonVO(id, name, category, stockNum, isLike, count);
				StockJsonModel.getStockJsonList().addElement(vo);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (response.body() != null && response != null) {
				response.body().close();
			}
		}

		return StockJsonModel.getStockJsonList();
	} // getJson

	// 즐겨찾기 여부 변경
	public static void patchJson(String id, Boolean isLike) {
		// request 요청 객체 만들기
		String jsonBody;

		// 파라미터를 Boolean 으로 바꿔야 잘 작동한다.
		if (isLike) {
			jsonBody = "{\"isLike\" : " + (boolean) false + "}";
		} else {
			jsonBody = "{\"isLike\" : " + (boolean) true + "}";
		}
		// String jsonBody = "{\"isLike\" : " + (isLike.equals('X') ? (boolean)false :
		// (boolean)true) + "}";

		RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonBody);
		Request request = new Request.Builder().url("http://localhost:3000/stock/" + id).patch(body).build();
		Response response = null;
		try {
			response = ApiConstant.client.newCall(request).execute();
			// 리스트 삭제후 새로 고침
			StockJsonModel.getStockJsonList().removeAllElements();
			getJson();
			System.out.println("put 요청 성공!");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (response.body() != null && response != null) {
				response.body().close();
			}
		}

	} // getJson

}
