package opd.master.dao;

import java.util.List;

import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UserVO;

public interface UnitImageDescMasterDAOi
{
	public void create(UnitImageDescMasterVO unitImageDescMasterVO,UserVO userVO);
	
	public void modifySave(UnitImageDescMasterVO unitImageDescMasterVO,UserVO userVO);
	
	public List select(String selectedUnit,String imageId,String slNo,UserVO userVO);
	
	public void delete(String unittCode,UserVO userVO);
	
	// Getting Description List of Colors in String and Format
	public List<ImagePointerMasterVO> getUnitsImageColorDesc(String _unitCode, UserVO _UserVO);
	
}
