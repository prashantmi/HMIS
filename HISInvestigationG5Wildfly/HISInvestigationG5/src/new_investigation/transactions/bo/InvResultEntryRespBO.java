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
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
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
import new_investigation.transactions.dao.InvResultEntryRespDAO;
import new_investigation.transactions.dao.InvValueAuditDAO;
import new_investigation.transactions.dao.InvestigationBillingDAO;
import new_investigation.transactions.dao.InvResultEntryRespDAO;
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



import new_investigation.vo.InvResultValidationRespVO;

/**
 * @author vinita1fajax
 * 
 */
public class InvResultEntryRespBO implements InvResultEntryRespBOi {
	
	Random obj = new Random();

	public Map AjaxGetEntryReqnList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		Map mp=new HashMap();
		List<InvResultValidationRespVO> lstInvResultValidationRespVO=new ArrayList<InvResultValidationRespVO>();
		List<InvResultValidationRespVO> lstInvResultValidationRespVO1=new ArrayList<InvResultValidationRespVO>();

		List labNoCombo=new ArrayList();
		List testCombo=new ArrayList();
		List sampleNoCombo=new ArrayList();
		List testGroupCombo=new ArrayList();
		List groupCode=new ArrayList();
		List employeeNameCombo =new ArrayList();
		
		 List<InvCriteriaCodeVO > objCriteriaCodeList = InvestigationTemplateDataHelper.getInstance().getCriteriaCode();
		 List<TestMandRefMasterVO > objRefRangeList = InvestigationTemplateDataHelper.getInstance().getReferanceRange(_UserVO.getHospitalCode());
		 TemplateProcessingUSE tpu = new TemplateProcessingUSE();

		List<InvResultValidationRespVO> lstInvResultValidationRespVO_groupModified=new ArrayList<InvResultValidationRespVO>();
		try
		{
			tx.begin();
			InvResultEntryRespDAO invResultEntryRespDAO = new InvResultEntryRespDAO(tx);

			lstInvResultValidationRespVO=invResultEntryRespDAO.AjaxGetEntryReqnList(invResultValidationRespVO, _UserVO);
			
			for(InvResultValidationRespVO tempVo:lstInvResultValidationRespVO)
			{
				if(tempVo.getGroupCode()!=null)
				{
					if(!groupCode.contains(tempVo.getGroupCode()+tempVo.getRequisitionNo()))
					{
						groupCode.add(tempVo.getGroupCode()+tempVo.getRequisitionNo());
						
						lstInvResultValidationRespVO_groupModified.add(tempVo);
					}
				}
				else
					lstInvResultValidationRespVO_groupModified.add(tempVo);
				
			}
			
			mp.put("InvResultValGroupModifiedReqnList",lstInvResultValidationRespVO_groupModified);
			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationRespVO);
			
			employeeNameCombo=invResultEntryRespDAO.setEmployeeNameComboEssentials(invResultValidationRespVO, _UserVO);
			mp.put(InvestigationConfig.EMP_NAME_COMBO_FOR_RESULT_ENTRY,employeeNameCombo);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
	public  Map saveResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HttpSession session =_request.getSession();
		List   listResultEntryDtl =new ArrayList();
		ResultEntryVO resultEntryVO=new ResultEntryVO();
	

		boolean isComplete=true;
		List<ResultEntryVO> lstResultEntryVO=new ArrayList<ResultEntryVO>();
		 
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			InvResultEntryDAO objResultEntrtyDAO=new InvResultEntryDAO(tx);
			
