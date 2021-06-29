/**
 * 
 */
package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author pankaj
 *
 */
public class LeaveDeskTransCNT extends GenericController  {
	static LeaveDeskTransUTL masterObj = new LeaveDeskTransUTL();

	public LeaveDeskTransCNT() {
		super(masterObj, "/transactions/LeaveDeskTransCNT");
	}

	public ActionForward CANCELPAGE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward LEAVEREQUEST(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveRequestTransCNT.cnt?hmode=ADDLEAVEDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward LEAVERECORD(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveJoinRecordTransCNT.cnt?hmode=ADDLEAVEONLEAVEDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward JOINRECORD(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveJoinRecordTransCNT.cnt?hmode=ADDJOINLEAVEDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward OFFLINELEAVE(ActionMapping _mapping, ActionForm form,
			HttpServletRequest _request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveCNT.cnt?hmode=ADDLEAVEDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
