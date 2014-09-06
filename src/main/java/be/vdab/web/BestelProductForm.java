package be.vdab.web;

import javax.validation.constraints.*;

public class BestelProductForm {
	@NotNull
	private long productId;
	@NotNull
	@Min(1)
	private Integer aantal;
	
	BestelProductForm() {
		this.aantal = 1;
	}
	
	BestelProductForm(long productNr) {
		super();
		this.productId = productNr;
		this.aantal = 1;
	}

	BestelProductForm(long productNr, Integer aantal) {
		super();
		this.productId = productNr;
		this.aantal = aantal;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productNr) {
		this.productId = productNr;
	}

	public Integer getAantal() {
		return aantal;
	}

	public void setAantal(Integer aantal) {
		this.aantal = aantal;
	}
}
