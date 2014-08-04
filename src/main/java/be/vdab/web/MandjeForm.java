package be.vdab.web;

import javax.validation.constraints.NotNull;

public class MandjeForm {
	@NotNull
	private long productId;
	@NotNull
	private Integer aantal;
	
	MandjeForm() {
	}
	
	MandjeForm(long productNr) {
		super();
		this.productId = productNr;
	}

	MandjeForm(long productNr, Integer aantal) {
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
