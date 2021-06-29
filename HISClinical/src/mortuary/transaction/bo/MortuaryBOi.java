package mortuary.transaction.bo;

import java.util.List;

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

public interface MortuaryBOi 
{
	 
	/** Getting The List of Dead Patient Send To Mortuary
	 * @param userVO
	 * @return
	 */
	public PatientDeathDetailVO[] getInHouseDeadPatientList(UserVO userVO);
	
	
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
	public String saveDeceasedDetail(MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO,MortuaryPoliceVerificationVO policeVerVO,DeceasedBroughtByDtlVO deceasedBroughtVO,DeceasedDetailVO deceasedDtlVO,DeceasedHandoverDtlVO deceasedHandoverVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListForStorage(UserVO userVO);
	
	public void saveDeceasedStorageDetail(DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListToHandover(UserVO userVO);
	
	public void saveDeceasedHandoverDetail(String bodyStatus, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedHandoverDtlVO deceasedHandoverVO,UserVO userVO);
	
	public String getChamberRackName(String deceasedNo,UserVO userVO);
	
	public PatientDeathDetailVO getDeceasedHandoverDetail(String crNo,UserVO userVO);
	
	public String saveOfflineDeceasedStorageDetail(DeceasedDetailVO deceasedDtlVO, DeceasedBroughtByDtlVO deceasedBroughtVO, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemRequest(UserVO userVO);
	
	public String savePostmortemRequest(MortuaryPoliceVerificationVO postmortemPoliceVerVO, PostmortemRequestDetailVO postmortemRequestDtlVO,List<PostmortemItemReqDtlVO> lstItemVO, List<PostmortemOpinionReqDtlVO> lstOpinionVO, List<DeceasedRelativeDtlVO> lstRelativeVO, UserVO userVO);
	
	public void saveGeneralAppearance(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl, PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	public DeceasedDetailVO getDeceasedDetailByDeceasedNo(DeceasedDetailVO deceasedVO,UserVO userVO);
	
	public String getMlcNo(String deceasedNo,String crNo,UserVO userVO);
	
	public void saveItemToBePreserved(List<PostmortemItemDtlVO> lstItem, UserVO userVO);
	
	public void saveBodyIdentificationDetail(DeceasedRelativeDtlVO[] arrRelativeVO, DeceasedIdentityDtlVO[] arrIdentityDtlVO, UserVO userVO);
	
	public void saveUploadedPhoto(String deceasedNo, String srNo,MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO, UserVO userVO);
	
	public void saveInjuriesDetail(PostmortemDetailVO postmortemInjuryVO, UserVO userVO);
	
	public DeceasedDetailVO[] getUnknownBodyList(UserVO userVO);
	
	public void saveTeamDetail(List<PostmortemTeamDetailVO> lstTeamModify,List<PostmortemTeamDetailVO> lstTeamAdd,UserVO userVO);
	
	public void updateGeneralAppearance(List postMortemExamVOList,List<PostmortemOpinionDetailVO> lstOpinionMod, List<PostmortemOpinionDetailVO> lstOpinionDtl, PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	public DeceasedDetailVO[] getFinalOpinionToBeApproved(UserVO userVO);
	
	public void saveApprovedFinalOpinion(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl,PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	public DeceasedDetailVO[] getWaveoffDetails(UserVO userVO);

	public void approvedPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO,List<PostmortemItemReqDtlVO> lstItemAdd,List<PostmortemItemReqDtlVO> lstItemRevoke,List<PostmortemOpinionReqDtlVO> lstOpinionAdd,List<PostmortemOpinionReqDtlVO> lstOpinionRevoke,List<PostmortemTeamDetailVO> lstTeam, UserVO userVO);
	
	public void cancelledPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO, UserVO userVO);
	

	public String saveExternalDeceasedAcceptance(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO);
	
	public String saveOnSpotDeceasedAcceptance(DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedDetailVO deceasedDtlVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO);

	public void savePostMortemWaveoffDetail(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO,UserVO userVO);

	public List getPatientImageDtlByDeceasedNo(String deceasedNo, UserVO userVO);

	public void saveDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO, MortuaryDeceasedImageDtlVO[] deceasedImageUpdateVO, UserVO userVO);


	public void deleteDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO, UserVO userVO);


	public void saveSampleSendDetail(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtReqSampleDtlVO> lstSample,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,UserVO userVO);
	
	public void saveSampleResultEntry(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,List<MortuaryExtReqSampleDtlVO> lstSample,UserVO userVO);
	
	public void saveUnknownBodyIdentificationDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO);
	
	public void saveDeceasedClaimedDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO);
	
	public void savePostmortemHandoverDetail(PostmortemHandoverDtlVO postmortemHandoverDtlVO, UserVO userVO);

	
	public void saveDeceasedGeneralAppearance(DeceasedDetailVO deceasedDtlVO,UserVO userVO);
	
	public DeceasedHandoverDtlVO getDeceasedDtlAfterHandover(String deceasedNo,UserVO userVO);
	
	//added for the purpose of duplicate MLC Check at External Deceased Entry By Shruti Shail on 11-May-2017
	public Boolean chkDuplicateMLC(String mlcNo,String HospCode);
	
}
