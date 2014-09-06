package be.vdab.web;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import be.vdab.valueobjects.Adres;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class MandjeImpl implements Mandje, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Map<Long, Integer> producten;
	private Adres adres;
	
	public MandjeImpl() {
		producten = new ConcurrentHashMap<>();
	}
	
	@Override
	public void addProduct(Long productId, int aantal) {
		producten.put(productId, aantal);
	}
	
	@Override
	public void removeProduct(long productId) {
		producten.remove(productId);
	}
	
	@Override
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	@Override
	public Adres getAdres() {
		return adres;
	}
	
	@Override
	public void leegMandje() {
		producten.clear();
	}

	@Override
	public Map<Long, Integer> getProducten() {
		return producten;
	}
}
