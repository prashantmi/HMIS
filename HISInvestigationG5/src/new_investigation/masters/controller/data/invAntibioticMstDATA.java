package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.invAntibioticMstVO;

public class invAntibioticMstDATA {
	
	
	
	
	public static void saveAntibiotic(invAntibioticMstVO testNewMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveAntibiotic(testNewMasterVO, _UserVO);
	}

	public static Map fetchAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchAntibiotic(testNewMasterVO, _UserVO,namecode);
	}
	
	
	public static Map fetchAntibioticD(invAntibioticMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchAntibiotic(testNewMasterVO, _UserVO,namecode);
	}
	
	public static void savemodifyAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyAntibiotic(testNewMasterVO, _UserVO);
	}

}
