package cl.nexo.manager.obj.tools;

import java.util.ArrayList;

public class ObjComboSelect2GroupValueInt {
	int id;
	String text;
	ArrayList<ObjComboSelect2GroupValueInt> children;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<ObjComboSelect2GroupValueInt> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<ObjComboSelect2GroupValueInt> children) {
		this.children = children;
	}
	
	
}
