package mrd.masters.dao;

import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface DoctorDesigMappingDAOi {

	public List getProcessType();
	
	public List getNotAssignedDesignationList(String processType,UserVO _userVO);
	
	public List getAssignedDesignationList(String processType,UserVO _userVO);
	
	public void modify(DoctorDesigMappingVO doctorDesigMappingVO ,UserVO userVO);
	
	public void insert(DoctorDesigMappingVO doctorDesigMappingVO ,UserVO userVO);
	
	public String getDesignationName(String designationId,UserVO _userVO);
	
}
