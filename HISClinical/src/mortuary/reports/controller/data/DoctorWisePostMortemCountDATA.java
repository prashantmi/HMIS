package mortuary.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import mortuary.masters.delegate.MortuaryEssentialDelegate;


public class DoctorWisePostMortemCountDATA extends ReportDATA
{
	public static List getAllDoctor(UserVO userVO)
	{
		MortuaryEssentialDelegate essentialDelegate= new MortuaryEssentialDelegate();
		List allDoctorList=essentialDelegate.getAllDoctor(userVO);
		return allDoctorList;
	}
	
}
