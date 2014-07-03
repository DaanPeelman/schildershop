package be.vdab.web;

import javax.validation.constraints.NotNull;

class TitelForm {
	@NotNull
	private String titel;

	TitelForm() {}
	
	TitelForm(String titel) {
		this.titel = titel;
	}

	public String getTitel() {
		return titel;
	}

	@Override
	public String toString() {
		return titel;
	}
}
