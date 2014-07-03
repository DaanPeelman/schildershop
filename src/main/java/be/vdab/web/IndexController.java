package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.dao.GebruikerDAO;
import be.vdab.entities.Gebruiker;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@RequestMapping
	public String index() {
		return "index";
	}
}
