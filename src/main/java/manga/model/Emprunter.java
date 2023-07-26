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

@Entity
public class Emprunter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(nullable = true, unique = false)
	private String numEmprunter;
	@Column(length = 50)
	private String nom;

	@Column(length = 50)
	private String prenom;
	@Column(length = 50)
	private String rue;
	@Column(length = 50)
	private String cp;
	@Column(length = 50)
	private String ville;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEmprunt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRetour;

	// dependance
	@ManyToOne
	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "emprunter")
	private Collection<Manga> mangas;

	@OneToMany(mappedBy = "emprunter")
	private Collection<Tom> toms;

	public Emprunter() {
		mangas = new ArrayList<>();
		toms = new ArrayList<>();
	}

	public Emprunter(String numEmprunter, String nom, String prenom, String rue, String cp, String ville,
			Date dateEmprunt, Date dateRetour) {
		this();
		this.numEmprunter = numEmprunter;
		this.nom = nom;
		this.prenom = prenom;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;

		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;

	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getNumEmprunter() {
		return numEmprunter;
	}

	public void setNumEmprunter(String numEmprunter) {
		this.numEmprunter = numEmprunter;
	}

	public Collection<Manga> getMangas() {
		return mangas;
	}

	public void setMangas(Collection<Manga> mangas) {
		this.mangas = mangas;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Collection<Tom> getToms() {
		return toms;
	}

	public void setToms(Collection<Tom> toms) {
		this.toms = toms;
	}

	@Override
	public String toString() {
		return "Emprunter [nom=" + nom + ", prenom=" + prenom + ", dateEmprunt=" + dateEmprunt + ", dateRetour="
				+ dateRetour + "]";
	}
}
