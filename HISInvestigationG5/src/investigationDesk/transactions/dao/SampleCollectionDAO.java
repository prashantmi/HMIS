package investigationDesk.transactions.dao;

/**
 * @author : C-DAC, Noida
 * Project : HISInvestigationG5
 * Module  : Blood Bank
 * Created On : 18 Aug, 2008
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This Class should be used for all Essential Data Access Data Layer Methods 
 * 			regarding all processes
 * 
 * Modified By: Pawan Kumar B N
 * 
 * Modified On: 18-11-2011
 */


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;








import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import investigationDesk.InvestigationConfig;
import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.LabTestVO;
//import investigationDesk.vo.SampleCollectionCumAcceptanceVO;




public class SampleCollectionDAO extends DataAccessObject //implements InvestigationEssentialDAOi
{
	public SampleCollectionDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
		
	/*  ## 		Modification Log							
 		##		Modify Date				:19thFeb'15 
 		##		Reason	(CR/PRS)		:Sample Collection Process :: get 
 		##		Modify By				:Sheeldarshi */
		public  List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _UserVO,String wardCode)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST";
			Sequence sq = new Sequence();

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			populateMAP.put(sq.next(), wardCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), wardCode);
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
		                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
		                Inv_SampleCollectionVO InvSampleCollectionVO=null;
		                while (rs.next()) {
		                	InvSampleCollectionVO=new Inv_SampleCollectionVO();
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
		public  List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER";
			Sequence sq = new Sequence();
			
			String condition1=" and HRGNUM_PUK= "+objSampleCollectionVO.getPatCRNo();
			String orderby=" order by HIVDT_REQUISITION_DATE";
      		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(),_UserVO.getSeatId());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), objSampleCollectionVO.getSampleAreaCode());
			populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
			//put(sq.next(), objSampleCollectionVO.getFromDate());
			//populateMAP.put(sq.next(), objSampleCollectionVO.getToDate());
		
		
			
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
				
					query+=condition1+orderby;
				
			
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
		                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
		                Inv_SampleCollectionVO InvSampleCollectionVO=null;
		                while (rs.next()) {
		                	InvSampleCollectionVO=new Inv_SampleCollectionVO();
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
		//End:Sheeldarshi
		
		public  List<Inv_SampleCollectionVO> getBilledPatList(String reqNo,String reqType,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.BILLING_PAT_LIST.HIVT_REQUISITION_DTLS";
			Sequence sq = new Sequence();
			
			Inv_SampleCollectionVO voSampleCollection=null;

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			
			populateMAP.put(sq.next(), reqType);
			populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
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
		                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
		                while (rs.next()) {
		                	voSampleCollection=new Inv_SampleCollectionVO();
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
			
			//populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
			
			//populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
		// Not in Use form 27th-May-2015
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
		
		//Not in Use from 27th-May-2015
		public void 
eSampleNoSequenceInMaintainer(String sequence,String sampleCode,String labCode,UserVO _UserVO)
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
		
		// Commented By:- Anant Patel Dated:- 28th-May-2015
	   //   Reason:-Query Moved to Package'pkg_inv_dml.proc_insert_reqisition_dtl from properties file'
		/*public void updateRequisitionDtl(Inv_SampleCollectionVO voSample,UserVO _UserVO)
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
				populateMAP.put(sq.next(),voSample.getDefaultUOMCode());
				populateMAP.put(sq.next(),voSample.getDefaultContainerCode());
				populateMAP.put(sq.next(),voSample.getSampleQnty());
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
		
		//In use from 28th-May-2015 Added By Anant Patel Dated:-28th-May-2015
		public void updateRequisitionDtl(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			String sampleNo=""; 
			String errorMsg="";
			ResultSet rs=null;
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_REQUSITION_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_MODE);
				strProc.addInParameter(2,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(3,Types.VARCHAR,voSample.getRequisitionDNo());
				strProc.addInParameter(4,Types.VARCHAR,null);
				strProc.addInParameter(5,Types.VARCHAR,null);
				strProc.addInParameter(6,Types.VARCHAR,null);
				strProc.addInParameter(7,Types.VARCHAR,null);
				strProc.addInParameter(8,Types.VARCHAR,null);
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR,voSample.getReqDtlStatus());
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
				strProc.addInParameter(22,Types.VARCHAR,null);
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
				strProc.addInParameter(34,Types.VARCHAR,null);
				strProc.addInParameter(35,Types.VARCHAR,null);
				strProc.addInParameter(36,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(37,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(38,Types.VARCHAR,voSample.getSampleAreaCode());
				strProc.addInParameter(39,Types.VARCHAR,null);
				strProc.addInParameter(40,Types.VARCHAR,null);
				strProc.addInParameter(41,Types.VARCHAR,InvestigationConfig.SAMPLE_RECEIVED);
				strProc.addInParameter(42,Types.VARCHAR,voSample.getSampleNo());
				strProc.addInParameter(43,Types.VARCHAR,voSample.getDefaultUOMCode());
				strProc.addInParameter(44,Types.VARCHAR,voSample.getSampleQnty());
				strProc.addInParameter(45,Types.VARCHAR,voSample.getDefaultContainerCode());
				strProc.addInParameter(46,Types.VARCHAR,null);
				strProc.addInParameter(47,Types.VARCHAR,null);
				strProc.addInOutParameter(48,Types.VARCHAR ,"");
				
				strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			
		}
		// Commented By:- Anant Patel Dated:- 28th-May-2015
	   //  Reason:-Query Moved to Package'pkg_inv_dml.proc_insert_sample_dtl from properties file'
		/*public void insertSampleDtl(Inv_SampleCollectionVO voSample, UserVO _userVO) {

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
		
		// In use from 28th-May-2015 Added By:- Anant Patel Date:-28th-May-2015
		public void insertSampleDtl(Inv_SampleCollectionVO voSample, UserVO userVO)
		{
			String sampleNo=""; 
			String errorMsg="";
			ResultSet rs=null;
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_SAMPLE_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_MODE);
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
				strProc.addInParameter(22,Types.VARCHAR,(voSample.getTempSampleNo()==null?"0":voSample.getTempSampleNo()));
				strProc.addInOutParameter(23,Types.VARCHAR ,"");
				strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			
		}
		
		
		
		public boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample,UserVO _UserVO)
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
			
			populateMap.put(sq.next(), voSample.getTempSampleNo());
			
			//populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//rs.beforeFirst();
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
		
		
		
		//Check Auto Generation of Sample No 
		
	/*	
		public List<Inv_SampleCollectionVO> checkAutoGenFormate(Inv_SampleCollectionVO voSample,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST1";
			   
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
		}*/
		public List<Inv_SampleCollectionVO> getSampleCollAutoSampleNOConfig(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
			 
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
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getLabCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getTestCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				} 
				if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
				{
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getLabCode());
				//populateMAP.put(sq.next(),inv_SampleCollectionVO.getSampleAreaCode());
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				} 
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<Inv_SampleCollectionVO> listInv_SampleCollectionVO = new ArrayList<Inv_SampleCollectionVO>();
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
					valueObjects = HelperMethods.populateVOfrmRS(Inv_SampleCollectionVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						listInv_SampleCollectionVO.add((Inv_SampleCollectionVO)valueObjects[0]);
					//}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());	
				}
				else return listInv_SampleCollectionVO;
			}
			return listInv_SampleCollectionVO;
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
				  
 
		/*public void updateSampleCollInhivtsamplenoconfmst1(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
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
				*//**hivbl_list_status=?, updateSampleCollInhivtsamplenoconfmst1ResetLabNO
                                WHERE hivnum_packing_list_no=?
                     **//*
				if(voSamColl.getTempSampleNo().equals("1"))
				{
					populateMAP.put(sq.next(),finalDate);
					populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
				populateMAP.put(sq.next(),voSamColl.getLabCode());
				populateMAP.put(sq.next(),voSamColl.getTestCode());
				populateMAP.put(sq.next(),voSamColl.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				}
				
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				populateMAP.put(sq.next(),finalDate);
				populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
				populateMAP.put(sq.next(),voSamColl.getLabCode());
				populateMAP.put(sq.next(),voSamColl.getSampleAreaCode());
				populateMAP.put(sq.next(),voSamColl.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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

*/
		
		
	/*	public void updateSampleCollInhivtsamplenoconfmst1ResetLabNO(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
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
				*//**hivbl_list_status=?, updateSampleCollInhivtsamplenoconfmst1ResetLabNO
                                WHERE hivnum_packing_list_no=?
                     **//*
				if(voSamColl.getTempSampleNo().equals("1"))
				{
					 
				populateMAP.put(sq.next(),voSamColl.getLabCode());
				populateMAP.put(sq.next(),voSamColl.getTestCode());
				populateMAP.put(sq.next(),voSamColl.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				}
				
				if(voSamColl.getTempSampleNo().equals("2"))
				{
				 
				populateMAP.put(sq.next(),voSamColl.getLabCode());
				populateMAP.put(sq.next(),voSamColl.getSampleAreaCode());
				populateMAP.put(sq.next(),voSamColl.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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
		}*/

		/*public String checkAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
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
					populateMap.put(sq.next(),inv_SampleCollectionVO.getLabCode());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getTestCode());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getPatType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
				} 
				if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
				{
					populateMap.put(sq.next(),inv_SampleCollectionVO.getLabCode());
					populateMap.put(sq.next(),inv_SampleCollectionVO.getPatType());
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
		*/
		
		public  List<Inv_SampleCollectionVO> getSampleCollectionAreaWard(UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLLECTION_AREA_WARD.HIVT_COLLECTION_AREA_MST";
			Sequence sq = new Sequence();

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
	
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
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
		                throw new HisRecordNotFoundException("No Sample Collection Area Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
		                Inv_SampleCollectionVO InvSampleCollectionVO=null;
		                while (rs.next()) {
		                	InvSampleCollectionVO=new Inv_SampleCollectionVO();
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
		
		
		
		public  List<Inv_SampleCollectionVO> getBilledPatListForRaisingCumCollection(LabTestVO voLabTest,String reqType,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.BILLING_DETAILS.BILL_FUNCTION";
			Sequence sq = new Sequence();
			
			Inv_SampleCollectionVO voSampleCollection=null;

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			
			
							
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), voLabTest.getTestGroupType());
			populateMAP.put(sq.next(), voLabTest.getTestCode());
			populateMAP.put(sq.next(), voLabTest.getTestCode());
			populateMAP.put(sq.next(), voLabTest.getLabCode());
			populateMAP.put(sq.next(), voLabTest.getTestGroupCode());
			populateMAP.put(sq.next(), voLabTest.getLabCode());
			populateMAP.put(sq.next(), voLabTest.getTestGroupCode());
			populateMAP.put(sq.next(), voLabTest.getTestCode());
			populateMAP.put(sq.next(), reqType);
			populateMAP.put(sq.next(), voLabTest.getTestGroupType());
			populateMAP.put(sq.next(), voLabTest.getReqNo());
			
			
		/*	populateMAP.put(sq.next(), reqType);
			populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
			populateMAP.put(sq.next(), reqNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), reqdno);*/
			
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
		                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
		                while (rs.next()) {
		                	voSampleCollection=new Inv_SampleCollectionVO();
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
		
		public void insertSampleDtlRaisingCumCollection(LabTestVO voSample, UserVO userVO)
		{
			String sampleNo=""; 
			String errorMsg="";
			ResultSet rs=null;
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_SAMPLE_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_MODE);
				strProc.addInParameter(2,Types.VARCHAR,voSample.getSampleNo());
				strProc.addInParameter(3,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(4,Types.VARCHAR,voSample.getReqDno());
				strProc.addInParameter(5,Types.VARCHAR,null);
				strProc.addInParameter(6,Types.VARCHAR,voSample.getSampleAreaCode());
				strProc.addInParameter(7,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(8,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR,null);
				strProc.addInParameter(11,Types.VARCHAR,null);
				strProc.addInParameter(12,Types.VARCHAR,voSample.getPrintStatus());  
				strProc.addInParameter(13,Types.VARCHAR,voSample.getContainerVolume());
				strProc.addInParameter(14,Types.VARCHAR,voSample.getSampleCode());
				strProc.addInParameter(15,Types.VARCHAR,voSample.getUomCode());
				strProc.addInParameter(16,Types.VARCHAR,voSample.getContainerCode());
				strProc.addInParameter(17,Types.VARCHAR,voSample.getTempSampleNo());
				strProc.addInParameter(18,Types.VARCHAR,null);
				strProc.addInParameter(19,Types.VARCHAR,voSample.getTypeOfComponent());
				strProc.addInParameter(20,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(21,Types.VARCHAR,InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
				strProc.addInParameter(22,Types.VARCHAR,(voSample.getTempSampleNo()==null?"0":voSample.getTempSampleNo()));
				strProc.addInOutParameter(23,Types.VARCHAR ,"");
				strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			
		}
		
		
		
		public List<LabTestVO> checkAutoGenFormateRaisingCumCollection(LabTestVO voSample,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
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
			List<LabTestVO> listLabTestVO = new ArrayList<LabTestVO>();
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
					valueObjects = HelperMethods.populateVOfrmRS(LabTestVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					listLabTestVO.add((LabTestVO)valueObjects[0]);
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
		
		
		public void updateSampleCollInhivtsamplenoconfmst1ResetLabNORaisingCumCollection(LabTestVO voSamColl,String finalDate, UserVO _UserVO)
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
				/**hivbl_list_status=?, updateSampleCollInhivtsamplenoconfmst1ResetLabNO
                                WHERE hivnum_packing_list_no=?
                     **/
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
					populateMAP.put(sq.next(),voSamColl.getSampleAreaCode());
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

		
		public void updateSampleCollInhivtsamplenoconfmst1RaisingCumCollection(LabTestVO voSamColl,String finalDate, UserVO _UserVO)
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
					populateMAP.put(sq.next(),voSamColl.getSampleAreaCode());
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

		
		public String checkAutoGenFormateRunningLabNoRaisingCumCollection(LabTestVO inv_SampleCollectionVO, UserVO _UserVO)
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
					populateMap.put(sq.next(),inv_SampleCollectionVO.getSampleAreaCode());
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
		

		public String checkAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
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
		
		
		
		public void updateSampleCollInhivtsamplenoconfmst1ResetLabNO(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
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
				/**hivbl_list_status=?, updateSampleCollInhivtsamplenoconfmst1ResetLabNO
                                WHERE hivnum_packing_list_no=?
                     **/
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
		
		
		
		public void updateSampleCollInhivtsamplenoconfmst1(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
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

		
		public List<Inv_SampleCollectionVO> checkAutoGenFormate(Inv_SampleCollectionVO voSample,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
			
			if (voSample.getPatType()==null)
				voSample.setPatType("1");
			else if(voSample.getPatType().equals("3") || voSample.getPatType().equals("4"))
				voSample.setPatType("1");
			else
				;
			
			
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
					populateMap.put(sq.next(),voSample.getConfigArea());
					populateMap.put(sq.next(),voSample.getPatType());
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
		
		
}





