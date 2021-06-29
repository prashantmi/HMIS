package ipd.transactions;

import java.util.ResourceBundle;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

public class MsApprovalTransHLP {

	public static String ocuupationView(MsApprovalTransVO VO) throws Exception {

		StringBuffer sBuffer = new StringBuffer("");

		/*
		 * if(VO.getStrPatCategory().equalsIgnoreCase("Employee")){
		 * 
		 * beanObj.setStrIsPgiEmp("1"); beanObj.setStrGovtEmployee("1");
		 * 
		 * }else{ beanObj.setStrIsPgiEmp("0"); }
		 * if((VO.getStrIsDependent().equals("00"))||(VO.getStrIsDependent().equals("0"))) {
		 * 
		 * beanObj.setStrdependent("0"); } else{ beanObj.setStrdependent("1"); }
		 * 
		 * if(VO.getStrGovtEmployee().equals("0")){
		 * beanObj.setStrGovtEmployee("0"); }
		 * if(VO.getStrGovtEmployee().equals("1")){
		 * beanObj.setStrGovtEmployee("1"); }
		 * 
		 */

		try {

			sBuffer.append("<table width='100%' >");
			sBuffer.append("<tr> ");
			sBuffer.append(" <td class='LABEL'>Dependent</td>");
			sBuffer.append(" <td class='CONTROL' colspan='4'><input type ='radio' name='strdependent' ");
			sBuffer.append("  value='1' ");
			if (!(VO.getStrIsDependent().equals("00"))
					|| (VO.getStrIsDependent().equals("0"))) {
				sBuffer.append("checked='checked' ");
			}
			sBuffer.append("/> Yes ");
			sBuffer.append("<input type ='radio'");
			sBuffer.append(" name='strdependent' value='0' ");
			if ((VO.getStrIsDependent().equals("00"))
					|| (VO.getStrIsDependent().equals("0"))) {
				sBuffer.append("checked='checked' ");
			}
			sBuffer.append(" />No</td></tr>");
			sBuffer.append(" <tr> ");
			sBuffer
					.append(" <td class='LABEL' width ='25%'>Employee Name</td> ");
			sBuffer
					.append(" <td class='CONTROL' width='25%'><input type='text' name='strEmpName' ");
			sBuffer.append(" value='" + VO.getStrEmpName() + "'  /> ");
			sBuffer.append("<input type ='hidden' name ='strEmp_No' value  ='"
					+ VO.getStrEmp_No() + "'/> </td> ");
			sBuffer.append(" <td class='CONTROL' width ='12%'></td> ");
			sBuffer.append(" <td class='LABEL'>Relationship</td> ");
			sBuffer.append(" <td class='CONTROL' width='12%'> ");
			sBuffer.append(" <input type ='text' name ='strRelation'  value='"
					+ VO.getStrRelation() + "' /> ");
			sBuffer
					.append(" <input type ='hidden' name =	'hstrRelation' value ='"
							+ VO.getHstrRelation() + "'</td> ");
			sBuffer.append(" </tr><tr> ");
			sBuffer.append(" <td class='LABEL'>Is Govt. Employee</td>");
			sBuffer
					.append(" <td class='CONTROL'><input type ='radio' name='strGovtEmployee' ");
			sBuffer.append("  value='1' ");
			if (VO.getStrGovtEmployee().equals("1")) {
				sBuffer.append("checked='checked' ");
			}
			sBuffer.append(" /> Yes ");
			sBuffer.append("<input type ='radio'");
			sBuffer.append(" name='strGovtEmployee' value='0' ");
			if (VO.getStrGovtEmployee().equals("0")) {
				sBuffer.append("checked='checked' ");
			}
			sBuffer.append(" />No</td>");
			sBuffer.append(" <td class='CONTROL'></td> ");
			sBuffer.append(" <td class='LABEL'>Designation</td>");
			sBuffer
					.append(" <td class='CONTROL'><input type ='text' name='strDesigName' ");
			sBuffer.append("  value='" + VO.getStrDesigName() + "' /></td>");
			sBuffer.append(" </tr><tr>");
			sBuffer.append(" <td class='LABEL'>Basic/Incom</td>");
			sBuffer
					.append(" <td class='CONTROL'><input type ='text' name='strBasicIncom' ");
			sBuffer.append(" value='" + VO.getStrBasicIncom() + "' /></td>");
			sBuffer.append(" <td class='CONTROL'></td>");
			sBuffer.append(" <td class='LABEL'>Office Name</td>");
			sBuffer
					.append(" <td class='CONTROL'><input type ='text' name='strOfficeName' ");
			sBuffer.append(" value='" + VO.getStrOfficeName() + "' /></td>");
			sBuffer.append(" </tr><tr>");
			sBuffer.append(" <td class='LABEL'>Office Address</td>");
			sBuffer
					.append(" <td class='CONTROL'><input type ='text' name='strOfficeAddres1' ");
			sBuffer
					.append(" value='" + VO.getStrOfficeAddress1()
							+ "' /></td>");
			sBuffer
					.append(" <td class='CONTROL' colspan='3'><input type ='text'");
			sBuffer.append(" name='strOfficeAddress2'  ");
			sBuffer
					.append(" value='" + VO.getStrOfficeAddress2()
							+ "' /></td>");
			sBuffer.append(" </tr><tr>");
			sBuffer.append(" <td class='LABEL'>Phone No</td>");
			sBuffer
					.append(" <td class='CONTROL'><input type ='text' name='strPhoneNo ");
			sBuffer.append("  value='" + VO.getStrPhoneNo() + "'  /></td>");
			if (VO.getStrPatCategory().equalsIgnoreCase("Employee")) {
				sBuffer
						.append(" <td class='CONTROL'><input type = 'hidden' name ='strGovtTypeHidden' value ='4'</td>");
			} else {
				sBuffer
						.append(" <td class='CONTROL'><input type = 'hidden' name ='strGovtTypeHidden' value ='"
								+ VO.getStrGovtTypeHidden() + "'</td>");
			}
			sBuffer.append(" <td class='LABEL' >Govt Type </td>");
			sBuffer.append(" <td class='CONTROL'> ");
			if (VO.getStrPatCategory().equalsIgnoreCase("Employee")) {
				sBuffer
						.append(" <input type ='text' name ='strGovtType' value = 'GOVT. OF INDIA' ");
			} else {
				sBuffer
						.append(" <input type ='text' name ='strGovtType' value = '"
								+ VO.getStrGovtType() + "' ");
			}
			sBuffer.append("</td> ");
			sBuffer.append(" </tr>");
			sBuffer.append("</table>");

		} catch (Exception e) {
			new HisException("Approval Transaction",
					"MsApprovalTransHLP.ocuupationView()-->", e.getMessage());
		}

		return sBuffer.toString();
	}

