package manga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manga.model.Adresse;
@Repository
public interface AdressRepository extends JpaRepository<Adresse, Integer> {

}
