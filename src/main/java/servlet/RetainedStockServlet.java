package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.service.StockDiviService;
import model.service.impl.StockServiceImpl;
import model.vo.StockDividendInfoVO;

public class RetainedStockServlet extends HttpServlet {

	private static final long serialVersionUID = 16457585435L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		StockDiviService service = new StockServiceImpl();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String companyName = req.getParameter("stockName").trim();
		String numOfHoldings = req.getParameter("stockCount").trim();

		List<StockDividendInfoVO> allInfoList = service.getStockDiviList(companyName);

		Map<String, Object> dataForJs = new HashMap<String, Object>();

		String json = null;

		try {
//			사용자가 존재하지 않는 주식명을 입력했을 경우
			if (allInfoList == null) {

				resp.setCharacterEncoding("utf-8");

				dataForJs.put("errorMessage", "stockInfoNotExist");

				json = gson.toJson(dataForJs);

				resp.setContentType("application/json; charset=utf-8");
				resp.getWriter().write(json);

			} else {
				
				List<String> dividendList = dividendForThisYear(allInfoList);
				
				if(dividendList.size() == 1) {
					
					dataForJs.put("dividendCalc", calcResult(allInfoList, numOfHoldings));
					dataForJs.put("errorMessage", "올해 배당 이력 없음");
					
					json = gson.toJson(dataForJs);

					resp.setContentType("application/json; charset=utf-8");
					resp.getWriter().write(json);
					return;
					
				} else {
					resp.setCharacterEncoding("utf-8");
					
					dataForJs.put("dividendCalc", calcResult(allInfoList, numOfHoldings));
					dataForJs.put("dividendForThisYear", dividendList);

					json = gson.toJson(dataForJs);

					resp.setContentType("application/json; charset=utf-8");
					resp.getWriter().write(json);
					return;
				}
				
			}
		} catch(StringIndexOutOfBoundsException sbe) {
			System.out.println(sbe);
		}
			
			

	} // doGet

	/*
	 * 가장 최근 배당금 지급 금액 => 다음 배당 수익 예상 
	 * 올해 현금 배당 날짜들 => 배당 지급 현황
	 * 
	 */

	// 올해 배당 지급 날짜 리스트 반환 메소드 반환 형식(예시) => [6월, 9월, 11월]
	public static List<String> dividendForThisYear(List<StockDividendInfoVO> infoList)
			throws StringIndexOutOfBoundsException {

		List<String> resultList = new ArrayList<String>();

		// 올해 년도
		String thisYear = String.valueOf(LocalDate.now().getYear());
		resultList.add(thisYear + "년");

		infoList.stream().filter(vo -> vo.getCashDvdnPayDt() != null && !vo.getCashDvdnPayDt().isEmpty())
				// 올해 데이터만 필터링
				.filter(vo -> vo.getCashDvdnPayDt().substring(0, 4).equals(thisYear))
				// 월 오름차순 정렬
				.sorted((a, b) -> Integer.valueOf(a.getCashDvdnPayDt().substring(4, 6))
						- Integer.valueOf(b.getCashDvdnPayDt().substring(4, 6)))
				// result 리스트에 월만 추가
				.forEach(ele -> {
					if (ele.getCashDvdnPayDt().charAt(4) == '0') {
						resultList.add(ele.getCashDvdnPayDt().substring(5, 6) + "월");
					} else {
						resultList.add(ele.getCashDvdnPayDt().substring(4, 6) + "월");
					}
				});
		
		System.out.println(resultList);

		return resultList;

	} // dividendDateForThisYear

	// 사용자 예상 수익금 계산 후 반환하는 메소드
	public static String calcResult(List<StockDividendInfoVO> infoList, String numOfHoldings) {

		// 지난 배당금 지급일 동안의 한 주당 배당금으로 평균을 내서
		// 이번 달 예상 배당금을 구한다.
		List<String> lastThreeMonth = new ArrayList<String>();

		// 지난 배당금 지급일들의 주당 배당금을 담는 리스트
		List<StockDividendInfoVO> list = infoList.stream()
				.filter(vo -> vo.getCashDvdnPayDt() != null && !vo.getCashDvdnPayDt().isEmpty())
				// 날짜 내림차순 정렬
				.sorted((a, b) -> Integer.parseInt(b.getCashDvdnPayDt()) - Integer.parseInt(a.getCashDvdnPayDt()))
				.limit(3) // 최신 데이터를 3개만 가져온다
				.toList();

		for (StockDividendInfoVO vo : list) {
			lastThreeMonth.add(vo.getStckGenrDvdnAmt());
		}

		String result = null;

		if (lastThreeMonth != null) {
			int userHolding = Integer.valueOf(numOfHoldings); // 사용자 보유 주식 수
			int reduce = 0;
			for (int i = 0; i < 3; i++) {

				// 지난 배당금들을 모두 더한다.
				reduce += Integer.parseInt(lastThreeMonth.get(i));
			}
			// 지난 배당금으로 구한 평균 배당금 * 사용자 보유 주식 수 = 다음 예상 배당 수익금
			result = String.valueOf((reduce / 3) * userHolding + " 원");
		}

		return result;

	} // findRecentInfo

} // class
