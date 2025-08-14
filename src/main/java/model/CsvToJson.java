package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;

public class CsvToJson {
    public static void main(String[] args) throws Exception {
        String csvPath = "C:\\heewon\\TeamProject\\JavaProject\\StockDividends\\KOSPI_company.csv";
        String jsonPath = "C:\\heewon\\jsonServer\\stock.json";

        CSVReader csvReader = new CSVReader(new FileReader(csvPath));
        String[] headers = csvReader.readNext(); // 첫 줄 읽어 컬럼명 저장

        List<Map<String, Object>> jsondata = new ArrayList<>();
        String[] values;
        
        int count = 1;
        int num = 0;
        
        while ((values = csvReader.readNext()) != null) {
            Map<String, Object> row = new LinkedHashMap<>();
            // 원하는 컬럼만 추출
            row.put("id", count);
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals("기업명") || headers[i].equals("업종") || headers[i].equals("상장주식수(주)")) {
                    row.put(headers[i], values.length > i ? values[i] : "");
                }
            }
            // 추가 필드
            row.put("isLike", false);
            row.put("count", num);
            count++;
            jsondata.add(row);
        }
        csvReader.close();

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("stock", jsondata);
        
        // JSON 파일로 저장
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(jsonPath);
        gson.toJson(root, writer);
        writer.close();

        System.out.println("변환 완료");
    }
}
