package be.vdab.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long bestelbonNr;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "klantNr")
	private Gebruiker klant;
	@Embedded
	private Adres leverAdres;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bestelbonNr"))
	private Set<Bestelbonlijn> bestelbonlijnen;
	
	protected Bestelbon() {
	}

	public Bestelbon(long bestelbonNr, Gebruiker klant, Adres leverAdres) {
		this.bestelbonNr = bestelbonNr;
		this.klant = klant;
		this.leverAdres = leverAdres;
		bestelbonlijnen = new HashSet<>();
	}

	public long getId() {
		return bestelbonNr;
	}

	public void setId(long bestelbonNr) {
		this.bestelbonNr = bestelbonNr;
	}

	public Gebruiker getKlant() {
		return klant;
	}

	public void setKlant(Gebruiker klant) {
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
		result = prime * result + (int) (bestelbonNr ^ (bestelbonNr >>> 32));
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
		if (bestelbonNr != other.bestelbonNr)
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		return true;
	}
	
	
}
