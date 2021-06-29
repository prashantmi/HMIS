/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST GLOBAL Master
 ## Purpose						        : 
 ## Date of Creation					:19-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package new_investigation.masters.controller.data;

import hisglobal.vo.LabTestMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.masters.bo.InvestigationMasterBO;
import new_investigation.vo.LabTestGlobalMstVO;

public class LabTestGlobalMstDATA {
	
	public static Map fetchLabTest(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabTest(labTestGlobalMasterVO, _UserVO);
	}
	public static List getTestBylabCode(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBO masterDelegate = new InvestigationMasterBO();
		return masterDelegate.getTestBylabCode(labTestGlobalMasterVO, _UserVO);
	}
	public static void saveLabTest(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLabTest(LabTestGlobalMstVO, _UserVO);
	}
	
	public static void savemodifyLabTest(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyLabTest(LabTestGlobalMstVO, _UserVO);
	}
	
	public static Map fetchModifyLabTestGlobal(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyLabTestGlobal(labTestGlobalMasterVO, _UserVO);
	}
	
	public static List getMachine(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBO masterDelegate = new InvestigationMasterBO();
		return masterDelegate.getMachine(labTestGlobalMasterVO, _UserVO);
	}
	
	public static List getTemplate(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBO masterDelegate = new InvestigationMasterBO();
		return masterDelegate.getTemplate(labTestGlobalMasterVO, _UserVO);
	}
	
}
