package model;

import javax.swing.DefaultListModel;

import model.vo.StockDividendInfoVO;

// 주식 배당금 정보 api를 담는 모델
public class StockDividendInfoModel {

	private static DefaultListModel<StockDividendInfoVO> stockDividendList = new DefaultListModel<StockDividendInfoVO>();

	public StockDividendInfoModel() {
	}
	
	public static DefaultListModel<StockDividendInfoVO> getStockDividendList() {
		return stockDividendList;
	}
			
} // class
	
