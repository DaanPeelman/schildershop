package be.vdab.web;

import java.util.Map;

import be.vdab.valueobjects.Adres;

public interface Mandje {
	void addProduct(Long productId, int aantal);
	void removeProduct(long productId);
	Adres getAdres();
	void setAdres(Adres adres);
	void leegMandje();
	Map<Long, Integer> getProducten();
}
