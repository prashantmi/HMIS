package inpatient.reports.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.ArrayList;
import java.util.List;

import mrd.transaction.delegate.MrdEssentialDelegate;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class ListOfCaseSheetDATA extends ReportDATA
{

	public static List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}
	public static List getAllConsultantDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List consultantDetails = new ArrayList();
		consultantDetails = essentialDelegate.getAllConsultantDetails(unitCode,_UserVO);
		return consultantDetails;
	}
	public static List getDeptUnitList(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
}
