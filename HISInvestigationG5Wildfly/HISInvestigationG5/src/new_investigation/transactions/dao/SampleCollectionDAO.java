package new_investigation.transactions.dao;

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
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.codehaus.jettison.json.JSONObject;

import new_investigation.InvestigationConfig;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.LabTestVO;


public class SampleCollectionDAO extends DataAccessObject //implements InvestigationEssentialDAOi
{
	public SampleCollectionDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	

		public  List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST";
			Sequence sq = new Sequence();

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			populateMAP.put(sq.next(),  _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		//	populateMAP.put(sq.next(), _UserVO.getUserSeatId());
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
		
	//Added by prachi S
		public  List<Inv_SampleCollectionVO> getSampleCollectionAreaSeatBased(UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST_NEW";
			Sequence sq = new Sequence();

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			populateMAP.put(sq.next(),  _UserVO.getHospitalCode());
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
			String queryKeyIPDWARD = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER_IPD_WARD";
			Sequence sq = new Sequence();
			
			String iswardforccolection="";
			String wardcode="";
			String wardtype="";
			
			if(objSampleCollectionVO.getWardCode()==null)
			{
			 iswardforccolection=iswardforcollectionarea(objSampleCollectionVO.getSampleAreaCode(),_UserVO.getHospitalCode());
			
			 if(iswardforccolection!=null && !iswardforccolection.equals(""))
			{
		    
		     wardtype=iswardforccolection.split("#")[0];
			
			if(wardtype.equals("2"))
			{
				wardcode=iswardforccolection.split("#")[1];
				
			}
			
		   }
			
			}
			Date dateInstance = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateInstance);
			cal.add(Calendar.DATE, -1);
			Date date = cal.getTime();
			String dateBefore7Days = WebUTIL.getCustomisedSysDate(date, "dd-MMM-yyyy");
			//System.out.println("dateBefore7Days : "+dateBefore7Days);
		
			String condition1=" and HRGNUM_PUK= "+objSampleCollectionVO.getPatCRNo();
			//String condition2=" AND (TRUNC(hivdt_entry_date) >= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))  AND (TRUNC(hivdt_entry_date) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
			String condition2=" AND (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+dateBefore7Days+"\','DD-Mon-YYYY')))  AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
				String condition3="and  (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getFromDate()+"\','DD-Mon-YYYY'))) AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getToDate()+"\','DD-Mon-YYYY')))";
				String condition4="and  EXISTS ( SELECT 1 FROM HIVT_COLLECTION_AREA_MST z WHERE z.gnum_area_code ="+objSampleCollectionVO.getSampleAreaCode()+"  and z.hipnum_ward_code=A.hipnum_wardcode and z.gnum_hospital_code=a.gnum_hospital_code and gnum_isvalid=1)";
	String orderBy=" order by hivdt_entry_date desc";
				List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(),_UserVO.getSeatId());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//put(sq.next(), objSampleCollectionVO.getFromDate());
			//populateMAP.put(sq.next(), objSampleCollectionVO.getToDate());
			if(objSampleCollectionVO.getWardCode()==null)
			{
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			populateMAP.put(sq.next(), objSampleCollectionVO.getSampleAreaCode());
			}
			else
			{
			populateMAP.put(sq.next(),objSampleCollectionVO.getSampleAreaCode());
			}
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
			/*populateMAP.put(sq.next(), InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS);*/
			try
			{
				
				if(objSampleCollectionVO.getWardCode()==null )
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				else
					query = HelperMethodsDAO.getQuery(filename, queryKeyIPDWARD);		
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				if(objSampleCollectionVO.getPatCRNo()!=null&&!objSampleCollectionVO.getPatCRNo().equals(""))
				{
					/*PRASHANT SMC deisabled in case of cr no. input given from to date is not need*/
					//if(objSampleCollectionVO.getFromDate()!=null)
					//query+=condition3;
					
					query+=condition1;
				}
				else if(objSampleCollectionVO.getFromDate()!=null&&!objSampleCollectionVO.getFromDate().equals("")&&objSampleCollectionVO.getToDate()!=null&&!objSampleCollectionVO.getToDate().equals(""))
				{
					query+=condition3;
				}
				else
				{
					 query+=condition2;
				}
				
				if(wardtype.equals("2") )
				{
					
					query+=condition4;
				}
				
				if(objSampleCollectionVO.getWardCode()!=null)
				{
					
					query+=" and a.HIPNUM_WARDCODE="+objSampleCollectionVO.getWardCode()+" ";

				}
				/*
				if(objSampleCollectionVO.getWardCode()!=null && !objSampleCollectionVO.getWardCode().equals(""))
				{
					
					query+=condition4;
				}*/
				query+=orderBy;
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
		                    //added by krishnan nema on 26-02-2019
		                    int billStatus = 0; //1 for not billed, 2 for partially billed, 3 for fully billed
		                    if(InvSampleCollectionVO.getIsBilled()!=null && InvSampleCollectionVO.getIsBilled().contains("##")){
		                    	int billedTestCount = 0;
	                    		int unbilledTestCount = 0;
		                    	String billArray[] = InvSampleCollectionVO.getIsBilled().split("##");
		                    	for(int p = 0; p<billArray.length; p++){
		                    		String billNo = billArray[p].replace("^", "#").split("#")[0];
		                    		if(billNo!=null && !billNo.equals("0")){
		                    			billedTestCount++;
		                    		}else{
		                    			unbilledTestCount++;
		                    		}
		                    	}
		                    	
		                    	if(billedTestCount == billArray.length){
		                    		billStatus = 3;
		                    	}
		                    	if(billedTestCount > 0 && billedTestCount < billArray.length){
		                    		billStatus = 2;
		                    	}
		                    	if(unbilledTestCount == billArray.length){
		                    		billStatus = 1;
		                    	}
		                    }else{
		                    	if(InvSampleCollectionVO.getIsBilled()!=null)
		                    	{
		                    	String billNo = InvSampleCollectionVO.getIsBilled().replace("^", "#").split("#")[0];
		                    	if(billNo!=null && !billNo.equals("0")){
		                    		billStatus = 3;
		                    	}else{
		                    		billStatus = 1;
		                    	}
		                    }
		                    }
		                    	
		                    if(billStatus == 1){
		                    	InvSampleCollectionVO.setBillDtl("Not Billed");
		                    }else if(billStatus == 2){
		                    	InvSampleCollectionVO.setBillDtl("Patially Billed");
		                    }else if(billStatus == 3){
		                    	InvSampleCollectionVO.setBillDtl("Billed");
		                    }
		                    
		                    
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
				e.printStackTrace();
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

			//	throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstInvSampleCollectionVO;

		}
		
