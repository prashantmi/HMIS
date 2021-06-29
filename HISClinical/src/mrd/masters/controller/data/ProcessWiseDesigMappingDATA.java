package mrd.masters.controller.data;

import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.UserVO;
import java.util.List;

import mrd.masters.delegate.MrdMasterDelegate;


public class ProcessWiseDesigMappingDATA  
{
	
	public static List getProcessType()
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getProcessType();
	}

	public static void saveProcessWiseDesig(
			DoctorDesigMappingVO[] insertDoctorDesigMappingVO,
			DoctorDesigMappingVO[] updateDoctorDesigMappingVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		masterDelegate.saveProcessWiseDesig(insertDoctorDesigMappingVO,updateDoctorDesigMappingVO,userVO);
	}

	public static List getAssignedDesignationList(String processType,
			UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getAssignedDesignationList(processType,userVO);
	}
		
	public static List getNotAssignedDesignationList(String processType,
			UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getNotAssignedDesignationList(processType,userVO);
	}
	
}
