package command;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import model.apiUtil.constant.ApiConstant;

public class StockLikePatchCommand extends AbstractStockJson {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 요청 body를 문자열로 읽기 (body 담아서 보내는 요청은 getparameter 로 읽기 불가능
		StringBuilder sb = new StringBuilder();
		String line;
		// req.getReader 로 json 요청 읽기
		try (BufferedReader reader = req.getReader()) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}
		String requestBody = sb.toString();
		// System.out.println("받은 JSON: " + requestBody);

		JsonObject jsonObj = ApiConstant.gson.fromJson(requestBody, JsonObject.class);

		String jsonId = jsonObj.get("stockId").getAsString();
		boolean jsonBool = jsonObj.get("isLike").getAsBoolean();

		stockJsonService.putJson(jsonId, jsonBool);
	}
}
