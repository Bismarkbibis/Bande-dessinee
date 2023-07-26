package manga.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {

	
	private BCryptPasswordEncoder passwordEncoder; //classe de spring secu pour hascher les mdp
	
	//c'est pas une classe service de spring donc les instanciation sont gerer a la main 
	public PasswordEncoderService() {
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
	// hacher le mdp
	public String encoder(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	// verification des deux mdp
	public boolean verifier(String rawPassword, String encoder) {
		return passwordEncoder.matches(rawPassword, encoder);
	}

	
}
