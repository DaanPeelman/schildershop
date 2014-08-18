package be.vdab.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "rollen")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long rolId;
	private String naam;
	@ManyToMany
	@JoinTable(name = "gebruikersrollen", joinColumns = @JoinColumn(name = "rolId"), inverseJoinColumns = @JoinColumn(name = "gebruikerId"))
	private Set<Gebruiker> gebruikers;
	
	protected Rol() {
	}
	
	public Rol(String naam) {
		setNaam(naam);
		gebruikers = new HashSet<>();
	}

	public long getRolId() {
		return rolId;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Set<Gebruiker> getGebruikers() {
		return Collections.unmodifiableSet(gebruikers);
	}

	public void addGebruiker(Gebruiker gebruiker) {
		gebruikers.add(gebruiker);
		
		if(!gebruiker.getRollen().contains(this)) {
			gebruiker.addRol(this);
		}
	}
	
	public void removeGebruiker(Gebruiker gebruiker) {
		gebruikers.remove(gebruiker);
		
		if(gebruiker.getRollen().contains(this)) {
			gebruiker.removeRol(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		Rol other = (Rol) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
}
