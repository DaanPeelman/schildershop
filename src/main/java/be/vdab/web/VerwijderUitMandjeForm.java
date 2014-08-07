package be.vdab.web;

public class VerwijderUitMandjeForm {
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
