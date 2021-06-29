package new_investigation.masters;

import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import new_investigation.masters.bo.InvestigationMasterBO;
import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.vo.BookMarkMstVO;
import new_investigation.vo.CannedMasterVO;
import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.ItemLabTestMappingMstVO;
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.LabConfigratorMstVO;
import new_investigation.vo.LabConsumableMstVO;
import new_investigation.vo.LabItemMappingMasterVO;
import new_investigation.vo.LabMacroMapMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.LabNoConfigMasterVO;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.LocalTestMandRefMasterVO;
import new_investigation.vo.LoincMstVO;
import new_investigation.vo.MachineTestParameterMasterVO;
import new_investigation.vo.MacroMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.RejectionReasonMasterVO;
import new_investigation.vo.SampleNoConfigMasterVO;
import new_investigation.vo.TemplateMstVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.TestMandatoryMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.TestParameterMasterVO;

 
 
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.UserBookMarkMstVO;

 

import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.UserwiseBookMarkMstVO;
import new_investigation.vo.invAddendumReasonMstVO;
import new_investigation.vo.invAntibioticMstVO;
import new_investigation.vo.invOrganicAntibioticMappingMstVO;
import new_investigation.vo.invOrganicMstVO;
import new_investigation.vo.machineMstVO;

public class InvestigationMasterDelegate extends Delegate
{
	public InvestigationMasterDelegate()
	{
		super(new InvestigationMasterBO());
	}


	//// INVESTIGATIGATION SAMPLE MASTER START ///


	public void saveCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveCheckList(bCheckListMasterVO,_UserVO);
	}

	public Map fetchCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckList(bCheckListMasterVO, _UserVO);
	}

	public void savemodifyCheckList(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyCheckList(bCheckListMasterVO,_UserVO);
	}


	public Map fetchSample(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchSample(bCheckListMasterVO, _UserVO);
	}
	////INVESTIGATIGATION SAMPLE MASTER END ///


	///////////////////////////////////Mandatory Combo MASTER START /////////////////////////////////////////////


	public void saveMandatoryCombo(List<MandatoryComboMasterVO> lstMandatoryComboVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveMandatoryCombo(lstMandatoryComboVO,_UserVO);
	}

	public Map fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListMandatoryCombo(mandatorycombo_VO, _UserVO);
	}

	public void savemodifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyMandatoryCombo(mandatorycombo_VO,_UserVO);
	}


	public Map fetchMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchMandatoryCombo(mandatorycombo_VO, _UserVO);
	}

	public List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchdisplaydataMandatoryCombo(mandatorycombo_VO, _UserVO);
	}
	/////////////////////////////////////MANDATORY COMBO MASTER END //////////////////////////////////////////////


	////INVESTIGATIGATION UOM MASTER START ///


	public void saveUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveUOM(uOMMasterVO,_UserVO);
	}


	public Map fetchUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)

	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return	serviceBO.fetchUOM(uOMMasterVO, _UserVO);
	}

	public Map fetchUOMD(UOMMasterVO uOMMasterVO, UserVO _UserVO)

	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return	serviceBO.fetchUOMD(uOMMasterVO, _UserVO);
	}

	public void savemodifyUOM(UOMMasterVO uOMMasterVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyUOM(uOMMasterVO,_UserVO);
	}
	////INVESTIGATIGATION UOM MASTER END ///

	////INVESTIGATIGATION LAB MASTER START ///
	 

	public void saveLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLab(labMasterVO,_UserVO);
	}

	public Map fetchLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLab(labMasterVO, _UserVO);
	}

	public Map fetchLabD(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabD(labMasterVO, _UserVO);
	}

	public void savemodifyLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyLab(labMasterVO,_UserVO);
	}
