package ec.com.denunciasecuador.feature.denuncia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaRequestDTO;
import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaResponseDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;
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

	public DenunciaController(DenunciaServiceImpl denunciaServiceImpl) {
		this.denunciaServiceImpl = denunciaServiceImpl;
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<DenunciaResponseDTO> obtenerDenunciaPorId(@PathVariable Long id) {
		DenunciaResponseDTO denunciaResponseDTO = crearDenunciaResponseDTO(
				denunciaServiceImpl.obtenerDenunciaPorId(id));
		return ResponseEntity.ok(denunciaResponseDTO);
	}

	@GetMapping("/getByIdentityNumberUsuario/{identityNumber}")
	public ResponseEntity<Page<Denuncia>> obtenerDenunciaPorCIUsuario(@PathVariable String identityNumber,
			@RequestParam("page") int numPage, @RequestParam("size") int pageSize) {
		return ResponseEntity
				.ok(denunciaServiceImpl.obtenerDenunciasPorNumeroIdentidadUsuario(identityNumber, numPage, pageSize));
	}

	@PostMapping("/guardar_denuncia")
	public ResponseEntity<DenunciaResponseDTO> guardarDenuncia(
			@Valid @RequestBody DenunciaRequestDTO denunciaRequestDTO) {
		DenunciaResponseDTO denunciaResponseDTO = crearDenunciaResponseDTO(
				denunciaServiceImpl.guardarDenuncia(denunciaRequestDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(denunciaResponseDTO);
	}

	private DenunciaResponseDTO crearDenunciaResponseDTO(Denuncia denuncia) {
		DenunciaResponseDTO denunciaResponseDTO = new DenunciaResponseDTO();
		denunciaResponseDTO.setReport_id(denuncia.getId());
		denunciaResponseDTO.setTitle(denuncia.getTitle());
		denunciaResponseDTO.setDescription(denuncia.getDescription());
		denunciaResponseDTO.setEventTimestamp(denuncia.getEventTimestamp());
		denunciaResponseDTO.setCityProvince(denuncia.getCityProvince());
		denunciaResponseDTO.setPrivate(denuncia.isPrivate());
		denunciaResponseDTO.setReportType(denuncia.getReportType().toString());
		denunciaResponseDTO.setUsuario_id(denuncia.getUsuario().getId());
		return denunciaResponseDTO;
	}

}
