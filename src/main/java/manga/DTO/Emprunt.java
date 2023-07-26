package manga.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class Emprunt implements Serializable {

//	private String MangaNom;

	private int Tom;

	private String rue;

	private String cp;

	private String ville;

}
