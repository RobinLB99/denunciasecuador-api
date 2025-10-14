package ec.com.denunciasecuador.feature.denuncia.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DenunciaResponseDTO {

	private Long report_id;
	private String title;
	private String description;
	private LocalDateTime eventTimestamp;
	private String cityProvince;
	private boolean isPrivate;
	private String reportType;
	private Long usuario_id;

}
