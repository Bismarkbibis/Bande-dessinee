 package manga.DTO;

 import lombok.Data;

 import java.io.Serializable;

 @Data
public class LoginEntrant implements Serializable {

	private String email;
	
	private String passeword;

}
