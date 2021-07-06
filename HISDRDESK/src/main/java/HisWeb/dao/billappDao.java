package HisWeb.dao;

import hisglobal.transactionmgnt.HisDAO;

import java.util.ArrayList;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

public class billappDao {
	
	  public static String insertBillAppService(String jsonString) {
	    	/* mode=1 Mobileno
	    	 * mode=2 crno
	    	 * mode=3 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call pkg_bill_dml.dml_app_bill(?,?,?,?,?,?,?,?)}";
	    	String proc_name2 = "{call pkg_bill_dml.dml_app_bill_service(?,?,?,?,?,?,?,?,?)}";
	        int procIndex1 = 0;
	        int procIndex2 = 0;
	        HisDAO dao = null;
	         int totBillQty=0;
	         Double totAmt=0.00;
	         Double disamt=0.00;
	         String reqno=null,billNo=null,crno=null;
	         JSONObject mainObj = new JSONObject();
	         String hosp_code=null;
	         HttpServletRequest request=null;
	         StringBuffer buffer=new StringBuffer();
	         JSONObject mainObj1 = new JSONObject(); 
	        
	        try {
	        	hosp_code="10911";//request.getSession().getAttribute("HOSPITAL_CODE").toString();
	        	//testing String json="{'INVESTIGATION_DETAILS':[{'REQ_NO':'331011170016867','crno':'331017170000231','TARIFF_DETAILS':['1240006^1^35.00^0.00 RS.','1080001^1^35.00^0.00 RS.']}]}";
	   		 JSONObject object = new JSONObject(jsonString);
	   		 JSONArray mainobj =(JSONArray) object.get("INVESTIGATION_DETAILS");
	   		 for(int i=0;i<mainobj.length();i++)
	   		 {
	   			getBillNo billno=new getBillNo();
	   			billNo=billno.select();
	   			buffer.append(billNo+",");
	   			JSONObject object1=mainobj.getJSONObject(i);
	   			 
	   			System.out.println("REQ_NO "+object1.get("REQ_NO"));
	   			System.out.println("crno"+object1.get("crno"));
	   			reqno=object1.get("REQ_NO").toString();
	   			crno=object1.get("crno").toString();
	   			 JSONArray ja1 =(JSONArray) object1.get("TARIFF_DETAILS");
	   			 for(int j=0; j<ja1.length();j++)
	   			 {
	   				//totBillQty=totBillQty+Integer.parseInt(ja1.get(j).toString().replace("^", "#").split("#")[1]);
	   				 totBillQty=Integer.parseInt(ja1.get(j).toString().replace("^", "#").split("#")[1]);
	   				totAmt=totAmt+(Double.parseDouble(ja1.get(j).toString().replace("^", "#").split("#")[2])*totBillQty);
	   				disamt=disamt+Double.parseDouble(ja1.get(j).toString().replace("^", "#").split("#")[3].replaceAll(" RS.", "").replaceAll("%", ""));
	   				System.out.println("disamt   "+disamt);
	   			}
	   			 System.out.println(totBillQty+"  "+totAmt+"   "+disamt);
	   			 
	   			dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
	            procIndex1 = dao.setProcedure(proc_name1);
	            dao.setProcInValue(procIndex1, "modval", "1",1);
	            dao.setProcInValue(procIndex1, "hosp_code", hosp_code,2);
	            dao.setProcInValue(procIndex1, "puk", crno,3); 
	            dao.setProcInValue(procIndex1, "reqno", reqno,4); 
	            dao.setProcInValue(procIndex1, "netamt", String.valueOf(totAmt),5); 
	            dao.setProcInValue(procIndex1, "billno", billNo,6); 
	            dao.setProcInValue(procIndex1, "disamt", String.valueOf(disamt).trim(),7); 
	            dao.setProcOutValue(procIndex1, "err", 1,8);
	            dao.executeProcedureByPosition(procIndex1);
	            err=dao.getString(procIndex1, "err");
	            
	            
	            for(int j=0; j<ja1.length();j++)
	   			 {
	   				totBillQty=totBillQty+Integer.parseInt(ja1.get(j).toString().replace("^", "#").split("#")[1]);
	   				totAmt=totAmt+Double.parseDouble(ja1.get(j).toString().replace("^", "#").split("#")[2]);
	   				disamt=disamt+Double.parseDouble(ja1.get(j).toString().replace("^", "#").split("#")[3].replace(" RS.", "").replace("%", ""));
	   				String trfID=ja1.get(j).toString().replace("^", "#").split("#")[0];
	   				System.out.println("disamt   "+disamt);
	   				dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
		            procIndex2 = dao.setProcedure(proc_name2);
		            dao.setProcInValue(procIndex2, "modval", "1",1);
		            dao.setProcInValue(procIndex2, "hosp_code", hosp_code,2);
		            dao.setProcInValue(procIndex2, "puk", 	crno,3); 
		            dao.setProcInValue(procIndex2, "reqno", reqno,4);
		            dao.setProcInValue(procIndex2, "trfid", trfID,5);
		            dao.setProcInValue(procIndex2, "netamt", String.valueOf(totBillQty*totAmt),6); 
		            dao.setProcInValue(procIndex2, "billno", billNo,7); 
		            dao.setProcInValue(procIndex2, "disamt", String.valueOf(disamt).trim(),8); 
		            dao.setProcOutValue(procIndex2, "err", 1,9);
		            dao.executeProcedureByPosition(procIndex2);
		            err=dao.getString(procIndex2, "err");
	   				
	   				
	   				
	   			}
	            /*synchronized (dao) {
					dao.fire();
				}*/
	   		}
	           
	        
	   		mainObj1.put("TransactionNo", buffer.deleteCharAt(buffer.length()-1).toString());

	   		 
	   			
