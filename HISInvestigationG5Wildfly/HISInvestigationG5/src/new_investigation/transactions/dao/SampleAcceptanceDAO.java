package new_investigation.transactions.dao;

/**
 * @author : C-DAC, Noida
 * Project : HISInvestigationG5
 * Module  : New_Investigation
 * Created On : 23-Feb-2015
 * 
 * Developed By : Pathan Basha
 * 
 * Purpose : This Class should be used for all Essential Data Access Data Layer Methods 
 * 			regarding all processes
 * 
 * Modified By:
 * 
 * Modified On: 
 */


import hisglobal.backutil.HisDAO;
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

import new_investigation.InvestigationConfig;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.SampleAcceptanceVO;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/

public class SampleAcceptanceDAO extends DataAccessObject implements SampleAcceptanceDAOi
{
	public SampleAcceptanceDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	 
		 
	
	//Lab Combo For Sample Acceptacne Process
	
	
	public List sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List sampleAcceptanceCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_SAMPLE_ACCEPTANCE_LAB_COMBO_HIVT_LABORATORY_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		populateMap.put(sq.next(),_UserVO.getUserSeatId());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
		 
		
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
				throw new HisRecordNotFoundException();
			}
			else
			{
				 
				rs.beforeFirst();
				 
				sampleAcceptanceCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return sampleAcceptanceCombo;
	}
	public List<SampleAcceptanceVO> getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.SAMPLE_ACCEPTANCE_PACKING_LIST_ON_LOAD_HIVT_REQUISITION_DTL";
		 
		 
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
		 
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			//labcode in
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
		//	populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
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
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(SampleAcceptanceVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[i]);
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
	
	public String getSampleAcceptanceDetailForCheckPackNo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		String count=""	;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.SAMPLE_ACCEPTANCE_PACKING_LIST_FOR_CHECK_PACKNO_HIVT_REQUISITION_DTL";
		 
		 
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
		 
			populateMAP.put(sq.next(),sampleAcceptanceVO.getPackingListNO());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return count;
	}	
	
	
	
	/*public List<SampleAcceptanceVO> getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.SAMPLE_ACCEPTANCE_PACKAGE_LIST_HIVT_REQUISITION_DTL";
		String condition1=" AND GNUM_LAB_CODE= "+sampleAcceptanceVO.getLabCode(); 
		String condition2=" AND gnum_machine_code= "+sampleAcceptanceVO.getMachineCodee(); 
		String condition3=" and (gnum_machine_code is null or gnum_machine_code=-1) " ; 

		 String orderBy=" order by hivtnum_req_dno";
		try
		{
			//query = HelperMethodsDAO.getQuery(filename, queryKey);
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
			
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			if(sampleAcceptanceVO.getAcceptance().equals("1"))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			populateMAP.put(sq.next(),InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE);
			}else
			{
				String queryKey1 = "SELECT.SAMPLE_ACCEPTANCE_PACKAGE_LIST_HIVT_REQUISITION_DTL_NON_ACC";
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
			populateMAP.put(sq.next(),InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY);
			}
			
			populateMAP.put(sq.next(),sampleAcceptanceVO.getFromDate());
			populateMAP.put(sq.next(),sampleAcceptanceVO.getToDate());
		 	 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		 
		List<SampleAcceptanceVO> listSampleAcceptanceVO = new ArrayList<SampleAcceptanceVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			if(!sampleAcceptanceVO.getLabCode().equals("%"))
				query+=condition1;
			
			if(sampleAcceptanceVO.getFlag().equals("1"))
			{
			    
				
				query+=condition2;
			}
			
			if(sampleAcceptanceVO.getFlag()==null || sampleAcceptanceVO.getFlag().equals("") || sampleAcceptanceVO.getFlag().equals("0") )
			{
				query+=condition3;
				
			}
			
			query+=orderBy;
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
					listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[i]);
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
	}	*/
	 
	
	public List<SampleAcceptanceVO> getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.SAMPLE_ACCEPTANCE_PACKAGE_LIST_HIVT_REQUISITION_DTL";
		
		String condition1=" AND GNUM_LAB_CODE= "+sampleAcceptanceVO.getLabCode(); 
		String condition2=" AND gnum_machine_code= "+sampleAcceptanceVO.getMachineCodee(); 
		String condition3=" and (gnum_machine_code is null or gnum_machine_code=-1) " ; 

		 String orderBy=" order by groupCode,machinename,hivtnum_req_dno";
		try
		{
			//query = HelperMethodsDAO.getQuery(filename, queryKey);
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
			
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			if(sampleAcceptanceVO.getAcceptance().equals("1"))
			{
				/*if(!(sampleAcceptanceVO.getSearchCrNo()==null || sampleAcceptanceVO.getSearchCrNo().equals(""))){
					query = HelperMethodsDAO.getQuery(filename, queryKeyCrNoWise);
				}else{*/
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				/*}*/
				
			populateMAP.put(sq.next(),InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE);
			}else
			{
				String queryKey1 = "SELECT.SAMPLE_ACCEPTANCE_PACKAGE_LIST_HIVT_REQUISITION_DTL_NON_ACC";
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
			populateMAP.put(sq.next(),InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY);
			}
			
			populateMAP.put(sq.next(),sampleAcceptanceVO.getFromDate());
			populateMAP.put(sq.next(),sampleAcceptanceVO.getToDate());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		 
		List<SampleAcceptanceVO> listSampleAcceptanceVO = new ArrayList<SampleAcceptanceVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			if(!sampleAcceptanceVO.getLabCode().equals("%"))
				query+=condition1;
			
			if(sampleAcceptanceVO.getFlag().equals("1"))
			{
			    
				
				query+=condition2;
			}
			
			/*if(sampleAcceptanceVO.getFlag()==null || sampleAcceptanceVO.getFlag().equals("") || sampleAcceptanceVO.getFlag().equals("0") )
			{
				query+=condition3;
				
			}*/
			
			query+=orderBy;
			
			if(!(sampleAcceptanceVO.getSearchCrNo()==null || sampleAcceptanceVO.getSearchCrNo().equals("")) && sampleAcceptanceVO.getSearchCrNo().length() == 15){
				query = "Select * from(" + query + ") where patCRNo = "+sampleAcceptanceVO.getSearchCrNo();
			}
			
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
					listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[i]);
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
	
	 
	
	public List getSampleCombo(String labCode,String testCode,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstSample=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABTESTSAMPLECOMBO.HIVT_LABTEST_SAMPLE_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), labCode);
		populateMap.put(sq.next(), testCode);
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
			//	throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstSample=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstSample;
	}
	
	
	public List getSampleAcceptanceRejectionCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.SAMPLE_ACCCOMBO_HIVT_REJECTIONREASON_MST";
		
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
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	 	
		public void updateSampleAccInRequisitionDtl(SampleAcceptanceVO voSamAcc,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey1 ="UPDATE.SAMPLEACCDETAIL.HIVT_REQUISITION_DTL";
            String queryKey2="UPDATE.SAMPLE_NOTACC_DETAIL.HIVT_REQUISITION_DTL";
            String queryKey3="UPDATE.SAMPLE_RESCHEDULED_ACCDETAIL.HIVT_REQUISITION_DTL";
            String queryKey="UPDATE.SAMPLE_RECIEVE_NO_DETAIL.HIVT_REQUISITION_DTL";
			
			try {
				 if(voSamAcc.getRecieved().equals(InvestigationConfig.RECIEVED_STATUS))//1
			 {
			    if(voSamAcc.getAccepted().equals(InvestigationConfig.ACCPETED_STATUS))//1
			    {
			    	 voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_UPDATE_ACCEPTED_STATUS); //6 	
			    	 
			    		
			    	 if(voSamAcc.getTestBasedMachine()!=null)
			    	 if(!voSamAcc.getTestBasedMachine().equals("-1"))
			    			voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_MACHINE_RESULT_ENTRY); //17
			    	 
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
			    }
			    else
			    {
			    	if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_CANCELLED_STATUS))//1
			    	{
			    		 voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_UPDATE_CANCELLED_STATUS); //11
			    		query = HelperMethodsDAO.getQuery(filename, queryKey2);
			    	}
			    		
			    	if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_RESCHEDULED_STATUS))//2
			    	{
			    		voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS); //12
			    		query = HelperMethodsDAO.getQuery(filename, queryKey3);
			    	}
			    	
			    }
			 }
				 else
				 {
					 voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_UPDATE_RECEIVED_NO_STATUS); //26
					 query = HelperMethodsDAO.getQuery(filename, queryKey);
				 }
				 
			 
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				 if(voSamAcc.getRecieved().equals(InvestigationConfig.RECIEVED_STATUS))//1
				 {
					
					 if(voSamAcc.getAccepted().equals(InvestigationConfig.ACCPETED_STATUS))//1
				    {
					
		         populateMAP.put(sq.next(),voSamAcc.getReqDtlStatus());
		         populateMAP.put(sq.next(),voSamAcc.getRecieved());
		         populateMAP.put(sq.next(),voSamAcc.getAccepted());		         
		         populateMAP.put(sq.next(),_userVO.getSeatId());
		         populateMAP.put(sq.next(),voSamAcc.getLabNoConfiguration());
		         
		         if(voSamAcc.getTestBasedMachine()!=null)
		        	 	populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
		         else
		        	   populateMAP.put(sq.next(),"-1");
//where
		         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
		         populateMAP.put(sq.next(),voSamAcc.getTestCode());
		         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
		         
				    }
				 else
				 {
					 if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_CANCELLED_STATUS))//1
				    	{
						
						 populateMAP.put(sq.next(),voSamAcc.getReqDtlStatus());
						 populateMAP.put(sq.next(),voSamAcc.getRecieved());
						 populateMAP.put(sq.next(),voSamAcc.getRejectionAction());
						 populateMAP.put(sq.next(),voSamAcc.getRejectionReason());
						 populateMAP.put(sq.next(),_userVO.getSeatId());
				         populateMAP.put(sq.next(),voSamAcc.getAccepted());
				         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
				         populateMAP.put(sq.next(),voSamAcc.getTestCode());
				         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
				    	}
				    		
					 if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_RESCHEDULED_STATUS))//2
				    	{
				    		
				    		populateMAP.put(sq.next(),voSamAcc.getReqDtlStatus());
				    		populateMAP.put(sq.next(),voSamAcc.getRecieved());
							 populateMAP.put(sq.next(),voSamAcc.getRejectionAction());
							 populateMAP.put(sq.next(),voSamAcc.getRejectionReason());
							 populateMAP.put(sq.next(),_userVO.getSeatId());
					         populateMAP.put(sq.next(),voSamAcc.getAccepted());
					         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
					         populateMAP.put(sq.next(),voSamAcc.getTestCode()); 
					         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
					         
				    	}
				 }
				 
			}
			 else
			 {
				
				 populateMAP.put(sq.next(),voSamAcc.getReqDtlStatus());
				 populateMAP.put(sq.next(),_userVO.getSeatId());
		         populateMAP.put(sq.next(),voSamAcc.getRecieved());	
		         populateMAP.put(sq.next(),voSamAcc.getAccepted());
		         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
		         populateMAP.put(sq.next(),voSamAcc.getTestCode());
		         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
			}
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
			}

		}
		
		 
		
		public void updateSampleAccInSampleDtl(SampleAcceptanceVO voSamAcc,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey1 ="UPDATE.SAMPLEACCDETAIL.HIVT_SAMPLE_DTL";
            String queryKey2="UPDATE.SAMPLE_NOTACC_DETAIL.HIVT_SAMPLE_DTL";
            String queryKey="UPDATE.SAMPLE_RECIEVE_NO_DETAIL.HIVT_SAMPLE_DTL";
			try {
				
				    if(voSamAcc.getAccepted().equals(InvestigationConfig.ACCPETED_STATUS))//1
				    {
				    	query = HelperMethodsDAO.getQuery(filename, queryKey1);
			         }
			         else
			         {
			    		query = HelperMethodsDAO.getQuery(filename, queryKey2);
			        }
		     	 
				 
				 
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				 
			 
					 if(voSamAcc.getAccepted().equals(InvestigationConfig.ACCPETED_STATUS))//1
					 {
				         populateMAP.put(sq.next(),_userVO.getSeatId());
				         populateMAP.put(sq.next(),voSamAcc.getLabNoConfiguration());
				         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
				         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
			         }
			 else
			        {
				     populateMAP.put(sq.next(),_userVO.getSeatId());
					 populateMAP.put(sq.next(),voSamAcc.getRejectionAction());
					 populateMAP.put(sq.next(),voSamAcc.getRejectionReason());
			        // populateMAP.put(sq.next(),voSamAcc.getLabNoConfiguration());
			         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
			         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
			        }
				 
				
				 
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
		
		public void updateInPackingListDtl(SampleAcceptanceVO voSamAcc, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLEACCDETAIL.HIVT_PACKING_LIST_DTL";
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
				/**hivbl_list_status=?, 
                                WHERE hivnum_packing_list_no=?
                     **/
				
				populateMAP.put(sq.next(),voSamAcc.getPackingListTableStatus());
				populateMAP.put(sq.next(),voSamAcc.getPackingListNO());
				 
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
		
		
		
		
 	
		public boolean checkDailyLabNoDuplicacyforSampleAcc(SampleAcceptanceVO voPatient,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			boolean isLabNoPresent = false;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.CHECKDAILYLABNODUPLICACY.FORSAMPLEACCEPTANCE";
			int count=0;
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			populateMap.put(sq.next(), voPatient.getLabNoConfiguration());
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
					throw new HisRecordNotFoundException();
				}
				else
				{
					//rs.beforeFirst();
					count=rs.getInt(1);
					if(count>0)
						isLabNoPresent=true;
					
				//	departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return isLabNoPresent;
		}		
		
		
		public List<SampleAcceptanceVO> getSampleAcceptanceAutoLabNOConfig(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_ACCC_DETAIL_AUTOGEN_LABNO.HIVT_LABNO_CONF_MST";
			 
			
			if(sampleAcceptanceVO.getPatType().equals("3") || sampleAcceptanceVO.getPatType().equals("4"))
				sampleAcceptanceVO.setPatType("1");
			 
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
			 
				populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getTestCode());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getMachineCode());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getPatType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

			 	 
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
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleAcceptanceVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[0]);
					//}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					return listSampleAcceptanceVO;
				}
				else return listSampleAcceptanceVO;
			}
			return listSampleAcceptanceVO;
		}	
		public String generateLabNoDateSequence(String subLength,UserVO userVO)
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
				  
 
		public void updateSampleAccInhivtlabnoconfmst(SampleAcceptanceVO voSamAcc,String finalEntryDate, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLEACCDETAIL.HIVT_LABNO_CONF_MST";
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
				/**hivbl_list_status=?, 
                                WHERE hivnum_packing_list_no=?
                     **/
				populateMAP.put(sq.next(),finalEntryDate);
				populateMAP.put(sq.next(),voSamAcc.getLabNoConfiguration());
				populateMAP.put(sq.next(),voSamAcc.getLabCode());
				populateMAP.put(sq.next(),voSamAcc.getConfigTest());
				populateMAP.put(sq.next(),voSamAcc.getMachineCode()==null?"-1":voSamAcc.getMachineCode());
				populateMAP.put(sq.next(),voSamAcc.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamAcc.getConfigSeq());


				 
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

		public void updateSampleAccInhivtlabnoconfmstresettheLabNo(SampleAcceptanceVO voSamAcc,String finalEntryDate, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.SAMPLEACCDETAIL.RESET_RUNNING_LABNO.HIVT_LABNO_CONF_MST";
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
				/**hivbl_list_status=?, 
                                WHERE hivnum_packing_list_no=?
                     **/
				 
				populateMAP.put(sq.next(),voSamAcc.getLabCode());
				populateMAP.put(sq.next(),voSamAcc.getConfigTest());
				populateMAP.put(sq.next(),voSamAcc.getMachineCode()==null?"-1":voSamAcc.getMachineCode());
				populateMAP.put(sq.next(),voSamAcc.getConfigType());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamAcc.getConfigSeq());


				 
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

//START Sample Acceptance Through BarCode
		
		public List<SampleAcceptanceVO> getSampleAcceptanceDetailBarCode(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_ACCEPTANCE_TROUGH_BARCODE_HIVT_REQUISITION_DTL";
		 
			 
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
			 
				
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			//	populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getSampleNumber().split("###")[0]);
				populateMAP.put(sq.next(),sampleAcceptanceVO.getSampleNumber().split("###")[1]);
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
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleAcceptanceVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[i]);
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

		
		public List<SampleAcceptanceVO> getSampleAcceptanceDetailBarCode1(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_ACCEPTANCE_TROUGH_BARCODE_HIVT_REQUISITION_DTL_FETCH_SAMPLENO";
			String condition2=" and b.GNUM_LAB_CODE="+sampleAcceptanceVO.getLabCode();
			String condition3=" and b.gnum_machine_code="+sampleAcceptanceVO.getMachineCodee();
			String condition4=" and (gnum_machine_code is null or gnum_machine_code=-1) ";
			 
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
				
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			//	populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getSampleNumber().toString());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getFromDate());
				populateMAP.put(sq.next(),sampleAcceptanceVO.getToDate());
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<SampleAcceptanceVO> listSampleAcceptanceVO = new ArrayList<SampleAcceptanceVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				if(!sampleAcceptanceVO.getLabCode().equals("%"))
				{
					query+=condition2;
					
				}
				
				
				if(sampleAcceptanceVO.getFlag().equals("1"))
				{
					query+=condition3;
					
				}
				
				
				/*if(sampleAcceptanceVO.getFlag().equals("") || sampleAcceptanceVO.getFlag().equals("0"))
				{
					query+=condition4;
					
				}*/
				
				System.out.println("query::"+query);
			 
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
						listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[i]);
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

		
		
		public void updateSampleAccInRequisitionDtlToReject(SampleAcceptanceVO voSamAcc,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			
            String queryKey2="UPDATE.ACCEPTED_SAMPLE_NOTACC_DETAIL.HIVT_REQUISITION_DTL";
            String queryKey3="UPDATE.ACCEPTED_SAMPLE_RESCHEDULED_ACCDETAIL.HIVT_REQUISITION_DTL";
            
			
			try {
				
		
			       	if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_CANCELLED_STATUS))//1
			    	{
			    		 voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_UPDATE_CANCELLED_STATUS); //11
			    		query = HelperMethodsDAO.getQuery(filename, queryKey2);
			    	}
			    		
			    	if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_RESCHEDULED_STATUS))//2
			    	{
			    		voSamAcc.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS); //12
			    		query = HelperMethodsDAO.getQuery(filename, queryKey3);
			    	}
			    				   
			 
				
			 
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				 
					
				
					 if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_CANCELLED_STATUS))//1
				    	{
						
						 populateMAP.put(sq.next(),voSamAcc.getReqDtlStatus());
						
						 populateMAP.put(sq.next(),voSamAcc.getRejectionAction());
						 populateMAP.put(sq.next(),voSamAcc.getRejectionReason());
						 populateMAP.put(sq.next(),_userVO.getSeatId());
				       
				         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
				         populateMAP.put(sq.next(),voSamAcc.getTestCode());
				         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
				    	}
				    		
					 if(voSamAcc.getRejectionAction().equals(InvestigationConfig.REJECTION_ACTION_RESCHEDULED_STATUS))//2
				    	{
				    		
				    		populateMAP.put(sq.next(),voSamAcc.getReqDtlStatus());
				    		
							 populateMAP.put(sq.next(),voSamAcc.getRejectionAction());
							 populateMAP.put(sq.next(),voSamAcc.getRejectionReason());
							 populateMAP.put(sq.next(),_userVO.getSeatId());
					      
					         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
					         populateMAP.put(sq.next(),voSamAcc.getTestCode()); 
					         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
					         
				    	}
				
				 
			
			
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
			}

		}
		
		public void updateSampleAccInSampleDtlToReject(SampleAcceptanceVO voSamAcc,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		
            String queryKey2="UPDATE.SAMPLE_NOTACC_DETAIL.HIVT_SAMPLE_DTL";
            
			try {
				
				  
			        
			        
			    		query = HelperMethodsDAO.getQuery(filename, queryKey2);
			       
		     	 
				 
				 
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				 
			 
					
			
				     populateMAP.put(sq.next(),_userVO.getSeatId());
					 populateMAP.put(sq.next(),voSamAcc.getRejectionAction());
					 populateMAP.put(sq.next(),voSamAcc.getRejectionReason());
			        // populateMAP.put(sq.next(),voSamAcc.getLabNoConfiguration());
			         populateMAP.put(sq.next(),voSamAcc.getSampleNo());
			         populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
			        
				 
				
				 
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
		
		
		
		public List<SampleAcceptanceVO> getSampleAcceptanceDetailForCheckPackNoToReject(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_ACCEPTANCE_PACKING_LIST_FOR_CHECK_PACKNO_HIVT_REQUISITION_DTL_TO_REJECT";
			 
			 
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
			 
				
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				
				
			//	populateMAP.put(sq.next(),sampleAcceptanceVO.getLabCode());
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
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(SampleAcceptanceVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[i]);
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
		
		public String getLabTestMachine(String labCode,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			/*List testMachine=new ArrayList();*/
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.LABTEST_MACHINE.HIVT_LABTEST_MST";
	
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			
			
			try
			{
			 

				populateMap.put(sq.next(), labCode.split("#")[0]);
			//	populateMap.put(sq.next(), labCode.split("#")[1]);
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
				populateMap.put(sq.next(), labCode.split("#")[0]);

			 	 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			try
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			String testMachine="";
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
					while (rs.next())
					testMachine+=rs.getString(1)+"#"+rs.getString(2)+"#"+rs.getString(3)+"@";
				
					
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					//throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return testMachine;
		}
		
		
		public void saveSampleDetail(SampleAcceptanceVO voSamAcc, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT_SAM_ACC_DTLS.HMIT_SAMPLE_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename) OR getting query out of property file"
								+ e);
			}

			try {
				  if(voSamAcc.getTestBasedMachine()!=null)
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				  else
					  populateMAP.put(sq.next(),"-1");
					  
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				  
				  if(voSamAcc.getTestBasedMachine()!=null)
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				  else
					  populateMAP.put(sq.next(),"-1");  
				  
				populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamAcc.getTestCode());
				populateMAP.put(sq.next(),voSamAcc.getSampleNo());
				populateMAP.put(sq.next(),"0");
				populateMAP.put(sq.next(),voSamAcc.getPriorityCode());
				populateMAP.put(sq.next(),"0");
				populateMAP.put(sq.next(),voSamAcc.getTempSampleNo());
				populateMAP.put(sq.next(),voSamAcc.getCollDate());
				populateMAP.put(sq.next(),_userVO.getUserId());
				populateMAP.put(sq.next(),"1");
				populateMAP.put(sq.next(),voSamAcc.getPatCRNo());
				populateMAP.put(sq.next(),voSamAcc.getPatName());
				populateMAP.put(sq.next(),voSamAcc.getPatAge());
				populateMAP.put(sq.next(),voSamAcc.getPatGender());
				
       	
	            	
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in sample acceptance process");
			}

			
		}
		
		public void updateRequestId(SampleAcceptanceVO voSamAcc, UserVO _userVO) {

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
				
				 if(voSamAcc.getTestBasedMachine()!=null)
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				 else
						populateMAP.put(sq.next(),"-1");
					 
				 
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				
				 if(voSamAcc.getTestBasedMachine()!=null)
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				 else
					 populateMAP.put(sq.next(),"-1");
					 
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
		
		public String checkAutoGenFormate(SampleAcceptanceVO voSample,UserVO _UserVO)
		{
			String value="";
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_ACC_AUTOGEN_.HIVT_LABNO_CONF_MST";
			   
			
			
			
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
			
				if(voSample.getTempSampleNo().equals("2"))
				{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
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
				if(voSample.getTempSampleNo().equals("2"))
				{
					populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
                    populateMap.put(sq.next(),voSample.getLabCode());
					populateMap.put(sq.next(),voSample.getTestCodee());
					populateMap.put(sq.next(),voSample.getPatType());

				} 

				
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			List<SampleAcceptanceVO> listSampleAcceptanceVO = new ArrayList<SampleAcceptanceVO>();
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
					rs.next();
					 value=rs.getString(1);
					//valueObjects = HelperMethods.populateVOfrmRS(SampleAcceptanceVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						//listSampleAcceptanceVO.add((SampleAcceptanceVO)valueObjects[0]);
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
			return value;
		}

		
		
		
		
		public List getLabCombos(SampleAcceptanceVO onlinePatientvo,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List parameterCombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT_LAB_COMBO_HIVT_LABORATORY_MST";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(),_UserVO.getHospitalCode());
			populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMap.put(sq.next(),_UserVO.getUserSeatId());
			populateMap.put(sq.next(),_UserVO.getHospitalCode());
			
			 
			
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
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return parameterCombo;
		}



		

		
		
		
		public  Inv_RequisitionRaisingPatientVO getInvRaisingPatDetails(String crno,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL_SAMPLE_ACC";
			Sequence sq = new Sequence();

			Inv_RequisitionRaisingPatientVO invReqRaisingVO;
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), crno);
			
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
				invReqRaisingVO=new Inv_RequisitionRaisingPatientVO();
				if(!rs.next())
				{
				}
				else HelperMethods.populateVOfrmRS(invReqRaisingVO,rs);
				
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return invReqRaisingVO;
		}
		
		
		
		public void makeRefund(SampleAcceptanceVO voSamAcc, UserVO userVO,String tariffdetails,String tariffQty,Inv_RequisitionRaisingPatientVO patVO,String  requisitionTypeForBilling,String makeBillingTestWise)
		{
			String errorMsg = "";
			String billNo="";
			HisDAO hisDAO_p=new HisDAO("New Investigation", "SampleAcceptanceDAO");
			final String strProcName = InvestigationDaoConfig.PROCEDURE_REFUND_BILLING;
			final int nProcedureIndex;
			final String strDbErr;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				
				
				

				
				//System.out.println("department code................"+voBillingDtl.getDeptCode());
				
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);

				//hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
				if(voSamAcc.getIsbilledornot()!=null && (!voSamAcc.getIsbilledornot().equals("") && voSamAcc.getIsbilledornot().equals("2")))
					hisDAO_p.setProcInValue(nProcedureIndex, "modval","2",1);
					else
				hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
			
				hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_chargetype_id",requisitionTypeForBilling,2);
				hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_service_id",makeBillingTestWise,3);  
		     	hisDAO_p.setProcInValue(nProcedureIndex, "gstr_req_no",voSamAcc.getRequisitionNo(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "gnum_treatment_cat",patVO.getPatCategoryCode(),5);
		
				hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_puk",patVO.getPatCRNo(),6);
				hisDAO_p.setProcInValue(nProcedureIndex, "gstr_tariff",tariffdetails,7);
				hisDAO_p.setProcInValue(nProcedureIndex, "gstr_reqqty",tariffQty,8);			
				
				hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid",userVO.getSeatId(),9);
				hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code",userVO.getHospitalCode(),10);
				
				hisDAO_p.setProcInValue(nProcedureIndex, "admno",(patVO.getPatadmissionno()==null?"0":patVO.getPatadmissionno()),11);
				hisDAO_p.setProcInValue(nProcedureIndex, "remarks",voSamAcc.getRejectionReason()==null?"":voSamAcc.getRejectionReason(),12);

				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13); 

				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				
				/* Getting out parameters */
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				System.out.println("Refund Done Successussfully"+strDbErr);

				/* If Database Error Occurs, No farther processing is required. */
				if (strDbErr != null && !strDbErr.equals("")) {
					throw new Exception("Data Base Error:" + strDbErr);
				}

			}

			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new HisDataAccessException(e.getMessage());
			}
			
		}

		public List getMachine(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstcombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.MACHINE_COMBO.HIVT_MACHINE_MST";
			
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
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					lstcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstcombo;
		}



		
		public List machinelist(SampleAcceptanceVO sampleAcceptanceVO,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstcombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.MACHINE_COMBO.HMIT_MACHINE_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			
			
			try
			{
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					lstcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstcombo;
		}
		

		
		public String issufficientbalance(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			ResultSet rs=null;
			String record=null;
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="SELECT.IS_SUFFICIENT_AMOUNT.HIVT_REQUISITION_DTL";
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
				/*populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_AFETR_DELETE);
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				
				//Where Condiotion
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_WHERE_CONDITION);
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_FOR_PATIENT_BASED_WHERE_CONDITION);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getDelLabCode());
				populateMAP.put(sq.next(),searchVO.getDelTestCode());*/
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				//populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
				//populateMAP.put(sq.next(), InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS);
				populateMAP.put(sq.next(), searchVO.getRequisitingtypeforbilling());
				populateMAP.put(sq.next(), searchVO.getPatientCrNo());
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
				if(rs.next())
					record = rs.getString(1);
				System.out.println("issufficientamount===================="+record);
				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			return record;
		}
		
		
		public String getamountfortest(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			ResultSet rs=null;
			String record=null;
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="SELECT.AMOUNT_FOR_ONE_TEST.HIVT_REQUISITION_DTL";
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
				/*populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_AFETR_DELETE);
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				
				//Where Condiotion
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_WHERE_CONDITION);
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_FOR_PATIENT_BASED_WHERE_CONDITION);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getDelLabCode());
				populateMAP.put(sq.next(),searchVO.getDelTestCode());*/
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				//populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
				//populateMAP.put(sq.next(), InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS);
				populateMAP.put(sq.next(), searchVO.getWarcode());
				populateMAP.put(sq.next(), searchVO.getPatcatcode());
				populateMAP.put(sq.next(), searchVO.getRequisitingtypeforbilling());
				populateMAP.put(sq.next(), searchVO.getTestCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
				if(rs.next())
					record = rs.getString(1);
				System.out.println("rate for test===================="+record);
				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			return record;
		}
		
		
		public String gettestcode(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			ResultSet rs=null;
			String record=null;
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="SELECT.GET_TEST_CODE.HIVT_REQUISITION_DTL";
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
				/*populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_AFETR_DELETE);
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				
				//Where Condiotion
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_WHERE_CONDITION);
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_FOR_PATIENT_BASED_WHERE_CONDITION);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getDelLabCode());
				populateMAP.put(sq.next(),searchVO.getDelTestCode());*/
				populateMAP.put(sq.next(), searchVO.getUsertestcode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				//populateMAP.put(sq.next(), InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
				//populateMAP.put(sq.next(), InvestigationConfig.REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS);
		
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
				if(rs.next())
					record = rs.getString(1);
				System.out.println("rate for test===================="+record);
				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			return record;
		}
		
		
		//added by krishnan nema on 26/10/2018
		public void updateSampleAccInRequisitionDtlModify(SampleAcceptanceVO voSamAcc,  UserVO _userVO) {

			String query1 = "";
			String query2 = "";
			String query3 = "";
			String query4 = "";
			Map populateMAP = new HashMap();
			Map populateMAP1 = new HashMap();
			Map populateMAP2 = new HashMap();

			Sequence sq = new Sequence();
			Sequence sq1 = new Sequence();
			Sequence sq2 = new Sequence();

			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String status="";
            String queryKey1="UPDATE_SAMPLE_ACC_MACHINE_DTL.HIVT_REQUISITION_DTL";
            String queryKey2="UPDATE_SAMPLE_ACC_MACHINE_DTL.HMIT_SAMPLE_DTL";
            String queryKey3="UPDATE_SAMPLE_ACC_MACHINE_DTL_NO_MACHINE.HIVT_REQUISITION_DTL";
            String queryKey4="UPDATE_HGSTR_DAILY_LABNO.HIVT_REQUISITION_DTL";
            String queryKey5="UPDATE_HGSTR_DAILY_LABNO.HIVT_SAMPLE_DTL";
            

            try {
					query1 = HelperMethodsDAO.getQuery(filename, queryKey3);
					query2 = HelperMethodsDAO.getQuery(filename, queryKey2);
            		query3 = HelperMethodsDAO.getQuery(filename, queryKey4);
            		query4 = HelperMethodsDAO.getQuery(filename, queryKey5);
					
			       	if(voSamAcc.getTestBasedMachine().equals("-1"))
			    	{
			       		//query2 = HelperMethodsDAO.getQuery(filename, queryKey3);
			       		status="6";
			    	}else{
			    		
			    	status="17";
			    	}
			       	
			    		
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				 

				/*--normal sample dtl
  				update hmit_sample_dtl set hminum_machine_id = ? where gnum_hospital_code = ? hminum_inv_reqdno = ?

  				--requisition dtl machine code
  				update hivt_requisition_dtl set gnum_machine_code = ? where gnum_hospital_code = ? and hivtnum_req_dno = ?

				--in case of no machine
				update hivt_requisition_dtl set gnum_machine_code = ?, hivnum_reqdtl_status = ? where gnum_hospital_code = ? and hivtnum_req_dno = ?*/
						
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				populateMAP.put(sq.next(),status);
				populateMAP.put(sq.next(),_userVO.getHospitalCode()); 
				populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
					         
				if(voSamAcc.getTestBasedMachine().equals("-1"))
		    	{
				populateMAP1.put(sq1.next(),"-1");
		    	}
				else
					populateMAP1.put(sq1.next(),voSamAcc.getTestBasedMachine());
				populateMAP1.put(sq1.next(),_userVO.getHospitalCode()); 
				populateMAP1.put(sq1.next(),voSamAcc.getRequisitionDNo());
				
				
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query1, populateMAP);
				
				
			
		    		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query2, populateMAP1);
		    	
		    		if(voSamAcc.getCheckLabConfigForAutoGen().equals("1")){
		    			//UPDATE_HGSTR_DAILY_LABNO.HIVT_REQUISITION_DTL = UPDATE hivt_requisition_dtl SET hgstr_daily_labno = ? where gnum_hospital_code = ? and hivtnum_req_dno = ?
		    			// UPDATE_HGSTR_DAILY_LABNO.HIVT_SAMPLE_DTL = UPDATE hivt_sample_dtl SET hgstr_daily_labno = ? where gnum_hospital_code = ? and hivtnum_req_dno = ? 
		    			populateMAP2.put(sq2.next(),voSamAcc.getLabNoConfiguration());
						populateMAP2.put(sq2.next(),_userVO.getHospitalCode()); 
						populateMAP2.put(sq2.next(),voSamAcc.getRequisitionDNo());
					
		    			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query3, populateMAP2);
		    			
		    			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query4, populateMAP2);
		    		}
			
			
			} catch (Exception e) {
				throw new HisDataAccessException("Exception in database insertion");
			}
