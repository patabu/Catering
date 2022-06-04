package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.model.Credentials;
import com.spring.model.Utente;
import com.spring.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired private CredentialsService credentialsService;
	
	//@Autowired private UtenteValidator utenteValidator;
	
	// @Autowired private CredentialsValidator credentialsValidator;
	
	@GetMapping("/register")
	public String showRegisterForm (Model model) {
		System.out.println("Testr?");
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "registerForm";
	}
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		System.out.println("Testl?");
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "index";
	}
	
	@GetMapping("/home")
    public String home(Model model) {
		this.credentialsService.setRoleInModel(model);
        return "home";
    }
	 
	/*@PostMapping("/register")
    public String registerUtente(@ModelAttribute("utente") Utente utente,
                 BindingResult utenteBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {
		System.out.println("Register?");
        // validate utente and credentials fields
        // this.utenteValidator.validate(utente, utenteBindingResult);
        // this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the Utente and the Credentials into the DB
        if(!utenteBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the utente and store the credentials;
            // this also stores the Utente, thanks to Cascade.ALL policy
        	credentials.setRole("ADMIN_ROLE");
            credentials.setUtente(utente);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessful";
        }
        return "registerForm";
    }*/
	
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("utente") Utente utente,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        //this.userValidator.validate(user, userBindingResult);
        //this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUtente(utente);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessful";
        }
        return "registerForm";
    }
}

