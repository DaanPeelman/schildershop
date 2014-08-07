package be.vdab.web;

import java.math.BigDecimal;

public class ProductAankoopForm {
	private long productId;
	private int aantal;
	private BigDecimal prijs;
	
	public ProductAankoopForm() {
	}
	
	public ProductAankoopForm(long productId) {
		this.productId = productId;
	}
	
	public ProductAankoopForm(long productId, int aantal, BigDecimal prijs) {
		this.productId = productId;
		this.aantal = aantal;
		this.prijs = prijs;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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
}
