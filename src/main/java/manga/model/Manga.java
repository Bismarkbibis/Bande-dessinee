package manga.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Manga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 50, nullable = false)
	private String nom;

	@Column(length = 50, nullable = false)
	private String numSeri;
	@Column(nullable = false)
	private String numImage;
	@Column(length = 100, nullable = false)
	private String titre;
	@Column(length = 50, nullable = false)
	private BigDecimal prix;
	@Column(length = 200, nullable = false)
	private String description;
	@Column(length = 50, nullable = false)
	private int tom;

	@Column(nullable = false)
	private boolean statut = false;

	@Column(length = 50, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSortie;

	@Column(length = 50, nullable = false)
	private int age;

	// dependance
	@JsonIgnore
	@ManyToOne
	private Genre genre;
	@JsonIgnore
	@ManyToOne
	private Edition edition;
	@JsonIgnore
	@ManyToOne
	private Auteur auteur;
	@JsonIgnore
	@ManyToOne
	private Tva tva;
	@JsonIgnore
	@ManyToOne
	private Langue langue;
	@JsonIgnore
	@ManyToOne
	private Emprunter emprunter;
	@JsonIgnore
	@OneToMany(mappedBy = "manga")
	private Collection<Commentaire> commentaires;
	@JsonIgnore
	@OneToMany(mappedBy = "manga")
	private Collection<Type> types;
	@JsonIgnore
	@OneToMany(mappedBy = "manga")
	private Collection<LigneCommande> ligeCommandes;
	@JsonIgnore
	@OneToMany(mappedBy = "manga")
	private Collection<Actualiter> actualiters;
	@JsonIgnore
	@OneToMany(mappedBy = "manga")
	private Collection<Reservation> reservations;
	@JsonIgnore
	@OneToMany(mappedBy ="manga")
	private Collection<Tom> toms;
	
	public Manga() {
		commentaires = new ArrayList<>();
		types = new ArrayList<>();
		ligeCommandes = new ArrayList<>();
		actualiters = new ArrayList<>();
		reservations = new ArrayList<>();
		toms = new ArrayList<>();
	}

	public Manga(String nom, String numSeri, String numImage, String titre, String description, int tom,
			boolean statut, Date dateSortie, int age, BigDecimal prix) {
		this();
		this.nom = nom;
		this.numSeri = numSeri;
		this.numImage = numImage;
		this.titre = titre;
		this.description = description;
		this.tom = tom;
		this.statut = statut;
		this.dateSortie = dateSortie;
		this.prix = prix;
		this.age = age;
	}

	



}
