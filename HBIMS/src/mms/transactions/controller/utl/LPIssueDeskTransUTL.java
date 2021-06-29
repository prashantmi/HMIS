package mms.transactions.controller.utl;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

public class LPIssueDeskTransUTL extends TransInterface {

	private static final long serialVersionUID = 02L;
	
	HttpSession httpSession = null;
	String[] cmbVal = null;

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}

	public String getMasterName() {
		String masterName = "Issue Desk";
		return masterName;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[]) 
	{
		StringBuffer brMainQuery = new StringBuffer(500);
		String strReqId="";
  // added by shefali garg 1-jan-2015 & changed by shalini on 13-01-2015 for item category and hardcoded hospital code
		brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_REQ_NO||'@'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_REQ_NO||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')"+
                 " as DATA FROM SSTT_INDENT_DTL A "+
                  "WHERE GNUM_ISVALID = 1 and hstnum_is_utility_indent = 0 and hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1   AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) ORDER BY HIPDT_ADMDATETIME  DESC) and hstnum_indent_status <> 50 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString());
		
		if(cmb!=null)
		{
			
			if(cmb[1].equals("31") && cmb[2].equals("1"))
			{
				 
				
				brMainQuery = new StringBuffer(500);
				 brMainQuery.append("SELECT  HSTNUM_REQ_NO||'@'|| HSTNUM_STORE_ID ||'@'||A.gnum_hospital_code||'@'||a.HSTNUM_URGENT_FLAG||'@'||a.HSTNUM_URGENT_FLAG ||'^'||HSTNUM_REQ_NO||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
                 " mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^0'||'^0'  ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')"+
                 " as DATA FROM SSTT_INDENT_DTL A "+
                  "WHERE GNUM_ISVALID = 1 and hstnum_is_utility_indent = 0 and sstnum_reqtype_id = 17 and hstnum_indent_status in (40,49) AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
                  "and HSTNUM_TOSTORE_ID ="+cmb[0]);
				 
				 
			}
			else
				if(cmb[1].equals("31") && cmb[2].equals("2"))
				{
					 
					
					brMainQuery = new StringBuffer(500);
					 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_REQ_NO||'@'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_REQ_NO||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
	                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
	                 " mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^0'||'^0'  ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')"+
	                 " as DATA FROM SSTT_INDENT_DTL A "+
	                  "WHERE GNUM_ISVALID = 1 and hstnum_is_utility_indent = 0 and hstnum_indent_status > 0 and hstnum_indent_status < 50 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
	                  "and HSTNUM_TOSTORE_ID ="+cmb[0]);
					 
					 
				}
				else 
					if(cmb[1].equals("31") && cmb[2].equals("3"))
				    {
						brMainQuery = new StringBuffer(500);
						 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_REQ_NO||'@'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'@'|| HSTNUM_BS_REFNO || '@' || HSTNUM_URGENT_FLAG ||'^'||HSTNUM_REQ_NO||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
		                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
		                 " mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal') "+
		                 " as DATA FROM SSTT_INDENT_DTL A "+
		                  "where hstnum_indent_status <> 53 and hstnum_is_utility_indent = 0 and hstnum_bs_refno <> 0 and hstnum_bs_refno in (select distinct hstnum_req_no from hstt_po_req_dtl where hstnum_store_id = "+cmb[0]+" and hstnum_po_no in (select distinct hstnum_po_no from decode(A.sstnum_item_cat_no,10,'hstt_drug_currstock_dtl','hstt_item_currstock_dtl') where gnum_hospital_code = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+"  and hstnum_store_id = "+cmb[0]+"))");
								
				
				    }
			else
				if(cmb[1].equals("31") && cmb[2].equals("4"))
				{
						 
						brMainQuery = new StringBuffer(500);
						/*commented by vipul on 12.05.2021 to show indent no. in issue desk*/
						/* brMainQuery.append("SELECT HSTNUM_issue_NO||'@'|| TO_CHAR (HSTDT_issue_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||SSTNUM_ITEM_CAT_NO||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'|| Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STOREID) ||'^'|| mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^0^0^0' as DATA FROM SSTT_ISSUE_DTL A  WHERE GNUM_ISVALID = 1  AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" and sstnum_reqtype_id=31"+
		                  "and HSTNUM_STORE_ID ="+cmb[0]);*/
						
						brMainQuery.append("SELECT HSTNUM_issue_NO||'@'|| TO_CHAR (HSTDT_issue_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||SSTNUM_ITEM_CAT_NO||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'|| HSTNUM_REQ_NO||'^'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'|| Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STOREID) ||'^'|| mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^0^0^0' as DATA FROM SSTT_ISSUE_DTL A  WHERE GNUM_ISVALID = 1  AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" and sstnum_reqtype_id=31"+
				                  "and HSTNUM_STORE_ID ="+cmb[0]);
						  
				}
			
			else 
				if(cmb[1].equals("32") && cmb[2].equals("1"))
			    {
				
					brMainQuery = new StringBuffer(500);
					 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||hstnum_issue_no||'@'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'^'||hstnum_issue_no||'^'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
	                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
	                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'||'Normal' "+
	                " as DATA FROM tmp_pat_issue_dtl A "+
	                  "WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = 101 "+
	                  " and HSTNUM_STORE_ID = "+cmb[0]);
							
			
			    }
			else 
				if(cmb[1].equals("32") && cmb[2].equals("2"))
			    {
				
					brMainQuery = new StringBuffer(500);
					 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||hstnum_issue_no||'@'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'^'||hstnum_issue_no||'^'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
	                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| 'Normal'  ||'^'|| 'Normal' "+
	                " as DATA FROM tmp_pat_issue_dtl A "+
	                  "WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" "+
	                  " and HSTNUM_STORE_ID = "+cmb[0]);
							
			
			    }
				else 
					if(cmb[1].equals("32") && cmb[2].equals("4"))
				    {
					
						brMainQuery = new StringBuffer(500);
						/*commented on 12.05.2021 by vipul to show indent no. in issue desk*/
						/*brMainQuery.append("SELECT HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
						   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||hstnum_req_no||'@'||to_char(hstdt_req_date,'dd-Mon-yyyy hh24:mi')||'@'||GNUM_HOSPITAL_CODE||'^'||hstnum_issue_no||'^'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
		                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| 'Normal'  ||'^'|| 'Normal'  "+
		                " as DATA FROM sstt_patemp_issue_dtl A "+
		                  "WHERE GNUM_ISVALID = 1  AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" "+
		                  " and HSTNUM_STORE_ID = "+cmb[0]);*/
						 brMainQuery.append("SELECT HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
								   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||hstnum_req_no||'@'||to_char(hstdt_req_date,'dd-Mon-yyyy hh24:mi')||'@'||GNUM_HOSPITAL_CODE||'^'||hstnum_issue_no||'^'||hstnum_req_no||'^'|| TO_CHAR (HSTDT_ISSUE_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
				                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| 'Normal'  ||'^'|| 'Normal'  "+
				                " as DATA FROM sstt_patemp_issue_dtl A "+
				                  "WHERE GNUM_ISVALID = 1  AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" "+
				                  " and HSTNUM_STORE_ID = "+cmb[0]);
								
				
				    }
					else 
						if(cmb[1].equals("35") && cmb[2].equals("1"))
					    {
						
							brMainQuery = new StringBuffer(500);
							 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_REQ_NO||'@'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'@'|| HSTNUM_BS_REFNO || '@' || HSTNUM_URGENT_FLAG ||'^'||HSTNUM_REQ_NO||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
			                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
			                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal') "+
			                 " as DATA FROM SSTT_INDENT_DTL A "+
			                  "WHERE GNUM_ISVALID = 1 and hstnum_is_utility_indent = 0 and nvl(hstnum_bs_refno,0) = 0 and sstnum_reqtype_id = 13 and a.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1   AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) ORDER BY HIPDT_ADMDATETIME  DESC) and hipnum_adm_no is not null and hstnum_indent_status = 40 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
			                  "and HSTNUM_TOSTORE_ID ="+cmb[0]);
									
					
					    }
					else 
						if(cmb[1].equals("35") && cmb[2].equals("2"))
					    {
							brMainQuery = new StringBuffer(500);
							 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_REQ_NO||'@'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'@'|| HSTNUM_BS_REFNO || '@' || HSTNUM_URGENT_FLAG ||'^'||HSTNUM_REQ_NO||'^'||DECODE(HSTNUM_BS_REFNO,NULL,'0',HSTNUM_BS_REFNO)||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
			                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
			                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal') "+
			                 " as DATA FROM SSTT_INDENT_DTL A "+
			                  "WHERE GNUM_ISVALID = 1 and hstnum_is_utility_indent = 0 and nvl(hstnum_bs_refno,0) <> 0 and sstnum_reqtype_id = 13 and a.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT COUNT(*) FROM SBLT_OUTBOUND_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_TRANS_TYPE = 1 AND HBLNUM_STATUS = 1 AND SBLNUM_BSERVICE_ID = 21 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)=0  AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) ORDER BY HIPDT_ADMDATETIME  DESC) and hipnum_adm_no is not null and hstnum_indent_status = 49 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+"and hstnum_bs_refno not in (select distinct hstnum_req_no from hstt_po_req_dtl where hstnum_store_id = "+cmb[0]+" and hstnum_po_no in (select distinct hstnum_po_no from decode(A.sstnum_item_cat_no,10,'hstt_drug_currstock_dtl','hstt_item_currstock_dtl') where gnum_hospital_code = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+"  and hstnum_store_id = "+cmb[0]+"))"+
			                  "and HSTNUM_TOSTORE_ID ="+cmb[0]);
									
					
					    }
						else 
							if(cmb[1].equals("35") && cmb[2].equals("3"))
						    {
								brMainQuery = new StringBuffer(500);
								 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_REQ_NO||'@'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'@'|| HSTNUM_BS_REFNO || '@' || HSTNUM_URGENT_FLAG ||'^'||HSTNUM_REQ_NO||'^'||DECODE(HSTNUM_BS_REFNO,NULL,'0',HSTNUM_BS_REFNO)||'^'|| TO_CHAR (HSTDT_REQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
				                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
				                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal') "+
				                 " as DATA FROM SSTT_INDENT_DTL A "+
				                  "where hstnum_indent_status <> 53 and hstnum_is_utility_indent = 0 and hstnum_bs_refno <> 0 and hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT COUNT(*) FROM SBLT_OUTBOUND_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_TRANS_TYPE = 1 AND HBLNUM_STATUS = 1 AND SBLNUM_BSERVICE_ID = 21 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)=0  AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) ORDER BY HIPDT_ADMDATETIME  DESC) and hstnum_bs_refno in (select distinct hstnum_req_no from hstt_po_req_dtl where hstnum_store_id = "+cmb[0]+" and hstnum_po_no in (select distinct hstnum_po_no from decode(A.sstnum_item_cat_no,10,'hstt_drug_currstock_dtl','hstt_item_currstock_dtl') where gnum_hospital_code = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+"  and hstnum_store_id = "+cmb[0]+"))");
										
						
						    }
						else 
							if(cmb[1].equals("35") && cmb[2].equals("4"))
						    {
								brMainQuery = new StringBuffer(500);
								/* brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
						   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||hstnum_req_no||'@'||to_char(hstdt_req_date,'dd-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy hh24:mi')||'^'|| "+
						   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STOREID )||'^'||HRGNUM_PUK||'^'||"+
						    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^0'"+
						   "as  DATA FROM  SSTT_ISSUE_DTL A"+
						    " WHERE GNUM_ISVALID=1 "+
						   " AND HSTNUM_STORE_ID="+cmb[0]+
						    " AND SSTNUM_REQTYPE_ID="+cmb[1]+
						    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" and a.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1   AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) ORDER BY HIPDT_ADMDATETIME  DESC) and a.hipnum_adm_no in (SELECT hipnum_admno AS admno FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1   AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) and hrgnum_puk = a.hrgnum_puk ORDER BY HIPDT_ADMDATETIME  DESC)");*/
							/*commented by vipul on 12.05.2021 to show indent no. in issue desk*/
								brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
										   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||hstnum_req_no||'@'||to_char(hstdt_req_date,'dd-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||HSTNUM_REQ_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy hh24:mi')||'^'|| "+
										   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STOREID )||'^'||HRGNUM_PUK||'^'||"+
										    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^0'"+
										   "as  DATA FROM  SSTT_ISSUE_DTL A"+
										    " WHERE GNUM_ISVALID=1 "+
										   " AND HSTNUM_STORE_ID="+cmb[0]+
										    " AND SSTNUM_REQTYPE_ID="+cmb[1]+
										    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" and a.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1   AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) ORDER BY HIPDT_ADMDATETIME  DESC) and a.hipnum_adm_no in (SELECT hipnum_admno AS admno FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1   AND HIPDT_ADMSTATUS_CODE NOT IN (13,14,18) and hrgnum_puk = a.hrgnum_puk ORDER BY HIPDT_ADMDATETIME  DESC)");
						
						    }
			
			else 
				if(cmb[1].equals("14") && cmb[2].equals("1"))
			    {
				
					brMainQuery = new StringBuffer(500); 
					 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_LPREQ_NO||'@'|| TO_CHAR (HSTDT_LPREQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_LPREQ_NO||'^'|| TO_CHAR (HSTDT_LPREQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
	                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
	                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal') "+
	                 " as DATA FROM HSTT_LP_REQ_DTL A "+
	                  "WHERE GNUM_ISVALID = 1 and hstnum_lpreq_status <> 50 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
	                  "and HSTNUM_TOSTORE_ID ="+cmb[0]);
					
			}
			else 
				if(cmb[1].equals("13") && cmb[2].equals("2"))//TO Be Returned
			    {				 	
				    strReqId="35";
				    brMainQuery = new StringBuffer(500);
				    
				    brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
				   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-YYYY hh24:mi')||'^'|| "+
				   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||"+
				    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)||'^'|| MMS_MST.GET_ITEMCAT_DTL(1,100,SSTNUM_ITEM_CAT_NO) ||'^0' "+
				   "as DATA FROM  SSTT_ISSUE_DTL A"+
				    " WHERE GNUM_ISVALID=1 and sstnum_item_cat_no <> 21"+
				   " AND HSTNUM_STORE_ID="+cmb[0]+
				    " AND SSTNUM_REQTYPE_ID="+strReqId+
				    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());  
				    brMainQuery.append(" AND EXISTS (SELECT 'X' FROM  SSTT_PATEMP_ISSUE_ITEM_DTL");            /******Changed table HSTT To SSTT ********/
				    brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE");  
                    brMainQuery.append(" AND HSTNUM_ISSUE_NO=A.HSTNUM_ISSUE_NO");
                    brMainQuery.append(" AND HSTNUM_STORE_ID=A.HSTNUM_STORE_ID");
                    brMainQuery.append(" AND (HSTNUM_ISSUE_QTY-(HSTNUM_RETURN_QTY*(select MMS_MST.GETUNIT_CONV_VALUE(HSTNUM_RETQTY_UNITID,HSTNUM_ISSUEQTY_UNITID,GNUM_HOSPITAL_CODE) from dual)))>0)");
                    
                 //   System.out.println("Main Query:::>>>>"+brMainQuery);


			}
			/*else 
				if(cmb[1].equals("12") && cmb[2].equals("2"))
			    {
				
				strReqId="36";
				brMainQuery = new StringBuffer(500);
				brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
						   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'^'|| "+
						   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||PISTR_EMP_NO||'^'||"+
						    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) "+
						   " DATA FROM  SSTT_ISSUE_DTL A"+
						    " WHERE GNUM_ISVALID=1"+
						   " AND HSTNUM_STORE_ID="+cmb[0]+
						    " AND SSTNUM_REQTYPE_ID="+strReqId+
						    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());
				
				brMainQuery.append(" AND EXISTS (SELECT 'X' FROM  HSTT_PATEMP_ISSUE_ITEM_DTL");
			    brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE");
                brMainQuery.append(" AND HSTNUM_ISSUE_NO=A.HSTNUM_ISSUE_NO");
                brMainQuery.append(" AND HSTNUM_STORE_ID=A.HSTNUM_STORE_ID");
                brMainQuery.append(" AND (HSTNUM_ISSUE_QTY-(HSTNUM_RETURN_QTY*(select MMS_MST.GETUNIT_CONV_VALUE(HSTNUM_RETQTY_UNITID,HSTNUM_ISSUEQTY_UNITID,GNUM_HOSPITAL_CODE) from dual)))>0)");
        
			}
			else 
				if(cmb[1].equals("14") && cmb[2].equals("2")) 
				{
				    
				 	strReqId="37";
					brMainQuery = new StringBuffer(500);
					brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
							   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-YYYY')||'^'|| "+
							   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||PISTR_EMP_NO||'^'||"+
							    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) "+
							   " DATA FROM  SSTT_ISSUE_DTL A"+
							    " WHERE GNUM_ISVALID=1"+
							   " AND HSTNUM_STORE_ID="+cmb[0]+
							    " AND SSTNUM_REQTYPE_ID="+strReqId+
							    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());
					
					brMainQuery.append(" AND EXISTS (SELECT 'X' FROM  HSTT_PATEMP_ISSUE_ITEM_DTL");
				    brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE");
                    brMainQuery.append(" AND HSTNUM_ISSUE_NO=A.HSTNUM_ISSUE_NO");
                    brMainQuery.append(" AND HSTNUM_STORE_ID=A.HSTNUM_STORE_ID");
                    brMainQuery.append(" AND (HSTNUM_ISSUE_QTY-(HSTNUM_RETURN_QTY*(select MMS_MST.GETUNIT_CONV_VALUE(HSTNUM_RETQTY_UNITID,HSTNUM_ISSUEQTY_UNITID,GNUM_HOSPITAL_CODE) from dual)))>0)");

			}*/
			
			else if(cmb[1].equals("13") && cmb[2].equals("3"))
			{
			 	
				   strReqId="35";
				    brMainQuery = new StringBuffer(500);
				
					 brMainQuery.append("SELECT SSTNUM_ITEM_CAT_NO||'@'||HSTNUM_LPREQ_NO||'@'|| TO_CHAR (HSTDT_LPREQ_DATE,  'DD-Mon-yyyy ')||'@'||HSTNUM_STORE_ID||'@'||HRGNUM_PUK||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_LPREQ_NO||'^'|| TO_CHAR (HSTDT_LPREQ_DATE,  'DD-Mon-yyyy hh24:mi') ||'^'||"+
	                "Mms_Mst.GET_STORE_DTL (1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID) ||'^'||"+
	                 " HRGNUM_PUK ||'^'|| Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||mms_mst.get_itemcat_dtl(1::numeric,gnum_hospital_code,sstnum_item_cat_no) ||'^'|| decode(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')"+
	                 " as DATA FROM HSTT_LP_REQ_DTL A "+
	                  "WHERE GNUM_ISVALID = 1 and sstnum_item_cat_no <> 21 and hstnum_lpreq_status = 50 AND GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
	                  "and HSTNUM_TOSTORE_ID ="+cmb[0]);
				    
				    
				
					
			}
			else if(cmb[1].equals("12") && cmb[2].equals("3"))
			{
				
				strReqId="36";
				brMainQuery = new StringBuffer(500);
				brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
						   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy hh24:mi')||'^'|| "+
						   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||PISTR_EMP_NO||'^'||"+
						    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) ||'^0' "+
						   "  as DATA FROM  SSTT_ISSUE_DTL "+
						    " WHERE GNUM_ISVALID=1 and sstnum_item_cat_no <> 21"+
						   " AND HSTNUM_STORE_ID="+cmb[0]+
						    " AND SSTNUM_REQTYPE_ID="+strReqId+
						    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());
			}
			else 
				if(cmb[1].equals("14") && cmb[2].equals("3"))
				{
				    
				 	strReqId="37";
					brMainQuery = new StringBuffer(500);
					brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
							   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-YYYY hh24:mi')||'^'|| "+
							   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||PISTR_EMP_NO||'^'||"+
							    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) ||'^0'"+
							   "as  DATA FROM  SSTT_ISSUE_DTL "+
							    " WHERE GNUM_ISVALID=1 and sstnum_item_cat_no <> 21"+
							   " AND HSTNUM_STORE_ID="+cmb[0]+
							    " AND SSTNUM_REQTYPE_ID="+strReqId+
							    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());
			}
			
			else 
				if(cmb[1].equals("13") && cmb[2].equals("4"))
				{
			 	
				   strReqId="35";
				    brMainQuery = new StringBuffer(500);
				    
				    brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
				   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-YYYY hh24:mi')||'^'|| "+
				   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||"+
				    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) ||'^0' "+
				   "  as DATA FROM  SSTT_ISSUE_DTL A"+
				    " WHERE GNUM_ISVALID=1 and sstnum_item_cat_no <> 21"+
				   " AND HSTNUM_STORE_ID="+cmb[0]+
				    " AND SSTNUM_REQTYPE_ID="+strReqId+
				    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());  
				    brMainQuery.append(" AND EXISTS (SELECT 'X' FROM  HSTT_PATEMP_ISSUE_ITEM_DTL");
				    brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE");
	             brMainQuery.append(" AND HSTNUM_ISSUE_NO=A.HSTNUM_ISSUE_NO");
	             brMainQuery.append(" AND HSTNUM_STORE_ID=A.HSTNUM_STORE_ID");
	             brMainQuery.append(" AND HSTNUM_RETURN_QTY>0)");
	     
                 //System.out.println("Main Query:::>>>>"+brMainQuery);


			}
			else if(cmb[1].equals("12") && cmb[2].equals("4"))
			{
				
				strReqId="36";
				brMainQuery = new StringBuffer(500);
				brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||HRGNUM_PUK||'@'||PISTR_EMP_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
						   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy hh24:mi')||'^'|| "+
						   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||PISTR_EMP_NO||'^'||"+
						    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) ||'^0'"+
						   "as  DATA FROM  SSTT_ISSUE_DTL A"+
						    " WHERE GNUM_ISVALID=1 and sstnum_item_cat_no <> 21"+
						   " AND HSTNUM_STORE_ID="+cmb[0]+
						    " AND SSTNUM_REQTYPE_ID="+strReqId+
						    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());
				
				brMainQuery.append(" AND EXISTS (SELECT 'X' FROM  HSTT_PATEMP_ISSUE_ITEM_DTL");
			    brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE");
             brMainQuery.append(" AND HSTNUM_ISSUE_NO=A.HSTNUM_ISSUE_NO");
             brMainQuery.append(" AND HSTNUM_STORE_ID=A.HSTNUM_STORE_ID");
             brMainQuery.append(" AND HSTNUM_RETURN_QTY>0)");
     
			}
			else 
			{
				    
				 	strReqId="37";
					brMainQuery = new StringBuffer(500);
					brMainQuery.append("SELECT  HSTNUM_STORE_ID||'@'||HSTNUM_ISSUE_NO||'@'||SSTNUM_ITEM_CAT_NO||'@'||"	+		
							   " TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-yyyy')||'@'||MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'@'||GNUM_HOSPITAL_CODE||'^'||HSTNUM_ISSUE_NO||'^'||TO_CHAR(HSTDT_ISSUE_DATE,'DD-Mon-YYYY hh24:mi')||'^'|| "+
							   " MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )||'^'||HRGNUM_PUK||'^'||PISTR_EMP_NO||'^'||"+
							    " AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK) ||'^0' "+
							   "AS DATA FROM  SSTT_ISSUE_DTL A"+
							    " WHERE GNUM_ISVALID=1 and sstnum_item_cat_no <> 21"+
							   " AND HSTNUM_STORE_ID="+cmb[0]+
							    " AND SSTNUM_REQTYPE_ID="+strReqId+
							    " AND GNUM_HOSPITAL_CODE="+ httpSession.getAttribute("HOSPITAL_CODE").toString());
					
					brMainQuery.append(" AND EXISTS (SELECT 'X' FROM  HSTT_PATEMP_ISSUE_ITEM_DTL");
				    brMainQuery.append(" WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE");
	             brMainQuery.append(" AND HSTNUM_ISSUE_NO=A.HSTNUM_ISSUE_NO");
	             brMainQuery.append(" AND HSTNUM_STORE_ID=A.HSTNUM_STORE_ID");
	             brMainQuery.append(" AND HSTNUM_RETURN_QTY>0)");
	     

			}

			
			
			
			
		}
		System.out.println("In LP Issue Desk Main Query-->>"+brMainQuery.toString());
		
		return brMainQuery.toString();

	}

	public String[] getSearchField() 
	{
		
		String search_field[] = {"1", "HSTNUM_REQ_NO"};
		if(cmbVal != null){
			if(cmbVal[1].equals("32") && cmbVal[2].equals("1"))
			{
				search_field=new String[4];
				search_field[0]="1";
				search_field[1]="HSTNUM_REQ_NO";
				search_field[2]="4";
				search_field[3]="HRGNUM_PUK";
//				search_field[4]="5";
//				search_field[5]="PISTR_EMP_NO";
//				search_field[6]="6";
//				search_field[7]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
				
			}
//			else if(cmbVal[1].equals("14") && cmbVal[2].equals("1")){
//				search_field=new String[2];
//				search_field[0]="3";
//				search_field[1]="MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STORE_ID )";
//				//search_field[2]="3";
//				//search_field[3]="MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STORE_ID )";
//				
//			}
			else if(cmbVal[1].equals("31") && cmbVal[2].equals("1")){
				search_field=new String[4];
				search_field[0]="1";
				search_field[1]="HSTNUM_REQ_NO";
				search_field[2]="4";
				search_field[3]="HRGNUM_PUK";
//				search_field[4]="5";
//				search_field[5]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
			}
			
			if(cmbVal[1].equals("32") && (cmbVal[2].equals("4")))
			{
				search_field=new String[4];
				search_field[0]="1";
				search_field[1]="HSTNUM_ISSUE_NO";
				search_field[2]="4";
				search_field[3]="HRGNUM_PUK";
//				search_field[4]="5";
//				search_field[5]="PISTR_EMP_NO";
//				search_field[6]="6";
//				search_field[7]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
				
			}
			else if(cmbVal[1].equals("31") && (cmbVal[2].equals("4"))){
				search_field=new String[2];
				search_field[0]="1";
				search_field[1]="HSTNUM_ISSUE_NO";
//				search_field[2]="3";
//				search_field[3]="MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )";
				
			}
			else {
				search_field=new String[4];
				search_field[0]="1";
				search_field[1]="HSTNUM_REQ_NO";
				search_field[2]="4";
				search_field[3]="HRGNUM_PUK";
//				search_field[4]="5";
//				search_field[5]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
			}
		}
		else{
			
			search_field=new String[4];
			search_field[0]="1";
			search_field[1]="HSTNUM_REQ_NO";
			search_field[2]="4";
			search_field[3]="HRGNUM_PUK";
//			search_field[4]="5";
//			search_field[5]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";

		}
return search_field;

	}

	/**
	 * 0^0 0 Means Combo From Query,
	 * 1  Means User Defined Combo,0 After ^ Means Select Value,
	 * 1 Means All,2 Means Default Selected
	 * */
	public String[] getComboHeader() 
	{
		//String[] strComboHeader = {"0^2","Drug Warehouse Name","1","Request From","1","Status"};
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] strComboHeader = new String[6];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	

			strComboHeader[0] = "0^0";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "0^0";
			strComboHeader[3] = "Request Type";
			strComboHeader[4] = "1";
			strComboHeader[5] = "Status";

		}
		else
		{
			strComboHeader[0] = "0^0";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "0^0";
			strComboHeader[3] = "Request Type";
			strComboHeader[4] = "1";
			strComboHeader[5] = "Status";

		}

		
		
	    return strComboHeader;
	}

	public String[] getColumnHeader() 
	{
		String[] strColumnHeader = { "Indent No.", "Indent Date", "Raising Store","CR.No", "Patient Name","Item Cat"};
		
		
		
		if(cmbVal != null){
			
			if(cmbVal[1].equals("31"))			//issue to store
			{
				strColumnHeader = new String[5];
			//System.out.println("cmbVal[2]:::::::::::::::::::::"+cmbVal[2]);
				if(cmbVal[2].equals("4"))		//processed
				{
					strColumnHeader[0]="Issue No.";
					strColumnHeader[1]="Indent No.";
					strColumnHeader[2]="Issue Date";
					strColumnHeader[3]="Issuing Store";
					strColumnHeader[4]="Item Cat";
				}
				else
				{
					strColumnHeader = new String[4];
					strColumnHeader[0]="Indent No.";
					strColumnHeader[1]="Indent Date";
					strColumnHeader[2]="Raising Store";
					strColumnHeader[3]="Item Cat";
				}
				
				
			}
			if(cmbVal[1].equals("32"))			//issue to pat(OPD)
			{
				strColumnHeader = new String[5];
				if(cmbVal[2].equals("4"))			//processed
				{
					strColumnHeader = new String[6];
					strColumnHeader[0]="Issue No.";
					strColumnHeader[1]="Indent No.";
					strColumnHeader[2]="Issue Date";
					strColumnHeader[3]="CR.No";
					strColumnHeader[4]="Patient Name";
					strColumnHeader[5]="Item Cat";
				}
				else
				{
					strColumnHeader[0]="Indent No.";
					strColumnHeader[1]="Indent Date";
					strColumnHeader[2]="CR.No";
					strColumnHeader[3]="Patient Name";
					strColumnHeader[4]="Item Cat";
				}
				
				
			}
			if(cmbVal[1].equals("35"))			//issue to pat(IPD)
			{
				strColumnHeader = new String[6];			//processed
				if(cmbVal[2].equals("4"))
				{
					strColumnHeader = new String[7];
					strColumnHeader[0]="Issue No.";
					strColumnHeader[1]="Indent No.";
					strColumnHeader[2]="Issue Date";
					strColumnHeader[3]="Raising Store";
					strColumnHeader[4]="CR.No";
					strColumnHeader[5]="Patient Name";
					strColumnHeader[6]="Item Cat";
				}
				else
					if(cmbVal[2].equals("1"))
					{
						strColumnHeader = new String[6];
						strColumnHeader[0]="Indent No.";
						strColumnHeader[1]="Indent Date";
						strColumnHeader[2]="Raising Store";
						strColumnHeader[3]="CR.No";
						strColumnHeader[4]="Patient Name";
						strColumnHeader[5]="Item Cat";
					}
				else
				{
						strColumnHeader = new String[7];
						strColumnHeader[0]="Indent No.";
						strColumnHeader[1]="LP Indent No.";
						strColumnHeader[2]="Indent Date";
						strColumnHeader[3]="Raising Store";
						strColumnHeader[4]="CR.No";
						strColumnHeader[5]="Patient Name";
						strColumnHeader[6]="Item Cat";
						
					
				}
				
				
			}
			/*if(cmbVal[1].equals("13") && cmbVal[2].equals("2"))
			{
				strColumnHeader = new String[6];
				strColumnHeader[0]="Issue No.";
				strColumnHeader[1]="Req Date";
				strColumnHeader[2]="Raising Store";
				strColumnHeader[3]="CR.No";
				strColumnHeader[4]="Patient Name";
				strColumnHeader[5]="Item Cat";
				
				
			}*/
		}
		
		return strColumnHeader;
	}      

	public String[] getComboQuery() 
	{
			
		String[] comboQuery = new String[3];
		comboQuery[0] = "SELECT HSTNUM_STORE_ID, HSTSTR_STORE_NAME FROM HSTT_STORE_MST A WHERE GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
				" AND GDT_EFFECTIVE_FRM <= SYSDATE AND GNUM_ISVALID = 1 "+
				" AND EXISTS"+
				   "("+
				    "SELECT 'X'"+
				    "FROM GBLT_ROLE_SEAT_TABLE_DTL P"+
				    " WHERE  UPPER(P.gstr_table_name) = UPPER('HSTT_STORE_MST')"+
				    " AND UPPER(P.gstr_column_name) = UPPER('HSTNUM_STORE_ID')"+
				    " AND gnum_column_value = A.HSTNUM_STORE_ID and gnum_module_id = 63"+
				 " AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+httpSession.getAttribute("SEATID").toString()+				 
				 ",A.GNUM_HOSPITAL_CODE)"+
				") ORDER BY INITCAP(HSTSTR_STORE_NAME)";
		
		//comboQuery[1] = "13^Patient#12^Staff#14^Dept";
		//comboQuery[2] = "1^Active#2^To Be Return#3^Issued#4^Returned";    changed as per req. of merging of all issue desk
		
		comboQuery[1] = "SELECT   SSTNUM_INDENTTYPE_ID,"
				+ " SSTSTR_INDENTTYPE_NAME"
				+ "  FROM SSTT_INDENTTYPE_MST A"
                   + " WHERE SSTNUM_REQ_FOR = 3"
                   + " AND GNUM_HOSPITAL_CODE = 100"
                 + "   AND GNUM_ISVALID = 1"
                 + "   AND EXISTS ("
                  + "  SELECT 'X'"
                  + "  FROM HSTT_STORE_REQTYPE_MST"
                  + "  WHERE GNUM_ISVALID = 1 "
                  + "  AND GNUM_HOSPITAL_CODE ='"+ httpSession.getAttribute("HOSPITAL_CODE").toString()+"'"
                  + "  AND SSTNUM_INDENTTYPE_ID = A.SSTNUM_INDENTTYPE_ID" 
                  + "  AND HSTNUM_STORE_ID =#1#) and SSTNUM_INDENTTYPE_ID not in (98,99)"
                  + "  ORDER BY SSTNUM_FILE_TYPE";
		
		comboQuery[2] ="1^New Request#2^In process#3^Item Received#4^Processed";
		
		System.out.println("comboQuery[0] ::"+comboQuery[0]);
		return comboQuery;
		
		
		
	}

	public String getViewQuery() {
		return "";
	}

	public String getButtons() {

		String strButtons = ""; 
		//"<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png'  border=0 tabindex='1' onKeyPress='window.parent.closeTab();' onClick='window.parent.closeTab();' ></a>";

//		String strButtons ="";
		//"<br><a href='#' class='button' onKeyPress='window.parent.closeTab();' onClick='window.parent.closeTab();'><span class='cancel'>Cancel</span></a> ";
		//"<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png'  border=0 tabindex='1' onKeyPress='window.parent.closeTab();' onClick='window.parent.closeTab();' ></a>";

return strButtons;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		deleteQuery[0] = "";

		return deleteQuery;
	}

	public List<String> getDeleteData(String chk) {
		
		List<String> deleteData = new ArrayList<String>();
		return deleteData;
	}

	public String getJsFiles() {
		String files = "../js/LpIssuedesk_Trans.js";
		return files;

	}

	public String[] getRowStatus() {
		String[] status = { "Urgent", "8", "CYAN", "Urgent-----*Return applicable for IPD patients with active billing account only"};
		return status;
	}

	public String getEventState() {
		String str = "issueDeskButtonStatus";
		return str;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() {
		String[] strButtons = {"Issue@validateIssue()@1@00aced@glyphicon-check",
				"Return@validateReturn()@1@32506d@glyphicon-menu-left",
				"View@callViewCnt()@1@007bb6@glyphicon-fullscreen"
				,"Direct IP Issue and LP@callNewIssueDesk()@1@Cb2027@glyphicon-hand-right" // Name@jsFunction()@checkFlg@colorCode@icon
				};	
		return strButtons;
	}

	public String[] getDbButtons() {
		String[] str = { "2" };
		return str;
	}

	public int getMinPanelButton() {
		return 1;
	}

	public String getMenuOption() {
		String menuType = "Tiles";
		return menuType;
	}

	public String getComboEventState() {
		return "changeCombo";
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "GDT_ENTRY_DATE","2","HSTDT_REQ_DATE","3","hstnum_store_id","6","sstnum_item_cat_no"};
		
	/*	if(cmbVal != null){
			
			if(cmbVal[1].equals("12") && cmbVal[2].equals("1"))
			{
				orderBy=new String[8];
				orderBy[0]="1";
				orderBy[1]="HSTNUM_REQ_NO";
				orderBy[2]="4";
				orderBy[3]="HRGNUM_PUK";
				orderBy[4]="5";
				orderBy[5]="PISTR_EMP_NO";
				orderBy[6]="6";
				orderBy[7]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
				
				
				
			}
			else if(cmbVal[1].equals("14") && cmbVal[2].equals("1")){
				orderBy=new String[2];
				orderBy[0]="2";
				orderBy[1]="HSTDT_REQ_DATE";
				//orderBy[2]="3";
				//orderBy[3]="MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_REQ_STORE_ID )";
				
				
			}
			else if(cmbVal[1].equals("13") && cmbVal[2].equals("1")){
				orderBy=new String[6];
				orderBy[0]="1";
				orderBy[1]="HSTNUM_REQ_NO";
				orderBy[2]="4";
				orderBy[3]="HRGNUM_PUK";
				orderBy[4]="5";
				orderBy[5]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
				
			}
			
			else if(cmbVal[1].equals("12") && (cmbVal[2].equals("2")||cmbVal[2].equals("3")||cmbVal[2].equals("4")))
			{
				orderBy=new String[8];
				orderBy[0]="1";
				orderBy[1]="HSTNUM_ISSUE_NO";
				orderBy[2]="4";
				orderBy[3]="HRGNUM_PUK";
				orderBy[4]="5";
				orderBy[5]="PISTR_EMP_NO";
				orderBy[6]="6";
				orderBy[7]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
				
				
			}
			else if(cmbVal[1].equals("14") && (cmbVal[2].equals("2")||cmbVal[2].equals("3")||cmbVal[2].equals("4"))){
				orderBy=new String[4];
				orderBy[0]="1";
				orderBy[1]="HSTNUM_ISSUE_NO";
				orderBy[2]="3";
				orderBy[3]="MMS_MST.GET_STORE_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_STORE_ID )";
				
				
			}
			else {
					orderBy=new String[6];
					orderBy[0]="1";
					orderBy[1]="HSTNUM_ISSUE_NO";
					orderBy[2]="4";
					orderBy[3]="HRGNUM_PUK";
					orderBy[4]="5";
					orderBy[5]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
				
			}
		}
	else{
		
			orderBy=new String[6];
			orderBy[0]="1";
			orderBy[1]="HSTNUM_REQ_NO";
			orderBy[2]="4";
			orderBy[3]="HRGNUM_PUK";
			orderBy[4]="5";
			orderBy[5]="AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK)";
		
	}*/
		
		return orderBy;
	}
	
	@Override
	public String[] getButtonIcons() {
		String[] str={"IssueOnDesk.png","ReturnOnDesk.png","ViewOnDesk.png","ViewOnDesk.png"};
		// TODO Auto-generated method stub
		return str;
	}
	

}
