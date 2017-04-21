package cl.nexo.manager.access.agenda;

import java.util.ArrayList;

import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;



public interface AgendaAccess {

    public int setAgendado(ObjPersonaAgenda per);
    public ArrayList<ObjPersonaAgenda> getListAgendadosByidOperacion(int id_operacion);
    public int DeleteAgendado(int user,int operacion);
    public boolean getExistUserAgenda(int user,int operacion);
    public int SetAsistencia(int user,int operacion,int accion);
    
}
