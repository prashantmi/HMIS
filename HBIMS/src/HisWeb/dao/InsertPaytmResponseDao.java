package HisWeb.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InsertPaytmResponseDao {
	
	 public static String insertBillAppService(String jsonString ,String crno , String mobile_no , String accno) throws JSONException {
	    	/* mode=1 Mobileno
	    	 * mode=2 crno
	    	 * mode=3 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call pkg_bill_dml.dml_online_app_response(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)}";
	    	int procIndex1 = 0;
	     
	        HisDAO dao = null;
	        JSONObject mainObj = new JSONObject();
	         String hosp_code=null;
	         HttpServletRequest request=null;
	         StringBuffer buffer=new StringBuffer();
	         JSONObject mainObj1 = new JSONObject(); 
	        
	        try {
	        	hosp_code="33101";//request.getSession().getAttribute("HOSPITAL_CODE").toString();
	        	//testing String json="{'INVESTIGATION_DETAILS':[{'REQ_NO':'331011170016867','crno':'331017170000231','TARIFF_DETAILS':['1240006^1^35.00^0.00 RS.','1080001^1^35.00^0.00 RS.']}]}";
	        	JSONObject object = new JSONObject(jsonString);
	        	getBillNo no=new getBillNo();
	        	String billNO=no.select();
	   			dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
	            procIndex1 = dao.setProcedure(proc_name1);
	            dao.setProcInValue(procIndex1, "modval", "1",1);
	            dao.setProcInValue(procIndex1, "puk", crno,2);
	            dao.setProcInValue(procIndex1, "mobileno", mobile_no,3);
	            dao.setProcInValue(procIndex1, "billno", billNO,4);
	            dao.setProcInValue(procIndex1, "tstatus", object.getString("STATUS"),5);
	            dao.setProcInValue(procIndex1, "checksum", object.getString("CHECKSUMHASH"),6);
	            dao.setProcInValue(procIndex1, "bankname", object.getString("BANKNAME"),7);
	            dao.setProcInValue(procIndex1, "orderid", object.getString("ORDERID"),8);
	            dao.setProcInValue(procIndex1, "amt", object.getString("TXNAMOUNT"),9);
	            dao.setProcInValue(procIndex1, "date", object.getString("TXNDATE"),10);
	            
	            dao.setProcInValue(procIndex1, "mid",  object.getString("MID"),11);
	            dao.setProcInValue(procIndex1, "txnid",  object.getString("TXNID"),12);
	            dao.setProcInValue(procIndex1, "responsecode",  object.getString("RESPCODE"),13);
	            dao.setProcInValue(procIndex1, "paymentmode",  object.getString("PAYMENTMODE"),14);
	            dao.setProcInValue(procIndex1, "btransactionid",  object.getString("BANKTXNID"),15);
	            dao.setProcInValue(procIndex1, "currencycode",  object.getString("CURRENCY"),16);
	            
	            dao.setProcInValue(procIndex1, "gatewayname", object.getString("GATEWAYNAME"),17); 
	            dao.setProcInValue(procIndex1, "responsemsg", object.getString("RESPMSG"),18); 
	            dao.setProcInValue(procIndex1, "isvalid", "1",19); 
	            dao.setProcInValue(procIndex1, "hospcode", "33101",20); 
	            System.out.println(accno);
	            dao.setProcInValue(procIndex1, "accno1", accno,21); 
	            dao.setProcOutValue(procIndex1, "err", 1,22);
	            dao.executeProcedureByPosition(procIndex1);
	            
	            
	            err=dao.getString(procIndex1, "err");
	            
	            return mainObj1.put("SUCCESS", "1").toString();
	            
	            /*synchronized (dao) {
					dao.fire();
				}*/
	   		//return "";
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            return mainObj1.put("FAIL", "2").toString();
	        }
	        finally {
	        	if (dao != null) {
	                dao.free();
	                dao = null;
	            }
	        }
	    }

}
