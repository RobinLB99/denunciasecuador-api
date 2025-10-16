package ec.com.denunciasecuador.feature.usuario.dto;

/**
 * DTO (Data Transfer Object) para la respuesta de información de un usuario.
 * <p>
 * Este record se utiliza para encapsular los datos de un usuario que se envían
 * como respuesta, típicamente después de una operación exitosa como la creación,
 * consulta o actualización de un usuario.
 * </p>
 *
 * @param firstName El primer nombre del usuario.
 * @param surnames  Los apellidos del usuario.
 * @param username  El nombre de usuario asociado a las credenciales del usuario.
 * @author Robin Lugo
 * @version 1.0
 */
public record UsuarioResponseDTO(
    String firstName,
    String surnames,
    String username
) {}
