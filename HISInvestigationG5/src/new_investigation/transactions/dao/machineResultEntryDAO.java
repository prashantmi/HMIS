package new_investigation.transactions.dao;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.InvCriteriaCodeVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.TestMandRefMasterVO;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
import new_investigation.vo.machineResultEntryVO;


public class machineResultEntryDAO extends DataAccessObject
{
	public machineResultEntryDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	    	
	
	
	public List LabComboForMachineResultEntry(machineResultEntryVO invmachineResultEntryVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
		String queryKey="SELECT_LAB_COMBO_FOR_RESULT_ENTRY_HIVT_LABORATORY_MST";
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
		
	
	
	public List<machineResultEntryVO> getPatientMachineResultEntry(machineResultEntryVO invmachineResultEntryVO, UserVO _UserVO)
	{
		
		
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		Map populateMAP1 = new HashMap();

		String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
		
		String queryKey = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL";
		String queryKey_crno = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_patcrno1";
		String queryKey_sampleno = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno";
		String queryKey_sampleno_all = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_all";
		
		String queryKey_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_MACHINEALL";
		String queryKey_crno_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_patcrno1_MACHINEALL";
		String queryKey_sampleno_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno_MACHINEALL";
		String queryKey_sampleno_all_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_all_MACHINEALL";
		
		
		String queryKeyUnmapped = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL";
	
		String queryKeyUnmapped_sampleno = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno";
		
		String queryKeyUnmapped_machineall = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_MACHINEALL";
		
		String queryKeyUnmapped_sampleno_machineall = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno_MACHINEALL";
		
		
		List<InvCriteriaCodeVO > objCriteriaCodeList = InvestigationTemplateDataHelper.getInstance().getCriteriaCode();
		 List<TestMandRefMasterVO > objRefRangeList = InvestigationTemplateDataHelper.getInstance().getReferanceRange(_UserVO.getHospitalCode());
		
		
		try
		{
			
			if(invmachineResultEntryVO.getRecord().equals("1"))
			{
				
			
				if(invmachineResultEntryVO.getPatcrno1()==null && invmachineResultEntryVO.getSamplenoo()==null)
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
					
					if(invmachineResultEntryVO.getMachineCode().equals("%"))
					{
						query = HelperMethodsDAO.getQuery(filename, queryKey_machineall);
					}
				}
				
			
			
			if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_crno);
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey_crno_machineall);
				}
			}
			
			if(invmachineResultEntryVO.getSamplenoo()!=null && invmachineResultEntryVO.getPatcrno1()==null)
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno);
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno_machineall);
				}
			}
			
			if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()!=null)
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno_all);
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno_all_machineall);
				}
				
			}
			
			}else
			{
			       if(invmachineResultEntryVO.getSamplenoo()==null)	
			       {
				query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped);
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped_machineall);
				}
				
			       }else
			       {   query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped_sampleno);	  
			       if(invmachineResultEntryVO.getMachineCode().equals("%"))
					{
						query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped_sampleno_machineall);
					}
			       }
			       
			  }
			
			}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		Sequence sq1 = new Sequence();

		try
		{
			if(invmachineResultEntryVO.getRecord().equals("1"))
			{
				
			      populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getRecord());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getResultEntryDate());
					
					
					if(invmachineResultEntryVO.getMachineCode().equals("%"))
					{
					populateMAP1.put(sq1.next(), _UserVO.getHospitalCode());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getLabCode());
					}
					else
					{
						
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getMachineCode());
					}
					
					populateMAP1.put(sq1.next(), _UserVO.getHospitalCode());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
					
					/*populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				
					
					populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

					
					
					
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getRecord());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());*/
					
					populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());	
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());

					if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
					{
						
					}
					else
	                 {
						populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	                 }
					
					if(invmachineResultEntryVO.getMachineCode().equals("%"))
					{
						//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
						//populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
						//populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

					}
					else
					{
						populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());


						//populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

						if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
						{
							
						}
						else
						{
						populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
						}

					}

					if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
					{
					
						if(invmachineResultEntryVO.getMachineCode().equals("%")) {}
						else {
							populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
						}
					}
					else
					{
					populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
		//	populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
			
		/*	if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				//populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
					
			}
			else
			{
				populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
				
			}*/
			
		//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//	populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

			

					if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
					{
					
						if(invmachineResultEntryVO.getMachineCode().equals("%")) {
							
							populateMAP.put(sq.next(), _UserVO.getHospitalCode());
							populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
							populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
						}
						else {
							populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode()); //pm
							}
					}
					else
					{
						populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode()); //pm
								}
					
			
			
			if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
			{
				
				populateMAP.put(sq.next(), invmachineResultEntryVO.getPatcrno1());
			}

			
			if(invmachineResultEntryVO.getSamplenoo()!=null && invmachineResultEntryVO.getPatcrno1()==null)
			{
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSamplenoo());
			}
			
			if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()!=null)
			{
				populateMAP.put(sq.next(), invmachineResultEntryVO.getPatcrno1());

				populateMAP.put(sq.next(), invmachineResultEntryVO.getSamplenoo());

			}
			
			if(invmachineResultEntryVO.getIsrepeattest()!=null && invmachineResultEntryVO.getIsrepeattest().equals("1"))
			populateMAP.put(sq.next(), "1");
			else 
				populateMAP.put(sq.next(), "0");	
			
		//	populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			
			
			}
			else
			{
				
				/*populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());*/
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				
				
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
				}
				else
				{
					
					populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
				}
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
				}
				else
				{
					
					populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
				}
				
				//populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
						
			       if(invmachineResultEntryVO.getSamplenoo()!=null)	
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSamplenoo());

			       
					populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
					populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());

					
			}
			
			if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
			{
				////populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());	
				//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//	populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
			///	populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
				
			//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				
				
			}

		
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		List<machineResultEntryVO> lstInvmachineResultEntryVO = new ArrayList<machineResultEntryVO>();
		List<machineResultEntryVO> finalLstInvmachineResultEntryVO = new ArrayList<machineResultEntryVO>();
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
				valueObjects = HelperMethods.populateVOfrmRS(machineResultEntryVO.class, rs);
				HashMap<String, String> reqMachineTestParaMapping = new HashMap<String, String>(); 

				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					if(invmachineResultEntryVO.getRecord().equals("1"))
					{
					
						machineResultEntryVO ss = (machineResultEntryVO)valueObjects[i];
						String temp = ss.getReqDNo() + ss.getMachineTestParameterCode();
						
						if(reqMachineTestParaMapping.containsKey(temp))
						{
							
							
						}
						else {
							reqMachineTestParaMapping.put(temp, "1");
							machineResultEntryVO voo=(machineResultEntryVO)valueObjects[i];
						 
							if(invmachineResultEntryVO.getRecord().equals("1"))
							{
								
								if(voo.getPatCrNo()!=null )
									System.out.println("range call crno::"+voo.getPatCrNo());
							
								if(voo.getReqDNo()!=null )
									System.out.println("range call dno::"+voo.getReqDNo());
							
								if(voo.getTestCode()!=null)
								System.out.println("range call testcode::"+voo.getTestCode());
								
							String refRange = getReferenceRange(objRefRangeList, objCriteriaCodeList, voo.getTestCode(), voo.getParameterCode(), voo.getPatGender(),voo.getPatAge()); 
							
							String rangeValue=refRange;
							
							refRange=voo.getParameterCode()+"#@"+voo.getParaname()+"#@"+rangeValue+"#@"+voo.getMachineResult()+"#@";
							
							
							voo.setRanges(refRange);       
							}
							else
							{
								voo.setRanges(""+"@#"+"+"+"@#"+"@#"+""+"@#");
							}
							
							lstInvmachineResultEntryVO.add(voo);
							
						}
					
					}
					else
					{
						
						machineResultEntryVO voo=(machineResultEntryVO)valueObjects[i];
						
						if(invmachineResultEntryVO.getRecord().equals("1"))
						{
							
							if(voo.getPatCrNo()!=null )
								System.out.println("range call crno::"+voo.getPatCrNo());
						
							if(voo.getReqDNo()!=null )
								System.out.println("range call dno::"+voo.getReqDNo());
						
							if(voo.getTestCode()!=null)
							System.out.println("range call testcode::"+voo.getTestCode());
							
						String refRange = getReferenceRange(objRefRangeList, objCriteriaCodeList, voo.getTestCode(), voo.getParameterCode(), voo.getPatGender(),voo.getPatAge()); 
						
						voo.setRanges(refRange);       
						}
						else
						{
							voo.setRanges(""+"@#"+"+"+"@#"+"@#"+""+"@#");
						}
						
						lstInvmachineResultEntryVO.add(voo);
						
					//lstInvmachineResultEntryVO.add((machineResultEntryVO)valueObjects[i]);
					}
					
					/*if(lstInvmachineResultEntryVO.get(i).getReqDtlStatus().equals(InvestigationConfig.READY_RESULTPRINTING) || lstInvmachineResultEntryVO.get(i).getReqDtlStatus().equals(InvestigationConfig.REPORT_PDF_GEN))
					{
						if(lstInvmachineResultEntryVO.get(i).getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
						{						
							finalLstInvmachineResultEntryVO.add(lstInvmachineResultEntryVO.get(i));
							//status 13 and 26 added if its report is available after this process only
						}
					}
					else{
						finalLstInvmachineResultEntryVO.add(lstInvmachineResultEntryVO.get(i)); //add all other values
					}*/
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
		return lstInvmachineResultEntryVO;
	}	
	
	
	public void updateMachineResultEntryInRequisitionDtl(machineResultEntryVO voResultEntry, UserVO _UserVO)
	{
		String query = "";
		String query_new = "";
			Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
		String queryKey ="UPDATE.REQDTLS.MACHINE.ENTRY.HIVT_REQUISITION_DTLS";//UPDATE.REQDTLS.RESULT.ENTRY.HIVT_REQUISITION_DTLS
		String queryKey_new ="UPDATE.REQDTLS.MACHINE.ENTRY.HIVT_REQUISITION_DTLS_NEW";//UPDATE.REQDTLS.RESULT.ENTRY.HIVT_REQUISITION_DTLS
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			query_new = HelperMethodsDAO.getQuery(filename, queryKey_new);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
				
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(),voResultEntry.getReqDtlStatus());
			//populateMAP.put(sq.next(), _UserVO.getSeatId());
			
			populateMAP.put(sq.next(), voResultEntry.getReqDNo());
			//populateMAP.put(sq.next(), voResultEntry.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query_new, populateMAP);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	
	public void insertMachineResultEntryDtl(machineResultEntryVO voResultEntry, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
		String queryKey ="INSERT.MACHINE.ENTRY.DETAIL.HIVT_PARAMETER_DTL";//INSERT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 if(voResultEntry.getMachineResult()==null || voResultEntry.getMachineResult().equals(""))
			 {
				 voResultEntry.setMachineResult("--");
			 }
			 else
			 {
				 voResultEntry.setMachineResult(voResultEntry.getMachineResult().trim() );
				 
				 if(voResultEntry.getMachineResult()==null || voResultEntry.getMachineResult().equals(""))
				 {
					 voResultEntry.setMachineResult("--");
				 }
			 }
			 
			 if (voResultEntry.getMachineResult()!=null && voResultEntry.getMachineResult().contains(">"))
			 {
				 voResultEntry.setMachineResult(voResultEntry.getMachineResult().replaceAll(">", "greater than ")) ;
			 }
			 
			 if (voResultEntry.getMachineResult()!=null && voResultEntry.getMachineResult().contains("<"))
			 {
				 voResultEntry.setMachineResult(voResultEntry.getMachineResult().replaceAll("<", "less than ")) ;
			 }
			 

	            	
			 // for manglagiri
//			 String testcode="12815";  // round off testcode
	//		 String hospitalcode="96101"; // hospital code
		//	 String parcode="3620,3613"; // round off para values
			
			/*
			 * String testcode="13631"; // round off testcode String hospitalcode="37913";
			 * // hospital code String parcode="2385,2387,2388,2389,2397,5185"; // round off
			 * para values
			 */

             String testcode="";
			      	 String hospitalcode="";
						 String parcode="";
						 
					 // for manglagiri
						 if(_UserVO.getHospitalCode().equals("96101"))
						 {
					  testcode="12815";  // round off testcode
					  hospitalcode="96101"; // hospital code
				  parcode="3620,3613"; // round off para values
						 }
				
						 if(_UserVO.getHospitalCode().equals("37913"))
						 {
					  testcode="13631";  // round off testcode
					  hospitalcode="37913"; // hospital code
					  parcode="2385,2387,2388,2389,2397,5185"; // round off para values
						 }
						 
			 // start round off
			 if( voResultEntry.getTestCode()!=null && (testcode).contains(voResultEntry.getTestCode()) && _UserVO.getHospitalCode().equals(hospitalcode))
	         {

	           if (voResultEntry.getParameterCode()!=null && !voResultEntry.getParameterCode().equals("") && !voResultEntry.getParameterCode().equals("--") && (parcode).contains(voResultEntry.getParameterCode()))
	           {
	         	  
	         	  String machineval=voResultEntry.getMachineResult() ;
	         	  
	         	  
	         	  try
	         	  {
	         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
	         		  
	         		  int int_dec=(int)Math.round(x);
	                   String int_tostring=String.valueOf(int_dec);
	                   voResultEntry.setMachineResult(int_tostring);
	             
	         	  }
	         	  catch(Exception e)
	         	  {
	         		  
	         		  
	         	  }
	         	  
	         	  
	           }

	         } // end rounoff
			 
			 
			 
			 Map<String,String> map=new HashMap<String,String>();
				
			 
			 String tlc_code="";
			 String hospitalcodee="";
			 
			 if(_UserVO.getHospitalCode().equals("96101"))
			 {	
				 
				 tlc_code="128153617"; // dep para value like tlc 96101
					
				    map.put("128153613","128153620"); // depe val
					map.put("128153616","128153604"); // dep va;
					hospitalcodee="96101";
			 }
			 
			 if(_UserVO.getHospitalCode().equals("37913"))
			 {				 
			  tlc_code="136311180"; // dep para value like tlc 96101
				
			 //dev
			    //map.put("128153613","128153620"); // depe val
				//map.put("128153616","128153604"); // dep va;
				map.put("136312385","136315186") ;
				map.put("136312387","136315188") ;
				map.put("136312388","136315189") ;
				map.put("136312389","136315187") ;
				map.put("136312397","136315190") ;

			 
				//String hospitalcodee="96101";
				 hospitalcodee="37913";
}	
			 
			// String tlc_code="128153617"; // dep para value like tlc 96101
				//map.put("128153613","128153620"); // depe val
			//	map.put("128153616","128153604"); // dep va;
             //String hospitalcodee="96101";
				
			//testing 
			 //String tlc_code="128153617"; // dep para value like tlc 96101
			 //String tlc_code="136311180"; // dep para value like tlc 96101
				
			 //dev
			    //map.put("128153613","128153620"); // depe val
				//map.put("128153616","128153604"); // dep va;
			/*
			 * map.put("136312385","136315186") ; map.put("136312387","136315188") ;
			 * map.put("136312388","136315189") ; map.put("136312389","136315187") ;
			 * map.put("136312397","136315190") ;
			 * 
			 */			 
				//String hospitalcodee="96101";
			//	String hospitalcodee="37913";
			
			 
				if(voResultEntry.getTestCode()!=null && voResultEntry.getParameterCode()!=null && _UserVO.getHospitalCode().equals(hospitalcodee) )
				{
					
				if(map.containsKey(voResultEntry.getTestCode()+""+voResultEntry.getParameterCode()))
				{
				
					
					String dep_testparacode=map.get(voResultEntry.getTestCode()+""+voResultEntry.getParameterCode()) ;
			        		
					
					String isparameterexist=isparameterexist_fordepe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5));
					
					
					if(isparameterexist.equals("") || isparameterexist.equals("0"))
					{
						String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,tlc_code,tlc_code.substring(0,5));
						dependent_para_val=dependent_para_val.trim();
						if(dependent_para_val==null || dependent_para_val.equals("") )
						{
							insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
							
						}
						else
						{
							
							String int_dec11="--";  
							
				         	  try
				         	  {
				         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
				         		  
				         		  int int_dec=(int)Math.round(x);
				            
	                            double x1=  Double.parseDouble(dependent_para_val);
				         		  
				         		  int int_dec1=(int)Math.round(x1);
				             
				         		  double x3=(x*x1)/100;
  
				         		  int_dec11=df2.format(x3);
						             
				         	  }
				         	  catch(Exception e)
				         	  {
				         		  
				         		  
				         	  }
							insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
							
						}
					
					}else
					{
						String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,tlc_code,tlc_code.substring(0,5));
						dependent_para_val=dependent_para_val.trim();
						
						
						String int_dec11="--";  
						
			         	  try
			         	  {
			         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
			         		  
			         		  int int_dec=(int)Math.round(x);
			            
                            double x1=  Double.parseDouble(dependent_para_val);
			         		  
			         		  int int_dec1=(int)Math.round(x1);
			             
			         		  double x3=(x*x1)/100;
			         		  
			         		  int_dec11=df2.format(x3);
					             
			         	  }
			         	  catch(Exception e)
			         	  {
			         		  
			         		  
			         	  }
						
						if(int_dec11==null || int_dec11.equals("") )
						{
							updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
							
						}
						else
						{
							updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
							
									
						}
					
					}
				}
				else
				{
					
				// tlcccccccccc
					
                      if((voResultEntry.getTestCode()+""+voResultEntry.getParameterCode()).equals(tlc_code))
                          {
                    

                    		Iterator itrBatch=map.keySet().iterator();
                    						
                    						while(itrBatch.hasNext())//for(int i=0;i<size;i++)
                    						{
                    							
                    							String keyy=(String)itrBatch.next();
    
                    							String dep_testparacode=map.get(keyy) ;
                    			        		
                    							
                    							String isparameterexist=isparameterexist_fordepe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5));
                    							
                    							
                    							if(isparameterexist.equals("") || isparameterexist.equals("0"))
                    							{
                    								String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,keyy,keyy.substring(0,5));
                    								dependent_para_val=dependent_para_val.trim();
                    								if(dependent_para_val==null || dependent_para_val.equals("") )
                    								{
                    									insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
                    									
                    								}
                    								else
                    								{
                    									
                    									String int_dec11="--";  
                    									
                    						         	  try
                    						         	  {
                    						         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
                    						         		  
                    						         		  int int_dec=(int)Math.round(x);
                    						            
                    			                            double x1=  Double.parseDouble(dependent_para_val);
                    						         		  
                    						         		  int int_dec1=(int)Math.round(x1);
                    						             
                    						         		  double x3=(x*x1)/100;
                    		  
                    						         		  int_dec11=df2.format(x3);
                    								             
                    						         	  }
                    						         	  catch(Exception e)
                    						         	  {
                    						         		  
                    						         		  
                    						         	  }
                    									insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
                    									
                    								}
                    							
                    							}else
                    							{
                    								String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,keyy,keyy.substring(0,5));
                    								dependent_para_val=dependent_para_val.trim();
                    								
                    								
                    								String int_dec11="--";  
                    								
                    					         	  try
                    					         	  {
                    					         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
                    					         		  
                    					         		  int int_dec=(int)Math.round(x);
                    					            
                    		                            double x1=  Double.parseDouble(dependent_para_val);
                    					         		  
                    					         		  int int_dec1=(int)Math.round(x1);
                    					             
                    					         		  double x3=(x*x1)/100;
                    					         		  
                    					         		  int_dec11=df2.format(x3);
                    							             
                    					         	  }
                    					         	  catch(Exception e)
                    					         	  {
                    					         		  
                    					         		  
                    					         	  }
                    								
                    								if(int_dec11==null || int_dec11.equals("") )
                    								{
                    									updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
                    									
                    								}
                    								else
                    								{
                    									updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
                    									
                    											
                    								}
                    							
                    							}
                    							
                    						}
                    						
                          }				
					
	
				}
					
				}
				
				
				

			 
			 
			 
            populateMAP.put(sq.next(), voResultEntry.getReqDNo());
  
            populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
            populateMAP.put(sq.next(), voResultEntry.getTestCode()+voResultEntry.getParameterCode());  
            populateMAP.put(sq.next(),_UserVO.getHospitalCode());
            populateMAP.put(sq.next(), _UserVO.getSeatId());
            populateMAP.put(sq.next(), voResultEntry.getMachineResult());
            populateMAP.put(sq.next(), voResultEntry.getParameterCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voResultEntry.getRefRange()); 
	//		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
	//		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
			
			

			
			
			
        	
            	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
		}

	}
 
		


