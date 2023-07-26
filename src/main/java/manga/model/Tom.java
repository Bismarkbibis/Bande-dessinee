package manga.model;

import java.io.Serializable;
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
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
public class Tom implements Serializable {

	/**
	 * 
	 */


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(length = 50)
	private String nom;
	@Column(length = 50)
	private String numImage;
	@Column(length = 50)
	private int numero;
	@Column(length = 50)
	private boolean Statut;
	@Column(length = 50)
	private String numSeri;
	@Column(length = 100)
	private String titre;
	@Column(length = 50)
	private float prix;
	@Column(length = 200)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDeSortie;
	@JsonIgnore
	@ManyToOne
	private Manga manga;
	@JsonIgnore
	@ManyToOne
	private Emprunter emprunter;
	@JsonIgnore
	@OneToMany(mappedBy = "manga")
	private Collection<Commentaire> commentaires;

	public Tom() {
		commentaires = new ArrayList<>();
	}

	public Tom(String nom, String numImage, int numero, boolean Statut, String numSeri, String titre,
			float prix, String description, Date dateDeSortie) {
		this();
		this.nom = nom;
		this.numImage = numImage;
		this.numero = numero;
		this.Statut = Statut;
		this.numSeri = numSeri;
		this.titre = titre;
		this.prix = prix;
		this.description = description;
		this.dateDeSortie = dateDeSortie;
	}



}
