package cl.nexo.manager.access.instructivo;

import java.util.ArrayList;
import cl.nexo.manager.obj.instructivo.ObjInstructivo;



public interface InstructivoAccess {

    public int crearInstructivo(ObjInstructivo inst);
    public int crearInstructivoCapi(ObjInstructivo inst);
    public ObjInstructivo getDetailInstructivoCatiById(int id);
    public ObjInstructivo getDetailInstructivoById(int id);
    public int updateInstructivoCapi(ObjInstructivo inst);
    public int updateInstructivo(ObjInstructivo inst);
	

	

	

	

	

	

}
