package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.service.JsonService;
import model.service.impl.JsonServiceImpl;

public class PutJsonServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		JsonService jsonService = new JsonServiceImpl();
		
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
	    System.out.println("받은 JSON: " + requestBody);

	    // JSON파싱
	    Gson gson = new Gson();
	    JsonObject jsonObj = gson.fromJson(requestBody, JsonObject.class);

	    String jsonId = jsonObj.get("stockId").getAsString();
	    boolean jsonBool = jsonObj.get("isLike").getAsBoolean();

	    System.out.println("stockId: " + jsonId);
	    System.out.println("isLike: " + jsonBool);

	    jsonService.putJson(jsonId, jsonBool);
		
	}
	
}// class
















