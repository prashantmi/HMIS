package hr.pis.empReg.reports.controller.data;

import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import hr.pis.empReg.bo.reports.ListOfRegisteredEmpRptBO;



import java.util.List;
import java.util.Map;

public class ListOfRegisteredEmpRptDATA 
{
	public static Map<String, List<Entry>> getRegistrationRptFormEssentials(UserVO _UserVO) {
		ListOfRegisteredEmpRptBO boObj = new ListOfRegisteredEmpRptBO();
		Map<String, List<Entry>> mp = boObj.getRegistrationRptFormEssentials(_UserVO);
		return mp;
	}	
	

}
