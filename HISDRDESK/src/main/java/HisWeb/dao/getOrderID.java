package HisWeb.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

public class getOrderID {

	
	


	public String getPaytmOrderID() throws Exception
	{
		int nInsertedIndex = 0;
		 String strErr = "";
		String strOrderID = "";
		HisDAO dao = null;
		String hosp_code="33101";
	    HttpServletRequest request=null;
		
		WebRowSet ws = null;
		try 
		{
			dao = new HisDAO("WebServices", "getBillNo.select()()");
			nInsertedIndex = dao.setProcedure("{call pkg_bill_view.Proc_Sblt_Primarykey_Dtl(?,?,?,?,?,?,?)}");
	
			dao.setProcInValue(nInsertedIndex,"keyname","ORDER_ID",1);
			dao.setProcInValue(nInsertedIndex,"blockkey","1",2);
			dao.setProcInValue(nInsertedIndex,"commitflag","0",3);
			dao.setProcInValue(nInsertedIndex,"hosp_code",hosp_code,4);
			dao.setProcInValue(nInsertedIndex,"appendhospcodefalg","1",5);
			dao.setProcOutValue(nInsertedIndex,"err",1,7);
			dao.setProcOutValue(nInsertedIndex,"resultset",2,6);
			
			dao.executeProcedureByPosition(nInsertedIndex);
			ws = dao.getWebRowSet(nInsertedIndex,"resultset");

			if(ws.next())
			{
				strOrderID=ws.getString(1);
				System.out.println("Primary Key"+ws.getString(1));
			}
		} 
		catch(Exception e) 
		{
			strErr = e.getMessage();
			e.printStackTrace();
			//throw new Exception(strFileName + ".insert() --> " + this.strErr);
		}
		finally
		{
			if (dao != null) {
                dao.free();
                dao = null;
            }
			 if(ws != null){
	        		ws.close();
	        		ws = null;
	        	}       
		}
		return strOrderID;
	}
	
}
