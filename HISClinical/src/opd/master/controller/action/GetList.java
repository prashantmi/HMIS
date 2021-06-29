package opd.master.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GetList
 *
 */
 public class GetList extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   
	public GetList() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List list=new OpdEssentialDelegate().opdDeskEssentials(ControllerUTIL.getUserVO(request));
		PrintWriter printWriter= response.getWriter();
		//ListIterator listIterator=list.listIterator();
		String result="";
		
	/*	String result="<select name='check'>" +
				"<option value='-1'>Select Value</option>";*/
		/*while(listIterator.hasNext()){ 
			Entry entry=(Entry)listIterator.next();
			result=result+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
		}*/
		//result=result+"</select>";
		result="12^15";
		printWriter.write(result);
		}  	

		  	    
}