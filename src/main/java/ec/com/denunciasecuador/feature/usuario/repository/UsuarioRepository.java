package ec.com.denunciasecuador.feature.usuario.repository;

import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Usuario}.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * y consultas personalizadas sobre la entidad {@code Usuario} en la base de datos.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca un usuario por su número de identificación.
     * <p>
     * Este método ejecuta una consulta JPQL para encontrar un {@link Usuario}
     * cuyo campo {@code identityNumber} coincida con el valor proporcionado.
     * </p>
     *
     * @param identityNumber El número de identificación del usuario a buscar.
     * @return Un {@link Optional} que contiene el {@code Usuario} si se encuentra,
     *         o un {@code Optional} vacío si no existe ningún usuario con ese número de identificación.
     */
    @Query("SELECT u FROM Usuario u WHERE u.identityNumber = :identityNumber")
    public Optional<Usuario> findUsuarioByIdentityNumber(
        @Param("identityNumber") String identityNumber
    );
}
