package mortuary.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.DeceasedTileFB;
import mortuary.transaction.controller.utl.DeceasedTileUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedTileACT extends DispatchAction
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedTileFB fb=(DeceasedTileFB)form;
		DeceasedTileUTL.getDeceasedDetailByDeceasedNo(fb,request);
		
		return mapping.findForward("NEW");
	}
}
