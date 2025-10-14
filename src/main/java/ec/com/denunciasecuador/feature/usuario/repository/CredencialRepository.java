package ec.com.denunciasecuador.feature.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.denunciasecuador.feature.usuario.model.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {

	@Query("SELECT c FROM Credencial c WHERE c.username = :username")
	public Optional<Credencial> findCredencialByUsername(@Param("username") String username);

}
