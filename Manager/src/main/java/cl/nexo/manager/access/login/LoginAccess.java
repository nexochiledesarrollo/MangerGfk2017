package cl.nexo.manager.access.login;

import java.util.ArrayList;

import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public interface LoginAccess {

	public ObjLoginUser getUserById(int id);
	
	public ObjLoginUser getUserByLogin(String login);
	
	public ObjLoginUser getUserByActiveCode(String code);
	
	public ObjLoginUser getUserByMail(String mail);

	public ArrayList<ObjLoginUser> getListUsers(int cliente);

	public int getIdMaxInsertId(int idSesion);

	public int getCountUsers(int idCliente);

	public String getStrLoginById(int idUser);

	public int getExistLoginById(String login);
	
	public int getExistLoginByRun(String run);
	
	public int getExistLoginByMail(String mail);

	public int setUserManager(ObjLoginUser user);

	public int updatePasswordLoginBySistem(int idUser);
	
	public int activePasswordByUser(ObjLoginUser user);

	public int updateLogin(ObjLoginUser user);

	public int updatePasswordByUser(ObjLoginUser user);

	public int deleteUser(int idUser);

	public ArrayList<ObjComboSelect2ValueInt> getCombo(int tipo, int id, String place);
	
	
	
	//////////////////////////////////////////////////////////////////
	////Listar USuario  Mant user
	public ArrayList<ObjListManUser> getListUserByCliente(int cliente);

	public String getStrNameLoginById(int idUser);

	

	

	

	

	

	

	

	

}
