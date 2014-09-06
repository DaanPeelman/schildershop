package be.vdab.web;

import be.vdab.valueobjects.Adres;

class AdresForm extends Adres {
	private static final long serialVersionUID = 1L;
	
	AdresForm() {
	}
	
	AdresForm(Adres adres) {
		setStraat(adres.getStraat());
		setNummer(adres.getNummer());
		setGemeente(adres.getGemeente());
		setPostcode(adres.getPostcode());
	}

	@Override
	public void setStraat(String straat) {
		super.setStraat(straat);
	}
	
	@Override
	public void setGemeente(String gemeente) {
		super.setGemeente(gemeente);
	}
	
	@Override
	public void setNummer(String nummer) {
		super.setNummer(nummer);
	}
	
	@Override
	public void setPostcode(Integer postcode) {
		super.setPostcode(postcode);
	}
}
