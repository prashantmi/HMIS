package Investigation_webservice.dao;


import hisglobal.Status;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;
import javax.xml.bind.DatatypeConverter;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.utl.InvResultReportPrintingUTIL;
import new_investigation.transactions.controller.utl.MergeAllPdfNewInv;
import new_investigation.transactions.controller.utl.MongoXmlHandler;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.template.ResultEntryVO;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import Investigation_webservice.vo.InvauthVO;
import Investigation_webservice.vo.InvestigationReportVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InvCrNoDAO extends DataAccessObject {

	
	public InvCrNoDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	private static String propertiesFileName = "Investigation_webservice.Investigation_query";
	
	public static String InvCrNoBasedDetails(String mode, String crno,String hospCode) {

		String err = "";
		String proc_name1 = "{call inv_crno_webservice.proc_inv_crno_based_details(?,?,?,?,?)}";
		int procIndex1 = 0;
		int procIndex2 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		String status = "0";
		JSONObject mainObj = new JSONObject();  //for combined data
		JSONObject DemoObj = new JSONObject();  //for demo data
		JSONObject EncObj = new JSONObject();   //for enc data

		ArrayList<String> columnlist = new ArrayList<String>();
		ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
		int length = 0;
		try {
			dao = new HisDAO("patient_demographic_data", "getPatientDtl()");
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "p_mode", mode, 1);
			dao.setProcInValue(procIndex1, "hosp_code", hospCode, 2);
			dao.setProcInValue(procIndex1, "p_crno",crno, 3);
			dao.setProcOutValue(procIndex1, "err", 1, 4);
			dao.setProcOutValue(procIndex1, "resultset", 2, 5);
			dao.executeProcedureByPosition(procIndex1);
			err = dao.getString(procIndex1, "err");
			if (err.equals("")) {
				ws = dao.getWebRowSet(procIndex1, "resultset");
				length = ws.getMetaData().getColumnCount();
				for (int i = 1; i <= length; i++) {
					columnlist.add(ws.getMetaData().getColumnName(i)
							.toUpperCase());
				}

			}
			if (ws != null && ws.size() > 0) {
				while (ws.next()) {
					//int j = 0;
					status = "1";
					JSONObject js = new JSONObject();
					for (int i = 1; i <= length; i++) {
						String key = columnlist.get(i - 1);
						String value = ws.getString(i);
						js.put(key, value);

					}
					jsonolist.add(js);

				}
				
			}
			 System.out.println("jsonolist"+jsonolist);

			// DemoObj.put("status", status);
			 if(mode.equals("0"))
			 {
			 DemoObj.put("patient_demographic_data", jsonolist);
			}
			 else
			 {
				 
				 DemoObj.put("patient_Encounter_data", jsonolist); 
			 }
			 DemoObj.toString();
			 return DemoObj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return DemoObj.toString();
		}
			
finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	
	
	
	


		
	public  InvauthVO getauthdata(String sourcee) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
		String queryKey = "SELECT_AUTH_DATA";
		InvauthVO thaladirVO = null;
		List<InvauthVO> reqLst = new ArrayList<InvauthVO>();
		try {
			System.out.println("queryKeyqueryKey"+queryKey);
			String query = HelperMethodsDAO.getQuery(propertiesFileName,queryKey);
			//con = GlobalUtils.getBloodBankUATConnection();		
			con=super.getTransactionContext().getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, sourcee.toLowerCase());
			rs =  stmt.executeQuery();
			while (rs.next()) {
				thaladirVO=new InvauthVO();
				String key=rs.getString("key");
				String iswhitelistreq=rs.getString("iswhitelistreq");
				String whitelistip=rs.getString("whitelistip");
				String source=rs.getString("source");
			
				
				HelperMethods.populateVOfrmRS(thaladirVO, rs);
	            //reqLst.add(thaladirVO);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
			return thaladirVO;
		}
	
	
	
	/*public  boolean logDetail(String ipaddress,String source,String key,String url,String type) {
		
		//BiometricVO thalVo = null;
		String hcode  = "";
		PreparedStatement stmt = null;
		Connection con = null;
		boolean result = false;
		String query = "";
		try{
			 String biometricdatainsert = "INSERT_LOG_DETAIL_";
			 con=super.getTransactionContext().getConnection();			
			 query = HelperMethodsDAO.getQuery(propertiesFileName,biometricdatainsert);
			stmt = con.prepareStatement(query);
			
		         
		            int idx=1;
					stmt.setString(1, (ipaddress));//hbnum_temp_requisition_no
					
					stmt.setString(2, (source));//hbnum_billing_category
					stmt.setString(3, (key));//hbdt_issue_date
					stmt.setString(4, (url));//hbstr_patient_firstname
					stmt.setString(5, (type));//hbstr_patient_firstname
				//	Date date = new Date();
				//	System.out.println(dateFormat.format(date)); 
				//	String formattedDate = dateFormat.format(date);
						int j = stmt.executeUpdate();
				
			 con.setAutoCommit(true);
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		
//		if(result){
//			 return hcode;
//		}  
		
		return result;
	}*/

	
	public  boolean logDetail(String ipaddress,String source,String key,String url,String type) 
	{
		boolean result = true;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	//	String queryKey ="UPDATE.SAMPLECOllDETAIL.HIVT_SAMPLENO_CONF_MS";
	//	String queryKey1 ="UPDATE.SAMPLECOLLDETAIL.HIVT_COLAREA_SAMNO_CONFIG_MST";
		
		
		//BiometricVO thalVo = null;
		String hcode  = "";
		PreparedStatement stmt = null;
		Connection con = null;
		//boolean result = false;
		String query = "";
		 String biometricdatainsert = "INSERT_LOG_DETAIL_";

		 try {
			query = HelperMethodsDAO.getQuery(propertiesFileName,biometricdatainsert);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	
		try
		{
			/**hivbl_list_status=?, updateSampleCollInhivtsamplenoconfmst1ResetLabNO
                            WHERE hivnum_packing_list_no=?
                 **/
			
			populateMAP.put(sq.next(),ipaddress);
			populateMAP.put(sq.next(),source);
			populateMAP.put(sq.next(),key);
			populateMAP.put(sq.next(),url);
			populateMAP.put(sq.next(),type);


		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	

	return result;
	}

	

	public String isfromFTPorMONGO( )
	{
		String count=""	;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		//String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.HIVT_REPORT_DB_MST_SERVICE";
		 
		try
		{
			 query = HelperMethodsDAO.getQuery(propertiesFileName,queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
		 
			//populateMAP.put(sq.next(),sampleAcceptanceVO.getPackingListNO());
			//populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			//populateMAP.put(sq.next(),sampleAcceptanceVO.getFromDate());
			//populateMAP.put(sq.next(),sampleAcceptanceVO.getToDate());
		 	 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		 
		List<SampleAcceptanceVO> listSampleAcceptanceVO = new ArrayList<SampleAcceptanceVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			 
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				count=rs.getString(1);

			}
		}
		catch (Exception e)
		{
			
		}
		return count;
	}	
	

	
	public byte[]  printReportFTP(String filename,String ftpurl)
	{
		
		byte[] pdfData1 =null;
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		Status status = new Status();
		//List<byte[]> pdfs = new ArrayList<byte[]>(); //chnaged by chandan FTP
		List<InputStream> pdfs = new ArrayList<InputStream>();
		//System.out.println("fb.getFileName() : "+fb.getFileName());
         String strPdfName=filename;
		try
		{
			String patientcreatefileftpaddress= "";

			String crNo=strPdfName.substring(0,15);
			System.out.println(strPdfName.substring(5,7));
			String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
		    String insideyear=MergeAllPdfNewInv.getInsideYear(crNo);
		    String count=MergeAllPdfNewInv.getcount(crNo);
		      

			  String strPdfPath = ftpurl+"/"+strPdfName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+strPdfName.substring(0,15)+"/"+strPdfName;
			//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
			  
			  URL urlftp1 =new URL(strPdfPath);
				URLConnection urlc1=	urlftp1.openConnection();
	            
	            
	            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
				InputStream is = null;
				try {
				  is = urlftp1.openStream ();
				  byte[] byteChunk = new byte[4897]; // Or whatever size you want to read in at a time.
				  int n;

				  while ( (n = is.read(byteChunk)) > 0 ) {
					  baos1.write(byteChunk, 0, n);
				  }
				}
				catch (IOException e) {
				  System.err.printf ("Failed while reading bytes from %s: %s", urlftp1.toExternalForm(), e.getMessage());
				  e.printStackTrace ();
				  // Perform any other exception handling that's appropriate.
				}
				finally {
				  if (is != null) { is.close(); }
				}
				
	             pdfData1 = baos1.toByteArray();

		}
		catch (Exception e)
		{
			strMsgText = "InvResultReportPrintingUTIL.printReport() -> " + e.getMessage();
			//HisException eObj = 
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
			
			
			//WebUTIL.setStatus(objRequest_p, status);
		}

		return pdfData1;

	}	
	
	
	public static String AjaxBilledUnbilledDetails(Inv_SampleCollectionVO vo, UserVO userVO) {

		String err = "";
		String proc_name1 = "{call inv_crno_webservice.proc_billing_dtl_based_on_crno(?,?,?,?,?)}";
		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		String status = "0";
		JSONObject mainObj = new JSONObject();  //for combined data
		ArrayList<String> columnlist = new ArrayList<String>();
		ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
		int length = 0;
		try {
			dao = new HisDAO("patient_demographic_data", "AjaxBilledUnbilledDetails()");
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "reqtype", vo.getReqType(), 1);
			dao.setProcInValue(procIndex1, "hospcode",userVO.getHospitalCode(), 2);
			dao.setProcInValue(procIndex1, "crno",vo.getCrNo(), 3);
			dao.setProcOutValue(procIndex1, "err", 1, 4);
			dao.setProcOutValue(procIndex1, "resultset",2, 5);
			dao.executeProcedureByPosition(procIndex1);
			err = dao.getString(procIndex1, "err");
			if (err.equals("")) {
				ws = dao.getWebRowSet(procIndex1, "resultset");
				length = ws.getMetaData().getColumnCount();
				for (int i = 1; i <= length; i++) {
					columnlist.add(ws.getMetaData().getColumnName(i)
							.toUpperCase());
				}

			}
			if (ws != null && ws.size() > 0) {
				while (ws.next()) {
					//int j = 0;
					status = "1";
					JSONObject js = new JSONObject();
					for (int i = 1; i <= length; i++) {
						String key = columnlist.get(i - 1);
						String value = ws.getString(i);
						js.put(key, value);

					}
					jsonolist.add(js);

				}
				
			}
			 System.out.println("jsonolist"+jsonolist);

			 mainObj.put("BilledUnbilledDtl", jsonolist);
			
			 mainObj.toString();
			 return mainObj.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return mainObj.toString();
		}
			
	finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			}
		}


	
	
	
}