////INVESTIGATIGATION LAB MASTER END ///
	 

	////INVESTIGATIGATION TEST MASTER START ///


	public void saveTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTest(testNewMasterVO,_UserVO);
	}

	public Map fetchTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTest(testNewMasterVO, _UserVO);
	}

	public void savemodifyTest(TestNewMasterVO testNewMasterVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyTest(testNewMasterVO,_UserVO);
	}


	public Map fetchTestD(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestD(testNewMasterVO, _UserVO);
	}
	////INVESTIGATIGATION TEST MASTER END ///	

	///////parameter master start/////////


	//// parameter checklist ////
	public void saveParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveParameter(parametermaster_VO,_UserVO);
	}

	public void fetchCheckListParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO =  (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchCheckListParameter(parametermaster_VO, _UserVO);
	}

	public void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchParameter(parametermaster_VO, _UserVO);
	}

	public void savemodifyParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyParameter(parametermaster_VO,_UserVO);
	}

	//////parameter master ends////////

	
 
	public void saveTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestParameter(testParameterMasterVO,_UserVO);
	}

	public Map fetchTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestParameter(testParameterMasterVO, _UserVO);
	}

	 
	
	public void savemodifyTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyTestParameter(testParameterMasterVO,_UserVO);
	}
 
	public Map fetchParameterCombo(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchParameterCombo(testParameterMasterVO, _UserVO);
	}
	
	
 

 
	public Map  TestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.TestParameter(testParameterMasterVO, _UserVO);
	}
 
	/*public Map ajaxUrlCombo(TestParameterMasterVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.ajaxUrlCombo(testNewMasterVO, _UserVO);
	}*/
	 
	
	
 
	////INVESTIGATIGATION TEST SAMPLE  MASTER END ///


	///////////////////////////////////Mandatory MASTER START ///////////////////////////////////////////


	public void saveMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveMandatory(mandatorymaster_VO,_UserVO);
	}

	public void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchCheckListMandatory(mandatorymaster_VO, _UserVO);
	}

	public void savemodifyMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyMandatory(mandatorymaster_VO,_UserVO);
	}


	public void fetchMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchMandatory(mandatorymaster_VO, _UserVO);	}

	////////////////////////////////////////Mandatory MASTER END//////////////////////////////////// ///

	////INVESTIGATIGATION CONTAINER  MASTER START ///


	public void saveContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveContainer(container_VO,_UserVO);
	}
////INVESTIGATIGATION TEST SAMPLE  MASTER END ///
////INVESTIGATIGATION  LAB TEST GLOBAL MASTER STARTS ///
	
	public Map fetchLabTest(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabTest(labTestMasterVO, _UserVO);
	}
	public void saveLabTest(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabTestGlobalTest(labTestMasterVO,_UserVO);
	}
	

	public Map fetchCheckListContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListContainer(container_VO, _UserVO);
	}

	public void savemodifyContainer(ContainerMasterVO container_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyContainer(container_VO,_UserVO);
	}


	public Map fetchContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchContainer(container_VO, _UserVO);
	}
	////INVESTIGATIGATION CONTAINER MASTER END ///

	////INVESTIGATIGATION COLLECTION AREA MASTER START ///


	public void saveCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveCollectionArea(collectionarea_VO,_UserVO);
	}

	public Map fetchCheckListCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListCollectionArea(collectionarea_VO, _UserVO);
	}

	public void savemodifyCollectionArea(CollectionAreaMasterVO collectionarea_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyCollectionArea(collectionarea_VO,_UserVO);
	}


	public Map fetchCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCollectionArea(collectionarea_VO, _UserVO);
	}
	////INVESTIGATIGATION COLLECTION AREA MASTER END ///


	////INVESTIGATIGATION TEST SAMPLE MASTER START ///


	public void saveInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveInvTestSample(testsample_VO,_UserVO);
	}

	public Map fetchCheckListInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListInvTestSample(testsample_VO, _UserVO);
	}

	public void savemodifyInvTestSample(InvTestSampleMasterVO testsample_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyInvTestSample(testsample_VO,_UserVO);
	}


	public Map fetchInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchInvTestSample(testsample_VO, _UserVO);
	}




	///////////////////////////////////TestPara Combo MASTER START /////////////////////////////////////////////


	public void saveTestParaCombo(List<TestParaComboMasterVO> lstTestParaComboVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestParaCombo(lstTestParaComboVO,_UserVO);
	}

	public Map fetchCheckListTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListTestParaCombo(testparacombo_VO, _UserVO);
	}

	public void savemodifyTestParaCombo(TestParaComboMasterVO testparacombo_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyTestParaCombo(testparacombo_VO,_UserVO);
	}


	public Map fetchTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestParaCombo(testparacombo_VO, _UserVO);
	}

	public List<TestParaComboMasterVO> fetchdisplaydataTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchdisplaydataTestParaCombo(testparacombo_VO, _UserVO);
	}
	public void savemodifyLabTest(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyLabTest(labTestMasterVO,_UserVO);
	}
	
	public Map fetchModifyLabTestGlobal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyLabTestGlobal(labTestMasterVO, _UserVO);
	}
////INVESTIGATIGATION  LAB TEST LOCAL MASTER STARTS ///
	public Map fetchLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabTestLocal(labTestMasterVO, _UserVO);
	}
	public void saveLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabTestLocalTest(labTestMasterVO,_UserVO);
	}
	
	public void savemodifyLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyLabTestLocal(labTestMasterVO,_UserVO);
	}
	
	public Map fetchModifyLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyLabTestLocal(labTestMasterVO, _UserVO);
	}
	public Map fetchParameterCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchParameterCombo(testparacombo_VO, _UserVO);
	}
	/////////////////////////////////////test para COMBO MASTER END //////////////////////////////////////////////

	////INVESTIGATIGATION lab COLLECTION AREA MASTER START ///


	public void saveLabCollectionArea(LabCollectionAreaMasterVO[] insert_labcollectionarea_VO,LabCollectionAreaMasterVO[] delete_labcollectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabCollectionArea(insert_labcollectionarea_VO,delete_labcollectionarea_VO,_UserVO);
	}

	

	public Map fetchLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabCollectionArea(labcollectionarea_VO, _UserVO);
	}
	
	public Map getArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getArea(labcollectionarea_VO, _UserVO);
	}
	////INVESTIGATIGATION lab COLLECTION AREA MASTER END ///