//insertResultEntryDtlInJobWorkorderData


public void insertMachineResultEntryDtlInJobWorkorderData(machineResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey ="INSERT.RESULT.ENTRY.DETAIL.HIVT_JOBWORKORDER_DATA";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		 
		
		 
		
	   // populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
        //Need To Be ADD IS_VALID_ACTIVE
	//	 populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
		 populateMAP.put(sq.next(), InvestigationConfig.UPDATION_TYPE);
		 populateMAP.put(sq.next(), InvestigationConfig.STATUS_CODE);
		 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	//	 populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
	 //       populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
	        populateMAP.put(sq.next(), voResultEntry.getTestCode());
	        populateMAP.put(sq.next(), voResultEntry.getLabCode());
	//        populateMAP.put(sq.next(), voResultEntry.getSampleNo()==null?"":voResultEntry.getSampleNo());
	//        populateMAP.put(sq.next(), voResultEntry.getPatAge()==null?"":voResultEntry.getPatAge());
	//        populateMAP.put(sq.next(), voResultEntry.getPatGender());
	//        
	//        populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode()==null?"":voResultEntry.getTestParaMeterCode()); 
	//        populateMAP.put(sq.next(), voResultEntry.getReportAvailableAfter());	        
	        
    /*    populateMAP.put(sq.next(), voResultEntry.getResultEntryValue()==null?"":voResultEntry.getResultEntryValue());
        populateMAP.put(sq.next(), voResultEntry.getPatVisitDat());
        populateMAP.put(sq.next(), voResultEntry.getPatVisitNo().equals("null")?"":voResultEntry.getPatVisitNo());
        populateMAP.put(sq.next(), voResultEntry.getLabNo());
        populateMAP.put(sq.next(), voResultEntry.getEpisodeCode());
        populateMAP.put(sq.next(), voResultEntry.getDepartmentcode());
        populateMAP.put(sq.next(), voResultEntry.getPatdeptunitcode());
        populateMAP.put(sq.next(), voResultEntry.getRequisitionTypeCode());
        populateMAP.put(sq.next(), voResultEntry.getTestName());
        populateMAP.put(sq.next(), voResultEntry.getPatLabName());
        populateMAP.put(sq.next(), voResultEntry.getSampleName());
        populateMAP.put(sq.next(), voResultEntry.getTempSampleNo());
*/

		
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
	}

}



