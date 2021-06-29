//Source file: C:\\Documents and Settings\\Pranav Vikas\\workspace\\AHIMS\\src\\registration\\master\\bo\\MasterBOi.java

package registration.master.bo;



import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.UserVO;

import java.util.Map;


public interface MasterBOi{
   
   
   public void saveEmployeeDetail(EmployeeMasterVO _empMasterVO,UserVO _userVO);
   public Map getEmployeeMasterDetails(String _empCode,UserVO _userVO);
   public void updateEmployeeMaster(EmployeeMasterVO _empMasterVO,UserVO _userVO);


}

