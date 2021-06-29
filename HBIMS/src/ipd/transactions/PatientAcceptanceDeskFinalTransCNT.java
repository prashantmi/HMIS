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


/**
 * @author Anurudra Goel
 * 
 */
public class PatientAcceptanceDeskFinalTransCNT extends GenericController {
	static PatientAcceptanceDeskFinalTransUTL masterObj = new PatientAcceptanceDeskFinalTransUTL();

	public PatientAcceptanceDeskFinalTransCNT() {
		
		super(masterObj, "/transactions/PatientAcceptanceDeskFinalTransCNT");
	}

	public ActionForward CANCELPAGE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	/**
	 * This function is used to forward control to acceptance mode of NursingDeskTransCNT.cnt  
	 * @param _mapping
	 * @param _form
	 * @param _request
	 * @param _response
	 * @return
	 * @throws HisException
	 */
	public ActionForward ACCEPTANCE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		generateToken(_request);
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/ipd/transactions/NursingDeskTransCNT.cnt?hmode=ADDACCEPTANCEDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	/**
	 * This function is used to forward control to report mode of NursingDeskTransCNT.cnt  
	 * @param _mapping
	 * @param _form
	 * @param _request
	 * @param _response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward NOTREPORTING(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskTransCNT.cnt?hmode=NOTREPORTINGACCEPTANCEDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	
}