public List getLabBasedMachine(machineResultEntryVO invmachineResultEntryVO,UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List labBasedMachineCombo=new ArrayList();
	String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey="SELECT.LABTEST_MACHINE.HIVT_LABTEST_MST.MACHINE.RESULT.ENTRY";
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	

	populateMap.put(sq.next(),_UserVO.getHospitalCode());
	populateMap.put(sq.next(),invmachineResultEntryVO.getLabCode());

	
	 
	
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
			labBasedMachineCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
	return labBasedMachineCombo;
}
	


public void updateResultDtl(machineResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey ="UPDATE.HMIT_RESULT_DTL";
	String queryUnmappded="UPDATE.HMIT_RESULT_DTL.UNMAPPED";
	
	try {
		if(voResultEntry.getRecord().equals("1"))
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		else
			query = HelperMethodsDAO.getQuery(filename, queryUnmappded);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {	 
		
		if(voResultEntry.getRecord().equals("1"))
		{	
			populateMAP.put(sq.next(), "2");
		populateMAP.put(sq.next(), _UserVO.getSeatId());
		populateMAP.put(sq.next(), voResultEntry.getMachineReqId());
		}
		else
			populateMAP.put(sq.next(), "0");
		
		
		
		populateMAP.put(sq.next(), "1");
		populateMAP.put(sq.next(), voResultEntry.getMachineRecordId());
        populateMAP.put(sq.next(), voResultEntry.getMachineCode());
        populateMAP.put(sq.next(), _UserVO.getHospitalCode());
                	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}




public void updateMachineSampleDtl(machineResultEntryVO voResultEntry, UserVO _UserVO,String updateParaCount) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey ="UPDATE_STATUS.HMIT_SAMPLE_DTL";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {	 
		
		populateMAP.put(sq.next(), voResultEntry.getSampleStatus());
		populateMAP.put(sq.next(), updateParaCount);		
	
		populateMAP.put(sq.next(), voResultEntry.getReqDNo());

		populateMAP.put(sq.next(), voResultEntry.getMachineReqId());
        populateMAP.put(sq.next(), voResultEntry.getMachineCode());
        populateMAP.put(sq.next(), _UserVO.getHospitalCode());
        //SAMPLENO TESTCODE COLL DATE
                	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}


//fetch para count
public String getParaCount(String labTestString,UserVO userVO)
{
	String paraCount=""; 
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey="SELECT.PARA.COUNT.HMIT_SAMPLE_DTL";
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
		 
		populateMap.put(sq.next(), labTestString.split("#")[0]);
		populateMap.put(sq.next(), labTestString.split("#")[1]);
		populateMap.put(sq.next(), labTestString.split("#")[2]);
		populateMap.put(sq.next(), labTestString.split("#")[3]);
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
			//throw new HisRecordNotFoundException("");
			paraCount="";
		}
		else
		{
			paraCount=rs.getString(1);
			
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
	return paraCount;
}


public List<TestMandRefMasterVO> getRefRangeValues(UserVO uservo) {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey = "SELECT.hivt_test_mand_ref_val_mst";
	JDBCTransactionContext tx = new JDBCTransactionContext();
	tx.begin();
	Connection conn = tx.getConnection();
	List<TestMandRefMasterVO> objList = new ArrayList<TestMandRefMasterVO>();

	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), uservo.getHospitalCode());
		
		
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		rs.beforeFirst();
		while (rs.next()) {
			TestMandRefMasterVO objTestMandRefVo = new TestMandRefMasterVO();
			HelperMethods.populateVOfrmRS(objTestMandRefVo, rs);
			objList.add(objTestMandRefVo);
		}
	} catch (Exception e) {
		e.printStackTrace();
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException();
		} else if (e.getClass() == HisDataAccessException.class) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		} else if (e.getClass() == HisApplicationExecutionException.class) {
			throw new HisApplicationExecutionException(
					"DailyPatientDAO.populateMAP::" + e);
		} else
			throw new HisDataAccessException("HisDataAccessException:: "
					+ e);
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// tx.close();
	}
	return objList;
}



public List<InvCriteriaCodeVO> getCriteriaCodeValues() {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey = "SELECT.CRITERIA_CODE";
	JDBCTransactionContext tx = new JDBCTransactionContext();
	tx.begin();
	Connection conn = tx.getConnection();
	List<InvCriteriaCodeVO> objList = new ArrayList<InvCriteriaCodeVO>();

	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();

		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		rs.beforeFirst();
		while (rs.next()) {
			InvCriteriaCodeVO objTestMandRefVo = new InvCriteriaCodeVO();
			HelperMethods.populateVOfrmRS(objTestMandRefVo, rs);
			objList.add(objTestMandRefVo);
		}
	} catch (Exception e) {
		e.printStackTrace();
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException();
		} else if (e.getClass() == HisDataAccessException.class) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		} else if (e.getClass() == HisApplicationExecutionException.class) {
			throw new HisApplicationExecutionException(
					"DailyPatientDAO.populateMAP::" + e);
		} else
			throw new HisDataAccessException("HisDataAccessException:: "
					+ e);
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// tx.close();
	}
	return objList;
}



public String isparameterexist(machineResultEntryVO voResultEntry, UserVO _UserVO)
{
	String paraCount=""; 
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey="SELECT_COUNT_TESTPARAMETER";
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
		 
		   populateMAP.put(sq.next(), voResultEntry.getReqDNo());
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
           populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
           populateMAP.put(sq.next(), voResultEntry.getTestCode()+voResultEntry.getParameterCode());  
           
          /* populateMAP.put(sq.next(), _UserVO.getSeatId());
           populateMAP.put(sq.next(), voResultEntry.getMachineResult());
           populateMAP.put(sq.next(), voResultEntry.getParameterCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voResultEntry.getRefRange());*/ 
	//		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
	//		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
			
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		
		if (!rs.next())
		{
			//throw new HisRecordNotFoundException("");
			paraCount="";
		}
		else
		{
			paraCount=rs.getString(1);
		
			System.out.println("paracount");
		}
		
		
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			//throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	return paraCount;
}


