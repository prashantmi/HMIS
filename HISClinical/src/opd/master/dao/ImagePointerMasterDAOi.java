package opd.master.dao;

import java.util.List;

import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.UserVO;

public interface ImagePointerMasterDAOi
{
	public void create(ImagePointerMasterVO ImagePointerMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateImagePointer(ImagePointerMasterVO ImagePointerMasterVO,UserVO userVO);
	
	public ImagePointerMasterVO getDataForModify(ImagePointerMasterVO ImagePointerMasterVO, UserVO _UserVO);
	
	public void update(ImagePointerMasterVO ImagePointerMasterVO,UserVO _UserVO);
	
	public void modifySave(ImagePointerMasterVO ImagePointerMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(ImagePointerMasterVO ImagePointerMasterVO,UserVO userVO);
	
	// Getting Default Images Pointer List
	public List<ImagePointerMasterVO> getDefaultImagePointersList(UserVO _userVO);
	
}
