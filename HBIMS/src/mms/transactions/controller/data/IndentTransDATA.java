package mms.transactions.controller.data;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;

import mms.transactions.bo.IndentTransBO;
import mms.transactions.controller.fb.IndentTransFB;
import mms.transactions.vo.IndentTransVO;

public class IndentTransDATA 
{
	/**
	 * This function is used to invoke Bo's update method to update data
	 * 
	 * @param request
	 * @param formBean
	 */
	public static boolean CancelRecord(HttpServletRequest request, IndentTransFB formBean) {
		IndentTransBO bo = null;
		IndentTransVO vo = null;
		boolean retValue = true;
		//String tempChk[] = null;
		String strChk = "";
		
		try {
			            bo = new IndentTransBO();
			            vo = new IndentTransVO();			
			        strChk = formBean.getStrChk();						
			String[] temp  = strChk.split("\\^");
			String[] temp1 = temp[0].split("\\@");

//			System.out.println("Req No-->>"+temp1[0]);
//			System.out.println("Store Id-->>"+temp1[1]);
//			System.out.println("Req Type Id-->>"+temp1[2]);
//			System.out.println("Catg No-->>"+temp1[3]);
//			System.out.println("Urgent Falg-->>"+temp1[4]);
//		    System.out.println("Indent Period-->>"+strIndentPeriod);
//			System.out.println("Reson-->>>"+temp[1]);
			formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());			
			vo.setStrItemCatgNo(temp1[3]);
			vo.setStrReqNo(temp1[0]);
			vo.setStrStoreId(temp1[1]);
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrReqTypeId(temp1[2]);
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrCancelReson(temp[1]);
			// Calling BO Method
			bo.CANCEL(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				retValue = false;
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
				formBean.setStrMsg("Record is successfully updated");

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			retValue = false;
			String strmsgText = "Item Master.IndentTransDATA.update(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("MMS","IndentTransDATA->update()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
		return retValue;
	}

}
