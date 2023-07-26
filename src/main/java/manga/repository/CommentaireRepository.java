package manga.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import manga.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {


}
