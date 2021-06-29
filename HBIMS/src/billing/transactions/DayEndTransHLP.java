package billing.transactions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

public class DayEndTransHLP {

	public static String getDayEndSummaryDetails(WebRowSet ws, WebRowSet ws1, WebRowSet ws2, DayEndTransFB formBean, String strReportName)throws Exception{
		
		StringBuffer sb = new StringBuffer("");
		HisUtil hisUtil = null;
		BillConfigUtil bcu = null;	
		bcu = new BillConfigUtil(formBean.getStrHospitalCode());	
		hisUtil = new HisUtil("Billing" , "DayEndTransDATA.dayEndProcess()");
		//final String strHeader=hisUtil.getHospitalHeader1(formBean.getStrHospitalCode(), 1, "html");
		
		Map logoReq=new HashMap<String ,Object>();
	      
	      logoReq.put("FORMAT", "html");
	      logoReq.put("ISREQOBJ", "NO");
	      logoReq.put("HOSPCODE", formBean.getStrHospitalCode());
	
	final String strHeader=hisUtil.getHospitalHeaderMain(logoReq);
		double totamt=Double.parseDouble(ws.getString(2))+Double.parseDouble(ws.getString(4));
		String total = String.valueOf(totamt);
		double ototamt=Double.parseDouble(ws.getString(3))-Double.parseDouble(ws.getString(13));
		String ototal = String.valueOf(ototamt);
		sb.append("<table width='100%'>");
		if(bcu.getLogoReq().equals("1"))
			sb.append(strHeader);
		else
			sb.append("<thead><tr style='height:100px;'><th></th></tr></thead>");
		sb.append("<tr>");
		sb.append("<div align='right'><td>Report Date & Time :</td><td>'"+new Date()+"'</td></div></tr><br>");
		sb.append("<tr><td>");
		sb.append("<div align='center'><b>'"+strReportName+"'</b></div></td></tr><br>");
		sb.append("<table style='border:1px solid black;border-collapse: collapse; ' width='100%' cellspacing='0'  cellpadding='0'>");
		sb.append("<tr>");
		sb.append("<td width='20%'><b>User/Counter</b></td>");
		sb.append("<td width='5%'>:</td><td width='25%'><b>'"+ws.getString(11)+"/"+ws.getString(7)+"'</b></td>");
		sb.append("<td width='20%'><b>Scroll No. / Date</b></td>");
		sb.append("<td width='5%'>:</td><td width='25%'><b>'"+ws.getString(1)+"'</b></td>");
		sb.append("</tr>");
		sb.append("<br><br>");
		sb.append("<tr>");
		sb.append("<td width='40%'><b>(A).  Receipt Cash</b></td>");
		sb.append("<td width='10%'>:</td>");
		sb.append("<td width='40%'>'"+ws.getString(2)+"'</td>");
		sb.append("<td width='10%'>&#8377;</td>");
		sb.append("</tr><br>");
		sb.append("<tr>");
		sb.append("<td width='40%'><b>(B).  Refund Cash</b></td>");
		sb.append("<td width='10%'>:</td>");
		sb.append("<td width='40%'>'"+ws.getString(4)+"'</td>");
		sb.append("<td width='10%'>&#8377;</td>");
		sb.append("</tr><br>");
		sb.append("<tr>");
		sb.append("<td width='40%'><b>(A+B).  Net Amount Cash</b></td>");
		sb.append("<td width='10%'>:</td>");
		sb.append("<td width='40%'>'"+total+"'</td>");
		sb.append("<td width='10%'>&#8377;</td>");
		sb.append("</tr><br>");
		sb.append("<tr>");
		sb.append("<td width='40%'><b>(C).  Credit Collection</b></td>");
		sb.append("<td width='10%'>:</td>");
		sb.append("<td width='40%'>'"+ws.getString(13)+"'</td>");
		sb.append("<td width='10%'>&#8377;</td>");
		sb.append("</tr><br>");
		sb.append("<tr>");
		sb.append("<td width='40%'><b>(D).  Other Collection(Credit Card/Debit Card/DD/Cheque/Wallet)</b></td>");
		sb.append("<td width='10%'>:</td>");
		sb.append("<td width='40%'>'"+ototal+"'</td>");
		sb.append("<td width='10%'>&#8377;</td>");
		sb.append("</tr><br>");
		sb.append("<tr style='border:1px solid black;border-collapse: collapse; '>");
		sb.append("<td width='40%'><b>(A+B+C+D).  Gross Amount</b></td>");
		sb.append("<td width='10%'><b>:</b></td>");
		sb.append("<td width='40%'><b>'"+ws.getString(6)+"'</b></td>");
		sb.append("<td width='10%'><b>&#8377;</b></td>");
		sb.append("</tr><br></table><br>");
		sb.append("<tr><td align='center'>Bill Details:</td></tr>");
		sb.append("<table width='100%' style='border:1px solid black;border-collapse: collapse; ' cellspacing='0'  cellpadding='0'>");
		sb.append("<tr>");
		sb.append("<td width='3%' class='LABEL'><div align='center'>S.No.</div></td>");
		sb.append("<td width='12%'class='LABEL'><div align='center'>Receipt No.</div></td>");
		sb.append("<td width='12%' class='LABEL'><div align='center'>CR No.</div></td>");
		sb.append("<td width='12%' class='LABEL'><div align='center'>Patient Name</div></td>");
		sb.append("<td width='12%' class='LABEL'><div align='center'>Category</div></td>");
		sb.append("<td width='12%' class='LABEL'><div align='center'>Rcpt Type</div></td>");
		sb.append("<td width='9%' class='LABEL'><div align='center'>Received Amt.</div></td>");
		sb.append("<td width='8%' class='LABEL'><div align='center'>Payment Mode</div></td>");
		sb.append("<td width='11%' class='LABEL'><div align='center'>Payment Dtls.</div></td>");
		sb.append("<td width='9%' class='LABEL'><div align='center'>Refund Amt.</div></td>");
		sb.append("</tr></table>");	
		if(ws1 != null){
			
			
			
		}else{
			
			
			
		}
		sb.append("<tr><td align='center'>Credit Letters List</td></tr>");
		sb.append("<table width='100%' style='border:1px solid black;border-collapse: collapse; ' cellspacing='0'  cellpadding='0'>");
		sb.append("<tr>");
		sb.append("<td width='4%' class='LABEL'><div align='center'>S.No.</div></td>");
		sb.append("<td width='12%'class='LABEL'><div align='center'>CR No.</div></td>");
		sb.append("<td width='12%' class='LABEL'><div align='center'>Patient Name</div></td>");
		sb.append("<td width='11%' class='LABEL'><div align='center'>Bill No.</div></td>");
		sb.append("<td width='10%' class='LABEL'><div align='center'>Rcpt Type</div></td>");
		sb.append("<td width='8%' class='LABEL'><div align='center'>Amount</div></td>");
		sb.append("<td width='13%' class='LABEL'><div align='center'>Credit Letter No.</div></td>");
		sb.append("<td width='11%' class='LABEL'><div align='center'>Credit Letter Date</div></td>");
		sb.append("<td width='19%' class='LABEL'><div align='center'>Organization Name</div></td>");
		sb.append("</tr></table>");	
		
		if(ws2.size()!=0)
		  {	  
			  sb.append("<table width='100%' style='border:1px solid black;border-collapse: collapse; ' cellspacing='1px' >");
			while(ws2.next()){
				
				sb.append("<tr>");
				sb.append("<td  class='multiControl' width='4%'>");
				sb.append(ws.getString(1));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(2));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(3));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(4));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(5));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(6));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(7));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(8));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(9));
				sb.append("</td>");
				
				sb.append("<td   class='multiControl' width='10%'>");
				sb.append(ws.getString(10));
				sb.append("</td>");
				/*sb.append("<td   class='multiControl'>");
				sb.append("<a name='"+selectedSrNo+"' href='#' onClick='myFunc(\"1\",this,\"V\");'>");
				sb.append(ws.getString(8));
				sb.append("</a></td>");*/
				sb.append("</tr>");
			}
			sb.append("</table>");
		  }	
		  else
		  {
			  sb.append("<table width='90%' align='center' bgcolor='pink' cellspacing='1px' border='0'>");
			  sb.append("<tr><td width='90%' colspan='7' class='CONTROL' bgcolor='black'><div align='center'><font color='red' weight='bold' size='2'>No Advice Generated Today!!</font></div></td></tr>");
			  sb.append("</table>");	
		  }	
		
		return sb.toString();
		
	}
	
}
