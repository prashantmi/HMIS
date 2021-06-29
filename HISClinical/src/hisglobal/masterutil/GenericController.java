package hisglobal.masterutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GenericController extends DispatchAction {

	MasterInterface masterObj = null;
 
	String cnt_page = "";

	//String combo[] = null;

	// Logger logger = Logger.getLogger(getClass());
	StringBuffer br = new StringBuffer(1000);

	public GenericController() 
	{
	}

	public GenericController(MasterInterface masterObj,String cnt_page)
	{
		this.masterObj = masterObj;		
		this.cnt_page = cnt_page;
	}

	public final ActionForward unspecified(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		GenericData.unspecified(request, masterObj);
		
		return this.LIST(mapping, form, request, response);
	}

	public final ActionForward LIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		GenericData.LIST(request, masterObj, cnt_page);
		ActionForward actFrw = new ActionForward();	
		actFrw.setPath("/hisglobal/masterutil/master/lst_mstTemp_gbl.jsp");
		actFrw.setContextRelative(true);
		return actFrw;
	}

	public final ActionForward LISTPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GenericData.LISTPAGE(request, response, masterObj);
		return null;

	}
	/**
	 * Clicked on search button
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public final ActionForward SEARCH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GenericData.SEARCH(request, response, masterObj);
		
		return null;

	}

	public final ActionForward DEFAULT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		GenericData.DEFAULT(request, response, masterObj);
		return null;
	}
	
	//in report page, cancel is clicked
	public final ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return this.LIST(mapping, form, request, response);
	}

	public final ActionForward DELETE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		GenericData.DELETE(request, response, masterObj);
		return null;
	}

	public final ActionForward REPORT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("combo", request.getParameterValues("combo"));
		ActionForward actFrw = new ActionForward();
		actFrw.setPath("/hisglobal/masterutil/master/rpt_mstTemp_gbl.jsp");
		actFrw.setContextRelative(true);
    	return actFrw;
	}

	public final ActionForward VIEWDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		GenericData.VIEWDATA(request, response, masterObj);
		return null;
	}

	public final ActionForward REPORTDATA(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) 
	{
		GenericData.REPORTDATA(request, response, masterObj);
		return null;
	}
	public final ActionForward REPORTINTERFACE(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		GenericData.REPORTINTERFACE(request, response, masterObj);
		return null;
	}
	public MasterInterface getMasterObj() {
		return masterObj;
	}
}
