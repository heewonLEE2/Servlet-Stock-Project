package model.apiUtil.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;

public class ApiConstant {

    // HTTP 요청을 보내고 응답을 받을 수 있는 OkHttpClient 객체
    public static final OkHttpClient client = new OkHttpClient();

    // Gson 객체
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // API 키를 저장할 변수, properties 파일에서 읽어올 예정
    public static String apiKey;

    // 주식 배당 정보 API URL
    public static String STOCK_API;

    // 주식 가격 정보 API URL
    public static String STOCKINFO_API;

    // static 블록: 클래스가 처음 로드될 때 실행됨
    static {
        // properties 파일에서 API 키 읽어옴
        apiKey = getApiKey(); 

        if (apiKey != null) {
            // API 키가 정상적으로 읽혔다면 URL에 포함
            STOCK_API = "https://apis.data.go.kr/1160100/service/GetStocDiviInfoService/getDiviInfo?serviceKey="
                    + apiKey + "&pageNo=1&numOfRows=100&resultType=json&isinCd=";

            STOCKINFO_API = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey="
                    + apiKey + "&numOfRows=1&pageNo=1&resultType=json&itmsNm=";
        } else {
            // API 키가 null이면 에러 메시지 출력
            System.out.println("apiKey null");
        }
    }

    // api.properties 파일에서 STOCK_KEY 값을 읽어오는 메서드
    public static String getApiKey() {
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
            return prop.getProperty("STOCK_KEY");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
