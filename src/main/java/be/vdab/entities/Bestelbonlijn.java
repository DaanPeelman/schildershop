package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private int aantal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BestelBonNr")
	private Bestelbon bestelbon;
	
	protected Bestelbonlijn() {
	}

	public Bestelbonlijn(Product product, int aantal, Bestelbon bestelbon) {
		this.product = product;
		this.aantal = aantal;
		this.bestelbon = bestelbon;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Bestelbon getBestelbon() {
		return bestelbon;
	}

	public void setBestelbon(Bestelbon bestelbon) {
		this.bestelbon = bestelbon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelbon == null) ? 0 : bestelbon.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (bestelbon == null) {
			if (other.bestelbon != null)
				return false;
		} else if (!bestelbon.equals(other.bestelbon))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
