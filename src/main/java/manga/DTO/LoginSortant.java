package manga.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginSortant implements Serializable {
	
	private String Token ;
	private String identifiant;
	private String email;
	private String nom;
	private String prenom;
	private String numero;
	private String role ;


	
}
