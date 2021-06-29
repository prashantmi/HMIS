package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.delegate.invFungusProcessDelegate;
import new_investigation.vo.invFungusProcessVO;

public class invFungusProcessDATA {

	
	
	public static Map LabComboForAudit(invFungusProcessFB invinvFungusProcessFB, UserVO _UserVO)
	{
		invFungusProcessDelegate masterDelegate = new invFungusProcessDelegate();
		return masterDelegate.LabComboForAudit(invinvFungusProcessFB, _UserVO);
	}
	
	public static Map getAntibioticName(invFungusProcessVO vo, UserVO _UserVO)
	{
		invFungusProcessDelegate masterDelegate = new invFungusProcessDelegate();
		return masterDelegate.getAntibioticName(vo, _UserVO);
	}
	
	public static String getxml(invFungusProcessFB invinvFungusProcessFB, UserVO _UserVO)
	{
		invFungusProcessVO vo=new invFungusProcessVO();
		invFungusProcessDelegate masterDelegate = new invFungusProcessDelegate();
		return masterDelegate.getxml(vo, _UserVO);
	}
	
}
