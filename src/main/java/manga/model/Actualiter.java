package manga.model;

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



@Entity
public class Actualiter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id;
	
	@Column()
	private String actualiter;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date actuDate;

	// dependance
	@ManyToOne
	private Manga manga;
	
	public Actualiter() {
		
	}

	public Actualiter(String actualiter, Date actuDate) {
		this.actualiter = actualiter;
		this.actuDate = actuDate;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActualiter() {
		return actualiter;
	}

	public void setActualiter(String actualiter) {
		this.actualiter = actualiter;
	}

	public Date getActuDate() {
		return actuDate;
	}

	public void setActuDate(Date actuDate) {
		this.actuDate = actuDate;
	}

	@Override
	public String toString() {
		return "Actualiter [actualiter=" + actualiter + ", actuDate=" + actuDate + "]";
	}
	
	
	
}
