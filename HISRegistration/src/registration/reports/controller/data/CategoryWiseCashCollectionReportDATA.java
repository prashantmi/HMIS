package registration.reports.controller.data;


import java.util.List;

import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.bo.RegEssentialBO;


public class CategoryWiseCashCollectionReportDATA {
	public static List getPatCategoryList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getPatientPrimaryCategory(uservo);	
	}
	public static List getUserList(UserVO uservo){
		RegEssentialBO bo = new RegEssentialBO();
		return bo.getUserList(uservo);	
	}
	
		
	
}
