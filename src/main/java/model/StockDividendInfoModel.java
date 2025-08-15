package model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.cdimascio.dotenv.Dotenv;
import model.vo.StockDividendInfoVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 주식 배당금 정보 api를 담는 모델
public class StockDividendInfoModel {

	private static DefaultListModel<StockDividendInfoVO> stockDividendList = new DefaultListModel<StockDividendInfoVO>();

	public StockDividendInfoModel() {
	}
	
	public static DefaultListModel<StockDividendInfoVO> getStockDividendList() {
		return stockDividendList;
	}
			
} // class
	