	public static String approvallist(WebRowSet[] ws) throws Exception {

		StringBuffer sBuffer = new StringBuffer("");
		try {
			//System.out.println("ws.length->"+ws.length);
			for (int i = 0, nlength = ws.length; i < nlength; i++) {
					
				while (ws[i].next()) {
					sBuffer
							.append(" <table class='TABLEWIDTH' align='center' cellspacing ='1px' cellpadding='1px'>");

					sBuffer.append("<tr>");
					sBuffer.append(" <td class ='multiControl' width ='20%' colspan='1'>"
							+ ws[i].getString(1) + " ");
					sBuffer.append(" <input type ='hidden' name ='hcrnoArr' value = "
									+ ws[i].getString(1) + " />");
					sBuffer.append(" <input type ='hidden' name ='hadvnoArr' value = "
									+ ws[i].getString(2) + " /> ");
					sBuffer.append(" <input type ='hidden' name ='hbkdateArr' value = "
									+ ws[i].getString(3) + " /></td> ");
					sBuffer.append(" <td CLASS='multiControl' width ='20%' nowrap colspan='1'>"
									+ ws[i].getString(3) + "</td> ");
					sBuffer.append(" <td CLASS ='multiControl' width ='10%' colspan='1'>"
							+ ws[i].getString(4) + "</td> ");
					sBuffer.append(" <td CLASS ='multiControl' width ='20%' colspan='1' >"
							+ ws[i].getString(5) + "</td> ");//dept Code
					
					sBuffer.append(" <td CLASS ='multiControl' width ='10%' colspan='1'><font color='blue'>"
							+ (ws[i].getString(8).equals("1") ? "Urgent" :"Normal") + " </font></td>");

					// sBuffer.append(" <td CLASS ='multiControl' width
					// ='15%'>"+VO.getStrAdmNo()+"</td> ");
					sBuffer.append(" <td CLASS ='multiControl' width ='20%' colspan='1'>"
							+ ws[i].getString(7) + "</td> ");
					

					sBuffer.append(" </tr>");
					sBuffer.append(" </table>");
						
				}

			}

		}

		catch (Exception e) {

			new HisException("Approval Transaction",
					"MsApprovalTransHLP.approvallist()-->", e.getMessage());

		}

		return sBuffer.toString();

	}

