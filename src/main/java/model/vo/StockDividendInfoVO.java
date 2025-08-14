package model.vo;

import java.io.Serializable;

//주식 배당금 정보 api에서 가져온 객체
public class StockDividendInfoVO implements Serializable{

	private static final long serialVersionUID = 65315165548554235L;
	
	private String isinCdNm; // 회사이름
	private String dvdnBasDt; // 배당기준일
	private String cashDvdnPayDt; // 현금지급일
	private String stckDvdnRcdNm; // 배당 타입
	private String stckGenrDvdnAmt; // 한 주당 배당금
	private String stckGenrCashDvdnRt; // 현금배당률
	private String stckParPrc; // 주식 가격

	public StockDividendInfoVO() {
	}

	public StockDividendInfoVO(String isinCdNm, String dvdnBasDt, String cashDvdnPayDt, String stckDvdnRcdNm,
			String stckGenrDvdnAmt, String stckGenrCashDvdnRt) {
		this.isinCdNm = isinCdNm;
		this.dvdnBasDt = dvdnBasDt;
		this.cashDvdnPayDt = cashDvdnPayDt;
		this.stckDvdnRcdNm = stckDvdnRcdNm;
		this.stckGenrDvdnAmt = stckGenrDvdnAmt;
		this.stckGenrCashDvdnRt = stckGenrCashDvdnRt;
	}

	public String getIsinCdNm() {
		return isinCdNm;
	}

	public void setIsinCdNm(String isinCdNm) {
		this.isinCdNm = isinCdNm;
	}

	public String getDvdnBasDt() {
		return dvdnBasDt;
	}

	public void setDvdnBasDt(String dvdnBasDt) {
		this.dvdnBasDt = dvdnBasDt;
	}

	public String getCashDvdnPayDt() {
		return cashDvdnPayDt;
	}

	public void setCashDvdnPayDt(String cashDvdnPayDt) {
		this.cashDvdnPayDt = cashDvdnPayDt;
	}

	public String getStckDvdnRcdNm() {
		return stckDvdnRcdNm;
	}

	public void setStckDvdnRcdNm(String stckDvdnRcdNm) {
		this.stckDvdnRcdNm = stckDvdnRcdNm;
	}

	public String getStckGenrDvdnAmt() {
		return stckGenrDvdnAmt;
	}

	public void setStckGenrDvdnAmt(String stckGenrDvdnAmt) {
		this.stckGenrDvdnAmt = stckGenrDvdnAmt;
	}

	public String getStckGenrCashDvdnRt() {
		return stckGenrCashDvdnRt;
	}

	public void setStckGenrCashDvdnRt(String stckGenrCashDvdnRt) {
		this.stckGenrCashDvdnRt = stckGenrCashDvdnRt;
	}

	public String getStckParPrc() {
		return stckParPrc;
	}

	public void setStckParPrc(String stckParPrc) {
		this.stckParPrc = stckParPrc;
	}

	@Override
	public String toString() {
		return "StockDividendInfoVO [isinCdNm=" + isinCdNm + ", dvdnBasDt=" + dvdnBasDt + ", cashDvdnPayDt="
				+ cashDvdnPayDt + ", stckDvdnRcdNm=" + stckDvdnRcdNm + ", stckGenrDvdnAmt=" + stckGenrDvdnAmt
				+ ", stckGenrCashDvdnRt=" + stckGenrCashDvdnRt + ", stckParPrc=" + stckParPrc + "]";
	}


}
