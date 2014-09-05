package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import be.vdab.valueobjects.Adres;

public class MandjeForm {
	@Valid
	List<MandjeFormLijn> lijnen;
	@Valid
	private Adres adres;
	
	public MandjeForm() {
		adres = new AdresForm();
	}
	
	public MandjeForm(Adres adres) {
		this.adres = adres;
	}

	public MandjeForm(List<MandjeFormLijn> lijnen, Adres leverAdres) {
		this.lijnen = lijnen;
		this.adres = leverAdres;
	}

	public List<MandjeFormLijn> getLijnen() {
		return lijnen;
	}

	public void setLijnen(List<MandjeFormLijn> lijnen) {
		this.lijnen = lijnen;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres leverAdres) {
		this.adres = leverAdres;
	}
}
