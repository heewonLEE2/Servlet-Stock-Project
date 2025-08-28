package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockLikePatchCommand extends AbstractStockJson {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");

		String jsonId = req.getParameter("stockId");
		boolean jsonBool = Boolean.parseBoolean(req.getParameter("newLikeStatus"));

		stockJsonService.putJson(jsonId, jsonBool);
	}
}















