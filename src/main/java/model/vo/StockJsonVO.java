package model.vo;

import java.io.Serializable;

// json 서버에서 가져온 주식 객체
public class StockJsonVO implements Serializable{

	private static final long serialVersionUID = 659521212121564654L;
	
	private int id; // 아이디
	private String name; // 주식종목명
	private String category; // 회사 업종
	private String stockNum; // 상장주식 수 
	private boolean isLike; // 즐겨찾기여부
	private int count; // 가지고 있는 주 수
	

	public StockJsonVO() {
	}

	public StockJsonVO(int id, String name, String category, String stockNum, boolean isLike, int count) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.stockNum = stockNum;
		this.isLike = isLike;
		this.count = count;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StockJsonVO [id=" + id + ", name=" + name + ", category=" + category + ", stockNum=" + stockNum
				+ ", isLike=" + isLike + ", count=" + count + "]";
	}

}
