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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
@Entity
public class Tva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 50)
    private String nom;
	
	@Column(nullable = false)
    private float taux;
	
	//dependance
	@JsonIgnore
	@OneToMany(mappedBy = "tva")
	private Collection<Manga> mangas;

	public Tva() {
		mangas = new ArrayList<>();
	}

	public Tva(String nom, float taux) {
		this();
		this.nom = nom;
		this.taux = taux;
	}
}
