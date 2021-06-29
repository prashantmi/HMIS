package opd.transaction.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.fb.OpdImageEditorFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdImageEditorACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward SHOW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	// Calling Image Editor
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = WebUTIL.getSession(request);
		OpdImageEditorFB getFB = null;
		OpdImageEditorFB fb = (OpdImageEditorFB) form;
		try
		{
			getFB = (OpdImageEditorFB) session.getAttribute(OpdConfig.OPD_IMAGE_EDITOR_FB);
			fb.setInFileName(getFB.getInFileName());
			fb.setOutFileName(getFB.getOutFileName());
			fb.setInFilePath(getFB.getInFilePath());
			fb.setOutFilePath(getFB.getOutFilePath());
			fb.setTitle(getFB.getTitle());
			fb.setControlDesc(getFB.getControlDesc());
			fb.setColorDesc(getFB.getColorDesc());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//--------- Incomplete Information Handling
		
		
		
		if (fb.getInFileName().equals(""))
		{
			fb.setInFileName("IMAGE002.bmp");
			fb.setOutFileName("imgPragya.png");
			fb.setInFilePath("inStandardpath");
			fb.setOutFilePath("outStandardPath");
			fb.setTitle("Pragya Sharma Image Editor");
			// fb.setControlDesc("F:L:E");
			fb.setColorDesc("FF0000:Injury");
		}

		return mapping.findForward("EDITOR");
	}
}
