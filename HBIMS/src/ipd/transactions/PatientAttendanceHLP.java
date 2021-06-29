package ipd.transactions;
/*
 * Patient Attendance HLP
 * 
 * author: Debashis Sardar
 * 
 * dated: 16/02/2012 
 */

import java.sql.SQLException;
import javax.sql.rowset.WebRowSet;

public class PatientAttendanceHLP {

/**
 * to get pending patient list
 * @param PatientAttendanceVO VO
 * @return String sBuffer.toString()
 * 
 */ 
	public static String getPatientListPending(PatientAttendanceVO VO) throws Exception {

		WebRowSet ws = VO.getStrPatDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();
		if(ws!=null && ws.size()>0)
			sBuffer.append("<input type='hidden' name='strCount' value="+ws.size()+">");
		else
			sBuffer.append("<input type='hidden' name='strCount' value='0'>");
		
		    sBuffer.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px' width='100%'> ");

		if (ws != null && ws.size() != 0) {		
			sBuffer.append("<tr> ");
			sBuffer.append("<td class='multiLabel' width ='5%'> </td> ");
			sBuffer.append("<td class='multiLabel' width ='15%'>CR No.</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Admission No.</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='20%'>Patient Name</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='30%'>From Dept/Unit/Ward</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%' nowrap>Transfer Date/Time</td> ");
			sBuffer.append(" </tr>");
			
			try {

				int i = 0;
				while (ws.next()){
					
					String chk=ws.getString(1);
					String strcrno = ws.getString(2);
					String stradmno = ws.getString(3);
					String strPatName=ws.getString(4);
					String fromDept=ws.getString(5);
					String dateTime=ws.getString(6);
					if (stradmno == null)
						stradmno = "-";
					if (strcrno == null)
						strcrno = "-";
					if (fromDept == null)
						fromDept = "-";
					if (dateTime == null)
						dateTime = "-";
					if (strPatName == null)
						strPatName = "-";
		
						sBuffer.append("<tr class ='multiControl'> ");
							
							
						sBuffer.append("<td width ='5%'><input type ='checkbox' name = 'strchk'  value ='"
												+ chk
												+ "' id = 'strchk-"
												+ i
												+ "'  onClick = ''  ></td> ");
							
							sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stradmno + "</font></td> ");
							sBuffer.append(" <td width ='20%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strPatName + "</font></td> ");
							sBuffer.append(" <td width ='30%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + fromDept + "</font></td> ");
							sBuffer.append(" <td width ='15%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + dateTime + "</font></td> ");
							
							sBuffer.append("</td>");
							sBuffer.append(" </tr> ");
							
							i++;					
						} 
				
			} catch (SQLException e)
			{
				VO.setStrMsgString("PatientAttendanceDAO.getPatientListPending() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PatientAttendanceDAO.getPatientListPending() -->"
						+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<tr><div align=\"center\"><font color=\"red\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No Patient Available</font></div></tr>");
				
		}
		sBuffer.append("</table > ");
		
		sBuffer.append("<table align ='center' cellspacing ='1px' width='100%'> ");
		sBuffer.append("<tr class='HEADER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		
		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/Accept.png'  style='cursor:hand;cursor:pointer;'  title ='accept ' onClick ='return accept();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/reject_tab.gif' style='cursor:hand;cursor:pointer;'  title ='reject ' onClick ='return reject();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/btn-clr.png'  style='cursor:hand;cursor:pointer;'  title ='clear ' onClick ='formClear();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='cancel' onClick ='cancel();' />");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");
		return sBuffer.toString();
	}
	
	
	/**
	 * to get patient status list for View Process 
	 * @param PatientAttendanceVO VO
	 * @return String sBuffer.toString()
	 * 
	 */ 
	public static String getPatientStatusView(PatientAttendanceVO VO) throws Exception {

		WebRowSet ws = VO.getStrPatStatusViewDtlWs();
		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();		
	
		sBuffer.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px' width='100%'> ");

		if (ws != null && ws.size() != 0) {		
			
			sBuffer.append("<tr> ");
			sBuffer.append("<td class='multiLabel' width ='15%'>CR No.</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Admission No.</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Patient Name</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='25%'>From Dept/Unit/Ward</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='20%' nowrap>Transfer Date/Time</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='10%' nowrap>Status</td> ");
			sBuffer.append(" </tr>");	
			try {

				int i = 0;
				while (ws.next()){								
					String strcrno = ws.getString(1);
					String stradmno = ws.getString(2);
					String strPatName=ws.getString(3);
					String fromDept=ws.getString(4);
					String dateTime=ws.getString(5);
					String status=ws.getString(6);
					if (stradmno == null)
						stradmno = "-";
					if (strcrno == null)
						strcrno = "-";
					if (fromDept == null)
						fromDept = "-";
					if (dateTime == null)
						dateTime = "-";
					if (strPatName == null)
						strPatName = "-";
					if (status == null)
						status = "-";
						sBuffer.append("<tr class ='multiControl'> ");
							
							sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stradmno + "</font></td> ");
							sBuffer.append(" <td width ='15%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strPatName + "</font></td> ");
							sBuffer.append(" <td width ='25%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + fromDept + "</font></td> ");
							sBuffer.append(" <td width ='20%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + dateTime + "</font></td> ");
							sBuffer.append(" <td width ='10%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + status + "</font></td> ");
							
							sBuffer.append("</td>");
							sBuffer.append(" </tr> ");
							
							i++;					
						} 		
						
			} catch (SQLException e) {
				VO.setStrMsgString("PatientAttendanceDAO.getPatientStatusView() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PatientAttendanceDAO.getPatientStatusView() -->"
						+ e.getMessage());
			}

		} 
		else{
			sBuffer.append("<tr><div align=\"center\"><font color=\"red\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No Patient Available</font></div></tr>");
				
		}
		sBuffer.append("</table > ");
		
		return sBuffer.toString();
	}

	
	/**
	 * to get accepted/to be transferred patient list
	 * @param PatientAttendanceVO VO
	 * @return String sBuffer.toString()
	 * 
	 */ 
	public static String getPatientList(PatientAttendanceVO VO) throws Exception {

		WebRowSet ws = VO.getStrPatDetailWs();
		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();
		if(ws!=null && ws.size()>0)
			sBuffer.append("<input type='hidden' name='strCount' value="+ws.size()+">");
		else
			sBuffer.append("<input type='hidden' name='strCount' value='0'>");
	
		sBuffer.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px' width='100%'> ");

		if (ws != null && ws.size() != 0) {
			sBuffer.append("<tr> ");
			sBuffer.append("<td class='multiLabel' width ='5%'> </td> ");
			sBuffer.append("<td class='multiLabel' width ='15%'>CR No.</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Admission No.</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='20%'>Patient Name</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='30%'>From Dept/Unit/Ward</td> ");
			sBuffer.append(" <td CLASS='multiLabel' width ='15%' nowrap>Transfer Date/Time</td> ");
			sBuffer.append(" </tr>");		
			try {

				int i = 0;
				while (ws.next()){
					
					String chk=ws.getString(1);
					String strcrno = ws.getString(2);
					String stradmno = ws.getString(3);
					String strPatName=ws.getString(4);
					String fromDept=ws.getString(5);
					String dateTime=ws.getString(6);
					if (stradmno == null)
						stradmno = "-";
					if (strcrno == null)
						strcrno = "-";
					if (fromDept == null)
						fromDept = "-";
					if (dateTime == null)
						dateTime = "-";
					if (strPatName == null)
						strPatName = "-";
					
						sBuffer.append("<tr class ='multiControl'> ");
							
								
						sBuffer.append("<td width ='5%'><input type ='radio' name = 'strchk'  value ='"
												+ chk
												+ "' id = 'strchk-"
												+ i
												+ "'  onClick = 'getBlkHrs(this,\""
												+ i + "\");'  ></td> ");
							
							sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td width ='15%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stradmno + "</font></td> ");
							sBuffer.append(" <td width ='20%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strPatName + "</font></td> ");
							sBuffer.append(" <td width ='30%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + fromDept + "</font></td> ");
							sBuffer.append(" <td width ='15%' align='left'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + dateTime + "</font></td> ");

							
							sBuffer.append("</td></tr>");
							
							i++;
						}
				sBuffer.append(" </table>");
				sBuffer.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px' width='100%'> ");
				sBuffer.append("<tr class='TITLE'>");
				sBuffer.append("<td  colspan='2'>Transferred To Details</td>\n");
				sBuffer.append("</tr>");
				sBuffer.append("</table>");
			
		        sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center' width='100%'>"); 
		        sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'></font></div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' >");
		        sBuffer.append("</td>");
		        sBuffer.append("<td width='25%' class='LABEL'><div align='right'><font color='red'>*</font>Same Ward Room</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' >");
		        sBuffer.append("<input type='checkbox' name='strsamewardchk' checked='checked' onclick='callchk2()'>");
		        sBuffer.append("</td></tr>");  
		       
		     
		        sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Department</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' >");
		        sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strDepartment' class='comboNormal' onChange ='fununit();'>"+VO.getStrDeptProperty()+"</select>");
		        sBuffer.append("</td>");
		        sBuffer.append("<td width='25%' class='LABEL'><div align='right'><font color='red'>*</font>Unit</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL'><div id ='unitId'>");
		        sBuffer.append("<select name='strUnit' class='comboNormal' title='Select Unit' onChange='funward();'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></td></tr>");  
		        sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Ward</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' ><div id ='wardId'>");
		        sBuffer.append("<select name='strWard' class='comboNormal' title='Select Ward' onChange='funroom()'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></td>");
		        sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Room</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div id ='roomId'>");
				sBuffer.append("<select name='strRoom' class='comboNormal' title='Select Room'><option value='0'>Select Value</option></select>");
				sBuffer.append("</div></td>");
		        sBuffer.append("</tr>"); 
		        sBuffer.append("<tr><td width='25%' class='LABEL'>Remarks</td>\n");
				sBuffer.append("<td width='75%' class='CONTROL' colspan='3'>");
				sBuffer.append("<textarea name='strRemarks' rows='3' cols='25'></textarea>" );
				sBuffer.append("</td>");
		        sBuffer.append("</tr>");  
		        sBuffer.append("</table>");
				
			} catch (SQLException e) {
				VO.setStrMsgString("PatientAttendanceDAO.getPatientList() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PatientAttendanceDAO.getPatientList() -->"
						+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<tr><div align=\"center\"><font color=\"red\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No Patient Available</font></div></tr></table>");
				
		}
		
	
        sBuffer.append("<table align ='center' cellspacing ='1px' width='100%'> ");
		sBuffer.append("<tr class='HEADER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/btn-save-validate.png'  style='cursor:hand;cursor:pointer;'  title ='transfer ' onClick ='return transfer();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/btn-clr.png'  style='cursor:hand;cursor:pointer;'  title ='clear ' onClick ='formClear();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='cancel' onClick ='cancel();' />&nbsp;");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");
		return sBuffer.toString();
	}
	
	public static String getPatientListPending_BS(PatientAttendanceVO VO) throws Exception {

		WebRowSet ws = VO.getStrPatDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();
		if(ws!=null && ws.size()>0)
			sBuffer.append("<input type='hidden' name='strCount' value="+ws.size()+">");
		else
			sBuffer.append("<input type='hidden' name='strCount' value='0'>");
		
		    sBuffer.append("<table id='datatable' class='table table-striped table-bordered' style='width:100%'> ");

		if (ws != null && ws.size() != 0) {		
			sBuffer.append("<thead> ");
			sBuffer.append("<tr> ");
			sBuffer.append("<th>#</th> ");
			sBuffer.append("<th>CR No.</th> ");
			sBuffer.append(" <th>Admission No.</th> ");
			sBuffer.append(" <th>Patient Name</th> ");
			sBuffer.append(" <th>From Dept/Unit/Ward</th> ");
			sBuffer.append(" <th>Transfer Date/Time</th> ");
			sBuffer.append(" <th>Accept/Reject</th> ");
			sBuffer.append(" </tr>");
			sBuffer.append("</thead> ");
			sBuffer.append("<tbody> ");

			try {

				int i = 0;
				while (ws.next()){
					
					String chk=ws.getString(1);
					String strcrno = ws.getString(2);
					String stradmno = ws.getString(3);
					String strPatName=ws.getString(4);
					String fromDept=ws.getString(5);
					String dateTime=ws.getString(6);
					if (stradmno == null)
						stradmno = "-";
					if (strcrno == null)
						strcrno = "-";
					if (fromDept == null)
						fromDept = "-";
					if (dateTime == null)
						dateTime = "-";
					if (strPatName == null)
						strPatName = "-";
		
						sBuffer.append("<tr> ");
							
							
						sBuffer.append("<td><input type ='checkbox' name = 'strchk'  value ='"
												+ chk
												+ "' id = 'strchk-"
												+ i
												+ "'  onClick = ''  ></td> ");
							sBuffer.append(" <td>" + strcrno + "</td> ");
							sBuffer.append(" <td>" + stradmno + "</font></td> ");
							sBuffer.append(" <td>" + strPatName + "</font></td> ");
							sBuffer.append(" <td>" + fromDept + "</font></td> ");
							sBuffer.append(" <td>" + dateTime + "</font></td> ");
							
							sBuffer.append("<td>&nbsp;&nbsp;<button type='buttton' class='btn btn-primary'  onClick ='return accept();' /><i class='fas fa-check'></i></button>&nbsp;&nbsp;");
							sBuffer.append("<button type='buttton' class='btn btn-danger'  onClick ='return reject();' /><i class='fas fa-ban'></i></button>&nbsp;</td>");

							sBuffer.append(" </tr> ");
							
							i++;					
						} 
				
			} catch (SQLException e)
			{
				VO.setStrMsgString("PatientAttendanceDAO.getPatientListPending() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PatientAttendanceDAO.getPatientListPending() -->"
						+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<tr><div class='alert alert-danger' role='alert'>No Patient Available</div></tr>");
				
		}
		sBuffer.append("<tbody>");
		sBuffer.append("</table> ");
		
		
		
	/*	sBuffer.append("<div class='row rowFlex reFlex'>");
		sBuffer.append("<div class='col-sm-3'>");
		sBuffer.append("</div>");
		sBuffer.append("<div class='col-sm-6' align='center'>");
		sBuffer.append("<button type='buttton' class='btn btn-primary'  onClick ='return accept();' />accept<i class='fas fa-check'></i></button>&nbsp;");
		sBuffer.append("<button type='buttton' class='btn btn-danger'  onClick ='return reject();' />reject<i class='fas fa-ban'></i></button>&nbsp;");
		sBuffer.append("<button type='buttton' class='btn btn-warning'  onClick ='formClear();' />clear</button>&nbsp;");
		sBuffer.append("<button type='buttton' class='btn btn-danger' onClick ='cancel();'>cancel</button>");
		sBuffer.append("</div>");
		sBuffer.append("</div>");
		sBuffer.append("<div class='col-sm-3'>");
		sBuffer.append("</div>");
		*/
		return sBuffer.toString();
	}
	
	
	/**
	 * to get patient status list for View Process 
	 * @param PatientAttendanceVO VO
	 * @return String sBuffer.toString()
	 * 
	 */ 
	public static String getPatientStatusView_BS(PatientAttendanceVO VO) throws Exception {

		WebRowSet ws = VO.getStrPatStatusViewDtlWs();
		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();		
	
		sBuffer.append("<table id='datatable' class='table table-striped table-bordered' style='width:100%'> ");

		if (ws != null && ws.size() != 0) {		
			sBuffer.append("<thead> ");
			sBuffer.append("<tr> ");
			sBuffer.append("<th>CR No.</th> ");
			sBuffer.append(" <th>Admission No.</th> ");
			sBuffer.append(" <th>Patient Name</th> ");
			sBuffer.append(" <th>From Dept/Unit/Ward</th> ");
			sBuffer.append(" <th>Transfer Date/Time</th> ");
			sBuffer.append(" <th>Status</th> ");
			sBuffer.append(" </tr>");
			sBuffer.append("</thead> ");
			sBuffer.append(" </tbody>");
			try {

				int i = 0;
				while (ws.next()){								
					String strcrno = ws.getString(1);
					String stradmno = ws.getString(2);
					String strPatName=ws.getString(3);
					String fromDept=ws.getString(4);
					String dateTime=ws.getString(5);
					String status=ws.getString(6);
					if (stradmno == null)
						stradmno = "-";
					if (strcrno == null)
						strcrno = "-";
					if (fromDept == null)
						fromDept = "-";
					if (dateTime == null)
						dateTime = "-";
					if (strPatName == null)
						strPatName = "-";
					if (status == null)
						status = "-";
						sBuffer.append("<tr> ");
							
							sBuffer.append(" <td>" + strcrno + "</td> ");
							sBuffer.append(" <td>" + stradmno + "</td> ");
							sBuffer.append(" <td>" + strPatName + "</td> ");
							sBuffer.append(" <td>" + fromDept + "</td> ");
							sBuffer.append(" <td>" + dateTime + "</td> ");
							sBuffer.append(" <td>" + status + "</td> ");
							
							sBuffer.append("</td>");
							sBuffer.append(" </tr> ");
							
							i++;					
						} 		
						
			} catch (SQLException e) {
				VO.setStrMsgString("PatientAttendanceDAO.getPatientStatusView() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PatientAttendanceDAO.getPatientStatusView() -->"
						+ e.getMessage());
			}

		} 
		else{
			sBuffer.append("<tr><div class='alert alert-danger'>No Patient Available</font></div></tr>");
				
		}
		sBuffer.append("</body > ");
		sBuffer.append("</table > ");
		
		return sBuffer.toString();
	}

	
	/**
	 * to get accepted/to be transferred patient list
	 * @param PatientAttendanceVO VO
	 * @return String sBuffer.toString()
	 * 
	 */ 
	public static String getPatientList_BS(PatientAttendanceVO VO) throws Exception {

		WebRowSet ws = VO.getStrPatDetailWs();
		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();
		if(ws!=null && ws.size()>0)
			sBuffer.append("<input type='hidden' name='strCount' value="+ws.size()+">");
		else
			sBuffer.append("<input type='hidden' name='strCount' value='0'>");
	
		sBuffer.append("<table id='datatable' class='table table-striped table-bordered' style='width:100%'> ");

		if (ws != null && ws.size() != 0) {
			sBuffer.append("<thead> ");
			sBuffer.append("<tr>");
			sBuffer.append("<th>#</th> ");
			sBuffer.append("<th>CR No.</th> ");
			sBuffer.append(" <th>Admission No.</th> ");
			sBuffer.append(" <th>Patient Name</th> ");
			sBuffer.append(" <th>From Dept/Unit/Ward</th> ");
			sBuffer.append(" <th>Transfer Date/Time</th> ");
			sBuffer.append(" </tr>");	
			sBuffer.append("</thead> ");
			sBuffer.append("<tbody> ");

			try {

				int i = 0;
				while (ws.next()){
					
					String chk=ws.getString(1);
					String strcrno = ws.getString(2);
					String stradmno = ws.getString(3);
					String strPatName=ws.getString(4);
					String fromDept=ws.getString(5);
					String dateTime=ws.getString(6);
					if (stradmno == null)
						stradmno = "-";
					if (strcrno == null)
						strcrno = "-";
					if (fromDept == null)
						fromDept = "-";
					if (dateTime == null)
						dateTime = "-";
					if (strPatName == null)
						strPatName = "-";
					
						sBuffer.append("<tr> ");
							
								
						sBuffer.append("<td><input type ='radio' name = 'strchk'  value ='"
												+ chk
												+ "' id = 'strchk-"
												+ i
												+ "'  onClick = 'getBlkHrs(this,\""
												+ i + "\");'  ></td> ");
							
							sBuffer.append(" <td>" + strcrno + "</font></td> ");
							sBuffer.append(" <td>" + stradmno + "</font></td> ");
							sBuffer.append(" <td>" + strPatName + "</font></td> ");
							sBuffer.append(" <td>" + fromDept + "</font></td> ");
							sBuffer.append(" <td>" + dateTime + "</font></td> ");

							
							sBuffer.append("</td></tr>");
							
							i++;
						}
				sBuffer.append("<tbody> ");
				sBuffer.append(" </table>");
				/*sBuffer.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px' width='100%'> ");
				sBuffer.append("<tr class='TITLE'>");
				sBuffer.append("<td  colspan='2'>Transferred To Details</td>\n");
				sBuffer.append("</tr>");
				sBuffer.append("</table>");
			
		        sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center' width='100%'>"); 
		        sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'></font></div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' >");
		        sBuffer.append("</td>");
		        sBuffer.append("<td width='25%' class='LABEL'><div align='right'><font color='red'>*</font>Same Ward Room</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' >");
		        sBuffer.append("<input type='checkbox' name='strsamewardchk' checked='checked' onclick='callchk2()'>");
		        sBuffer.append("</td></tr>");  
		       
		     
		        sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Department</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' >");
		        sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strDepartment' class='comboNormal' onChange ='fununit();'>"+VO.getStrDeptProperty()+"</select>");
		        sBuffer.append("</td>");
		        sBuffer.append("<td width='25%' class='LABEL'><div align='right'><font color='red'>*</font>Unit</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL'><div id ='unitId'>");
		        sBuffer.append("<select name='strUnit' class='comboNormal' title='Select Unit' onChange='funward();'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></td></tr>");  
		        sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Ward</div></td>");
		        sBuffer.append("<td width='25%' class='CONTROL' ><div id ='wardId'>");
		        sBuffer.append("<select name='strWard' class='comboNormal' title='Select Ward' onChange='funroom()'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></td>");
		        sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Room</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div id ='roomId'>");
				sBuffer.append("<select name='strRoom' class='comboNormal' title='Select Room'><option value='0'>Select Value</option></select>");
				sBuffer.append("</div></td>");
		        sBuffer.append("</tr>"); 
		        sBuffer.append("<tr><td width='25%' class='LABEL'>Remarks</td>\n");
				sBuffer.append("<td width='75%' class='CONTROL' colspan='3'>");
				sBuffer.append("<textarea name='strRemarks' rows='3' cols='25'></textarea>" );
				sBuffer.append("</td>");
		        sBuffer.append("</tr>");  
		        sBuffer.append("</table>");
				*/
				
				
				
				
				sBuffer.append("<p class='subHeaders'>Transferred To Details</p>");
				
				sBuffer.append("<div class='row rowFlex reFlex'>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<label><font color='red'>*</font>Same Ward Room</label>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("<input type='checkbox' name='strsamewardchk' checked='checked' onclick='callchk2()'>");
		        sBuffer.append("</div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("</div>");
				
				sBuffer.append("<div class='row rowFlex reFlex'>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<label><font color='red'>*</font>Department</label>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("<select  name = 'strDepartment' class='form-control' onChange ='fununit();' >"+VO.getStrDeptProperty()+"</select>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<label><font color='red'>*</font>Unit</label>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("<div id ='unitId'><select name='strUnit' class='form-control' onChange='funward();'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("</div>");
				
				
				sBuffer.append("<div class='row rowFlex reFlex'>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<label><font color='red'>*</font>Ward</label>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("<div id ='wardId'><select name='strWard' class='form-control'   onChange='funroom();'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></div>");
		        sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<label><font color='red'>*</font>Room</label>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<div id ='roomId'><select name='strRoom' class='form-control'><option value='0'>Select Value</option></select>");
		        sBuffer.append("</div></div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("</div>");
				
				sBuffer.append("<div class='row rowFlex reFlex'>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<label>Remarks</label>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("<textarea class='form-control' name='strRemarks' rows='1' cols='16'></textarea>" );
		        sBuffer.append("</div></div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("</div>");
		        sBuffer.append("<div class='col-sm-2'>");
		        sBuffer.append("</div></div>");
				sBuffer.append("<div class='col-sm-2'>");
				sBuffer.append("</div>");
				sBuffer.append("</div>");
			} catch (SQLException e) {
				VO.setStrMsgString("PatientAttendanceDAO.getPatientList() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("PatientAttendanceDAO.getPatientList() -->"
						+ e.getMessage());
			}
		} 
		else{
			sBuffer.append("<tr><div class='alert alert-danger'>No Patient Available</font></div></tr></table>");
				
		}
		
	
       /* sBuffer.append("<table align ='center' cellspacing ='1px' width='100%'> ");
		sBuffer.append("<tr class='HEADER'>");
		sBuffer.append("<td  colspan='2'> </td>\n");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");*/
		/*sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='2' align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/btn-save-validate.png'  style='cursor:hand;cursor:pointer;'  title ='transfer ' onClick ='return transfer();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/btn-clr.png'  style='cursor:hand;cursor:pointer;'  title ='clear ' onClick ='formClear();' />&nbsp;");
		sBuffer.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='cancel' onClick ='cancel();' />&nbsp;");
		sBuffer.append("</td></tr>");
		sBuffer.append("</table>");*/
		
		sBuffer.append("<div class='row rowFlex reFlex'>");
		sBuffer.append("<div class='col-sm-3'>");
		sBuffer.append("</div>");
		sBuffer.append("<div class='col-sm-6' align='center'>");
		sBuffer.append("<button type='buttton' class='btn btn-success' onClick ='return transfer();'>Save & validate <i class='fas fa-save'></i></button>&nbsp;");
		sBuffer.append("</div>");
		sBuffer.append("<div class='col-sm-3'>");
		sBuffer.append("</div>");
		sBuffer.append("</div>");
		return sBuffer.toString();
	}
}
