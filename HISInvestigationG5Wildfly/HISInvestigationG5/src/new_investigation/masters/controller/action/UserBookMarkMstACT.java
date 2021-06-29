package new_investigation.masters.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.LabConfigratorMstFB;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.masters.controller.fb.UserBookMarkMstFB;
import new_investigation.masters.controller.utl.LabConfigratorMstUTIL;
import new_investigation.masters.controller.utl.LabTestSampleGlobalMstUTIL;
import new_investigation.masters.controller.utl.UserBookMarkMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class UserBookMarkMstACT extends GenericController {

	
	public UserBookMarkMstACT()
	{

		super(new UserBookMarkMstUTIL(),"/masters/UserBookMarkMstACT");

	}
	String mode=null;
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb = (UserBookMarkMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		UserBookMarkMstUTIL.getEssential(fb, request);
		mode=fb.getHmode();
		fb.setIsTestGroup("0");
		return mapping.findForward("ADD");
	}
	public ActionForward AJX_G_UNIT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		System.out.println("DeskHeaderACTION.AJX_G_UNIT()");
		String deptUnitCode = objRequest_p.getParameter("dept");
		System.out.println("unitCodeWithDiagCodeType :"+deptUnitCode);
		UserBookMarkMstFB objFB = (UserBookMarkMstFB) objForm_p;
		StringBuffer strBuff = UserBookMarkMstUTIL.getUnit(objFB, objRequest_p, deptUnitCode);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		System.out.println("JSON"+strBuff.toString());
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
	//method called and labcode,bookmarkcode is passed when lab is choosed to fetch all test 
	public ActionForward AJX_G_TEST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		System.out.println("DeskHeaderACTION.AJX_G_TEST()");
		String labCode = objRequest_p.getParameter("labCode");
		String bookmarkCode = objRequest_p.getParameter("bookMarkCode");
		String userId = objRequest_p.getParameter("userId");
		System.out.println("labCode :"+labCode);
		UserBookMarkMstFB objFB = (UserBookMarkMstFB) objForm_p;
		StringBuffer strBuff = UserBookMarkMstUTIL.getTest(objFB, objRequest_p, labCode,bookmarkCode,userId );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		System.out.println("JSON"+strBuff.toString());
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
	
	public ActionForward AJX_G_TESTgroup(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
			{
				System.out.println("DeskHeaderACTION.AJX_G_TEST()");
				String labCode = objRequest_p.getParameter("labCode");
				String bookmarkCode = objRequest_p.getParameter("bookMarkCode");
				String userId = objRequest_p.getParameter("userId");
				System.out.println("labCode :"+labCode);
				UserBookMarkMstFB objFB = (UserBookMarkMstFB) objForm_p;
				StringBuffer strBuff = UserBookMarkMstUTIL.getTestgroup(objFB, objRequest_p, labCode,bookmarkCode, userId);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				System.out.println("JSON"+strBuff.toString());
				objResponse_p.getWriter().print(strBuff.toString());
				return null;	
			}
	
	//function executes when plus button is clicked
	public ActionForward ADDOPINION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb=(UserBookMarkMstFB)form;
		UserBookMarkMstUTIL.addOpinionRow(fb,request); 
		fb.resetOpinion(mapping, request);
		
		/*
		 * if(fb.getGroupCode()!=null && !fb.getGroupCode().equals("-1"))
		 * fb.setIsTestGroup("1");
		 */
			return mapping.findForward("ADD");
	}
	
	public ActionForward DELETEOPINION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb=(UserBookMarkMstFB)form;
		String labCode=fb.getLabCode();
		String isTestGroup=fb.getIsTestGroup();
		UserBookMarkMstUTIL.deleteOpinionRow(fb,request);
		fb.setLabCode(labCode);
		fb.setIsTestGroup(isTestGroup);
		return mapping.findForward("ADD");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb = (UserBookMarkMstFB) form;
		boolean hasFlag=  UserBookMarkMstUTIL.saveUserBookMark(fb, request);
		if(hasFlag){
			fb.reset(mapping, request);
		//	WebUTIL.refreshTransState(request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb = (UserBookMarkMstFB) form;
		WebUTIL.refreshTransState(request);
		UserBookMarkMstUTIL.fetchModifyUserBookMark(fb, request);
		mode=fb.getHmode();
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb = (UserBookMarkMstFB) form;
		boolean hasFlag= UserBookMarkMstUTIL.modifySaveUserBookmark(fb, request);
		if(hasFlag){

            return this.LIST(mapping,fb, request, response);
         }
           else
           {
       				fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb = (UserBookMarkMstFB) form;
		UserBookMarkMstUTIL.reFetchModify(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
	public ActionForward GETTYPE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserBookMarkMstFB fb = (UserBookMarkMstFB) form;
		WebUTIL.refreshTransState(request);
		UserBookMarkMstUTIL.getBookMarkType(fb, request);
		if(mode.equals("MODIFY"))
		{
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
		}
		else
		{
			fb.setHmode("ADD");
			return mapping.findForward("ADD");	
		}
	}
	
	
	
	
}

