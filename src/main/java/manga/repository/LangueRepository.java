package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Langue;

public interface LangueRepository extends JpaRepository<Langue, Integer>{

	@Query("select l from Langue l where l.nom = :paraNom")
	public Optional<Langue> findGenreBylangue(String paraNom);
}
