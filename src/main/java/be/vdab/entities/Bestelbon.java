package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import be.vdab.valueobjects.Adres;

public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Klant klant;
	private Adres leverAdres;
	private Set<Bestelbonlijn> bestelbonlijnen;
	
	protected Bestelbon() {
	}

	public Bestelbon(long id, Klant klant, Adres leverAdres) {
		this.id = id;
		this.klant = klant;
		this.leverAdres = leverAdres;
		bestelbonlijnen = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public Adres getLeverAdres() {
		return leverAdres;
	}

	public void setLeverAdres(Adres leverAdres) {
		this.leverAdres = leverAdres;
	}

	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}

	public void setBestelbonlijnen(Set<Bestelbonlijn> bestelbonlijnen) {
		this.bestelbonlijnen = bestelbonlijnen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelbonlijnen == null) ? 0 : bestelbonlijnen.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
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
		Bestelbon other = (Bestelbon) obj;
		if (bestelbonlijnen == null) {
			if (other.bestelbonlijnen != null)
				return false;
		} else if (!bestelbonlijnen.equals(other.bestelbonlijnen))
			return false;
		if (id != other.id)
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		return true;
	}
	
	
}
