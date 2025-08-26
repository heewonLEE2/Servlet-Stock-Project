package model.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.apiUtil.constant.ApiConstant;
import model.service.AiCommentService;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AiCommentServiceImpl implements AiCommentService {

	private static String openApiKey;

	static {
		openApiKey = getOpenApiKey();
	}

	@Override
	public String aiCommentProc(String question) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		// 요청 JSON 구성 (Responses API)
		ArrayNode input = mapper.createArrayNode();
		ObjectNode sys = mapper.createObjectNode();
		sys.put("role", "system");
		sys.put("content", "한글로 답하고, 핵심만 5문장 이내로 요약해줘. 너는 친절한 주식 전문가다. 질문은 하지말고 대답 형식으로만!");
		input.add(sys);

		ObjectNode user = mapper.createObjectNode();
		user.put("role", "user");
		user.put("content", question == null ? "" : question);
		input.add(user);

		ObjectNode root = mapper.createObjectNode();
		root.put("model", "gpt-5-mini");
		root.set("input", input);

		// 추론 강도: minimal/low/medium/high
		ObjectNode reasoning = mapper.createObjectNode();
		reasoning.put("effort", "minimal"); // 필요에 따라 조절
		root.set("reasoning", reasoning);

		// 출력 토큰 한도 (Responses API는 max_output_tokens)
		root.put("max_output_tokens", 512);

		String requestJson = mapper.writeValueAsString(root);

		RequestBody body = RequestBody.create(
		    MediaType.get("application/json; charset=utf-8"),
		    requestJson
		);

		Request request = new Request.Builder()
		    .url("https://api.openai.com/v1/responses")
		    .addHeader("Authorization", "Bearer " + openApiKey)
		    .addHeader("Content-Type", "application/json")
		    .post(body)
		    .build();

		try (Response resp = ApiConstant.client.newCall(request).execute()) {
		    String respBody = resp.body() != null ? resp.body().string() : "";
		    if (!resp.isSuccessful()) {
		        // 실패 원인 로그 (Responses API는 본문에 에러 사유를 줌)
		        throw new IOException("OpenAI API 오류: HTTP " + resp.code() + " - " + respBody);
		    }

		    JsonNode rootNode = mapper.readTree(respBody);

		    // 1) SDK에서는 보통 output_text를 제공하지만, raw JSON에서는 output 배열에서 조립해야 할 수 있음
		    String direct = rootNode.path("output_text").asText(null);
		    if (direct != null && !direct.isEmpty()) {
		        return direct;
		    }

		    // 2) output 배열에서 assistant 메시지의 output_text 수집
		    StringBuilder sb = new StringBuilder();
		    for (JsonNode item : rootNode.path("output")) {
		        if ("message".equals(item.path("type").asText())
		                && "assistant".equals(item.path("role").asText())) {
		            for (JsonNode c : item.path("content")) {
		                if ("output_text".equals(c.path("type").asText())) {
		                    sb.append(c.path("text").asText());
		                }
		            }
		        }
		    }
		    String text = sb.toString().trim();
		    if (text.isEmpty()) {
		        throw new IOException("응답 파싱 실패: assistant output_text 없음 - " + respBody);
		    }
		    return text;
		}
	} // aiCommentProc

	// api.properties 파일에서 STOCK_KEY 값을 읽어오는 메서드
	public static String getOpenApiKey() {
		Properties prop = new Properties();
		// try-with-resources로 InputStream 자동 닫기, finally 안써도 됨
		try (InputStream input = ApiConstant.class.getClassLoader().getResourceAsStream("api.properties")) {

			if (input == null) {
				// 파일이 클래스패스에 없으면 null 반환
				System.out.println("api.properties 파일을 찾을 수 없습니다.");
				return null;
			}

			// properties 파일 로드
			prop.load(input);
			// STOCK_KEY 값 반환
			return prop.getProperty("OPENAI_KEY");

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	} // getOpenApiKey

} // class
