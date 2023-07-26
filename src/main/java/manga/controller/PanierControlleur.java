package manga.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import manga.model.Commande;
import manga.model.Utilisateur;
import manga.service.AccessSecurityService;
import manga.service.PanierService;

@RestController()
@RequestMapping("/panier")
@CrossOrigin("*")
public class PanierControlleur {

	@Autowired
	private PanierService panierService;
	@Autowired
	private AccessSecurityService accessSecurityService;

	@PostMapping("/commande")
	public ResponseEntity<Commande> clientOrder(HttpServletRequest request,@RequestBody List<HashMap<String, String>> inputs) {
		
		Utilisateur utilisateur = accessSecurityService.findUserByToken(request);
		if (!(utilisateur==null)) {
			Commande commande = panierService.creatOrderManga(utilisateur, inputs);
		    return ResponseEntity.ok(commande);
		}
		
		return null;
		
	}

}
