package manga.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Commentaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 200, nullable = false)
	private String avis;

	@Column(length = 50)
	private String identifiant;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date cmmDate;

	/// dependance
	@JsonIgnore
	@ManyToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Tom tom;
	@JsonIgnore
	@ManyToOne
	private Manga manga;

	public Commentaire() {
	}

	public Commentaire(String avis, String identifiant, Date cmmDate) {
		this.avis = avis;
		this.identifiant = identifiant;
		this.cmmDate = cmmDate;
	}

	public Tom getTom() {
		return tom;
	}

	public void setTom(Tom tom) {
		this.tom = tom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public Date getCmmDate() {
		return cmmDate;
	}

	public void setCmmDate(Date cmmDate) {
		this.cmmDate = cmmDate;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Commentaire [avis=" + avis + ", cmmDate=" + cmmDate + "]";
	}

}
