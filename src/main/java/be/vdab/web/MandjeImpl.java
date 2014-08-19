package be.vdab.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class MandjeImpl implements Mandje{
	Map<Long, Integer> producten;
	
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
	public void leegMandje() {
		producten.clear();
	}
	
	@Override
	public Integer getAantal(long productId) {
		return producten.get(productId);
	}

	@Override
	public Map<Long, Integer> getProducten() {
		return producten;
	}
}
