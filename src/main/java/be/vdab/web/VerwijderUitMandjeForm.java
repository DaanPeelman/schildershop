package be.vdab.web;

import javax.validation.constraints.NotNull;

public class VerwijderUitMandjeForm {
	@NotNull
	private long productId;
	
	protected VerwijderUitMandjeForm() {
	}
	
	public VerwijderUitMandjeForm(long productId) {
		this.productId = productId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
}
