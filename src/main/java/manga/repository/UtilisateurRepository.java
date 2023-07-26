package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import manga.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	@Query("SELECT u FROM Utilisateur u WHERE u.email = :emailUtilisateur")
	public Optional<Utilisateur> chercherUtilisateurParEmail(String emailUtilisateur);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.identifiant = :identifiantUtilisateur")
	public Optional<Utilisateur> chercherUtilisateurParidentifiant(String identifiantUtilisateur);
	
	
//	@Query("UPDATE u Utilisateur u set u.identifiant =:identifiant  ")
//	public String updateIdentifiant(String identifiant);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE Utilisateur u SET u.identifiant =:identifiant WHERE u.id = :id")
//	int  updateIdentifiant(@Param ("id") int id,@Param ("identifiant") String identifiant);

	public Optional<Utilisateur> findByEmail(String email);
	

}
