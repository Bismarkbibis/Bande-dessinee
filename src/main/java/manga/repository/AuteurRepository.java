package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur,Integer> {

	
	@Query("select a from Auteur a where a.nom = :paraNom")
	public Optional<Auteur> findAuteurByName(String paraNom);
	
	
}
