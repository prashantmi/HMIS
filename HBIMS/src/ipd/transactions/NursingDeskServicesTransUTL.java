package ipd.transactions;

import java.util.List;

import hisglobal.transactionutil.TransInterface;

import javax.servlet.http.HttpSession;

public class NursingDeskServicesTransUTL extends TransInterface {

	HttpSession session = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	@Override
	public String getButtonConfiguration() {
		// TODO Auto-generated method stub
		return "left";
	}

	@Override
	public String getButtons() {
		String strButtons = "";
		return strButtons;
	}

	@Override
	public String[] getColumnHeader() {
		String[] strColumnHeader = {"Adm No","CR No","Name","Age/Sex","Bed"};
		return strColumnHeader;
	}

	@Override
	public String[] getComboHeader() {
		String[] strComboHeader = { "0","Department","0","Unit","0","Ward","0","Room","1","Services" };
		
		return strComboHeader;
	}

	@Override
	public String[] getComboQuery() {
		String[] strComboQry = new String[5];
		strComboQry[0] =   " select    GNUM_DEPT_CODE,GSTR_DEPT_NAME    from     GBLT_DEPARTMENT_MST    where     GNUM_HOSPITAL_CODE = '108'"+  
			               " AND GNUM_DEPT_TYPE = 1 AND   GNUM_ISVALID =1  AND " +    
			               " TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "+
			               " ORDER BY "+
			               " GSTR_DEPT_NAME";
	//	System.out.println("strComboQry[0] "+strComboQry[0] );
         
		strComboQry[1] = " SELECT      HGNUM_DEPTUNITCODE  ,   HGSTR_UNITNAME    FROM         HGBT_UNIT_MST  WHERE "+   
						 " HGNUM_DEPT_CODE  =   '103'  AND  GNUM_HOSPITAL_CODE  = '108'    AND    GNUM_ISVALID   =1   AND " +  
		                 " TRUNC(SYSDATE) BETWEEN GDT_EFFECTIVE_FRM AND TRUNC(NVL(GDT_EFFECTIVE_TO,SYSDATE)) "+
		                 " ORDER  BY   HGSTR_UNITNAME ";
	//	System.out.println("strComboQry[1] "+strComboQry[1] );
		
		strComboQry[2] = " SELECT  distinct HIPNUM_WARD_CODE, "+
						" Ipd_Mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE ) "+ 
		                "   FROM HIPT_DUWRBED_MST "+
                        "	WHERE HGNUM_DEPTUNITCODE ='10311' AND " + 
                        "	GNUM_HOSPITAL_CODE ='108' AND GNUM_ISVALID = 1 AND "+ 
                        "	TRUNC(SYSDATE) >= GDT_EFFECTIVE_FRM ";
	//	System.out.println("strComboQry[2] "+strComboQry[2] );   
		
		strComboQry[3] = " SELECT b.hipnum_room_no, b.hipstr_room_desc "+
		                 " FROM HIPT_ROOM_CONFIG_MST b "+
		                 " WHERE gnum_hospital_code = '108'" +
		                 " AND gnum_isvalid = 1 AND "+
		                 " EXISTS (SELECT    'x' "+
		                 " FROM HIPT_WARD_ROOM_BED_MST " +
		                 " WHERE hipnum_room_no=b.hipnum_room_no AND "+
		                 " hgnum_ward_code= '103' AND "+
		                 " hgnum_bed_status_code=13 AND "+
		                 " gnum_isvalid=1) ";   
			
		
		
		
	//	System.out.println("strComboQry[3] "+strComboQry[3] );   
			
	     strComboQry[4] =	"0^Select Value#1^Patient Confirmation#2^Nursing Services";
     
	
        	
        	return strComboQry;
         
       
	}

	@Override
	public String[] getDbButtons() {
		String[] strDbButton = {"2"};
		return strDbButton;
	}

	@Override
	public String getEventState() {
		String strEvent = "";
		return strEvent;
	}


