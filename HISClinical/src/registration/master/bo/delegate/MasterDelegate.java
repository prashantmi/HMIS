package registration.master.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.DepartmentUnitRoomMasterVO;

import hisglobal.vo.DeptCounterMappingVO;

import hisglobal.vo.DeptUnitConsultantInitialVO;

import hisglobal.vo.DeptUnitGroupingMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.ExternalInstituteMasterVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.PatientCategoryVO;
import hisglobal.vo.PatientCategoryVerificationMasterVO;
import hisglobal.vo.RegEmergencyCaseMstVO;
import hisglobal.vo.RegInjuryTypeMstVO;
import hisglobal.vo.RegOccupMstVO;
import hisglobal.vo.RegPatStatusMstVO;
import hisglobal.vo.RegistrationCatMstVO;
import hisglobal.vo.RegistrationTimingMasterVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.RosterMasterVO;
import hisglobal.vo.SeasonMasterVO;
import hisglobal.vo.UnitConsultantMasterVO;
import hisglobal.vo.UnitConsultantRosterMasterVO;
import hisglobal.vo.UnitCounterMappingVO;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UserVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import registration.master.bo.MasterBO;
import registration.master.bo.MasterBOi;



public class MasterDelegate extends Delegate{
	
	
	
	public MasterDelegate(){
		super(new MasterBO()); ///<<Setting the service provider
	}
	
		public void saveEmployeeDetail(EmployeeMasterVO _empMasterVO,UserVO _userVO){
			   MasterBOi serviceBO = (MasterBOi)super.getServiceProvider();
				serviceBO.saveEmployeeDetail(_empMasterVO, _userVO); 
		}
		public Map getEmployeeMasterDetails(String _empCode,UserVO _userVO){
			   MasterBOi serviceBO = (MasterBOi)super.getServiceProvider();
				return serviceBO.getEmployeeMasterDetails(_empCode,_userVO);
		} 
		public void updateEmployeeMaster(EmployeeMasterVO _empMasterVO,UserVO _userVO){
			MasterBOi serviceBO = (MasterBOi)super.getServiceProvider();
			serviceBO.updateEmployeeMaster(_empMasterVO, _userVO);
		}

}
