package mrd.transaction.dao;

import hisglobal.vo.AdmissionAdviceVO;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;

import mrd.vo.CommonCaseSheetEnquiryVO;

public interface MrdDAOi 
{
	/** Getting the List of Rest Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public EpisodeRestAdviceVO[] getEpisodeRestAdvice(String crNo,String epiCode,UserVO userVO);
	
	/** Getting the List of All Visits of the patient on a Particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public EpisodeVO[] getAllVisitOfEpisodePat(String crNo,String epiCode,UserVO userVO);
	
	/** Getting the List of Admission Advice Given to the Patient on a Particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public AdmissionAdviceVO[] getAdmissionAdvice(String crNo,String epiCode,UserVO userVO);
	
	/** Getting The List of Certificate For Movement By Unit 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateForMoveBydUnit(String unitCode,UserVO userVO);
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateForMoveByCrNo(String crNo,UserVO userVO);
	
	public EpisodeRestAdviceVO[] getEpisodeRestAdviceTodayVisited(String crNo,String epiCode,UserVO userVO);
	
	public List<CaseSheetDtlVO> getCaseSheetListToReady(String unitCode,String wardCode,UserVO userVO);
	
	//added by swati on date:01-may-2019
	public List<CaseSheetDtlVO> getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO);
	
	//added by swati on date:02-may-2019
	public List<CaseSheetDtlVO> getCaseSheetListToReadyCRNOWISE(String crno,UserVO userVO);
	
	public PatientDetailVO getDischargePatientDtlByCrNo(String patCrNo,UserVO userVO);
	
	public RecordTypeWiseEnclosureMstVO[] getAllEnclosureDetails(String recordType,UserVO userVO);
	
	public RecordTypeCheckListMstVO[] getAllChecklistDetails(String recordType,String checklistMode, UserVO userVO);
	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtlByCrNo(String patCrNo,UserVO userVO);
	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtl(String deptUnitCode,String ward,UserVO userVO);
	
	public RecordDispatchDtlVO[] getRecordListByUnitWard(RecordDispatchDtlVO recordDispatchVO,UserVO userVO);
	//ADDED BY SWATI  ON DATE:06-MAY-2019
			//ADM NO WISE DTL
	public RecordDispatchDtlVO[] getCaseSheetListToReadyADMNOWISE2(String admno,UserVO userVO);
	
	
	//ADDED BY SWATI  ON DATE:07-MAY-2019
	//CR NO WISE DTL
public RecordDispatchDtlVO[] getCaseSheetListToReadyCRNOWISE2(String crno,UserVO userVO);

	public RecordTypeCheckListMstVO[] getChecklistByRecordType(String checklistMode,String recordType,UserVO userVO);
	
	public RecordDispatchDtlVO[] getRecordListByPatCrNo(RecordDispatchDtlVO recordDispatchVO,UserVO userVO);
	
	public List getEmployeeListForHandover(String processId,String departmentUnitCode,String wardCode,UserVO userVO);
	
	public CommonCaseSheetEnquiryVO[] searchRecord(HashMap _finalQueryMap,UserVO userVO);
	
	public List<MrdRecordDtlVO> getMrdRecordStatusDetail(String mrdRecordId,String requestId,UserVO userVO);
	
	public MrdRecordDtlVO[] searchLostRecord(HashMap _finalQueryMap,UserVO userVO);
	
	public CommonCaseSheetEnquiryVO[] searchOpdFileRecord(HashMap _finalQueryMap,UserVO userVO);

	public MrdRecordRequestDtlVO checkUserIsEmp(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);
	
	public RecordDispatchDtlVO[] getRecordListByPatAdmNo(RecordDispatchDtlVO recordDispatchVO,UserVO userVO);
	
}
