package ec.com.denunciasecuador.feature.denuncia.service.contract;

import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaResponseDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;

public interface DenunciaDTOService {
	public DenunciaResponseDTO crearDenunciaResponseDTO(Denuncia denuncia);
}
