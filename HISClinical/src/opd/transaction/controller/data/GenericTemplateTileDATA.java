package opd.transaction.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import opd.bo.OpdPatientBO;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.delegate.GenericTemplateEssentialDelegate;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class GenericTemplateTileDATA extends ControllerDATA
{
	// Getting Desk Menu Template List for Current Active Desk
	public static List<Entry> getDeskMenuTemplateList(String _deskType, String _patCrNo, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		GenericTemplateEssentialDelegate delegate=new GenericTemplateEssentialDelegate();
		return delegate.getDeskMenuTemplateList(_deskType, _patCrNo, _userDeskTempVO, _userVO);		
	}

	// Getting Patient-Centric & Episode-Centric Clinical Template Data
	public static Map<String, List<Entry>> getPatientClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientClinicalData(_deskType, _patClinicalVO, _userVO);		
	}

	// Getting Patient Clinical Template Data
	public static Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientFinalClinicalData(_deskType, _patClinicalVO, _userVO);		
	}

	// Saving Patient Clinical Data 
	public static void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.savePatientClinicalDetail(_deskType, _lstPatCliDtl, _userVO);
	}

	//** Get All Template Details   
	public static Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getAllTemplateDetails(_lstTemps, _userVO);
	}

	//** Get All Template Parameters Detail   
	public static List<TemplateParameterMasterVO> getAllTemplateParametersDetail(List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getAllTemplateParametersDetail(_lstTemps, _userVO);
	}

	//** Get Report Date List 
	public static List<Entry> getReportDateList(String _deskType, PatientClinicalDetailVO _patCliDtlVO, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getReportDateList(_deskType, _patCliDtlVO, _userVO);
	}

	//** Getting Patient Chart Clinical Template Data
	public static Map<String, Map<String, Map<String, String>>> getPatientChartClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientChartClinicalData(_deskType, _patClinicalVO, _lstReportDates, _userVO);		
	}

	//** Getting Patient Chart Clinical Template Data Template Wise -old not in use
	public static Map<String, Map<String, Map<String, String>>> getPatientChartClinicalDataTempWise(String _deskType, 
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientChartClinicalDataTempWise(_deskType, _patClinicalVO, _lstReportDates, _lstTemps, _userVO);		
	}

	//** Getting Patient Chart Clinical Template Data Template Wise -old not in use
	public static Map<String, Map<String, Map<String, String>>> getPatientChartClinicalDataTempWiseforProfile(String _deskType, 
			String _profileGenerationMode, PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, UserVO _userVO)
	{
		OpdPatientBO boOPD = new OpdPatientBO();
		return boOPD.getPatientChartClinicalDataTempWiseforProfile(_deskType, _profileGenerationMode, _patClinicalVO, _lstReportDates, _userVO);		
	}

	//** Getting Patient Chart Clinical Template Data Template Wise With Para Detial
	public static Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> getPatientChartClinicalDataTempWiseWithParaDtl(String _deskType, 
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientChartClinicalDataTempWiseWithParaDtl(_deskType, _patClinicalVO, _lstReportDates, _lstTemps, _userVO);		
	}

	/*// * Getting Previous Visit Dates List
	public static List getPrevVisitDates(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getPrevVisitDates(_patCrNo, _episodeCode, _userVO);
		return lst;
	}

	// * Getting All Parameters List of given Templates
	public static List getOpdDeskTemplatesAllParas(List tempIds, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getOpdDeskTemplatesAllParas(tempIds, _userVO);
		return lst;
	}

	// * Saving Template Parameter Values in Clinical Table
	public static void saveOrReplaceOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, UserVO userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.saveOrReplaceOpdTempParaValues(clinicalDtlVO, userVO);
	}

	// * Removing Template Parameter Values in Clinical Table
	public static void removeOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, List tempIds, UserVO userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.removeOpdTempParaValues(clinicalDtlVO, tempIds, userVO);
	}

	// * Saving Template Parameter Values in Clinical Table from List
	public static void saveOpdTempParaValues(List<OpdClinicalDetailVO> lst, UserVO userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.saveOpdTempParaValues(lst, userVO);
	}

	// * Getting Clinical Template Data
	public static List getClinicalTemplateData(OpdClinicalDetailVO voClnData, UserVO _UserVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getClinicalTemplateData(voClnData, _UserVO);
		return lst;
	}


	// * Getting All Parameters List of given Templates
	public static List getOpdDeskTemplatesAllParas(List tempIds, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getOpdDeskTemplatesAllParas(tempIds, _userVO);
		return lst;
	}

	// * Getting Template Parameter Names
	public static List getOpdTemplParaNames(List paraIds, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getOpdTemplParaNames(paraIds, _userVO);
		return lst;
	}

	// * Getting Previous Visits In Between Given Dates
	public static List getPrevVisitsInBetween(String _patCrNo, String _episodeCode, String _fromDate, String _toDate,
			UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getPrevVisitsInBetween(_patCrNo, _episodeCode, _fromDate, _toDate, _userVO);
		return lst;
	}

	// * Getting OPD Clinical Data By Selected Parameters
	public static List getOpdClinDataBySelParas(OpdClinicalDetailVO voOpdCD, String qryVisits, String paraQuery,
			UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getOpdClinDataBySelParas(voOpdCD, qryVisits, paraQuery, _userVO);
		return lst;
	}*/
}