		public List<String> getStaffDetails(String crNo,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.STAFF_DETAILS_LIST.HRGT_PATIENT_ID_DTL";
			Sequence sq = new Sequence();
			
			Inv_SampleCollectionVO voSampleCollection=null;

			List<String> staffDetails =null;
			
			populateMAP.put(sq.next(), crNo);
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
		                //throw new HisRecordNotFoundException("No Staff Details Found");
		         }
		         else
		         {
		                rs.beforeFirst();
		                staffDetails=new ArrayList<String>();
		                while (rs.next()){
		                	//System.out.println("rs.getString(1) : "+rs.getString(1)+" rs.getString(2) : "+rs.getString(2)+" rs.getString(3) : "+rs.getString(3));
		                	staffDetails.add(rs.getString(1));
		                	staffDetails.add(rs.getString(2));
		                	staffDetails.add(rs.getString(3));
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
			return staffDetails;

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
			
			System.out.println("calling generateSampleNoSequence");
			
			try
			{
				
				System.out.println("start calling generateSampleNoSequence");

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
						"HelperMethodsDAO.loadPropertiesFile(filename) OR getting query out of property file"
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
				strProc.addInParameter(35,Types.VARCHAR,voSample.getDefaultmachineCode());
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
				strProc.addInParameter(49,Types.VARCHAR ,null);

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
				Connection conn=super.getTransactionContext().getConnection();
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_SAMPLE_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.SAMPLE_COLLECTION_MODE);
				strProc.addInParameter(2,Types.VARCHAR,voSample.getSampleNo());
				strProc.addInParameter(3,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(4,Types.VARCHAR,voSample.getRequisitionDNo());
				strProc.addInParameter(5,Types.VARCHAR,"");
				strProc.addInParameter(6,Types.VARCHAR,voSample.getSampleAreaCode());
				strProc.addInParameter(7,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(8,Types.VARCHAR,userVO.getSeatId());
				strProc.addInParameter(9,Types.VARCHAR,"");
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
				strProc.execute(conn);
			
			}
				
			//	int nProcedureIndex;
				//String strDBErr;
				/*try
				{
					
					HisDAO hisDAO_p=new HisDAO("sampleCollection", "sampleCollectionDAO"); 
					
					nProcedureIndex = hisDAO_p.setProcedure(InvestigationConfig.PROCEDURE_INSERT_SAMPLE_DTL_HMODE_1);
*/
					// Setting and Registering In and Out Parameters 
				/*	hisDAO_p.setProcInValue(nProcedureIndex, "hmode", InvestigationConfig.SAMPLE_COLLECTION_MODE,1);
					hisDAO_p.setProcInValue(nProcedureIndex, "sampleno", voSample.getSampleNo(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "hcode", userVO.getHospitalCode(),3);
					hisDAO_p.setProcInValue(nProcedureIndex, "reqdno",voSample.getRequisitionDNo(),4);
					hisDAO_p.setProcInValue(nProcedureIndex, "packing_list_no", null,5);
					hisDAO_p.setProcInValue(nProcedureIndex, "sample_col_area_code", voSample.getSampleAreaCode(),6);
					hisDAO_p.setProcInValue(nProcedureIndex, "collection_seatid", userVO.getSeatId(),7);
					hisDAO_p.setProcInValue(nProcedureIndex, "acceptance_seatid",userVO.getSeatId(),8);
					hisDAO_p.setProcInValue(nProcedureIndex, "sam_rejection_saetid", null,9);
					hisDAO_p.setProcInValue(nProcedureIndex, "samp_rejection_action", null,10);
					hisDAO_p.setProcInValue(nProcedureIndex, "sam_rejection_reason", null,11);
					hisDAO_p.setProcInValue(nProcedureIndex, "printstatus",voSample.getPrintStatus(),12);
					hisDAO_p.setProcInValue(nProcedureIndex, "collected_vol", voSample.getSampleQnty(),13);
					hisDAO_p.setProcInValue(nProcedureIndex, "samplecode", voSample.getSampleCode(),14);
					hisDAO_p.setProcInValue(nProcedureIndex, "uom_code",voSample.getDefaultUOMCode(),15);
					hisDAO_p.setProcInValue(nProcedureIndex, "container_code", voSample.getDefaultContainerCode(),16);
					hisDAO_p.setProcInValue(nProcedureIndex, "daily_no", voSample.getTempSampleNo(),17);
					hisDAO_p.setProcInValue(nProcedureIndex, "accession_no", null,18);
					hisDAO_p.setProcInValue(nProcedureIndex, "typeofcomponent", voSample.getTypeOfComponent(),19);
					hisDAO_p.setProcInValue(nProcedureIndex, "seatid", userVO.getSeatId(),20);
					hisDAO_p.setProcInValue(nProcedureIndex, "sam_status_code",(voSample.getTempSampleNo()==null?"0":voSample.getTempSampleNo()),21);
					hisDAO_p.setProcInValue(nProcedureIndex, "temp_sample_no", (voSample.getTempSampleNo()==null?"0":voSample.getTempSampleNo()),22);
							
					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,23);*/ // varchar

					// Executing Procedure 
					//hisDAO_p.executeProcedureByPosition(nProcedureIndex);

					// Getting out parameters not in case of Batch Processing 
//					strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		//
//					// If Database Error Occurs, No farther processing is required. 
//					if (strDBErr != null && !strDBErr.equals(""))
//					{
//						throw new Exception("Data Base Error:" + strDBErr);
//					}
			//	}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new HisDataAccessException("samplecoll.dml()::hisDAO_p.execute" + InvestigationConfig.PROCEDURE_INSERT_SAMPLE_DTL 
							+ ") -> " + e.getMessage());
				}

				
				
				
				
		//}
			/*catch (HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}*/
			
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
				voSample.setPatType("2");;
			
			
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
		public List<Inv_SampleCollectionVO> getSampleCollAutoSampleNOConfig(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1";
			String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST";
			   
			if (inv_SampleCollectionVO.getPatType()==null)
				inv_SampleCollectionVO.setPatType("1");
			else if(inv_SampleCollectionVO.getPatType().equals("3") || inv_SampleCollectionVO.getPatType().equals("4"))
				inv_SampleCollectionVO.setPatType("1");
			else
				;
			
			
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
				populateMAP.put(sq.next(),inv_SampleCollectionVO.getConfigArea());
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



public void updateRequisitionDtlRaiseCumColl(LabTestVO voSample,UserVO userVO)
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
		strProc.addInParameter(10,Types.VARCHAR,"3");
		strProc.addInParameter(11,Types.VARCHAR,userVO.getSeatId());
		strProc.addInParameter(12,Types.VARCHAR,null);
		strProc.addInParameter(13,Types.VARCHAR,voSample.getTempSampleNo());
		strProc.addInParameter(14,Types.VARCHAR,null);
		strProc.addInParameter(15,Types.VARCHAR,null);
		strProc.addInParameter(16,Types.VARCHAR,null);
		strProc.addInParameter(17,Types.VARCHAR,"0");//bill no
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
		strProc.addInParameter(43,Types.VARCHAR,voSample.getUomCode());
		strProc.addInParameter(44,Types.VARCHAR,voSample.getSampleQnty());
		strProc.addInParameter(45,Types.VARCHAR,voSample.getContainerCode());
		strProc.addInParameter(46,Types.VARCHAR,null);
		strProc.addInParameter(47,Types.VARCHAR,null);
		strProc.addInOutParameter(48,Types.VARCHAR ,"");
		strProc.addInParameter(49,Types.VARCHAR ,null);
		strProc.execute(super.getTransactionContext().getConnection());
		

	}
	catch (HisRecordNotFoundException e)
	{
		throw new HisRecordNotFoundException("No Record Found");
	}
	
}



public  List<Inv_SampleCollectionVO> getPatListBarcode(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO)
{
	String query = "";		
	Map populateMAP = new HashMap();
	ResultSet rs = null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER_BARCODE";
	Sequence sq = new Sequence();
	
	String condition1=" and HRGNUM_PUK= "+objSampleCollectionVO.getPatCRNo();
	 String condition2=" AND (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))  AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY'))) ";
		String condition3="and  (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getFromDate()+"\','DD-Mon-YYYY'))) AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getToDate()+"\','DD-Mon-YYYY'))) ";
		String condition4="and  EXISTS ( SELECT 1 FROM HIVT_COLLECTION_AREA_MST z WHERE z.gnum_area_code ="+objSampleCollectionVO.getSampleAreaCode()+"  and z.hipnum_ward_code=A.hipnum_wardcode and z.gnum_hospital_code=a.gnum_hospital_code and gnum_isvalid=1)";

		
		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO1 =null;

	//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	//populateMAP.put(sq.next(),_UserVO.getSeatId());
	//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
String iswardforccolection=iswardforcollectionarea(objSampleCollectionVO.getSampleAreaCode(),_UserVO.getHospitalCode());

String wardtype=iswardforccolection.split("#")[0];
String wardcode="";
if(wardtype.equals("2"))
{
	wardcode=iswardforccolection.split("#")[1];
	
}

	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	//put(sq.next(), objSampleCollectionVO.getFromDate());
	//populateMAP.put(sq.next(), objSampleCollectionVO.getToDate());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	//populateMAP.put(sq.next(), objSampleCollectionVO.getSampleAreaCode());
	populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
	populateMAP.put(sq.next(), _UserVO.getUserSeatId());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST);
	populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE);
	populateMAP.put(sq.next(), "6");
	populateMAP.put(sq.next(), "17");

	
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
		if(objSampleCollectionVO.getPatCRNo()!=null&&!objSampleCollectionVO.getPatCRNo().equals(""))
		{
			/*PRASHANT SMC deisabled in case of cr no. input given from to date is not need*/
			//query+=condition3+condition1+"";
			query+=condition1+"";
		}
		else if(objSampleCollectionVO.getFromDate()!=null&&!objSampleCollectionVO.getFromDate().equals("")&&objSampleCollectionVO.getToDate()!=null&&!objSampleCollectionVO.getToDate().equals(""))
		{
			query+=condition3;
		}
		else
		{
			 query+=condition2;
		}
		
		if(wardtype.equals("2"))
		{
			
			query+=condition4;
		}
		
		query+="order by hivdt_entry_date desc";
		
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

		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("" + e);
	}
	
	Map<String,Inv_SampleCollectionVO> mpp=new LinkedHashMap<String,Inv_SampleCollectionVO>();
	
	List<LabTestVO> sugartest=gettestforsugar();
	List<LabTestVO> machinetest=gettestformachine();

	
	
	
	if(lstInvSampleCollectionVO!=null && lstInvSampleCollectionVO.size()>0)
	{
		lstInvSampleCollectionVO1=new ArrayList<Inv_SampleCollectionVO>();
		String key="";
		
		
		for(int k=0;k<lstInvSampleCollectionVO.size();k++)
		{
			Inv_SampleCollectionVO voo=lstInvSampleCollectionVO.get(k);
		
			String newkey=voo.getRequisitionNo()+"#"+voo.getTempSampleNo();
			boolean flg=false;
			
			if(sugartest!=null && sugartest.size()>0)
			{
				for(int k1=0;k1<sugartest.size();k1++)
				{
					LabTestVO vo=sugartest.get(k1);
					
					if(vo.getTestCode().equals(voo.getTestCode()))
					{
						newkey=voo.getRequisitionNo()+"#"+voo.getTempSampleNo()+vo.getSugartestcode();
						voo.setSugarTestCode(vo.getSugartestcode());
						mpp.put(newkey, voo);
						flg=true;
					}
				}
				
			}
			
			
				/*
				 * if(machinetest!=null && machinetest.size()>0) { for(int
				 * k1=0;k1<machinetest.size();k1++) { LabTestVO vo=machinetest.get(k1);
				 * 
				 * if(vo.getTestCode().equals(voo.getTestCode())) {
				 * newkey=voo.getRequisitionNo()+"#"+voo.getTempSampleNo()+"#"+vo.
				 * getSugartestcode(); voo.setSugarTestCode(vo.getSugartestcode());
				 * voo.setTestName(vo.getTestName()); mpp.put(newkey, voo); flg=true; } }
				 * 
				 * }
				 */
			
			           //added by chandan for adding machine symbol 22-jan-2020
		                  	boolean fl=false;
					if(machinetest!=null && machinetest.size()>0)
					{
						for(int k1=0;k1<machinetest.size();k1++)
						{
							LabTestVO vo=machinetest.get(k1);
							
							newkey=voo.getRequisitionNo()+"#"+voo.getTempSampleNo();
							
							if(mpp.containsKey(newkey))
							{
								Inv_SampleCollectionVO voadd=(Inv_SampleCollectionVO)mpp.get(newkey);
								String machie_symnbol="";
								
								if(vo.getTestCode().equals(voo.getTestCode()))
								{
								if(voadd.getSugarTestCode()!=null && !voadd.getSugarTestCode().equals(""))
								{
									machie_symnbol=voadd.getSugarTestCode() ;
								
								      if(!machie_symnbol.contains(vo.getSugartestcode()))
								    voo.setSugarTestCode(machie_symnbol+","+vo.getSugartestcode());
								      else		
								    	  voo.setSugarTestCode(machie_symnbol); 
								
								      mpp.put(newkey, voo);
								
								}
								else
								{
									  voo.setSugarTestCode(vo.getSugartestcode()); 
										
								      mpp.put(newkey, voo);
								}
								
								}
								flg=true;
								//break;
							}
							else
							{
								if(vo.getTestCode().equals(voo.getTestCode()))
								{
								if(vo.getSugartestcode()!=null && !vo.getSugartestcode().equals(""))
		                        voo.setSugarTestCode(vo.getSugartestcode());
								}
								
								mpp.put(newkey, voo);
								flg=true;
								//break;
							}
							
							
							
						}
						
					}
			
			
			if(flg==false)
			{
				newkey=voo.getRequisitionNo()+"#"+voo.getTempSampleNo()+"#"+"";
			
				mpp.put(newkey, voo);
			}
			
			/*if(key.equals(voo.getRequisitionNo()+"#"+voo.getTempSampleNo()))
			{
			
			}
			else
			{
				 key=voo.getRequisitionNo()+"#"+voo.getTempSampleNo();	
				 lstInvSampleCollectionVO1.add(voo);
			}*/
			
		}
		
	}

	
	  if(mpp!=null && mpp.size()>0)
	  {
		  Iterator itr=mpp.keySet().iterator();
		  
		  while(itr.hasNext())//for(int i=0;i<size;i++)
			{
			  
				String keys=(String)itr.next();
				Inv_SampleCollectionVO voo=mpp.get(keys);
				lstInvSampleCollectionVO1.add(voo);
			}
		  
	  }
	  
	  
	  
	 
	  
	  
	  
	
	return lstInvSampleCollectionVO1;

}




