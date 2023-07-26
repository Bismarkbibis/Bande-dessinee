package manga.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(length = 70, nullable = false)
	private float prixHT;

	@Column(length = 70, nullable = false)
	private float tauxTva;

	@Column(length = 70, nullable = false)
	private String numero;
	@Column(length = 50)
	private int tom;
	// dependance
	@JsonIgnore
	@ManyToOne
	private Manga manga;

	
	@JsonIgnore
	@ManyToOne
	private Utilisateur utilisateur;

	public Reservation() {

	}

	public Reservation(float prixHT, float tauxTva, String numero,int tom) {
		
		this.prixHT = prixHT;
		this.tauxTva = tauxTva;
		this.numero = numero;
		this.tom = tom;
	}

	
	public int getTom() {
		return tom;
	}

	public void setTom(int tom) {
		this.tom = tom;
	}

	public float getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(float prixHT) {
		this.prixHT = prixHT;
	}

	public float getTauxTva() {
		return tauxTva;
	}

	public void setTauxTva(float tauxTva) {
		this.tauxTva = tauxTva;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Reservation [prixHT=" + prixHT + ", tauxTva=" + tauxTva + ", numero=" + numero + "]";
	}



}
