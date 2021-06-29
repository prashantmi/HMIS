/******************************************Start of program*************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST MANDATORY REFERENCE MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	
 ## Date of Creation					: 	20-Apr-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/***********************************************************************************************************/

package new_investigation.masters.controller.action;

import java.sql.SQLException;

import hisglobal.backutil.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.LocalTestMandRefMstFB;
import new_investigation.masters.controller.fb.TestMandRefMstFB;
import new_investigation.masters.controller.utl.LocalTestMandRefMstUTIL;
import new_investigation.masters.controller.utl.TestMandRefMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LocalTestMandRefMstACT extends GenericController
{

	String target = null;

	public LocalTestMandRefMstACT()
	{

		super(new LocalTestMandRefMstUTIL(),"/masters/LocalTestMandRefMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LocalTestMandRefMstUTIL.fetchLocalTestMandRef(testmandref_fb, request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETPARAMETER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
					LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
					LocalTestMandRefMstUTIL.fetchParameterCombo(testmandref_fb, request);
				
					testmandref_fb.setHmode("ADD");
					return mapping.findForward("ADD");
	}
	
	
	
	
	public ActionForward GETCOMBO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
					LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
					LocalTestMandRefMstUTIL.fetchCombo(testmandref_fb, request);
				
					testmandref_fb.setHmode("ADD");
					return mapping.findForward("ADD");
	}
	
	public ActionForward GETVALUES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
					LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
					LocalTestMandRefMstUTIL.getValues(testmandref_fb, request);
				
					testmandref_fb.setHmode("ADD");
					return mapping.findForward("ADD");
	}
	
	

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
		boolean hasFlag=  LocalTestMandRefMstUTIL.saveLocalTestMandRef(testmandref_fb, request);
		if(hasFlag){
			testmandref_fb.reset(mapping, request);
			
			testmandref_fb.setHmode("ADD");
            return this.ADD(mapping, testmandref_fb, request, response);
		}
		
		testmandref_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
		WebUTIL.refreshTransState(request);
		LocalTestMandRefMstUTIL.fetchCheckListLocalTestMandRef(testmandref_fb, request);
			return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
		
	
		boolean hasFlag= LocalTestMandRefMstUTIL.savemodifyLocalTestMandRef(testmandref_fb, request);
		if(hasFlag){
            return this.LIST(mapping, testmandref_fb, request, response);
         }
           else
           {
       		
		testmandref_fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
           }
		
	}




	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LocalTestMandRefMstFB testmandref_fb = (LocalTestMandRefMstFB) form;
		LocalTestMandRefMstUTIL.reFetchCheckListLocalTestMandRef(testmandref_fb, request);
		testmandref_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	
	public ActionForward AJX_G_LABSAMPLE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
			{
				System.out.println("DeskHeaderACTION.AJX_G_TEST()");
				String labCode = objRequest_p.getParameter("labCode");
				String testCode = objRequest_p.getParameter("testCode");
				System.out.println("labCode :"+labCode);
				LocalTestMandRefMstFB objFB = (LocalTestMandRefMstFB) objForm_p;
				StringBuffer strBuff = LocalTestMandRefMstUTIL.getLabTest(objFB, objRequest_p, labCode,testCode);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				System.out.println("JSON"+strBuff.toString());
				objResponse_p.getWriter().print(strBuff.toString());
				return null;	
			}



}
