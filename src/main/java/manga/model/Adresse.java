package manga.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
@Entity
public class Adresse implements Serializable {

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

	@Column(length = 50)
	private String ville;

	// dependance
	@ManyToOne
	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "adresseLivraison")
	private Collection<Commande> commandesLivraison;

	@OneToMany(mappedBy = "adresseFacture")
	private Collection<Commande> commandesFacture;

	public Adresse() {
		commandesLivraison = new ArrayList<>();
		commandesFacture = new ArrayList<>();
	}
}
