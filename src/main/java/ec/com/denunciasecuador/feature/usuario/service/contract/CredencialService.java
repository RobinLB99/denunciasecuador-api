package ec.com.denunciasecuador.feature.usuario.service.contract;

import org.springframework.data.domain.Page;

import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;

public interface CredencialService {

	public Credencial guardarCredencial(CredencialRequestDTO credencialRequestDTO);

	public void eliminarCredencialPorId(Long id);

	public void eliminarCredencial(Credencial credencial);

	public Credencial buscarCredencialPorId(Long id);

	public Credencial buscarCredencialPorUsername(String username);

	public Page<Credencial> obtenerCredencialesPaginadas(int numPage, int sizePage);

}
