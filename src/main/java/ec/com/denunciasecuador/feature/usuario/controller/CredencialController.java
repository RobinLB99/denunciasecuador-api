package ec.com.denunciasecuador.feature.usuario.controller;

import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialResponseDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.service.CredencialServiceImpl;
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
 * Controlador REST para la gestión de credenciales de usuario.
 * <p>
 * Este controlador maneja las solicitudes HTTP relacionadas con las operaciones
 * CRUD de las credenciales, interactuando con {@link CredencialServiceImpl}.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
@RestController
@RequestMapping("/usuarios")
public class CredencialController {

    private CredencialServiceImpl credencialServiceImpl;

    /**
     * Constructor que inyecta el servicio de implementación de credenciales.
     *
     * @param credencialServiceImpl El servicio de implementación de credenciales a inyectar.
     */
    public CredencialController(CredencialServiceImpl credencialServiceImpl) {
        this.credencialServiceImpl = credencialServiceImpl;
    }

    /**
     * Obtiene una credencial por su nombre de usuario.
     * <p>
     * Responde a solicitudes GET en {@code /usuarios/credenciales/getByUsername/{username}}.
     * </p>
     *
     * @param username El nombre de usuario de la credencial a buscar.
     * @return Un {@link ResponseEntity} que contiene un {@link CredencialResponseDTO}
     *         con los datos de la credencial encontrada y un estado HTTP 200 OK.
     */
    @GetMapping("/credenciales/getByUsername/{username}")
    public ResponseEntity<CredencialResponseDTO> obtenerCredencialPorUsername(
        @PathVariable String username
    ) {
        Credencial credencial =
            credencialServiceImpl.buscarCredencialPorUsername(username);
        CredencialResponseDTO credencialDTO =
            credencialServiceImpl.crearCResponseDTO(credencial);
        return ResponseEntity.ok(credencialDTO);
    }

    /**
     * Obtiene una credencial por su identificador único.
     * <p>
     * Responde a solicitudes GET en {@code /usuarios/credenciales/getById/{id}}.
     * </p>
     *
     * @param id El ID de la credencial a buscar.
     * @return Un {@link ResponseEntity} que contiene un {@link CredencialResponseDTO}
     *         con los datos de la credencial encontrada y un estado HTTP 200 OK.
     */
    @GetMapping("/credenciales/getById/{id}")
    public ResponseEntity<CredencialResponseDTO> obtenerCredencialPorId(
        @PathVariable Long id
    ) {
        Credencial credencial = credencialServiceImpl.buscarCredencialPorId(id);
        CredencialResponseDTO credencialDTO =
            credencialServiceImpl.crearCResponseDTO(credencial);
        return ResponseEntity.ok(credencialDTO);
    }

    /**
     * Guarda una nueva credencial en el sistema.
     * <p>
     * Responde a solicitudes POST en {@code /usuarios/credenciales/guardar_credencial}.
     * El cuerpo de la solicitud debe contener un {@link CredencialRequestDTO} válido.
     * </p>
     *
     * @param credencialRequestDTO DTO con los datos de la credencial a guardar.
     * @return Un {@link ResponseEntity} que contiene un {@link CredencialResponseDTO}
     *         con los datos de la credencial creada y un estado HTTP 201 CREATED.
     */
    @PostMapping("/credenciales/guardar_credencial")
    public ResponseEntity<CredencialResponseDTO> guardarCredencial(
        @Valid @RequestBody CredencialRequestDTO credencialRequestDTO
    ) {
        Credencial credencial = credencialServiceImpl.guardarCredencial(
            credencialRequestDTO
        );
        CredencialResponseDTO credencialDTO =
            credencialServiceImpl.crearCResponseDTO(credencial);
        return ResponseEntity.status(HttpStatus.CREATED).body(credencialDTO);
    }
}
