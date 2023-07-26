package manga.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Edition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(length = 50,nullable = false)
	private String nom;
	
	//dependance
	@OneToMany(mappedBy = "edition")
	private Collection<Manga> mangas;
	
	public Edition() {
		mangas = new ArrayList<>();
	}

	public Edition(String nom) {
		super();
		this.nom = nom;
	}
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Manga> getMangas() {
		return mangas;
	}

	public void setMangas(Collection<Manga> mangas) {
		this.mangas = mangas;
	}

	@Override
	public String toString() {
		return "Edition [nom=" + nom + "]";
	}
	
	

}
