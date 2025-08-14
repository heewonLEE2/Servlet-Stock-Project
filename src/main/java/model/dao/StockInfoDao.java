package model.dao;

import java.io.IOException;
import java.util.List;

import model.vo.StockInfoVO;

public interface StockInfoDao {
	
	public abstract List<StockInfoVO> getStockInfoList(String itmsNm) throws IOException;

}
