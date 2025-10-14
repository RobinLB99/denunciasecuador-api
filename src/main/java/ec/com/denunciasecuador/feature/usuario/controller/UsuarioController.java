package ec.com.denunciasecuador.feature.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.denunciasecuador.feature.usuario.dto.UsuarioRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioResponseDTO;
import ec.com.denunciasecuador.feature.usuario.service.UsuarioServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioServiceImpl usuarioServiceImpl;

	public UsuarioController(UsuarioServiceImpl usuarioServiceImpl) {
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	@GetMapping("/getByCI/{numeroIdentidad}")
	public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorNumeroIdentidad(@PathVariable String numeroIdentidad) {
		UsuarioResponseDTO usuarioDTO = usuarioServiceImpl.buscarUsuarioPorNumeroIdentidad(numeroIdentidad);
		return ResponseEntity.ok(usuarioDTO);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
		UsuarioResponseDTO usuarioDTO = usuarioServiceImpl.buscarUsuarioPorId(id);
		return ResponseEntity.ok(usuarioDTO);
	}

	@PostMapping("/guardar")
	public ResponseEntity<UsuarioResponseDTO> guardarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		UsuarioResponseDTO usuarioDTO = 
				new UsuarioResponseDTO(usuarioServiceImpl.guardarUsuario(usuarioRequestDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}

}
