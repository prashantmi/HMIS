package hisglobal.tools.tag;

import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisNotAnEmergencyCaseException;
import hisglobal.exceptions.HisNotAnOPDcaseException;
import hisglobal.exceptions.HisNotEligibleForMLCException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import registration.RegistrationConfig;
import registration.bo.delegate.*;


public class PatientstatusTag extends TagSupport{
	
	private String emergency="";
	private String opd="";
	
	
	
	public String getEmergency() {
		return emergency;
	}

	public String getOpd() {
		return opd;
	}

	public void setOpd(String opd) {
		this.opd = opd;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}	

	public int doStartTag() throws JspTagException		{
		    System.out.println("emergency true");
	     	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
	     	//Status objStatus= (Status) request.getAttribute(RegistrationConfig.STATUS_OBJECT);
	 	   Status objStatus= (Status) request.getAttribute(Config.STATUS_OBJECT);
	 	   
	    	try{	  		    
			  	HttpSession session = (HttpSession)pageContext.getSession();
			  	PatientVO patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);			
				if(emergency.equalsIgnoreCase("true")){
				   System.out.println("emergency true");
					EpisodeVO episodeVO= new PatientDelegate().isEmergency(patVO,ControllerUTIL.getUserVO(request));
					WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,episodeVO);
				   	return EVAL_BODY_INCLUDE;
			}			
			if(opd.equalsIgnoreCase("true")){
			   System.out.println("opd true");
			   PatientDelegate delegate=new PatientDelegate();
				String patStatus= delegate.checkPatientStatus(patVO,ControllerUTIL.getUserVO(request));
				if(patStatus.equalsIgnoreCase("12")){
					return EVAL_BODY_INCLUDE;					
				}
				else{					
					throw new HisNotAnOPDcaseException();									
				}
							   	
			}
		   }//end of try	    	
		  	catch(HisNotAnOPDcaseException e){
		  		System.out.println("Not An OPD Case");
		  		objStatus.add(Status.CUSTOM,"","Not An OPD Case/Episode already Confirmed/Closed");
		  		return SKIP_BODY;			
		  	}	
		  	catch(HisNotAnEmergencyCaseException e){
		  		System.out.println("Not An Emergency Case");
		  		objStatus.add(Status.CUSTOM,"","Not An Emergency Case/Episode already Confirmed/Closed");
		  		return SKIP_BODY;			
		  	}	
		  	catch(HisException e){
		  		System.out.println("Inside");
		  		objStatus.add(Status.UNSUCESSFULL,"","Error::");		  	
		  		return SKIP_BODY;		
		  	}
		  	
		  	finally{
		  	request.setAttribute(Config.STATUS_OBJECT,objStatus);		  		  		
		  	} 			  	
			return SKIP_BODY;	
		}

	
}//end of class
	