	/*
	 * public static String allotementlist(MsApprovalTransVO VO) throws
	 * Exception{
	 * 
	 * 
	 * WebRowSet ws[]= VO.getApprovalListWs(); StringBuffer sBuffer = new
	 * StringBuffer(""); try{
	 * 
	 * sBuffer.append(" <table width ='100%'> "); sBuffer.append(" <tr> ");
	 * 
	 * sBuffer.append(" <td class='multiLabel' width ='15%'>Cr No</td> ");
	 * sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Name</td> ");
	 * sBuffer.append( "<td CLASS='multiLabel' width ='15%'>Age/Sex</td> ");
	 * sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Dept/Unit</td> ");
	 * sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Ward</td> ");
	 * sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Room</td> ");
	 * sBuffer.append(" <td CLASS='multiLabel' width ='15%'>Bed</td> ");
	 * sBuffer.append(" </tr> ");
	 * 
	 * 
	 * 
	 * for(int i=0, nlength=ws.length;i<nlength;i++){
	 * 
	 * while(ws[i].next()){
	 * 
	 * 
	 * 
	 * sBuffer.append("<tr>"); sBuffer.append("
	 * <td class ='multiControl' width ='15%'>"+ws[i].getString(1)+" ");
	 * sBuffer.append(" <input type ='hidden' name ='hcrno' value =
	 * "+ws[i].getString(1)+" />"); sBuffer.append(" <input type ='hidden' name
	 * ='hadvno' value = "+ws[i].getString(2)+" /> "); sBuffer.append(" <input
	 * type ='hidden' name ='hbkdate' value = "+ws[i].getString(3)+" /></td>
	 * "); sBuffer.append(" <td CLASS='multiControl' width ='15%' nowrap>"+ws[i].getString(4)+"</td>
	 * "); sBuffer.append(" <td CLASS ='multiControl' width ='15%' nowrap>"+ws[i].getString(5)+"</td>
	 * "); sBuffer.append(" <td CLASS ='multiControl' width ='15%' nowrap>"+ws[i].getString(6)+"</td>
	 * "); //sBuffer.append(" <td CLASS ='multiControl' width ='15%'>"+VO.getStrAdmNo()+"</td>
	 * ");
	 * 
	 * sBuffer.append(" <td CLASS ='multiControl' width ='15%'><select name
	 * ='strprivateward'>"+VO.getStrprivatewardValue()+"</select></td> ");
	 * sBuffer.append(" <td CLASS ='multiControl' width ='15%'><select name
	 * ='strroom'>"+ VO.getStrRoomValue()+"</select></td> "); sBuffer.append("
	 * <td CLASS ='multiControl' width ='15%'><select name
	 * ='stbedno'>"+VO.getStrbedvalue()+"</select></td> ");
	 * 
	 * sBuffer.append(" </tr>"); } }
	 * 
	 * sBuffer.append(" </table> "); }
	 * 
	 * 
	 * catch(Exception e){
	 * 
	 * new HisException("Approval
	 * Transaction","MsApprovalTransHLP.approvallist()-->",e.getMessage()); }
	 * 
	 * return sBuffer.toString(); }
	 */
	/*
	 * public static String allotementlist(MsApprovalTransVO VO) throws
	 * Exception {
	 * 
	 * WebRowSet ws = VO.getStrwardallotementListWs();
	 * 
	 * StringBuffer sBuffer = new StringBuffer(""); try {
	 * 
	 * if (ws != null) {
	 * 
	 * sBuffer.append(" <table width ='100%'> "); sBuffer.append(" <tr> ");
	 * 
	 * sBuffer .append(" <td class='multiLabel' width ='15%'>Cr No</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Name</td> ");
	 * sBuffer .append("<td CLASS='multiLabel' width ='15%'>Age/Sex</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Dept/Unit</td>
	 * "); sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Ward</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Room</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Bed</td> ");
	 * sBuffer.append(" </tr> ");
	 * 
	 * while (ws.next()) {
	 * 
	 * sBuffer.append("<tr>"); sBuffer.append("
	 * <td class ='multiControl' width ='15%'>" + ws.getString(1) + " ");
	 * sBuffer .append(" <input type ='hidden' name ='hcrno' value = " +
	 * ws.getString(1) + " />"); sBuffer .append(" <input type ='hidden' name
	 * ='hadvno' value = " + ws.getString(2) + " /> "); sBuffer .append(" <input
	 * type ='hidden' name ='hbkdate' value = " + ws.getString(3) + " /></td>
	 * "); sBuffer .append(" <td CLASS='multiControl' width ='15%' nowrap>" +
	 * ws.getString(4) + "</td> "); sBuffer .append("
	 * <td CLASS ='multiControl' width ='15%' nowrap>" + ws.getString(5) + "</td>
	 * "); sBuffer .append(" <td CLASS ='multiControl' width ='15%' nowrap>" +
	 * ws.getString(6) + "</td> "); // sBuffer.append(" <td CLASS
	 * ='multiControl' width // ='15%'>"+VO.getStrAdmNo()+"</td> ");
	 * 
	 * sBuffer .append(" <td CLASS ='multiControl' width ='15%'><select name
	 * ='strprivateward' onChange = 'roomCombo(\"ROOM\";'>" +
	 * VO.getStrprivatewardValue() + "</select></td> "); sBuffer .append("
	 * <td CLASS ='multiControl' width ='15%'> <div id='roomid'><select name =
	 * 'strroom' ></select></td> "); sBuffer .append("
	 * <td CLASS ='multiControl' width ='15%'><div id='bedid'><select name =
	 * 'stbedno' ></select> </td> ");
	 * 
	 * sBuffer.append(" </tr>"); }
	 * 
	 * sBuffer.append(" </table> "); } }
	 * 
	 * catch (Exception e) {
	 * 
	 * new HisException("Approval Transaction",
	 * "MsApprovalTransHLP.approvallist()-->", e.getMessage()); }
	 * 
	 * return sBuffer.toString(); }
	 */