////INVESTIGATIGATION TEST GROUP INFO MASTER START ///


	public void saveTestGroupInfo(TestGroupInfoMasterVO[] insert_testgroupinfo_VO,TestGroupInfoMasterVO[] delete_testgroupinfo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestGroupInfo(insert_testgroupinfo_VO,delete_testgroupinfo_VO,_UserVO);
	}


	public Map fetchTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestGroupInfo(testgroupinfo_VO, _UserVO);
	}
	
	public Map getTest(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTest(testgroupinfo_VO, _UserVO);
	}
	

	////INVESTIGATIGATION TEST GROUP INFO MASTER END ///
	
////INVESTIGATIGATION TEST GROUP INFO LOCAL MASTER START ///


	public void saveTestGroupInfoLocal(TestGroupInfoMasterVO[] insert_testgroupinfolocal_VO,TestGroupInfoMasterVO[] delete_testgroupinfolocal_VO, UserVO _UserVO,TestGroupInfoMasterVO[] modify_testgroupinfolocal_VO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestGroupInfoLocal(insert_testgroupinfolocal_VO,delete_testgroupinfolocal_VO,_UserVO,modify_testgroupinfolocal_VO);
	}


	public Map fetchTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestGroupInfoLocal(testgroupinfolocal_VO, _UserVO);
	}
	
	public Map getTestLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTestLocal(testgroupinfolocal_VO, _UserVO);
	}
	////INVESTIGATIGATION TEST GROUP INFO LOCAL MASTER END ///
	
	
////INVESTIGATIGATION LAB NO CONFIG MASTER START ///


	public void saveLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabNoConfig(labnoconfig_VO,_UserVO);
	}

	public Map fetchCheckListLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListLabNoConfig(labnoconfig_VO, _UserVO);
	}

	public void savemodifyLabNoConfig(LabNoConfigMasterVO labnoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyLabNoConfig(labnoconfig_VO,_UserVO);
	}


	public Map fetchLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabNoConfig(labnoconfig_VO, _UserVO);
	}
	
	public Map getTest(LabNoConfigMasterVO labnoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTest(labnoconfig_VO,_UserVO);
	}
	////INVESTIGATIGATION LAB NO CONFIG MASTER END ///
	
	
	
	

////INVESTIGATIGATION Rejection Reason MASTER START (add by yogender)///
	
	public void saveRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveRejectionReason(rejectionreasonmaster_VO,_UserVO);
	}
		
	public void fetchCheckListRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate serviceBO = new InvestigationMasterDelegate();
		serviceBO.fetchCheckListRejectionReason(rejectionreasonmaster_VO, _UserVO);
	}
	
	public void fetchRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchRejectionReason(rejectionreasonmaster_VO, _UserVO);
	}
	
	public void savemodifyRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyRejectionReason(rejectionreasonmaster_VO,_UserVO);
	}
	
////INVESTIGATIGATION rejection Reason MASTER END ///
	
	
////Macro Master Start (added by yogender yadav)////
	public void saveMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveMacro(macromaster_VO,_UserVO);
	}
		
	public void fetchCheckListMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchCheckListMacro(macromaster_VO, _UserVO);
	}
	
	public void fetchMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchMacro(macromaster_VO, _UserVO);
	}
	
	public void savemodifyMacro(MacroMasterVO macromaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyMacro(macromaster_VO,_UserVO);
	}
	
	//////Macro master ends////////
	
////INVESTIGATIGATION Global Test Group MASTER START (add by yogender)///
	
	public void saveTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestGroup(testgroupmaster_VO,_UserVO);
	}
		
	public void modifyTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate serviceBO = new InvestigationMasterDelegate();
		serviceBO.modifyTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public void fetchTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public void updateTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.updateTestGroup(testgroupmaster_VO, _UserVO);
	} 
	
////INVESTIGATIGATION Global Test Group MASTER END ///
	
////INVESTIGATIGATION Local Test Group MASTER START (add by yogender)///
	
	public void saveLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLocalTestGroup(testgroupmaster_VO,_UserVO);
	}
		
	public void modifyLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate serviceBO = new InvestigationMasterDelegate();
		serviceBO.modifyLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public Map fetchCheckListLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public Map fetchLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
	
	public void updateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.updateLocalTestGroup(testgroupmaster_VO, _UserVO);
	} 
	
	public void fetchdisplaydataLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchdisplaydataLocalTestGroup(testgroupmaster_VO, _UserVO);
	}
