package manga.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga.DTO.LoginSortant;
import manga.Outile.CustomedException;
import manga.model.Role;
import manga.model.Token;
import manga.model.Utilisateur;
import manga.repository.RoleRepository;
import manga.repository.TokenRepository;
import manga.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private PasswordEncoderService passwordEncoderService;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TokenRepository tokenRepository;


	public int clientAge(String birthDate) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		int d1 = Integer.parseInt(formatter.format(birthDate));
		int d2 = Integer.parseInt(formatter.format(new Date()));
		int age = (d2 - d1) / 10000;
		return age;
	}

	public Utilisateur createUser(String nom, String prenom, String identifiant, int age, String numtelephone,
			String email, String mdp01, String mdp02) throws CustomedException, ParseException {

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
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setIdentifiant(identifiant);
		utilisateur.setNumerotel(numtelephone);
//		utilisateur.setDateNaissance(dateNaissance);
// verifier 
//		Penaliter penaliter = new  Penaliter(true);
//		utilisateur.setPenaliter(penaliter);
		utilisateur.setAge(age);
		utilisateur.setCompteStatue(Utilisateur.active);
// a modifier
//		utilisateur.setDateNaissance(dateNaissance);
		String encodPasseword = passwordEncoderService.encoder(mdp01);
		utilisateur.setMdp(encodPasseword);
		utilisateur.setEmail(email);
		// a changer en user
		Optional<Role> roles = roleRepository.findRoleByNom(Role.USER);
		Role roleUser = roles.get();
		utilisateur.setRole(roleUser);
		utilisateurRepository.save(utilisateur);
		return utilisateur;
	}

	public LoginSortant login(String email, String password) throws Exception {
		Optional<Utilisateur> optional = utilisateurRepository.findByEmail(email);
		Exception ex = new Exception("Erreur sur identifiant ou le mot de passe");
		if (optional.isPresent()) {
			Utilisateur utilisateur = optional.get();// recupere l'utilisateur;
			boolean ok = passwordEncoderService.verifier(password, utilisateur.getMdp());
			if (ok) {
				// generer le token
				Token token01 = genererToken();
				token01.setUtilisateur(utilisateur);// on affecte le token au utlisateur
				tokenRepository.save(token01);
				// cree la valeur de retour
				LoginSortant loginSortant = new LoginSortant();
				loginSortant.setIdentifiant(utilisateur.getIdentifiant());
				loginSortant.setEmail(utilisateur.getEmail());
				loginSortant.setNom(utilisateur.getNom());
				loginSortant.setPrenom(utilisateur.getPrenom());
				loginSortant.setNumero(utilisateur.getNumerotel());
				loginSortant.setRole(utilisateur.getRole().getNom());
				loginSortant.setToken(token01.getValeur());
				return loginSortant;
			} else {
				throw ex;
			}
		} else {
			throw ex;
		}

	}

	private Token genererToken() {
		UUID uuid = UUID.randomUUID();
		String valeurToken = uuid.toString(); // le token envoyer part le toString

		Calendar calendar = Calendar.getInstance(); // permet de recupere la date et l'heur actuelle
		// la durere du token
		// on le met 15min
		calendar.add(Calendar.MINUTE, 30); // la durer
		Date expirationDate = calendar.getTime(); // constrution de date
		return new Token(valeurToken, expirationDate);
	}

	// modifier l'identifiant
//	public String modifInformation(Utilisateur utilisateur, String identifiant,int id) {
//
//		Optional<Utilisateur> user = utilisateurRepository.chercherUtilisateurParidentifiant(identifiant);
//		if (user.isPresent()) {
//			Utilisateur utilisateur2 = user.get();
//			int identifiant = utilisateurRepository.updateIdentifiant(id, identifiant);
//			
//		}
//		return null;
//		
//
//	}

}