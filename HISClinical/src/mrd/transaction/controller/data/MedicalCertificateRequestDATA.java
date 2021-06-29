/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	Medical Certificate Request
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	19-November-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.bo.MrdBO;
import mrd.transaction.bo.MrdEssentialBO;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class MedicalCertificateRequestDATA extends ControllerDATA
{
	/**  Getting The List of All The Episodes of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */

	public static Map<String, Object> getEssentials(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		MrdEssentialBO mrdEssentialBO= new MrdEssentialBO();
		return mrdEssentialBO.getEssentials(patMedicalDtlVO, userVO);
	}

	public static String getPatDiagnosisList(PatMedicalDtlVO patMedicalDtlVO,	UserVO userVO) 
	{
		MrdEssentialBO mrdEssentialBO= new MrdEssentialBO();
		return mrdEssentialBO.getPatDiagnosisList(patMedicalDtlVO, userVO);
	}

	public static void saveMedicalCertificateRequest(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) 
	{
		MrdBO mrdBO= new MrdBO();
		mrdBO.saveMedicalCertificateRequest(patMedicalDtlVO, userVO);
	}
	
}
