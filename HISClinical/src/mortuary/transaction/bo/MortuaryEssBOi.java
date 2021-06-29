package mortuary.transaction.bo;

import hisglobal.vo.AddressVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface MortuaryEssBOi 
{
	
	/** Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public List getPeonList(String deptCode,UserVO userVO);
	
	
	/** Getting Essential Data
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public Map getDeceasedEssential(String deptCode,UserVO userVO);
	
	
	/** Getting Deceased Address
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public AddressVO getPatAddress(String crNo,UserVO userVO);
	
	
	/** Getting Police Verification Detail
	 * @param mlcNo
	 * @param userVO
	 * @return
	 */
	public PoliceVerificationDtlVO getPoliceVerificationDetail(String mlcNo,UserVO userVO);
	
	
	/** Getting Deceased Existing Image
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatientImageDtlVO[] getDeceasedExistingImage(String crNo,UserVO userVO);
	
	
	public Map getDeceasedEssentialStorageDetail(UserVO userVO);
	
	public List getAllRelationship(UserVO userVO);
	
	public DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO);
	
	public Map getEssentialForPostmortemRequest(String deceasedNo, UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemEntry(UserVO userVO);
	
	public Map getPostmortemConductedEssential(String dob, String postmortemId, UserVO userVO);
	
	public boolean checkExistingRecord(String postmoretmId,UserVO userVO);
	
	public PostmortemItemReqDtlVO[] getItemToBePreserved(String postmortemId,UserVO userVO);
	
	public boolean checkBodyIdentified(String deceasedNo,UserVO userVO);
	
	public MortuaryDeceasedImageDtlVO[] getExistingPhoto(String deceasedNo,UserVO userVO);
	
	public Map getEssentialForInjuries(UserVO userVO);
	
	public PatientDeathDetailVO getExistingInjuryDetail(String crNo,UserVO userVO);
	
	public boolean getGeneralAppearanceExsistance(String postmortemId,UserVO userVO);
	
	public PostmortemDetailVO getAddedInjuryDetailToDisplay(String postmortemId,UserVO userVO);
	
	public DeceasedIdentityDtlVO[] getIdentifiedByDetail(String deceasedNo,UserVO userVO);
	
	public PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO);
	
	public String getPatientIdMark(String crNo,UserVO userVO);
	
	public Map getEssentialForTeamDetail(String postmortemId,UserVO userVO);
	
	public PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO);
	
	public List getChamberBasedOnMortuaryArea(String mortuaryArea,UserVO userVO);
	
	public List getAreaBasedOnMortuary(String mortuary,UserVO userVO);
	
	public Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO);
	
	public PostmortemRequestDetailVO[] getPostmortemReqList(UserVO userVO);
	
	public PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO);
	
	public Map getEssentialForPostmortemReqApproved(String deceasedNo,String consentStatus,String postmortemId,UserVO userVO);
	
	public Map getPreservativeList(String postmortemId, UserVO userVO);
	

	public Map getEssentialForExternalBodyAcceptance(UserVO userVO);

	public Map fetchPoliceVeriDetails(String postmortemId,String deceasedNo,UserVO userVO);
	
	public Map getEssentialForSampleSend(String postmortemId,UserVO userVO);
	
	public Map getAllRequestedIdDetailNDeceasedNo(String postmortemId,UserVO userVO);
	
	public Map getAllSampleNInvestigationRequestDetail(String requestId,UserVO userVO);
	
	public Map checkForDeceasedHandover(String deceasedNo,String postmortemReq,UserVO userVO);
	
	public Map getEssentialForUnknown(String deceasedNo,UserVO userVO);
	
	public DeceasedDetailVO[] getPostmortemReadyToHandover(UserVO userVO);
	
	public Map getEssentialForPostmortemHandover(UserVO userVO);
	
	public DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO);
	
	public DeceasedDetailVO[] searchDeceasedNo(String fName,String mName,String lName,String deathDate,UserVO userVO);
	
	public DeceasedDetailVO[] searchPostmortemNo(String decNo, String fName,String mName,String lName,String deathDate,UserVO userVO);
	
	public MortuaryDeceasedImageDtlVO getDedeasedDefaultImage(String deceasedNo,UserVO userVO);
	
	public MortuaryExtLabRequestDtlVO[] getSampleResult(String postmortemId,UserVO userVO);
	

}