			resultEntryVO=invResultentryVO.get(0);
			if(resultEntryVO.getCrNoReqNoString()!=null)
			for(int i =0;i<resultEntryVO.getCrNoReqNoString().length;i++)
			{				
				objResultEntrtyDAO.updateFinalRemarkEmpCodeInRequisitionHeader(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], resultEntryVO.getEmpNameWise()[i] ,_userVO);				
			}
			
			
			
			for(ResultEntryVO voInvResultEntry:invResultentryVO)
			{
				
                       String uploaddata="";
				
			
				
				//modified by krishnan nema on 28/01/2018 for save to draft
				if(voInvResultEntry.getIsSaveToDraft()!=null && voInvResultEntry.getIsSaveToDraft().equals("1")){
					voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS_SAVE_TO_DRAFT);
				}else{
				if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
					voInvResultEntry.setReqDtlStatus("13");
				else				
				voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);
				}
				
				objResultEntrtyDAO.updateResultEntryInRequisitionDtl(voInvResultEntry,_userVO);
				
				//added by prashant
				objResultEntrtyDAO.updateIndicationInRequisitionheader(voInvResultEntry,_userVO);
				objResultEntrtyDAO.commentsupdateResultEntryInRequisitionDtl(voInvResultEntry,_userVO);

				if(voInvResultEntry.getIsSaveToDraft()!=null && voInvResultEntry.getIsSaveToDraft().equals("1")){
					objResultEntrtyDAO.updateResultEntryInRequisitionDtlDraftLog(voInvResultEntry,_userVO);
				}
				
				if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				{
				objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
				}		
				
			
			}
			
			
			
			/*String Ishyperlink="";
			for(ResultEntryVO voInvResultEntry:invResultEntryForParameterDtlVO)
			{
				Ishyperlink=voInvResultEntry.getIsHyperLink();
			}*/
			
		/*	if(Ishyperlink.equals("hyperlink"))*/
				
				
				
			
			
				
			int count=1;
			for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultEntryForParameterDtlVO)
			{
				
				
				if(_request.getAttribute("getuploadedfiledata")!=null)
				{
					Map<String,String> mpo=(Map<String,String>)_request.getAttribute("getuploadedfiledata");
					
					if(mpo.containsKey(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()+voInvResulEntryForParaMeterDtl.getParantParaCode()))
					{
						String finaldata=mpo.get(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()+voInvResulEntryForParaMeterDtl.getParantParaCode());
						System.out.println("==============================================="+finaldata);
						
						voInvResulEntryForParaMeterDtl.setFileuploaddata(finaldata.split("@@")[1].split(",")[1]);
						voInvResulEntryForParaMeterDtl.setFilename(finaldata.split("@@")[0]);
						
					}
					
				}
				
				
				if(voInvResulEntryForParaMeterDtl.getFinalechovalue()!=null && !voInvResulEntryForParaMeterDtl.getFinalechovalue().equals(""))
				{
					objResultEntrtyDAO.insertechodata(voInvResulEntryForParaMeterDtl,_userVO);

				}
				
				String parentid=voInvResulEntryForParaMeterDtl.getParantParaCode();
				
				String Ishyperlink="";
				Ishyperlink=voInvResulEntryForParaMeterDtl.getIsHyperLink();
				 if(Ishyperlink.equals("-"))
				 {
					 
					 String count1=objResultEntrtyDAO.isparameterexist( voInvResulEntryForParaMeterDtl,  _userVO);
						
						if(count1.equals("") || count1.equals("0"))
							objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
						else
							objResultEntrtyDAO.updatetestparameterdtl(voInvResulEntryForParaMeterDtl,_userVO);

						
				  //objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
				  
				
				
				 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
			      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
			      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

			      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
			      
			      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
					getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
					
					
				String status="";
				if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				{
					status="13";
				     	
				}
				else
				{
					status="7";
				}
				
				if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
				  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

				if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
					objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
				
				
				 }
				 else
				 {
					
					 
					 
					 
					 Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

                      if(mpBilled==null)
                      {
     					 
     					  mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);

                      }
                      else
                      {
                    	  Map<String,List<antibioticprocessVO>>	  mpBilledfungus= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
                    	  
                    	  if(mpBilledfungus!=null)
                    	  mpBilled.putAll(mpBilledfungus);
                    	  
                      }
					 
					 if(mpBilled!=null)
	                    {
	            			List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

	            			boolean flag=false;
	          Iterator itr1=mpBilled.keySet().iterator();
	          while(itr1.hasNext())
		 		{
	        	  
		 			String organisgm1=(String)itr1.next();
	            
		 			 
		 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm1);
		 			
		 			int rowSpanSize=lstVOSample.size();
		 			
		 			 
		 			 
		 			for(int k=0;k<lstVOSample.size();k++)
		 			{
		 				antibioticprocessVO voo=lstVOSample.get(k);
		 				
		 				 if(voo.getRequisitionDNo().equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && voo.getTestParaCode().equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
						  {
						   objResultEntrtyDAO.insertHyperLinkDetailsNew(voInvResulEntryForParaMeterDtl,_userVO,voo);
						 flag=true;
						  }
		 				 
		 				  String antibioticCode="";
						  String organismCode="";
						  String selectval="";
						  String reqdno="";
						  String testparacode="";
						  
		 				
		 			}
		 			
		 			  
		 		}
	          
	          
	          if(flag==true)
			  {
				  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") && !voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
				  {
					  String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
					  
					  for(int i11=0;i11<tbl1.length;i11++)
					  {
					String tbl=  tbl1[i11];
					
					String[] values=tbl.split("\\$\\$");
					
					if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
					{
					voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
				    }
					  }
					  
				  }
			
                             if( !voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
                             {
                            	 
                            	 String count1=objResultEntrtyDAO.isparameterexist( voInvResulEntryForParaMeterDtl,  _userVO);
         						
         						if(count1.equals("") || count1.equals("0"))
         							objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
         						else
         							objResultEntrtyDAO.updatetestparameterdtl(voInvResulEntryForParaMeterDtl,_userVO);

         		
				  
					
					 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
				      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
				      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

				      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
				      
				      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
						getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO, tx);
					
					
					
                             }
					String status="";
					if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
					{
						status="13";
					     	
					}
					else
					{
						status="7";
					}
					
					if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
					  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

					if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
						objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
					
				  
			  }
	          
	                    }
					 else
					 {
						 String antibioticCode="";
						  String organismCode="";
						  String selectval="";
						  
						// objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
						
						  if( !voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
                          {
                         	 
							  String count1=objResultEntrtyDAO.isparameterexist( voInvResulEntryForParaMeterDtl,  _userVO);
								
								if(count1.equals("") || count1.equals("0"))
									objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
								else
									objResultEntrtyDAO.updatetestparameterdtl(voInvResulEntryForParaMeterDtl,_userVO);

					
					 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
				      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
				      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

				      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
				      
				      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
				      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
											
					
					
                          }
						  

							String status="";
							if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
							{
								status="13";
							     	
							}
							else
							{
								status="7";
							}
							
							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
							  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
								objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
							
						  
					 }
					 
					 
					 
					 
					 
					/* if(!fb.getSelectValuemapping().equals(""))
							{
								
								 String[] selectvaluemapping=fb.getSelectValuemapping().split("@");
						
						for(int i1=0;i1<selectvaluemapping.length;i1++)
							 {
								 
								 String mappingvalue=selectvaluemapping[i1];
						  String[] val=mappingvalue.split("\\$\\$\\$");
						  int len=val.length;
						  boolean flag=false;
						  for(int i=0;i<len;i++)
						  {
							  String[] value=val[i].split("\\$\\$");
							  System.out.println("chandan:"+value);
							  String antibioticCode="";
							  String organismCode="";
							  String selectval="";
							  String reqdno="";
							  String testparacode="";
							  
							  organismCode   =value[0];
							  antibioticCode=value[1];
							   selectval=value[2];
							  reqdno=value[3];
							  testparacode=value[4];
							  if(reqdno.equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && testparacode.equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
							  {
							   objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
							 flag=true;
							  }
						  }
						  if(flag==true)
						  {
							  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals(""))
							  {
								  String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
								  
								  for(int i11=0;i11<tbl1.length;i11++)
								  {
								String tbl=  tbl1[i11];
								
								String[] values=tbl.split("\\$\\$");
								
								if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
								{
								voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
							    }
								  }
								  
							  }
						
							  objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
						  }
						  }
				
						 
						
						}
							 else
							  {
								  String antibioticCode="";
								  String organismCode="";
								  String selectval="";
								  
								// objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
								  objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							  }*/
				 }
					 
				 
			//	if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				//{
				//objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResulEntryForParaMeterDtl,_userVO);
			//	}
				//listResultEntryDtl.add("Test Name= "+voInvResultEntry.getTestName()+"Value=" +voInvResultEntry.getResultEntryValue());
				 count++;
			}	 
			
			
			
			
			
			//Put List in Map
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultEntryDtl);
			 

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
	
	
		
	public  Map modifyResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB _fb)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List   listResultEntryDtl =new ArrayList();
		HttpSession session=_request.getSession();

		boolean isComplete=true;
		List<ResultEntryVO> lstResultEntryVO=new ArrayList<ResultEntryVO>();
		ResultEntryVO resultEntryVO=new ResultEntryVO();
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			InvResultEntryDAO objResultEntrtyDAO=new InvResultEntryDAO(tx);
			
			resultEntryVO=invResultentryVO.get(0);
			if(resultEntryVO.getCrNoReqNoString()!=null)
			for(int i =0;i<resultEntryVO.getCrNoReqNoString().length;i++)
			{				
				objResultEntrtyDAO.updateFinalRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], _userVO);				
			}
			
			
			for(ResultEntryVO voInvResultEntry:invResultentryVO)
			{
				
				if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY)){
					voInvResultEntry.setReqDtlStatus("13");
				}else if(voInvResultEntry.getIsSaveToDraft()!=null && voInvResultEntry.getIsSaveToDraft().equals("1")){
					voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS_SAVE_TO_DRAFT);
				}
				else				
				voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);
				
				
				
				
				
				objResultEntrtyDAO.updateModifyResultEntryInRequisitionDtl(voInvResultEntry,_userVO);
				
				/* Added by Prashant */		
				objResultEntrtyDAO.updateIndicationInRequisitionheader(voInvResultEntry,_userVO);
				
				if(voInvResultEntry.getIsSaveToDraft()!=null && voInvResultEntry.getIsSaveToDraft().equals("1")){
					objResultEntrtyDAO.updateResultEntryInRequisitionDtlDraftLog(voInvResultEntry,_userVO);
				}
				
				objResultEntrtyDAO.commentsupdateResultEntryInRequisitionDtl(voInvResultEntry,_userVO);

				/*if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				{
				objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
				}	*/	
				
			
			}
			
			
			//added by chandan for hyperlink
			
			
			
			for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultEntryForParameterDtlVO)
			{

				if(voInvResulEntryForParaMeterDtl.getFinalechovalue()!=null && !voInvResulEntryForParaMeterDtl.getFinalechovalue().equals(""))
				{
					objResultEntrtyDAO.updateechodata(voInvResulEntryForParaMeterDtl,_userVO);

				}
				
				if(_request.getAttribute("getuploadedfiledata")!=null)
				{
					Map<String,String> mpo=(Map<String,String>)_request.getAttribute("getuploadedfiledata");
					
					if(mpo.containsKey(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()+voInvResulEntryForParaMeterDtl.getParantParaCode()))
					{
						String finaldata=mpo.get(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()+voInvResulEntryForParaMeterDtl.getParantParaCode());
						System.out.println("==============================================="+finaldata);
						
						voInvResulEntryForParaMeterDtl.setFileuploaddata(finaldata.split("@@")[1].split(",")[1]);
						voInvResulEntryForParaMeterDtl.setFilename(finaldata.split("@@")[0]);
						
					}
					
				}
				
				String parentid=voInvResulEntryForParaMeterDtl.getParantParaCode();
				String Ishyperlink="";
				Ishyperlink=voInvResulEntryForParaMeterDtl.getIsHyperLink();
				 if(Ishyperlink.equals("-"))
				 {
					 
				//objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
				 
					 String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
					 String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
					 if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
						{
						  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
						} else
						{
						if(isexist!=null && !isexist.equals("0"))
						 {objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						 
						 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
					      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
					      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

					      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
					      
					      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
					      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
							
					      
						 }
						else
							{objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							
							
							 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
						      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
						      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

						      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
						      
						      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
						      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
																	
								
							
							}
						}
			/*
			 * if(isexist!=null && !isexist.equals("0"))
			 * {objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,
			 * _userVO);} else
			 * {objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,
			 * _userVO);}
			 */
						
						String status="";
						if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
							status="13";
						else				
						status="7";
						
						if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
							  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
								objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
							
							
				objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
				
				

				 }
				 else
				 {
					 
					 if(!_fb.getSelectValuemapping().equals(""))
						{
							
							 String[] selectvaluemapping=_fb.getSelectValuemapping().split("@");
							 objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO);
							 
							 
							 //Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

							 
							 
							 Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

							    if(mpBilled==null)
			                      {
			     					 
			     					  mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);

			                      }
			                      else
			                      {
			                    	  Map<String,List<antibioticprocessVO>>	  mpBilledfungus= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
			                    	  
			                    	  if(mpBilledfungus!=null && mpBilledfungus.size()>0)
			                    	  mpBilled.putAll(mpBilledfungus);
			                    	  
			                      }
		                      
				       if(mpBilled!=null)
			                {
			            			List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

			            			boolean flag=false;
			               Iterator itr1=mpBilled.keySet().iterator();
			                while(itr1.hasNext())
				 	     	{
			        	  
			                	String organisgm1=(String)itr1.next();
					            
					 			 
					 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm1);
					 			
					 			int rowSpanSize=lstVOSample.size();
					 			for(int k=0;k<lstVOSample.size();k++)
					 			{
					 				antibioticprocessVO voo=lstVOSample.get(k);
					 				
					 				 if(voo.getRequisitionDNo().equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && voo.getTestParaCode().equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
									  {
									   objResultEntrtyDAO.insertHyperLinkDetailsNew(voInvResulEntryForParaMeterDtl,_userVO,voo);
									 flag=true;
									  }
					 				 
					 				  String antibioticCode="";
									  String organismCode="";
									  String selectval="";
									  String reqdno="";
									  String testparacode="";
									  
					 				
					 			}
					 			
					 			 if(flag==true)
								  {
					 				  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || !voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
									  {
										  String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
										  
										  for(int i11=0;i11<tbl1.length;i11++)
										  {
										String tbl=  tbl1[i11];
										
										String[] values=tbl.split("\\$\\$");
										
										if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
										{
										voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
										
										String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
										//added by prashantMi for echo modify
										  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
										
										 if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
											{
											  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
											} else
											{
										if(isexist!=null && !isexist.equals("0"))
										{ objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
										
										 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
									      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
									      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

									      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
									      
									      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
									      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
											
									      
										}else
										{
											objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
										
											
											 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
										      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
										      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

										      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
										      
										      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
										      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
																									
												
										}	}
										
										String status="";
										if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
											status="13";
										else				
										status="7";
										
										if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
											  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

											if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
												objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
											
											
										
										//objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
										}
										  }
										
										  
									  }
								
									  
								  }
					 			
				 		    }
			            }
				/*	for(int i1=0;i1<selectvaluemapping.length;i1++)
						 {
							 
							 String mappingvalue=selectvaluemapping[i1];
					  String[] val=mappingvalue.split("\\$\\$\\$");
					  int len=val.length;
					  boolean flag=false;
					  for(int i=0;i<len;i++)
					  {
						  String[] value=val[i].split("\\$\\$");
						//  System.out.println("chandan:"+value);
						  String antibioticCode="";
						  String organismCode="";
						  String selectval="";
						  String reqdno="";
						  String testparacode="";
						  
						  organismCode   =value[0];
						  antibioticCode=value[1];
						   selectval=value[2];
						  reqdno=value[3];
						  testparacode=value[4];
						  if(reqdno.equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && testparacode.equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
						  {
							  
							  objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
						   objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
						 flag=true;
						  }
					  }
					  
					  if(flag==true)
					  {
						  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals(""))
						  {
							  String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
							  
							  for(int i11=0;i11<tbl1.length;i11++)
							  {
							String tbl=  tbl1[i11];
							
							String[] values=tbl.split("\\$\\$");
							
							if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
							{
							voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
						    }
							  }
							  
						  }
						  
						  
						  String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
							
							if(isexist!=null && !isexist.equals("0"))
							 objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							else
								objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							
							objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
					  }
					  }*/
					  
					  }
					 else
					  {
						  String antibioticCode="";
						  String organismCode="";
						  String selectval="";
						String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
						
						//added by prashantMi for echo modify
						  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
						  if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
							{
							  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
							} else
							{
						if(isexist!=null && !isexist.equals("0"))
						{ objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						
						 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
					      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
					      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

					      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
					      
					      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
					      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
							
					      
						}
						else
							{objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							
							
							 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
						      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
						      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

						      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
						      
						      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
						      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
																	
								
							}
							}	
						
						String status="";
						if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
							status="13";
						else				
						status="7";
						
						
						if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
							  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
								objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
							
							
							
						//System.out.println("i============================="+i);
						 // objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
						 // objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
					  }
					  
				 }
			//	if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				//{
				//objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResulEntryForParaMeterDtl,_userVO);
			//	}
				//listResultEntryDtl.add("Test Name= "+voInvResultEntry.getTestName()+"Value=" +voInvResultEntry.getResultEntryValue());
			}	 
			//Put List in Map
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultEntryDtl);
			 

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	

	 
	
 

	public boolean checkSampleNoDuplicacy(SampleCollectionCumAcceptanceVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		boolean isTempSampleNoPresent=false;
	try {
			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionDAO=new SampleCollectionCumAcceptanceDAO(tx);
			isTempSampleNoPresent=objSampleCollectionDAO.checkSampleNoDuplicacy(voSample, _UserVO);
			
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
		} finally {

			tx.close();
		}

		return isTempSampleNoPresent;
	}

	public String checkAutoGenFormateSampleCollCum(SampleCollectionCumAcceptanceVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		List isFormatePresent=null;

		List<Inv_SampleCollectionVO> voSample1=null;
		
       String Formate="null";

      // String Formate="null+#+null+#+null+#+null+#+null+#+null+#+null";
       
		try {

			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionDAO=new SampleCollectionCumAcceptanceDAO(tx);
			voSample1=objSampleCollectionDAO.checkAutoGenFormateSampleCollCum(voSample, _UserVO);


			int size=voSample1.size();
			
			if(size!=0)
			{
				StringBuilder sb = new StringBuilder();
				//all but last
				for(Inv_SampleCollectionVO voSampleVo:voSample1 ) {
					sb.append(voSampleVo.getSampleNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningSampleNo()+"#"+voSampleVo.getPatType()+"#"+voSampleVo.getConfigLab()+"#"+voSampleVo.getConfigType()+"#"+voSampleVo.getConfigSeq()+"#"+voSampleVo.getConfigTest()+"#"+voSampleVo.getConfigArea()  );
					sb.append("#");
				}
				 
				Formate=sb.toString();
				System.out.println("the Formate"+Formate);
			}


		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
		} finally {

			tx.close();
		}

		return Formate;
	}



	public Map getBilledPatientList(List<String> reqList,SampleCollectionCumAcceptanceVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();

		String reqType="";



		List<SampleCollectionCumAcceptanceVO> lstTestBased= new ArrayList<SampleCollectionCumAcceptanceVO>();

		List<SampleCollectionCumAcceptanceVO> lstUnBilledPatList=null;
		List<SampleCollectionCumAcceptanceVO> lstBilledPatList=null;

		//Change as per Astha Ma'ms suggestion
		Map<String,List<SampleCollectionCumAcceptanceVO>> mpBilledPat=new HashMap<String, List<SampleCollectionCumAcceptanceVO>>();
		Map<String,List<SampleCollectionCumAcceptanceVO>> mpUnBilledPat=new HashMap<String, List<SampleCollectionCumAcceptanceVO>>();

		List lstUOMCombo=null;
		List lstContainerCombo=null;



		try {

			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionDAO=new SampleCollectionCumAcceptanceDAO(tx);

			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);

			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);

              
			if(voSample.getPatStatus().equals("IPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_IPD; //1
			else if(voSample.getPatStatus().equals("OPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_OPD; //2
			else
				reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3

			for(String str:reqList)
			{

				String reqNo=str.split("#")[1]; //CRNO#ReqNo#labCode#sampleCode#index
				String labCode=str.split("#")[2]; //CRNO#ReqNo#labCode#sampleCode#index
				String sampleCode=str.split("#")[3]; //CRNO#ReqNo#labCode#sampleCode#index

				boolean isBilled=false;
				//SampleCollectionCumAcceptanceVO voSampleCollection=new SampleCollectionCumAcceptanceVO();
				lstTestBased =objSampleCollectionDAO.getBilledPatList(reqNo,reqType,_UserVO);
				if(lstTestBased!=null)
				{
					for(SampleCollectionCumAcceptanceVO voSampleCollection:lstTestBased)
					{
						sampleCode=voSampleCollection.getSampleCode()==null?sampleCode:voSampleCollection.getSampleCode();
						lstBilledPatList= mpBilledPat.get(reqNo+"#"+labCode+"#"+sampleCode);
						lstUnBilledPatList= mpUnBilledPat.get(reqNo+"#"+labCode+"#"+sampleCode);

						if(lstBilledPatList==null||!(lstBilledPatList.size()>0))
							lstBilledPatList=new ArrayList<SampleCollectionCumAcceptanceVO>();
							if(lstUnBilledPatList==null||!(lstUnBilledPatList.size()>0))
								lstUnBilledPatList=new ArrayList<SampleCollectionCumAcceptanceVO>();	

								voSampleCollection.setPatCRNo(voSample.getPatCRNo());
								voSampleCollection.setRequisitionNo(reqNo);
								
								if(voSampleCollection!=null)
								{
									String billNo=voSampleCollection.getBillDetail().replace("^", "#").split("#")[0];
									if(!billNo.equals("0")&& billNo!=null)
									{
										isBilled=true;
										voSampleCollection.setBillNo(billNo);
									}
									else
										isBilled=false;
								}
								if(isBilled)
									lstBilledPatList.add(voSampleCollection);
								else
									lstUnBilledPatList.add(voSampleCollection);

								mpBilledPat.put(reqNo+"#"+labCode+"#"+sampleCode,lstBilledPatList); // Putting Map as per Astha Ma'm Suggestion
								mpUnBilledPat.put(reqNo+"#"+labCode+"#"+sampleCode,lstUnBilledPatList); // Putting Map as per Astha Ma'm Suggestion
					}
				}
			}

			mp.put(InvestigationConfig.LIST_UOM_COMBO,lstUOMCombo);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO,lstContainerCombo);
			mp.put(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED,mpUnBilledPat);
			mp.put(InvestigationConfig.MAP_PAT_SAMPLE_BILLED,mpBilledPat);

		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
		} finally {

			tx.close();
		}

		return mp;
	}
	public  List saveSampleCollectionDetail(Map<String,Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>> mp,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listSamples=new ArrayList();
		String collAreaBasedSampleNo="";

		List<SampleCollectionCumAcceptanceVO> lstAutoSampleNOConfig=new ArrayList<SampleCollectionCumAcceptanceVO>();
		try
		{    
			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionDAO=new SampleCollectionCumAcceptanceDAO(tx);

			PackingListGenerationDAO objPackingListDAO=new PackingListGenerationDAO(tx);
			
			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);

			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();

			// Step-1 :: Get Map Of Requiasitions from Map of CrNo's
			Set setCrNo=mp.keySet();

			Iterator itrCrNo=setCrNo.iterator();

			//iterate over Crno's
			while(itrCrNo.hasNext())
			{
				// Get CrNo from iterator
				String crNo=(String)itrCrNo.next();

				// Get Map of Requisitions from Map of Cr No's
				Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>> mpReqNo=mp.get(crNo);
				Set setReqNo=mpReqNo.keySet();

				Iterator itrReqNo=setReqNo.iterator();

				//Iterate over ReqNo's
				while(itrReqNo.hasNext())
				{
					// Get Requistion No from iterator
					String reqNo=(String)itrReqNo.next();

					// Update in Requisition Header table for Mobile No, emailId,Address
					boolean isReqHeaderUpdated=false;

					// Get Map of SampleCodes from Map of requisitions
					Map<String,List<SampleCollectionCumAcceptanceVO>> mpSample=mpReqNo.get(reqNo);

					//Getting set of Sample Codes
					Set setSample=mpSample.keySet();

					Iterator itrSample=setSample.iterator();

					//iterate over Samples
					while(itrSample.hasNext()) 
					{
						// Insert in Sample Dtl table 
						boolean isSampleDtlInsert=false;

						//Get Sample Code from Iterator
						String sampleCodeHashLabCode=(String)itrSample.next();
						String sampleCode=sampleCodeHashLabCode.split("#")[0];
						String labCode=sampleCodeHashLabCode.split("#")[1];

						//changed by ashu
						
						// Required HospitalCode,LabCode
						//Generate Packing List No Sequence  for each lab
						String sequence_Hash_yymmdd=objPackingListDAO.generatePackingListNoSequence(labCode, _userVO);  // Returns sequence#yymmdd

						String sequence=sequence_Hash_yymmdd.split("#")[0];
						String yymmdd=sequence_Hash_yymmdd.split("#")[1];
						String strPackingListNo = labCode+yymmdd+sequence;

						//Logic to check the sequence is '1001' 
						if(sequence.equals(InvestigationConfig.PACKINGLIST_NO_SEQUENCE_INVESTIGATION)) //1001
						{
							// Insert in PackingListNo Sequence Maintainer Table
							objPackingListDAO.insertPackingListNoSequenceInMaintainer(labCode,sequence,yymmdd,_userVO);
						}
						else
						{
							objPackingListDAO.updatePackingListNoSequenceInMaintainer(sequence, labCode, _userVO);
						}

						
						
						
						// Generate of SampleNo
						// Required HospitalCode,sampleNo,LabCode
						//Generate Sample No Sequence  for each lab
						/*String sequence_Hash_yymmdd=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);  // Returns sequence#yymmdd
						// Commented By Anant Patel on 27th-May-2015
						//  Reason:- Query Location changed to pkg_inv_unique_no_generation.generate_save_sampleno from properties file 

						String sequence=sequence_Hash_yymmdd.split("#")[0];
						String yymmdd=sequence_Hash_yymmdd.split("#")[1];
						String strSampleNo = labCode+sampleCode+yymmdd+sequence;

						//Logic to check the sequence is '100001' 
						if(sequence.equals(InvestigationConfig.SAMPLE_NO_SEQUENCE_INVESTIGATION)) //100001
						{
							// Insert in SampleNo Sequence Maintainer Table
							objSampleCollectionDAO.insertSampleNoSequenceInMaintainer(sampleCode,labCode,sequence,yymmdd,_userVO);
						}
						else
						{
							objSampleCollectionDAO.updateSampleNoSequenceInMaintainer(sequence,sampleCode, labCode, _userVO);
						}*/
						String strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);
						 String sameSampleNO="";	
				  		//Get List of Sample Collection VO from Map of Samples
						List<SampleCollectionCumAcceptanceVO> lstSampleCollectionVO=mpSample.get(sampleCode+"#"+labCode);

						// Loop over VO for saving
						Map<String,String> mpsampleuse=new HashMap<String, String>();

						for(SampleCollectionCumAcceptanceVO voSample:lstSampleCollectionVO)
						{
							
							
							
							
							//added by chandan to check first time if sample no not generate in aday
							String entryDatecheck= voSample.getInitDate();//get the Entry Date 
							
							DateFormat dfcheck = new SimpleDateFormat("yyyy-MM-dd");
							
							 
							Date datecheck = dfcheck.parse(entryDatecheck);
							String finalEntryDatecheck = dfcheck.format(datecheck);
							Date todayDateobjcheck = new Date();
							SimpleDateFormat dateobcheck = new SimpleDateFormat("yyyy-MM-dd");
							String strDatecheck= dateobcheck.format(todayDateobjcheck);
							//put comment
							int EntryDatecomparWithSysDAtecheck = strDatecheck.compareTo(finalEntryDatecheck);
							
							if(EntryDatecomparWithSysDAtecheck<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
							{
								
							}
							else  // first time check only  if entry is not sysdate 
							{
								
								
								List<SampleCollectionCumAcceptanceVO> voo=objSampleCollectionDAO.checkAutoGenFormateDATEWISE(voSample, _userVO);

								SampleCollectionCumAcceptanceVO vodatewsie=voo.get(0);
								
								voSample.setConfigLab(vodatewsie.getConfigLab());
								voSample.setConfigType(vodatewsie.getConfigType());
								voSample.setConfigSeq(vodatewsie.getConfigSeq());
								voSample.setConfigTest(vodatewsie.getConfigTest());
								voSample.setSampleNoFormat(vodatewsie.getSampleNoFormat());
								voSample.setInitDate(vodatewsie.getInitDate());
								voSample.setNoOfSeqDigit(vodatewsie.getNoOfSeqDigit());
								voSample.setFromSeries(vodatewsie.getFromSeries());
								voSample.setToSeries(vodatewsie.getToSeries());
								voSample.setInitType(vodatewsie.getInitType());
								voSample.setRunningSampleNo(vodatewsie.getRunningSampleNo());
								voSample.setPatType(vodatewsie.getPatType());
								
								
							}
								
							
							//voSample.setSampleNo(strSampleNo);

							// Update Requisition Header (only once based on flag isReqHeaderUpdated)
							if(!isReqHeaderUpdated)
							{
								objSampleCollectionDAO.updateRequisitionHeader(voSample.getPatMobile(), voSample.getPatEmail(), voSample.getPatAddress(), reqNo, _userVO);
								isReqHeaderUpdated=true;
							}
							
							//changed by ashu
							
							//objPackingListDAO.updateRequisitionDtlSampleCumAccept(voSample, _userVO);
							
							
							//Logic For Auto SAmple No Generation
						 
							 
							//lstAutoSampleNOConfig=objSampleCollectionDAO.getSampleCollAutoSampleNOConfig(voSample, _userVO);
							//int lstOfSize=lstAutoSampleNOConfig.size();
							
							System.out.println("------------>"+lstAutoSampleNOConfig);
							if(voSample.getCheckSamConfigForAutoGen().equals("1")||voSample.getCheckSamConfigForAutoGen().equals("2"))
							{

								//for(SampleCollectionCumAcceptanceVO autoSampleVo:lstAutoSampleNOConfig)
								//{
									String Str=voSample.getSampleNoFormat();
									//  String Str = new String(autoLabNumber);
									int MainStrlength=Str.length();
									String[] dateFormate = Str.split("&");
									String subDateFormate=dateFormate[0];
									String xMainValue=dateFormate[1];
									//getting the DateFormate number
									String sequence_SampleNO_yymmdd=objSampleCollectionDAO.generateSampleNoDateSequence(subDateFormate, _userVO);  // Returns   yymmdd
									String entryDate= voSample.getInitDate();//get the Entry Date 
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
									Date date = df.parse(entryDate);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Calendar c = Calendar.getInstance();
									c.setTime(date); // Added a entry date. into Calender
                                      String finalDate="";
									
									if(voSample.getInitType().equals("m"))
									{
										
										int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
										  
										String[] SplitDate=entryDate.split("-");
										
										 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
										 date=df.parse(finalDate);
										 
									}
									if(voSample.getInitType().equals("d"))
									{
										
										date = df.parse(entryDate);
										//c.add(Calendar.DATE,1); 
									}
									if(voSample.getInitType().equals("y"))
									{
										String[] SplitDate=entryDate.split("-");
										
										 finalDate=SplitDate[0]+"-"+12+"-"+31;
										date = df.parse(finalDate);
									}
									if(voSample.getInitType().equals("w"))
									{
										int weekOfTheDay= c.get(Calendar.DAY_OF_WEEK); 
										//"2:Monday", "3:Tuesday", "4:Wednesday", "5:Thursday", "6:Friday", "7:Saturday", "1:Sunday"
										String[] SplitDate;
										/*c.add(Calendar.DATE,7);
										String finalEntryDate = sdf.format(c.getTime());
										*/
										switch (weekOfTheDay) {
										case 1:date = df.parse(entryDate);
    											break;
											  	
										case 2: c.add(Calendar.DATE,6);
				                               finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 3: c.add(Calendar.DATE,5);
							                          finalDate=sdf.format(c.getTime()); 
							                          date = df.parse(finalDate);
        										break;
										case 4:c.add(Calendar.DATE,4);
				                                finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 5:c.add(Calendar.DATE,3);
				                               finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 6:c.add(Calendar.DATE,2);
				                                finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 7:c.add(Calendar.DATE,1);
				                                finalDate=sdf.format(c.getTime()); 
				                               date = df.parse(finalDate);
										    break;
										 default:
											break;
										}
										
									}
									
									
									String finalEntryDate = sdf.format(date);
									Date todayDateobj = new Date();
									SimpleDateFormat dateob = new SimpleDateFormat("yyyy-MM-dd");
									String strDate= dateob.format(todayDateobj);
									//put comment
									int EntryDatecomparWithSysDAte = strDate.compareTo(finalEntryDate);
									//String xMainValue=Str.substring(subStrDateLength,MainStrlength);
									int xMainLen=xMainValue.length();
									//For From Series Count
									String FinalSampleNo="";
									int fromSerValue;
									  
									String fromSer=voSample.getFromSeries();
									int fromserlen=fromSer.length();
									int xSubLen=xMainLen-fromserlen;
									String value=xMainValue.substring(0,xSubLen);
									String value2=xMainValue.substring(xSubLen,xMainLen);
									String getXvalue ="";
									String getXvalue2 ="";
									if(!value.equals(""))
									{
										getXvalue=value.replace("X","0");
									}
									if(!value2.equals(""))
									{
										getXvalue2=value2.replace(value2, fromSer);
									}
									String xVal=getXvalue+getXvalue2;
									int xValLen=xVal.length();
									int gnumnoofseqdigits1=Integer.parseInt(voSample.getNoOfSeqDigit());
									 String subStrvalueLab1=xVal.substring(Math.max(0,xValLen - gnumnoofseqdigits1));
									 
								/*
								 * String
								 * runningLabNO=objSampleCollectionDAO.checkAutoGenFormateRunningLabNo(voSample,
								 * _userVO);
								 * 
								 * List<SampleCollectionCumAcceptanceVO>
								 * lstAutoSampleNOConfigtestwise=objSampleCollectionDAO.
								 * getSampleCollAutoSampleNOConfigtestwise(voSample, _userVO);
								 */	 
									 
										int isrunninglabnsyncc=1;
										
										 String runningLabNO=null;
										 List<SampleCollectionCumAcceptanceVO> lstAutoSampleNOConfigtestwise=null;
										int counter_sync=0;
										 while(isrunninglabnsyncc!=0 && counter_sync<=5)
										 {
											 counter_sync++;
											 //String isrunninglabnsync=objSampleCollectionDAO.isAutoGenFormateRunningLabNofree(voSample, _userVO);
											
											 String isrunninglabnsync=objSampleCollectionDAO.checkAutoGenFormateRunningLabNo_isysnc(voSample, _userVO);
											 
											 isrunninglabnsyncc=Integer.parseInt(isrunninglabnsync);
										
											 if(isrunninglabnsync!=null && isrunninglabnsync.equals("0"))
											 {
												 runningLabNO=objSampleCollectionDAO.checkAutoGenFormateRunningLabNo(voSample, _userVO);
											 lstAutoSampleNOConfigtestwise=objSampleCollectionDAO.getSampleCollAutoSampleNOConfigtestwise(voSample, _userVO);
											 }
											 
									/*
									 * runningLabNO=objSampleCollectionDAO.pkgcheckAutoGenFormateRunningLabNo(
									 * voSample, _userVO);
									 * 
									 * if(runningLabNO!=null && runningLabNO.equals("")) { runningLabNO=null; }
									 * 
									 * String lstAutoSampleNOConfigtestwis=objSampleCollectionDAO.
									 * pkgcheckAutoGenFormateRunningLabNo_testwise(voSample, _userVO);
									 * 
									 * if(lstAutoSampleNOConfigtestwis!=null &&
									 * lstAutoSampleNOConfigtestwis.equals("")) {
									 * lstAutoSampleNOConfigtestwise=null; } else { Inv_SampleCollectionVO vo=new
									 * Inv_SampleCollectionVO(); vo.setChkval("1");
									 * 
									 * lstAutoSampleNOConfigtestwise=new ArrayList<Inv_SampleCollectionVO>() ;
									 * 
									 * lstAutoSampleNOConfigtestwise.add(vo);
									 * 
									 * }
									 */
											// lstAutoSampleNOConfigtestwise=objSampleCollectionDAO.getSampleCollAutoSampleNOConfigtestwise(voSample, _userVO);
											 //checkAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
										 }
										 
									 
										 if(sameSampleNO==strSampleNo && runningLabNO!=null && (lstAutoSampleNOConfigtestwise==null || lstAutoSampleNOConfigtestwise.size()==0))
										 {
											voSample.setTemparorySampleNO(runningLabNO); 
											
											

											 if(!mpsampleuse.containsKey("0"))
											 {
												
													 strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);
													 mpsampleuse.put("0", strSampleNo);
												
											 }
											 else
											 {
												 mpsampleuse.put("0", strSampleNo);

											 }
											 

									 }
                                      else
                                      {
									 voSample.setRunningSampleNo(runningLabNO);
									 
									 if((lstAutoSampleNOConfigtestwise!=null &&  lstAutoSampleNOConfigtestwise.size()>0))
									 {
										 strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);
										 mpsampleuse.put("1", strSampleNo);
										 
									 }
									 else
									 {
										 mpsampleuse.put("0", strSampleNo);
									 }
									 

									  if((lstAutoSampleNOConfigtestwise!=null &&  lstAutoSampleNOConfigtestwise.size()>0))
									 {
										 strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);
										 mpsampleuse.put(strSampleNo+sampleCode, "1");
										 
									 }
									  
									  
									  
									  if((lstAutoSampleNOConfigtestwise!=null && lstAutoSampleNOConfigtestwise.size()>0))
									 {
										 strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);
										 mpsampleuse.put(strSampleNo, "1");
										 
									 }
									  
									 if(voSample.getRunningSampleNo()!=null)
										{
										 String  sampleNO=voSample.getRunningSampleNo();
											int sampleNOLen=sampleNO.length();
											 int gnumnoofseqdigits=Integer.parseInt(voSample.getNoOfSeqDigit());
											 String subStrvalueLab=sampleNO.substring(Math.max(0, sampleNO.length() - gnumnoofseqdigits));
											
											 //if Date is not Available in Formate Case Modify On 22-06-2015
											 String constVAlue="";
											 try
											 {
										  constVAlue =sampleNO.substring(subDateFormate.length(),sampleNOLen-gnumnoofseqdigits);
											 }
											 catch (Exception e)
												{
												 constVAlue="";
												}
											 fromSerValue= Integer.parseInt(subStrvalueLab);
										}
									 else
									 {
										 fromSerValue = Integer.parseInt(subStrvalueLab1);
									 }
									
									if(sequence_SampleNO_yymmdd==null)
									 {
										FinalSampleNo=xVal;
									 }
									 else
									 {
										 FinalSampleNo=sequence_SampleNO_yymmdd+xVal;
									 }
									 
									//For To Series Count
									String toSer=voSample.getToSeries();
									int toserlen=toSer.length();
									int xSubLenToSer=xMainLen-toserlen;
									String valueToSer=xMainValue.substring(0,xSubLenToSer);
									String valueToSer2=xMainValue.substring(xSubLenToSer,xMainLen);
									String getXvalueToSer="";
									String getXvalueToSer2="";
									if(!valueToSer.equals(""))
									{
										getXvalueToSer=valueToSer.replace("X","0");
									}
									if(!valueToSer2.equals(""))
									{
										getXvalueToSer2=valueToSer2.replace(valueToSer2, toSer);
									}
									String xValToSer=getXvalueToSer+getXvalueToSer2;
									int xValToSerLen=xValToSer.length();
									int gnumnoofseqdigits2=Integer.parseInt(voSample.getNoOfSeqDigit());
									 String subStrvalueLab2=xValToSer.substring(Math.max(0,xValToSerLen - gnumnoofseqdigits2));
								 
									int toSerValue = Integer.parseInt(subStrvalueLab2);

									if(EntryDatecomparWithSysDAte<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
									{	 

										if(voSample.getRunningSampleNo()==null)
										{

											voSample.setTemparorySampleNO(FinalSampleNo);
										}
										else
										{

											if(fromSerValue<toSerValue)
											{
												String  sampleNO=voSample.getRunningSampleNo();
												int sampleNOLen=sampleNO.length();
												 int gnumnoofseqdigits=Integer.parseInt(voSample.getNoOfSeqDigit());
												 String subStrvalueLab=sampleNO.substring(Math.max(0, sampleNO.length() - gnumnoofseqdigits));
												 //constVAlue =sampleNO.substring(subDateFormate.length(),sampleNOLen-gnumnoofseqdigits);
												 //if Date is not Available in Formate Case Modify On 22-06-2015
												 String constVAlue="";
												 try
												 {
													 constVAlue =sampleNO.substring(subDateFormate.length(),sampleNOLen-gnumnoofseqdigits);
												 }
												 catch (Exception e)
													{
													 constVAlue="";
													}
												 
												  
											 	int toSubStrValueLAb= Integer.parseInt(subStrvalueLab);
												++toSubStrValueLAb;
												int length = String.valueOf(toSubStrValueLAb).length();
												String leftPadded = StringUtils.leftPad("" + toSubStrValueLAb,subStrvalueLab.length(), "0");
												
												String finalSample="";
														
												if(sequence_SampleNO_yymmdd==null)
												{
													finalSample=constVAlue+leftPadded;
												}
												else
												{
													finalSample=sequence_SampleNO_yymmdd+constVAlue+leftPadded;
												}
												voSample.setTemparorySampleNO(finalSample);
											}
											else
											{
												voSample.setTemparorySampleNO(FinalSampleNo);
											}
										}

									}

									else  ////for EntryDatecomparWithSysDAte value is Positive
									{
										voSample.setTemparorySampleNO(FinalSampleNo);
										
										objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1resetsampleno(voSample,finalEntryDate,_userVO);
										Date todayDateob = new Date();
										SimpleDateFormat dateobj = new SimpleDateFormat("yyyy-MM-dd");
										String strSysDate= dateob.format(todayDateob);
										Date todayDat =dateobj.parse(strSysDate) ; 
										Calendar c1 = Calendar.getInstance();
										c1.setTime(todayDat); // Now use SYDATE date.

										if(voSample.getInitType().equals("m"))
										{
											int daysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
											  
											String[] SplitDate=strSysDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
											 todayDat=dateobj.parse(finalDate);
										}
										if(voSample.getInitType().equals("d"))
										{
											todayDat=dateobj.parse(strSysDate);
										}
										if(voSample.getInitType().equals("y"))
										{
											String[] SplitDate=strSysDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+12+"-"+31;
											 todayDat = dateobj.parse(finalDate);
										}
										if(voSample.getInitType().equals("w"))
										{
											int weekOfTheDay= c.get(Calendar.DAY_OF_WEEK); 
											//"2:Monday", "3:Tuesday", "4:Wednesday", "5:Thursday", "6:Friday", "7:Saturday", "1:Sunday"
											String[] SplitDate;
											/*c.add(Calendar.DATE,7);
											String finalEntryDate = sdf.format(c.getTime());
											*/
											switch (weekOfTheDay) {
											case 1:date = df.parse(entryDate);
	    											break;
												  	
											case 2: c.add(Calendar.DATE,6);
					                               finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 3: c.add(Calendar.DATE,5);
								                          finalDate=sdf.format(c.getTime()); 
								                          date = df.parse(finalDate);
	        										break;
											case 4:c.add(Calendar.DATE,4);
					                                finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 5:c.add(Calendar.DATE,3);
					                               finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 6:c.add(Calendar.DATE,2);
					                                finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 7:c.add(Calendar.DATE,1);
					                                finalDate=sdf.format(c.getTime()); 
					                               date = df.parse(finalDate);
											    break;
											 default:
												break;
											}
											  
										}
										  finalEntryDate = dateobj.format(todayDat);
									}
								 
									
									//-----------------------------------------------area wise sample no temp!
									if(voSample.getTempSampleNo().equals("2"))
									if(collAreaBasedSampleNo.equals(""))
									{
									collAreaBasedSampleNo=voSample.getTemparorySampleNO();
								//	objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
									}
									else
										voSample.setTemparorySampleNO(collAreaBasedSampleNo);
										
										
										
									
									//--------------------------------------appended above in sample no generation area wise. 
									//-=------------------------------------ remove comment is above block of collAreaBasedSampleNo removed
								//	
									//objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
									objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1_issync(voSample,finalEntryDate,_userVO);
									
									
									
									
									
									
									//}
								}
								 
								// Update Requisition Dtl  Table 
								//Setting Requisition Dtl Status as '3' for packing list generation
								
								voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY); //6
								voSample.setSampleNo(strSampleNo);
								
					//////////////////////////////////			change made			////////////////////////////////////////
								
								if(voSample.getTemparorySampleNO()!=null)
								voSample.setTempSampleNo(voSample.getTemparorySampleNO());
								
								if(voSample.getTestBasedMachine()!=null)
								if(!voSample.getTestBasedMachine().equals("-1"))
									voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_MACHINE_RESULT_ENTRY); //17
								
								//changed by ashu
								
								voSample.setPackingListNo(strPackingListNo);
								
								objSampleCollectionDAO.updateRequisitionDtl(voSample, _userVO);
								
							
								

								// Insert in Sample Dtl Table
								if(!isSampleDtlInsert)
								{
									
									
									objSampleCollectionDAO.insertSampleDtl(voSample, _userVO);
									//isSampleDtlInsert=true;
								}
								sameSampleNO=strSampleNo;
							 
							}
							else
							{
								// Update Requisition Dtl  Table 
								//Setting Requisition Dtl Status as '3' for packing list generation
								voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY); //6
								voSample.setSampleNo(strSampleNo);
								if(voSample.getTestBasedMachine()!=null)
								if(!voSample.getTestBasedMachine().equals("-1"))
									voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_MACHINE_RESULT_ENTRY); //17
								
								//changed by ashu
								
								voSample.setPackingListNo(strPackingListNo);
								objSampleCollectionDAO.updateRequisitionDtl(voSample, _userVO);
							

								// Insert in Sample Dtl Table
								if(!isSampleDtlInsert)
								{
									objSampleCollectionDAO.insertSampleDtl(voSample, _userVO);
								//	isSampleDtlInsert=true;
								}
							}
							 
								if(voSample.getTestBasedMachine()!=null)
								if(!voSample.getTestBasedMachine().equals("-1"))
								{	objSampleCollectionDAO.saveSampleDetail(voSample, _userVO);
								objSampleCollectionDAO.updateRequestId(voSample, _userVO);
								
								
								}									
									
							String tariffId="";
							String serviceId="";
							//Update Billing
							if(voSample.getGroupType() == null)
							{
								tariffId = voSample.getTestCode();
								serviceId = "1";
							}
							else if(voSample.getGroupType().equals("1") || voSample.getGroupType().equals("2"))
							{
								tariffId = voSample.getLabCode()+voSample.getGroupCode();
								serviceId = "4";
							}  
							else
							{
								tariffId = voSample.getTestCode();
								serviceId = "1";
							}

							voBillingDtl.setBillNo(voSample.getBillNo());
							voBillingDtl.setConsQty("1"); // Need to discuss
							voBillingDtl.setTariffId(tariffId);
							voBillingDtl.setServiceId(serviceId);

							invBillingDAO.updateBillingQty(voBillingDtl, tariffId, serviceId, _userVO);

							//Adding generated sampleNo dtls for displaying
							if(!listSamples.contains(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo())){
								listSamples.add(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo());
								//System.out.println(" -- "+voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getSampleCode()+"#"+voSample.getTempSampleNo());
							}
								
								
						}

					}
				}
			}

			return listSamples;	
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	
	// revalidate process begins/////////////////////
	
	public Map  LabComboForResultReValidation(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();

		try
		{
			tx.begin();
			InvResultReValidationDAO onlinePatientDao = new InvResultReValidationDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=onlinePatientDao.LabComboForResultValidation(invresultentryvo,_UserVO);
			mp.put(InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY, labcombo);


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
	
	public Map setPatientResultReValidationEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<ResultEntryVO> lstInvResultValidationRespVO=new ArrayList<ResultEntryVO>();
		List labNoCombo=new ArrayList();
		List testCombo=new ArrayList();
		List sampleNoCombo=new ArrayList();
		List testGroupCombo=new ArrayList();
		 List<InvCriteriaCodeVO > objCriteriaCodeList = InvestigationTemplateDataHelper.getInstance().getCriteriaCode();
		 List<TestMandRefMasterVO > objRefRangeList = InvestigationTemplateDataHelper.getInstance().getReferanceRange(_UserVO.getHospitalCode());
		 TemplateProcessingUSE tpu = new TemplateProcessingUSE();

		List groupCode=new ArrayList();
		List<ResultEntryVO> groupModified_lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
		try
		{
			tx.begin();
			InvResultReValidationDAO invresultentrydao = new InvResultReValidationDAO(tx);

			lstInvResultValidationRespVO=invresultentrydao.setPatientResultValidationEssentials(invresultentryvo, _UserVO);
			
			
			
			//////logic to group by groupcode /////
			
			for(int i=0;i<lstInvResultValidationRespVO.size();i++)
			{
				String concatString=lstInvResultValidationRespVO.get(i).getTestParameterName();
				String testCode=lstInvResultValidationRespVO.get(i).getTestCode();
				
				String properParaValues="";
				
				String[] parameters = null;
				int paraSize = 0;
//				if(concatString != null)
//				{
					parameters =concatString.split("`");
					paraSize=parameters.length;
				//}
//				else
//				{
//					paraSize=0;
//					continue;
//				}
				
				
				for(int iterate=0;iterate<paraSize;iterate++)
				{
					
					System.out.println("dno::::"+lstInvResultValidationRespVO.get(i).getRequisitionDNo());
					String[] paraValues=parameters[iterate].split("#@");
					String paraCode=paraValues[0];
					String paraName=paraValues[1];
				
					String paraEntry="";
					
					if(paraValues.length>3)
						 paraEntry=paraValues[3];
					//String paraEntry=paraValues[3];
					
					/*String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, lstInvResultValidationRespVO.get(i).getPatGender(), 
											lstInvResultValidationRespVO.get(i).getPatAge());*/

					String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, lstInvResultValidationRespVO.get(i).getPatGender(),lstInvResultValidationRespVO.get(i).getPatAge());
					
					
					String rangeValue=refRange;//paraValues[2];
					String comboValue="";
					if(paraEntry.contains("$"))
					{
						String[] multiValue=paraEntry.split("\\$");
						
						for(int k=0;k<multiValue.length;k++)
						comboValue+=invresultentrydao.setComboValueName(testCode,paraCode,multiValue[k])+"$";
						
					}
					else				
					 comboValue=invresultentrydao.setComboValueName(testCode,paraCode,paraEntry);
				 
					if(comboValue!=null)
						properParaValues+=paraCode+"#@"+paraName+"#@"+rangeValue+"#@"+comboValue+"`";
					else
						properParaValues+=paraCode+"#@"+paraName+"#@"+rangeValue+"#@"+paraEntry+"#@"+"`";
					
					
					
				}
				
		
				properParaValues=properParaValues.substring(0, properParaValues.length()-1);
				lstInvResultValidationRespVO.get(i).setTestParameterName(properParaValues);
				
				//to append para values for all group values
				if(lstInvResultValidationRespVO.get(i).getGroupCode()!=null)
				{
					
					if(!groupCode.contains(lstInvResultValidationRespVO.get(i).getGroupCode()+lstInvResultValidationRespVO.get(i).getRequisitionNo()))
					{
					for(int j=i+1;j<lstInvResultValidationRespVO.size();j++)
					{
						if(lstInvResultValidationRespVO.get(j).getGroupCode()!=null)
						if(lstInvResultValidationRespVO.get(i).getGroupCode().equals(lstInvResultValidationRespVO.get(j).getGroupCode()) && lstInvResultValidationRespVO.get(i).getRequisitionNo().equals(lstInvResultValidationRespVO.get(j).getRequisitionNo()))
						{	
						String temp_concatString=lstInvResultValidationRespVO.get(j).getTestParameterName();
						String temp_testCode=lstInvResultValidationRespVO.get(j).getTestCode();
						
						String temp_properParaValues="";
						
						String[] temp_parameters = null;
						int temp_paraSize = 0;
//						if(concatString != null)
//						{
							temp_parameters =temp_concatString.split("`");
							temp_paraSize=temp_parameters.length;
						//}
//						else
//						{
//							paraSize=0;
//							continue;
//						}
						
						
						for(int iterate=0;iterate<temp_paraSize;iterate++)
						{
							String[] paraValues=temp_parameters[iterate].split("#@");
							String paraCode=paraValues[0];
							String paraName=paraValues[1];
							String paraEntry="";
							
							if(paraValues.length>3)
								 paraEntry=paraValues[3];
							
							String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, temp_testCode, paraCode, lstInvResultValidationRespVO.get(j).getPatGender(), 
									lstInvResultValidationRespVO.get(j).getPatAge());
							
							
							String rangeValue=refRange;//paraValues[2];
							String comboValue="";
							if(paraEntry.contains("$"))
							{
								String[] multiValue=paraEntry.split("\\$");
								
								for(int k=0;k<multiValue.length;k++)
								comboValue+=invresultentrydao.setComboValueName(testCode,paraCode,multiValue[k])+"$";
								
							}
							else				
							 comboValue=invresultentrydao.setComboValueName(temp_testCode,paraCode,paraEntry);
						 
							if(comboValue!=null)
								temp_properParaValues+=paraCode+"#@"+paraName+"#@"+rangeValue+"#@"+comboValue+"`";
							else
								temp_properParaValues+=paraCode+"#@"+paraName+"#@"+rangeValue+"#@"+paraEntry+"#@"+"`";
							
							
							
						}
						
				
						temp_properParaValues=temp_properParaValues.substring(0, temp_properParaValues.length()-1);
						lstInvResultValidationRespVO.get(j).setTestParameterName(temp_properParaValues);
						
						
						lstInvResultValidationRespVO.get(i).setTestParameterName(lstInvResultValidationRespVO.get(i).getTestParameterName()+"`"+temp_properParaValues);
					}
					}
						groupCode.add(lstInvResultValidationRespVO.get(i).getGroupCode()+lstInvResultValidationRespVO.get(i).getRequisitionNo());
						groupModified_lstInvResultEntryVO.add(lstInvResultValidationRespVO.get(i));
					}
					else
					{;}
					
				}
				else
				{
					groupModified_lstInvResultEntryVO.add(lstInvResultValidationRespVO.get(i));
				}
				
				
			
				
				
			}
			
mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultEntryVO);

			
			//////////////////////////////////////
			
			//////////////////////////////logic to fetch combo value name of the tests//////////////////////////
			
