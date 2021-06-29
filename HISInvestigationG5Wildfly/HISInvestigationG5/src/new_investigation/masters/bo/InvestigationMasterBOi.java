package new_investigation.masters.bo;

import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import new_investigation.vo.TestGroupMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.TestMandatoryMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.TestParameterMasterVO;
import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.UserBookMarkMstVO;
import new_investigation.vo.UserwiseBookMarkMstVO;
import new_investigation.vo.invAddendumReasonMstVO;
import new_investigation.vo.invAntibioticMstVO;
import new_investigation.vo.invOrganicAntibioticMappingMstVO;
import new_investigation.vo.invOrganicMstVO;
import new_investigation.vo.machineMstVO;
public interface InvestigationMasterBOi
{



	// Sample Master///


	public void saveCheckList(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO);
	public Map fetchCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	public void savemodifyCheckList(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO);
	public Map fetchSample(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO);
	// UOM Master///
	public void saveUOM(UOMMasterVO uOMMasterVO,UserVO _UserVO);
	public Map fetchUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO);
	public void savemodifyUOM(UOMMasterVO uOMMasterVO,UserVO _UserVO);	
	public Map fetchUOMD(UOMMasterVO uOMMasterVO, UserVO _UserVO);



	// Rejection Reason Master(add by yogender)///

	public void saveRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO);
	public void fetchCheckListRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO);
	public void fetchRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO);
	public void savemodifyRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO);


	// Macro MASTER(added by yogender yadav) //

	public void saveMacro(MacroMasterVO macromaster_VO,UserVO _UserVO);
	public void fetchCheckListMacro(MacroMasterVO macromaster_VO, UserVO _UserVO);
	public void fetchMacro(MacroMasterVO macromaster_VO, UserVO _UserVO);
	public void savemodifyMacro(MacroMasterVO macromaster_VO,UserVO _UserVO);

	// Global Test  Group Master(add by yogender)///

	public void saveTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);
	public void modifyTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
	public void fetchTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
	public void updateTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);

	// Local Test  Group Master(add by yogender)///

	public void saveLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);
	public void modifyLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
	public Map fetchCheckListLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
	public Map fetchLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
	public void updateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);
	public void fetchdisplaydataLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);


	// Canned Master(add by yogender)///

	public void saveCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO);
	public void modifyCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO);
	public void fetchCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO);
	public void updateCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO);


	// Lab Macro Local Mapping Master(add by yogender)///
	public void saveLabMacroLocalMap(LabMacroMapMasterVO[] insert_labmacromap_VO,LabMacroMapMasterVO[] delete_labmacromap_VO,UserVO _UserVO,LabMacroMapMasterVO[] modify_labmacromap_VO);
	public Map fetchLabMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);	
	public Map getLocalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	/*public void fetchdisplaydataMacroLocalMap(LabMacroMapMasterVO labmacromapmaster_VO, UserVO _UserVO);*/

	// Lab Macro Global Mapping Master(add by yogender)///
	public void saveLabMacroGlobalMap(LabMacroMapMasterVO[] insert_labmacromap_VO,LabMacroMapMasterVO[] delete_labmacromap_VO,UserVO _UserVO);
	public Map fetchLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);	
	public Map getGlobalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);


	// Lab Local Canned Master(add by yogender)///
	public void saveLocalLabCanned(LabCannedMasterVO[] insert_labmacromap_VO,LabCannedMasterVO[] delete_labmacromap_VO,UserVO _UserVO,LabCannedMasterVO[] modify_labmacromap_VO);
	public Map fetchLocalLabCanned(LabCannedMasterVO labmacromap_VO, UserVO _UserVO);	
	public Map getLocalLabCanned(LabCannedMasterVO labmacromap_VO, UserVO _UserVO);
	/*public void fetchdisplaydataMacroLocalMap(LabCannedMasterVO labmacromapmaster_VO, UserVO _UserVO);*/

	// Lab Global Canned Master Master(add by yogender)///
	public void saveGlobalLabCanned(LabCannedMasterVO[] insert_labmacromap_VO,LabCannedMasterVO[] delete_labmacromap_VO,UserVO _UserVO);
	public Map fetchGlobalLabCanned(LabCannedMasterVO labmacromap_VO, UserVO _UserVO);	
	public Map getGlobalLabCanned(LabCannedMasterVO labmacromap_VO, UserVO _UserVO);

	
	public void saveLab(LabMasterVO labMasterVO,UserVO _UserVO);
	public Map fetchLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void savemodifyLab(LabMasterVO labMasterVO,UserVO _UserVO);		
	public Map fetchLabD(LabMasterVO labMasterVO, UserVO _UserVO);




	// LAB TEST GLOBAL MASTER By Anant Patel///
	public Map fetchLabTest(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO);
	public void saveLabTestGlobalTest(LabTestGlobalMstVO labTestMasterVO,UserVO _UserVO); 
	public void savemodifyLabTest(LabTestGlobalMstVO labTestMasterVO,UserVO _UserVO);
	public Map fetchModifyLabTestGlobal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO);
	// LAB TEST LOCAL MASTER By Anant Patel///
	public Map fetchLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO);
	public void saveLabTestLocalTest(LabTestGlobalMstVO labTestMasterVO,UserVO _UserVO);
	public Map fetchModifyLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO);
	public void savemodifyLabTestLocal(LabTestGlobalMstVO labTestMasterVO,UserVO _UserVO);
	/* LAB TEST SAMPLE GLOBAL MASTER By Anant Patel*/
	
	public Map fetchLabTestSampleEssential(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO);
	public void saveLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO);
	public Map fetchModifyLabTestGlobal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO);
	public void saveModifyLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO);	
	public List getUOM(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO);
	public List getSampleQty(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO);
	public String getContainerValues(String containerCode,UserVO _UserVO);
	/* LAB TEST SAMPLE LOCAL MASTER By Anant Patel */

	public Map fetchLabTestLocalSampleEssential(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO);
	public void saveLabTestLocalSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO);
	public Map fetchModifyLabTestLocal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO);
	public void saveModifyLabTestLocalSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO);	
	public Map fetchGlobalData(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO);
	/* Test Mandatory GLobal Master By Anant Patel */

	public Map fetchTestMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO);
	public Map getMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO);
	public void saveTestMandatory(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO,UserVO _UserVO);

	/* Test Mandatory Local Master By Anant Patel */

	public Map fetchTestMandatoryLocal(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO);
	public Map getMandatoryLocal(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO);
	public void saveTestMandatoryLocal(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO,UserVO _UserVO);

	// LAB TEST Parameter Master///
	public void saveTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);
	public Map fetchTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);
	public void savemodifyTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);		
	public Map fetchParameterCombo(TestParameterMasterVO testParameterMasterVO,	UserVO _UserVO);
	public Map TestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);
	/*public Map ajaxUrlCombo(TestParameterMasterVO testNewMasterVO, UserVO _UserVO);*/



	// Test Master///

	public void saveTest(TestNewMasterVO testNewMasterVO,UserVO _UserVO);
	public Map fetchTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO);
	public void savemodifyTest(TestNewMasterVO testNewMasterVO,UserVO _UserVO);
	public Map fetchTestD(TestNewMasterVO testNewMasterVO, UserVO _UserVO);	


	// PARAMETER MASTER //

	public void saveParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO);
	public void fetchCheckListParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO);
	public void savemodifyParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO);

	// MANDATORY MASTER //

	public void saveMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO);
	public void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public void fetchMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO);
	public void savemodifyMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO);

	// MANDATORY COMBO MASTER //

	public void saveMandatoryCombo(List<MandatoryComboMasterVO> lstMandatoryComboVO,UserVO _UserVO);
	public Map fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public Map fetchMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO);
	public void savemodifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO,UserVO _UserVO);


	// LAB TEST SAMPLE Master///
	///public void saveTestSample(TestSampleMasterVO testSampleMasterVO,UserVO _UserVO);
	//public Map fetchTestSample(TestSampleMasterVO testSampleMasterVO, UserVO _UserVO);
	///public void savemodifyTestSample(TestSampleMasterVO testSampleMasterVO,UserVO _UserVO);		
	//public Map fetchTestSampleD(TestSampleMasterVO testSampleMasterVO, UserVO _UserVO);	

	// CONTAINER MASTER///

	public void saveContainer(ContainerMasterVO container_VO,UserVO _UserVO);
	public Map fetchCheckListContainer(ContainerMasterVO container_VO, UserVO _UserVO);
	public void savemodifyContainer(ContainerMasterVO container_VO,UserVO _UserVO);
	public Map fetchContainer(ContainerMasterVO container_VO, UserVO _UserVO);

	// COLLECTION AREA MASTER///

	public void saveCollectionArea(CollectionAreaMasterVO collectionarea_VO,UserVO _UserVO);
	public Map fetchCheckListCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO);
	public void savemodifyCollectionArea(CollectionAreaMasterVO collectionarea_VO,UserVO _UserVO);
	public Map fetchCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO);

	// TEST SAMPLE MASTER///

	public void saveInvTestSample(InvTestSampleMasterVO testsample_VO,UserVO _UserVO);
	public Map fetchCheckListInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public void savemodifyInvTestSample(InvTestSampleMasterVO testsample_VO,UserVO _UserVO);
	public Map fetchInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);

	// TEST PARA COMBO MASTER //

	public void saveTestParaCombo(List<TestParaComboMasterVO> lstTestParaComboVO,UserVO _UserVO);
	public Map fetchCheckListTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public List<TestParaComboMasterVO> fetchdisplaydataTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public Map fetchTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);
	public void savemodifyTestParaCombo(TestParaComboMasterVO testparacombo_VO,UserVO _UserVO);
	public Map fetchParameterCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO);

	// LAB COLLECTION AREA MASTER///

	public void saveLabCollectionArea(LabCollectionAreaMasterVO[] insert_labcollectionarea_VO,LabCollectionAreaMasterVO[] delete_labcollectionarea_VO,UserVO _UserVO);
	public Map fetchLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);	
	public Map getArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);				  

	// TEST GROUP INFO MASTER///

	public void saveTestGroupInfo(TestGroupInfoMasterVO[] insert_testgroupinfo__VO,TestGroupInfoMasterVO[] delete_testgroupinfo__VO,UserVO _UserVO);
	public Map fetchTestGroupInfo(TestGroupInfoMasterVO testgroupinfo__VO, UserVO _UserVO);	
	public Map getTest(TestGroupInfoMasterVO testgroupinfo__VO, UserVO _UserVO);
	

	// TEST GROUP INFO LOCAL MASTER///

	public void saveTestGroupInfoLocal(TestGroupInfoMasterVO[] insert_testgroupinfolocal__VO,TestGroupInfoMasterVO[] delete_testgroupinfolocal__VO,UserVO _UserVO,TestGroupInfoMasterVO[] modify_testgroupinfolocal_VO);
	public Map fetchTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal__VO, UserVO _UserVO);	
	public Map getTestLocal(TestGroupInfoMasterVO testgroupinfolocal__VO, UserVO _UserVO);


	// Lab No Config///
	public void saveLabNoConfig(LabNoConfigMasterVO labnoconfig_VO,UserVO _UserVO);
	public Map fetchCheckListLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public void savemodifyLabNoConfig(LabNoConfigMasterVO labnoconfig_VO,UserVO _UserVO);
	public Map fetchLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO);
	public Map getTest(LabNoConfigMasterVO labnoconfig_VO,UserVO _UserVO);

	
