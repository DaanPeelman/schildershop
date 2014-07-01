package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String straat;
	@NotNull
	@Size(min = 1, max = 7)
	private String nummer;
	@NotNull
	@Size(min = 1, max = 50)
	private String gemeente;
	@NotNull
	@Min(1000)
	@Max(9999)
	private Integer postcode;
	
	protected Adres() {
	}

	public Adres(String straat, String nummer, String gemeente, Integer postcode) {
		this.straat = straat;
		this.nummer = nummer;
		this.gemeente = gemeente;
		this.postcode = postcode;
	}

	public String getStraat() {
		return straat;
	}

	public String getNummer() {
		return nummer;
	}

	public String getGemeente() {
		return gemeente;
	}

	public Integer getPostcode() {
		return postcode;
	}

	protected void setStraat(String straat) {
		this.straat = straat;
	}

	protected void setNummer(String nummer) {
		this.nummer = nummer;
	}

	protected void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	protected void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}
}