////INVESTIGATIGATION Local Test Group MASTER END ///
	
////INVESTIGATIGATION Canned MASTER START (add by yogender)///
	
	public void saveCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveCanned(cannedmaster_VO,_UserVO);
	}
		
	public void modifyCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate serviceBO = new InvestigationMasterDelegate();
		serviceBO.modifyCanned(cannedmaster_VO, _UserVO);
	}
	
	public void fetchCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchCanned(cannedmaster_VO, _UserVO);
	}
	
	public void updateCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.updateCanned(cannedmaster_VO, _UserVO);
	} 
	
////INVESTIGATIGATION Canned MASTER END ///
	
////INVESTIGATIGATION Lab Macro Local Mapping Master (add by yogender)///



	public void saveLabMacroLocalMap(LabMacroMapMasterVO[] insert_labmacromapmaster_VO,LabMacroMapMasterVO[] delete_labmacromapmaster_VO, UserVO _UserVO,LabMacroMapMasterVO[] modify_labmacromap_VO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabMacroLocalMap(insert_labmacromapmaster_VO,delete_labmacromapmaster_VO,_UserVO,modify_labmacromap_VO);
	}

	public Map fetchLabMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabMacroLocalMap(labmacromap_VO, _UserVO);
	}

	public Map getLocalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getLocalMacro(labmacromap_VO, _UserVO);
	}
	
	/*public void fetchdisplaydataMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchdisplaydataMacroLocalMap(labmacromap_VO, _UserVO);
	}*/

////INVESTIGATIGATION Lab Macro Local Mapping Master END ///
	
									/*Lab Test Sample Global Master*/
	public Map fetchLabTestSampleEssential(LabTestSampleMstVO labTestSampleMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabTestSampleEssential(labTestSampleMasterVO, _UserVO);
	}
	public void saveLabTestSample(LabTestSampleMstVO labTestSampleMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabTestSample(labTestSampleMasterVO,_UserVO);
	}
	public Map fetchModifyLabTestGlobal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyLabTestGlobal(labTestSampleMstVO, _UserVO);
	}
	public void saveModifyLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveModifyLabTestSample(labTestSampleMstVO,_UserVO);
	}
	public List getUOM(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getUOM(labTestSampleMstVO,_UserVO);
	}
	
	public String getContainerValue(String containerCode,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getContainerValues(containerCode, _UserVO);
	}
	public List getSampleQty(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getSampleQty(labTestSampleMstVO,_UserVO);
	}
	
	
	
	
	

////INVESTIGATIGATION Lab Macro Global Mapping Master (add by yogender)///



	public void saveLabMacroGlobalMap(LabMacroMapMasterVO[] insert_labmacromapmaster_VO,LabMacroMapMasterVO[] delete_labmacromapmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabMacroGlobalMap(insert_labmacromapmaster_VO,delete_labmacromapmaster_VO,_UserVO);
	}

	

	public Map fetchLabMacroGlobalMap(LabMacroMapMasterVO labmacromapmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabMacroGlobalMap(labmacromapmaster_VO, _UserVO);
	}
	
	public Map getGlobalMacro(LabMacroMapMasterVO labmacromapmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getGlobalMacro(labmacromapmaster_VO, _UserVO);
	}


////INVESTIGATIGATION Lab Macro global Mapping Master END ///
	

////INVESTIGATIGATION Global Lab Canned Master Master (add by yogender)///



	public void saveGlobalLabCanned(LabCannedMasterVO[] insert_labcannedmaster_VO,LabCannedMasterVO[] delete_labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveGlobalLabCanned(insert_labcannedmaster_VO,delete_labcannedmaster_VO,_UserVO);
	}

	

	public Map fetchGlobalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchGlobalLabCanned(labcannedmaster_VO, _UserVO);
	}
	
	public Map getGlobalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getGlobalLabCanned(labcannedmaster_VO, _UserVO);
	}


////INVESTIGATIGATION Global Lab Canned Master Master END ///
	

////INVESTIGATIGATION  Local Lab Canned Master (add by yogender)///



	public void saveLocalLabCanned(LabCannedMasterVO[] insert_labcannedmaster_VO,LabCannedMasterVO[] delete_labcannedmaster_VO, UserVO _UserVO,LabCannedMasterVO[] modify_labcannedmaster_VO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLocalLabCanned(insert_labcannedmaster_VO,delete_labcannedmaster_VO,_UserVO,modify_labcannedmaster_VO);
	}

	public Map fetchLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLocalLabCanned(labcannedmaster_VO, _UserVO);
	}

	public Map getLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getLocalLabCanned(labcannedmaster_VO, _UserVO);
	}
	
	/*public void fetchdisplaydataMacroLocalMap(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchdisplaydataMacroLocalMap(labcannedmaster_VO, _UserVO);
	}*/

