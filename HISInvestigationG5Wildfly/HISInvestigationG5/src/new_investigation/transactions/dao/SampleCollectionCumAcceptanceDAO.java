/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Collection Cum Acceptance Process
 ## Purpose						        :  
 ## Date of Creation					:25-May-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.transactions.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class SampleCollectionCumAcceptanceDAO extends DataAccessObject {

	public SampleCollectionCumAcceptanceDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
		public  List<SampleCollectionCumAcceptanceVO> getSampleCollectionArea(UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST";
			Sequence sq = new Sequence();

			List<SampleCollectionCumAcceptanceVO> lstInvSampleCollectionVO =null;
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
				rs.beforeFirst();
				
				 if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Sample Collection Area Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstInvSampleCollectionVO=new ArrayList<SampleCollectionCumAcceptanceVO>();
		                SampleCollectionCumAcceptanceVO InvSampleCollectionVO=null;
		                while (rs.next()) {
		                	InvSampleCollectionVO=new SampleCollectionCumAcceptanceVO();
		                    HelperMethods.populateVOfrmRS(InvSampleCollectionVO, rs);
		                    lstInvSampleCollectionVO.add(InvSampleCollectionVO);
		                }
		            }
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstInvSampleCollectionVO;

		}
		public  List<SampleCollectionCumAcceptanceVO> getPatList(SampleCollectionCumAcceptanceVO objSampleCollectionVO,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER_COLLCUMACC";
			String queryKey_All = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER_COLLCUMACC_ALL";
			Sequence sq = new Sequence();
			
			Date dateInstance = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateInstance);
			cal.add(Calendar.DATE, -7);
			Date date = cal.getTime();
			String dateBefore7Days = WebUTIL.getCustomisedSysDate(date, "dd-MMM-yyyy");
			
			String condition1=" and HRGNUM_PUK= "+objSampleCollectionVO.getPatCRNo();
			//String condition2=" AND (TRUNC(hivdt_entry_date) >= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))  AND (TRUNC(hivdt_entry_date) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
			String condition2=" AND (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+dateBefore7Days+"\','DD-Mon-YYYY')))  AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
			String condition3="and  (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getFromDate()+"\','DD-Mon-YYYY'))) AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getToDate()+"\','DD-Mon-YYYY')))";
			String orderby=" order by hivdt_entry_date";
			List<SampleCollectionCumAcceptanceVO> lstInvSampleCollectionVO =null;
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			if(!objSampleCollectionVO.getSampleAreaCode().equals("-2")){
				populateMAP.put(sq.next(), objSampleCollectionVO.getSampleAreaCode());
			}
			
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
			
			try
			{
				if(!objSampleCollectionVO.getSampleAreaCode().equals("-2")){
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}else{
					query = HelperMethodsDAO.getQuery(filename, queryKey_All);
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				if(objSampleCollectionVO.getPatCRNo()!=null&&!objSampleCollectionVO.getPatCRNo().equals(""))
				{
					if(objSampleCollectionVO.getFromDate()!=null&&!objSampleCollectionVO.getFromDate().equals("")&&objSampleCollectionVO.getToDate()!=null&&!objSampleCollectionVO.getToDate().equals(""))
					query+=condition3+condition1;
					
				}
				else if(objSampleCollectionVO.getFromDate()!=null&&!objSampleCollectionVO.getFromDate().equals("")&&objSampleCollectionVO.getToDate()!=null&&!objSampleCollectionVO.getToDate().equals(""))
				{
					query+=condition3;
				}
				else
				{
					 query+=condition2;
				}
				query+=orderby;
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
				rs.beforeFirst();
				
				 if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Patient List  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstInvSampleCollectionVO=new ArrayList<SampleCollectionCumAcceptanceVO>();
		                SampleCollectionCumAcceptanceVO InvSampleCollectionVO=null;
		                while (rs.next()) {
		                	InvSampleCollectionVO=new SampleCollectionCumAcceptanceVO();
		                    HelperMethods.populateVOfrmRS(InvSampleCollectionVO, rs);
		                    lstInvSampleCollectionVO.add(InvSampleCollectionVO);
		                }
		            }
			}
			catch (HisRecordNotFoundException e)
			{

			//	throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstInvSampleCollectionVO;

		}
		
		public  List<SampleCollectionCumAcceptanceVO> getBilledPatList(String reqNo,String reqType,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.BILLING_PAT_LIST.HIVT_REQUISITION_DTLS";
			Sequence sq = new Sequence();
			
			SampleCollectionCumAcceptanceVO voSampleCollection=null;

			List<SampleCollectionCumAcceptanceVO> lstInvSampleCollectionVO =null;
			
			populateMAP.put(sq.next(), reqType);
			populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
			populateMAP.put(sq.next(), InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS);
			populateMAP.put(sq.next(), reqNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
				rs.beforeFirst();
				
				 if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Patient List  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstInvSampleCollectionVO=new ArrayList<SampleCollectionCumAcceptanceVO>();
		                while (rs.next()) {
		                	voSampleCollection=new SampleCollectionCumAcceptanceVO();
		                	HelperMethods.populateVOfrmRS(voSampleCollection, rs);
		                	 lstInvSampleCollectionVO.add(voSampleCollection);
		                }
		            }
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstInvSampleCollectionVO;

		}
		
		public List getUOMCombo(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List uomCombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.UOM_COMBO.HIVT_UOM_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					uomCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return uomCombo;
		}
		
		
		public List getContainerCombo(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List containerCombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.CONTAINER_COMBO.HIVT_CONTAINER_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					containerCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return containerCombo;
		}
		public String generateSampleNoSequence(String sampleCode,String labCode,UserVO userVO)
		{
			String sampleNo=""; 
			String errorMsg="";
			ResultSet rs=null;
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_GENERATE_SAMPLENO);
				strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(2,Types.VARCHAR,sampleCode);
				strProc.addInParameter(3,Types.VARCHAR,labCode);
				strProc.setReturnType(Types.VARCHAR);
				return	sampleNo = (String)strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			
		}
		
		
		public void insertSampleNoSequenceInMaintainer(String sampleCode,String labCode,String sequence,String yymmdd, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.SAMPLENO.SYS_SAMPLE_MAINTAINER";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),labCode);
				populateMAP.put(sq.next(),sampleCode);
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),sequence);
				populateMAP.put(sq.next(),yymmdd);
				
	        	
	            	
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
			}

		}
		
		public void updateSampleNoSequenceInMaintainer(String sequence,String sampleCode,String labCode,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLENO.SYS_SAMPLE_MAINTAINER";
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
				populateMAP.put(sq.next(),sequence);
				populateMAP.put(sq.next(),labCode);
				populateMAP.put(sq.next(),sampleCode);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
		
		// Commented By:- Anant Patel Dated:- 29th-May-2015
		// Reason:-Query Moved to Package'pkg_inv_dml.proc_insert_reqisition_header' from properties file

		/*public void updateRequisitionHeader(String mobileNo,String emailId,String patAddress,String reqNo,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PATDTLS.HIVT_REQUISITION_HEADER";
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
				populateMAP.put(sq.next(),mobileNo);
				populateMAP.put(sq.next(),emailId);
				populateMAP.put(sq.next(),patAddress);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), reqNo);
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
		}*/
		//In use from 29th-May-2015 Added By Anant Patel Dated:-29th-May-2015
		public void updateRequisitionHeader(String mobileNo,String emailId,String patAddress,String reqNo,UserVO _UserVO)
		{	
			try
			{ 
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_UPDATE_REQUSITION_HEADER);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_MODE);
				strProc.addInParameter(2,Types.VARCHAR,reqNo);
				strProc.addInParameter(3,Types.VARCHAR,null);
				strProc.addInParameter(4,Types.VARCHAR,_UserVO.getHospitalCode());
				strProc.addInParameter(5,Types.VARCHAR,null);
				strProc.addInParameter(6,Types.VARCHAR,null);
				strProc.addInParameter(7,Types.VARCHAR,null);
				strProc.addInParameter(8,Types.VARCHAR,null);
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR,null);
				strProc.addInParameter(11,Types.VARCHAR,null);
				strProc.addInParameter(12,Types.VARCHAR,null);
				strProc.addInParameter(13,Types.VARCHAR,null);
				strProc.addInParameter(14,Types.VARCHAR,null);
				strProc.addInParameter(15,Types.VARCHAR,null);
				strProc.addInParameter(16,Types.VARCHAR,null);
				strProc.addInParameter(17,Types.VARCHAR,null);
				strProc.addInParameter(18,Types.VARCHAR,null);
				strProc.addInParameter(19,Types.VARCHAR,null);
				strProc.addInParameter(20,Types.VARCHAR,null);
				strProc.addInParameter(21,Types.VARCHAR,patAddress);
				strProc.addInParameter(22,Types.VARCHAR,null);
				strProc.addInParameter(23,Types.VARCHAR,mobileNo);
				strProc.addInParameter(24,Types.VARCHAR,emailId);
				strProc.addInParameter(25,Types.VARCHAR,null);
				strProc.addInParameter(26,Types.VARCHAR,null);
				strProc.addInParameter(27,Types.VARCHAR,null);
				strProc.addInParameter(28,Types.VARCHAR,null);
				strProc.addInParameter(29,Types.VARCHAR,null);
				strProc.addInParameter(30,Types.VARCHAR,null);
				strProc.addInParameter(31,Types.VARCHAR,null);
				strProc.addInParameter(32,Types.VARCHAR,null);
				strProc.addInParameter(33,Types.VARCHAR,null);
				strProc.addInParameter(34,Types.VARCHAR,null);
				strProc.addInParameter(35,Types.VARCHAR,null);
				strProc.addInParameter(36,Types.VARCHAR,null);
				strProc.addInParameter(37,Types.VARCHAR,null);
				strProc.addInParameter(38,Types.VARCHAR,null);
				strProc.addInParameter(39,Types.VARCHAR,null);
				strProc.addInParameter(40,Types.VARCHAR,null);
				strProc.addInParameter(41,Types.VARCHAR,null);
				strProc.addInParameter(42,Types.VARCHAR,null);
				strProc.addInParameter(43,Types.VARCHAR,null);
				strProc.addInParameter(44,Types.VARCHAR,null);
				strProc.addInParameter(45,Types.VARCHAR,Config.IS_VALID_ACTIVE);
				strProc.addInParameter(46,Types.VARCHAR,null);
				strProc.addInParameter(47, Types.VARCHAR,null);
				strProc.addInParameter(48, Types.VARCHAR,null);
				strProc.addInOutParameter(49,Types.VARCHAR,"");
				strProc.execute(super.getTransactionContext().getConnection());
			}
			catch (HisDataAccessException e)
			{
				throw new HisDataAccessException();
			}
			
		}
		//In use from 28th-May-2015 Added By Anant Patel Dated:-28th-May-2015
		public void updateRequisitionDtl(SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
		{
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_REQUSITION_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_CUM_ACCEPTANCE_MODE);
				strProc.addInParameter(2,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(3,Types.VARCHAR,voSample.getRequisitionDNo());
				strProc.addInParameter(4,Types.VARCHAR,null);
				strProc.addInParameter(5,Types.VARCHAR,null);
				strProc.addInParameter(6,Types.VARCHAR,null);
				strProc.addInParameter(7,Types.VARCHAR,null);
				strProc.addInParameter(8,Types.VARCHAR,null);
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR, voSample.getReqDtlStatus());
				strProc.addInParameter(11,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(12,Types.VARCHAR,null);
				strProc.addInParameter(13,Types.VARCHAR,voSample.getTempSampleNo());
				strProc.addInParameter(14,Types.VARCHAR,null);
				strProc.addInParameter(15,Types.VARCHAR,null);
				strProc.addInParameter(16,Types.VARCHAR,null);
				strProc.addInParameter(17,Types.VARCHAR,voSample.getBillNo());
				strProc.addInParameter(18,Types.VARCHAR,null);
				strProc.addInParameter(19,Types.VARCHAR,null);
				strProc.addInParameter(20,Types.VARCHAR,null);
				strProc.addInParameter(21,Types.VARCHAR,null);
				if(voSample.getTempLabNumber()!=null && !voSample.getTempLabNumber().equals(""))
				strProc.addInParameter(22,Types.VARCHAR,voSample.getTempLabNumber());
				else
					strProc.addInParameter(22,Types.VARCHAR,voSample.getTempSampleNo());	
				strProc.addInParameter(23,Types.VARCHAR,null);
				strProc.addInParameter(24,Types.VARCHAR,null);
				strProc.addInParameter(25,Types.VARCHAR,null);
				strProc.addInParameter(26,Types.VARCHAR,null);
				strProc.addInParameter(27,Types.VARCHAR,null);
				strProc.addInParameter(28,Types.VARCHAR,null);
				strProc.addInParameter(29,Types.VARCHAR,null);
				strProc.addInParameter(30,Types.VARCHAR,null);
				strProc.addInParameter(31,Types.VARCHAR,null);
				strProc.addInParameter(32,Types.VARCHAR,null);
				strProc.addInParameter(33,Types.VARCHAR,InvestigationConfig.SAMPLE_ACCEPTED);
				strProc.addInParameter(34,Types.VARCHAR,voSample.getPackingListNo());
				strProc.addInParameter(35,Types.VARCHAR,voSample.getTestBasedMachine());
				strProc.addInParameter(36,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(37,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(38,Types.VARCHAR,voSample.getSampleAreaCode());
				strProc.addInParameter(39,Types.VARCHAR,null);
				strProc.addInParameter(40,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(41,Types.VARCHAR,InvestigationConfig.SAMPLE_RECEIVED);
				strProc.addInParameter(42,Types.VARCHAR,voSample.getSampleNo());
				strProc.addInParameter(43,Types.VARCHAR,voSample.getDefaultUOMCode());
				strProc.addInParameter(44,Types.VARCHAR,voSample.getSampleQnty());
				strProc.addInParameter(45,Types.VARCHAR,voSample.getDefaultContainerCode());
				strProc.addInParameter(46,Types.VARCHAR,null);
				strProc.addInParameter(47,Types.VARCHAR,null);
				
				strProc.addInOutParameter(48,Types.VARCHAR ,"");
				strProc.addInParameter(49,Types.VARCHAR ,null);

				strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisDataAccessException e)
			{
				throw new HisDataAccessException();
			}
			
		}
		
		
		// Commented By:- Anant Patel Dated:- 28th-May-2015
		// Reason:-Query Moved to Package'pkg_inv_dml.proc_insert_reqisition_dtl' from properties file
		/*public void updateRequisitionDtl(SampleCollectionCumAcceptanceVO voSample,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.REQDTLS.HIVT_REQUISITION_DTLS";
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
				populateMAP.put(sq.next(),voSample.getReqDtlStatus());
				populateMAP.put(sq.next(),voSample.getSampleNo());
				populateMAP.put(sq.next(),voSample.getBillNo());
				populateMAP.put(sq.next(),voSample.getTempSampleNo());
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(),voSample.getSampleAreaCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), voSample.getRequisitionDNo());
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
		}*/
		// Commented By:- Anant Patel Dated:- 28th-May-2015
		// Reason:-Query Moved to Package'pkg_inv_dml.proc_insert_sample_dtl' from properties file
		/*public void insertSampleDtl(SampleCollectionCumAcceptanceVO voSample, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.SAMPLEDTLS.HIVT_SAMPLE_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),voSample.getSampleNo());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),voSample.getRequisitionDNo());
				populateMAP.put(sq.next(),voSample.getSampleAreaCode());
				populateMAP.put(sq.next(),_userVO.getSeatId());
				populateMAP.put(sq.next(),voSample.getPrintStatus());
				populateMAP.put(sq.next(),voSample.getSampleQnty());
				populateMAP.put(sq.next(),voSample.getSampleCode());
				populateMAP.put(sq.next(),voSample.getDefaultUOMCode());
				populateMAP.put(sq.next(),voSample.getDefaultContainerCode());
				populateMAP.put(sq.next(),voSample.getTypeOfComponent());
				populateMAP.put(sq.next(),_userVO.getSeatId());
				populateMAP.put(sq.next(),(voSample.getSampleStatusCode()==null?"1":voSample.getSampleStatusCode()));
				populateMAP.put(sq.next(),(voSample.getTempSampleNo()==null?"0":voSample.getTempSampleNo()));
				
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_SAMPLE_DTL Table");
			}

		}*/
		//In use from 28th-May-2015 Added By Anant Patel Dated:-28th-May-2015
		public void insertSampleDtl(SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
		{
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_SAMPLE_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_CUM_ACCEPTANCE_MODE);
				strProc.addInParameter(2,Types.VARCHAR,voSample.getSampleNo());
				strProc.addInParameter(3,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(4,Types.VARCHAR,voSample.getRequisitionDNo());
				strProc.addInParameter(5,Types.VARCHAR,null);
				strProc.addInParameter(6,Types.VARCHAR,voSample.getSampleAreaCode());
				strProc.addInParameter(7,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(8,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR,null);
				strProc.addInParameter(11,Types.VARCHAR,null);
				strProc.addInParameter(12,Types.VARCHAR,voSample.getPrintStatus());
				strProc.addInParameter(13,Types.VARCHAR,voSample.getSampleQnty());
				strProc.addInParameter(14,Types.VARCHAR,voSample.getSampleCode());
				strProc.addInParameter(15,Types.VARCHAR,voSample.getDefaultUOMCode());
				strProc.addInParameter(16,Types.VARCHAR,voSample.getDefaultContainerCode());
				strProc.addInParameter(17,Types.VARCHAR,voSample.getTempSampleNo());
				strProc.addInParameter(18,Types.VARCHAR,null);
				strProc.addInParameter(19,Types.VARCHAR,voSample.getTypeOfComponent());
				strProc.addInParameter(20,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(21,Types.VARCHAR,InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
				strProc.addInParameter(22,Types.VARCHAR,voSample.getTempSampleNo());
				strProc.addInOutParameter(23,Types.VARCHAR ,"");
				strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisDataAccessException e)
			{
				throw new HisDataAccessException();
			}
			
		}
		
		
		public boolean checkSampleNoDuplicacy(SampleCollectionCumAcceptanceVO voSample,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			int count=0;
			boolean isTempSampleNoPresent=false;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.TEMP_SAMPLE_NO.HIVT_SAMPLE_DTL";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), voSample.getSampleAreaCode());
			
			populateMap.put(sq.next(), voSample.getSampleNo());
			try
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException();
				}
				else
				{
					count=rs.getInt(1);
					if(count>0)
						isTempSampleNoPresent=true;
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return isTempSampleNoPresent;
		}
		public List<SampleCollectionCumAcceptanceVO> getSampleCollAutoSampleNOConfig(SampleCollectionCumAcceptanceVO SampleCollectionCumAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
			 
			
			if (SampleCollectionCumAcceptanceVO.getPatType()==null)
				SampleCollectionCumAcceptanceVO.setPatType("1");
			else if(SampleCollectionCumAcceptanceVO.getPatType().equals("3") || SampleCollectionCumAcceptanceVO.getPatType().equals("4"))
				SampleCollectionCumAcceptanceVO.setPatType("1");
			else
				;
				
				
				
			try
			{
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("1"))
				{
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getTestCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

				
				} 
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("2"))
				{
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getSampleAreaCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

				} 
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<SampleCollectionCumAcceptanceVO> listSampleCollectionCumAcceptanceVO = new ArrayList<SampleCollectionCumAcceptanceVO>();
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
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleCollectionCumAcceptanceVO.class, rs);
					listSampleCollectionCumAcceptanceVO.add((SampleCollectionCumAcceptanceVO)valueObjects[0]);
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					return listSampleCollectionCumAcceptanceVO;
				}
				else 	return listSampleCollectionCumAcceptanceVO;
			}
			return listSampleCollectionCumAcceptanceVO;
		}	
		public String generateSampleNoDateSequence(String subLength,UserVO userVO)
		{
			String sequence_Hash_yyymmdd=""; 
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.SAMPLEACC.LABNODATEGEN.FROM.DUAL";
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				 
				populateMap.put(sq.next(), subLength);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("");
				}
				else
				{
					sequence_Hash_yyymmdd=rs.getString(1);
					
				}
				
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return sequence_Hash_yyymmdd;
		}
				  
 
		public void updateSampleCollInhivtsamplenoconfmst1resetsampleno(SampleCollectionCumAcceptanceVO voSamColl, String finalEntryDate,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLECOllDETAIL.RESET.RUNNING.NO.HIVT_SAMPLENO_CONF_MS";
			String queryKey1 ="UPDATE.SAMPLECOLLDETAIL.RESET.RUNNING.NO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			
			try
			{
				if(voSamColl.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
			}
			catch (Exception e) 
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				
				if(voSamColl.getTempSampleNo().equals("1"))
				{
					 
				populateMAP.put(sq.next(),voSamColl.getConfigLab());
				populateMAP.put(sq.next(),voSamColl.getConfigTest());
				populateMAP.put(sq.next(),voSamColl.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamColl.getConfigSeq());


				}
				
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				 
				populateMAP.put(sq.next(),voSamColl.getConfigLab());
				populateMAP.put(sq.next(),voSamColl.getConfigArea());
				populateMAP.put(sq.next(),voSamColl.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamColl.getConfigSeq());


				}
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
		
		
		
		public void updateSampleCollInhivtsamplenoconfmst1(SampleCollectionCumAcceptanceVO voSamColl, String finalEntryDate,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLECOllDETAIL.HIVT_SAMPLENO_CONF_MS";
			String queryKey1 ="UPDATE.SAMPLECOLLDETAIL.HIVT_COLAREA_SAMNO_CONFIG_MST";
			
			try
			{
				if(voSamColl.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
			}
			catch (Exception e) 
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				
				if(voSamColl.getTempSampleNo().equals("1"))
				{
					populateMAP.put(sq.next(),finalEntryDate);
					populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
					
				populateMAP.put(sq.next(),voSamColl.getConfigLab());
				populateMAP.put(sq.next(),voSamColl.getConfigTest());
				populateMAP.put(sq.next(),voSamColl.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamColl.getConfigSeq());


				}
				
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				populateMAP.put(sq.next(),finalEntryDate);
				populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
				populateMAP.put(sq.next(),voSamColl.getConfigLab());
				populateMAP.put(sq.next(),voSamColl.getConfigArea());
				populateMAP.put(sq.next(),voSamColl.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamColl.getConfigSeq());


				}
				
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

		
		public List checkAutoGenFormateSampleCollCum(SampleCollectionCumAcceptanceVO voColl,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
			if (voColl.getPatType()==null)
				voColl.setPatType("1");
			else if(voColl.getPatType().equals("3") || voColl.getPatType().equals("4"))
				voColl.setPatType("1");
			else
				;
			
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				if(voColl.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(voColl.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				if(voColl.getTempSampleNo().equals("1"))
				{
					populateMap.put(sq.next(),voColl.getLabCode());
					populateMap.put(sq.next(),voColl.getTestCode());
					populateMap.put(sq.next(),voColl.getPatType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());

				} 
				if(voColl.getTempSampleNo().equals("2"))
				{
					populateMap.put(sq.next(),voColl.getLabCode());
					populateMap.put(sq.next(),voColl.getConfigArea());
					populateMap.put(sq.next(),voColl.getPatType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());

				} 
				
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			List<Inv_SampleCollectionVO> listInv_SampleCollectionVO = new ArrayList<Inv_SampleCollectionVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				 
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(Inv_SampleCollectionVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						listInv_SampleCollectionVO.add((Inv_SampleCollectionVO)valueObjects[0]);
					//}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return listInv_SampleCollectionVO;
		}
		
		
		public String checkAutoGenFormateRunningLabNo(SampleCollectionCumAcceptanceVO inv_SampleCollectionVO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String Formate="";
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.AJAX.CHECK.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.AJAX.CHECK.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				if(inv_SampleCollectionVO.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				if(inv_SampleCollectionVO.getTempSampleNo().equals("1"))
				{
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigLab());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigTest());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigSeq());


				} 
				if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
				{
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigLab());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigArea());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getConfigSeq());


				} 
				
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					if(rs.next())
					{
						Formate=rs.getString(1);
 					}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return Formate;
		}
		
		
		public void saveSampleDetail(SampleCollectionCumAcceptanceVO inv_SampleCollectionVO, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.HMIT_SAMPLE_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename) OR getting query out of property file"
								+ e);
			}

			try {
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTestBasedMachine());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTestBasedMachine());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getRequisitionDNo());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTestCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getSampleNo());
				populateMAP.put(sq.next(),"0");
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPriorityCode());
				populateMAP.put(sq.next(),"0");
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTempSampleNo());
				populateMAP.put(sq.next(),_userVO.getUserId());
				populateMAP.put(sq.next(),"1");
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPatCRNo());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPatAge());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPatName());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPatGenderCode());
				
				
				
				
				
				
				
				
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
			}

		}
		
		
		public void updateRequestId(SampleCollectionCumAcceptanceVO inv_SampleCollectionVO, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE_REQUEST_ID.HMIT_MACHINE_MST";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename) OR getting query out of property file"
								+ e);
			}

			try {
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTestBasedMachine());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTestBasedMachine());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				
				
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
			}

		}

		
		
		
		public List<SampleCollectionCumAcceptanceVO> getSampleCollAutoSampleNOConfigtestwise(SampleCollectionCumAcceptanceVO SampleCollectionCumAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1_TESTWISE";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST_TESTWISE";
			   
			 
			
			if (SampleCollectionCumAcceptanceVO.getPatType()==null)
				SampleCollectionCumAcceptanceVO.setPatType("1");
			else if(SampleCollectionCumAcceptanceVO.getPatType().equals("3") || SampleCollectionCumAcceptanceVO.getPatType().equals("4"))
				SampleCollectionCumAcceptanceVO.setPatType("1");
			else
				;
				
				
				
			try
			{
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("1"))
				{
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getTestCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

				
				} 
				if(SampleCollectionCumAcceptanceVO.getTempSampleNo().equals("2"))
				{
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getSampleAreaCode());
				populateMAP.put(sq.next(),SampleCollectionCumAcceptanceVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

				} 
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<SampleCollectionCumAcceptanceVO> listSampleCollectionCumAcceptanceVO = new ArrayList<SampleCollectionCumAcceptanceVO>();
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
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleCollectionCumAcceptanceVO.class, rs);
					listSampleCollectionCumAcceptanceVO.add((SampleCollectionCumAcceptanceVO)valueObjects[0]);
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					return listSampleCollectionCumAcceptanceVO;
				}
				else 	return listSampleCollectionCumAcceptanceVO;
			}
			return listSampleCollectionCumAcceptanceVO;
		}	
		

		
		
		public List<SampleCollectionCumAcceptanceVO> checkAutoGenFormateDATEWISE(SampleCollectionCumAcceptanceVO voSample,UserVO _UserVO) // first time null ajax issue added by chandan on 22oct2019
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1_DATEWISE";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST_DATEWISE";
			   
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				if(voSample.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(voSample.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				if(voSample.getTempSampleNo().equals("1"))
				{
					populateMap.put(sq.next(),voSample.getLabCode());
					populateMap.put(sq.next(),voSample.getTestCode());
					populateMap.put(sq.next(),voSample.getPatType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
				} 
				if(voSample.getTempSampleNo().equals("2"))
				{
					populateMap.put(sq.next(),voSample.getLabCode());
					populateMap.put(sq.next(),voSample.getSampleAreaCode());
					populateMap.put(sq.next(),voSample.getPatType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());

				} 
				
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			List<SampleCollectionCumAcceptanceVO> listLabTestVO = new ArrayList<SampleCollectionCumAcceptanceVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				 
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleCollectionCumAcceptanceVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					listLabTestVO.add((SampleCollectionCumAcceptanceVO)valueObjects[0]);
					//}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return listLabTestVO;
		}

		
		
		public  String checkAutoGenFormateRunningLabNo_isysnc(SampleCollectionCumAcceptanceVO inv_SampleCollectionVO, UserVO _UserVO) {
		    String reqNo = "";
		    String errorMsg = "";
		    ResultSet rs = null;
		    
		    try {
		      Procedure strProc = null;
		     
		      if(inv_SampleCollectionVO.getTempSampleNo().equals("1"))
				{
		      strProc = new Procedure("pkg_inv_unique_sample_no_generation.check_samplno_conf_mst_issync");
			
		      strProc.addInParameter(1,12,inv_SampleCollectionVO.getConfigLab());
		      strProc.addInParameter(2,12,inv_SampleCollectionVO.getConfigTest());
		      strProc.addInParameter(3,12,inv_SampleCollectionVO.getConfigType());
		      strProc.addInParameter(4,12,_UserVO.getHospitalCode());
		      strProc.addInParameter(5,12,inv_SampleCollectionVO.getConfigSeq());
		      strProc.setReturnType(12);
		      
				}
		      
		      if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
				{
		    	  
		    	  strProc = new Procedure("pkg_inv_unique_sample_no_generation.check_samplno_collarea_mst_issync");
		    		
		          strProc.addInParameter(1,12,inv_SampleCollectionVO.getConfigLab());
		          strProc.addInParameter(2,12,inv_SampleCollectionVO.getConfigArea());
		          strProc.addInParameter(3,12,inv_SampleCollectionVO.getConfigType());
		          strProc.addInParameter(4,12,_UserVO.getHospitalCode());
		          strProc.addInParameter(5,12,inv_SampleCollectionVO.getConfigSeq());
		          strProc.setReturnType(12);
		          
				}
		      //strProc.addInParameter(1, 12, userVO.getHospitalCode());
		      //strProc.addInParameter(2, 12, labCode);
		      //strProc.setReturnType(12);
		     
		    
			
		      
		      return reqNo = (String)strProc.execute(getTransactionContext().getConnection());
		    
		    }
		    catch (HisRecordNotFoundException e) {
		      
		      throw new HisRecordNotFoundException("No Record Found");
		    } 
		  }


		  
		  
		public void updateSampleCollInhivtsamplenoconfmst1_issync(SampleCollectionCumAcceptanceVO voSamColl,String finalDate, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLECOllDETAIL.HIVT_SAMPLENO_CONF_MS_ISYSYNC";
			String queryKey1 ="UPDATE.SAMPLECOLLDETAIL.HIVT_COLAREA_SAMNO_CONFIG_MST_ISYSYNC";
			
			try
			{
				if(voSamColl.getTempSampleNo().equals("1"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
				}
			}
			catch (Exception e) 
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				/**hivbl_list_status=?, updateSampleCollInhivtsamplenoconfmst1ResetLabNO
		                        WHERE hivnum_packing_list_no=?
		             **/
				if(voSamColl.getTempSampleNo().equals("1"))
				{
					populateMAP.put(sq.next(),finalDate);
					populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
					
				populateMAP.put(sq.next(),voSamColl.getConfigLab());
				populateMAP.put(sq.next(),voSamColl.getConfigTest());
				populateMAP.put(sq.next(),voSamColl.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamColl.getConfigSeq());


				}
				
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				populateMAP.put(sq.next(),finalDate);
				populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
				populateMAP.put(sq.next(),voSamColl.getConfigLab());
				populateMAP.put(sq.next(),voSamColl.getConfigArea());
				populateMAP.put(sq.next(),voSamColl.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamColl.getConfigSeq());


				}
				
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
