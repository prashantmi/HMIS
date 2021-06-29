package new_investigation.masters.dao;

import java.util.List;

import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.TestNewMasterVO;
import hisglobal.vo.LabTestMasterVO;
import new_investigation.vo.TestParameterMasterVO;
import hisglobal.vo.UserVO;

public interface InvTestParameterMstDAOi
{
	 
			
//START  TEST Parameter MASTER//
			
			public TestParameterMasterVO[] getTestParameterDetail(UserVO userVO);
			public String checkDuplicateTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
			public void createTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
			 
			public List<TestParameterMasterVO> CheckTestParameterCode(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
			public void fetchTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
			public String checkDuplicateTestParameterModify(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);
			public void updateTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
			public void savemodifyTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
			 
			public List getTestCombo(UserVO _UserVO);
			public List getParameterCombo(UserVO _UserVO);
			public List getCriteriaCombo(UserVO _UserVO);
			public List getElementTypeCombo(UserVO _UserVO);
			public List getTestParameterCombo(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);
			public List getLoincScale(UserVO _UserVO);
			public List ajaxUrlCombo(UserVO _UserVO);
			public List getmastertypeTestCombo(UserVO _UserVO);
			public List<TestParameterMasterVO> fetchTestParameterForMaster(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);			
			//ENDS  Test Parameter MASTER//	
			
			public List getTestComboReqform(UserVO _UserVO);
			public void updateisreqformmapped(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
}
