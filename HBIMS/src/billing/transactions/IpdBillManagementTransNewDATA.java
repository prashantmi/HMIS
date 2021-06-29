package billing.transactions;
/*
 * IPD Bill Management New DATA
 * 
 * author:Debashis Sardar
 * 
 * dated:12th Mar 2013
 */
import hisglobal.exceptions.HisException;
import javax.servlet.http.HttpServletRequest;

public class IpdBillManagementTransNewDATA {

	public static void getPatientDtls(IpdBillManagementTransFB formBean,HttpServletRequest request) 
	{
		String strmsgText = "";
		String strHospitalCode = "";
		IpdBillManagementTransVO vo = new IpdBillManagementTransVO();
		IpdBillManagementTransNewBO bo = new IpdBillManagementTransNewBO();
		
		try 
		{
			strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String chkValue=formBean.getChkadm();
			formBean.setStrAddmissionNo(chkValue.replace("^","#").split("#")[1]);
	        formBean.setStrAccountNo(chkValue.replace("^","#").split("#")[0]);
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrAccountNo(formBean.getStrAccountNo());
			vo.setStrAddmissionNo(formBean.getStrAddmissionNo());
			vo.setStrHospitalCode(strHospitalCode);
			bo.getPatientDtls(vo);
			String[] chk1={vo.getStrChk()};
			formBean.setChk(chk1);
			
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
	public static void getAdmissionDtls(IpdBillManagementTransFB formBean,HttpServletRequest request) 
	{
		String strmsgText = "";
		String strHospitalCode = "";
		String strAdmissionDtls="";
		IpdBillManagementTransVO vo = new IpdBillManagementTransVO();
		IpdBillManagementTransNewBO bo = new IpdBillManagementTransNewBO();
		
		try 
		{
			strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(strHospitalCode);
			bo.getAdmissionDtls(vo);
			strAdmissionDtls=IpdBillManagementTransNewHLP.patAdmissionList(vo);
			formBean.setStrPatAdmissionList(strAdmissionDtls);		
			
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
	
}