	public static String allotementlist(MsApprovalTransVO VO) throws Exception {

		WebRowSet ws = VO.getStrwardallotementListWs();

		StringBuffer sBuffer = new StringBuffer("");

		int count = 0;
		try {

			if (ws != null) {

				sBuffer.append("<table width ='100%'>");
				sBuffer.append("<tr>");
				sBuffer.append("<td class='multiLabel' width ='15%'></td>");
				sBuffer.append("<td class='multiLabel' width ='15%'>CR No</td>");
				sBuffer.append("<td CLASS='multiLabel' width ='15%'>Name</td>");
				sBuffer.append("</tr>");

				while (ws.next()) 
				{
					sBuffer.append("<tr>");
					sBuffer.append("<td class ='multiControl' width ='15%'>");

					if (count == 0) {
						sBuffer
								.append(" <input type ='radio' name ='selectedpat' checked='checked' value='"
										+ ws.getString(1) + "'> </td>");
					} else {
						sBuffer
								.append(" <input type ='radio' name ='selectedpat' value='"
										+ ws.getString(1) + "'> </td>");
					}
					sBuffer.append(" <td class ='multiControl' width ='15%'>"
							+ ws.getString(1) + " </td>");

					sBuffer
							.append(" <input type ='hidden' name ='hcrno' value = "
									+ ws.getString(1) + " />");
					sBuffer
							.append(" <input type ='hidden' name ='hadvno' value = "
									+ ws.getString(2) + " /> ");
					sBuffer
							.append(" <input type ='hidden' name ='hbkdate' value = "
									+ ws.getString(3) + " /></td> ");

					sBuffer
							.append(" <td CLASS='multiControl' width ='15%' nowrap>"
									+ ws.getString(4) + "</td> ");

					sBuffer.append(" </tr>");

					count++;
				}

				sBuffer.append(" </table> ");

			}
		}

		catch (Exception e) {

			new HisException("Approval Transaction",
					"MsApprovalTransHLP.approvallist()-->", e.getMessage());

		}

		return sBuffer.toString();

	}

