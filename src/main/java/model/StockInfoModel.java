package model;

import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.vo.StockInfoVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 주식 시세정보 api를 요청 객체로 불러와서 list에 담음
public class StockInfoModel {

	private static DefaultListModel<StockInfoVO> stockInfoList = new DefaultListModel<StockInfoVO>();

	public StockInfoModel() {
	}

	public static DefaultListModel<StockInfoVO> getStockInfoList() {
		return stockInfoList;
	}

} // class
