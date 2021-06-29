package billing.reports;

import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IPDPatientProvisionalBillRptDATA 
{		
	public static void getPatientDtls(IPDPatientProvisionalBillRptFB formBean,HttpServletRequest request) 
	{	
		String strmsgText = "";
		String strHospitalCode = "";
		IPDPatientProvisionalBillRptVO vo = new IPDPatientProvisionalBillRptVO();
		IPDPatientProvisionalBillRptBO bo = new IPDPatientProvisionalBillRptBO();
		
		try 
		{
			strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(strHospitalCode);
			bo.getPatientDtls(vo);
			//System.out.println("vo.getStrChk()"+vo.getStrChk());
			String[] chk1={vo.getStrChk()};
			formBean.setChk(chk1);
			formBean.setStrPatAcctStatus(vo.getStrPatAcctStatus());
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			strmsgText = "billing.transactions.IpdBillManagementTransNewDATA.getPatientDtls(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("Billing","RefundApprovalTransaction->IpdBillManagementTransNewDATA->getPatientDtls()",strmsgText);
			
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{			
			vo = null;
			bo = null;
		}
}
	

 public static void showReport(IPDPatientProvisionalBillRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
 {								
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		String strmsgText = "";
		String strTemp[] = null;
		IPDPatientProvisionalBillRptBO bo = null;
		Map<String, Object> params = new HashMap<String, Object>();
		IPDPatientProvisionalBillRptVO vo = null;
		String strReopen="";
			
		try 
		{
			vo = new IPDPatientProvisionalBillRptVO();
			String strChk[] = formBean.getChk();
			String strRptMode = "0";
			
			if(request.getParameter("rptMode") != null)
			{				
				strRptMode = request.getParameter("rptMode");				
			}
			
			strTemp = strChk[0].replace("@", "#").split("#");
			String strAccountNo = strTemp[0];
			
			
			String strBillNo="----";
			if((request.getParameter("billNo"))!=null)
			{
			 strBillNo=request.getParameter("billNo");
			}
			String strHospitalCode=ControllerUTIL.getUserVO(request).getHospitalCode();
			String strReportName = "Provisional Final Adjustment Bill";
			vo.setStrAccountNo(strAccountNo);
			vo.setStrHospitalCode(strHospitalCode);
			/*
			strReopen=IPDPatientProvisionalBillRptBO.getBillReopenFlag(vo);
			*/
			if(strRptMode.equals("1"))
			{
				params.put("modeval", "39");
			}
			else
			{
				if( strReopen.equals("1"))
					params.put("modeval", "39");
				else
					params.put("modeval", "37");				
			}		 
			
			String reportPath = "/billing/reports/IPDPatientProvisionalNew_billrpt.rptdesign";
			params.put("hosp_Code", strHospitalCode);
			params.put("report_id", "1");
			params.put("report_Name", strReportName);

			params.put("acc_No", strAccountNo);
			params.put("bill_No",strBillNo);
			params.put("dept_Code", "1");
			params.put("ward_Code", "1");

			ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
		
		} 
		catch (Exception e) 
		{
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","IPDPatientProvisionalBillRptDATA->showReport()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID() + "],"+ "Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		}   
 }

}