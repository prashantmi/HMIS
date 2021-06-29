package new_investigation;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.exceptions.TransactionException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;

public class MachineInterfaceDatabaseObject extends DataAccessObject
{
	public MachineInterfaceDatabaseObject(TransactionContext _tx)
	{
		super(_tx);
	}


	public String insertData(HttpServletRequest request, HttpServletResponse response)
	{
		String m_recordID = null;
		String Eqp_ID = request.getParameter("eqp");
		String	Hos_ID= request.getParameter("hos");
		String	Test_code= request.getParameter("tcode");
		String Test_val= request.getParameter("tval");
		String sampleNo= request.getParameter("sam");
		String User_ID= request.getParameter("uid");
		//String Hmis_test_code = request.getParameter("hmtcode");
		
		String json = "";
		try
		{

			Map populateMAP = new HashMap();
			String get_recordid_qry="select PKG_MACHINE_INTERFACE.gen_resultID('"+ Eqp_ID +"','"+ Hos_ID +"')from dual";		
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), get_recordid_qry, populateMAP);
			while(rs.next())
			{
				m_recordID = rs.getString(1);
				json = m_recordID;
				System.out.println(m_recordID);
			}

			String Insert_qry_recordid ="update HMIT_MACHINE_MST set HMINUM_MACHINE_RECORDID='"+ m_recordID +"' where HMINUM_MACHINE_ID='"+ Eqp_ID +"' and GNUM_HOSPITAL_CODE='"+ Hos_ID +"' " ;
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), Insert_qry_recordid, populateMAP);
			String Insert_qry = "INSERT INTO hmit_result_dtl(HMINUM_MACHINE_RECORDID,HMINUM_MACHINE_ID,GNUM_HOSPITAL_CODE,HMISTR_MACHINE_TEST_CODE,HMISTR_RESULT,HMISTR_MACHINE_SAMPLENO,HMIDT_RESULTENTRY_DATE,HMINUM_RESULTENTRY_BY,HMINUM_STATUS)values('"+ m_recordID +"','"+ Eqp_ID +"','"+ Hos_ID +"','"+ Test_code +"','"+ Test_val +"','"+ sampleNo +"',sysdate,'"+ User_ID +"','1') ";
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), Insert_qry, populateMAP);
			rs.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		try {
//			writeResponse(response, json);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
return json;
	}
	
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("text");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setHeader("Content-Disposition", "inline");
			resp.getWriter().write(output);
		} catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();

		}
	}
	
	public String SelectData(HttpServletRequest request,  HttpServletResponse response)
	{
		String grp_test_code = null;
		String m_mac_test_code = null;
		String m_pat_name = null;
		String m_pat_age = null;
		String m_pat_sex = null;
		String Eqp_ID = request.getParameter("eqp");
		String	Hos_ID= request.getParameter("hos");
		//String	Test_code= request.getParameter("tcode");
		//String Test_val= request.getParameter("tval");
		String sampleNo= request.getParameter("sam");
		//String User_ID= request.getParameter("uid");
		String Out_result=null;
		boolean flag=true;
		try
		{

			Map populateMAP = new HashMap();
			// "INSERT INTO hmit_result_dtl(HMINUM_MACHINE_RECORDID,HMINUM_MACHINE_ID,GNUM_HOSPITAL_CODE,HMISTR_MACHINE_TEST_CODE,HMISTR_RESULT,HMISTR_MACHINE_SAMPLENO,HMIDT_RESULTENTRY_DATE,HMINUM_RESULTENTRY_BY,HMINUM_STATUS)values('"+ m_recordID +"','"+ Eqp_ID +"','"+ Hos_ID +"','"+ Test_code +"','"+ Test_val +"','"+ sampleNo +"',sysdate,'"+ User_ID +"','1') ";
			
			
			/*String select_qry = "SELECT hmtm.hmistr_machine_test_code AS machinetestcode, hmistr_pat_name,hmistr_age, hmistr_sex FROM hmit_sample_dtl  sam, hmit_machine_testpara_mst  hmtm WHERE hmtm.gnum_hospital_code = sam.gnum_hospital_code 	AND hmtm.hminum_hmis_test_code = sam.hminum_hmis_test_code AND hmtm.hminum_machine_id = sam.hminum_machine_id AND gnum_isvalid = 1 	AND trunc (sam.hmidt_collection_date) >= trunc (sysdate) AND sam.gnum_hospital_code = '"+ Hos_ID +"' AND sam.hmistr_machine_sampleno = '"+sampleNo+"' AND sam.hminum_test_status = '1' and sam.hminum_machine_id='"+ Eqp_ID +"'  ";*/
	       
		    String select_qry = "";
			
			
			if(Eqp_ID.equals("100001") && Hos_ID.equals("33101")) // specific for DX-800 Pathology Lab
			{
			select_qry = "SELECT (select gstr_test_name from hivt_test_mst where gnum_isvalid=1 and gnum_test_code=hmtm.hminum_hmis_test_code) testname,hmistr_pat_name,hmistr_age, hmistr_sex FROM hmit_sample_dtl  sam, hmit_machine_testpara_mst  hmtm WHERE hmtm.gnum_hospital_code = sam.gnum_hospital_code 	AND hmtm.hminum_hmis_test_code = sam.hminum_hmis_test_code AND hmtm.hminum_machine_id = sam.hminum_machine_id AND gnum_isvalid = 1 	AND trunc (sam.hmidt_collection_date) >= trunc (sysdate) AND sam.gnum_hospital_code = '"+ Hos_ID +"' AND sam.hmistr_machine_sampleno = '"+sampleNo+"' AND sam.hminum_test_status = '1' and sam.hminum_machine_id='"+ Eqp_ID +"' group by hmistr_pat_name,hmistr_age, hmistr_sex,testname "; 
			}
			else // for old query .. Run for other labs machine
			{
			select_qry ="SELECT hmtm.hmistr_machine_test_code AS machinetestcode, hmistr_pat_name,hmistr_age, hmistr_sex FROM hmit_sample_dtl  sam, hmit_machine_testpara_mst  hmtm WHERE hmtm.gnum_hospital_code = sam.gnum_hospital_code 	AND hmtm.hminum_hmis_test_code = sam.hminum_hmis_test_code AND hmtm.hminum_machine_id = sam.hminum_machine_id AND gnum_isvalid = 1 	AND trunc (sam.hmidt_collection_date) >= trunc (sysdate) AND sam.gnum_hospital_code = '"+ Hos_ID +"' AND sam.hmistr_machine_sampleno = '"+sampleNo+"' AND sam.hminum_test_status = '1' and sam.hminum_machine_id='"+ Eqp_ID +"'  ";
			}
			
			
			System.out.println(select_qry);
			//HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), select_qry, populateMAP);
			ResultSet rss = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), select_qry, populateMAP);
			while(rss.next())
			{
				m_mac_test_code = rss.getString(1);
				//grp_test_code=rss.getString(5);
				if(Out_result!=null)
				{
					
					
							Out_result = Out_result + m_mac_test_code;
							
					
					
						Out_result = Out_result +";";
						System.out.println(m_mac_test_code);
						m_pat_name = rss.getString(2);
						m_pat_age = rss.getString(3);
						m_pat_sex = rss.getString(4);
					}
				
				else
				{
					
					
						Out_result = m_mac_test_code+";";
						
					
					
				 
				  m_pat_name = rss.getString(2);
					m_pat_age = rss.getString(3);
					m_pat_sex = rss.getString(4);
					
				}
				}
			Out_result = Out_result + "#";
			Out_result = Out_result + m_pat_name;
			Out_result = Out_result + "#";
			Out_result = Out_result + m_pat_age;
			Out_result = Out_result + "#";
			Out_result = Out_result + m_pat_sex;
			Out_result = Out_result + "#";
			Out_result = Out_result + sampleNo;
			Out_result = Out_result + "#";		
					
			
			rss.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Out_result; 
	}

	
	
	public String SelectDataTYPE3(HttpServletRequest request,  HttpServletResponse response)
	{
		String grp_test_code = null;
		String m_mac_test_code = null;
		String m_pat_name = null;
		String m_pat_age = null;
		String m_pat_sex = null;
		String Eqp_ID = request.getParameter("eqp");
		String	Hos_ID= request.getParameter("hos");
		//String	Test_code= request.getParameter("tcode");
		//String Test_val= request.getParameter("tval");
		String sampleNo= request.getParameter("sam");
		//String User_ID= request.getParameter("uid");
		String Out_result=null;
		boolean flag=true;
		try
		{

			Map populateMAP = new HashMap();
			// "INSERT INTO hmit_result_dtl(HMINUM_MACHINE_RECORDID,HMINUM_MACHINE_ID,GNUM_HOSPITAL_CODE,HMISTR_MACHINE_TEST_CODE,HMISTR_RESULT,HMISTR_MACHINE_SAMPLENO,HMIDT_RESULTENTRY_DATE,HMINUM_RESULTENTRY_BY,HMINUM_STATUS)values('"+ m_recordID +"','"+ Eqp_ID +"','"+ Hos_ID +"','"+ Test_code +"','"+ Test_val +"','"+ sampleNo +"',sysdate,'"+ User_ID +"','1') ";
			
			
			/*String select_qry = "SELECT hmtm.hmistr_machine_test_code AS machinetestcode, hmistr_pat_name,hmistr_age, hmistr_sex FROM hmit_sample_dtl  sam, hmit_machine_testpara_mst  hmtm WHERE hmtm.gnum_hospital_code = sam.gnum_hospital_code 	AND hmtm.hminum_hmis_test_code = sam.hminum_hmis_test_code AND hmtm.hminum_machine_id = sam.hminum_machine_id AND gnum_isvalid = 1 	AND trunc (sam.hmidt_collection_date) >= trunc (sysdate) AND sam.gnum_hospital_code = '"+ Hos_ID +"' AND sam.hmistr_machine_sampleno = '"+sampleNo+"' AND sam.hminum_test_status = '1' and sam.hminum_machine_id='"+ Eqp_ID +"'  ";*/
	       
		    String select_qry = "";
			
			
			if(Eqp_ID.equals("100001") && Hos_ID.equals("33101")) // specific for DX-800 Pathology Lab
			{
			select_qry = "SELECT (select gstr_test_name from hivt_test_mst where gnum_isvalid=1 and gnum_test_code=hmtm.hminum_hmis_test_code) testname,hmistr_pat_name,hmistr_age, hmistr_sex FROM hmit_sample_dtl  sam, hmit_machine_testpara_mst  hmtm WHERE hmtm.gnum_hospital_code = sam.gnum_hospital_code 	AND hmtm.hminum_hmis_test_code = sam.hminum_hmis_test_code AND hmtm.hminum_machine_id = sam.hminum_machine_id AND gnum_isvalid = 1 	AND trunc (sam.hmidt_collection_date) >= trunc (sysdate) AND sam.gnum_hospital_code = '"+ Hos_ID +"' AND sam.hmistr_machine_sampleno = '"+sampleNo+"' AND sam.hminum_test_status = '1' and sam.hminum_machine_id='"+ Eqp_ID +"' group by hmistr_pat_name,hmistr_age, hmistr_sex,testname "; 
			}
			else // for old query .. Run for other labs machine
			{
			select_qry ="SELECT hmtm.hmistr_machine_test_code AS machinetestcode, hmistr_pat_name,hmistr_age, hmistr_sex FROM hmit_sample_dtl  sam, hmit_machine_testpara_mst  hmtm WHERE hmtm.gnum_hospital_code = sam.gnum_hospital_code 	AND hmtm.hminum_hmis_test_code = sam.hminum_hmis_test_code AND hmtm.hminum_machine_id = sam.hminum_machine_id AND gnum_isvalid = 1 	AND trunc (sam.hmidt_collection_date) >= trunc (sysdate-30) AND sam.gnum_hospital_code = '"+ Hos_ID +"' AND sam.hmistr_machine_sampleno = '"+sampleNo+"' AND sam.hminum_test_status = '1' and sam.hminum_machine_id='"+ Eqp_ID +"'  ";
			}
			
			
			System.out.println(select_qry);
			//HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), select_qry, populateMAP);
			ResultSet rss = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), select_qry, populateMAP);
			while(rss.next())
			{
				m_mac_test_code = rss.getString(1);
				//grp_test_code=rss.getString(5);
				if(Out_result!=null)
				{
					
					
							Out_result = Out_result + m_mac_test_code;
							
					
					
						Out_result = Out_result +";";
						System.out.println(m_mac_test_code);
						m_pat_name = rss.getString(2);
						m_pat_age = rss.getString(3);
						m_pat_sex = rss.getString(4);
					}
				
				else
				{
					
					
						Out_result = m_mac_test_code+";";
						
					
					
				 
				  m_pat_name = rss.getString(2);
					m_pat_age = rss.getString(3);
					m_pat_sex = rss.getString(4);
					
				}
				}
			Out_result = Out_result + "#";
			Out_result = Out_result + m_pat_name;
			Out_result = Out_result + "#";
			Out_result = Out_result + m_pat_age;
			Out_result = Out_result + "#";
			Out_result = Out_result + m_pat_sex;
			Out_result = Out_result + "#";
			Out_result = Out_result + sampleNo;
			Out_result = Out_result + "#";		
					
			
			rss.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Out_result; 
	}

	
	
	public String insertMultipleData(HttpServletRequest request, HttpServletResponse response)
	{
		 String m_recordID = null;
		 String Eqp_ID = request.getParameter("eqp");
		 String	Hos_ID= request.getParameter("hos");
		 String	Test_code= request.getParameter("tcode");
		 String Test_val= request.getParameter("tval");
		 String sampleNo= request.getParameter("sam");
		 String User_ID= request.getParameter("uid");
		// String Hmis_test_code = request.getParameter("hmtcode");
		
		//final String[] testCodes;
		//final String[] testVals;
		//final String[] hmisTestCodes;
		 
		 String[] testCodes = Test_code.split("\\^");
 		 String[] testVals = Test_val.split("\\^");
 		// String[] hmisTestCodes = Hmis_test_code.split("\\^");
 		 Map populateMAP = new HashMap();
 		 String Insert_qry = null;
		 
		 String json = "Record IDs of inserted data (separated by $) -> ";
		
		 if((testCodes.length == testVals.length) ){
				
				for (int i=0; i < testCodes.length; i++) {
					
					if(testCodes[i] != null && testCodes[i] != ""){
						
						
						if(testVals[i] == null || testVals[i].equals(""))
							testVals[i] = "--";
						else if((testCodes[i].equals("BA#") || testCodes[i].equals("EO#") || testCodes[i].equals("LY#") || testCodes[i].equals("MO#") || testCodes[i].equals("NE#") || testCodes[i].equals("NRBC#"))  &&  Eqp_ID.equals("100001")){
							
							double d=Math.round(Double.parseDouble(testVals[i]));
							testVals[i] = Double.toString(d);
						}
						
						Insert_qry = "INSERT INTO hmit_machine_data_dtl(hminum_machine_id,gnum_hospital_code,hmistr_machine_test_code,hmistr_machine_test_val,hmistr_machine_sampleno,hmidt_entry_date,hminum_result_validated_by,hminum_status)values('"+ Eqp_ID +"','"+ Hos_ID +"','"+ testCodes[i] +"','"+ testVals[i] +"','"+ sampleNo +"',sysdate,'"+ User_ID +"','1') ";
						 try {
							HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), Insert_qry, populateMAP);
						} catch (TransactionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					 
		        }
			}else{
				json = "In the above url, tcode count doesn't match against either tval count or hmtcode count";
			}
		 
		/* try
		 {
			 Map populateMAP = new HashMap();
			 String Insert_qry = "INSERT INTO hmit_machine_data_dtl(hminum_machine_id,gnum_hospital_code,hmistr_machine_test_code,hmistr_machine_test_val,hmistr_machine_sampleno,hmidt_entry_date,hminum_result_validated_by,hminum_status,hmistr_hmis_test_code)values('"+ Eqp_ID +"','"+ Hos_ID +"','"+ Test_code +"','"+ Test_val +"','"+ sampleNo +"',sysdate,'"+ User_ID +"','1','"+Hmis_test_code+"') ";
			 HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), Insert_qry, populateMAP);
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }*/
		 
		
		 
		 
		/*Thread t=	new Thread(new Runnable() {
 		      public void run() {
 		    	 try
 				{
 		    		 String[] testCodes = Test_code.split("\\^");
 		    		 String[] testVals = Test_val.split("\\^");
 		    		 String[] hmisTestCodes = Hmis_test_code.split("\\^");
 		    		 String json = "";
 		    		 
 					if((testCodes.length == testVals.length) && (testCodes.length == hmisTestCodes.length)){
 						
 						for (int i=0; i < testCodes.length; i++) {
 							
 							if(testCodes[i] != null && testCodes[i] != "")
 								json+=executeInsertionMultipleData(Eqp_ID,Hos_ID,testCodes[i],testVals[i],sampleNo,User_ID,hmisTestCodes[i]);
 								json+="$";
 				        }
 					}else{
 						json = "In the above url, tcode count doesn't match against either tval count or hmtcode count";
 					}
 					
 					  
 					
 				}

 				catch(Exception e)
 				{
 					e.printStackTrace();
 				}
 		      }
 			});
 		t.start();	*/
		
		 /*try
			{
	    		 String[] testCodes = Test_code.split("\\^");
	    		 String[] testVals = Test_val.split("\\^");
	    		 String[] hmisTestCodes = Hmis_test_code.split("\\^");
	    		// String json = "";
	    		 
				if((testCodes.length == testVals.length) && (testCodes.length == hmisTestCodes.length)){
					
					for (int i=0; i < testCodes.length; i++) {
						
						if(testCodes[i] != null && testCodes[i] != "")
							json+=executeInsertionMultipleData(Eqp_ID,Hos_ID,testCodes[i],testVals[i],sampleNo,User_ID,hmisTestCodes[i]);
							json+="$";
			        }
				}
				else{
					json = "In the above url, tcode count doesn't match against either tval count or hmtcode count";
				}
				
				  
				
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
 		System.out.println("Final json "+json);
		return json;*/
 		return "Done";
	}
	
	
	
}