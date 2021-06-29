package medicalboard.transactions.controller.data;

import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;


public class MbRegistrationSearchDATA 

{
	public static List getPatientCategory(UserVO _UserVO)
	{
		MbTransEssentialDelegate essentialDelegate = new MbTransEssentialDelegate();
		List primaryCat = new ArrayList();
		primaryCat = essentialDelegate.getPatientCategory(_UserVO);
		return primaryCat;
	}
	public static List getCertificateType(UserVO _UserVO)
	{
		MbTransEssentialDelegate essentialDelegate = new MbTransEssentialDelegate();
		List primaryCat = new ArrayList();
		primaryCat = essentialDelegate.getCertificateType(_UserVO);
		return primaryCat;
	}
	
	public static List getOrganisation(UserVO _UserVO)
	{
		MbTransEssentialDelegate essentialDelegate = new MbTransEssentialDelegate();
		List primaryCat = new ArrayList();
		primaryCat = essentialDelegate.getOrganisationName(_UserVO);
		return primaryCat;
	}
	
	public static MedicalBoardChecklistVO[] getAllChecklistDetails(UserVO userVO)
	{
//		MbTransEssentialDelegate essentialDelegate = new MbTransEssentialDelegate();
//		return essentialDelegate.getAllChecklistDetails(userVO); 
		return null;
	}
	

}
