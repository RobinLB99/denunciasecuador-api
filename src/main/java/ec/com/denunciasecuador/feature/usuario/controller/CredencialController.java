package ec.com.denunciasecuador.feature.usuario.controller;

import org.springframework.web.bind.annotation.RestController;

import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialResponseDTO;
import ec.com.denunciasecuador.feature.usuario.service.CredencialServiceImpl;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class CredencialController {

	private CredencialServiceImpl credencialServiceImpl;

	public CredencialController(CredencialServiceImpl credencialServiceImpl) {
		this.credencialServiceImpl = credencialServiceImpl;
	}

	@GetMapping("/credenciales/getByUsername/{username}")
	public ResponseEntity<CredencialResponseDTO> obtenerCredencialPorUsername(@PathVariable String username) {
		CredencialResponseDTO credencialDTO = new CredencialResponseDTO(
				credencialServiceImpl.buscarCredencialPorUsername(username));
		return ResponseEntity.ok(credencialDTO);
	}

	@GetMapping("/credenciales/getById/{id}")
	public ResponseEntity<CredencialResponseDTO> obtenerCredencialPorId(@PathVariable Long id) {
		CredencialResponseDTO credencialDTO = new CredencialResponseDTO(
				credencialServiceImpl.buscarCredencialPorId(id));
		return ResponseEntity.ok(credencialDTO);
	}

	@PostMapping("/credenciales/guardar_credencial")
	public ResponseEntity<CredencialResponseDTO> guardarCredencial(
			@Valid @RequestBody CredencialRequestDTO credencialRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new CredencialResponseDTO(credencialServiceImpl.guardarCredencial(credencialRequestDTO)));
	}

}
