package ec.com.denunciasecuador.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuración para la validación de beans en la aplicación.
 * <p>
 * Esta clase establece la configuración necesaria para utilizar mensajes de
 * validación personalizados desde un archivo de propiedades, en lugar de los
 * mensajes predeterminados de Hibernate Validator.
 * </p>
 */
@Configuration
public class ValidationConfig {

	/**
	 * Configura y devuelve un {@link MessageSource} para cargar los mensajes de
	 * validación.
	 * <p>
	 * El {@code MessageSource} se configura para leer los mensajes desde el archivo
	 * {@code messagevalidator.properties} ubicado en el classpath.
	 * </p>
	 * 
	 * @return una instancia de {@code MessageSource} configurada.
	 */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messagevalidator");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
	 * Configura y devuelve un {@link LocalValidatorFactoryBean} que utiliza el
	 * {@code MessageSource} personalizado.
	 * <p>
	 * Este método integra el {@code MessageSource} de mensajes de validación con el
	 * validador de beans de Spring, permitiendo que las anotaciones de validación
	 * (como {@code @NotNull}, {@code @Size}, etc.) usen los mensajes definidos en
	 * {@code messagevalidator.properties}.
	 * </p>
	 * 
	 * @return una instancia de {@code LocalValidatorFactoryBean} configurada.
	 */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
