package be.vdab.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BestelProductForm {
	@NotNull
	private long productId;
	@NotNull
	@Min(1)
	private Integer aantal;
	
	BestelProductForm() {
	}
	
	BestelProductForm(long productNr) {
		super();
		this.productId = productNr;
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
