package model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import model.vo.StockDividendInfoVO;
import model.vo.StockJsonVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StockJsonModel {

	private static DefaultListModel<StockJsonVO> stockJsonList = new DefaultListModel<StockJsonVO>();

	public StockJsonModel() {
	}

	public static DefaultListModel<StockJsonVO> getStockJsonList() {
		return stockJsonList;
	}

}
