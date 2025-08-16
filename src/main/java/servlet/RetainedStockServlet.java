package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.StockService;
import model.service.impl.StockServiceImpl;
import model.vo.StockDividendInfoVO;

public class RetainedStockServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		StockService service = new StockServiceImpl();
		
		String companyName = req.getParameter("companyName");
		String numOfHoldings = req.getParameter("holding");
		
		List<StockDividendInfoVO> allInfoList = service.getStockDiviList(companyName);

		if(allInfoList == null || allInfoList.isEmpty()) {
			System.out.println("데이터 불러오는 도중 오류 발생");
		} else {
			resp.setCharacterEncoding("utf-8");
			
			String dividendCalc = findRecentInfo(allInfoList, numOfHoldings);
			
			req.setAttribute("dividendCalc", dividendCalc);
			req.setAttribute("dividendForThisYear", dividendDateForThisYear(allInfoList));
			
			// attribute 유지, 포워딩
			RequestDispatcher dispatcher = req.getRequestDispatcher("/html/dividendCalcResult.jsp");
			dispatcher.forward(req, resp);
		}
		
	} // doGet
	
	/*
	 * 가장 최근 배당금 지급 금액 => 다음 배당 수익 예상
	 * 올해 현금 배당 날짜들 => 배당 지급 현황
	 * Map으로 한꺼번에 담아서 view에 전달
	 * */

	// 올해 배당 지급 날짜 리스트 반환 메소드 반환 형식(예시) => [6월, 9월, 11월]
	public static List<String> dividendDateForThisYear(List<StockDividendInfoVO> infoList) {
		
		SimpleDateFormat month = new SimpleDateFormat("M");
		List<String> resultList = new ArrayList<String>();
		
		// 올해 년도
		String thisYear = String.valueOf(LocalDate.now().getYear());
		
		infoList.stream().filter(vo -> !(vo.getCashDvdnPayDt()==null) 
				|| vo.getCashDvdnPayDt().isEmpty())
			// 올해 데이터만 필터링
			.filter(vo -> vo.getCashDvdnPayDt().substring(0, 4).equals(thisYear))
			// 월 오름차순 정렬
			.sorted((a, b) -> Integer.valueOf(a.getCashDvdnPayDt().substring(4, 6))
						- Integer.valueOf(b.getCashDvdnPayDt().substring(4, 6)))
			// result 리스트에 월만 추가
			.forEach(ele -> {
				if(ele.getCashDvdnPayDt().indexOf(4) == 0) {
					resultList.add(ele.getCashDvdnPayDt().substring(5, 6) + "월");
				} else {
					resultList.add(ele.getCashDvdnPayDt().substring(4, 6) + "월");
				}
			});
		
		return resultList;

	}
	
	// 사용자 예상 수익금 계산 후 반환하는 메소드
	public static String findRecentInfo(List<StockDividendInfoVO> infoList, String numOfHoldings) {
		  StockDividendInfoVO recentInfo =  infoList.stream()
				.filter(vo -> !(vo.getCashDvdnPayDt()==null) || vo.getCashDvdnPayDt().isEmpty()) 
				.max(Comparator.comparingInt(vo -> Integer.valueOf(vo.getCashDvdnPayDt())))
				.orElse(null);
			
		  String result = null;
		  
		   if(!(recentInfo==null)) {
			   int userHolding = Integer.valueOf(numOfHoldings);
			   int dividendAmt = Integer.valueOf(recentInfo.getStckGenrDvdnAmt());
			   
			   result = Integer.toString(userHolding * dividendAmt);
		   }

			return result;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // class
