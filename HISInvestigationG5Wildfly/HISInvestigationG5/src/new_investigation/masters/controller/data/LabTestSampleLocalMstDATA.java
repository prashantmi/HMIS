/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE LOCAL MASTER
 ## Purpose						        : This master is used for mapping the Sample to labs and test locally i.e. on hospital code 101
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabTestSampleMstVO;

public class LabTestSampleLocalMstDATA {
	
	public static Map fetchLabTestSampleEssential(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabTestSampleLocalEssential(labTestSampleMstVO, _UserVO);
	}

	public static void saveLabTest(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLabTestLocalSample(labTestSampleMstVO, _UserVO);
	}
	public static Map fetchModifyLabTestLocal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyLabTestLocal(labTestSampleMstVO, _UserVO);
	}

	public static void saveModifyLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveModifyLabTestLocalSample(labTestSampleMstVO, _UserVO);
	}
	public static Map fetchGlobalData(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchGlobalData(labTestSampleMstVO, _UserVO);
	}

}
