package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import be.vdab.dao.GebruikerDAO;
import be.vdab.dao.RolDAO;
import be.vdab.entities.Gebruiker;
import be.vdab.entities.Rol;
import be.vdab.exceptions.GebruikerMetDezeEmailBestaatAlException;

@Service
public class GebruikerServiceImpl implements GebruikerService {
	private final RolDAO rolDAO;
	private final GebruikerDAO gebruikerDAO;
	
	@Autowired
	public GebruikerServiceImpl(GebruikerDAO gebruikerDAO, RolDAO rolDAO) {
		this.gebruikerDAO = gebruikerDAO;
		this.rolDAO = rolDAO;
	}
	
	@Override
	public void create(Gebruiker gebruiker) {
		if(gebruikerDAO.findByEmailadres(gebruiker.getEmailadres()) != null) {
			throw new GebruikerMetDezeEmailBestaatAlException();
		}
		
		Rol rol = rolDAO.findOne(2L);		// automatisch een klant
		gebruiker.addRol(rol);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String nWachtwoord = encoder.encode(gebruiker.getWachtwoord());
		System.out.println(nWachtwoord);
		gebruiker.setWachtwoord(nWachtwoord);		// wachtwoord encryptie
		gebruikerDAO.save(gebruiker);
	}
	
	@Override
	public Gebruiker findByEmailadres(String emailadres) {
		return gebruikerDAO.findByEmailadres(emailadres);
	}
	
	@Override
	public void update(Gebruiker gebruiker) {
		gebruikerDAO.save(gebruiker);
	}
}
