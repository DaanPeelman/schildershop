package be.vdab.web;

import java.math.BigDecimal;

import javax.validation.constraints.*;

import be.vdab.constraints.Prijs;

class VanTotPrijsForm {
	@NotNull
	@Prijs
	private BigDecimal vanPrijs;
	@NotNull
	@Prijs
	private BigDecimal totPrijs;

	VanTotPrijsForm() {
	}

	VanTotPrijsForm(BigDecimal vanPrijs, BigDecimal totPrijs) {
		this.vanPrijs = vanPrijs;
		this.totPrijs = totPrijs;
	}

	public BigDecimal getVanPrijs() {
		return vanPrijs;
	}

	public BigDecimal getTotPrijs() {
		return totPrijs;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", vanPrijs, totPrijs);
	}
	
	public boolean isValid() {
		if (vanPrijs == null || totPrijs == null) {
			return true;
		}
		return vanPrijs.compareTo(totPrijs) <= 0;
	}
}
