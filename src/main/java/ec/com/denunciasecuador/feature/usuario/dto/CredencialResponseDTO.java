package ec.com.denunciasecuador.feature.usuario.dto;

/**
 * DTO (Data Transfer Object) para la respuesta de credenciales.
 * <p>
 * Este record se utiliza para encapsular los datos de credenciales que se
 * envían como respuesta, típicamente después de una operación exitosa como la
 * creación o consulta de credenciales.
 * </p>
 *
 * @param id       El identificador único de la credencial.
 * @param username El nombre de usuario asociado a la credencial.
 * @author Robin Lugo
 * @version 1.0
 */
public record CredencialResponseDTO(Long id, String username) {}
