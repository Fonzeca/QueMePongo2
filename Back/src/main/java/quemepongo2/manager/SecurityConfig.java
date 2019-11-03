package main.java.quemepongo2.manager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SecurityConfig {

	private SecretKeySpec secretKey;
	private	long offset = 3600*1000;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	private void init() {
		//Ciframos la clave, para que ande
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodeBytes = digest.digest("quemepongo2".getBytes(StandardCharsets.UTF_8));
			secretKey = new SecretKeySpec(encodeBytes, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String crearToken(int userId) {
		//Obtenemos el timestamp de ahora
		long vencimientoTime = new Date().getTime();
		//Le agregamos 60 segundos
		vencimientoTime += offset;
		
		//Concatenamos el Id + el timestampVencimiento: 27-1572805868
		String valoresToken = userId + "-" + vencimientoTime;
		
		
		//Encriptamos eso y mandamos como token
		String token = encriptar(valoresToken);
		
		log.info("Crea token = " + token + "   valores = " + valoresToken);
		
		return token;
	}
	
	public int validarToken(String token) {
		try {
			//desencriptamos y partimos a la mitad el string
			String[] valoresToken = desencriptar(token).split("-");
			//Agarramos el timestampVencimiento
			long vencimiento = Long.parseLong(valoresToken[1]);
			int userId = Integer.parseInt(valoresToken[0]);;
			
			//Verificamos que no se haya vencido
			if(new Date().getTime() > vencimiento) {
				//Token vencido
				log.error("Token vencido = " + token + "  UserId: " + userId);
				throw new RuntimeException("Token vencido, por favor vuelva a inicar sesion.");
			}
			
			return userId;
		} catch (NumberFormatException e) {
			
			log.error("Token invalido = " + token);
			throw new RuntimeException("Token invalido, por favor vuelva a inicar sesion.");
		}
	}
	
	public String resetearToken(String token) {
		String[] valoresToken = desencriptar(token).split("-");
		int userId = Integer.parseInt(valoresToken[1]);
		return crearToken(userId);
	}
	
	private String encriptar(String toToken) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			byte[] encriptado = cipher.doFinal(toToken.getBytes("UTF-8"));
			String encriptadoStr = Base64.getUrlEncoder().encodeToString(encriptado);
			
			return encriptadoStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String desencriptar(String token) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			
			byte[] encriptado = Base64.getUrlDecoder().decode(token);
			String desencriptadoStr = new String(cipher.doFinal(encriptado));
			
			return desencriptadoStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
