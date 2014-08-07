package be.vdab.web;

import javax.validation.constraints.NotNull;

class ZoekTermForm {
	@NotNull
	private String zoekterm;

	/**
	 * @param zoekterm the zoekterm to set
	 */
	public void setZoekterm(String zoekterm) {
		this.zoekterm = zoekterm;
	}

	/**
	 * @return the zoekterm
	 */
	public String getZoekterm() {
		return zoekterm;
	}

	ZoekTermForm() {}
	
	ZoekTermForm(String zoekterm) {
		this.zoekterm = zoekterm;
	}

	@Override
	public String toString() {
		return zoekterm;
	}
}
