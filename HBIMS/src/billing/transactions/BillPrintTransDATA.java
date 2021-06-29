package billing.transactions;

import javax.servlet.http.HttpServletRequest;

import billing.PrintBillHLP;
import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;

public class BillPrintTransDATA {
	
	// /////////////////////////////////////////////////////////////////////////////

	public static void initOnlineReq(BillPrintTransFB formBean) {

		BillPrintTransVO vo = null;
		BillPrintTransBO bo = null;
		
		String strmsgText = null;

		try {
			vo = new BillPrintTransVO();
			bo = new BillPrintTransBO();
			
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());

			String strPatView = PatientDtlHLP.patientDtl(formBean.getStrCrNo(),
					true);
			if (strPatView.trim().equals("")) {
				formBean.setStrErrMsg("Invalid CR No.");
				formBean.setStrMsgType("1");
				
			} else {
				
				strPatView = BillPrintTransHLP.getPatientHeaderView()+" <div id='onLineId'>"+strPatView +"</div>";
				
				formBean.setStrPatientDetailsView(strPatView);
				formBean.setStrMsgType("0");
			}

			bo.getOnlineRequestDetails(vo);
			
			if(vo.getStrMsgType().equals("1")){
				
				throw new Exception(vo.getStrMsgString());
				
			}
			
			formBean.setStrOnLineReqWs(vo.getStrOnLineReqWs());
			
			formBean.setStrOnlineRequestDtls(BillPrintTransHLP.getOnlineRequestDtls(formBean.getStrOnLineReqWs()));
						
			
		} catch (Exception e) {
			strmsgText = e.getMessage();
		 new HisException("Billing",
					"BillPrintTransDATA->initOnlineReq()", strmsgText);
			formBean.setStrErrMsg("Invalid CR No.");
			formBean.setStrMsgType("1");
		

		} finally {

				bo = null;
				vo = null;
			}
	}

	
	public static boolean printBill(BillPrintTransFB formBean , HttpServletRequest request){
		
		boolean fRes = false;
		
		String strTemp[] = formBean.getStrRequestValue().replace("^", "#").split("#");
		
		if(strTemp.length == 4){
			
			try {
				PrintBillHLP.findPrintType1(strTemp[0], strTemp[1], strTemp[2], formBean.getStrHospitalCode(), formBean.getStrBillType(), "0", strTemp[3] , request);
				
				formBean.setStrMsg("Bill Printed Successfully");
				formBean.setStrCrNo("");
				
				fRes = true;	
				
			} catch (Exception e) {

				String msgStr = e.getMessage();
				HisException eObj = new HisException("Billing",
						"BillPrintTransDATA->printBill()", msgStr);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator! ");

				formBean.setStrCrNo("");
				eObj = null;
				
				fRes = false;	
				
			}
			
				
		}
				
		return fRes;
		
	}
	

}
