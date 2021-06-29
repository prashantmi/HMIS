package new_investigation.transactions.bo;

 
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.InvestigationListingConfig;
import new_investigation.transactions.demo;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingRespFB;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.transactions.dao.InvDuplicateResultReportPrintingDAO;
import new_investigation.transactions.dao.InvResultEntryDAO;
import new_investigation.transactions.dao.InvResultReValidationDAO;
import new_investigation.transactions.dao.InvResultReportPrintingDAO;
import new_investigation.transactions.dao.InvResultReportPrintingNewDAO;
import new_investigation.transactions.dao.InvResultValidationDAO;
import new_investigation.transactions.dao.InvResultReportPrintingRespDAO;
import new_investigation.transactions.dao.InvValueAuditDAO;
import new_investigation.transactions.dao.InvestigationBillingDAO;
import new_investigation.transactions.dao.InvestigationEssentialDAO;
import new_investigation.transactions.dao.OnlinePatientAcceptanceDAO;
import new_investigation.transactions.dao.OnlinePatientAcceptanceDAOi;
import new_investigation.transactions.dao.PackingListGenerationDAO;
import new_investigation.transactions.dao.SampleAcceptanceDAO;
import new_investigation.transactions.dao.SampleAcceptanceDAOi;
import new_investigation.transactions.dao.SampleCollectionCumAcceptanceDAO;
import new_investigation.transactions.dao.SampleCollectionDAO;
import new_investigation.transactions.dao.TestWiseConsumableDAO;
import new_investigation.transactions.dao.departmentSpecificResultEntryDAO;
import new_investigation.transactions.dao.externalInvestigationCaptureDAO;
import new_investigation.transactions.dao.filmUsedDAO;
import new_investigation.transactions.dao.filmUsedDAOi;
import new_investigation.transactions.dao.invAntibioticProcessDAO;
import new_investigation.transactions.dao.invFungusProcessDAO;
import new_investigation.transactions.dao.invReportAddendumDAO;
import new_investigation.transactions.dao.invReportHistoryDAO;
import new_investigation.transactions.dao.invReportInProcessDAO;
import new_investigation.transactions.dao.invStatusDashboardDAO;
import new_investigation.transactions.dao.machineEnquiryDAO;
import new_investigation.transactions.dao.machineResultEntryDAO;
import new_investigation.transactions.dao.reportDownloadProcessDAO;
import new_investigation.transactions.dao.testAvailabilityDAO;
import new_investigation.transactions.dao.viewExternalInvDAO;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.InvCriteriaCodeVO;
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.InvResultReportPrintingNewVO;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationRequisitionBillDtlVO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.RequistionHeaderVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.externalInvestigationCaptureVO;
import new_investigation.vo.filmUsedVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.invFungusProcessVO;
import new_investigation.vo.invReportAddendumVO;
import new_investigation.vo.invReportHistoryVO;
import new_investigation.vo.invReportInProcessVO;
import new_investigation.vo.machineEnquiryVO;
import new_investigation.vo.machineResultEntryVO;
import new_investigation.vo.reportDownloadProcessVO;
import new_investigation.vo.testAvailabilityVO;
import new_investigation.vo.viewExternalInvVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TestWiseConsumableVO;
//import mrd.transaction.dao.*;
//import mrd.transaction.delegate.*;
//import mrd.MrdConfig;
import new_investigation.vo.template.TriStringObject;
import new_investigation.vo.template.invStatusDashboardVO;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ibm.icu.util.Calendar;



import new_investigation.vo.InvResultReportPrintingRespVO;

/**
 * @author vinita1fajax
 * 
 */
public class InvResultReportPrintingRespBO implements InvResultReportPrintingRespBOi {
	
