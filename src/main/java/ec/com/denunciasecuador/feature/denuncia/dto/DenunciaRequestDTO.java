package ec.com.denunciasecuador.feature.denuncia.dto;

import java.time.LocalDateTime;

import ec.com.denunciasecuador.feature.denuncia.model.constant.TipoDenuncia;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "{TITLE.NOTBLANK}")
	@Size(min = 5, message = "{TITLE.SIZE}")
	private String title;
	
	@Nonnull
	@NotNull(message = "{DESCRIPTION.NOTNULL}")
	private String description;
	
	@Nonnull
	@NotNull(message = "{EVENTTIMESTAMP.NOTNULL}")
	private LocalDateTime eventTimestamp;
	
	@Nonnull
	@NotBlank(message = "{CITYPROVINCE.NOTBLANK}")
	private String cityProvince;
	
	@Nonnull
	@NotNull(message = "{ISPRIVATE.NOTNUL}")
	private boolean isPrivate;
	
	@Nonnull
	@NotNull(message = "{REPORTTYPE.NOTNULL}")
	@Enumerated(EnumType.STRING)
	private TipoDenuncia reportType;
	
	@Nonnull
	@NotNull(message = "{USERID.NOTNULL}")
	private Long usuario_id;
	
}
