package manga.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Commande  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = true, unique = false)
	private String numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date cmdDate;
	
	@Column(nullable = true)
	private String factureNumero;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date factureDate;

	///dependance
	@OneToMany(mappedBy = "commande")
	private Collection<LigneCommande> ligeCommandes;
	@ManyToOne
	private Adresse adresseLivraison;
	@ManyToOne
	private Adresse adresseFacture;
	@ManyToOne
	private Utilisateur utilisateur;
	
	public Commande() {
		ligeCommandes = new ArrayList<>();
		
	}

	public Commande(String numero, Date cmdDate, String factureNumero, Date factureDate) {
		this();
		this.numero = numero;
		this.cmdDate = cmdDate;
		this.factureNumero = factureNumero;
		this.factureDate = factureDate;
	}

	
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Collection<LigneCommande> getLigeCommandes() {
		return ligeCommandes;
	}

	public void setLigeCommandes(Collection<LigneCommande> ligeCommandes) {
		this.ligeCommandes = ligeCommandes;
	}

	public Adresse getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public Adresse getAdresseFacture() {
		return adresseFacture;
	}

	public void setAdresseFacture(Adresse adresseFacture) {
		this.adresseFacture = adresseFacture;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getCmdDate() {
		return cmdDate;
	}

	public void setCmdDate(Date cmdDate) {
		this.cmdDate = cmdDate;
	}

	public String getFactureNumero() {
		return factureNumero;
	}

	public void setFactureNumero(String factureNumero) {
		this.factureNumero = factureNumero;
	}

	public Date getFactureDate() {
		return factureDate;
	}

	public void setFactureDate(Date factureDate) {
		this.factureDate = factureDate;
	}

	@Override
	public String toString() {
		return "Commande [numero=" + numero + ", cmdDate=" + cmdDate + ", factureNumero=" + factureNumero
				+ ", factureDate=" + factureDate + "]";
	}
}
