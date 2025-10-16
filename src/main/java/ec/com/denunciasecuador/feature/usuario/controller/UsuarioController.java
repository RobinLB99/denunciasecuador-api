package ec.com.denunciasecuador.feature.usuario.controller;

import ec.com.denunciasecuador.feature.usuario.dto.UsuarioRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioResponseDTO;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import ec.com.denunciasecuador.feature.usuario.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de usuarios.
 * <p>
 * Este controlador maneja las solicitudes HTTP relacionadas con las operaciones
 * CRUD de los usuarios, interactuando con {@link UsuarioServiceImpl}.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioServiceImpl usuarioServiceImpl;

    /**
     * Constructor que inyecta el servicio de implementación de usuarios.
     *
     * @param usuarioServiceImpl El servicio de implementación de usuarios a inyectar.
     */
    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    /**
     * Obtiene un usuario por su número de identificación.
     * <p>
     * Responde a solicitudes GET en {@code /usuarios/getByCI/{numeroIdentidad}}.
     * </p>
     *
     * @param numeroIdentidad El número de identificación del usuario a buscar.
     * @return Un {@link ResponseEntity} que contiene un {@link UsuarioResponseDTO}
     *         con los datos del usuario encontrado y un estado HTTP 200 OK.
     */
    @GetMapping("/getByCI/{numeroIdentidad}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorNumeroIdentidad(
        @PathVariable String numeroIdentidad
    ) {
        UsuarioResponseDTO usuarioDTO =
            usuarioServiceImpl.crearUsuarioResponseDTO(
                usuarioServiceImpl.buscarUsuarioPorNumeroIdentidad(
                    numeroIdentidad
                )
            );
        return ResponseEntity.ok(usuarioDTO);
    }

    /**
     * Obtiene un usuario por su identificador único.
     * <p>
     * Responde a solicitudes GET en {@code /usuarios/getById/{id}}.
     * </p>
     *
     * @param id El ID del usuario a buscar.
     * @return Un {@link ResponseEntity} que contiene un {@link UsuarioResponseDTO}
     *         con los datos del usuario encontrado y un estado HTTP 200 OK.
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(
        @PathVariable Long id
    ) {
        UsuarioResponseDTO usuarioDTO =
            usuarioServiceImpl.crearUsuarioResponseDTO(
                usuarioServiceImpl.buscarUsuarioPorId(id)
            );
        return ResponseEntity.ok(usuarioDTO);
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     * <p>
     * Responde a solicitudes POST en {@code /usuarios/guardar_usuario}.
     * El cuerpo de la solicitud debe contener un {@link UsuarioRequestDTO} válido.
     * </p>
     *
     * @param usuarioRequestDTO DTO con los datos del usuario a guardar.
     * @return Un {@link ResponseEntity} que contiene un {@link UsuarioResponseDTO}
     *         con los datos del usuario creado y un estado HTTP 201 CREATED.
     */
    @PostMapping("/guardar_usuario")
    public ResponseEntity<UsuarioResponseDTO> guardarUsuario(
        @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        Usuario usuario = usuarioServiceImpl.mapperDTOUsuario(
            usuarioRequestDTO
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
            usuarioServiceImpl.crearUsuarioResponseDTO(
                usuarioServiceImpl.guardarUsuario(usuario)
            )
        );
    }
}
