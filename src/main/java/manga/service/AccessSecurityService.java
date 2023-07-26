package manga.service;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga.model.Role;
import manga.model.Token;
import manga.model.Utilisateur;
import manga.repository.TokenRepository;

@Service
public class AccessSecurityService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public Optional<Token> findToken(HttpServletRequest request){
		String valeurToken=request.getHeader("Authorization");
		// on peut supprimer les token obsolete
		// utilisateur a essayer de ce connecter sans authentification
		if(valeurToken==null) {
			return Optional.empty(); // je retourn une enveloppe vide
		}else {
			// connesion avec authentificatiioon
			valeurToken = valeurToken.replace("Bearer ", "");//je remplace mot Bearer et lespace part une chaien vide pour garder uniquement le token

			Optional<Token> optional=tokenRepository.selectByValeur(valeurToken);
			return optional;
		}
	}

	public boolean verifyRole(HttpServletRequest request, String nomRole) {
		nomRole = Role.ADMIN;
		Optional<Token> optional =findToken(request);
		if (optional.isPresent()) {
			Token token = optional.get();
			//si le token expiere on retour false
			 if (token.getDateExpire().before(new Date())) { // je verifie si la date a expirer
				return false;
			}else {
				Utilisateur utilisateur=token.getUtilisateur();
				Role role =utilisateur.getRole();
				return  role.getNom().equals(nomRole);
			}
		}else {
			return false;
		}
	}

	// je cherche l'utilisateur part son token 
	public Utilisateur findUserByToken(HttpServletRequest request) {
		Optional<Token> utOptional = findToken(request);
		if (utOptional.isPresent()) {
			Token token = utOptional.get(); // je recupere le token 
			Utilisateur utilisateur = token.getUtilisateur();  //on recupere l'utilisateur qui correspondant au token
			return utilisateur;
		}else {
			return null;
		}
	}
}
