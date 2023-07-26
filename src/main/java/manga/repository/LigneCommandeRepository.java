package manga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import manga.model.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {

	
}
