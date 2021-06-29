package opd.transaction.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.UserVO;

public class OpdReportDeskLeftMenuDATA extends ControllerDATA {
	public static DeskDetailVO[] getOpdReportDeskLeftMenuDetail(UserVO _UserVO,String location,String unitCode,String deskType){
		OpdEssentialDelegate  opdessentialDelegate=new OpdEssentialDelegate();
		DeskDetailVO[] deskDetailVOs=opdessentialDelegate.getOpdMenuDetail(_UserVO,location,unitCode,deskType);
		return deskDetailVOs; 		
	}	
		
}
