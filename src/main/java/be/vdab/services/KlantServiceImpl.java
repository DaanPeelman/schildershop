package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.KlantDAO;
import be.vdab.dao.RolDAO;
import be.vdab.entities.Gebruiker;
import be.vdab.entities.Rol;
import be.vdab.exceptions.GebruikerMetDezeEmailBestaatAlException;

@Service
public class KlantServiceImpl implements KlantService {
	private final RolDAO rolDAO;
	private final KlantDAO klantDAO;
	
	@Autowired
	public KlantServiceImpl(KlantDAO klantDAO, RolDAO rolDAO) {
		this.klantDAO = klantDAO;
		this.rolDAO = rolDAO;
	}
	
	@Override
	public void voegToe(Gebruiker klant) {
		if(klantDAO.findByEmailadres(klant.getEmailadres()) != null) {
			throw new GebruikerMetDezeEmailBestaatAlException();
		}
		
		Rol rol = rolDAO.findOne(2L);		// automatisch een klant
		klant.addRol(rol);
		klantDAO.save(klant);
	}
}