////INVESTIGATIGATION  Local Lab Canned Master  END ///
	
	

	

	////INVESTIGATIGATION  LAB TEST SAMPLE LOCAL  MASTER STARTS ///


	public Map fetchLabTestSampleLocalEssential(LabTestSampleMstVO labTestSampleMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabTestLocalSampleEssential(labTestSampleMasterVO, _UserVO);
	}
	public void saveLabTestLocalSample(LabTestSampleMstVO labTestSampleMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabTestLocalSample(labTestSampleMasterVO,_UserVO);
	}
	public Map fetchModifyLabTestLocal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyLabTestLocal(labTestSampleMstVO, _UserVO);
	}
	public void saveModifyLabTestLocalSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveModifyLabTestLocalSample(labTestSampleMstVO,_UserVO);
	}
	public Map fetchGlobalData(LabTestSampleMstVO labTestSampleMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchGlobalData(labTestSampleMasterVO, _UserVO);
	}
	                               /* Test Mandatory Global Master By Anant Patel */
	
	public Map fetchTestMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestMandatory(testMandatoryMstVO, _UserVO);
	}
	
	public Map getMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getMandatory(testMandatoryMstVO, _UserVO);
	}
	
	public void saveTestMandatory(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestMandatory(insert_testMandatoryMstVO,delete_testMandatoryMstVO,_UserVO);
	}
	
                              /* Test Mandatory Local Master By Anant Patel */
	
	public Map fetchTestMandatoryLocal(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestMandatoryLocal(testMandatoryMstVO, _UserVO);
	}
	
	public Map getMandatoryLocal(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getMandatoryLocal(testMandatoryMstVO, _UserVO);
	}
	
	public void saveTestMandatoryLocal(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveTestMandatoryLocal(insert_testMandatoryMstVO,delete_testMandatoryMstVO,_UserVO);
	}
	
	                          /*User BookMark Master By Anant Patel*/
	public Map getEssential(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getEssential(userBookMarkMstVO, _UserVO);
	}
	
	public void saveUserBookMark(UserBookMarkMstVO userBookMarkMstVO,List<UserBookMarkMstVO> lstUserBookMarkVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveUserBookMark(userBookMarkMstVO,lstUserBookMarkVO,_UserVO);
	}
	public Map fetchModifyUserBookMark(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyUserBookMark(userBookMarkMstVO, _UserVO);
	}
	public Map getBookMarkType(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getBookMarkType(userBookMarkMstVO, _UserVO);
	}
	public List getUnit(String deptCode,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getUnit(deptCode, _UserVO);
	}
	public List getTest(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTest(userBookMarkMstVO, _UserVO);
	}
	
	public List<UserBookMarkMstVO> getTestByGroup(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTestByGroup(userBookMarkMstVO, _UserVO);
	}
	
	public void modifySaveUserBookmark(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.modifySaveUserBookmark(userBookMarkMstVO,_UserVO);
	}
	public void getTestName(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.getTestName(userBookMarkMstVO,_UserVO);
	}
	////INVESTIGATIGATION LOCAL LAB MASTER START ///
	 

 	public void saveLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLocalLab(labMasterVO,_UserVO);
	}

	public Map fetchLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLocalLab(labMasterVO, _UserVO);
	}

	public Map fetchLocalLabD(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLocalLabD(labMasterVO, _UserVO);
	}
	
	public Map Populate(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.Populate(labMasterVO, _UserVO);
	}


	public void savemodifyLocalLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyLocalLab(labMasterVO,_UserVO);
	}
	
	public Map fetchGlobalLabCombo(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchGlobalLabCombo(labMasterVO, _UserVO);
	}
	
////INVESTIGATIGATION LOCAL LAB MASTER END ///
	
	
	
////INVESTIGATIGATION SAMPLE NO CONFIG MASTER START ///


	public void saveSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveSampleNoConfig(samplenoconfig_VO,_UserVO);
	}

	public Map fetchCheckListSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListSampleNoConfig(samplenoconfig_VO, _UserVO);
	}

	public void savemodifySampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifySampleNoConfig(samplenoconfig_VO,_UserVO);
	}


	public Map fetchSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchSampleNoConfig(samplenoconfig_VO, _UserVO);
	}
	
	public Map getTest(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTest(samplenoconfig_VO,_UserVO);
	}
	////INVESTIGATIGATION SAMPLE NO CONFIG MASTER END ///
	
						/*START OF LAB CONFIGRATOR MASTER By Anant Patel */
	

	public Map fetchLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLabConfigrator(labConfigratorMstVO, _UserVO);
	}
	public Map populate(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.populate(labConfigratorMstVO, _UserVO);
	}
	public Map getTestByLabCode(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getTestByLabCode(labConfigratorMstVO, _UserVO);
	}
	public void saveLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveLabConfigrator(labConfigratorMstVO,_UserVO);
	}
	public Map fetchModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyLabConfigrator(labConfigratorMstVO, _UserVO);
	}
	public void saveModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO, List deleteTest, List deleteSample, List deleteMand, List deleteCanned,List deleteMacro)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveModifyLabConfigrator(labConfigratorMstVO,_UserVO,deleteTest,deleteSample,deleteMand,deleteCanned,deleteMacro);
	}
						/*END OF LAB CONFIGRATOR MASTER*/
	
	
	
	

