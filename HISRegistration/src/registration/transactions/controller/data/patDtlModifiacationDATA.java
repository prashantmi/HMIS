package registration.transactions.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import registration.bo.PatientBO;
import vo.registration.PatientVO;


public class patDtlModifiacationDATA extends ControllerDATA {

	
	/**
	 * gets  all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 * @param _UserVO Provides User details.
	 * @return 
	 */
	public static PatientVO[] getCRNoForModification(UserVO _UserVO,String episodeVisitType){	
		PatientBO  patientDelegate= new PatientBO();			
		return  patientDelegate.getCrNoForModification(_UserVO,episodeVisitType);
	    		
	}
	
}
	