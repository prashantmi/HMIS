package new_investigation.masters.controller.data;


import java.util.List;
import java.util.Map;

import new_investigation.vo.MandatoryComboMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class MandatoryComboMstDATA
{
	public static void saveMandatoryCombo(List<MandatoryComboMasterVO> lstMandatoryComboVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveMandatoryCombo(lstMandatoryComboVO, _UserVO);
	}

	public static Map fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListMandatoryCombo(mandatorycombo_VO, _UserVO);
	}


	public static Map fetchMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchMandatoryCombo(mandatorycombo_VO, _UserVO);
	}

	public static void savemodifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyMandatoryCombo(mandatorycombo_VO, _UserVO);
	}

	public static List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchdisplaydataMandatoryCombo(mandatorycombo_VO, _UserVO);
	}


}
