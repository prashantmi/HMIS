package mortuary.transaction.dao;

import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.UserVO;

public interface MortuaryPoliceVerificationDAOi 
{
	 
	/** Inserting Police Verification Detail
	 * @param polVerificationVO
	 * @param userVO
	 */
	public void create(MortuaryPoliceVerificationVO polVerificationVO, UserVO userVO);
	
	public MortuaryPoliceVerificationVO getExistingPoliceVerDetail(String deceasedNo,UserVO userVO);
	
	public String getPoliceCaseNo(String deceasedNo,UserVO userVO);
	
	public MortuaryPoliceVerificationVO fetchPoliceVeriDetailWaveoff(String deceasedNo,UserVO userVO);
	
	public MortuaryPoliceVerificationVO fetchPoliceVeriDetail(String deceasedNo,UserVO userVO);
}
