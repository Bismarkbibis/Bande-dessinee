package manga.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import manga.DTO.MangaDTO;
import manga.model.Manga;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import manga.Outile.CustomedException;
import manga.model.Actualiter;
import manga.model.Role;
import manga.service.AccessSecurityService;
import manga.service.AdminService;
import manga.service.MangaService;

@RestController()
@RequestMapping("/manga")
@CrossOrigin("*")
public class MangaControlleur {

	private final AccessSecurityService accessSecurityService;
	private final AdminService admService;

	private final MangaService mangaService;

	public MangaControlleur(AccessSecurityService accessSecurityService, AdminService admService, MangaService mangaService) {
		this.accessSecurityService = accessSecurityService;
		this.admService = admService;
		this.mangaService = mangaService;
	}

	@GetMapping(value = "/actualiter")
	private List<Actualiter> getALLL() {
		List<Actualiter> mangas = mangaService.getAllActu();
		return mangas;
	}

	@GetMapping(value = "/bd")
	private List<manga.model.Manga> getAllbd() {
		List<manga.model.Manga> mangalis = mangaService.getAllbd();
		return mangalis;
	}

	@GetMapping(value = "/{nom}")
	public ResponseEntity<Optional<manga.model.Manga>> getMangaByName(@PathVariable("nom") String nomManga) {
		Optional<manga.model.Manga> manga = mangaService.getMangaByName(nomManga);
		return ResponseEntity.ok(manga);
	}

	@GetMapping(value = "/{langue}")
	public ResponseEntity<List<manga.model.Manga>> getMangaByLangue(@PathVariable("langue") int langueManga) {
		System.out.println("sdddddddddddddddddd");
		List<manga.model.Manga> mangas = mangaService.selectMangaByLangue(langueManga);
		return ResponseEntity.ok(mangas);
	}

	@PostMapping("/insert")
	public ResponseEntity<Manga> insertManga(HttpServletRequest request, @RequestBody MangaDTO insertManga) {
		boolean userAdmin = accessSecurityService.verifyRole(request, Role.ADMIN);

		String valueToken = request.getHeader("Authorization");
		System.out.println(" token recu " + valueToken);
		System.out.println(" token recu " + userAdmin);

		if (userAdmin) {
			System.out.println("hello suis pass" + insertManga.getNom() + "///  " + insertManga.getDescription());
			if (insertManga == null) {
				System.out.println("hello suis pas passer errue 1");

				// Validation : Le corps de la requête ne contient pas d'objet Manga
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Objet Manga manquant dans la requête.");
			}

			System.out.println("hello suis pass et j'insere");
			Manga manga = null;
			try {
				manga = admService.insertManga(insertManga);
			} catch (CustomedException e) {
				throw new RuntimeException(e);
			}

			return ResponseEntity.ok(manga);
		}
		return null;
	}
}