	Random obj = new Random();


		
	public Map  LabComboForResultReportPrintingResp(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();

		try
		{
			tx.begin();
			InvResultReportPrintingRespDAO invresultreportprintingdao = new InvResultReportPrintingRespDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=invresultreportprintingdao.LabComboForResultReportPrintingResp(invresultreportprintingvo,_UserVO);
			mp.put(InvestigationConfig.LAB_COMBO_FOR_RESULT_REPORT_PRINTING, labcombo);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}	
	
	
	public Map setResultReportPrintingRespEssentials(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultReportPrintingRespVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingRespVO>();
		List<InvResultReportPrintingRespVO> groupModified_lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingRespVO>();

		List labNoCombo=new ArrayList();
		List testCombo=new ArrayList();
		List sampleNoCombo=new ArrayList();
		List testGroupCombo=new ArrayList();
		List groupCode=new ArrayList();
	

		try
		{
			tx.begin();
			InvResultReportPrintingRespDAO invresultreportprintingdao = new InvResultReportPrintingRespDAO(tx);

			lstInvResultReportPrintingVO=invresultreportprintingdao.setResultReportPrintingRespEssentials(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
			
			
			
			
			for(InvResultReportPrintingRespVO tempVo:lstInvResultReportPrintingVO)
			{
				if(tempVo.getGroupCode()!=null)
				{
					if(!groupCode.contains(tempVo.getGroupCode()+tempVo.getPatReqNo()))
					{
						groupCode.add(tempVo.getGroupCode()+tempVo.getPatReqNo());
						
						groupModified_lstInvResultReportPrintingVO.add(tempVo);
					}
				}
				else
					groupModified_lstInvResultReportPrintingVO.add(tempVo);
				
				tempVo=null;
			}
			 System.gc();
			
			mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultReportPrintingVO);

			
			
			
			
			
			
			labNoCombo=invresultreportprintingdao.setLabNoComboEssentials(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,labNoCombo);
			  
				testCombo=invresultreportprintingdao.setTestComboEssentials(invresultreportprintingvo, _UserVO);
			 
			mp.put(InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY,testCombo);
			
			sampleNoCombo=invresultreportprintingdao.setSamplNoComboEssentials(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
			
			testGroupCombo=invresultreportprintingdao.setTestGroupComboEssentials(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY,testGroupCombo);
	
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	public Map saveResultReportPrintingRespDetails(List<InvResultReportPrintingRespVO> invresultreportprintingvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultReportPrintingRespVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingRespVO>();

		
		//InvResultReportPrintingVO invresultreportp=new InvResultReportPrintingVO();
		try
		{
			tx.begin();
			
			InvResultReportPrintingRespDAO invresultreportprintingdao = new InvResultReportPrintingRespDAO(tx);
			for(InvResultReportPrintingRespVO invresultreportprintvo:invresultreportprintingvo)
			{
				
				invresultreportprintingdao.updateResultReportPrintingRespDetailInRequisitionDtl(invresultreportprintvo, _UserVO);
				invresultreportprintingdao.updateResultReportPrintingRespDetailInRequisitionDtldialytbl(invresultreportprintvo, _UserVO);
				invresultreportprintingdao.updateResultReportPrintingRespDetailInRequisitionDtlhisotrytbl(invresultreportprintvo, _UserVO);
				
				
				
				 
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();  

			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}

	public Map setResultReportPrintingRespEssentialsOnLoad(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultReportPrintingRespVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingRespVO>();
		
		List labNoCombo=new ArrayList();
		List testCombo=new ArrayList();
		List sampleNoCombo=new ArrayList();
		List testGroupCombo=new ArrayList();
		List<InvResultReportPrintingRespVO> groupModified_lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingRespVO>();
		List groupCode=new ArrayList();


		try
		{
			tx.begin();
			InvResultReportPrintingRespDAO invresultreportprintingdao = new InvResultReportPrintingRespDAO(tx);

			lstInvResultReportPrintingVO=invresultreportprintingdao.setResultReportPrintingRespEssentialsOnLoad(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
			
			
			for(InvResultReportPrintingRespVO tempVo:lstInvResultReportPrintingVO)
			{
				if(tempVo.getGroupCode()!=null)
				{
					if(!groupCode.contains(tempVo.getGroupCode()+tempVo.getPatReqNo()))
					{
						groupCode.add(tempVo.getGroupCode()+tempVo.getPatReqNo());
						
						groupModified_lstInvResultReportPrintingVO.add(tempVo);
					}
				}
				else
					groupModified_lstInvResultReportPrintingVO.add(tempVo);
				
			}
			
			
			mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultReportPrintingVO);

			
			
			 
			labNoCombo=invresultreportprintingdao.setLabNoComboEssentialsOnLoad(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,labNoCombo);
			  
				testCombo=invresultreportprintingdao.setTestComboEssentialsOnLoad(invresultreportprintingvo, _UserVO);
			 
			mp.put(InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY,testCombo);
			
			sampleNoCombo=invresultreportprintingdao.setSamplNoComboEssentialsOnLoad(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
			
			testGroupCombo=invresultreportprintingdao.setTestGroupComboEssentialsOnLoad(invresultreportprintingvo, _UserVO);

			mp.put(InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY,testGroupCombo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}

	public Map pdfDetails(List<InvResultReportPrintingRespVO> invresultreportprintingvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultReportPrintingRespVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingRespVO>();

		
		InvResultReportPrintingRespVO invresultreportp=new InvResultReportPrintingRespVO();

		try
		{
			tx.begin();
			
			InvResultReportPrintingRespDAO invresultreportprintingdao = new InvResultReportPrintingRespDAO(tx);
			for(InvResultReportPrintingRespVO invresultreportprintvo:invresultreportprintingvo)
			{
				
				//invresultreportp=invresultreportprintingdao.getPdfName(invresultreportprintvo, _UserVO);

				invresultreportprintvo.setPdfFileName(invresultreportprintvo.getReportUrl());
				lstInvResultReportPrintingVO.add(invresultreportprintvo);
				
				
			}

			mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL,lstInvResultReportPrintingVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL,lstInvResultReportPrintingVO);

			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	public String  isfromFTPorMONGO( UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		String labcombo="";

		try
		{
			tx.begin();
			InvResultReportPrintingRespDAO invresultreportprintingdao = new InvResultReportPrintingRespDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=invresultreportprintingdao.isfromFTPorMONGO(_UserVO);
			


		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return labcombo;
	}	
	
	
	
			
}

