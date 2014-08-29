package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.BestelbonDAO;
import be.vdab.entities.Bestelbon;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false)
	@Override
	public void create(Bestelbon bestelbon) {
		bestelbonDAO.save(bestelbon);
	}
}
