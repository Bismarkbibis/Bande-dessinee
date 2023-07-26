package manga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query("select r from Role r where r.nom = :roleNom")
	public Optional<Role> findRoleByNom(String roleNom);

}
