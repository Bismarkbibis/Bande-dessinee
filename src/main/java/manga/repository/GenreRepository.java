package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

	@Query("select g from Genre g where g.nom = :paraNom")
	public Optional<Genre> findGenreByName(String paraNom);
	
}
