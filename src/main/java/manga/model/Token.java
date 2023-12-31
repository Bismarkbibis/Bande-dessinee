package manga.model;

import lombok.Data;

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

@Data
@Entity
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(nullable = true)
	private String valeur;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateExpire;

	@ManyToOne
	private Utilisateur  utilisateur;
	
	public Token() {
		
	}

	public Token(String valeur, Date dateExpire) {
		
		this.valeur = valeur;
		this.dateExpire = dateExpire;
	}


}
