package registration.reports.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.vo.UserVO;

import java.util.Collection;
import java.util.List;

import registration.bo.RegMasterBO;



public class GlobalOutPatientStatReportDATA {

	public static List getDeptList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getDeptList(uservo);	
	}
	
		
	public static List getUnitList(String strDeptCode,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getUnitList(strDeptCode, uservo);	
	}	
	
	public static List getHospitalList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getHospitalList(uservo);	
	}	
	
	
		
	public static void save(Collection col,String unitRoomMasterCapacity,String unitCode,String roomCode, UserVO userVO) throws HisException{
		RegMasterBO bo = new RegMasterBO();
		bo.saveDeptUnitRoster(col,unitRoomMasterCapacity,unitCode,roomCode, userVO);
	}
	
}
