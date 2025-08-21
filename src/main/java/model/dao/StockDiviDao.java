package model.dao;

import java.io.IOException;
import java.util.List;

import model.vo.StockDividendInfoVO;

public interface StockDiviDao {

	public abstract List<StockDividendInfoVO> getStockDiviList(String itmsNm) throws IOException;
	
}
