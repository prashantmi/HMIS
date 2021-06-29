package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.delegate.invAntibioticProcessDelegate;
import new_investigation.vo.invAntibioticProcessVO;

public class invAntibioticProcessDATA {

	
	
	public static Map LabComboForAudit(invAntibioticProcessFB invinvAntibioticProcessFB, UserVO _UserVO)
	{
		invAntibioticProcessDelegate masterDelegate = new invAntibioticProcessDelegate();
		return masterDelegate.LabComboForAudit(invinvAntibioticProcessFB, _UserVO);
	}
	
	public static Map getAntibioticName(invAntibioticProcessVO vo, UserVO _UserVO)
	{
		invAntibioticProcessDelegate masterDelegate = new invAntibioticProcessDelegate();
		return masterDelegate.getAntibioticName(vo, _UserVO);
	}
	
	public static String getxml(invAntibioticProcessFB invinvAntibioticProcessFB, UserVO _UserVO)
	{
		invAntibioticProcessVO vo=new invAntibioticProcessVO();
		invAntibioticProcessDelegate masterDelegate = new invAntibioticProcessDelegate();
		return masterDelegate.getxml(vo, _UserVO);
	}
	
}
