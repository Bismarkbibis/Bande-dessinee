package manga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import manga.model.Reservation;

public interface LignEmprunterRepository extends JpaRepository<Reservation,Integer>{

}
