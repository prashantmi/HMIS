package mms.transactions.controller.utl;
import hisglobal.hisconfig.Config;
import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

public class DrugInventoryTransUTL extends TransInterface
{
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;
	String[] cmbVal = null;
	
	public void setHttpSession(HttpSession session) 
	{
		this.httpSession = session;
	}
	/*public void setCombo(String[] arg0) {
		// TODO Auto-generated method stub
		
	}*/

	//new
	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	
	}
	public String getMasterName()
	{
		String masterName = "Drug Inventory";
		return masterName;
	}
	

	public List<String> getViewHeader() 
	{
			
			List <String>viewHdr = new ArrayList<String>();
			
			
			return viewHdr;
	}
		
	public String getMainQuery(String cmb[]) 
	{
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());
		StringBuffer brMainQuery = new StringBuffer(500);
		
		ResourceBundle res = mms.qryHandler_mms.res;
		if(res == null) 
		{
			res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
			mms.qryHandler_mms.res = res;
		}

		
		if(res.getString("DRUG_INVENTORY").equals("1"))
		{
	        
	       String tempQry = mms.qryHandler_mms.getQuery(2, "select.main.0").replace("#", mmscofigutil.getStrExpAlertDays());
			
			brMainQuery.append(tempQry.replace("?",httpSession.getAttribute("HOSPITAL_CODE").toString()));
			if (cmb != null)
			{
				if (!cmb[0].equals("0"))
					brMainQuery.append(" AND "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.1").replace("?", cmb[0]));
				else
					brMainQuery.append(" AND "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.1").replace("?", "0"));
			}
			else
			brMainQuery.append(" and "+mms.qryHandler_mms.getQuery(2, "select.main.cond.1").replace("?", "0"));
					
			if (cmb != null)
			{
					
				if (!cmb[1].equals("0")) 
					//brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);
				
					
				brMainQuery.append(" AND "
							+ mms.qryHandler_mms.getQuery(2,
									"select.main.cond.2").replace("?", cmb[1]));
	
				if (cmb[2].equals("16")) 
				{				
					brMainQuery = new StringBuffer("");
					brMainQuery.append(" SELECT    c.hstnum_store_id|| '@'|| c.hstnum_item_id|| '@'|| c.hstnum_itembrand_id|| '@'|| c.hststr_batch_no");
					brMainQuery.append(" || '@'|| c.gnum_hospital_code|| '@'|| hstnum_stock_status_code|| '^'|| c.item_brand_name|| '^'|| c.hststr_batch_no");
					brMainQuery.append(" || '^'|| c.item_inhand_dtl|| '^'|| c.expiry_date || '^'|| sl");
					brMainQuery.append("|| '^'|| 1 ");
					brMainQuery.append("|| '^'|| mms_mst.get_reorder_flag (c.gnum_hospital_code,c.hstnum_itembrand_id,c.hstnum_item_id,c.hstnum_store_id,c.hstnum_inhand_qty,c.hststr_batch_no,1)");
					brMainQuery.append("|| '^'|| c.hstnum_stock_status_code as DATA FROM (SELECT hstnum_store_id, hstnum_item_id, hstnum_itembrand_id,hststr_batch_no, gnum_hospital_code, hstnum_group_id,");
					brMainQuery.append("mms_mst.get_item_dtl (1,gnum_hospital_code,hstnum_itembrand_id)item_brand_name,sstnum_item_cat_no, hstnum_stock_status_code,hstnum_inhand_qty|| ' '|| mms_mst.getunitname (gnum_hospital_code," +
							"hstnum_inhand_qty_unitid) item_inhand_dtl,hstnum_saleprice sl,");
					brMainQuery.append("gnum_isvalid,round(mms_mst.get_reorder(gnum_hospital_code,hstnum_itembrand_id,hstnum_item_id,hstnum_store_id,1))");
					brMainQuery.append("");
					brMainQuery.append("TO_CHAR (hstdt_expiry_date, 'DD-Mon-yyyy') AS expiry_date,hstnum_inhand_qty,hstdt_expiry_date FROM hstt_drug_currstock_dtl");
					brMainQuery.append(" WHERE gnum_isvalid = 1 AND hstnum_inhand_qty > 0 AND hstdt_expiry_date < TRUNC(SYSDATE) ) c");
					brMainQuery.append(" WHERE c.gnum_hospital_code = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+"");
					
					if( cmb[0] != null && !cmb[0].equals("0"))
					{
						
						brMainQuery.append(" AND c.hstnum_store_id = ").append(cmb[0] );	
						
					}
					
				}
				else
				{
					if(!cmb[2].equals("0"))
					{
						if(cmb[2].equals("10"))
						{
						    brMainQuery.append(" and "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?",cmb[2]));
						    brMainQuery.append(" and  c.hstdt_expiry_date > TRUNC(SYSDATE)");
						}
						else
						{
							brMainQuery.append(" and "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?",cmb[2]));	
						}	
					}
					/*
					else
					{
				  	  brMainQuery.append(	" and "	+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?","10,11,12,15"));
					}
					*/  
				}	
				
				if (!cmb[0].equals("0"))
					brMainQuery.append(" AND "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.1").replace("?", cmb[0]));
				else    
					brMainQuery.append(" AND "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.1").replace("?", "0"));
			} 
//			else
//			{
//				
//				brMainQuery.append(	" and "	+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?","9"));
//				
//			}
		}
		else
		{      				

			String tempQry = mms.qryHandler_mms.getQuery(2, "select.main.0").replace("#", mmscofigutil.getStrExpAlertDays());
			
			brMainQuery.append(tempQry.replace("?",httpSession.getAttribute("HOSPITAL_CODE").toString()));
					
			if (cmb != null)
			{
					
				if (!cmb[1].equals("0")) 
					//brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);
				
					
				brMainQuery.append(" AND "
							+ mms.qryHandler_mms.getQuery(2,
									"select.main.cond.2").replace("?", cmb[1]));
	
				if (cmb[2].equals("16")) 
				{				
					brMainQuery = new StringBuffer("");
					brMainQuery.append(" SELECT    c.hstnum_store_id|| '@'|| c.hstnum_item_id|| '@'|| c.hstnum_itembrand_id|| '@'|| c.hststr_batch_no");
					brMainQuery.append(" || '@'|| c.gnum_hospital_code|| '@'|| hstnum_stock_status_code|| '^'|| c.item_brand_name|| '^'|| c.hststr_batch_no");
					brMainQuery.append(" || '^'|| c.item_inhand_dtl || '^'|| c.expiry_date || '^'|| sl");
					brMainQuery.append("|| '^'|| 1 ");
					brMainQuery.append("|| '^'|| mms_mst.get_reorder_flag (c.gnum_hospital_code,c.hstnum_itembrand_id,c.hstnum_item_id,c.hstnum_store_id,c.hstnum_inhand_qty,c.hststr_batch_no,1)");
					brMainQuery.append("|| '^'|| c.hstnum_stock_status_code as DATA FROM (SELECT hstnum_store_id, hstnum_item_id, hstnum_itembrand_id,hststr_batch_no, gnum_hospital_code, hstnum_group_id,");
					brMainQuery.append("mms_mst.get_item_dtl (1,gnum_hospital_code,hstnum_itembrand_id)item_brand_name,sstnum_item_cat_no, hstnum_stock_status_code,hstnum_inhand_qty|| ' '|| mms_mst.getunitname (gnum_hospital_code," +
							"hstnum_inhand_qty_unitid) item_inhand_dtl, hstnum_saleprice sl,");
					brMainQuery.append("gnum_isvalid,round(mms_mst.get_reorder(gnum_hospital_code,hstnum_itembrand_id,hstnum_item_id,hstnum_store_id,1))");
					//brMainQuery.append("|| ' '|| mms_mst.getunitname (gnum_hospital_code,hstnum_inhand_qty_unitid)item_reorder_level,");
					brMainQuery.append("TO_CHAR (hstdt_expiry_date, 'DD-Mon-yyyy') AS expiry_date,hstnum_inhand_qty,hstdt_expiry_date FROM hstt_drug_currstock_dtl");
					brMainQuery.append(" WHERE gnum_isvalid = 1  ) c");
					brMainQuery.append(" WHERE c.gnum_hospital_code = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+"");
					
					if( cmb[0] != null && !cmb[0].equals("0"))
					{
						
						brMainQuery.append(" AND c.hstnum_store_id = ").append(cmb[0] );
						brMainQuery.append(" and  c.hstdt_expiry_date < TRUNC(SYSDATE)");
						
					}
					
				}
				else
				{
					if(!cmb[2].equals("0"))
					{
						if(cmb[2].equals("10"))
						{
						    brMainQuery.append(" and "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?",cmb[2]));
						    brMainQuery.append(" and  c.hstdt_expiry_date > TRUNC(SYSDATE)");
						}
						else
						{
							brMainQuery.append(" and "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?",cmb[2]));	
						}	
					}
					/*
					else
					{
				  	  brMainQuery.append(	" and "	+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?","10,11,12,15"));
				  	  brMainQuery.append(" and  c.expiry_date < TRUNC(SYSDATE)");
					}  
					*/
				}	
				
				if (!cmb[0].equals("0"))
					brMainQuery.append(" AND "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.1").replace("?", cmb[0]));
				else
					brMainQuery.append(" AND "+ mms.qryHandler_mms.getQuery(2,"select.main.cond.1").replace("?", "0"));
			} 
//			else
//			{
//				
//				brMainQuery.append(	" and "	+ mms.qryHandler_mms.getQuery(2,"select.main.cond.0").replace("?","9"));
//				
//			}			
			
		}
		
		res = null;
		mmscofigutil= null;
		
		System.out.println("Drug Inventory Query==>"+brMainQuery.toString());
		return brMainQuery.toString();
		

	}
	
	public String[] getSearchField() 
	{
		String search_field[] = {"1", "C.ITEM_BRAND_NAME","2","C.HSTSTR_BATCH_NO"};
		return search_field;
	}
	
	/**
	 * A^B 
	 * A = 0 Means Combo From Query,
	 * A = 1 Means User Defined Combo
	 * B = 0 After ^ Means Select Value,
	 * B = 1 Means All,
	 * B = 2 Means Default Selected
	 * */
	public String[] getComboHeader() 
	{
		String[] strComboHeader = {"0^0","Store Name", "0^1","Group Name","0^2","Stock Status"};
	    return strComboHeader;
	}
		
	public String[] getColumnHeader() 
	{
		String[] strColumnHeader = { "Drug Name", "Batch No" , "Qty InHand" ,"Exp Date","Cost to Patient"};
		return strColumnHeader;
	}      
		
	public String[] getComboQuery() 
	{
	String[] comboQuery = new String[3];
	String strSeatId=httpSession.getAttribute("SEATID").toString();
	
		comboQuery[0] = "select HSTNUM_STORE_ID,HSTSTR_STORE_NAME from HSTT_STORE_MST A where hstnum_store_id in (select distinct ba.hstnum_store_id from hstt_store_category_mst ba where ba.SSTNUM_ITEM_CAT_NO = 10 and ba.hstnum_store_id = a.hstnum_store_id and ba.gnum_isvalid = 1 and ba.gnum_hospital_code = a.gnum_hospital_code) AND  GNUM_ISVALID = 1  "// and gnum_ward_code = 0"	//dept and ward code 0 chk added by shalini to remove ward store from combo(CHK REMOVED BY AJAY BCOZ INVENTORY REQD.)
				+ "  AND  GNUM_HOSPITAL_CODE ="+
				httpSession.getAttribute("HOSPITAL_CODE").toString()+
				" AND EXISTS"+
						   "("+
						    "SELECT 'X'"+
						    "FROM GBLT_ROLE_SEAT_TABLE_DTL P"+
						    " WHERE UPPER(gstr_table_name) = UPPER('HSTT_STORE_MST')"+
						    " AND UPPER(gstr_column_name) = UPPER('HSTNUM_STORE_ID') and gnum_module_id = 63 "+
						    " AND P.gnum_hospital_code = A.gnum_hospital_code AND gbl_isvalid = 1 "+
						  " AND gnum_column_value = A.HSTNUM_STORE_ID"+
						 " AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+httpSession.getAttribute("SEATID").toString()+				 
						 ",A.GNUM_HOSPITAL_CODE)"+")"+
				" ORDER BY INITCAP(HSTSTR_STORE_NAME)";
				
		comboQuery[1] = "SELECT HSTNUM_GROUP_ID,HSTSTR_GROUP_NAME " +
				"FROM HSTT_GROUP_MST WHERE GNUM_HOSPITAL_CODE = " +Config.SUPER_USER_HOSPITAL_CODE +
				" AND GNUM_ISVALID = 1 AND SSTNUM_ITEM_CAT_NO = 10"+" ORDER BY HSTSTR_GROUP_NAME";

		comboQuery[2] = "SELECT HSTNUM_STOCK_STATUS_CODE, HSTSTR_STOCK_STATUS_DESC FROM SSTT_STOCK_STATUS_MST" +
		" WHERE GNUM_ISVALID = 1 AND HSTNUM_STOCK_STATUS_CODE = 10 AND GNUM_HOSPITAL_CODE = "+Config.SUPER_USER_HOSPITAL_CODE +" AND SSTNUM_ITEM_CAT_NO = 10 "
		+" ORDER BY HSTNUM_STOCK_STATUS_CODE";
		
		return comboQuery;
		
	}
		
	public String getViewQuery()
	{
		return "";
	}
	
	public String getButtons()
	{
	    
		return "";
	}
	
	public String[] getDeleteQuery() 
	{
		
		         String    deleteQuery[]={""};
		return deleteQuery;
	}
	
	public List<String[]> getDeleteData(String chk)
	{
		
		return null;
	}
	
	public String  getJsFiles()
	{
		String files="../../mms/js/mms_trans.js";
					
		return files;
		
	}

	public String[] getRowStatus() 
	{
		/*
		 * "1"--->> Value Which we Want to Maaped        1         2    3   4   5    6       7
		 * "7"--->> Column Name Primary Key + 1 e.g. 10@1001@108^Amole^XAS^1000^3^2-Jan-2011^1
		 * "blue"--->>
		 * "Exp Within"--->>Argument You Want to Show in Footer of Template
		 * 
		 */
		ResourceBundle res = mms.qryHandler_mms.res;
		if(res == null) 
		{
			res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
			mms.qryHandler_mms.res = res;
		}

      	String[] status = {"1", "7", "#7D786C", "Expired"};
		//String[] status = {"1", "7", "LightSlateGrey", "('*'Reserved/Branded Item ) Expired within " + mmscofigutil.getStrExpAlertDays() + " Day (s)","0","8",res.getString("RE_ORDER_COLOR"),"Quantity < Re-Oreder Level"};
		
      	res = null;
      	
		return status;
	}


	public String getEventState() {
		String str = "chkUserDefinedFunc";
		return str;
	}

	public String getButtonConfiguration() {
		// TODO Auto-generated method stub
		return "left";
	}

	public String[] getUserDefinedButtons() 
	{
		ResourceBundle res = mms.qryHandler_mms.res;
		if(res == null) 
		{
			res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
			mms.qryHandler_mms.res = res;
		}

		String retVal = res.getString("DRUG_INVENTORY");
		res = null;
		System.out.println("DRUG_INVENTORY===>>>>>>"+retVal);
		if(retVal.equals("1"))
		{
	       
			//Add@validateAddInventory(document.forms[0],'ADD')@0--Buton Name@CallingJSFunction@OnloadButtonEnable-0/ButtonDisable-1
			String[] strButtons = {//"Add@validateAddInventory(document.forms[0],'ADD')@0",   
					             	//"Modify@validateAddInventory(document.forms[0],'MODIFY')@1",
					             	"Add@getnewaddmode()@0@3b5998@glyphicon-plus"
					//,"Modify@getnewmodifymode()@1"
					             	};
	       return strButtons;
		}
		else
		{
			String[] strButtons = {"Add@validateAddInventory(document.forms[0],'ADD')@0@3b5998@glyphicon-plus"};
		       return strButtons;
		}
		
	}

	public String[] getDbButtons() {
		// TODO Auto-generated method stub
		//String[] str={"1"};
		return null;
	}

	public int getMinPanelButton() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * Frames option will not work
	 *  (non-Javadoc)
	 * @see hisglobal.transactionutil.TransInterface#getMenuOption()
	 */
	
	public String getMenuOption() {
		String menuType = "Tiles";
		return menuType;
	}

	@Override
	public String getComboEventState()
	{
		return "";
	}
	
	//new features
	public String[] getOrderBy() 
	{
		String orderBy[] = {"1", "C.ITEM_BRAND_NAME", "2","C.HSTSTR_BATCH_NO", "5", "C.hstdt_expiry_date"};
		//String orderBy[] = {};
		return orderBy;
	} 
	
	@Override
	public String[] getButtonIcons() {
		String[] str={//"AddOnDesk.png","UpdateOnDesk.png",
						"AddOnDesk.png","UpdateOnDesk.png"};
		// TODO Auto-generated method stub
		return str;
	}
		
}
