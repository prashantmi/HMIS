package inpatient.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dietkitchen.transactions.controller.data.CommonDietDetailDATA;
import dietkitchen.transactions.controller.fb.DietAdviceFB;
import dietkitchen.transactions.controller.util.CommonDietDetailUTIL;
import dietkitchen.vo.MealTimeMasterVO;

import opd.OpdConfig;
import opd.bo.delegate.OpdPatientDelegate;
import inpatient.transaction.controller.fb.InpatientDeskFB;
import inpatient.transaction.controller.utl.PatNursingDeskDataFlagsMenuWiseUTL;
import inpatient.transaction.delegate.InPatientEssentialDelegate;

public class PatNursingDeskDataFlagsMenuWise extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
		throws HisException, Exception, SQLException
	{
		try
		{
			InpatientDeskFB fb = (InpatientDeskFB) objForm_p;
			
			StringBuffer strBuff = PatNursingDeskDataFlagsMenuWiseUTL.getMenuWisePateintNursingDeskDataFlags(fb, objRequest_p);

			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
