
	package billing.transactions;
	/*
	 * Ipd Bill Re-Open Transaction DATA
	 * 
	 * author : Debashis Sardar
	 * 
	 * date: 10-Dec-2011
	 * 
	 */
	import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;

	public class IpdBillReOpenTransBSDATA {

		public static void getPatDtls(IpdBillReOpenTransFB formBean,
				HttpServletRequest request) {
			String strmsgText = "";
			String strHospitalCode = "";
			String strPatientDtls = "";
			IpdBillReOpenTransVO vo = new IpdBillReOpenTransVO();
			
			try {
				strHospitalCode = request.getSession()
						.getAttribute("HOSPITAL_CODE").toString();
				
				vo.setStrCrNo(formBean.getStrCrNo());
				vo.setStrHospitalCode(strHospitalCode);

					strPatientDtls = hisglobal.tools.hlp.PatientDtlHLPNew
							.patientDtl(vo.getStrCrNo(),true);
					
					if (strPatientDtls.trim().equals("")) {
						formBean.setStrErrMsg("Invalid Bill No.");
						formBean.setStrMsgType("1");
					}
					else
					{
							formBean.setStrPatientDtls(strPatientDtls);
						
					}
				
			} catch (Exception e) {
				strmsgText = "billing.transactions.IpdBillReOpenTransBSDATA.getPatDtls(vo) --> "
						+ e.getMessage();
				HisException eObj = new HisException(
						"Billing",
						"IpdBillReOpenTransaction->IpdBillReOpenTransBSDATA->getPatDtls()",
						strmsgText);
				
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				
				eObj = null;
			} finally {
				
			
				vo = null;
				
			}

		}

		
		
		public static void getCrNo(IpdBillReOpenTransFB formBean,
				HttpServletRequest request) {
			String strmsgText = "";
			String strHospitalCode = "";

			IpdBillReOpenTransVO vo = new IpdBillReOpenTransVO();
			IpdBillReOpenTransBO bo = new IpdBillReOpenTransBO();
		
			try {
				strHospitalCode = request.getSession()
						.getAttribute("HOSPITAL_CODE").toString();
				
				vo.setStrHospitalCode(strHospitalCode);
			   vo.setStrRcptNo(formBean.getStrRcptNo());
				
			   bo.getCrNo(vo);
	
				formBean.setStrCrNo(vo.getStrCrNo());
				formBean.setStrPatAccNo(vo.getStrPatAccNo());
					
					
			} catch (Exception e) {
				
				strmsgText = "billing.transactions.IpdBillReOpenTransBSDATA.getCrNo(vo) --> "
						+ e.getMessage();
				HisException eObj = new HisException(
						"Billing",
						"IpdBillReOpenTransaction->IpdBillReOpenTransBSDATA->getCrNo()",
						strmsgText);
				
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				
				eObj = null;
				
			} finally {
				
			
				vo = null;
				bo = null;
			}

		}

		public static void getBillDetails(IpdBillReOpenTransFB formBean,HttpServletRequest request) 
		{
			String strmsgText = "";
			String strHospitalCode = "";
			
			IpdBillReOpenTransVO vo = new IpdBillReOpenTransVO();
			IpdBillReOpenTransBO bo = new IpdBillReOpenTransBO();
			
			try 
			{
				strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				
				vo.setStrHospitalCode(strHospitalCode);
				vo.setStrRcptNo(formBean.getStrRcptNo());
				vo.setStrPatAccNo(formBean.getStrPatAccNo());
				bo.getBillDetails(vo);
				
				formBean.setStrBillDate(vo.getStrBillDate());
				formBean.setStrApprovedBy(vo.getStrApprovedBy());
				formBean.setStrExpenseAmt(HisUtil.getAmountWithDecimal(vo.getStrExpenseAmt(), 2));
				formBean.setStrReceiveAmt(HisUtil.getAmountWithDecimal(vo.getStrReceiveAmt(), 2));	
				formBean.setStrBillCatCode(vo.getStrBillCatCode());	
				formBean.setStrBillCatGrp(vo.getStrBillCatGrp());	
					
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				strmsgText = "billing.transactions.IpdBillReOpenTransBSDATA.getBillDetails(vo) --> "+ e.getMessage();
				HisException eObj = new HisException("Billing","IpdBillReOpenTransaction->IpdBillReOpenTransBSDATA->getBillDetails()",strmsgText);
				
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

	
		public static boolean saveData(ActionForm form,HttpServletRequest request, HttpServletResponse response) 
		{
			String strmsgText = null;
			boolean fretValue = true;
			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID").toString();
			
			IpdBillReOpenTransBO bo = null;
			IpdBillReOpenTransVO vo = null;
			IpdBillReOpenTransFB formBean = null;

			try 
			{
				bo = new IpdBillReOpenTransBO();
				vo = new IpdBillReOpenTransVO();
				formBean = (IpdBillReOpenTransFB) form;
	
				vo.setStrRcptNo(formBean.getStrRcptNo());
				vo.setStrCrNo(formBean.getStrCrNo());
				vo.setStrPatAccNo(formBean.getStrPatAccNo());
				vo.setStrHospitalCode(strHospitalCode);
				vo.setStrSeatId(strSeatId);
				vo.setStrBillCatCode(formBean.getStrBillCatCode());
				vo.setStrBillCatGrp(formBean.getStrBillCatGrp());
								
				bo.saveData(vo);

				if (vo.getStrMsgType().equals("1")) 
				{
					fretValue = false;
					formBean.setStrCrNo("");
					formBean
							.setStrErrMsg("Not Saved Successfully !");
					throw new Exception(vo.getStrMsgString());
				} 
				else 
				{
					fretValue = true;
					formBean
					.setStrErrMsg("Data Saved Successfully !");
					formBean.setStrMsgType("0");
					formBean.setStrCrNo("");
				}

			} catch (Exception e) {
				e.printStackTrace();
				fretValue = false;
				strmsgText = "billing.transactions.IpdBillReOpenTransBSDATA.saveData(vo) --> "
						+ e.getMessage();
				HisException eObj = new HisException("Billing",
						"IpdBillReOpenTransBSDATA->saveData()", strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;
			} finally {

				bo = null;
				vo = null;

			}
			return fretValue;
		}

		
	}


