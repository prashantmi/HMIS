package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtReminderDtlVO;

public class HemtReminderDtlDAO {
	/*
	 * To get data
	 * 
	 * @param hemtReminderDtlVO_p the HemtReminderDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void getData(HemtReminderDtlVO hemtReminderDtlVO_p,HisDAO hisDAO_p)throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_hemt_reminder_dtl(?,?,?,?,? ,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(hemtReminderDtlVO_p);

			/* Setting and Registering In and Out Parameters */

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",	hemtReminderDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_req_id",hemtReminderDtlVO_p.getStrReqId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_reminder_id",hemtReminderDtlVO_p.getStrReminderId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",	hemtReminderDtlVO_p.getStrHospitalCode(),4);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // 1 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // 2

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			hemtReminderDtlVO_p.setWrsData(webRowSet);

		} 
		catch (Exception exception) 
		{
			throw new Exception("HemtReminderDtlDAO.getData(HemtReminderDtlVO,HisDAO)-->"+ exception.getMessage());
		}

	}
	/*
	 * To insert or update data
	 * 
	 * @param hemtReminderDtlVO_p the HemtReminderDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void save(HemtReminderDtlVO hemtReminderDtlVO_p,HisDAO hisDAO_p) throws Exception 
	{
		/* Total Variable 17 */// Insertion into the table
		// HEMT_REMINDER_DTL
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_reminder_dtl(?,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
		final int nProcedureIndex;
		try 
		{		
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			HisUtil.replaceNullValueWithEmptyString(hemtReminderDtlVO_p);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",hemtReminderDtlVO_p.getStrMode(),1); // 1
						
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_ID",	hemtReminderDtlVO_p.getStrReqId(),2); // 2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_HOSPITAL_CODE",hemtReminderDtlVO_p.getStrHospitalCode(),3); // 3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REMINDER_ID",hemtReminderDtlVO_p.getStrReminderId(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_TYPE",hemtReminderDtlVO_p.getStrReqType(),5); // 5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_REMINDER_BY_ID",hemtReminderDtlVO_p.getStrReminderById(),6); // 6
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_REMINDER_REMARKS",hemtReminderDtlVO_p.getStrReminderRemarks(),7); // 7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_REMINDER_SENT_DATE",hemtReminderDtlVO_p.getStrReminderSentDate(),8);//8
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_REMINDER_REPLY_ID", hemtReminderDtlVO_p.getStrReminderReplyId(),9); // 9
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_REPLY_REMARKS", hemtReminderDtlVO_p.getStrReplyRemarks(),10); //10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_REMINDER_REPLY_DATE",	hemtReminderDtlVO_p.getStrReminderReplyDate(),11); // 11
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REMINDER_STATUS",hemtReminderDtlVO_p.getStrReminderStatus(),12); // 12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GDT_ENTRY_DATE",hemtReminderDtlVO_p.getStrEntryDate(),13); // 13
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_ISVALID",hemtReminderDtlVO_p.getStrIsValid(),14); // 14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_SEATID",hemtReminderDtlVO_p.getStrSeatId(),15); // 15
			hisDAO_p.setProcInValue(nProcedureIndex, "P_REMINDER_TYPE",hemtReminderDtlVO_p.getRemindertype(),16); // 16
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,17); // 17
			
			hisDAO_p.execute(nProcedureIndex, 1);
		}
		catch (Exception exception) 
		{			
			exception.printStackTrace();	
			throw new Exception("HemtReminderDtlDAO.save(hemtReminderDtlVO_p,hisDAO_p)-->"+ exception.getMessage());
		}
	}

	/*
	 * To modify data
	 * 
	 * @param hemtReminderDtlVO_p the HemtReminderDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void modify(HemtReminderDtlVO hemtReminderDtlVO_p,HisDAO hisDAO_p) 
	{

	}

	/*
	 * To delete data
	 * 
	 * @param hemtReminderDtlVO_p the HemtReminderDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void delete(HemtReminderDtlVO hemtReminderDtlVO_p,HisDAO hisDAO_p) 
	{

	}

}
