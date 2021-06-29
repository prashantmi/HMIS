package opd.transaction.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.UserVO;

public class OpdTemplateTabDATA extends ControllerDATA
{
	// * Getting Desk Menu Template List for Current Active Desk
	public static List getOpdDeskMenuTemplates(String _unitCode, String _deskMenuId, UserVO _UserVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List lst = opdEssentialDelegate.getOpdDeskMenuTemplates(_unitCode, _deskMenuId, _UserVO);
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

	// * Getting Previous Visit Dates List
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
	}
}
