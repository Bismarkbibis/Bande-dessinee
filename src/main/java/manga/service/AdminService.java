package manga.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import manga.DTO.MangaDTO;
import manga.model.*;
import manga.repository.*;
import org.springframework.stereotype.Service;

import manga.Outile.CustomedException;

@Service
public class AdminService {

	private final MangaRepository mangaRepository;
	private final AuteurRepository auteurRepository;
	private final  LangueRepository langueRepository;
	private final TvaRepository tvaRepository;
	private final GenreRepository genreRepository;
	private final EditionRepository editionRepository;
	private final TomRepository tomRepository;
	private final UtilisateurRepository utilisateurRepository;
	private final PasswordEncoderService passwordEncoderService;
	private final RoleRepository roleRepository;

	public AdminService(MangaRepository mangaRepository, AuteurRepository auteurRepository, LangueRepository langueRepository, TvaRepository tvaRepository, GenreRepository genreRepository, EditionRepository editionRepository, TomRepository tomRepository, UtilisateurRepository utilisateurRepository, PasswordEncoderService passwordEncoderService, RoleRepository roleRepository) {
		this.mangaRepository = mangaRepository;
		this.auteurRepository = auteurRepository;
		this.langueRepository = langueRepository;
		this.tvaRepository = tvaRepository;
		this.genreRepository = genreRepository;
		this.editionRepository = editionRepository;
		this.tomRepository = tomRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.passwordEncoderService = passwordEncoderService;
		this.roleRepository = roleRepository;
	}

	public Utilisateur createAdmin(String nom, String prenom, String identifiant, int age, String numtelephone,
								   String email, String mdp01, String mdp02) throws CustomedException {

		HashMap<String, String> erreurs = new HashMap<>();
		//role

		// verification identifiant
		Optional<Utilisateur> optionalIdentifant = utilisateurRepository.chercherUtilisateurParidentifiant(identifiant);
		if (optionalIdentifant.isPresent()) {
			erreurs.put("identifant present", "idenfiant est deja utiliser veuiller choisir un autre");
		}
		// verification email
		String regex = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*";
		if (!email.matches(regex)) {
			erreurs.put("errEmail", "email incorrecte");
		}
		Optional<Utilisateur> optionUtilisateur = utilisateurRepository.chercherUtilisateurParEmail(email);
		if (optionUtilisateur.isPresent()) {
			erreurs.put("errEmail", "Email existe dejas");
		}
		// verification du mdp
		if (!mdp01.equals(mdp02)) {
			erreurs.put("erreur Mots de passe ", "Les mots de passe ne sont pas identiques");
		} else if (mdp01.equals(mdp02) && mdp01.length() > 10) {
			erreurs.put("erreursMdp", "il faut au moins 10 caractères pour Mdp");
		}
		if (!erreurs.isEmpty()) {
			CustomedException ex = new CustomedException(erreurs, "Echec de l'inscription");
			throw ex;
		}
		Utilisateur admin = new Utilisateur();
		admin.setNom(nom);
		admin.setPrenom(prenom);
		admin.setIdentifiant(identifiant);
		admin.setNumerotel(numtelephone);
//		admin.setDateNaissance(dateNaissance);
// verifier
//		Penaliter penaliter = new  Penaliter(true);
//		admin.setPenaliter(penaliter);
		admin.setAge(age);
		admin.setCompteStatue(Utilisateur.active);
// a modifier
//		admin.setDateNaissance(dateNaissance);
		String encodPasseword = passwordEncoderService.encoder(mdp01);
		admin.setMdp(encodPasseword);
		admin.setEmail(email);
		// a changer en user
		Optional<Role> roles = roleRepository.findRoleByNom(Role.ADMIN);
		Role adminRole = roles.get();
		admin.setRole(adminRole);
		utilisateurRepository.save(admin);
		return admin;
	}

	public List<Manga> mangas() {
		List<Manga> mangas = mangaRepository.findAll();
		return mangas;
	}

	public Optional<Manga> seachMangaByName(String nom) {
		Optional<Manga> mangaName = mangaRepository.findMangaByNom(nom);
		return mangaName;
	}

