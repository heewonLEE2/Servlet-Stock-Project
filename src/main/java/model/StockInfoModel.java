package model;

import javax.swing.DefaultListModel;

import model.vo.StockInfoVO;
// 주식 시세정보 api를 요청 객체로 불러와서 list에 담음
public class StockInfoModel {

	private static DefaultListModel<StockInfoVO> stockInfoList = new DefaultListModel<StockInfoVO>();

	public StockInfoModel() {
	}

	public static DefaultListModel<StockInfoVO> getStockInfoList() {
		return stockInfoList;
	}

} // class
