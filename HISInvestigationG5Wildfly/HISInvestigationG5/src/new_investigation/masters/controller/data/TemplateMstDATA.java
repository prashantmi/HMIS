

package new_investigation.masters.controller.data;

import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.TemplateMstVO;

public class TemplateMstDATA {
	
	public static void saveTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveTemplate(templateMstVO, _UserVO);
	}
	public static void fetchModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.fetchModifyTemplate(templateMstVO, _UserVO);
	}
	public static void saveModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveModifyTemplate(templateMstVO, _UserVO);
	}
}
