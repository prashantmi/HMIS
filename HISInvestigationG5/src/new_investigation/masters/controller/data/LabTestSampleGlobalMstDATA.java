/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE/SYSTEM GLOBAL MASTER
 ## Purpose						        : This master is used for mapping the Sample to labs and test globally i.e. on hospital code 100
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;

public class LabTestSampleGlobalMstDATA {
	
	public static Map fetchLabTestSampleEssential(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabTestSampleEssential(labTestSampleMstVO, _UserVO);
	}

	public static void saveLabTest(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLabTestSample(labTestSampleMstVO, _UserVO);
	}
	public static Map fetchModifyLabTestGlobal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyLabTestGlobal(labTestSampleMstVO, _UserVO);
	}

	public static void saveModifyLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveModifyLabTestSample(labTestSampleMstVO, _UserVO);
	}
	
	public static List getUOM(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getUOM(labTestSampleMstVO, _UserVO);
	}
	public static List getSampleQty(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getSampleQty(labTestSampleMstVO, _UserVO);
	}
	
	public static String getContainerValues(String containerCode, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getContainerValue(containerCode, _UserVO);
	}
	
}
