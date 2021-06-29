package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.HospitalDiseaseMasterVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;

public class HospitalDiseaseMasterDATA extends ControllerDATA  
{
	public static Map getHospitalDiseaseEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getHospitalDiseaseEssentials(_userVO);		
	}
	public static void saveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO,
			UserVO _userVO) 
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveHospitalDisease(_hospitalDiseaseMasterVO,_userVO);
	}
	
	public static Map fetchHospitalDiseaseMapping(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchHospitalDiseaseMappings(_hospitalDiseaseMasterVO);		
	}
	
	
	public static HospitalDiseaseMasterVO fetchHospitalDiseaseModify(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchHospitalDiseaseModify(_hospitalDiseaseMasterVO,_userVO);
	}
	
	public static void modifySave(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySaveHospitalDisease(_hospitalDiseaseMasterVO,_userVO);
	}

}
