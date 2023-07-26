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
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String USER = "ROLE_USER";
	public static final String ADMIN ="ROLE_ADMIN";
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id;
	
	@Column(length = 50)
	private String nom;

	@OneToMany(mappedBy = "role")
	private Collection<Utilisateur> utilisateurs;
	
	public Role() {
		utilisateurs =new  ArrayList<>();
	}


}
