package ipd.transactions;

import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class PackAdmTransHLP {
	
	public static String getAdmitDetailNr(PackAdmTransVO VO,String temp) throws Exception 
	{
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospCode());
		HisUtil util = null;
		util = new HisUtil("IPD", "PackAdmTransHLP");

		PackAdmTransBO BO = new PackAdmTransBO();
		BO.getAdmitDetailIPD(VO);

		// if there is error
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" PackAdmTransHLP.getAdmitDetail() --> "+ strMsg);
		}

		WebRowSet ws = VO.getStrAdmitDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();

		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='4%'>#</td> ");
		sBuffer.append("<td class='multiLabel' width ='15%'>Adm. No. </td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='17%'>CR No.</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='20%'>Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' >Age/Sex</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='21%' >Adm. Date/Time</td> ");
		/*sBuffer.append("<td CLASS='multiLabel' width ='13%' ><font color='red'>*</font>Apply OT Package</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Date of Applying Package</td> ");*/
		

	
		
		sBuffer.append(" </tr>");
		/* sBuffer.append(" </table>"); */

		
		if (ws != null && ws.size() != 0) 
		{
			try 
			{
				int i = 0;
				while (ws.next())
				{
					//strbed = tempStrBed;
					
					i++;
					String stradmno = ws.getString(1);
					String strcrno = ws.getString(2);
					String strname = ws.getString(3);
					String stragesex = ws.getString(4);
					String strbedname = ws.getString(5);
					String strRemarks;
					String strtransinflag = ws.getString(7);
					String strmoveno = ws.getString(8);
					String strbedno = ws.getString(6);
					VO.setStrPreviousOccupiedbed(strbedno);
					String strunitcode = ws.getString(15);
					String strroomcode = ws.getString(16);
					String strunitname = ws.getString(19);
					String strAdmDate = ws.getString(14);
					VO.setStrDeptName(ws.getString(17));
					VO.setStrWardName(ws.getString(18));
					if (stradmno == null)
						stradmno = "";
					if (strcrno == null)
						strcrno = "";
					if (strname == null)
						strname = " ";
					if (stragesex == null)
						stragesex = "";
					if (strbedno == null || strbedno.equals("null"))
						strbedno = "";
					/*if (!strbedno.equals("") && !strbedno.equals("0")) 
					{
						strbed = "<option value = '0'>Select Value</option>";
						strbed += "<option value = \"" + strbedno + "\">"+ strbedname + "</option>";
					}
					else
					{
						strbed = "<option value = '0'>Select Value</option>";
					}*/
					
					boolean fPrintAllRow = false;
					if (VO.getStrCrNo() == null || VO.getStrCrNo().equals("")) 
					{
						fPrintAllRow = true;
					}
					if (fPrintAllRow || strcrno.equals(VO.getStrCrNo())) 
					{
					
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1)//only single record 
							{
								//sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
								sBuffer.append("<td    width ='4%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'></td> ");
							} 
							else 
							{
								sBuffer.append("<td  width ='4%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td width ='18%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td   width ='21%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno
									+ "</font></td> ");
							sBuffer.append(" <td   width ='21%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strname + "</font></td> ");
							sBuffer.append(" <td  width ='12%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ stragesex + "</font></td> ");
							sBuffer.append(" <td  width ='13%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strAdmDate + "</font></td> ");
							
							/*sBuffer.append("<td   width ='13%'><select name='strPackageApply' class='comboNormal' onChange='setAdvAmt();' value='"+temp+"'></select></td>");
							
							sBuffer.append("<td   width ='12%'><date:date name='strPackAppDate' value=''></date:date></td>");*/
						  
								

							//<bean:write name='PackAdmFB' property='strPackageComboValues' filter='false'/>
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1"))
							{
								/*sBuffer.append(" <td  width ='11%'  >");
								sBuffer.append("<img src='../../hisglobal/images/Belongings_1.png'  style='cursor:hand;cursor:pointer;' " +
											"  title = 'Belonging Details' onClick ='beforedisplayBelogingPopup(this,"
											+ i + ");'> ");*/
							}
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								/*sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");*/
								
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
							sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						}
					}
				
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				VO.setStrMsgString("PackAdmTransHLP.checklistdetail() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PackAdmTransHLP.getAdmitDetailNr() -->"
						+ e.getMessage());
			}

		} 
		else 
		{

			sBuffer
					.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
			sBuffer.append("<tr class ='multiControl'> ");
			sBuffer
					.append("<td colspan='7' align='center'> <b><font color='red'>No Record Available</font></b> </td>");
			sBuffer.append(" </tr> ");
			sBuffer.append("</table > ");

		}

		return sBuffer.toString();
	}

}
