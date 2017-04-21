package cl.nexo.manager.obj.login;

public class ObjExistePermisoModuloPerfil {
	int modulo_id;
	int perfil_id;
	int existe_permiso;
	int permiso_id;
	String permiso_nombre;
	
	public int getModulo_id() {
		return modulo_id;
	}
	public void setModulo_id(int modulo_id) {
		this.modulo_id = modulo_id;
	}
	public int getPerfil_id() {
		return perfil_id;
	}
	public void setPerfil_id(int perfil_id) {
		this.perfil_id = perfil_id;
	}
	public int getExiste_permiso() {
		return existe_permiso;
	}
	public void setExiste_permiso(int existe_permiso) {
		this.existe_permiso = existe_permiso;
	}
	public int getPermiso_id() {
		return permiso_id;
	}
	public void setPermiso_id(int permiso_id) {
		this.permiso_id = permiso_id;
	}
	public String getPermiso_nombre() {
		return permiso_nombre;
	}
	public void setPermiso_nombre(String permiso_nombre) {
		this.permiso_nombre = permiso_nombre;
	}
	
	
}
