package mortuary.transaction.dao;

import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedImageDtlDAOi 
{
	
	/** Inserting Record
	 * @param deceasedImageDtlVO
	 * @param userVO
	 */
	public void create(MortuaryDeceasedImageDtlVO deceasedImageDtlVO, UserVO userVO);
	
	
	/** Getting The File Name
	 * @param deceasedNo
	 * @return
	 */
	public String getFileName(String deceasedNo);
	
	public MortuaryDeceasedImageDtlVO[] getExistingPhotoByDeceasedNo(String deceasedNo,UserVO userVO);
	
	public void updateIsDefaultNoToAll(String deceasedNo, UserVO userVO);
	
	public void updateIsDefaultValue(String deceasedNo, String srNo, UserVO userVO);
	
	public void deleteDeceasedImage(MortuaryDeceasedImageDtlVO deceasedImageDtlVO,UserVO userVO);
	
	public void updateIsDefaultValue(MortuaryDeceasedImageDtlVO deceasedImageDtlVO, UserVO userVO);
	
	public MortuaryDeceasedImageDtlVO getDedeasedDefaultImage(String deceasedNo,UserVO userVO);
	
}
