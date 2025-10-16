package ec.com.denunciasecuador.feature.denuncia.service;

import org.springframework.stereotype.Service;

import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaResponseDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;
import ec.com.denunciasecuador.feature.denuncia.service.contract.DenunciaDTOService;

@Service
public class DenunciaDTOServiceImpl implements DenunciaDTOService {

	@Override
	public DenunciaResponseDTO crearDenunciaResponseDTO(Denuncia denuncia) {
		return new DenunciaResponseDTO(
				denuncia.getId(), 
				denuncia.getTitle(),
				denuncia.getDescription(), 
				denuncia.getEventTimestamp(), 
				denuncia.getCityProvince(),
				denuncia.isPrivate(), 
				denuncia.getReportType().toString(),
				denuncia.getUsuario().getFirstName() + " " + denuncia.getUsuario().getSurnames());
	}

}
