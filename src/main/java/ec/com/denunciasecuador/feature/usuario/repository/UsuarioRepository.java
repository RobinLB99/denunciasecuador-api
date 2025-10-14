package ec.com.denunciasecuador.feature.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.denunciasecuador.feature.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.identityNumber = :identityNumber")
	public Optional<Usuario> findUsuarioByIdentityNumber(@Param("identityNumber") String identityNumber);

}
