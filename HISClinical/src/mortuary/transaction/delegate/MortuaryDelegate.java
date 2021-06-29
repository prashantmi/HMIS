package mortuary.transaction.delegate;

import java.util.List;
import java.util.Map;

import mortuary.transaction.bo.MortuaryBO;
import mortuary.transaction.bo.MortuaryBOi;
import mortuary.transaction.bo.MortuaryEssBOi;
import hisglobal.business.Delegate;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemHandoverDtlVO;
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

public class MortuaryDelegate extends Delegate
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public MortuaryDelegate() 
	{
		super(new MortuaryBO()); // /<<Setting the service provider
	}
	
	 
	/** Getting The List of Dead Patient Send To Mortuary
	 * @param userVO
	 * @return
	 */
	public PatientDeathDetailVO[] getInHouseDeadPatientList(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getInHouseDeadPatientList(userVO);
	}
	
	
	/** Saving Deceased Detail
	 * @param deceasedImageDtlVO
	 * @param policeVerVO
	 * @param deceasedBroughtVO
	 * @param deceasedDtlVO
	 * @param deceasedHandoverVO
	 * @param deceasedRelativeVO
	 * @param deceasedStorageVO
	 * @param userVO
	 */
	public String saveDeceasedDetail(MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO,MortuaryPoliceVerificationVO policeVerVO,DeceasedBroughtByDtlVO deceasedBroughtVO,DeceasedDetailVO deceasedDtlVO,DeceasedHandoverDtlVO deceasedHandoverVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.saveDeceasedDetail(deceasedImageDtlVO,policeVerVO,deceasedBroughtVO,deceasedDtlVO,deceasedHandoverVO,deceasedRelativeVO,deceasedStorageVO,userVO);
	}

	public DeceasedDetailVO[] getDeceasedListForStorage(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedListForStorage(userVO);
	}
	
	public void saveDeceasedStorageDetail(DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveDeceasedStorageDetail(deceasedRelativeVO,deceasedStorageVO,userVO);
	}
	
	public DeceasedDetailVO[] getDeceasedListToHandover(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedListToHandover(userVO);
	}
	
	public void saveDeceasedHandoverDetail(String bodyStatus, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedHandoverDtlVO deceasedHandoverVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveDeceasedHandoverDetail(bodyStatus,deceasedRelativeVO, deceasedHandoverVO,userVO);
	}
	
	public String getChamberRackName(String deceasedNo,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getChamberRackName(deceasedNo,userVO);
	}
	
	public PatientDeathDetailVO getDeceasedHandoverDetail(String crNo,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedHandoverDetail(crNo,userVO);
	}
	
	public String saveOfflineDeceasedStorageDetail(DeceasedDetailVO deceasedDtlVO, DeceasedBroughtByDtlVO deceasedBroughtVO, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.saveOfflineDeceasedStorageDetail(deceasedDtlVO,deceasedBroughtVO, deceasedRelativeVO,deceasedStorageVO,userVO);
	}
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemRequest(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedListForPostmortemRequest(userVO);
	}
	
	public String savePostmortemRequest(MortuaryPoliceVerificationVO postmortemPoliceVerVO, PostmortemRequestDetailVO postmortemRequestDtlVO,List<PostmortemItemReqDtlVO> lstItemVO, List<PostmortemOpinionReqDtlVO> lstOpinionVO, List<DeceasedRelativeDtlVO> lstRelativeVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.savePostmortemRequest(postmortemPoliceVerVO, postmortemRequestDtlVO, lstItemVO, lstOpinionVO, lstRelativeVO, userVO);
	}
	
	public void saveGeneralAppearance(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl, PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveGeneralAppearance(postMortemExamVOList,lstOpinionDtl, postmortemDtlVO,userVO);
	}
	
	public DeceasedDetailVO getDeceasedDetailByDeceasedNo(DeceasedDetailVO deceasedVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedDetailByDeceasedNo(deceasedVO,userVO);
	}
	
	public String getMlcNo(String deceasedNo,String crNo,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getMlcNo(deceasedNo,crNo,userVO);
	}
	
	public void saveItemToBePreserved(List<PostmortemItemDtlVO> lstItem, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveItemToBePreserved(lstItem,userVO);
	}
	
	public void saveBodyIdentificationDetail(DeceasedRelativeDtlVO[] arrRelativeVO, DeceasedIdentityDtlVO[] arrIdentityDtlVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveBodyIdentificationDetail(arrRelativeVO,arrIdentityDtlVO,userVO);
	}
	
	public void saveUploadedPhoto(String deceasedNo, String srNo,MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveUploadedPhoto(deceasedNo, srNo, deceasedImageDtlVO,userVO);
	}
	
	public void saveInjuriesDetail(PostmortemDetailVO postmortemInjuryVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveInjuriesDetail(postmortemInjuryVO,userVO);
	}
	
	public DeceasedDetailVO[] getUnknownBodyList(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getUnknownBodyList(userVO);
	}
	
	public void saveTeamDetail(List<PostmortemTeamDetailVO> lstTeamModify,List<PostmortemTeamDetailVO> lstTeamAdd,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveTeamDetail(lstTeamModify,lstTeamAdd,userVO);
	}
	
	public void updateGeneralAppearance(List postMortemExamVOList,List<PostmortemOpinionDetailVO> lstOpinionMod, List<PostmortemOpinionDetailVO> lstOpinionDtl, PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.updateGeneralAppearance(postMortemExamVOList,lstOpinionMod, lstOpinionDtl, postmortemDtlVO,userVO);
	}
	
	public DeceasedDetailVO[] getFinalOpinionToBeApproved(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getFinalOpinionToBeApproved(userVO);
	}
	
	public void saveApprovedFinalOpinion(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl,PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveApprovedFinalOpinion(postMortemExamVOList,lstOpinionDtl, postmortemDtlVO,userVO);
	}
	

	public DeceasedDetailVO[] getWaveoffDetails(UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getWaveoffDetails(userVO);
	}

	public void approvedPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO,List<PostmortemItemReqDtlVO> lstItemAdd,List<PostmortemItemReqDtlVO> lstItemRevoke,List<PostmortemOpinionReqDtlVO> lstOpinionAdd,List<PostmortemOpinionReqDtlVO> lstOpinionRevoke,List<PostmortemTeamDetailVO> lstTeam, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.approvedPostmortemRequest(postmortemReqVO,lstItemAdd,lstItemRevoke,lstOpinionAdd,lstOpinionRevoke,lstTeam, userVO);
	}
	
	public void cancelledPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.cancelledPostmortemRequest(postmortemReqVO,userVO);
	}

	
	public String saveExternalDeceasedAcceptance(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.saveExternalDeceasedAcceptance(deceasedDtlVO,deceasedRelativeVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,userVO);
	}
	
	public String saveOnSpotDeceasedAcceptance(DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedDetailVO deceasedDtlVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.saveOnSpotDeceasedAcceptance(deceasedRelativeVO,deceasedDtlVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,userVO);
	}
	


	public void savePostMortemWaveoffDetail(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.savePostMortemWaveoffDetail(postmortemWaveoffDtlVO,userVO);
	}


	public List getPatientImageDtlByDeceasedNo(String deceasedNo, UserVO userVO) {
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getPatientImageDtlByDeceasedNo(deceasedNo,userVO);
	}


	public void saveDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO,
			MortuaryDeceasedImageDtlVO[] deceasedImageUpdateVO, UserVO userVO) {
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveDeceasedImage(deceasedImageVO,deceasedImageUpdateVO,userVO);
		
	}


	public void deleteDeceasedImage(
			MortuaryDeceasedImageDtlVO[] deceasedImageVO, UserVO userVO) {
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.deleteDeceasedImage(deceasedImageVO,userVO);
	}
	
	public void saveSampleSendDetail(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtReqSampleDtlVO> lstSample,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveSampleSendDetail(extLabReqDtlVO,lstSample,lstInvestigation,userVO);
	}
	
	public void saveSampleResultEntry(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,List<MortuaryExtReqSampleDtlVO> lstSample,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveSampleResultEntry(extLabReqDtlVO,lstInvestigation,lstSample,userVO);
	}

	public void saveUnknownBodyIdentificationDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveUnknownBodyIdentificationDetail(deceasedDtlVO,relativeVO,identityDtlVO,userVO);
	}
	
	public void saveDeceasedClaimedDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveDeceasedClaimedDetail(deceasedDtlVO,relativeVO,identityDtlVO,userVO);
	}
	
	public void savePostmortemHandoverDetail(PostmortemHandoverDtlVO postmortemHandoverDtlVO, UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.savePostmortemHandoverDetail(postmortemHandoverDtlVO,userVO);
	}
	
	public void saveDeceasedGeneralAppearance(DeceasedDetailVO deceasedDtlVO,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		serviceBO.saveDeceasedGeneralAppearance(deceasedDtlVO,userVO);
	}
	

	public DeceasedHandoverDtlVO getDeceasedDtlAfterHandover(String deceasedNo,UserVO userVO)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedDtlAfterHandover(deceasedNo,userVO);
	}
	//added for the purpose of duplicate MLC Check at External Deceased Entry By Shruti Shail on 11-May-2017
	public Boolean chkDuplicateMLC(String mlcNo,String HospCode)
	{
		MortuaryBOi serviceBO=(MortuaryBOi)super.getServiceProvider();
		return serviceBO.chkDuplicateMLC(mlcNo,HospCode);
	}
	
}