	@Override
	public String getJsFiles() {
     	  
		return "../../ipd/js/nursingDesk.js";
	}

	@Override
	public String getMainQuery(String[] cmb) 
	{
				
		StringBuffer sb = null;
		 sb = new StringBuffer("");
		 		 
		
     sb.append(" SELECT HIPNUM_ADMNO||'@'||HRGNUM_PUK||'^'||HIPNUM_ADMNO||'^'||HRGNUM_PUK||'^'|| ");
     sb.append(" Ahis_Function.FUN_PAT_NAME(a.hrgnum_puk)||'^'|| ");
     sb.append(" Ahis_Function.FUN_PAT_AGE_GEN(a.hrgnum_puk)||'^'||");
     sb.append(" (SELECT HGNUM_BED_NAME ");
     sb.append(" FROM HIPT_WARD_ROOM_BED_MST ");
     sb.append("  WHERE HGNUM_BED_CODE=A.HIPNUM_BED_CODE ) ");
     sb.append("   as DATA ");
     sb.append(" FROM HIPT_PAT_MOV_DTL a WHERE  HIPDT_TRANSIN_DATETIME IS NULL AND  HIPNUM_TRANSIN_FLG IN(0,2)  AND "); 
     sb.append(" GNUM_DEPT_CODE='103'  AND   GNUM_DEPTUNIT_CODE  = '10311'  AND ");     
     sb.append(" HIPNUM_WARDCODE = '103' ");
     sb.append(" AND  HIPNUM_ROOMNO  = '20'    AND    GNUM_HOSPITAL_CODE  ='108 ' "); 
	//System.out.println("list query"+sb.toString());
	 
    return sb.toString();
}	@Override
	public String getMasterName() {
		// TODO Auto-generated method stub
		return "Nursing Services ";
	}

	@Override
	public String getMenuOption() {
		// TODO Auto-generated method stub
		return "tiles";
	}

	@Override
	public int getMinPanelButton() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String[] getRowStatus() {
		String[] strRowStatus = new String[0];
		return strRowStatus;
	}

	@Override
	public String[] getSearchField() {
		String[] strSearch = {"1","HRGNUM_PUK"};
		return strSearch;
	}

	@Override
	public String[] getUserDefinedButtons()
	{
		
		
		
		String[] strButtons = new String[5];
		
		
		strButtons[0] = "<a id ='acceptId' onClick='IpdMscheckColor(\"ACCEPT\",\"acceptId\");'>Accept</a>";
		strButtons[1] = "<a id ='modifyId' onClick='IpdMscheckColor(\"TRANSFER\",\"transferId\");'>Transfer</a>";
		strButtons[2] = "<a id ='lamaId' onClick='IpdMscheckColor(\"LEAVE\",\"leaveId\");'>Patient on Leave</a>";
		strButtons[3] = "<a id ='transferId' onClick='IpdMscheckColor(\"DISCHARGE\",\"dischargeId\");'>Patient Final Discharge</a>";
		strButtons[4] = "<a id ='investigationId' onClick='IpdMscheckColor(\"DEATHNOTE\",\"deathNoteId\");'>Death Notification</a>";

		
		
		return null;
	}

	@Override
	public void setHttpSession(HttpSession session) {
		
		this.session = session;
	}

	@Override
	public String getComboEventState() 
	{
		// TODO Auto-generated method stub
	   //return "abcd";
    		return "IpdMSRecordStatus";
		// return "changeRecordStatus2";
		
	}
	 @Override
	 public void setCombo(String[] cmb) 
	 {
	  cmb = null;  
	 }
	    public String[] getOrderBy()
	    {
	     String orderBy[] = {"1","HRGNUM_PUK"};
	     return orderBy;
	    }

		@Override
		public List<String> getViewHeader() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getViewQuery() {
			// TODO Auto-generated method stub
			return null;
		}
	 

}
