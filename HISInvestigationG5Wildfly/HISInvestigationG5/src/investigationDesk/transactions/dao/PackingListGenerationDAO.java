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
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
/*import hisglobal.vo.FormAReportVO;
import hisglobal.vo.FormCReportVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.QualityControlMstVO;
import hisglobal.vo.QualityParameterMappingVO;
import hisglobal.vo.RefreshmentItemMstVO;
import hisglobal.vo.ScreeningOfOtherTTDReportVO;
import hisglobal.vo.TherapeuticPatientDtlVO;*/
//import hisglobal.vo.TherapeuticTypeMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
//import hisglobal.vo.VoluntaryCardDtlVO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import investigationDesk.InvestigationConfig;
import investigationDesk.vo.Inv_SampleCollectionVO;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
import investigationDesk.vo.SampleAcceptanceVO;


public class PackingListGenerationDAO extends DataAccessObject //implements InvestigationEssentialDAOi
{
	public PackingListGenerationDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
		public List getLaboratoryCombo(Inv_SampleCollectionVO voSample,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List uomCombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.LAB_COMBO.HIVT_LABORATORY_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			populateMap.put(sq.next(), voSample.getSampleAreaCode());
			populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMap.put(sq.next(), _UserVO.getUserSeatId());
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
		
		public  List<Inv_SampleCollectionVO> getPackingListPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PACKINGLIST_PATIENT_LIST.HIVT_REQUISITION_DTL";
			Sequence sq = new Sequence();
			
			String condition1=" and HRGNUM_PUK= "+objSampleCollectionVO.getPatCRNo();
			
			String condition2=" and GNUM_LAB_CODE= "+objSampleCollectionVO.getLabCode();
			
			String orderBy=" order by hivnum_temp_sample_no,hivtnum_req_dno ";

			List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(),_UserVO.getSeatId());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			if(objSampleCollectionVO.getPackingListGenerationType().equals(InvestigationConfig.PACKING_LIST_GENERATION_TYPE_NORMAL)) //0
				populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST);  //3
			else
				populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE);  //4
			populateMAP.put(sq.next(), objSampleCollectionVO.getFromDate());
			populateMAP.put(sq.next(), objSampleCollectionVO.getToDate());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), objSampleCollectionVO.getSampleAreaCode());
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
				if(objSampleCollectionVO.getPatCRNo()!=null&&!objSampleCollectionVO.getPatCRNo().equals(""))
				{
					query+=condition1;
				}
				if(!objSampleCollectionVO.getLabCode().equals("%"))
					query+=condition2;
				
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
		
		public String generatePackingListNoSequence(String labCode,UserVO userVO)
		{
			String sequence_Hash_yyymmdd=""; 
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.PACKINGLISTNO.SYS_PACKINGLIST_MAINTAINER";
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
				populateMap.put(sq.next(), labCode);
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
					sequence_Hash_yyymmdd=rs.getString(1)+"#"+rs.getString(2);
					
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
		
		public void insertPackingListNoSequenceInMaintainer(String labCode,String sequence,String yymmdd, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.PACKINGLISTNO.SYS_PACKINGLIST_MAINTAINER";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),labCode);
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
				throw new HisDataAccessException("Exception While insertion in SYS_PATACC_MAINTAINER Table");
			}

		}
		
		public void updatePackingListNoSequenceInMaintainer(String sequence,String labCode,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PACKINGLISTNO.SYS_PACKINGLIST_MAINTAINER";
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
		
		public void updateRequisitionDtl(Inv_SampleCollectionVO voSample,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PACKINGLISTNO.HIVT_REQUISITION_DTLS";
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
				populateMAP.put(sq.next(),voSample.getPackingListNo());
				populateMAP.put(sq.next(),_UserVO.getSeatId());
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
		}
		
		public void updateSampleDtl(Inv_SampleCollectionVO voSample,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PACKINGLISTNO.HIVT_SAMPLE_DTL";
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
				populateMAP.put(sq.next(),voSample.getSampleStatus());
				populateMAP.put(sq.next(),voSample.getPackingListNo());
				populateMAP.put(sq.next(), voSample.getSampleNo());
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
		
		public void insertPackingListDtl(Inv_SampleCollectionVO voSample, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.PACKINGLISTDTLS.HIVT_PACKING_LIST_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),voSample.getPackingListNo());
				populateMAP.put(sq.next(),voSample.getListStatus());
				populateMAP.put(sq.next(),_userVO.getSeatId());
				populateMAP.put(sq.next(),voSample.getIsPackingListOffline());
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
				throw new HisDataAccessException("Exception While insertion in HIVT_SAMPLE_DTL Table");
			}

		}
		
		public String getPackingListNo(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			String packingListNo=""; 
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.HIVNUM_PACKING_LIST_NO.HIVT_REQUISITION_DTL";
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
				populateMap.put(sq.next(), voSample.getRequisitionDNo());
				populateMap.put(sq.next(), userVO.getHospitalCode());
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("InvestigationEssentialDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("");
				}
				else
				{
					packingListNo=rs.getString(1);
					
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
			return packingListNo;
		}
		
		
		public List<Inv_SampleCollectionVO> getPackingListGenerationOnLoad(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PACKINGLIST_PATIENT_LIST.ONLOAD.HIVT_REQUISITION_DTL";
			 
			String orderBy=" order by hivnum_temp_sample_no,hivtnum_req_dno ";

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
			 
				//	populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
				//populateMAP.put(sq.next(),sampleAcceptanceVO.getFromDate());
				populateMAP.put(sq.next(),userVO.getHospitalCode());
				populateMAP.put(sq.next(),userVO.getHospitalCode());
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				populateMAP.put(sq.next(), userVO.getUserSeatId());
				populateMAP.put(sq.next(), userVO.getHospitalCode());
				//populateMAP.put(sq.next(),sampleAcceptanceVO.getToDate());
			 	 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<Inv_SampleCollectionVO> listSampleAcceptanceVO = new ArrayList<Inv_SampleCollectionVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				 query+=orderBy;
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
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						listSampleAcceptanceVO.add((Inv_SampleCollectionVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
			}
			return listSampleAcceptanceVO;
		}	
		
		
		
		//to get paking list details to generate dup list
		public List<SampleAcceptanceVO> getPackingListDetails(SampleAcceptanceVO packListDetailsVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PACKAGING_LIST_DETAILS.HIVT_REQUISITION_DTL";
			String condition1=" AND GNUM_LAB_CODE= "+packListDetailsVO.getLabCode(); 
			String orderCondition=" order by hivtdt_packinglist_date_time, hivtnum_req_dno";
			 // SAMPLE AREA CODE TO BE CHECKED TOO
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
			 
				//	populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
				// populateMAP.put(sq.next(),InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE); packing list should generate duplicate list irrespective of req dtl status
			 				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				
				populateMAP.put(sq.next(),packListDetailsVO.getFromDate());
				populateMAP.put(sq.next(),packListDetailsVO.getToDate());
			 	 
				//area code
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), packListDetailsVO.getSampleAreaCode());
				
				//labcode in
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				populateMAP.put(sq.next(), _UserVO.getUserSeatId());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<SampleAcceptanceVO> lstPackListDetail = new ArrayList<SampleAcceptanceVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				if(!packListDetailsVO.getLabCode().equals("%"))
					query+=condition1;
				
				query+=orderCondition;
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleAcceptanceVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstPackListDetail.add((SampleAcceptanceVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
			}
			return lstPackListDetail;
		}	
		 
		
}





