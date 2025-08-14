package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.JsonService;
import model.service.impl.JsonServiceImpl;

public class PutJsonServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		JsonService jsonService = new JsonServiceImpl();
		
		String jsonId = req.getParameter("name");
		String jsonBool = req.getParameter("likeState");
		
		System.out.println(jsonId);
		System.out.println(jsonBool);
		
		jsonService.putJson(jsonId, jsonBool.equals("false")? false : true);
		
		resp.sendRedirect("/html/test.jsp");
	}
	
}// class
















