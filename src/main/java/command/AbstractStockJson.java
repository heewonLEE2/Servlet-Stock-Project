package command;

import model.service.StockJsonService;
import model.service.impl.StockJsonServiceImpl;

public abstract class AbstractStockJson implements StockWaveMainCommand{

	public StockJsonService stockJsonService;
	
	public AbstractStockJson() {
		stockJsonService = new StockJsonServiceImpl();
	}
	
}
