package inpatient.reports.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.ArrayList;
import java.util.List;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class DoctorCallBookLogReportDATA extends ReportDATA
{

	public static List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}
	
	public static List getWardListBasedOnRole(UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getWardListBasedOnRole(userVO); 
	}

	public static List getAllConsultantDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List consultantDetails = new ArrayList();
		consultantDetails = essentialDelegate.getAllConsultantDetails(unitCode,_UserVO);
		return consultantDetails;
	}
}
