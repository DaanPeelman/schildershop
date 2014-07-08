package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.SchilderDAO;
import be.vdab.entities.Schilder;
import be.vdab.exceptions.SchilderMetDezeNaamBestaatAlException;

@Service
@Transactional(readOnly = true)
public class SchilderServiceImpl implements SchilderService {
	private final SchilderDAO schilderDAO;
	
	@Autowired
	public SchilderServiceImpl(SchilderDAO schilderDAO) {
		this.schilderDAO = schilderDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void create(Schilder schilder) {
		Iterable<Schilder> schilder2 = schilderDAO.findByNaamLike(schilder.getNaam());
		if (schilder2.iterator().hasNext()) {
			throw new SchilderMetDezeNaamBestaatAlException();
		}		
		
		schilder.setSchilderId(schilderDAO.save(schilder).getSchilderId());
	}

	@Override
	public Iterable<Schilder> findAll() {
		return schilderDAO.findAll(new Sort("naam"));
	}
}
