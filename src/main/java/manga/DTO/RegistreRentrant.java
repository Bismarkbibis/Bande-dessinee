package manga.DTO;

import lombok.Data;

@Data
public class RegistreRentrant {

	private String nom;
	private String prenom;
	private String identifiant;
	private int age;
	private String numero;
	private String mdp;
	private String mdp2;
	private String email;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

//	public String getDateNaissance() {
//		return dateNaissance;
//	}
//
//	public void setDateNaissance(String dateNaissance) {
//		this.dateNaissance = dateNaissance;
//	}
	
	

	public String getMdp() {
		return mdp;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getMdp2() {
		return mdp2;
	}

	public void setMdp2(String mdp2) {
		this.mdp2 = mdp2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RegistreRentrant [nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", age=" + age
				+ ", numero=" + numero + ", email=" + email + "]";
	}
}
