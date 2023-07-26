package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Query("SELECT t FROM Token t WHERE t.valeur =:paravaleur")
	public Optional<Token> selectByValeur(String paravaleur);
	
}
