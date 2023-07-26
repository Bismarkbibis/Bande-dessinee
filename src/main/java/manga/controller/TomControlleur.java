package manga.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import manga.DTO.TomInsert;
import manga.Outile.CustomedException;
import manga.model.Role;
import manga.model.Tom;
import manga.model.Utilisateur;
import manga.service.AccessSecurityService;
import manga.service.AdminService;

@RestController()
@RequestMapping("/tom")
@CrossOrigin("*")
public class TomControlleur {
	@Autowired
	private AccessSecurityService accessSecurityService;
	@Autowired
	private AdminService adminService;

	@PostMapping("/insert")
	public ResponseEntity<Tom> inserTomManga(HttpServletRequest request, Utilisateur utilisateur,
			@RequestBody TomInsert tom) {
		Boolean admin = accessSecurityService.verifyRole(request, Role.ADMIN);

		if (admin) {
			try {
			
				Tom tom2 = adminService.insertTom(utilisateur, tom.getNom(), tom.getManga(),tom.getNumero(),tom.getNombrePage(), tom.getDateSortir(), tom.getNumImage(), tom.getTitre(),tom.getPrix(), tom.getDescription());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+tom2);
				return ResponseEntity.ok(tom2);
			} catch (CustomedException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
		}
		return null;

	}
}
