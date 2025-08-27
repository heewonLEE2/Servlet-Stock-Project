package command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import model.apiUtil.constant.ApiConstant;
import model.service.AiCommentService;
import model.service.impl.AiCommentServiceImpl;

public class AiCommentCommand implements StockWaveMainCommand{

	private AiCommentService aiCommentService;
	
	public AiCommentCommand() {
		this.aiCommentService = new AiCommentServiceImpl();
	}
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
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
		
		// 받아온 데이터를 String으로 변환
		String jsonString = ApiConstant.gson.fromJson(requestBody, JsonObject.class).get("question").toString();
		System.out.println("받은 JSON: "+ jsonString);
		
		// 객체 (자바 bean DTO 를 만들고 싶지 않아서) 맵을 만들어서 JSON로 변환해 데이터를 보냄
		Map<String, String> map = new HashMap<>();
		map.put("content", aiCommentService.aiCommentProc(jsonString));

		String json = ApiConstant.gson.toJson(map);
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.append(json);
		pw.flush();
		pw.close();
		
	}
}



