package be.vdab.web;

import be.vdab.valueobjects.Adres;

class AdresForm extends Adres {
	private static final long serialVersionUID = 1L;
	
	AdresForm() {
	}
	
	AdresForm(Adres adres) {
		setStraat(adres.getStraat());
		setHuisNr(adres.getHuisNr());
		setGemeente(adres.getGemeente());
		setPostcode(adres.getPostcode());
	}

	@Override
	public void setStraat(String straat) {
		super.setStraat(straat);
	}
	
	@Override
	public void setGemeente(String gemeente) {
		System.out.println("gemeente op " + gemeente + " zetten");
		super.setGemeente(gemeente);
	}
	
	@Override
	public void setHuisNr(String huisNr) {
		super.setHuisNr(huisNr);
	}
	
	@Override
	public void setPostcode(String postcode) {
		super.setPostcode(postcode);
	}
}
