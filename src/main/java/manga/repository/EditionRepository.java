package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Edition;

public interface EditionRepository extends JpaRepository<Edition, Integer>{

	@Query("select e from Edition e where e.nom = :paraNom")
	public Optional<Edition> findGenreByEdition(String paraNom);
}
