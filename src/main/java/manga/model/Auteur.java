package manga.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Data
@Entity
public class Auteur  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(length = 50, nullable = false)
	private String nom;
	
	@Column(length = 50)
	private String prenom;

	//dependance
	@OneToMany(mappedBy ="auteur")
	private Collection<Manga> mangas;
	
	public Auteur() {
		mangas = new ArrayList<>();
	}


}
