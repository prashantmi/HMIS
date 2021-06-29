package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.invAddendumReasonMstVO;

public class invAddendumReasonMstDATA {

	
	
	public static void saveAddendum(invAddendumReasonMstVO testNewMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveAddendum(testNewMasterVO, _UserVO);
	}

	public static Map fetchAddendum(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchAddendum(testNewMasterVO, _UserVO,namecode);
	}
	
	
	public static Map fetchAddendumD(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchAddendum(testNewMasterVO, _UserVO,namecode);
	}
	
	public static void savemodifyAddendum(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyAddendum(testNewMasterVO, _UserVO);
	}
	

}
