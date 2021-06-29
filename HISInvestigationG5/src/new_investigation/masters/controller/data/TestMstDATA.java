package new_investigation.masters.controller.data;

 
import java.util.Map;

import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.TestNewMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class TestMstDATA
{
	public static void saveTest(TestNewMasterVO testNewMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveTest(testNewMasterVO, _UserVO);
	}

	public static Map fetchTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTest(testNewMasterVO, _UserVO);
	}
	
	
	public static Map fetchTestD(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestD(testNewMasterVO, _UserVO);
	}
	
	public static void savemodifyTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyTest(testNewMasterVO, _UserVO);
	}
}
