/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : MRD
 ## Process/Database Object Name	    : UNIT WISE ESTIMATE PROCEDURE MAPPING MASTER
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package mrd.masters.controller.data;
import java.util.List;
import java.util.Map;

import mrd.masters.bo.MrdEssentialBO;
import mrd.masters.bo.MrdMasterBO;
import mrd.vo.UnitWiseEstProcedureMappingMstVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.UserVO;
public class UnitWiseEstProcedureMappingMstDATA extends ControllerDATA
{
	public static Map getProcedureUnitListEssential(UserVO _userVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();
		return serviceBO.getProcedureUnitListEssential(_userVO);
	}

	public static void saveProcedureUnitList(List _procUnitMapppingVO, UserVO _userVO) {
		MrdMasterBO masterserviceBO=new MrdMasterBO();
		masterserviceBO.saveEstProcedureUnitList(_procUnitMapppingVO,_userVO);
		
	}
	
	public static Map getProcedureUnitListForModify(UnitWiseEstProcedureMappingMstVO _vo, UserVO _userVO) {
		MrdEssentialBO serviceBO=new MrdEssentialBO();
		return serviceBO.getProcedureUnitListForModify(_vo,_userVO);
	}

	public static void modifySaveProcedureList(List _procUnitMapppingVO,UserVO _userVO) {
		MrdMasterBO masterserviceBO=new MrdMasterBO();
		masterserviceBO.modifySaveEstProcedureList(_procUnitMapppingVO,_userVO);
		
	}

}
