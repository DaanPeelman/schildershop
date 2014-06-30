package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Gebruiker;

@Service
public class KlantServiceImpl implements KlantService {
	private final KlantDAO klantDAO;
	
	@Autowired
	public KlantServiceImpl(KlantDAO klantDAO) {
		this.klantDAO = klantDAO;
	}
	
	@Override
	public void voegToe(Gebruiker klant) {
			klantDAO.save(klant);
	}
}