public void updatetestparameterdtl(machineResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey ="UPDATE_HIVT_PARAMETER_DTL";
	
	try {
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HHelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {	 
		
		
		
		if(voResultEntry.getMachineResult()==null || voResultEntry.getMachineResult().equals(""))
		 {
			 voResultEntry.setMachineResult("--");
		 }
		 else
		 {
			 voResultEntry.setMachineResult(voResultEntry.getMachineResult().trim() );
			 
			 if(voResultEntry.getMachineResult()==null || voResultEntry.getMachineResult().equals(""))
			 {
				 voResultEntry.setMachineResult("--");
			 }
		 }
		
		 if (voResultEntry.getMachineResult()!=null && voResultEntry.getMachineResult().contains(">"))
		 {
			 voResultEntry.setMachineResult(voResultEntry.getMachineResult().replaceAll(">", "greater than ")) ;
		 }
		 
		 if (voResultEntry.getMachineResult()!=null && voResultEntry.getMachineResult().contains("<"))
		 {
			 voResultEntry.setMachineResult(voResultEntry.getMachineResult().replaceAll("<", "less than ")) ;
		 }
		
		   	
		               String testcode="";
			      	 String hospitalcode="";
						 String parcode="";
						 
					 // for manglagiri
						 if(_UserVO.getHospitalCode().equals("96101"))
						 {
					  testcode="12815";  // round off testcode
					  hospitalcode="96101"; // hospital code
				  parcode="3620,3613"; // round off para values
						 }
				
						 if(_UserVO.getHospitalCode().equals("37913"))
						 {
					  testcode="13631";  // round off testcode
					  hospitalcode="37913"; // hospital code
					  parcode="2385,2387,2388,2389,2397,5185"; // round off para values
						 }

					 // start round off
					 if( voResultEntry.getTestCode()!=null && (testcode).contains(voResultEntry.getTestCode()) && _UserVO.getHospitalCode().equals(hospitalcode))
			         {

			           if (voResultEntry.getParameterCode()!=null && !voResultEntry.getParameterCode().equals("") && !voResultEntry.getParameterCode().equals("--") && (parcode).contains(voResultEntry.getParameterCode()))
			           {
			         	  
			         	  String machineval=voResultEntry.getMachineResult() ;
			         	  
			         	  
			         	  try
			         	  {
			         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
			         		  
			         		  int int_dec=(int)Math.round(x);
			                   String int_tostring=String.valueOf(int_dec);
			                   voResultEntry.setMachineResult(int_tostring);
			             
			         	  }
			         	  catch(Exception e)
			         	  {
			         		  
			         		  
			         	  }
			         	  
			         	  
			           }

			         } // end rounoff
					 
					 
					 
					 
					 Map<String,String> map=new HashMap<String,String>();
					
					 //dev
					 //String tlc_code="128153617"; // dep para value like tlc 96101
					
					 String tlc_code="";
					 String hospitalcodee="";
					 
					 if(_UserVO.getHospitalCode().equals("96101"))
					 {	
						 
						 tlc_code="128153617"; // dep para value like tlc 96101
							
						    map.put("128153613","128153620"); // depe val
							map.put("128153616","128153604"); // dep va;
							hospitalcodee="96101";
					 }
					 
					 if(_UserVO.getHospitalCode().equals("37913"))
					 {				 
					  tlc_code="136311180"; // dep para value like tlc 96101
						
					 //dev
					    //map.put("128153613","128153620"); // depe val
						//map.put("128153616","128153604"); // dep va;
						map.put("136312385","136315186") ;
						map.put("136312387","136315188") ;
						map.put("136312388","136315189") ;
						map.put("136312389","136315187") ;
						map.put("136312397","136315190") ;

					 
						//String hospitalcodee="96101";
						 hospitalcodee="37913";
	}	
						if(voResultEntry.getTestCode()!=null && voResultEntry.getParameterCode()!=null && _UserVO.getHospitalCode().equals(hospitalcodee) )
						{
							
						if(map.containsKey(voResultEntry.getTestCode()+""+voResultEntry.getParameterCode()))
						{
						
							
							String dep_testparacode=map.get(voResultEntry.getTestCode()+""+voResultEntry.getParameterCode()) ;
					        		
							
							String isparameterexist=isparameterexist_fordepe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5));
							
							
							if(isparameterexist.equals("") || isparameterexist.equals("0"))
							{
								String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,tlc_code,tlc_code.substring(0,5));
								dependent_para_val=dependent_para_val.trim();
								if(dependent_para_val==null || dependent_para_val.equals("") )
								{
									insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
									
								}
								else
								{
									
									String int_dec11="--";  
									
						         	  try
						         	  {
						         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
						         		  
						         		  int int_dec=(int)Math.round(x);
						            
			                            double x1=  Double.parseDouble(dependent_para_val);
						         		  
						         		  int int_dec1=(int)Math.round(x1);
						             
						         		  double x3=(x*x1)/100;
		  
						         		  int_dec11=df2.format(x3);
								             
						         	  }
						         	  catch(Exception e)
						         	  {
						         		  
						         		  
						         	  }
									insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
									
								}
							
							}else
							{
								String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,tlc_code,tlc_code.substring(0,5));
								dependent_para_val=dependent_para_val.trim();
								
								
								String int_dec11="--";  
								
					         	  try
					         	  {
					         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
					         		  
					         		  int int_dec=(int)Math.round(x);
					            
		                            double x1=  Double.parseDouble(dependent_para_val);
					         		  
					         		  int int_dec1=(int)Math.round(x1);
					             
					         		  double x3=(x*x1)/100;
					         		  
					         		  int_dec11=df2.format(x3);
							             
					         		  
					         	  }
					         	  catch(Exception e)
					         	  {
					         		  
					         		  
					         	  }
								
								if(int_dec11==null || int_dec11.equals("") )
								{
									updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
									
								}
								else
								{
									updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
									
											
								}
							
							}
						}
						else
						{
							
						// tlcccccccccc
							
		                      if((voResultEntry.getTestCode()+""+voResultEntry.getParameterCode()).equals(tlc_code))
		                          {
		                    

		                    		Iterator itrBatch=map.keySet().iterator();
		                    						
		                    						while(itrBatch.hasNext())//for(int i=0;i<size;i++)
		                    						{
		                    							
		                    							String keyy=(String)itrBatch.next();
		    
		                    							String dep_testparacode=map.get(keyy) ;
		                    			        		
		                    							
		                    							String isparameterexist=isparameterexist_fordepe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5));
		                    							
		                    							
		                    							if(isparameterexist.equals("") || isparameterexist.equals("0"))
		                    							{
		                    								String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,keyy,keyy.substring(0,5));
		                    								dependent_para_val=dependent_para_val.trim();
		                    								if(dependent_para_val==null || dependent_para_val.equals("") )
		                    								{
		                    									insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
		                    									
		                    								}
		                    								else
		                    								{
		                    									
		                    									String int_dec11="--";  
		                    									
		                    						         	  try
		                    						         	  {
		                    						         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
		                    						         		  
		                    						         		  int int_dec=(int)Math.round(x);
		                    						            
		                    			                            double x1=  Double.parseDouble(dependent_para_val);
		                    						         		  
		                    						         		  int int_dec1=(int)Math.round(x1);
		                    						             
		                    						         		  double x3=(x*x1)/100;
		                    		  
		                    						         		  int_dec11=df2.format(x3);
		                    								             
		                    						         	  }
		                    						         	  catch(Exception e)
		                    						         	  {
		                    						         		  
		                    						         		  
		                    						         	  }
		                    									insertMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
		                    									
		                    								}
		                    							
		                    							}else
		                    							{
		                    								String dependent_para_val=Select_dep_val(voResultEntry,_UserVO,keyy,keyy.substring(0,5));
		                    								dependent_para_val=dependent_para_val.trim();
		                    								
		                    								
		                    								String int_dec11="--";  
		                    								
		                    					         	  try
		                    					         	  {
		                    					         		  double x=  Double.parseDouble(voResultEntry.getMachineResult());
		                    					         		  
		                    					         		  int int_dec=(int)Math.round(x);
		                    					            
		                    		                            double x1=  Double.parseDouble(dependent_para_val);
		                    					         		  
		                    					         		  int int_dec1=(int)Math.round(x1);
		                    					             
		                    					         		  double x3=(x*x1)/100;
		                    					         		  
		                    					         		  int_dec11=df2.format(x3);
		                    							             
		                    					         	  }
		                    					         	  catch(Exception e)
		                    					         	  {
		                    					         		  
		                    					         		  
		                    					         	  }
		                    								
		                    								if(int_dec11==null || int_dec11.equals("") )
		                    								{
		                    									updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),"--");
		                    									
		                    								}
		                    								else
		                    								{
		                    									updateMachineResultEntryDtl_depe(voResultEntry,_UserVO,dep_testparacode,dep_testparacode.substring(0,5),int_dec11);
		                    									
		                    											
		                    								}
		                    							
		                    							}
		                    							
		                    						}
		                    						
		                          }				
							
			
						}
							
						}
						
						
						
	 
		 
		 populateMAP.put(sq.next(), voResultEntry.getMachineResult());
		 
		 //where clause
		 populateMAP.put(sq.next(), voResultEntry.getReqDNo());
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
         populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
         populateMAP.put(sq.next(), voResultEntry.getTestCode()+voResultEntry.getParameterCode());  
         
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}


/*
public  Inv_RequisitionRaisingPatientVO getPatGenderAge(String crNo,UserVO _UserVO)
{
	String query = "";		
	Map populateMAP = new HashMap();
	ResultSet rs = null;
	String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey = "SELECT.PATIENT_GENDER_AGE.HRGT_PATIENT_DTL";
	Sequence sq = new Sequence();

	Inv_RequisitionRaisingPatientVO invReqRaisingVO;

	populateMAP.put(sq.next(), crNo);
	
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
	*/





	/*
	 * public static String getReferenceRange( List<TestMandRefMasterVO >
	 * objRefRangeList, List<InvCriteriaCodeVO > objCriteriaCodeList, String
	 * strTestCode, String strTestParaCode, String strGender, String strAge) {
	 * String strCriteriaCode =null; String strReferanceRangeString = null;
	 * for(InvCriteriaCodeVO objCriteriaCodeVO : objCriteriaCodeList) {
	 * 
	 * if(strTestCode.equalsIgnoreCase(objCriteriaCodeVO.getTestCode()) &&
	 * strTestParaCode.equalsIgnoreCase(objCriteriaCodeVO.getParamterCode())) {
	 * strCriteriaCode = objCriteriaCodeVO.getCriteriaCode(); break; } }
	 * 
	 * for(TestMandRefMasterVO objRefRange : objRefRangeList) { if(
	 * InvestigationConfig.REFERENCE_RANGE_CRITERIA_NORMAL.equalsIgnoreCase(
	 * strCriteriaCode)) {
	 * if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) &&
	 * objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)) {
	 * if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
	 * strReferanceRangeString = objRefRange.getRangeTyp() + "$" +
	 * objRefRange.getHighValue() + "$" + objRefRange.getLowValue() + "$" +
	 * objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" +
	 * objRefRange.getSymbol() ; else strReferanceRangeString =
	 * objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +
	 * objRefRange.getRange() +"$" + objRefRange.getSymbol() ; // for range like >,<
	 * (unit,range,symbol) break; } }
	 * 
	 * 
	 * if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_AGE.equalsIgnoreCase(
	 * strCriteriaCode)&&objRefRange.getLowAge()!=null) {
	 * 
	 * 
	 * double lowAge=Double.valueOf(objRefRange.getLowAge());
	 * 
	 * if(objRefRange.getLowAgeUom().equals("1")) ; else
	 * if(objRefRange.getLowAgeUom().equals("2")) lowAge=lowAge/12; else
	 * lowAge=lowAge/365;
	 * 
	 * 
	 * 
	 * 
	 * double highAge=Double.valueOf(objRefRange.getHighAge());
	 * 
	 * 
	 * if(objRefRange.getHighAgeUom().equals("1")) ; else
	 * if(objRefRange.getHighAgeUom().equals("2")) highAge=highAge/12; else
	 * highAge=highAge/365;
	 * 
	 * //System.out.println("refRangelowAge"+lowAge);
	 * System.out.println("refRangehighAge"+strAge); String[]
	 * splitAge=strAge.split(" "); double Age=Double.valueOf(splitAge[0]); String
	 * ageUom=splitAge[1];
	 * 
	 * if(ageUom.equals("Yr")) ; else if(ageUom.equals("Wk")) Age=Age/52; else
	 * if(ageUom.equals("Mth")) Age=Age/12; else Age=Age/365;
	 * 
	 * 
	 * 
	 * //System.out.println("refRangeAge"+Age);
	 * 
	 * if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) &&
	 * objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&lowAge<=Age
	 * && highAge>=Age) { if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) //
	 * range from-to strReferanceRangeString = objRefRange.getRangeTyp() + "$" +
	 * objRefRange.getHighValue() + "$" + objRefRange.getLowValue() + "$" +
	 * objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" +
	 * objRefRange.getSymbol() ; else strReferanceRangeString =
	 * objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +
	 * objRefRange.getRange() +"$" + objRefRange.getSymbol() ; // for range like >,<
	 * (unit,range,symbol) break; }
	 * 
	 * 
	 * }
	 * 
	 * if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_GENDER.equalsIgnoreCase(
	 * strCriteriaCode)) {
	 * 
	 * System.out.println("refGender"); String gender=""; if(strGender.equals("M"))
	 * { gender="0"; } if(strGender.equals("F")) { gender="1"; }
	 * 
	 * System.out.println("refGender"+gender);
	 * 
	 * if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) &&
	 * objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&objRefRange
	 * .getGender().equals(gender)) {
	 * if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
	 * strReferanceRangeString = objRefRange.getRangeTyp() + "$" +
	 * objRefRange.getHighValue() + "$" + objRefRange.getLowValue() + "$" +
	 * objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" +
	 * objRefRange.getSymbol() ; else strReferanceRangeString =
	 * objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +
	 * objRefRange.getRange() +"$" + objRefRange.getSymbol() ; // for range like >,<
	 * (unit,range,symbol)
	 * 
	 * 
	 * 
	 * break; }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // added by change for ref range GENDER_AGE wise (13)
	 * 
	 * if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_GENDER_AGE.equalsIgnoreCase(
	 * strCriteriaCode)) {
	 * 
	 * System.out.println("refGender-age wise"); String gender="";
	 * if(strGender.equals("M")) { gender="0"; } if(strGender.equals("F")) {
	 * gender="1"; }
	 * 
	 * System.out.println("refGender"+gender);
	 * 
	 * if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) &&
	 * objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&objRefRange
	 * .getGender().equals(gender)) {
	 * 
	 * 
	 * 
	 * double lowAge=Double.valueOf(objRefRange.getLowAge());
	 * 
	 * if(objRefRange.getLowAgeUom().equals("1")) ; else
	 * if(objRefRange.getLowAgeUom().equals("2")) lowAge=lowAge/12; else
	 * lowAge=lowAge/365;
	 * 
	 * 
	 * 
	 * 
	 * double highAge=Double.valueOf(objRefRange.getHighAge());
	 * 
	 * 
	 * if(objRefRange.getHighAgeUom().equals("1")) ; else
	 * if(objRefRange.getHighAgeUom().equals("2")) highAge=highAge/12; else
	 * highAge=highAge/365;
	 * 
	 * //System.out.println("refRangelowAge"+lowAge);
	 * System.out.println("refRangehighAge"+strAge); String[]
	 * splitAge=strAge.split(" "); double Age=Double.valueOf(splitAge[0]); String
	 * ageUom=splitAge[1];
	 * 
	 * if(ageUom.equals("Yr")) ; else if(ageUom.equals("Wk")) Age=Age/52; else
	 * if(ageUom.equals("Mth")) Age=Age/12; else Age=Age/365;
	 * 
	 * if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) &&
	 * objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&lowAge<=Age
	 * && highAge>=Age) {
	 * 
	 * if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
	 * strReferanceRangeString = objRefRange.getRangeTyp() + "$" +
	 * objRefRange.getHighValue() + "$" + objRefRange.getLowValue() + "$" +
	 * objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" +
	 * objRefRange.getSymbol() ; else strReferanceRangeString =
	 * objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +
	 * objRefRange.getRange() +"$" + objRefRange.getSymbol() ; // for range like >,<
	 * (unit,range,symbol) break; } }
	 * 
	 * 
	 * }
	 * 
	 * } System.out.println("strReferanceRangeString"+strReferanceRangeString);
	 * return strReferanceRangeString; }
	 */




