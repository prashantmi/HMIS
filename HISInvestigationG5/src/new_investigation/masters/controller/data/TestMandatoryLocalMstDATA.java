/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory locally i.e. on hospital code 101
 ## Date of Creation					:19-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.TestMandatoryMstVO;

public class TestMandatoryLocalMstDATA {
	
	public static Map fetchTestMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchTestMandatoryLocal(testMandatoryMstVO, _UserVO);
	}

	public static Map getMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getMandatoryLocal(testMandatoryMstVO, _UserVO);
	}
	
	public static void saveTestMandatory(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveTestMandatoryLocal(insert_testMandatoryMstVO,delete_testMandatoryMstVO, _UserVO);
	}

}
