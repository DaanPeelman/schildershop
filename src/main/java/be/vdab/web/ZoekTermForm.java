package be.vdab.web;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.*;

class ZoekTermForm {
	@NotNull
	private String zoekterm;
	@NotNull
	@Jaartal
	private Integer vanJaartal;
	@NotNull
	@Jaartal
	private Integer totJaartal;
	@NotNull
	@Prijs
	private BigDecimal vanPrijs;
	@NotNull
	@Prijs
	private BigDecimal totPrijs;

	ZoekTermForm() {
	}

	ZoekTermForm(String zoekterm, Integer vanJaartal, Integer totJaartal,
			BigDecimal vanPrijs, BigDecimal totPrijs) {
		this.zoekterm = zoekterm;
		this.vanJaartal = vanJaartal;
		this.totJaartal = totJaartal;
		this.vanPrijs = vanPrijs;
		this.totPrijs = totPrijs;
	}

	/**
	 * @return the zoekterm
	 */
	public String getZoekterm() {
		return zoekterm;
	}

	public Integer getVanJaartal() {
		return vanJaartal;
	}

	public Integer getTotJaartal() {
		return totJaartal;
	}

	public BigDecimal getVanPrijs() {
		return vanPrijs;
	}

	public BigDecimal getTotPrijs() {
		return totPrijs;
	}

	@Override
	public String toString() {
		return String.format("%d, %s - %s, € %s - € %s", zoekterm, vanJaartal,
				totJaartal, vanPrijs, totPrijs);
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
