package ec.com.denunciasecuador.feature.registro.controller;

import ec.com.denunciasecuador.feature.registro.service.RegistroServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.denunciasecuador.feature.registro.dto.RegistroRequestDTO;
import ec.com.denunciasecuador.feature.registro.dto.RegistroResponseDTO;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/registro")
public class RegistroController {

	private RegistroServiceImpl registroService;

	public RegistroController(RegistroServiceImpl registroService) {
		this.registroService = registroService;
	}

	@PostMapping("/nuevo_registro")
	public ResponseEntity<RegistroResponseDTO> realizarNuevoRegistro(@Valid @RequestBody RegistroRequestDTO registro) {
		RegistroResponseDTO registrado = registroService
				.usuarioMapearRegistro(registroService.registrarNuevoUsuario(registro));
		return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
	}

}
