package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Genre;
import manga.model.Reservation;
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Query("select r.utilisateur from Reservation r where r.utilisateur.id = :paraReservation")
	public Optional<Genre> findClientById(int paraReservation);
	
//	@Query("select r from Reservation r join r.Manga m where m.id = :paraMangaReserver")
//	public Optional<Reservation> findMangaRserve(int paraMangaReserver);
}
