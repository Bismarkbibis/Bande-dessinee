package manga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga.model.Commentaire;
import manga.model.Manga;
import manga.model.Tom;
import manga.model.Utilisateur;
import manga.repository.CommentaireRepository;
import manga.repository.MangaRepository;
import manga.repository.TomRepository;

@Service
public class CommentaireService {

	@Autowired
	private MangaRepository mangaRepository;
	@Autowired
	private CommentaireRepository commentaireRepository;
	@Autowired
	private TomRepository tomRepository;
	
	public Commentaire insertAvis(Utilisateur utilisateur, int idManga,int idTom ,String Commentaire) throws Exception {
		Exception exception = new Exception("le manga que vous essayais de mettre un commentaire n'est pas disponible");
		Optional<Manga> mangaAvis = mangaRepository.findById(idManga);
		Optional<Tom> tomAvis =tomRepository.findById(idTom);
		
		if (mangaAvis.isPresent()) {
			Commentaire commentaire = new Commentaire();
			if (tomAvis.isPresent()) {
				Manga manga2 = mangaAvis.get();
				Tom tom = tomAvis.get();
				
				commentaire.setAvis(Commentaire);
				commentaire.setIdentifiant(utilisateur.getIdentifiant());
				commentaire.setUtilisateur(utilisateur);
				commentaire.setManga(manga2);
				commentaire.setTom(tom);
				
				commentaire.setCmmDate(new Date());
				commentaireRepository.save(commentaire);
				return commentaire;
			} else {
				throw exception;
			}
		}
		return null;
	}

	public List<Commentaire> getAllCommentaire() {
		List<Commentaire> avis = commentaireRepository.findAll();
		return avis;
	}

}
