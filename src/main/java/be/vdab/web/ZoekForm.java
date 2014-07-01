package be.vdab.web;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

class ZoekForm {
	private final static int MIN_PRIJS = 0;
	private final static int MIN_JAARTAL = 1;
	private String titel;
	private String schilderNaam;
	private String stijl;
	@Min(MIN_PRIJS)
	private BigDecimal vanPrijs;
	@Min(MIN_PRIJS)
	private BigDecimal totPrijs;
	@Min(MIN_JAARTAL)
	private Integer vanJaartal;
	@Min(MIN_JAARTAL)
	private Integer totJaartal;

	ZoekForm() {
	}

	ZoekForm(Integer vanPrijs, Integer totPrijs, Integer vanJaartal,
			Integer totJaartal) {
		this.vanPrijs = new BigDecimal(vanPrijs);
		this.totPrijs = new BigDecimal(totPrijs);
		this.vanJaartal = vanJaartal;
		this.totJaartal = totJaartal;
	}

	public Integer getTotJaartal() {
		return totJaartal;
	}

	public void setTotJaartal(Integer totJaartal) {
		this.totJaartal = totJaartal;
	}

	public Integer getVanJaartal() {
		return vanJaartal;
	}

	public void setVanJaartal(Integer vanJaartal) {
		this.vanJaartal = vanJaartal;
	}

	public String getSchilderNaam() {
		return schilderNaam;
	}

	public void setSchilderNaam(String schilderNaam) {
		this.schilderNaam = schilderNaam;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getStijl() {
		return stijl;
	}

	public void setStijl(String stijl) {
		this.stijl = stijl;
	}

	public BigDecimal getVanPrijs() {
		return vanPrijs;
	}

	public void setVanPrijs(BigDecimal vanPrijs) {
		this.vanPrijs = vanPrijs;
	}

	public BigDecimal getTotPrijs() {
		return totPrijs;
	}

	public void setTotPrijs(BigDecimal totPrijs) {
		this.totPrijs = totPrijs;
	}

	public boolean isValidPrijs() {
		if (vanPrijs == null || totPrijs == null) {
			return true;
		}
		return vanPrijs.compareTo(totPrijs) <= 0;
	}

	public boolean isValidJaartal() {
		if (vanJaartal == null || totJaartal == null) {
			return true;
		}
		return vanJaartal <= totJaartal;
	}
}
