package new_investigation.transactions.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.reportDownloadProcessVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class reportDownloadProcessDAO extends DataAccessObject {

	public reportDownloadProcessDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	public String fetchMobileNo(reportDownloadProcessFB fb)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="SELECT.FETCH_MOBILE_NO.HRGT_PATIENT_DTL";
		String record=null;
		/*try
		{
			int value= Integer.parseInt(fb.getCannedCode());
			fb.getCannedCode().valueOf(value);
		}
		catch(Exception e)
		{
			
		}*/

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{
			       
			populateMAP.put(sq.next(),fb.getCrNo());

		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			return record;
		}
		return record;

	}
	
	
	public String fetchusername(reportDownloadProcessFB fb)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="SELECT.FETCH_USERNAME.HRGT_PATIENT_DTL";
		String record=null;
		/*try
		{
			int value= Integer.parseInt(fb.getCannedCode());
			fb.getCannedCode().valueOf(value);
		}
		catch(Exception e)
		{
			
		}*/

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{
			       
			populateMAP.put(sq.next(),fb.getCrNo());

		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			return record;
		}
		return record;

	}
	
	
	
	public String validatecrno(reportDownloadProcessFB fb)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="SELECT.VALIDATE_CRNO_.HRGT_PATIENT_DTL";
		String record=null;
		/*try
		{
			int value= Integer.parseInt(fb.getCannedCode());
			fb.getCannedCode().valueOf(value);
		}
		catch(Exception e)
		{
			
		}*/

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{
			       
			populateMAP.put(sq.next(),fb.getCrNo());

		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			return record;
		}
		return record;

	}
	
	
	
	
	public Map fetchlist(reportDownloadProcessVO vo)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="SELECT.LIST.HIVT_REQUISITION_DTL_PUBLIC_REPORT";
		String record=null;
		String json = null;
		String location = null;
		ValueObject[] valueObjects = null;
		List<InvResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvResultReportPrintingVO>();
		Map mp=new HashMap();
		/*try
		{
			int value= Integer.parseInt(fb.getCannedCode());
			fb.getCannedCode().valueOf(value);
		}
		catch(Exception e)
		{
			
		}*/

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return mp;
		}
		try
		{
			  
          //  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			//populateMAP.put(sq.next(),_UserVO.getUserSeatId());
		//	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),vo.getCrNo());
		//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
		}

		catch (Exception e)
		{
			return mp;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);


			if (rs== null || !rs.next()) {
				json = "{\"data\": []}";
				location = "";
				// throw new HisRecordNotFoundException();
			} else {
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(InvResultReportPrintingVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultReportPrintingVO.add((InvResultReportPrintingVO)valueObjects[i]);
				}
				
				mp.put(InvestigationConfig.PUBLIC_LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
				
				String totalCount = "0";
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				boolean isFirstRecord = true;
				int count = 0;
				// json = "{\"records\": [";
				rs.beforeFirst();
				json = "{\"data\": [";
				while (rs.next()) {
				
					count++;
					if (isFirstRecord) {
						isFirstRecord = false;						
					} else {
						json += ",";
						//location += "$";
					}
					// String name = rs.getString(1);
					// String address = rs.getString(2);
					// // json += "{\"name\":\""+name + "\",";
					// // json += "\"address\":\""+address + "\"}";
					// json += "[\""+name + "\",";
					// json += "\""+address + "\"]";
					String url = rs.getString(3);
					String crno = rs.getString(21);

					String patname = rs.getString(23);
					String agegender = rs.getString(39).trim()+"/"+rs.getString(24);
					String deptunit = rs.getString(46);
					String testname = rs.getString(8);
					String labname = rs.getString(10);
					String sampleno = rs.getString(14);
					String labno = rs.getString(16);
					
					String reqDate = rs.getString(48);
					
					String repDate = rs.getString(49);
					
                     String reqDateee = rs.getString(50);
					
					String repDatee = rs.getString(51);
					
					
					if(sampleno.equalsIgnoreCase(labno))
					{
						
					}
					else
					{
						sampleno=sampleno+"/"+labno;
					}
					String patstatus = rs.getString(40);
					String reqdno = rs.getString(2);
					String reqno = rs.getString(1);
					String groupcode=rs.getString(12);
					String vall=crno+"$"+reqno+"$"+reqdno+"$"+groupcode+"$"+url;
				     String selctedcheckbox="selctedcheckbox"+count;
				String firstRow1="<input type='checkbox' id="+selctedcheckbox+" class='chkbox' name='selctedcheckbox' value="+vall+" onclick='onclickk(this)'>";
					json += "[\"";
					json += firstRow1;
					
					json += "\",";
					json += "\"";
					json += reqDate;
					json += "\",\"";
					json += repDate;
					json += "\",\"";
					json += deptunit;
					json += "\",\"";
					json += testname;
					json += "\",\"";
					json += labname;
					json += "\",\"";
					
					
					// String x = "javascript:campSchedule(" + hospitalCode +
					// ");";//"$.fn.colorbox({open:true, href:'" +
					// BBPUBLICConfig.BBPUBLIC_LIST_CAMPSCHEDULEPAGE+
					// "?GETNEARBYCAMPS&hospitalCode=" + hospitalCode + "'})";
					// json += "<a href='" + x+ "'>View Camps</a>";
					
					json += "\"]";
					
				}
                       
				// json += "],\"queryRecordCount\":" + Integer.toString(count) +
				// ",\"totalRecordCount\":" + totalCount + "}";
				json += "]}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HisDataAccessException:: "
						+ e);
		}
		System.out.println("json:::"+json);
		mp.put(InvestigationConfig.publicjson,json);
		//json = json + "@%@" + location; 
		return mp;
	}
	
	
	
	
	
	public void saveResultReportPrintingDetailInpubicreport(InvResultReportPrintingVO invresultreportprintingvo)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="";
		
		
		
			queryKey="INSERT_REPORT_DTL_HIVT_PUBLIC_REPORT_DTL";
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			
			populateMAP.put(sq.next(),invresultreportprintingvo.getPatPuk());
			populateMAP.put(sq.next(),invresultreportprintingvo.getPatReqNo()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),invresultreportprintingvo.getIpAddress());
			
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
	}

	
	
	public Map Pfetchlist(reportDownloadProcessVO vo)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="SELECT.LIST.HIVT_REQUISITION_DTL_PUBLIC_REPORT";
		String record=null;
		String json = null;
		String location = null;
		ValueObject[] valueObjects = null;
		List<InvResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvResultReportPrintingVO>();
		Map mp=new HashMap();
		/*try
		{
			int value= Integer.parseInt(fb.getCannedCode());
			fb.getCannedCode().valueOf(value);
		}
		catch(Exception e)
		{
			
		}*/

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return mp;
		}
		try
		{
			  
          //  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			//populateMAP.put(sq.next(),_UserVO.getUserSeatId());
		//	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),vo.getCrNo());
		//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
		}

		catch (Exception e)
		{
			return mp;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);


			if (rs== null || !rs.next()) {
				json = "{\"data\": []}";
				location = "";
				// throw new HisRecordNotFoundException();
			} else {
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(InvResultReportPrintingVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultReportPrintingVO.add((InvResultReportPrintingVO)valueObjects[i]);
				}
				
			
				
				String totalCount = "0";
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				boolean isFirstRecord = true;
				int count = 0;
				// json = "{\"records\": [";
				rs.beforeFirst();
				json = "{\"data\": [";
				if (rs.next()) {
				 
					count++;
					if (isFirstRecord) {
						isFirstRecord = false;						
					} else {
						json += ",";
						//location += "$";
					}
					// String name = rs.getString(1);
					// String address = rs.getString(2);
					// // json += "{\"name\":\""+name + "\",";
					// // json += "\"address\":\""+address + "\"}";
					// json += "[\""+name + "\",";
					// json += "\""+address + "\"]";
					String url = rs.getString(3);
					String crno = rs.getString(21);

					String patname = rs.getString(23);
					String agegender = rs.getString(39).trim()+"/"+rs.getString(24);
					String deptunit = rs.getString(46);
					String testname = rs.getString(8);
					String labname = rs.getString(10);
					String sampleno = rs.getString(14);
					String labno = rs.getString(16);
					
					String reqDate = rs.getString(48);
					
					String repDate = rs.getString(49);
					
					if(sampleno.equalsIgnoreCase(labno))
					{
						
					}
					else
					{
						sampleno=sampleno+"/"+labno;
					}
					String patstatus = rs.getString(40);
					String reqdno = rs.getString(2);
					String reqno = rs.getString(1);
					String groupcode=rs.getString(12);
					String vall=crno+"$"+reqno+"$"+reqdno+"$"+groupcode+"$"+url;
				     String selctedcheckbox="selctedcheckbox"+count;
				String firstRow1="<input type='checkbox' id="+selctedcheckbox+" class='chkbox' name='selctedcheckbox' value="+vall+" onclick='onclickk(this)'>";
					json += "[\"";
					json += crno;
					
					json += "\",";
					json += "\"";
					json += patname;
					json += "\",\"";
					json += agegender;
					json += "\",\"";
					json += patstatus;
					json += "\",\"";
					
					
					
					// String x = "javascript:campSchedule(" + hospitalCode +
					// ");";//"$.fn.colorbox({open:true, href:'" +
					// BBPUBLICConfig.BBPUBLIC_LIST_CAMPSCHEDULEPAGE+
					// "?GETNEARBYCAMPS&hospitalCode=" + hospitalCode + "'})";
					// json += "<a href='" + x+ "'>View Camps</a>";
					
					json += "\"]";
					
				}
                       
				// json += "],\"queryRecordCount\":" + Integer.toString(count) +
				// ",\"totalRecordCount\":" + totalCount + "}";
				json += "]}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HisDataAccessException:: "
						+ e);
		}
		System.out.println("json:::"+json);
		mp.put(InvestigationConfig.publicjson,json);
		//json = json + "@%@" + location; 
		return mp;
	}

	
	
	public void loginInsertDetails(InvResultReportPrintingVO invresultreportprintingvo)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="";
		
		
		
			queryKey="INSERT_REPORT_LOGIN_DTL_HIVT_PUBLIC_REPORT_LOGIN_DTL";
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			
			populateMAP.put(sq.next(),invresultreportprintingvo.getPatCRNo()); 
			populateMAP.put(sq.next(),invresultreportprintingvo.getIpAddress());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),invresultreportprintingvo.getPatCRNo());
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
	}
	
}
