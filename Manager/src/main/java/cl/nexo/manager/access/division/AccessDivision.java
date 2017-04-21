package cl.nexo.manager.access.division;

import java.util.ArrayList;

import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public interface AccessDivision {

	public ArrayList<ObjComboSelectValueInt> getComboDivision();

	public ArrayList<ObjComboSelectValueInt> getComboSubDivision(int div);

}
