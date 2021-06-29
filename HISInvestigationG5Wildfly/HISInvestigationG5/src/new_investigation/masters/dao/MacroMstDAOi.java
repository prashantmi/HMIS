package new_investigation.masters.dao;

import java.util.List;




import new_investigation.vo.MacroMasterVO;
import hisglobal.vo.UserVO;


public interface MacroMstDAOi
{

			
			//START Macro MASTER(add by yogender)//
			
				
				/*public MacroMasterVO[] getChecklistDetail_macro(UserVO userVO);*/
				public String checkDuplicateMacro(MacroMasterVO macromaster_VO, UserVO _UserVO);
				public void createMacro(MacroMasterVO macromaster_VO, UserVO _UserVO);
				public void fetchMacro(MacroMasterVO macromaster_VO, UserVO _UserVO);
				public String checkDuplicateModifyMacro(MacroMasterVO macromaster_VO,UserVO _UserVO);
				public void updateMacro(MacroMasterVO macromaster_VO, UserVO _UserVO);
				
			//ENDS Macro Master Master//
}
