package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Commande;


public interface CommandeRepository extends JpaRepository<Commande, Integer>{
	
	@Query("SELECT c FROM Commande c WHERE c.numero  =:numSerie")
	public Optional<Commande> findByNumSeri(String numSerie);
	

}
