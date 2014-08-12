package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "schilders")
public class Schilder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long schilderId;
	@NotEmpty
	private String naam;
	@OneToMany(mappedBy = "schilder")
	private Set<Product> schilderijen;
	
	public Schilder() {
	}

	public Schilder(String naam) {
		this.naam = naam;
		schilderijen = new HashSet<>();
	}

	public long getSchilderId() {
		return schilderId;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Schilder)) {
			return false;
		}
		Schilder other = (Schilder) obj;
		if (naam == null) {
			if (other.naam != null) {
				return false;
			}
		} else if (!naam.equals(other.naam)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return naam;
	}

	public void removeSchilderij(Product product) {
		if (product.getSchilder() == this) {
			schilderijen.remove(product);
			product.setSchilder(null);
		}
	}

	public void addSchilderij(Product product) {
		schilderijen.add(product);
		if (product.getSchilder() != this) {
			product.setSchilder(this);
		}
	}
}
