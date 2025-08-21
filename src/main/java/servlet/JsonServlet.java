package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.apiUtil.constant.ApiConstant;
import model.service.StockJsonService;
import model.service.impl.StockJsonServiceImpl;
import model.vo.StockJsonVO;

public class JsonServlet extends HttpServlet{

   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
      
      StockJsonService jsonService = new StockJsonServiceImpl();
      
      List<StockJsonVO> jsonList = jsonService.getStockJsonList();
      
      
      if(jsonList.isEmpty()) {
         System.out.println("json 데이터 불러오기 오류!! ");
      }else {
         String json = ApiConstant.gson.toJson(jsonList);
         
         resp.setContentType("application/json; charset=utf-8");
         PrintWriter pw = resp.getWriter(); 
         pw.append(json);
         pw.flush();
         pw.close();
      }
      
   }
   
} // class