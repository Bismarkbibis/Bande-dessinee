package manga.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga.model.Actualiter;
import manga.model.Manga;
import manga.repository.ActualiterRepository;
import manga.repository.MangaRepository;

@Service
public class MangaService {
	
	@Autowired
	private ActualiterRepository actualiterRepository;
	@Autowired
	private MangaRepository mangaRepository;

	// envoie tout les manga
	public List<Actualiter> getAllActu() {
		return actualiterRepository.findAll();
	}
	//tout manga
	public List<Manga> getAllbd() {
		return mangaRepository.findAll();
	}

	// recherche part nom
	public Optional<Manga> getMangaByName(String nom) {
		Optional<Manga> mangaNom = mangaRepository.findMangaByNom(nom);
		return mangaNom;
	}
	// recherche part langue

	public List<Manga> selectMangaByLangue(int idLangue) {
		List<Manga> mangaLangue = mangaRepository.findMangaByLangueManga(idLangue);
		if (!mangaLangue.isEmpty()) {
			return mangaLangue;
		}
		return null;
	}

	// select part genre
	public List<Manga> selectMangaByGenre(int idGenre) {
		List<Manga> mangaLangue = mangaRepository.findMangaByLangueManga(idGenre);
		if (!mangaLangue.isEmpty()) {
			return mangaLangue;
		}
		return null;
	}

	// ajoute des actualliter
	public Actualiter ajoutActu(String actu,int idManga) throws Exception {
		Optional<Manga> manga = mangaRepository.findById(idManga);
		Exception exception = new Exception("Le Manga n'existe pas actuellent");
		if (manga.isPresent()) {
			Manga manga2 = manga.get();
			Actualiter actualiter = new Actualiter();
			actualiter.setActualiter(actu);
			actualiter.setActuDate(new Date());
			actualiter.setManga(manga2);
			actualiterRepository.save(actualiter);
			return 	actualiter;
		} else if (manga.isEmpty()) {
			 throw exception;
		}
		return null;
	}
	// des actualiter general
	public Actualiter actuAll(String actu) {
		Actualiter actualiter = new Actualiter();
		actualiter.setActualiter(actu);
		actualiter.setActuDate(new Date());
		actualiterRepository.save(actualiter);
		return actualiter;
	}
	// delete actualiter
	public void deletActu(int idActu) {
		
	}
	
	
}