	   			System.out.println("DATA INSERTED SUCCESSFULLY "+mainObj1.toString());
	            return mainObj1.toString();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            return "Something Went Wrong";
	        }
	        finally {
	        	if (dao != null) {
	        	 totBillQty=0;
	   	          totAmt=0.00;
	   	          disamt=0.00;
	                dao.free();
	                dao = null;
	            }
	        }
	    }
	  
	  
	  
	  
	  //343058
	  
	  
	  
	  public static String insertImageData(String ImageData ,String DeptValue) throws JSONException {
	    	/* mode=1 Mobileno
	    	 * mode=2 crno
	    	 * mode=3 WalletNo
	    	 */
	    	String err = "";
	    	String proc_name1 = "{call PKG_WEBSERVICES.insert_image_data(?,?,?,?  ,?,?,?,? , ?,?,?,? ,?,?,?)}";
	        int procIndex1 = 0;
	        HisDAO dao = null;
	        JSONObject mainObj = new JSONObject();
	        try {
	        
	        	String temp=DeptValue.replaceAll("\\^", "#");
	        	String val[]=temp.split("#");
	   			dao = new HisDAO("WebServices", "walletWebserviceDao.getwalletDataSummary()");
	            procIndex1 = dao.setProcedure(proc_name1);
	            dao.setProcInValue(procIndex1, "modeval", "1",1);
	            dao.setProcInValue(procIndex1, "crno", val[0],2);
	            dao.setProcInValue(procIndex1, "episodecode", val[1],3);
	            dao.setProcInValue(procIndex1, "visitno", val[3],4);
	            dao.setProcInValue(procIndex1, "deptcode", val[2],5);
	            dao.setProcInValue(procIndex1, "deptunitcode", val[4],6);
	            dao.setProcInValue(procIndex1, "hsop_code", val[5],7);
	            dao.setProcInValue(procIndex1, "pat_name", val[9],8);
	            dao.setProcInValue(procIndex1, "dept_name", val[7],9);
	            dao.setProcInValue(procIndex1, "dept_unit_name", val[6],10);
	            dao.setProcInValue(procIndex1, "pat_address", val[8],11);
	            
	            
	            
	            /*dao.setProcInValue(procIndex1, "imagedata",ImageData,12);
	            dao.setProcOutValue(procIndex1, "err", 1,13);
	            //dao.setProcOutValue(procIndex1, "resultset", 2,5);
	            dao.executeProcedureByPosition(procIndex1);
	            err=dao.getString(procIndex1, "err");
	            
	            System.out.println("DATA INSERTED SUCCESSFULLY ");
	            return  mainObj.put("status", "1").toString(); */
	            
	            //343058
	            dao.setProcInValue(procIndex1, "episode_date", val[10],12);
	            
	            dao.setProcInValue(procIndex1, "page_count", val[11],13);
	            
	            dao.setProcInValue(procIndex1, "imagedata",ImageData,14);
	            dao.setProcOutValue(procIndex1, "err", 1,15);
	            //dao.setProcOutValue(procIndex1, "resultset", 2,5);
	            dao.executeProcedureByPosition(procIndex1);
	            err=dao.getString(procIndex1, "err");
	            
	            System.out.println("DATA INSERTED SUCCESSFULLY ");
	            return  mainObj.put("status", "1").toString();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            return mainObj.put("status", "0").toString();
	        }
	        finally {
	        	if (dao != null) {
	                dao.free();
	                dao = null;
	            }
	        }
	    }

}
