package be.vdab.web;

import javax.validation.constraints.*;

import be.vdab.constraints.Jaartal;

class VanTotJaartalForm {
	@NotNull
	@Jaartal
	private Integer vanJaartal;
	@NotNull
	@Jaartal
	private Integer totJaartal;

	VanTotJaartalForm() {
	}

	VanTotJaartalForm(Integer vanJaartal, Integer totJaartal) {
		this.vanJaartal = vanJaartal;
		this.totJaartal = totJaartal;
	}

	public Integer getVanJaartal() {
		return vanJaartal;
	}

	public Integer getTotJaartal() {
		return totJaartal;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", vanJaartal, totJaartal);
	}
	
	public boolean isValid() {
		if (vanJaartal == null || totJaartal == null) {
			return true;
		}
		return vanJaartal <= totJaartal;
	}
}
