package model.vo;

import java.io.Serializable;

// 주식 시세정보 api에서 가져온 객체
public class StockInfoVO implements Serializable {

	private static final long serialVersionUID = 48465452154544545L;
	
	private String mrktTotAmt; // 시가총액
	private String isinCd; // isinCd
	private String mkp; // 시가
	private String clpr; // 종가
	private String hipr; // 고가
	private String lopr; // 저가

	public StockInfoVO() {
	}

	public StockInfoVO(String mrktTotAmt, String isinCd, String mkp, String clpr, String hipr, String lopr) {
		this.mrktTotAmt = mrktTotAmt;
		this.isinCd = isinCd;
		this.mkp = mkp;
		this.clpr = clpr;
		this.hipr = hipr;
		this.lopr = lopr;
	}

	public String getMrktTotAmt() {
		return mrktTotAmt;
	}

	public void setMrktTotAmt(String mrktTotAmt) {
		this.mrktTotAmt = mrktTotAmt;
	}

	public String getIsinCd() {
		return isinCd;
	}

	public void setIsinCd(String isinCd) {
		this.isinCd = isinCd;
	}

	public String getMkp() {
		return mkp;
	}

	public void setMkp(String mkp) {
		this.mkp = mkp;
	}

	public String getClpr() {
		return clpr;
	}

	public void setClpr(String clpr) {
		this.clpr = clpr;
	}

	public String getHipr() {
		return hipr;
	}

	public void setHipr(String hipr) {
		this.hipr = hipr;
	}

	public String getLopr() {
		return lopr;
	}

	public void setLopr(String lopr) {
		this.lopr = lopr;
	}

	@Override
	public String toString() {
		return "StockInfo [mrktTotAmt=" + mrktTotAmt + ", isinCd=" + isinCd + ", mkp=" + mkp + ", clpr=" + clpr
				+ ", hipr=" + hipr + ", lopr=" + lopr + "]";
	}
}
