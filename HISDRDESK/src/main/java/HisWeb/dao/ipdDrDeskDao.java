package HisWeb.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class ipdDrDeskDao {
	
	public static String SaveDrugAdviceData( String JsonData)
	{
		System.out.println("method called for ipd SaveDrugAdviceData--->");
    	String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.proc_insert_drdesk_data(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,? ,?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("DrugCodeCat "+object.get("DrugCodeCat"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			JSONArray DrugObjArray =(JSONArray) object.get("DrugCodeCat");
   			if(DrugObjArray!=null)
   				{
   				
		   			for (int i=0 ;i<DrugObjArray.length();i++)
		   			{
		   				
		   				dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
		   	            procIndex1 = dao.setProcedure(proc_name1);
		   	            dao.setProcInValue(procIndex1, "modval", "1",1);
		   	            dao.setProcInValue(procIndex1, "puk", Crno,2);
		   	            dao.setProcInValue(procIndex1, "episodecode", EpisodeCode,3); 
		   	            dao.setProcInValue(procIndex1, "slno", String.valueOf(i+1),4); 
		   	            dao.setProcInValue(procIndex1, "admno", "0",5); 
		   	            dao.setProcInValue(procIndex1, "visitno", "1",6); 
		   	            String tmp = (String) DrugObjArray.get(i);
		   	            String tmpArray[]=tmp.split("&&");
		   	            dao.setProcInValue(procIndex1, "itemid",tmpArray[1].split("#")[0] ,7); 
		   	            dao.setProcInValue(procIndex1, "doseid", tmpArray[3],8);
						dao.setProcInValue(procIndex1, "seatid", seatId,9);
						dao.setProcInValue(procIndex1, "doseName", tmpArray[2],10);
						dao.setProcInValue(procIndex1, "routeid", "0",11);
						dao.setProcInValue(procIndex1, "frqid",  tmpArray[5],12);
						
						dao.setProcInValue(procIndex1, "days",  tmpArray[7].split("#")[0],13);
						dao.setProcInValue(procIndex1, "startdate",  tmpArray[6],14);
						dao.setProcInValue(procIndex1, "endDate",  tmpArray[6],15);
						dao.setProcInValue(procIndex1, "remarks",  "Remarks",16);
						
						dao.setProcInValue(procIndex1, "itemName", tmpArray[0],17);
						dao.setProcInValue(procIndex1, "hospcode", hosp_code,18);
						dao.setProcInValue(procIndex1, "empid", "0",19);
						dao.setProcInValue(procIndex1, "doseqty", tmpArray[7].split("#")[1],20);
						dao.setProcInValue(procIndex1, "brandId", tmpArray[1].split("#")[4],21);
						dao.setProcInValue(procIndex1, "itemtypeid", tmpArray[1].split("#")[1],22);
						
		   	            dao.setProcOutValue(procIndex1, "err", 1,23);
		   	            dao.executeProcedureByPosition(procIndex1);
		   	    	}
   				}
            return "DATA INSERTED SUCCESSFULLY";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveDrugAdviceData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }

	public static String SaveInvData(String JsonData) {
		System.out.println("method called for ipd SaveInvData2--->");
		String err = "";
    	String proc_name1 = "{call pkg_inv_dml.proc_insert_hivt_requisition_dtl(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    	String proc_name3 = "{call pkg_inv_dml.proc_insert_hivt_reqisition_header(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    	String proc_name2 = "{? = call pkg_inv_unique_no_generation.generate_save_requisitionno(?, ?)}";
    	String proc_name4="{call Bill_Interface.Dml_Online_Billreq(?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        int procIndex3 =0;
        int procIndex4 =0;
        int funcIndex = 0;
		String strRequisitionNo = "";
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
         WebRowSet wb =null;
         String strRequisitionDNo="";
         StringBuffer testsb=null;
         StringBuffer testQtysb=null;
         String strBillingstrRequisitionNo="";
        try {
        	
   		    JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("DrugCodeCat "+object.get("DrugCodeCat"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("pat_Name"+object.get("pat_Name"));
			System.out.println("patGender"+object.get("patGender"));
			System.out.println("patAge"+object.get("patAge"));
			System.out.println("patCat"+object.get("patCat"));
   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			String patName=(String) object.get("pat_Name");
   			String patGender=(String) object.get("patGender");
   			String patAge=(String) object.get("patAge");
   			String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
   			JSONArray InvesObjArray =(JSONArray) object.get("InvTestCode");
   			StringBuffer sb = new StringBuffer();
   			JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			String strInvestgationNote=(String) object.get("strInvestgationNote");
   			
   			if(ReasonOfVisit!=null)
   			   				{
   			   				
   					   			for (int i=0 ;i<ReasonOfVisit.length();i++)
   					   			{
   					   				sb.append((ReasonOfVisit.get(i).toString().split("\\^")[1]).replaceAll(";", "") +";");
   					   			}
   			   				}
   			/*int index =Arrays.asList(InvesObjArray).indexOf("0^0^0^0^0");
   			System.out.println("Index Value "+index);
   			*/
   			HashMap <String ,List> map=null;
   			HashMap <String ,List> bldMap=null;
   			List<String> l1=null;
   			List<String> bldList=null;
   			map=new HashMap<String, List>();
		   		//if(InvesObjArray.length() > 0 ){
		   			if(InvesObjArray!=null && InvesObjArray.length() >0)
		   			{
		   			for(int j=0 ;j<InvesObjArray.length();j++)
		   			{
		   				dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.getRequistion No()");
		   				String tmpInv = (String) InvesObjArray.get(j);
		   				String labCode=tmpInv.split("\\^")[1];
		   				System.out.println("labCode1:::"+labCode);
		   				l1=new ArrayList<String>();
		   				for(int k=0;k<InvesObjArray.length();k++)
		   				{
		   					String tmpInv1 = (String) InvesObjArray.get(k);
		   					//System.out.println("labCode2:::"+labCode);
		   					
		   					if(labCode.equalsIgnoreCase(tmpInv1.split("\\^")[1]))
		   					{
		   						l1.add(tmpInv1.split("\\^")[0]+"^"+tmpInv1.split("\\^")[2]+"^"+tmpInv1.split("\\^")[3]);   						
		   					}
		   					
		   				}
		   				map.put(labCode, l1);
		   				
		   			}
		   			System.out.println("MAP::::::::::::\n"+map);
		   			//System.out.println("List:::::::::::\n"+l1);
		   		
		   		if(map!=null)
		   		{
		   			bldMap=new HashMap<>();
	   				bldList=new ArrayList<String>();
		   			for(Map.Entry m:map.entrySet())
		   	   		{
		   				funcIndex = dao.setFunction(proc_name2);
		   	   			dao.setFuncInValue(funcIndex, 2, hosp_code);
		   				dao.setFuncInValue(funcIndex, 3, m.getKey().toString());
		   				dao.setFuncOutValue(funcIndex, 1);
		   				dao.executeFunction(funcIndex);
		   				strRequisitionNo = dao.getFuncString(funcIndex);
		   				
		   				bldMap.put(strRequisitionNo, (ArrayList)m.getValue());
		   				System.out.println("RequisitionNo::::::::"+strRequisitionNo);
		   				System.out.println("Array List :::: "+m.getValue());
		   				
		   					procIndex3 = dao.setProcedure(proc_name3);
			   	            dao.setProcInValue(procIndex3, "hmode", "4",1);
			   	            dao.setProcInValue(procIndex3, "req_no", strRequisitionNo,2);
			   	         
			   	            dao.setProcInValue(procIndex3, "puk_no", Crno,3); 
			   	            dao.setProcInValue(procIndex3, "hcode", hosp_code,4); 
			   	            dao.setProcInValue(procIndex3, "visitno", PatCompleteGeneralDtl.split("#")[4],5); 
			   	            dao.setProcInValue(procIndex3, "labcode", m.getKey().toString(),6); 
			   	         
		
			   	            dao.setProcInValue(procIndex3, "isconfidential", "0",7);
			   	            dao.setProcInValue(procIndex3, "reqheader_status", "1",8);
							dao.setProcInValue(procIndex3, "seatid", seatId,9);
							dao.setProcInValue(procIndex3, "episode_code", EpisodeCode,10);
							dao.setProcInValue(procIndex3, "patname", patName,11);
							dao.setProcInValue(procIndex3, "requisition_type",  "1",12);
							
							dao.setProcInValue(procIndex3, "dob", "",13);
							dao.setProcInValue(procIndex3, "visit_date",  PatCompleteGeneralDtl.split("#")[7],14);
							dao.setProcInValue(procIndex3, "isactual_dob",  "",15);
							dao.setProcInValue(procIndex3, "ordered_by_doc",  "",16);
							
							dao.setProcInValue(procIndex3, "gender_code", patGender,17);
							dao.setProcInValue(procIndex3, "req_raised_through", "7",18);
							dao.setProcInValue(procIndex3, "age", patAge,19);
							dao.setProcInValue(procIndex3, "admno", "",20);
							dao.setProcInValue(procIndex3, "address", "",21);
							dao.setProcInValue(procIndex3, "wardcode", "",22);
							
							dao.setProcInValue(procIndex3, "mobile_no", "",23);
							dao.setProcInValue(procIndex3, "email_id", "",24);
							dao.setProcInValue(procIndex3, "room_code", "",25);
							dao.setProcInValue(procIndex3, "bed_code", "",26);
							dao.setProcInValue(procIndex3, "mlc_no", "",27);
							dao.setProcInValue(procIndex3, "bed_name", "",28);
							
							
							dao.setProcInValue(procIndex3, "room_name", "",29);
							dao.setProcInValue(procIndex3, "ward_name", "",30);
							dao.setProcInValue(procIndex3, "dept_name", PatCompleteGeneralDtl.split("#")[8],31);
							dao.setProcInValue(procIndex3, "request_del_seatid", "",32);
							dao.setProcInValue(procIndex3, "unit_name", PatCompleteGeneralDtl.split("#")[9],33);
							dao.setProcInValue(procIndex3, "dept_code", PatCompleteGeneralDtl.split("#")[6],34);
							
							dao.setProcInValue(procIndex3, "deptunitcode", PatCompleteGeneralDtl.split("#")[5],35);
							dao.setProcInValue(procIndex3, "isautomated_request", "",36);
							dao.setProcInValue(procIndex3, "ordered_by_doc_name", "",37);
							dao.setProcInValue(procIndex3, "reffered_hospital_code", "",38);
							dao.setProcInValue(procIndex3, "reffered_dept_unit_name","",39);
							dao.setProcInValue(procIndex3, "reffered_lab_code", "",40);
							dao.setProcInValue(procIndex3, "ext_hosporlab_name", "",41);
							
							dao.setProcInValue(procIndex3, "ext_crnumber","",42);
							dao.setProcInValue(procIndex3, "bill_no", "",43);
							dao.setProcInValue(procIndex3, "delete_reason", "",44);
							dao.setProcInValue(procIndex3, "isvalid", "1",45);
							dao.setProcInValue(procIndex3, "req_date", "",46);
							dao.setProcInValue(procIndex3, "hgnum_patient_cat_code", "",47);
							dao.setProcInValue(procIndex3, "visit_reason", sb.toString()+""+strInvestgationNote,48);
							dao.setProcOutValue(procIndex3, "err", 1,49);
							dao.execute(procIndex3,1);
		   				
		   				
		   				ArrayList<String> TestCodeList=(ArrayList)m.getValue();
		   				if(TestCodeList!=null)
		   				{
		   					for(int i=0 ;i <TestCodeList.size();i++)
		   					{
		   						
		   		   	            procIndex1 = dao.setProcedure(proc_name1);
		   		   	            dao.setProcInValue(procIndex1, "hmode", "5",1);
		   		   	            dao.setProcInValue(procIndex1, "hcode", hosp_code,2);
		   		   	            if(i<9)
		   		   	            {
		   		   	            strRequisitionDNo=strRequisitionNo+"0"+(i+1);	
		   		   	            }else{
		   		   	          strRequisitionDNo=strRequisitionNo+(i+1);	
		   		   	            }
		   		   	            dao.setProcInValue(procIndex1, "reqdno", strRequisitionDNo,3); 
		   		   	            dao.setProcInValue(procIndex1, "isappointment", "0",4); 
		   		   	            dao.setProcInValue(procIndex1, "labcode", m.getKey().toString(),5); 
		   		   	            dao.setProcInValue(procIndex1, "testcode", TestCodeList.get(i).split("\\^")[0],6); 
		   		   	         
		
		   		   	            dao.setProcInValue(procIndex1, "isconfidential", "0",7);
		   		   	            dao.setProcInValue(procIndex1, "prioritycode", "1",8);
		   						dao.setProcInValue(procIndex1, "resultseatid", "",9);
		   						String staus="";
		   						//System.out.println("test Code Value"+TestCodeList.get(i).split("\\^")[1]);
		   						if(TestCodeList.get(i).split("\\^")[1].equalsIgnoreCase("-1") || TestCodeList.get(i).split("\\^")[1].equalsIgnoreCase("0"))
		   						{
		   							 staus="5";
		   						}else
		   						{
		   							staus="2";
		   						}
		   						
		   						dao.setProcInValue(procIndex1, "req_dtl_status", staus,10);
		   						dao.setProcInValue(procIndex1, "seatid", seatId,11);
		   						dao.setProcInValue(procIndex1, "app_ref_no",  "",12);
		   						
		   						dao.setProcInValue(procIndex1, "temp_sample_no", "",13);
		   						dao.setProcInValue(procIndex1, "groupcode",  "",14);
		   						dao.setProcInValue(procIndex1, "grouptype",  "",15);
		   						dao.setProcInValue(procIndex1, "cancellation_seat_id",  "",16);
		   						
		   						dao.setProcInValue(procIndex1, "billno", "",17);
		   						dao.setProcInValue(procIndex1, "res_val_seat_id", "",18);
		   						dao.setProcInValue(procIndex1, "res_re_val_seat_id", "",19);
		   						dao.setProcInValue(procIndex1, "samplecode", TestCodeList.get(i).split("\\^")[1],20);
		   						dao.setProcInValue(procIndex1, "res_print_seat_id", "",21);
		   						dao.setProcInValue(procIndex1, "daily_lab_no", "",22);
		   						
		   						dao.setProcInValue(procIndex1, "pat_rejection_reason", "",23);
		   						dao.setProcInValue(procIndex1, "res_modify_seat_id", "",24);
		   						dao.setProcInValue(procIndex1, "pat_rejection_action", "",25);
		   						dao.setProcInValue(procIndex1, "reschedule_seat_id", "",26);
		   						dao.setProcInValue(procIndex1, "work_order_seq", "",27);
		   						dao.setProcInValue(procIndex1, "sam_rejection_action", "",28);
		   						
		   						
		   						dao.setProcInValue(procIndex1, "sam_rejection_reason", "",29);
		   						dao.setProcInValue(procIndex1, "test_delete_seat_id", "",30);
		   						dao.setProcInValue(procIndex1, "type_of_component", "",31);
		   						dao.setProcInValue(procIndex1, "req_no", strRequisitionNo,32);
		   						dao.setProcInValue(procIndex1, "is_accepted", "",33);
		   						dao.setProcInValue(procIndex1, "packing_list_no", "",34);
		   						
		   						dao.setProcInValue(procIndex1, "machinecode", "",35);
		   						dao.setProcInValue(procIndex1, "acceptance_seat_id", "",36);
		   						dao.setProcInValue(procIndex1, "collection_seat_id", "",37);
		   						dao.setProcInValue(procIndex1, "sample_col_area_code", "",38);
		   						dao.setProcInValue(procIndex1, "appointment_time","",39);
		   						dao.setProcInValue(procIndex1, "packing_list_seat_id", "",40);
		   						dao.setProcInValue(procIndex1, "is_sample_received", "",41);
		   						
		   						dao.setProcInValue(procIndex1, "sample_no","",42);
		   						dao.setProcInValue(procIndex1, "uomcode", "",43);
		   						dao.setProcInValue(procIndex1, "collected_vol", "",44);
		   						dao.setProcInValue(procIndex1, "container_code", "",45);
		   						dao.setProcInValue(procIndex1, "appointment_date", "",46);
		   						dao.setProcInValue(procIndex1, "priority_all", "",47);
		   						dao.setProcOutValue(procIndex1, "err", 1,48);
		   						dao.setProcInValue(procIndex1, "site", "",49);
		   		   	            //dao.executeProcedure(procIndex1);
		   						dao.execute(procIndex1,1);
		   						
		   					}
		   				}
		   			}
		   			
		   			
		   			
		   			/* Billing  */
		   			
		   			
		   			System.out.println("bldMap"+bldMap);
		   			if(bldMap!=null)
			   		{
		   				testsb=new StringBuffer();
		   				testQtysb=new StringBuffer();
		   				
			   			for(Map.Entry bldMap1:bldMap.entrySet())
			   	   		{
			   				String reqNo=bldMap1.getKey().toString();
			   				
			   				ArrayList<String> BLDgenerationLogic=(ArrayList)bldMap1.getValue();
			   				int temprpt=0;
			   				for(int tempbld=0;tempbld<BLDgenerationLogic.size();tempbld++)
			   				{
			   					testQtysb.append("1"+"^");
			   					String TestCode=BLDgenerationLogic.get(tempbld).split("\\^")[0];
								testsb.append(TestCode+"#1^");
			   					if(BLDgenerationLogic.get(0).split("\\^")[2].equalsIgnoreCase("BLD"))
			   					{
			   						if(temprpt==0)
			   						{
			   						strBillingstrRequisitionNo=strBillingstrRequisitionNo+reqNo+"|BLD";
			   						temprpt++;
			   						}else{
			   							strBillingstrRequisitionNo=strBillingstrRequisitionNo+"|BLD";
			   							//temprpt++;
			   						}
			   						
			   					}else{
			   						strBillingstrRequisitionNo=strBillingstrRequisitionNo+reqNo;
			   					}
			   				}
			   				strBillingstrRequisitionNo=strBillingstrRequisitionNo+"!";
			   	   		}
			   		}
		   			
		   			System.out.println("strBillingstrRequisitionNo"+strBillingstrRequisitionNo);
		   				
					/*	for(int k=0;k<InvesObjArray.length();k++)
						{
							testsb=new StringBuffer();
							testQtysb=new StringBuffer();
							String tmpInv1 = (String) InvesObjArray.get(k);
							
							testQtysb.append("1"+"^");
							String TestCode=tmpInv1.split("\\^")[0];
							testsb.append(TestCode+"^");
							
							
						}*/
						System.out.println("testsb"+testsb.toString());
						System.out.println("testQtysb"+testQtysb.toString());
						
						procIndex4 = dao.setProcedure(proc_name4);
						dao.setProcInValue(procIndex4, "modval", "1",1);
						dao.setProcInValue(procIndex4, "gnum_dept_code", PatCompleteGeneralDtl.split("#")[6],2);
						dao.setProcInValue(procIndex4, "sblnum_chargetype_id", PatCompleteGeneralDtl.split("#")[11],3); 
						dao.setProcInValue(procIndex4, "sblnum_service_id", "1",4); 
						dao.setProcInValue(procIndex4, "gstr_req_no", strBillingstrRequisitionNo,5); 
						dao.setProcInValue(procIndex4, "gnum_treatment_cat", PatCompleteGeneralDtl.split("#")[10],6); 
						System.out.println("gnum_treatment_cat"+PatCompleteGeneralDtl.split("#")[10]);
						System.out.println("testsb.toString().substring(0, testsb.toString().length() - 1)"+testsb.toString().substring(0, testsb.toString().length() - 1));
						dao.setProcInValue(procIndex4, "hrgnum_episode_code", EpisodeCode,7);
						dao.setProcInValue(procIndex4, "hrgnum_puk", Crno,8);
						dao.setProcInValue(procIndex4, "gstr_tariff", testsb.toString().substring(0, testsb.toString().length() - 1),9);
						dao.setProcInValue(procIndex4, "gstr_reqqty", testQtysb.toString().substring(0, testQtysb.toString().length() - 1),10);
						dao.setProcInValue(procIndex4, "hblstr_patient_name", "0",11);
						//dao.setProcInValue(procIndex4, "app_ref_no",  "",12);
						
						dao.setProcInValue(procIndex4, "hblstr_pat_address", "0",12);
						dao.setProcInValue(procIndex4, "hblstr_contact_no",  "0",13);
						dao.setProcInValue(procIndex4, "age",  "0",14);
						dao.setProcInValue(procIndex4, "ageunit",  "0",15);
						
						dao.setProcInValue(procIndex4, "gender", "0",16);
						dao.setProcInValue(procIndex4, "refdoctor", "0",17);
						dao.setProcInValue(procIndex4, "refdoccontactno", "0",18);
						dao.setProcInValue(procIndex4, "gnum_seatid", seatId,19);
						dao.setProcInValue(procIndex4, "hosp_code", hosp_code,20);
						dao.setProcInValue(procIndex4, "ward_code", "0",21);
						
						dao.setProcInValue(procIndex4, "admno", "0",22);
						dao.setProcInValue(procIndex4, "visitno", "1",23);
						dao.setProcOutValue(procIndex4, "err",1, 24);
						
						//dao.executeProcedure(procIndex1);
						dao.execute(procIndex4,1);
					
		   			
		   			
		   			
		   		}
		   		
		   		synchronized (dao) 
				{
					
						dao.fire();
						return "DATA INSERTED SUCCESSFULLY";
				}
		   	}
		   //}
         
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveInvData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		return "No Record Updated";
		
		
	}

	public static String SaveGenralData(String JsonData) {
		System.out.println("method called for ipd SaveGenralData3--->");
		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.proc_hrgt_episode_diagnosis_dml(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			JSONArray Diagnosis =(JSONArray) object.get("Diagnosis");
   			String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
   			if(Diagnosis!=null)
   				{
   				
		   			for (int i=0 ;i<Diagnosis.length();i++)
		   			{
		   				
		   				dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
		   	            procIndex1 = dao.setProcedure(proc_name1);
		   	            dao.setProcInValue(procIndex1, "p_mode", "1",1);
		   	            dao.setProcInValue(procIndex1, "p_puk", Crno,2);
		   	            dao.setProcInValue(procIndex1, "p_episodecode", EpisodeCode,3); 
		   	            dao.setProcInValue(procIndex1, "p_visit_no", PatCompleteGeneralDtl.split("#")[4],4); 
		   	            dao.setProcInValue(procIndex1, "p_admissionno", "0",5); 
		   	            String tmp[] = Diagnosis.getString(i).split("\\^");
		   	            dao.setProcInValue(procIndex1, "p_diagnostictypecode", tmp[0].split("#")[1],6); 
		   	          // String tmpArray[]=tmp.split("&&");*/
		   	            System.out.println("Diagnosis Code"+tmp[0].split("#")[0]);
		   	            dao.setProcInValue(procIndex1, "p_diagnosticcode",tmp[0].split("#")[0] ,7); 
		   	            dao.setProcInValue(procIndex1, "p_seat_id", seatId,8);
						dao.setProcInValue(procIndex1, "p_isvalid", "1",9);
						dao.setProcInValue(procIndex1, "p_episodedate", "",10);
						dao.setProcInValue(procIndex1, "p_remarks", "",11);
						//System.out.println(tmp[0] +"fffffffffffffffffffff"+tmp[0].split("#")[2]);
						dao.setProcInValue(procIndex1, "p_diagnosiscode_type",  tmp[0].split("#")[2],12);
						
						dao.setProcInValue(procIndex1, "p_hospcode",  hosp_code,13);
						dao.setProcInValue(procIndex1, "p_isrepeat",  "0",14);
						dao.setProcInValue(procIndex1, "p_diseasesiteid",  "",15);
						dao.setProcInValue(procIndex1, "p_diagnostic_name",  tmp[1].split("#")[0],16);
						
						dao.setProcInValue(procIndex1, "p_diagnostictypename",  tmp[1].split("#")[1],17);
						dao.setProcInValue(procIndex1, "p_disease_site", "",18);
						dao.setProcInValue(procIndex1, "p_source", "",19);
						dao.setProcInValue(procIndex1, "p_somedicdmapped", "",20);
						
		   	            dao.setProcOutValue(procIndex1, "err", 1,21);
		   	            dao.executeProcedureByPosition(procIndex1);
		   	    	}
   				}
            return "DATA INSERTED SUCCESSFULLY";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveGenralData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
        
		
	}

	public static String SaveVisitReasonData(String JsonData) {
		System.out.println("method called for ipd SaveVisitReasonData4--->");
		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.proc_hrgt_episode_dtl(?,?,?,?,?, ?,?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
   			StringBuffer sb=new StringBuffer();
   			if(ReasonOfVisit!=null)
   				{
   				
		   			for (int i=0 ;i<ReasonOfVisit.length();i++)
		   			{
		   				sb.append((ReasonOfVisit.get(i).toString().split("\\^")[1]).replaceAll(";", "") +";");
		   			}
   				}
		   			if(ReasonOfVisit.length()>0)
		   			{
		   				dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
		   	            procIndex1 = dao.setProcedure(proc_name1);
		   	            dao.setProcInValue(procIndex1, "p_mode", "1",1);
		   	            dao.setProcInValue(procIndex1, "p_puk", Crno,2);
		   	            dao.setProcInValue(procIndex1, "p_hospcode", hosp_code,3); 
		   	            dao.setProcInValue(procIndex1, "p_visitreason", sb.toString(),4); 
		   	            dao.setProcInValue(procIndex1, "p_snomedpt", "0",5); 
		   	            dao.setProcInValue(procIndex1, "p_snomedcid", "1",6); 
		   	            dao.setProcInValue(procIndex1, "p_episodecode",EpisodeCode ,7); 
		   	            dao.setProcInValue(procIndex1, "p_visitno", PatCompleteGeneralDtl.split("#")[4],8);
						dao.setProcInValue(procIndex1, "p_isvalid", "1",9);
						
		   	            dao.setProcOutValue(procIndex1, "err", 1,10);
		   	            dao.executeProcedureByPosition(procIndex1);
		   			}
		   			return "DATA INSERTED SUCCESSFULLY";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveVisitReasonData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
        
	}
	public static String FollowUpDTL(String JsonData) {
		System.out.println("method called for ipd FollowUpDTL5--->");
		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.proc_save_pat_follow_up_dtl(?,?,?,?,?, ?,?,?,?,? ,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
   			JSONArray FOLLOWUPDTL=(JSONArray) object.get("FOLLOW_UP");
   			StringBuffer sb=new StringBuffer();
   			
		   			if(FOLLOWUPDTL.length()>0)
		   			{
		   				JSONObject FollowupJson= (JSONObject) FOLLOWUPDTL.get(0);
		   				JSONArray arr=(JSONArray) FollowupJson.get("Planned_Visit");
		   				
		   				String  progressNote=(String) FollowupJson.get("progressNote").toString().replaceAll("[\\[\\]]", "");
		   				//String  progressNote=(String) FollowupJson.get("progressNoteNew");
		   				
		   				System.out.println("arr"+arr);
		   				JSONObject temp=(JSONObject) arr.get(0);
		   				
		   				//JSONObject temp1=(JSONObject) arr.get(0);
		   				System.out.println("plannedVisitDays"+temp.get("plannedVisitSos"));
		   				dao = new HisDAO("OPD DR DESK DAO", "FollowUpDTL.save()");
		   	            procIndex1 = dao.setProcedure(proc_name1);
		   	            dao.setProcInValue(procIndex1, "p_mode", "1",1);
		   	            dao.setProcInValue(procIndex1, "p_puk", Crno,2);
		   	            dao.setProcInValue(procIndex1, "p_hospcode", hosp_code,3); 
		   	            dao.setProcInValue(procIndex1, "p_visitreason", "",4); 
		   	            dao.setProcInValue(procIndex1, "p_episodecode",EpisodeCode ,5); 
		   	            dao.setProcInValue(procIndex1, "p_visitno", PatCompleteGeneralDtl.split("#")[4],6);
		   	            dao.setProcInValue(procIndex1, "p_is_open", "1",7);
						dao.setProcInValue(procIndex1, "p_is_confirm", "1",8);
						dao.setProcInValue(procIndex1, "p_visitnotes", progressNote,9);
						dao.setProcInValue(procIndex1, "p_visitdate", temp.getString("plannedVisitDate"),10);
						dao.setProcOutValue(procIndex1, "err", 1,11);
		   	            dao.executeProcedureByPosition(procIndex1);
		   			}
		   			return "DATA INSERTED SUCCESSFULLY";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.FollowUpDTL()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
        
	}

	public static String SaveEHRData(String JsonData) {
		System.out.println("method called for ipd SaveEHRData6--->");
		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.hrgt_ehrjson_dtl(?,?,?,?,?, ?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
   			StringBuffer sb=new StringBuffer();
   			
			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode", "1",1);
            dao.setProcInValue(procIndex1, "p_puk", Crno,2);
            dao.setProcInValue(procIndex1, "p_hospcode", hosp_code,3); 
            dao.setProcInValue(procIndex1, "p_seatId", seatId,4); 
            dao.setProcInValue(procIndex1, "p_episodecode",EpisodeCode ,5); 
            dao.setProcInValue(procIndex1, "p_visitno", PatCompleteGeneralDtl.split("#")[4],6);
            dao.setProcInValue(procIndex1, "p_json", JsonData,7);
			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
			
            dao.setProcOutValue(procIndex1, "err", 1,9);
            dao.executeProcedureByPosition(procIndex1);
		   		
		   			return "DATA INSERTED SUCCESSFULLY";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveEHRData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	}	
	
	public static String saveReferPatientDetails(String JsonData) {
        System.out.println("method calling ipd saveReferPatientDetails---->");
		String err = "";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
   		System.out.println("JsonData"+JsonData);
		System.out.println("Diagnosis "+object.get("Diagnosis"));
		System.out.println("CR_No"+object.get("CR_No"));
		System.out.println("episodeCode"+object.get("episodeCode"));


   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			String visitNo=(String) object.get("episodeVisitNo");
   			//String strRefrealRemaks=(String) object.get("strReffralReason");
   			StringBuffer sb=new StringBuffer();
   			JSONArray strRefferalDept =(JSONArray) object.get("strReferal");
   			int length=((JSONArray) object.get("strReferal")).length();
   			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
   			for(int i=0 ; i< length ; i++){
   				
   			 String proc_name1 = "{call pkg_ipdDesk_dml.patient_referal_confirmation_flag(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)}";
   			 procIndex1 = dao.setProcedure(proc_name1);
             dao.setProcInValue(procIndex1, "p_mode", "1",1);
             dao.setProcInValue(procIndex1, "p_puk", Crno,2);
             dao.setProcInValue(procIndex1, "p_hospcode", hosp_code,3);
             dao.setProcInValue(procIndex1, "p_seatId", seatId,4); 
             dao.setProcInValue(procIndex1, "p_episodecode",EpisodeCode ,5); 
             dao.setProcInValue(procIndex1, "from_dept", "",6);
             dao.setProcInValue(procIndex1, "from_deptunit", "",7);
             JSONObject strjsonObj =(JSONObject) strRefferalDept.get(i);
             dao.setProcInValue(procIndex1, "to_dept", ((String)(strjsonObj.get("strReffralDeptDone"))).split("#")[0],8);
             dao.setProcInValue(procIndex1, "to_deptunit", ((String)(strjsonObj.get("strReffralDeptDone"))).split("#")[1],9);
             dao.setProcInValue(procIndex1, "p_visitno", visitNo,10);
             dao.setProcInValue(procIndex1, "to_ip_address", "",11);
             dao.setProcInValue(procIndex1, "remarks", (String)strjsonObj.get("strReffralReason"),12);
             dao.setProcInValue(procIndex1, "isref_out", (String)strjsonObj.get("strreferralType"),13);
             dao.setProcInValue(procIndex1, "p_json", JsonData,14);
             dao.setProcOutValue(procIndex1, "err", 1,15);
             dao.execute(procIndex1, 1);
   				
   			}
   			
   			synchronized (dao) {
				dao.fire();
			}
   			 		
		   			return "1";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.saveReferPatientDetails()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "2";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	public static String SaveVitalData(String JsonData) {
        System.out.println("method called SaveVitalData--->  ");
		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.Dml_Vital_dtls(?,?,?,?,?, ?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?)}";
    	
        int procIndex1 = 0;
        HisDAO dao = null;
        try {
        	
   		 	JSONObject object = new JSONObject(JsonData);
   		 	String []strPatdtls=((String) object.get("strPatdtls")).split("\\^");
   			StringBuffer sb=new StringBuffer();
   			String strWeight=(String) object.get("strWeight")==null ? "" : (String) object.get("strWeight") ;
   			String strHeight=(String) object.get("strHeight")==null ? "" : (String) object.get("strHeight") ;
   			String strBmid=(String) object.get("strBmid")==null ? "" : (String) object.get("strBmid") ;
   			String strTempreature=(String) object.get("strTempreature")==null ? "" : (String) object.get("strTempreature") ;
   			
   			String strrespRate=(String) object.get("strrespRate")==null ? "" : (String) object.get("strrespRate") ;
   			String strhaemoglobin=(String) object.get("strhaemoglobin")==null ? "" : (String) object.get("strhaemoglobin") ;
   			String strdiastolic=(String) object.get("strdiastolic")==null ? "" : (String) object.get("strdiastolic") ;
   			String strsystolic=(String) object.get("strsystolic")==null ? "" : (String) object.get("strsystolic") ;
   			
   			String strfasting=(String) object.get("strfasting")==null ? "" : (String) object.get("strfasting") ;
   			String strRateId=(String) object.get("strRateId")==null ? "" : (String) object.get("strRateId") ;
   			String strhba1c=(String) object.get("strhba1c")==null ? "" : (String) object.get("strhba1c") ;
   			String strsymptoms=(String) object.get("strsymptoms")==null ? "" : (String) object.get("strsymptoms") ;
   			
   			
   			
   			
			dao = new HisDAO("OPD DR DESK DAO", "vitalSave.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode", "1",1);
            dao.setProcInValue(procIndex1, "p_puk", strPatdtls[1],2);
            dao.setProcInValue(procIndex1, "p_hospcode", strPatdtls[4],3); 
            dao.setProcInValue(procIndex1, "p_seatId", strPatdtls[7],4); 
            dao.setProcInValue(procIndex1, "p_episodecode",strPatdtls[2] ,5); 
            dao.setProcInValue(procIndex1, "p_visitno", strPatdtls[3],6);
           
            dao.setProcInValue(procIndex1, "strWeight", strWeight,7);
            dao.setProcInValue(procIndex1, "strHeight", strHeight,8);
            dao.setProcInValue(procIndex1, "strBmid", strBmid,9);
            dao.setProcInValue(procIndex1, "strTempreature", strTempreature,10);
            dao.setProcInValue(procIndex1, "strrespRate", strrespRate,11);
            dao.setProcInValue(procIndex1, "strhaemoglobin", strhaemoglobin,12);
            dao.setProcInValue(procIndex1, "strdiastolic", strdiastolic,13);
            
            
            dao.setProcInValue(procIndex1, "strsystolic", strsystolic,14);
            dao.setProcInValue(procIndex1, "strfasting", strfasting,15);
            dao.setProcInValue(procIndex1, "strRateId", strRateId,16);
            dao.setProcInValue(procIndex1, "strhba1c", strhba1c,17);
            dao.setProcInValue(procIndex1, "strsymptoms", strsymptoms,18);
            
            dao.setProcInValue(procIndex1, "p_json", JsonData,19);
			dao.setProcInValue(procIndex1, "p_isvalid", "1",20);
			
			
			
            dao.setProcOutValue(procIndex1, "err", 1,21);
            dao.executeProcedureByPosition(procIndex1);
		   	
            return "1";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveVitalData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "0";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	}

	public static String savePrecriptionProfileData(String JsonData){
		
     System.out.println("method called for ipd savePrecriptionProfileData for table check--->");

		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.hopl_emr_dtl(?,?,?,?,?, ?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
   		Integer strProfileId=(Integer) object.get("strProfileId");
   		Integer strStatus=(Integer) object.get("strStatus");
   			
			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode", "3",1);
            dao.setProcInValue(procIndex1, "p_puk", "",2);
            dao.setProcInValue(procIndex1, "p_hospcode", String.valueOf(strProfileId),3); 
            dao.setProcInValue(procIndex1, "p_seatId", String.valueOf(strStatus),4); 
            dao.setProcInValue(procIndex1, "p_episodecode","" ,5); 
            dao.setProcInValue(procIndex1, "p_visitno", "",6);
            dao.setProcInValue(procIndex1, "p_json", JsonData,7);
			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
			
            dao.setProcOutValue(procIndex1, "err", 1,9);
            dao.executeProcedureByPosition(procIndex1);
		   		
		   			return "1";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SavePrescriptionBookMarkData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "0";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	
	
	public static String SaveEMRVitalData(String JsonData) {
      
		System.out.println("method called for SaveEMRVitalData---->");
		String err = "";
    	String proc_name1 = "{call pkg_emr_dtl.sync_pat_vitals_from_oplite(?,?,?,?,?, ?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,   ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?)}";
    	
        int procIndex1 = 0;
        HisDAO dao = null;
        try {
        	
   		 	JSONObject object = new JSONObject(JsonData);
   		 	String []strPatdtls=((String) object.get("strPatdtls")).split("\\^");
   			StringBuffer sb=new StringBuffer();
   			String strWeight=(String) object.get("strWeight")==null ? "" : (String) object.get("strWeight") ;
   			String strHeight=(String) object.get("strHeight")==null ? "" : (String) object.get("strHeight") ;
   			String strBmid=(String) object.get("strBmid")==null ? "" : (String) object.get("strBmid") ;
   			String strTempreature=(String) object.get("strTempreature")==null ? "" : (String) object.get("strTempreature") ;
   			
   			String strrespRate=(String) object.get("strrespRate")==null ? "" : (String) object.get("strrespRate") ;
   			String strhaemoglobin=(String) object.get("strhaemoglobin")==null ? "" : (String) object.get("strhaemoglobin") ;
   			String strdiastolic=(String) object.get("strdiastolic")==null ? "" : (String) object.get("strdiastolic") ;
   			String strsystolic=(String) object.get("strsystolic")==null ? "" : (String) object.get("strsystolic") ;
   			
   			String strfasting=(String) object.get("strfasting")==null ? "" : (String) object.get("strfasting") ;
   			String strRateId=(String) object.get("strRateId")==null ? "" : (String) object.get("strRateId") ;
   			String strhba1c=(String) object.get("strhba1c")==null ? "" : (String) object.get("strhba1c") ;
   			String strsymptoms=(String) object.get("strsymptoms")==null ? "" : (String) object.get("strsymptoms") ;
   			
   			
   			String strpulseRate=(String) object.get("strpulseRate")==null ? "" : (String) object.get("strpulseRate") ;
   			String strbloodGroup=(String) object.get("strbloodGroup")==null ? "" : (String) object.get("strbloodGroup") ;
   			String strmuac=(String) object.get("strmuac")==null ? "" : (String) object.get("strmuac") ;
   			String strcurcumference=(String) object.get("strcurcumference")==null ? "" : (String) object.get("strcurcumference") ;
   			
   			String strbmiErrmsg=(String) object.get("strbmiErrmsg")==null ? "" : (String) object.get("strbmiErrmsg") ;
   			String strtemperatureErrmsg=(String) object.get("strtemperatureErrmsg")==null ? "" : (String) object.get("strtemperatureErrmsg") ;
   			String strrespRateErrmsg=(String) object.get("strrespRateErrmsg")==null ? "" : (String) object.get("strrespRateErrmsg") ;
   			String strhaemoglobinErrmsg=(String) object.get("strhaemoglobinErrmsg")==null ? "" : (String) object.get("strhaemoglobinErrmsg") ;
   			
   			String strbpErrmsg=(String) object.get("strbpErrmsg")==null ? "" : (String) object.get("strbpErrmsg") ;
   			String strfastingErrmsg=(String) object.get("strfastingErrmsg")==null ? "" : (String) object.get("strfastingErrmsg") ;
   			String strppRateErrmsg=(String) object.get("strppRateErrmsg")==null ? "" : (String) object.get("strppRateErrmsg") ;
   			String strhba1cErrmsg=(String) object.get("strhba1cErrmsg")==null ? "" : (String) object.get("strhba1cErrmsg") ;
   			
   			/* <!-- ----------------------added for cancer screening--------------- --> */
   			String strcancerScreening = object.get("strcancerScreening").toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "");
   			
   			
   			
   			
   			
   			
   			
			dao = new HisDAO("OPD DR DESK DAO", "emrsave.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "pmode", "1",1);
            dao.setProcInValue(procIndex1, "puk", strPatdtls[1],2);
            dao.setProcInValue(procIndex1, "episode_code", strPatdtls[2],3); 
            dao.setProcInValue(procIndex1, "visit_no", strPatdtls[3],4); 
            dao.setProcInValue(procIndex1, "seq_no","" ,5); 
            dao.setProcInValue(procIndex1, "hospital_code", strPatdtls[4],6);
           
            dao.setProcInValue(procIndex1, "weight_val", strWeight,7);
            dao.setProcInValue(procIndex1, "height_val", strHeight,8);
            dao.setProcInValue(procIndex1, "bmi_val", strBmid,9);
            dao.setProcInValue(procIndex1, "bmi_class", strTempreature,10);
            dao.setProcInValue(procIndex1, "hoplstr_bmi_classname", strtemperatureErrmsg,11);
            dao.setProcInValue(procIndex1, "temp_val", strTempreature,12);
            dao.setProcInValue(procIndex1, "istemphigh", "0",13);
            
            
            dao.setProcInValue(procIndex1, "rr_val", strrespRate,14);
            dao.setProcInValue(procIndex1, "isrrabnormal", "0",15);
            dao.setProcInValue(procIndex1, "hoplstr_rr_classname", strrespRateErrmsg,16);
            dao.setProcInValue(procIndex1, "hb_val", strhaemoglobin,17);
            dao.setProcInValue(procIndex1, "ishbabnormal", "0",18);
            
            dao.setProcInValue(procIndex1, "bpsy_val", strsystolic,19);
            dao.setProcInValue(procIndex1, "bpdiasy_val", strfasting,20);
            dao.setProcInValue(procIndex1, "bpclass", strbpErrmsg,21);
            dao.setProcInValue(procIndex1, "hoplstr_bp_classname", strbpErrmsg,22);
            dao.setProcInValue(procIndex1, "sugarfast_val", strfasting,23);
            dao.setProcInValue(procIndex1, "sugarpp_val", strRateId,24);
            dao.setProcInValue(procIndex1, "diabeticclass", strbpErrmsg,25);
            dao.setProcInValue(procIndex1, "hoplstr_diabetic_classname", strfastingErrmsg,26);
            dao.setProcInValue(procIndex1, "hba1c_val", strhba1c,27);
            dao.setProcInValue(procIndex1, "hba1c_class", strhba1cErrmsg,28);
            
            
            
            dao.setProcInValue(procIndex1, "hoplstr_hba1cclassname", strhba1cErrmsg,29);
            dao.setProcInValue(procIndex1, "hoplstr_sympinfo_val", strsymptoms,30);
            dao.setProcInValue(procIndex1, "gnum_isvalid", "1",31);
            dao.setProcInValue(procIndex1, "gnum_seat_id", strPatdtls[7],32);
            dao.setProcInValue(procIndex1, "gnum_lstmod_seat_id", "",33);
            dao.setProcInValue(procIndex1, "hopldt_entry_date", "",34);
            dao.setProcInValue(procIndex1, "hopldt_lstmod_date", "",35);
            dao.setProcInValue(procIndex1, "hoplstr_chronic_vitals", "",36);
            dao.setProcInValue(procIndex1, "hoplstr_tempreture_classname", "",37);
            dao.setProcInValue(procIndex1, "hoplstr_sugarpp_classname", "",38);
            
            
            
            
            dao.setProcInValue(procIndex1, "hoplstr_json_data", JsonData,39);
            dao.setProcInValue(procIndex1, "pulse_rate", strpulseRate,40);
            dao.setProcInValue(procIndex1, "hoplstr_blood_group", strbloodGroup,41);
            dao.setProcInValue(procIndex1, "curcumference_val", strcurcumference,42);
            
            
            dao.setProcInValue(procIndex1, "muac_val", strmuac,43);
           
             dao.setProcOutValue(procIndex1, "err", 1,44);
            dao.executeProcedureByPosition(procIndex1);
		   	
            return "1";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveEMRVitalData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "0";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	}
	
	public static String getModifyVitalData(String JsonData) throws JSONException {

		
		System.out.println("method called for getModifyVitalData----> ");
		/* Get EHR Details */
    	String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.get_vital_dtls(?,?,?,?,? ,?,?,?,?)}";
        int procIndex1 = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
        
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
        	JSONObject object = new JSONObject(JsonData);
   		 	String CR_No=(String) object.get("CR_No")==null ? "" : (String) object.get("CR_No") ;
   			String episodeCode=(String) object.get("episodeCode")==null ? "" : (String) object.get("episodeCode") ;
   			String hospitalCode=(String) object.get("hospitalCode")==null ? "" : (String) object.get("hospitalCode") ;
   			String visitNo=(String) object.get("visitNo")==null ? "" : (String) object.get("visitNo") ;
        	//System.out.println("crno::::::::::::::"+crno);
            dao = new HisDAO("WebServices", "EHRDetailsDAO.getPatinetEHRDtls()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "modeval", "1",1);
            dao.setProcInValue(procIndex1, "crno", CR_No,2);
            dao.setProcInValue(procIndex1, "episodeCode", episodeCode,3);
            dao.setProcInValue(procIndex1, "visitNo", visitNo,4);
            dao.setProcInValue(procIndex1, "seatId", "",5);
            dao.setProcInValue(procIndex1, "entrydate", "",6);
            dao.setProcInValue(procIndex1, "hosp_code", hospitalCode,7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            dao.setProcOutValue(procIndex1, "resultset", 2,9);
            //dao.executeProcedureByPosition(procIndex1);
            
            dao.executeProcedureByPosition(procIndex1);
            err=dao.getString(procIndex1, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		//JSONParser parser = new JSONParser();
                		//org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
             
              }
       
          
         
          mainObj.put("status", "1");
          mainObj.put("VitalDtls", jsonolist);
         
             if(ws != null){
        		ws.close();
        		ws = null;
        	}          
            return mainObj.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.getModifyVitalData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            mainObj.put("status", "2");
            mainObj.put("VitalDtls", "");
            return mainObj.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
    }
	public static void SaveGenralDataFormattedData(String JsonData) {
		
       System.out.println("method called for ipd SaveGenralDataFormattedData for data check---->");

		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.hopl_emr_dtl(?,?,?,?,?, ?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			/*System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));*/

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("EpisodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			String EpisodeVisitNo=(String) object.get("EpisodeVisitNo");
   			/*JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			*/
   			StringBuffer sb=new StringBuffer();
   			
			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode", "1",1);
            dao.setProcInValue(procIndex1, "p_puk", Crno,2);
            dao.setProcInValue(procIndex1, "p_hospcode", hosp_code,3); 
            dao.setProcInValue(procIndex1, "p_seatId", seatId,4); 
            dao.setProcInValue(procIndex1, "p_episodecode",EpisodeCode ,5); 
            dao.setProcInValue(procIndex1, "p_visitno", EpisodeVisitNo,6);
            dao.setProcInValue(procIndex1, "p_json", JsonData,7);
			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
			
            dao.setProcOutValue(procIndex1, "err", 1,9);
            dao.executeProcedureByPosition(procIndex1);
		   		
		   			return ;
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveGenralDataFormattedData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return ;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	public static void SavePrescriptionBookMarkData(String JsonData) {
		
		 System.out.println("method called for ipd SavePrescriptionBookMarkData for data check---->");


		String err = "";
    	String proc_name1 = "{call pkg_ipdDesk_dml.hopl_emr_dtl(?,?,?,?,?, ?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			/*System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));*/

   			//String Crno=(String) object.get("CR_No");
   			//String EpisodeCode=(String) object.get("EpisodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			//String EpisodeVisitNo=(String) object.get("EpisodeVisitNo");
   			/*JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			*/
   			StringBuffer sb=new StringBuffer();
   			
			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode", "2",1);
            dao.setProcInValue(procIndex1, "p_puk", "",2);
            dao.setProcInValue(procIndex1, "p_hospcode", hosp_code,3); 
            dao.setProcInValue(procIndex1, "p_seatId", seatId,4); 
            dao.setProcInValue(procIndex1, "p_episodecode","" ,5); 
            dao.setProcInValue(procIndex1, "p_visitno", "",6);
            dao.setProcInValue(procIndex1, "p_json", JsonData,7);
			dao.setProcInValue(procIndex1, "p_isvalid", "1",8);
			
            dao.setProcOutValue(procIndex1, "err", 1,9);
            dao.executeProcedureByPosition(procIndex1);
		   		
		   			return ;
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SavePrescriptionBookMarkData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return ;
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	
	public static String SaveEConsultancyData(String JsonData) {
		System.out.println("method called for ipd SaveEConsultancyData--->");
		String err = "";
    	String proc_name1 = "{call pkg_webservices.update_request_status(?,?,?,?,?, ?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
        int totBillQty=0;
        
         String reqno=null,billNo=null,crno=null;
         JSONObject mainObj = new JSONObject();
         HttpServletRequest request=null;
         StringBuffer buffer=new StringBuffer();
         JSONObject mainObj1 = new JSONObject(); 
        
        try {
        	
   		 JSONObject object = new JSONObject(JsonData);
			System.out.println("JsonData"+JsonData);
			System.out.println("Diagnosis "+object.get("Diagnosis"));
			System.out.println("CR_No"+object.get("CR_No"));
			System.out.println("episodeCode"+object.get("episodeCode"));

   			String Crno=(String) object.get("CR_No");
   			String EpisodeCode=(String) object.get("episodeCode");
   			String seatId=(String) object.get("seatId");
   			String hosp_code=(String) object.get("hosp_code");
   			JSONArray ReasonOfVisit =(JSONArray) object.get("ReasonOfVisit");
   			String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
   			StringBuffer sb=new StringBuffer();
   			
			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.SaveEConsultancyData()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode_val", "1",1);
            dao.setProcInValue(procIndex1, "p_req_id", PatCompleteGeneralDtl.split("#")[19],2);
            dao.setProcInValue(procIndex1, "p_hosp_code", hosp_code,3); 
            dao.setProcInValue(procIndex1, "p_req_status", "3",4); 
            dao.setProcInValue(procIndex1, "p_cons_id","" ,5); 
            dao.setProcInValue(procIndex1, "p_cons_name", "",6);
            dao.setProcInValue(procIndex1, "p_cons_mob_no", "",7);
            dao.setProcOutValue(procIndex1, "err", 1,8);
            
            dao.executeProcedureByPosition(procIndex1);
		   		
		   			return "DATA INSERTED SUCCESSFULLY";
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveEHRData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
            return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	}	
	
	
		
}
