package new_investigation.transactions.controller.data;

import java.util.Map;
import hisglobal.vo.UserVO;
import com.jscape.inet.ssh.util.User;

import new_investigation.transactions.delegate.viewExternalInvDelegate;
import new_investigation.vo.viewExternalInvVO;

public class viewExternalInvDATA {

	public static Map showPatDetails(viewExternalInvVO vo, UserVO _UserVO,String reqNos)
	{
		viewExternalInvDelegate masterDelegate=new viewExternalInvDelegate(); 
		return masterDelegate.showPatDetails(vo, _UserVO,reqNos);
	}

	
}
