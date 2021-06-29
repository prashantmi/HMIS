package mortuary.transaction.dao;

import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedStorageDtlDAOi 
{
	
	/** Inserting The Record
	 * @param deceasedStorageVO
	 * @param userVO
	 */
	public void create(DeceasedStorageDtlVO deceasedStorageVO, UserVO userVO);
	
	
	/** Counting The Dead Body in Rack
	 * @param deceasedStorageVO
	 * @param userVO
	 * @return
	 */
	public String countdeadBodyInRack(DeceasedStorageDtlVO deceasedStorageVO, UserVO userVO);
	
	public String getRackIdByDeceasedNo(String deceasedNo, UserVO userVO);
	
	public void updateHandoverStorageDtl(String deceasedNo, UserVO userVO);
	
}
