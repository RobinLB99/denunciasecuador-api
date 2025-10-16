package ec.com.denunciasecuador.feature.usuario.service.contract;

import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import org.springframework.data.domain.Page;

/**
 * Interfaz de servicio para la gestión de {@link Credencial}.
 * <p>
 * Define el contrato para las operaciones de negocio relacionadas con las
 * credenciales de usuario, incluyendo la creación, eliminación y búsqueda.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
public interface CredencialService {
    /**
     * Guarda una nueva credencial en el sistema.
     *
     * @param credencialRequestDTO DTO que contiene los datos de la credencial a guardar.
     * @return La {@link Credencial} guardada.
     */
    public Credencial guardarCredencial(
        CredencialRequestDTO credencialRequestDTO
    );

    /**
     * Elimina una credencial por su identificador único.
     *
     * @param id El ID de la credencial a eliminar.
     */
    public void eliminarCredencialPorId(Long id);

    /**
     * Elimina una credencial existente.
     *
     * @param credencial La {@link Credencial} a eliminar.
     */
    public void eliminarCredencial(Credencial credencial);

    /**
     * Busca una credencial por su identificador único.
     *
     * @param id El ID de la credencial a buscar.
     * @return La {@link Credencial} encontrada.
     */
    public Credencial buscarCredencialPorId(Long id);

    /**
     * Busca una credencial por su nombre de usuario.
     *
     * @param username El nombre de usuario de la credencial a buscar.
     * @return La {@link Credencial} encontrada.
     */
    public Credencial buscarCredencialPorUsername(String username);

    /**
     * Obtiene una página de credenciales.
     *
     * @param numPage  El número de página (0-basado).
     * @param sizePage El tamaño de la página.
     * @return Un {@link Page} de {@link Credencial}.
     */
    public Page<Credencial> obtenerCredencialesPaginadas(
        int numPage,
        int sizePage
    );
}