public static String getReferenceRange( List<TestMandRefMasterVO > objRefRangeList,  List<InvCriteriaCodeVO > objCriteriaCodeList, String strTestCode, String strTestParaCode, String strGender, String strAge)
	{
		String strCriteriaCode =null;
		String strReferanceRangeString = null;
		for(InvCriteriaCodeVO objCriteriaCodeVO : objCriteriaCodeList)
		{
			
			if(strTestCode.equalsIgnoreCase(objCriteriaCodeVO.getTestCode()) && strTestParaCode.equalsIgnoreCase(objCriteriaCodeVO.getParamterCode()))
			{
				strCriteriaCode = objCriteriaCodeVO.getCriteriaCode();
				break;
			}
		}
		
		for(TestMandRefMasterVO objRefRange : objRefRangeList)
		{
			if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_NORMAL.equalsIgnoreCase(strCriteriaCode))
			{
				if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) && objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode))
				{
					
					
					
					boolean isflag=true;
					
					if(objRefRange.getRangeTyp().equalsIgnoreCase("1")  )
					{

						if(objRefRange.getLowValue()==null)
						{
							objRefRange.setLowValue("-");
						}
						
						if(objRefRange.getHighValue()==null)
						{
							objRefRange.setHighValue("-");
						}
						
	                 }
					else
					{

						if(objRefRange.getRange()==null)
						{
							objRefRange.setRange("-");
						}
						
					}
					
					if(isflag==true)
					{
						
					if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
					strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getHighValue() + "$" +  objRefRange.getLowValue() + "$" + objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" + objRefRange.getSymbol()  ;
					else
						strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +  objRefRange.getRange() +"$" + objRefRange.getSymbol() ;	// for range like >,< (unit,range,symbol)
					break;
					
					}
				}
			}

			
			if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_AGE.equalsIgnoreCase(strCriteriaCode)&&objRefRange.getLowAge()!=null)
			{
				
				
				
				boolean isflag=false;
				
				
				System.out.println("REFERENCE_RANGE_CRITERIA_AGE wisee");
				
				String[] splitAgenew=strAge.split(" ");
				

				if(splitAgenew!=null && splitAgenew.length>=2 && splitAgenew[0]!=null && splitAgenew[1]!=null)
					  if(is_string_double_numeric(splitAgenew[0])) // if age exist
					  {
						  isflag=true;
					
						  
						  if(objRefRange.getLowAge()==null || objRefRange.getLowAge().equals(""))
							{
								objRefRange.setLowAge("0");
							}
							
							if(objRefRange.getHighAge()==null || objRefRange.getHighAge().equals(""))
							{
								objRefRange.setHighAge("0");
							}
							
							
						 if( objRefRange.getRangeTyp().equalsIgnoreCase("1"))
						 {
						 
							 if(objRefRange.getLowValue()==null)
								{
									objRefRange.setLowValue("-");
								}
								
								if(objRefRange.getHighValue()==null)
								{
									objRefRange.setHighValue("-");
								}
							 
						 }
						 else
						 {
							 if(objRefRange.getRange()==null)
								{
									objRefRange.setRange("-");
								}
							 
						 }
						 
						 
					  }
					  else
					  {
						  isflag=false;
					  }
					 
				
					
				
				if(isflag==true)
				{
				double lowAge=Double.valueOf(objRefRange.getLowAge());
				
				if(objRefRange.getLowAgeUom().equals("1"))
					;
				else if(objRefRange.getLowAgeUom().equals("2"))
					lowAge=lowAge/12;
				else
					lowAge=lowAge/365;
				
				
				
				
				double  highAge=Double.valueOf(objRefRange.getHighAge());
				

				if(objRefRange.getHighAgeUom().equals("1"))
					;
				else if(objRefRange.getHighAgeUom().equals("2"))
					highAge=highAge/12;
				else
					highAge=highAge/365;
				
				//System.out.println("refRangelowAge"+lowAge);
				System.out.println("refRangehighAge"+strAge);
				String[] splitAge=strAge.split(" ");
				double Age=Double.valueOf(splitAge[0]);
				String ageUom=splitAge[1];
				
				if(ageUom.equals("Yr"))
					;
				else if(ageUom.equals("Wk"))
					Age=Age/52;
				else if(ageUom.equals("Mth"))
					Age=Age/12;
				else
					Age=Age/365;
													
				
				
				//System.out.println("refRangeAge"+Age);
			  
				if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) && objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&lowAge<=Age && highAge>=Age)
				{
					if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
					strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getHighValue() + "$" +  objRefRange.getLowValue() + "$" + objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" + objRefRange.getSymbol() ;
					else
						strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +  objRefRange.getRange() +"$" + objRefRange.getSymbol() ;	// for range like >,< (unit,range,symbol)	
					break;
				}
				
				}
			}
			
			if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_GENDER.equalsIgnoreCase(strCriteriaCode))
			{
				
				System.out.println("refGender");
				String gender="";
				if(strGender.equals("M"))
				{
					gender="0";
				}
				if(strGender.equals("F"))
				{
					gender="1";
				}
				
				System.out.println("refGender"+gender);
				

				boolean isflag=true;
				
				if(objRefRange.getRangeTyp().equalsIgnoreCase("1")  )
				{

					if(objRefRange.getLowValue()==null)
					{
						objRefRange.setLowValue("-");
					}
					
					if(objRefRange.getHighValue()==null)
					{
						objRefRange.setHighValue("-");
					}
					
                 }
				else
				{

					if(objRefRange.getRange()==null)
					{
						objRefRange.setRange("-");
					}
					
				}
				
				
					 
				
				if(isflag==true)
				{
					
				
				if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) && objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&objRefRange.getGender().equals(gender))
				{
					if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
					strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getHighValue() + "$" +  objRefRange.getLowValue() + "$" + objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" + objRefRange.getSymbol() ;
					else
						strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +  objRefRange.getRange() +"$" + objRefRange.getSymbol() ;	// for range like >,< (unit,range,symbol)	
					
					

					break;
				}
				
				}
			
				
			}
			
			
			// added by change for ref range GENDER_AGE wise (13)
			
			if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_GENDER_AGE.equalsIgnoreCase(strCriteriaCode))
			{
				
				System.out.println("refGender-age wise");
				String gender="";
				if(strGender.equals("M"))
				{
					gender="0";
				}
				if(strGender.equals("F"))
				{
					gender="1";
				}
				
				System.out.println("refGender"+gender);
				
				if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) && objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&objRefRange.getGender().equals(gender))
				{
					
					
					System.out.println("refRangehighAge"+strAge);
					String[] splitAgenew=strAge.split(" ");
					
					boolean isflag=false;
					

					if(splitAgenew!=null && splitAgenew.length>=2 && splitAgenew[0]!=null && splitAgenew[1]!=null)
						  if(is_string_double_numeric(splitAgenew[0])) // if age exist
						  {
							  isflag=true;
						
							  
							  if(objRefRange.getLowAge()==null || objRefRange.getLowAge().equals(""))
								{
									objRefRange.setLowAge("0");
								}
								
								if(objRefRange.getHighAge()==null || objRefRange.getHighAge().equals(""))
								{
									objRefRange.setHighAge("0");
								}
								
								
							 if( objRefRange.getRangeTyp().equalsIgnoreCase("1"))
							 {
							 
								 if(objRefRange.getLowValue()==null)
									{
										objRefRange.setLowValue("-");
									}
									
									if(objRefRange.getHighValue()==null)
									{
										objRefRange.setHighValue("-");
									}
								 
							 }
							 else
							 {
								 if(objRefRange.getRange()==null)
									{
										objRefRange.setRange("-");
									}
								 
							 }
							 
							 
						  }
						  else
						  {
							  isflag=false;
						  }
					
					
					if(isflag==true)
					{
						
					double lowAge=Double.valueOf(objRefRange.getLowAge());
					
					if(objRefRange.getLowAgeUom().equals("1"))
						;
					else if(objRefRange.getLowAgeUom().equals("2"))
						lowAge=lowAge/12;
					else
						lowAge=lowAge/365;
					
					
					
					
					double  highAge=Double.valueOf(objRefRange.getHighAge());
					

					if(objRefRange.getHighAgeUom().equals("1"))
						;
					else if(objRefRange.getHighAgeUom().equals("2"))
						highAge=highAge/12;
					else
						highAge=highAge/365;
					
					//System.out.println("refRangelowAge"+lowAge);
					System.out.println("refRangehighAge"+strAge);
					String[] splitAge=strAge.split(" ");
					
					
				
					if(splitAge!=null && splitAge.length>=2 && splitAge[0]!=null && splitAge[1]!=null)
					isflag=is_string_double_numeric(splitAge[0]);
					
					
						
					double Age=Double.valueOf(splitAge[0]);
					String ageUom=splitAge[1];
					
					if(ageUom.equals("Yr"))
						;
					else if(ageUom.equals("Wk"))
						Age=Age/52;
					else if(ageUom.equals("Mth"))
						Age=Age/12;
					else
						Age=Age/365;
					
					if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) && objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&lowAge<=Age && highAge>=Age)
					{

					if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
					strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getHighValue() + "$" +  objRefRange.getLowValue() + "$" + objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" + objRefRange.getSymbol() ;
					else
						strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +  objRefRange.getRange() +"$" + objRefRange.getSymbol() ;	// for range like >,< (unit,range,symbol)	
					break;
					}
						}
						
						}
			
				
			}
			
		}
		System.out.println("strReferanceRangeString"+strReferanceRangeString);
		return strReferanceRangeString;
	}





