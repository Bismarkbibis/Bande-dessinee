package manga.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class Utilisateur implements Serializable {
	/**
	 * 
	 */
	public static final boolean active = true;
	public static final boolean bloquer = true;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false,unique = true)
	private String identifiant;

	@Column(length = 50)
	private boolean compteStatue;

	@Column(length = 50, nullable = false)
	private String nom;

	@Column(length = 50, nullable = false)
	private String prenom;

	@Column(length = 100, unique = true,nullable = false)
	private String email;
//	@Column(length = 50)
//	private String dateNaissance;
	@Column(length = 50)
	int age;
	@Column(length = 50,nullable = false,unique = true)
	private String numerotel;
	@JsonIgnore
	@Column(nullable = false, unique = true)
	private String mdp;
	@Column(length = 50)
	private int nombreEmprunter;

/// dependance 
	@JsonIgnore
	@ManyToOne
	private Role role;

	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Token> tokens;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Commentaire> commentaires;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Adresse> adresses;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Emprunter> emprunters;
	@OneToMany(mappedBy ="utilisateur")
	private Collection<Reservation> reservations;
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Commande> commandes;
	
	
	public Utilisateur() {
		reservations = new ArrayList<>();
		emprunters = new ArrayList<>();
		commentaires = new ArrayList<>();
		adresses = new ArrayList<>();
		tokens = new ArrayList<>();
		commandes = new ArrayList<>();
	}

	public Utilisateur(String identifiant, String nom, String prenom, String email,  int age,
			String numerotel, String mdp,boolean compteStatue,int nombreEmprunter) {
		this();

		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
//		this.dateNaissance = dateNaissance;
		this.age = age;
		this.numerotel = numerotel;
		this.mdp = mdp;
		this.compteStatue= compteStatue;
		this.nombreEmprunter= nombreEmprunter;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", identifiant=" + identifiant + ", compteStatue=" + compteStatue
				+ ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age
				+ ", numerotel=" + numerotel + ", nombreEmprunter=" + nombreEmprunter + "]";
	}
}