	/*
	 * public static String allotementlist(MsApprovalTransVO VO) throws
	 * Exception {
	 * 
	 * WebRowSet ws = VO.getStrwardallotementListWs();
	 * 
	 * StringBuffer sBuffer = new StringBuffer(""); int count =0; try {
	 * 
	 * if (ws != null) {
	 * 
	 * 
	 * 
	 * sBuffer.append(" <table width ='100%'> "); sBuffer.append(" <tr> ");
	 * 
	 * sBuffer .append(" <td class='multiLabel' width ='15%'>Cr No</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Name</td> ");
	 * sBuffer .append("<td CLASS='multiLabel' width ='15%'>Age/Sex</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Dept/Unit</td>
	 * "); sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Ward</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Room</td> ");
	 * sBuffer .append(" <td CLASS='multiLabel' width ='15%'>Bed</td> ");
	 * sBuffer.append(" </tr> ");
	 * 
	 * while (ws.next()) {
	 * 
	 * count++;
	 * 
	 * sBuffer.append("<tr>"); sBuffer.append("
	 * <td class ='multiControl' width ='15%'>" + ws.getString(1) + " ");
	 * sBuffer .append(" <input type ='hidden' name ='hcrnoArr' value = " +
	 * ws.getString(1) + " />"); sBuffer .append(" <input type ='hidden' name
	 * ='hadvnoArr' value = " + ws.getString(2) + " /> "); sBuffer .append("
	 * <input type ='hidden' name ='hbkdateArr' value = " + ws.getString(3) + " /></td>
	 * "); sBuffer .append(" <td CLASS='multiControl' width ='15%' nowrap>" +
	 * ws.getString(4) + "</td> "); sBuffer .append("
	 * <td CLASS ='multiControl' width ='15%' nowrap>" + ws.getString(5) + "</td>
	 * "); sBuffer .append(" <td CLASS ='multiControl' width ='15%' nowrap>" +
	 * ws.getString(6) + "</td> "); // sBuffer.append(" <td CLASS
	 * ='multiControl' width // ='15%'>"+VO.getStrAdmNo()+"</td> ");
	 * 
	 * sBuffer .append(" <td CLASS ='multiControl' width ='15%'><select name
	 * ='strprivateward' onChange = 'roomCombo(this,\"ROOM\",\""+count+"\");'>" +
	 * VO.getStrprivatewardValue() + "</select></td> "); sBuffer .append("
	 * <td CLASS ='multiControl' width ='15%'> <div id='roomid"+count+"'><select
	 * name = 'strroom' ></select></td> "); sBuffer .append("
	 * <td CLASS ='multiControl' width ='15%'><div id='bedid"+count+"'><select
	 * name = 'strbedno' ></select></td> ");
	 * 
	 * 
	 * sBuffer .append(" <input type = 'hidden' name ='strAdviceNo' value = +
	 * ");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * sBuffer.append(" </tr>"); }
	 * 
	 * sBuffer.append(" </table> "); } }
	 * 
	 * catch (Exception e) {
	 * 
	 * new HisException("Approval Transaction",
	 * "MsApprovalTransHLP.approvallist()-->", e.getMessage()); }
	 * 
	 * return sBuffer.toString(); }
	 * 
	 */

