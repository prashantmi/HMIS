/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory globally i.e. on hospital code 100
 ## Date of Creation					:16-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package new_investigation.masters.controller.data;

import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.TestMandatoryMstVO;

public class TestMandatoryGlobalMstDATA {
	
	public static Map fetchTestMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestMandatory(testMandatoryMstVO, _UserVO);
	}

	public static Map getMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getMandatory(testMandatoryMstVO, _UserVO);
	}
	
	public static void saveTestMandatory(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveTestMandatory(insert_testMandatoryMstVO,delete_testMandatoryMstVO, _UserVO);
	}
}
