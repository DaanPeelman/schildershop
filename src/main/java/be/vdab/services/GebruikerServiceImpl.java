package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.*;
import be.vdab.entities.*;
import be.vdab.exceptions.GebruikerMetDezeEmailBestaatAlException;
import be.vdab.valueobjects.Adres;

@Service
@Transactional(readOnly = true)
public class GebruikerServiceImpl implements GebruikerService {
	private final RolDAO rolDAO;
	private final GebruikerDAO gebruikerDAO;
	
	@Autowired
	public GebruikerServiceImpl(GebruikerDAO gebruikerDAO, RolDAO rolDAO) {
		this.gebruikerDAO = gebruikerDAO;
		this.rolDAO = rolDAO;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void create(Gebruiker gebruiker) throws GebruikerMetDezeEmailBestaatAlException{
		if(gebruikerDAO.findByEmailadres(gebruiker.getEmailadres()) != null) {
			throw new GebruikerMetDezeEmailBestaatAlException();
		}
		
		Rol rol = rolDAO.findByNaamIs("Klant");		// automatisch een klant
		gebruiker.addRol(rol);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String nWachtwoord = encoder.encode(gebruiker.getWachtwoord());
		gebruiker.setWachtwoord(nWachtwoord);		// wachtwoord encryptie
		gebruikerDAO.save(gebruiker);
	}
	
	@Override
	public Gebruiker findByEmailadres(String emailadres) {
		return gebruikerDAO.findByEmailadres(emailadres);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void update(Gebruiker gebruiker) {
		gebruikerDAO.save(gebruiker);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void updateGegevens(long id, Adres adres) {
		Gebruiker gebruiker = gebruikerDAO.findOne(id);
		gebruiker.setAdres(adres);
		gebruikerDAO.save(gebruiker);
	}
	
	@Override
	public Gebruiker find(long id) {
		return gebruikerDAO.findOne(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void updateWachtwoord(long id, String wachtwoord) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Gebruiker gebruiker = gebruikerDAO.findOne(id);
		String nWachtwoord = encoder.encode(wachtwoord);
		
		gebruiker.setWachtwoord(nWachtwoord);
		gebruikerDAO.save(gebruiker);
	}
}