/*			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
			}*/

		}


		
		public String islistexistinhmitsample(SampleAcceptanceVO voPatient,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			boolean isLabNoPresent = false;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT_IS_EXIST_REQUSITION_HMIT_SAMPLE_DTL";
			String count="";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			
			populateMap.put(sq.next(), voPatient.getRequisitionDNo());
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
		
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
					throw new HisRecordNotFoundException();
				}
				else
				{
					//rs.beforeFirst();
					count=rs.getString(1);
										
				//	departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return count;
		}



	

		public void saveSampleDetaillabcase(SampleAcceptanceVO voSamAcc, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT_SAM_ACC_DTLS.HMIT_SAMPLE_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename) OR getting query out of property file"
								+ e);
			}

			try {
				  if(voSamAcc.getTestBasedMachine()!=null)
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				  else
					  populateMAP.put(sq.next(),"-1");
					  
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				  
				  if(voSamAcc.getTestBasedMachine()!=null)
				populateMAP.put(sq.next(),voSamAcc.getTestBasedMachine());
				  else
					  populateMAP.put(sq.next(),"-1");  
				  
				populateMAP.put(sq.next(),voSamAcc.getRequisitionDNo());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),voSamAcc.getTestCode());
				populateMAP.put(sq.next(),voSamAcc.getSampleNo());
				populateMAP.put(sq.next(),"0");
				populateMAP.put(sq.next(),voSamAcc.getPriorityCode());
				populateMAP.put(sq.next(),"0");
				populateMAP.put(sq.next(),voSamAcc.getTempSampleNo());
				populateMAP.put(sq.next(),voSamAcc.getCollDate());
				populateMAP.put(sq.next(),_userVO.getUserId());
				populateMAP.put(sq.next(),"1");
				populateMAP.put(sq.next(),voSamAcc.getPatCRNo());
				populateMAP.put(sq.next(),voSamAcc.getPatName());
				populateMAP.put(sq.next(),voSamAcc.getPatAge());
				populateMAP.put(sq.next(),voSamAcc.getPatGender());
				
       	
	            	
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

		
		public List machinelistnew(String labCode,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstcombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.LABTEST_MACHINE.HIVT_LABTEST_MST_NEW";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			
			
			try
			{

			//	populateMap.put(sq.next(), labCode.split("#")[0]);
			//	populateMap.put(sq.next(), labCode.split("#")[1]);
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
				populateMap.put(sq.next(), labCode.split("#")[0]);
				
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
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					lstcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstcombo;
		}

		
		public  Inv_RequisitionRaisingPatientVO getInvRaisingPatDetailsnew(String crno,UserVO _UserVO,String reqno)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL_SAMPLE_ACC_NEW";
			//String queryKey = "SELECT_DATA_BILLING_REQUISITIONWISE";
			Sequence sq = new Sequence();

			Inv_RequisitionRaisingPatientVO invReqRaisingVO;
			
			populateMAP.put(sq.next(), reqno);
			//populateMAP.put(sq.next(), reqno);

			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), crno);
			
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
				invReqRaisingVO=new Inv_RequisitionRaisingPatientVO();
				if(!rs.next())
				{
				}
				else HelperMethods.populateVOfrmRS(invReqRaisingVO,rs);
				
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return invReqRaisingVO;
		}

		
		
		public void makeRefundpatbased(OnlinePatientAcceptanceVO voSamAcc, UserVO userVO,String tariffdetails,String tariffQty,Inv_RequisitionRaisingPatientVO patVO,String  requisitionTypeForBilling,String makeBillingTestWise)
		{
			String errorMsg = "";
			String billNo="";
			HisDAO hisDAO_p=new HisDAO("New Investigation", "SampleAcceptanceDAO");
			final String strProcName = InvestigationDaoConfig.PROCEDURE_REFUND_BILLING;
			final int nProcedureIndex;
			final String strDbErr;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				
				
				

				
				//System.out.println("department code................"+voBillingDtl.getDeptCode());
				
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);

				//hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
				
				hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
			
				hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_chargetype_id",requisitionTypeForBilling,2);
				hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_service_id",makeBillingTestWise,3);  
		     	hisDAO_p.setProcInValue(nProcedureIndex, "gstr_req_no",voSamAcc.getRequisitionNorefund(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "gnum_treatment_cat",patVO.getPatCategoryCode(),5);
		
				hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_puk",patVO.getPatCRNo(),6);
				hisDAO_p.setProcInValue(nProcedureIndex, "gstr_tariff",tariffdetails,7);
				hisDAO_p.setProcInValue(nProcedureIndex, "gstr_reqqty",tariffQty,8);			
				
				hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid",userVO.getSeatId(),9);
				hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code",userVO.getHospitalCode(),10);
				
				hisDAO_p.setProcInValue(nProcedureIndex, "admno",(patVO.getPatadmissionno()==null?"0":patVO.getPatadmissionno()),11);
				hisDAO_p.setProcInValue(nProcedureIndex, "remarks",voSamAcc.getRejectionReason()==null?"":voSamAcc.getRejectionReason(),12);

				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,13); 

				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				
				/* Getting out parameters */
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				System.out.println("Refund Done Successussfully"+strDbErr);

				/* If Database Error Occurs, No farther processing is required. */
				if (strDbErr != null && !strDbErr.equals("")) {
					throw new Exception("Data Base Error:" + strDbErr);
				}

			}

			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new HisDataAccessException(e.getMessage());
			}
			
		}

		
}





