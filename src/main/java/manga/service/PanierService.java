package manga.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import manga.model.Commande;
import manga.model.LigneCommande;
import manga.model.Manga;
import manga.model.Utilisateur;
import manga.repository.CommandeRepository;
import manga.repository.LigneCommandeRepository;
import manga.repository.MangaRepository;

@Service
public class PanierService {

	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;

	@Autowired
	private MangaRepository mangaRepository;

	public String NumeroSeri() {
		UUID uuid = UUID.randomUUID();
		String nume = uuid.toString();
		return nume;
	}

	public Commande creatOrderManga(Utilisateur utilisateur, List<HashMap<String, String>> panier) {
		if (!panier.isEmpty()) {
			Commande commande = new Commande();
			commande.setNumero(NumeroSeri());
			commande.setCmdDate(new Date());
			commande.setUtilisateur(utilisateur);
			List<LigneCommande> ligneCmd = new ArrayList<>();
			for (HashMap<String, String> ligneCommande : panier) {

				int idManga = Integer.valueOf(ligneCommande.get("id"));
				Optional<Manga> manga01 = mangaRepository.findById(idManga);
				if (manga01.isPresent()) {
					Manga manga = manga01.get();
					String key = "Quantite";
					int qteTom = Integer.valueOf(ligneCommande.get(key));
					LigneCommande lignecommande = new LigneCommande();
					lignecommande.setManga(manga);
					lignecommande.setQte(qteTom);
					lignecommande.setPrixHT(manga.getPrix().multiply(BigDecimal.valueOf(qteTom))); // Utilisez multiply() pour les calculs de BigDecimal
					lignecommande.setTauxTva(BigDecimal.valueOf(manga.getTva().getTaux()));

					ligneCmd.add(lignecommande);
				}
			}
			if (ligneCmd.isEmpty()) {
				return null;
			} else {
				commande = commandeRepository.save(commande);
				for (LigneCommande lcd : ligneCmd) {
					lcd.setCommande(commande);
					ligneCommandeRepository.save(lcd);
				}
				return commande;
			}
		} else {
			return null;
		}
	}


}
