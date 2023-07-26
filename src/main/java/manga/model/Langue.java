package manga.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Langue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String nom ;
	
	@OneToMany(mappedBy ="langue")
	private Collection<Manga> mangas;

	public Langue() {
		super();
	}

	public Langue(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Manga> getManga() {
		return mangas;
	}

	public void setManga(Collection<Manga> manga) {
		this.mangas = manga;
	}

	@Override
	public String toString() {
		return "Langue [nom=" + nom + ", manga=" + mangas + "]";
	}
	
	
}
