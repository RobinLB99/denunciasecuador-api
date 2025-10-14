package ec.com.denunciasecuador.feature.denuncia.dto;

import java.time.LocalDateTime;

import ec.com.denunciasecuador.feature.denuncia.model.constant.TipoDenuncia;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaRequestDTO {
	
	@Nonnull
	@NotBlank(message = "{}")
	private String title;
	
	@Nonnull
	@NotNull(message = "{}")
	private String description;
	
	@Nonnull
	@NotNull(message = "{}")
	private LocalDateTime eventTimestamp;
	
	@Nonnull
	@NotBlank(message = "{}")
	private String cityProvince;
	
	@Nonnull
	@NotNull(message = "{}")
	private boolean isPrivate;
	
	@Nonnull
	@NotNull(message = "{}")
	@Enumerated(EnumType.STRING)
	private TipoDenuncia reportType;
	
	@Nonnull
	@NotNull(message = "{}")
	private Long usuario_id;
	
}
