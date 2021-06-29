package new_investigation.masters.dao;

import java.util.List;

import new_investigation.vo.InvParameterMasterVO;

import new_investigation.vo.MandatoryComboMasterVO;

import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.TestNewMasterVO;
//import hisglobal.vo.LabMasterVO;
import hisglobal.vo.UserVO;

public interface InvSampleMstDAOi
{
	//START SAMPLE MASTER//

	public InvSampleMasterVO[] getChecklistDetail(UserVO userVO);
	public String checkDuplicateCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	public void create(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	public void fetchCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	public String checkDuplicateCheckListModify(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO);
	public void updateCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	public void savemodifyCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	public List getLoinicCombo(UserVO _UserVO);

	//ENDS SAMPLE MASTER//




	//START UOM MASTER//

	public UOMMasterVO[] getUOMDetail(UserVO userVO);
	public String checkDuplicateUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO);
	public void createUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO);
	public void fetchUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO);
	public String checkDuplicateUOMModify(UOMMasterVO uOMMasterVO,UserVO _UserVO);
	public void updateUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO);
	public void savemodifyUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO);
	public List getLoinicCombouom(UserVO _UserVO);
	//ENDS UOM MASTER//

	//START LAb MASTER//

	public LabMasterVO[] getLabDetail(UserVO userVO);
	public String checkDuplicateLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void createLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void fetchLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public String checkDuplicateLabModify(LabMasterVO labMasterVO,UserVO _UserVO);
	public void updateLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void savemodifyLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public List getLabCombo(UserVO _UserVO);
	//ENDS LAB MASTER//


	//START TESTMASTER//

	public TestNewMasterVO[] getTestDetail(UserVO userVO);
	public String checkDuplicateTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO);
	public void createTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO);
	public void fetchTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO);
	public String checkDuplicateTestModify(TestNewMasterVO testNewMasterVO,UserVO _UserVO);
	public void updateTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO);
	 
	public List getTestCombo(UserVO _UserVO);

	//ENDS TEST MASTER//	

	////////////////////////////////////START PARAMETER MASTER//////////////////////////////////////////////////

	public InvParameterMasterVO[] getChecklistDetail_parameter(UserVO userVO);
	public String checkDuplicateParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public void createParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public String checkDuplicateModifyParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO);
	public void updateParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);

	////////////////////////////////////END PARAMETER MASTER////////////////////////////////////////////////////////


	//////////////////////////////////START Mandatory Combo MASTER////////////////////////////////////////////

	public MandatoryComboMasterVO[] getMandatoryComboDetail(UserVO userVO);
	public String checkDuplicateMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public void createMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public void fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public String checkDuplicateModifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO,UserVO _UserVO);
	public void updateMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public List getMandCombo(UserVO _UserVO);

	///////////////////////////////////END MANDATORY COMBO MASTER//////////////////////////////////////////////


	///////////////////////////////START MANDATORY MASTER/////////////////////////////////////////////////////

	public MandatoryMasterVO[] getMandatoryDetail(UserVO userVO);
	public String checkDuplicateMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public void createMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public String checkDuplicateModifyMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO);
	public void updateMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);


	/////////////////////////////////ENDS MANDATORY MASTER////////////////////////////////////////////////////			



	public List getTestPrintingTemplateCombo(UserVO _UserVO);
	public void updatePrintingTemplate(TestNewMasterVO testNewMasterVO, UserVO _UserVO);
	public void updatePrintingTemplateOnModify(TestNewMasterVO testNewMasterVO, UserVO _UserVO);


	 

}