public static boolean is_string_double_numeric(String data)
{
	    boolean isflag=false;
	
	 try {
	        Double d = Double.parseDouble(data);
	        isflag=true;
	        System.out.printf("valid no:"+d);
	    }
	    catch (NumberFormatException nfe) {
	    	isflag=false;
	        System.out.printf("not a valid number:");
	    }
	 
	 return isflag;
	 
}


public List<machineResultEntryVO> AjaxGetLabList(machineResultEntryVO vo, UserVO userVO) {
	
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
	String queryKey="SELECT.LAB_COMBO_NEW.HIVT_LABORATORY_MST";
	String query="";
	
	ResultSet rs=null;
	Connection conn=super.getTransactionContext().getConnection();
	
	Map populateMap= new HashMap();
	Sequence sq= new Sequence();
	List<machineResultEntryVO> listMachineTestReportNewVO=new ArrayList<machineResultEntryVO>();
	
	try 
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	
	try
	{
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		populateMap.put(sq.next(),userVO.getUserSeatId());
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
		rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);
		
		if(!rs.next()) { }
		else {
			rs.beforeFirst();
			while(rs.next()) {
				machineResultEntryVO vo2 = new machineResultEntryVO();
				HelperMethods.populateVOfrmRS(vo2, rs);
				
				listMachineTestReportNewVO.add(vo2);
			}
		}
		
	}
	catch (Exception e)
	{		 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	
	return listMachineTestReportNewVO;
}



public List<machineResultEntryVO> AjaxGetMachineList(machineResultEntryVO vo, UserVO userVO) {
	
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
	String queryKey="SELECT.LABTEST_MACHINE_NEW.HIVT_LABTEST_MST.MACHINE";
	String query="";
	
	ResultSet rs=null;
	Connection conn=super.getTransactionContext().getConnection();
	
	Map populateMap= new HashMap();
	Sequence sq= new Sequence();
	List<machineResultEntryVO> listmachineResultEntryVO=new ArrayList<machineResultEntryVO>();
	
	try 
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	
	try
	{
		populateMap.put(sq.next(), userVO.getHospitalCode());
	    populateMap.put(sq.next(), vo.getLabCode());
		
		rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);
		
		if(!rs.next()) { }
		else {
			rs.beforeFirst();
			while(rs.next()) {
				machineResultEntryVO vo2 = new machineResultEntryVO();
				HelperMethods.populateVOfrmRS(vo2, rs);
				
				listmachineResultEntryVO.add(vo2);
			}
		}
		
	}
	catch (Exception e)
	{		 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	
	return listmachineResultEntryVO;
}



