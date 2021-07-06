package HisWeb.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

public class getBillNo {
	
	private int nInsertedIndex = 0;
	private String strErr = "";
	private String billNo = "";
	HisDAO dao = null;
	String hosp_code=null;
    HttpServletRequest request=null;
	public String select() throws Exception
	{
		strErr = "";
		hosp_code="10911";//request.getSession().getAttribute("HOSPITAL_CODE").toString();
		
		WebRowSet ws = null;
		try 
		{
			dao = new HisDAO("WebServices", "getBillNo.select()()");
			nInsertedIndex = dao.setProcedure("{call pkg_bill_view.Proc_Sblt_Primarykey_Dtl(?,?,?,?,?,?,?)}");
	
			dao.setProcInValue(nInsertedIndex,"keyname","BILL",1);
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
				billNo=ws.getString(1);
				System.out.println("Primary Key"+ws.getString(1));
			}
		} 
		catch(Exception e) 
		{
			this.strErr = e.getMessage();
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
		return billNo;
	}

}
