package uploadfilesonserver;

import java.util.List;


import hisglobal.vo.BloodBankMasterVO;
import hisglobal.vo.BloodRequisitionDtlVO;
import hisglobal.vo.BloodBagDtlVO;

import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.UserVO;

public interface BloodRequisitionDtlDAOi
{
		//Added by hemant for fetching path data
	public String getPathData(UserVO userVO);
	//Added bY hemant for saving of uploaded file data
	public void saveDetailsOfFile(FileUploadedVO vo ,UserVO userVO);
}