	public static String hlproomdetail(MsApprovalTransVO VO) throws Exception {

		StringBuffer sBuffer = new StringBuffer("");

		try {

			sBuffer.append("<table width='100%' >");
			sBuffer.append(" <tr> ");
			sBuffer.append(" <td width='21%' class='LABEL'>Room Type</td> ");
			sBuffer.append(" <td width='21%' class='CONTROL'>"
					+ VO.getStrRoomType() + " ");
			sBuffer
					.append(" <input type ='hidden' name ='hstrRoomType' value ="
							+ VO.getHstrRoomType() + " </td> ");
			sBuffer.append(" <td width='21%' class='LABEL' >Building</td> ");

			sBuffer.append(" <td width='21%' class='CONTROL'>");
			if (VO.getStrBuilding() != null) {
				sBuffer.append("" + VO.getStrBuilding() + "");
			}
			sBuffer.append("</td> ");

			sBuffer.append(" </tr> ");
			sBuffer.append(" <tr> ");
			sBuffer.append(" <td width='21%' class='LABEL'>Block</td> ");
			sBuffer.append(" <td width='21%' class='CONTROL'>");
			if (VO.getStrBlock() != null) {
				sBuffer.append("" + VO.getStrBlock() + "");
			}
			sBuffer.append("</td> ");
			sBuffer.append(" <td width='21%' class='LABEL' >Floor</td> ");
			sBuffer.append(" <td width='21%' class='CONTROL'>");
			if (VO.getStrFloor() != null) {
				sBuffer.append("" + VO.getStrFloor() + "");
			}
			sBuffer.append("</td> ");
			sBuffer.append(" </tr> ");
			sBuffer.append(" <tr> ");
			sBuffer.append(" <td width='21%' class='LABEL'>Bed</td> ");

			sBuffer.append(" <td width='21%' class='CONTROL' colspan='3'> ");
			if (VO.getStrPrivateBed() != null) {
				sBuffer.append(" " + VO.getStrPrivateBed() + " ");

				sBuffer
						.append(" <input type ='hidden' name ='hstrbedcode' value ="
								+ VO.getStrPrivateBed() + "  ");
				sBuffer
						.append(" <input type ='hidden' name ='hstrbedType' value ="
								+ VO.getHstrbedType() + " </td> ");

			}
			sBuffer.append(" </table> ");

		}

		catch (Exception e) {
			new HisException("Approval Transaction",
					"MsApprovalTransHLP.hlproomdetail()-->", e.getMessage());
		}

		return sBuffer.toString();
	}

	public static String hlpallocatedlis(MsApprovalTransVO VO) throws Exception {

		StringBuffer sBuffer = new StringBuffer("");

		try {
			sBuffer.append("<table width='100%' >");
			sBuffer.append(" <tr> ");
			sBuffer
					.append("<td  class ='TITLE' colspan='6'>Approved and Wait List>></td></tr> ");
			sBuffer.append("<tr> ");
			sBuffer.append("<td colspan ='6' align='center'><u><b>Date :</b>"
					+ VO.getStrCtDate() + "</u> </td> </tr> ");
			sBuffer.append(" <tr> ");
			sBuffer.append(" <td width='21%'><b>Cr No</b></td> ");
			sBuffer.append(" <td width='21%'><b>Name</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Age/Sex</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Department/Unit</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Ward</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Bed</b></td> ");
			sBuffer.append(" </tr> ");
			sBuffer
					.append("<tr><td colspan ='6'><hr  color ='black'></hr></td></tr>");
			for (int i = 0, arraySize = VO.getStrApprovepatList().size(); i < arraySize; i = i + 6) {

				sBuffer.append(" <tr> ");
				sBuffer.append(" <td width='21%' >"
						+ VO.getStrApprovepatList().get(i) + " </td>");
				sBuffer.append(" <td width='21%'  nowrap>"
						+ VO.getStrApprovepatList().get(i + 1) + " </td>");
				sBuffer.append(" <td width='21%' nowrap>"
						+ VO.getStrApprovepatList().get(i + 2) + "</td> ");
				sBuffer.append(" <td width='21%'>"
						+ VO.getStrApprovepatList().get(i + 3) + "</td> ");
				sBuffer.append(" <td width='21%'>"
						+ VO.getStrApprovepatList().get(i + 4) + "</td> ");
				sBuffer.append(" <td width='21%'>"
						+ VO.getStrApprovepatList().get(i + 5) + "</td> ");

			}

			sBuffer.append(" </table> ");

		} catch (Exception e) {
			new HisException("Approval Transaction",
					"MsApprovalTransHLP.hlpallocatedlis()-->", e.getMessage());
		}

		return sBuffer.toString();
	}

