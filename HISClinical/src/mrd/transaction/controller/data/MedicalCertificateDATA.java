
/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg 
 ## Module Name					: MRD
 ## Process/Database Object Name: Medical and Fitness certificate issue process
 ## Purpose						: Medical and Fitness certificate issue process
 ## Date of Creation			: 
 ## Modification Log			:				
 ##		Modify Date				: 04-Dec-2014 
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: Amit Garg

*/





package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.bo.MrdEssentialBO;
import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AdmissionAdviceVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;

public class MedicalCertificateDATA extends ControllerDATA
{
	/**  Getting The List of All The Episodes of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllEpisodeOfThePatient(crNo,userVO);
	}
	 
	/** Getting the List of Admission Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public static AdmissionAdviceVO[] getAdmissionAdvice(String crNo,String epiCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAdmissionAdvice(crNo,epiCode,userVO);
	}
	 
	/** Getting the List of Rest Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public static EpisodeRestAdviceVO[] getEpisodeRestAdvice(String crNo,String epiCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getEpisodeRestAdvice(crNo,epiCode,userVO);
	}
	
	/** Getting the List of Diagnosis Given to the Patient on a Particular Episode 
	 * @param diagCodeType
	 * @param epiRestAdviceVO
	 * @param userVO
	 * @return
	 */
	public static List getPatDiagnosisList(String diagCodeType,EpisodeRestAdviceVO epiRestAdviceVO,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getPatDiagnosisList(diagCodeType,epiRestAdviceVO,userVO);
	}
	
	/** Saving the Medical Certificate Details 
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public static void saveOnBasisRestAdvice(PatMedicalDtlVO patMedicalDtlVO,String restFlag,String unitCode,String genMode, UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveOnBasisRestAdvice(patMedicalDtlVO,restFlag,unitCode,genMode,userVO);
	}
	
	/** Getting the List of All Visits of the patient on a Particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public static EpisodeVO[] getAllVisitOfEpisodePat(String crNo,String epiCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllVisitOfEpisodePat(crNo,epiCode,userVO);
	}
	
	/** Getting the List of All the Consultant Name
	 * @param userVO
	 * @return
	 */
	public static List getAllConsultantForMC(String unitCode,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getAllConsultantForMC(unitCode,userVO);
	}
	
	/**   Getting the List of Medical Certificate Generated for the Patient on a particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public static PatMedicalDtlVO[] getIssuedMedicalCertificate(String crNo,String epiCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getIssuedMedicalCertificate(crNo,epiCode,userVO);
	}
	
	/** Saving The Fitness Date
	 * @param patFitnessDtlVO
	 * @param unitCode
	 * @param genMode
	 * @param userVO
	 */
	public static void saveFitnessDate(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveFitnessDate(patFitnessDtlVO,unitCode,genMode, userVO);
	}
	
	/** Saving the Change Duration Information of the Medical Certificate
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public static void saveExtendMedicalCertificate(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveExtendMedicalCertificate(patMedicalDtlVO, userVO);
	}
	
	public static EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllEpisodeOfThePatientTodayVisited(crNo,userVO);
	}
	
	public static EpisodeRestAdviceVO[] getEpisodeRestAdviceTodayVisited(String crNo,String epiCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getEpisodeRestAdviceTodayVisited(crNo,epiCode,userVO);
	}
	
	public static void saveFitnessDateNExtendMC(PatMedicalDtlVO patMedicalDtlVO,PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveFitnessDateNExtendMC(patMedicalDtlVO,patFitnessDtlVO,unitCode,genMode, userVO);
	}
	
	public static String getEmpMaxDaysOnline(UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getEmpMaxDaysOnline(userVO);
	}
	public static Map getMedicalCertificatePatReqList(UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return (serviceBO.getMedicalCertificatePatReqList(UserVO));
	}
	public static Map getMedicalCertificateIssuePatDtl(PatMedicalDtlVO patMedicalDtlVO, UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return (serviceBO.getMedicalCertificateIssuePatDtl(patMedicalDtlVO,UserVO));
	}
	public static String getBillNoQtyDtl(PatMedicalDtlVO patMedicalDtlVO, UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return (serviceBO.getBillNoQtyDtl(patMedicalDtlVO,UserVO));
	}
	public static void saveMedicalCertificateIssueDtl(PatMedicalDtlVO patMedicalDtlVO, UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();
		serviceBO.saveMedicalCertificateIssueDtl(patMedicalDtlVO,UserVO);
	}
	
	
}
