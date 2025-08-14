package model.service;

import java.io.IOException;
import java.util.List;

import model.vo.StockInfoVO;

public interface StockInfoService {
	
	public abstract List<StockInfoVO> getStockInfoList(String itmsNm) throws IOException;

}
