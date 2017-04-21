package cl.nexo.manager.access.login;

import java.util.ArrayList;

import cl.nexo.manager.obj.login.ObjExistePermisoSubDivisionPerfil;
import cl.nexo.manager.obj.login.ObjPermisosSubDivisionPerfil;
import cl.nexo.manager.obj.login.ObjExistePermisoModuloPerfil;
import cl.nexo.manager.obj.login.ObjListManPerfil;
import cl.nexo.manager.obj.login.ObjMenuPermisosModulosPerfil;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.login.ObjPermisosModuloPerfil;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public interface AccessPerfil {

	public ObjPerfilLogin getPerfilById(int id);

	public ArrayList<ObjPerfilLogin> getListPerfilByCliente(int id);

	public int setPerfilUser(ObjPerfilLogin perfil);

	public int updatePerfilUser(ObjPerfilLogin perfil);

	public int deletePerfilUser(int perfil);

	public int setAccesoPerfilModulo(ObjPermisosModuloPerfil permiso);

	public int deletePerfilModulo(ObjPermisosModuloPerfil permiso);

	public ObjExistePermisoModuloPerfil existePermisoPerfilModulo(int perfil, int modulo);

	public int getPermisoPerfilModulo(int perfil, int modulo);

	public ArrayList<ObjMenuPermisosModulosPerfil> getListModulosPermisosPerfil(int menu, int perfil);

	public ArrayList<ObjComboSelectValueInt> getComboMenuByPerfil(int cliente);

	public String getStrPerfilById(int id);

	public ArrayList<ObjListManPerfil> getListManPerfilByCliente(int id);

	public int getExistPerfilByName(String perfil);

	public int getIdPerfilUserByName(String perfil);

	public ArrayList<ObjComboSelectValueInt> getComboPerfilCliente(int id, int div);

	public ArrayList<ObjPermisosSubDivisionPerfil> getListSubDivisionesPermisosPerfil(int perfil);

	public ObjExistePermisoSubDivisionPerfil existePermisoPerfilSubDivision(int perfil, int subDiv);

	public int setAccesoPerfilSubDiv(ObjPermisosModuloPerfil permiso);

	public int deletePerfilSubDiv(ObjPermisosModuloPerfil permiso);

}
