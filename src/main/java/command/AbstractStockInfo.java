package command;

import model.service.StockInfoService;
import model.service.impl.StockInfoServiceImpl;

public abstract class AbstractStockInfo implements StockWaveMainCommand{

	public StockInfoService stockInfoService;
	
	public AbstractStockInfo() {
		stockInfoService = new StockInfoServiceImpl();
	}
	
}
