package billing.transactions;


/*
 * COMPULSORY CHARGES BY CONSULTANT 
 * 
 * author:Manisha Gangwar
 * 
 * dated:23rd Jan 2019
 */


import ipd.transactions.NursingDeskTransDATA;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;


public class CompulsoryChargesByConsultantCNT extends CSRFGardTokenAction{

	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB) form;
		formBean.setStrCrNo("");
		String target = "compCharges";
	
		return mapping.findForward(target);

    }
	public ActionForward IPDDESKGO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		String crno=request.getParameter("patCrNo");
		CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB) form;
		formBean.setStrIsDesk("true");
		formBean.setStrCrNo(crno);
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		ActionForward acFwd = new ActionForward();
	    acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=GO");
		acFwd.setContextRelative(true);
		return acFwd;
				
	}
	
	
	public ActionForward WARDWISE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		String crno=request.getParameter("patCrNo");
		String wardcode=request.getParameter("wardCode");
		CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB) form;
		formBean.setStrIsDesk("true");
		formBean.setStrCrNo(crno);
		formBean.setStrWardCode(wardcode);
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		ActionForward acFwd = new ActionForward();
	    acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=PATLISTWARD");
		acFwd.setContextRelative(true);
		return acFwd;
				
	}
	
	public ActionForward PATLISTWARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB) form;
		CompulsoryChargesByConsultantDATA.getPatientDtls(formBean,request);
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		CompulsoryChargesByConsultantDATA.getWardOnBasisOfUnitCode(formBean, "%",request, response);
		//List listWard=CompulsoryChargesByConsultantDATA.getWardOnBasisOfUnitCode("%",getUserVO(request));
	//	WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);
		return null;
				
	}
	
	
	
	
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);

		CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB) form;
		  
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		String strTarget = "compCharges";
		CompulsoryChargesByConsultantDATA.getPatientDtls(formBean,request);
		if(formBean.getChk()[0].equals("0"))
		{
			formBean.setStrCrNo("");
			formBean.setStrErrMsg("No Data Found/Account Not Opened/Patient Not Accepted in Ward!");
			return mapping.findForward(strTarget);
		}
		else
		{
			ActionForward acFwd = new ActionForward();
			formBean.setStrIpdBillManagementMode("2");
			//request.getSession().setAttribute("USERVALUE", "0");// Clerk Wise
			formBean.setStrIsCalledFromIpdBillNew("1");
			String strAccStatus=formBean.getChk()[0].split("@")[11];
			String finalBillFlag=formBean.getChk()[0].split("@")[12];
			if(finalBillFlag.equals("91"))
				request.getSession().setAttribute("USERVALUE", "1");// Auditor Wise
			else
				request.getSession().setAttribute("USERVALUE", "0");// Clerk Wise
			formBean.setStrAcctStatMode(strAccStatus);
			String[] StrCombo={strAccStatus,"1","1"};
			formBean.setCombo(StrCombo);
			if(strAccStatus.equals("1"))
			    acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=ADDCOMPULSORYCHARGES");
			else if(strAccStatus.equals("2"))
			    acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=ADDCOMPULSORYCHARGES");
			else if(strAccStatus.equals("0"))
			{
				formBean.setStrCrNo("");
				formBean.setStrErrMsg("Patient Account has been Closed!");
				return mapping.findForward(strTarget);
			//	acFwd.setPath("/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=NODUESPRINT");
			}
			else if(strAccStatus.equals("5"))
			{
				formBean.setStrCrNo("");
				formBean.setStrErrMsg("Patient Bill has been Approved and sent for Final Adjustment!");
				return mapping.findForward(strTarget);
			}
			else
				acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=ADDCOMPCHARGESINSERT");
			acFwd.setContextRelative(true);
			return acFwd;
		}
		
	}
	
	public ActionForward ADDCOMPULSORYCHARGES(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		generateToken(request);
		CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB)form;
		String strtarget = "addCompulsoryCharges";
		//String chk = request.getParameter("chk");
		//String chk2 = request.getParameter("chk");
		String chk ="";
		String chk2 ="";
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
		{
			String[] arr=formBean.getChk();
			 chk =arr[0];
			 chk2 =arr[0];
			
		}
		else
		{
			 chk =formBean.getStrChk();
			 chk2 =formBean.getStrChk();
		}
		
		
		//formBean.setStrComboValue(request.getParameterValues("combo"));
		formBean.setStrComboValue(formBean.getCombo());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	
		String strTemp[] = chk.replace("@", "#").split("#");
		
		formBean.setStrAccountNo(strTemp[0]);
		formBean.setStrCrNo(strTemp[1]);
		formBean.setStrChargeTypeID(strTemp[4]);
		formBean.setStrWardType(strTemp[16]);  // added for ipdchgtype   by manisha 
		
		//if(request.getParameter("chk")==null)
		if(chk==null)
		{
			return null;//return this.LIST(mapping, form, request, response);
		}	
		else
		{	
			 boolean bRetVal  	= CompulsoryChargesByConsultantDATA.PatientDtl(request,response,formBean,chk);
			// boolean bRetVal=true;
	    		 if(bRetVal)// && bRetVal1)
			 {
	    			
	    		 String strMode = formBean.getStrIpdBillManagementMode();
	    		 CompulsoryChargesByConsultantDATA.initAddServicesDtl(request, response, formBean , strMode);
	    		 return mapping.findForward(strtarget);
			 }
			 else
			 {
				return null;
			 }
		}
	}
	
	
	public ActionForward ADDCOMPCHARGESINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		String strtarget = "addCompulsoryCharges";
		validateToken(request, response);

		UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
        boolean retValue = false ;
        CompulsoryChargesByConsultantFB formBean = (CompulsoryChargesByConsultantFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
		if (formBean.getStrChk()== null)
		{
			return null;
		}	
		else
		{
			retValue = CompulsoryChargesByConsultantDATA.InsertCompChargesByConsultant(formBean, userVO);     //defined in DATA class
			if(formBean.getStrIsCalledFromIpdBillNew().equals("1"))
		      {
		    	  
				
					ActionForward acFwd = new ActionForward();
		    	    formBean.setStrErrMsg("");
		    	    formBean.setStrMsg("Record Updated Successfully.");
					if(formBean.getStrIsDesk().equalsIgnoreCase("true"))
							acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=ADDCOMPULSORYCHARGES");
					else
					
					 acFwd.setPath("/billing/transactions/CompulsoryChargesByConsultantCNT.cnt?hmode=unspecified");
					acFwd.setContextRelative(true);
					return acFwd;
		      }
		      else
		      {
				if(retValue)
				{	
				    return null;
				}
				else
				{
					return mapping.findForward(strtarget);
				}	
		      }
		}	

		
   }
	
}
