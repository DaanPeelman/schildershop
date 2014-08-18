package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Embeddable
@Table(name = "bestelbonlijnen")
public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;
	private int aantal;
	private BigDecimal prijs;
	
	protected Bestelbonlijn() {
	}

	public Bestelbonlijn(Product product, int aantal, BigDecimal prijs) {
		this.product = product;
		this.aantal = aantal;
		this.prijs = prijs;
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

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		if (!(obj instanceof Bestelbonlijn)) {
			return false;
		}
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		return true;
	}
}