public List<machineResultEntryVO> getPatientMachineResultEntrynew(machineResultEntryVO invmachineResultEntryVO, UserVO _UserVO)
{
	
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	Map populateMAP1 = new HashMap();

	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	
	String queryKey = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_NEWMACHINE";
	String queryKey_crno = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_patcrno1_NEWMACHINE";
	String queryKey_sampleno = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno_NEWMACHINE";
	String queryKey_sampleno_all = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_all_NEWMACHINE";
	
	String queryKey_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_MACHINEALL_NEWMACHINE";
	String queryKey_crno_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_patcrno1_MACHINEALL_NEWMACHINE";
	String queryKey_sampleno_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno_MACHINEALL_NEWMACHINE";
	String queryKey_sampleno_all_machineall = "SELECT.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_all_MACHINEALL_NEWMACHINE";
	
	
	String queryKeyUnmapped = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL";

	String queryKeyUnmapped_sampleno = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno";
	
	String queryKeyUnmapped_machineall = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_MACHINEALL";
	
	String queryKeyUnmapped_sampleno_machineall = "SELECT.UNMAPPED.MACHINE_RESULT_ENTRY.HMIT_RESULT_DTL_sampleno_MACHINEALL";
	
	
	List<InvCriteriaCodeVO > objCriteriaCodeList = InvestigationTemplateDataHelper.getInstance().getCriteriaCode();
	 List<TestMandRefMasterVO > objRefRangeList = InvestigationTemplateDataHelper.getInstance().getReferanceRange(_UserVO.getHospitalCode());
	
	
	try
	{
		
		if(invmachineResultEntryVO.getRecord().equals("1"))
		{
			
		
			if(invmachineResultEntryVO.getPatcrno1()==null && invmachineResultEntryVO.getSamplenoo()==null)
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey_machineall);
				}
			}
			
		
		
		if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey_crno);
			if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_crno_machineall);
			}
		}
		
		if(invmachineResultEntryVO.getSamplenoo()!=null && invmachineResultEntryVO.getPatcrno1()==null)
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno);
			if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno_machineall);
			}
		}
		
		if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()!=null)
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno_all);
			if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_sampleno_all_machineall);
			}
			
		}
		
		}else
		{
		       if(invmachineResultEntryVO.getSamplenoo()==null)	
		       {
			query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped);
			if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped_machineall);
			}
			
		       }else
		       {   query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped_sampleno);	  
		       if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					query = HelperMethodsDAO.getQuery(filename, queryKeyUnmapped_sampleno_machineall);
				}
		       }
		       
		  }
		
		}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
	Sequence sq1 = new Sequence();

	try
	{
		if(invmachineResultEntryVO.getRecord().equals("1"))
		{
			
		      populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getRecord());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getResultEntryDate());
				
				
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
				populateMAP1.put(sq1.next(), _UserVO.getHospitalCode());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getLabCode());
				}
				else
				{
					
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getMachineCode());
				}
				
				populateMAP1.put(sq1.next(), _UserVO.getHospitalCode());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP1.put(sq1.next(), invmachineResultEntryVO.getSampleCollDate());
				
				/*populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			
				
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

				
				
				
		populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getRecord());
		populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());*/
				
				populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());	
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());

				if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
				{
					
				}
				else
                 {
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
                 }
				
				if(invmachineResultEntryVO.getMachineCode().equals("%"))
				{
					//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					//populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
					//populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

				}
				else
				{
					populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());


					//populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

					if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
					{
						
					}
					else
					{
					populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
					}

				}

				if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
				{
				
					if(invmachineResultEntryVO.getMachineCode().equals("%")) {}
					else {
						populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
					}
				}
				else
				{
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
	//	populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
		
	/*	if(invmachineResultEntryVO.getMachineCode().equals("%"))
		{
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
				
		}
		else
		{
			populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
			
		}*/
		
	//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	//	populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

		

				if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
				{
				
					if(invmachineResultEntryVO.getMachineCode().equals("%")) {
						
						populateMAP.put(sq.next(), _UserVO.getHospitalCode());
						populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
						populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
					}
					else {
						populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode()); //pm
						}
				}
				else
				{
					populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode()); //pm
							}
				
		
		
		if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
		{
			
			populateMAP.put(sq.next(), invmachineResultEntryVO.getPatcrno1());
		}

		
		if(invmachineResultEntryVO.getSamplenoo()!=null && invmachineResultEntryVO.getPatcrno1()==null)
		{
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSamplenoo());
		}
		
		if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()!=null)
		{
			populateMAP.put(sq.next(), invmachineResultEntryVO.getPatcrno1());

			populateMAP.put(sq.next(), invmachineResultEntryVO.getSamplenoo());

		}
		
		if(invmachineResultEntryVO.getIsrepeattest()!=null && invmachineResultEntryVO.getIsrepeattest().equals("1"))
		populateMAP.put(sq.next(), "1");
		else 
			populateMAP.put(sq.next(), "0");	
		
	//	populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
		
		
		}
		else
		{
			
			/*populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());

			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());*/
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			
			if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
			}
			else
			{
				
				populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
			}
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			if(invmachineResultEntryVO.getMachineCode().equals("%"))
			{
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
			}
			else
			{
				
				populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
			}
			
			//populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
					
		       if(invmachineResultEntryVO.getSamplenoo()!=null)	
			populateMAP.put(sq.next(), invmachineResultEntryVO.getSamplenoo());

		       
				populateMAP.put(sq.next(), invmachineResultEntryVO.getSampleCollDate());
				populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());

				
		}
		
		if(invmachineResultEntryVO.getPatcrno1()!=null && invmachineResultEntryVO.getSamplenoo()==null)
		{
			////populateMAP.put(sq.next(), invmachineResultEntryVO.getResultEntryDate());	
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//	populateMAP.put(sq.next(), invmachineResultEntryVO.getLabCode());
		///	populateMAP.put(sq.next(), invmachineResultEntryVO.getMachineCode());
			
		//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			
		}

	
		 
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
	}
	
	PatientDetailVO[] dailyPatientVOs = null;
	List<machineResultEntryVO> lstInvmachineResultEntryVO = new ArrayList<machineResultEntryVO>();
	List<machineResultEntryVO> finalLstInvmachineResultEntryVO = new ArrayList<machineResultEntryVO>();
	ValueObject[] valueObjects = null;
	TemplateProcessingUSE tpu = new TemplateProcessingUSE();
	
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
			valueObjects = HelperMethods.populateVOfrmRS(machineResultEntryVO.class, rs);
			HashMap<String, String> reqMachineTestParaMapping = new HashMap<String, String>(); 

			//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
			for (int i = 0; i < valueObjects.length; i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				if(invmachineResultEntryVO.getRecord().equals("1"))
				{
				
					machineResultEntryVO ss = (machineResultEntryVO)valueObjects[i];
					String temp = ss.getReqDNo() + ss.getMachineTestParameterCode();
					
					if(reqMachineTestParaMapping.containsKey(temp))
					{
						
						
					}
					else {
						reqMachineTestParaMapping.put(temp, "1");
						machineResultEntryVO voo=(machineResultEntryVO)valueObjects[i];
					 
						if(invmachineResultEntryVO.getRecord().equals("1"))
						{
							
							if(voo.getPatCrNo()!=null )
								System.out.println("range call crno::"+voo.getPatCrNo());
						
							if(voo.getReqDNo()!=null )
								System.out.println("range call dno::"+voo.getReqDNo());
						
							if(voo.getTestCode()!=null)
							System.out.println("range call testcode::"+voo.getTestCode());
	

						String refRangeneww = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, voo.getTestCode(), voo.getParameterCode(), voo.getPatGender(),voo.getPatAge()); 

						voo.setAllrefrange(refRangeneww==null?"":refRangeneww);
						
							String finalranges	=	getrangeeentrynew(	voo.getReqDNo(),voo.getTestCode()+voo.getParameterCode(),voo.getTestCode(),voo.getParameterCode(),voo.getPatGender(),voo.getPatAge(),voo.getMachineResult(),_UserVO,refRangeneww);
							
							if(finalranges.contains("@@@"))
							{
								String arrayrange[]=finalranges.split("@@@") ;
								
								if(arrayrange.length>=1)
									voo.setRanges(arrayrange[0]);
								else
									voo.setRanges("");
								
								
								if(arrayrange.length>=2)
									voo.setIs_inrange(arrayrange[1]);
								else
									voo.setIs_inrange("0");
							
							}
							
						
							
								/*
								 * String finalranges = getrangeeentry(
								 * voo.getReqDNo(),voo.getTestCode()+voo.getParameterCode(),voo.getTestCode(),
								 * voo.getParameterCode(),voo.getPatGender(),voo.getPatAge(),voo.
								 * getMachineResult(),_UserVO);
								 * 
								 * String refRange = getReferenceRange(objRefRangeList, objCriteriaCodeList,
								 * voo.getTestCode(), voo.getParameterCode(),
								 * voo.getPatGender(),voo.getPatAge());
								 * 
								 * String rangeValue=refRange;
								 * 
								 * refRange=voo.getParameterCode()+"#@"+voo.getParaname()+"#@"+rangeValue+"#@"+
								 * voo.getMachineResult()+"#@";
								 * 
								 * 
								 * voo.setRanges(refRange);
								 * 
								 */			
						}
						else
						{
							voo.setRanges("");
							voo.setIs_inrange("0");
						}
						
						lstInvmachineResultEntryVO.add(voo);
						
					}
				
				}
				else
				{
					
					machineResultEntryVO voo=(machineResultEntryVO)valueObjects[i];
					
					if(invmachineResultEntryVO.getRecord().equals("1"))
					{
						
						if(voo.getPatCrNo()!=null )
							System.out.println("range call crno::"+voo.getPatCrNo());
					
						if(voo.getReqDNo()!=null )
							System.out.println("range call dno::"+voo.getReqDNo());
					
						if(voo.getTestCode()!=null)
						System.out.println("range call testcode::"+voo.getTestCode());
						
						String refRangeneww = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, voo.getTestCode(), voo.getParameterCode(), voo.getPatGender(),voo.getPatAge()); 

						voo.setAllrefrange(refRangeneww==null?"":refRangeneww);
						
							String finalranges	=	getrangeeentrynew(	voo.getReqDNo(),voo.getTestCode()+voo.getParameterCode(),voo.getTestCode(),voo.getParameterCode(),voo.getPatGender(),voo.getPatAge(),voo.getMachineResult(),_UserVO,refRangeneww);
							
						
						if(finalranges.contains("@@@"))
						{
							String arrayrange[]=finalranges.split("@@@") ;
							
							if(arrayrange.length>=1)
								voo.setRanges(arrayrange[0]);
							else
								voo.setRanges("");
							
							
							if(arrayrange.length>=2)
								voo.setIs_inrange(arrayrange[1]);
							else
								voo.setIs_inrange("0");
						
						}
						
							/*
							 * String refRange = getReferenceRange(objRefRangeList, objCriteriaCodeList,
							 * voo.getTestCode(), voo.getParameterCode(),
							 * voo.getPatGender(),voo.getPatAge());
							 * 
							 * voo.setRanges(refRange);
							 */
					}
					else
					{
						voo.setRanges("");
						voo.setIs_inrange("0");
					}
					
					lstInvmachineResultEntryVO.add(voo);
					
				//lstInvmachineResultEntryVO.add((machineResultEntryVO)valueObjects[i]);
				}
				
				/*if(lstInvmachineResultEntryVO.get(i).getReqDtlStatus().equals(InvestigationConfig.READY_RESULTPRINTING) || lstInvmachineResultEntryVO.get(i).getReqDtlStatus().equals(InvestigationConfig.REPORT_PDF_GEN))
				{
					if(lstInvmachineResultEntryVO.get(i).getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
					{						
						finalLstInvmachineResultEntryVO.add(lstInvmachineResultEntryVO.get(i));
						//status 13 and 26 added if its report is available after this process only
					}
				}
				else{
					finalLstInvmachineResultEntryVO.add(lstInvmachineResultEntryVO.get(i)); //add all other values
				}*/
			}
		}
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
		//	throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
	}
	return lstInvmachineResultEntryVO;
}	









public String getrangeeentry(	String reqdno,String parantparaCode,String testCode,String paraCode,String gender,String age,String val,UserVO _UserVO)
{

List<InvCriteriaCodeVO > objCriteriaCodeList = InvestigationTemplateDataHelper.getInstance().getCriteriaCode();
List<TestMandRefMasterVO > objRefRangeList = InvestigationTemplateDataHelper.getInstance().getReferanceRange(_UserVO.getHospitalCode());
TemplateProcessingUSE tpu = new TemplateProcessingUSE();
String ranges="";
try
{


if(reqdno!=null )
System.out.println("range call dno::"+reqdno);

if(testCode!=null)
System.out.println("range call testcode::"+testCode);


String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, gender,age); 
////////				lstInvResultValidationVO.get(i).getPatAge());
           //val+=refRange+"`";
String rangeValue=refRange;//paraValues[2];

//String rangeValue="";
//if(paraValues.length>2)
// rangeValue=paraValues[2];



String	properParaValues=paraCode+"#@"+"-"+"#@"+rangeValue+"#@"+val+"#@"+"`";
//2368#@Hemoglobin#@1$170$130$g/l$g/l$null#@12#@
//properParaValues+=parameters[iterate]+"`";

String rangee=getfinalrange(properParaValues,reqdno,testCode,parantparaCode,_UserVO.getHospitalCode());

ranges=rangee;

}

catch(Exception e)
{

}
finally {


return ranges;
//tx.close();
}

}