	public static String hlpNotApprovedList(MsApprovalTransVO VO)
			throws Exception {

		StringBuffer sBuffer = new StringBuffer("");

		try {
			sBuffer.append("<table width='100%' >");
			sBuffer.append(" <tr> ");
			sBuffer
					.append("<td  class ='TITLE' colspan='4'>Not Approved Pateint List >> </td></tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td colspan ='4' align='center'><u><b>Date :</b>"
					+ VO.getStrCtDate() + "</u> </td> </tr> ");
			sBuffer.append(" <tr> ");
			sBuffer.append(" <td width='21%'><b>Cr No</b></td> ");
			sBuffer.append(" <td width='21%'><b>Name</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Age/Sex</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Department/Unit</b></td> ");
			sBuffer.append(" </tr> ");
			sBuffer
					.append("<tr><td colspan ='4'><hr  color ='black'></hr></td></tr>");
			for (int i = 0, arraySize = VO.getStrNotApprovedList().size(); i < arraySize; i = i + 4) {

				/*
				 * System.out.println("VO.getStrNotApprovedList().size()==" +
				 * VO.getStrNotApprovedList().size());
				 * System.out.println("VO.getStrNotApprovedList().get(i)==" +
				 * VO.getStrNotApprovedList().get(i));
				 * System.out.println("VO.getStrNotApprovedList().get(i+1)==" +
				 * VO.getStrNotApprovedList().get(i + 1));
				 * System.out.println("VO.getStrNotApprovedList().get(i+2)==" +
				 * VO.getStrNotApprovedList().get(i + 2));
				 * System.out.println("VO.getStrNotApprovedList().get(i+3)==" +
				 * VO.getStrNotApprovedList().get(i + 3));
				 */
				sBuffer.append(" <tr> ");
				sBuffer.append(" <td width='21%' >"
						+ VO.getStrNotApprovedList().get(i) + " </td>");
				sBuffer.append(" <td width='21%'  nowrap>"
						+ VO.getStrNotApprovedList().get(i + 1) + " </td>");
				sBuffer.append(" <td width='21%' nowrap>"
						+ VO.getStrNotApprovedList().get(i + 2) + "</td> ");
				sBuffer.append(" <td width='21%'>"
						+ VO.getStrNotApprovedList().get(i + 3) + "</td> ");

			}

			sBuffer.append(" </table> ");

		} catch (Exception e) {
			new HisException("Approval Transaction",
					"MsApprovalTransHLP.hlpallocatedlist()-->", e.getMessage());
		}

		return sBuffer.toString();
	}

