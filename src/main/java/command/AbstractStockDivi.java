package command;

import model.service.StockDiviService;
import model.service.impl.StockDiviServiceImpl;

public abstract class AbstractStockDivi implements StockWaveMainCommand{

	public StockDiviService stockDiviService;
	
	public AbstractStockDivi() {
		stockDiviService = new StockDiviServiceImpl();
	}
	
}
