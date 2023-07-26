package manga.Outile;

import java.util.HashMap;

public class CustomedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> erreurs;

	public CustomedException(String tvaNonTrouvée) {
		erreurs = new HashMap<>();
	}
	public CustomedException(HashMap<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    public CustomedException(HashMap<String, String> erreurs, String message) {
        super(message);
        this.erreurs = erreurs;
    }

    public HashMap<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(HashMap<String, String> erreurs) {
        this.erreurs = erreurs;
    }
	

}
