package be.vdab.web;

import javax.validation.constraints.NotNull;

class StijlForm {
	@NotNull
	private String stijl;
	
	StijlForm() {}
	
	StijlForm(String stijl) {
		this.stijl = stijl;
	}

	public String getStijl() {
		return stijl;
	}

	@Override
	public String toString() {
		return stijl;
	}
}
