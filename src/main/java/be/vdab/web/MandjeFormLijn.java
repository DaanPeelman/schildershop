package be.vdab.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MandjeFormLijn {
	private long id;
	@Min(1)
	private int aantal;
	
	public MandjeFormLijn() {
	}
	
	public MandjeFormLijn(long id, int aantal) {
		this.id = id;
		this.aantal = aantal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
}
