package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Schilder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	// pieter breughel de JONGE && De OUDE!!
	private String naam;
	private Set<Product> schilderijen;
	
	protected Schilder() {
	}

	public Schilder(String naam) {
		this.naam = naam;
		schilderijen = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Set<Product> getSchilderijen() {
		return Collections.unmodifiableSet(schilderijen);
	}

	public void setSchilderijen(Set<Product> schilderijen) {
		this.schilderijen = schilderijen;
	}

}
