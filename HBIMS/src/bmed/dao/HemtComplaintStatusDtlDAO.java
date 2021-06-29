package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import bmed.vo.HemtComplaintStatusDtlVO;

public class HemtComplaintStatusDtlDAO 
{
	
	/*
	 * To get data
	 * 
	 * @param hemtComplaintStatusDtlVO_p		the HemtComplaintStatusDtlVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void getData(HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To insert or update data
	 * 
	 * @param hemtComplaintStatusDtlVO_p		the HemtComplaintStatusDtlVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void save(HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO_p, HisDAO hisDAO_p) throws Exception
	{
		   /*   Total Variable 12   */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_complaint_status_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?)}";
		final int nProcedureIndex;
			try 
			{			
				
					nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
					
					HisUtil.replaceNullValueWithEmptyString(hemtComplaintStatusDtlVO_p);
					
//					System.out.println("p_mode"+hemtComplaintStatusDtlVO_p.getStrMode());                      //1
//					System.out.println("p_HEMNUM_REQ_ID"+hemtComplaintStatusDtlVO_p.getStrReqId());            //  2
//					System.out.println("p_GNUM_HOSPITAL_CODE"+hemtComplaintStatusDtlVO_p.getStrHospitalCode());	//3
//					System.out.println("p_HEMNUM_SL_NO"+hemtComplaintStatusDtlVO_p.getStrSlNo());       //4	
//					System.out.println("p_HEMNUM_MAIN_STATUS"+hemtComplaintStatusDtlVO_p.getStrMainStatus());	//5			
//					System.out.println("p_HEMNUM_SUB_STATUS"+hemtComplaintStatusDtlVO_p.getStrSubStatus());    //6      
//					System.out.println("p_HEMNUM_TRANS_ID"+ hemtComplaintStatusDtlVO_p.getStrTransId());       //7                            
//					System.out.println("p_HEMNUM_ACTION_ID" +hemtComplaintStatusDtlVO_p.getStrActionId());  // 8 
//					System.out.println("p_GSTR_REMARKS"+hemtComplaintStatusDtlVO_p.getStrRemarks());  // 9
//					System.out.println("p_GDT_ENTRY_DATE"+""); // Hard Coded in Proc                    // 10  
//					System.out.println("p_GNUM_ISVALID" +hemtComplaintStatusDtlVO_p.getStrIsValid());  // 11
//					System.out.println("p_GNUM_SEATID"+hemtComplaintStatusDtlVO_p.getStrSeatId());  //12

					hisDAO_p.setProcInValue(nProcedureIndex,"p_mode",hemtComplaintStatusDtlVO_p.getStrMode(),1);                      //1
					
					hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_REQ_ID",hemtComplaintStatusDtlVO_p.getStrReqId(),2);                //2
					hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_HOSPITAL_CODE",hemtComplaintStatusDtlVO_p.getStrHospitalCode(),3);	//3
					hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_SL_NO",hemtComplaintStatusDtlVO_p.getStrSlNo(),4);                  //4	
					hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_MAIN_STATUS",hemtComplaintStatusDtlVO_p.getStrMainStatus(),5);	    //5			
					hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_SUB_STATUS",hemtComplaintStatusDtlVO_p.getStrSubStatus(),6);        //6      
					hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_TRANS_ID", hemtComplaintStatusDtlVO_p.getStrTransId(),7);           //7                            
					hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_ACTION_ID" ,hemtComplaintStatusDtlVO_p.getStrActionId(),8);         //8 
					hisDAO_p.setProcInValue(nProcedureIndex,"p_GSTR_REMARKS",hemtComplaintStatusDtlVO_p.getStrRemarks(),9);               //9
					hisDAO_p.setProcInValue(nProcedureIndex,"p_GDT_ENTRY_DATE","",10); // Hard Coded in Proc                               //10   
					hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_ISVALID" ,hemtComplaintStatusDtlVO_p.getStrIsValid(),11);  // 11
					hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_SEATID",hemtComplaintStatusDtlVO_p.getStrSeatId(),12);  //12
					
					/* Setting and Registering In and Out Parameters */
					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13); // varchar    //13
						
					/* Executing Procedure */
					hisDAO_p.execute(nProcedureIndex, 1);
				
			} 
			catch (Exception exception) 
			{				
			   throw new Exception("HemtItemMcDtlDAO.getItemBrandCombo(HemtItemMcDtlVO)-->"	+ exception.getMessage());
			}
	
	}

	
	/*
	 * To modify data
	 * 
	 * @param hemtComplaintStatusDtlVO_p		the HemtComplaintStatusDtlVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void modify(HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To delete data
	 * 
	 * @param hemtComplaintStatusDtlVO_p		the HemtComplaintStatusDtlVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void delete(HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO_p, HisDAO hisDAO_p)
	{
		
		
	}
}
