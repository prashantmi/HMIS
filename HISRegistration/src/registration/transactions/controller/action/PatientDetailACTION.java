package registration.transactions.controller.action;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.CRNoSUP;
import registration.transactions.controller.util.DuplicateCardGenerationUTIL;
import registration.transactions.controller.util.PatDetailUTIL;


public class PatientDetailACTION extends CRNoSUP
{
	/**
	 * sets Patient Details
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "SAME"
	 */
	public String execute(){
		{
			System.out.println("PatientDetailACTION :: GETPATDTL");	
			CharacterEncoding.setCharacterEncoding(objRequest); 
			
			String strCrNo=(String)objRequest.getParameter("patCrNo");
			if(strCrNo!=null && !strCrNo.equals(""))
				this.patCrNo=strCrNo;
			PatDetailUTIL.getPatientDtlByCrno(this,objRequest);				
		 	//System.out.println("before forwarding"); 
			return "NEW";			
		}
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}	

	
}
