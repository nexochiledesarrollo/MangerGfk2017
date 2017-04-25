package cl.nexo.manager.access.agenda;

import java.util.ArrayList;
import cl.nexo.manager.obj.agenda.ObjAgenda;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;



public interface AgendaAccess {

    public int setAgendado(ObjPersonaAgenda per);
    public ArrayList<ObjPersonaAgenda> getListAgendadosByid(int id_agenda);
    public int DeleteAgendado(int user,int agenda);
    public boolean getExistUserAgenda(int user,int agenda);
    public int SetAsistencia(int user,int operacion,int accion);
    public int createAgenda(ObjAgenda agen);
    public void modificaAgenda(ObjAgenda agen);
    public int aceptarAgenda(int agenda);
    public int rechazarAgenda(int agenda);
    public ObjAgenda getAgendaAbiertaByidOperacion(int operacion);
    public ObjAgenda getAgendaDefinitivaByidOperacion(int operacion);
    
}
