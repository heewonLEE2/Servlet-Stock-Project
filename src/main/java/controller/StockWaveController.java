package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.StockWaveCommand;

public class StockWaveController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();

		String command = null;

		if (requestURI != null && requestURI.length() > 0 && requestURI.endsWith(".stockwave")) {

			command = requestURI.substring(1).substring(0, req.getRequestURI().length() - 11);
		}
		System.out.println(command);

		String className = "command." + command.substring(0, 1).toUpperCase() + command.substring(1)
				+ "Command";

		try {

			Class commandClass = Class.forName(className);

			StockWaveCommand commandObj = (StockWaveCommand) commandClass.newInstance();

			commandObj.process(req, resp);

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // processRequest

} // class
