/**
 * Developer : Anurudra Goel
 * Version : 1.0
 * Date : 17/July/2009
 *  
*/
package mms.reports.bo;

import mms.reports.dao.IssueToPatientDetailRptDAO;
import mms.reports.vo.IssueToPatientDetailRptVO;

public class IssueToPatientDetailRptBO {
	
public void getInitDtl(IssueToPatientDetailRptVO _IssueToPatientDetailRptVO){
		
		IssueToPatientDetailRptDAO.setStoreCombo(_IssueToPatientDetailRptVO);
		
		if (_IssueToPatientDetailRptVO.getStrMsgType().equals("1")) {
			_IssueToPatientDetailRptVO.setStrMsgString("IssueToPatientDetailRptBO.getInitDtl() --> "
					+ _IssueToPatientDetailRptVO.getStrMsgString());
		}
		
	}
public void getItemCateg(IssueToPatientDetailRptVO _IssueToPatientDetailRptVO){
		
		IssueToPatientDetailRptDAO.setItemCategCombo(_IssueToPatientDetailRptVO);
		
		if (_IssueToPatientDetailRptVO.getStrMsgType().equals("1")) {
			_IssueToPatientDetailRptVO.setStrMsgString("IssueToPatientDetailRptBO.getInitDtl() --> "
					+ _IssueToPatientDetailRptVO.getStrMsgString());
		}
		
	}


}
