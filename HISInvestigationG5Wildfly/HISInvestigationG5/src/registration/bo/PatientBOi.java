package registration.bo;

import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ChangeCategoryVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeDeathVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.FileNumberChangeVO;
import hisglobal.vo.MLCRevokeEpisodeDetailVO;
import hisglobal.vo.MlcDocUploadDtlVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientParichitVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.RegCardPrintVO;
import hisglobal.vo.RegChargeDtlVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UnitChangeVO;
import hisglobal.vo.UnknownChangeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.VerificationDocumentVO;
import hisglobal.vo.PatientAdhaarDtlVO;
import hisglobal.vo.MCTSRegistrationVO;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.RegistrationSlip;
import registration.vo.BPLDetailsVO;

/**
 * PatientBOi is an interface that declares the methods associated with all the transactions of registration.
 * 
 * @author AHIS
 */
public interface PatientBOi
{
	
	 
	
	
	
	
	public PatientVO[] searchPatientByName(String _patientName, UserVO _userVO);
	public PatientVO[] searchPatientByContactNo(String contactNo, UserVO _userVO);
	public PatientVO[] searchPatientByEmployeeID(String employeeID, UserVO _userVO);
	
	public PatientVO[] searchPatientByNationalID(String nationalID, UserVO _userVO);
	public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO);


}
