package manga.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@Entity
public class LigneCommande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(length = 70,nullable = false)
	private BigDecimal prixHT;
	@Column(length = 70 ,nullable = false)
	private BigDecimal tauxTva;
	@Column(length = 70,nullable = false)
	private int qte;
	
	// dependance
	@ManyToOne
	private Commande commande;
	@ManyToOne
	private Manga manga;

	public LigneCommande() {
		
	}
	

}
