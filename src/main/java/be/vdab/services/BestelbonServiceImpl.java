package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.BestelbonDAO;
import be.vdab.entities.Bestelbon;

@Service
public class BestelbonServiceImpl implements BestelbonService {
	private final BestelbonDAO bestelbonDAO;
	
	@Autowired
	public BestelbonServiceImpl(BestelbonDAO bestelbonDAO) {
		this.bestelbonDAO = bestelbonDAO;
	}
	
	@Override
	public Iterable<Bestelbon> findAll() {
		return bestelbonDAO.findAll();
	}
	
	@Override
	public Bestelbon read(long id) {
		return bestelbonDAO.findOne(id);
	}
	
	@Override
	public void create(Bestelbon bestelbon) {
		bestelbonDAO.save(bestelbon);
	}
}
