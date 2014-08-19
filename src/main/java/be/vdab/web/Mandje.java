package be.vdab.web;

import java.util.Map;

public interface Mandje {
	void addProduct(Long productId, int aantal);
	void removeProduct(long productId);
	void leegMandje();
	Map<Long, Integer> getProducten();
	Integer getAantal(long productId);
}
