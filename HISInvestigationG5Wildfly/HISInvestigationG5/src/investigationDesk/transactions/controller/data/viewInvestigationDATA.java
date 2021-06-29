package investigationDesk.transactions.controller.data;

import hisglobal.vo.UserVO;
import investigationDesk.transactions.delegate.InvestigationEssentialDelegate;
import java.util.Map;













public class viewInvestigationDATA
{
  public static Map getReqData(String CrNo, UserVO userVO, String fromwhichcall) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getReqData(CrNo, userVO, fromwhichcall);
  }
}