	public static String hlpCancelList(MsApprovalTransVO VO) throws Exception {

		StringBuffer sBuffer = new StringBuffer("");

		try {
			sBuffer.append("<table width='100%' >");
			sBuffer.append(" <tr> ");
			sBuffer
					.append("<td  class ='TITLE' colspan='4'>Cancel List >> </td></tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td colspan ='4' align='center'><u><b>Date :</b>"
					+ VO.getStrCtDate() + "</u> </td> </tr> ");
			sBuffer.append(" <tr> ");
			sBuffer.append(" <td width='21%'><b>Cr No</b></td> ");
			sBuffer.append(" <td width='21%'><b>Name</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Age/Sex</b></td> ");
			sBuffer.append(" <td width='21%' ><b>Department/Unit</b></td> ");
			sBuffer.append(" </tr> ");
			sBuffer
					.append("<tr><td colspan ='4'><hr  color ='black'></hr></td></tr>");
			for (int i = 0, arraySize = VO.getStrCancelList().size(); i < arraySize; i = i + 4) {

				sBuffer.append(" <tr> ");
				sBuffer.append(" <td width='21%' >"
						+ VO.getStrCancelList().get(i) + " </td>");
				sBuffer.append(" <td width='21%'  nowrap>"
						+ VO.getStrCancelList().get(i + 1) + " </td>");
				sBuffer.append(" <td width='21%' nowrap>"
						+ VO.getStrCancelList().get(i + 2) + "</td> ");
				sBuffer.append(" <td width='21%'>"
						+ VO.getStrCancelList().get(i + 3) + "</td> ");

			}

			sBuffer.append(" </table> ");

		} catch (Exception e) {
			new HisException("Approval Transaction",
					"MsApprovalTransHLP.hlpallocatedlis()-->", e.getMessage());
		}

		return sBuffer.toString();
	}
	public static String patApprovedList(MsApprovalTransVO _msApprovalTransVO) 
	{
		StringBuffer sBuffer = null;
		int tmpI = 0;
		int nCrNoLength = 0;
		MsApprovalTransBO msApprovalTransBO = null;
		try 
		{
			sBuffer = new StringBuffer();
			msApprovalTransBO = new MsApprovalTransBO();

			nCrNoLength = Integer.parseInt(ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties").getString("CR_FORMAT"));

			sBuffer.append("<tr>");
			sBuffer.append("<td class='multiLabel' align='center' width='5%'>#</td>");
			sBuffer.append("<td class='multiLabel' align='center' width='25%'>CR No</td>");
			sBuffer.append("<td class='multiLabel' align='center' width='30%'>Name</td>");
			sBuffer.append("<td class='multiLabel' align='center' width='15%'>Age/Sex</td>");
			sBuffer.append("<td class='multiLabel' align='center' width='25%'>Dept/Unit/Ward</td>");
			sBuffer.append("</tr>");
			while (tmpI < _msApprovalTransVO.getStrChk1().length) 
			{
				_msApprovalTransVO.setStrCrNo(_msApprovalTransVO.getStrChk1()[tmpI].substring(0, nCrNoLength));
				msApprovalTransBO.patApprovedList(_msApprovalTransVO);

				if (_msApprovalTransVO.getStrMsgType().equals("1"))
					throw new Exception(_msApprovalTransVO.getStrMsgString());

				if (_msApprovalTransVO.getWsPatApprovedList().next()) 
				{
					sBuffer.append("<tr>");
					sBuffer.append("<td class='multiControl'><input type='radio' onclick='wardCombo()' name='approvedCrNo' value='"+ _msApprovalTransVO.getStrChk1()[tmpI]+ "@"
									+ _msApprovalTransVO.getWsPatApprovedList().getString(5)+ "@"+ _msApprovalTransVO.getWsPatApprovedList().getString(6) + "'></td>");
					sBuffer.append("<td class='multiControl'>"+ _msApprovalTransVO.getWsPatApprovedList().getString(1) + "</td>");
					sBuffer.append("<td class='multiControl'>"+ _msApprovalTransVO.getWsPatApprovedList().getString(2) + "</td>");
					sBuffer.append("<td class='multiControl'>"+ _msApprovalTransVO.getWsPatApprovedList().getString(3) + "</td>");
					sBuffer.append("<td class='multiControl'>"+ _msApprovalTransVO.getWsPatApprovedList().getString(4) );
					sBuffer.append("<input type='hidden' name='strPatCatCode' value='"+_msApprovalTransVO.getWsPatApprovedList().getString(7)+"'>");
					sBuffer.append("<input type='hidden' name='strHiddenPatDtl' value='^^^"+_msApprovalTransVO.getWsPatApprovedList().getString(3)+"'>");
					sBuffer.append("</td></tr>");
					
					
				}
				tmpI++;
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			_msApprovalTransVO.setStrMsgType("1");
			_msApprovalTransVO.setStrMsgString("MsApprovalTransHLP.patApprovedList() -->"+ e.getMessage());
		}
		return sBuffer.toString();
	}
}