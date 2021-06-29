package uploadfilesonserver;

import java.util.ArrayList;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
 

public class CellSerumRegroupingDATA extends ControllerDATA
{
	
	//Added bY hemant for fetching path details on 14-11-2018
	public static String getDetailsOfPath(UserVO _UserVO)
	{
		BloodInvestigationDelegate delegate = new BloodInvestigationDelegate();
		return( delegate.getPathData(_UserVO));
	}
	//Added by hemant for saving uploaded file data
	public static void saveDetailsOfFile(FileUploadedVO vo,UserVO _UserVO)
	{
		BloodInvestigationDelegate delegate = new BloodInvestigationDelegate();
		 delegate.saveDetailsOfFile(vo, _UserVO);
	}
}
