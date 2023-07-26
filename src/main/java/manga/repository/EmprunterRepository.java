package manga.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Emprunter;


public interface EmprunterRepository extends JpaRepository<Emprunter, String>{

	@Query("select l from Emprunter l where l.dateRetour = :paradateRetour")
	public Optional<Emprunter> findEmprunteBydateRetour(Date paradateRetour);

}
