package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.invOrganicAntibioticMappingMstVO;

public class invOrganicAntibioticMappingMstDATA {

	
	
	public static void saveOrganicAntibiotic(invOrganicAntibioticMappingMstVO[] insert_labcannedmaster_VO,invOrganicAntibioticMappingMstVO[] delete_labcannedmaster_VO,UserVO _UserVO,invOrganicAntibioticMappingMstVO[] update)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveOrganicAntibiotic(insert_labcannedmaster_VO,delete_labcannedmaster_VO, _UserVO,update);
	}

	public static Map fetchOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchOrganicAntibiotic(testNewMasterVO, _UserVO,namecode);
	}
	
	
	public static Map fetchOrganicAntibioticD(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchOrganicAntibiotic(testNewMasterVO, _UserVO,namecode);
	}
	
	public static void savemodifyOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyOrganicAntibiotic(testNewMasterVO, _UserVO);
	}
	
	public static Map fetchOrganicAntibioticD1(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchOrganicAntibioticD1(testNewMasterVO, _UserVO);
	}

	
	public static Map getGlobalLabCanned(invOrganicAntibioticMappingMstVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getGlobalLabCanned(labcannedmaster_VO, _UserVO);
	}
	
	public static Map fetchGlobalLabCanned(invOrganicAntibioticMappingMstVO labcannedmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchGlobalLabCanned(labcannedmaster_VO, _UserVO);
	}
	
	
}
