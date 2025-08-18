package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.apiUtil.constant.ApiConstant;
import model.service.JsonService;
import model.service.impl.JsonServiceImpl;
import model.vo.StockJsonVO;

public class JsonServlet extends HttpServlet{

   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
      System.out.println("json Servlet 요청 들어옴!");
      
      JsonService jsonService = new JsonServiceImpl();
      
      List<StockJsonVO> jsonList = jsonService.getStockJsonList();
      
      
      if(jsonList.isEmpty()) {
         System.out.println("json 데이터 불러오기 오류!! ");
      }else {
         System.out.println("json Servlet 요청응답 성공");
         String json = ApiConstant.gson.toJson(jsonList);
         
         resp.setContentType("application/json; charset=utf-8");
         PrintWriter pw = resp.getWriter(); 
         pw.append(json);
         pw.flush();
         pw.close();
      }
      
   }
   
} // class