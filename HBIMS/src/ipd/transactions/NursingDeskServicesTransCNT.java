



package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




public class NursingDeskServicesTransCNT extends GenericController  {
	
	public NursingDeskServicesTransCNT(){
		super(new NursingDeskServicesTransUTL(), "/transactions/NursingDeskServicesTransCNT");
	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException  {

		String strtarget ="";
	
	   
		 strtarget = "acceptlist";
        return mapping.findForward(strtarget);
	}
	
	public ActionForward TRANSFER(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException  {

		String strtarget ="";
	
	   
		 strtarget = "transfer";
        return mapping.findForward(strtarget);
	}
	
	public ActionForward LEAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException  {

		String strtarget ="";
	
	   
		 strtarget = "leave";
        return mapping.findForward(strtarget);
	}
	public ActionForward DISCHARGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException  {

		String strtarget ="";
	
	   
		 strtarget = "discharge";
        return mapping.findForward(strtarget);
	}
	public ActionForward DEATHNOTE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException  {

		String strtarget ="";
	
	   
		 strtarget = "deathnotification";
        return mapping.findForward(strtarget);
	}
}