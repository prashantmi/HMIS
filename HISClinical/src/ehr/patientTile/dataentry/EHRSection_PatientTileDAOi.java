package ehr.patientTile.dataentry;

import java.util.List;

import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public interface EHRSection_PatientTileDAOi 
{
	public PatientDetailVO getIpdPatientDtlByCrNo(PatientDetailVO patDtlVO,UserVO userVO);
	
	//Checking the Patient Admission Status 
	public String checkPatientAdmStatus(PatientDetailVO patDtlVO,UserVO userVO);
	
	//Getting OPD Patient Detail By Cr No
	public PatientDetailVO getOpdPatientDtlByCrNo(PatientDetailVO patDtlVO,UserVO userVO);
	
	// Checking whether the Patient is Admitted or Not
	public boolean checkPatAdmitted(PatientDetailVO patDtlVO,UserVO userVO);

	public List getPatientStatus(UserVO userVO);
	
	public boolean checkPatientStatus(String admNo,UserVO userVO);
	
	public void updatePatStatusForDischargeReq(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
	
	public void updatePatStatusForDischargeRevoke(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
	
	public boolean GetBillAccStatus(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
	
	public void reopenPatBillingForDischargeRevoke(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
	
	public PatientDetailVO getIpdDiscPatientDtlByCrNo(PatientDetailVO patDtlVO,UserVO userVO);
	
	
	
}
