package manga.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import manga.DTO.LoginEntrant;
import manga.DTO.LoginSortant;
import manga.service.UtilisateurService;


@RestController()
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginControlleur {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	//HttpServletRequest : il encapsule les donner de la requete  a voir 
	@PostMapping("/test")
	public String testerPoste(HttpServletRequest request ) {
		 String valeurtoken = request.getHeader("Authorization");
		 System.out.println(" requet "+valeurtoken);
		return "bon connexion";
	}
	
	
	//@RequestBody :les information de notre requete on convertie vers un ibjet de type loginentrant
	@PostMapping("/user")
	public ResponseEntity<LoginSortant> login(@RequestBody LoginEntrant info) {
		try {
			LoginSortant loginSortant=  utilisateurService.login(info.getEmail(), info.getPasseword());
			return ResponseEntity.ok(loginSortant);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		}
		
	}
}
