package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarModel {

	public CalendarModel() {
	}

	public List<String> getCalendarDays(int year, int month) {
		// 캘린더 객체 생성
		Calendar calendar = Calendar.getInstance();
		// 해당 연도와 월을 1일로 설정
		calendar.set(year, month, 1);
		
		// 해당 월을 시작 요일
		int startDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 해당 월의 총 일 수
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		// days 담는 리스트
		List<String> dayList = new ArrayList<String>(42);
		
		// 6*7 표에서 해당 월의 시작요일 전에는 빈칸을 넣음
		for(int i=1; i<startDay; i++) {
			dayList.add("");
		}
		
		// 1부터 maxDay까지 날짜 넣음
		for(int i=1; i<=maxDay; i++) {
			dayList.add(String.valueOf(i));
		}
		
		while(dayList.size() < 42) {
			dayList.add("");
		}
		
		return dayList;
	} // getCalendarDays

}
