/**
 * 
 */
package ipd.transactions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;
import ipd.transactions.NursingDeskFinalTransUTL;

/**
 * @author pankaj
 * 
 */
public class NursingDeskFinalTransCNT extends GenericController {
	//static NursingDeskFinalTransUTL masterObj = new NursingDeskFinalTransUTL();

	public NursingDeskFinalTransCNT() {
		super(new NursingDeskFinalTransUTL(), "/transactions/NursingDeskFinalTransCNT");
	}

	public ActionForward CANCELPAGE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	public ActionForward MOVEMENT(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientTransferTransBSCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward BELONGING(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatBelongingTransCNT.cnt?hmode=ADDNURSINGDESK&belMode=1");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public ActionForward ISSUEDITEMS(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatBelongingTransCNT.cnt?hmode=ADDNURSINGDESK&belMode=2");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward BELONGINGRETURN(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatBelongingTransCNT.cnt?hmode=ADDRETURNNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward LEAVEREQUEST(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveRequestTransCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward DEATHNOTIFICATION(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/DeathNotificationTransCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward LEAVERECORD(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveJoinRecordTransCNT.cnt?hmode=ADDLEAVENURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward JOINRECORD(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveJoinRecordTransCNT.cnt?hmode=ADDJOINNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward ACCEPTANCE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskTransCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward NOTREPORTING(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskTransCNT.cnt?hmode=NOTREPORTINGNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward NOTREPORTED(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskTransCNT.cnt?hmode=NOTREPORTEDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward OFFLINELEAVE(ActionMapping _mapping, ActionForm form,
			HttpServletRequest _request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public ActionForward BEDSTATUS(ActionMapping _mapping, ActionForm form,
			HttpServletRequest _request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/IPDCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public ActionForward FINALDISCHARGE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientFinalDischargeDeskTransCNT.cnt?hmode=GO");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
