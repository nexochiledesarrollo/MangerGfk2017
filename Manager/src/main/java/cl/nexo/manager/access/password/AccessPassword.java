package cl.nexo.manager.access.password;

public interface AccessPassword {

	public String getPinNumber();

	public String getPassword();

	public String getPassword(int length);

	public String getPassword(String key, int length);

	public String getEncriptaPass(String pass);

}