public List getmachineCombo(UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List containerCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.MACHINE_COMBO.HMIT_MACHINE_MST";
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMap.put(sq.next(), _UserVO.getHospitalCode());
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



public String iswardforcollectionarea(String collareacode,String hospitalcode)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String Formate="";
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.WARDCODE.HIVT_COLLECTION_AREA_MST";
	///String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.AJAX.CHECK.HIVT_COLAREA_SAMNO_CONFIG_MST";
	   
	Connection conn=super.getTransactionContext().getConnection();
	try
	{
		
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	try
	{
		
			populateMap.put(sq.next(),collareacode);
			populateMap.put(sq.next(),hospitalcode);
		

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




public   String getcollectionareafromward(String wardcode,String hospitalcode,String seatid)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String Formate="";
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.COLLECTIONAREACODE.HIVT_COLLECTION_AREA_MST";

	Connection conn=super.getTransactionContext().getConnection();
	try
	{
		
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	try
	{
		
			populateMap.put(sq.next(),hospitalcode);
			populateMap.put(sq.next(),hospitalcode);
			populateMap.put(sq.next(),seatid);
			populateMap.put(sq.next(),wardcode);
		

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


///added by krishnan nema on 25042019
public  List<Inv_SampleCollectionVO> getPatListSampleColAdvance(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO)
{
	String query = "";		
	Map populateMAP = new HashMap();
	ResultSet rs = null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER";
	String queryKeyIPDWARD = "SELECT.SAMPLE_PATIENT_LIST.HIVT_REQUISITION_HEADER_IPD_WARD";
	Sequence sq = new Sequence();
	
	String iswardforccolection="";
	String wardcode="";
	String wardtype="";
	
	if(objSampleCollectionVO.getWardCode()==null)
	{
	 iswardforccolection=iswardforcollectionarea(objSampleCollectionVO.getSampleAreaCode(),_UserVO.getHospitalCode());
	
	 if(iswardforccolection!=null && !iswardforccolection.equals(""))
	{
    
     wardtype=iswardforccolection.split("#")[0];
     
	
	if(wardtype.equals("2"))
	{
		wardcode=iswardforccolection.split("#")[1];
		
	}
	}
	
	}
	Date dateInstance = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(dateInstance);
	cal.add(Calendar.DATE, -7);
	Date date = cal.getTime();
	String dateBefore7Days = WebUTIL.getCustomisedSysDate(date, "dd-MMM-yyyy");
	//System.out.println("dateBefore7Days : "+dateBefore7Days);

	String condition1=" and HRGNUM_PUK= "+objSampleCollectionVO.getPatCRNo();
	//String condition2=" AND (TRUNC(hivdt_entry_date) >= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))  AND (TRUNC(hivdt_entry_date) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
//	String condition2=" AND (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+dateBefore7Days+"\','DD-Mon-YYYY')))  AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
	String condition2=" AND (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))  AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(SYSDATE,'DD-Mon-YYYY')))";
		String condition3="and  (TRUNC(HIVDT_REQUISITION_DATE) >= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getFromDate()+"\','DD-Mon-YYYY'))) AND (TRUNC(HIVDT_REQUISITION_DATE) <= TRUNC(TO_DATE(\'"+objSampleCollectionVO.getToDate()+"\','DD-Mon-YYYY')))";
		String condition4="and  EXISTS ( SELECT 1 FROM HIVT_COLLECTION_AREA_MST z WHERE z.gnum_area_code ="+objSampleCollectionVO.getSampleAreaCode()+"  and z.hipnum_ward_code=A.hipnum_wardcode and z.gnum_hospital_code=a.gnum_hospital_code and gnum_isvalid=1)";
String orderBy=" order by hivdt_entry_date desc";
		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
	//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	//populateMAP.put(sq.next(),_UserVO.getSeatId());
	//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	//put(sq.next(), objSampleCollectionVO.getFromDate());
	//populateMAP.put(sq.next(), objSampleCollectionVO.getToDate());
	if(objSampleCollectionVO.getWardCode()==null)
	{
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	populateMAP.put(sq.next(), objSampleCollectionVO.getSampleAreaCode());
	}
	populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
	populateMAP.put(sq.next(), _UserVO.getUserSeatId());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
	/*populateMAP.put(sq.next(), InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS);*/
	try
	{
		
		if(objSampleCollectionVO.getWardCode()==null )
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		else
			query = HelperMethodsDAO.getQuery(filename, queryKeyIPDWARD);		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
		if(objSampleCollectionVO.getPatCRNo()!=null&&!objSampleCollectionVO.getPatCRNo().equals(""))
		{
			//if(objSampleCollectionVO.getFromDate()!=null)
			//query+=condition3;
			
			query+=condition1;
		}
		else if(objSampleCollectionVO.getFromDate()!=null&&!objSampleCollectionVO.getFromDate().equals("")&&objSampleCollectionVO.getToDate()!=null&&!objSampleCollectionVO.getToDate().equals(""))
		{
			query+=condition3;
		}
		else
		{
			 query+=condition2;
		}
		
		if(wardtype.equals("2") )
		{
			
			query+=condition4;
		}
		
		if(objSampleCollectionVO.getWardCode()!=null)
		{
			
			query+=" and a.HIPNUM_WARDCODE="+objSampleCollectionVO.getWardCode()+" ";

		}
		/*
		if(objSampleCollectionVO.getWardCode()!=null && !objSampleCollectionVO.getWardCode().equals(""))
		{
			
			query+=condition4;
		}*/
		query+=orderBy;
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
                    //added by krishnan nema on 26-02-2019
                    int billStatus = 0; //1 for not billed, 2 for partially billed, 3 for fully billed
                    if(InvSampleCollectionVO.getIsBilled().contains("##")){
                    	int billedTestCount = 0;
                		int unbilledTestCount = 0;
                    	String billArray[] = InvSampleCollectionVO.getIsBilled().split("##");
                    	for(int p = 0; p<billArray.length; p++){
                    		String billNo = billArray[p].replace("^", "#").split("#")[0];
                    		if(billNo!=null && !billNo.equals("0")){
                    			billedTestCount++;
                    		}else{
                    			unbilledTestCount++;
                    		}
                    	}
                    	
                    	if(billedTestCount == billArray.length){
                    		billStatus = 3;
                    	}
                    	if(billedTestCount > 0 && billedTestCount < billArray.length){
                    		billStatus = 2;
                    	}
                    	if(unbilledTestCount == billArray.length){
                    		billStatus = 1;
                    	}
                    }else{
                    	String billNo = InvSampleCollectionVO.getIsBilled().replace("^", "#").split("#")[0];
                    	if(billNo!=null && !billNo.equals("0")){
                    		billStatus = 3;
                    	}else{
                    		billStatus = 1;
                    	}
                    }
                    	
                    if(billStatus == 1){
                    	InvSampleCollectionVO.setBillDtl("Not Billed");
                    }else if(billStatus == 2){
                    	InvSampleCollectionVO.setBillDtl("Patially Billed");
                    }else if(billStatus == 3){
                    	InvSampleCollectionVO.setBillDtl("Billed");
                    }
                    
                    
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




public List<LabTestVO> gettestforsugar()
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List<String> Formate=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.SUGAR_TEST";
	   
	Connection conn=super.getTransactionContext().getConnection();
	try
	{
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	try
	{
		
		
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
			for (int i = 0; i < valueObjects.length; i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				
			listLabTestVO.add((LabTestVO)valueObjects[i]);
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
	return listLabTestVO;
}



public List<LabTestVO> gettestformachine()
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List<String> Formate=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.MACHINE_TEST";
	   
	Connection conn=super.getTransactionContext().getConnection();
	try
	{
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	try
	{
		
		
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
			for (int i = 0; i < valueObjects.length; i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				
			listLabTestVO.add((LabTestVO)valueObjects[i]);
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
	return listLabTestVO;
}





public List<Inv_SampleCollectionVO> getSampleCollAutoSampleNOConfigtestwise(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
{
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_SAMPLENO_CONF_MST1_TESTWISE";
	String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.HIVT_COLAREA_SAMNO_CONFIG_MST_TESTWISE";
	   
	if (inv_SampleCollectionVO.getPatType()==null)
		inv_SampleCollectionVO.setPatType("1");
	else if(inv_SampleCollectionVO.getPatType().equals("3") || inv_SampleCollectionVO.getPatType().equals("4"))
		inv_SampleCollectionVO.setPatType("1");
	else
		;
	
	
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
		populateMAP.put(sq.next(),inv_SampleCollectionVO.getConfigArea());
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




public String getsamplebarcodeconfig(UserVO userVO)
{
	String config="0"; 
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.ISSAMPLEBARCODE.DUPLICATE.PRINT.HIVT_REPORT_DB_MST";
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
		 
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
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
			config=rs.getString(1);
		
			if(config==null || config.equals(""))
			{
				config="0";
			}
			
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
	return config;
}





public List<Inv_SampleCollectionVO> checkAutoGenFormateDATEWISE(Inv_SampleCollectionVO voSample,UserVO _UserVO) // first time null ajax issue added by chandan on 22oct2019
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
	List<Inv_SampleCollectionVO> listLabTestVO = new ArrayList<Inv_SampleCollectionVO>();
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
			listLabTestVO.add((Inv_SampleCollectionVO)valueObjects[0]);
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


public String isAutoGenFormateRunningLabNofree(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String Formate="";
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.AJAX.CHECK.HIVT_SAMPLENO_CONF_MST1_ISSYNC";
	String queryKey1 = "SELECT.SAMPLE_COLL_DETAIL_AUTOGEN_SAMPLENO.AJAX.CHECK.HIVT_COLAREA_SAMNO_CONFIG_MST_ISYSNC";
	   
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



public String pkgcheckAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO) {
    String reqNo = "";
    String errorMsg = "";
    ResultSet rs = null;
    
    try {
      Procedure strProc = null;
     
      if(inv_SampleCollectionVO.getTempSampleNo().equals("1"))
		{
      strProc = new Procedure("pkg_inv_unique_sample_no_generation.generate_sampleno_conf_mst");
	
      strProc.addInParameter(1,12,inv_SampleCollectionVO.getConfigLab());
      strProc.addInParameter(2,12,inv_SampleCollectionVO.getConfigTest());
      strProc.addInParameter(3,12,inv_SampleCollectionVO.getConfigType());
      strProc.addInParameter(4,12,_UserVO.getHospitalCode());
      strProc.addInParameter(5,12,inv_SampleCollectionVO.getConfigSeq());
      strProc.setReturnType(12);
      
		}
      
      if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
		{
    	  
    	  strProc = new Procedure("pkg_inv_unique_sample_no_generation.generate_sampleno_coll_area_mst");
    		
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




public void updateSampleCollInhivtsamplenoconfmst1_issync(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
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



public String pkgcheckAutoGenFormateRunningLabNo_testwise(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO) {
    String reqNo = "";
    String errorMsg = "";
    ResultSet rs = null;
    
    try {
      Procedure strProc = null;
     
      if(inv_SampleCollectionVO.getTempSampleNo().equals("1"))
		{
      strProc = new Procedure("pkg_inv_unique_sample_no_generation.generate_sampleno_conf_mst_testwise");
	
      strProc.addInParameter(1,12,inv_SampleCollectionVO.getConfigLab());
      strProc.addInParameter(2,12,inv_SampleCollectionVO.getConfigTest());
      strProc.addInParameter(3,12,inv_SampleCollectionVO.getConfigType());
      strProc.addInParameter(4,12,_UserVO.getHospitalCode());
      strProc.addInParameter(5,12,inv_SampleCollectionVO.getConfigSeq());
      strProc.setReturnType(12);
      
		}
      
      if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
		{
    	  
    	  strProc = new Procedure("pkg_inv_unique_sample_no_generation.generate_sampleno_coll_area_mst_testwise");
    		
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




public  String checkAutoGenFormateRunningLabNo_isysnc(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO) {
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




public void updateSampleCollInhivtsamplenoconfmst1_issyncforsamesampleno(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.SAMPLECOllDETAIL.HIVT_SAMPLENO_CONF_MS_ISYSYNC_SAMESAMPLENO";
	String queryKey1 ="UPDATE.SAMPLECOLLDETAIL.HIVT_COLAREA_SAMNO_CONFIG_MST_ISYSYNC_SAMESAMPLENO";
	
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
		//populateMAP.put(sq.next(),finalDate);
		//populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
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




public  String checkAutoGenFormateRunningLabNo_New(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO) {
    String reqNo = "";
    String errorMsg = "";
    ResultSet rs = null;
    
    try {
      Procedure strProc = null;
     
      if(inv_SampleCollectionVO.getTempSampleNo().equals("1"))
		{
      strProc = new Procedure("pkg_inv_unique_sample_no_generation.getsamplenocongif");
	
      strProc.addInParameter(1,12,inv_SampleCollectionVO.getConfigLab());
      strProc.addInParameter(2,12,inv_SampleCollectionVO.getConfigTest());
      strProc.addInParameter(3,12,inv_SampleCollectionVO.getConfigType());
      strProc.addInParameter(4,12,_UserVO.getHospitalCode());
      strProc.addInParameter(5,12,inv_SampleCollectionVO.getConfigSeq());
      strProc.setReturnType(12);
      
		}
      
      if(inv_SampleCollectionVO.getTempSampleNo().equals("2"))
		{
    	  
    	  strProc = new Procedure("pkg_inv_unique_sample_no_generation.getsamplenocongif");
    		
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
      
    	e.printStackTrace();
      throw new HisRecordNotFoundException("No Record Found");
    } 
  }



public String getlabcongiNewSampleGene(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	String Formate="";
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.LABWISE.FORMAT.SAMPLENOGEN";
	String queryKey1 = "SELECT.LABWISE.FORMAT.SAMPLENOGEN_COLLAREA";

	Connection conn=super.getTransactionContext().getConnection();
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
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	try
	{
		if(voSamColl.getTempSampleNo().equals("1"))
		{
			//populateMAP.put(sq.next(),finalDate);
		//	populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
			
		populateMAP.put(sq.next(),voSamColl.getConfigLab());
		populateMAP.put(sq.next(),voSamColl.getConfigTest());
		populateMAP.put(sq.next(),voSamColl.getConfigType());
		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		populateMAP.put(sq.next(),voSamColl.getConfigSeq());


		}
		
		if(voSamColl.getTempSampleNo().equals("2"))
		{
		//populateMAP.put(sq.next(),finalDate);
	//	populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
		populateMAP.put(sq.next(),voSamColl.getConfigLab());
		populateMAP.put(sq.next(),voSamColl.getConfigArea());
		populateMAP.put(sq.next(),voSamColl.getConfigType());
		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		populateMAP.put(sq.next(),voSamColl.getConfigSeq());


		}
		
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
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





public String getTestcongiNewSampleGene(Inv_SampleCollectionVO voSamColl,String finalDate, UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	String Formate="";
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.TESTWISE.FORMAT.SAMPLENOGEN";
	//String queryKey1 = "SELECT.TESTWISE.FORMAT.SAMPLENOGEN";

	Connection conn=super.getTransactionContext().getConnection();
	try
	{
		
			/*
			 * if(voSamColl.getTempSampleNo().equals("1")) {
			 */
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		/*}
		if(voSamColl.getTempSampleNo().equals("2"))
		{
		
		query = HelperMethodsDAO.getQuery(filename, queryKey1);
		}*/
		
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	try
	{
			/*
			 * if(voSamColl.getTempSampleNo().equals("1")) {
			 */
		//	populateMAP.put(sq.next(),finalDate);
		//	populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
			
		populateMAP.put(sq.next(),voSamColl.getConfigLab());
		populateMAP.put(sq.next(),voSamColl.getConfigTest());
		populateMAP.put(sq.next(),voSamColl.getConfigType());
		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		populateMAP.put(sq.next(),voSamColl.getConfigSeq());


		/*}
		
		if(voSamColl.getTempSampleNo().equals("2"))
		{
		populateMAP.put(sq.next(),finalDate);
		populateMAP.put(sq.next(),voSamColl.getTemparorySampleNO());
		populateMAP.put(sq.next(),voSamColl.getConfigLab());
		populateMAP.put(sq.next(),voSamColl.getConfigArea());
		populateMAP.put(sq.next(),voSamColl.getConfigType());
		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		populateMAP.put(sq.next(),voSamColl.getConfigSeq());


		}*/
		
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
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

public Inv_SampleCollectionVO AjaxBilledUnbilledDetails(Inv_SampleCollectionVO vo, UserVO userVO) {
	
	
	String filename=InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO;
	String queryKey1="SELECT_INV_BILLED_UNBILLED_DTL_BASED_ON_CRNO";
			
	String query1="";


	ResultSet rs=null;
	Connection conn=super.getTransactionContext().getConnection();
	
	Map populateMap = new HashMap();
	Sequence sq = new Sequence();
	Inv_SampleCollectionVO vo2 = new Inv_SampleCollectionVO();
	
	try
	{
	
			query1 = HelperMethodsDAO.getQuery(filename, queryKey1);
		
	} 

	catch (Exception e) {
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	try
	{	
		
			populateMap.put(sq.next(), vo.getCrNo());

		rs = HelperMethodsDAO.executeQuery(conn, query1, populateMap);
		
		if(!rs.next()) { }
		else {
			rs.beforeFirst();
			while(rs.next()) {
				HelperMethods.populateVOfrmRS(vo2, rs);
			}
		}
		
		
	}
	
	catch (Exception e)
	{		 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	}
	return vo2;
}





}