//			for(int i=0;i<lstInvResultValidationRespVO.size();i++)
//			{
//				String concatString=lstInvResultValidationRespVO.get(i).getTestParameterName();
//				String testCode=lstInvResultValidationRespVO.get(i).getTestCode();
//				
//				String properParaValues="";
//				
//				String[] parameters=concatString.split("`");
//				int paraSize=parameters.length;
//				
//				for(int iterate=0;iterate<paraSize;iterate++)
//				{
//					String[] paraValues=parameters[iterate].split("#");
//					String paraCode=paraValues[0];
//					String paraName=paraValues[1];
//					String paraEntry=paraValues[3];
//					String rangeValue=paraValues[2];
//					
//					String comboValue="";
//					if(paraEntry.contains("$"))
//					{
//						String[] multiValue=paraEntry.split("\\$");
//						
//						for(int k=0;k<multiValue.length;k++)
//						comboValue+=invresultentrydao.setComboValueName(testCode,paraCode,multiValue[k])+"$";
//						
//					}
//					else		
//						comboValue=invresultentrydao.setComboValueName(testCode,paraCode,paraEntry);
//					
//					if(comboValue!=null)
//						properParaValues+=paraCode+"#"+paraName+"#"+rangeValue+"#"+comboValue+"`";
//					else
//						properParaValues+=parameters[iterate]+"`";
//					
//					
//					
//				}
//				
//		
//				properParaValues=properParaValues.substring(0, properParaValues.length()-1);
//				lstInvResultValidationRespVO.get(i).setTestParameterName(properParaValues);
//			}
//			
			////////////////////////////logic to fetch combo value name of the tests ENDS//////////////////////////
			
			

			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationRespVO);
			
			
			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationRespVO);
			
			 
			labNoCombo=invresultentrydao.setLabNoComboEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,labNoCombo);
			  
				testCombo=invresultentrydao.setTestComboEssentials(invresultentryvo, _UserVO);
			 
			mp.put(InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY,testCombo);
			
			sampleNoCombo=invresultentrydao.setSamplNoComboEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
			
			testGroupCombo=invresultentrydao.setTestGroupComboEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY,testGroupCombo);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
	
	/**
	public Map ResultValidationPatientDetails(InvResultValidationRespVO invresultentryvo,List<String> reqList, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultValidationRespVO> lstInvResultValidationRespVO=new ArrayList<InvResultValidationRespVO>();
		List<InvResultValidationRespVO> reqLis=new ArrayList<InvResultValidationRespVO>();
		String reqType="";
		List criteriacombo=new ArrayList();
		try
		{
			tx.begin();
			OnlinePatientAcceptanceDAO onlinePatientDao = new OnlinePatientAcceptanceDAO(tx);
			///criteriacombo=onlinePatientDao.getTestCombo( _UserVO);
			//mp.put(InvestigationConfig.TEST_REASON_COMBO, criteriacombo);
			if(onlinePatientvo.getPatStatus().equals("IPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_IPD; //1
			else if(onlinePatientvo.getPatStatus().equals("OPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_OPD; //2
			else
				reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3

			OnlinePatientAcceptanceVO[] arrPatientCollectionVO=null;

			for(String str:reqList)
			{
				String reqNo=str.split("#")[1]; //CRNO#ReqNo#index
				boolean isBilled=false;
				List<OnlinePatientAcceptanceVO> lstTestBased=new ArrayList<OnlinePatientAcceptanceVO>();
				lstTestBased=onlinePatientDao.patientDetails(reqNo,reqType, _UserVO);

				if(lstTestBased!=null)
				{
					for(OnlinePatientAcceptanceVO voPatientCollection:lstTestBased)
					{
						voPatientCollection.setPatPuk(onlinePatientvo.getPatPuk());

						voPatientCollection.setRequisitionNo(reqNo);


						if(voPatientCollection!=null)
						{
							String billNo=voPatientCollection.getBillDetail().replace("^", "#").split("#")[0];
							voPatientCollection.setBillDetail(billNo);
							if(!billNo.equals("0"))isBilled=true;
						}
						if(isBilled){
							lstOnlinePatientAcceptanceVO.add(voPatientCollection);

						}
						else
							reqLis.add(voPatientCollection);
					}

				}
			}

			mp.put(InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED,lstOnlinePatientAcceptanceVO);
			mp.put(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED,reqLis);

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
 
	
	**/
	
	public  Map saveResultReValidationDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List   listResultValidationDtl =new ArrayList();
		 
            HttpSession session=_request.getSession();
		boolean isComplete=true;
		List<ResultEntryVO> lstResultValidationVO=new ArrayList<ResultEntryVO>();
		ResultEntryVO resultEntryVO=new ResultEntryVO();
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			InvResultReValidationDAO objResultEntrtyDAO=new InvResultReValidationDAO(tx);
			
			
			resultEntryVO=invResultentryVO.get(0);
			if(resultEntryVO.getCrNoReqNoString()!=null)
			for(int i =0;i<resultEntryVO.getCrNoReqNoString().length;i++)
			{				
				objResultEntrtyDAO.updateFinalRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], _userVO);				
			}
			
			
			
			for(ResultEntryVO voInvResultValidationResp:invResultentryVO)
			{
					
				/*if(voInvResultValidationResp.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
						voInvResultValidationResp.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				else*/					
				voInvResultValidationResp.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				
				
				objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidationResp,_userVO);
				
				//added by prashant
				objResultEntrtyDAO.updateIndicationInRequisitionheader(voInvResultValidationResp,_userVO);
				objResultEntrtyDAO.commentsupdateResultValidationInRequisitionDtl(voInvResultValidationResp,_userVO);

				
				if(voInvResultValidationResp.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
				{
				objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidationResp,_userVO);
				}		
				
			
			
			
			}
		
			
			
			int count=1;
			for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				
				if(_request.getAttribute("getuploadedfiledata")!=null)
				{
					Map<String,String> mpo=(Map<String,String>)_request.getAttribute("getuploadedfiledata");
					
					if(mpo.containsKey(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()+voInvResulEntryForParaMeterDtl.getParantParaCode()))
					{
						String finaldata=mpo.get(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()+voInvResulEntryForParaMeterDtl.getParantParaCode());
						System.out.println("==============================================="+finaldata);
						
						voInvResulEntryForParaMeterDtl.setFileuploaddata(finaldata.split("@@")[1].split(",")[1]);
						voInvResulEntryForParaMeterDtl.setFilename(finaldata.split("@@")[0]);
						
					}
					
				}
				
				String parentid=voInvResulEntryForParaMeterDtl.getParantParaCode();
				
				String Ishyperlink="";
				Ishyperlink=voInvResulEntryForParaMeterDtl.getIsHyperLink();
				 if(Ishyperlink.equals("-"))
				 {
					 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
					 
					 
						
					 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
				      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
				      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

				      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
				      
				      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
				      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
											
						
						objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
				 
						if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
							  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,"13");

						if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
							objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),"13");

						if(voInvResulEntryForParaMeterDtl.getFinalechovalue()!=null && !voInvResulEntryForParaMeterDtl.getFinalechovalue().equals(""))
                                         {
                                  objResultEntrtyDAO.updateechodata(voInvResulEntryForParaMeterDtl,_userVO);

                                          }
				 }
				 else
				 {
					
					 
					 //objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO);
					  objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,null,null,null,null);

					// Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

					  Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

					    if(mpBilled==null)
	                      {
	     					 
	     					  mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);

	                      }
	                      else
	                      {
	                    	  Map<String,List<antibioticprocessVO>>	  mpBilledfungus= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
	                    	 
	                    	  if(mpBilledfungus!=null && mpBilledfungus.size()>0)
	                    	  mpBilled.putAll(mpBilledfungus);
	                    	  
	                      }
                      
					 if(mpBilled!=null)
	                    {
	            			List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

	            			boolean flag=false;
	          Iterator itr1=mpBilled.keySet().iterator();
	          while(itr1.hasNext())
		 		{
	        	  
		 			String organisgm1=(String)itr1.next();
	            
		 			 
		 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm1);
		 			
		 			int rowSpanSize=lstVOSample.size();
		 			
		 			 
		 			 
		 			for(int k=0;k<lstVOSample.size();k++)
		 			{
		 				antibioticprocessVO voo=lstVOSample.get(k);
		 				
		 					 if(voo.getRequisitionDNo().equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && voo.getTestParaCode().equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
							  {
						   objResultEntrtyDAO.insertHyperLinkDetailsNew(voInvResulEntryForParaMeterDtl,_userVO,voo);
						 flag=true;
						  }
		 				 
		 				  String antibioticCode="";
						  String organismCode="";
						  String selectval="";
						  String reqdno="";
						  String testparacode="";
						  
		 				
		 			}
		 			

		 			 if(flag==true)
					  {
		 				if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") &&  !voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
						  {
							  String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
							  
							  for(int i11=0;i11<tbl1.length;i11++)
							  {
							String tbl=  tbl1[i11];
							
							String[] values=tbl.split("\\$\\$");
							
							if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
							{
							voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
							objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							
							
							 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
						      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
						      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

						      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
						      
						      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
						      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
															
								
							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
								  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,"13");

							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
								objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),"13");

							}
							  }
							
							  
						  }
						  else
						  {
							  
							  
							  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
							  {
									voInvResulEntryForParaMeterDtl.setResultEntryValue("--");
								
								
								 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
								 
									
								 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
							      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
							      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

							      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
							      
							      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
							      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
																	
									
							  }
							  
								String status="";
								if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
								{
									status="13";
								     	
								}
								else
								{
									status="8";
								}
								
								if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
								  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,status);

								if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
									objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,null,voInvResulEntryForParaMeterDtl.getResultEntryValue(),status);
								
								
						  }
					
						  
					  }
		 		}
	          
	          
	          /*if(flag==true)
			  {
				  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals(""))
				  {
					  String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
					  
					  for(int i11=0;i11<tbl1.length;i11++)
					  {
					String tbl=  tbl1[i11];
					
					String[] values=tbl.split("\\$\\$");
					
					if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
					{
					voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
				    }
					  }
					  
				  }
			
				  objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
			  }*/
	          
	                    }
					 else
					 {
						 String antibioticCode="";
						  String organismCode="";
						  String selectval="";
						  
						// objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
							//added by prashantMi for echo modify
						  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
						  if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
							{
							  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
							} else
							{
						  objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						  
							
							 String resultEntryvalue = voInvResulEntryForParaMeterDtl.getResultEntryValue();
						      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
						      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

						      String value = voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
						      
						      String properParaValues=voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulEntryForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
						      getrangeeentry(	voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo(),voInvResulEntryForParaMeterDtl.getParantParaCode(),voInvResulEntryForParaMeterDtl.getTestCode(),voInvResulEntryForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulEntryForParaMeterDtl.getPatGender(),voInvResulEntryForParaMeterDtl.getPatAge(),value,_userVO,tx);
															
								
							}
							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12762") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3590"))
								  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,"13");

							if(voInvResulEntryForParaMeterDtl.getTestCode().equals("12765") && voInvResulEntryForParaMeterDtl.getTestParaMeterCode().equals("3598"))
								  objResultEntrtyDAO.updatepidvaluesresult(voInvResulEntryForParaMeterDtl,_userVO,voInvResulEntryForParaMeterDtl.getResultEntryValue(),null,"13");

					 }
					 
					 
							/*if(!fb.getSelectValuemapping().equals(""))
							{
								
								 String[] selectvaluemapping=fb.getSelectValuemapping().split("@");
						
						for(int i1=0;i1<selectvaluemapping.length;i1++)
							 {
								 
								 String mappingvalue=selectvaluemapping[i1];
						  String[] val=mappingvalue.split("\\$\\$\\$");
						  int len=val.length;
						  boolean flag=false;
						  for(int i=0;i<len;i++)
						  {
							  String[] value=val[i].split("\\$\\$");
							  System.out.println("chandan:"+value);
							  String antibioticCode="";
							  String organismCode="";
							  String selectval="";
							  String reqdno="";
							  String testparacode="";
							  
							  organismCode   =value[0];
							  antibioticCode=value[1];
							   selectval=value[2];
							  reqdno=value[3];
							  testparacode=value[4];
							  if(reqdno.equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && testparacode.equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
							  {
								  objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
								   objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
							 flag=true;
							  }
						  }
						  if(flag==true)
						  {
							  
							  if(!voInvResulEntryForParaMeterDtl.getResultEntryValue().equals(""))
							  {
                                 String[] tbl1=  voInvResulEntryForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
								  
								  for(int i11=0;i11<tbl1.length;i11++)
								  {
								String tbl=  tbl1[i11];
								
								String[] values=tbl.split("\\$\\$");
								
								if(values[0].equals(voInvResulEntryForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulEntryForParaMeterDtl.getParantParaCode()))
								{
								voInvResulEntryForParaMeterDtl.setResultEntryValue(values[2]);
							    }
								  }
							  }
							  objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						  
						  
						  }
						  }
				
						 
						
						}
							 else
							  {
								  String antibioticCode="";
								  String organismCode="";
								  String selectval="";
								  
								// objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
								//  objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							  }*/
				 }
					 
				 
			//	if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				//{
				//objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResulEntryForParaMeterDtl,_userVO);
			//	}
				//listResultEntryDtl.add("Test Name= "+voInvResultEntry.getTestName()+"Value=" +voInvResultEntry.getResultEntryValue());
				 count++;
			}	
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultValidationDtl);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
	
	
	public  void revalidateDirectly(List<ResultEntryVO> invResultentryVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List   listResultValidationDtl =new ArrayList();
		 

		boolean isComplete=true;
		List<ResultEntryVO> lstResultValidationVO=new ArrayList<ResultEntryVO>();
		 
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			InvResultReValidationDAO objResultEntrtyDAO=new InvResultReValidationDAO(tx);
			
			for(ResultEntryVO voInvResultValidationResp:invResultentryVO)
			{
					 
				//voInvResultValidationResp.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
				voInvResultValidationResp.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidationResp,_userVO);
				
				if(voInvResultValidationResp.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
				{
				objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidationResp,_userVO);
				}	
				
				objResultEntrtyDAO.updatepidvaluesresultwithoutchange(voInvResultValidationResp,_userVO);

				
			}
	
			/*
			for(InvResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
				
				//listResultValidationDtl.add("Test Name= "+voInvResultValidationResp.getTestName()+"Value=" +voInvResultValidationResp.getResultValidationValue());
			}	 
			//Put List in Map
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultValidationDtl);
			 */

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	//	return mp;
		
	}
	
	
	
	public  String getLoincCode(String passValue)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List   listResultValidationDtl =new ArrayList();
		 
