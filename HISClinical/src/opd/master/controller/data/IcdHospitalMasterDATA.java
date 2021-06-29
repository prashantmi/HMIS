package opd.master.controller.data;
import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;

import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.UserVO;


public class IcdHospitalMasterDATA extends ControllerDATA

{
	public static List getEssentails(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		List lst=opdEssDelegate.getIcdHospitalMasterEssentails(_userVO);
		return lst;
	}
	
	public static List fetchDiseaseList(IcdHospitalMasterVO _diseaseMasterVO,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		List lst=opdEssDelegate.fetchDiseaseList(_diseaseMasterVO,_userVO);
		return lst;
	}
	
	public static void save(IcdHospitalMasterVO[] _VOs,UserVO _userVO)
	{
		OpdMasterDelegate opdMstDelegate= new OpdMasterDelegate();
		opdMstDelegate.save(_VOs,_userVO);
		 
		}
	
}
