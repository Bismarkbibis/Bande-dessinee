package manga.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import manga.DTO.Avis;
import manga.DTO.Emprunt;
import manga.DTO.RegistreRentrant;
import manga.Outile.CustomedException;
import manga.model.Commentaire;
import manga.model.Emprunter;
import manga.model.Reservation;
import manga.model.Utilisateur;
import manga.service.AccessSecurityService;
import manga.service.CommentaireService;
import manga.service.EmprunterService;
import manga.service.UtilisateurService;

@RequestMapping("/user")
@RestController()
@CrossOrigin("*")
public class ClientControlleur {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private CommentaireService commentaireService;
	@Autowired
	private AccessSecurityService accessSecurityService;

	@Autowired
	private EmprunterService emprunterService;

	@PostMapping("/inscrption")
	public ResponseEntity<Utilisateur> creatUser(@RequestBody RegistreRentrant Info) {
		try {
			Utilisateur user = utilisateurService.createUser(Info.getNom(), Info.getPrenom(), Info.getIdentifiant(),
					Info.getAge(), Info.getNumero(), Info.getEmail(), Info.getMdp(), Info.getMdp2());
			System.out.println("" + user);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			e.printStackTrace(); // Il vous dit ce qui s'est passé et où dans le code cela s'est produit.
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	@PostMapping("/avis")
	public ResponseEntity<Commentaire> getAllAvisInsert(HttpServletRequest request,@RequestBody Avis avisClient01) {

		Utilisateur utilisateur = accessSecurityService.findUserByToken(request);
		if (!(utilisateur == null)) {
			try {
				Commentaire avis = commentaireService.insertAvis(utilisateur, avisClient01.getIdManga(),avisClient01.getIdTom(),avisClient01.getAvis());
				return ResponseEntity.ok(avis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/manga/avis")
	public ResponseEntity<List<Commentaire>> getAllAvis() {
		List<Commentaire> commentaires = commentaireService.getAllCommentaire();
		return ResponseEntity.ok(commentaires);
	}
	
	@PostMapping("/reserve")
	private ResponseEntity<Reservation> ClientReservation(HttpServletRequest request,
			@RequestBody List<HashMap<String, String>> mangaReserve) {

		Utilisateur utilisateur = accessSecurityService.findUserByToken(request);
		if (utilisateur != null) {
			System.out.println("" + utilisateur);
			try {
				Reservation reservation = emprunterService.clientReserve(utilisateur, mangaReserve);
				System.out.println("" + reservation);
				return ResponseEntity.ok(reservation);
			} catch (CustomedException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}

		}
		return null;
	}

	
	@PostMapping("/emprunter")
	public ResponseEntity<Emprunter> ClientEmprunt(HttpServletRequest request,@RequestBody Emprunt bollow) throws CustomedException{
		Utilisateur utilisateur = accessSecurityService.findUserByToken(request);
		if (utilisateur != null) {
			System.out.println("<<<<<<<<<<<<<<<<<<<"+utilisateur);
			try {
				Emprunter emprunter = emprunterService.EmprunterTom(utilisateur, bollow.getTom(), bollow.getRue(), bollow.getCp(),bollow.getVille());
				System.out.println("<<<<<<<<<<<<<<<<<<<"+emprunter);
				return ResponseEntity.ok(emprunter);
				
			} catch (Exception e) {
				e.printStackTrace(); //Il vous dit ce qui s'est passé et où dans le code cela s'est produit.
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
			}
		}
		return null;
	}

}
