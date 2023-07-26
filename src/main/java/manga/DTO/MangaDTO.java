package manga.DTO;

import lombok.Getter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
public class MangaDTO implements Serializable {

    private String numSeri;
    private String nom;
    private String description;
    private String titre;
    private String image;
    private String auteur;
    private String genre;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortieManag;

    private String langue;
    private String edition;
    private int age;

    private int tva;
    private BigDecimal prix;

}
