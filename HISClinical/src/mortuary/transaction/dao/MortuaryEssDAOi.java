package mortuary.transaction.dao;

import java.util.List;

import hisglobal.vo.AddressVO;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.UserVO;

public interface MortuaryEssDAOi 
{
	 
	/** Getting The List of Dead Patient Send To Mortuary
	 * @param userVO
	 * @return
	 */
	public PatientDeathDetailVO[] getInHouseDeadPatientList(UserVO userVO);
	
	
	/** Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public List getEmployeeListDeptProcessWise(String processId,String deptCode,UserVO userVO);
	
	
	/** Getting Deceased Address
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public AddressVO getPatAddress(String crNo,UserVO userVO);
	
	
	/** Getting deceased Police Verification Detail
	 * @param mlcNo
	 * @param userVO
	 * @return
	 */
	public PoliceVerificationDtlVO getPoliceVerificationDetail(String mlcNo,UserVO userVO);
	
	
	/** Counting the Capacity of Rack
	 * @param rackId
	 * @param userVO
	 * @return
	 */
	public String countMaxCapacityOfRack(String rackId,UserVO userVO);
	
	
	/** Updating the Chamber Rack Status
	 * @param rackId
	 * @param userVO
	 */
	public void updateChamberStatus(String chamberStatus, String rackId,UserVO userVO);
	
	
	/** Getting Deceased Existing Image
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatientImageDtlVO[] getDeceasedExistingImage(String crNo,UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListInStreacher(UserVO userVO);
	
	public void updateBodyStatus(String bodyStatus, String deceasedNo,UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListToHandover(UserVO userVO);
	
	public void updateBodyStatusNIssueTime(String bodyStatus, String deceasedNo,UserVO userVO);
	
	public String getChamberRackName(String deceasedNo,UserVO userVO);
	
	public PatientDeathDetailVO getDeceasedHandoverDetail(String crNo,UserVO userVO);
	
	public DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemRequest(UserVO userVO);
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemEntry(UserVO userVO);
	

	public String getMortuaryName(String mortuaryCode,UserVO userVO);
	

	public PatientDeathDetailVO getExistingInjuryDetail(String crNo,UserVO userVO);
	
	public DeceasedDetailVO[] getUnknownBodyList(UserVO userVO);
	
	public String getPatientIdMark(String crNo,UserVO userVO);
	
	public List getTemplateIdForPostmoprtemEntry(String autopsyCatId,UserVO userVO);
	
	public String getAutopsyCategoryId(String dob,UserVO userVO);
	
	public DeceasedDetailVO[] getFinalOpinionToBeApproved(UserVO userVO);
	

	public List getGenderList(UserVO userVO);
	
	public List getMaritilStatusList(UserVO userVO);
	
	public String getPatientDOB(String age,String ageUnit,UserVO userVO);
	
	public DeceasedDetailVO[] getWaveoffDetails(UserVO userVO);
	
	public String getDeceasedNoByPostmortemId(String postmortemId,UserVO userVO);
	
	public DeceasedDetailVO[] getPostmortemReadyToHandover(UserVO userVO);
	
	public ConsentMappingMasterVO[] getConsentMappingDetail(UserVO userVO);
	
	////Consent Raised
	public void generatConsent(PostmortemRequestDetailVO postmortemRequestDtlVO,UserVO userVO);

	///Consent Received
	public void updateConsentReceivedStatus(DeceasedRelativeDtlVO relativeVO,PostmortemRequestDetailVO postmortemRequestDtlVO,UserVO userVO);
	
}
