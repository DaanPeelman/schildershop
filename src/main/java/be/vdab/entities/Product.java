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

@Entity
@Table(name = "producten")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long productId;
	private String titel;
	private int jaartal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schilderId")
	private Schilder schilder;
	private String stijl;
	private BigDecimal prijs;
	
	protected Product() {
	}

	public Product(String titel, int jaartal, Schilder schilder, String stijl, BigDecimal prijs) {
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

	public int getJaartal() {
		return jaartal;
	}

	public void setJaartal(int jaartal) {
		this.jaartal = jaartal;
	}

	public Schilder getSchilder() {
		return schilder;
	}

	public void setSchilder(Schilder schilder) {
		this.schilder = schilder;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jaartal;
		result = prime * result
				+ ((schilder == null) ? 0 : schilder.hashCode());
		result = prime * result + ((stijl == null) ? 0 : stijl.hashCode());
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
		Product other = (Product) obj;
		if (jaartal != other.jaartal)
			return false;
		if (schilder == null) {
			if (other.schilder != null)
				return false;
		} else if (!schilder.equals(other.schilder))
			return false;
		if (stijl == null) {
			if (other.stijl != null)
				return false;
		} else if (!stijl.equals(other.stijl))
			return false;
		return true;
	}
}
