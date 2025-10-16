package ec.com.denunciasecuador.feature.denuncia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaRequestDTO;
import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaResponseDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;
import ec.com.denunciasecuador.feature.denuncia.service.DenunciaDTOServiceImpl;
import ec.com.denunciasecuador.feature.denuncia.service.DenunciaServiceImpl;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

	private DenunciaServiceImpl denunciaServiceImpl;
	private DenunciaDTOServiceImpl denunciaDTOService;

	public DenunciaController(DenunciaServiceImpl denunciaServiceImpl, DenunciaDTOServiceImpl denunciaDTOService) {
		this.denunciaServiceImpl = denunciaServiceImpl;
		this.denunciaDTOService = denunciaDTOService;
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<DenunciaResponseDTO> obtenerDenunciaPorId(@PathVariable Long id) {
		DenunciaResponseDTO denunciaResponseDTO = denunciaDTOService
				.crearDenunciaResponseDTO(denunciaServiceImpl.obtenerDenunciaPorId(id));
		return ResponseEntity.ok(denunciaResponseDTO);
	}

	@GetMapping("/getByIdentityNumberUsuario/{identityNumber}")
	public ResponseEntity<Page<Denuncia>> obtenerDenunciaPorCIUsuario(
			@PathVariable String identityNumber,
			@RequestParam("page") int numPage, 
			@RequestParam("size") int pageSize) {
		Page<Denuncia> denunciasPage = denunciaServiceImpl
				.obtenerDenunciasPorNumeroIdentidadUsuario(identityNumber, numPage, pageSize);
		return ResponseEntity.ok(denunciasPage);
	}

	@PostMapping("/guardar_denuncia")
	public ResponseEntity<DenunciaResponseDTO> guardarDenuncia(
			@Valid @RequestBody DenunciaRequestDTO denunciaRequestDTO) {
		DenunciaResponseDTO denunciaResponseDTO = denunciaDTOService
				.crearDenunciaResponseDTO(denunciaServiceImpl.guardarDenuncia(denunciaRequestDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(denunciaResponseDTO);
	}

}
