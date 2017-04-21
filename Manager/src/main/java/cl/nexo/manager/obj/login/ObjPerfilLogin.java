package cl.nexo.manager.obj.login;

public class ObjPerfilLogin {
	int id_perfil;
	int codigo_perfil;
	String nombre_perfil;
	int estado_perfil;
	int id_cliente;
	int es_admin;
	
	public int getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}
	public int getCodigo_perfil() {
		return codigo_perfil;
	}
	public void setCodigo_perfil(int codigo_perfil) {
		this.codigo_perfil = codigo_perfil;
	}
	public String getNombre_perfil() {
		return nombre_perfil;
	}
	public void setNombre_perfil(String nombre_perfil) {
		this.nombre_perfil = nombre_perfil;
	}
	public int getEstado_perfil() {
		return estado_perfil;
	}
	public void setEstado_perfil(int estado_perfil) {
		this.estado_perfil = estado_perfil;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getEs_admin() {
		return es_admin;
	}
	public void setEs_admin(int es_admin) {
		this.es_admin = es_admin;
	}
	
	
}
