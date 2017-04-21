package cl.nexo.manager.imp.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.nexo.manager.access.password.AccessPassword;

public class PasswordPlataform implements AccessPassword {
	/*  
	 PasswordGenerator.getPassword(
									PasswordGenerator.MINUSCULAS+
									PasswordGenerator.MAYUSCULAS+
									PasswordGenerator.ESPECIALES,10
								   ); 
	*/
	
	public static String NUMEROS = "0123456789";
	 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	public static String ESPECIALES = "��";
	
	@Override
	public  String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}
	@Override
	public  String getPassword() {
		return getPassword(10);
	}
	@Override
	public String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
	@Override
	public String getPassword(String key, int length) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}
	@Override
	public String getEncriptaPass(String pass){
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pass);
		
		return hashedPassword;
	}
	
	
}
