package opd.master.dao;

import java.util.List;

import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.UserVO;

public interface ImageMasterDAOi
{
	// Inserting Image Record
	public void create(ImageMasterVO imageMasterVO, UserVO userVO);

	// Selecting File Name
	public String selectFileName(ImageMasterVO imageMasterVO, UserVO userVO);

	public ImageMasterVO getImageForModify(String imageCode, String imageSlno, UserVO userVO);

	// Updating Image Record
	public void update(ImageMasterVO imageMasterVO, UserVO userVO);

	public void modifySaveWithImage(ImageMasterVO imageMasterVO, UserVO userVO);

	public void modifySaveWithoutImage(ImageMasterVO imageMasterVO, UserVO userVO);

	// Selecting File Name For Modification
	public String selectFileNameModify(ImageMasterVO imageMasterVO, UserVO userVO);

	// Updating Image Name
	public void updateImageName(ImageMasterVO imageMasterVO, UserVO userVO);

	public boolean checkDuplicateImage(ImageMasterVO imageMasterVO, UserVO userVO);

	public boolean check_Modify_DuplicateImage(ImageMasterVO imageMasterVO, UserVO userVO);

	// Getting Default Images List
	public List<ImageMasterVO> getDefaultImagesList(UserVO _UserVO);
}
