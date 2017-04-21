package cl.nexo.manager.imp.dashboard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.dashboard.AccessDashboard;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.proyecto.AccessCotizacion;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.tarea.AccessTarea;
import cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin;


public class PanelControl implements AccessDashboard {
	private static final Logger logger = Logger.getLogger(PanelControl.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ObjWidjetAccessAdmin getValueDashAdmin(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	    LoginAccess login = (LoginAccess) context.getBean("LoginAccess");
	    AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
	    AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
	    AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
	    
		
	    int users = login.getCountUsers(0);
	    int proy = ests.getCountEstudios();
	    int cotiza = cots.getCountCotizacion();
	    int tarea = tareas.getCountTareas(6, 0, 0);
	    int conf = 0;
	    
		ObjWidjetAccessAdmin result = new ObjWidjetAccessAdmin();
		result.setUsuarios(users);
		result.setProyectos(proy);
		result.setCotiza(cotiza);
		result.setTareas(tarea);
		result.setConf(conf);
		
		return result;
	}
}
