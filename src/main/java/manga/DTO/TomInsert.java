package manga.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TomInsert implements Serializable {

	private String nom;
	private String manga;
	private int numero;
	private int nombrePage;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateSortir;
	private String numImage;
	private String titre;
	private float prix;
	private String description;



}
