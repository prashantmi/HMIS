package new_investigation.transactions.bo;

 
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.InvestigationListingConfig;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.transactions.dao.InvDuplicateResultReportPrintingDAO;
import new_investigation.transactions.dao.InvResultEntryDAO;
import new_investigation.transactions.dao.InvResultReValidationDAO;
import new_investigation.transactions.dao.InvResultReportPrintingDAO;
import new_investigation.transactions.dao.InvResultValidationDAO;
import new_investigation.transactions.dao.InvValueAuditDAO;
import new_investigation.transactions.dao.InvestigationBillingDAOxray;
import new_investigation.transactions.dao.InvestigationEssentialDAOxray;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.icu.util.Calendar;

/**
 * @author vinita1fajax
 * 
 */
public class InvestigationEssentialBOxray implements InvestigationEssentialBOixray {
	
	

	/* Function to get the Patient Registration Details */
	public Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(
			Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO) {

		Inv_RequisitionRaisingPatientVO voPatient=new Inv_RequisitionRaisingPatientVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			voPatient=invEssentialDAO.getInvRaisingPatDetails(patVO, _UserVO);



		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			patVO = null;
		} finally {

			tx.close();
		}

		return voPatient;
	}

	/* Function to search Laboatory wise Test Details */

	public Map searchLabWiseTestDtls(InvestigationSearchVO searchVO, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		List<LabTestVO> lstLabTestSample=null;
		List lstUserGroupCode=null;
		Map<String, String> testAvailabilityDetail = null;
		List lsttestteriff=new ArrayList();
		String labNames="";
		String testNames="";
		String testCode="";
		String testCodenew="";
		String testCodenewtestwisesearch="";

		String groupCodes="";
		
		
		//List<LabTestVO> lstPreviousLabTest=null;
		
		Map mp=new HashMap();
		StringBuilder labBuild = new StringBuilder();
		StringBuilder testBuild = new StringBuilder();
		StringBuilder testCodeBuild = new StringBuilder();
		StringBuilder testCodeBuildnew = new StringBuilder();
		StringBuilder testCodeBuildnewlabwise = new StringBuilder();

		
		StringBuilder userGroupCodeBuild = new StringBuilder();
		List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
		List<String> testGroupCode=new ArrayList<String>();
		//Map<String, String> testAvailabilityDetail = new HashMap<String, String>();
		
		
		try {

			tx.begin();
			//added by chandan 
           String testCodes="";
			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			if(!((searchVO.getTestSearchKeyword()).equals("")))
					{
				testCodes=invEssentialDAO.getTestCodesKeyword(searchVO, _UserVO);
				  searchVO.setTestCodesSearchKeyword(testCodes);

					}


                  Inv_RequisitionRaisingPatientVO patVO=null;
                  Inv_PatientAdmissionDtlVO patVOadd=null;
                  Inv_RequisitionRaisingPatientVO voo=new Inv_RequisitionRaisingPatientVO();
                 if(searchVO.getLabwisetestteriff()!=null && searchVO.getLabwisetestteriff().equals("1"))
                  {voo.setPatCRNo(searchVO.getPatientCrNo());
                  patVO=invEssentialDAO.getInvRaisingPatDetails(voo,_UserVO);

                  if(patVO.getPatStatus()!=null && patVO.getPatStatus().equalsIgnoreCase("ipd"))
                  {
                	  List<Inv_PatientAdmissionDtlVO> ss=invEssentialDAO.getPatientAdmission(voo,_UserVO);
                	  patVOadd=ss.get(0);
                	  
                  }
                  
					lstLabTest=invEssentialDAO.searchLabWiseTestDtlstariff(searchVO, _UserVO,patVO,patVOadd);
                  }
                 else
                 {
                	 lstLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
                	 
                 }
                 lstLabTestSample=invEssentialDAO.searchLabTestSample(searchVO, _UserVO);
			lstUserGroupCode=invEssentialDAO.fetchUserGroupCode(searchVO, _UserVO);
			testAvailabilityDetail=invEssentialDAO.getTestAvailabilityDetail(searchVO, _UserVO);
			              lsttestteriff=invEssentialDAO.getlabteriff( _UserVO,searchVO);
			labBuild.append("");
			//String sampleComboStr="<option value='-1'>Select Value</option>";

			
			
			//*********************************logic to create various combo***********************************//
			if(lstLabTest!=null)
			for(LabTestVO vo:lstLabTest)
			{
				
				
				//******creating lab name combo
				if(labBuild.indexOf(vo.getLabCode())==-1)
				{
				labBuild.append("{ label: \""+vo.getLabName()+"\" ,  value: \""+vo.getLabCode()+"\" }");
				labBuild.append(",");
				}
				//******creating test name combo
				
				
				testBuild.append("{ label: \""+vo.getTestName()+"("+vo.getLabName()+")"+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"\" }");
				testBuild.append(",");
				
				//******creating test code combo
				if(vo.getUserTestCode()!=null)
				{
					vo.setTestName(vo.getTestName()+" ("+vo.getUserTestCode()+")");
				testCodeBuild.append("{ label: \""+vo.getUserTestCode()+"\" ,  value: \""+vo.getUserTestCode()+"\" }");
				testCodeBuild.append(",");
				
				/*if(searchVO.getSearchLabName()!=null && searchVO.getSearchLabName().trim().equalsIgnoreCase(vo.getLabCode().trim()))
				{*/
				testCodeBuildnew.append("{ label: \""+vo.getTestName()+"("+vo.getLabName()+")"+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"\" }");
				testCodeBuildnew.append(",");
				/*}*/
				
				testCodeBuildnewlabwise.append("{ label: \""+vo.getTestName()+"("+vo.getLabName()+")"+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"@testwise"+"\" }");
				testCodeBuildnewlabwise.append(",");
				
				
				}
				
				String strSAmpleCode = vo.getDefaultSampleCode();	//default sample code value
				String sampleComboStr="<option value='-1'>Select Value</option>";
				
				//filter out specific samples from all lab test samples
				int noOfSamples=0;
				String singleSampleName="";
				String singleSampleCode="";
				String reqSampleShortName="";
				for(LabTestVO sample_vo:lstLabTestSample)
				{					
					if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
					{
						noOfSamples++;
						singleSampleName=sample_vo.getsName();
						reqSampleShortName=sample_vo.getReqSampleShortName();
						singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
						
						if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
						{
						String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
							sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setReqdSampleName(sample_vo.getsName());
						vo.setReqSampleShortName(reqSampleShortName);
						System.out.println("this is the sample name "+vo.getReqdSampleName());
						System.out.println("this is the sample short name "+vo.getReqSampleShortName());
						
						}
						else
						{
							String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
							sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"'>"+sample_vo.getsName()+"</option>";
						}
						}
													
				}
				if(noOfSamples==1)//single sample
				{
					
					sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
					vo.setSingleSample(singleSampleCode);
					vo.setReqdSampleName(singleSampleName);
					vo.setReqSampleShortName(reqSampleShortName);
					System.out.println("this is the sample name "+vo.getReqdSampleName());
					System.out.println("this is the sample short name "+vo.getReqSampleShortName());

				}
				
				//getting (samplecode#samplename,sam.....) string 
				/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
				
				if(sampleString.equals("")==false)
				{
					String splitValues[] = sampleString.split(",");
					
					for(String sampleCodeName: splitValues)
					{
						if(splitValues.length==1)
						{
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
							vo.setSingleSample(sampleCodeName.split("#")[0]);
						}
						else
						{
						if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
						}
						
						
					}
					
					
					
				}
				*/
				//Sample Combo Logic
				if(vo.getTestType()!=null)
				{
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);
				}
				else
					vo.setSampleComboStr(sampleComboStr);
				
				//**********************calling query to get samples for each lab test by hitting database for each pair**********//
				
	/*			List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						
						if(lstSampleCombo.size()==1)
							
						{
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
							vo.setSingleSample(entry.getValue());
						}
						else
						{
						if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
						}
						}
				}
*/
				
				
				
				
				
				//Mandatory Combo/text Logic
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
					
				{
					String[] mandInfoCommaSeparator=mandInfo.split(",");
					
					int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
					String textBoxAndCombo="";
					String  textBoxComboNames="";
					
					String  textBoxComboCode="";
					
					for(int i=0;i<mandInfoCommaSeparatorlength;i++)
					{
						String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
						
						 
						
						 
							String mandCode=maninfoHashSeparator[0];
							
							String mandName=maninfoHashSeparator[1];
							
							String mandType=maninfoHashSeparator[2];
						
							
							if(mandType.equals("1"))
							{
							
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
									
									textBoxComboNames=mandName;
									textBoxComboCode=mandCode;
								}
								else
								{
									textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
								    
									textBoxComboNames=textBoxComboNames+"&"+mandName;
									
									textBoxComboCode=textBoxComboCode+"&"+mandCode;
								    
								}
								
							}
							if(mandType.equals("2"))
							{
								
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
									
									textBoxComboNames=mandName;
									
									textBoxComboCode=mandCode;
								}
								else
								{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								String mandCombo=vo.getMandCombo();
								
								String[] mandComboCommaSeparator=mandCombo.split(",");
								
								int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
								
								for(int k=0;k<mandComboCommaSeparatorlength;k++)
								{
									String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
										
									String mandComboCode=manComboHashSeparator[0];
									String mandComboName=manComboHashSeparator[1];
									
									if(mandCode.equals(mandComboCode))
									{
										textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
									}
									
								}
								textBoxAndCombo=textBoxAndCombo+"</select>";
								
							}
							
							
						 
								vo.setSetMandTextBoxCombo(textBoxAndCombo);
								vo.setMandComboTextBoxComboNames(textBoxComboNames);
								
								vo.setMandCode(textBoxComboCode);

					 
					}
				}
				       
				
				//changed by ashu 
				
				/*Map<String, LabTestVO> userCodeLabTestMap = new HashMap<String, LabTestVO>();
				if(lstLabTest != null){
					//System.out.println(lstLabTest.size());
					String key;
					for(int i=0;i < lstLabTest.size(); i++){
						key = lstLabTest.get(i).getUserTestCode();
						userCodeLabTestMap.put(key, lstLabTest.get(i));
						//System.out.println(" : "+lstLabTest.get(i).getUserTestCode()+" : "+lstLabTest.get(i));
					}
				}
				 
				mp.put(InvestigationConfig.MAP_USER_CODE_WISE_TEST_DTLS,userCodeLabTestMap);*/
 
				//lstPreviousLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
				if(searchVO.getSearchTestGroupName()!=null&&searchVO.getSearchTestGroupName()!="")
				{
					vo.setSearchTestGroup(searchVO.getSearchTestGroup());
					
					vo.setTestGroupCode(searchVO.getSearchTestGroupName());
					mp.put(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS,lstLabTest);
					
					
					if(!testGroupCode.contains(vo.getTestGroupCode()+vo.getLabCode()))
					{
						testGroupCode.add(vo.getTestGroupCode()+vo.getLabCode());
						lstSingleTestGroupDetail.add(vo);
						
						
						mp.put(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS,lstSingleTestGroupDetail);
						
					}
					
					
				}
				else
{
					mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
}
			//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstPreviousLabTest);
          
			}
			
			
			//create user group code json
			if(lstUserGroupCode!=null)
			{
				for(Entry obj:(List<Entry>)lstUserGroupCode)
				{
				
					userGroupCodeBuild.append("{ label: \""+obj.getLabel()+"\" ,  value: \""+obj.getValue()+"\" }");
					userGroupCodeBuild.append(",");
						
					testCodeBuildnewlabwise.append("{ label: \""+obj.getLabel()+"\" ,  value: \""+obj.getValue()+"@groupwise"+"\" }");
					testCodeBuildnewlabwise.append(",");
				
					
					
				}
			}
			
			labBuild.deleteCharAt(labBuild.length()-1);
			labNames="["+labBuild.toString()+"]";
			
			testBuild.deleteCharAt(testBuild.length()-1);
			testNames="["+testBuild.toString()+"]";
			
			testCodeBuild.deleteCharAt(testCodeBuild.length()-1);
			testCode="["+testCodeBuild.toString()+"]";
			
			if(testCodeBuildnew!=null && !testCodeBuildnew.toString().equals(""))
			{
			testCodeBuildnew.deleteCharAt(testCodeBuildnew.length()-1);
			testCodenew="["+testCodeBuildnew.toString()+"]";
			
			testCodeBuildnewlabwise.deleteCharAt(testCodeBuildnewlabwise.length()-1);
			testCodenewtestwisesearch="["+testCodeBuildnewlabwise.toString()+"]";
			
			}
			
			
			userGroupCodeBuild.deleteCharAt(userGroupCodeBuild.length()-1);
			groupCodes="["+userGroupCodeBuild.toString()+"]";
			
			
			if(searchVO.getLabEmpty().equals("1"))
			mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);
			if(searchVO.getTestEmpty().equals("1"))
			mp.put(InvestigationConfig.ARRAY_TESTNAMES, testNames);
			if(searchVO.getTestCodeEmpty().equals("1"))
			mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCode);
			
			mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_LABWISE, testCodenew);
			mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_LABWISE_TESTWISESEARCH, testCodenewtestwisesearch);

			
			if(searchVO.getGroupCodeEmpty().equals("1"))
				mp.put(InvestigationConfig.ARRAY_GROUP_CODE_WISE, groupCodes);
			
			
			Map<String, LabTestVO> userCodeLabTestMap = new HashMap<String, LabTestVO>();
			if(lstLabTest != null){
				//System.out.println(lstLabTest.size());
				String key;
				for(int i=0;i < lstLabTest.size(); i++){
					key = lstLabTest.get(i).getUserTestCode();
					if(key != null){
						key = key.toUpperCase();
						userCodeLabTestMap.put(key, lstLabTest.get(i));
					}
					
					//System.out.println(" : "+lstLabTest.get(i).getUserTestCode()+" : "+lstLabTest.get(i));
				}
			}
			 
			mp.put(InvestigationConfig.MAP_USER_CODE_WISE_TEST_DTLS,userCodeLabTestMap);
			
			mp.put(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS,testAvailabilityDetail);
			
			mp.put(InvestigationConfig.MAP_LAB_CODE_TERIIFF_CHANGE,lsttestteriff);
			
			
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

	
	
	
	
	/* Function to search Laboatory wise Test Details */
	public Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		List<LabTestVO> lstLabTestSample=null;

		List<LabTestVO> lstLabTestForTestGruop=new ArrayList<LabTestVO>();
		//List<LabTestVO> lstPreviousLabTest=null;
		
		Map mp=new HashMap();
		
		List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
		List<String> testGroupCode=new ArrayList<String>();
		
		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstLabTest=invEssentialDAO.searchLaboratoryWiseTestGroupOnClick(searchVO, _UserVO);
			lstLabTestSample=invEssentialDAO.searchLabTestSample(searchVO, _UserVO);

			//String sampleComboStr="<option value='-1'>Select Value</option>";

			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				String strSAmpleCode = vo.getDefaultSampleCode();
				String sampleComboStr="<option value='-1'>Select Value</option>";
				
				
				//filter out specific samples from all lab test samples
				int noOfSamples=0;
				String singleSampleName="";
				String singleSampleCode="";
				
		     	String	reqSampleShortName="";
				for(LabTestVO sample_vo:lstLabTestSample)
				{					
					if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
					{
						noOfSamples++;
					String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
						singleSampleName=sample_vo.getsName();
						reqSampleShortName=sample_vo.getReqSampleShortName();
						singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
						if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
						{	sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setReqdSampleName(sample_vo.getsName());
						vo.setReqSampleShortName(reqSampleShortName);
						}
						else
							sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode+"'>"+sample_vo.getsName()+"</option>";
					}
													
				}
				if(noOfSamples==1)//single sample
				{
					
					sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
					vo.setSingleSample(singleSampleCode);
					vo.setReqdSampleName(singleSampleName);
					vo.setReqSampleShortName(reqSampleShortName);
				}
				
				
				
				
				//getting (samplecode#samplename,sam.....) string 
				/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
				
				if(sampleString.equals("")==false)
				{
					String splitValues[] = sampleString.split(",");
					
					for(String sampleCodeName: splitValues)
					{
						if(splitValues.length==1)
						{
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
							vo.setSingleSample(sampleCodeName.split("#")[0]);
						}
						else
						{
						if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
						}
						
						
					}
					
					
					
				}
				*/
				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);
				
				
				
				
				/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						
						if(lstSampleCombo.size()==1)
						{
							
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
							vo.setSingleSample(entry.getValue());
						}
						else
						{
						if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
						}
						}
				}
*/
				

				//Mandatory Combo/text Logic
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO)&&mandInfo!=null)
					
				{
					String[] mandInfoCommaSeparator=mandInfo.split(",");
					
					int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
					String textBoxAndCombo="";
					String  textBoxComboNames="";
					
					String  textBoxComboCode="";
					
					for(int i=0;i<mandInfoCommaSeparatorlength;i++)
					{
						String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
						
						 
						
						 
							String mandCode=maninfoHashSeparator[0];
							
							String mandName=maninfoHashSeparator[1];
							
							String mandType=maninfoHashSeparator[2];
						
							
							if(mandType.equals("1"))
							{
							
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
									
									textBoxComboNames=mandName;
									textBoxComboCode=mandCode;
								}
								else
								{
									textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
								    
									textBoxComboNames=textBoxComboNames+"&"+mandName;
									
									textBoxComboCode=textBoxComboCode+"&"+mandCode;
								    
								}
								
							}
							if(mandType.equals("2"))
							{
								
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
									
									textBoxComboNames=mandName;
									
									textBoxComboCode=mandCode;
								}
								else
								{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								String mandCombo=vo.getMandCombo();
								
								String[] mandComboCommaSeparator=mandCombo.split(",");
								
								int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
								
								for(int k=0;k<mandComboCommaSeparatorlength;k++)
								{
									String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
										
									String mandComboCode=manComboHashSeparator[0];
									String mandComboName=manComboHashSeparator[1];
									
									if(mandCode.equals(mandComboCode))
									{
										textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
									}
									
								}
								textBoxAndCombo=textBoxAndCombo+"</select>";
								
							}
							
							
						 
								vo.setSetMandTextBoxCombo(textBoxAndCombo);
								vo.setMandComboTextBoxComboNames(textBoxComboNames);
								
								vo.setMandCode(textBoxComboCode);

					 
					}
				}
				       
				
				
				
				 
 
				//lstPreviousLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
				
				if(vo.getSearchTestGroup()!=null)
				{
					lstLabTestForTestGruop.add(vo);
					mp.put(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS,lstLabTestForTestGruop);
					
					if(!testGroupCode.contains(vo.getTestGroupCode()+vo.getLabCode()))
					{
						testGroupCode.add(vo.getTestGroupCode()+vo.getLabCode());
						lstSingleTestGroupDetail.add(vo);
						
						
						mp.put(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS,lstSingleTestGroupDetail);
						
					}
				}
 
			//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstPreviousLabTest);
          
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

		return mp;
	}




	public  synchronized List saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO,String priorityAll,HttpSession session)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listReqId=new ArrayList();
		String labName="";
		String testName="";
		String billno="";
		String reqnoforpid="";
		 String datapidd="";
		 String datapidddata="";

		try
		{    
			
			
		
			tx.begin();
			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			
			InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);
			
			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
			
		 
			
	 
			 
			//First iterate the Map over Lab codes for generating requisition no's
			
			//String[] labCodeArray=(String[])mp.keySet().toArray();
			
			//int size=labCodeArray.length;
			
			int requisitionTypeForBilling=0;
			
			Iterator itrLab=mp.keySet().iterator();
			
			while(itrLab.hasNext())//for(int i=0;i<size;i++)
			{
				testName="";
				String requisitionNumber="";
				String crNO="";
				String patStatus="";
				
				String err="";
				
				String labCode=(String)itrLab.next();
				//Generate Requisition No Sequence  for each lab
			
				
				// Procedure to generate and insert/update req no
				
				//String sequence_Hash_yymmdd=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);  // Returns sequence#yymmdd
				
				//String sequence=sequence_Hash_yymmdd.split("#")[0];
				//String yymmdd=sequence_Hash_yymmdd.split("#")[1];
				
				//Logic to check the sequence is '10001' 
				//if(sequence.equals(InvestigationConfig.REQUISITION_NO_SEQUENCE_INVESTIGATION)) //10001
				//{
					// Insert in Requisition Maintainer Table
					//	invEssentialDAO.insertRequisitionSequenceInMaintainer(labCode,sequence,yymmdd,_userVO);
				//}
				//else
			/*	{
					invEssentialDAO.updateRequisitionSequenceInMaintainer(sequence, labCode, _userVO);
				}*/
				
				//Insertion in Requisition Header Detail Table
				
				
				//check if new requisitions or already raised one 
				
				  Map<String,LabTestVO> testInLab=mp.get(labCode);
				int totalTests=testInLab.size();
				int testsAlreadyRaised=0;
				
				Iterator itrTestInLab=testInLab.keySet().iterator();
		    
				
				String labCode1="";
				String reqdno1="";
				String reqno1="";
				
				while(itrTestInLab.hasNext())
				{
					String testCode=(String)itrTestInLab.next();
					
					LabTestVO voLabTest=testInLab.get(testCode);
					
					if(voLabTest.getAlreadyRaised().equals("1"))
						testsAlreadyRaised++;
				}
				
				
				
				if(testsAlreadyRaised<totalTests)
				
				{
				
				   
				   // Prepare Requisition Number Dynamically with given values in LLLLLYYMMDDXXXXX format
				 //  String requisitionNumber=_userVO.getHospitalCode()+labCode+yymmdd+sequence; //hospitalcodeLLLLLYYMMDDXXXXX format
				  if(session.getAttribute("IsAddendum")!=null)
				  {
					   reqno1=(String) session.getAttribute("reqNo");
					   reqdno1=(String) session.getAttribute("reqDno");
					   labCode1=(String) session.getAttribute("labCode");
					  
					  
				  }
					
				  if(session.getAttribute("IsAddendum")!=null)
				  {
					  String oldreqno=(String) session.getAttribute("reqNo");
					  if(labCode.equals(labCode1))
					  {
						 // crNO=patVO.getPatCRNo();
						  requisitionNumber=reqno1;
						  err="success";
						  crNO=patVO.getPatCRNo();
						  patStatus =patVO.getPatStatusCode();
						   //Setting Requisition HeaderVO Values
						   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
						   voReqHeader.setLabCode(labCode);
						   voReqHeader.setRequisitionNumber(requisitionNumber);
						   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
						   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
						   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
						   voReqHeader.setBedCode(patVO.getBedCode());
						   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
						   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
						   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
						   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
						   voReqHeader.setGenderCode(patVO.getPatGenderCode());
						   voReqHeader.setIsActualDob(patVO.getIsActualDob());
						   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
						   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
						   voReqHeader.setMlcNo(patVO.getPatmlcno());
						   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
						   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
						    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
						   voReqHeader.setPatDob(patVO.getPatDOB());
						   voReqHeader.setPatAge(patVO.getPatAge());
						   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
						   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
						
						   
						   if(patVO.getPatStatusCode().equals("2"))
							{						  
								requisitionTypeForBilling=2;
							}
							else 
							{
								//visit type code 1-opd, 2,3-emergency, 4 special
								if(patVO.getPatvisittypecode()==null)
									requisitionTypeForBilling=1;
								else{
									
								
								if(patVO.getPatvisittypecode().equals("1"))
									requisitionTypeForBilling=1;
								if(patVO.getPatvisittypecode().equals("4"))
									requisitionTypeForBilling=4;
								if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
									requisitionTypeForBilling=3;
								}
								
							}
							   
						   
						  // voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
						   voReqHeader.setRequsitionRaisedThrough("5");  //7

						   
						   
						   voReqHeader.setRoomCode(patVO.getPatroomno());
						   voReqHeader.setWardCode(patVO.getPatwardcode());
						   voReqHeader.setReqType(""+requisitionTypeForBilling);
						   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
						   voReqHeader.setWardName(patVO.getPatwardname());
						   voReqHeader.setBedName(patVO.getBedname());
						   
						   voReqHeader.setVisitNo(patVO.getPatVisitNo());
						  
						   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
						   
						   
						   
						   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
						    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
						    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
						    voReqHeader.setVisitReason(patVO.getVisitReason());
						    voReqHeader.setIsassociatedTest("1");
						    voReqHeader.setIsassociatedreqno(reqno1);
						 
						
						  
						  
						  
					  }
					  else
					  { 

						  invEssentialDAO.updateinheaderfordifflabaddendum( requisitionNumber, oldreqno,_userVO); 
					  
						  
						  crNO=patVO.getPatCRNo();
						  patStatus=patVO.getPatStatusCode();
						   //Setting Requisition HeaderVO Values
						   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
						   voReqHeader.setLabCode(labCode);
						   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
						   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
						   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
						   voReqHeader.setBedCode(patVO.getBedCode());
						   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
						   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
						   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
						   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
						   voReqHeader.setGenderCode(patVO.getPatGenderCode());
						   voReqHeader.setIsActualDob(patVO.getIsActualDob());
						   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
						   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
						   voReqHeader.setMlcNo(patVO.getPatmlcno());
						   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
						   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
						    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
						   voReqHeader.setPatDob(patVO.getPatDOB());
						   voReqHeader.setPatAge(patVO.getPatAge());
						   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
						   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
						
						   
						   if(patVO.getPatStatusCode().equals("2"))
							{						  
								requisitionTypeForBilling=2;
							}
							else 
							{
								//visit type code 1-opd, 2,3-emergency, 4 special
								if(patVO.getPatvisittypecode()==null)
									requisitionTypeForBilling=1;
								else{
									
								
								if(patVO.getPatvisittypecode().equals("1"))
									requisitionTypeForBilling=1;
								if(patVO.getPatvisittypecode().equals("4"))
									requisitionTypeForBilling=4;
								if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
									requisitionTypeForBilling=3;
								}
								
							}
							   
						   
						  // voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
						   voReqHeader.setRequsitionRaisedThrough("5");  //7
						   
						   
						   voReqHeader.setRoomCode(patVO.getPatroomno());
						   voReqHeader.setWardCode(patVO.getPatwardcode());
						   voReqHeader.setReqType(""+requisitionTypeForBilling);
						   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
						   voReqHeader.setWardName(patVO.getPatwardname());
						   voReqHeader.setBedName(patVO.getBedname());
						   
						   voReqHeader.setVisitNo(patVO.getPatVisitNo());
						  
						   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
						   
						   
						   
						   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
						    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
						    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
						    voReqHeader.setVisitReason(patVO.getVisitReason());
						    voReqHeader.setIsassociatedTest("1");
						    voReqHeader.setIsassociatedreqno(reqno1);
					

						
						   
						   for(int p=0;p<3;p++)
						    {
						    
						    requisitionNumber=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);	//procedure generating req no and inserting/updating it accordingly
						    voReqHeader.setRequisitionNumber(requisitionNumber);
						   
						     err= invEssentialDAO.insertRequisitionHeaderDtl_addendum(voReqHeader,_userVO);	//procedure to insert in req dtl

						   
						     System.out.println("insert in header tbl for req no "+requisitionNumber +"error:"+ err);
						     voReqHeader.setStatus(err);

						    if(!err.contains("duplicate key value violates"))
						      {break;}
						      
						    }
						   
					  }
					  
				  }
				  else
				  {
				 
				 crNO=patVO.getPatCRNo();
				patStatus= patVO.getPatStatusCode();
				   //Setting Requisition HeaderVO Values
				   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
				   voReqHeader.setLabCode(labCode);
				   
				   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
				   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
				   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
				   voReqHeader.setBedCode(patVO.getBedCode());
				   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
				   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
				   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
				   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
				   voReqHeader.setGenderCode(patVO.getPatGenderCode());
				   voReqHeader.setIsActualDob(patVO.getIsActualDob());
				   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
				   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
				   voReqHeader.setMlcNo(patVO.getPatmlcno());
				   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
				   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
				    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
				   voReqHeader.setPatDob(patVO.getPatDOB());
				   voReqHeader.setPatAge(patVO.getPatAge());
				   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
				   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
				
				   
				   if(patVO.getPatStatusCode().equals("2"))
					{						  
						requisitionTypeForBilling=2;
					}
					else 
					{
						//visit type code 1-opd, 2,3-emergency, 4 special
						if(patVO.getPatvisittypecode()==null)
							requisitionTypeForBilling=1;
						else{
							
						
						if(patVO.getPatvisittypecode().equals("1"))
							requisitionTypeForBilling=1;
						if(patVO.getPatvisittypecode().equals("4"))
							requisitionTypeForBilling=4;
						if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
							requisitionTypeForBilling=3;
						}
						
					}
					   
				   
				   voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
				   voReqHeader.setRequsitionRaisedThrough("5");  //7
				   
				   
				   voReqHeader.setRoomCode(patVO.getPatroomno());
				   voReqHeader.setWardCode(patVO.getPatwardcode());
				   voReqHeader.setReqType(""+requisitionTypeForBilling);
				   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
				   voReqHeader.setWardName(patVO.getPatwardname());
				   voReqHeader.setBedName(patVO.getBedname());
				   
				   voReqHeader.setVisitNo(patVO.getPatVisitNo());
				  
				   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
				   
				   
				   
				   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
				    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
				    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
				    voReqHeader.setVisitReason(patVO.getVisitReason());
				    
				    
				    for(int p=0;p<3;p++)
				    {
				    
				    requisitionNumber=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);	//procedure generating req no and inserting/updating it accordingly
				    voReqHeader.setRequisitionNumber(requisitionNumber);
				   
				     err= invEssentialDAO.insertRequisitionHeaderDtl(voReqHeader,_userVO);	//procedure to insert in req dtl
				   
				     System.out.println("insert in header tbl for req no "+requisitionNumber +"error:"+ err);
				     voReqHeader.setStatus(err);

				    if(!err.contains("duplicate key value violates"))
				      {break;}
				      
				    }
				   
				   
				  /* if(voReqHeader.getLabbasedaptdatetime()!=null && !voReqHeader.getLabbasedaptdatetime().equals(""))
				   invEssentialDAO.updateappointmentdateinheader(voReqHeader,_userVO);	//procedure to insert in req dtl
*/
				}
			}	  
				else
				{
					
					 crNO=patVO.getPatCRNo();
					patStatus= patVO.getPatStatusCode();
					 if(patVO.getPatStatusCode().equals("2"))
						{						  
							requisitionTypeForBilling=2;
						}
						else 
						{
							//visit type code 1-opd, 2,3-emergency, 4 special
							if(patVO.getPatvisittypecode().equals("1"))
								requisitionTypeForBilling=1;
							if(patVO.getPatvisittypecode().equals("4"))
								requisitionTypeForBilling=4;
							if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
								requisitionTypeForBilling=3;
						}
					 
				
					
				}
				
				//Insertion in HIVT_REQUISITION_DTL Table
				   // Getting Map of Test from Map of Lab
				   
				if(err.equalsIgnoreCase("success"))
				{
			     Map<String,LabTestVO> mpTest=mp.get(labCode);
				   
					//First iterate the Map over Test codes for generating requisitionDno's
					
					//String[] testCodeArray=(String[])mpTest.keySet().toArray();
					
					//int sizeTest=testCodeArray.length;
			     
			     	Iterator itrTest=mpTest.keySet().iterator();
			     	
					int testCounter=0;
					String runningSequence="00";
					
					while(itrTest.hasNext())//for(int j=0;j<sizeTest;j++)
					{
						
						String testCode=(String)itrTest.next();
						LabTestVO newLabTest=new LabTestVO();
						newLabTest=mpTest.get(testCode);
						InvestigationRequistionVO voReq=new InvestigationRequistionVO();
						
						voReq.setPiddata(newLabTest.getPiddata());
						voReq.setPidtestcontains(newLabTest.getPidtestcontains());
						voReq.setIsAppointment(newLabTest.getIsAppointment());

						voReq.setIslabappointmentbased(newLabTest.getIslabappointmentbased());
						
						if(newLabTest.getAlreadyRaised().equals("1"))
						{
							
							voReq.setStrRequsitionDNo(newLabTest.getRequisitionDNo());
							voReq.setStrReqNo(newLabTest.getRequisitionDNo().substring(0, newLabTest.getRequisitionDNo().length()-2));
							voReq.setStrTestGroupCode((newLabTest.getTestGroupCode().equals("0")?null:newLabTest.getTestGroupCode()));
							
							
							voReq.setStrLabCode(newLabTest.getLabCode());
							voReq.setStrTestCode(newLabTest.getTestCode());
							
							
							voReq.setStrTestGroupType((newLabTest.getTestGroupType().equals("0")?null:newLabTest.getTestGroupType()));
							voReq.setIslabappointmentbased((newLabTest.getIslabappointmentbased()));
							voReq.setIsAppointment(newLabTest.getIsAppointment());

							voReq.setStrAppointmentDate(newLabTest.getAppointmentDate());
							voReq.setStrAppointmentTime(newLabTest.getAppointmentTime());
							voReq.setAppointmentRefNo(newLabTest.getAppointmentRefNo());
							voReq.setLabbaseddatetimeappt(newLabTest.getLabbasedaptdatetime());
							voReq.setSite(newLabTest.getSite());
							
							voReq.setStrSampleCode(newLabTest.getSampleCode());
								invEssentialDAO.saveAppointmentDetail(voReq,_userVO);	
							
								   if(voReq.getLabbaseddatetimeappt()!=null && !voReq.getLabbaseddatetimeappt().equals(""))
									   invEssentialDAO.updateappointmentdateinheader(voReq,_userVO);	//procedure to insert in req dtl

								   
								//confirm Appoitment 
								if(!newLabTest.getAppoitmentNo().equals("0")&&newLabTest.getAppoitmentNo()!=null)
								{
								voReq.setAppointmentNo(newLabTest.getAppoitmentNo());
								invEssentialDAO.ConfirmAppointment(voReq, _userVO);
								}
							
							
								//Billing Logic
								//Billing Logic //modified to include the sample blood for extra charge
								/*if(newLabTest.getReqdSampleName().equalsIgnoreCase("blood"))
								{*/
								
								//comment for extra charge by ashu 
								
								//newraiseadvice=newLabTest.getRaiseAdvise();
								
								
								if(newLabTest.getReqSampleShortName().equalsIgnoreCase("bld"))
								{
									if(voBillingDtl.getRequisitionNos()==null)
									{
										voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+"|BLD!");
										voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
										voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
									}
									else
									{
									if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
									{
										voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+"|BLD!");
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
									}
									else
									{
										voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos().replace(voReq.getStrReqNo(), voReq.getStrReqNo()+"|BLD"));
										
										}
									}
									
								}
								else
								{								
								if(voBillingDtl.getRequisitionNos()==null)
								{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								else
								{
								if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
								{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								}
								}
								
							/*	*/
								
								if(voBillingDtl.getRequisitionNos()==null)
								{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								else
								{
								if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
								{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								}
								System.out.println("voBillingDtl1.setRequisitionNos::::::::  "+voBillingDtl.getRequisitionNos());
								
								if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
								{
									if(newLabTest.getTestGroupType()==null ||newLabTest.getTestGroupType().equals("")||newLabTest.getTestGroupCode().equals("0")||newLabTest.getTestGroupType().equals("3"))
									{
										if(voBillingDtl.getTariffDetails()==null)
										{
											voBillingDtl.setTariffDetails(new ArrayList<String>());
											voBillingDtl.setTariffQty(new ArrayList<String>());
										}
										
										voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
										voBillingDtl.getTariffQty().add("1");
									}
									else
									{
										if(voBillingDtl.getGrouptariffDetails()==null)
										{
											voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
											voBillingDtl.setGrouptariffQty(new ArrayList<String>());
										}
										
										voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
										voBillingDtl.getGrouptariffQty().add("1");
									}
								}
						}
						else
						{
							
							List<InvestigationRequistionVO> list_on_old_reqdno=new ArrayList<InvestigationRequistionVO>();
							InvestigationRequistionVO fetch_list_on_old_reqdno_vo= new InvestigationRequistionVO();
							
							String requisitionDNo="";
					            
							if(session.getAttribute("IsAddendum")!=null)
								
							{
								String labcode=(String) session.getAttribute("labCode");
								
								String reqno=(String) session.getAttribute("reqNo");
						        
								String reqdno=(String) session.getAttribute("reqDno");
								
								list_on_old_reqdno=invEssentialDAO.getDetailsOnBasisReqDno(reqdno,_userVO);
								
								  if(labCode.equals(newLabTest.getLabCode()))
								  {
									   requisitionDNo=invEssentialDAO.isaddendumreqdno(reqno, _userVO);  // max dno generate for same lab for addendum test
									   
									   
								  }
								  else
								  {
										testCounter++;
										//Logic for generating running sequence 'XX'
										if(testCounter/10<1)
											runningSequence="0"+Integer.toString(testCounter); //Like 01,02,03 etc..
										else
											runningSequence=Integer.toString(testCounter);
										
										//requisitionDNo generation:: format requisitionNumberXX
										
										 requisitionDNo=requisitionNumber+runningSequence;
									  
								  }
								  
								  
								  if(list_on_old_reqdno!=null)
								  {
									  for(int i=0;i<list_on_old_reqdno.size();i++)
									  {
									      
										  fetch_list_on_old_reqdno_vo=  list_on_old_reqdno.get(i);
									 	  
										  voReq.setTempSampleNo(fetch_list_on_old_reqdno_vo.getTempSampleNo());
										  voReq.setStrAcceptanceDateTime(fetch_list_on_old_reqdno_vo.getStrAcceptanceDateTime());
										  voReq.setStrAcceptanceSeatId(fetch_list_on_old_reqdno_vo.getStrAcceptanceSeatId());
										  voReq.setStrCollDateTime(fetch_list_on_old_reqdno_vo.getStrCollDateTime());
										  voReq.setStrCollectionSeatId(fetch_list_on_old_reqdno_vo.getStrCollectionSeatId());
										  voReq.setStrSampleCollectionAreaCode(fetch_list_on_old_reqdno_vo.getStrSampleCollectionAreaCode());
										  voReq.setIs_Sample_Received(fetch_list_on_old_reqdno_vo.getIs_Sample_Received());
										  voReq.setSampleNo(fetch_list_on_old_reqdno_vo.getSampleNo());
										  voReq.setUomCode(fetch_list_on_old_reqdno_vo.getUomCode());
										  voReq.setCollectedVolume(fetch_list_on_old_reqdno_vo.getCollectedVolume());
										  voReq.setContainerCode(fetch_list_on_old_reqdno_vo.getContainerCode());
										  voReq.setStrDailyLabNo(fetch_list_on_old_reqdno_vo.getStrDailyLabNo());
										  voReq.setStrIsAccepted(fetch_list_on_old_reqdno_vo.getStrIsAccepted());
										  
										  voReq.setChangeCount(fetch_list_on_old_reqdno_vo.getChangeCount());
										  voReq.setReportChange(fetch_list_on_old_reqdno_vo.getReportChange());
										  voReq.setReportChangeDate(fetch_list_on_old_reqdno_vo.getReportChangeDate());
										  voReq.setReportChangeSeatId(fetch_list_on_old_reqdno_vo.getReportChangeSeatId());
										  voReq.setReportUrl(fetch_list_on_old_reqdno_vo.getReportUrl());
										  voReq.setReportDate(fetch_list_on_old_reqdno_vo.getReportDate());
									  }
									  
								  }
								  
							}
							
							if(session.getAttribute("IsAddendum")==null)
							{
						testCounter++;
						//Logic for generating running sequence 'XX'
						if(testCounter/10<1)
							runningSequence="0"+Integer.toString(testCounter); //Like 01,02,03 etc..
						else
							runningSequence=Integer.toString(testCounter);
						
						//requisitionDNo generation:: format requisitionNumberXX
						
						 requisitionDNo=requisitionNumber+runningSequence;
							}
							
					   //Setting Requisition HeaderVO Values
					
						voReq.setStrLabCode(newLabTest.getLabCode());
						voReq.setStrTestCode(newLabTest.getTestCode());
						voReq.setStrSampleCode(newLabTest.getSampleCode());
						voReq.setStrRequsitionDNo(requisitionDNo);
						voReq.setStrReqNo(requisitionNumber);
						voReq.setStrWorkOrderSequence(runningSequence);
						voReq.setStrPriority((newLabTest.getPriority()==null?InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL:newLabTest.getPriority()));
						
						if(newLabTest.getTestType().equals("P"))
							voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PATIENT_BASED);
						else
							voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
						
						
						if(newLabTest.getRaiseAdvise().equals("0"))
							voReq.setStrRequisitionDtlStatus(InvestigationConfig.APPOINTMENT_ADVISED_DESK);
						
						
						voReq.setStrTypeOfComponent("1"); //need to configure
						
						voReq.setStrTestGroupCode((newLabTest.getTestGroupCode().equals("0")?null:newLabTest.getTestGroupCode()));
						
						voReq.setStrTestGroupType((newLabTest.getTestGroupType().equals("0")?null:newLabTest.getTestGroupType()));
					    
						voReq.setIslabappointmentbased((newLabTest.getIslabappointmentbased()));
						voReq.setIsAppointment(newLabTest.getIsAppointment());

						voReq.setStrAppointmentDate(newLabTest.getAppointmentDate());
						voReq.setStrAppointmentTime(newLabTest.getAppointmentTime());
						//voReq.setFinalMandValues(voLabTest.getFinalMandValues());
						 voReq.setAdvisedBYDoctorName(newLabTest.getAdvisedByDoctorName());
						// voReq.setStrIsAppointment(voLabTest.getIsAppointment());
						
						 voReq.setAppointmentRefNo(newLabTest.getAppointmentRefNo());
						 
						 voReq.setIsrequisitionFormNeeded(newLabTest.getIsRequisitionFormNeeded());
						 voReq.setSite(newLabTest.getSite());
						 
						 voReq.setRequisitionFormData(newLabTest.getRequisitionFormData());
						 if(newLabTest.getAdvice().equals("")) 
						 {
							 voReq.setAdvice(newLabTest.getAdvice());
							 
						 }
						 else
						 {
						 String[] advices=newLabTest.getAdvice().split("@");
						 
						 for(int i1=0;i1<advices.length;i1++)
						 {
							 String[] advice=advices[i1].split("#");
							 String testcode=advice[0];
							 String labcode=advice[1];
							 
							 if(testcode.equals(voReq.getStrTestCode()) && labcode.equals(voReq.getStrLabCode()))
							 {
								 if(!advice[2].equals(""))
								 voReq.setAdvice(advice[2]); 
								 else
									 voReq.setAdvice("-");
							 }
							 
						 }
						 }
						 
						 
						String apoitmentDAte=newLabTest.getAppointmentDate();
                    	String testlabcodeduplicatecheck="";
					
						// save requisition form data
						if(voReq.getIsrequisitionFormNeeded().equals("1"))
						{
							ResultEntryVO vo=new ResultEntryVO();
							
						if(!voReq.getRequisitionFormData().equals(""))
						{
							String value=voReq.getRequisitionFormData();
							
							String[] val=value.split("\\$");
							
							for(int i=val.length-1;i>=0;i--)
							{
								String data=val[i];
								
								String[] values=data.split("@");
								
								for(int i1=values.length-1;i1>=0;i1--)
								{
									
									String[] data1=values[i1].split("#");
									String testcode=data1[0].substring(0,5);
									
							       String labcode=data1[0].substring(5,10);
							       
							        
							       
									String testparameter=data1[3];
									if(!testlabcodeduplicatecheck.contains(testparameter))
									{
										
									
									
									String valuess=data1[4];
									String paracode=data1[3].substring(5, 9);
									
									if(testcode.equals(voReq.getStrTestCode()) && labcode.equals(voReq.getStrLabCode()))
									{
										testlabcodeduplicatecheck+="#"+testparameter;
									vo.setParameterRequisitionDNo(voReq.getStrRequsitionDNo());
									vo.setTestCode(testcode);
									vo.setParantParaCode(testparameter);
									vo.setTestParaMeterCode(paracode);
									vo.setLoincCode("");
								    vo.setRefRange("");
								    vo.setResultEntryValue(valuess);
								    vo.setLabCode(labcode);
								    invEssentialDAO.insertRequisitionFormDtl(vo,_userVO);

									}
									}
									
									}
								
							}
							
						}
						else
						{
								
						}
						
						}
						
						
					
					//	invEssentialDAO.insertRequisitionDtl(voReq,_userVO,priorityAll);	//calling procedure to add into req dtl table
						
						voReq.setLabbaseddatetimeappt(newLabTest.getLabbasedaptdatetime());
						
						   if(voReq.getLabbaseddatetimeappt()!=null && !voReq.getLabbaseddatetimeappt().equals(""))
							   invEssentialDAO.updateappointmentdateinheader(voReq,_userVO);	//procedure to insert in req dtl

						   
							if((voReq.getPiddata()!=null && !voReq.getPiddata().equals("")) && voReq.getPidtestcontains()!=null)
							{
							
								datapidd+=voReq.getStrReqNo()+"#"+voReq.getStrLabCode()+"#"+voReq.getStrTestCode()+"#"+"@@";	
								datapidddata=voReq.getPiddata();
							}
							
							
						
						
						
						
						
						if(session.getAttribute("IsAddendum")!=null)
						{
                             
							if(session.getAttribute("IsAddendumENTRY")!=null)
							{
							invEssentialDAO.insertRequisitionDtl_addendumNewTest(voReq,_userVO);	//update 
							}
							else
							{
								invEssentialDAO.insertRequisitionDtl_addendum(voReq,_userVO);	//update
							}
							}
						
						//invEssentialDAO.updateRequisitionHeader(requisitionNumber,apoitmentDAte,_userVO);	//calling procedure to update req/app date
						
						
						String[] SplitedValues=newLabTest.getFinalMandValues().split("@");
						
						String[] splitedManCode=newLabTest.getFinalMandCode().split("&");
						
		
						if(!newLabTest.getFinalMandCode().equals("undefined")&&!newLabTest.getFinalMandCode().equals("null")&&!newLabTest.getFinalMandCode().equals(""))
						{
						for(int i=0;i<SplitedValues.length;i++)
						{
						voReq.setFinalMandValues(SplitedValues[i]);
						 
						voReq.setMandCode(splitedManCode[i]);
						
							invEssentialDAO.insertHivtRequsitionTestMandatoryDtl(voReq,_userVO);	//procedure to insert into req_test_mand_dtl
						}
						
						}
						
						
						//confirm Appoitment
						if(newLabTest != null){
							if(newLabTest.getAppoitmentNo()!=null && !newLabTest.getAppoitmentNo().equals("0"))
							{
								voReq.setAppointmentNo(newLabTest.getAppoitmentNo());
								invEssentialDAO.ConfirmAppointment(voReq, _userVO);
							}
						}
						
						
						// insert piddd
						
						
						//patStatus= patVO.getPatStatusCode();
						   //Setting Requisition HeaderVO Values
						
						//Billing Logic
						//Billing Logic //modified to include the sample blood for extra charge
						/*if(newLabTest.getReqdSampleName().equalsIgnoreCase("blood"))
						{	*/
						
						//comment for extra charge by ashu
						
						if(newLabTest.getReqSampleShortName().equalsIgnoreCase("bld"))
						{
						if(voBillingDtl.getRequisitionNos()==null)
						{
							voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
							voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+"|BLD!");
							voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
							voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
							System.out.println("voBillingDtl2.setRequisitionNos1::::::::1  with bld"+voBillingDtl.getRequisitionNos());
						}
						else
						{
						if(!voBillingDtl.getRequisitionNos().contains(requisitionNumber))
						{
							voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
							System.out.println("voBillingDtl2.setRequisitionNos6::::::::6 widout bld "+voBillingDtl.getRequisitionNos());
							System.out.println("requisitionNumber::::: "+requisitionNumber);
						voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+"|BLD!");
						voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
						voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
						
						System.out.println("voBillingDtl2.setRequisitionNos2::::::::2  "+voBillingDtl.getRequisitionNos());
						}
						else{
							voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
							voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos().replace(requisitionNumber, requisitionNumber+"|BLD"));
							System.out.println("voBillingDtl2.setRequisitionNos3::::::::3  "+voBillingDtl.getRequisitionNos());
							}
						}
						}
						else
						{
							System.out.println("voBillingDtl.getRequisitionNos() "+voBillingDtl.getRequisitionNos());
							if(voBillingDtl.getRequisitionNos()==null || voBillingDtl.getRequisitionNos().equalsIgnoreCase(""))
							{
								voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								System.out.println("voBillingDtl2.setRequisitionNos4::::::::4  "+voBillingDtl.getRequisitionNos());
							}
							else
							{
								//chanksssssss
							if(!(voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!") || voBillingDtl.getRequisitionNos().contains("BLD!")))
							{
								voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
								
								/*if(!(voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!")) || (voBillingDtl.getRequisitionNos().contains("BLD!")))
								{*/	
							voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
							voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
							voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
							System.out.println("voBillingDtl2.setRequisitionNos5::::::::5  "+voBillingDtl.getRequisitionNos());
							}
							else if(!(voBillingDtl.getRequisitionNos().contains(requisitionNumber)))
							{
								voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								System.out.println("voBillingDtl2.setRequisitionNos else::::::::5  "+voBillingDtl.getRequisitionNos());
							}
							}	
							
							
						}
						
						System.out.println("voBillingDtl2.setRequisitionNos::::::::  "+voBillingDtl.getRequisitionNos());
				/*	*/ // changeforbld ashu
						
						/*if(voBillingDtl.getRequisitionNos()==null)
						{
							voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
							voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
							voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
						}
						else
						{
						if(!voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!"))
						{
						voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
						voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
						voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
						}
						}*/
							if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
							{
								if(newLabTest.getTestGroupType()==null ||newLabTest.getTestGroupType().equals("")||newLabTest.getTestGroupCode().equals("0")||newLabTest.getTestGroupType().equals("3"))
								{
									if(voBillingDtl.getTariffDetails()==null)
									{
										voBillingDtl.setTariffDetails(new ArrayList<String>());
										voBillingDtl.setTariffQty(new ArrayList<String>());
									}
									
									voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
									voBillingDtl.getTariffQty().add("1");
								}
								else
								{
									if(voBillingDtl.getGrouptariffDetails()==null)
									{
										voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
										voBillingDtl.setGrouptariffQty(new ArrayList<String>());
									}
									
									//changed for multiple groups -- ashu
									if(!(voBillingDtl.getGrouptariffDetails().contains(voReq.getStrLabCode()+voReq.getStrTestGroupCode()))){
										
										voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
										voBillingDtl.getGrouptariffQty().add("1");
									}
										
									
								}
							}
						}
							//Adding Details for Display after Save
							labName=newLabTest.getLabName();
							if(!testName.equals(""))
							  testName=testName+","+newLabTest.getTestName();
							else
								testName=newLabTest.getTestName();
							
					} // Loop Over TestCodes
					listReqId.add(crNO+"#"+labName+"#"+testName+"#"+patStatus);
				}		
				
				else
				{
					
			          tx.rollback();  // if not save succesfully		
				}
				//Add The Requisition Number in list
				
			}
			
			
			// Billing Logic :: Save
					
					
			System.out.println("patvo before address : "+patVO.getPatAddress());
			
			if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
			{
						String simpletariffdetails="";
						String simpletariffQty="";
			
						if(voBillingDtl.getTariffDetails()!=null)
						{
							for(int indexCounter=0;indexCounter<voBillingDtl.getTariffDetails().size();indexCounter++)
							{
								if(indexCounter==0)
								{
									
									simpletariffdetails=voBillingDtl.getTariffDetails().get(indexCounter).substring(5)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
									//simpletariffdetails=voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
									simpletariffQty=voBillingDtl.getTariffQty().get(indexCounter);
								}
								else
								{
									simpletariffdetails+="^"+voBillingDtl.getTariffDetails().get(indexCounter).substring(5)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
									//simpletariffdetails+="^"+voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
									simpletariffQty+="^"+voBillingDtl.getTariffQty().get(indexCounter);
								}
								
								
							}
						}
						System.out.println("simpletariffdetails : "+simpletariffdetails);
						String grouptariffdetails="";
						String checkDuplicateDetail="";
						boolean checkGrp=false;
						String grouptariffQty="";
						if(voBillingDtl.getGrouptariffDetails()!=null)
						{
							for(int indexCounter=0;indexCounter<voBillingDtl.getGrouptariffDetails().size();indexCounter++)
							{
								if(indexCounter==0)
								{
									grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
									//grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter);
									grouptariffQty=voBillingDtl.getGrouptariffQty().get(indexCounter);
									checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
								}
								else
								{
									String[] SplitCheckDuplicateDetail=checkDuplicateDetail.split("&");
									if(SplitCheckDuplicateDetail.length>1)
										for(int y=1;y<SplitCheckDuplicateDetail.length;y++)
										{
											if(!SplitCheckDuplicateDetail[y].equals(voBillingDtl.getGrouptariffDetails().get(indexCounter)))
											{
												  checkGrp=true;
											}
											//else
											//{
												 //checkGrp=false;
											//}
									
										}
									
									if(checkGrp)
									{
									grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
									//grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
									grouptariffQty+="^"+voBillingDtl.getGrouptariffQty().get(indexCounter);
									checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
								}
								}
							 
								
							}
						
						}
						
						System.out.println("grouptariffdetails -- : "+grouptariffdetails);
						
						
						
						
						
						if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
						{
							if(!voBillingDtl.getRaiseAdvise().equals("0"))
							{
								billno= invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
							}
							}
						
						if(grouptariffdetails.equals("")==false)
						{
							if(!voBillingDtl.getRaiseAdvise().equals("0"))
							{
								billno=	invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
							}
							}
			}
					System.out.println("patvo after address : "+patVO.getPatAddress());
			
			   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
			//   voReqHeader.setLabCode(labCode);
			 //  voReqHeader.setRequisitionNumber(requisitionNumber);
			   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
			   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
			   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
			   voReqHeader.setBedCode(patVO.getBedCode());
			   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
			   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
			   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
			   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
			   voReqHeader.setGenderCode(patVO.getPatGenderCode());
			   voReqHeader.setIsActualDob(patVO.getIsActualDob());
			   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
			   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
			   voReqHeader.setMlcNo(patVO.getPatmlcno());
			   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
			   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
			    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
			   voReqHeader.setPatDob(patVO.getPatDOB());
			   voReqHeader.setPatAge(patVO.getPatAge());
			   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
			   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
			
			  
			  // voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
			   voReqHeader.setRequsitionRaisedThrough("5");  //7
			   
			   
			   voReqHeader.setRoomCode(patVO.getPatroomno());
			   voReqHeader.setWardCode(patVO.getPatwardcode());
			   voReqHeader.setReqType(""+requisitionTypeForBilling);
			   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
			   voReqHeader.setWardName(patVO.getPatwardname());
			   voReqHeader.setBedName(patVO.getBedname());
			   
			   voReqHeader.setVisitNo(patVO.getPatVisitNo());
			  
			   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
			   
			   
			   
			   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
			    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
			    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
			    voReqHeader.setVisitReason(patVO.getVisitReason());
			    voReqHeader.setFollowup(patVO.getFollowup());
			if(datapidd!=null && !datapidd.equals(""))
			invEssentialDAO.insertPid(voReqHeader,_userVO,datapidd,datapidddata);	//procedure to insert into req_test_mand_dtl

			
			
			//Billing Save Logic End
			
		
			 
			
				
	//	return listReqId;	
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
			
			String msg="HIS DATA EXCEPTION";
			if(patVO.getIsamountshort()!=null && patVO.getIsamountshort().equals("1")){
				msg="Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!";
			
			}
			
			throw new HisDataAccessException(msg);
		
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
		return listReqId;
	}


	public  List<Inv_EpisodeVO> getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_EpisodeVO> listEpisodeVO=new ArrayList<Inv_EpisodeVO>();
		try
		{    
			tx.begin();
			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);

			listEpisodeVO=invEssentialDAO.getPatientEpisode(patVO, _userVO);
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
		return listEpisodeVO;

	}

	public  List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_PatientAdmissionDtlVO> listPatientAdmissionVO=new ArrayList<Inv_PatientAdmissionDtlVO>();
		try
		{    
			tx.begin();
			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);

			listPatientAdmissionVO=invEssentialDAO.getPatientAdmission(patVO, _userVO);
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
		return listPatientAdmissionVO;

	}

	/* Function to search Laboatory wise Test Details */
	public Map<String,Map<String,List<String>>>  getBookMarkDetails(UserVO userVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<BookMarkVO> lstBookMark=null;
		Map<String,Map<String,List<String>>> mpBookMark=new LinkedHashMap<String,Map<String,List<String>>>();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstBookMark=invEssentialDAO.getBookMarkList(userVO);

			if(lstBookMark!=null&&lstBookMark.size()>0)
			{
				List<String> lstTest=null;
				//Logic for Setting Book Mark
				for(BookMarkVO vo:lstBookMark)
				{
					Map<String,List<String>> mpLab=mpBookMark.get(vo.getBookMarkCode()+"#"+vo.getBookMarkName());
					
					
					if(mpLab==null)
					{
						mpLab=new LinkedHashMap<String,List<String>>();
						lstTest=new ArrayList<String>();
						lstTest.add(vo.getTestCode());
						mpLab.put(vo.getLabCode(),lstTest);
						mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
					}
					else
					{
						lstTest=mpLab.get(vo.getLabCode());
						if(lstTest!=null&&lstTest.size()>0)
						{
							lstTest.add(vo.getTestCode());
							mpLab.put(vo.getLabCode(),lstTest);
							mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
						}
						else
						{
							lstTest=new ArrayList<String>();
							lstTest.add(vo.getTestCode());
							mpLab.put(vo.getLabCode(),lstTest);
							mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
						}
					}

				}
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

		return mpBookMark;
	}


	/* Function to search Laboatory wise Test Details */
	public Map searchBookMark(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		List<LabTestVO> lstLabTestSample=null;

		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstLabTest=invEssentialDAO.searchBookMark(searchVO, _UserVO);
			lstLabTestSample=invEssentialDAO.searchLabTestSample(searchVO, _UserVO);



			//Logic for Getting Sample Combo
			if(lstLabTest!=null)
			for(LabTestVO vo:lstLabTest)
			{
				String strSAmpleCode = vo.getDefaultSampleCode();
				String sampleComboStr="<option value='-1'>Select Value</option>";
				
				//filter out specific samples from all lab test samples
				int noOfSamples=0;
				String singleSampleName="";
				String singleSampleCode="";
				String reqSampleShortName="";
				for(LabTestVO sample_vo:lstLabTestSample)
				{					
					if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
					{
						noOfSamples++;
						singleSampleName=sample_vo.getsName();
						reqSampleShortName=sample_vo.getReqSampleShortName();
						singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
						if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
						{
							
							String singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
							
							sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setReqdSampleName(sample_vo.getsName());
						vo.setReqSampleShortName(reqSampleShortName);
						}
						else
							sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
					}
													
				}
				if(noOfSamples==1)//single sample
				{
					
					sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
					vo.setSingleSample(singleSampleCode);
					vo.setReqdSampleName(singleSampleName);
					vo.setReqSampleShortName(reqSampleShortName);
				}
				
				
				//getting (samplecode#samplename,sam.....) string 
			/*	String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
				if(sampleString.equals("")==false)
				{
					String splitValues[] = sampleString.split(",");
					
					for(String sampleCodeName: splitValues)
					{
						if(splitValues.length==1)
						{
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
							vo.setSingleSample(sampleCodeName.split("#")[0]);
						}
						else
						{
						if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
						}
						
						
					}
					
					
					
				}*/
				
				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);

				
				
				
				
				
				/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						
						
						if(lstSampleCombo.size()==1)
						{
							
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
							vo.setSingleSample(entry.getValue());
						}
						else
						{
						
						
						if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
						}
						}
				}
*/
				

			 
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
					
				{
					String[] mandInfoCommaSeparator=mandInfo.split(",");
					
					int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
					String textBoxAndCombo="";
					
					String textBoxComboNames="";
					
					String  textBoxComboCode="";
					
					for(int i=0;i<mandInfoCommaSeparatorlength;i++)
					{
						String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
						
						 
						
						 
							String mandCode=maninfoHashSeparator[0];
							
							String mandName=maninfoHashSeparator[1];
							
							String mandType=maninfoHashSeparator[2];
						
							
							if(mandType.equals("1"))
							{
							
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <input type=\"text\" name=\""+mandName+"\" />";
								   	
									textBoxComboNames=mandName;
									
									textBoxComboCode=mandCode;
									
								}
								else
								{
									textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type=\"text\" name=\""+mandName+"\" />";
								
									textBoxComboNames=textBoxComboNames+"&"+mandName;
									
									textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								
							}
							if(mandType.equals("2"))
							{
								
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <select name=\""+mandName+"\"><option value=\"-1\">Select Value</option>";
									
									textBoxComboNames=mandName;
									
									textBoxComboCode=mandCode;
									
								}
								else
								{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name=\""+mandName+"\"><option value=\"-1\">Select Value</option>";
								
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								String mandCombo=vo.getMandCombo();
								
								String[] mandComboCommaSeparator=mandCombo.split(",");
								
								int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
								
								for(int k=0;k<mandComboCommaSeparatorlength;k++)
								{
									String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
										
									String mandComboCode=manComboHashSeparator[0];
									String mandComboName=manComboHashSeparator[1];
									
									if(mandCode.equals(mandComboCode))
									{
										textBoxAndCombo=textBoxAndCombo+"<option value=\""+mandComboName+"\">"+mandComboName+"</option>";	
									}
									
								}
								textBoxAndCombo=textBoxAndCombo+"</select>";
								
							}
							
							
						 
								vo.setSetMandTextBoxCombo(textBoxAndCombo);
								
								vo.setMandComboTextBoxComboNames(textBoxComboNames);
								vo.setMandCode(textBoxComboCode);

					 
					}
				}
				    

				 

			}


			mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK,lstLabTest);


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

	public Map searchTestGroup(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstLabTest=invEssentialDAO.searchTestGroup(searchVO, _UserVO);

			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				String sampleComboStr="<option value='-1'>Select Value</option>";
				List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
					}
				}
				//vo.setSampleComboStr(sampleComboStr);

				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);

				//Mandatory Combo/text Logic
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
					
				{
					String[] mandInfoCommaSeparator=mandInfo.split(",");
					
					int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
					String textBoxAndCombo="";
					String textBoxComboNames="";
					
					String textBoxComboCode="";
					
					for(int i=0;i<mandInfoCommaSeparatorlength;i++)
					{
						String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
						
						 
						
						 
							String mandCode=maninfoHashSeparator[0];
							
							String mandName=maninfoHashSeparator[1];
							
							String mandType=maninfoHashSeparator[2];
						
							
							if(mandType.equals("1"))
							{
							
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
									
									textBoxComboNames=mandName;
									
									textBoxComboCode=mandCode;
									
								}
								else
								{
									textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
									
									textBoxComboNames=textBoxComboNames+"&"+mandName;
									
									textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								
							}
							if(mandType.equals("2"))
							{
								
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
									
									textBoxComboNames=mandName;
									
									textBoxComboCode=mandCode;
									 
								}
								else
								{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								String mandCombo=vo.getMandCombo();
								
								String[] mandComboCommaSeparator=mandCombo.split(",");
								
								int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
								
								for(int k=0;k<mandComboCommaSeparatorlength;k++)
								{
									String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
										
									String mandComboCode=manComboHashSeparator[0];
									String mandComboName=manComboHashSeparator[1];
									
									if(mandCode.equals(mandComboCode))
									{
										textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
									}
									
								}
								textBoxAndCombo=textBoxAndCombo+"</select>";
				   				
							}
							
							
						 
								vo.setSetMandTextBoxCombo(textBoxAndCombo);
								
								vo.setMandComboTextBoxComboNames(textBoxComboNames);
								
								vo.setMandCode(textBoxComboCode);

					 
					}
				}
				    
				
				
				
				
 
			}


			mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);


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


	/* Function to search Group wise Test Details */
	public Map getTestsBasedOnGroup(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstLabTest=invEssentialDAO.getTestsUsingGroup(searchVO, _UserVO);

			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				String sampleComboStr="<option value='-1'>Select Value</option>";
				List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
					}
				}
				//vo.setSampleComboStr(sampleComboStr);

				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);

				//Mandatory Combo/text Logic
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
					
				{
					String[] mandInfoCommaSeparator=mandInfo.split(",");
					
					int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
					String textBoxAndCombo="";
					String textBoxComboNames="";
					String textBoxComboCode="";
					
					for(int i=0;i<mandInfoCommaSeparatorlength;i++)
					{
						String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
						
						 
						
						 
							String mandCode=maninfoHashSeparator[0];
							
							String mandName=maninfoHashSeparator[1];
							
							String mandType=maninfoHashSeparator[2];
						
							
							if(mandType.equals("1"))
							{
							
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
									  textBoxComboNames=mandName;
									  
									  textBoxComboCode=mandCode;
								}
								else
								{
									textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
								     
									textBoxComboNames=textBoxComboNames+"&"+mandName;
									
									textBoxComboCode=textBoxComboCode+"&"+mandCode;
								
								}
								
							}
							if(mandType.equals("2"))
							{
								
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
									
									textBoxComboNames=mandName;
									textBoxComboCode=mandCode;
									
								}
								else
								{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								String mandCombo=vo.getMandCombo();
								
								String[] mandComboCommaSeparator=mandCombo.split(",");
								
								int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
								
								for(int k=0;k<mandComboCommaSeparatorlength;k++)
								{
									String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
										
									String mandComboCode=manComboHashSeparator[0];
									String mandComboName=manComboHashSeparator[1];
									
									if(mandCode.equals(mandComboCode))
									{
										textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
									}
									
								}
								textBoxAndCombo=textBoxAndCombo+"</select>";
								
							}
							
							
						 
								vo.setSetMandTextBoxCombo(textBoxAndCombo);
                                
								vo.setMandComboTextBoxComboNames(textBoxComboNames);
								vo.setMandCode(textBoxComboCode);
					 
					}
				}
				    
				 

			}


			//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
			
			
			mp.put(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS,lstLabTest);


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


	/* Function to search Test Cod Wise Test Details */
	public Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		List<LabTestVO> lstLabTestSample=null;

		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);
			lstLabTestSample=invEssentialDAO.searchLabTestSample(searchVO, _UserVO);
                 

			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				String strSAmpleCode = vo.getDefaultSampleCode();
				String sampleComboStr="<option value='-1'>Select Value</option>";
				
				
				//filter out specific samples from all lab test samples
				int noOfSamples=0;
				String singleSampleName="";
				String singleSampleCode="";
				String reqSampleShortName="" ;
				
				for(LabTestVO sample_vo:lstLabTestSample)
				{					
					if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
					{
						noOfSamples++;
						singleSampleName=sample_vo.getsName();
						reqSampleShortName=sample_vo.getReqSampleShortName();
						singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
						
						if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
						{	String singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
							sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setReqdSampleName(sample_vo.getsName());
						vo.setReqSampleShortName(reqSampleShortName);
						}
						else
							sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode+"'>"+sample_vo.getsName()+"</option>";
					}
													
				}
				if(noOfSamples==1)//single sample
				{
					
					sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
					vo.setSingleSample(singleSampleCode);
					vo.setReqdSampleName(singleSampleName);
					vo.setReqSampleShortName(reqSampleShortName);
				}
				
				
				
				//getting (samplecode#samplename,sam.....) string 
				/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
				
				if(sampleString.equals("")==false)
				{
					String splitValues[] = sampleString.split(",");
					
					for(String sampleCodeName: splitValues)
					{
						if(splitValues.length==1)
						{
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
							vo.setSingleSample(sampleCodeName.split("#")[0]);
						}
						else
						{
						if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
						}
						
						
					}
					
					
					
				}
				*/
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);

				
				/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						if(lstSampleCombo.size()==1)
							
						{
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
							vo.setSingleSample(entry.getValue());
						}
						else
						{
						if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						else
							sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
						}
					}
				}*/
				//vo.setSampleComboStr(sampleComboStr);

				//Sample Combo Logic
				
				//Mandatory Combo/text Logic
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
					
				{
					String[] mandInfoCommaSeparator=mandInfo.split(",");
					
					int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
					String textBoxAndCombo="";
					String textBoxComboNames="";
					String textBoxComboCode="";
					
					for(int i=0;i<mandInfoCommaSeparatorlength;i++)
					{
						String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
						
						 
						
						 
							String mandCode=maninfoHashSeparator[0];
							
							String mandName=maninfoHashSeparator[1];
							
							String mandType=maninfoHashSeparator[2];
						
							
							if(mandType.equals("1"))
							{
							
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
									  textBoxComboNames=mandName;
									  
									  textBoxComboCode=mandCode;
								}
								else
								{
									textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
								     
									textBoxComboNames=textBoxComboNames+"&"+mandName;
									
									textBoxComboCode=textBoxComboCode+"&"+mandCode;
								
								}
								
							}
							if(mandType.equals("2"))
							{
								
								if(textBoxAndCombo.equals(""))
								{
									textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
									
									textBoxComboNames=mandName;
									textBoxComboCode=mandCode;
									
								}
								else
								{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
								}
								String mandCombo=vo.getMandCombo();
								
								String[] mandComboCommaSeparator=mandCombo.split(",");
								
								int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
								
								for(int k=0;k<mandComboCommaSeparatorlength;k++)
								{
									String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
										
									String mandComboCode=manComboHashSeparator[0];
									String mandComboName=manComboHashSeparator[1];
									
									if(mandCode.equals(mandComboCode))
									{
										textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
									}
									
								}
								textBoxAndCombo=textBoxAndCombo+"</select>";
								
							}
							
							
						 
								vo.setSetMandTextBoxCombo(textBoxAndCombo);
                                
								vo.setMandComboTextBoxComboNames(textBoxComboNames);
								vo.setMandCode(textBoxComboCode);
					 
					}
				}
				    
				 

			}


			//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
			
			
			mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

	
	
	
	
	public Map deleteReqDtl(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		Map mp=new HashMap();
		SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
		
		try {
			SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);
			
			
			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
			
			/* Inv_RequisitionRaisingPatientVO patVO=null;*/
          	 
			// String crno=patVO.getPatCRNo();
	        /*patVO=objSampleAcceptanceDAO.getInvRaisingPatDetails(crno,_UserVO);*/
			
			Inv_RequisitionRaisingPatientVO patVOnew=null;
	         
			//String crno=patVO.getPatCRNo();
			
			patVOnew=invEssentialDAO.getInvRaisingPatDetailsnew(null,_UserVO,searchVO.getRequisitionNo());
			
			
	             String  requisitionTypeForBilling="";
			
	             String reqNo=searchVO.getRequisitionNo()+"!";
	             voSamAcc.setRequisitionNo(reqNo);
			
		/*	  if(patVOnew.getPatStatusCode()!=null && patVOnew.getPatStatusCode().equals("2"))
				{						  
					requisitionTypeForBilling="2";
				}
				else 
				{
					//visit type code 1-opd, 2,3-emergency, 4 special
					if(patVOnew.getPatvisittypecode()==null)
						requisitionTypeForBilling="1";
					else{
						
					
					if(patVOnew.getPatvisittypecode().equals("1"))
						requisitionTypeForBilling="1";
					if(patVOnew.getPatvisittypecode().equals("4"))
						requisitionTypeForBilling="4";
					if(patVOnew.getPatvisittypecode().equals("2") ||patVOnew.getPatvisittypecode().equals("3") )
						requisitionTypeForBilling="3";
					}
				}*/
			  
			  
			  
			  if(voBillingDtl.getTariffDetails()==null)
				{
					voBillingDtl.setTariffDetails(new ArrayList<String>());
					voBillingDtl.setTariffQty(new ArrayList<String>());
				}
				
				voBillingDtl.getTariffDetails().add(searchVO.getDelLabCode()+searchVO.getDelTestCode());
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
				
				if(searchVO.getGroupcode()!=null && !searchVO.getGroupcode().equals("0"))
				{
					 makeBillingTestWise="4";//if group
					 simpletariffdetails=searchVO.getDelLabCode()+searchVO.getGroupcode();
				}
				else
				{
				 makeBillingTestWise="1";//if test
				 
				}
				
				}	
			
			voSamAcc.setIsbilledornot(searchVO.getIsbilledornot());
			
			objSampleAcceptanceDAO.makeRefund(voSamAcc,_UserVO,simpletariffdetails,simpletariffQty,patVO, patVOnew.getPatvisittypecode(),makeBillingTestWise);
	        
	        
			invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
 
			mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

	public int checkBillDtl(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		Map mp=new HashMap();
		String billDetail = null;
		int billed = 0;
		//SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
		
		try {
			SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);
			
			
			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
			
			/* Inv_RequisitionRaisingPatientVO patVO=null;*/
          	 
			Inv_RequisitionRaisingPatientVO patVOnew=null;
         
			String crno=patVO.getPatCRNo();
			
			patVOnew=invEssentialDAO.getInvRaisingPatDetailsnew(crno,_UserVO,searchVO.getRequisitionNo());
			
			 
	        /*patVO=objSampleAcceptanceDAO.getInvRaisingPatDetails(crno,_UserVO);*/
			
	        
	             String  requisitionTypeForBilling="";
			
	             String reqNo=searchVO.getRequisitionNo()+"!";
	             voSamAcc.setRequisitionNo(reqNo);
			
	         /*   if(patVOnew.getPatStatus().equals("IPD"))
	            	requisitionTypeForBilling=InvestigationConfig.REQUISITION_TYPE_IPD; //1
	 			else if(patVOnew.getPatStatus().equals("OPD"))
	 				requisitionTypeForBilling=InvestigationConfig.REQUISITION_TYPE_OPD; //2
	 			else
	 				requisitionTypeForBilling=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3
			  */
			  
			  
			  if(voBillingDtl.getTariffDetails()==null)
				{
					voBillingDtl.setTariffDetails(new ArrayList<String>());
					voBillingDtl.setTariffQty(new ArrayList<String>());
				}
				
				voBillingDtl.getTariffDetails().add(searchVO.getDelLabCode()+searchVO.getDelTestCode());
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
			}	
			
		//	objSampleAcceptanceDAO.makeRefund(voSamAcc,_UserVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
	        
	        
			billDetail = invEssentialDAO.checkBillDtl_beforedelete(searchVO, _UserVO,patVOnew.getPatvisittypecode());
 
			if(billDetail!=null)
			{
				String billNo=billDetail.replace("^", "#").split("#")[0];
				
				
				if(!billNo.equals("0")&& billNo!=null)
					billed = 1;
				else
					billed = 0;
			}
			/*else
				billed = 0;*/
			
			//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

		return billed;
	}
	
	//Sample Collection Process

	/* Function to get the Sample colleaction area */
	public List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _UserVO) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			lstinvSampleCollectionVO =objSampleCollectionDAO.getSampleCollectionArea(_UserVO);



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

		return lstinvSampleCollectionVO;
	}
	/* Function to get the sample Patient List */
	public List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

			lstinvSampleCollectionVO =objSampleCollectionDAO.getPatList(objSampleCollectionVO,_UserVO);


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

		return lstinvSampleCollectionVO;
	}


	/* Function to search Laboatory wise Test Details */
	@Override
	public Map getBilledPatientList(List<String> reqList,Inv_SampleCollectionVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();

		String reqType="";



		List<Inv_SampleCollectionVO> lstTestBased= new ArrayList<Inv_SampleCollectionVO>();

		List<Inv_SampleCollectionVO> lstUnBilledPatList=null;
		List<Inv_SampleCollectionVO> lstBilledPatList=null;

		//Change as per Astha Ma'ms suggestion
		Map<String,List<Inv_SampleCollectionVO>> mpBilledPat=new HashMap<String, List<Inv_SampleCollectionVO>>();
		Map<String,List<Inv_SampleCollectionVO>> mpUnBilledPat=new HashMap<String, List<Inv_SampleCollectionVO>>();

		List lstUOMCombo=null;
		List lstContainerCombo=null;
		List lstmachineCombo=null;


		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);

			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);

			lstmachineCombo=objSampleCollectionDAO.getmachineCombo(_UserVO);
			
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
				//Inv_SampleCollectionVO voSampleCollection=new Inv_SampleCollectionVO();
				lstTestBased =objSampleCollectionDAO.getBilledPatList(reqNo,reqType,_UserVO);
				if(lstTestBased!=null)
				{
					for(Inv_SampleCollectionVO voSampleCollection:lstTestBased)
					{
						sampleCode=voSampleCollection.getSampleCode()==null?sampleCode:voSampleCollection.getSampleCode();
						lstBilledPatList= mpBilledPat.get(reqNo+"#"+labCode+"#"+sampleCode);
						lstUnBilledPatList= mpUnBilledPat.get(reqNo+"#"+labCode+"#"+sampleCode);

						if(lstBilledPatList==null||!(lstBilledPatList.size()>0))
							lstBilledPatList=new ArrayList<Inv_SampleCollectionVO>();
							if(lstUnBilledPatList==null||!(lstUnBilledPatList.size()>0))
								lstUnBilledPatList=new ArrayList<Inv_SampleCollectionVO>();	

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
								{
									
									lstBilledPatList.add(voSampleCollection);
									mpBilledPat.put(reqNo+"#"+labCode+"#"+sampleCode,lstBilledPatList); // Putting Map as per Astha Ma'm Suggestion
								}
								else
								{
									
									lstUnBilledPatList.add(voSampleCollection);
									mpUnBilledPat.put(reqNo+"#"+labCode+"#"+sampleCode,lstUnBilledPatList); // Putting Map as per Astha Ma'm Suggestion
								}

					}
				}
			}


			//Putting Combos in map
			mp.put(InvestigationConfig.LIST_UOM_COMBO,lstUOMCombo);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO,lstContainerCombo);
			mp.put(InvestigationConfig.LIST_MACHINEE_COMBO,lstmachineCombo);

			// Code commented as logic replaced by Map Logic
			//Putting Billed & UnBilled List
			//mp.put(InvestigationConfig.LIST_PAT_SAMPLE_BILLED,lstBilledPatList);
			mp.put(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED,mpUnBilledPat);

			//Putting Billed & UnBilled Map in essential Map
			mp.put(InvestigationConfig.MAP_PAT_SAMPLE_BILLED,mpBilledPat);
			//mp.put(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED,lstUnBilledPatList);


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

	
	public List<String> getStaffDetails(String crNo, UserVO _UserVO){
		
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<String> staffDetails= new ArrayList<String>();
		
		try{

			tx.begin();
			
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

			staffDetails=objSampleCollectionDAO.getStaffDetails(crNo,_UserVO);
			
			
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
		
		
		return staffDetails;
	}
	
	
	  public Map getPackingListGenerationOnLoad(Inv_SampleCollectionVO voSample, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<Inv_SampleCollectionVO> lstSampleAcceptanceVO=new ArrayList<Inv_SampleCollectionVO>();

  
		try
		{
			tx.begin();
			PackingListGenerationDAO objPackingListGenrationDAO=new PackingListGenerationDAO(tx); 

			lstSampleAcceptanceVO=objPackingListGenrationDAO.getPackingListGenerationOnLoad(voSample, _UserVO);

			//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);
			mp.put(InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO,lstSampleAcceptanceVO);
			
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
	
	
		//Sample Collection Save Logic
		public  List saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			List listSamples=new ArrayList();
			List newlistSamples=new ArrayList();

			Map<String,List> mpp=new HashMap<>();
			String collAreaBasedSampleNo="";
			List<Inv_SampleCollectionVO> lstAutoSampleNOConfig=new ArrayList<Inv_SampleCollectionVO>();
			try
			{    
				tx.begin();
				SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

				InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);

				InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();

				// Step-1 :: Get Map Of Requisitions from Map of CrNo's
				Set setCrNo=mp.keySet();

				Iterator itrCrNo=setCrNo.iterator();

				//iterate over Crno's
				while(itrCrNo.hasNext())
				{
					// Get CrNo from iterator
					String crNo=(String)itrCrNo.next();

					// Get Map of Requisitions from Map of Cr No's
					Map<String,Map<String,List<Inv_SampleCollectionVO>>> mpReqNo=mp.get(crNo);

					//Getting Set of Requisitions
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
						Map<String,List<Inv_SampleCollectionVO>> mpSample=mpReqNo.get(reqNo);

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

							// Generate of SampleNo
							// Required HospitalCode,sampleNo,LabCode
							//Generate Sample No Sequence  for each lab
							
							// Commented By Anant Patel on 27th-May-2015
							//  Reason:- Query Location changed to pkg_inv_unique_no_generation.generate_save_sampleno from properties file 
							
							/*String sequence_Hash_yymmdd=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);  // Returns sequence#yymmdd 
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
							}
							
							//objSampleCollectionDAO.saveSampleNoInMaintainer(sampleCode, labCode, sequence, yymmdd, _userVO); Commented By Anant Patel on 27th-May-2015  */
							
							//Now this method itself generate the sample No and also insert/update in SYS_SAMPLE_MAINTAINER.  Added By Anant Patel on 27th-May-2015
							String strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO); 
							
						   String sameSampleNO="";	
							//Get List of Sample Collection VO from Map of Samples
							List<Inv_SampleCollectionVO> lstSampleCollectionVO=mpSample.get(sampleCode+"#"+labCode);
	                   
							// Loop over VO for saving
							for(Inv_SampleCollectionVO voSample:lstSampleCollectionVO)
							{
							
								
								//voSample.setSampleNo(strSampleNo);

								// Update Requisition Header (only once based on flag isReqHeaderUpdated)
								if(!isReqHeaderUpdated)
								{
									objSampleCollectionDAO.updateRequisitionHeader(voSample.getPatMobile(), voSample.getPatEmail(), voSample.getPatAddress(), reqNo, _userVO);
									isReqHeaderUpdated=true;
								}
								//Logic For Auto SAmple No Generation
							 
								//lstAutoSampleNOConfig=objSampleCollectionDAO.getSampleCollAutoSampleNOConfig(voSample, _userVO);
							 
							//	int lstofSize=lstAutoSampleNOConfig.size(); &&lstofSize!=0
								 
									
								if((voSample.getCheckAutoLabConfig().equals("1")||voSample.getCheckAutoLabConfig().equals("2"))) 
								{

									//for(Inv_SampleCollectionVO autoSampleVo:lstAutoSampleNOConfig)
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
											
											//c.add(Calendar.DATE,7);  
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
										 
										 String runningLabNO=objSampleCollectionDAO.checkAutoGenFormateRunningLabNo(voSample, _userVO);
										 //checkAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
										 if(sameSampleNO==strSampleNo)
										 {
												voSample.setTemparorySampleNO(runningLabNO); 
										 }
										 else
										 {
										 
										 voSample.setRunningSampleNo(runningLabNO);
										 
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
											
											SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
											objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1ResetLabNO(voSample,finalEntryDate,_userVO);
											voSample.setTemparorySampleNO(FinalSampleNo);
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
										SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
										
										//-----------------------------------------------area wise sample no temp!
										if(voSample.getTempSampleNo().equals("2"))
										if(collAreaBasedSampleNo.equals(""))
										{
										collAreaBasedSampleNo=voSample.getTemparorySampleNO();
									//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
										}
										else
											voSample.setTemparorySampleNO(collAreaBasedSampleNo);
										
										
										//--------------------------------------appended above in sample no generation area wise. 
										//-=------------------------------------ remove comment is above block of collAreaBasedSampleNo removed
										objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
								
										
										
										
										
										//}for Loop End
									// Update Requisition Dtl  Table 
									//Setting Requisition Dtl Status as '3' for packing list generation
										 }
									voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST); //3
									voSample.setSampleNo(strSampleNo);
									voSample.setTempSampleNo(voSample.getTemparorySampleNO());
									
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
									voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST); //3
									voSample.setSampleNo(strSampleNo);
									objSampleCollectionDAO.updateRequisitionDtl(voSample, _userVO);


									// Insert in Sample Dtl Table
									if(!isSampleDtlInsert)
									{
										objSampleCollectionDAO.insertSampleDtl(voSample, _userVO);
									//	isSampleDtlInsert=true;
									}
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

								SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								Date date = new Date();
								//System.out.println(dateFormat.format(date));
								String datee=dateFormat.format(date);
								//Adding generated sampleNo dtls for displaying
								if(!listSamples.contains(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleNo()+"#"+voSample.getLabName()+"#"+voSample.getRequisitionDate()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleName()+"#"+datee+"#")) 
									listSamples.add(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleNo()+"#"+voSample.getLabName()+"#"+voSample.getRequisitionDate()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleName()+"#"+datee+"#");
							
								String keyy=voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleNo()+"#"+voSample.getLabName()+"#"+voSample.getRequisitionDate()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleName()+"#"+datee+"#";
								
							if(!mpp.containsKey(keyy))
							{
								List listSamples1=new ArrayList();

								listSamples1.add(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleNo()+"#"+voSample.getLabName()+"#"+voSample.getRequisitionDate()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleName()+"#"+datee+"#"+voSample.getTestName());
								mpp.put(keyy, listSamples1);
								
							}
							else
							{
								List listSamples11=mpp.get(keyy);
								
								String ss=listSamples11.get(0).toString();
								String sample=ss.split("#")[1];
								String testname=ss.split("#")[9];
								
								
								if(voSample.getSampleName().equalsIgnoreCase(sample))
								{
									
									List listSamples1=new ArrayList();

									listSamples1.add(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleNo()+"#"+voSample.getLabName()+"#"+voSample.getRequisitionDate()+"#"+voSample.getTempSampleNo()+"#"+voSample.getSampleName()+"#"+datee+"#"+voSample.getTestName()+","+testname);
									mpp.remove(keyy);
									mpp.put(keyy, listSamples1);
									
								}
								

								
							}
							
							
							
							
							
								
							
							}
							
							

						}
					}
				}

				if(mpp!=null && mpp.size()>0)
				{
					Iterator itr=mpp.keySet().iterator();
			          Iterator itr1=mpp.keySet().iterator();
			          while(itr1.hasNext())
				 		{
				 			
				 			String keyyy=(String)itr1.next();
				 			
				 			List lstVOSample=mpp.get(keyyy);
                              String ss=(String) lstVOSample.get(0);
				 			newlistSamples.add(ss);
				 		}
				}
				
				return newlistSamples;	
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


		
		
	  
	  
	
	/* Function to search Laboatory wise Test Details */
	public boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		boolean isTempSampleNoPresent=false;



		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

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

	//Check Auto Generation Of Sample No For Sample Collection Process
	public String checkAutoGenFormate(Inv_SampleCollectionVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		List isFormatePresent=null;

		List<Inv_SampleCollectionVO> voSample1=null;
		
       String Formate="null";

      // String Formate="null+#+null+#+null+#+null+#+null+#+null+#+null";
       
		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

			voSample1=objSampleCollectionDAO.checkAutoGenFormate(voSample, _UserVO);

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

	//Packing List Generation Process
	/* Function to search Laboatory wise Test Details */
	public Map getPackingListGenerationEssentials(Inv_SampleCollectionVO voSample, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();

		List lstLabCombo=null;

		try {

			tx.begin();
			PackingListGenerationDAO objPackingListGenrationDAO=new PackingListGenerationDAO(tx);

			lstLabCombo=objPackingListGenrationDAO.getLaboratoryCombo(voSample, _UserVO);

			//Putting Combos in map
			mp.put(InvestigationConfig.LIST_LABORATORY_COMBO,lstLabCombo);

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


	/* Function to get the sample Patient List */
	public List<Inv_SampleCollectionVO> getPackingListPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

		try {

			tx.begin();
			PackingListGenerationDAO objPackingListDAO=new PackingListGenerationDAO(tx);

			lstinvSampleCollectionVO =objPackingListDAO.getPackingListPatList(objSampleCollectionVO,_UserVO);


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

		return lstinvSampleCollectionVO;
	}

	//Sample Collection Save Logic
	public  Map<String,List<Inv_SampleCollectionVO>> savePackingListDetails(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listSamples=new ArrayList();
		try
		{    
			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

			PackingListGenerationDAO objPackingListDAO=new PackingListGenerationDAO(tx);


			InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);

			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();


			// Step-1 :: Get Map Of SampleNo's
			Set setLabCode=mp.keySet();

			Iterator itrLabCode=setLabCode.iterator();

			//iterate over Sample No's
			while(itrLabCode.hasNext())
			{
				// Get sampleNo from iterator
				String labCode=(String)itrLabCode.next();

				boolean isRequisitionDtlUpdated=false;
				boolean isSampleDtlUpdated=false;
				boolean isPackingListInserted=false;


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


				// Get List of Inv_SampleCollectionVO from Map of Sample No's
				List<Inv_SampleCollectionVO> lstSampleCollectionVO=mp.get(labCode);

				for(Inv_SampleCollectionVO voSample:lstSampleCollectionVO)
				{
					//Setting Packing List No in VO
					voSample.setPackingListNo(strPackingListNo);

					// Update Requisition Dtl (only once based on flag isRequisitionDtlUpdated)
					//if(!isRequisitionDtlUpdated)
					//{
					//Setting Requisition Dtl Status as '4' for Sample Acceptance Process
					voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE); //4

					objPackingListDAO.updateRequisitionDtl(voSample, _userVO);
					//	isRequisitionDtlUpdated=true;
					//}


					// Update Sample Dtl (only once based on flag isSampleDtlUpdated)
					//if(!isSampleDtlUpdated)
					//{
					//Setting Sample Dtl Status as '5' for Sample Acceptance Process
					voSample.setSampleStatus(InvestigationConfig.SAMPLE_DTL_STATUS_READY_SAMPLE_ACCEPTANCE); //5

					objPackingListDAO.updateSampleDtl(voSample, _userVO);

					//isSampleDtlUpdated=true;
					//}


					if(!isPackingListInserted)
					{	
						voSample.setPackingListNo(strPackingListNo);
						voSample.setListStatus(InvestigationConfig.LIST_STATUS_INCOMPLETE);  //1
						voSample.setIsPackingListOffline(InvestigationConfig.PACKING_LIST_OFFLINE_YES);  //1


						// Insert in PackingList Dtl Table
						objPackingListDAO.insertPackingListDtl(voSample, _userVO);
						isPackingListInserted=true;
					}

				}
			}

			return mp;	
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

	//Sample Collection Save Logic
	public  Map<String,List<Inv_SampleCollectionVO>> generateDuplicatePackingList(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{    
			tx.begin();

			PackingListGenerationDAO objPackingListDAO=new PackingListGenerationDAO(tx);

			// Step-1 :: Get Map Of SampleNo's
			Set setLabCode=mp.keySet();

			Iterator itrLabCode=setLabCode.iterator();

			//iterate over Sample No's
			while(itrLabCode.hasNext())
			{
				// Get labCode from iterator
				String labCode=(String)itrLabCode.next();

				// Get List of Inv_SampleCollectionVO from Map of Sample No's
				List<Inv_SampleCollectionVO> lstSampleCollectionVO=mp.get(labCode);

				for(Inv_SampleCollectionVO voSample:lstSampleCollectionVO)
				{
					String strPackingListNo=objPackingListDAO.getPackingListNo(voSample, _userVO);
					//Setting Packing List No in VO
					voSample.setPackingListNo(strPackingListNo);
				}
			}

			return mp;	
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

	public Map  getRequisitionRaisingEssentials(UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String labNames="";
		String testNames="";
		String testGroupNames="";
		String advisedByNames="";
		String testCodeWise="";
		String testCodeWiseValueForCombo="";
		String testCodeWiseValueForComboValue="";
		
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		//	List lstLabNames=invEssentialDAO.getLabNames(userVO);
			List lstLabNames=null;
		//	List lstTestNames=invEssentialDAO.getTestNames(userVO);
			List lstTestNames=null;

			List lstTestGroupNames=invEssentialDAO.getTestGroupNames(userVO);
          
			List lstadvisedByNames=invEssentialDAO.getAdvisedByNames(userVO);
			
		//	List lstTestCodeWise=invEssentialDAO.getTestCodeNames(userVO);
			List lstTestCodeWise=null;
			
			if(lstLabNames!=null)
			{
				StringBuilder sb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstLabNames.size() - 1 ; i++) {
					sb.append("{ label: \""+((Entry)lstLabNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstLabNames.get(i)).getValue()+"\" }");
					sb.append(",");
				}

				// last string, no separator
				if(lstLabNames.size() > 0){
					sb.append("{ label: \""+((Entry)lstLabNames.get(lstLabNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstLabNames.get(lstLabNames.size()-1)).getValue()+"\" }");
				}

				labNames="["+sb.toString()+"]";
			}

 
	
 
			if(lstTestNames!=null)
			{
				StringBuilder sbb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstTestNames.size() - 1 ; i++) {
					sbb.append("{ label: \""+((Entry)lstTestNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(i)).getValue()+"\" }");
					sbb.append(",");
				}

				// last string, no separator
				if(lstTestNames.size() > 0){
					sbb.append("{ label: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getValue()+"\" }");
				}

				testNames="["+sbb.toString()+"]";
				System.out.println("testName"+sbb.toString());	
				System.out.println("testName"+testNames);
			}
	 
			if(lstTestGroupNames!=null)
			{
				StringBuilder sb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstTestGroupNames.size() - 1 ; i++) {
					sb.append("{ label: \""+((Entry)lstTestGroupNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestGroupNames.get(i)).getValue()+"\" }");
					sb.append(",");
				}

				// last string, no separator
				if(lstTestGroupNames.size() > 0){
					sb.append("{ label: \""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getValue()+"\" }");
				}

				testGroupNames="["+sb.toString()+"]";
			}
			
			
			
			if(lstadvisedByNames!=null)
			{
				StringBuilder sb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstadvisedByNames.size() - 1 ; i++) {
					sb.append("{ label: \""+((Entry)lstadvisedByNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(i)).getValue()+"\" }");
					//sb.append("{ label: \""+((Entry)lstTestCodeWise.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(i)).getValue()+"\" }");
				//	((Entry)lstTestCodeWise.get(i)).getValue()
					sb.append(",");
				}

				// last string, no separator
				if(lstadvisedByNames.size() > 0){
					sb.append("{ label: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getValue()+"\" }");
				//	sb.append("{ label: \""+((Entry)lstTestCodeWise.get(lstTestCodeWise.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getValue()+"\" }");
				
				}

				advisedByNames="["+sb.toString()+"]";
			}
			
			
			if(lstTestCodeWise!=null)
			{
				StringBuilder sb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstTestCodeWise.size() - 1 ; i++) {
					sb.append("{ label: \""+((Entry)lstTestCodeWise.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestCodeWise.get(i)).getValue()+"\" }");
					sb.append(",");
				}

				// last string, no separator
				if(lstTestCodeWise.size() > 0){
					sb.append("{ label: \""+((Entry)lstTestCodeWise.get(lstTestCodeWise.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestCodeWise.get(lstTestCodeWise.size()-1)).getValue()+"\" }");
				}

				testCodeWise="["+sb.toString()+"]";
				
				
				
				 
			}
			
			/*//For Test
			if(lstTestCodeWise!=null)
			{
				StringBuilder sb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstTestCodeWise.size() - 1 ; i++) {
					sb.append(((Entry)lstTestCodeWise.get(i)).getLabel());
					sb.append(",");
				}
				testCodeWiseValueForCombo=sb.toString();
				
				for(int i = 0; i < lstTestCodeWise.size() - 1 ; i++) {
					sb.append(((Entry)lstTestCodeWise.get(i)).getValue());
					sb.append(",");
				}
                
				testCodeWiseValueForComboValue=sb.toString();
				
				
				 
			}
			*/

			//mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);

		//	mp.put(InvestigationConfig.ARRAY_TESTNAMES, testNames);

			mp.put(InvestigationConfig.ARRAY_TESTGROUPNAMES, testGroupNames);
			
			
			mp.put(InvestigationConfig.ARRAY_ADVISEDBY_NAMES, advisedByNames);
			
			
		//	mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCodeWise);
			
			
			/*mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_FOR_COMBO, testCodeWiseValueForCombo);
			mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_FOR_COMBO_VALUE, testCodeWiseValueForComboValue);*/

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

	public String  getTestComboAJAX(String labCode,UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String testNames="";

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			List lstTestNames=invEssentialDAO.getTestNamesUsingAJAX(labCode, userVO);


			if(lstTestNames!=null)
			{
				StringBuilder sb = new StringBuilder();

				// all but last
				for(int i = 0; i < lstTestNames.size() - 1 ; i++) {
					sb.append("{ label: \""+((Entry)lstTestNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(i)).getValue()+"\" }");
					sb.append(",");
				}

				// last string, no separator
				if(lstTestNames.size() > 0){
					sb.append("{ label: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getValue()+"\" }");
				}

				testNames="["+sb.toString()+"]";
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

		return testNames;
	}
	public String  getTestGroupAJAX(String labCode,UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String testGroupNames="";

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			List lstTestGroupNames=invEssentialDAO.getTestGroupUsingAJAX(labCode, userVO);


			if(lstTestGroupNames!=null)
			{
				StringBuilder sb = new StringBuilder();
				// all but last
				for(int i = 0; i < lstTestGroupNames.size() - 1 ; i++) {
					sb.append("{\"label\":\""+((Entry)lstTestGroupNames.get(i)).getLabel()+"\",\"value\":\""+((Entry)lstTestGroupNames.get(i)).getValue()+"\"}");
					sb.append(",");
				}
				// last string, no separator
				if(lstTestGroupNames.size() > 0){
					sb.append("{\"label\":\""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getLabel()+"\",\"value\":\""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getValue()+"\"}");
					}
				testGroupNames="["+sb.toString()+"]";
				System.out.println("the value is of TESTGROUP"+testGroupNames);
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

		return testGroupNames;
	}

	//changed by ashu
	
	public String  getStringToAddToRowAJAX(String isTestGroup,LabTestVO voLabTest){
		
		String testCodeListVal = "";
		
		String singleSample="-1";
		String reqdSampleName="-";
		
		String testGroupCode = (isTestGroup == null || isTestGroup
				.equals("0")) ? "0"
				: voLabTest
						.getTestGroupCode();
		String testGroupType = (isTestGroup == null || isTestGroup
				.equals("0")) ? "0"
				: voLabTest
						.getTestGroupType();
		// Please Notice the Order of Hashing value is fixed, should get the value any where in future in the same order
		// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType      //Please Ensure the corresponding Changes(In UTIL file, java script) before changing this order
		if(voLabTest.getSingleSample()!=null && voLabTest.getSingleSample().equals("")==false)
			singleSample=voLabTest.getSingleSample();

		if(voLabTest.getReqdSampleName()!=null && voLabTest.getReqdSampleName().equals("")==false)
			reqdSampleName=voLabTest.getReqdSampleName();

		String instruction1=voLabTest.getInstructionPat();
		
		testCodeListVal = voLabTest.getLabCode()
				+ "#"
				+ voLabTest.getLabName()
				+ "#"
				+ voLabTest.getTestCode()
				+ "#"
				+ voLabTest.getTestName()
				+ "#"
				+ voLabTest
						.getSampleComboStr()
				+ "#"
				+ voLabTest.getTestType()
				+ "#"
				+ voLabTest
						.getIsAppointment()
				+ "#"
				+ voLabTest
						.getIsConfidential()
				+ "#"
				+ singleSample
				+ "#"
				+ "1"
				+ "#"
				+ testGroupCode
				+ "#"
				+ testGroupType
				+ "#"
				+ voLabTest
						.getIsMandatoryReq()
				+ "#"
				+ voLabTest
						.getBookMarkCode()
				+ "#"
				+ voLabTest
						.getOfflineAppoitMentDate()

				+ "#"+voLabTest.getSetMandTextBoxCombo()+"#"+voLabTest.getMandComboTextBoxComboNames()+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+instruction1+"#"+voLabTest.getIsLabAvailable()  /* islabavailable contains 2 strings separated by hash. care to be taken to add more values in lab test array numbering */
				+"#"+reqdSampleName+"#"+voLabTest.getIsRequisitionFormNeeded()+"#"+voLabTest.getReqSampleShortName()+"#"+voLabTest.getDeskcallingid()+"#"+voLabTest.getIspidshow()+"#"+voLabTest.getIslabappointmentbased()+"#";
	 
		//System.out.println("testCodeListVal : "+testCodeListVal);
		
		
		return testCodeListVal;
	}
	
	
	//getreqStatusAJAX
	public String  getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO _UserVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String testReqStatus="";

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			List lstTestReqStatus=invEssentialDAO.getreqStatusAJAX(searchVO, _UserVO);


			if(lstTestReqStatus!=null)
			{
				StringBuilder sb = new StringBuilder();
				// all but last
				for(int i = 0; i < lstTestReqStatus.size()  ; i++) {
					sb.append(((Entry)lstTestReqStatus.get(i)).getLabel());
					sb.append(",");
				}
				 
				 
				testReqStatus=sb.toString();
				System.out.println("the value is of TESTGROUP"+testReqStatus);
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

		return testReqStatus;
	}
	
	
	public  List savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listReqdtlId=new ArrayList();
		/*String sameLabNo="";
		String sameLabCode="";*/
		System.out.println("savePatientDetailss");
		try
		{    
			tx.begin();
			OnlinePatientAcceptanceDAO objOnlinePatientAcceptanceDAO=new OnlinePatientAcceptanceDAO(tx);
                 Map<String,String> keyy=new HashMap<String,String>();
			InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);

			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();

			// Step-1 :: Get Map Of Requisitions from Map of CrNo's
			Set setCrNo=mp.keySet();

			Iterator itrCrNo=setCrNo.iterator();

			//iterate over Crno's
			while(itrCrNo.hasNext())   
			{
				
				String sameLabNo="";
				String sameLabCode="";
				
				// Get CrNo from iterator
				String crNo=(String)itrCrNo.next();

				// Get Map of Requisitions from Map of Cr No's
				Map<String,Map<String,List<OnlinePatientAcceptanceVO>>> mpReqNo=mp.get(crNo);

				//Getting Set of Requisitions
				Set setReqNo=mpReqNo.keySet();

				Iterator itrReqNo=setReqNo.iterator();

				//Iterate over ReqNo's
				while(itrReqNo.hasNext())
				{
					// Get Requistion No from iterator
					String reqNo=(String)itrReqNo.next();

					// Update in Requisition Header table for Mobile No, emailId,Address
					boolean isReqHeaderUpdated=false;


					Map<String,List<OnlinePatientAcceptanceVO>> mpLabCode=mpReqNo.get(reqNo);

					Set setLabCode=mpLabCode.keySet();

					Iterator itrLabCode=setLabCode.iterator();


					while(itrLabCode.hasNext()) 
					{
						
						
						String labCode=(String)itrLabCode.next();
						
						//added by prashant
						List<OnlinePatientAcceptanceVO> lstPatientCollectionVO=mpLabCode.get(labCode);
						String acceptedToNotAccepted=lstPatientCollectionVO.get(0).getAcceptedToNotAccepted();
						String patAccNo="";
						//get acc_no if already accepted
						if(acceptedToNotAccepted.equals("2")) {
							patAccNo=objOnlinePatientAcceptanceDAO.selectPataccNoPatientAcceptedDtl(lstPatientCollectionVO.get(0),_userVO);
						} else 
					     	{
							String sequence_Hash_yymmdd=objOnlinePatientAcceptanceDAO.generatePatientNoSequence(labCode, _userVO);  // Returns sequence#yymmdd  //attention
							String sequence=sequence_Hash_yymmdd.split("#")[0];
							String yymmdd=sequence_Hash_yymmdd.split("#")[1]; //attention
							patAccNo=labCode+""+yymmdd+""+sequence; 
							//Logic to check the sequence is '100001' 

							if(sequence.equals(InvestigationConfig.SAMPLE_NO_SEQUENCE_INVESTIGATION)) //100001
							{
								objOnlinePatientAcceptanceDAO.insertPatientNoSequenceInMaintainer(labCode,sequence,yymmdd,_userVO);//attention
							}
							else
							{
								objOnlinePatientAcceptanceDAO.updatePatientNoSequenceInMaintainer(sequence, labCode, _userVO);//attention
							}
						}
						
						
						
						//commented by prashant
						//List<OnlinePatientAcceptanceVO> lstPatientCollectionVO=mpLabCode.get(labCode);
						// Loop over VO for saving
					
						for(OnlinePatientAcceptanceVO voPatient:lstPatientCollectionVO)
						{
					
							// Update Requisition Header (only once based on flag isReqHeaderUpdated)
							if(!isReqHeaderUpdated)
							{
								objOnlinePatientAcceptanceDAO.updateRequisitionHeaderForPatient( voPatient, voPatient.getAddress(), reqNo, _userVO);
								isReqHeaderUpdated=true;
							}
							
							//Logic For Auto Lab No Generation
						 
							List<OnlinePatientAcceptanceVO> lstAutoLabNOCon=new ArrayList<OnlinePatientAcceptanceVO>();
                             
							//lstAutoLabNOCon=objOnlinePatientAcceptanceDAO.getPatientAcceptanceAutoLabNOConfig(voPatient, _userVO);
							
							///int lstofSize=lstAutoLabNOCon.size();
							
							
							
							
							
							
							if(voPatient.getCheckAutoLabConfig().equals("2") )
							{
								
								
								

								//for(OnlinePatientAcceptanceVO autoLabVo:lstAutoLabNOCon)
								//{
									
									//if(!autoLabVo.getLabNoFormat().equals("-"))
									//{
										
									
									String autoLabNumber=voPatient.getLabNoFormat();
									String Str = new String(autoLabNumber);
									int MainStrlength=Str.length();
									String[] dateFormate = Str.split("&");
									String subDateFormate=dateFormate[0];
									String xMainValue=dateFormate[1];
									//getting the DateFormate number
									String sequence_LabNO_yymmdd=objOnlinePatientAcceptanceDAO.generateLabNoDateSequence(subDateFormate, _userVO);  // Returns Date Formate
									String entryDate= voPatient.getInitDate();
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
									Date date = df.parse(entryDate);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Calendar c = Calendar.getInstance();
									c.setTime(date); // Now use entry date.
                                     String finalDate="";
									
									if(voPatient.getInitType().equals("m"))
									{
										
										int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
										  
										String[] SplitDate=entryDate.split("-");
										
										 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
										 date=df.parse(finalDate);
										 
									}
									if(voPatient.getInitType().equals("d"))
									{
										
										date = df.parse(entryDate);
										//c.add(Calendar.DATE,1); 
									}
									if(voPatient.getInitType().equals("y"))
									{
										String[] SplitDate=entryDate.split("-");
										
										 finalDate=SplitDate[0]+"-"+12+"-"+31;
										date = df.parse(finalDate);
									}
									if(voPatient.getInitType().equals("w"))
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
									Date todaydateobj = new Date();
									SimpleDateFormat dateFormatObj = new SimpleDateFormat("yyyy-MM-dd");
									String strDate= dateFormatObj.format(todaydateobj);
									int EntryDatecomparWithSysDAte = strDate.compareTo(finalEntryDate);	//attention	
									//String xMainValue=Str.substring(subStrDateLength,MainStrlength);
									int xMainLen=xMainValue.length();
									
									
									//For From Series Count
									String FinalLabNo="";
									int fromSerValue;
									  
									String fromSer=voPatient.getFromSeries();
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
									int gnumnoofseqdigits1=Integer.parseInt(voPatient.getNoOfSeqDigit());
									 String subStrvalueLab1=xVal.substring(Math.max(0,xValLen - gnumnoofseqdigits1));
								
									 //check for 1 labcode generate 1 labno
									if(sameLabCode.equals(voPatient.getLabCode()))
									{
										
									}
									else
									{
                                      String runningLabNO="";
                                      
                                      if(voPatient.getIslabNoAreaWise().equals("0"))
                                      runningLabNO  =objOnlinePatientAcceptanceDAO.checkAutoGenFormateRunningLabNo(voPatient, _userVO);
                                      
                                      if(voPatient.getIslabNoAreaWise().equals("1"))
                                      runningLabNO  =objOnlinePatientAcceptanceDAO.checkAutoGenFormateRunningLabNoAreaWise(voPatient, _userVO);
                                      
                                      sameLabCode=voPatient.getLabCode();
									 sameLabNo=runningLabNO;
									}
                                      voPatient.setRunningLabNo(sameLabNo);
									
									 if(voPatient.getRunningLabNo()!=null)
										{
										 String  sampleNO=voPatient.getRunningLabNo();
											int sampleNOLen=sampleNO.length();
											 int gnumnoofseqdigits=Integer.parseInt(voPatient.getNoOfSeqDigit());
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
									
									if(sequence_LabNO_yymmdd==null)
									 {
										FinalLabNo=xVal;
									 }
									 else
									 {
										 FinalLabNo=sequence_LabNO_yymmdd+xVal;
									 }
									
									
									 

									//For To Series Count
									String toSer=voPatient.getToSeries();
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
									 
									int xValToSerLen=xVal.length();
									int gnumnoofseqdigits2=Integer.parseInt(voPatient.getNoOfSeqDigit());
									 String subStrvalueLab2=xValToSer.substring(Math.max(0,xValToSerLen - gnumnoofseqdigits2));
									int toSerValue = Integer.parseInt(subStrvalueLab2);
									if(EntryDatecomparWithSysDAte<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
									{
										if(voPatient.getRunningLabNo()==null)
										{

											voPatient.setLabNoConfiguration(FinalLabNo);
										}
										else
										{

											if(fromSerValue<toSerValue)
											{
												String  LabNO=voPatient.getRunningLabNo();
												int LabNOlen=LabNO.length();
												 int gnumnoofseqdigits=Integer.parseInt(voPatient.getNoOfSeqDigit());
												 String subStrvalueLab=LabNO.substring(Math.max(0, LabNO.length() - gnumnoofseqdigits));
												// String constVAlue =LabNO.substring(subDateFormate.length(),LabNOlen-gnumnoofseqdigits);
												 
												 //if Date is not Available in Formate Case Modify On 22-06-2015
												 String constVAlue="";
												 try
												 {
													 constVAlue =LabNO.substring(subDateFormate.length(),LabNOlen-gnumnoofseqdigits);
												 }
												 catch (Exception e)
													{
													 constVAlue="";
													}
											 	int toSubStrValueLAb= Integer.parseInt(subStrvalueLab);
												++toSubStrValueLAb;
												int length = String.valueOf(toSubStrValueLAb).length();
												String leftPadded = StringUtils.leftPad("" + toSubStrValueLAb,subStrvalueLab.length(), "0");
												String finalLab="";
												if(sequence_LabNO_yymmdd==null)
												{
													finalLab=constVAlue+leftPadded;
												}
												else
												{
													finalLab=sequence_LabNO_yymmdd+constVAlue+leftPadded;
												}
												voPatient.setLabNoConfiguration(finalLab);
											}
											else  //for EntryDatecomparWithSysDAte value is Positive Case
											{
												voPatient.setLabNoConfiguration(FinalLabNo);
											}
										}
									}


									else 
									{
										
										voPatient.setLabNoConfiguration(FinalLabNo);
										
										
										objOnlinePatientAcceptanceDAO.updatePatientAccInhivtlabnoconfmstResetLabNO(voPatient,finalEntryDate,_userVO);
										Date todayDateob = new Date();
										SimpleDateFormat dateobj = new SimpleDateFormat("yyyy-MM-dd");
										String strSysDate= dateobj.format(todayDateob);
										Date todayDat =dateobj.parse(strSysDate) ; 
										Calendar c1 = Calendar.getInstance();
										c1.setTime(todayDat); // Now use SYDATE date.

										if(voPatient.getInitType().equals("m"))
										{
											int daysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
											  
											String[] SplitDate=strSysDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
											 todayDat=dateobj.parse(finalDate);
										}
										if(voPatient.getInitType().equals("d"))
										{
											todayDat=dateobj.parse(strSysDate);
										}
										if(voPatient.getInitType().equals("y"))
										{
											String[] SplitDate=strSysDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+12+"-"+31;
											 todayDat = dateobj.parse(finalDate);
										}
										if(voPatient.getInitType().equals("w"))
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
									objOnlinePatientAcceptanceDAO.updatePatientAccInhivtlabnoconfmst(voPatient,finalEntryDate,_userVO);
								
									//}
									//else
									//{
										
									//}
									
							//}//for Loop
									if(voPatient.getAccepted().equals("1"))
									{
										/* Added by Prashant */
										if(voPatient.getAcceptedToNotAccepted().equals("2")) {
										objOnlinePatientAcceptanceDAO.updatePatientAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());

										}
										else {
											objOnlinePatientAcceptanceDAO.insertPatientDtlForPatient(patAccNo,voPatient,  _userVO);
											voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
											objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
											listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());
										}
									}
									else
								{       /* Added by Prashant */
										if(voPatient.getAcceptedToNotAccepted().equals("2")) {
											objOnlinePatientAcceptanceDAO.updatePatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
										} else {
											objOnlinePatientAcceptanceDAO.insertPatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
										}
										
										

										if(voPatient.getRejectionAction().equals("1"))
										{
											
											
											if(voPatient.getRejectionReason().equals("-2"))
											{
												
												String maxsize=objOnlinePatientAcceptanceDAO.getmaxidreasonmstt(voPatient,  _userVO);
												voPatient.setRejectionReason(maxsize);
												objOnlinePatientAcceptanceDAO.insertreasonmst(voPatient, _userVO,"2",maxsize);
												
												
											}
											
											//calling refund functn chanksrefund
											refund(patAccNo,voPatient,  _userVO,keyy);
											
											
											
											
										    voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_CANCELLED); //9
											objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatient(voPatient,  _userVO);	 
											listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                 has been  Canceled");
										}	
										if(voPatient.getRejectionAction().equals("2"))
										{


											voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_RESCHEDULED); //10
											objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientRescheduled(voPatient,  _userVO);	 
											listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                  will be Re-Scheduled");
										}	

									}
								
							}//if loop
							else
							{

								if(voPatient.getAccepted().equals("1"))
								{
									/* Added by Prashant */
									if(voPatient.getAcceptedToNotAccepted().equals("2")) {
									objOnlinePatientAcceptanceDAO.updatePatientAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
									voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
									objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
									listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());

									}
									else {
										objOnlinePatientAcceptanceDAO.insertPatientDtlForPatient(patAccNo,voPatient,  _userVO);
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());
									}
									
								}
								else
								{ /* Added by Prashant */
									if(voPatient.getAcceptedToNotAccepted().equals("2")) {
										objOnlinePatientAcceptanceDAO.updatePatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
									} else {
										objOnlinePatientAcceptanceDAO.insertPatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
									}
									
									if(voPatient.getRejectionAction().equals("1"))
									{
										
										
										if(voPatient.getRejectionReason().equals("-2"))
										{
											
											String maxsize=objOnlinePatientAcceptanceDAO.getmaxidreasonmstt(voPatient,  _userVO);
											voPatient.setRejectionReason(maxsize);
											objOnlinePatientAcceptanceDAO.insertreasonmst(voPatient, _userVO,"2",maxsize);
											
											
										}
										
										//calling refund functn chanksrefund
										refund(patAccNo,voPatient,  _userVO,keyy); //refund prashant
										
										
										
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_CANCELLED); //9
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatient(voPatient,  _userVO);	 
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                 has been  Canceled");

									}	
									if(voPatient.getRejectionAction().equals("2"))
									{
										
										if(voPatient.getRejectionReason().equals("-2"))
										{
											
											String maxsize=objOnlinePatientAcceptanceDAO.getmaxidreasonmstt(voPatient,  _userVO);
											voPatient.setRejectionReason(maxsize);
											objOnlinePatientAcceptanceDAO.insertreasonmst(voPatient, _userVO,"2",maxsize);
											
											
										}
										
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_RESCHEDULED); //10
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientRescheduled(voPatient,  _userVO);	 
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                 will be Re-Scheduled");

									}	

								}


							}

//							String tariffId="";
//							String serviceId="";
//							//Update Billing
//							if(voPatient.getGroupType() == null)
//							{
//								tariffId = voPatient.getTestCode();
//								serviceId = "1";
//							}
//							else if(voPatient.getGroupType().equals("1") || voPatient.getGroupType().equals("2"))
//							{
//								tariffId = voPatient.getLabCode()+voPatient.getGroupCode();
//								serviceId = "4";
//							}  
//							else
//							{
//								tariffId = voPatient.getTestCode();
//								serviceId = "1";
//							}

							

						}

					}
				}
				//tx.commitAll()
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

	//START SAMPLE ACCEPTANCE PROCESS ADDED BY PATHAN BASHA

	public Map  sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List sampleAcceptanceLabCombo=new ArrayList();
		List machinelist=new ArrayList();
		try
		{
			tx.begin();
			SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			sampleAcceptanceLabCombo=sampleAcceptanceDaoi.sampleAcceptanceLabCombo(sampleAcceptanceVO,_UserVO);
			mp.put(InvestigationConfig.SAMPLE_ACCEPTANCE_LAB_COMBO, sampleAcceptanceLabCombo);


			machinelist=sampleAcceptanceDaoi.machinelist(sampleAcceptanceVO,_UserVO);
			mp.put(InvestigationConfig.MACHINE_LIST_ACCEPTANCE, machinelist);
			
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

	public Map setPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<OnlinePatientAcceptanceVO> lstOnlinePatientAcceptanceVO=new ArrayList<OnlinePatientAcceptanceVO>();


		try
		{
			tx.begin();
			OnlinePatientAcceptanceDAOi onlinePatientDaoi = new OnlinePatientAcceptanceDAO(tx);
			
			if(onlinePatientvo.getAcceptedToNotAccepted().equals("2")) {
			lstOnlinePatientAcceptanceVO=onlinePatientDaoi.setAcceptedPatientEssentials(onlinePatientvo, _UserVO);
			}else {
			lstOnlinePatientAcceptanceVO=onlinePatientDaoi.setPatientEssentials(onlinePatientvo, _UserVO);
			}

			mp.put(InvestigationConfig.LIST_EPISODE_VO,lstOnlinePatientAcceptanceVO);
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
	
	
	
	
	
	
	
	public Map setPatientEssentialsOnLoad(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<OnlinePatientAcceptanceVO> lstOnlinePatientAcceptanceVO=new ArrayList<OnlinePatientAcceptanceVO>();


		try
		{
			tx.begin();
			OnlinePatientAcceptanceDAOi onlinePatientDaoi = new OnlinePatientAcceptanceDAO(tx);

			lstOnlinePatientAcceptanceVO=onlinePatientDaoi.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);

			mp.put(InvestigationConfig.LIST_EPISODE_VO,lstOnlinePatientAcceptanceVO);
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
	
	
	
	
	
	
	
	
	
	public Map patientDetails(List<OnlinePatientAcceptanceVO> onlinePatientvoo,List<String> reqList, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<OnlinePatientAcceptanceVO> lstOnlinePatientAcceptanceVO=new ArrayList<OnlinePatientAcceptanceVO>();
		List<OnlinePatientAcceptanceVO> reqLis=new ArrayList<OnlinePatientAcceptanceVO>();
		String reqType="";
		List criteriacombo=new ArrayList();
		try
		{
			tx.begin();
			OnlinePatientAcceptanceDAO onlinePatientDao = new OnlinePatientAcceptanceDAO(tx);
			criteriacombo=onlinePatientDao.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.TEST_REASON_COMBO, criteriacombo);
			
			for(int l=0;l<onlinePatientvoo.size();l++)
			{
				OnlinePatientAcceptanceVO onlinePatientvo=onlinePatientvoo.get(l);
				
			
			
			if(onlinePatientvo.getPatStatus().equals("IPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_IPD;
			else if(onlinePatientvo.getPatStatus().equals("OPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_OPD; 
			else
				reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; 

		
			if(onlinePatientvo.getPatStatus().equals("IPD"))
				onlinePatientvo.setPatType("2");
			else
				onlinePatientvo.setPatType("1");
			
			
			
			OnlinePatientAcceptanceVO[] arrPatientCollectionVO=null;

			for(String str:reqList)
			{
				String reqNoo=str.split("#")[1]; //CRNO#ReqNo#index
				
				if(reqNoo.equalsIgnoreCase(onlinePatientvo.getPatReqNo()))
				{
				String reqNo=str.split("#")[1]; //CRNO#ReqNo#index
				/* Added by Prashant */
				String acceptedToNotAccepted =onlinePatientvo.getAcceptedToNotAccepted();
				
				boolean isBilled=false;
				List<OnlinePatientAcceptanceVO> lstTestBased=new ArrayList<OnlinePatientAcceptanceVO>();
				/* changed by Prashant */
				lstTestBased=onlinePatientDao.patientDetails(reqNo,reqType,acceptedToNotAccepted, _UserVO);

				if(lstTestBased!=null)
				{
					for(OnlinePatientAcceptanceVO voPatientCollection:lstTestBased)
					{
						voPatientCollection.setPatPuk(onlinePatientvo.getPatPuk());
						voPatientCollection.setPatType(onlinePatientvo.getPatType());
						voPatientCollection.setRequisitionNo(reqNo);
						voPatientCollection.setAcceptedToNotAccepted(onlinePatientvo.getAcceptedToNotAccepted());
						
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



	public boolean  checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		boolean isLabNoPresent = false;

		try
		{
			tx.begin();
			OnlinePatientAcceptanceDAO onlinePatientDao = new OnlinePatientAcceptanceDAO(tx);
			isLabNoPresent=onlinePatientDao.checkDailyLabNoDuplicacy(onlinePatientvo, _UserVO);
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
		return isLabNoPresent;
	}

	public String checkAutoGenFormate(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		List isFormatePresent=null;

		List<OnlinePatientAcceptanceVO> onlinepatientacceptancevo=null;
		
       String Formate="null";

      // String Formate="null+#+null+#+null+#+null+#+null+#+null+#+null";
       
		try {

			tx.begin();
			OnlinePatientAcceptanceDAO onlinePatientDao = new OnlinePatientAcceptanceDAO(tx);

			onlinepatientacceptancevo=onlinePatientDao.checkAutoGenFormateAreawise(onlinePatientvo, _UserVO);

			if(onlinepatientacceptancevo.size()==0)
			{
				
				onlinepatientacceptancevo=onlinePatientDao.checkAutoGenFormate(onlinePatientvo, _UserVO);
				
			}
			
			int size=onlinepatientacceptancevo.size();
			
			if(size!=0)
			{
				StringBuilder sb = new StringBuilder();
				//all but last
				for(OnlinePatientAcceptanceVO voSampleVo:onlinepatientacceptancevo ) {
					sb.append(voSampleVo.getLabNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningLabNo()+"#"+voSampleVo.getConfigLab()+"#"+voSampleVo.getConfigSeq()+"#"+voSampleVo.getConfigType()+"#"+voSampleVo.getConfigTest()+"#"+voSampleVo.getSampleAreaCode()+"#"+voSampleVo.getIslabNoAreaWise()  );
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
	
	
	
	
	
	public Map  LabCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();

		try
		{
			tx.begin();
			OnlinePatientAcceptanceDAOi onlinePatientDaoi = new OnlinePatientAcceptanceDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=onlinePatientDaoi.getLabCombos(onlinePatientvo,_UserVO);
			mp.put(InvestigationConfig.LAB_COMBO, labcombo);


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







	public Map getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();


		try
		{
			tx.begin();
			SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);

			lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetail(sampleAcceptanceVO, _UserVO);


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

			//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);
			mp.put(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO,objMapSamAcc);
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


	public Map getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();


		try
		{
			tx.begin();
			SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);

			lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetailOnLoad(sampleAcceptanceVO, _UserVO);


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

			//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);
			
			
			mp.put(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO,objMapSamAcc);
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



	public Map SampleAcceptanceRejectionCom(SampleAcceptanceVO sampleAcceptanceVO,List<String> reqList, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<SampleAcceptanceVO> lstsampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();

		String reqType="";
		List rejectionReasoncombo=new ArrayList();
		try
		{
			tx.begin();

			SampleAcceptanceDAO sampleAcceptanceDao = new SampleAcceptanceDAO(tx);
			rejectionReasoncombo=sampleAcceptanceDao.getSampleAcceptanceRejectionCombo( _UserVO);
			mp.put(InvestigationConfig.TEST_REJECTION_REASON_COMBO, rejectionReasoncombo);

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

	public Map SampleAcceptanceRejectionCombo(SampleAcceptanceVO sampleAcceptanceVO,List<String> reqList, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<SampleAcceptanceVO> lstsampleAcceptance=new ArrayList<SampleAcceptanceVO>();
		SampleAcceptanceVO vo=new SampleAcceptanceVO();
		Map mp=new HashMap();

		try {

			tx.begin();

			SampleAcceptanceDAO sampleAcceptanceDao = new SampleAcceptanceDAO(tx);

			String sampleRejectnComboStr="";
			List  lstsampleAcceptanceCombo=sampleAcceptanceDao.getSampleAcceptanceRejectionCombo( _UserVO);
			if(lstsampleAcceptanceCombo!=null&&lstsampleAcceptanceCombo.size()>0)
			{
				Iterator lstIterator=lstsampleAcceptanceCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					sampleRejectnComboStr=sampleRejectnComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
			}
			mp.put(InvestigationConfig.TEST_REJECTION_REASON_COMBO,sampleRejectnComboStr);


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

	public boolean  checkDailyLabNoDuplicacyforSampleAcc(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		boolean isLabNoPresent = false;

		try
		{
			tx.begin();
			SampleAcceptanceDAO sampleDao = new SampleAcceptanceDAO(tx);
			isLabNoPresent=sampleDao.checkDailyLabNoDuplicacyforSampleAcc(sampleAcceptanceVO, _UserVO);
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
		return isLabNoPresent;
	}

	public  Map saveSampleAccDetails(List<SampleAcceptanceVO> voSampleAcc,UserVO _userVO,HttpServletRequest _request)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		
		List   listSampleAccepted =new ArrayList();
		List   listSampleNotAccepted =new ArrayList();
		List   listSampleNotReceived =new ArrayList();

		boolean isComplete=true;
		List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();
		List<SampleAcceptanceVO> lstAutoLabNOConfig=new ArrayList<SampleAcceptanceVO>();
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
			SampleAcceptanceVO voSampl=new SampleAcceptanceVO();

			String sameSampleNO="";
			String keyy="";
			for(SampleAcceptanceVO voSamAcc:voSampleAcc)
			{
				
				
				//Logic For Auto Lab No Generation
				
				lstAutoLabNOConfig=objSampleAcceptanceDAO.getSampleAcceptanceAutoLabNOConfig(voSamAcc, _userVO);
				
				int lstofSize=lstAutoLabNOConfig.size();
				
				
				if(voSamAcc.getCheckLabConfigForAutoGen().equals("2")&&lstofSize!=0)
				{
                         
					
					
					for(SampleAcceptanceVO autoLabVo:lstAutoLabNOConfig)
					{
						System.out.println("Size of the auto list is : "+lstAutoLabNOConfig.size()+" and the aulab no is : "+autoLabVo.getRunningLabNo());
					  
						voSamAcc.setConfigSeq(autoLabVo.getConfigSeq());
						voSamAcc.setConfigTest(autoLabVo.getTestCode());
						voSamAcc.setConfigType(autoLabVo.getPatType());
						
						if(sameSampleNO.equals(voSamAcc.getTempSampleNo()))
						{
							
							if(!autoLabVo.getLabNoFormat().equals("-"))
							voSamAcc.setLabNoConfiguration(autoLabVo.getRunningLabNo());
							else
								voSamAcc.setLabNoConfiguration(sameSampleNO);
							
						}
						else
						{
						if(!autoLabVo.getLabNoFormat().equals("-"))
						{
						String Str=autoLabVo.getLabNoFormat();
						//  String Str = new String(autoLabNumber);
						int MainStrlength=Str.length();
						String[] dateFormate = Str.split("&");
						String subDateFormate=dateFormate[0];
						String xMainValue=dateFormate[1];
						//getting the DateFormate number
						String sequence_LabNO_yymmdd=objSampleAcceptanceDAO.generateLabNoDateSequence(subDateFormate, _userVO);  // Returns   yymmdd
						String entryDate= autoLabVo.getInitDate();//get the Entry Date 
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date date = df.parse(entryDate);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						c.setTime(date); // Added a initDate date. to Calender
						String finalDate="";
						
						if(autoLabVo.getInitType().equals("m"))
						{
							
							int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
							  
							String[] SplitDate=entryDate.split("-");
							
							 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
							 date=df.parse(finalDate);
							 
						}
						if(autoLabVo.getInitType().equals("d"))
						{
							
							date = df.parse(entryDate);
							//c.add(Calendar.DATE,1); 
						}
						if(autoLabVo.getInitType().equals("y"))
						{
							String[] SplitDate=entryDate.split("-");
							
							 finalDate=SplitDate[0]+"-"+12+"-"+31;
							date = df.parse(finalDate);
						}
						if(autoLabVo.getInitType().equals("w"))
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
						String FinalLabNo="";
						int fromSerValue;
						  
						String fromSer=autoLabVo.getFromSeries();
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
						int gnumnoofseqdigits1=Integer.parseInt(autoLabVo.getNoOfSeqDigit());
						 String subStrvalueLab1=xVal.substring(Math.max(0,xValLen - gnumnoofseqdigits1));
						 
						 if(autoLabVo.getRunningLabNo()!=null)
							{
							 String  sampleNO=autoLabVo.getRunningLabNo();
								int sampleNOLen=sampleNO.length();
								 int gnumnoofseqdigits=Integer.parseInt(autoLabVo.getNoOfSeqDigit());
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
						
						if(sequence_LabNO_yymmdd==null)
						 {
							FinalLabNo=xVal;
						 }
						 else
						 {
							 FinalLabNo=sequence_LabNO_yymmdd+xVal;
						 }
						 
						 
						//For To Series Count
						String toSer=autoLabVo.getToSeries();
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
						int gnumnoofseqdigits2=Integer.parseInt(autoLabVo.getNoOfSeqDigit());
						 String subStrvalueLab2=xValToSer.substring(Math.max(0,xValToSerLen - gnumnoofseqdigits2));
						int toSerValue = Integer.parseInt(subStrvalueLab2);

						if(EntryDatecomparWithSysDAte<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
						{	 

							if(autoLabVo.getRunningLabNo()==null)
							{

								voSamAcc.setLabNoConfiguration(FinalLabNo);
							}
							else
							{

								if(fromSerValue<toSerValue)
								{
									String  LabNO=autoLabVo.getRunningLabNo();
									int LabNOlen=LabNO.length();
									 int gnumnoofseqdigits=Integer.parseInt(autoLabVo.getNoOfSeqDigit());
									 String subStrvalueLab=LabNO.substring(Math.max(0, LabNO.length() - gnumnoofseqdigits));
									 //String constVAlue =LabNO.substring(subDateFormate.length(),LabNOlen-gnumnoofseqdigits);
								 	
									 
									 //if Date is not Available in Formate Case Modify On 22-06-2015
									 String constVAlue="";
									 try
									 {
									  constVAlue =LabNO.substring(subDateFormate.length(),LabNOlen-gnumnoofseqdigits);
									 }
									 catch (Exception e)
										{
										 constVAlue="";
										}
									 
									 int toSubStrValueLAb= Integer.parseInt(subStrvalueLab);
									++toSubStrValueLAb;
									int length = String.valueOf(toSubStrValueLAb).length();
									String leftPadded = StringUtils.leftPad("" + toSubStrValueLAb,subStrvalueLab.length(), "0");
									
									String finalLab="";
											
									if(sequence_LabNO_yymmdd==null)
									{
										
										finalLab=constVAlue+leftPadded;
									}
									else
									{
										finalLab=sequence_LabNO_yymmdd+constVAlue+leftPadded;
									}
									voSamAcc.setLabNoConfiguration(finalLab);
								}
								else
								{
									voSamAcc.setLabNoConfiguration(FinalLabNo);
								}
							}

						}

						else  ////for EntryDatecomparWithSysDAte value is Positive
						{
							voSamAcc.setLabNoConfiguration(FinalLabNo); 
							
						
							
							objSampleAcceptanceDAO.updateSampleAccInhivtlabnoconfmstresettheLabNo(voSamAcc,finalEntryDate,_userVO);
							Date todayDateob = new Date();
							SimpleDateFormat dateobj = new SimpleDateFormat("yyyy-MM-dd");
							String strSysDate= dateob.format(todayDateob);
							Date todayDat =dateobj.parse(strSysDate) ; 
							Calendar c1 = Calendar.getInstance();
							c1.setTime(todayDat); // Now use SYDATE date.

							if(autoLabVo.getInitType().equals("m"))
							{
								int daysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
								  
								String[] SplitDate=strSysDate.split("-");
								
								 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
								 todayDat=dateobj.parse(finalDate);
							}
							if(autoLabVo.getInitType().equals("d"))
							{
								todayDat=dateobj.parse(strSysDate);
							}
							if(autoLabVo.getInitType().equals("y"))
							{
								String[] SplitDate=strSysDate.split("-");
								
								 finalDate=SplitDate[0]+"-"+12+"-"+31;
								 todayDat = dateobj.parse(finalDate);
							}
							if(autoLabVo.getInitType().equals("w"))
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
						objSampleAcceptanceDAO.updateSampleAccInhivtlabnoconfmst(voSamAcc,finalEntryDate,_userVO);
					
					}
					else{
						voSamAcc.setLabNoConfiguration(voSamAcc.getTempSampleNo());
					}
						sameSampleNO=voSamAcc.getTempSampleNo();
					}
				}
					objSampleAcceptanceDAO.updateSampleAccInRequisitionDtl(voSamAcc,_userVO);
					objSampleAcceptanceDAO.updateSampleAccInSampleDtl(voSamAcc,_userVO);
					if(voSamAcc.getTestBasedMachine()!=null)
					if(!voSamAcc.getTestBasedMachine().equals("-1"))
						{
						
						objSampleAcceptanceDAO.saveSampleDetail(voSamAcc,_userVO);
						objSampleAcceptanceDAO.updateRequestId(voSamAcc,_userVO);
						
						}
					sameSampleNO=voSamAcc.getTempSampleNo();
				}

				else
				{
					objSampleAcceptanceDAO.updateSampleAccInRequisitionDtl(voSamAcc,_userVO);
					objSampleAcceptanceDAO.updateSampleAccInSampleDtl(voSamAcc,_userVO);
					if(voSamAcc.getTestBasedMachine()!=null)
					if(!voSamAcc.getTestBasedMachine().equals("-1"))
						{
						
						//objSampleAcceptanceDAO.saveSampleDetail(voSamAcc,_userVO);

						//changed by chandan as in case if lab no generate manually save sample no in machine sample dtl instead of lab no
						
						objSampleAcceptanceDAO.saveSampleDetaillabcase(voSamAcc,_userVO);
						objSampleAcceptanceDAO.updateRequestId(voSamAcc,_userVO);

						}
					
			
					
					
				}
				if(voSamAcc.getRecieved().equals(InvestigationConfig.RECIEVED_STATUS))

				{
					if(voSamAcc.getAccepted().equals(InvestigationConfig.ACCPETED_STATUS))//1
					{

						listSampleAccepted.add("Packing List No= "+voSamAcc.getPackingListNO()+"        ,Lab Name="+voSamAcc.getLabName()+"      ,Lab NO="+voSamAcc.getLabNoConfiguration()+"			,Test Name ="+voSamAcc.getTestName());

					}
					else
					{

						listSampleNotAccepted.add("Packing List No= "+voSamAcc.getPackingListNO()+"        ,Lab Name="+voSamAcc.getLabName()+"      ,Test Name ="+voSamAcc.getTestName());
	}

				}
				else
				{

					listSampleNotReceived.add("Packing List No= "+voSamAcc.getPackingListNO()+"       ,Lab Name="+voSamAcc.getLabName()+"      ,Test Name ="+voSamAcc.getTestName());

				}
				
				//	added by chandan for refund
				
				
				String requisitionNumber=voSamAcc.getRequisitionNo();
				
				InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();   
				
				
				//added parameters value by chandan
				
				if(voSamAcc.getShortSampleName().equalsIgnoreCase("bld"))
				{
					
					/*voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"|BLD!");*/
					voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");
				}
				else
				{
					
				   /* voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");*/
					 voSamAcc.setRequisitionNo(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");
				
				}
				//	voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
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
				// added by chandan for refund sample acc
			
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
					
					}
			}
			
			//Put List in Map
			mp.put(InvestigationConfig.LIST_ACCPETED_STATUS, listSampleAccepted);
			mp.put(InvestigationConfig.LIST_NOT_ACCPETED_STATUS, listSampleNotAccepted);
			mp.put(InvestigationConfig.LIST_NOT_RECIEVED_STATUS, listSampleNotReceived);

			SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);
			SampleAcceptanceVO vo=new SampleAcceptanceVO();
			//Logic For Update Status In Packing List Dtl Table
			
			
			for(SampleAcceptanceVO voSamAccForCheckPackNo:voSampleAcc)
			{
				String isexist=sampleAcceptanceDaoi.getSampleAcceptanceDetailForCheckPackNo(voSamAccForCheckPackNo, _userVO);
				
				
				/*String strPackingList=voSamAccForCheckPackNo.getPackingListNO();
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
				}*/	 
				
				if(isexist.equals("0"))
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

//Start a Result Entry Process Added By Basha on 20-04-2015
		
	public Map  LabComboForResultEntry(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();

		try
		{
			tx.begin();
			InvResultEntryDAO onlinePatientDao = new InvResultEntryDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=onlinePatientDao.LabComboForResultEntry(invresultentryvo,_UserVO);
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
	
	
	
	
	public Map  showCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		String cannedDetail="";
		List labcombo=new ArrayList();
		List<ResultEntryVO> lstCannedDetails=null;
		try
		{
			tx.begin();
			InvResultEntryDAO onlinePatientDao = new InvResultEntryDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			lstCannedDetails=onlinePatientDao.showCannedDetails(labCode,cannedMacroCheck,_UserVO);
			
			
			 for(ResultEntryVO vo:lstCannedDetails)
			  {
				if(cannedMacroCheck.equals("CANNED"))
				 cannedDetail+=vo.getUserCannedCode()+"#"+vo.getCannedContent()+"@";  
				if(cannedMacroCheck.equals("MACRO"))
					 cannedDetail+=vo.getUserCannedCode()+"#"+vo.getMacroText()+"@";  
					
				  	 
			  }
			  
			
			mp.put(InvestigationConfig.LIST_CANNED_FILE_DETAILS, cannedDetail);


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
	public Map setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
		List<ResultEntryVO> groupModified_lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
		List labNoCombo=new ArrayList();
		List testCombo=new ArrayList();
		List sampleNoCombo=new ArrayList();
		List testGroupCombo=new ArrayList();
		List groupCode=new ArrayList();
		List employeeNameCombo =new ArrayList();
		
		 
		try
		{
			tx.begin();
			InvResultEntryDAO invresultentrydao = new InvResultEntryDAO(tx);

			
			lstInvResultEntryVO=invresultentrydao.setPatientResultEntryEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultEntryVO);
			
			
			for(ResultEntryVO tempVo:lstInvResultEntryVO)
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

			labNoCombo=invresultentrydao.setLabNoComboEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,labNoCombo);
			  
			testCombo=invresultentrydao.setTestComboEssentials(invresultentryvo, _UserVO);
			 
			mp.put(InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY,testCombo);
			
			sampleNoCombo=invresultentrydao.setSamplNoComboEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
			
			testGroupCombo=invresultentrydao.setTestGroupComboEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY,testGroupCombo);
			
			employeeNameCombo=invresultentrydao.setEmployeeNameComboEssentials(invresultentryvo, _UserVO);
			
			//mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,employeeNameCombo);
			mp.put(InvestigationConfig.EMP_NAME_COMBO_FOR_RESULT_ENTRY,employeeNameCombo);
			
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
	
	/**
	public Map ResultEntryPatientDetails(InvResultEntryVO invresultentryvo,List<String> reqList, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultEntryVO> lstInvResultEntryVO=new ArrayList<InvResultEntryVO>();
		List<InvResultEntryVO> reqLis=new ArrayList<InvResultEntryVO>();
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
	
	public  Map saveResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultEntryFB fb)
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
				objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
				  
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
                            	 
                             
				  objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
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
                         	 
                          
				  objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
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
	public Map  getTestComboAJAXMAP(String labCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String labNames="";
		String testNames="";
		String testGroupNames="";
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			//List lstLabNames=invEssentialDAO.getLabNames(userVO);
			List lstTestNames=invEssentialDAO.getTestNamesUsingAJAX(labCode, userVO);
			//List lstTestGroupNames=invEssentialDAO.getTestGroupNames(userVO);

			//if(lstLabNames!=null)
			//{
			//	StringBuilder sb = new StringBuilder();

				// all but last
			//	for(int i = 0; i < lstLabNames.size() - 1 ; i++) {
			//		sb.append("{ label: \""+((Entry)lstLabNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstLabNames.get(i)).getValue()+"\" }");
			////		sb.append(",");
			//	}

				// last string, no separator
			//	if(lstLabNames.size() > 0){
			//		sb.append("{ label: \""+((Entry)lstLabNames.get(lstLabNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstLabNames.get(lstLabNames.size()-1)).getValue()+"\" }");
			//	}

			//	labNames="["+sb.toString()+"]";
			//}


			if(lstTestNames!=null)
			{
				StringBuilder sb = new StringBuilder();

	//  [{ label: "Acid Phosphate" ,  value: "10001" },{ label: "Protein S" ,  value: "10004" }]
		
	//[{"label":"Jean","value":1},{"label":"carl","value":2}]			
				for(int i = 0; i < lstTestNames.size() - 1 ; i++) {
					sb.append("{\"label\":\""+((Entry)lstTestNames.get(i)).getLabel()+"\",\"value\":\""+((Entry)lstTestNames.get(i)).getValue()+"\"}");
					
					
					sb.append(",");
				}

				// last string, no separator
				if(lstTestNames.size() > 0){
					sb.append("{\"label\":\""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getLabel()+"\",\"value\":\""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getValue()+"\"}");
				}

				testNames="["+sb.toString()+"]";
				System.out.println("the value is of TEST"+testNames);
			}


			//if(lstTestGroupNames!=null)
		//	{
			//	StringBuilder sb = new StringBuilder();

				// all but last
			//	for(int i = 0; i < lstTestGroupNames.size() - 1 ; i++) {
			//		sb.append("{ label: \""+((Entry)lstTestGroupNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestGroupNames.get(i)).getValue()+"\" }");
			//		sb.append(",");
			//	}

				// last string, no separator
			//	if(lstTestGroupNames.size() > 0){
			//		sb.append("{ label: \""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getValue()+"\" }");
			//	}

			////	testGroupNames="["+sb.toString()+"]";
			///}

			//mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);

			mp.put(InvestigationConfig.ARRAY_TESTNAMES_AJAX, testNames);

			//mp.put(InvestigationConfig.ARRAY_TESTGROUPNAMES, testGroupNames);

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
	
	//Start a Result Validation Process Puneet
	
		public Map  LabComboForResultValidation(ResultEntryVO invresultentryvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List labcombo=new ArrayList();
			List sampleNoCombo=new ArrayList();

			try
			{
				tx.begin();
				InvResultValidationDAO onlinePatientDao = new InvResultValidationDAO(tx);
				invReportAddendumDAO dao=new invReportAddendumDAO(tx);
				//labMstDAOi.fetchLab(labMasterVO, _UserVO);

				labcombo=onlinePatientDao.LabComboForResultValidation(invresultentryvo,_UserVO);
/*				sampleNoCombo=dao.setSamplNoComboEssentials(invresultentryvo, _UserVO);

				mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
				*/
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
		
		
		
		public Map getResultEntryData(ResultEntryVO invresultentryvo)
		{

			List<TriStringObject> resultValidationDataList = null;
			List<ResultEntryVO> hyperlist = null;
			List<invAntibioticProcessVO> antibioticlist = null;
			Map mp = null;
			Map mp1 = null;
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try {
				tx.begin();
				
				InvResultValidationDAO resultEntryDetailDAO = new InvResultValidationDAO(
						tx);
				
				if(invresultentryvo.getParaType()!=null /*|| invresultentryvo.getParaType().equals("2")*/)  // for requisition form modified by chandan
				{
					if(invresultentryvo.getRequisitionDNo()!=null)
					resultValidationDataList = resultEntryDetailDAO
							.getRequisiitionDataList(invresultentryvo);
						
				}
				else
				resultValidationDataList = resultEntryDetailDAO
						.getResultValidfationDataList(invresultentryvo);
				
			
				mp = new HashMap(4);

				System.out.println("resultEntryVO.getTestDocument()"
						+ invresultentryvo.getTestDocument());
				if (invresultentryvo.getTestDocument() != null) {
					XPathFactory factory = XPathFactory.newInstance();
					XPath xpath = factory.newXPath();
					String xQuery = "/test/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@idC = 'hyperlink'][@callUrl='sssfsdf' or @callUrl='/HISInvestigationG5/new_investigation/antibioticprocesss.cnt']";
					System.out.println("xQuery  :: " + xQuery);
					XPathExpression expr = xpath.compile(xQuery);
					Object result = expr.evaluate(invresultentryvo.getTestDocument(),
							XPathConstants.NODESET);
					NodeList nodes = (NodeList) result;
					
					
					Node node=nodes.item(0);
				/*	for(int i=0;i<nodes.getLength();i++)
					{
					    Node node=nodes.item(i);
					    if(node.getAttributes().getNamedItem("callUrl").getNodeValue().equals("/HISInvestigationG5/new_investigation/antibioticprocesss.cnt"))
					    {*/
					
					//saved list based on req d no and test para code
					    	hyperlist = resultEntryDetailDAO.gethyperdata(invresultentryvo,node);
					    	
					    	System.out.println("hyperlistsize"+hyperlist.size());
					    	
					    	if(hyperlist.size()>0)
					    	{
					    		
					    	
					    	ResultEntryVO tempvo=hyperlist.get(0);
					    	String orgCode=tempvo.getOrganismcode();
					    	String reqdno=tempvo.getRequisitionDNo();
					    	String tempparacode=tempvo.getTestParaMeterCode();
					    		
					    	if(orgCode!=null)
					    	{
					    	antibioticlist=resultEntryDetailDAO.getantibioticlist(invresultentryvo,orgCode,tempvo,reqdno,tempparacode);
					    	mp.put("antibioticlistt", antibioticlist);
					    	}
					    		}
					    	
					    /*}*/
					    
					System.out.println(nodes.getLength());

					/*}*/
				}

				mp.put("resultValidationDataList", resultValidationDataList);
				mp.put("hyperlinkdatalist", hyperlist);
				
	
				
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
			return mp;
		
		}

		
		
		
		public Map setPatientResultValidationEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<ResultEntryVO> lstInvResultValidationVO=new ArrayList<ResultEntryVO>();
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
				InvResultValidationDAO invresultentrydao = new InvResultValidationDAO(tx);

				lstInvResultValidationVO=invresultentrydao.setPatientResultValidationEssentials(invresultentryvo, _UserVO);
				
				
				Map<String,List<ResultEntryVO>> crnomap=new HashMap<String,List<ResultEntryVO>>();
				
				List<ResultEntryVO> crnolist= null;
				
				int size1=0;
		 		if(lstInvResultValidationVO!=null && lstInvResultValidationVO.size()>0 )
		 		{
		 			//size=lstPatVO11.size();
		 		

						
		 			for(int l=0;l<lstInvResultValidationVO.size();l++)
		 			{
		 				ResultEntryVO vo=lstInvResultValidationVO.get(l);
		 				
		 				String patcrnoo="";
		 				
		 				patcrnoo=vo.getPatCRNo();
		 				
		 				
		 				if(crnomap.size()>0)
		 				{
		 					
		 					if(crnomap.containsKey(patcrnoo))
		 					{
		 						List<ResultEntryVO> voo= crnomap.get(patcrnoo);
		 						voo.add(vo);
		 						crnomap.put(patcrnoo, voo);
		 					}
		 					else
		 					{
		 						crnolist= new ArrayList<ResultEntryVO>();
			 					crnolist.add(vo);
			 					
			 					crnomap.put(patcrnoo, crnolist);
		 						
		 					}
		 					
		 					
		 				}
		 				else
		 				{
		 				
		 					crnolist= new ArrayList<ResultEntryVO>();
		 					crnolist.add(vo);
		 					
		 					crnomap.put(patcrnoo, crnolist);
		 					
		 				}
		 				
		 			}
		 		
		 		}
		 		
				List<ResultEntryVO> lstSampleAcceptanceVO1=new ArrayList<ResultEntryVO>();

				
		 		if(crnomap.size()>0)
		 		{
					 Iterator itr=crnomap.keySet().iterator();
		              
					 while(itr.hasNext() )
						{
						      String crnoo=(String)itr.next();
							List<ResultEntryVO> lstSampleAcceptanceVO=crnomap.get(crnoo);
							ResultEntryVO vo=lstSampleAcceptanceVO.get(0);
							lstSampleAcceptanceVO1.add(vo);
						}
		 		}
				
				
				//////logic to group by groupcode /////
				
				for(int i=0;i<lstInvResultValidationVO.size();i++)
				{
					String concatString=lstInvResultValidationVO.get(i).getTestParameterName();
					String testCode=lstInvResultValidationVO.get(i).getTestCode();
					
					String properParaValues="";
					
					String[] parameters = null;
					int paraSize = 0;
//					if(concatString != null)
//					{
					if(concatString==null)
					{
						System.out.println("nullll::::"+lstInvResultValidationVO.get(i).getRequisitionDNo());
					}
					
						parameters =concatString.split("`");
						paraSize=parameters.length;
					//}
//					else
//					{
//						paraSize=0;
//						continue;
//					}
					
					
					for(int iterate=0;iterate<paraSize;iterate++)
					{
						
						System.out.println("DNO ===== "+lstInvResultValidationVO.get(i).getRequisitionDNo());
						
						String[] paraValues=parameters[iterate].split("#@");
						String paraCode=paraValues[0];
						System.out.println("DNO paracode ===== "+paraCode);
						String paraName=paraValues[1];
						//String paraEntry=paraValues[3];
						
						String paraEntry="";
						if(paraValues.length>3)
						 paraEntry=paraValues[3];
						else
							paraEntry="";
						
						
						String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, lstInvResultValidationVO.get(i).getPatGender(),lstInvResultValidationVO.get(i).getPatAge()); 
								////////				lstInvResultValidationVO.get(i).getPatAge());
                                           //val+=refRange+"`";
								String rangeValue=refRange;//paraValues[2];
						
						//String rangeValue="";
						//if(paraValues.length>2)
						// rangeValue=paraValues[2];
						
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
						//2368#@Hemoglobin#@1$170$130$g/l$g/l$null#@12#@
							//properParaValues+=parameters[iterate]+"`";
						
						
						
					}
					
			
					properParaValues=properParaValues.substring(0, properParaValues.length()-1);
					lstInvResultValidationVO.get(i).setTestParameterName(properParaValues);
					
					//to append para values for all group values
					if(lstInvResultValidationVO.get(i).getGroupCode()!=null)
					{
						
						if(!groupCode.contains(lstInvResultValidationVO.get(i).getGroupCode()+lstInvResultValidationVO.get(i).getRequisitionNo()))
						{
						for(int j=i+1;j<lstInvResultValidationVO.size();j++)
						{
							if(lstInvResultValidationVO.get(j).getGroupCode()!=null)
							if(lstInvResultValidationVO.get(i).getGroupCode().equals(lstInvResultValidationVO.get(j).getGroupCode())  && lstInvResultValidationVO.get(i).getRequisitionNo().equals(lstInvResultValidationVO.get(j).getRequisitionNo()))
							{	
							String temp_concatString=lstInvResultValidationVO.get(j).getTestParameterName();
							String temp_testCode=lstInvResultValidationVO.get(j).getTestCode();
							
							String temp_properParaValues="";
							
							String[] temp_parameters = null;
							int temp_paraSize = 0;
//							if(concatString != null)
//							{
								temp_parameters =temp_concatString.split("`");
								temp_paraSize=temp_parameters.length;
							//}
//							else
//							{
//								paraSize=0;
//								continue;
//							}
							
							
							for(int iterate=0;iterate<temp_paraSize;iterate++)
							{
								String[] paraValues=temp_parameters[iterate].split("#@");
								String paraCode=paraValues[0];
								String paraName=paraValues[1];
								
								
								String paraEntry="";
							    
								if(paraValues.length>3)
								paraEntry=	paraValues[3];
								
								
								String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, temp_testCode, paraCode, lstInvResultValidationVO.get(j).getPatGender(),lstInvResultValidationVO.get(j).getPatAge()); 
								
								
								String rangeValue=refRange;
								if(paraValues.length>2)
									rangeValue=paraValues[2];
								
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
							lstInvResultValidationVO.get(j).setTestParameterName(temp_properParaValues);
							
							
							lstInvResultValidationVO.get(i).setTestParameterName(lstInvResultValidationVO.get(i).getTestParameterName()+"`"+temp_properParaValues);
						}
						}
							groupCode.add(lstInvResultValidationVO.get(i).getGroupCode()+lstInvResultValidationVO.get(i).getRequisitionNo());
							groupModified_lstInvResultEntryVO.add(lstInvResultValidationVO.get(i));
						}
						else
						{;}
						
					}
					else
					{
						groupModified_lstInvResultEntryVO.add(lstInvResultValidationVO.get(i));
					}
					
					
				
					
					
				}
				
	mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultEntryVO);
	mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO1,lstSampleAcceptanceVO1);

	
				
				//////////////////////////////////////
				
				//////////////////////////////logic to fetch combo value name of the tests//////////////////////////
				
//				for(int i=0;i<lstInvResultValidationVO.size();i++)
//				{
//					String concatString=lstInvResultValidationVO.get(i).getTestParameterName();
//					String testCode=lstInvResultValidationVO.get(i).getTestCode();
//					
//					String properParaValues="";
//					
//					String[] parameters = null;
//					int paraSize = 0;
////	not req				if(concatString != null)
////	not req				{
//						parameters =concatString.split("`");
//						paraSize=parameters.length;
//					//not req}
////	not req				else
////	not req				{
////	not req					paraSize=0;
////	not req					continue;
////	not req				}
//					
//					
//					for(int iterate=0;iterate<paraSize;iterate++)
//					{
//						String[] paraValues=parameters[iterate].split("#");
//						String paraCode=paraValues[0];
//						String paraName=paraValues[1];
//						String paraEntry=paraValues[3];
//						String rangeValue=paraValues[2];
//						String comboValue="";
//						if(paraEntry.contains("$"))
//						{
//							String[] multiValue=paraEntry.split("\\$");
//							
//							for(int k=0;k<multiValue.length;k++)
//							comboValue+=invresultentrydao.setComboValueName(testCode,paraCode,multiValue[k])+"$";
//							
//						}
//						else				
//						 comboValue=invresultentrydao.setComboValueName(testCode,paraCode,paraEntry);
//					 
//						if(comboValue!=null)
//							properParaValues+=paraCode+"#"+paraName+"#"+rangeValue+"#"+comboValue+"`";
//						else
//							properParaValues+=parameters[iterate]+"`";
//						
//						
//						
//					}
//					
//			
//					properParaValues=properParaValues.substring(0, properParaValues.length()-1);
//					lstInvResultValidationVO.get(i).setTestParameterName(properParaValues);
//				}
				
				////////////////////////////logic to fetch combo value name of the tests ENDS//////////////////////////
				
				
				
				
				
				
				
				
				
				
				mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationVO);
				
				
			
							
							
				InvResultValidationDAO invresultValdao = new InvResultValidationDAO(tx);
				labNoCombo=invresultValdao.setLabNoComboEssentials(invresultentryvo, _UserVO);

				mp.put(InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY,labNoCombo);
				  
					testCombo=invresultValdao.setTestComboEssentials(invresultentryvo, _UserVO);
				 
				mp.put(InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY,testCombo);
				
				sampleNoCombo=invresultValdao.setSamplNoComboEssentials(invresultentryvo, _UserVO);

				mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
				
				testGroupCombo=invresultValdao.setTestGroupComboEssentials(invresultentryvo, _UserVO);

				mp.put(InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY,testGroupCombo);
				
				
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
		
		/**
		public Map ResultValidationPatientDetails(InvResultValidationVO invresultentryvo,List<String> reqList, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<InvResultValidationVO> lstInvResultValidationVO=new ArrayList<InvResultValidationVO>();
			List<InvResultValidationVO> reqLis=new ArrayList<InvResultValidationVO>();
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
		
		public  Map saveResultValidationDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb)
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
				InvResultValidationDAO objResultEntrtyDAO=new InvResultValidationDAO(tx);
				
				resultEntryVO=invResultentryVO.get(0);
				if(resultEntryVO.getCrNoReqNoString()!=null)
				for(int i =0;i<resultEntryVO.getCrNoReqNoString().length;i++)
				{				
					objResultEntrtyDAO.updateFinalRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], _userVO);				
				}
				
				if(resultEntryVO.getCrNoReqNoStringAddendum()!=null)
					for(int i =0;i<resultEntryVO.getCrNoReqNoStringAddendum().length;i++)
					{				
						objResultEntrtyDAO.updateAddendumRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoStringAddendum()[i].split("#")[1], resultEntryVO.getAddendumRemark()[i], _userVO);				
					}
				
				
				
				
				for(ResultEntryVO voInvResultValidation:invResultentryVO)
				{
					if(fb.getIsSaveToDraft()!=null && fb.getIsSaveToDraft().equals("1")){
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS_SAVE_TO_DRAFT);
					}else{
						if(voInvResultValidation.getSaveReVal().equals("1")){//check to send test for reval
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
						}
						else if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION)){
							voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
						}
						else				
						{
							voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
						}
					}
					
					
					
					
				
					
				
					objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
				    
					//added by prashant
					objResultEntrtyDAO.updateIndicationInRequisitionheader(voInvResultValidation,_userVO);
					objResultEntrtyDAO.commentsupdateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
					
					if(voInvResultValidation.getIsSaveToDraft()!=null && voInvResultValidation.getIsSaveToDraft().equals("1")){
						objResultEntrtyDAO.updateResultValidationInRequisitionDtlDraftLog(voInvResultValidation,_userVO);
					}
					
					if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
					{
						if(voInvResultValidation.getIsSaveToDraft()!=null && voInvResultValidation.getIsSaveToDraft().equals("1")){
							
						}else{
							objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
						}
					}		
					
				
				
				
				}
				
				/*for(ResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
				{
					objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
					objResultEntrtyDAO.insertResultLogDtl(voInvResulValidationForParaMeterDtl,_userVO);
					//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
				}*/	 
				
				
				/*for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultValidationForParameterDtlVO)
				{
					int count=1;
					String parentid=voInvResulEntryForParaMeterDtl.getParantParaCode();
					
					String Ishyperlink="";
					Ishyperlink=voInvResulEntryForParaMeterDtl.getIsHyperLink();
					 if(Ishyperlink.equals("-"))
					 {
						 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
					 }
					 else
					 {
						
						
								if(!fb.getSelectValuemapping().equals("null"))
								{
							  String[] val=fb.getSelectValuemapping().split("\\$\\$\\$");
							  int len=val.length;
							  for(int i=0;i<len;i++)
							  {
								  String[] value=val[i].split("\\$\\$");
								  System.out.println("chandan:"+value);
								  String antibioticCode="";
								  String organismCode="";
								  String selectval="";
								  
								  organismCode   =value[0];
								  antibioticCode=value[1];
								   selectval=value[2];
								  
								   objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
								   objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
								   
							  }
							  objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
								}
					
							  else
							  {
								  String antibioticCode="";
								  String organismCode="";
								  String selectval="";
								  
								// objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
								// objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
								 // objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							  }
							
							}*/
				
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
						 String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
							
							//added by prashantMi for echo modify
						  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
						  if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
							{
							  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
							} else {
							if(isexist!=null && !isexist.equals("0"))
							 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							else
								objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							}
							String status="";
							if(voInvResulEntryForParaMeterDtl.getReportAvailableAfter().equals(InvestigationConfig.RESULT_VALIDATION))
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
							
							
							  
							if(voInvResulEntryForParaMeterDtl.getFinalechovalue()!=null && !voInvResulEntryForParaMeterDtl.getFinalechovalue().equals(""))
							{
								objResultEntrtyDAO.updateechodata(voInvResulEntryForParaMeterDtl,_userVO);

							}
							
						   //objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
					 }
					 else
					 {
						 objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO);

//						 Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

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
								
								String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
								//added by prashantMi for echo modify
								  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
								
								 if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
									{
									  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
									} else
									{
								if(isexist!=null && !isexist.equals("0"))
								 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
								else
									objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
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
								
								
								//objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
								}
								  }
								
								  
							  }
							  else
							  {
								  
								  if( !voInvResulEntryForParaMeterDtl.getResultEntryValue().contains("hyperchanks"))
								  {
										voInvResulEntryForParaMeterDtl.setResultEntryValue("--");
									
									String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);
									//added by prashantMi for echo modify
									  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
									
									 if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
										{
										  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
										} else
										{
									if(isexist!=null && !isexist.equals("0"))
									 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
									else
										objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
										}
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
									
									
									//objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
									}
									  }
									
									  
								  
								
			 			 
			 			  
			 		}
		          
		          
		         
		          
		                    }
						 else
						 {
							 String antibioticCode="";
							  String organismCode="";
							  String selectval="";
							  
							// objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
							  String isexist=  objResultEntrtyDAO.checkparameterexistornot(voInvResulEntryForParaMeterDtl,_userVO);

								//added by prashantMi for echo modify
							  String isEcho=objResultEntrtyDAO.checkifEcho(voInvResulEntryForParaMeterDtl,_userVO);
							  if ( isEcho.equals("1") && (voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("") || voInvResulEntryForParaMeterDtl.getResultEntryValue() ==null || voInvResulEntryForParaMeterDtl.getResultEntryValue().equals("undefined"))  )
								{
								  //do not update HIVT_PARAMETER_DTL if resultEntryValue null in case of modify of echo template
								} else
								{
								  if(isexist!=null && !isexist.equals("0"))
								   {objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);}
								  else
									{objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);}
									 
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
								
								
							  //objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							 
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
					 
					 
								/*if(!fb.getSelectValuemapping().equals(""))
								{
									
									 String[] selectvaluemapping=fb.getSelectValuemapping().split("@");
									 objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO);
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
				
				
				//Put List in Map
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
		
		
		public  void revalidate(List<ResultEntryVO> invResultentryVO,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();

			List   listResultValidationDtl =new ArrayList();
			 

			boolean isComplete=true;
			List<ResultEntryVO> lstResultValidationVO=new ArrayList<ResultEntryVO>();
			 
			Map mp=new HashMap();

			try
			{    

				tx.begin();
				InvResultValidationDAO objResultEntrtyDAO=new InvResultValidationDAO(tx);
				
				for(ResultEntryVO voInvResultValidation:invResultentryVO)
				{
						 
					/*voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
					objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);*/
					
					if(voInvResultValidation.getIsSendToMachine()!=null && voInvResultValidation.getIsSendToMachine().equals("1")){
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_MACHINE_RESULT_ENTRY);
						objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
						
					}
					else
					{
						if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
							voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
						else					
							voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
					
					
						objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
						
						if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						{
							objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
						}
					
						objResultEntrtyDAO.updatepidvaluesresultwithoutchange(voInvResultValidation,_userVO);
					
					}
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

		//	return mp;
			
		}
	
	
	
	public Map  getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String labNames="";
		String prvTestDetail="";
		List<LabTestVO> lstPrvLabTest=null;
		String testGroupNames="";
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			//List lstLabNames=invEssentialDAO.getLabNames(userVO);
			  lstPrvLabTest=invEssentialDAO.getPrvTestDtlUsingAJAX(CrNo, userVO);
		 
			  //LabTestVO LabTestVO=new LabTestVO();
			  
			 
			  
			  for(LabTestVO vo:lstPrvLabTest)
			  {
				  prvTestDetail+=vo.getStatus()+"#"+vo.getLabName()+"#"+vo.getSampleName()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+vo.getBookMarkCode()+"#"+vo.getOfflineAppoitMentDate()+'#'+vo.getReqDate()+'#'+vo.getPrvLabCode()+'#'+vo.getPrvTestCode()+'#'+vo.getPrvReqStatus()+'#'+vo.getReqNo()+"#"+vo.getTestGroupName()+"@";  
				  
				  	 
			  }
			mp.put(InvestigationConfig.LIST_PRVTESTDTL_AJAX, prvTestDetail);

		 

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
	
	
	 
	public Map getAptBasedTest(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstAptBased=null;
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			lstAptBased=invEssentialDAO.getAptBasedTest(searchVO, _UserVO);



			mp.put(InvestigationConfig.LIST_APTBASED_TEST,lstAptBased);


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
	
 
	
	public boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		boolean isTempReqPresent=false;



		try {

			tx.begin();
			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);

			isTempReqPresent=invEssentialDAO.checkRequisitionPending(searchVO, _UserVO);



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

		return isTempReqPresent;
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

public String getAccountNo(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
 


		JDBCTransactionContext tx = new JDBCTransactionContext();
		String accountNo="";
		try {

			tx.begin();

			InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
			accountNo=invEssentialDAO.getAccountNo(patVO, userVO);
			
			
			
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

		return accountNo;
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
			
			InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);

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
						for(SampleCollectionCumAcceptanceVO voSample:lstSampleCollectionVO)
						{
							
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
									 
                                      String runningLabNO=objSampleCollectionDAO.checkAutoGenFormateRunningLabNo(voSample, _userVO);
									 
                                      if(sameSampleNO==strSampleNo)
 									 {
 											voSample.setTemparorySampleNO(runningLabNO); 
 									 }
                                      else
                                      {
									 voSample.setRunningSampleNo(runningLabNO);
									 
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
									objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
									
									
									
									
									
									
									
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

	@Override
	public List<SampleCollectionCumAcceptanceVO> getPatList(SampleCollectionCumAcceptanceVO objSampleCollectionVO,UserVO _UserVO) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<SampleCollectionCumAcceptanceVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<SampleCollectionCumAcceptanceVO>();

		try {

			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionCumAcceptanceDAO=new SampleCollectionCumAcceptanceDAO(tx);

			lstinvSampleCollectionVO =objSampleCollectionCumAcceptanceDAO.getPatList(objSampleCollectionVO,_UserVO);


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

		return lstinvSampleCollectionVO;
	}

	@Override
	public List<SampleCollectionCumAcceptanceVO> getSampleCollectionAreas(UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<SampleCollectionCumAcceptanceVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<SampleCollectionCumAcceptanceVO>();

		try {

			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionCumAcceptanceDAO=new SampleCollectionCumAcceptanceDAO(tx);
			lstinvSampleCollectionVO =objSampleCollectionCumAcceptanceDAO.getSampleCollectionArea(_UserVO);



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

		return lstinvSampleCollectionVO;
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
		List<ResultEntryVO> lstInvResultValidationVO=new ArrayList<ResultEntryVO>();
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

			lstInvResultValidationVO=invresultentrydao.setPatientResultValidationEssentials(invresultentryvo, _UserVO);
			
			
			
			//////logic to group by groupcode /////
			
			for(int i=0;i<lstInvResultValidationVO.size();i++)
			{
				String concatString=lstInvResultValidationVO.get(i).getTestParameterName();
				String testCode=lstInvResultValidationVO.get(i).getTestCode();
				
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
					
					System.out.println("dno::::"+lstInvResultValidationVO.get(i).getRequisitionDNo());
					String[] paraValues=parameters[iterate].split("#@");
					String paraCode=paraValues[0];
					String paraName=paraValues[1];
				
					String paraEntry="";
					
					if(paraValues.length>3)
						 paraEntry=paraValues[3];
					//String paraEntry=paraValues[3];
					
					/*String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, lstInvResultValidationVO.get(i).getPatGender(), 
											lstInvResultValidationVO.get(i).getPatAge());*/

					String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, testCode, paraCode, lstInvResultValidationVO.get(i).getPatGender(),lstInvResultValidationVO.get(i).getPatAge());
					
					
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
						properParaValues+=parameters[iterate]+"`";
					
					
					
				}
				
		
				properParaValues=properParaValues.substring(0, properParaValues.length()-1);
				lstInvResultValidationVO.get(i).setTestParameterName(properParaValues);
				
				//to append para values for all group values
				if(lstInvResultValidationVO.get(i).getGroupCode()!=null)
				{
					
					if(!groupCode.contains(lstInvResultValidationVO.get(i).getGroupCode()+lstInvResultValidationVO.get(i).getRequisitionNo()))
					{
					for(int j=i+1;j<lstInvResultValidationVO.size();j++)
					{
						if(lstInvResultValidationVO.get(j).getGroupCode()!=null)
						if(lstInvResultValidationVO.get(i).getGroupCode().equals(lstInvResultValidationVO.get(j).getGroupCode()) && lstInvResultValidationVO.get(i).getRequisitionNo().equals(lstInvResultValidationVO.get(j).getRequisitionNo()))
						{	
						String temp_concatString=lstInvResultValidationVO.get(j).getTestParameterName();
						String temp_testCode=lstInvResultValidationVO.get(j).getTestCode();
						
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
							
							String refRange = tpu.getReferenceRange(objRefRangeList, objCriteriaCodeList, temp_testCode, paraCode, lstInvResultValidationVO.get(j).getPatGender(), 
									lstInvResultValidationVO.get(j).getPatAge());
							
							
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
								temp_properParaValues+=temp_parameters[iterate]+"`";
							
							
							
						}
						
				
						temp_properParaValues=temp_properParaValues.substring(0, temp_properParaValues.length()-1);
						lstInvResultValidationVO.get(j).setTestParameterName(temp_properParaValues);
						
						
						lstInvResultValidationVO.get(i).setTestParameterName(lstInvResultValidationVO.get(i).getTestParameterName()+"`"+temp_properParaValues);
					}
					}
						groupCode.add(lstInvResultValidationVO.get(i).getGroupCode()+lstInvResultValidationVO.get(i).getRequisitionNo());
						groupModified_lstInvResultEntryVO.add(lstInvResultValidationVO.get(i));
					}
					else
					{;}
					
				}
				else
				{
					groupModified_lstInvResultEntryVO.add(lstInvResultValidationVO.get(i));
				}
				
				
			
				
				
			}
			
mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,groupModified_lstInvResultEntryVO);

			
			//////////////////////////////////////
			
			//////////////////////////////logic to fetch combo value name of the tests//////////////////////////
			
//			for(int i=0;i<lstInvResultValidationVO.size();i++)
//			{
//				String concatString=lstInvResultValidationVO.get(i).getTestParameterName();
//				String testCode=lstInvResultValidationVO.get(i).getTestCode();
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
//				lstInvResultValidationVO.get(i).setTestParameterName(properParaValues);
//			}
//			
			////////////////////////////logic to fetch combo value name of the tests ENDS//////////////////////////
			
			

			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationVO);
			
			
			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationVO);
			
			 
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
	public Map ResultValidationPatientDetails(InvResultValidationVO invresultentryvo,List<String> reqList, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvResultValidationVO> lstInvResultValidationVO=new ArrayList<InvResultValidationVO>();
		List<InvResultValidationVO> reqLis=new ArrayList<InvResultValidationVO>();
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
	
	public  Map saveResultReValidationDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb)
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
			
			
			
			for(ResultEntryVO voInvResultValidation:invResultentryVO)
			{
					
				/*if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				else*/					
				voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				
				
				objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
				
				//added by prashant
				objResultEntrtyDAO.updateIndicationInRequisitionheader(voInvResultValidation,_userVO);
				objResultEntrtyDAO.commentsupdateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);

				
				if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
				{
				objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
				}		
				
			
			
			
			}
			
		/*	for(ResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
				objResultEntrtyDAO.insertResultLogDtl(voInvResulValidationForParaMeterDtl, _userVO);
				//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
			}*/	 
			
			/*for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				
				String parentid=voInvResulEntryForParaMeterDtl.getParantParaCode();
				
				String Ishyperlink="";
				Ishyperlink=voInvResulEntryForParaMeterDtl.getIsHyperLink();
				 if(Ishyperlink.equals("-"))
				 {
					 objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
				 }
				 else
				 {
					
					
							if(!fb.getSelectValuemapping().equals("null"))
							{
						  String[] val=fb.getSelectValuemapping().split("\\$\\$\\$");
						  int len=val.length;
						  for(int i=0;i<len;i++)
						  {
							  String[] value=val[i].split("\\$\\$");
							  System.out.println("chandan:"+value);
							  String antibioticCode="";
							  String organismCode="";
							  String selectval="";
							  
							  organismCode   =value[0];
							  antibioticCode=value[1];
							   selectval=value[2];
							  
							   objResultEntrtyDAO.updateHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
							   objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);
							
						  }
						   objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							}
				
						  else
						  {
							  String antibioticCode="";
							  String organismCode="";
							  String selectval="";
							  
						//	 objResultEntrtyDAO.insertHyperLinkDetails(voInvResulEntryForParaMeterDtl,_userVO,antibioticCode,selectval,organismCode,parentid);	  
							  //objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
							 // objResultEntrtyDAO.insertResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						  }
						
						}
			
			
			//Put List in Map
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_STATUS, listResultValidationDtl);
			 

		}*/
			
			
			
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
			
			for(ResultEntryVO voInvResultValidation:invResultentryVO)
			{
					 
				//voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
				voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
				
				if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
				{
				objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
				}	
				
				objResultEntrtyDAO.updatepidvaluesresultwithoutchange(voInvResultValidation,_userVO);

				
			}
	
			/*
			for(InvResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
				
				//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
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
			
			public  Map modifyResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,InvResultEntryFB _fb)
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
								 {objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);}
								else
									{objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);}
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
													 objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
												else
													objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
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
								{ objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);}
								else
									{objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);}
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
			
			
			public Map getAptByDeskRaiseCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstAptByDesk=null;
				Map mp=new HashMap();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					lstAptByDesk=invEssentialDAO.getAptByDesk(searchVO, _UserVO);



					mp.put(InvestigationConfig.LIST_APT_BY_DESK,lstAptByDesk);


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
			
			public Map getAppointmentRaiseCumColl(String reqNo,String crNo, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstAptByDesk=null;
				Map mp=new HashMap();
				List lstUOMCombo=null;
				List lstContainerCombo=null;


				try {

					tx.begin();

					SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

					lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);
					lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);
					
					
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					lstAptByDesk=invEssentialDAO.getAppointmentRaiseCumColl(reqNo,crNo, _UserVO);



					mp.put(InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK,lstAptByDesk);
					//Putting Combos in map
					mp.put(InvestigationConfig.LIST_UOM_COMBO,lstUOMCombo);
					mp.put(InvestigationConfig.LIST_CONTAINER_COMBO,lstContainerCombo);

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
			
			
			
			public  List saveAppointmentDetailsRaiseCumColl(List<LabTestVO> lstLabTestVO,LabTestVO patVO,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List listReqId=new ArrayList();
				String labName="";
				String testName="";
				List<LabTestVO> voSample1=null;
				String Formate="null";
				String collAreaBasedSampleNo="";

				try
				{    
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

					InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);
					
					InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
								
					int requisitionTypeForBilling=0;
					
						   if(patVO.getPatStatus().equals("IPD"))
						   {  
							   requisitionTypeForBilling=2;
						   }
						   else if(patVO.getPatStatus().equals("OPD"))
						   {
							   requisitionTypeForBilling=1;
						   }
						   else
						   {
							   requisitionTypeForBilling=1;
						   }
							   
						   for(int i=0;i<lstLabTestVO.size();i++)
						   {
						   
	   							LabTestVO voLabTest=lstLabTestVO.get(i);
	   							InvestigationRequistionVO voReq=new InvestigationRequistionVO();
	   							String sameSampleNO="";
	   							
	   							voReq.setStrLabCode(voLabTest.getLabCode());
								voReq.setStrTestCode(voLabTest.getTestCode());
								voReq.setStrSampleCode(voLabTest.getSampleCode());
								
								String strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(voLabTest.getSampleCode(), voLabTest.getLabCode(), _userVO); 

								
								
								voReq.setStrRequsitionDNo(voLabTest.getRequisitionDNo());
								voReq.setStrReqNo(voLabTest.getReqNo());
								voReq.setStrPriority((voLabTest.getPriority()==null?InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL:voLabTest.getPriorityCode()));
														
								voReq.setIslabappointmentbased((voLabTest.getIslabappointmentbased()));
		
								voReq.setStrAppointmentDate(voLabTest.getAppointmentDate());
								voReq.setStrAppointmentTime(voLabTest.getAppointmentTime());
														
								voReq.setAppointmentRefNo(voLabTest.getAppointmentRefNo());
								String apoitmentDAte=voLabTest.getAppointmentDate();
								voLabTest.setTempSampleNo(voLabTest.getSampleNoConfiguration());
								///////////*************************************** autogen function ****************************************////////////
								if(voLabTest.getSampleNoConfiguration().equals("1")||voLabTest.getSampleNoConfiguration().equals("2"))
									
								{
								voSample1=objSampleCollectionDAO.checkAutoGenFormateRaisingCumCollection(voLabTest, _userVO);
								
										int size=voSample1.size();
								
								if(size!=0)
								{
									StringBuilder sb = new StringBuilder();
									//all but last
									for(LabTestVO voSampleVo:voSample1 ) {
										sb.append(voSampleVo.getSampleNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningSampleNo()+"#"+voSampleVo.getPatType()  );
										sb.append("#");
										
										voLabTest.setSampleNoFormat(voSampleVo.getSampleNoFormat());
										voLabTest.setInitDate(voSampleVo.getInitDate());
										voLabTest.setNoOfSeqDigit(voSampleVo.getNoOfSeqDigit());
										voLabTest.setFromSeries(voSampleVo.getFromSeries());
										voLabTest.setToSeries(voSampleVo.getToSeries());
										voLabTest.setInitType(voSampleVo.getInitType());
										voLabTest.setRunningSampleNo(voSampleVo.getRunningSampleNo());
										voLabTest.setPatType(voSampleVo.getPatType());
										
										voLabTest.setConfigArea(voSampleVo.getConfigArea());
										voLabTest.setConfigLab(voSampleVo.getConfigLab());
										voLabTest.setConfigSeq(voSampleVo.getConfigSeq());
										voLabTest.setConfigType(voSampleVo.getConfigType());
										voLabTest.setConfigTest(voSampleVo.getConfigTest());
									}
									 
									Formate=sb.toString();
								
								}
								}
								////////////****************************************autogen function ends***********************************///////////////
								voLabTest.setSampleFormate(Formate);
								
		
								
								
								
								
								
								
								
								
								//invEssentialDAO.saveAppointmentDetail(voReq,_userVO);	
								
								//confirm Appoitment 
								
								if(!voLabTest.getAppoitmentNo().equals("0")&&voLabTest.getAppoitmentNo()!=null)
								{
									voReq.setAppointmentNo(voLabTest.getAppoitmentNo());
									invEssentialDAO.ConfirmAppointment(voReq, _userVO);
								}
								
								
								
								
								/////////////////////////////////////*******************using autogen values*******************************///////////////////////////////////
								
								if((voLabTest.getSampleNoConfiguration().equals("1")||voLabTest.getSampleNoConfiguration().equals("2"))) 
								{

									//for(Inv_SampleCollectionVO autoSampleVo:lstAutoSampleNOConfig)
									//{
									
									 
										
										String Str=voLabTest.getSampleNoFormat();
										//  String Str = new String(autoLabNumber);
										int MainStrlength=Str.length();
										String[] dateFormate = Str.split("&");
										String subDateFormate=dateFormate[0];
										String xMainValue=dateFormate[1];
										//getting the DateFormate number
										String sequence_SampleNO_yymmdd=objSampleCollectionDAO.generateSampleNoDateSequence(subDateFormate, _userVO);  // Returns   yymmdd
										String entryDate= voLabTest.getInitDate();//get the Entry Date 
										DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										
										 
										Date date = df.parse(entryDate);
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										Calendar c = Calendar.getInstance();
										c.setTime(date); // Added a entry date. into Calender
										String finalDate="";
										
										if(voLabTest.getInitType().equals("m"))
										{
											
											int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
											  
											String[] SplitDate=entryDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
											 date=df.parse(finalDate);
											 
										}
										if(voLabTest.getInitType().equals("d"))
										{
											
											date = df.parse(entryDate);
											//c.add(Calendar.DATE,1); 
										}
										if(voLabTest.getInitType().equals("y"))
										{
											String[] SplitDate=entryDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+12+"-"+31;
											date = df.parse(finalDate);
										}
										if(voLabTest.getInitType().equals("w"))
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
											
											//c.add(Calendar.DATE,7);  
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
										  
										String fromSer=voLabTest.getFromSeries();
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
										int gnumnoofseqdigits1=Integer.parseInt(voLabTest.getNoOfSeqDigit());
										 String subStrvalueLab1=xVal.substring(Math.max(0,xValLen - gnumnoofseqdigits1));
										 
										 String runningLabNO=objSampleCollectionDAO.checkAutoGenFormateRunningLabNoRaisingCumCollection(voLabTest, _userVO);
										 
										/* String runningLabNO=voLabTest.getRunningSampleNo();*/
										 //checkAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
										 if(sameSampleNO==strSampleNo)
										 {
											 voLabTest.setTemparorySampleNO(runningLabNO); 
										 }
										 else
										 {
										 
											 voLabTest.setRunningSampleNo(runningLabNO);
										 
										 if(voLabTest.getRunningSampleNo()!=null)
											{
											 String  sampleNO=voLabTest.getRunningSampleNo();
												int sampleNOLen=sampleNO.length();
												 int gnumnoofseqdigits=Integer.parseInt(voLabTest.getNoOfSeqDigit());
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
										
										String toSer=voLabTest.getToSeries();
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
										int gnumnoofseqdigits2=Integer.parseInt(voLabTest.getNoOfSeqDigit());
										 String subStrvalueLab2=xValToSer.substring(Math.max(0,xValToSerLen - gnumnoofseqdigits2));
									 
										int toSerValue = Integer.parseInt(subStrvalueLab2);

										if(EntryDatecomparWithSysDAte<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
										{	 

											if(voLabTest.getRunningSampleNo()==null)
											{

												voLabTest.setTemparorySampleNO(FinalSampleNo);
											}
											else
											{

												if(fromSerValue<toSerValue)
												{
													String  sampleNO=voLabTest.getRunningSampleNo();
													int sampleNOLen=sampleNO.length();
													 int gnumnoofseqdigits=Integer.parseInt(voLabTest.getNoOfSeqDigit());
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
													voLabTest.setTemparorySampleNO(finalSample);
												}
												else
												{
													voLabTest.setTemparorySampleNO(FinalSampleNo);
													
												}
											}

										}

										else  ////for EntryDatecomparWithSysDAte value is Positive
										{
											
											SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
											objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1ResetLabNORaisingCumCollection(voLabTest,finalEntryDate,_userVO);
											voLabTest.setTemparorySampleNO(FinalSampleNo);
											Date todayDateob = new Date();
											SimpleDateFormat dateobj = new SimpleDateFormat("yyyy-MM-dd");
											String strSysDate= dateob.format(todayDateob);
											Date todayDat =dateobj.parse(strSysDate) ; 
											Calendar c1 = Calendar.getInstance();
											c1.setTime(todayDat); // Now use SYDATE date.

											if(voLabTest.getInitType().equals("m"))
											{
												int daysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
												  
												String[] SplitDate=strSysDate.split("-");
												
												 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
												 todayDat=dateobj.parse(finalDate);
											}
											if(voLabTest.getInitType().equals("d"))
											{
												todayDat=dateobj.parse(strSysDate);
											}
											if(voLabTest.getInitType().equals("y"))
											{
												String[] SplitDate=strSysDate.split("-");
												
												 finalDate=SplitDate[0]+"-"+12+"-"+31;
												 todayDat = dateobj.parse(finalDate);
											}
											if(voLabTest.getInitType().equals("w"))
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
										SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
										
										
										//-----------------------------------------------area wise sample no temp!
										if(voLabTest.getTempSampleNo().equals("2"))
										if(collAreaBasedSampleNo.equals(""))
										{
										collAreaBasedSampleNo=voLabTest.getTemparorySampleNO();
									//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
										}
										else
											voLabTest.setTemparorySampleNO(collAreaBasedSampleNo);
										
										
										//--------------------------------------appended above in sample no generation area wise. 
										//-=------------------------------------ remove comment is above block of collAreaBasedSampleNo removed
										objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1RaisingCumCollection(voLabTest,finalEntryDate,_userVO);
										
										
									//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1RaisingCumCollection(voLabTest,finalEntryDate,_userVO);
										
										
										
										/*	//-----------------------------------------------area wise sample no temp!
												if(voSample.getTempSampleNo().equals("2"))
												if(collAreaBasedSampleNo.equals(""))
												{
												collAreaBasedSampleNo=voSample.getTemparorySampleNO();
											//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
												}
												else
													voSample.setTemparorySampleNO(collAreaBasedSampleNo);
												
												
												//--------------------------------------appended above in sample no generation area wise. 
												//-=------------------------------------ remove comment is above block of collAreaBasedSampleNo removed
												objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);*/
									//}for Loop End
									// Update Requisition Dtl  Table 
									//Setting Requisition Dtl Status as '3' for packing list generation
										 }
										 voLabTest.setRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST); //3
										 voLabTest.setSampleNo(strSampleNo);
										 voLabTest.setTempSampleNo(voLabTest.getTemparorySampleNO());
									
									//	 invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table

									

									sameSampleNO=strSampleNo;
								}
								else{
									 voLabTest.setRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST); //3
									 voLabTest.setSampleNo(strSampleNo);
									// invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table
								}
								
								//////////////////////////////////*************************using ends*****************************////////////////////////
							
								
								
								
						
								if(voBillingDtl.getRequisitionNos()==null)
								{
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voLabTest.getReqNo()+'!');
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getPatDeptCode()==null?patVO.getPatDeptUnitCode():patVO.getPatDeptCode());
								}
								else
								{
								if(!voBillingDtl.getRequisitionNos().contains(voLabTest.getReqNo()+"!"))
								{
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voLabTest.getReqNo()+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getPatDeptCode()==null?patVO.getPatDeptUnitCode():patVO.getPatDeptCode());
								}
								}
								
								
									if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
									{
										if(voLabTest.getTestGroupType()==null ||voLabTest.getTestGroupType().equals("")||voLabTest.getTestGroupCode().equals("0")||voLabTest.getTestGroupType().equals("3"))
										{
											if(voBillingDtl.getTariffDetails()==null)
											{
												voBillingDtl.setTariffDetails(new ArrayList<String>());
												voBillingDtl.setTariffQty(new ArrayList<String>());
											}
											
											voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
											voBillingDtl.getTariffQty().add("1");
										}
										else
										{
											if(voBillingDtl.getGrouptariffDetails()==null)
											{
												voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
												voBillingDtl.setGrouptariffQty(new ArrayList<String>());
											}
											
											voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
											voBillingDtl.getGrouptariffQty().add("1");
										}
									}
						
									//Adding Details for Display after Save
									labName=voLabTest.getLabName();
									/*if(!testName.equals(""))
									  testName=testName+","+voLabTest.getTestName();
									else
										testName=voLabTest.getTestName();*/
									
							 // Loop Over TestCodes
							
									
									//invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table
									//need update query
									objSampleCollectionDAO.updateRequisitionDtlRaiseCumColl(voLabTest, _userVO);

									objSampleCollectionDAO.insertSampleDtlRaisingCumCollection(voLabTest, _userVO);
									
									
									
									
									
						//Add The Requisition Number in list
							listReqId.add(patVO.getPatCrNo()+"#"+""+"#"+"");
						   }
				//	**********************************************************************************************************************************
					
					
					// Billing Logic :: Save
					if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
					{
								String simpletariffdetails="";
								String simpletariffQty="";
					
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
								System.out.println(simpletariffdetails);
								String grouptariffdetails="";
								String checkDuplicateDetail="";
								boolean checkGrp=false;
								String grouptariffQty="";
								if(voBillingDtl.getGrouptariffDetails()!=null)
								{
									for(int indexCounter=0;indexCounter<voBillingDtl.getGrouptariffDetails().size();indexCounter++)
									{
										if(indexCounter==0)
										{
											grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter);
											grouptariffQty=voBillingDtl.getGrouptariffQty().get(indexCounter);
											checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
										}
										else
										{
											String[] SplitCheckDuplicateDetail=checkDuplicateDetail.split("&");
											if(SplitCheckDuplicateDetail.length>1)
												for(int y=1;y<SplitCheckDuplicateDetail.length;y++)
												{
													if(!SplitCheckDuplicateDetail[y].equals(voBillingDtl.getGrouptariffDetails().get(indexCounter)))
													{
														  checkGrp=true;
													}
													//else
													//{
														 //checkGrp=false;
													//}
											
												}
											
											if(checkGrp)
											{
											grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
											grouptariffQty+="^"+voBillingDtl.getGrouptariffQty().get(indexCounter);
											checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
										}
										}
									 
										
									}
								
								}
								
								Inv_RequisitionRaisingPatientVO reqVO=new Inv_RequisitionRaisingPatientVO();
								
													
								reqVO.setPatCategoryCode(patVO.getPatCategoryCode());
								reqVO.setPatepisodecode(patVO.getEpisodeCode());
								reqVO.setPatCRNo(patVO.getPatCrNo());
								reqVO.setPatFirstName(patVO.getPatName());
								reqVO.setPatLastName("");
								reqVO.setPatMiddleName("");
								reqVO.setPatAddress(patVO.getPatAddress());
								reqVO.setPatMobileNo(patVO.getPatMobile());
								reqVO.setPatAge(patVO.getPatAge());
								reqVO.setPatGenderCode(patVO.getPatGenderCode());
								reqVO.setConsultantName(patVO.getPatOrderByDoc());
								reqVO.setPatwardcode(patVO.getPatWardCode());
								reqVO.setPatadmissionno(patVO.getPatAdmNo());
								reqVO.setPatVisitNo(patVO.getPatVisitNo());
								
								
								
								if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
								{
									// invBillingDAO.makeBillingTestWise(voBillingDtl, reqVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
								}
								
								if(grouptariffdetails.equals("")==false)
								{
									//invBillingDAO.makeBillingTestWise(voBillingDtl, reqVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
								}
					}	
					//Billing Save Logic End
					
				
				 
					
						
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
			
			
			
			public Map getAppointmentDetailsOnClickGORaiseCumColl(LabTestVO voLabTest, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstAptByDesk=null;
				Map mp=new HashMap();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					lstAptByDesk=invEssentialDAO.getAppointmentDetailsOnClickGO(voLabTest, _UserVO);


					if(lstAptByDesk!=null)
					mp.put(InvestigationConfig.LIST_APT_DETAILS_ON_CLICK_GO,lstAptByDesk);


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
						String refRange=InvestigationEssentialBOxray.getRefRangeForTestPara(objRefRangeList,objCriteriaCodeList,voInvResultEntry.getTestCode(),voInvResultEntry.getParameterCode(),voInvResultEntry.getPatGender(),voInvResultEntry.getPatAge());
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

			


			

public  List saveRequisitionCollectionDetails(Map<String,Map<String,List<LabTestVO>>> mp_lab,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List listReqId=new ArrayList();
	String labName="";
	String testName="";
	String sampleName="";
	String patType="";
	String tempSampleNo="";
	String collAreaBasedSampleNo="";
	String sampleNo="";
	String reqDate="";
	try
	{    
		tx.begin();
		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);
		
		InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
		List<LabTestVO> voSample1=null;
		 String Formate="null";
		//First iterate the Map over Lab codes for generating requisition no's
		
		//String[] labCodeArray=(String[])mp.keySet().toArray();
		
		//int size=labCodeArray.length;
		
		int requisitionTypeForBilling=0;
		
		Iterator itrLab=mp_lab.keySet().iterator();
		
		while(itrLab.hasNext())//for(int i=0;i<size;i++)
		{
			testName="";
			
			String labCode=(String)itrLab.next();
			
			//Generate Requisition No Sequence  for each lab
			// Prepare Requisition Number Dynamically with given values in LLLLLYYMMDDXXXXX format
			//  String requisitionNumber=_userVO.getHospitalCode()+labCode+yymmdd+sequence; //hospitalcodeLLLLLYYMMDDXXXXX format
			  
				String requisitionNumber=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);	//procedure generating req no and inserting/updating it accordingly
				String crNO=patVO.getPatCRNo();
			   
			   //Setting Requisition HeaderVO Values
			   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
			   voReqHeader.setLabCode(labCode);
			   voReqHeader.setRequisitionNumber(requisitionNumber);
			   voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
			   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
			   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
			   voReqHeader.setBedCode(patVO.getBedCode());
			   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
			   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
			   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
			   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
			   voReqHeader.setGenderCode(patVO.getPatGenderCode());
			   voReqHeader.setIsActualDob(patVO.getIsActualDob());
			   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
			   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
			   voReqHeader.setMlcNo(patVO.getPatmlcno());
			   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
			   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
			   voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
			   voReqHeader.setPatDob(patVO.getPatDOB());
			   voReqHeader.setPatAge(patVO.getPatAge());
			   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
			   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
			 
			   
			  /* if(patVO.getPatStatus().equals("IPD"))
			   {
				   voReqHeader.setReqType(InvestigationConfig.REQUISITION_TYPE_IPD);
				   requisitionTypeForBilling=2;
				   patType=InvestigationConfig.REQUISITION_TYPE_IPD;
			   }
			   else if(patVO.getPatStatus().equals("OPD"))
			   {
				   voReqHeader.setReqType(InvestigationConfig.REQUISITION_TYPE_OPD);
				   requisitionTypeForBilling=1;
				   patType=InvestigationConfig.REQUISITION_TYPE_OPD;
			   }
			   else
			   {
				   voReqHeader.setReqType(InvestigationConfig.REQUISITION_TYPE_CASUALITY);
				   requisitionTypeForBilling=1;
				   patType=InvestigationConfig.REQUISITION_TYPE_CASUALITY;
			   }
				  */ 
			   
			   
			   if(patVO.getPatStatusCode().equals("2"))
				{						  
					requisitionTypeForBilling=2;
				}
				else 
				{
					//visit type code 1-opd, 2,3-emergency, 4 special
					if(patVO.getPatvisittypecode()==null)
						requisitionTypeForBilling=1;
					else{
						
					
					if(patVO.getPatvisittypecode().equals("1"))
						requisitionTypeForBilling=1;
					if(patVO.getPatvisittypecode().equals("4"))
						requisitionTypeForBilling=4;
					if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
						requisitionTypeForBilling=3;
					}
					
				}
			   
			   //voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
			   voReqHeader.setRequsitionRaisedThrough("5");  //7
			   voReqHeader.setReqType(""+requisitionTypeForBilling);
			   
			   voReqHeader.setRoomCode(patVO.getPatroomno());
			   voReqHeader.setWardCode(patVO.getPatwardcode());
			   
			   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
			   voReqHeader.setWardName(patVO.getPatwardname());
			   voReqHeader.setBedName(patVO.getBedname());
			   
			   voReqHeader.setVisitNo(patVO.getPatVisitNo());
			  
			   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
			   
			   
			   
			   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
			    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
			    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
			  
			    
			    
			   invEssentialDAO.insertRequisitionHeaderDtl(voReqHeader,_userVO);	//procedure to insert in req header dtl
			   
			
			//Insertion in HIVT_REQUISITION_DTL Table
			   // Getting Map of Test from Map of Lab
			   
		     Map<String,List<LabTestVO>> mp_sample=mp_lab.get(labCode);
			   
				//First iterate the Map over Test codes for generating requisitionDno's
				
				//String[] testCodeArray=(String[])mpTest.keySet().toArray();
				
				//int sizeTest=testCodeArray.length;
		     
		     	Iterator itrSample=mp_sample.keySet().iterator();
		     	
				int testCounter=0;					//to check if need to be put in below loop
				String runningSequence="00";	//to check if need to be put in below loop
				
				while(itrSample.hasNext())																	//for(int j=0;j<sizeTest;j++)
				{
					String sampleCode=(String)itrSample.next();
					
					
					//write logic to generate sample no based on sample and lab code
					String strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO); 
					String sameSampleNO="";	
					
					//then fetch vo from listvo one by one and generate reqdno for each test
					List<LabTestVO> lstLabTestVO=mp_sample.get(sampleCode);
					
					
					
					// Loop over VO for saving
					
					for(LabTestVO voLabTest:lstLabTestVO)
					{
				
					//Logic for generating running sequence 'XX'
						
					testCounter++;
					if(testCounter/10<1)
						runningSequence="0"+Integer.toString(testCounter); //Like 01,02,03 etc..
					else
						runningSequence=Integer.toString(testCounter);
					
					//requisitionDNo generation:: format requisitionNumberXX
					
					String requisitionDNo=requisitionNumber+runningSequence;
					
				   //Setting Requisition VO Values
					
					InvestigationRequistionVO voReq=new InvestigationRequistionVO();
					voReq.setStrLabCode(voLabTest.getLabCode());
					voReq.setStrTestCode(voLabTest.getTestCode());
					voReq.setStrSampleCode(voLabTest.getSampleCode());
					voReq.setStrRequsitionDNo(requisitionDNo);
					voLabTest.setReqDno(requisitionDNo);
					voLabTest.setReqNo(requisitionNumber);
					voReq.setStrReqNo(requisitionNumber);
					voReq.setStrWorkOrderSequence(runningSequence);
					voLabTest.setWorkingOrderSequence(runningSequence);
					voReq.setStrPriority((voLabTest.getPriority()==null?InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL:voLabTest.getPriority()));
					voLabTest.setPriority(voReq.getStrPriority());
					
					if(voLabTest.getTestType().equals("P"))
						voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PATIENT_BASED);
					else
						voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST);
					
					voLabTest.setRequisitionDtlStatus(voReq.getStrRequisitionDtlStatus());
					
					voReq.setStrTypeOfComponent("1"); //need to configure
					voLabTest.setTypeOfComponent(voReq.getStrTypeOfComponent());
					voReq.setStrTestGroupCode((voLabTest.getTestGroupCode().equals("0")?null:voLabTest.getTestGroupCode()));
					voLabTest.setTestGroupCode(voReq.getStrTestGroupCode());
					voReq.setStrTestGroupType((voLabTest.getTestGroupType().equals("0")?null:voLabTest.getTestGroupType()));
				    voLabTest.setTestGroupType(voReq.getStrTestGroupType());
				
				    //sample details
					voReq.setUomCode(voLabTest.getUomCode());
					voReq.setContainerCode(voLabTest.getContainerCode());
					voReq.setContainerVolume(voLabTest.getContainerVolume());
					voReq.setTempSampleNo(voLabTest.getTempSampleNo());
					voReq.setSampleNo(strSampleNo);
					voLabTest.setSampleNo(strSampleNo);
					voLabTest.setPatType(patType);
					voReq.setIslabappointmentbased((voLabTest.getIslabappointmentbased()));

					voReq.setStrAppointmentDate(voLabTest.getAppointmentDate());
					voReq.setStrAppointmentTime(voLabTest.getAppointmentTime());
					voReq.setAdvisedBYDoctorName(voLabTest.getAdvisedByDoctorName());
					voReq.setAppointmentRefNo(voLabTest.getAppointmentRefNo());
					String apoitmentDAte=voLabTest.getAppointmentDate();
					reqDate=apoitmentDAte;
					///////////*************************************** autogen function ****************************************////////////
					if(voLabTest.getSampleNoConfig().equals("1")||voLabTest.getSampleNoConfig().equals("2"))
						
					{
					voSample1=objSampleCollectionDAO.checkAutoGenFormateRaisingCumCollection(voLabTest, _userVO);
					
							int size=voSample1.size();
					
					if(size!=0)
					{
						StringBuilder sb = new StringBuilder();
						//all but last
						for(LabTestVO voSampleVo:voSample1 ) {
							sb.append(voSampleVo.getSampleNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningSampleNo()+"#"+voSampleVo.getPatType()  );
							sb.append("#");
							
							voLabTest.setSampleNoFormat(voSampleVo.getSampleNoFormat());
							voLabTest.setInitDate(voSampleVo.getInitDate());
							voLabTest.setNoOfSeqDigit(voSampleVo.getNoOfSeqDigit());
							voLabTest.setFromSeries(voSampleVo.getFromSeries());
							voLabTest.setToSeries(voSampleVo.getToSeries());
							voLabTest.setInitType(voSampleVo.getInitType());
							voLabTest.setRunningSampleNo(voSampleVo.getRunningSampleNo());
							voLabTest.setPatType(voSampleVo.getPatType());
							
							voLabTest.setConfigArea(voSampleVo.getConfigArea());
							voLabTest.setConfigLab(voSampleVo.getConfigLab());
							voLabTest.setConfigSeq(voSampleVo.getConfigSeq());
							voLabTest.setConfigType(voSampleVo.getConfigType());
							voLabTest.setConfigTest(voSampleVo.getConfigTest());
						}
						 
						Formate=sb.toString();
					
					}
					}
					////////////****************************************autogen function ends***********************************///////////////
					voLabTest.setSampleFormate(Formate);
				
					
					
					invEssentialDAO.updateRequisitionHeader(requisitionNumber,apoitmentDAte,_userVO);	//calling procedure to update req/app date
					
					String[] SplitedValues=voLabTest.getFinalMandValues().split("@");
					
					String[] splitedManCode=voLabTest.getFinalMandCode().split("&");
					
					if(!voLabTest.getFinalMandCode().equals("undefined")&&!voLabTest.getFinalMandCode().equals("null")&&!voLabTest.getFinalMandCode().equals(""))
					{
					for(int i=0;i<SplitedValues.length;i++)
					{
					voReq.setFinalMandValues(SplitedValues[i]);
					 
					voReq.setMandCode(splitedManCode[i]);
					
						invEssentialDAO.insertHivtRequsitionTestMandatoryDtl(voReq,_userVO);	//procedure to insert into req_test_mand_dtl
					}
					
					}
					
					
					/////////////////////////////////////*******************using autogen values*******************************///////////////////////////////////
					
					if((voLabTest.getSampleNoConfig().equals("1")||voLabTest.getSampleNoConfig().equals("2"))) 
					{

						//for(Inv_SampleCollectionVO autoSampleVo:lstAutoSampleNOConfig)
						//{
						
						 
							
							String Str=voLabTest.getSampleNoFormat();
							//  String Str = new String(autoLabNumber);
							int MainStrlength=Str.length();
							String[] dateFormate = Str.split("&");
							String subDateFormate=dateFormate[0];
							String xMainValue=dateFormate[1];
							//getting the DateFormate number
							String sequence_SampleNO_yymmdd=objSampleCollectionDAO.generateSampleNoDateSequence(subDateFormate, _userVO);  // Returns   yymmdd
							String entryDate= voLabTest.getInitDate();//get the Entry Date 
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							
							 
							Date date = df.parse(entryDate);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Calendar c = Calendar.getInstance();
							c.setTime(date); // Added a entry date. into Calender
							String finalDate="";
							
							if(voLabTest.getInitType().equals("m"))
							{
								
								int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
								  
								String[] SplitDate=entryDate.split("-");
								
								 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
								 date=df.parse(finalDate);
								 
							}
							if(voLabTest.getInitType().equals("d"))
							{
								
								date = df.parse(entryDate);
								//c.add(Calendar.DATE,1); 
							}
							if(voLabTest.getInitType().equals("y"))
							{
								String[] SplitDate=entryDate.split("-");
								
								 finalDate=SplitDate[0]+"-"+12+"-"+31;
								date = df.parse(finalDate);
							}
							if(voLabTest.getInitType().equals("w"))
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
								
								//c.add(Calendar.DATE,7);  
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
							  
							String fromSer=voLabTest.getFromSeries();
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
							int gnumnoofseqdigits1=Integer.parseInt(voLabTest.getNoOfSeqDigit());
							 String subStrvalueLab1=xVal.substring(Math.max(0,xValLen - gnumnoofseqdigits1));
							 
							 String runningLabNO=objSampleCollectionDAO.checkAutoGenFormateRunningLabNoRaisingCumCollection(voLabTest, _userVO);
							 
							/* String runningLabNO=voLabTest.getRunningSampleNo();*/
							 //checkAutoGenFormateRunningLabNo(Inv_SampleCollectionVO inv_SampleCollectionVO, UserVO _UserVO)
							 if(sameSampleNO==strSampleNo)
							 {
								 voLabTest.setTemparorySampleNO(runningLabNO); 
							 }
							 else
							 {
							 
								 voLabTest.setRunningSampleNo(runningLabNO);
							 
							 if(voLabTest.getRunningSampleNo()!=null)
								{
								 String  sampleNO=voLabTest.getRunningSampleNo();
									int sampleNOLen=sampleNO.length();
									 int gnumnoofseqdigits=Integer.parseInt(voLabTest.getNoOfSeqDigit());
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
							
							String toSer=voLabTest.getToSeries();
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
							int gnumnoofseqdigits2=Integer.parseInt(voLabTest.getNoOfSeqDigit());
							 String subStrvalueLab2=xValToSer.substring(Math.max(0,xValToSerLen - gnumnoofseqdigits2));
						 
							int toSerValue = Integer.parseInt(subStrvalueLab2);

							if(EntryDatecomparWithSysDAte<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
							{	 

								if(voLabTest.getRunningSampleNo()==null)
								{

									voLabTest.setTemparorySampleNO(FinalSampleNo);
								}
								else
								{

									if(fromSerValue<toSerValue)
									{
										String  sampleNO=voLabTest.getRunningSampleNo();
										int sampleNOLen=sampleNO.length();
										 int gnumnoofseqdigits=Integer.parseInt(voLabTest.getNoOfSeqDigit());
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
										voLabTest.setTemparorySampleNO(finalSample);
									}
									else
									{
										voLabTest.setTemparorySampleNO(FinalSampleNo);
										
									}
								}

							}

							else  ////for EntryDatecomparWithSysDAte value is Positive
							{
								
								SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
								objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1ResetLabNORaisingCumCollection(voLabTest,finalEntryDate,_userVO);
								voLabTest.setTemparorySampleNO(FinalSampleNo);
								Date todayDateob = new Date();
								SimpleDateFormat dateobj = new SimpleDateFormat("yyyy-MM-dd");
								String strSysDate= dateob.format(todayDateob);
								Date todayDat =dateobj.parse(strSysDate) ; 
								Calendar c1 = Calendar.getInstance();
								c1.setTime(todayDat); // Now use SYDATE date.

								if(voLabTest.getInitType().equals("m"))
								{
									int daysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
									  
									String[] SplitDate=strSysDate.split("-");
									
									 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
									 todayDat=dateobj.parse(finalDate);
								}
								if(voLabTest.getInitType().equals("d"))
								{
									todayDat=dateobj.parse(strSysDate);
								}
								if(voLabTest.getInitType().equals("y"))
								{
									String[] SplitDate=strSysDate.split("-");
									
									 finalDate=SplitDate[0]+"-"+12+"-"+31;
									 todayDat = dateobj.parse(finalDate);
								}
								if(voLabTest.getInitType().equals("w"))
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
							SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
							
							
							//-----------------------------------------------area wise sample no temp!
							if(voLabTest.getTempSampleNo().equals("2"))
							if(collAreaBasedSampleNo.equals(""))
							{
							collAreaBasedSampleNo=voLabTest.getTemparorySampleNO();
						//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
							}
							else
								voLabTest.setTemparorySampleNO(collAreaBasedSampleNo);
							
							
							//--------------------------------------appended above in sample no generation area wise. 
							//-=------------------------------------ remove comment is above block of collAreaBasedSampleNo removed
							objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1RaisingCumCollection(voLabTest,finalEntryDate,_userVO);
							
							
						//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1RaisingCumCollection(voLabTest,finalEntryDate,_userVO);
							
							
							
							/*	//-----------------------------------------------area wise sample no temp!
									if(voSample.getTempSampleNo().equals("2"))
									if(collAreaBasedSampleNo.equals(""))
									{
									collAreaBasedSampleNo=voSample.getTemparorySampleNO();
								//	objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
									}
									else
										voSample.setTemparorySampleNO(collAreaBasedSampleNo);
									
									
									//--------------------------------------appended above in sample no generation area wise. 
									//-=------------------------------------ remove comment is above block of collAreaBasedSampleNo removed
									objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);*/
						//}for Loop End
						// Update Requisition Dtl  Table 
						//Setting Requisition Dtl Status as '3' for packing list generation
							 }
							 voLabTest.setRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST); //3
							 voLabTest.setSampleNo(strSampleNo);
							 voLabTest.setTempSampleNo(voLabTest.getTemparorySampleNO());
						
						//	 invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table

						

						sameSampleNO=strSampleNo;
					}
					else{
						 voLabTest.setRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PACKING_LIST); //3
						 voLabTest.setSampleNo(strSampleNo);
						// invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table
					}
					
					//////////////////////////////////*************************using ends*****************************////////////////////////
				
					//Billing Logic
					if(voBillingDtl.getRequisitionNos()==null)
					{
						voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
						voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
						voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
					}
					else
					{
					
					if(!voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!"))
					{
					voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
					voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
					voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
					}
					
					}
					/*	//Billing Logic
								if(voBillingDtl.getRequisitionNos()==null)
								{
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								else
								{
								if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
								{
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								}*/
					
					
					
					
					
					
					
					
					
						if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
						{
							if(voLabTest.getTestGroupType()==null ||voLabTest.getTestGroupType().equals("")||voLabTest.getTestGroupCode().equals("0")||voLabTest.getTestGroupType().equals("3"))
							{
								if(voBillingDtl.getTariffDetails()==null)
								{
									voBillingDtl.setTariffDetails(new ArrayList<String>());
									voBillingDtl.setTariffQty(new ArrayList<String>());
								}
								
								voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
								voBillingDtl.getTariffQty().add("1");
								
								
							}
							else
							{
								if(voBillingDtl.getGrouptariffDetails()==null)
								{
									voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
									voBillingDtl.setGrouptariffQty(new ArrayList<String>());
								}
								
								voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
								voBillingDtl.getGrouptariffQty().add("1");
							}
						}
			
						//Adding Details for Display after Save
						labName=voLabTest.getLabName();
						if(!testName.equals(""))
						  testName=testName+","+voLabTest.getTestName();
						else
							testName=voLabTest.getTestName();
					
						tempSampleNo=voLabTest.getTempSampleNo();
						sampleNo=voLabTest.getSampleNo();
						sampleName=voLabTest.getSampleName();
						
						
						//Adding generated sampleNo dtls for displaying
						if(!listReqId.contains(crNO+"#"+sampleName+"#"+tempSampleNo+"#"+sampleNo+"#"+labName+"#"+reqDate+"#"+tempSampleNo+"#"+sampleName)) 
							listReqId.add(crNO+"#"+sampleName+"#"+tempSampleNo+"#"+sampleNo+"#"+labName+"#"+reqDate+"#"+tempSampleNo+"#"+sampleName);
				}
				} // Loop Over TestCodes
				
			//Add The Requisition Number in list
				
				//
			
		
		}
		
		
		// Billing Logic :: Save
		if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
		{
					String simpletariffdetails="";
					String simpletariffQty="";
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
					System.out.println(simpletariffdetails);
					String grouptariffdetails="";
					String grouptariffQty="";
					if(voBillingDtl.getGrouptariffDetails()!=null)
					{
						for(int indexCounter=0;indexCounter<voBillingDtl.getGrouptariffDetails().size();indexCounter++)
						{
							if(indexCounter==0)
							{
								grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter);
								grouptariffQty=voBillingDtl.getGrouptariffQty().get(indexCounter);
							}
							else
							{
								if(!grouptariffdetails.equals(voBillingDtl.getGrouptariffDetails().get(indexCounter)))
								{
								grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
								grouptariffQty+="^"+voBillingDtl.getGrouptariffQty().get(indexCounter);
							}
							}
							
							
						}
					
					}
					
					if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
					{
				//		 invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
					}
					
					if(grouptariffdetails.equals("")==false)
					{
				//		invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
					}
		}	
		//Billing Save Logic End
			
		
		
		
		
		//****************************loop to fetch bill no and add it in respective vo and then saving data in req dtl*****************************************
		/*
		itrLab=mp_lab.keySet().iterator();
		
		while(itrLab.hasNext())//for(int i=0;i<size;i++)
		{
			testName="";
			
			String labCode=(String)itrLab.next();
			
							   
		    Map<String,List<LabTestVO>> mp_sample=mp_lab.get(labCode);
			   
			
		     
		    Iterator itrSample=mp_sample.keySet().iterator();
		     	
							
			while(itrSample.hasNext())																	//for(int j=0;j<sizeTest;j++)
			{
					String sampleCode=(String)itrSample.next();
					
					
			 
					
					
					//then fetch vo from listvo one by one and generate reqdno for each test
					List<LabTestVO> lstLabTestVO=mp_sample.get(sampleCode);
					
					
					
					// Loop over VO for saving
					
					for(LabTestVO voLabTest:lstLabTestVO)
					{
				
					//Logic for generating running sequence 'XX'
						
						String requisitionNo=voLabTest.getReqNo();
						String reqDno=voLabTest.getReqDno();
						
						String reqType="";
						if(patVO.getPatStatus().equals("IPD"))
						{
						
						 reqType=InvestigationConfig.REQUISITION_TYPE_IPD; //1
						// isBilled=true;
						
						}
						else if(patVO.getPatStatus().equals("OPD"))
						reqType=InvestigationConfig.REQUISITION_TYPE_OPD; //2
						else
						reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3
					
						List<Inv_SampleCollectionVO> lstTestBased= new ArrayList<Inv_SampleCollectionVO>();
					
					
					///////////////////////////////////////billing logic starts///////////////////////////////////////////////////////////////
					
						lstTestBased =objSampleCollectionDAO.getBilledPatListForRaisingCumCollection(voLabTest,reqType,_userVO);
						if(lstTestBased!=null)
						{
							for(Inv_SampleCollectionVO voSampleCollection:lstTestBased)
							{
								String sample_Code=voSampleCollection.getSampleCode()==null?sampleCode:voSampleCollection.getSampleCode();
								String reqdno=voSampleCollection.getRequisitionDNo();

								
										if(!(reqDno!=reqdno))
										{
											String billNo=voSampleCollection.getBillDetail().replace("^", "#").split("#")[0];
											if(!billNo.equals("0")&& billNo!=null)
											{
												
												voSampleCollection.setBillNo(billNo);
												voLabTest.setBillNo(billNo);
											}
											
										}
										
							}
						}
					
						
						
					//////////////////////////////////////billing logic ends///////////////////////////////////////////////////////////////
					
					invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table
					
					
					
						
				}
				} // Loop Over TestCodes
				
			
				
		}
		*/
		
		
		
			
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




/////////////////////////////////////////////







public  List insertRaisingCumSelectionInReqDtl(Map<String,Map<String,List<LabTestVO>>> mp_lab,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List listReqId=new ArrayList();
	String labName="";
	String testName="";
	String sampleName="";
	try
	{    
		tx.begin();
		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);
		
		InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
		
	
		
		Iterator itrLab=mp_lab.keySet().iterator();
				
		//****************************loop to fetch bill no and add it in respective vo and then saving data in req dtl*****************************************
				
		while(itrLab.hasNext())//for(int i=0;i<size;i++)
		{
			testName="";
			
			String labCode=(String)itrLab.next();
			
							   
		    Map<String,List<LabTestVO>> mp_sample=mp_lab.get(labCode);
			   
			
		     
		    Iterator itrSample=mp_sample.keySet().iterator();
		     	
							
			while(itrSample.hasNext())																	//for(int j=0;j<sizeTest;j++)
			{
					String sampleCode=(String)itrSample.next();
					
					
					boolean isSampleDtlInsert=false;
					
					
					//then fetch vo from listvo one by one and generate reqdno for each test
					List<LabTestVO> lstLabTestVO=mp_sample.get(sampleCode);
					
					
					
					// Loop over VO for saving
					
					for(LabTestVO voLabTest:lstLabTestVO)
					{
				
					//Logic for generating running sequence 'XX'
						
						String requisitionNo=voLabTest.getReqNo();
						String reqDno=voLabTest.getReqDno();
						  
						String reqType="";
						if(patVO.getPatStatus().equals("IPD"))
						{
						
						 reqType=InvestigationConfig.REQUISITION_TYPE_IPD; //1
						// isBilled=true;
						
						}
						else if(patVO.getPatStatus().equals("OPD"))
						reqType=InvestigationConfig.REQUISITION_TYPE_OPD; //2
						else
						reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3
					
						List<Inv_SampleCollectionVO> lstTestBased= new ArrayList<Inv_SampleCollectionVO>();
					
					
					///////////////////////////////////////billing logic starts///////////////////////////////////////////////////////////////
					
//						lstTestBased =objSampleCollectionDAO.getBilledPatListForRaisingCumCollection(voLabTest,reqType,_userVO);
//						if(lstTestBased!=null)
//						{
//							for(Inv_SampleCollectionVO voSampleCollection:lstTestBased)
//							{
//								String sample_Code=voSampleCollection.getSampleCode()==null?sampleCode:voSampleCollection.getSampleCode();
//								//String reqdno=voSampleCollection.getRequisitionDNo();
//
//								
//										
//											String billNo=voSampleCollection.getBillDetail().replace("^", "#").split("#")[0];
//											if(!billNo.equals("0")&& billNo!=null)
//											{
//												
//												voSampleCollection.setBillNo(billNo);
//												voLabTest.setBillNo(billNo);
//											}
//											
//										
//										
//							}
//						}
					
						
						
					//////////////////////////////////////billing logic ends///////////////////////////////////////////////////////////////
					
					invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table
					
					
					//insertion in sample details table 
					if(!isSampleDtlInsert)
					{
						objSampleCollectionDAO.insertSampleDtlRaisingCumCollection(voLabTest, _userVO);
						isSampleDtlInsert=true;
					}
					
					
		/*			String tariffId="";
					String serviceId="";
					//Update Billing
					if(voLabTest.getTestGroupType() == null)
					{
						tariffId = voLabTest.getTestCode();
						serviceId = "1";
					}
					else if(voLabTest.getTestGroupType().equals("1") || voLabTest.getTestGroupType().equals("2"))
					{
						tariffId = voLabTest.getLabCode()+voLabTest.getTestGroupType();
						serviceId = "4";
					}  
					else
					{
						tariffId = voLabTest.getTestCode();
						serviceId = "1";
					}

					voBillingDtl.setBillNo(voLabTest.getBillNo());
					voBillingDtl.setConsQty("1"); // Need to discuss
					voBillingDtl.setTariffId(tariffId);
					voBillingDtl.setServiceId(serviceId);*/

					//invBillingDAO.updateBillingQty(voBillingDtl, tariffId, serviceId, _userVO);
						
				}
				} // Loop Over TestCodes
				
			
				
		}
		
		
		
		
			
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




/* Function to search Laboatory wise Test Details for Raising cum collection */
public Map searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstLabTest=null;
	List<LabTestVO> lstLabTestSample=null;
	List<LabTestVO> lstLabTestForTestGruop=null;
	String labNames="";
	String testNames="";
	String testCode="";
	String groupCodes="";
	String defaultUOM="";
	String defaultContainer="";
	String defaultQuantity="";
	String uomComboStr="<option value='-1'>Select Value</option>";
	String containerComboStr="<option value='-1'>Select Value</option>";
	List lstUserGroupCode=null;
	//List<LabTestVO> lstPreviousLabTest=null;
	StringBuilder userGroupCodeBuild = new StringBuilder();
	Map mp=new HashMap();
	StringBuilder labBuild = new StringBuilder();
	StringBuilder testBuild = new StringBuilder();
	StringBuilder testCodeBuild = new StringBuilder();
	
	List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
	List<String> testGroupCode=new ArrayList<String>();

	List lstUOMCombo=null;
	List lstContainerCombo=null;
	String sampleValues="";
	
	try {

		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		lstLabTest=invEssentialDAO.searchLabWiseTestDtlsRaisingCumCollection(searchVO, _UserVO);
		lstLabTestSample=invEssentialDAO.searchLabTestSampleRaisingCumCollection(searchVO, _UserVO);
		lstUserGroupCode=invEssentialDAO.fetchUserGroupCodeCumColl(searchVO, _UserVO);		
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
		lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo
		
		
		labBuild.append("");
		//String sampleComboStr="<option value='-1'>Select Value</option>";

		
		
		//*********************************logic to create various combo***********************************//
		
		for(LabTestVO vo:lstLabTest)
		{
			
			sampleValues="";
			
			 uomComboStr="<option value='-1'>Select Value</option>";
			 containerComboStr="<option value='-1'>Select Value</option>";
			vo.setSampleAreaCode(searchVO.getSampleNo());//fetch sample area code. need to be discussed
			
			//******creating lab name combo
			if(labBuild.indexOf(vo.getLabCode())==-1)
			{
			labBuild.append("{ label: \""+vo.getLabName()+"\" ,  value: \""+vo.getLabCode()+"\" }");
			labBuild.append(",");
			}
			//******creating test name combo
			testBuild.append("{ label: \""+vo.getTestName()+"\" ,  value: \""+vo.getTestCode()+"\" }");
			testBuild.append(",");
			
			//******creating test code combo
			if(vo.getUserTestCode()!=null)
			{
				vo.setTestName(vo.getTestName()+" ("+vo.getUserTestCode()+")");
			testCodeBuild.append("{ label: \""+vo.getUserTestCode()+"\" ,  value: \""+vo.getUserTestCode()+"\" }");
			testCodeBuild.append(",");
			}
			

			
			
			
			String strSAmpleCode = vo.getDefaultSampleCode();	//default sample code value
			String sampleComboStr="<option value='-1'>Select Value</option>";
			String singleUomCode="";
			String singleContainerCode="";
			String singleQuantity="";
			
			//filter out specific samples from all lab test samples
			int noOfSamples=0;
			String singleSampleName="";
			String singleSampleCode="";
			for(LabTestVO sample_vo:lstLabTestSample)
			{					
				if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
				{
					noOfSamples++;
					sampleValues+=sample_vo.getsCode()+"&"+sample_vo.getUomCode()+"&"+sample_vo.getContainerCode()+"&"+sample_vo.getDefaultContainerVol()+"*";
					singleSampleName=sample_vo.getsName();
					singleSampleCode=sample_vo.getsCode();
					singleUomCode=sample_vo.getUomCode();
					singleContainerCode=sample_vo.getContainerCode();
					singleQuantity=sample_vo.getDefaultContainerVol();
					if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
					{	
						sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setsName(sample_vo.getsName());
						defaultContainer=sample_vo.getContainerCode();
						defaultUOM=sample_vo.getUomCode();
						defaultQuantity=sample_vo.getDefaultContainerVol();
					}
					else
						sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
				}
												
			}
			if(noOfSamples==1)//single sample
			{
				
				sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
				vo.setSingleSample(singleSampleCode);
				vo.setsName(singleSampleName);
				defaultContainer=singleContainerCode;
				defaultUOM=singleUomCode;
				defaultQuantity=singleQuantity;
			}
			
			vo.setSampleValues(sampleValues);
			
			//getting (samplecode#samplename,sam.....) string 
			/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
			
			if(sampleString.equals("")==false)
			{
				String splitValues[] = sampleString.split(",");
				
				for(String sampleCodeName: splitValues)
				{
					if(splitValues.length==1)
					{
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						vo.setSingleSample(sampleCodeName.split("#")[0]);
					}
					else
					{
					if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
					else
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
					}
					
					
				}
				
				
				
			}
			*/
			
			
			
			
			
			//******default container quantity
			vo.setDefaultContainerVol(defaultQuantity);
			
			//******creating uom code combo
			if(lstUOMCombo!=null && lstUOMCombo.size()>0)
			{
				Iterator lstIterator=lstUOMCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					if(entry.getValue().equalsIgnoreCase(defaultUOM))
						uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
				
				
			}
			
			vo.setUomComboStr(uomComboStr);
			
			
			//******creating container code combo
			if(lstContainerCombo!=null && lstContainerCombo.size()>0)
			{
				Iterator lstIterator=lstContainerCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					if(entry.getValue().equalsIgnoreCase(defaultContainer))
						containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
				
				
			}
			vo.setContainerComboStr(containerComboStr);
			
			
			
			
			
			
			//Sample Combo Logic
			if(vo.getTestType()!=null)
			{
			if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
				vo.setSampleComboStr("");
			else      // Sample and Slide Based
				vo.setSampleComboStr(sampleComboStr);
			}
			else
				vo.setSampleComboStr(sampleComboStr);
			
			//**********************calling query to get samples for each lab test by hitting database for each pair**********//
			
/*			List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
			if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
			{
				Iterator lstIterator=lstSampleCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					
					if(lstSampleCombo.size()==1)
						
					{
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						vo.setSingleSample(entry.getValue());
					}
					else
					{
					if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
					}
					}
			}
*/
			
			
			
			
			
			//Mandatory Combo/text Logic
			String ismandInfo=vo.getIsMandatoryReq();
			String mandInfo=vo.getMandInfo();
			
              
			if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
				
			{
				String[] mandInfoCommaSeparator=mandInfo.split(",");
				
				int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
				String textBoxAndCombo="";
				String  textBoxComboNames="";
				
				String  textBoxComboCode="";
				
				for(int i=0;i<mandInfoCommaSeparatorlength;i++)
				{
					String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
					
					 
					
					 
						String mandCode=maninfoHashSeparator[0];
						
						String mandName=maninfoHashSeparator[1];
						
						String mandType=maninfoHashSeparator[2];
					
						
						if(mandType.equals("1"))
						{
						
							if(textBoxAndCombo.equals(""))
							{
								textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
								
								textBoxComboNames=mandName;
								textBoxComboCode=mandCode;
							}
							else
							{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
							    
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
							    
							}
							
						}
						if(mandType.equals("2"))
						{
							
							if(textBoxAndCombo.equals(""))
							{
								textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								
								textBoxComboNames=mandName;
								
								textBoxComboCode=mandCode;
							}
							else
							{
							textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
							
							textBoxComboNames=textBoxComboNames+"&"+mandName;
							
							textBoxComboCode=textBoxComboCode+"&"+mandCode;
							}
							String mandCombo=vo.getMandCombo();
							
							String[] mandComboCommaSeparator=mandCombo.split(",");
							
							int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
							
							for(int k=0;k<mandComboCommaSeparatorlength;k++)
							{
								String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
									
								String mandComboCode=manComboHashSeparator[0];
								String mandComboName=manComboHashSeparator[1];
								
								if(mandCode.equals(mandComboCode))
								{
									textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
								}
								
							}
							textBoxAndCombo=textBoxAndCombo+"</select>";
							
						}
						
						
					 
							vo.setSetMandTextBoxCombo(textBoxAndCombo);
							vo.setMandComboTextBoxComboNames(textBoxComboNames);
							
							vo.setMandCode(textBoxComboCode);

				 
				}
			}
			       
			
			
			
			 

			//lstPreviousLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
			if(searchVO.getSearchTestGroupName()!=null&&searchVO.getSearchTestGroupName()!="")
			{
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				
				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				mp.put(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS,lstLabTest);
				
				if(!testGroupCode.contains(vo.getTestGroupCode()+vo.getLabCode()))
				{
					testGroupCode.add(vo.getTestGroupCode()+vo.getLabCode());
					lstSingleTestGroupDetail.add(vo);
					
					
					mp.put(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS,lstSingleTestGroupDetail);
					
				}
				
				
			}
			else
{
				mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
}
		//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstPreviousLabTest);
      
		}
		
		//create user group code json
		if(lstUserGroupCode!=null)
		{
			for(Entry obj:(List<Entry>)lstUserGroupCode)
			{
			
				userGroupCodeBuild.append("{ label: \""+obj.getLabel()+"\" ,  value: \""+obj.getValue()+"\" }");
				userGroupCodeBuild.append(",");
					
				
			}
		}
		
		labBuild.deleteCharAt(labBuild.length()-1);
		labNames="["+labBuild.toString()+"]";
		
		testBuild.deleteCharAt(testBuild.length()-1);
		testNames="["+testBuild.toString()+"]";
		
		testCodeBuild.deleteCharAt(testCodeBuild.length()-1);
		testCode="["+testCodeBuild.toString()+"]";
		
		userGroupCodeBuild.deleteCharAt(userGroupCodeBuild.length()-1);
		groupCodes="["+userGroupCodeBuild.toString()+"]";
		
		
		if(searchVO.getLabEmpty().equals("1"))
		mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);
		if(searchVO.getTestEmpty().equals("1"))
		mp.put(InvestigationConfig.ARRAY_TESTNAMES, testNames);
		if(searchVO.getTestCodeEmpty().equals("1"))
		mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCode);
		if(searchVO.getGroupCodeEmpty().equals("1"))
		mp.put(InvestigationConfig.ARRAY_GROUP_CODE_WISE, groupCodes);
		
		
		
		
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


public Map searchBookMarkRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstLabTest=null;
	List<LabTestVO> lstLabTestSample=null;
	String defaultUOM="";
	String defaultContainer="";
	String defaultQuantity="";
	String uomComboStr="<option value='-1'>Select Value</option>";
	String containerComboStr="<option value='-1'>Select Value</option>";
	String sampleValues="";
	List lstUOMCombo=null;
	List lstContainerCombo=null;
	Map mp=new HashMap();

	try {

		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		lstLabTest=invEssentialDAO.searchBookMarkRaisingCumCollection(searchVO, _UserVO);
		lstLabTestSample=invEssentialDAO.searchLabTestSampleRaisingCumCollection(searchVO, _UserVO);

		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
		lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo

		//Logic for Getting Sample Combo
		for(LabTestVO vo:lstLabTest)
		{
			sampleValues="";
			 uomComboStr="<option value='-1'>Select Value</option>";
			 containerComboStr="<option value='-1'>Select Value</option>";
			String strSAmpleCode = vo.getDefaultSampleCode();
			String sampleComboStr="<option value='-1'>Select Value</option>";
			String singleUomCode="";
			String singleContainerCode="";
			String singleQuantity="";
			 uomComboStr="<option value='-1'>Select Value</option>";
			 containerComboStr="<option value='-1'>Select Value</option>";
			//filter out specific samples from all lab test samples
			int noOfSamples=0;
			String singleSampleName="";
			String singleSampleCode="";
			for(LabTestVO sample_vo:lstLabTestSample)
			{					
				if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
				{
					noOfSamples++;
					sampleValues+=sample_vo.getsCode()+"&"+sample_vo.getUomCode()+"&"+sample_vo.getContainerCode()+"&"+sample_vo.getDefaultContainerVol()+"*";
					singleSampleName=sample_vo.getsName();
					singleSampleCode=sample_vo.getsCode();
					singleUomCode=sample_vo.getUomCode();
					singleContainerCode=sample_vo.getContainerCode();
					singleQuantity=sample_vo.getDefaultContainerVol();
					
					if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
						{
						
						sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setsName(sample_vo.getsName());
						defaultContainer=sample_vo.getContainerCode();
						defaultUOM=sample_vo.getUomCode();
						defaultQuantity=sample_vo.getDefaultContainerVol();
						
						}
					else
						sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
				}
												
			}
			if(noOfSamples==1)//single sample
			{
				
				sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
				vo.setSingleSample(singleSampleCode);
				vo.setsName(singleSampleName);
				defaultContainer=singleContainerCode;
				defaultUOM=singleUomCode;
				defaultQuantity=singleQuantity;
			}
			
			
			
			//******default container quantity
			vo.setDefaultContainerVol(defaultQuantity);
			
			//******creating uom code combo
			if(lstUOMCombo!=null && lstUOMCombo.size()>0)
			{
				Iterator lstIterator=lstUOMCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					if(entry.getValue().equalsIgnoreCase(defaultUOM))
						uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
				
				
			}
			
			vo.setUomComboStr(uomComboStr);
			
			
			//******creating container code combo
			if(lstContainerCombo!=null && lstContainerCombo.size()>0)
			{
				Iterator lstIterator=lstContainerCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					if(entry.getValue().equalsIgnoreCase(defaultContainer))
						containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
				
				
			}
			vo.setContainerComboStr(containerComboStr);
			
			
			vo.setSampleValues(sampleValues);
			//getting (samplecode#samplename,sam.....) string 
		/*	String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
			if(sampleString.equals("")==false)
			{
				String splitValues[] = sampleString.split(",");
				
				for(String sampleCodeName: splitValues)
				{
					if(splitValues.length==1)
					{
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						vo.setSingleSample(sampleCodeName.split("#")[0]);
					}
					else
					{
					if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
					else
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
					}
					
					
				}
				
				
				
			}*/
			
			//Sample Combo Logic
			if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
				vo.setSampleComboStr("");
			else      // Sample and Slide Based
				vo.setSampleComboStr(sampleComboStr);

			
			
			
			
			
			/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
			if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
			{
				Iterator lstIterator=lstSampleCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					
					
					if(lstSampleCombo.size()==1)
					{
						
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						vo.setSingleSample(entry.getValue());
					}
					else
					{
					
					
					if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
					}
					}
			}
*/
			

		 
			String ismandInfo=vo.getIsMandatoryReq();
			String mandInfo=vo.getMandInfo();
			
              
			if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
				
			{
				String[] mandInfoCommaSeparator=mandInfo.split(",");
				
				int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
				String textBoxAndCombo="";
				
				String textBoxComboNames="";
				
				String  textBoxComboCode="";
				
				for(int i=0;i<mandInfoCommaSeparatorlength;i++)
				{
					String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
					
					 
					
					 
						String mandCode=maninfoHashSeparator[0];
						
						String mandName=maninfoHashSeparator[1];
						
						String mandType=maninfoHashSeparator[2];
					
						
						if(mandType.equals("1"))
						{
						
							if(textBoxAndCombo.equals(""))
							{
								textBoxAndCombo="<b>"+mandName+":</b>  <input type=\"text\" name=\""+mandName+"\" />";
							   	
								textBoxComboNames=mandName;
								
								textBoxComboCode=mandCode;
								
							}
							else
							{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type=\"text\" name=\""+mandName+"\" />";
							
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
							}
							
						}
						if(mandType.equals("2"))
						{
							
							if(textBoxAndCombo.equals(""))
							{
								textBoxAndCombo="<b>"+mandName+":</b>  <select name=\""+mandName+"\"><option value=\"-1\">Select Value</option>";
								
								textBoxComboNames=mandName;
								
								textBoxComboCode=mandCode;
								
							}
							else
							{
							textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name=\""+mandName+"\"><option value=\"-1\">Select Value</option>";
							
							textBoxComboNames=textBoxComboNames+"&"+mandName;
							
							textBoxComboCode=textBoxComboCode+"&"+mandCode;
							}
							String mandCombo=vo.getMandCombo();
							
							String[] mandComboCommaSeparator=mandCombo.split(",");
							
							int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
							
							for(int k=0;k<mandComboCommaSeparatorlength;k++)
							{
								String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
									
								String mandComboCode=manComboHashSeparator[0];
								String mandComboName=manComboHashSeparator[1];
								
								if(mandCode.equals(mandComboCode))
								{
									textBoxAndCombo=textBoxAndCombo+"<option value=\""+mandComboName+"\">"+mandComboName+"</option>";	
								}
								
							}
							textBoxAndCombo=textBoxAndCombo+"</select>";
							
						}
						
						
					 
							vo.setSetMandTextBoxCombo(textBoxAndCombo);
							
							vo.setMandComboTextBoxComboNames(textBoxComboNames);
							vo.setMandCode(textBoxComboCode);

				 
				}
			}
			    

			 

		}


		mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK,lstLabTest);


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




/* Function to search Laboatory wise Test Details */
public Map searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstLabTest=null;
	List<LabTestVO> lstLabTestSample=null;
	
	String defaultUOM="";
	String defaultContainer="";
	String defaultQuantity="";
	String uomComboStr="<option value='-1'>Select Value</option>";
	String containerComboStr="<option value='-1'>Select Value</option>";
	String sampleValues="";
	List lstUOMCombo=null;
	List lstContainerCombo=null;
	
	

	List<LabTestVO> lstLabTestForTestGruop=new ArrayList<LabTestVO>();
	//List<LabTestVO> lstPreviousLabTest=null;
	
	Map mp=new HashMap();
	
	
	List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
	List<String> testGroupCode=new ArrayList<String>();

	try {

		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		lstLabTest=invEssentialDAO.searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(searchVO, _UserVO);
		lstLabTestSample=invEssentialDAO.searchLabTestSampleRaisingCumCollection(searchVO, _UserVO);
		
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
		lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo

		//String sampleComboStr="<option value='-1'>Select Value</option>";

		//Logic for Getting Sample Combo
		for(LabTestVO vo:lstLabTest)
		{
			sampleValues="";
			vo.setSampleAreaCode(searchVO.getSampleNo());
			 uomComboStr="<option value='-1'>Select Value</option>";
			 containerComboStr="<option value='-1'>Select Value</option>";
			String strSAmpleCode = vo.getDefaultSampleCode();
			String sampleComboStr="<option value='-1'>Select Value</option>";
			String singleUomCode="";
			String singleContainerCode="";
			String singleQuantity="";
			
			
			//filter out specific samples from all lab test samples
			int noOfSamples=0;
			String singleSampleName="";
			String singleSampleCode="";
			for(LabTestVO sample_vo:lstLabTestSample)
			{					
				if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
				{
					noOfSamples++;
					sampleValues+=sample_vo.getsCode()+"&"+sample_vo.getUomCode()+"&"+sample_vo.getContainerCode()+"&"+sample_vo.getDefaultContainerVol()+"*";
					singleSampleName=sample_vo.getsName();
					singleSampleCode=sample_vo.getsCode();
					singleUomCode=sample_vo.getUomCode();
					singleContainerCode=sample_vo.getContainerCode();
					singleQuantity=sample_vo.getDefaultContainerVol();
					
					if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
					{	
						
						sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"' selected>"+sample_vo.getsName()+"</option>";
						vo.setsName(sample_vo.getsName());
						defaultContainer=sample_vo.getContainerCode();
					defaultUOM=sample_vo.getUomCode();
					defaultQuantity=sample_vo.getDefaultContainerVol();
					
					
					}
					else
						sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
				}
												
			}
			if(noOfSamples==1)//single sample
			{
				
				sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
				vo.setSingleSample(singleSampleCode);
				vo.setsName(singleSampleName);
				defaultContainer=singleContainerCode;
				defaultUOM=singleUomCode;
				defaultQuantity=singleQuantity;
			}
			
			
			//******default container quantity
			vo.setDefaultContainerVol(defaultQuantity);
			
			//******creating uom code combo
			if(lstUOMCombo!=null && lstUOMCombo.size()>0)
			{
				Iterator lstIterator=lstUOMCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					if(entry.getValue().equalsIgnoreCase(defaultUOM))
						uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
				
				
			}
			
			vo.setUomComboStr(uomComboStr);
			
			
			//******creating container code combo
			if(lstContainerCombo!=null && lstContainerCombo.size()>0)
			{
				Iterator lstIterator=lstContainerCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					if(entry.getValue().equalsIgnoreCase(defaultContainer))
						containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
				}
				
				
			}
			vo.setContainerComboStr(containerComboStr);
			vo.setSampleValues(sampleValues);
			//getting (samplecode#samplename,sam.....) string 
			/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
			
			if(sampleString.equals("")==false)
			{
				String splitValues[] = sampleString.split(",");
				
				for(String sampleCodeName: splitValues)
				{
					if(splitValues.length==1)
					{
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
						vo.setSingleSample(sampleCodeName.split("#")[0]);
					}
					else
					{
					if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
					else
						sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
					}
					
					
				}
				
				
				
			}
			*/
			//Sample Combo Logic
			if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
				vo.setSampleComboStr("");
			else      // Sample and Slide Based
				vo.setSampleComboStr(sampleComboStr);
			
			
			
			
			/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
			if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
			{
				Iterator lstIterator=lstSampleCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					
					if(lstSampleCombo.size()==1)
					{
						
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
						vo.setSingleSample(entry.getValue());
					}
					else
					{
					if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
					else
						sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
					}
					}
			}
*/
			

			//Mandatory Combo/text Logic
			String ismandInfo=vo.getIsMandatoryReq();
			String mandInfo=vo.getMandInfo();
			
              
			if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO)&&mandInfo!=null)
				
			{
				String[] mandInfoCommaSeparator=mandInfo.split(",");
				
				int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
				String textBoxAndCombo="";
				String  textBoxComboNames="";
				
				String  textBoxComboCode="";
				
				for(int i=0;i<mandInfoCommaSeparatorlength;i++)
				{
					String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
					
					 
					
					 
						String mandCode=maninfoHashSeparator[0];
						
						String mandName=maninfoHashSeparator[1];
						
						String mandType=maninfoHashSeparator[2];
					
						
						if(mandType.equals("1"))
						{
						
							if(textBoxAndCombo.equals(""))
							{
								textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
								
								textBoxComboNames=mandName;
								textBoxComboCode=mandCode;
							}
							else
							{
								textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
							    
								textBoxComboNames=textBoxComboNames+"&"+mandName;
								
								textBoxComboCode=textBoxComboCode+"&"+mandCode;
							    
							}
							
						}
						if(mandType.equals("2"))
						{
							
							if(textBoxAndCombo.equals(""))
							{
								textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
								
								textBoxComboNames=mandName;
								
								textBoxComboCode=mandCode;
							}
							else
							{
							textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
							
							textBoxComboNames=textBoxComboNames+"&"+mandName;
							
							textBoxComboCode=textBoxComboCode+"&"+mandCode;
							}
							String mandCombo=vo.getMandCombo();
							
							String[] mandComboCommaSeparator=mandCombo.split(",");
							
							int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
							
							for(int k=0;k<mandComboCommaSeparatorlength;k++)
							{
								String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
									
								String mandComboCode=manComboHashSeparator[0];
								String mandComboName=manComboHashSeparator[1];
								
								if(mandCode.equals(mandComboCode))
								{
									textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
								}
								
							}
							textBoxAndCombo=textBoxAndCombo+"</select>";
							
						}
						
						
					 
							vo.setSetMandTextBoxCombo(textBoxAndCombo);
							vo.setMandComboTextBoxComboNames(textBoxComboNames);
							
							vo.setMandCode(textBoxComboCode);

				 
				}
			}
			       
			
			
			
			 

			//lstPreviousLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
			
			if(vo.getSearchTestGroup()!=null)
			{
				lstLabTestForTestGruop.add(vo);
				mp.put(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS,lstLabTestForTestGruop);
				
				if(!testGroupCode.contains(vo.getTestGroupCode()+vo.getLabCode()))
				{
					testGroupCode.add(vo.getTestGroupCode()+vo.getLabCode());
					lstSingleTestGroupDetail.add(vo);
					
					
					mp.put(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS,lstSingleTestGroupDetail);
					
				}
			}

		//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstPreviousLabTest);
      
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

	return mp;
}


public Map getAptByDesk(InvestigationSearchVO searchVO, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstAptByDesk=null;
	Map mp=new HashMap();

	try {

		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		lstAptByDesk=invEssentialDAO.getAptByDesk(searchVO, _UserVO);



		mp.put(InvestigationConfig.LIST_APT_BY_DESK,lstAptByDesk);


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

public Map getAppointment(String reqNo,String crNo, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstAptByDesk=null;
	Map mp=new HashMap();

	try {

		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		lstAptByDesk=invEssentialDAO.getAppointment(reqNo,crNo, _UserVO);



		mp.put(InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK,lstAptByDesk);


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



public  List saveAppointmentDetails(List<LabTestVO> lstLabTestVO,LabTestVO patVO,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List listReqId=new ArrayList();
	String labName="";
	String testName="";
	try
	{    
		tx.begin();
		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		
		InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);
		
		InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
					
		int requisitionTypeForBilling=0;
		
			   if(patVO.getPatStatus().equals("IPD"))
			   {  
				   requisitionTypeForBilling=2;
			   }
			   else if(patVO.getPatStatus().equals("OPD"))
			   {
				   requisitionTypeForBilling=1;
			   }
			   else
			   {
				   requisitionTypeForBilling=1;
			   }
				   
			   for(int i=0;i<lstLabTestVO.size();i++)
			   {
			   
						LabTestVO voLabTest=lstLabTestVO.get(i);
						InvestigationRequistionVO voReq=new InvestigationRequistionVO();
					
						
						voReq.setStrLabCode(voLabTest.getLabCode());
					voReq.setStrTestCode(voLabTest.getTestCode());
					voReq.setStrSampleCode(voLabTest.getSampleCode());
					voReq.setStrRequsitionDNo(voLabTest.getRequisitionDNo());
					voReq.setStrReqNo(voLabTest.getReqNo());
					voReq.setStrPriority((voLabTest.getPriority()==null?InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL:voLabTest.getPriorityCode()));
											
					voReq.setIslabappointmentbased((voLabTest.getIslabappointmentbased()));
			
					voReq.setStrAppointmentDate(voLabTest.getAppointmentDate());
					voReq.setStrAppointmentTime(voLabTest.getAppointmentTime());
											
					voReq.setAppointmentRefNo(voLabTest.getAppointmentRefNo());
					String apoitmentDAte=voLabTest.getAppointmentDate();
					
					
					invEssentialDAO.saveAppointmentDetail(voReq,_userVO);	
					
					//confirm Appoitment 
					
					if(!voLabTest.getAppoitmentNo().equals("0")&&voLabTest.getAppoitmentNo()!=null)
					{
						voReq.setAppointmentNo(voLabTest.getAppoitmentNo());
						invEssentialDAO.ConfirmAppointment(voReq, _userVO);
					}
					
					
					//Billing Logic
					/*if(!voBillingDtl.getRequisitionNos().equals(voLabTest.getReqNo()))
					{
					voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voLabTest.getReqNo()+'!');
					voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
					voBillingDtl.setDeptCode(patVO.getPatDeptCode()==null?patVO.getPatDeptUnitCode():patVO.getPatDeptCode());
					}*/
					
					//Billing Logic
					if(voBillingDtl.getRequisitionNos()==null)
					{
						voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voLabTest.getReqNo()+'!');
						voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
						voBillingDtl.setDeptCode(patVO.getPatDeptCode()==null?patVO.getPatDeptUnitCode():patVO.getPatDeptCode());
					}
					else
					{
					if(!voBillingDtl.getRequisitionNos().contains(voLabTest.getReqNo()+"!"))
					{
					voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voLabTest.getReqNo()+'!');
					voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
					voBillingDtl.setDeptCode(patVO.getPatDeptCode()==null?patVO.getPatDeptUnitCode():patVO.getPatDeptCode());
					}
					}
					
					
						if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
						{
							if(voLabTest.getTestGroupType()==null ||voLabTest.getTestGroupType().equals("")||voLabTest.getTestGroupCode().equals("0")||voLabTest.getTestGroupType().equals("3"))
							{
								if(voBillingDtl.getTariffDetails()==null)
								{
									voBillingDtl.setTariffDetails(new ArrayList<String>());
									voBillingDtl.setTariffQty(new ArrayList<String>());
								}
								
								voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
								voBillingDtl.getTariffQty().add("1");
							}
							else
							{
								if(voBillingDtl.getGrouptariffDetails()==null)
								{
									voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
									voBillingDtl.setGrouptariffQty(new ArrayList<String>());
								}
								
								voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
								voBillingDtl.getGrouptariffQty().add("1");
							}
						}
			
						//Adding Details for Display after Save
						labName=voLabTest.getLabName();
						/*if(!testName.equals(""))
						  testName=testName+","+voLabTest.getTestName();
						else
							testName=voLabTest.getTestName();*/
						
				 // Loop Over TestCodes
				
			//Add The Requisition Number in list
				listReqId.add(patVO.getPatCrNo()+"#"+""+"#"+"");
			   }
	//	**********************************************************************************************************************************
		
		
		// Billing Logic :: Save
		if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
		{
					String simpletariffdetails="";
					String simpletariffQty="";
		
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
					System.out.println(simpletariffdetails);
					String grouptariffdetails="";
					String checkDuplicateDetail="";
					boolean checkGrp=false;
					String grouptariffQty="";
					if(voBillingDtl.getGrouptariffDetails()!=null)
					{
						for(int indexCounter=0;indexCounter<voBillingDtl.getGrouptariffDetails().size();indexCounter++)
						{
							if(indexCounter==0)
							{
								grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter);
								grouptariffQty=voBillingDtl.getGrouptariffQty().get(indexCounter);
								checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
							}
							else
							{
								String[] SplitCheckDuplicateDetail=checkDuplicateDetail.split("&");
								if(SplitCheckDuplicateDetail.length>1)
									for(int y=1;y<SplitCheckDuplicateDetail.length;y++)
									{
										if(!SplitCheckDuplicateDetail[y].equals(voBillingDtl.getGrouptariffDetails().get(indexCounter)))
										{
											  checkGrp=true;
										}
										//else
										//{
											 //checkGrp=false;
										//}
								
									}
								
								if(checkGrp)
								{
								grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
								grouptariffQty+="^"+voBillingDtl.getGrouptariffQty().get(indexCounter);
								checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
							}
							}
						 
							
						}
					
					}
					
					Inv_RequisitionRaisingPatientVO reqVO=new Inv_RequisitionRaisingPatientVO();
					
										
					reqVO.setPatCategoryCode(patVO.getPatCategoryCode());
					reqVO.setPatepisodecode(patVO.getEpisodeCode());
					reqVO.setPatCRNo(patVO.getPatCrNo());
					reqVO.setPatFirstName(patVO.getPatName());
					reqVO.setPatLastName("");
					reqVO.setPatMiddleName("");
					reqVO.setPatAddress(patVO.getPatAddress());
					reqVO.setPatMobileNo(patVO.getPatMobile());
					reqVO.setPatAge(patVO.getPatAge());
					reqVO.setPatGenderCode(patVO.getPatGenderCode());
					reqVO.setConsultantName(patVO.getPatOrderByDoc());
					reqVO.setPatwardcode(patVO.getPatWardCode());
					reqVO.setPatadmissionno(patVO.getPatAdmNo());
					reqVO.setPatVisitNo(patVO.getPatVisitNo());
					
					
					
					if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
					{
						 invBillingDAO.makeBillingTestWise(voBillingDtl, reqVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
					}
					
					if(grouptariffdetails.equals("")==false)
					{
						invBillingDAO.makeBillingTestWise(voBillingDtl, reqVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
					}
		}	
		//Billing Save Logic End
		
	
	 
		
			
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



public Map getAppointmentDetailsOnClickGO(LabTestVO voLabTest, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstAptByDesk=null;
	Map mp=new HashMap();

	try {

		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		lstAptByDesk=invEssentialDAO.getAppointmentDetailsOnClickGO(voLabTest, _UserVO);


		if(lstAptByDesk!=null)
		mp.put(InvestigationConfig.LIST_APT_DETAILS_ON_CLICK_GO,lstAptByDesk);


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



			
			public Map  TestComboForAudit(InvValueAuditVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List testcombo=new ArrayList();

				try
				{
					tx.begin();
					InvValueAuditDAO onlinePatientDao = new InvValueAuditDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					testcombo=onlinePatientDao.TestComboForAudit(vo,_UserVO);
					mp.put(InvestigationConfig.TEST_COMBO_FOR_AUDIT_PROCESS, testcombo);


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
			
			
			public Map  getlistauditprocess(InvValueAuditVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvValueAuditVO> listauditprocess=new ArrayList<InvValueAuditVO>();
				List<InvValueAuditVO> listauditprocess1=new ArrayList<InvValueAuditVO>();
				try
				{
					tx.begin();
					InvValueAuditDAO onlinePatientDao = new InvValueAuditDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					listauditprocess=onlinePatientDao.getlistauditprocess(vo,_UserVO);

				
                 	mp.put(InvestigationConfig.LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO, listauditprocess);


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
			
			
			public Map  showPatDetails(InvValueAuditVO vo, UserVO _UserVO,String dNo,String testCode,String labCode)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvValueAuditVO> listauditprocess=new ArrayList<InvValueAuditVO>();

				try
				{
					tx.begin();
					InvValueAuditDAO onlinePatientDao = new InvValueAuditDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                     
					listauditprocess=onlinePatientDao.showPatDetails(vo,_UserVO,dNo,testCode,labCode);
					
					
					for(int i=0;i<listauditprocess.size();i++)
					{
						String paracode=listauditprocess.get(i).getParaCode();
						String testCodee=listauditprocess.get(i).getTestCode();
						String newvalue=listauditprocess.get(i).getNewValue();
						String prevalue=listauditprocess.get(i).getPreValue();
						 
						 //ListBox New Value check
						if(newvalue.contains("$"))
						{
							String	comboNewValueListBox="";
							String[] multiValue=newvalue.split("\\$");
							
							for(int k=0;k<multiValue.length;k++)
						comboNewValueListBox+=onlinePatientDao.setComboValueNameForNewValue(testCodee,paracode,multiValue[k])+"$";
						
							if(comboNewValueListBox!=null || !comboNewValueListBox.equals(""))
							{
								listauditprocess.get(i).setNewValue(comboNewValueListBox);
							}
						}
						// combo and auto complete box New value check
						else
						{
					    String	checkNewValue=onlinePatientDao.setComboValueNameForNewValue(testCodee,paracode,newvalue);
					    if(checkNewValue!=null)
					    {
					    	listauditprocess.get(i).setNewValue(checkNewValue);
					    }
						}
					    
						 //ListBox Pre Value check
						if(prevalue.contains("$"))
						{
							String	comboPreValueListBox="";
							String[] multiValue=prevalue.split("\\$");
							
							for(int k=0;k<multiValue.length;k++)
						comboPreValueListBox+=onlinePatientDao.setComboValueNameForPreValue(testCodee,paracode,multiValue[k])+"$";
						
							if(comboPreValueListBox!=null || !comboPreValueListBox.equals(""))
							{
								listauditprocess.get(i).setPreValue(comboPreValueListBox);
							}
						}
						// combo and auto complete box Pre value check
						else
						{
					 String checkPreValue=onlinePatientDao.setComboValueNameForPreValue(testCodee,paracode,prevalue);
						
						if(checkPreValue!=null)
					    {
					    	listauditprocess.get(i).setPreValue(checkPreValue);
					    }
						}
					}
					
					mp.put(InvestigationConfig.LIST_PATIENT_AUDIT_MODIFY_PROCESS_ESSENTIALS_VO, listauditprocess);
						

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
			
			
            //Inv Audit Process
			
			public Map  LabComboForAudit(InvValueAuditFB invInvValueAuditFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					InvValueAuditDAO onlinePatientDao = new InvValueAuditDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.LabComboForAudit(invInvValueAuditFB,_UserVO);
					mp.put(InvestigationConfig.LAB_COMBO_FOR_AUDIT_PROCESS, labcombo);


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
			
			public Map  AllTestComboForAudit(InvValueAuditVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List testcombo=new ArrayList();

				try
				{
					tx.begin();
					InvValueAuditDAO onlinePatientDao = new InvValueAuditDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					testcombo=onlinePatientDao.AllTestComboForAudit(vo,_UserVO);
					mp.put(InvestigationConfig.TEST_COMBO_FOR_AUDIT_PROCESS, testcombo);


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

			public String checkAutoGenFormateSampleAcceptance(SampleAcceptanceVO voSample, UserVO _UserVO) {

				JDBCTransactionContext tx = new JDBCTransactionContext();

				List isFormatePresent=null;

				String voSample1="";
				
		       String Formate="null";

		      // String Formate="null+#+null+#+null+#+null+#+null+#+null+#+null";
		       
				try {

					tx.begin();
					SampleAcceptanceDAO objSampleCollectionDAO=new SampleAcceptanceDAO(tx);

					voSample1=objSampleCollectionDAO.checkAutoGenFormate(voSample, _UserVO);



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

				return voSample1;
			}

             // for view external inv process
			
			
			public Map  showPatDetails(viewExternalInvVO vo, UserVO _UserVO,String reqNos)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<viewExternalInvVO> listexternaprocess=new ArrayList<viewExternalInvVO>();

				try
				{
					tx.begin();
					viewExternalInvDAO onlinePatientDao = new viewExternalInvDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                     
					listexternaprocess=onlinePatientDao.showPatDetails(vo,_UserVO,reqNos);
					
					
					mp.put(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO, listexternaprocess);
						

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
			
			
			
			/* Function to search user group Cod Wise Test Details */
			public Map getGroupCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				List<LabTestVO> lstLabTestSample=null;

				Map mp=new HashMap();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					lstLabTest=invEssentialDAO.getGroupCodeWiseDtl(searchVO, _UserVO);
					lstLabTestSample=invEssentialDAO.searchLabTestSample(searchVO, _UserVO);


					//Logic for Getting Sample Combo
					if(lstLabTest!=null )
					{
					for(LabTestVO vo:lstLabTest)
					{
						String strSAmpleCode = vo.getDefaultSampleCode();
						String sampleComboStr="<option value='-1'>Select Value</option>";
						
						
						//filter out specific samples from all lab test samples
						int noOfSamples=0;
						String singleSampleName="";
						String singleSampleCode="";
						String defsingleSampleCode="";

						String reqSampleShortName="";
						for(LabTestVO sample_vo:lstLabTestSample)
						{				
							if(vo.getTestCode().equals("12779") && vo.getLabCode().equals("10003"))
							{
								
								System.out.println("test match");
							}
							
							if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
							{
								
								if(vo.getTestCode().equals("12779") )
								{
									
									System.out.println("test match");
								}
								
								noOfSamples++;
								singleSampleName=sample_vo.getsName();
								defsingleSampleCode=vo.getDefaultSampleCode();
								reqSampleShortName=sample_vo.getReqSampleShortName();
								singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
								if(defsingleSampleCode!=null)
								defsingleSampleCode=defsingleSampleCode;
								else
									defsingleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;

								
								if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
								{	
									
									if(sample_vo.getsCode().equals("12779"))
									{
										System.out.println("aaa");
									}
										
								String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
								
								if(sample_vo.getsCode().equals(defsingleSampleCode))
								defsingleSampleCode=defsingleSampleCode+"$"+reqSampleShortName;
								
									sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
								vo.setReqdSampleName(sample_vo.getsName());
								vo.setReqSampleShortName(reqSampleShortName);
								}
								else
									sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
								
								

							}
															
						}
						if(noOfSamples==1)//single sample
						{
							
							sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
							vo.setSingleSample(singleSampleCode);
							vo.setReqdSampleName(singleSampleName);
							vo.setReqSampleShortName(reqSampleShortName);
						}
						else
						{
							vo.setSingleSample(defsingleSampleCode);
						}
						
						
						
						//getting (samplecode#samplename,sam.....) string 
						/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
						
						if(sampleString.equals("")==false)
						{
							String splitValues[] = sampleString.split(",");
							
							for(String sampleCodeName: splitValues)
							{
								if(splitValues.length==1)
								{
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
									vo.setSingleSample(sampleCodeName.split("#")[0]);
								}
								else
								{
								if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
								}
								
								
							}
							
							
							
						}
						*/
						if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
							vo.setSampleComboStr("");
						else      // Sample and Slide Based
							vo.setSampleComboStr(sampleComboStr);

						
						/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
						if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
						{
							Iterator lstIterator=lstSampleCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(lstSampleCombo.size()==1)
									
								{
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
									vo.setSingleSample(entry.getValue());
								}
								else
								{
								if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
								}
							}
						}*/
						//vo.setSampleComboStr(sampleComboStr);

						//Sample Combo Logic
						
						//Mandatory Combo/text Logic
						String ismandInfo=vo.getIsMandatoryReq();
						String mandInfo=vo.getMandInfo();
						
		                  
						if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
							
						{
							String[] mandInfoCommaSeparator=mandInfo.split(",");
							
							int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
							String textBoxAndCombo="";
							String textBoxComboNames="";
							String textBoxComboCode="";
							
							for(int i=0;i<mandInfoCommaSeparatorlength;i++)
							{
								String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
								
								 
								
								 
									String mandCode=maninfoHashSeparator[0];
									
									String mandName=maninfoHashSeparator[1];
									
									String mandType=maninfoHashSeparator[2];
								
									
									if(mandType.equals("1"))
									{
									
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
											  textBoxComboNames=mandName;
											  
											  textBoxComboCode=mandCode;
										}
										else
										{
											textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
										     
											textBoxComboNames=textBoxComboNames+"&"+mandName;
											
											textBoxComboCode=textBoxComboCode+"&"+mandCode;
										
										}
										
									}
									if(mandType.equals("2"))
									{
										
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
											
											textBoxComboNames=mandName;
											textBoxComboCode=mandCode;
											
										}
										else
										{
										textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
										
										textBoxComboNames=textBoxComboNames+"&"+mandName;
										
										textBoxComboCode=textBoxComboCode+"&"+mandCode;
										}
										String mandCombo=vo.getMandCombo();
										
										String[] mandComboCommaSeparator=mandCombo.split(",");
										
										int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
										
										for(int k=0;k<mandComboCommaSeparatorlength;k++)
										{
											String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
												
											String mandComboCode=manComboHashSeparator[0];
											String mandComboName=manComboHashSeparator[1];
											
											if(mandCode.equals(mandComboCode))
											{
												textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
											}
											
										}
										textBoxAndCombo=textBoxAndCombo+"</select>";
										
									}
									
									
								 
										vo.setSetMandTextBoxCombo(textBoxAndCombo);
		                                
										vo.setMandComboTextBoxComboNames(textBoxComboNames);
										vo.setMandCode(textBoxComboCode);
							 
							}
						}
						    
						 

					}
				}

					//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
					
					
					mp.put(InvestigationConfig.LIST_GROUP_CODE_WISE_DTLS,lstLabTest);


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
			
			
			
			
			/* Function to search user group Cod Wise Test Details */
			public Map getGroupCodeWiseDtlCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				List<LabTestVO> lstLabTestSample=null;
				List lstUOMCombo=null;
				List lstContainerCombo=null;
				String defaultUOM="";
				String defaultContainer="";
				String defaultQuantity="";
				String uomComboStr="<option value='-1'>Select Value</option>";
				String containerComboStr="<option value='-1'>Select Value</option>";
						String sampleValues="";
				Map mp=new HashMap();
				List lstUserGroupCode=null;


				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
				//	lstLabTest=invEssentialDAO.searchLabWiseTestDtlsRaisingCumCollection(searchVO, _UserVO);
					lstLabTest=invEssentialDAO.getGroupCodeWiseDtlCumColl(searchVO, _UserVO);
					lstLabTestSample=invEssentialDAO.searchLabTestSampleRaisingCumCollection(searchVO, _UserVO);
				//	lstUserGroupCode=invEssentialDAO.fetchUserGroupCode(searchVO, _UserVO);		
					SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
					lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
					lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo
					

					//Logic for Getting Sample Combo
					for(LabTestVO vo:lstLabTest)
					{
						
						
						
						sampleValues="";
						
						 uomComboStr="<option value='-1'>Select Value</option>";
						 containerComboStr="<option value='-1'>Select Value</option>";
						vo.setSampleAreaCode(searchVO.getSampleNo());//fetch sample area code. need to be discussed
						
						
						
						
						String strSAmpleCode = vo.getDefaultSampleCode();
						String sampleComboStr="<option value='-1'>Select Value</option>";
						String singleUomCode="";
						String singleContainerCode="";
						String singleQuantity="";
								
								
						
						//filter out specific samples from all lab test samples
						int noOfSamples=0;
						String singleSampleName="";
						String singleSampleCode="";
						for(LabTestVO sample_vo:lstLabTestSample)
						{					
							if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
							{
								noOfSamples++;
								sampleValues+=sample_vo.getsCode()+"&"+sample_vo.getUomCode()+"&"+sample_vo.getContainerCode()+"&"+sample_vo.getDefaultContainerVol()+"*";
								singleSampleName=sample_vo.getsName();
								singleSampleCode=sample_vo.getsCode();
								singleUomCode=sample_vo.getUomCode();
								singleContainerCode=sample_vo.getContainerCode();
								singleQuantity=sample_vo.getDefaultContainerVol();
								
								if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
								{
									sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"' selected>"+sample_vo.getsName()+"</option>";
									vo.setsName(sample_vo.getsName());
									defaultContainer=sample_vo.getContainerCode();
									defaultUOM=sample_vo.getUomCode();
									defaultQuantity=sample_vo.getDefaultContainerVol();
								
								}
								else
									sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
							}
															
						}
						if(noOfSamples==1)//single sample
						{
							
							
							sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
							vo.setSingleSample(singleSampleCode);
							vo.setsName(singleSampleName);
							defaultContainer=singleContainerCode;
							defaultUOM=singleUomCode;
							defaultQuantity=singleQuantity;
						}
						
						
						vo.setSampleValues(sampleValues);

						//getting (samplecode#samplename,sam.....) string 
						/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
						
						if(sampleString.equals("")==false)
						{
							String splitValues[] = sampleString.split(",");
							
							for(String sampleCodeName: splitValues)
							{
								if(splitValues.length==1)
								{
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
									vo.setSingleSample(sampleCodeName.split("#")[0]);
								}
								else
								{
								if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
								}
								
								
							}
							
							
							
						}
						*/
						
						
						//******default container quantity
						vo.setDefaultContainerVol(defaultQuantity);
						
						//******creating uom code combo
						if(lstUOMCombo!=null && lstUOMCombo.size()>0)
						{
							Iterator lstIterator=lstUOMCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(entry.getValue().equalsIgnoreCase(defaultUOM))
									uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
							}
							
							
						}
						
						vo.setUomComboStr(uomComboStr);
						
						
						//******creating container code combo
						if(lstContainerCombo!=null && lstContainerCombo.size()>0)
						{
							Iterator lstIterator=lstContainerCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(entry.getValue().equalsIgnoreCase(defaultContainer))
									containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
							}
							
							
						}
						vo.setContainerComboStr(containerComboStr);
						
						
						
						
						
						
						
						
						//Sample Combo Logic
						if(vo.getTestType()!=null)
						{
						if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
							vo.setSampleComboStr("");
						else      // Sample and Slide Based
							vo.setSampleComboStr(sampleComboStr);
						}
						else
							vo.setSampleComboStr(sampleComboStr);

						
						/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
						if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
						{
							Iterator lstIterator=lstSampleCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(lstSampleCombo.size()==1)
									
								{
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
									vo.setSingleSample(entry.getValue());
								}
								else
								{
								if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
								}
							}
						}*/
						//vo.setSampleComboStr(sampleComboStr);

						//Sample Combo Logic
						
						//Mandatory Combo/text Logic
						String ismandInfo=vo.getIsMandatoryReq();
						String mandInfo=vo.getMandInfo();
						
		                  
						if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
							
						{
							String[] mandInfoCommaSeparator=mandInfo.split(",");
							
							int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
							String textBoxAndCombo="";
							String textBoxComboNames="";
							String textBoxComboCode="";
							
							for(int i=0;i<mandInfoCommaSeparatorlength;i++)
							{
								String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
								
								 
								
								 
									String mandCode=maninfoHashSeparator[0];
									
									String mandName=maninfoHashSeparator[1];
									
									String mandType=maninfoHashSeparator[2];
								
									
									if(mandType.equals("1"))
									{
									
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
											  textBoxComboNames=mandName;
											  
											  textBoxComboCode=mandCode;
										}
										else
										{
											textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
										     
											textBoxComboNames=textBoxComboNames+"&"+mandName;
											
											textBoxComboCode=textBoxComboCode+"&"+mandCode;
										
										}
										
									}
									if(mandType.equals("2"))
									{
										
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
											
											textBoxComboNames=mandName;
											textBoxComboCode=mandCode;
											
										}
										else
										{
										textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
										
										textBoxComboNames=textBoxComboNames+"&"+mandName;
										
										textBoxComboCode=textBoxComboCode+"&"+mandCode;
										}
										String mandCombo=vo.getMandCombo();
										
										String[] mandComboCommaSeparator=mandCombo.split(",");
										
										int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
										
										for(int k=0;k<mandComboCommaSeparatorlength;k++)
										{
											String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
												
											String mandComboCode=manComboHashSeparator[0];
											String mandComboName=manComboHashSeparator[1];
											
											if(mandCode.equals(mandComboCode))
											{
												textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
											}
											
										}
										textBoxAndCombo=textBoxAndCombo+"</select>";
										
									}
									
									
								 
										vo.setSetMandTextBoxCombo(textBoxAndCombo);
		                                
										vo.setMandComboTextBoxComboNames(textBoxComboNames);
										vo.setMandCode(textBoxComboCode);
							 
							}
						}
						    
						 

					}


					//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
					
					
					mp.put(InvestigationConfig.LIST_GROUP_CODE_WISE_DTLS,lstLabTest);


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
			
			
			
			
			
			
			
			/* Function to search user group Cod Wise Test Details */
			public Map getTestsCodeWiseDtlCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				List<LabTestVO> lstLabTestSample=null;
				List lstUOMCombo=null;
				List lstContainerCombo=null;
				String defaultUOM="";
				String defaultContainer="";
				String defaultQuantity="";
				String uomComboStr="<option value='-1'>Select Value</option>";
				String containerComboStr="<option value='-1'>Select Value</option>";
						String sampleValues="";
				Map mp=new HashMap();
				List lstUserGroupCode=null;


				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
				//	lstLabTest=invEssentialDAO.searchLabWiseTestDtlsRaisingCumCollection(searchVO, _UserVO);
					lstLabTest=invEssentialDAO.getTestsCodeWiseDtlCumColl(searchVO, _UserVO);
					lstLabTestSample=invEssentialDAO.searchLabTestSampleRaisingCumCollection(searchVO, _UserVO);
				//	lstUserGroupCode=invEssentialDAO.fetchUserGroupCode(searchVO, _UserVO);		
					SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
					lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
					lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo
					

					//Logic for Getting Sample Combo
					for(LabTestVO vo:lstLabTest)
					{
						
						
						
						sampleValues="";
						
						 uomComboStr="<option value='-1'>Select Value</option>";
						 containerComboStr="<option value='-1'>Select Value</option>";
						vo.setSampleAreaCode(searchVO.getSampleNo());//fetch sample area code. need to be discussed
						
						
						
						
						String strSAmpleCode = vo.getDefaultSampleCode();
						String sampleComboStr="<option value='-1'>Select Value</option>";
						String singleUomCode="";
						String singleContainerCode="";
						String singleQuantity="";
								
								
						
						//filter out specific samples from all lab test samples
						int noOfSamples=0;
						String singleSampleName="";
						String singleSampleCode="";
						for(LabTestVO sample_vo:lstLabTestSample)
						{					
							if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
							{
								noOfSamples++;
								sampleValues+=sample_vo.getsCode()+"&"+sample_vo.getUomCode()+"&"+sample_vo.getContainerCode()+"&"+sample_vo.getDefaultContainerVol()+"*";
								singleSampleName=sample_vo.getsName();
								singleSampleCode=sample_vo.getsCode();
								singleUomCode=sample_vo.getUomCode();
								singleContainerCode=sample_vo.getContainerCode();
								singleQuantity=sample_vo.getDefaultContainerVol();
								
								if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
								{
									sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"' selected>"+sample_vo.getsName()+"</option>";
									vo.setsName(sample_vo.getsName());
									defaultContainer=sample_vo.getContainerCode();
									defaultUOM=sample_vo.getUomCode();
									defaultQuantity=sample_vo.getDefaultContainerVol();
								
								}
								else
									sampleComboStr=sampleComboStr+"<option value='"+sample_vo.getsCode()+"'>"+sample_vo.getsName()+"</option>";
							}
															
						}
						if(noOfSamples==1)//single sample
						{
							
							
							sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
							vo.setSingleSample(singleSampleCode);
							vo.setsName(singleSampleName);
							defaultContainer=singleContainerCode;
							defaultUOM=singleUomCode;
							defaultQuantity=singleQuantity;
						}
						
						
						vo.setSampleValues(sampleValues);

						//getting (samplecode#samplename,sam.....) string 
						/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
						
						if(sampleString.equals("")==false)
						{
							String splitValues[] = sampleString.split(",");
							
							for(String sampleCodeName: splitValues)
							{
								if(splitValues.length==1)
								{
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
									vo.setSingleSample(sampleCodeName.split("#")[0]);
								}
								else
								{
								if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
								}
								
								
							}
							
							
							
						}
						*/
						
						
						//******default container quantity
						vo.setDefaultContainerVol(defaultQuantity);
						
						//******creating uom code combo
						if(lstUOMCombo!=null && lstUOMCombo.size()>0)
						{
							Iterator lstIterator=lstUOMCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(entry.getValue().equalsIgnoreCase(defaultUOM))
									uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									uomComboStr=uomComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
							}
							
							
						}
						
						vo.setUomComboStr(uomComboStr);
						
						
						//******creating container code combo
						if(lstContainerCombo!=null && lstContainerCombo.size()>0)
						{
							Iterator lstIterator=lstContainerCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(entry.getValue().equalsIgnoreCase(defaultContainer))
									containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									containerComboStr=containerComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
							}
							
							
						}
						vo.setContainerComboStr(containerComboStr);
						
						
						
						
						
						
						
						
						//Sample Combo Logic
						if(vo.getTestType()!=null)
						{
						if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
							vo.setSampleComboStr("");
						else      // Sample and Slide Based
							vo.setSampleComboStr(sampleComboStr);
						}
						else
							vo.setSampleComboStr(sampleComboStr);

						
						/*List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
						if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
						{
							Iterator lstIterator=lstSampleCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								if(lstSampleCombo.size()==1)
									
								{
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
									vo.setSingleSample(entry.getValue());
								}
								else
								{
								if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
								}
							}
						}*/
						//vo.setSampleComboStr(sampleComboStr);

						//Sample Combo Logic
						
						//Mandatory Combo/text Logic
						String ismandInfo=vo.getIsMandatoryReq();
						String mandInfo=vo.getMandInfo();
						
		                  
						if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
							
						{
							String[] mandInfoCommaSeparator=mandInfo.split(",");
							
							int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
							String textBoxAndCombo="";
							String textBoxComboNames="";
							String textBoxComboCode="";
							
							for(int i=0;i<mandInfoCommaSeparatorlength;i++)
							{
								String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
								
								 
								
								 
									String mandCode=maninfoHashSeparator[0];
									
									String mandName=maninfoHashSeparator[1];
									
									String mandType=maninfoHashSeparator[2];
								
									
									if(mandType.equals("1"))
									{
									
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
											  textBoxComboNames=mandName;
											  
											  textBoxComboCode=mandCode;
										}
										else
										{
											textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
										     
											textBoxComboNames=textBoxComboNames+"&"+mandName;
											
											textBoxComboCode=textBoxComboCode+"&"+mandCode;
										
										}
										
									}
									if(mandType.equals("2"))
									{
										
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
											
											textBoxComboNames=mandName;
											textBoxComboCode=mandCode;
											
										}
										else
										{
										textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
										
										textBoxComboNames=textBoxComboNames+"&"+mandName;
										
										textBoxComboCode=textBoxComboCode+"&"+mandCode;
										}
										String mandCombo=vo.getMandCombo();
										
										String[] mandComboCommaSeparator=mandCombo.split(",");
										
										int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
										
										for(int k=0;k<mandComboCommaSeparatorlength;k++)
										{
											String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
												
											String mandComboCode=manComboHashSeparator[0];
											String mandComboName=manComboHashSeparator[1];
											
											if(mandCode.equals(mandComboCode))
											{
												textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
											}
											
										}
										textBoxAndCombo=textBoxAndCombo+"</select>";
										
									}
									
									
								 
										vo.setSetMandTextBoxCombo(textBoxAndCombo);
		                                
										vo.setMandComboTextBoxComboNames(textBoxComboNames);
										vo.setMandCode(textBoxComboCode);
							 
							}
						}
						    
						 

					}


					//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
					
					
					mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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
					}
					
					if(resultEntryVO.getCrNoReqNoStringAddendum()!=null)
						for(int i =0;i<resultEntryVO.getCrNoReqNoStringAddendum().length;i++)
						{				
							objResultEntrtyDAO.updateAddendumRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoStringAddendum()[i].split("#")[1], resultEntryVO.getAddendumRemark()[i], _userVO);				
						}
					}
									
					for(ResultEntryVO voInvResultValidation:invResultentryVO)
					{
						String reasoncode="";
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);
					//	objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
						if(changeType[1].equals("1"))
							voInvResultValidation.setReportChangeFlag("2");
						else
							voInvResultValidation.setReportChangeFlag("3");
						
						
						
						
						objResultEntrtyDAO.updateNewValuesInRequisitionDtl(voInvResultValidation, _userVO);
						
						//get value 0 index .... array get from invrsultentry 0 index
						//travese loop of cr no and req no for reason........  the value id that matches cr no and req no of  "voInvResultValidation"
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
						
							if(crNo.equals(voInvResultValidation.getPatCRNo()) && ReqNo.equals(voInvResultValidation.getRequisitionNo()))
							{
								System.out.println("in loop");
								reasoncode=(resultEntryVO.getReasonCode()[i1]);
							}
							
						}
						}
						
						objResultEntrtyDAO.insertAddendumLogDtl(voInvResultValidation,_userVO,reasoncode);
						/*if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						{
						objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
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

									 String status="13";
										
										
										if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12762") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3590"))
										  objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,voInvResulValidationForParaMeterDtl.getResultEntryValue(),null,status);

										if(voInvResulValidationForParaMeterDtl.getTestCode().equals("12765") && voInvResulValidationForParaMeterDtl.getTestParaMeterCode().equals("3598"))
											objResultEntrtyDAO.updatepidvaluesresult(voInvResulValidationForParaMeterDtl,_userVO,null,voInvResulValidationForParaMeterDtl.getResultEntryValue(),status);
										
										
										
							 }
							 
							 
						 }
						
						
						
						//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
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
			public String  checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
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
					InvResultValidationDAO onlinePatientDao = new InvResultValidationDAO(tx);
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
			public String  checkcannedCodeName1(InvResultValidationFB fb, UserVO _UserVO)
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
					InvResultReValidationDAO onlinePatientDao = new InvResultReValidationDAO(tx);
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
			


			
			//dept result entry - puneet
			
			public Map setPatientDeptResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
				List<ResultEntryVO> groupModified_lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
				List labNoCombo=new ArrayList();
				List testCombo=new ArrayList();
				List sampleNoCombo=new ArrayList();
				List testGroupCombo=new ArrayList();
				List groupCode=new ArrayList();

				
				 
				try
				{
					tx.begin();
					departmentSpecificResultEntryDAO invresultentrydao = new departmentSpecificResultEntryDAO(tx);

					lstInvResultEntryVO=invresultentrydao.setPatientResultEntryEssentials(invresultentryvo, _UserVO);

					mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultEntryVO);
					
					
					for(ResultEntryVO tempVo:lstInvResultEntryVO)
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
			
			
			
			public Map getDeptResultEntryData(ResultEntryVO invresultentryvo)
			{

				List<TriStringObject> resultValidationDataList = null;
				
				Map mp = null;
				JDBCTransactionContext tx = new JDBCTransactionContext();
				try {
					tx.begin();
					
					departmentSpecificResultEntryDAO resultEntryDetailDAO = new departmentSpecificResultEntryDAO(
							tx);
					
					resultValidationDataList = resultEntryDetailDAO
							.getResultValidfationDataList(invresultentryvo);
					
				
					mp = new HashMap(4);

					System.out.println("resultEntryVO.getTestDocument()"
							+ invresultentryvo.getTestDocument());
					if (invresultentryvo.getTestDocument() != null) {
						XPathFactory factory = XPathFactory.newInstance();
						XPath xpath = factory.newXPath();
						String xQuery = "/test/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@idC = 'button'][@callUrl='/AHIMS/investigation/transaction/searchFunctionality.cnt' or @callUrl='/AHIMS/investigation/commonaction.cnt?mode=ORGANISMANTIBIOTIC']";
						System.out.println("xQuery  :: " + xQuery);
						XPathExpression expr = xpath.compile(xQuery);
						Object result = expr.evaluate(invresultentryvo.getTestDocument(),
								XPathConstants.NODESET);
						NodeList nodes = (NodeList) result;
						System.out.println(nodes.getLength());

			
					}

					mp.put("resultValidationDataList", resultValidationDataList);
		
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
				return mp;
			
			}
			
			
			
			
			
			//save department result entry
			public  Map saveDeptResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

				List   listResultEntryDtl =new ArrayList();
				ResultEntryVO resultEntryVO=new ResultEntryVO();
			

				boolean isComplete=true;
				List<ResultEntryVO> lstResultEntryVO=new ArrayList<ResultEntryVO>();
				 
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					departmentSpecificResultEntryDAO objResultEntrtyDAO=new departmentSpecificResultEntryDAO(tx);
					
					resultEntryVO=invResultentryVO.get(0);
					if(resultEntryVO.getCrNoReqNoString()!=null)
					for(int i =0;i<resultEntryVO.getCrNoReqNoString().length;i++)
					{				
						objResultEntrtyDAO.updateFinalRemarkInRequisitionHeader(resultEntryVO.getCrNoReqNoString()[i].split("#")[1], resultEntryVO.getFinalRemarkValue()[i], _userVO);				
					}
					
					
					
					for(ResultEntryVO voInvResultEntry:invResultentryVO)
					{
						
						/*if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
							voInvResultEntry.setReqDtlStatus("13");
						else				
						voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);*/
						
						
						if(voInvResultEntry.getSaveReVal()==null)
							voInvResultEntry.setReqDtlStatus("31");//31 department entry done and sent for modification purpose
						else if(voInvResultEntry.getSaveReVal().equals("1"))
							voInvResultEntry.setReqDtlStatus("32");//32 ready for dept report printing
						else				
							voInvResultEntry.setReqDtlStatus("31");
						
						
						
					//	voInvResultEntry.setReqDtlStatus("7");
						objResultEntrtyDAO.updateResultEntryInRequisitionDtl(voInvResultEntry,_userVO);
						
						
						
						
						if(voInvResultEntry.getReqDtlStatus().equals("32"))
						{
						objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
						}		
						
						
						
						//when status 13
						//objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
						
					/*	if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
						{
						objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
						}	*/	
						
					
					}
					
					for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultEntryForParameterDtlVO)
					{
						objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
						
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
			
			
			
			
			
			
			public  Map modifyDeptResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

				List   listResultEntryDtl =new ArrayList();
				 

				boolean isComplete=true;
				List<ResultEntryVO> lstResultEntryVO=new ArrayList<ResultEntryVO>();
				 
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					departmentSpecificResultEntryDAO objResultEntrtyDAO=new departmentSpecificResultEntryDAO(tx);
					
					for(ResultEntryVO voInvResultEntry:invResultentryVO)
					{
						if(voInvResultEntry.getSaveReVal()==null)
							voInvResultEntry.setReqDtlStatus("31");
						else if(voInvResultEntry.getSaveReVal().equals("1"))
							voInvResultEntry.setReqDtlStatus("32");
						else				
							voInvResultEntry.setReqDtlStatus("31");
						
						objResultEntrtyDAO.updateModifyResultEntryInRequisitionDtl(voInvResultEntry,_userVO);
					
						
						
						
						
						if(voInvResultEntry.getReqDtlStatus().equals("32"))
						{
						objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
						}		
						
					
					}
					String alreadyPresentPara=null;
					for(ResultEntryVO voInvResulEntryForParaMeterDtl:invResultEntryForParameterDtlVO)
					{
						
						alreadyPresentPara=	objResultEntrtyDAO.checkParameterPresent(voInvResulEntryForParaMeterDtl,_userVO);
						
						if(alreadyPresentPara==null)
						{
							//insert
							objResultEntrtyDAO.insertResultEntryDtl(voInvResulEntryForParaMeterDtl,_userVO);
							
						}
						else
						objResultEntrtyDAO.modifyResultValidationDtl(voInvResulEntryForParaMeterDtl,_userVO);
						
						
						//objResultEntrtyDAO.insertResultLogDtl(voInvResulEntryForParaMeterDtl,_userVO);
						
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

			
			
			
			
			// inv antibiotic process
			
			public Map  LabComboForAudit(invAntibioticProcessFB invinvAntibioticProcessFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					invAntibioticProcessDAO onlinePatientDao = new invAntibioticProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.LabComboForAudit(invinvAntibioticProcessFB,_UserVO);
					mp.put(InvestigationConfig.ORGANISM_COMBO, labcombo);


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
			
			public Map  getAntibioticName(invAntibioticProcessVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invAntibioticProcessVO> testcombo=new ArrayList<invAntibioticProcessVO>();
				List<invAntibioticProcessVO> testcomboprefrred=new ArrayList<invAntibioticProcessVO>();

				try
				{
					tx.begin();
					invAntibioticProcessDAO onlinePatientDao = new invAntibioticProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					testcombo=onlinePatientDao.getAntibioticName(vo,_UserVO);
					mp.put(InvestigationConfig.ANTIBIOTIC_COMBO, testcombo);
					
					testcomboprefrred=onlinePatientDao.getAntibioticNamepreferred(vo,_UserVO);
					mp.put(InvestigationConfig.ANTIBIOTIC_COMBO_PREFFREDED, testcomboprefrred);


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
			
			
			public String  getxml(invAntibioticProcessVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				String mp;
				

				try
				{
					tx.begin();
					invAntibioticProcessDAO onlinePatientDao = new invAntibioticProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					mp=onlinePatientDao.getxml(vo,_UserVO);
					


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


			public Map reasonList( UserVO _UserVO)
			  {
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List reasonlist=new ArrayList();
				
				List lstPrintTemplate=new ArrayList();
				try
				{
					tx.begin();
					invReportAddendumDAO objResultEntrtyDAO=new invReportAddendumDAO(tx);
					//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
					
					reasonlist=objResultEntrtyDAO.getreasonlist( _UserVO);
					mp.put(InvestigationConfig.ADDENDUM_REASON_LIST,reasonlist);
					
				}
				catch (HisDuplicateRecordException e)
				{
					tx.rollback();
					throw new HisDuplicateRecordException(e.getMessage());

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

			
		// inprocess report new form
			public Map setResultReportPrintingEssentialsOnLoad(UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invReportInProcessVO> lstinvReportInProcessVO=new ArrayList<invReportInProcessVO>();
				
				List<invReportInProcessVO> groupModified_lstinvReportInProcessVO=new ArrayList<invReportInProcessVO>();
				
				List groupCode=new ArrayList();

				try
				{
					tx.begin();
					invReportInProcessDAO invReportInProcessDAO = new invReportInProcessDAO(tx);

					lstinvReportInProcessVO=invReportInProcessDAO.setResultReportPrintingEssentialsOnLoad( _UserVO);

					mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO_INPROCESS,lstinvReportInProcessVO);
					
					
					for(invReportInProcessVO tempVo:lstinvReportInProcessVO)
					{
						if(tempVo.getGroupCode()!=null)
						{
							if(!groupCode.contains(tempVo.getGroupCode()+tempVo.getPatReqNo()))
							{
								groupCode.add(tempVo.getGroupCode()+tempVo.getPatReqNo());
								
								groupModified_lstinvReportInProcessVO.add(tempVo);
							}
						}
						else
							groupModified_lstinvReportInProcessVO.add(tempVo);
						
					}
					
					
					mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS,groupModified_lstinvReportInProcessVO);

					
					
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
			
			
			// inprocess report new form
						public Map setResultReportPrintingEssentials(invReportInProcessVO invresultreportprintingvo,UserVO _UserVO)
						{
							JDBCTransactionContext tx = new JDBCTransactionContext();
							Map mp=new HashMap();
							List<invReportInProcessVO> lstinvReportInProcessVO=new ArrayList<invReportInProcessVO>();
							
							List<invReportInProcessVO> groupModified_lstinvReportInProcessVO=new ArrayList<invReportInProcessVO>();
							
							List groupCode=new ArrayList();

							try
							{
								tx.begin();
								invReportInProcessDAO invReportInProcessDAO = new invReportInProcessDAO(tx);

								lstinvReportInProcessVO=invReportInProcessDAO.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);

								mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO_INPROCESS,lstinvReportInProcessVO);
								
								
								for(invReportInProcessVO tempVo:lstinvReportInProcessVO)
								{
									if(tempVo.getGroupCode()!=null)
									{
										if(!groupCode.contains(tempVo.getGroupCode()+tempVo.getPatReqNo()))
										{
											groupCode.add(tempVo.getGroupCode()+tempVo.getPatReqNo());
											
											groupModified_lstinvReportInProcessVO.add(tempVo);
										}
									}
									else
										groupModified_lstinvReportInProcessVO.add(tempVo);
									
								}
								
								
								mp.put(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS,groupModified_lstinvReportInProcessVO);

								
								
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
	
			
			
			public void saveInJobWorkOrder(List<invReportInProcessVO> vo,UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				

				try
				{
					
					
					
					tx.begin();
					invReportInProcessDAO invReportInProcessDAO = new invReportInProcessDAO(tx);

					for(invReportInProcessVO voo:vo)
					{
						String count="";
						
				//	count=	invReportInProcessDAO.checkDataInJobWorkOrder(voo, _UserVO);
						if(count.equals("1"))
						{
						//	invReportInProcessDAO.updateInJobWorkOrder(voo, _UserVO);
							
						}
						else
			               	invReportInProcessDAO.saveInJobWorkOrder(voo, _UserVO);
						
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
				
			}
			
			
			
			
			public Map  LabCombos(SampleAcceptanceVO onlinePatientvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();
				 List machinelist=new ArrayList();		

				try
				{
					tx.begin();
					SampleAcceptanceDAO onlinePatientDaoi = new SampleAcceptanceDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDaoi.getLabCombos(onlinePatientvo,_UserVO);
					mp.put(InvestigationConfig.LAB_COMBO, labcombo);


					
					machinelist=onlinePatientDaoi.machinelist(onlinePatientvo,_UserVO);
					mp.put(InvestigationConfig.MACHINE_LIST_ACCEPTANCE, machinelist);
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
			
			
			
			public List<Inv_SampleCollectionVO> getPatListBarcode(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO) 
			{

				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
				lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

				try {

					tx.begin();
					SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

					lstinvSampleCollectionVO =objSampleCollectionDAO.getPatListBarcode(objSampleCollectionVO,_UserVO);


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

				return lstinvSampleCollectionVO;
			}

			
			
// report history Dtl
			
			public Map  fetchActiveReportData(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invReportHistoryVO> activeReportList=new ArrayList<invReportHistoryVO>();

				try
				{
					tx.begin();
					invReportHistoryDAO onlinePatientDao = new invReportHistoryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					activeReportList=onlinePatientDao.fetchActiveReportData(invinvReportHistoryFB,_UserVO);
					mp.put(InvestigationConfig.DATA_FOR_REPORT_HISTORY, activeReportList);


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
			
			
			public Map  fetchActiveReportData1(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invReportHistoryVO> activeReportList=new ArrayList<invReportHistoryVO>();

				try
				{
					tx.begin();
					invReportHistoryDAO onlinePatientDao = new invReportHistoryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					activeReportList=onlinePatientDao.fetchActiveReportData1(invinvReportHistoryFB,_UserVO);
					mp.put(InvestigationConfig.DATA_FOR_REPORT_HISTORY, activeReportList);


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
			
			
			public Map  fetchActiveReportDataall(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invReportHistoryVO> activeReportList=new ArrayList<invReportHistoryVO>();

				try
				{
					tx.begin();
					invReportHistoryDAO onlinePatientDao = new invReportHistoryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					activeReportList=onlinePatientDao.fetchActiveReportDataall(invinvReportHistoryFB,_UserVO);
					mp.put(InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL, activeReportList);


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
			
			
			
			public Map  fetchActiveReportDataallInactive(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invReportHistoryVO> activeReportList=new ArrayList<invReportHistoryVO>();

				try
				{
					tx.begin();
					invReportHistoryDAO onlinePatientDao = new invReportHistoryDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					activeReportList=onlinePatientDao.fetchActiveReportDataallInactive(invinvReportHistoryFB,_UserVO);
					mp.put(InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL_INACTIVE, activeReportList);


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
			
			
			//addendum
			
			public  Map saveAddendumDetails(invReportAddendumVO newPatVO,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

			//	List   listResultValidationDtl =new ArrayList();
		//		 
			//	String[] changeType=amendType.split("#");//diagnosis addendum amendment
				boolean isComplete=true;
				List<ResultEntryVO> lstResultValidationVO=new ArrayList<ResultEntryVO>();
				ResultEntryVO resultEntryVO=new ResultEntryVO();
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					invReportAddendumDAO objResultEntrtyDAO=new invReportAddendumDAO(tx);
					
					
					//demographics only case //update details in req header
					
						
						
						
						objResultEntrtyDAO.updateNewValuesInRequisitionDtll(newPatVO, _userVO);
						
						//get value 0 index .... array get from invrsultentry 0 index
						//travese loop of cr no and req no for reason........  the value id that matches cr no and req no of  "voInvResultValidation"
					
						
					objResultEntrtyDAO.insertAddendumLogDtll(newPatVO,_userVO);
						/*if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						{
						objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
						}		*/
					

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
			
			
			// check result entry billing for addendum test
			
			public String  checkBilling(InvResultEntryFB fb, UserVO _UserVO)
			{
				List<Inv_SampleCollectionVO> lstTestBased= new ArrayList<Inv_SampleCollectionVO>();
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				String value="";
				String reqType="";
				String reqNo="";
				String samplecode="";
				String reqDno="";
				boolean isBilled=false;
				try
				{
					tx.begin();
					InvResultEntryDAO onlinePatientDao = new InvResultEntryDAO(tx);
					/*SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
					OnlinePatientAcceptanceDAO onlinePatientDao1 = new OnlinePatientAcceptanceDAO(tx);*/
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                    
					String selectedvalues[]=fb.getSelectedCheckbox().split("@");         
					  //   reqType=selectedvalues[5];
					     reqNo=selectedvalues[1];
					     reqDno=selectedvalues[2];
					
					     String patStatus=onlinePatientDao.getPatStatus(reqNo,_UserVO);
					     
					     
					     if(patStatus.equals("IPD"))
								reqType=InvestigationConfig.REQUISITION_TYPE_IPD; //1
							else if(patStatus.equals("OPD"))
								reqType=InvestigationConfig.REQUISITION_TYPE_OPD; //2
							else
								reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3

					     
					     if((Integer.parseInt(selectedvalues[6]))==-1)    //to check patnt based or sample based 
					{
						
						
						
						List<OnlinePatientAcceptanceVO> lstTestBased1=new ArrayList<OnlinePatientAcceptanceVO>();
									lstTestBased1=onlinePatientDao.patientDetails(reqNo,reqType, _UserVO,reqDno);
									
									
									if(lstTestBased1!=null)
									{
										for(OnlinePatientAcceptanceVO voPatientCollection:lstTestBased1)
										{
										  	if(voPatientCollection!=null)
											{
												String billNo=voPatientCollection.getBillDetail().replace("^", "#").split("#")[0];
												
												if(!billNo.equals("0"))
												{isBilled=true;
												}else
												{
												isBilled=false;
												}
											}
											
											

											}
										
										if(isBilled)
					                     {
					                    	 value="1";   // set for billed
					                     }
					                     else
					                     {
					                    	 value="2";   //set for unbilled
					                     }

										}
									
									
						}	
						
					
					else
					{
						
						lstTestBased =onlinePatientDao.getBilledPatList(reqNo,reqType,_UserVO,reqDno);
						
						
						if(lstTestBased!=null)
						{
							for(Inv_SampleCollectionVO voSampleCollection:lstTestBased)
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
			                     {
			                    	 value="1";   // set for billed
			                     }
			                     else
			                     {
			                    	 value="2";   //set for unbilled
			                     }
						}
						
						
						
						
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
			
			
			
			
			
			
			public  String  getlabcodesaddendum(String reqno,UserVO userVO)
			{
				List<Inv_SampleCollectionVO> lstTestBased= new ArrayList<Inv_SampleCollectionVO>();
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				String value="";
				String reqType="";
				String reqNo="";
				String samplecode="";
				String reqDno="";
				boolean isBilled=false;
				try
				{
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					/*SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
					OnlinePatientAcceptanceDAO onlinePatientDao1 = new OnlinePatientAcceptanceDAO(tx);*/
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                    
				
					
					     String labcodes=invEssentialDAO.getlabcodesaddendum(reqno,userVO);
					     
					     
					     value=labcodes;
					                   
					
					
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

			
			
			
			public Map fetchMachime( UserVO _UserVO)
			  {
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List lsttest=new ArrayList();
				List itemnamecombo=new ArrayList();
				List storenamecombo=new ArrayList();
				
				List lstPrintTemplate=new ArrayList();
				try
				{
					tx.begin();
					SampleAcceptanceDAO testMstDAOi = new SampleAcceptanceDAO(tx);
					//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
					lsttest=testMstDAOi.getMachine( _UserVO);
					mp.put(InvestigationConfig.LIST_FILM_TESTNAME_COMBO, lsttest);
					
				}
				catch (HisDuplicateRecordException e)
				{
					tx.rollback();
					throw new HisDuplicateRecordException(e.getMessage());

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
			
			public String  fetchMobileNo(reportDownloadProcessFB fb)
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
					reportDownloadProcessDAO onlinePatientDao = new reportDownloadProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 value=onlinePatientDao.fetchMobileNo(fb);
					
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
			
			public String  getBillingCheck(UserVO userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				
				String check="";
				try
				{
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 check=invEssentialDAO.getBillingCheck1(userVO);
					
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
				return check;
			}
			
			public String  getInstruction(UserVO userVO,ResultEntryVO invresultentryv)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				
				String check="";
				try
				{
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					// check=invEssentialDAO.getInstruction(userVO,invresultentryv);
					
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
				return check;
			}
			
			public String  fetchusername(reportDownloadProcessFB fb)
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
					reportDownloadProcessDAO onlinePatientDao = new reportDownloadProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 value=onlinePatientDao.fetchusername(fb);
					
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
			
			
			public Map  fetchlist(reportDownloadProcessVO vo)
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
					reportDownloadProcessDAO onlinePatientDao = new reportDownloadProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 mp=onlinePatientDao.fetchlist(vo);
					
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
			
			
			public Map  Pfetchlist(reportDownloadProcessVO vo)
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
					reportDownloadProcessDAO onlinePatientDao = new reportDownloadProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 mp=onlinePatientDao.Pfetchlist(vo);
					
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
			
			
			public Map savePublicResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();

				
				//InvResultReportPrintingVO invresultreportp=new InvResultReportPrintingVO();
				try
				{
					tx.begin();
					
					reportDownloadProcessDAO reportDownloadProcessDAO = new reportDownloadProcessDAO(tx);
					for(InvResultReportPrintingVO invresultreportprintvo:invresultreportprintingvo)
					{
						
						reportDownloadProcessDAO.saveResultReportPrintingDetailInpubicreport(invresultreportprintvo);

						
						 
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


			public String  validatecrno(reportDownloadProcessFB fb)
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
					reportDownloadProcessDAO onlinePatientDao = new reportDownloadProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 value=onlinePatientDao.validatecrno(fb);
					
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
			


			public Map loginInsertDetails(InvResultReportPrintingVO invresultreportprintingvo)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();

				
				//InvResultReportPrintingVO invresultreportp=new InvResultReportPrintingVO();
				try
				{
					tx.begin();
					
					reportDownloadProcessDAO reportDownloadProcessDAO = new reportDownloadProcessDAO(tx);
					
						
						reportDownloadProcessDAO.loginInsertDetails(invresultreportprintingvo);


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
			
			
			/*public List<String> getEmployeeNameList()
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<String> empName = new ArrayList();
				
				try
				{
					tx.begin();
					InvResultEntryDAO invResultEntryDAO = new InvResultEntryDAO(tx);
					
					empName = invResultEntryDAO.setEmployeeNameComboEssentials();
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
				return empName;
			}*/

			
			public  String getRequisitionFormMasterData(String patVO,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<Inv_EpisodeVO> listEpisodeVO=new ArrayList<Inv_EpisodeVO>();
				String ar="";
				try
				{    
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);

					 ar=invEssentialDAO.getRequisitionFormMasterData(patVO, _userVO);
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
				return ar;

			}

			
			
			
			
			public  Map saveAddendumDetails(InvResultEntryVO newPatVO,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();

			//	List   listResultValidationDtl =new ArrayList();
		//		 
			//	String[] changeType=amendType.split("#");//diagnosis addendum amendment
				boolean isComplete=true;
				List<ResultEntryVO> lstResultValidationVO=new ArrayList<ResultEntryVO>();
				ResultEntryVO resultEntryVO=new ResultEntryVO();
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					invReportAddendumDAO objResultEntrtyDAO=new invReportAddendumDAO(tx);
					
					
					//demographics only case //update details in req header
					
						
						
						
						//objResultEntrtyDAO.updateNewValuesInRequisitionDtll(newPatVO, _userVO);
						
						//get value 0 index .... array get from invrsultentry 0 index
						//travese loop of cr no and req no for reason........  the value id that matches cr no and req no of  "voInvResultValidation"
					
						
					//objResultEntrtyDAO.insertAddendumLogDtll(newPatVO,_userVO);
						/*if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						{
						objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
						}		*/
					

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

			
			public Map  getPrvTestDtlAJAXMAPP(String CrNo,UserVO userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				String labNames="";
				String prvTestDetail="";
				List<LabTestVO> lstPrvLabTest=null;
				String testGroupNames="";
				Map mp=new HashMap();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//List lstLabNames=invEssentialDAO.getLabNames(userVO);
					  lstPrvLabTest=invEssentialDAO.getPrvTestDtlUsingAJAXX(CrNo, userVO);
				 
					  //LabTestVO LabTestVO=new LabTestVO();
					  
					 
					  //if(lstPrvLabTest!=null)
					  for(LabTestVO vo:lstPrvLabTest)
					  {
						  prvTestDetail+=vo.getStatus()+"#"+vo.getLabName()+"#"+vo.getSampleName()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+vo.getBookMarkCode()+"#"+vo.getOfflineAppoitMentDate()+'#'+vo.getReqDate()+'#'+vo.getPrvLabCode()+'#'+vo.getPrvTestCode()+'#'+vo.getPrvReqStatus()+'#'+vo.getReqNo()+"#"+vo.getTestGroupName()+"@";  
						  
						  	 
					  }
					mp.put(InvestigationConfig.LIST_PRVTESTDTL_AJAX_RESULT_ENTRY, prvTestDetail);

				 

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

			
			
			public Map deleteReqDtll(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
				
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

				
					
					
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					/*	//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);
					
					
					InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
					
					 Inv_RequisitionRaisingPatientVO patVO=null;
		          	 
					 String crno=patVO.getPatCRNo();
			        patVO=objSampleAcceptanceDAO.getInvRaisingPatDetails(crno,_UserVO);
					
			        
			             String  requisitionTypeForBilling="";
					
			             String reqNo=searchVO.getRequisitionNo()+"!";
			             voSamAcc.setRequisitionNo(reqNo);
					
					  if(patVO.getPatStatusCode()!=null && patVO.getPatStatusCode().equals("2"))
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
							}
						}
					  
					  
					  
					  if(voBillingDtl.getTariffDetails()==null)
						{
							voBillingDtl.setTariffDetails(new ArrayList<String>());
							voBillingDtl.setTariffQty(new ArrayList<String>());
						}
						
						voBillingDtl.getTariffDetails().add(searchVO.getDelLabCode()+searchVO.getDelTestCode());
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
					}	
					
					objSampleAcceptanceDAO.makeRefund(voSamAcc,_UserVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
			        
			        */
					
					String dataforDeletion=searchVO.getDeletedtestdataviaresultentry();
					
					for(int k=0;k<dataforDeletion.split("@").length;k++)
					{
						String data=dataforDeletion.split("@")[k];
						String lab=data.split("\\$")[0];
						String test=data.split("\\$")[1];
						String requisitionno=data.split("\\$")[2];
				
						invEssentialDAO.deleteReqDtll(lab,test,requisitionno, _UserVO);
					}
					
				
		 
					mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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
			
			
			
			public  static String  getlabcodesaddendumResultentry(String reqno,UserVO userVO)
			{
				List<Inv_SampleCollectionVO> lstTestBased= new ArrayList<Inv_SampleCollectionVO>();
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String cannedDetail="";
				String finalList="";
				List labcombo=new ArrayList();
				List lstCannedDetails=null;
				String value="";
				String reqType="";
				String reqNo="";
				String samplecode="";
				String reqDno="";
				boolean isBilled=false;
				try
				{
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					/*SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
					OnlinePatientAcceptanceDAO onlinePatientDao1 = new OnlinePatientAcceptanceDAO(tx);*/
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);
                    
				
					
					     String labcodes=invEssentialDAO.getlabcodesaddendumResultEntry(reqno,userVO);
					     
					     
					     value=labcodes;
					                   
					
					
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
			
			
			
			
			public List  machinelist( UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List sampleAcceptanceLabCombo=new ArrayList();
				List machinelist=new ArrayList();
				try
				{
					tx.begin();
					PackingListGenerationDAO objPackingListGenrationDAO=new PackingListGenerationDAO(tx); 
                  	//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					
					machinelist=objPackingListGenrationDAO.machinelist(_UserVO);
					//mp.put(InvestigationConfig.MACHINE_LIST_ACCEPTANCE, machinelist);
					
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
				return machinelist;
			}

			

			
			public Map searchLabWiseTestDtlsNEW(InvestigationSearchVO searchVO, UserVO _UserVO) {

				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				List<LabTestVO> lstLabTestSample=null;
				List lstUserGroupCode=null;
				Map<String, String> testAvailabilityDetail = null;
				List lsttestteriff=new ArrayList();
				String labNames="";
				String testNames="";
				String testCode="";
				String testCodenew="";
				
				String groupCodes="";
				
				
				//List<LabTestVO> lstPreviousLabTest=null;
				
				Map mp=new HashMap();
				StringBuilder labBuild = new StringBuilder();
				StringBuilder testBuild = new StringBuilder();
				StringBuilder testCodeBuild = new StringBuilder();
				StringBuilder testCodeBuildnew = new StringBuilder();

				StringBuilder userGroupCodeBuild = new StringBuilder();
				List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
				List<String> testGroupCode=new ArrayList<String>();
				//Map<String, String> testAvailabilityDetail = new HashMap<String, String>();
				
				try {

					tx.begin();
					//added by chandan 
		           String testCodes="";
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					if(!((searchVO.getTestSearchKeyword()).equals("")))
							{
						testCodes=invEssentialDAO.getTestCodesKeyword(searchVO, _UserVO);
						  searchVO.setTestCodesSearchKeyword(testCodes);

							}


		                  Inv_RequisitionRaisingPatientVO patVO=null;
		                  Inv_PatientAdmissionDtlVO patVOadd=null;
		                  Inv_RequisitionRaisingPatientVO voo=new Inv_RequisitionRaisingPatientVO();
		                 if(searchVO.getLabwisetestteriff()!=null && searchVO.getLabwisetestteriff().equals("1"))
		                  {voo.setPatCRNo(searchVO.getPatientCrNo());
		                  patVO=invEssentialDAO.getInvRaisingPatDetails(voo,_UserVO);

		                  if(patVO.getPatStatus()!=null && patVO.getPatStatus().equalsIgnoreCase("ipd"))
		                  {
		                	  List<Inv_PatientAdmissionDtlVO> ss=invEssentialDAO.getPatientAdmission(voo,_UserVO);
		                	  patVOadd=ss.get(0);
		                	  
		                  }
		                  
							lstLabTest=invEssentialDAO.searchLabWiseTestDtlstariff(searchVO, _UserVO,patVO,patVOadd);
		                  }
		                 else
		                 {
		                	 lstLabTest=invEssentialDAO.searchLabWiseTestDtlsNEW(searchVO, _UserVO);
		                	 
		                 }
		                 lstLabTestSample=invEssentialDAO.searchLabTestSample(searchVO, _UserVO);
					lstUserGroupCode=invEssentialDAO.fetchUserGroupCode(searchVO, _UserVO);
					testAvailabilityDetail=invEssentialDAO.getTestAvailabilityDetail(searchVO, _UserVO);
					              lsttestteriff=invEssentialDAO.getlabteriff( _UserVO,searchVO);
					labBuild.append("");
					//String sampleComboStr="<option value='-1'>Select Value</option>";

					
					
					//*********************************logic to create various combo***********************************//
					
					for(LabTestVO vo:lstLabTest)
					{
						
						
						//******creating lab name combo
						if(labBuild.indexOf(vo.getLabCode())==-1)
						{
						labBuild.append("{ label: \""+vo.getLabName()+"\" ,  value: \""+vo.getLabCode()+"\" }");
						labBuild.append(",");
						}
						//******creating test name combo
						
						
						testBuild.append("{ label: \""+vo.getTestName()+"("+vo.getLabName()+")"+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"\" }");
						testBuild.append(",");
						
						//******creating test code combo
						if(vo.getUserTestCode()!=null)
						{
							vo.setTestName(vo.getTestName()+" ("+vo.getUserTestCode()+")");
						testCodeBuild.append("{ label: \""+vo.getUserTestCode()+"\" ,  value: \""+vo.getUserTestCode()+"\" }");
						testCodeBuild.append(",");
						testCodeBuildnew.append("{ label: \""+vo.getTestName()+"("+vo.getLabName()+")"+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"\" }");
						testCodeBuildnew.append(",");
						}
						
						String strSAmpleCode = vo.getDefaultSampleCode();	//default sample code value
						String sampleComboStr="<option value='-1'>Select Value</option>";
						
						//filter out specific samples from all lab test samples
						int noOfSamples=0;
						String singleSampleName="";
						String singleSampleCode="";
						String reqSampleShortName="";
						for(LabTestVO sample_vo:lstLabTestSample)
						{					
							if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
							{
								noOfSamples++;
								singleSampleName=sample_vo.getsName();
								reqSampleShortName=sample_vo.getReqSampleShortName();
								singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
								
								if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
								{
								String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
									sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
								vo.setReqdSampleName(sample_vo.getsName());
								vo.setReqSampleShortName(reqSampleShortName);
								System.out.println("this is the sample name "+vo.getReqdSampleName());
								System.out.println("this is the sample short name "+vo.getReqSampleShortName());
								
								}
								else
								{
									String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
									sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"'>"+sample_vo.getsName()+"</option>";
								}
								}
															
						}
						if(noOfSamples==1)//single sample
						{
							
							sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
							vo.setSingleSample(singleSampleCode);
							vo.setReqdSampleName(singleSampleName);
							vo.setReqSampleShortName(reqSampleShortName);
							System.out.println("this is the sample name "+vo.getReqdSampleName());
							System.out.println("this is the sample short name "+vo.getReqSampleShortName());

						}
						
						//getting (samplecode#samplename,sam.....) string 
						/*String sampleString=vo.getSampleString()==null?"":vo.getSampleString();
						
						if(sampleString.equals("")==false)
						{
							String splitValues[] = sampleString.split(",");
							
							for(String sampleCodeName: splitValues)
							{
								if(splitValues.length==1)
								{
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
									vo.setSingleSample(sampleCodeName.split("#")[0]);
								}
								else
								{
								if(sampleCodeName.split("#")[0].equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"' selected>"+sampleCodeName.split("#")[1]+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+sampleCodeName.split("#")[0]+"'>"+sampleCodeName.split("#")[1]+"</option>";
								}
								
								
							}
							
							
							
						}
						*/
						//Sample Combo Logic
						if(vo.getTestType()!=null)
						{
						if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
							vo.setSampleComboStr("");
						else      // Sample and Slide Based
							vo.setSampleComboStr(sampleComboStr);
						}
						else
							vo.setSampleComboStr(sampleComboStr);
						
						//**********************calling query to get samples for each lab test by hitting database for each pair**********//
						
			/*			List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
						if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
						{
							Iterator lstIterator=lstSampleCombo.iterator();
							while(lstIterator.hasNext())
							{
								Entry entry=(Entry)lstIterator.next();
								
								if(lstSampleCombo.size()==1)
									
								{
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
									vo.setSingleSample(entry.getValue());
								}
								else
								{
								if(entry.getValue().equalsIgnoreCase(strSAmpleCode))
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"' selected>"+entry.getLabel()+"</option>";
								else
									sampleComboStr=sampleComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
								}
								}
						}
		*/
						
						
						
						
						
						//Mandatory Combo/text Logic
						String ismandInfo=vo.getIsMandatoryReq();
						String mandInfo=vo.getMandInfo();
						
		                  
						if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
							
						{
							String[] mandInfoCommaSeparator=mandInfo.split(",");
							
							int mandInfoCommaSeparatorlength=mandInfoCommaSeparator.length;
							String textBoxAndCombo="";
							String  textBoxComboNames="";
							
							String  textBoxComboCode="";
							
							for(int i=0;i<mandInfoCommaSeparatorlength;i++)
							{
								String[] maninfoHashSeparator=mandInfoCommaSeparator[i].split("#");
								
								 
								
								 
									String mandCode=maninfoHashSeparator[0];
									
									String mandName=maninfoHashSeparator[1];
									
									String mandType=maninfoHashSeparator[2];
								
									
									if(mandType.equals("1"))
									{
									
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
											
											textBoxComboNames=mandName;
											textBoxComboCode=mandCode;
										}
										else
										{
											textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <input type='text' name='"+mandName+"' />";
										    
											textBoxComboNames=textBoxComboNames+"&"+mandName;
											
											textBoxComboCode=textBoxComboCode+"&"+mandCode;
										    
										}
										
									}
									if(mandType.equals("2"))
									{
										
										if(textBoxAndCombo.equals(""))
										{
											textBoxAndCombo="<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
											
											textBoxComboNames=mandName;
											
											textBoxComboCode=mandCode;
										}
										else
										{
										textBoxAndCombo=textBoxAndCombo+"&"+"<b>"+mandName+":</b>  <select name='"+mandName+"'><option value='-1'>Select Value</option>";
										
										textBoxComboNames=textBoxComboNames+"&"+mandName;
										
										textBoxComboCode=textBoxComboCode+"&"+mandCode;
										}
										String mandCombo=vo.getMandCombo();
										
										String[] mandComboCommaSeparator=mandCombo.split(",");
										
										int mandComboCommaSeparatorlength=mandComboCommaSeparator.length;
										
										for(int k=0;k<mandComboCommaSeparatorlength;k++)
										{
											String[] manComboHashSeparator=mandComboCommaSeparator[k].split("#");
												
											String mandComboCode=manComboHashSeparator[0];
											String mandComboName=manComboHashSeparator[1];
											
											if(mandCode.equals(mandComboCode))
											{
												textBoxAndCombo=textBoxAndCombo+"<option value='"+mandComboName+"'>"+mandComboName+"</option>";	
											}
											
										}
										textBoxAndCombo=textBoxAndCombo+"</select>";
										
									}
									
									
								 
										vo.setSetMandTextBoxCombo(textBoxAndCombo);
										vo.setMandComboTextBoxComboNames(textBoxComboNames);
										
										vo.setMandCode(textBoxComboCode);

							 
							}
						}
						       
						
						//changed by ashu 
						
						/*Map<String, LabTestVO> userCodeLabTestMap = new HashMap<String, LabTestVO>();
						if(lstLabTest != null){
							//System.out.println(lstLabTest.size());
							String key;
							for(int i=0;i < lstLabTest.size(); i++){
								key = lstLabTest.get(i).getUserTestCode();
								userCodeLabTestMap.put(key, lstLabTest.get(i));
								//System.out.println(" : "+lstLabTest.get(i).getUserTestCode()+" : "+lstLabTest.get(i));
							}
						}
						 
						mp.put(InvestigationConfig.MAP_USER_CODE_WISE_TEST_DTLS,userCodeLabTestMap);*/
		 
						//lstPreviousLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
						if(searchVO.getSearchTestGroupName()!=null&&searchVO.getSearchTestGroupName()!="")
						{
							vo.setSearchTestGroup(searchVO.getSearchTestGroup());
							
							vo.setTestGroupCode(searchVO.getSearchTestGroupName());
							mp.put(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS,lstLabTest);
							
							
							if(!testGroupCode.contains(vo.getTestGroupCode()+vo.getLabCode()))
							{
								testGroupCode.add(vo.getTestGroupCode()+vo.getLabCode());
								lstSingleTestGroupDetail.add(vo);
								
								
								mp.put(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS,lstSingleTestGroupDetail);
								
							}
							
							
						}
						else
		{
							mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstLabTest);
		}
					//mp.put(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS,lstPreviousLabTest);
		          
					}
					
					
					//create user group code json
					if(lstUserGroupCode!=null)
					{
						for(Entry obj:(List<Entry>)lstUserGroupCode)
						{
						
							userGroupCodeBuild.append("{ label: \""+obj.getLabel()+"\" ,  value: \""+obj.getValue()+"\" }");
							userGroupCodeBuild.append(",");
								
							
						}
					}
					
					labBuild.deleteCharAt(labBuild.length()-1);
					labNames="["+labBuild.toString()+"]";
					
					testBuild.deleteCharAt(testBuild.length()-1);
					testNames="["+testBuild.toString()+"]";
					
					testCodeBuild.deleteCharAt(testCodeBuild.length()-1);
					testCode="["+testCodeBuild.toString()+"]";
					
					testCodeBuildnew.deleteCharAt(testCodeBuildnew.length()-1);
					testCodenew="["+testCodeBuildnew.toString()+"]";
					
					userGroupCodeBuild.deleteCharAt(userGroupCodeBuild.length()-1);
					groupCodes="["+userGroupCodeBuild.toString()+"]";
					
					
					if(searchVO.getLabEmpty().equals("1"))
					mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);
					if(searchVO.getTestEmpty().equals("1"))
					mp.put(InvestigationConfig.ARRAY_TESTNAMES, testNames);
					if(searchVO.getTestCodeEmpty().equals("1"))
					mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCode);
					
					mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_LABWISE, testCodenew);
					
					
					if(searchVO.getGroupCodeEmpty().equals("1"))
						mp.put(InvestigationConfig.ARRAY_GROUP_CODE_WISE, groupCodes);
					
					
					Map<String, LabTestVO> userCodeLabTestMap = new HashMap<String, LabTestVO>();
					if(lstLabTest != null){
						//System.out.println(lstLabTest.size());
						String key;
						for(int i=0;i < lstLabTest.size(); i++){
							key = lstLabTest.get(i).getUserTestCode();
							if(key != null){
								key = key.toUpperCase();
								userCodeLabTestMap.put(key, lstLabTest.get(i));
							}
							
							//System.out.println(" : "+lstLabTest.get(i).getUserTestCode()+" : "+lstLabTest.get(i));
						}
					}
					 
					mp.put(InvestigationConfig.MAP_USER_CODE_WISE_TEST_DTLS,userCodeLabTestMap);
					
					mp.put(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS,testAvailabilityDetail);
					
					mp.put(InvestigationConfig.MAP_LAB_CODE_TERIIFF_CHANGE,lsttestteriff);
					
					
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

			
			
			
			public String  CHECKCISPARAMETERDEPENDENTRF(String fb, UserVO _UserVO,InvResultEntryFB fbb)
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
                    
					
					String checkcannedCode=onlinePatientDao.CHECKCISPARAMETERDEPENDENTRF(fb,_UserVO);
					
					if(!fbb.getSelectedindex().equalsIgnoreCase("Positive"))
					onlinePatientDao.deletehivtorganism(fbb,_UserVO);
					
					value=checkcannedCode;
				
					
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
			

			
			public String  getBillingChecktestcode(String testcodess,String ward,String req,String cat,UserVO userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				
				String check="";
				try
				{
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					 check=invEssentialDAO.getBillingCheck1testcode(testcodess,ward,req,cat,userVO);
					
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
				return check;
			}

			
			public  String getcollectionareafromward(String wardcode,String hospitalcode)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				
				String check="";
				try
				{
					tx.begin();
					SampleCollectionDAO invEssentialDAO=new SampleCollectionDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO);
                    
					check=invEssentialDAO.getcollectionareafromward(wardcode, hospitalcode);
					
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
				return check;
			}

			
			//added by krishnan nema on 01/10/2018
			public Map  LabComboForResultValidationAddendum(ResultEntryVO invresultentryvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();
				List sampleNoCombo=new ArrayList();

				try
				{
					tx.begin();
					InvResultValidationDAO onlinePatientDao = new InvResultValidationDAO(tx);
					invReportAddendumDAO dao=new invReportAddendumDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.LabComboForResultValidationAddendum(invresultentryvo,_UserVO);
	/*				sampleNoCombo=dao.setSamplNoComboEssentials(invresultentryvo, _UserVO);

					mp.put(InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY,sampleNoCombo);
					*/
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
			
			
			//added by krishnan nema on 08/10/2018
			public Map  MachineCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List machinecombo=new ArrayList();

				try
				{
					tx.begin();
					OnlinePatientAcceptanceDAOi onlinePatientDaoi = new OnlinePatientAcceptanceDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					machinecombo=onlinePatientDaoi.getMachineCombos(onlinePatientvo,_UserVO);
					mp.put(InvestigationConfig.LIST_LOCATION_BASED_MACHINE__COMBO, machinecombo);


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

			
			

			
			public String issufficientbalance(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
				String flag="2";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.issufficientbalance(searchVO,_UserVO,patVO);
			        
			       String accno=flag.split("\\^")[0];
			       float creditlimit=Float.parseFloat(flag.split("\\^")[2]);
			       float amount=Float.parseFloat(flag.split("\\^")[1]);
                   float AllTrfCost=0;				
			       int creditflag=Integer.parseInt(flag.split("\\^")[3]);

			       if(!accno.equals("0"))
					   {
						   /*if(creditlimit<0)
						   {*/
							
							   String testcoderates="";
							   
							   
							   if(searchVO.getIsamountsufficientflag()!=null && !searchVO.getIsamountsufficientflag().equals(""))
							   {
								   String len[]=searchVO.getIsamountsufficientflag().split("@");
								  
								   for(int l=0;l<len.length;l++)
								   {
									   String testcodeee=len[l].split("\\^")[2];
									   searchVO.setTestCode(testcodeee);
									   testcoderates=objSampleAcceptanceDAO.getamountfortest(searchVO,_UserVO,patVO);
								          
									   if(testcoderates.contains("^"))
					        		   {
							   
							   
					        			    AllTrfCost+=Float.parseFloat(testcoderates.split("\\^")[0]);
					                          
					        		   }
									   
								   }
								   
								   if(creditflag>0 && amount-AllTrfCost-creditlimit < 0)
								   {
									   flag="1";
								   }
								   else
									   flag="2";
								   
								   
							   /*}*/
							   
							  
							   
						   }
						   
					   }
			       else
			    	   flag="3";
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}
			
			
			//added by krishnan nema on 26/10/2018
			@Override
			public Map modifySampleAccDetails(List<SampleAcceptanceVO> voSampleAcceptance,UserVO _userVO, HttpServletRequest _request) {
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List listSampleMachineModified =new ArrayList();
				
				boolean isComplete=true;
				List<SampleAcceptanceVO> lstSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();
				
				Map mp=new HashMap();

				try
				{    

					tx.begin();
					SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
					SampleAcceptanceVO voSampl=new SampleAcceptanceVO();

					String sameSampleNO="";
					
					for(SampleAcceptanceVO voSamAcc:voSampleAcceptance)
					{
						
						if(!voSamAcc.getTestBasedMachine().equals("-1"))
						{
							String isexist=objSampleAcceptanceDAO.islistexistinhmitsample(voSamAcc,_userVO);
							
							 if (isexist!=null && isexist.equals("0"))
							 {
								 System.out.println("11");
								 voSamAcc.setLabNoConfiguration(voSamAcc.getTempSampleNo());
								
								 Date date = new Date();
								  //  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
								    SimpleDateFormat	 formatter = new SimpleDateFormat("dd-MMM-yyyy");  
								  String  strDate = formatter.format(date); 
								// String systemdate=WebUTIL.getCustomisedSysDate(date, "dd-mon-yyyy");
								 voSamAcc.setCollDate(strDate);
							  objSampleAcceptanceDAO.saveSampleDetail(voSamAcc,_userVO);
							  objSampleAcceptanceDAO.updateRequestId(voSamAcc,_userVO);
							 }
						}
						
						objSampleAcceptanceDAO.updateSampleAccInRequisitionDtlModify(voSamAcc,_userVO);
						//objSampleAcceptanceDAO.updateSampleAccInSampleDtlToReject(voSamAcc,_userVO);
					
					//	listSampleMachineModified.add("Packing List No= "+voSamAcc.getPackingListNO()+"       ,Lab Name="+voSamAcc.getLabName()+"      ,Test Name ="+voSamAcc.getTestName());
						listSampleMachineModified.add("Packing List No= "+voSamAcc.getPackingListNO()+"        ,Lab Name="+voSamAcc.getLabName()+"      ,Lab NO="+voSamAcc.getLabNoConfiguration()+"			,Test Name ="+voSamAcc.getTestName());

					}
					
					//Put List in Map
					mp.put(InvestigationConfig.LIST_MODIFIED, listSampleMachineModified);
					
					SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);
					SampleAcceptanceVO vo=new SampleAcceptanceVO();
					
					//Logic For Update Status In Packing List Dtl Table
					//lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetailForCheckPackNoToReject(vo, _userVO);
					
/*					for(SampleAcceptanceVO voSamAccForCheckPackNo:voSampleAcceptance)
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
					}*/

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
			
			
			public String isduplicatetestraisedtoday(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				InvestigationEssentialDAOxray objSampleAcceptanceDAO=new InvestigationEssentialDAOxray(tx);
				String flag="";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.isduplicatetestraisedtoday(searchVO,_UserVO,patVO);
			        
			      
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}
			
			
			public String getgrpcode(String searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				InvestigationEssentialDAOxray objSampleAcceptanceDAO=new InvestigationEssentialDAOxray(tx);
				String flag="";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.getgrpcode(searchVO,_UserVO);
			        
			      
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}
			

			public Inv_ictc_VO getictcdetails(
					Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO) {

				Inv_ictc_VO voPatient=new Inv_ictc_VO();
				JDBCTransactionContext tx = new JDBCTransactionContext();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					voPatient=invEssentialDAO.getictcdetails(patVO, _UserVO);



				} catch (HisApplicationExecutionException e) {
					throw new HisApplicationExecutionException();
				} catch (HisDataAccessException e) {
					throw new HisDataAccessException();
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
					System.out.println("error.... Essential BO");
					patVO = null;
				} finally {

					tx.close();
				}

				return voPatient;
			}
			

			
			public String ispidexist(InvestigationSearchVO searchVO, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				InvestigationEssentialDAOxray objSampleAcceptanceDAO=new InvestigationEssentialDAOxray(tx);
				String flag="";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.ispidexist(searchVO,_UserVO);
			        
			      
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}

			
			public Map getStatusDashboardRecord(invStatusDashboardVO invstatusdashboardvo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List statusRecord=new ArrayList();

				try
				{
					tx.begin();
					invStatusDashboardDAO invstatusdasboardDao = new invStatusDashboardDAO(tx);
					statusRecord = invstatusdasboardDao.invStatusDashboardRecord(invstatusdashboardvo,_UserVO);
					mp.put(InvestigationConfig.LIST_STATUS_DASHBOARD_RECORD, statusRecord);
				
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

			public Map getRequestedSampleListDashboard(String recordRequested,UserVO userVO) {
                JDBCTransactionContext tx = new JDBCTransactionContext();
                List statusRecord=new ArrayList();
                Map mp=new HashMap();
                try
                {
                    tx.begin();
                    invStatusDashboardDAO invstatusdasboardDao = new invStatusDashboardDAO(tx);
                    statusRecord = invstatusdasboardDao.getRequestedSampleListDashboard(recordRequested,userVO);
                    mp.put(InvestigationListingConfig.LIST_SAMPLE_DRILLDOWN_STATS, statusRecord);
                
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
			

			
			/*public Map getInvestigationListingNew(invListingReportNewVO invListingVO, UserVO userVO) 
			{
					JDBCTransactionContext tx = new JDBCTransactionContext();
					Map mp=new HashMap();
					List invListing=new ArrayList();

					try
					{
						tx.begin();
						invListingReportNewDAO invListingReportDao = new invListingReportNewDAO(tx);
						invListing = invListingReportDao.getinvListingReportNew(invListingVO,userVO);
						mp.put(InvestigationConfig.LIST_INV_LISTING_REPORT_NEW, invListing);
					
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
			}*/

			public String AJX_IS_LAB_MANDTORY(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				InvestigationEssentialDAOxray objSampleAcceptanceDAO=new InvestigationEssentialDAOxray(tx);
				String flag="";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.AJX_IS_LAB_MANDTORY(searchVO,_UserVO,patVO);
			        
			      
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}
						

			
			public String updateappointmentdateinheader(LabTestVO voLabTest, UserVO _UserVO) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				InvestigationEssentialDAOxray objSampleAcceptanceDAO=new InvestigationEssentialDAOxray(tx);
				String flag="";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					//flag=objSampleAcceptanceDAO.updateappointmentdateinheader(voLabTest,_UserVO);
			        
			      
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}

			
			public Map<String,Map<String,List<String>>>  getBookMarkListraising(UserVO userVO,String iscallingfromdesk) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<BookMarkVO> lstBookMark=null;
				Map<String,Map<String,List<String>>> mpBookMark=new LinkedHashMap<String,Map<String,List<String>>>();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					lstBookMark=invEssentialDAO.getBookMarkListraising(userVO, iscallingfromdesk);

					if(lstBookMark!=null&&lstBookMark.size()>0)
					{
						List<String> lstTest=null;
						//Logic for Setting Book Mark
						for(BookMarkVO vo:lstBookMark)
						{
							Map<String,List<String>> mpLab=mpBookMark.get(vo.getBookMarkCode()+"#"+vo.getBookMarkName());
							
							
							if(mpLab==null)
							{
								mpLab=new LinkedHashMap<String,List<String>>();
								lstTest=new ArrayList<String>();
								lstTest.add(vo.getTestCode());
								mpLab.put(vo.getLabCode(),lstTest);
								mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
							}
							else
							{
								lstTest=mpLab.get(vo.getLabCode());
								if(lstTest!=null&&lstTest.size()>0)
								{
									lstTest.add(vo.getTestCode());
									mpLab.put(vo.getLabCode(),lstTest);
									mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
								}
								else
								{
									lstTest=new ArrayList<String>();
									lstTest.add(vo.getTestCode());
									mpLab.put(vo.getLabCode(),lstTest);
									mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
								}
							}

						}
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

				return mpBookMark;
			}

			
			public  String getfileuploaddatatestwise(String patVO,UserVO _userVO,String testParaMeterCode)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<Inv_EpisodeVO> listEpisodeVO=new ArrayList<Inv_EpisodeVO>();
				String ar="";
				try
				{    
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);

					 ar=invEssentialDAO.getfileuploaddatatestwise(patVO, _userVO,testParaMeterCode);
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
				return ar;

			}

			
			//fungus
			
			public Map  LabComboForAudit(invFungusProcessFB invinvAntibioticProcessFB, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List labcombo=new ArrayList();

				try
				{
					tx.begin();
					invFungusProcessDAO onlinePatientDao = new invFungusProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					labcombo=onlinePatientDao.LabComboForAudit(invinvAntibioticProcessFB,_UserVO);
					mp.put(InvestigationConfig.ORGANISM_COMBO, labcombo);


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
			
			public Map  getAntibioticName(invFungusProcessVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List<invFungusProcessVO> testcombo=new ArrayList<invFungusProcessVO>();
				List<invFungusProcessVO> testcomboprefrred=new ArrayList<invFungusProcessVO>();

				try
				{
					tx.begin();
					invFungusProcessDAO onlinePatientDao = new invFungusProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					testcombo=onlinePatientDao.getAntibioticName(vo,_UserVO);
					mp.put(InvestigationConfig.ANTIBIOTIC_COMBO, testcombo);
					
					testcomboprefrred=onlinePatientDao.getAntibioticNamepreferred(vo,_UserVO);
					mp.put(InvestigationConfig.ANTIBIOTIC_COMBO_PREFFREDED, testcomboprefrred);


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
			
			
			public String  getxml(invFungusProcessVO vo, UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				String mp;
				

				try
				{
					tx.begin();
					invFungusProcessDAO onlinePatientDao = new invFungusProcessDAO(tx);
					//labMstDAOi.fetchLab(labMasterVO, _UserVO);

					mp=onlinePatientDao.getxml(vo,_UserVO);
					


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

			
			
			public void refund(String pataccNo,OnlinePatientAcceptanceVO voPatient,  UserVO _userVO,Map<String,String> keyy) 
			
			{
			
				JDBCTransactionContext tx = new JDBCTransactionContext();
                 tx.begin();
				SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);

				
				String requisitionNumber=voPatient.getRequisitionNo();
				InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();   
              
				//added parameters value by chandan
				
				voPatient.setRequisitionNorefund(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");
				
				Inv_RequisitionRaisingPatientVO patVO=null;
              	String crno=voPatient.getPatCRNo();
		patVO=objSampleAcceptanceDAO.getInvRaisingPatDetailsnew(crno,_userVO,requisitionNumber);
		
		
		String  requisitionTypeForBilling="";
		
		if(patVO.getPatvisittypecode()==null)
			requisitionTypeForBilling="1";
		else
			requisitionTypeForBilling=patVO.getPatvisittypecode();
		
		
		
		if(voBillingDtl.getTariffDetails()==null)
		{
			voBillingDtl.setTariffDetails(new ArrayList<String>());
			voBillingDtl.setTariffQty(new ArrayList<String>());
		}
		
		voBillingDtl.getTariffDetails().add(voPatient.getLabCode()+voPatient.getTestCode());
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
	
	    if(voPatient.getGroupCode()!=null && !voPatient.getGroupCode().equals("null") && (!voPatient.getGroupCode().equals("0") && !voPatient.getGroupCode().equals("")))
	    {
	    	makeBillingTestWise="4";//grpwise
	    	simpletariffdetails=voPatient.getLabCode()+voPatient.getGroupCode();
	    }
		 
	}
	
	// if reject
	   
    if( voPatient.getGroupCode()!=null && !voPatient.getGroupCode().equals("null") && ( !voPatient.getGroupCode().equals("0") && !voPatient.getGroupCode().equals("")) && keyy.size()==0)
    {
 	   objSampleAcceptanceDAO.makeRefundpatbased(voPatient,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
 	  String keyyid=voPatient.getRequisitionNo()+"#"+voPatient.getGroupCode();
 	 keyy.put(keyyid, "1");   
    }
    else if(   voPatient.getGroupCode()!=null && !voPatient.getGroupCode().equals("null") && ( !voPatient.getGroupCode().equals("0") && !voPatient.getGroupCode().equals("")) && keyy.size()>0)
    {
 	   
 	   if(!keyy.containsKey(voPatient.getRequisitionNo()+"#"+voPatient.getGroupCode()))
 	   {
 		   objSampleAcceptanceDAO.makeRefundpatbased(voPatient,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
 		  String keyyid=voPatient.getRequisitionNo()+"#"+voPatient.getGroupCode();
 		  keyy.put(keyyid, "1");
 	   }
    
    }

    
    if(voPatient.getGroupCode()==null || voPatient.getGroupCode().equals("null") ||  voPatient.getGroupCode().equals("") || voPatient.getGroupCode().equals("0"))
        objSampleAcceptanceDAO.makeRefundpatbased(voPatient,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
		
				
			}
			

			
			
			public String  isfromFTPorMONGO( UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				String labcombo="";

				try
				{
					tx.begin();
					InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);
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
			
			//added by krishnan nema on 25042019
			public List<Inv_SampleCollectionVO> getPatListSampleColAdvance(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO) 
			{

				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
				lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

				try {

					tx.begin();
					SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

					lstinvSampleCollectionVO =objSampleCollectionDAO.getPatListSampleColAdvance(objSampleCollectionVO,_UserVO);


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

				return lstinvSampleCollectionVO;

			}
			
			
			public  String getechodata(String reqdno,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<Inv_EpisodeVO> listEpisodeVO=new ArrayList<Inv_EpisodeVO>();
				String ar="";
				try
				{    
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);

					 ar=invEssentialDAO.getechodata(reqdno, _userVO);
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
				return ar;

			}
			
			
			
			public Map  getessentialdetailsforxray(UserVO userVO,Inv_RequisitionRaisingPatientVO patVO,List<Inv_PatientAdmissionDtlVO> patadm) 
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				String labNames="";
				String testNames="";
				String testGroupNames="";
				String advisedByNames="";
				String testCodeWise="";
				String testCodeWiseValueForCombo="";
				String testCodeWiseValueForComboValue="";
				
				Map mp=new HashMap();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
				//	List lstLabNames=invEssentialDAO.getLabNames(userVO);
					List lstLabNames=null;
				//	List lstTestNames=invEssentialDAO.getTestNames(userVO);
					List lstTestNames=null;

					List lstTestGroupNames=invEssentialDAO.getTestGroupNames(userVO);
		          
					List lstadvisedByNames=invEssentialDAO.getAdvisedByNames(userVO);
					
					
					String testfornotaddingviewscharge=invEssentialDAO.getbillfornoechargeviews(userVO);

					
					List<LabTestVO> lsttestNames=invEssentialDAO.gettestnameforxrayprocess(userVO);

					
					


					StringBuilder testBuild = new StringBuilder();

					

				for(LabTestVO vo:lsttestNames)
						{
				testBuild.append("{ label: \""+vo.getTestName()+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"#0"+"\" }");
							testBuild.append(",");
					
							testBuild.append("{ label: \""+vo.getUserTestCode()+"\" ,  value: \""+vo.getTestCode()+"#"+vo.getLabCode()+"#1"+"\" }");
							testBuild.append(",");
							
							}
				
				testBuild.deleteCharAt(testBuild.length()-1);
				testNames="["+testBuild.toString()+"]";
					
				//	List lstTestCodeWise=invEssentialDAO.getTestCodeNames(userVO);
					List lstTestCodeWise=null;
					
					if(lstLabNames!=null)
					{
						StringBuilder sb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstLabNames.size() - 1 ; i++) {
							sb.append("{ label: \""+((Entry)lstLabNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstLabNames.get(i)).getValue()+"\" }");
							sb.append(",");
						}

						// last string, no separator
						if(lstLabNames.size() > 0){
							sb.append("{ label: \""+((Entry)lstLabNames.get(lstLabNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstLabNames.get(lstLabNames.size()-1)).getValue()+"\" }");
						}

						labNames="["+sb.toString()+"]";
					}

		 
			
		 
					/*if(lstTestNames!=null)
					{
						StringBuilder sbb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstTestNames.size() - 1 ; i++) {
							sbb.append("{ label: \""+((Entry)lstTestNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(i)).getValue()+"\" }");
							sbb.append(",");
						}

						// last string, no separator
						if(lstTestNames.size() > 0){
							sbb.append("{ label: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getValue()+"\" }");
						}

						testNames="["+sbb.toString()+"]";
						System.out.println("testName"+sbb.toString());	
						System.out.println("testName"+testNames);
					}*/
			 
				/*	if(lstTestGroupNames!=null)
					{
						StringBuilder sb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstTestGroupNames.size() - 1 ; i++) {
							sb.append("{ label: \""+((Entry)lstTestGroupNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestGroupNames.get(i)).getValue()+"\" }");
							sb.append(",");
						}

						// last string, no separator
						if(lstTestGroupNames.size() > 0){
							sb.append("{ label: \""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestGroupNames.get(lstTestGroupNames.size()-1)).getValue()+"\" }");
						}

						testGroupNames="["+sb.toString()+"]";
					}*/
					
					
					
					if(lstadvisedByNames!=null)
					{
						StringBuilder sb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstadvisedByNames.size() - 1 ; i++) {
							sb.append("{ label: \""+((Entry)lstadvisedByNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(i)).getValue()+"\" }");
							//sb.append("{ label: \""+((Entry)lstTestCodeWise.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(i)).getValue()+"\" }");
						//	((Entry)lstTestCodeWise.get(i)).getValue()
							sb.append(",");
						}

						// last string, no separator
						if(lstadvisedByNames.size() > 0){
							sb.append("{ label: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getValue()+"\" }");
						//	sb.append("{ label: \""+((Entry)lstTestCodeWise.get(lstTestCodeWise.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getValue()+"\" }");
						
						}

						advisedByNames="["+sb.toString()+"]";
					}
					
					/*
					if(lstTestCodeWise!=null)
					{
						StringBuilder sb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstTestCodeWise.size() - 1 ; i++) {
							sb.append("{ label: \""+((Entry)lstTestCodeWise.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestCodeWise.get(i)).getValue()+"\" }");
							sb.append(",");
						}

						// last string, no separator
						if(lstTestCodeWise.size() > 0){
							sb.append("{ label: \""+((Entry)lstTestCodeWise.get(lstTestCodeWise.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestCodeWise.get(lstTestCodeWise.size()-1)).getValue()+"\" }");
						}

						testCodeWise="["+sb.toString()+"]";
						
						
						
						 
					}*/
					
					/*//For Test
					if(lstTestCodeWise!=null)
					{
						StringBuilder sb = new StringBuilder();

						// all but last
						for(int i = 0; i < lstTestCodeWise.size() - 1 ; i++) {
							sb.append(((Entry)lstTestCodeWise.get(i)).getLabel());
							sb.append(",");
						}
						testCodeWiseValueForCombo=sb.toString();
						
						for(int i = 0; i < lstTestCodeWise.size() - 1 ; i++) {
							sb.append(((Entry)lstTestCodeWise.get(i)).getValue());
							sb.append(",");
						}
		                
						testCodeWiseValueForComboValue=sb.toString();
						
						
						 
					}
					*/

					//mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);

					mp.put(InvestigationConfig.ARRAY_TESTNAMES_XRAYPROCESS, testNames);

				//	mp.put(InvestigationConfig.ARRAY_TESTGROUPNAMES, testGroupNames);
					
					
					mp.put(InvestigationConfig.ARRAY_ADVISEDBY_NAMES_XRAYPROCESS, advisedByNames);
					
					
				
					mp.put(InvestigationConfig.testfornotaddingviewscharge, testfornotaddingviewscharge);

					List<LabTestVO> lstLabTest=invEssentialDAO.searchLabWiseTestDtlstariffxrayprocess(userVO,patVO,patadm);

					Map<String,LabTestVO> mplist=new HashMap<String,LabTestVO>();
					
					if(lstLabTest!=null && lstLabTest.size()>0)
					{
						
						for(int i=0;i<lstLabTest.size();i++)
						{
							LabTestVO vo=lstLabTest.get(i);
							String keyy=vo.getTestCode()+"#"+vo.getLabCode();
							mplist.put(keyy, vo);
							
						}
						
						
					}
					
					mp.put(InvestigationConfig.MAP_ALL_TEST_DATA_XRAY_PROCESS, mplist);
					
					List<LabTestVO> lstviews=invEssentialDAO.getviews(userVO);

                   Map<String,List<LabTestVO>> mplistviews=new HashMap<String,List<LabTestVO>>();
					
					if(lstviews!=null && lstviews.size()>0)
					{
						
						for(int i=0;i<lstviews.size();i++)
						{
							LabTestVO vo=lstviews.get(i);
							String keyy=vo.getTestCode();
							
							if(mplistviews.containsKey(keyy))
							{
								List<LabTestVO> lst=(List<LabTestVO>)mplistviews.get(keyy);
								lst.add(vo);
							mplistviews.put(keyy, lst);
							}
							else
							{
								List<LabTestVO> lst=new ArrayList<LabTestVO>();
								lst.add(vo);
								mplistviews.put(keyy, lst);

							}
						}
						
						
					}
					
					mp.put(InvestigationConfig.MAP_ALL_TEST_DATA_XRAY_VIEWS_PROCESS, mplistviews);

				//	mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCodeWise);
					
					
					/*mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_FOR_COMBO, testCodeWiseValueForCombo);
					mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE_FOR_COMBO_VALUE, testCodeWiseValueForComboValue);*/

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
			

			
			public  synchronized List savexrayRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO,String priorityAll,HttpSession session)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List listReqId=new ArrayList();
				String labName="";
				String testName="";
				String billno="";
				String reqnoforpid="";
				 String datapidd="";
				 String datapidddata="";

				try
				{    
					
					
				
					tx.begin();
					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					
					InvestigationBillingDAOxray invBillingDAO=new InvestigationBillingDAOxray(tx);
					
					InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
					
			         RequistionHeaderVO voReqHeadernew = new RequistionHeaderVO();

					String xraytestforwhichextrachargenotadd=invEssentialDAO.gettestcodesforwhichnotaddextracharge(_userVO);
			 
					 
					//First iterate the Map over Lab codes for generating requisition no's
					
					//String[] labCodeArray=(String[])mp.keySet().toArray();
					
					//int size=labCodeArray.length;
					
					int requisitionTypeForBilling=0;
					
					Iterator itrLab=mp.keySet().iterator();
					
					while(itrLab.hasNext())//for(int i=0;i<size;i++)
					{
						testName="";
						String requisitionNumber="";
						String crNO="";
						String patStatus="";
						
						String err="";
						
						String labCode=(String)itrLab.next();
						//Generate Requisition No Sequence  for each lab
					
						
						// Procedure to generate and insert/update req no
						
						//String sequence_Hash_yymmdd=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);  // Returns sequence#yymmdd
						
						//String sequence=sequence_Hash_yymmdd.split("#")[0];
						//String yymmdd=sequence_Hash_yymmdd.split("#")[1];
						
						//Logic to check the sequence is '10001' 
						//if(sequence.equals(InvestigationConfig.REQUISITION_NO_SEQUENCE_INVESTIGATION)) //10001
						//{
							// Insert in Requisition Maintainer Table
							//	invEssentialDAO.insertRequisitionSequenceInMaintainer(labCode,sequence,yymmdd,_userVO);
						//}
						//else
					/*	{
							invEssentialDAO.updateRequisitionSequenceInMaintainer(sequence, labCode, _userVO);
						}*/
						
						//Insertion in Requisition Header Detail Table
						
						
						//check if new requisitions or already raised one 
						
						  Map<String,LabTestVO> testInLab=mp.get(labCode);
						int totalTests=testInLab.size();
						int testsAlreadyRaised=0;
						
						Iterator itrTestInLab=testInLab.keySet().iterator();
				    
						
						String labCode1="";
						String reqdno1="";
						String reqno1="";
						
						while(itrTestInLab.hasNext())
						{
							String testCode=(String)itrTestInLab.next();
							
							LabTestVO voLabTest=testInLab.get(testCode);
							
							if(voLabTest.getAlreadyRaised().equals("1"))
								testsAlreadyRaised++;
						}
						
						
						
						if(testsAlreadyRaised<totalTests)
						
						{
						
						   
						   // Prepare Requisition Number Dynamically with given values in LLLLLYYMMDDXXXXX format
						 //  String requisitionNumber=_userVO.getHospitalCode()+labCode+yymmdd+sequence; //hospitalcodeLLLLLYYMMDDXXXXX format
						  if(session.getAttribute("IsAddendum")!=null)
						  {
							   reqno1=(String) session.getAttribute("reqNo");
							   reqdno1=(String) session.getAttribute("reqDno");
							   labCode1=(String) session.getAttribute("labCode");
							  
							  
						  }
							
						  if(session.getAttribute("IsAddendum")!=null)
						  {
							  String oldreqno=(String) session.getAttribute("reqNo");
							  if(labCode.equals(labCode1))
							  {
								 // crNO=patVO.getPatCRNo();
								  requisitionNumber=reqno1;
								  err="success";
								  crNO=patVO.getPatCRNo();
								  patStatus =patVO.getPatStatusCode();
								   //Setting Requisition HeaderVO Values
								   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
								   voReqHeader.setLabCode(labCode);
								   voReqHeader.setRequisitionNumber(requisitionNumber);
								   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
								   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
								   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
								   voReqHeader.setBedCode(patVO.getBedCode());
								   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
								   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
								   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
								   voReqHeader.setGenderCode(patVO.getPatGenderCode());
								   voReqHeader.setIsActualDob(patVO.getIsActualDob());
								   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
								   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
								   voReqHeader.setMlcNo(patVO.getPatmlcno());
								   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
								   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
								    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
								   voReqHeader.setPatDob(patVO.getPatDOB());
								   voReqHeader.setPatAge(patVO.getPatAge());
								   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
								   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
								
								   
								   if(patVO.getPatStatusCode().equals("2"))
									{						  
										requisitionTypeForBilling=2;
									}
									else 
									{
										//visit type code 1-opd, 2,3-emergency, 4 special
										if(patVO.getPatvisittypecode()==null)
											requisitionTypeForBilling=1;
										else{
											
										
										if(patVO.getPatvisittypecode().equals("1"))
											requisitionTypeForBilling=1;
										if(patVO.getPatvisittypecode().equals("4"))
											requisitionTypeForBilling=4;
										if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
											requisitionTypeForBilling=3;
										}
										
									}
									   
								   
								  // voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
								   voReqHeader.setRequsitionRaisedThrough("5");  //7
								   
								   
								   voReqHeader.setRoomCode(patVO.getPatroomno());
								   voReqHeader.setWardCode(patVO.getPatwardcode());
								   voReqHeader.setReqType(""+requisitionTypeForBilling);
								   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
								   voReqHeader.setWardName(patVO.getPatwardname());
								   voReqHeader.setBedName(patVO.getBedname());
								   
								   voReqHeader.setVisitNo(patVO.getPatVisitNo());
								  
								   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
								   
								   
								   
								   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
								    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
								    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
								    voReqHeader.setVisitReason(patVO.getVisitReason());
								    voReqHeader.setIsassociatedTest("1");
								    voReqHeader.setIsassociatedreqno(reqno1);
								 
								
								  
								  
								  
							  }
							  else
							  { 

								  invEssentialDAO.updateinheaderfordifflabaddendum( requisitionNumber, oldreqno,_userVO); 
							  
								  
								  crNO=patVO.getPatCRNo();
								  patStatus=patVO.getPatStatusCode();
								   //Setting Requisition HeaderVO Values
								   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
								   voReqHeader.setLabCode(labCode);
								   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
								   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
								   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
								   voReqHeader.setBedCode(patVO.getBedCode());
								   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
								   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
								   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
								   voReqHeader.setGenderCode(patVO.getPatGenderCode());
								   voReqHeader.setIsActualDob(patVO.getIsActualDob());
								   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
								   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
								   voReqHeader.setMlcNo(patVO.getPatmlcno());
								   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
								   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
								    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
								   voReqHeader.setPatDob(patVO.getPatDOB());
								   voReqHeader.setPatAge(patVO.getPatAge());
								   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
								   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
								
								   
								   if(patVO.getPatStatusCode().equals("2"))
									{						  
										requisitionTypeForBilling=2;
									}
									else 
									{
										//visit type code 1-opd, 2,3-emergency, 4 special
										if(patVO.getPatvisittypecode()==null)
											requisitionTypeForBilling=1;
										else{
											
										
										if(patVO.getPatvisittypecode().equals("1"))
											requisitionTypeForBilling=1;
										if(patVO.getPatvisittypecode().equals("4"))
											requisitionTypeForBilling=4;
										if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
											requisitionTypeForBilling=3;
										}
										
									}
									   
								   
								  // voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
								   voReqHeader.setRequsitionRaisedThrough("5");  //7
								   
								   
								   voReqHeader.setRoomCode(patVO.getPatroomno());
								   voReqHeader.setWardCode(patVO.getPatwardcode());
								   voReqHeader.setReqType(""+requisitionTypeForBilling);
								   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
								   voReqHeader.setWardName(patVO.getPatwardname());
								   voReqHeader.setBedName(patVO.getBedname());
								   
								   voReqHeader.setVisitNo(patVO.getPatVisitNo());
								  
								   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
								   
								   
								   
								   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
								    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
								    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
								    voReqHeader.setVisitReason(patVO.getVisitReason());
								    voReqHeader.setIsassociatedTest("1");
								    voReqHeader.setIsassociatedreqno(reqno1);
								    voReqHeadernew = voReqHeader;

								
								   
								   for(int p=0;p<3;p++)
								    {
								    
								    requisitionNumber=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);	//procedure generating req no and inserting/updating it accordingly
								    voReqHeader.setRequisitionNumber(requisitionNumber);
								   
								     err= invEssentialDAO.insertRequisitionHeaderDtl_addendum(voReqHeader,_userVO);	//procedure to insert in req dtl
								     voReqHeadernew = voReqHeader;
								   
								     System.out.println("insert in header tbl for req no "+requisitionNumber +"error:"+ err);
								     voReqHeader.setStatus(err);

								    if(!err.contains("duplicate key value violates"))
								      {break;}
								      
								    }
								   
							  }
							  
						  }
						  else
						  {
						 
						 crNO=patVO.getPatCRNo();
						patStatus= patVO.getPatStatusCode();
						   //Setting Requisition HeaderVO Values
						   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
						   voReqHeader.setLabCode(labCode);
						   
						   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
						   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
						   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
						   voReqHeader.setBedCode(patVO.getBedCode());
						   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
						   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
						   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
						   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
						   voReqHeader.setGenderCode(patVO.getPatGenderCode());
						   voReqHeader.setIsActualDob(patVO.getIsActualDob());
						   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
						   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
						   voReqHeader.setMlcNo(patVO.getPatmlcno());
						   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
						   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
						    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
						   voReqHeader.setPatDob(patVO.getPatDOB());
						   voReqHeader.setPatAge(patVO.getPatAge());
						   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
						   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
						
						   
						   if(patVO.getPatStatusCode().equals("2"))
							{						  
								requisitionTypeForBilling=2;
							}
							else 
							{
								//visit type code 1-opd, 2,3-emergency, 4 special
								if(patVO.getPatvisittypecode()==null)
									requisitionTypeForBilling=1;
								else{
									
								
								if(patVO.getPatvisittypecode().equals("1"))
									requisitionTypeForBilling=1;
								if(patVO.getPatvisittypecode().equals("4"))
									requisitionTypeForBilling=4;
								if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
									requisitionTypeForBilling=3;
								}
								
							}
							   
						   
						 //  voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
						   voReqHeader.setRequsitionRaisedThrough("5");  //7
						   
						   
						   voReqHeader.setRoomCode(patVO.getPatroomno());
						   voReqHeader.setWardCode(patVO.getPatwardcode());
						   voReqHeader.setReqType(""+requisitionTypeForBilling);
						   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
						   voReqHeader.setWardName(patVO.getPatwardname());
						   voReqHeader.setBedName(patVO.getBedname());
						   
						   voReqHeader.setVisitNo(patVO.getPatVisitNo());
						  
						   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
						   
						   
						   
						   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
						    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
						    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
						    voReqHeader.setVisitReason(patVO.getVisitReason());
						    voReqHeadernew = voReqHeader;
						    
						    for(int p=0;p<3;p++)
						    {
						    
						    requisitionNumber=invEssentialDAO.generateRequisitionNoSequence(labCode, _userVO);	//procedure generating req no and inserting/updating it accordingly
						    voReqHeader.setRequisitionNumber(requisitionNumber);
						   
						     err= invEssentialDAO.insertRequisitionHeaderDtl(voReqHeader,_userVO);	//procedure to insert in req dtl
						     voReqHeadernew = voReqHeader;
						     System.out.println("insert in header tbl for req no "+requisitionNumber +"error:"+ err);
						     voReqHeader.setStatus(err);

						    if(!err.contains("duplicate key value violates"))
						      {break;}
						      
						    }
						   
						   
						  /* if(voReqHeader.getLabbasedaptdatetime()!=null && !voReqHeader.getLabbasedaptdatetime().equals(""))
						   invEssentialDAO.updateappointmentdateinheader(voReqHeader,_userVO);	//procedure to insert in req dtl
		*/
						}
					}	  
						else
						{
							
							 crNO=patVO.getPatCRNo();
							patStatus= patVO.getPatStatusCode();
							 if(patVO.getPatStatusCode().equals("2"))
								{						  
									requisitionTypeForBilling=2;
								}
								else 
								{
									//visit type code 1-opd, 2,3-emergency, 4 special
									if(patVO.getPatvisittypecode().equals("1"))
										requisitionTypeForBilling=1;
									if(patVO.getPatvisittypecode().equals("4"))
										requisitionTypeForBilling=4;
									if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
										requisitionTypeForBilling=3;
								}
							 
						
							
						}
						
						//Insertion in HIVT_REQUISITION_DTL Table
						   // Getting Map of Test from Map of Lab
						   
						if(err.equalsIgnoreCase("success"))
						{
					     Map<String,LabTestVO> mpTest=mp.get(labCode);
						   
							//First iterate the Map over Test codes for generating requisitionDno's
							
							//String[] testCodeArray=(String[])mpTest.keySet().toArray();
							
							//int sizeTest=testCodeArray.length;
					     
					     	Iterator itrTest=mpTest.keySet().iterator();
					     	
							int testCounter=0;
							String runningSequence="00";
							
							while(itrTest.hasNext())//for(int j=0;j<sizeTest;j++)
							{
								
								String testCode=(String)itrTest.next();
								LabTestVO newLabTest=new LabTestVO();
								newLabTest=mpTest.get(testCode);
								InvestigationRequistionVO voReq=new InvestigationRequistionVO();
							
								voReq.setViewscount(newLabTest.getViewscount());
							
								
								voReq.setPiddata(newLabTest.getPiddata());
								voReq.setPidtestcontains(newLabTest.getPidtestcontains());
								voReq.setIsAppointment(newLabTest.getIsAppointment());

								voReq.setIslabappointmentbased(newLabTest.getIslabappointmentbased());
								
								if(newLabTest.getAlreadyRaised().equals("1"))
								{
									
									voReq.setStrRequsitionDNo(newLabTest.getRequisitionDNo());
									voReq.setStrReqNo(newLabTest.getRequisitionDNo().substring(0, newLabTest.getRequisitionDNo().length()-2));
									voReq.setStrTestGroupCode((newLabTest.getTestGroupCode().equals("0")?null:newLabTest.getTestGroupCode()));
									
									
									voReq.setStrLabCode(newLabTest.getLabCode());
									voReq.setStrTestCode(newLabTest.getTestCode());
									
									
									voReq.setStrTestGroupType((newLabTest.getTestGroupType().equals("0")?null:newLabTest.getTestGroupType()));
									voReq.setIslabappointmentbased((newLabTest.getIslabappointmentbased()));
									voReq.setIsAppointment(newLabTest.getIsAppointment());

									voReq.setStrAppointmentDate(newLabTest.getAppointmentDate());
									voReq.setStrAppointmentTime(newLabTest.getAppointmentTime());
									voReq.setAppointmentRefNo(newLabTest.getAppointmentRefNo());
									voReq.setLabbaseddatetimeappt(newLabTest.getLabbasedaptdatetime());
									voReq.setSite(newLabTest.getSite());
									
									voReq.setStrSampleCode(newLabTest.getSampleCode());
										invEssentialDAO.saveAppointmentDetail(voReq,_userVO);	
									
										   if(voReq.getLabbaseddatetimeappt()!=null && !voReq.getLabbaseddatetimeappt().equals(""))
											   invEssentialDAO.updateappointmentdateinheader(voReq,_userVO);	//procedure to insert in req dtl

										   
										//confirm Appoitment 
										if(!newLabTest.getAppoitmentNo().equals("0")&&newLabTest.getAppoitmentNo()!=null)
										{
										voReq.setAppointmentNo(newLabTest.getAppoitmentNo());
										invEssentialDAO.ConfirmAppointment(voReq, _userVO);
										}
									
									
										//Billing Logic
										//Billing Logic //modified to include the sample blood for extra charge
										/*if(newLabTest.getReqdSampleName().equalsIgnoreCase("blood"))
										{*/
										
										//comment for extra charge by ashu 
										
										//newraiseadvice=newLabTest.getRaiseAdvise();
										
										
										if(newLabTest.getReqSampleShortName().equalsIgnoreCase("bld"))
										{
											if(voBillingDtl.getRequisitionNos()==null)
											{
												voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
												voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+"|BLD!");
												voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
												voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
											}
											else
											{
											if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
											{
												voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
											voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+"|BLD!");
											voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
											voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
											}
											else
											{
												voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
												voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos().replace(voReq.getStrReqNo(), voReq.getStrReqNo()+"|BLD"));
												
												}
											}
											
										}
										else
										{								
										if(voBillingDtl.getRequisitionNos()==null)
										{
											voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
											voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
											voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
											voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
										}
										else
										{
										if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
										{
											voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
										voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
										voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
										}
										}
										}
										
									/*	*/
										
										if(voBillingDtl.getRequisitionNos()==null)
										{
											voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
											voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
											voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
											voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
										}
										else
										{
										if(!voBillingDtl.getRequisitionNos().contains(voReq.getStrReqNo()+"!"))
										{
											voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+voReq.getStrReqNo()+'!');
										voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
										voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
										}
										}
										System.out.println("voBillingDtl1.setRequisitionNos::::::::  "+voBillingDtl.getRequisitionNos());
										
										if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
										{
											if(newLabTest.getTestGroupType()==null ||newLabTest.getTestGroupType().equals("")||newLabTest.getTestGroupCode().equals("0")||newLabTest.getTestGroupType().equals("3"))
											{
												if(voBillingDtl.getTariffDetails()==null)
												{
													voBillingDtl.setTariffDetails(new ArrayList<String>());
													voBillingDtl.setTariffQty(new ArrayList<String>());
												}
												
												voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
												
												if(xraytestforwhichextrachargenotadd!=null && !xraytestforwhichextrachargenotadd.equals("") && xraytestforwhichextrachargenotadd.contains(testCode))
													voBillingDtl.getTariffQty().add("1");
												else
													voBillingDtl.getTariffQty().add(newLabTest.getViewscount()=="0"?"1":newLabTest.getViewscount());
												
												voBillingDtl.getViewcode().add(newLabTest.getViewCode());								
												voBillingDtl.getReqdno().add(newLabTest.getRequisitionDNo());								
												voBillingDtl.getReqno().add(newLabTest.getReqNo());				
												
											}
											else
											{
												if(voBillingDtl.getGrouptariffDetails()==null)
												{
													voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
													voBillingDtl.setGrouptariffQty(new ArrayList<String>());
												}
												
												voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
												if(xraytestforwhichextrachargenotadd!=null && !xraytestforwhichextrachargenotadd.equals("") && xraytestforwhichextrachargenotadd.contains(testCode))
													voBillingDtl.getTariffQty().add("1");
												else
													voBillingDtl.getTariffQty().add(newLabTest.getViewscount()=="0"?"1":newLabTest.getViewscount());
												
												voBillingDtl.getViewcode().add(newLabTest.getViewCode());								
												voBillingDtl.getReqdno().add(newLabTest.getRequisitionDNo());								
												voBillingDtl.getReqno().add(newLabTest.getReqNo());				
												
											}
										}
								}
								else
								{
									
									List<InvestigationRequistionVO> list_on_old_reqdno=new ArrayList<InvestigationRequistionVO>();
									InvestigationRequistionVO fetch_list_on_old_reqdno_vo= new InvestigationRequistionVO();
									
									String requisitionDNo="";
							            
									if(session.getAttribute("IsAddendum")!=null)
										
									{
										String labcode=(String) session.getAttribute("labCode");
										
										String reqno=(String) session.getAttribute("reqNo");
								        
										String reqdno=(String) session.getAttribute("reqDno");
										
										list_on_old_reqdno=invEssentialDAO.getDetailsOnBasisReqDno(reqdno,_userVO);
										
										  if(labCode.equals(newLabTest.getLabCode()))
										  {
											   requisitionDNo=invEssentialDAO.isaddendumreqdno(reqno, _userVO);  // max dno generate for same lab for addendum test
											   
											   
										  }
										  else
										  {
												testCounter++;
												//Logic for generating running sequence 'XX'
												if(testCounter/10<1)
													runningSequence="0"+Integer.toString(testCounter); //Like 01,02,03 etc..
												else
													runningSequence=Integer.toString(testCounter);
												
												//requisitionDNo generation:: format requisitionNumberXX
												
												 requisitionDNo=requisitionNumber+runningSequence;
											  
										  }
										  
										  
										  if(list_on_old_reqdno!=null)
										  {
											  for(int i=0;i<list_on_old_reqdno.size();i++)
											  {
											      
												  fetch_list_on_old_reqdno_vo=  list_on_old_reqdno.get(i);
											 	  
												  voReq.setTempSampleNo(fetch_list_on_old_reqdno_vo.getTempSampleNo());
												  voReq.setStrAcceptanceDateTime(fetch_list_on_old_reqdno_vo.getStrAcceptanceDateTime());
												  voReq.setStrAcceptanceSeatId(fetch_list_on_old_reqdno_vo.getStrAcceptanceSeatId());
												  voReq.setStrCollDateTime(fetch_list_on_old_reqdno_vo.getStrCollDateTime());
												  voReq.setStrCollectionSeatId(fetch_list_on_old_reqdno_vo.getStrCollectionSeatId());
												  voReq.setStrSampleCollectionAreaCode(fetch_list_on_old_reqdno_vo.getStrSampleCollectionAreaCode());
												  voReq.setIs_Sample_Received(fetch_list_on_old_reqdno_vo.getIs_Sample_Received());
												  voReq.setSampleNo(fetch_list_on_old_reqdno_vo.getSampleNo());
												  voReq.setUomCode(fetch_list_on_old_reqdno_vo.getUomCode());
												  voReq.setCollectedVolume(fetch_list_on_old_reqdno_vo.getCollectedVolume());
												  voReq.setContainerCode(fetch_list_on_old_reqdno_vo.getContainerCode());
												  voReq.setStrDailyLabNo(fetch_list_on_old_reqdno_vo.getStrDailyLabNo());
												  voReq.setStrIsAccepted(fetch_list_on_old_reqdno_vo.getStrIsAccepted());
												  
												  voReq.setChangeCount(fetch_list_on_old_reqdno_vo.getChangeCount());
												  voReq.setReportChange(fetch_list_on_old_reqdno_vo.getReportChange());
												  voReq.setReportChangeDate(fetch_list_on_old_reqdno_vo.getReportChangeDate());
												  voReq.setReportChangeSeatId(fetch_list_on_old_reqdno_vo.getReportChangeSeatId());
												  voReq.setReportUrl(fetch_list_on_old_reqdno_vo.getReportUrl());
												  voReq.setReportDate(fetch_list_on_old_reqdno_vo.getReportDate());
											  }
											  
										  }
										  
									}
									
									if(session.getAttribute("IsAddendum")==null)
									{
								testCounter++;
								//Logic for generating running sequence 'XX'
								if(testCounter/10<1)
									runningSequence="0"+Integer.toString(testCounter); //Like 01,02,03 etc..
								else
									runningSequence=Integer.toString(testCounter);
								
								//requisitionDNo generation:: format requisitionNumberXX
								
								 requisitionDNo=requisitionNumber+runningSequence;
									}
									
							   //Setting Requisition HeaderVO Values
							
								voReq.setStrLabCode(newLabTest.getLabCode());
								voReq.setStrTestCode(newLabTest.getTestCode());
								voReq.setStrSampleCode(newLabTest.getSampleCode());
								voReq.setStrRequsitionDNo(requisitionDNo);
								voReq.setStrReqNo(requisitionNumber);
								voReq.setStrWorkOrderSequence(runningSequence);
								voReq.setStrPriority((newLabTest.getPriority()==null?InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL:newLabTest.getPriority()));
								
								if(newLabTest.getTestType().equals("P"))
									voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PATIENT_BASED);
								else
									voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
								
								
								if(newLabTest.getRaiseAdvise().equals("0"))
									voReq.setStrRequisitionDtlStatus(InvestigationConfig.APPOINTMENT_ADVISED_DESK);
								
								
								voReq.setStrTypeOfComponent("1"); //need to configure
								
								voReq.setStrTestGroupCode((newLabTest.getTestGroupCode().equals("0")?null:newLabTest.getTestGroupCode()));
								
								voReq.setStrTestGroupType((newLabTest.getTestGroupType().equals("0")?null:newLabTest.getTestGroupType()));
							    
								voReq.setIslabappointmentbased((newLabTest.getIslabappointmentbased()));
								voReq.setIsAppointment(newLabTest.getIsAppointment());

								voReq.setStrAppointmentDate(newLabTest.getAppointmentDate());
								voReq.setStrAppointmentTime(newLabTest.getAppointmentTime());
								//voReq.setFinalMandValues(voLabTest.getFinalMandValues());
								 voReq.setAdvisedBYDoctorName(newLabTest.getAdvisedByDoctorName());
								// voReq.setStrIsAppointment(voLabTest.getIsAppointment());
								
								 voReq.setAppointmentRefNo(newLabTest.getAppointmentRefNo());
								 
								 voReq.setIsrequisitionFormNeeded(newLabTest.getIsRequisitionFormNeeded());
								 voReq.setSite(newLabTest.getSite());
								 
								 voReq.setRequisitionFormData(newLabTest.getRequisitionFormData());
								 if(newLabTest.getAdvice()==null || newLabTest.getAdvice().equals("")) //xrayyyyyyy
								 {
									 voReq.setAdvice(newLabTest.getAdvice());
									 
								 }
								 else
								 {
								 String[] advices=newLabTest.getAdvice().split("@");
								 
								 for(int i1=0;i1<advices.length;i1++)
								 {
									 String[] advice=advices[i1].split("#");
									 String testcode=advice[0];
									 String labcode=advice[1];
									 
									 if(testcode.equals(voReq.getStrTestCode()) && labcode.equals(voReq.getStrLabCode()))
									 {
										 if(!advice[2].equals(""))
										 voReq.setAdvice(advice[2]); 
										 else
											 voReq.setAdvice("-");
									 }
									 
								 }
								 }
								 
								 
								String apoitmentDAte=newLabTest.getAppointmentDate();
		                    	String testlabcodeduplicatecheck="";
							
								// save requisition form data
								if(voReq.getIsrequisitionFormNeeded().equals("1"))
								{
									ResultEntryVO vo=new ResultEntryVO();
									
								if(!voReq.getRequisitionFormData().equals(""))
								{
									String value=voReq.getRequisitionFormData();
									
									String[] val=value.split("\\$");
									
									for(int i=val.length-1;i>=0;i--)
									{
										String data=val[i];
										
										String[] values=data.split("@");
										
										for(int i1=values.length-1;i1>=0;i1--)
										{
											
											String[] data1=values[i1].split("#");
											String testcode=data1[0].substring(0,5);
											
									       String labcode=data1[0].substring(5,10);
									       
									        
									       
											String testparameter=data1[3];
											if(!testlabcodeduplicatecheck.contains(testparameter))
											{
												
											
											
											String valuess=data1[4];
											String paracode=data1[3].substring(5, 9);
											
											if(testcode.equals(voReq.getStrTestCode()) && labcode.equals(voReq.getStrLabCode()))
											{
												testlabcodeduplicatecheck+="#"+testparameter;
											vo.setParameterRequisitionDNo(voReq.getStrRequsitionDNo());
											vo.setTestCode(testcode);
											vo.setParantParaCode(testparameter);
											vo.setTestParaMeterCode(paracode);
											vo.setLoincCode("");
										    vo.setRefRange("");
										    vo.setResultEntryValue(valuess);
										    vo.setLabCode(labcode);
										    invEssentialDAO.insertRequisitionFormDtl(vo,_userVO);

											}
											}
											
											}
										
									}
									
								}
								else
								{
										
								}
								
								}
								
								
							
								invEssentialDAO.insertRequisitionDtl(voReq,_userVO,priorityAll,voReqHeadernew);	//calling procedure to add into req dtl table
								
								voReq.setLabbaseddatetimeappt(newLabTest.getLabbasedaptdatetime());
								
								   if(voReq.getLabbaseddatetimeappt()!=null && !voReq.getLabbaseddatetimeappt().equals(""))
									   invEssentialDAO.updateappointmentdateinheader(voReq,_userVO);	//procedure to insert in req dtl

								   
									if((voReq.getPiddata()!=null && !voReq.getPiddata().equals("")) && voReq.getPidtestcontains()!=null)
									{
									
										datapidd+=voReq.getStrReqNo()+"#"+voReq.getStrLabCode()+"#"+voReq.getStrTestCode()+"#"+"@@";	
										datapidddata=voReq.getPiddata();
									}
									
									
								
								
								
								
								
								if(session.getAttribute("IsAddendum")!=null)
								{
		                             
									if(session.getAttribute("IsAddendumENTRY")!=null)
									{
									invEssentialDAO.insertRequisitionDtl_addendumNewTest(voReq,_userVO);	//update 
									}
									else
									{
										invEssentialDAO.insertRequisitionDtl_addendum(voReq,_userVO);	//update
									}
									}
								
								//invEssentialDAO.updateRequisitionHeader(requisitionNumber,apoitmentDAte,_userVO);	//calling procedure to update req/app date
								newLabTest.setFinalMandValues("undefined");//xrayyyyyyy
								newLabTest.setFinalMandCode("");//xrayyyyyyy
								
								String[] SplitedValues=newLabTest.getFinalMandValues().split("@");
								
								String[] splitedManCode=newLabTest.getFinalMandCode().split("&");
								
				
								if(!newLabTest.getFinalMandCode().equals("undefined")&&!newLabTest.getFinalMandCode().equals("null")&&!newLabTest.getFinalMandCode().equals(""))
								{
								for(int i=0;i<SplitedValues.length;i++)
								{
								voReq.setFinalMandValues(SplitedValues[i]);
								 
								voReq.setMandCode(splitedManCode[i]);
								
									invEssentialDAO.insertHivtRequsitionTestMandatoryDtl(voReq,_userVO);	//procedure to insert into req_test_mand_dtl
								}
								
								}
								
								
								//confirm Appoitment
								if(newLabTest != null){
									if(newLabTest.getAppoitmentNo()!=null && !newLabTest.getAppoitmentNo().equals("0"))
									{
										voReq.setAppointmentNo(newLabTest.getAppoitmentNo());
										invEssentialDAO.ConfirmAppointment(voReq, _userVO);
									}
								}
								
								
								// insert piddd
								
								
								//patStatus= patVO.getPatStatusCode();
								   //Setting Requisition HeaderVO Values
								
								//Billing Logic
								//Billing Logic //modified to include the sample blood for extra charge
								/*if(newLabTest.getReqdSampleName().equalsIgnoreCase("blood"))
								{	*/
								
								//comment for extra charge by ashu
								
								if(newLabTest.getReqSampleShortName().equalsIgnoreCase("bld"))
								{
								if(voBillingDtl.getRequisitionNos()==null)
								{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+"|BLD!");
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
									System.out.println("voBillingDtl2.setRequisitionNos1::::::::1  with bld"+voBillingDtl.getRequisitionNos());
								}
								else
								{
								if(!voBillingDtl.getRequisitionNos().contains(requisitionNumber))
								{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
									System.out.println("voBillingDtl2.setRequisitionNos6::::::::6 widout bld "+voBillingDtl.getRequisitionNos());
									System.out.println("requisitionNumber::::: "+requisitionNumber);
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+"|BLD!");
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								
								System.out.println("voBillingDtl2.setRequisitionNos2::::::::2  "+voBillingDtl.getRequisitionNos());
								}
								else{
									voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos().replace(requisitionNumber, requisitionNumber+"|BLD"));
									System.out.println("voBillingDtl2.setRequisitionNos3::::::::3  "+voBillingDtl.getRequisitionNos());
									}
								}
								}
								else
								{
									System.out.println("voBillingDtl.getRequisitionNos() "+voBillingDtl.getRequisitionNos());
									if(voBillingDtl.getRequisitionNos()==null || voBillingDtl.getRequisitionNos().equalsIgnoreCase(""))
									{
										voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
										voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
										voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
										System.out.println("voBillingDtl2.setRequisitionNos4::::::::4  "+voBillingDtl.getRequisitionNos());
									}
									else
									{
										//chanksssssss
									if(!(voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!") || voBillingDtl.getRequisitionNos().contains("BLD!")))
									{
										voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										
										/*if(!(voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!")) || (voBillingDtl.getRequisitionNos().contains("BLD!")))
										{*/	
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
									System.out.println("voBillingDtl2.setRequisitionNos5::::::::5  "+voBillingDtl.getRequisitionNos());
									}
									else if(!(voBillingDtl.getRequisitionNos().contains(requisitionNumber)))
									{
										voBillingDtl.setRaiseAdvise(newLabTest.getRaiseAdvise());
										voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
										voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
										voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
										System.out.println("voBillingDtl2.setRequisitionNos else::::::::5  "+voBillingDtl.getRequisitionNos());
									}
									}	
									
									
								}
								
								System.out.println("voBillingDtl2.setRequisitionNos::::::::  "+voBillingDtl.getRequisitionNos());
						/*	*/ // changeforbld ashu
								
								/*if(voBillingDtl.getRequisitionNos()==null)
								{
									voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
									voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
									voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								else
								{
								if(!voBillingDtl.getRequisitionNos().contains(requisitionNumber+"!"))
								{
								voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
								voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
								voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
								}
								}*/
									if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
									{
										if(newLabTest.getTestGroupType()==null ||newLabTest.getTestGroupType().equals("")||newLabTest.getTestGroupCode().equals("0")||newLabTest.getTestGroupType().equals("3"))
										{
											if(voBillingDtl.getTariffDetails()==null)
											{
												voBillingDtl.setTariffDetails(new ArrayList<String>());
												voBillingDtl.setTariffQty(new ArrayList<String>());
											}
											
											voBillingDtl.getTariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestCode());
											if(xraytestforwhichextrachargenotadd!=null && !xraytestforwhichextrachargenotadd.equals("") && xraytestforwhichextrachargenotadd.contains(testCode))
												voBillingDtl.getTariffQty().add("1");
											else
												voBillingDtl.getTariffQty().add(newLabTest.getViewscount()=="0"?"1":newLabTest.getViewscount());
											
											if(voBillingDtl.getViewcode()==null)
											{
												voBillingDtl.setViewcode(new ArrayList<String>());
												voBillingDtl.setReqdno(new ArrayList<String>());
												voBillingDtl.setReqno(new ArrayList<String>());
											}
											
											voBillingDtl.getViewcode().add(newLabTest.getViewCode());								
											voBillingDtl.getReqdno().add(voReq.getReqDno()==null?"":voReq.getReqDno());								
											voBillingDtl.getReqno().add(requisitionNumber);								

									  }
										else
										{
											if(voBillingDtl.getGrouptariffDetails()==null)
											{
												voBillingDtl.setGrouptariffDetails(new ArrayList<String>());
												voBillingDtl.setGrouptariffQty(new ArrayList<String>());
											}
											
											//changed for multiple groups -- ashu
											if(!(voBillingDtl.getGrouptariffDetails().contains(voReq.getStrLabCode()+voReq.getStrTestGroupCode()))){
												
												voBillingDtl.getGrouptariffDetails().add(voReq.getStrLabCode()+voReq.getStrTestGroupCode());
												if(xraytestforwhichextrachargenotadd!=null && !xraytestforwhichextrachargenotadd.equals("") && xraytestforwhichextrachargenotadd.contains(testCode))
													voBillingDtl.getTariffQty().add("1");
												else
													voBillingDtl.getTariffQty().add(newLabTest.getViewscount()=="0"?"1":newLabTest.getViewscount());											}
												
											if(voBillingDtl.getViewcode()==null)
											{
												voBillingDtl.setViewcode(new ArrayList<String>());
												voBillingDtl.setReqdno(new ArrayList<String>());
												voBillingDtl.setReqno(new ArrayList<String>());

											}
											
											voBillingDtl.getViewcode().add(newLabTest.getViewCode());								
											voBillingDtl.getReqdno().add(voReq.getReqDno()==null?"":voReq.getReqDno());								
											voBillingDtl.getReqno().add(requisitionNumber);							

										}
									}
								}
									//Adding Details for Display after Save
									labName=newLabTest.getLabName();
									if(!testName.equals(""))
									  testName=testName+","+newLabTest.getTestName();
									else
										testName=newLabTest.getTestName();
									
							} // Loop Over TestCodes
							listReqId.add(crNO+"#"+labName+"#"+testName+"#"+patStatus);
						}		
						
						else
						{
							
					          tx.rollback();  // if not save succesfully		
						}
						//Add The Requisition Number in list
						
					}
					
					
					// Billing Logic :: Save
							
							
					System.out.println("patvo before address : "+patVO.getPatAddress());
					
					if(InvestigationConfig.BILLING_REQUIRED.equals(InvestigationConfig.BILLING_REQUIRED_YES))
					{
								String simpletariffdetails="";
								String simpletariffQty="";
					      
								if(voBillingDtl.getTariffDetails()!=null)
								{

									Map<String,String> mpbil=new HashMap<String,String>();
									Map<String,String> mpbilviewcode=new HashMap<String,String>();
									Map<String,String> mpbilreqdno=new HashMap<String,String>();
									Map<String,String> mpbilreqno=new HashMap<String,String>();

									for(int indexCounter=0;indexCounter<voBillingDtl.getTariffDetails().size();indexCounter++)
									{
										if(indexCounter==0)
										{
											simpletariffdetails=voBillingDtl.getTariffDetails().get(indexCounter).substring(5)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
											//simpletariffdetails=voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
											simpletariffQty=voBillingDtl.getTariffQty().get(indexCounter);
											mpbil.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getTariffQty().get(indexCounter));
											mpbilviewcode.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getViewcode().get(indexCounter));
											mpbilreqdno.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getReqdno().get(indexCounter));
											mpbilreqno.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getReqno().get(indexCounter));
										}
										else
										{
											simpletariffdetails+="^"+voBillingDtl.getTariffDetails().get(indexCounter).substring(5)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
											//simpletariffdetails+="^"+voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
											simpletariffQty+="^"+voBillingDtl.getTariffQty().get(indexCounter);
											mpbil.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getTariffQty().get(indexCounter));
											mpbilviewcode.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getViewcode().get(indexCounter));
											mpbilreqdno.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getReqdno().get(indexCounter));
											mpbilreqno.put(voBillingDtl.getTariffDetails().get(indexCounter).substring(5), voBillingDtl.getReqno().get(indexCounter));

										}
										
										
									}
									
									  Iterator itrAuto=mpbil.keySet().iterator();
                                      
									  String newqty="";
									  String testcode="";
									  while(itrAuto.hasNext())
									  {
										  String testxrayy=(String)itrAuto.next();
										  String county=(String)mpbil.get(testxrayy);
										  if(xraytestforwhichextrachargenotadd!=null && !xraytestforwhichextrachargenotadd.equals("") && xraytestforwhichextrachargenotadd.contains(testxrayy))
											{
											  
											}
										  else
										  {
											  
											  newqty+=county+"^";
											 testcode=invEssentialDAO.getextratestforbillingxray(_userVO);

										  }
									  }
									  int newtotalcountviews=0; 
									  if(!newqty.equals(""))
									  {
										  
									  String[] val=newqty.split("\\^");
										int xraycount=0;
										for(int l=0;l<val.length;l++)
										{
											String county=val[l];
											xraycount+=Integer.parseInt(county); //views totalcount
										}
										newtotalcountviews=xraycount;
										double xraycounter=xraycount; 
										double newxraycounter=xraycounter/4;
										int newremiandernew=(int)newxraycounter;  

										double remainder=xraycounter%4;; 
										int remiandernew=(int)remainder;  

										if(remiandernew!=0)
											newremiandernew+=1;
										
										  
										simpletariffQty=simpletariffQty+"^"+newremiandernew; //hardcode
										simpletariffdetails=simpletariffdetails+"^"+testcode+"#"+priorityAll; //hardcoded
									  }
									  
									  
									  
									  if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
									  {
										  
										 
											   
											   String[] testcodes=simpletariffdetails.split("\\^");
											   
											   String reqno="";
											   for(int k=0;k<testcodes.length;k++)
											   {
												   String testcodexray=testcodes[k].split("#")[0];
												   String viewsxray=simpletariffQty.split("\\^")[k];
												String viewcode="";
												String reqdno="";
												  
												if(mpbilviewcode!=null && mpbilviewcode.size()>0)
												   {
													   viewcode=mpbilviewcode.get(testcodexray);
													   
												   }
												   
												   if(mpbilreqdno!=null && mpbilreqdno.size()>0)
												   {
													   reqdno=mpbilreqdno.get(testcodexray);
													   
												   }
												   
												   if(mpbilreqno!=null && mpbilreqno.size()>0)
												   {
													   
													    if(reqno.equals(""))
													   reqno=mpbilreqno.get(testcodexray);
													   
												   }
												   
												   if(testcodexray.equalsIgnoreCase(testcode))
												   invBillingDAO.insertviewsdetail(testcodexray, viewcode,viewsxray,reqno , reqdno,_userVO,newtotalcountviews,"1") ;
												   else if (xraytestforwhichextrachargenotadd!=null && !xraytestforwhichextrachargenotadd.equals("") && xraytestforwhichextrachargenotadd.contains(testcodexray))
												  invBillingDAO.insertviewsdetail(testcodexray, viewcode,viewsxray,reqno , reqdno,_userVO,newtotalcountviews,"2") ; 
												   else
													   invBillingDAO.insertviewsdetail(testcodexray, viewcode,viewsxray,reqno , reqdno,_userVO,newtotalcountviews,"3") ;
											   
											   
										   }
										  
									  }
									  
									  
									
								}
								
								
								System.out.println("simpletariffdetails : "+simpletariffdetails);
								String grouptariffdetails="";
								String checkDuplicateDetail="";
								boolean checkGrp=false;
								String grouptariffQty="";
								if(voBillingDtl.getGrouptariffDetails()!=null)
								{
									for(int indexCounter=0;indexCounter<voBillingDtl.getGrouptariffDetails().size();indexCounter++)
									{
										if(indexCounter==0)
										{
											grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
											//grouptariffdetails=voBillingDtl.getGrouptariffDetails().get(indexCounter);
											grouptariffQty=voBillingDtl.getGrouptariffQty().get(indexCounter);
											checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
										}
										else
										{
											String[] SplitCheckDuplicateDetail=checkDuplicateDetail.split("&");
											if(SplitCheckDuplicateDetail.length>1)
												for(int y=1;y<SplitCheckDuplicateDetail.length;y++)
												{
													if(!SplitCheckDuplicateDetail[y].equals(voBillingDtl.getGrouptariffDetails().get(indexCounter)))
													{
														  checkGrp=true;
													}
													//else
													//{
														 //checkGrp=false;
													//}
											
												}
											
											if(checkGrp)
											{
											grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter)+"#"+priorityAll; // change by ashu for setting priority for extra charge.
											//grouptariffdetails+="^"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
											grouptariffQty+="^"+voBillingDtl.getGrouptariffQty().get(indexCounter);
											checkDuplicateDetail+="&"+voBillingDtl.getGrouptariffDetails().get(indexCounter);
										}
										}
									 
										
									}
								
								}
								
								System.out.println("grouptariffdetails -- : "+grouptariffdetails);
								
								
								
								
								
								if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
								{
									if(!voBillingDtl.getRaiseAdvise().equals("0"))
									{
										billno= invBillingDAO.makeBillingTestWisexray(voBillingDtl, patVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
									}
									}
								
								if(grouptariffdetails.equals("")==false)
								{
									if(!voBillingDtl.getRaiseAdvise().equals("0"))
									{
										billno=	invBillingDAO.makeBillingTestWisexray(voBillingDtl, patVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
									}
									}
					}
							System.out.println("patvo after address : "+patVO.getPatAddress());
					
					   RequistionHeaderVO voReqHeader=new RequistionHeaderVO();
					//   voReqHeader.setLabCode(labCode);
					 //  voReqHeader.setRequisitionNumber(requisitionNumber);
					   	voReqHeader.setPatCrNo(patVO.getPatCRNo()); 
					   voReqHeader.setEpisodeCode(patVO.getPatepisodecode());
					   voReqHeader.setPatAdmissionNo(patVO.getPatadmissionno());
					   voReqHeader.setBedCode(patVO.getBedCode());
					   voReqHeader.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
					   voReqHeader.setDeptName(patVO.getDepartment()==null?patVO.getAdmitteddepartmentname():patVO.getDepartment());
					   voReqHeader.setDeptUnitCode(patVO.getPatdeptunitcode());
					   voReqHeader.setDeptUnitName(patVO.getPatdeptunit());
					   voReqHeader.setGenderCode(patVO.getPatGenderCode());
					   voReqHeader.setIsActualDob(patVO.getIsActualDob());
					   voReqHeader.setIsConfidential(InvestigationConfig.YESNO_FLAG_NO);  //0
					   voReqHeader.setIsAutomatedRequest(InvestigationConfig.IS_AUTOMATED_REQUEST_ONLINE); //0
					   voReqHeader.setMlcNo(patVO.getPatmlcno());
					   voReqHeader.setMobileNo(patVO.getPatMobileNo());  //need to discuss
					   voReqHeader.setOrderedByDoctor(patVO.getConsultantName());
					    voReqHeader.setPatAddress(patVO.getPatAddress()); //need to discuss
					   voReqHeader.setPatDob(patVO.getPatDOB());
					   voReqHeader.setPatAge(patVO.getPatAge());
					   voReqHeader.setPatName(patVO.getPatFirstName()+" "+patVO.getPatMiddleName()+" "+patVO.getPatLastName());
					   voReqHeader.setReqHeaderStatus(InvestigationConfig.REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS); //1
					
					  
					  // voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
					   voReqHeader.setRequsitionRaisedThrough("5");  //7
					   
					   
					   voReqHeader.setRoomCode(patVO.getPatroomno());
					   voReqHeader.setWardCode(patVO.getPatwardcode());
					   voReqHeader.setReqType(""+requisitionTypeForBilling);
					   voReqHeader.setRoomName(patVO.getRoom()==null?patVO.getPatroomname():patVO.getRoom());
					   voReqHeader.setWardName(patVO.getPatwardname());
					   voReqHeader.setBedName(patVO.getBedname());
					   
					   voReqHeader.setVisitNo(patVO.getPatVisitNo());
					  
					   voReqHeader.setVisitDate(patVO.getPatvisitdate()==null?patVO.getAdmissionDate():patVO.getPatvisitdate());
					   
					   
					   
					   voReqHeader.setAdvisedByDocName(patVO.getAdvisedByDocName());
					    voReqHeader.setAdvisedByDocNo(patVO.getAdvisedByDocNo());
					    voReqHeader.setPatCatCode(patVO.getPatCategoryCode());
					    voReqHeader.setVisitReason(patVO.getVisitReason());
					    voReqHeader.setFollowup(patVO.getFollowup());
					if(datapidd!=null && !datapidd.equals(""))
					invEssentialDAO.insertPid(voReqHeader,_userVO,datapidd,datapidddata);	//procedure to insert into req_test_mand_dtl

					
					
					//Billing Save Logic End
					
				
					 
					
						
			//	return listReqId;	
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
					
					String msg="HIS DATA EXCEPTION";
					if(patVO.getIsamountshort()!=null && patVO.getIsamountshort().equals("1")){
						msg="Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!";
					
					}
					
					throw new HisDataAccessException(msg);
				
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
				return listReqId;
			}

			
			public Map<String,Map<String,List<String>>>  getBookMarkListraisingxray(UserVO userVO,String iscallingfromdesk) {


				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<BookMarkVO> lstBookMark=null;
				Map<String,Map<String,List<String>>> mpBookMark=new LinkedHashMap<String,Map<String,List<String>>>();

				try {

					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					lstBookMark=invEssentialDAO.getBookMarkListraisingxray(userVO, iscallingfromdesk);

					if(lstBookMark!=null&&lstBookMark.size()>0)
					{
						List<String> lstTest=null;
						//Logic for Setting Book Mark
						for(BookMarkVO vo:lstBookMark)
						{
							Map<String,List<String>> mpLab=mpBookMark.get(vo.getBookMarkCode()+"#"+vo.getBookMarkName());
							
							
							if(mpLab==null)
							{
								mpLab=new LinkedHashMap<String,List<String>>();
								lstTest=new ArrayList<String>();
								lstTest.add(vo.getTestCode());
								mpLab.put(vo.getLabCode(),lstTest);
								mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
							}
							else
							{
								lstTest=mpLab.get(vo.getLabCode());
								if(lstTest!=null&&lstTest.size()>0)
								{
									lstTest.add(vo.getTestCode());
									mpLab.put(vo.getLabCode(),lstTest);
									mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
								}
								else
								{
									lstTest=new ArrayList<String>();
									lstTest.add(vo.getTestCode());
									mpLab.put(vo.getLabCode(),lstTest);
									mpBookMark.put(vo.getBookMarkCode()+"#"+vo.getBookMarkName(), mpLab);
								}
							}

						}
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

				return mpBookMark;
			}


			public String issufficientbalancexray(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {

                 String val="";
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
				String flag="2";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.issufficientbalance(searchVO,_UserVO,patVO);
			        
			       String accno=flag.split("\\^")[0];
			       float creditlimit=Float.parseFloat(flag.split("\\^")[2]);
			       float amount=Float.parseFloat(flag.split("\\^")[1]);
                   float AllTrfCost=0;				
			       int creditflag=Integer.parseInt(flag.split("\\^")[3]);

			       if(accno!=null && !accno.equals("0"))
					   {
						   val=flag;
						   
					   }
			       else
			    	   flag="";
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}
			

			

public String getlastreqdate(String testcode,String labcode, String crno,UserVO _UserVO)
				{

				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<LabTestVO> lstLabTest=null;
				Map mp=new HashMap();
				InvestigationEssentialDAOxray objSampleAcceptanceDAO=new InvestigationEssentialDAOxray(tx);
				String flag="";
				try {
					SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
					tx.begin();

					InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
					//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

					
					flag=objSampleAcceptanceDAO.getlastreqdate(testcode,labcode,crno,_UserVO);				

			        
			      
					
					//invEssentialDAO.deleteReqDtl(searchVO, _UserVO);
		 
					//mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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

				return flag;
			}
			



public Map<String, LabTestVO> getPrvTestDtlAJAXMAPxray(String CrNo, UserVO userVO, String fromwhichcall) {
  JDBCTransactionContext tx = new JDBCTransactionContext();
  String labNames = "";
  String prvTestDetail = "";
  Map<String, LabTestVO> lstPrvLabTest = new HashMap<String, LabTestVO>();
  String testGroupNames = "";
  Map mp = new HashMap();

  
  try {
    tx.begin();
    
    InvestigationEssentialDAOxray invEssentialDAO = new InvestigationEssentialDAOxray(tx);
    
    lstPrvLabTest = invEssentialDAO.getPrvTestDtlUsingAJAXxray(CrNo, userVO, fromwhichcall);






  
  }
  catch (HisApplicationExecutionException e) {
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
  
  return lstPrvLabTest;
}





public Map deleteReqDtlxray(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  patVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstLabTest=null;
	Map mp=new HashMap();
	SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);
	
	try {
		SampleAcceptanceVO voSamAcc=new SampleAcceptanceVO();
		tx.begin();

		InvestigationEssentialDAOxray invEssentialDAO=new InvestigationEssentialDAOxray(tx);
		//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);
		
		
		InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
		
		String xraytestforwhichextrachargenotadd=invEssentialDAO.gettestcodesforwhichnotaddextracharge(_UserVO);
		List<LabTestVO> lstPrvLabTest=null;

		  lstPrvLabTest=invEssentialDAO.getPrvTestDtlUsingAJAXxrayreqwise(searchVO.getRequisitionNo(), _UserVO);
		     String finaltestchargeqty="";
		     String finaltestcode="";
		     
		  for(int k=0;k<lstPrvLabTest.size();k++)
		  {
			  LabTestVO vo=lstPrvLabTest.get(k);

			  
				 String chargeqty=vo.getTotalviewcount();
				 String testcode=vo.getTestCode();

				 
				 if(finaltestchargeqty.equals(""))
				 finaltestchargeqty=chargeqty;
				 else
					 finaltestchargeqty+="^"+chargeqty;
				
				 if(finaltestcode.equals(""))
                     finaltestcode=testcode;
				 else
					 finaltestcode+="^"+testcode;
			  
			  
		  }
		  
            /*int xtrachargewqty=0;
            String extrachargetestcode="";
		  for(int k=0;k<lstPrvLabTest.size();k++)
		  {
			  LabTestVO vo=lstPrvLabTest.get(k);
			  
			  if(vo.getTotalreqviewtyp()!=null && vo.getTotalreqviewtyp().equals("1"))
			  {
				  xtrachargewqty=Integer.parseInt(vo.getTotalviewcount());
				  extrachargetestcode=vo.getTestCode();
			  }
			  
		  }
		  
		  boolean isextrachrgetest=false;
int totalcountextracharge=0;
		if((searchVO.getTotalreqviewtyp()).equals("3"))
		{
			
			int totalreqviewcount=Integer.parseInt(searchVO.getTotalreqviewcount());
			int viewtestcount=Integer.parseInt(searchVO.getTotalviewcount());
			
			int remainingviewcount=totalreqviewcount-viewtestcount;
			

			double xraycounter=remainingviewcount; 
			double newxraycounter=xraycounter/4;
			int newremiandernew=(int)newxraycounter;
			
			double remainder=xraycounter%4;; 
			int remiandernew=(int)remainder; 
			
			if(remiandernew!=0)
				newremiandernew+=1;
			
			
			
				xtrachargewqty=xtrachargewqty-newremiandernew;
		
				if(xtrachargewqty<=0)
				{
					xtrachargewqty=1;
					isextrachrgetest=true;
				}
				else
					isextrachrgetest=true;
		}*/
		
		
		
		/* Inv_RequisitionRaisingPatientVO patVO=null;*/
      	 
		// String crno=patVO.getPatCRNo();
        /*patVO=objSampleAcceptanceDAO.getInvRaisingPatDetails(crno,_UserVO);*/
		
		Inv_RequisitionRaisingPatientVO patVOnew=null;
         
		//String crno=patVO.getPatCRNo();
		
		patVOnew=invEssentialDAO.getInvRaisingPatDetailsnew(null,_UserVO,searchVO.getRequisitionNo());
		
		
             String  requisitionTypeForBilling="";
		
             String reqNo=searchVO.getRequisitionNo()+"!";
             voSamAcc.setRequisitionNo(reqNo);
		
	/*	  if(patVOnew.getPatStatusCode()!=null && patVOnew.getPatStatusCode().equals("2"))
			{						  
				requisitionTypeForBilling="2";
			}
			else 
			{
				//visit type code 1-opd, 2,3-emergency, 4 special
				if(patVOnew.getPatvisittypecode()==null)
					requisitionTypeForBilling="1";
				else{
					
				
				if(patVOnew.getPatvisittypecode().equals("1"))
					requisitionTypeForBilling="1";
				if(patVOnew.getPatvisittypecode().equals("4"))
					requisitionTypeForBilling="4";
				if(patVOnew.getPatvisittypecode().equals("2") ||patVOnew.getPatvisittypecode().equals("3") )
					requisitionTypeForBilling="3";
				}
			}*/
		  
		  
		  
		  if(voBillingDtl.getTariffDetails()==null)
			{
				voBillingDtl.setTariffDetails(new ArrayList<String>());
				voBillingDtl.setTariffQty(new ArrayList<String>());
			}
			
			voBillingDtl.getTariffDetails().add(searchVO.getDelLabCode()+searchVO.getDelTestCode());
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
			
			if(searchVO.getGroupcode()!=null && !searchVO.getGroupcode().equals("0"))
			{
				 makeBillingTestWise="4";//if group
				 simpletariffdetails=searchVO.getDelLabCode()+searchVO.getGroupcode();
			}
			else
			{
			 makeBillingTestWise="1";//if test
			 
			}
			
			}	
		
		voSamAcc.setIsbilledornot(searchVO.getIsbilledornot());
		
		
		if(true)
		{
			   
			simpletariffdetails=finaltestcode;
			simpletariffQty=finaltestchargeqty;
		}
		

		
		
		objSampleAcceptanceDAO.makeRefund(voSamAcc,_UserVO,simpletariffdetails,simpletariffQty,patVO, patVOnew.getPatvisittypecode(),makeBillingTestWise);
        
        
		invEssentialDAO.deleteReqDtl(searchVO, _UserVO);

		mp.put(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS,lstLabTest);


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


}//end class

