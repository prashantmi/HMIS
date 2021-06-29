package hisglobal.tools.tag;

import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisMlcAlreadyExistException;
import hisglobal.exceptions.HisNotEligibleForEmgOldPatientVisitException;
import hisglobal.exceptions.HisNotEligibleForMLCException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import registration.RegistrationConfig;
import registration.bo.delegate.PatientDelegate;
import registration.controller.data.OPDDoctorDeskDATA;
import registration.controller.util.OPDDoctorDeskUTIL;


public class RetrieveOpenEpisodesTag extends TagSupport {
	private String isMlcEligible="";	 
	private String isEmg="";
	private String isOpd="";
	
	public int doStartTag() throws JspTagException
	{
	   
	  PatientDelegate delegate=new PatientDelegate();
	   HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
	   //Status objStatus= (Status) request.getAttribute(RegistrationConfig.STATUS_OBJECT);
	   Status objStatus= (Status) request.getAttribute(Config.STATUS_OBJECT);
	   try{
	   if(isMlcEligible.equalsIgnoreCase("true")){
		    PatientVO patVO=(PatientVO)WebUTIL.getSession(request).getAttribute(RegistrationConfig.PATIENT_VO);
		    System.out.println("inside setopen episodes"+patVO.getPatFirstName());
		   	System.out.println("inside setopen episodes");			
			EpisodeVO arrepisodeVO=delegate.isMlcEligible(patVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,arrepisodeVO);
			return EVAL_BODY_INCLUDE;
	   }
	   if(isEmg.equalsIgnoreCase("true")){
		   PatientVO patVO=(PatientVO)WebUTIL.getSession(request).getAttribute(RegistrationConfig.PATIENT_VO);
		    System.out.println("inside isEmgOldVisitEligible episodes"+patVO.getPatFirstName());
		   	System.out.println("inside setopen episodes");			
			EpisodeVO episodeVO=delegate.getEmgOpenEpisode(patVO,getUserVO(request));
			if(episodeVO!=null){
				WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,episodeVO);
				return EVAL_BODY_INCLUDE;
			}
			else
			{ objStatus.add(Status.CUSTOM,"","NO open episode found"); 
				return SKIP_BODY;
			}
	   }
	   if(isEmg.equalsIgnoreCase("false")){
		    PatientVO patVO=(PatientVO)WebUTIL.getSession(request).getAttribute(RegistrationConfig.PATIENT_VO);
		    System.out.println("inside isEmgOldVisitEligible episodes"+patVO.getPatFirstName());
		   	System.out.println("inside setopen episodes");			
		   	EpisodeVO episodeVO=delegate.getEmgOpenEpisode(patVO,getUserVO(request));
		   	if(episodeVO==null){			
			   return EVAL_BODY_INCLUDE;			   
		   	}
		   	else{
		   		objStatus.add(Status.CUSTOM,"","Patients episode in emergency already open"); 
		   		return SKIP_BODY;
		   	}
	   }
	   
	   if(isOpd.equalsIgnoreCase("true")){		  
		   try {
			PatientVO patVO=(PatientVO)WebUTIL.getSession(request).getAttribute(RegistrationConfig.PATIENT_VO);
			   System.out.println("inside  episodes"+patVO.getPatFirstName());
			   System.out.println("inside setopen episodes");			
			   EpisodeVO[] episodeVO=delegate.searchPatientEpiosdeByCrNo(patVO,getUserVO(request),RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
			   WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,episodeVO);
			   return EVAL_BODY_INCLUDE;
		} 
		   catch (HisRecordNotFoundException e) {
			objStatus.add(Status.CUSTOM,"","NO Open episodes in OPD found");		
		}	   	
	   }
	   }//end of try
	   
	   catch (HisMlcAlreadyExistException e){
	   		System.out.println("Patient Not Eligible For MLC");
		  		objStatus.add(Status.CUSTOM,"","Patient Record For open Emergency Episode already exist");	     	  
		      }
	   
	 
	   catch (HisRecordNotFoundException e){
	   		System.out.println("Patient Not Eligible For MLC");
		  		objStatus.add(Status.CUSTOM,"","Patient Record For open Emergency Episode already exist");	     	  
		      }
	   catch (HisNotEligibleForMLCException e){
   		System.out.println("Patient Not Eligible For MLC");
	  		objStatus.add(Status.CUSTOM,"","Patient Not Eligible For MLC");	     	  
	      }
	  	catch(HisException e){
	  		System.out.println("Inside Exception");
	  		//objStatus.add(Status.UNSUCESSFULL,"","Error::");		  	
	  		return SKIP_BODY;		
	  	}	  	
	  	finally{
	  		WebUTIL.setStatus(request, objStatus);  		
	  	    		  		  		
	  	} 			  	
		return SKIP_BODY;	
	}
	
	private UserVO getUserVO(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIsMlcEligible() {
		return isMlcEligible;
	}

	public void setIsMlcEligible(String isMlcEligible) {
		this.isMlcEligible = isMlcEligible;
	}

	public String getIsEmg() {
		return isEmg;
	}

	public void setIsEmg(String isEmg) {
		this.isEmg = isEmg;
	}

	public String getIsOpd() {
		return isOpd;
	}

	public void setIsOpd(String isOpd) {
		this.isOpd = isOpd;
	}

}