	public Optional<Manga> seachMangaByStatus(String statue) {
		Optional<Manga> mangaStatus = mangaRepository.findMangaBystatut(statue);
		return mangaStatus;
	}

// a tester pou rplus tard attribuer un numSerie directement base de donnee
public String NumeroSeri() {
	UUID uuid = UUID.randomUUID();
	String nume = uuid.toString();
	return nume;
}


// insertion d'un manga
public Manga insertManga(MangaDTO mangaDTO) throws CustomedException {

	Optional<Manga> mangaNom = mangaRepository.findMangaByNom(mangaDTO.getNom());

	if (mangaNom.isEmpty()) {

		Manga newManga = new Manga();
		newManga.setNom(mangaDTO.getNom());
		newManga.setDescription(mangaDTO.getDescription());
		newManga.setNumSeri(mangaDTO.getNumSeri());
		newManga.setNumImage(mangaDTO.getImage());
		newManga.setTom(0);
		newManga.setDateSortie(mangaDTO.getDateSortieManag());
		newManga.setTitre(mangaDTO.getTitre());
		newManga.setAge(mangaDTO.getAge());
		newManga.setPrix(mangaDTO.getPrix());
		newManga.setStatut(true);

		// Récupération ou création de l'objet Auteur
		Auteur auteurObj = auteurRepository.findAuteurByName(mangaDTO.getAuteur())
				.orElseGet(() -> {
					Auteur newAuteur = new Auteur();
					newAuteur.setNom(mangaDTO.getAuteur());
					return auteurRepository.save(newAuteur);
				});
		newManga.setAuteur(auteurObj);

		// Récupération ou création de l'objet Genre
		//TODO ajoute les genre part toi meme

		Genre genreObj = genreRepository.findGenreByName(mangaDTO.getGenre())
				.orElseGet(() -> {
					Genre newGenre = new Genre();
					newGenre.setNom(mangaDTO.getGenre());
					return genreRepository.save(newGenre);
				});
		newManga.setGenre(genreObj);

		// Récupération ou création de l'objet Langue
		//TODO ajoute les langue part toi meme
		Langue langueObj = langueRepository.findGenreBylangue(mangaDTO.getLangue())
				.orElseGet(() -> {
					Langue newLangue = new Langue();
					newLangue.setNom(mangaDTO.getLangue());
					return langueRepository.save(newLangue);
				});
		newManga.setLangue(langueObj);

		// Récupération ou création de l'objet Edition
		//TODO ajoute les edition part toi meme
		Edition editionObj = editionRepository.findGenreByEdition(mangaDTO.getEdition())
				.orElseGet(() -> {
					Edition newEdition = new Edition();
					newEdition.setNom(mangaDTO.getEdition());
					return editionRepository.save(newEdition);
				});
		newManga.setEdition(editionObj);

		Optional<Tva> tvaObjOptional = tvaRepository.findById(mangaDTO.getTva());
		if (tvaObjOptional.isEmpty()) {
			throw new CustomedException("TVA non trouvée pour l'ID : " + mangaDTO.getTva());
		}
		Tva tvaObj = tvaObjOptional.get();
		newManga.setTva(tvaObj);


		mangaRepository.save(newManga);
		return newManga;
	}
	throw new CustomedException("Ce manga existe déjà. Veuillez en choisir un autre.");
}


	public Tom insertTom(Utilisateur utilisateur, String nom, String Manga, int numero, int nombrePage,
						 Date dateSortir, String numImage, String titre, float prix, String description) throws CustomedException {
		HashMap<String, String> erreurInsertTom = new HashMap<>();

		Optional<Manga> manga = mangaRepository.findMangaByNom(Manga);
		Optional<Tom> tom = tomRepository.findTomByNom(nom);

		if (manga.isPresent()) {
			Manga manga2 = manga.get();

			if (tom.isPresent()) {
				erreurInsertTom.put("Deja existant", "Le tom que vous essayer de inserer est deja existant ");
			} else {
				Tom tom01 = new Tom();
				tom01.setNom(nom);
				tom01.setNumero(numero);
				tom01.setNumSeri(NumeroSeri());
				tom01.setNumImage(numImage);
				tom01.setDateDeSortie(dateSortir);
				tom01.setDescription(description);
				tom01.setTitre(titre);
				tom01.setPrix(prix);
				tom01.setStatut(true);
				tom01.setManga(manga2);
				tomRepository.save(tom01);
				return tom01;
			}
		} else {
			erreurInsertTom.put("Le manga n'existe pas", "Le manga que vous essayer de inserer un tom n'existe pas ");
		}
		if (!erreurInsertTom.isEmpty()) {
			CustomedException exception = new CustomedException(erreurInsertTom);
			throw exception;
		}

		return null;

	}


}
