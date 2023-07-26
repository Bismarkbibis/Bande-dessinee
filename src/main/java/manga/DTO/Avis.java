package manga.DTO;

public class Avis {

	private String avis;
	
	private int idManga;
	private int idTom;

	public String getAvis() {
		return avis;
	}

	public int getIdTom() {
		return idTom;
	}

	public void setIdTom(int idTom) {
		this.idTom = idTom;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public int getIdManga() {
		return idManga;
	}

	public void setIdManga(int idManga) {
		this.idManga = idManga;
	}
	
	
}
