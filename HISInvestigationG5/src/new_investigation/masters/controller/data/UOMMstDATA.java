package new_investigation.masters.controller.data;

 
import java.util.Map;

import new_investigation.vo.UOMMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class UOMMstDATA
{
	public static void saveUOM(UOMMasterVO uOMMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveUOM(uOMMasterVO, _UserVO);
	}

	public static Map fetchUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchUOM(uOMMasterVO, _UserVO);
	}
	
	public static Map fetchUOMD(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchUOMD(uOMMasterVO, _UserVO);
	}
	
	
	
	public static void savemodifyUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyUOM(uOMMasterVO, _UserVO);
	}
}