////INVESTIGATIGATION COLLECTION AREA NO CONFIG MASTER START ///


	public void saveCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveCollAreaSampleNoConfig(collareasamplenoconfig_VO,_UserVO);
	}

	public Map fetchCheckListCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCheckListCollAreaSampleNoConfig(collareasamplenoconfig_VO, _UserVO);
	}

	public void savemodifyCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.savemodifyCollAreaSampleNoConfig(collareasamplenoconfig_VO,_UserVO);
	}


	public Map fetchCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchCollAreaSampleNoConfig(collareasamplenoconfig_VO, _UserVO);
	}
	
	public Map getArea(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getArea(collareasamplenoconfig_VO,_UserVO);
	}
	////INVESTIGATIGATION COLLECTION AREA SAMPLE NO CONFIG MASTER END ///
					/*BookMark Master By Anant Patel*/
	
	public void saveBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveBookMark(bookMarkMstVO,_UserVO);
	}
	public void fetchModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.fetchModifyBookMark(bookMarkMstVO,_UserVO);
	}
	public void saveModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		serviceBO.saveModifyBookMark(bookMarkMstVO,_UserVO);
	}

	
	////////// test mandatory reference master///////////////////////////////////////////
	


public void saveTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
serviceBO.saveTestMandRef(lstTestMandRefVO,_UserVO);
}

public Map fetchCheckListTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchCheckListTestMandRef(testmandref_VO, _UserVO);
}

public void savemodifyTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO,UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
serviceBO.savemodifyTestMandRef(lstTestMandRefVO,_UserVO);
}


public Map fetchTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchTestMandRef(testmandref_VO, _UserVO);
}

public List<TestMandRefMasterVO> fetchdisplaydataTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchdisplaydataTestMandRef(testmandref_VO, _UserVO);
}
	
public Map fetchParameterCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchParameterCombo(testmandref_VO, _UserVO);
}

public Map fetchCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchCombo(testmandref_VO, _UserVO);
}
public List fetchOld(String testCode, String parameterCode, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchOld(testCode,parameterCode, _UserVO);
}
public void deleteTestMandRef(List deleteList, String testCode, String parameterCode, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
serviceBO.deleteTestMandRef(deleteList,testCode,parameterCode,_UserVO);
}

public List getLabSample(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getLabSample(testmandref_VO, _UserVO);
}

public Map fetchCheckListLocalTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
}
	

	   					/*Loinc Master By Anant Patel*/
	public Map fetchLoincMst(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchLoincMst(loincMstVO,_UserVO);
	}
	public Map fetchTestPara(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestPara(loincMstVO,_UserVO);
	}
	/*public Map fetchTestUOM(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchTestUOMData(loincMstVO,_UserVO);
	}*/
	public Map getLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getLoinc(loincMstVO,_UserVO);
	}
	public Map getSearch(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.getSearch(loincMstVO,_UserVO);
	}
	public void saveLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		 serviceBO.saveLoinc(loincMstVO,_UserVO);
	}
	public Map fetchModifyLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		return serviceBO.fetchModifyLoinc(loincMstVO,_UserVO);
	}
	public void saveModifyLoinc(LoincMstVO loincMstVO,UserVO _UserVO)
	{
		InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
		 serviceBO.saveModifyLoinc(loincMstVO,_UserVO);
	}
	
////////// local test mandatory reference master///////////////////////////////////////////
	


public void saveLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
serviceBO.saveLocalTestMandRef(lstLocalTestMandRefVO,_UserVO);
}

public Map fetchCheckListLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
}

public void savemodifyLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO,UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
serviceBO.savemodifyLocalTestMandRef(lstLocalTestMandRefVO,_UserVO);
}


public Map fetchLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchLocalTestMandRef(testmandref_VO, _UserVO);
}

public List<LocalTestMandRefMasterVO> fetchdisplaydataLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchdisplaydataLocalTestMandRef(testmandref_VO, _UserVO);
}

public Map fetchParameterCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchParameterCombo(testmandref_VO, _UserVO);
}

public Map fetchCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchCombo(testmandref_VO, _UserVO);
}
public List fetchOldLocal(String testCode, String parameterCode, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
return serviceBO.fetchOldLocal(testCode,parameterCode, _UserVO);
}
public void deleteLocalTestMandRef(List deleteList, String testCode, String parameterCode, UserVO _UserVO)
{
InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
serviceBO.deleteLocalTestMandRef(deleteList,testCode,parameterCode,_UserVO);
}

