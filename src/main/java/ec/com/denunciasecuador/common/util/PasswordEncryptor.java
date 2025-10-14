package ec.com.denunciasecuador.common.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Clase de utilidad para encriptar y verificar contraseñas utilizando el algoritmo de hash BCrypt.
 * 
 * <p>Esta clase proporciona métodos estáticos para hashear contraseñas de forma segura y verificarlas
 * contra los hashes almacenados. BCrypt es una función de hash de contraseñas diseñada para ser lenta
 * y resistente a ataques de fuerza bruta.</p>
 * 
 * @author denunciasecuador
 * @version 1.0
 */
public class PasswordEncryptor {

	/**
	 * Encripta una contraseña en texto plano utilizando el algoritmo de hash BCrypt.
	 * 
	 * <p>Este método genera un nuevo salt y crea un hash BCrypt de la contraseña proporcionada.
	 * Cada llamada producirá un hash diferente incluso para la misma contraseña debido a la
	 * generación aleatoria del salt.</p>
	 * 
	 * @param plainPassword la contraseña en texto plano a encriptar
	 * @return la contraseña hasheada con BCrypt incluyendo el salt
	 * @throws IllegalArgumentException si la contraseña es null
	 */
	public static String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	/**
	 * Verifica una contraseña en texto plano contra una contraseña hasheada con BCrypt.
	 * 
	 * <p>Este método verifica si la contraseña en texto plano proporcionada coincide con el hash
	 * almacenado. Extrae el salt de la contraseña hasheada y lo utiliza para hashear la contraseña
	 * en texto plano, luego compara los resultados.</p>
	 * 
	 * @param plainPassword la contraseña en texto plano a verificar
	 * @param hashedPassword la contraseña hasheada con BCrypt contra la cual verificar
	 * @return {@code true} si la contraseña coincide con el hash, {@code false} en caso contrario
	 * @throws IllegalArgumentException si algún parámetro es null o la contraseña hasheada es inválida
	 */
	public static boolean verifyPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}

}
