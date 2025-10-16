package ec.com.denunciasecuador.feature.usuario.repository;

import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Credencial}.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * y consultas personalizadas sobre la entidad {@code Credencial} en la base de datos.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    /**
     * Busca una credencial por su nombre de usuario.
     * <p>
     * Este método ejecuta una consulta JPQL para encontrar una {@link Credencial}
     * cuyo campo {@code username} coincida con el valor proporcionado.
     * </p>
     *
     * @param username El nombre de usuario de la credencial a buscar.
     * @return Un {@link Optional} que contiene la {@code Credencial} si se encuentra,
     *         o un {@code Optional} vacío si no existe ninguna credencial con ese nombre de usuario.
     */
    @Query("SELECT c FROM Credencial c WHERE c.username = :username")
    public Optional<Credencial> findCredencialByUsername(
        @Param("username") String username
    );
}