public static String getfinalrange(String ran,String reqdno,String testcode,String parantparacode,String hoscode)
{

	
	String range=ran;
    String[] parameters=range.split("`");
    int paraSize=parameters.length;
    String rangee="";
    //System.out.println("reqdno"+voPat.getRequisitionDNo()+"testcode"+voPat.getTestCode());
             
    for(int iterate=0;iterate<paraSize;iterate++)
    {
            String[] paraValues=parameters[iterate].split("#@");
            String paracode=paraValues[0];
            String paraName=paraValues[1];
            String refRange="";
            if(paraValues.length>2)
             refRange=paraValues[2];
            String displayRef="";
            String rangeTypeFinal = "";
            String[] refreValueFinal = null;
            
            String isoutofbound="0";
            //updated by krishnan nema on 17/10/2018
            String paraEntry="";
            if(paraValues.length>3)
            {
                    paraEntry=paraValues[3];
            if(paraValues[3].equals("--")  )
            {
                     paraEntry="";
            }
            else
            {
                     
            }
            }
            
            if(!paraEntry.contains("<"))
            paraEntry=paraEntry.replace("\r\n","<br>");
            if(paraEntry.contains("$"))
                    paraEntry=paraEntry.replace("$","<br>");
            
            boolean flagg=false;
             if(refRange!=null||!refRange.equals(""))
            {
            refRange=refRange.replace("$", "@");
            String[] refValues=refRange.split("@");
            refreValueFinal = refValues;
             if(refValues.length>1)
             {
                     String checkRangetyp=refValues[0];
                     rangeTypeFinal = checkRangetyp;
                     if(checkRangetyp.equals("1"))
                     {
                             String highValue=refValues[1];
                                    
                                    String lowValue=refValues[2];
                                    
                                    if((highValue.matches("\\d*\\.?\\d+") ) && (lowValue.matches("\\d*\\.?\\d+") ))
                                    {
                                            
                                            flagg=true;
                                    }
                                    String highValueUom=refValues[3];
                                    String lowValueUom=refValues[4];
                                     displayRef=lowValue+" - "+highValue+" "+highValueUom;
                     }
                     else if(checkRangetyp.equals("2"))
                     {
                             
                             String rangetyp=">";
                                    
                                    String tovalue=refValues[2];
                                    String tovalueunit=refValues[1];
                                    
                                    if( (tovalue.matches("\\d*\\.?\\d+") ))
                                    {
                                            
                                            flagg=true;
                                    }
                                    
                                     displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
                                    
                     } 
                             
                     else if(checkRangetyp.equals("3"))
                     {
                             String rangetyp=">=";
                                    
                                    String tovalue="";
                                                    if(refValues.length>2)
                                                    tovalue=refValues[2];
                                    String tovalueunit="";
                                    if(refValues.length>1)
                                                    tovalueunit=        refValues[1];
                                    
                                    if( (tovalue.matches("\\d*\\.?\\d+") ))
                                    {
                                            
                                            flagg=true;
                                    }
                                    
                                     displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
                                     
                     }
                     else if(checkRangetyp.equals("4"))
                     {
                             String rangetyp="<";
                                    
                             String tovalue="";
                                    if(refValues.length>2)
                                    tovalue=refValues[2];
                    String tovalueunit="";
                    if(refValues.length>1)
                                    tovalueunit=        refValues[1];
                                    if( (tovalue.matches("\\d*\\.?\\d+") ))
                                    {
                                            
                                            flagg=true;
                                    }
                                     displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
                                     
                     }
                     else if(checkRangetyp.equals("5"))
                     {
                             String rangetyp="<=";
                                    
                             String tovalue="";
                                    if(refValues.length>2)
                                    tovalue=refValues[2];
                    String tovalueunit="";
                    if(refValues.length>1)
                                    tovalueunit=        refValues[1];
                                    if( (tovalue.matches("\\d*\\.?\\d+") ))
                                    {
                                            
                                            flagg=true;
                                    }
                                     displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
                                     
                     }
            
             }
            }
            else  
                    displayRef="";
            
            /*  String paraEntry=paraValues[3];
            if(paraValues[3].equals("--")  )
            {
                     paraEntry="";
            }
            else
            {
                     
            }
            
            
            if(!paraEntry.contains("<"))
            paraEntry=paraEntry.replace("\r\n","<br>");
            if(paraEntry.contains("$"))
                    paraEntry=paraEntry.replace("$","<br>"); */
    
                    
                    //updated by krishnan nema on 17/10/2018
                    
                    boolean numeric = false;
            //        try {
                //Double num = Double.parseDouble(paraEntry);
                if (paraEntry.matches("\\d*\\.?\\d+") ) 
        {
                        numeric = true;
        }
            //} catch (NumberFormatException e) 
            else        {
        numeric = false;
            }
                    
            if(numeric && flagg)
            {
                    
                    if(refreValueFinal!=null)
                    {
                            if(rangeTypeFinal.equals("1")){
                                    
                                     String highValue="";
                                                     if(refreValueFinal.length>1)
                                     highValue= refreValueFinal[1];
                                     String lowValue="";
                                     if(refreValueFinal.length>2)
                                     lowValue= refreValueFinal[2];
                                     
                                     
                                     if((Float.valueOf(paraEntry) > Float.valueOf(highValue)) || (Float.valueOf(paraEntry) < Float.valueOf(lowValue))){
                                           //  String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
                                           //         mapList.add(valu);
                                    	 isoutofbound="1";
                                     }else{
                                             
                                     }
                                     
                            
                            }else if(rangeTypeFinal.equals("2")){
                                    
                                    String tovalue=refreValueFinal[2];
                                    if((Float.valueOf(paraEntry) < Float.valueOf(tovalue))){
                                             //String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
                                                 //   mapList.add(valu);
                                    	 isoutofbound="1";
                                     }else{
                                            
                                    	 if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
											{
                                    		 isoutofbound="1";
											}
                                            
                                    }
                                    
                            }else if(rangeTypeFinal.equals("3")){
                                    
                                    String tovalue=refreValueFinal[2];
                                    if((Float.valueOf(paraEntry) <= Float.valueOf(tovalue))){
                                         //    String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
                                              //      mapList.add(valu);
                                    	if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
                                    	 {
                                    		
                                    		isoutofbound="0";
                                    	 }
                                    	else
                                    	{		isoutofbound="1";
                                    	}
                                    }else{
                                             
                                    }
                                    
                                    
                            }else if(rangeTypeFinal.equals("4")){
                                    
                                    
                                    String tovalue=refreValueFinal[2];
                                    if((Float.valueOf(paraEntry) > Float.valueOf(tovalue))){
                                          //   String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
                                            //        mapList.add(valu);
                                    	 isoutofbound="1";
                                     }else{
                                            
                                    	 if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
											{
                                 		 isoutofbound="1";
											}
                                    	 
                                    }
                                    
                            }else if(rangeTypeFinal.equals("5")){
                            
                                    String tovalue=refreValueFinal[2];
                                    if((Float.valueOf(paraEntry) >= Float.valueOf(tovalue))){
                                           ///  String valu = voPat.getRequisitionDNo() + "#" + "null" + "#template#" + voPat.getTestCode() +paracode;
                                            //        mapList.add(valu);
                                    	if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
                                   	 {
                                    		isoutofbound="0";

                                    		
                                   	 }
                                   	else
                                   	{		isoutofbound="1";
                                   	}
                                     }else{
                                            
                                    }
                                    
                                    
                            }
                            else
                            {
                            	
                            }
                            
                            
                    }else{


   

                                        


                                           
                                            }
                    }else{
                           

                           




                            
                            
                    }
            
            
            System.out.println("range::"+displayRef);
            
            
            
            System.out.println("isoutofboud::"+isoutofbound);
            
            

            
            rangee= displayRef+"@@@"+isoutofbound ;
            
                    }
    
    return rangee  ;      
    
    
      //                      dnoMap.put(voPat.getRequisitionDNo(),mapList);
                    
   
                                   
                                    
                            


}




public String getrangeeentrynew(	String reqdno,String parantparaCode,String testCode,String paraCode,String gender,String age,String val,UserVO _UserVO,String refrangenew)
{

String ranges="";
try
{


if(reqdno!=null )
System.out.println("range call dno::"+reqdno);

if(testCode!=null)
System.out.println("range call testcode::"+testCode);

String refRange =refrangenew ;

//String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, gender,age); 

////////				lstInvResultValidationVO.get(i).getPatAge());
           //val+=refRange+"`";
String rangeValue=refRange;//paraValues[2];

//String rangeValue="";
//if(paraValues.length>2)
// rangeValue=paraValues[2];



String	properParaValues=paraCode+"#@"+"-"+"#@"+rangeValue+"#@"+val+"#@"+"`";
//2368#@Hemoglobin#@1$170$130$g/l$g/l$null#@12#@
//properParaValues+=parameters[iterate]+"`";

String rangee=getfinalrange(properParaValues,reqdno,testCode,parantparaCode,_UserVO.getHospitalCode());

ranges=rangee;

}

catch(Exception e)
{

}
finally {


return ranges;
//tx.close();
}

}



public String isparameterexist_fordepe(machineResultEntryVO voResultEntry, UserVO _UserVO,String testpara_code,String testcode)
{
	String paraCount=""; 
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey="SELECT_COUNT_TESTPARAMETER";
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
		 
		   populateMAP.put(sq.next(), voResultEntry.getReqDNo());
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
           populateMAP.put(sq.next(), testcode); 
           populateMAP.put(sq.next(), testpara_code);  
           
          /* populateMAP.put(sq.next(), _UserVO.getSeatId());
           populateMAP.put(sq.next(), voResultEntry.getMachineResult());
           populateMAP.put(sq.next(), voResultEntry.getParameterCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voResultEntry.getRefRange());*/ 
	//		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
	//		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
			
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		
		if (!rs.next())
		{
			//throw new HisRecordNotFoundException("");
			paraCount="";
		}
		else
		{
			paraCount=rs.getString(1);
		
			System.out.println("paracount");
		}
		
		
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			//throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	return paraCount;
}



public String Select_dep_val(machineResultEntryVO voResultEntry, UserVO _UserVO,String testpara_code,String testcode)
{
	String paraCount="0"; 
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	String filename= "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey="SELECT_GETDEPE_VALUE_TESTPARAMETER";
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
		 
		   populateMAP.put(sq.next(), voResultEntry.getReqDNo());
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
           populateMAP.put(sq.next(), testcode); 
           populateMAP.put(sq.next(), testpara_code);  
           
          /* populateMAP.put(sq.next(), _UserVO.getSeatId());
           populateMAP.put(sq.next(), voResultEntry.getMachineResult());
           populateMAP.put(sq.next(), voResultEntry.getParameterCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voResultEntry.getRefRange());*/ 
	//		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
	//		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
			
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		
		if (!rs.next())
		{
			//throw new HisRecordNotFoundException("");
			paraCount="0";
		}
		else
		{
			paraCount=rs.getString(1);
		
			System.out.println("paracount");
		}
		
		
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			//throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	return paraCount;
}



public void insertMachineResultEntryDtl_depe(machineResultEntryVO voResultEntry, UserVO _UserVO,String testpara,String test,String depe_val) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey ="INSERT.MACHINE.ENTRY.DETAIL.HIVT_PARAMETER_DTL";//INSERT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		 if(depe_val==null || depe_val.equals(""))
		 {
			 depe_val="";
		 }
		 else
		 {
			 depe_val=depe_val.trim();
			 
			 if(depe_val==null || depe_val.equals(""))
			 {
				 depe_val="--";
			 }
		 }
		 
		 if (depe_val!=null && depe_val.contains(">"))
		 {
			 depe_val=depe_val.replaceAll(">", "greater than ") ;
		 }
		 
		 if (depe_val!=null && depe_val.contains("<"))
		 {
			 depe_val=depe_val.replaceAll("<", "less than ") ;
		 }
		 
 
		 
		 
        populateMAP.put(sq.next(), voResultEntry.getReqDNo());

        populateMAP.put(sq.next(), test); 
        populateMAP.put(sq.next(), testpara);  
        populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), depe_val);
        populateMAP.put(sq.next(), testpara.substring(5, 9)); 
		populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), voResultEntry.getRefRange()); 
//		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
//		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
		
		

		
		
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}

	
public void updateMachineResultEntryDtl_depe(machineResultEntryVO voResultEntry, UserVO _UserVO,String testpara,String test,String depe_val) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = "new_investigation.transactions..investigationTransactionMachineQuery";
	String queryKey ="UPDATE_HIVT_PARAMETER_DTL";
	
	try {
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HHelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {	 
		
		
		
		if(depe_val==null || depe_val.equals(""))
		 {
			depe_val="";
		 }
		 else
		 {
			 depe_val=depe_val.trim();
			 
			 if(depe_val==null || depe_val.equals(""))
			 {
				 depe_val="--";
			 }
		 }
		
		 if (depe_val!=null && depe_val.contains(">"))
		 {
			 depe_val=depe_val.replaceAll(">", "greater than ") ;
		 }
		 
		 if (depe_val!=null && depe_val.contains("<"))
		 {
			 depe_val=depe_val.replaceAll("<", "less than ") ;
		 }
		
		 
		 
		 
		 populateMAP.put(sq.next(), depe_val);
		 
		 //where clause
		 populateMAP.put(sq.next(), voResultEntry.getReqDNo());
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
         populateMAP.put(sq.next(), test); 
         populateMAP.put(sq.next(), testpara);  
         
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}






}

