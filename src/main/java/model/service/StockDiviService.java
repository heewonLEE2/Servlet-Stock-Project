package model.service;

import java.io.IOException;
import java.util.List;

import model.vo.StockDividendInfoVO;

public interface StockDiviService {

	public abstract List<StockDividendInfoVO> getStockDiviList(String itmsNm) throws IOException;
	
	
	
}
