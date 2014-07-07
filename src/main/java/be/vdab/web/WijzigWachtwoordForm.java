package be.vdab.web;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Wachtwoord;

public class WijzigWachtwoordForm {
	@NotNull
	private String oudWachtwoord;
	@Wachtwoord
	private String nieuwWachtwoord;
	@NotNull
	private String bevestigWachtwoord;
	
	protected WijzigWachtwoordForm() {
	}

	public WijzigWachtwoordForm(String oudWachtwoord, String nieuwWachtwoord, String bevestigWachtwoord) {
		this.oudWachtwoord = oudWachtwoord;
		this.nieuwWachtwoord = nieuwWachtwoord;
		this.bevestigWachtwoord = bevestigWachtwoord;
	}
	
	public boolean isValid() {
		return nieuwWachtwoord.equals(bevestigWachtwoord);
	}

	public String getOudWachtwoord() {
		return oudWachtwoord;
	}

	public void setOudWachtwoord(String oudWachtwoord) {
		this.oudWachtwoord = oudWachtwoord;
	}

	public String getNieuwWachtwoord() {
		return nieuwWachtwoord;
	}

	public void setNieuwWachtwoord(String nieuwWachtwoord) {
		this.nieuwWachtwoord = nieuwWachtwoord;
	}

	public String getBevestigWachtwoord() {
		return bevestigWachtwoord;
	}

	public void setBevestigWachtwoord(String bevestigWachtwoord) {
		this.bevestigWachtwoord = bevestigWachtwoord;
	}
}
