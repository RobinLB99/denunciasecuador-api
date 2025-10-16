package ec.com.denunciasecuador.feature.usuario.service.contract;

import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import org.springframework.data.domain.Page;

/**
 * Interfaz de servicio para la gestión de {@link Usuario}.
 * <p>
 * Define el contrato para las operaciones de negocio relacionadas con los
 * usuarios, incluyendo la creación, eliminación y búsqueda.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
public interface UsuarioService {
    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario El {@link Usuario} a guardar.
     * @return El {@link Usuario} guardado.
     */
    public Usuario guardarUsuario(Usuario usuario);

    /**
     * Elimina un usuario por su identificador único.
     *
     * @param id El ID del usuario a eliminar.
     */
    public void eliminarUsuarioPorId(Long id);

    /**
     * Elimina un usuario existente.
     *
     * @param usuario El {@link Usuario} a eliminar.
     */
    public void eliminarUsuario(Usuario usuario);

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id El ID del usuario a buscar.
     * @return El {@link Usuario} encontrado.
     */
    public Usuario buscarUsuarioPorId(Long id);

    /**
     * Busca un usuario por su número de identificación.
     *
     * @param numeroIdentidad El número de identificación del usuario a buscar.
     * @return El {@link Usuario} encontrado.
     */
    public Usuario buscarUsuarioPorNumeroIdentidad(String numeroIdentidad);

    /**
     * Obtiene una página de usuarios.
     *
     * @param numeroPagina  El número de página (0-basado).
     * @param tamanioPagina El tamaño de la página.
     * @return Un {@link Page} de {@link Usuario}.
     */
    public Page<Usuario> obtenerUsuariosPaginados(
        int numeroPagina,
        int tamanioPagina
    );
}
