package be.vdab.valueobjects;

import java.io.Serializable;

public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String straat;
	private String nummer;
	private String gemeente;
	private String postcode;
	
	protected Adres() {
	}

	public Adres(String straat, String nummer, String gemeente, String postcode) {
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

	public String getPostcode() {
		return postcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gemeente == null) ? 0 : gemeente.hashCode());
		result = prime * result + ((nummer == null) ? 0 : nummer.hashCode());
		result = prime * result
				+ ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adres other = (Adres) obj;
		if (gemeente == null) {
			if (other.gemeente != null)
				return false;
		} else if (!gemeente.equals(other.gemeente))
			return false;
		if (nummer == null) {
			if (other.nummer != null)
				return false;
		} else if (!nummer.equals(other.nummer))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		return true;
	}
}
