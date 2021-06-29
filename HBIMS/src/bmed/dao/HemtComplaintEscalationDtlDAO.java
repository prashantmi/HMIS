package bmed.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtComplaintEscalationDtlVO;

public class HemtComplaintEscalationDtlDAO 
{
	public static void getData(HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO, HisDAO dao) 
	{
		String proc_name1 = "";
		proc_name1 = "{call pkg_bmed_view.proc_HEMT_COMPLAINT_ESCAL_DTL(?,?,?,?,?,?)}";
	      
		int procIndex1 = 0;
		String strErr = "";
		WebRowSet ws = null;
		try 
		{
			procIndex1 = dao.setProcedure(proc_name1);
			
			HisUtil.replaceNullValueWithEmptyString(hemtComplaintEscalationDtlVO);
			
			// set value
//			System.out.println("P_mode::::"+hemtComplaintEscalationDtlVO.getStrMode());
//			System.out.println("Req Id::::"+hemtComplaintEscalationDtlVO.getStrReqId());
//			System.out.println("Esc Id:::"+hemtComplaintEscalationDtlVO.getStrEscId());
//			System.out.println("Hosp Code:::"+hemtComplaintEscalationDtlVO.getStrHospitalCode());
			
			dao.setProcInValue(procIndex1, "p_mode",hemtComplaintEscalationDtlVO.getStrMode(),1);
			dao.setProcInValue(procIndex1, "p_request_id", hemtComplaintEscalationDtlVO.getStrReqId(),2);
			dao.setProcInValue(procIndex1, "p_escalation_id", hemtComplaintEscalationDtlVO.getStrEscId(),3);
			dao.setProcInValue(procIndex1, "p_hospital_code", hemtComplaintEscalationDtlVO.getStrHospitalCode(),4);           	
		
			dao.setProcOutValue(procIndex1, "err", 1,5); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,6); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(procIndex1);

			// get value
			strErr = dao.getString(procIndex1, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				hemtComplaintEscalationDtlVO.setWrsData(ws);
				
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{			
			//e.printStackTrace();
			new HisException("bmed", "HemtComplaintEscalationDtlDAO","getData() --> " + e.getMessage());
			hemtComplaintEscalationDtlVO.setStrMsgString("HemtComplaintEscalationDtlDAO.getData() --> "+ e.getMessage());
			hemtComplaintEscalationDtlVO.setStrMsgType("1");
		}
		
		
	}
	
	public static void save(HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		/* Total Parameter 19. 1 out, 20 in parameter */

		final String strproc_name = "{CALL  PKG_BMED_DML.proc_hemt_complaint_Esc_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";
		final int nProcedureIndex;

		try 
		{
			
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			HisUtil.replaceNullValueWithEmptyString(hemtComplaintEscalationDtlVO_p);
			//HisUtil.printStringFieldsOfVO(hemtComplaintEscalationDtlVO_p);
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",hemtComplaintEscalationDtlVO_p.getStrMode(),1);   //1
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_ID",	hemtComplaintEscalationDtlVO_p.getStrReqId(),2);//2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_HOSPITAL_CODE",hemtComplaintEscalationDtlVO_p.getStrHospitalCode(),3);//3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ESCALATION_ID",hemtComplaintEscalationDtlVO_p.getStrEscId(),4);//4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_TYPE",hemtComplaintEscalationDtlVO_p.getStrReqType(),5);//5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ESCALATION_LEVEL",	hemtComplaintEscalationDtlVO_p.getStrEscLevel(),6);//6
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_VENDOR_COMM_ID",hemtComplaintEscalationDtlVO_p.getStrVendorCommId(),7);//7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ESC_MODE",hemtComplaintEscalationDtlVO_p.getStrEscMode(),8); //8
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_ESC_MODE_NUMBER",hemtComplaintEscalationDtlVO_p.getStrEscModeNo(),9);//9
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_SERVICE_ENGG_ID",hemtComplaintEscalationDtlVO_p.getStrServiceEnggId(),10);//10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_ESCALATION_TO",hemtComplaintEscalationDtlVO_p.getStrEscTo(),11);//11
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_ESCALATION_TO_NAME",hemtComplaintEscalationDtlVO_p.getStrEscToName(),12);//12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_ESCALATION_TO_DESIG",hemtComplaintEscalationDtlVO_p.getStrEscToDesignation(),13);//13
			//System.out.println("Esc Date with Time::::::"+hemtComplaintEscalationDtlVO_p.getStrEscDate());			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_ESCALATION_DATE",hemtComplaintEscalationDtlVO_p.getStrEscDate(),14);//14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GSTR_REMARKS",hemtComplaintEscalationDtlVO_p.getStrRemarks(),15);//15
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GDT_ENTRY_DATE",hemtComplaintEscalationDtlVO_p.getStrEntryDate(),16);//16				      
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",hemtComplaintEscalationDtlVO_p.getStrSeatId(),17);//17
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",hemtComplaintEscalationDtlVO_p.getStrIsValid(),18);//18
			hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,19);//19

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);

		} catch (Exception exception) {
			throw new Exception(
					"HemtComplaintEscalationDtlDAO.save(HemtComplaintEscalationDtlVO)-->"
							+ exception.getMessage());
		}
	}


}