String loincCode="";
		boolean isComplete=true;
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			InvResultEntryDAO objResultEntrtyDAO=new InvResultEntryDAO(tx);
			loincCode=objResultEntrtyDAO.getLoincCode(passValue);
			
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

		return loincCode;
		
	}

/////revalidate process ends///////////////////
	
	
	//fetch list para and loinc
/*
	public void fetchLoincCode(InvResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();


		try
		{
			tx.begin();
			InvResultReValidationDAO invresultentrydao = new InvResultReValidationDAO(tx);

			invresultentrydao.getParameterCodeList(invresultentryvo,_UserVO);
			invresultentrydao.fetchLoincCode(invresultentryvo,_UserVO);

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
		
	}
	*/
	
	
	
	//Start a Result Reprot Printing Process Added By Basha on 25-05-2015
	
		public Map  LabComboForResultReportPrinting(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List labcombo=new ArrayList();

			try
			{
				tx.begin();
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);
				//labMstDAOi.fetchLab(labMasterVO, _UserVO);

				labcombo=invresultreportprintingdao.LabComboForResultReportPrinting(invresultreportprintingvo,_UserVO);
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
	
	
		public Map setResultReportPrintingEssentials(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List<InvResultReportPrintingVO> groupModified_lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();

			List labNoCombo=new ArrayList();
			List testCombo=new ArrayList();
			List sampleNoCombo=new ArrayList();
			List testGroupCombo=new ArrayList();
			List groupCode=new ArrayList();
		
 
			try
			{
				tx.begin();
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);

				lstInvResultReportPrintingVO=invresultreportprintingdao.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);

				mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
				
				
				
				
				for(InvResultReportPrintingVO tempVo:lstInvResultReportPrintingVO)
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
		
		
		
		
		public Map pdfDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();

			
			InvResultReportPrintingVO invresultreportp=new InvResultReportPrintingVO();

			try
			{
				tx.begin();
				
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);
				for(InvResultReportPrintingVO invresultreportprintvo:invresultreportprintingvo)
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
		
		
		
		public Map saveResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();

			
			//InvResultReportPrintingVO invresultreportp=new InvResultReportPrintingVO();
			try
			{
				tx.begin();
				
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);
				for(InvResultReportPrintingVO invresultreportprintvo:invresultreportprintingvo)
				{
					
					invresultreportprintingdao.updateResultReportPrintingDetailInRequisitionDtl(invresultreportprintvo, _UserVO);
					invresultreportprintingdao.updateResultReportPrintingDetailInRequisitionDtldialytbl(invresultreportprintvo, _UserVO);
					invresultreportprintingdao.updateResultReportPrintingDetailInRequisitionDtlhisotrytbl(invresultreportprintvo, _UserVO);
					
					
					
					 
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
	
		public Map setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			
			List labNoCombo=new ArrayList();
			List testCombo=new ArrayList();
			List sampleNoCombo=new ArrayList();
			List testGroupCombo=new ArrayList();
			List<InvResultReportPrintingVO> groupModified_lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List groupCode=new ArrayList();


			try
			{
				tx.begin();
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);

				lstInvResultReportPrintingVO=invresultreportprintingdao.setResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);

				mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
				
				
				for(InvResultReportPrintingVO tempVo:lstInvResultReportPrintingVO)
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
	
	
		//Start a Duplicate Result Reprot Printing Process Added By Basha on 08-06-2015
		
			public Map  LabComboForDuplicateResultReportPrinting(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					InvDuplicateResultReportPrintingDAO invresultreportprintingdao = new InvDuplicateResultReportPrintingDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=invresultreportprintingdao.LabComboForDuplicateResultReportPrinting(invresultreportprintingvo,_UserVO);
					mp.put(InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING, labcombo);


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
		
			
			
			public Map setDuplicateResultReportPrintingEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
				List labNoCombo=new ArrayList();
				List testCombo=new ArrayList();
				List sampleNoCombo=new ArrayList();
				List testGroupCombo=new ArrayList();

				List<InvDuplicateResultReportPrintingVO> groupModified_lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
				List groupCode=new ArrayList();

				try
				{
					tx.begin();
					InvDuplicateResultReportPrintingDAO invresultreportprintingdao = new InvDuplicateResultReportPrintingDAO(tx);

					lstInvResultReportPrintingVO=invresultreportprintingdao.setDuplicateResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);

					mp.put(InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
					
					
					
					for(InvDuplicateResultReportPrintingVO tempVo:lstInvResultReportPrintingVO)
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
	
			
			
			
			
			
			
			public Map setDuplicateResultReportPrintingEssentials(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
				List labNoCombo=new ArrayList();
				List testCombo=new ArrayList();
				List sampleNoCombo=new ArrayList();
				List testGroupCombo=new ArrayList();

				List<InvDuplicateResultReportPrintingVO> groupModified_lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
				List groupCode=new ArrayList();

				try
				{
					tx.begin();
					InvDuplicateResultReportPrintingDAO invresultreportprintingdao = new InvDuplicateResultReportPrintingDAO(tx);

					lstInvResultReportPrintingVO=invresultreportprintingdao.setDuplicateResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);

					mp.put(InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
					
					
					
					
					
					for(InvDuplicateResultReportPrintingVO tempVo:lstInvResultReportPrintingVO)
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
			
			
			
			
			public Map duplicatePdfDetails(List<InvDuplicateResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();

				
				InvDuplicateResultReportPrintingVO invresultreportp=new InvDuplicateResultReportPrintingVO();

				try
				{
					tx.begin();
					
					InvDuplicateResultReportPrintingDAO invresultreportprintingdao = new InvDuplicateResultReportPrintingDAO(tx);
					for(InvDuplicateResultReportPrintingVO invresultreportprintvo:invresultreportprintingvo)
					{
						
						invresultreportp=invresultreportprintingdao.getDuplicatePdfName(invresultreportprintvo, _UserVO);

						
						lstInvResultReportPrintingVO.add(invresultreportp);
						
						
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
			
			public Map getSampleAcceptanceDetailBarCode(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();


				try
				{
					tx.begin();
					SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);

					lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetailBarCode(sampleAcceptanceVO, _UserVO);


					Map<String,List<SampleAcceptanceVO>> objMapSamAcc= new HashMap<String,List<SampleAcceptanceVO>>();
					String strPAckingListNoTemp = null;
					for(int i=0; i<lstSampleAcceptanceVO.size();i++)
					{
						SampleAcceptanceVO objSampleAcceptanceVO = lstSampleAcceptanceVO.get(i);
						List<SampleAcceptanceVO> lstTempSampleAcceptanceVO = null;
						String strPackingListNo = objSampleAcceptanceVO.getPackingListNO();

						lstTempSampleAcceptanceVO=objMapSamAcc.get(strPackingListNo);

						if(lstTempSampleAcceptanceVO==null)
						{
							lstTempSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();
							lstTempSampleAcceptanceVO.add(objSampleAcceptanceVO);
						}
						else
						{
							lstTempSampleAcceptanceVO.add(objSampleAcceptanceVO);
						}

						objMapSamAcc.put(strPackingListNo,lstTempSampleAcceptanceVO);

					}

					//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);MAP_SAMPLE_ACCEPTANCE_VO//MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO
					mp.put(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO,objMapSamAcc);
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
			
			
			
			public Map getSampleAcceptanceDetailBarCode1(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();


				try
				{
					tx.begin();
					SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);

					lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetailBarCode1(sampleAcceptanceVO, _UserVO);


					//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);MAP_SAMPLE_ACCEPTANCE_VO//MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO
					mp.put(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS,lstSampleAcceptanceVO);
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
			
			//reject sample acceptance 
			
			public  Map rejectSampleAccDetails(List<SampleAcceptanceVO> voSampleAcc,UserVO _userVO,HttpServletRequest _request)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

				
				List   listSampleRejected =new ArrayList();
				
				String keyy="";

				boolean isComplete=true;
				List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();
				
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
					SampleAcceptanceVO voSampl=new SampleAcceptanceVO();

					String sameSampleNO="";
					
					for(SampleAcceptanceVO voSamAcc:voSampleAcc)
					{
						
						objSampleAcceptanceDAO.updateSampleAccInRequisitionDtlToReject(voSamAcc,_userVO);
						objSampleAcceptanceDAO.updateSampleAccInSampleDtlToReject(voSamAcc,_userVO);
					
						
						
						
						
						String requisitionNumber=voSamAcc.getRequisitionNo();

						InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();   

						//added parameters value by chandan
						
						if(voSamAcc.getShortSampleName()!=null && voSamAcc.getShortSampleName().equalsIgnoreCase("bld"))
						{
							
							/*voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"|BLD!");*/
							voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");
						}
						else
						{
							
						   /* voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");*/
							 voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");
						
						}
						
//						voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
						//	voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
							Inv_RequisitionRaisingPatientVO patVO=null;
			                      	String crno=voSamAcc.getPatCRNo();
							patVO=objSampleAcceptanceDAO.getInvRaisingPatDetailsnew(crno,_userVO,requisitionNumber);
							
							String  requisitionTypeForBilling="";
							
							if(patVO.getPatvisittypecode()==null)
								requisitionTypeForBilling="1";
							else
								requisitionTypeForBilling=patVO.getPatvisittypecode();
							
							
							  /*if(patVO.getPatStatusCode()!=null && patVO.getPatStatusCode().equals("2"))
								{						  
									requisitionTypeForBilling="2";
								}
								else 
								{
									//visit type code 1-opd, 2,3-emergency, 4 special
									if(patVO.getPatvisittypecode()==null)
										requisitionTypeForBilling="1";
									else{
										
									
									if(patVO.getPatvisittypecode().equals("1"))
										requisitionTypeForBilling="1";
									if(patVO.getPatvisittypecode().equals("4"))
										requisitionTypeForBilling="4";
									if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
										requisitionTypeForBilling="3";
									}*/
									
								//}
						
							if(voBillingDtl.getTariffDetails()==null)
							{
								voBillingDtl.setTariffDetails(new ArrayList<String>());
								voBillingDtl.setTariffQty(new ArrayList<String>());
							}
							
							voBillingDtl.getTariffDetails().add(voSamAcc.getLabCode()+voSamAcc.getTestCode());
							voBillingDtl.getTariffQty().add("1");
							
							String simpletariffdetails="";
							String simpletariffQty="";
							String makeBillingTestWise="";
							
							if(voBillingDtl.getTariffDetails()!=null)
							{
								for(int indexCounter=0;indexCounter<voBillingDtl.getTariffDetails().size();indexCounter++)
								{
									if(indexCounter==0)
									{
										simpletariffdetails=voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
										simpletariffQty=voBillingDtl.getTariffQty().get(indexCounter);
									}
									else
									{
										simpletariffdetails+="^"+voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
										simpletariffQty+="^"+voBillingDtl.getTariffQty().get(indexCounter);
									}
									
									
								}
							}
							
							
							if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
							{
								 makeBillingTestWise="1";//procedure
							
							    if(voSamAcc.getGroupCode()!=null && !voSamAcc.getGroupCode().equals("null") && (!voSamAcc.getGroupCode().equals("0") && !voSamAcc.getGroupCode().equals("")))
							    {
							    	makeBillingTestWise="4";//grpwise
							    	simpletariffdetails=voSamAcc.getLabCode()+voSamAcc.getGroupCode();
							    }
								 
							}
							
							if(voSamAcc.getRejectionAction().equals("1") )   // rescheduled check
							{
							   
							/*if( (patVO.getPatCategoryGroup().equalsIgnoreCase("3")) || (patVO.getPatCategoryGroup().equalsIgnoreCase("4"))) //pat cat group check if 3 n 4 not refund 
								{}
							else
							{*/
							             
							               if( voSamAcc.getGroupCode()!=null && !voSamAcc.getGroupCode().equals("null") && ( !voSamAcc.getGroupCode().equals("0") && !voSamAcc.getGroupCode().equals("")) && keyy.equals(""))
							               {
							            	   objSampleAcceptanceDAO.makeRefund(voSamAcc,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
							            	   keyy=voSamAcc.getReqNo()+"#"+voSamAcc.getGroupCode();
							            	            	   
							               }
					        	           else if(   voSamAcc.getGroupCode()!=null && !voSamAcc.getGroupCode().equals("null") && ( !voSamAcc.getGroupCode().equals("0") && !voSamAcc.getGroupCode().equals("")) && !keyy.equals(""))
							               {
							            	   
							            	   if(!keyy.equals(voSamAcc.getReqNo()+"#"+voSamAcc.getGroupCode()))
							            	   {
							            		   objSampleAcceptanceDAO.makeRefund(voSamAcc,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
							            		   keyy=voSamAcc.getReqNo()+"#"+voSamAcc.getGroupCode();
								            	   
							            	   }
							               
							               }
							        
							               
							               if(voSamAcc.getGroupCode()==null || voSamAcc.getGroupCode().equals("null") ||  voSamAcc.getGroupCode().equals("") || voSamAcc.getGroupCode().equals("0"))
							                   objSampleAcceptanceDAO.makeRefund(voSamAcc,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
											
			
							               /*}*/
						
							   			listSampleRejected.add("Packing List No= "+voSamAcc.getPackingListNO()+"       ,Lab Name="+voSamAcc.getLabName()+"      ,Test Name ="+voSamAcc.getTestName());
										
							}
							
						
						
						
			

						
						
						
						
						
						
					}
					
					//Put List in Map
					mp.put(InvestigationConfig.LIST_REJECTED, listSampleRejected);
					

					SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);
					SampleAcceptanceVO vo=new SampleAcceptanceVO();
					//Logic For Update Status In Packing List Dtl Table
					
					
					lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetailForCheckPackNoToReject(vo, _userVO);
					for(SampleAcceptanceVO voSamAccForCheckPackNo:voSampleAcc)
					{
						String strPackingList=voSamAccForCheckPackNo.getPackingListNO();
						Map<String,List<SampleAcceptanceVO>> objMapSamAcc= new HashMap<String,List<SampleAcceptanceVO>>();
						for(int i=0; i<lstSampleAcceptanceVO.size();i++)
						{
							SampleAcceptanceVO objSampleAcceptanceVO = lstSampleAcceptanceVO.get(i);
							List<SampleAcceptanceVO> lstTempSampleAcceptanceVO = null;
							String strPackingListNo = objSampleAcceptanceVO.getPackingListNO();

							lstTempSampleAcceptanceVO=objMapSamAcc.get(strPackingListNo);
							if(strPackingListNo!= null)
							{
								if(strPackingListNo.equals(strPackingList))
								{
									isComplete=false;
								}
							}
						}	 
						if(isComplete)
						{
							voSamAccForCheckPackNo.setPackingListTableStatus(InvestigationConfig.HIVBL_LIST_STATUS_COMPLETE);
							objSampleAcceptanceDAO.updateInPackingListDtl(voSamAccForCheckPackNo,_userVO);
						}
						else
						{
							voSamAccForCheckPackNo.setPackingListTableStatus(InvestigationConfig.HIVBL_LIST_STATUS);	 
							objSampleAcceptanceDAO.updateInPackingListDtl(voSamAccForCheckPackNo,_userVO); 
						}
						break;
					}

				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				}
				catch (Exception e)
				{
					e.printStackTrace();
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
			
		public Map  autoCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				try
				{
					tx.begin();
					InvResultEntryDAO onlinePatientDao = new InvResultEntryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					lstCannedDetails=onlinePatientDao.autoCannedDetails(labCode,cannedMacroCheck,_UserVO);
					
					
					if(lstCannedDetails!=null)
					{
						StringBuilder sb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstCannedDetails.size() - 1 ; i++) {
							sb.append("{\"label\":\""+((Entry)lstCannedDetails.get(i)).getValue()+"\" ,\"value\": \""+((Entry)lstCannedDetails.get(i)).getLabel()+"\" }");
							sb.append(",");
						}

						
						// last string, no separator
						if(lstCannedDetails.size() > 0){
							sb.append("{\"label\": \""+((Entry)lstCannedDetails.get(lstCannedDetails.size()-1)).getValue()+"\" ,\"value\": \""+((Entry)lstCannedDetails.get(lstCannedDetails.size()-1)).getLabel() +"\" }");
						}

						finalList="["+sb.toString()+"]";
					}
					
					
					mp.put(InvestigationConfig.LIST_CANNED_CODE_FILE_DETAILS, finalList);
				

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
			
						

			public Map getLabTestMachine(String labCode, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String labTestMachine="<option value='-1'>No Machine</option>";
				String testMachine="";
				List machinelist=new ArrayList();
				try {

					tx.begin();

					SampleAcceptanceDAO sampleAcceptanceDao = new SampleAcceptanceDAO(tx);

				
					testMachine=sampleAcceptanceDao.getLabTestMachine(labCode, _UserVO);
					

					machinelist=sampleAcceptanceDao.machinelistnew(labCode,_UserVO);
					mp.put(InvestigationConfig.MACHINE_LIST_ACCEPTANCE, machinelist);
					
					if(testMachine!=null&&testMachine.length()>0)
					{
						String[] multiTestMachine=testMachine.split("@");
						
						for(String getValue:multiTestMachine)
						{
							
							labTestMachine=labTestMachine+"<option value='"+getValue.split("#")[0]+"'>"+getValue.split("#")[1]+"</option>";
						}
					}
					mp.put(InvestigationConfig.LIST_MACHINE_COMBO,labTestMachine);
					mp.put(InvestigationConfig.LIST_MACHINE_STRING, testMachine);


				} catch (HisApplicationExecutionException e) {
					throw new HisApplicationExecutionException();
				} catch (HisDataAccessException e) {
					throw new HisDataAccessException();
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
					System.out.println("error.... Essential BO");
				} finally {

					tx.close();
				}

				return mp;
			}
			
			
			
			public Map  LabComboForMachineResultEntry(machineResultEntryVO resultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					machineResultEntryDAO onlinePatientDao = new machineResultEntryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.LabComboForMachineResultEntry(resultentryvo,_UserVO);
					mp.put(InvestigationConfig.LAB_COMBO_FOR_MACHINE_RESULT_ENTRY, labcombo);


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
			
			
			public Map getPatientMachineResultEntry(machineResultEntryVO resultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<machineResultEntryVO> lstInvResultEntryVO=new ArrayList<machineResultEntryVO>();
				List<machineResultEntryVO> lstInvResultEntryVOall=new ArrayList<machineResultEntryVO>();


				
				 
				try
				{
					tx.begin();
					machineResultEntryDAO invresultentrydao = new machineResultEntryDAO(tx);
					lstInvResultEntryVO=invresultentrydao.getPatientMachineResultEntry(resultentryvo, _UserVO);
					
					/*if(lstInvResultEntryVO!=null && lstInvResultEntryVO.size()>0)
					{
						lstInvResultEntryVOall=lstInvResultEntryVO;
						
						
						
						for(int k=0;k<lstInvResultEntryVOall.size();k)
						
					}*/
					
					mp.put(InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultEntryVO);
					
				
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
			
			
			public Map  getLabBasedMachine(machineResultEntryVO resultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					machineResultEntryDAO onlinePatientDao = new machineResultEntryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.getLabBasedMachine(resultentryvo,_UserVO);
					mp.put(InvestigationConfig.LIST_MACHINE_COMBO, labcombo);


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
			
			public  Map saveMachineResultEntry(Map<String,List<machineResultEntryVO>> mp_resultEntry,UserVO _userVO,HttpServletRequest _request)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

				List   listResultEntryDtl =new ArrayList();
				List<machineResultEntryVO> invResultentryVO=new ArrayList<machineResultEntryVO>();
				String sampleDtlParaCount="";
				int intSampleDtlParaCount=0;					
				int entriesPresent=0;
				boolean isComplete=true;
				List<machineResultEntryVO> lstResultEntryVO=new ArrayList<machineResultEntryVO>();
				 
				int sizeOfMap=mp_resultEntry.size();
				
				
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					machineResultEntryDAO objResultEntrtyDAO=new machineResultEntryDAO(tx);
					
					
					//fetch the ref range values
					 List<TestMandRefMasterVO > objRefRangeList = objResultEntrtyDAO.getRefRangeValues(_userVO);
					 
					//fetch criteria code values
					 List<InvCriteriaCodeVO > objCriteriaCodeList = objResultEntrtyDAO.getCriteriaCodeValues();
					 
					 
					Iterator itrLabTestString=mp_resultEntry.keySet().iterator();
					
					
					while(itrLabTestString.hasNext())
					{
					
						String labTestString=(String)itrLabTestString.next();
						
						
						invResultentryVO=mp_resultEntry.get(labTestString);
						//fetch para count from hmit_sample_dtl for the given test on given machine
						if(!labTestString.contains("null"))
						{
						sampleDtlParaCount=objResultEntrtyDAO.getParaCount(labTestString,_userVO);
						
						 intSampleDtlParaCount=Integer.parseInt(sampleDtlParaCount);						
						 entriesPresent=invResultentryVO.size();
						}
						
						
						
						
						
					for(machineResultEntryVO voInvResultEntry:invResultentryVO)
					{
						if(voInvResultEntry.getRecord().equals("1"))
						
						{
						
						//check if sampleDtlParaCount equals paracount of hmit_machine_testpara_mst
												
						if(entriesPresent+intSampleDtlParaCount>=Integer.parseInt(voInvResultEntry.getmachineTestParameterParaCount()))
							voInvResultEntry.setSampleStatus("3"); //completed
						else
							voInvResultEntry.setSampleStatus("2"); //partial
				
						String updateParaCount=Integer.toString(entriesPresent+intSampleDtlParaCount);
						
						
						//get the ref range
						String refRange=InvResultEntryRespBO.getRefRangeForTestPara(objRefRangeList,objCriteriaCodeList,voInvResultEntry.getTestCode(),voInvResultEntry.getParameterCode(),voInvResultEntry.getPatGender(),voInvResultEntry.getPatAge());
						voInvResultEntry.setRefRange(refRange);
						//result validation status
						voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);
						//update in req dtl
						objResultEntrtyDAO.updateMachineResultEntryInRequisitionDtl(voInvResultEntry,_userVO);
						//insert in parameter dtl
						
						String count=objResultEntrtyDAO.isparameterexist( voInvResultEntry,  _userVO);
					
						if(count.equals("") || count.equals("0"))
						objResultEntrtyDAO.insertMachineResultEntryDtl(voInvResultEntry,_userVO);
						else
						objResultEntrtyDAO.updatetestparameterdtl(voInvResultEntry,_userVO);

						  String value = voInvResultEntry.getMachineResult().equals("") ? "--" : voInvResultEntry.getMachineResult();
					      
					      String properParaValues=voInvResultEntry.getParameterCode() +"#@"+"-"+"#@"+voInvResultEntry.getRefRange()+"#@"+value+"#@"+"`";
					      getrangeeentry(	voInvResultEntry.getReqDNo(),voInvResultEntry.getTestCode()+voInvResultEntry.getParameterCode(),voInvResultEntry.getTestCode(),voInvResultEntry.getParameterCode(),voInvResultEntry.getPatGender(),voInvResultEntry.getPatAge(),value,_userVO,tx);
							
					      
						//update hmit result dtl
						objResultEntrtyDAO.updateResultDtl(voInvResultEntry,_userVO);
						//update hmit sample dtl
						objResultEntrtyDAO.updateMachineSampleDtl(voInvResultEntry,_userVO,updateParaCount);
						}
						else
						{
							
							
							objResultEntrtyDAO.updateResultDtl(voInvResultEntry,_userVO);
							
						}
						
						
					}
					
					}
						
										
					 
					//Put List in Map
					mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultEntryDtl);
					 

				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				}
				catch (Exception e)
				{
					e.printStackTrace();
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
			
			
			
			//MACHINE ENQUIRY PROCESS//
			
			

			public Map  getMachineBasedSampleNo(machineEnquiryVO Enquiryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List sampleNoCombo=new ArrayList();

				try
				{
					tx.begin();
					machineEnquiryDAO onlinePatientDao = new machineEnquiryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					sampleNoCombo=onlinePatientDao.getMachineBasedSampleNo(Enquiryvo,_UserVO);
					mp.put(InvestigationConfig.SAMPLE_NO_MACHINE_ENQUIRY, sampleNoCombo);


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
			
			
			public Map getPatientmachineEnquiry(machineEnquiryVO Enquiryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<machineEnquiryVO> lstInvEnquiryVO=new ArrayList<machineEnquiryVO>();
		

				
				 
				try
				{
					tx.begin();
					machineEnquiryDAO invEnquirydao = new machineEnquiryDAO(tx);
					lstInvEnquiryVO=invEnquirydao.getPatientmachineEnquiry(Enquiryvo, _UserVO);
					mp.put(InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO,lstInvEnquiryVO);
					
				
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
			
			
			public Map  getMachineComboForEnquiry(machineEnquiryVO Enquiryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List machineCombo=new ArrayList();

				try
				{
					tx.begin();
					machineEnquiryDAO onlinePatientDao = new machineEnquiryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					machineCombo=onlinePatientDao.getMachineComboForEnquiry(Enquiryvo,_UserVO);
					mp.put(InvestigationConfig.LIST_MACHINE_COMBO_MACHINE_ENQUIRY, machineCombo);


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
			
			
			private static String getRefRangeForTestPara( List<TestMandRefMasterVO > objRefRangeList,  List<InvCriteriaCodeVO > objCriteriaCodeList, String strTestCode, String strTestParaCode, String strGender, String strAge)
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
							if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
								strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getHighValue() + "$" +  objRefRange.getLowValue() + "$" + objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" + objRefRange.getSymbol()  ;
								else
									strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +  objRefRange.getRange() +"$" + objRefRange.getSymbol() ;	// for range like >,< (unit,range,symbol)
								break;
						}
					}

					
					if( InvestigationConfig.REFERENCE_RANGE_CRITERIA_AGE.equalsIgnoreCase(strCriteriaCode)&&objRefRange.getLowAge()!=null)
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
						//System.out.println("refRangehighAge"+highAge);
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
						
						if(objRefRange.getTestCode().equalsIgnoreCase(strTestCode) && objRefRange.getParameterCode().equalsIgnoreCase(strTestParaCode)&&objRefRange.getGender().equals(gender))
						{

							if(objRefRange.getRangeTyp().equalsIgnoreCase("1")) // range from-to
								strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getHighValue() + "$" +  objRefRange.getLowValue() + "$" + objRefRange.getHighValueUom() + "$" + objRefRange.getLowValueUom() + "$" + objRefRange.getSymbol() ;
								else
									strReferanceRangeString = objRefRange.getRangeTyp() + "$" + objRefRange.getRangeUom() + "$" +  objRefRange.getRange() +"$" + objRefRange.getSymbol() ;	// for range like >,< (unit,range,symbol)	
								
								

								break;
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
				return strReferanceRangeString;
			}
			
			/*********************************external investigation capture process***********************************************/	
			
			/* Function to search Laboatory wise Test Details */
			public Map external_searchLabWiseTestDtls(InvestigationSearchVO searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				List<LabTestVO> lstLabTestSample=null;
				List<LabTestVO> lstLabTestForTestGruop=null;
				String labNames="";
				String testNames="";
				String testCode="";
				List lstExternalLabs=null;
				List lstLabs=null;
				List lstTest=null;
				List lstSample=null;
				List lstParameter=null;
				
				
				//List<LabTestVO> lstPreviousLabTest=null;
				
				Map mp=new HashMap();
				StringBuilder labBuild = new StringBuilder();
				StringBuilder testBuild = new StringBuilder();
				StringBuilder testCodeBuild = new StringBuilder();
				
				try {

					tx.begin();

					externalInvestigationCaptureDAO invEssentialDAO=new externalInvestigationCaptureDAO(tx);
					lstExternalLabs=invEssentialDAO.getExternalLabNames(_UserVO);
					lstLabs=invEssentialDAO.getLabNames(_UserVO);
					lstTest=invEssentialDAO.getTestNames_external(_UserVO);
					lstSample=invEssentialDAO.getSampleCombo_external( _UserVO);
					lstParameter=invEssentialDAO.getParameterNames_external( _UserVO);
					
					
					mp.put(InvestigationConfig.ARRAY_LABNAMES_EXTERNAL, lstExternalLabs);
					mp.put(InvestigationConfig.ARRAY_LABNAMES, lstLabs);
					mp.put(InvestigationConfig.ARRAY_TESTNAMES, lstTest);
					mp.put(InvestigationConfig.ARRAY_SAMPLENAMES, lstSample);
					mp.put(InvestigationConfig.ARRAY_PARAMETERNAME, lstParameter);
					
					
					
				} catch (HisApplicationExecutionException e) {
					throw new HisApplicationExecutionException();
				} catch (HisDataAccessException e) {
					throw new HisDataAccessException();
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
					System.out.println("error.... Essential BO");
				} finally {

					tx.close();
				}

				return mp;
			}


			//using to save external investigation captures
			public  List external_saveRequisitionDetails(List<externalInvestigationCaptureVO> lstExternalCapture,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List listReqId=new ArrayList();
				String crNO="";
				
				try
				{    
					tx.begin();
					externalInvestigationCaptureDAO invEssentialDAO=new externalInvestigationCaptureDAO(tx);
				
					for(externalInvestigationCaptureVO external_vo: lstExternalCapture)
					{
						
						invEssentialDAO.insertRequisitionDtl(external_vo,_userVO);	//calling procedure to add into req dtl table
						crNO=external_vo.getPatCrNo();
						
					}
					
																								
								
								
									
				
							
						//Add The Requisition Number in list
							listReqId.add(crNO);
					
								
				return listReqId;	
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
			}

	
			
			//to fetch packing list details for duplicate pak list generation
			public Map getPackingListDetails(SampleAcceptanceVO packListVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<SampleAcceptanceVO> lstPackListVO=new ArrayList<SampleAcceptanceVO>();


				try
				{
					tx.begin();
					PackingListGenerationDAO packListDAO=new PackingListGenerationDAO(tx);
					
					
					lstPackListVO=packListDAO.getPackingListDetails(packListVO, _UserVO);


					Map<String,List<SampleAcceptanceVO>> objMapSamAcc= new HashMap<String,List<SampleAcceptanceVO>>();
					String strPAckingListNoTemp = null;
					for(int i=0; i<lstPackListVO.size();i++)
					{
						SampleAcceptanceVO objPackListVO = lstPackListVO.get(i);
						List<SampleAcceptanceVO> lstTempVO = null;
						String strPackingListNo = objPackListVO.getPackingListNO();

						lstTempVO=objMapSamAcc.get(strPackingListNo);

						if(lstTempVO==null)
						{
							lstTempVO=new ArrayList<SampleAcceptanceVO>();
							lstTempVO.add(objPackListVO);
						}
						else
						{
							lstTempVO.add(objPackListVO);
						}

						objMapSamAcc.put(strPackingListNo,lstTempVO);

					}

					//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);
					mp.put(InvestigationConfig.MAP_PACK_LIST_DETAILS_VO,objMapSamAcc);
					
					
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
			
			
			
			//test availability**************************************************
			
			public Map  LabComboForTestAvailability(testAvailabilityVO resultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					testAvailabilityDAO onlinePatientDao = new testAvailabilityDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.LabComboForMachineResultEntry(resultentryvo,_UserVO);
					mp.put(InvestigationConfig.LAB_COMBO_FOR_TEST_AVAILABILITY, labcombo);


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
			
			
			
			public Map getTestDetails(testAvailabilityVO resultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<testAvailabilityVO> lstInvResultEntryVO=new ArrayList<testAvailabilityVO>();
		

				
				 
				try
				{
					tx.begin();
					testAvailabilityDAO invresultentrydao = new testAvailabilityDAO(tx);
					lstInvResultEntryVO=invresultentrydao.getPatientMachineResultEntry(resultentryvo, _UserVO);
					mp.put(InvestigationConfig.TEST_AVAILABILITY_DETAILS,lstInvResultEntryVO);
					
				
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
			
			
			public  Map updateTestDetails(List<testAvailabilityVO> mp_resultEntry,UserVO _userVO,HttpServletRequest _request)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

				List   listResultEntryDtl =new ArrayList();
				List<testAvailabilityVO> invResultentryVO=new ArrayList<testAvailabilityVO>();
				List<testAvailabilityVO> lstResultEntryVO=new ArrayList<testAvailabilityVO>();
				 
				
				System.out.println("to update list");
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					testAvailabilityDAO objResultEntrtyDAO=new testAvailabilityDAO(tx);
					
							
						
					for(testAvailabilityVO voInvResultEntry:mp_resultEntry)
					{
											
						//update lab test mst
						objResultEntrtyDAO.updateResultDtl(voInvResultEntry, _userVO);
						
						//insert into log table 
						objResultEntrtyDAO.insertLogDetailsTest(voInvResultEntry, _userVO);
						System.out.println("update done");
					}
					
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				}
				catch (Exception e)
				{
					e.printStackTrace();
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
			
			
			
			public  List saveFilmDetails(List<filmUsedVO> lstFilm,List<filmUsedVO> lstFilmAdd,List<filmUsedVO> lstFilmWaste,filmUsedVO filmusedvo,UserVO _userVO,Map mp)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List listReqdtlId=new ArrayList();

				try
				{    
					tx.begin();
					filmUsedDAO filmUsedDao=new filmUsedDAO(tx);
					
					
					String count=filmUsedDao.isrequisitionexist(filmusedvo.getRequisitionDNo(),_userVO);
				
					if(count==null || count.equals(""))
					filmUsedDao.insertUsedDetails(filmusedvo, _userVO);
					else
					filmUsedDao.updateFilmNodata(count,filmusedvo, _userVO);
					
					
					filmUsedDao.updateFilmNo(filmusedvo, _userVO);
					
					filmUsedDao.updateFilmFlag(filmusedvo, _userVO);
					
	Iterator itrBatch=mp.keySet().iterator();
					
					while(itrBatch.hasNext())//for(int i=0;i<size;i++)
					{
						
						String batchString=(String)itrBatch.next();
						if((batchString!=null && !batchString.equals("")))
						{
							if(!batchString.split("#")[1].equals(""))
							{
						float remainingInventory=Float.parseFloat(batchString.split("#")[1])-Integer.parseInt(mp.get(batchString).toString());
						
						batchString+="#"+remainingInventory;
						filmUsedDao.updateInventory(batchString, _userVO);
							}
						}
					
					}
					
					
					
					
					if(lstFilm!=null)
					{
						for(filmUsedVO filmVo:lstFilm)
						{
							
							filmUsedDao.insertFilmDetail(filmVo, _userVO);
							
						}
															
					}
					
					
					
					
					
					if(lstFilmAdd!=null)
					{
						for(filmUsedVO filmVo:lstFilmAdd)
						{
							
							filmUsedDao.insertFilmDetail(filmVo, _userVO);	
							
						}
															
					}
					
					
					
					
					if(lstFilmWaste!=null)
					{
						for(filmUsedVO filmVo:lstFilmWaste)
						{
							
							filmUsedDao.insertFilmDetail(filmVo, _userVO);
							
						}
															
					}

			
					
					
					
					
					return listReqdtlId;	
				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}

			}

			
			
			public Map setPatientEssentialsOnLoad(filmUsedVO onlinePatientvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<filmUsedVO> lstOnlinePatientAcceptanceVO=new ArrayList<filmUsedVO>();


				try
				{
					tx.begin();
					filmUsedDAOi onlinePatientDaoi = new filmUsedDAO(tx);

					lstOnlinePatientAcceptanceVO=onlinePatientDaoi.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);

					mp.put(InvestigationConfig.LIST_TEST_DETAILS_FILM_USED,lstOnlinePatientAcceptanceVO);
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
			
			
			
			public Map setPatientEssentials(filmUsedVO onlinePatientvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<filmUsedVO> lstFilmUsedvo=new ArrayList<filmUsedVO>();


				try
				{
					tx.begin();
					filmUsedDAOi onlinePatientDaoi = new filmUsedDAO(tx);

					lstFilmUsedvo=onlinePatientDaoi.setPatientEssentials(onlinePatientvo, _UserVO);

					mp.put(InvestigationConfig.LIST_TEST_DETAILS_FILM_USED,lstFilmUsedvo);
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
			
			
			
			
			public Map patientDetails(filmUsedVO filmvo,List<String> reqList, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String filmNo="";
				List<String> batchDetails= new ArrayList<String>();
				
				List filmSizeCombo=new ArrayList();
				List<filmUsedVO> patprevrequsition=new ArrayList<filmUsedVO>();
				try
				{
					tx.begin();
					filmUsedDAOi filmdao = new filmUsedDAO(tx);
		
					filmSizeCombo=filmdao.getFilmSizeCombo(filmvo, _UserVO);
					
					patprevrequsition=filmdao.getprevrequisition(filmvo.getRequisitionDNo(), _UserVO);
					mp.put(InvestigationConfig.PAT_PREV_REQUISITION, patprevrequsition);

					
					mp.put(InvestigationConfig.LIST_FILM_SIZE_FILM, filmSizeCombo);
					
					batchDetails=filmdao.getBatchDetails(filmvo, _UserVO);
					mp.put(InvestigationConfig.LIST_STRING_BATCH_DETAILS, batchDetails);
					
					filmNo=filmdao.getFilmNo(filmvo, _UserVO);
					filmvo.setTempFilmNo(filmNo);
			

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

			
			

			
			public Map  LabCombos(filmUsedVO filmusedvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					filmUsedDAOi onlinePatientDaoi = new filmUsedDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDaoi.getLabCombos(filmusedvo,_UserVO);
					mp.put(InvestigationConfig.FILM_USED_LAB_COMBO, labcombo);


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


			public Map  LabComboForTestWiseConsumable(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					TestWiseConsumableDAO testWiseConsumableDAO = new TestWiseConsumableDAO(tx);
					labcombo=testWiseConsumableDAO.LabComboForResultEntry(testWiseConsumableVO,_UserVO);
					mp.put(InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY, labcombo);


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
			public Map setPatientTestWiseConsumableEssentials(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<TestWiseConsumableVO> lstInvResultEntryVO=new ArrayList<TestWiseConsumableVO>();
				List<TestWiseConsumableVO> groupModified_lstInvResultEntryVO=new ArrayList<TestWiseConsumableVO>();
				List labNoCombo=new ArrayList();
				List testCombo=new ArrayList();
				List sampleNoCombo=new ArrayList();
				List testGroupCombo=new ArrayList();
				List groupCode=new ArrayList();

				
				 
				try
				{
					tx.begin();
					TestWiseConsumableDAO testWiseConsumableDAO = new TestWiseConsumableDAO(tx);

					lstInvResultEntryVO=testWiseConsumableDAO.setPatientResultEntryEssentials(testWiseConsumableVO, _UserVO);

					mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultEntryVO);
					
					
					for(TestWiseConsumableVO tempVo:lstInvResultEntryVO)
					{
						if(tempVo.getGroupCode()!=null)
						{
							if(!groupCode.contains(tempVo.getGroupCode()+tempVo.getRequisitionNo()))
							{
								groupCode.add(tempVo.getGroupCode()+tempVo.getRequisitionNo());
								
								groupModified_lstInvResultEntryVO.add(tempVo);
							}
						}
						else
							groupModified_lstInvResultEntryVO.add(tempVo);
						
					}
					
					
					mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultEntryVO);

					labNoCombo=testWiseConsumableDAO.setLabNoComboEssentials(testWiseConsumableVO, _UserVO);

					mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,labNoCombo);
					  
						testCombo=testWiseConsumableDAO.setTestComboEssentials(testWiseConsumableVO, _UserVO);
					 
					mp.put(InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY,testCombo);
					
					sampleNoCombo=testWiseConsumableDAO.setSamplNoComboEssentials(testWiseConsumableVO, _UserVO);

					mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
					
					testGroupCombo=testWiseConsumableDAO.setTestGroupComboEssentials(testWiseConsumableVO, _UserVO);

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
			public Map getTestWiseConsumableList(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				TestWiseConsumableVO[] lstInvResultEntryVO=null;
				TestWiseConsumableVO[] tempList=null;
				List lotCombo=new ArrayList();
				
				Entry []results=null;
				Entry entry=new Entry();
				try
				{
					tx.begin();
					TestWiseConsumableDAO testWiseConsumableDAO = new TestWiseConsumableDAO(tx);
					lstInvResultEntryVO=testWiseConsumableDAO.getTestWiseConsumableListConsumableDetail(testWiseConsumableVO, _UserVO);
					mp.put(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE,lstInvResultEntryVO);
					tempList=testWiseConsumableDAO.getTestWiseConsumableListALL(testWiseConsumableVO, _UserVO);
					mp.put(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL,tempList);
					//results=new Entry[lstInvResultEntryVO.length];
					
					/*for(int i=0;i<lstInvResultEntryVO.length;i++)
					{
						lotCombo=testWiseConsumableDAO.getLotCombo(lstInvResultEntryVO[i], _UserVO);
						mp.put("lotCombo"+i,lotCombo);
						testWiseConsumableVO.setOtherItemID(lstInvResultEntryVO[i].getOtherItemID());
						entry=testWiseConsumableDAO.getLotNQuantity(testWiseConsumableVO, _UserVO);
						if(null!=entry){
							results[i]=entry;
						}
						else
						{
							Entry nullEntry=new Entry();
							nullEntry.setLabel("-1");
							nullEntry.setValue("-1");
							results[i]=nullEntry;
						}
					}*/
					//mp.put(InvestigationConfig.LIST_TEST_WISE_CONSUMABLE_LOT, results);
					
					
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
			public Map saveConsumableList(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				TestWiseConsumableVO[] lstInvResultEntryVO=null;
				 
				try
				{
					tx.begin();
					TestWiseConsumableDAO testWiseConsumableDAO = new TestWiseConsumableDAO(tx);
					if(testWiseConsumableDAO.getCount(testWiseConsumableVO, _UserVO)>0)
					{
						testWiseConsumableDAO.updateConsumableList(testWiseConsumableVO, _UserVO);
					}
					else{
					testWiseConsumableDAO.saveConsumableList(testWiseConsumableVO, _UserVO);
					}
					
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
			

			public Map getPatientDetailsTestWiseConsumable(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				TestWiseConsumableVO[] lstInvResultEntryVO=null;
				List lotCombo=new ArrayList();
				
				Entry []results=null;
				Entry entry=new Entry();
				try
				{
					tx.begin();
					TestWiseConsumableDAO testWiseConsumableDAO = new TestWiseConsumableDAO(tx);
					lstInvResultEntryVO=testWiseConsumableDAO.getPatientDetails(testWiseConsumableVO, _UserVO);
					mp.put(InvestigationConfig.PATIENT_TEST_WISE_ITEM_CONSUMABLE,lstInvResultEntryVO);
					
					
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
			public Map updateTestWiseConsumableList(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				TestWiseConsumableVO[] lstInvResultEntryVO=null;
				 
				try
				{
					tx.begin();
					TestWiseConsumableDAO testWiseConsumableDAO = new TestWiseConsumableDAO(tx);
					
						testWiseConsumableDAO.deleteItem(testWiseConsumableVO, _UserVO);
					
					
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
			
			


			
			
			
			
			public Map setPatientReportAddendumEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<ResultEntryVO> lstinvReportAddendumVO=new ArrayList<ResultEntryVO>();
				List labNoCombo=new ArrayList();
				List testCombo=new ArrayList();
				List sampleNoCombo=new ArrayList();
				List testGroupCombo=new ArrayList();
	 
				List groupCode=new ArrayList();
				List<ResultEntryVO> groupModified_lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
				try
				{
					tx.begin();
					invReportAddendumDAO invresultentrydao = new invReportAddendumDAO(tx);

					lstinvReportAddendumVO=invresultentrydao.setPatientReportAddendumEssentials(invresultentryvo, _UserVO);
					
					
					
					
					sampleNoCombo=invresultentrydao.setSamplNoComboEssentials(invresultentryvo, _UserVO);

					mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
					
					
					//////logic to group by groupcode /////
					
					for(int i=0;i<lstinvReportAddendumVO.size();i++)
					{
						String concatString=lstinvReportAddendumVO.get(i).getTestParameterName();
						String testCode=lstinvReportAddendumVO.get(i).getTestCode();
						
						String properParaValues="";
						
						String[] parameters = null;
						int paraSize = 0;
//						if(concatString != null)
//						{
							parameters =concatString.split("`");
							paraSize=parameters.length;
						//}
//						else
//						{
//							paraSize=0;
//							continue;
//						}
						
						
						for(int iterate=0;iterate<paraSize;iterate++)
						{
							String[] paraValues=parameters[iterate].split("#@");
							String paraCode=paraValues[0];
							String paraName=paraValues[1];
							String paraEntry=paraValues[3];
							String rangeValue=paraValues[2];
							String comboValue="";
							if(paraEntry.contains("$"))
							{
								String[] multiValue=paraEntry.split("\\$");
								
								for(int k=0;k<multiValue.length;k++)
								comboValue+=invresultentrydao.setComboValueName(testCode,paraCode,multiValue[k])+"$";
								
							}
							else				
							 comboValue=invresultentrydao.setComboValueName(testCode,paraCode,paraEntry);
						 
							if(comboValue!=null)
								properParaValues+=paraCode+"#@"+paraName+"#@"+rangeValue+"#@"+comboValue+"`";
							else
								properParaValues+=parameters[iterate]+"`";
							
							
							
						}
						
				
						properParaValues=properParaValues.substring(0, properParaValues.length()-1);
						lstinvReportAddendumVO.get(i).setTestParameterName(properParaValues);
						
						//to append para values for all group values
						if(lstinvReportAddendumVO.get(i).getGroupCode()!=null)
						{
							
							if(!groupCode.contains(lstinvReportAddendumVO.get(i).getGroupCode()+lstinvReportAddendumVO.get(i).getRequisitionNo()))
							{
							for(int j=i+1;j<lstinvReportAddendumVO.size();j++)
							{
								if(lstinvReportAddendumVO.get(j).getGroupCode()!=null)
								if(lstinvReportAddendumVO.get(i).getGroupCode().equals(lstinvReportAddendumVO.get(j).getGroupCode())  && lstinvReportAddendumVO.get(i).getRequisitionNo().equals(lstinvReportAddendumVO.get(j).getRequisitionNo()))
								{	
								String temp_concatString=lstinvReportAddendumVO.get(j).getTestParameterName();
								String temp_testCode=lstinvReportAddendumVO.get(j).getTestCode();
								
								String temp_properParaValues="";
								
								String[] temp_parameters = null;
								int temp_paraSize = 0;
//								if(concatString != null)
//								{
									temp_parameters =temp_concatString.split("`");
									temp_paraSize=temp_parameters.length;
								//}
//								else
//								{
//									paraSize=0;
//									continue;
//								}
								
								
								for(int iterate=0;iterate<temp_paraSize;iterate++)
								{
									String[] paraValues=temp_parameters[iterate].split("#@");
									String paraCode=paraValues[0];
									String paraName=paraValues[1];
									String paraEntry=paraValues[3];
									String rangeValue=paraValues[2];
									String comboValue="";
									if(paraEntry.contains("$"))
									{
										String[] multiValue=paraEntry.split("\\$");
										
										for(int k=0;k<multiValue.length;k++)
										comboValue+=invresultentrydao.setComboValueName(testCode,paraCode,multiValue[k])+"$";
										
									}
									else				
									 comboValue=invresultentrydao.setComboValueName(temp_testCode,paraCode,paraEntry);
								 
									if(comboValue!=null)
										temp_properParaValues+=paraCode+"#@"+paraName+"#@"+rangeValue+"#@"+comboValue+"`";
									else
										temp_properParaValues+=temp_parameters[iterate]+"`";
									
									
									
								}
								
						
								temp_properParaValues=temp_properParaValues.substring(0, temp_properParaValues.length()-1);
								lstinvReportAddendumVO.get(j).setTestParameterName(temp_properParaValues);
								
								
								lstinvReportAddendumVO.get(i).setTestParameterName(lstinvReportAddendumVO.get(i).getTestParameterName()+"`"+temp_properParaValues);
							}
							}
								groupCode.add(lstinvReportAddendumVO.get(i).getGroupCode()+lstinvReportAddendumVO.get(i).getRequisitionNo());
								groupModified_lstInvResultEntryVO.add(lstinvReportAddendumVO.get(i));
							}
							else
							{;}
							
						}
						else
						{
							groupModified_lstInvResultEntryVO.add(lstinvReportAddendumVO.get(i));
						}
						
						
					
						
						
					}
					

					
		mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultEntryVO);
		mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstinvReportAddendumVO);
					
				
					
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					e.printStackTrace();
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					e.printStackTrace();
					throw new HisApplicationExecutionException();
				}
				catch (HisDataAccessException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					e.printStackTrace();
					throw new HisDataAccessException();
				}
				catch (HisException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					e.printStackTrace();
					throw new HisException();
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					e.printStackTrace();
					tx.rollback();
					throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
				return mp;
			}
			
			
			
			
			//get new patient entries addendum process
			public ResultEntryVO getNewEntriesPatient(ResultEntryVO invresultentryvo,UserVO _uservo)
			{

				
				ResultEntryVO newEntryVO=new ResultEntryVO();
				Map mp = null;
				JDBCTransactionContext tx = new JDBCTransactionContext();
				try {
					tx.begin();
					
					invReportAddendumDAO resultEntryDetailDAO = new invReportAddendumDAO(
							tx);
					
					newEntryVO = resultEntryDetailDAO
							.getNewEntriesPatient(invresultentryvo,_uservo);
					


		
				} catch (HisApplicationExecutionException e) {
					tx.rollback();
					throw new HisApplicationExecutionException();
				}

				catch (HisDataAccessException e) {
					e.printStackTrace();
					tx.rollback();
					throw new HisDataAccessException();
				} catch (Exception e) {
					e.printStackTrace();
					System.out
							.println("Error....Essential BO:setSampleAcceptanceEssential");
					tx.rollback();
					throw new HisApplicationExecutionException();
				} finally {

					tx.close();
				}
				return newEntryVO;
			
			}

		//	getOldEntriesPatient
			
			
			public ResultEntryVO getOldEntriesPatient(ResultEntryVO invresultentryvo,UserVO _uservo)
			{

				
				ResultEntryVO oldEntryVO=new ResultEntryVO();
				Map mp = null;
				JDBCTransactionContext tx = new JDBCTransactionContext();
				try {
					tx.begin();
					
					invReportAddendumDAO resultEntryDetailDAO = new invReportAddendumDAO(
							tx);
					
					oldEntryVO = resultEntryDetailDAO
							.getOldEntriesPatient(invresultentryvo, _uservo);
					


		
				} catch (HisApplicationExecutionException e) {
					tx.rollback();
					throw new HisApplicationExecutionException();
				}

				catch (HisDataAccessException e) {
					e.printStackTrace();
					tx.rollback();
					throw new HisDataAccessException();
				} catch (Exception e) {
					e.printStackTrace();
					System.out
							.println("Error....Essential BO:setSampleAcceptanceEssential");
					tx.rollback();
					throw new HisApplicationExecutionException();
				} finally {

					tx.close();
				}
				return oldEntryVO;
			
			}
			
			
			
			//save function report addendum
			public  Map saveReportAddendumDetails(ResultEntryVO newPatVO,List<ResultEntryVO> oldPatList,List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,String amendType)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

				List   listResultValidationDtl =new ArrayList();
				 
				String[] changeType=amendType.split("#");//diagnosis addendum amendment
				boolean isComplete=true;
				List<ResultEntryVO> lstResultValidationVO=new ArrayList<ResultEntryVO>();
				ResultEntryVO resultEntryVO=new ResultEntryVO();
				Map mp=new HashMap();
				HttpSession session=_request.getSession();
				
				

				try
				{    

					tx.begin();
					invReportAddendumDAO objResultEntrtyDAO=new invReportAddendumDAO(tx);
					
					
					//demographics only case //update details in req header
					if(changeType[0].equals("1") && changeType[1].equals("-") && changeType[2].equals("-"))
					{	
						for(ResultEntryVO tempVO:oldPatList)
						{
							
						tempVO.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);	
						tempVO.setReportChangeFlag("1");
						objResultEntrtyDAO.updateDemographicsInRequisitionHeader(tempVO, newPatVO, _userVO);				
						objResultEntrtyDAO.updateDemographicsInRequisitionHeaderdtl(tempVO, newPatVO, _userVO);				

						
						objResultEntrtyDAO.updateNewValuesInRequisitionDtl(tempVO, _userVO);
					
						//set reason blank for demographics
						String reasonCode="";
						objResultEntrtyDAO.insertAddendumLogDtl(tempVO,_userVO,reasonCode);
						
						tempVO.setPatAge(newPatVO.getPatAge());
						tempVO.setPatGender(newPatVO.getPatGenderCode());
						objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(tempVO,_userVO);
						}
					}
					else //case other than demographics only
					{
					if(changeType[0].equals("1"))
					{
						for(ResultEntryVO tempVO:oldPatList)
						{
							
						tempVO.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);	
						tempVO.setReportChangeFlag("1");
						objResultEntrtyDAO.updateDemographicsInRequisitionHeader(tempVO, newPatVO, _userVO);
						objResultEntrtyDAO.updateDemographicsInRequisitionHeaderdtl(tempVO, newPatVO, _userVO);
						objResultEntrtyDAO.updateNewValuesInRequisitionDtl(tempVO, _userVO);
					//demographics reason
						String reasonCode="";
						objResultEntrtyDAO.insertAddendumLogDtl(tempVO,_userVO, reasonCode);
						
						tempVO.setPatAge(newPatVO.getPatAge());
						tempVO.setPatGender(newPatVO.getPatGenderCode());
						objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(tempVO,_userVO);
						}
						
						
					}
					if(invResultentryVO!=null)
					{
					resultEntryVO=invResultentryVO.get(0);
					if(resultEntryVO.getCrNoReqNoString()!=null)
					for(int i =0;i<resultEntryVO.getCrNoReqNoString().length;i++)
					{				
						objResultEntrtyDAO.updateFinalRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], _userVO);				
						objResultEntrtyDAO.updateFinalRemarkInRequisitionHeaderdtl(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], _userVO);
					}
					
					if(resultEntryVO.getCrNoReqNoStringAddendum()!=null)
						for(int i =0;i<resultEntryVO.getCrNoReqNoStringAddendum().length;i++)
						{				
							objResultEntrtyDAO.updateAddendumRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoStringAddendum()[i].split("#")[1], resultEntryVO.getAddendumRemark()[i], _userVO);				
							objResultEntrtyDAO.updateAddendumRemarkInRequisitionHeaderdtl(resultEntryVO.getCrNoReqNoStringAddendum()[i].split("#")[1], resultEntryVO.getAddendumRemark()[i], _userVO);				

						}
					}
									
					for(ResultEntryVO voInvResultValidationResp:invResultentryVO)
					{
						String reasoncode="";
						voInvResultValidationResp.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);
					//	objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidationResp,_userVO);
						if(changeType[1].equals("1"))
							voInvResultValidationResp.setReportChangeFlag("2");
						else
							voInvResultValidationResp.setReportChangeFlag("3");
						
						
						
						
						objResultEntrtyDAO.updateNewValuesInRequisitionDtl(voInvResultValidationResp, _userVO);
						
						//get value 0 index .... array get from invrsultentry 0 index
						//travese loop of cr no and req no for reason........  the value id that matches cr no and req no of  "voInvResultValidationResp"
						if(invResultentryVO!=null)
						{
						resultEntryVO=invResultentryVO.get(0);
						
						for(int i1 =0;i1<resultEntryVO.getCrNoReqNoStringReason().length;i1++)
						{
							String crNo="";
							String ReqNo="";
							String[] values=resultEntryVO.getCrNoReqNoStringReason()[i1].split("#");
							crNo=values[0];
							ReqNo=values[1];
						
							if(crNo.equals(voInvResultValidationResp.getPatCRNo()) && ReqNo.equals(voInvResultValidationResp.getRequisitionNo()))
							{
								System.out.println("in loop");
								reasoncode=(resultEntryVO.getReasonCode()[i1]);
							}
							
						}
						}
						
						objResultEntrtyDAO.insertAddendumLogDtl(voInvResultValidationResp,_userVO,reasoncode);
						/*if(voInvResultValidationResp.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						{
						objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidationResp,_userVO);
						}		*/
					}
					
					
					for(ResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
					{
						
						if(voInvResulValidationForParaMeterDtl.getFinalechovalue()!=null && 
								
								!voInvResulValidationForParaMeterDtl.getFinalechovalue().equals(""))
{
objResultEntrtyDAO.updateechodata(voInvResulValidationForParaMeterDtl,_userVO);

}
						
						if(_request.getAttribute("getuploadedfiledata")!=null)
						{
							Map<String,String> mpo=(Map<String,String>)_request.getAttribute("getuploadedfiledata");
							
							if(mpo.containsKey(voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo()+voInvResulValidationForParaMeterDtl.getParantParaCode()))
							{
								String finaldata=mpo.get(voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo()+voInvResulValidationForParaMeterDtl.getParantParaCode());
								System.out.println("==============================================="+finaldata);
								
								voInvResulValidationForParaMeterDtl.setFileuploaddata(finaldata.split("@@")[1].split(",")[1]);
								voInvResulValidationForParaMeterDtl.setFilename(finaldata.split("@@")[0]);
								
							}
							
						}
						
						
						
						
						String Ishyperlink="";
						Ishyperlink=voInvResulValidationForParaMeterDtl.getIsHyperLink();
						 if(Ishyperlink.equals("-"))
						 {
							 objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
							 
								
							 String resultEntryvalue = voInvResulValidationForParaMeterDtl.getResultEntryValue();
						      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
						      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

						      String value = voInvResulValidationForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
						      
						      String properParaValues=voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulValidationForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
						      getrangeeentry(	voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo(),voInvResulValidationForParaMeterDtl.getParantParaCode(),voInvResulValidationForParaMeterDtl.getTestCode(),voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulValidationForParaMeterDtl.getPatGender(),voInvResulValidationForParaMeterDtl.getPatAge(),value,_userVO,tx);
																							
								
								objResultEntrtyDAO.insertResultLogDtl(voInvResulValidationForParaMeterDtl,_userVO);	
								
								 String status="13";
									
									
									if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12762") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3590"))
									  objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,voInvResulValidationForParaMeterDtl.getResultEntryValue(),null,status);

									if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12765") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3598"))
										objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,null,voInvResulValidationForParaMeterDtl.getResultEntryValue(),status);
		 					 
									
						 }
						 else
						 {
							 
							 objResultEntrtyDAO.insertResultLogDtl(voInvResulValidationForParaMeterDtl,_userVO);
							 
							 objResultEntrtyDAO.updateHyperLinkDetails(voInvResulValidationForParaMeterDtl,_userVO);

							 Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

							 
							 

							   if(mpBilled==null)
							                      {
							     					 
							     					  mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);

							                      }
							                      else
							                      {
							                    	  Map<String,List<antibioticprocessVO>>	  mpBilledfungus= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
							                    	  
							                    	  if(mpBilledfungus!=null)
							                    	  mpBilled.putAll(mpBilledfungus);
							                    	  
							                      }
							   
							 if(mpBilled!=null)
			                    {
			            			List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

			            			boolean flag=false;
			          Iterator itr1=mpBilled.keySet().iterator();
			          while(itr1.hasNext())
				 		{
			        	  
				 			String organisgm1=(String)itr1.next();
			            
				 			 
				 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm1);
				 			
				 			int rowSpanSize=lstVOSample.size();
				 			
				 			 
				 			 
				 			for(int k=0;k<lstVOSample.size();k++)
				 			{
				 				antibioticprocessVO voo=lstVOSample.get(k);
				 				
				 				 if(voo.getRequisitionDNo().equals(voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo()) && voo.getTestParaCode().equals(voInvResulValidationForParaMeterDtl.getParantParaCode()))
								  {
								   objResultEntrtyDAO.insertHyperLinkDetailsNew(voInvResulValidationForParaMeterDtl,_userVO,voo);
								 flag=true;
								  }
				 				 
				 				  String antibioticCode="";
								  String organismCode="";
								  String selectval="";
								  String reqdno="";
								  String testparacode="";
								  
				 				
				 			}
				 			
				 			

				 			 if(flag==true)
							  {
				 				  if(!voInvResulValidationForParaMeterDtl.getResultEntryValue().equals("") && !voInvResulValidationForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
								  {
									  String[] tbl1=  voInvResulValidationForParaMeterDtl.getResultEntryValue().split("\\$\\$\\$");
									  
									  for(int i11=0;i11<tbl1.length;i11++)
									  {
										  
										  String tbl=  tbl1[i11];
											
											String[] values=tbl.split("\\$\\$");
											
											if(values[0].equals(voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo()) && values[1].equals(voInvResulValidationForParaMeterDtl.getParantParaCode()))
											{ 
												voInvResulValidationForParaMeterDtl.setResultEntryValue(values[2]);
												
												 objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);

												 
												 String resultEntryvalue = voInvResulValidationForParaMeterDtl.getResultEntryValue();
											      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
											      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

											      String value = voInvResulValidationForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
											      
											      String properParaValues=voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulValidationForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
											      getrangeeentry(	voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo(),voInvResulValidationForParaMeterDtl.getParantParaCode(),voInvResulValidationForParaMeterDtl.getTestCode(),voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulValidationForParaMeterDtl.getPatGender(),voInvResulValidationForParaMeterDtl.getPatAge(),value,_userVO,tx);
																									
													
												 String status="13";
													
													
													if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12762") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3590"))
													  objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,voInvResulValidationForParaMeterDtl.getResultEntryValue(),null,status);

													if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12765") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3598"))
														objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,null,voInvResulValidationForParaMeterDtl.getResultEntryValue(),status);
												 
											}
									  }
								  }
				 				  
				 				 else
								  {
				 					 
				 					 
				 					 if( !voInvResulValidationForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
									  {
				 						voInvResulValidationForParaMeterDtl.setResultEntryValue("--");
										
										 objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);

										 
										 String resultEntryvalue = voInvResulValidationForParaMeterDtl.getResultEntryValue();
									      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
									      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

									      String value = voInvResulValidationForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
									      
									      String properParaValues=voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulValidationForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
									      getrangeeentry(	voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo(),voInvResulValidationForParaMeterDtl.getParantParaCode(),voInvResulValidationForParaMeterDtl.getTestCode(),voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulValidationForParaMeterDtl.getPatGender(),voInvResulValidationForParaMeterDtl.getPatAge(),value,_userVO,tx);
										
											
										 String status="13";
											
											
											if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12762") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3590"))
											  objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,voInvResulValidationForParaMeterDtl.getResultEntryValue(),null,status);

											if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12765") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3598"))
												objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,null,voInvResulValidationForParaMeterDtl.getResultEntryValue(),status);
				 					 
				 					 
								  }
				 				  
										  
									  }
							  }
				 		}
			                    }
							 
							 else
							 {
								 
								 String antibioticCode="";
								  String organismCode="";
								  String selectval="";
								  
								  
									 objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);

									 
									 String resultEntryvalue = voInvResulValidationForParaMeterDtl.getResultEntryValue();
								      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
								      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");

								      String value = voInvResulValidationForParaMeterDtl.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
								      
								      String properParaValues=voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9) +"#@"+"-"+"#@"+voInvResulValidationForParaMeterDtl.getStrRefRange()+"#@"+value+"#@"+"`";
								      getrangeeentry(	voInvResulValidationForParaMeterDtl.getParameterRequisitionDNo(),voInvResulValidationForParaMeterDtl.getParantParaCode(),voInvResulValidationForParaMeterDtl.getTestCode(),voInvResulValidationForParaMeterDtl.getParantParaCode().substring(5, 9),voInvResulValidationForParaMeterDtl.getPatGender(),voInvResulValidationForParaMeterDtl.getPatAge(),value,_userVO,tx);
									
										
									 String status="13";
										
										
										if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12762") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3590"))
										  objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,voInvResulValidationForParaMeterDtl.getResultEntryValue(),null,status);

										if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12765") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3598"))
											objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,null,voInvResulValidationForParaMeterDtl.getResultEntryValue(),status);
										
										
										
							 }
							 
							 
						 }
						
						
						
						//listResultValidationDtl.add("Test Name= "+voInvResultValidationResp.getTestName()+"Value=" +voInvResultValidationResp.getResultValidationValue());
					}	 
					//Put List in Map
					mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultValidationDtl);
					}

				}
				catch (HisRecordNotFoundException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				}
				catch (HisApplicationExecutionException e)
				{
					e.printStackTrace();
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				}
				catch (Exception e)
				{
					e.printStackTrace();
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
			

			
			//modify canned save 
			
			public String  checkcannedCodeName(InvResultValidationRespFB fb, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				String value="";
				try
				{
					tx.begin();
					InvResultEntryRespDAO onlinePatientDao = new InvResultEntryRespDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                    
					
					String checkcannedCode=onlinePatientDao.checkcannedCode(fb,_UserVO);
					String checkcannedName=onlinePatientDao.checkcannedName(fb,_UserVO);
					/*lstCannedDetails=onlinePatientDao.autoCannedDetails(Code,Name,_UserVO);*/
					
					if (checkcannedCode.equals("0") && checkcannedName.equals("0"))
					{

						onlinePatientDao.insertModifyCanned(fb,_UserVO);
						String code=onlinePatientDao.fetchCode(fb,_UserVO);
						onlinePatientDao.insertUserEnterCode(fb,_UserVO,code);
						
						value="1";
						
					}
					else
					{
						value="-3";
					}
					if(!checkcannedCode.equals("0"))
					{
						value="-1";
					}
					if(!checkcannedName.equals("0"))
					{
						value="-2";
					}
					
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
				return value;
			}
			
			
			//modify canned save 
			public String  checkcannedCodeName(InvResultEntryFB fb, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				String value="";
				try
				{
					tx.begin();
					InvResultEntryDAO onlinePatientDao = new InvResultEntryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                    
					
					String checkcannedCode=onlinePatientDao.checkcannedCode(fb,_UserVO);
					String checkcannedName=onlinePatientDao.checkcannedName(fb,_UserVO);
					/*lstCannedDetails=onlinePatientDao.autoCannedDetails(Code,Name,_UserVO);*/
					
					if (checkcannedCode.equals("0") && checkcannedName.equals("0"))
					{

						onlinePatientDao.insertModifyCanned(fb,_UserVO);
						String code=onlinePatientDao.fetchCode(fb,_UserVO);
						onlinePatientDao.insertUserEnterCode(fb,_UserVO,code);
						value="1";
						
					}
					else
					{
						value="-3";
					}
					if(!checkcannedCode.equals("0"))
					{
						value="-1";
					}
					if(!checkcannedName.equals("0"))
					{
						value="-2";
					}
					
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
				return value;
			}
			
			//modify canned save 
			
			public String  checkcannedCodeName1(InvResultValidationRespFB fb, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				String value="";
				try
				{
					tx.begin();
					InvResultEntryRespDAO onlinePatientDao = new InvResultEntryRespDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                    
					
					String checkcannedCode=onlinePatientDao.checkcannedCode(fb,_UserVO);
					String checkcannedName=onlinePatientDao.checkcannedName(fb,_UserVO);
					/*lstCannedDetails=onlinePatientDao.autoCannedDetails(Code,Name,_UserVO);*/
					
					if (checkcannedCode.equals("0") && checkcannedName.equals("0"))
					{

						onlinePatientDao.insertModifyCanned(fb,_UserVO);
						String code=onlinePatientDao.fetchCode(fb,_UserVO);
						onlinePatientDao.insertUserEnterCode(fb,_UserVO,code);
						value="1";
						
					}
					else
					{
						value="-3";
					}
					if(!checkcannedCode.equals("0"))
					{
						value="-1";
					}
					if(!checkcannedName.equals("0"))
					{
						value="-2";
					}
					
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
				return value;
			}
			


			public void getrangeeentry(	String reqdno,String parantparaCode,String testCode,String paraCode,String gender,String age,String val,UserVO _UserVO,JDBCTransactionContext tx)
			{
				
				 List<InvCriteriaCodeVO > objCriteriaCodeList = InvestigationTemplateDataHelper.getInstance().getCriteriaCode();
				 List<TestMandRefMasterVO > objRefRangeList = InvestigationTemplateDataHelper.getInstance().getReferanceRange(_UserVO.getHospitalCode());
				 TemplateProcessingUSE tpu = new TemplateProcessingUSE();

			try
			{

				
						
						
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
						
						getfinalrange(properParaValues,reqdno,testCode,parantparaCode,_UserVO.getHospitalCode(),tx);
						
					}

			catch(Exception e)
			{
				
			}
			finally {

//				tx.close();
			}
				
			}
			

			public static void getfinalrange(String ran,String reqdno,String testcode,String parantparacode,String hoscode,JDBCTransactionContext tx)
			{

				
				String range=ran;
			    String[] parameters=range.split("`");
			    int paraSize=parameters.length;
			    
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
			            
			            

			                          try
			                          {
			                               
			            				InvResultValidationDAO invresultentrydao = new InvResultValidationDAO(tx);

			            				invresultentrydao.updaterefrange( reqdno, testcode, parantparacode, displayRef,isoutofbound,hoscode);
			                          }
			                          catch(Exception e)
			                          {
			                        	  
			                          }
			                          finally {

			            					
			            				}
			            				
			            
			                    }
			    
			    
			    
			      //                      dnoMap.put(voPat.getRequisitionDNo(),mapList);
			                    
			   
			                                   
			                                    
			                            


			}




















			
}

