package opd.master.dao;

import java.util.List;

import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.OPDUnitImageMasterVO;
import hisglobal.vo.UserVO;

public interface UnitImageMasterDAOi
{
	// Inserting Image Unit Record
	public void create(OPDUnitImageMasterVO _UnitimagemasterVO, UserVO _UserVO);

	// Getting Images Foe View
	public OPDUnitImageMasterVO[] getImageForView(String _deptUnitCode, UserVO _userVO);
	
	// Getting Images List of  Unit
	public List<ImageMasterVO> getOPDImagesListOfUnit(String _unitCode, UserVO _UserVO);	

	// Deleting Images Unit Wise 
	public void deleteImageUnitWise(String _deptUnitCode, UserVO _userVO);
}
