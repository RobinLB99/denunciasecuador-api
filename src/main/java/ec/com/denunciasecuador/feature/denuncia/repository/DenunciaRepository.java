package ec.com.denunciasecuador.feature.denuncia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

	@Query("SELECT d FROM Denuncia d WHERE d.usuario.identityNumber = :identityNumber")
	public Page<Denuncia> findDenunciasByIdentityNumberUsuario(@Param("identityNumber") String identityNumberUsuario,
			Pageable pageable);

}