///	SAMPLE No Config///
	public void saveSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO);
	public Map fetchCheckListSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public void savemodifySampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO);
	public Map fetchSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO);
	public Map getTest(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO);


	//public Map  TestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO);

	
	                     /*USER BOOKMARK MASTER*/
	public Map getEssential(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO);
	public void saveUserBookMark(UserBookMarkMstVO userBookMarkMstVO,List<UserBookMarkMstVO> lstUserBookMarkVO,UserVO _UserVO);
	public Map fetchModifyUserBookMark(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO);
	public List getUnit(String deptCode,UserVO _UserVO);
	public List getTest(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO);
	public List getTestByGroup(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO);
	public void modifySaveUserBookmark(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO);
	public void getTestName(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO);
	public Map getBookMarkType(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO);
	// LOCAL LAB MASTER
	
	public void saveLocalLab(LabMasterVO labMasterVO,UserVO _UserVO);
	public Map fetchLocalLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void savemodifyLocalLab(LabMasterVO labMasterVO,UserVO _UserVO);		
	public Map fetchLocalLabD(LabMasterVO labMasterVO, UserVO _UserVO);
	public Map Populate(LabMasterVO labMasterVO, UserVO _UserVO);
	public Map fetchGlobalLabCombo(LabMasterVO labMasterVO, UserVO _UserVO);

	
	/* Lab Configrator Master By Anant Patel*/
	
	public Map fetchLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO);
	public Map populate(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO);
	public Map getTestByLabCode(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO);
	public void saveLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO);
	public Map fetchModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO);
	public void saveModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO, List deleteTest, List deleteSample, List deleteMand, List deleteCanned,List deleteMacro);
	
	///	COLL AREA SAMPLE No Config///
		public void saveCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO);
		public Map fetchCheckListCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
		public void savemodifyCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO);
		public Map fetchCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO);
		public Map getArea(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO);

		/* BookMark Master By Anant Patel*/
		public void saveBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO);
		public void fetchModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO);
		public void saveModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO);
		
		
		// TEST MANDATORY REFERENCE MASTER //

		public void saveTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO,UserVO _UserVO);
		public Map fetchCheckListTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public List<TestMandRefMasterVO> fetchdisplaydataTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public Map fetchTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public void savemodifyTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO,UserVO _UserVO);
		public Map fetchParameterCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public Map fetchCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public List fetchOld(String testCode, String parameterCode, UserVO _UserVO);
		public void deleteTestMandRef(List deleteList, String testCode, String parameterCode,UserVO _UserVO);
		public List getLabSample(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public Map fetchCheckListLocalTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO);


		/*Loinc Master By Anant Patel*/
		public Map fetchLoincMst(LoincMstVO loincMstVO,UserVO _UserVO);
		public Map fetchTestPara(LoincMstVO loincMstVO,UserVO _UserVO);
		//public Map fetchTestUOMData(LoincMstVO loincMstVO,UserVO _UserVO);
		public Map getLoinc(LoincMstVO loincMstVO,UserVO _UserVO);
		public void saveLoinc(LoincMstVO loincMstVO,UserVO _UserVO);
		public Map getSearch(LoincMstVO loincMstVO,UserVO _UserVO);
		public Map fetchModifyLoinc(LoincMstVO loincMstVO,UserVO _UserVO);
		public void saveModifyLoinc(LoincMstVO loincMstVO,UserVO _UserVO);
		
		// LOCAL TEST MANDATORY REFERENCE MASTER //

		public void saveLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO,UserVO _UserVO);
		public Map fetchCheckListLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public List<LocalTestMandRefMasterVO> fetchdisplaydataLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public Map fetchLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public void savemodifyLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO,UserVO _UserVO);
		public Map fetchParameterCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public Map fetchCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);
		public List fetchOldLocal(String testCode, String parameterCode, UserVO _UserVO);
		public void deleteLocalTestMandRef(List deleteList, String testCode, String parameterCode,UserVO _UserVO);
		public List getLabSample(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO);


		//MACHINE TEST PARAMETER MASTER by Akshita Topre
		
		public Map fetchParaMachineCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
		public Map fetchParaTestCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
		public List<MachineTestParameterMasterVO> displayParameterDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
		public void saveDetails(List<MachineTestParameterMasterVO> machineTestParamMst_listVO,UserVO _UserVO);
		public Map fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
		public Map displayDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);

		public void savemodifyMachineTestParaCombo(List<MachineTestParameterMasterVO> machinetestparacombo_listVO,UserVO _UserVO);
		//public Map fetchCheckListMachineTestParaCombo(MachineTestParameterMasterVO testparacombo_VO, UserVO _UserVO);
		
		// MACHINE MASTER///

		public void saveMachine(List<machineMstVO> lstVO,machineMstVO machine_VO,UserVO _UserVO);
		public void saveModifyMachine(List<machineMstVO> lstVO,machineMstVO machine_VO,List deleteList,UserVO _UserVO);

		public Map getEssentialMachineCombo(machineMstVO machinemst_vo, UserVO _UserVO);	
	//	public Map getMachineCombo(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);
		public List<machineMstVO> fetchMachineDetails(machineMstVO machinemst_vo, UserVO _UserVO);	



		
		public void saveTemplate(TemplateMstVO templateMstVO,UserVO _UserVO);
		public void fetchModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO);
		public void saveModifyTemplate(TemplateMstVO TemplateMstVO,UserVO _UserVO);
		
		//test group info master
		public void modifyTemplateValue(TestGroupInfoMasterVO testgroupinfo__VO,UserVO _UserVO);
		public void modifyTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo__VO,UserVO _UserVO);

		//public Map getTemplateLocal(TestGroupInfoMasterVO testgroupinfolocal__VO, UserVO _UserVO);

		// external lab master -puneet //

		/*public void saveParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO);
		public void fetchCheckListParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO);
		public void fetchParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO);
		public void savemodifyParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO);*/

		//collection area sample no generation
		public Map getAreaWiseLab(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO);

		//FILM MASTER START
		public Map fetchFilmD(FilmMstVO testNewMasterVO, UserVO _UserVO);	
        
		public void saveFilm(FilmMstVO testNewMasterVO,UserVO _UserVO);
        		
		public Map fetchFilm(FilmMstVO testNewMasterVO, UserVO _UserVO,String filmid,String slno);
		
		public void savemodifyFilm(FilmMstVO testNewMasterVO,UserVO _UserVO);
		
		//FILM MASTER END

		//Lab Consumable Master
		public void saveDetailsLabConsumable(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO);
		public Map fetchDetails(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO);
		public int checkDuplicateForModify(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO);
		
		public void saveModifyDetails(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO);
		
		public Map fetchDetailsOfItems(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO);
		
		public Map saveDetailsModifyItemCosnumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO);
		
		public Map saveDetailsItemCosnumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO);
		
		public Map getEssentialForLabConsumables(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO);
		
		public Map getEssentialsForItemConsumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO);
		
		public Map lotDuplicacyCheck(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO);
		
		public List getItemListCombo(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO);
		
		//Lab Item Mapping Master
		public Map getEssentialForLabItemMapping(LabItemMappingMasterVO labItemMappingMasterVO,UserVO _UserVO);
		
		public Map getLabItemsMappingList(LabItemMappingMasterVO testgroupinfolocal__VO, UserVO _UserVO);
		
		public  Map saveNewItemsList(LabItemMappingMasterVO labItemMappingMasterVO, ArrayList deleteList,ArrayList insertList,UserVO _UserVO);

		//organic MASTER START
				public Map fetchiOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO);	
		        
				public void saveOrganism(invOrganicMstVO testNewMasterVO,UserVO _UserVO);
		        		
				public Map fetchOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode);
				
				public void savemodifyOrganism(invOrganicMstVO testNewMasterVO,UserVO _UserVO);
				
				//organic MASTER END



				//Antibiotic MASTER START
				public Map fetchiAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO);	
		        
				public void saveAntibiotic(invAntibioticMstVO testNewMasterVO,UserVO _UserVO);
		        		
				public Map fetchAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO,String namecode);
				
				public void savemodifyAntibiotic(invAntibioticMstVO testNewMasterVO,UserVO _UserVO);
				
				//Antibiotic MASTER END

				
				//OrganicAntibiotic MASTER START
				public Map fetchiOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO);	
		        
				public void saveOrganicAntibiotic(invOrganicAntibioticMappingMstVO[] insert_labmacromap_VO,invOrganicAntibioticMappingMstVO[] delete_labmacromap_VO,UserVO _UserVO,invOrganicAntibioticMappingMstVO[] update);
		
		  
				public Map fetchOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode);
				
				public void savemodifyOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO,UserVO _UserVO);
				
				public Map fetchOrganicAntibioticD1(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO);
				
				public Map getGlobalLabCanned(invOrganicAntibioticMappingMstVO labmacromap_VO, UserVO _UserVO);
				
				public Map fetchGlobalLabCanned(invOrganicAntibioticMappingMstVO labmacromap_VO, UserVO _UserVO);	
				//OrganicAntibiotic MASTER END
				

				
				// addendum reason mst
				public void saveAddendum(invAddendumReasonMstVO testNewMasterVO,UserVO _UserVO);
        		
				public Map fetchAddendum(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO,String namecode);
				
				public void savemodifyAddendum(invAddendumReasonMstVO testNewMasterVO,UserVO _UserVO);

				public void saveTestParameterMasterForm(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);
          
				public Map fetchParameterComboReqForm( 	UserVO _UserVO);
				
				public void updateTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO);
				
				public List getTestgroup(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO);

				/*USERWISE BOOKMARK MASTER*/
				public Map getEssential(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO);
				public void saveUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,List<UserwiseBookMarkMstVO> lstUserwiseBookMarkVO,UserVO _UserVO);
				public Map fetchModifyUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO);
				//public List getUnit(String deptCodeee,UserVO _UserVOO);
				public List getTest(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO);
				public List getTestByGroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO);
				public void modifySaveUserBookmark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO);
				public void getTestName(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO);
				public Map getBookMarkType(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO);
				public List getTestgroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO);

				
				
				
}





