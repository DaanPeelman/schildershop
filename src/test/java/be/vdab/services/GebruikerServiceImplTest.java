package be.vdab.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import be.vdab.dao.GebruikerDAO;
import be.vdab.dao.RolDAO;
import be.vdab.entities.Gebruiker;
import be.vdab.entities.Rol;
import be.vdab.exceptions.GebruikerMetDezeEmailBestaatAlException;
import be.vdab.services.GebruikerService;
import be.vdab.valueobjects.Adres;

public class GebruikerServiceImplTest {
	private GebruikerService gebruikerService;
	
	@Before
	public void setUp() {
		Gebruiker gebruiker = new Gebruiker("J", "Unit", new Adres("TestStraat", "4", "TestGemeente", 2880), "JUnit1234", "JUnit@testing.com");
		
		GebruikerDAO gebruikerDAO = Mockito.mock(GebruikerDAO.class);
		RolDAO rolDAO = Mockito.mock(RolDAO.class);
		Mockito.when(rolDAO.findOne(2L)).thenReturn(new Rol("Klant"));
		Mockito.when(gebruikerDAO.findByEmailadres("JUnit@testing.com")).thenReturn(gebruiker);
		gebruikerService = new GebruikerServiceImpl(gebruikerDAO, rolDAO);
	}
	
//	@Test
//	public void createGebruikerMetEmailDieNogNietVoorkomtWerkt() {
//		Gebruiker gebruiker = new Gebruiker("J", "Unit", new Adres("TestStraat", "4", "TestGemeente", 2880), "JUnit1234", "JUnit2@testing.com");
//		
//		gebruikerService.create(gebruiker);
//	}
	
	@Test(expected = GebruikerMetDezeEmailBestaatAlException.class)
	public void createGebruikerMetEmailDieAlVoorkomtWerptException() {
		Gebruiker gebruiker = new Gebruiker("Daan", "Peelman", new Adres("Omgangstraat", "137", "Mariekerke", 2880), "Daan101292", "JUnit@testing.com");
		
		gebruikerService.create(gebruiker);
	}
}
