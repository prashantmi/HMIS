package mortuary.transaction.delegate;

import java.util.List;
import java.util.Map;

import hisglobal.business.Delegate;
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
import mortuary.transaction.bo.MortuaryEssBO;
import mortuary.transaction.bo.MortuaryEssBOi;

public class MortuaryEssDelegate extends Delegate 
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public MortuaryEssDelegate() 
	{
		super(new MortuaryEssBO()); // /<<Setting the service provider
	}
	
	
	/** Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public List getPeonList(String deptCode,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPeonList(deptCode,userVO);
	}
	
	
	/** Getting Essential Data
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public Map getDeceasedEssential(String deptCode,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getDeceasedEssential(deptCode,userVO);
	}
	
	 
	/** Getting Deceased Address
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPatAddress(crNo,userVO);
	}
	
	
	/** Getting Police Verification Detail
	 * @param mlcNo
	 * @param userVO
	 * @return
	 */
	public PoliceVerificationDtlVO getPoliceVerificationDetail(String mlcNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPoliceVerificationDetail(mlcNo,userVO); 
	}
	
	
	/** Getting Deceased Existing Image
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatientImageDtlVO[] getDeceasedExistingImage(String crNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getDeceasedExistingImage(crNo,userVO);
	}
	
	public Map getDeceasedEssentialStorageDetail(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getDeceasedEssentialStorageDetail(userVO);
	}
	
	public List getAllRelationship(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getAllRelationship(userVO);
	}
	
	public DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getExistingRelative(deceasedNo,userVO);
	}
	
	public Map getEssentialForPostmortemRequest(String deceasedNo, UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForPostmortemRequest(deceasedNo,userVO);
	}
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemEntry(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getDeceasedListForPostmortemEntry(userVO);
	}
	
	public Map getPostmortemConductedEssential(String dob, String postmortemId, UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPostmortemConductedEssential(dob, postmortemId, userVO);
	}
	
	public boolean checkExistingRecord(String postmoretmId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.checkExistingRecord(postmoretmId,userVO);
	}
	
	public PostmortemItemReqDtlVO[] getItemToBePreserved(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getItemToBePreserved(postmortemId,userVO);
	}
	
	public boolean checkBodyIdentified(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.checkBodyIdentified(deceasedNo,userVO);
	}
	
	public MortuaryDeceasedImageDtlVO[] getExistingPhoto(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getExistingPhoto(deceasedNo,userVO);
	}
	
	public Map getEssentialForInjuries(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForInjuries(userVO);
	}
	
	public PatientDeathDetailVO getExistingInjuryDetail(String crNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getExistingInjuryDetail(crNo,userVO);
	}
	
	public boolean getGeneralAppearanceExsistance(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getGeneralAppearanceExsistance(postmortemId,userVO);
	}
	
	public PostmortemDetailVO getAddedInjuryDetailToDisplay(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getAddedInjuryDetailToDisplay(postmortemId,userVO);
	}
	
	public DeceasedIdentityDtlVO[] getIdentifiedByDetail(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getIdentifiedByDetail(deceasedNo,userVO);
	}
	
	public PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getExistingGeneralAppearance(postmortemId,userVO);
	}
	
	public String getPatientIdMark(String crNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPatientIdMark(crNo,userVO);
	}
	
	public Map getEssentialForTeamDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForTeamDetail(postmortemId,userVO);
	}
	
	public PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getAddedOpinion(postmortemId,userVO);
	}
	
	public List getChamberBasedOnMortuaryArea(String mortuaryArea,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getChamberBasedOnMortuaryArea(mortuaryArea,userVO);
	}
	
	public List getAreaBasedOnMortuary(String mortuary,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getAreaBasedOnMortuary(mortuary,userVO);
	}
	
	public Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getTemplateDetail(postmortemId,userVO);
	}
	
	public PostmortemRequestDetailVO[] getPostmortemReqList(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPostmortemReqList(userVO);
	}
	
	public PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPostmortemRequestDetail(postmortemId,userVO);
	}
	
	public Map getEssentialForPostmortemReqApproved(String deceasedNo,String consentStatus,String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForPostmortemReqApproved(deceasedNo,consentStatus,postmortemId,userVO);
	}
	
	public Map getPreservativeList(String postmortemId, UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPreservativeList(postmortemId,userVO);
	}
	

	public Map getEssentialForExternalBodyAcceptance(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForExternalBodyAcceptance(userVO);
	}
	

	public Map fetchPoliceVeriDetails(String postmortemId,String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.fetchPoliceVeriDetails(postmortemId,deceasedNo,userVO);
	}
	
	public Map getEssentialForSampleSend(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForSampleSend(postmortemId,userVO);
	}
	
	public Map getAllRequestedIdDetailNDeceasedNo(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getAllRequestedIdDetailNDeceasedNo(postmortemId,userVO);
	}
	
	public Map getAllSampleNInvestigationRequestDetail(String requestId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getAllSampleNInvestigationRequestDetail(requestId,userVO);
	}
	
	public Map checkForDeceasedHandover(String deceasedNo,String postmortemReq,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.checkForDeceasedHandover(deceasedNo,postmortemReq,userVO);
	}
	
	public Map getEssentialForUnknown(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForUnknown(deceasedNo,userVO);
	}
	
	public DeceasedDetailVO[] getPostmortemReadyToHandover(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getPostmortemReadyToHandover(userVO);
	}
	
	public Map getEssentialForPostmortemHandover(UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getEssentialForPostmortemHandover(userVO);
	}

	public DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getDeceasedGeneralAppearance(deceasedNo,userVO);
	}
	
	public DeceasedDetailVO[] searchDeceasedNo(String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.searchDeceasedNo(fName,mName,lName,deathDate,userVO);
	}
	
	public DeceasedDetailVO[] searchPostmortemNo(String decNo,String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.searchPostmortemNo(decNo, fName,mName,lName,deathDate,userVO);
	}
	
	public MortuaryDeceasedImageDtlVO getDedeasedDefaultImage(String deceasedNo,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getDedeasedDefaultImage(deceasedNo,userVO);
	}
	
	public MortuaryExtLabRequestDtlVO[] getSampleResult(String postmortemId,UserVO userVO)
	{
		MortuaryEssBOi serviceBO=(MortuaryEssBOi)super.getServiceProvider();
		return serviceBO.getSampleResult(postmortemId,userVO);
	}
}
