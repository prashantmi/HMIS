package hisglobal.tools.hlp;

import hisglobal.exceptions.HisException;


public class PatientDtlBO {

	  public void getPatientDetails(GlobalVO voObj)
	  {
	    PatientDtlDAO.setPatientDtl(voObj);
		if(voObj.getStrMsgType().equals("1"))
		{
			  new HisException("Global Patient Details","PatientDtlBO.getPatientDetails() -->",voObj.getStrMsgString());			  
		}
		  
	  }	
	  public void getAdmissionDetails(GlobalVO voObj)
	  {
		    PatientDtlDAO.setAdmissionDtl(voObj);		  
		    if(voObj.getStrMsgType().equals("1"))
		    {
				  new HisException("HisGlobal","PatientDtlBO.getAdmissionDetails() -->",voObj.getStrMsgString());			  
			}
	  }
//added by shefali garg on 26-Aug-2014 for patient treatment detail in issu eto patient
	  public void getPatientTreatmentDetails(GlobalVO voObj)
	  {
	   		
		  PatientDtlDAO.setPatientTreatmentDtl(voObj);
		  if(voObj.getStrMsgType().equals("1"))
		  {
		   new HisException("Global Patient Details","PatientDtlBO.getPatientTreatmentDetails() -->",voObj.getStrMsgString()); 
		  }
		  
	  }
	  public void getPatientDetailsWithoutCateXpiryCheck(GlobalVO voObj)
	  {
	    PatientDtlDAO.setPatientDtlWithoutCatExpiryCheck(voObj);
		if(voObj.getStrMsgType().equals("1"))
		{
			  new HisException("Global Patient Details","PatientDtlBO.getPatientDetails() -->",voObj.getStrMsgString());			  
		}
		  
	  }	
	  public void getAdmissionDetailsView(GlobalVO voObj){
	   		
		  PatientDtlDAO.setAdmissionDtlView(voObj);
		  
 if(voObj.getStrMsgType().equals("1")){
			  
			  new HisException(
						"Global Patient Details",
						"PatientDtlBO.getAdmissionDetails() -->",
						voObj.getStrMsgString());
			  
		  }
		  
		}
	
	  
}
