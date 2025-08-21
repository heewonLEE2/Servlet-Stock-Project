package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StockWaveMainCommand {

	public abstract void process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
}
