package manga.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import manga.model.Actualiter;


public interface ActualiterRepository extends JpaRepository<Actualiter, Integer> {

//	@Query("DELECT FROM Actualiter a WHERE c.id =:idActu")
//	Optional<Actualiter> findActuById (int idActu);
}
