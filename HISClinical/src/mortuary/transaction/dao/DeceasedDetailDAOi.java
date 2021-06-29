package mortuary.transaction.dao;

import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedDetailDAOi 
{
	
	/** Generating Deceased No
	 * @param userVO
	 * @return
	 */
	public String generateDeceasedNo(UserVO userVO);
	
	
	/** Inserting Record
	 * @param deceasedDtlVO
	 * @param userVO
	 */
	public void create(DeceasedDetailVO deceasedDtlVO, UserVO userVO);

	public void updatePostmortemReq(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO, UserVO userVO);
	
	public DeceasedDetailVO getDeceasedDtlByDeceasedNo(String deceasedNo,UserVO userVO);
	
	public void updateUnknownToKnownDetail(DeceasedDetailVO deceasedDtlVO, UserVO userVO);
	
	public void updateDeceasedClaimedStatus(DeceasedDetailVO deceasedDtlVO, UserVO userVO);
	
	public void updateDeceasedGeneralAppearance(DeceasedDetailVO deceasedDtlVO, UserVO userVO);
	
	public DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO);
	
	public DeceasedDetailVO[] searchDeceasedNo(String fName,String mName,String lName,String deathDate,UserVO userVO);
	
	public DeceasedDetailVO[] searchPostmortemNo(String decNo, String fName,String mName,String lName,String deathDate,UserVO userVO);
	
	public DeceasedHandoverDtlVO getDeceasedDtlAfterHandover(String deceasedNo,UserVO userVO);
	
	//added for the purpose of duplicate MLC Check at External Deceased Entry By Shruti Shail on 11-May-2017
	public Boolean chkDuplicateMLC(String mlcNo,String HospCode);
	
}
