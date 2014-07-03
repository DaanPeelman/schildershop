package be.vdab.web;

import javax.validation.constraints.NotNull;

class SchilderForm {
	@NotNull
	private String schilderNaam;
	
	SchilderForm() {}

	SchilderForm(String schilderNaam) {
		this.schilderNaam = schilderNaam;
	}


	public String getSchilderNaam() {
		return schilderNaam;
	}

	@Override 
	public String toString() {
		return schilderNaam;
	}
}
