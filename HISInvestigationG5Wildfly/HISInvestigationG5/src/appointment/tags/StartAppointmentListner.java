package appointment.tags;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.struts.actions.DispatchAction;

import appointment.config.AppointmentConfig;



public class StartAppointmentListner implements ServletContextListener 
{
	private static final long serialVersionUID = 1L;
	static ServletContext scAppoinment=null;
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("contextInitialized---------->");
		Map mpActualParaRefId=new HashMap();
		arg0.getServletContext().setAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID,mpActualParaRefId);	 
		 scAppoinment=arg0.getServletContext();
		
	}
	 public static ServletContext getServletList()
	 {
		  return scAppoinment;
	 }
}