public List getLabSample(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getLabSample(testmandref_VO, _UserVO);
}

//Machine Test Parameter Master
public Map fetchParaMachineCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchParaMachineCombo(machineTestParaMstVO, _UserVO);
}


public Map fetchParaTestCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchParaTestCombo(machineTestParaMstVO, _UserVO);
}
public List<MachineTestParameterMasterVO> displayParameterDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.displayParameterDetails(machineTestParaMstVO, _UserVO);
}
public void saveDetails(List<MachineTestParameterMasterVO> machineTestParamMst_listVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveDetails(machineTestParamMst_listVO,_UserVO);
}
public Map fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchModifyDetails(machineTestParaMstVO, _UserVO);
}
public Map displayDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.displayDetails(machineTestParaMstVO, _UserVO);
}
public void savemodifyMachineTestParaCombo(List<MachineTestParameterMasterVO> machinetestparacombo_listVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyMachineTestParaCombo(machinetestparacombo_listVO,_UserVO);
}





public void saveTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveTemplate(templateMstVO,_UserVO);
}
public void fetchModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.fetchModifyTemplate(templateMstVO,_UserVO);
}
public void saveModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveModifyTemplate(templateMstVO,_UserVO);
}










////INVESTIGATIGATION MACHINE MASTER START ///


public void saveMachine(List<machineMstVO> lstVO,machineMstVO machine_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveMachine(lstVO,machine_VO,_UserVO);
}

public void saveModifyMachine(List<machineMstVO> lstVO,machineMstVO machine_VO,List deleteList, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveModifyMachine(lstVO,machine_VO,deleteList,_UserVO);
}



public Map getEssentialMachineCombo(machineMstVO machine_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getEssentialMachineCombo(machine_VO, _UserVO);
}


public List<machineMstVO> fetchMachineDetails(machineMstVO machine_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchMachineDetails(machine_VO, _UserVO);
}


public Map getMachineMstCombo(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getArea(labcollectionarea_VO, _UserVO);
}
////INVESTIGATIGATION MACHINE MASTER END ///


//test group info master
public void modifyTemplateValue(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.modifyTemplateValue(testgroupinfo_VO,_UserVO);
}

public void modifyTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.modifyTemplateValueLocal(testgroupinfo_VO,_UserVO);
}

/*public Map getTemplateLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getTemplateLocal(testgroupinfolocal_VO, _UserVO);
}*/



//// external lab master start puneet ////
/*public void saveParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveParameter(parametermaster_VO,_UserVO);
}

public void fetchCheckListParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO =  (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.fetchCheckListParameter(parametermaster_VO, _UserVO);
}

public void fetchParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.fetchParameter(parametermaster_VO, _UserVO);
}

public void savemodifyParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyParameter(parametermaster_VO,_UserVO);
}*/

//////external lab master ends////////


//collection area sample no generation
public Map getAreaWiseLab(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getAreaWiseLab(collareasamplenoconfig_VO,_UserVO);
}


//FILM MASTER START
public Map fetchFilmD(FilmMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchFilmD(testNewMasterVO, _UserVO);
}

public void saveFilm(FilmMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveFilm(testNewMasterVO,_UserVO);
}

public Map fetchFilm(FilmMstVO testNewMasterVO, UserVO _UserVO,String filmid,String slno)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchFilm(testNewMasterVO, _UserVO,filmid,slno);
}

public void savemodifyFilm(FilmMstVO testNewMasterVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyFilm(testNewMasterVO,_UserVO);
}

//FILM MASTER END
//Lab Consumable MST By Jatin 01-08-2016
public void saveDetailsLabConsumable(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveDetailsLabConsumable(labConsumableMstVO,_UserVO);
}
public Map fetchDetails(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchDetails(labConsumableMstVO,_UserVO);
}
public int checkDuplicateForModify(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.checkDuplicateForModify(labConsumableMstVO,_UserVO);
}
//Item Lab Test Mapping Mst
public Map fetchDetailsOfItems(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchDetailsOfItems(itemLabTestMappingMstVO,_UserVO);
}
public void saveDetailsModify(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveModifyDetails(labConsumableMstVO,_UserVO);
}
public Map saveDetailsModifyItemConsumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.saveDetailsModifyItemCosnumable(itemLabTestMappingMstVO,_UserVO);
}
public Map saveDetailsItemConsumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.saveDetailsItemCosnumable(itemLabTestMappingMstVO,_UserVO);
}
public Map getEssentials(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getEssentialForLabConsumables(labConsumableMstVO,_UserVO);
}
public Map getEssentialsForItemConsumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getEssentialsForItemConsumable(itemLabTestMappingMstVO,_UserVO);
}
public Map lotDuplicacyCheck(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.lotDuplicacyCheck(itemLabTestMappingMstVO,_UserVO);
}
public List getItemListCombo(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getItemListCombo(itemLabTestMappingMstVO,_UserVO);
}
//Lab Item Mapping Master
public Map getEssentialsForLabItemMapping(LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getEssentialForLabItemMapping(labItemMappingMasterVO, _UserVO);
}
public Map getItemsList(LabItemMappingMasterVO testgroupinfolocal_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getLabItemsMappingList(testgroupinfolocal_VO, _UserVO);
}
public  Map saveNewItemsList(LabItemMappingMasterVO labItemMappingMasterVO, ArrayList deleteList,ArrayList insertList,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.saveNewItemsList(labItemMappingMasterVO, deleteList, insertList, _UserVO);
	
}

