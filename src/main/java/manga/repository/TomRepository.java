package manga.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Tom;

public interface TomRepository extends JpaRepository<Tom, Integer> {

//	@Query("select t from Tom t JOIN t.manga m where m.Id =:paraManga")
//	public Optional<Tom> findTomById(int paraManga);
//	
	@Query("select t from Tom t WHERE t.id =:tomManga")
	public Optional<Tom> findTomById(int tomManga);
	
	@Query("SELECT t FROM Tom t WHERE t.nom=:numSeri")
	public Optional<Tom> findTomByNom (String numSeri);
	
	// remonter tout les tom qui ont pour nom manga == one pice par exemple
	@Query("SELECT t FROM Tom t join t.manga m WHERE m.id = :paraNomTom")
	public List<Tom> findTomByIdManga (int paraNomTom);
	
	

}
