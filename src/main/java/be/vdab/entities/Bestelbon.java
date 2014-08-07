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
	private long bestelbonId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gebruikerId")
	private Gebruiker gebruiker;
	@Embedded
	private Adres leverAdres;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bestelbonId"))
	private Set<Bestelbonlijn> bestelbonlijnen;
	private Date datum;
	
	public Bestelbon() {
		this.datum = new Date();
		bestelbonlijnen = new HashSet<>();
	}
	
	public Bestelbon(Gebruiker gebruiker, Adres leverAdres) {
		this.gebruiker = gebruiker;
		this.leverAdres = leverAdres;
		this.datum = new Date();
		bestelbonlijnen = new HashSet<>();
	}

	public Bestelbon(Gebruiker gebruiker, Adres leverAdres, Date datum) {
		this.gebruiker = gebruiker;
		this.leverAdres = leverAdres;
		bestelbonlijnen = new HashSet<>();
		this.datum = datum;
	}

	public long getBestelbonId() {
		return bestelbonId;
	}

	public void setBestelbonId(long bestelbonId) {
		this.bestelbonId = bestelbonId;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
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
	
	public void addBestelbonlijn(Bestelbonlijn bestelbonlijn) {
		this.bestelbonlijnen.add(bestelbonlijn);
	}
	
	public void deleteBestelbonlijn(Bestelbonlijn bestelbonlijn) {
		this.bestelbonlijnen.remove(bestelbonlijn);
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelbonlijnen == null) ? 0 : bestelbonlijnen.hashCode());
		result = prime * result + (int) (bestelbonId ^ (bestelbonId >>> 32));
		result = prime * result + ((gebruiker == null) ? 0 : gebruiker.hashCode());
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
		if (bestelbonId != other.bestelbonId)
			return false;
		if (gebruiker == null) {
			if (other.gebruiker != null)
				return false;
		} else if (!gebruiker.equals(other.gebruiker))
			return false;
		return true;
	}
	
	
}
