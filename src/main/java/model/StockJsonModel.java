package model;

import javax.swing.DefaultListModel;

import model.vo.StockJsonVO;

public class StockJsonModel {

	private static DefaultListModel<StockJsonVO> stockJsonList = new DefaultListModel<StockJsonVO>();

	public StockJsonModel() {
	}

	public static DefaultListModel<StockJsonVO> getStockJsonList() {
		return stockJsonList;
	}

}