//Organic master start


public void saveOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveOrganism(testNewMasterVO,_UserVO);
}

public Map fetchOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchOrganism(testNewMasterVO, _UserVO,namecode);
}

public void savemodifyOrganism(invOrganicMstVO testNewMasterVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyOrganism(testNewMasterVO,_UserVO);
}

//end



//Antibiotic master start


public void saveAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveAntibiotic(testNewMasterVO,_UserVO);
}

public Map fetchAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO,String namecode)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchAntibiotic(testNewMasterVO, _UserVO,namecode);
}

public void savemodifyAntibiotic(invAntibioticMstVO testNewMasterVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyAntibiotic(testNewMasterVO,_UserVO);
}

//end


// inv organism antibiotic mapping mst 
public void saveOrganicAntibiotic(invOrganicAntibioticMappingMstVO[] insert_labcannedmaster_VO,invOrganicAntibioticMappingMstVO[] delete_labcannedmaster_VO, UserVO _UserVO,invOrganicAntibioticMappingMstVO[] update)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveOrganicAntibiotic(insert_labcannedmaster_VO,delete_labcannedmaster_VO,_UserVO,update);
}

public  Map fetchOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchOrganicAntibiotic(testNewMasterVO, _UserVO,namecode);
}


/*public  Map fetchOrganicAntibioticD(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchOrganicAntibiotic(testNewMasterVO, _UserVO,namecode);
}*/

public  void savemodifyOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyOrganicAntibiotic(testNewMasterVO, _UserVO);
}

public Map fetchOrganicAntibioticD1(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchOrganicAntibioticD1(testNewMasterVO, _UserVO);
}


public Map getGlobalLabCanned(invOrganicAntibioticMappingMstVO labcannedmaster_VO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getGlobalLabCanned(labcannedmaster_VO, _UserVO);
}

public  Map fetchGlobalLabCanned(invOrganicAntibioticMappingMstVO labcannedmaster_VO, UserVO _UserVO)
{
	InvestigationMasterBOi masterDelegate = (InvestigationMasterBOi) super.getServiceProvider();
	return masterDelegate.fetchGlobalLabCanned(labcannedmaster_VO, _UserVO);
}

//end


// addendum reason mst
public void saveAddendum(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveAddendum(testNewMasterVO,_UserVO);
}


public Map fetchAddendum(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO,String namecode)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchAddendum(testNewMasterVO, _UserVO,namecode);
}


public void savemodifyAddendum(invAddendumReasonMstVO testNewMasterVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.savemodifyAddendum(testNewMasterVO,_UserVO);
}

public void saveTestParameterMasterForm(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveTestParameterMasterForm(testParameterMasterVO,_UserVO);
}

public Map fetchParameterComboReqform( UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchParameterComboReqForm( _UserVO);
}


public void updateTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.updateTestParameter(testParameterMasterVO,_UserVO);
}


public List getTestgroup(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getTestgroup(userBookMarkMstVO, _UserVO);
}


/*Userwise BookMark Master By Prashant*/
public Map getEssential(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getEssential(userwiseBookMarkMstVO, _UserVO);
}

public void saveUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,List<UserwiseBookMarkMstVO> lstUserwiseBookMarkVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.saveUserBookMark(userwiseBookMarkMstVO,lstUserwiseBookMarkVO,_UserVO);
}
public Map fetchModifyUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.fetchModifyUserBookMark(userwiseBookMarkMstVO, _UserVO);
}
public Map getBookMarkType(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getBookMarkType(userwiseBookMarkMstVO, _UserVO);
}

public List getTest(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getTest(userwiseBookMarkMstVO, _UserVO);
}

public List<UserwiseBookMarkMstVO> getTestByGroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getTestByGroup(userwiseBookMarkMstVO, _UserVO);
}

public void modifySaveUserBookmark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.modifySaveUserBookmark(userwiseBookMarkMstVO,_UserVO);
}
public void getTestName(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	serviceBO.getTestName(userwiseBookMarkMstVO,_UserVO);}


public List getTestgroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
{
	InvestigationMasterBOi serviceBO = (InvestigationMasterBOi) super.getServiceProvider();
	return serviceBO.getTestgroup(userwiseBookMarkMstVO, _UserVO);
}

}