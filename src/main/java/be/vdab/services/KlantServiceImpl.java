package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

@Service
public class KlantServiceImpl implements KlantService {
	private KlantDAO klantDAO;
	
	@Autowired
	public KlantServiceImpl(KlantDAO klantDAO) {
		this.klantDAO = klantDAO;
	}
	
	@Override
	public void voegToe(Klant klant) {
		klantDAO.save(klant);
	}
}
