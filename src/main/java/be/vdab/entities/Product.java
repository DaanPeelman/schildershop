package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "producten")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long productId;
	@NotEmpty
	private String titel;
	@NotNull
	private Integer jaartal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schilderId")
	private Schilder schilder;
	@NotEmpty
	private String stijl;
	@NotNull
	private BigDecimal prijs;

	public Product() {
	}

	public Product(String titel, Integer jaartal, Schilder schilder, String stijl,
			BigDecimal prijs) {
		this.titel = titel;
		this.jaartal = jaartal;
		this.schilder = schilder;
		this.stijl = stijl;
		this.prijs = prijs;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Integer getJaartal() {
		return jaartal;
	}

	public void setJaartal(Integer jaartal) {
		this.jaartal = jaartal;
	}

	public Schilder getSchilder() {
		return schilder;
	}

	public void setSchilder(Schilder schilder) {
		if (this.schilder != null
				&& this.schilder.getSchilderijen().contains(this)) {
			this.schilder.removeSchilderij(this);
		}
		
		this.schilder = schilder;
		
		if (schilder != null && !schilder.getSchilderijen().contains(this)) {
			schilder.addSchilderij(this);
		}
	}

	public String getStijl() {
		return stijl;
	}

	public void setStijl(String stijl) {
		this.stijl = stijl;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jaartal == null) ? 0 : jaartal.hashCode());
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result
				+ ((schilder == null) ? 0 : schilder.hashCode());
		result = prime * result + ((stijl == null) ? 0 : stijl.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (jaartal == null) {
			if (other.jaartal != null) {
				return false;
			}
		} else if (!jaartal.equals(other.jaartal)) {
			return false;
		}
		if (prijs == null) {
			if (other.prijs != null) {
				return false;
			}
		} else if (!prijs.equals(other.prijs)) {
			return false;
		}
		if (schilder == null) {
			if (other.schilder != null) {
				return false;
			}
		} else if (!schilder.getNaam().equals(other.schilder.getNaam())) {
			return false;
		}
		if (stijl == null) {
			if (other.stijl != null) {
				return false;
			}
		} else if (!stijl.equals(other.stijl)) {
			return false;
		}
		if (titel == null) {
			if (other.titel != null) {
				return false;
			}
		} else if (!titel.equals(other.titel)) {
			return false;
		}
		return true;
	}
}
