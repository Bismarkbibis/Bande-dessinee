package manga.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Data
@Entity
public class AdresseEmprunt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(nullable = false, length = 70)
	private String nom;

	@Column(nullable = false, length = 70)
	private String prenom;
	
	@Column(nullable = false, length = 70)
	private String rue;
	
	@Column(nullable = false, length = 50)
	private String cp;
	
	@Column(nullable = false, length = 50)
	private String ville;
		
//DEPENDANCE
	@OneToMany
	private Collection<Emprunter> emprunters;


	public AdresseEmprunt() {
		emprunters = new ArrayList<>();
	}

}
