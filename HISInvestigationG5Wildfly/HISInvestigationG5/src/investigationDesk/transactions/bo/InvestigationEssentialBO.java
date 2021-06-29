package investigationDesk.transactions.bo;

//last updated on july 06-07-06>>>priya

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
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

import investigationDesk.InvestigationConfig;
/*import investigationDesk.transactions.dao.InvDuplicateResultReportPrintingDAO;
import investigationDesk.transactions.dao.InvResultEntryDAO;
 
import investigationDesk.transactions.dao.InvResultReportPrintingDAO;
 
import investigationDesk.transactions.dao.InvResultReValidationDAO;
 
import investigationDesk.transactions.dao.InvResultValidationDAO;*/
import investigationDesk.transactions.dao.InvestigationBillingDAO;
import investigationDesk.transactions.dao.InvestigationEssentialDAO;
import investigationDesk.transactions.dao.viewInvestigationDAO;

/*import investigationDesk.transactions.dao.OnlinePatientAcceptanceDAO;
import investigationDesk.transactions.dao.OnlinePatientAcceptanceDAOi;*/
import investigationDesk.transactions.dao.PackingListGenerationDAO;
/*import investigationDesk.transactions.dao.SampleAcceptanceDAO;
import investigationDesk.transactions.dao.SampleAcceptanceDAOi;
import investigationDesk.transactions.dao.SampleCollectionCumAcceptanceDAO;*/
import investigationDesk.transactions.dao.SampleCollectionDAO;
import investigationDesk.vo.BookMarkVO;
/*import investigationDesk.vo.InvDuplicateResultReportPrintingVO;
import investigationDesk.vo.InvResultReportPrintingVO;
import investigationDesk.vo.InvResultValidationVO;*/
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationRequisitionBillDtlVO;
import investigationDesk.vo.InvestigationRequistionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;
import investigationDesk.vo.SampleAcceptanceVO;
import investigationDesk.vo.viewInvestigationVO;
//import investigationDesk.vo.OnlinePatientAcceptanceVO;
import investigationDesk.vo.RequistionHeaderVO;
//import investigationDesk.vo.SampleAcceptanceVO;
//import investigationDesk.vo.SampleCollectionCumAcceptanceVO;
//import investigationDesk.vo.template.ResultEntryVO;
//import mrd.transaction.dao.*;
//import mrd.transaction.delegate.*;
//import mrd.MrdConfig;
//import investigationDesk.vo.template.TriStringObject;




import org.apache.commons.lang.StringUtils;

import com.ibm.icu.util.Calendar;

/**
 * @author vinita1fajax
 * 
 */
public class InvestigationEssentialBO implements InvestigationEssentialBOi {
	
	

	/* Function to get the Patient Registration Details */
	public Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(
			Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO) {

		Inv_RequisitionRaisingPatientVO voPatient=new Inv_RequisitionRaisingPatientVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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
		List<LabTestVO> lstLabTestForTestGruop=null;
		//List<LabTestVO> lstPreviousLabTest=null;
		List lstUOMCombo=null;
		List lstContainerCombo=null;

		Map mp=new HashMap();


		List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
		List<String> testGroupCode=new ArrayList<String>();
		
		
		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			lstLabTest=invEssentialDAO.searchLabWiseTestDtls(searchVO, _UserVO);
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			//String sampleComboStr="<option value='-1'>Select Value</option>";
			String defaultUOM="";
			String defaultContainer="";
			String defaultQuantity="";
			String sampleValues="";
		
			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo
			

			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				
				defaultContainer="";
				defaultUOM="";
				defaultQuantity="";
				String strSAmpleCode = vo.getDefaultSampleCode();
				String sampleComboStr="<option value='-1'>Select Value</option>";
				String uomComboStr="<option value='-1'>Select Value</option>";
				String containerComboStr="<option value='-1'>Select Value</option>";
				
				List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						sampleValues+=entry.getValue()+"*";
						String[] codes=entry.getValue().split("&");
						String sampCode=codes[0];
						
						if(lstSampleCombo.size()==1)
						{
							
							sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
							
							vo.setSingleSample(sampCode);
							defaultContainer=codes[2];
							defaultUOM=codes[1];
							defaultQuantity=codes[3];
						}
						else
						{
						
						if(sampCode.equalsIgnoreCase(strSAmpleCode))
							{sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
							defaultContainer=codes[2];
							defaultUOM=codes[1];
							defaultQuantity=codes[3];
							}
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampCode+"'>"+entry.getLabel().split("&")[0]+"</option>";
						}
						}
				}

				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);
				
				//uom combo logic
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
				
				vo.setDefaultContainerVol(defaultQuantity);

				//container combo logic
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
			/*	String uomComboStr="<option value='-1'>Select Value</option>"
				lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);
				lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);
				LabTestVO voGetSample=new LabTestVO();
				
				if(strSAmpleCode!=null && strSAmpleCode!="")
				invEssentialDAO.getSampleRelatedValues(vo,_UserVO);
						*/
			
				

				
				
				//Mandatory Combo/text Logic
				String ismandInfo=vo.getIsMandatoryReq();
				String mandInfo=vo.getMandInfo();
				
                  
				if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
					
				{;
					/*String[] mandInfoCommaSeparator=mandInfo.split(",");
					
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

					 
					}*/
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
		List<LabTestVO> lstLabTestForTestGruop=new ArrayList<LabTestVO>();
		//List<LabTestVO> lstPreviousLabTest=null;
		List lstUOMCombo=null;
		List lstContainerCombo=null;
		
		List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
		List<String> testGroupCode=new ArrayList<String>();
		
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			lstLabTest=invEssentialDAO.searchLaboratoryWiseTestGroupOnClick(searchVO, _UserVO);
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			//String sampleComboStr="<option value='-1'>Select Value</option>";
			String defaultUOM="";
			String defaultContainer="";
			String defaultQuantity="";
			String sampleValues="";
		
			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);			
			//String sampleComboStr="<option value='-1'>Select Value</option>";

			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
			
				defaultContainer="";
				defaultUOM="";
				defaultQuantity="";
				String strSAmpleCode = vo.getDefaultSampleCode();
				String sampleComboStr="<option value='-1'>Select Value</option>";
				String uomComboStr="<option value='-1'>Select Value</option>";
				String containerComboStr="<option value='-1'>Select Value</option>";
				List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						sampleValues+=entry.getValue()+"*";
						String[] codes=entry.getValue().split("&");
						String sampCode=codes[0];
						
						
						if(lstSampleCombo.size()==1)
						{
							
							sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
							defaultContainer=codes[2];
							defaultUOM=codes[1];
							defaultQuantity=codes[3];
							vo.setSingleSample(sampCode);

						}
						else
						{
						
						if(sampCode.equalsIgnoreCase(strSAmpleCode))
							{sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
							defaultContainer=codes[2];
							defaultUOM=codes[1];
							defaultQuantity=codes[3];
							}
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampCode+"'>"+entry.getLabel().split("&")[0]+"</option>";
						}}
				}

				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);

				//uom combo logic
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
				
				vo.setDefaultContainerVol(defaultQuantity);

				//uom combo logic
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




	public  List saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listReqId=new ArrayList();
		String labName="";
		String testName="";
		try
		{    
			tx.begin();
			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			
			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);
			
			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();
			
			
			//First iterate the Map over Lab codes for generating requisition no's
			
			//String[] labCodeArray=(String[])mp.keySet().toArray();
			
			//int size=labCodeArray.length;
			
			int requisitionTypeForBilling=0;
			
			Iterator itrLab=mp.keySet().iterator();
			
			while(itrLab.hasNext())//for(int i=0;i<size;i++)
			{
				testName="";
				
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
				 
				/*   
				 /*  if(patVO.getPatStatus().equals("IPD"))
				   {
					   voReqHeader.setReqType(InvestigationConfig.REQUISITION_TYPE_IPD);
					   requisitionTypeForBilling=2;
				   }
				   else if(patVO.getPatStatus().equals("OPD"))
				   {
					   voReqHeader.setReqType(InvestigationConfig.REQUISITION_TYPE_OPD);
					   requisitionTypeForBilling=1;
				   }
				   else
				   {
					   voReqHeader.setReqType(InvestigationConfig.REQUISITION_TYPE_CASUALITY);
					   requisitionTypeForBilling=1;
				   }*/
					   
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
					   
					  
/*	  if(patVO.getPatStatusCode().equals("2"))
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
						
					}*/
	  
	  
				   voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7

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
						testCounter++;
						//Logic for generating running sequence 'XX'
						if(testCounter/10<1)
							runningSequence="0"+Integer.toString(testCounter); //Like 01,02,03 etc..
						else
							runningSequence=Integer.toString(testCounter);
						
						//requisitionDNo generation:: format requisitionNumberXX
						
						String requisitionDNo=requisitionNumber+runningSequence;
						
					   //Setting Requisition HeaderVO Values
						LabTestVO voLabTest=mpTest.get(testCode);
						InvestigationRequistionVO voReq=new InvestigationRequistionVO();
						voReq.setStrLabCode(voLabTest.getLabCode());
						voReq.setStrTestCode(voLabTest.getTestCode());
						voReq.setStrSampleCode(voLabTest.getSampleCode());
						voReq.setStrRequsitionDNo(requisitionDNo);
						voReq.setStrReqNo(requisitionNumber);
						voReq.setStrWorkOrderSequence(runningSequence);
						voReq.setStrPriority((voLabTest.getPriority()==null?InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL:voLabTest.getPriority()));
						
						if(voLabTest.getTestType().equals("P"))
							voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_PATIENT_BASED);
						else
							voReq.setStrRequisitionDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_SAMPLE_BASED);
						
						voReq.setStrTypeOfComponent("1"); //need to configure
						
						voReq.setStrTestGroupCode((voLabTest.getTestGroupCode().equals("0")?null:voLabTest.getTestGroupCode()));
						
						voReq.setStrTestGroupType((voLabTest.getTestGroupType().equals("0")?null:voLabTest.getTestGroupType()));
					    
						
						
						//set the req dtl status to advised by doctor desk if test is advise configured										
						if(voLabTest.getRaiseAdvise().equals("0"))
							voReq.setStrRequisitionDtlStatus(InvestigationConfig.APPOINTMENT_ADVISED_DESK);
						
						
						
						
						
						voReq.setStrAppointmentDate(voLabTest.getAppointmentDate());
						voReq.setStrAppointmentTime(voLabTest.getAppointmentTime());
						//voReq.setFinalMandValues(voLabTest.getFinalMandValues());
						 voReq.setAdvisedBYDoctorName(voLabTest.getAdvisedByDoctorName());
						
						 voReq.setAppointmentRefNo(voLabTest.getAppointmentRefNo());
						String apoitmentDAte=voLabTest.getAppointmentDate();
				
						//calling procedure to add into req dtl table
						invEssentialDAO.insertRequisitionDtl(voReq,_userVO);
						
						//invEssentialDAO.updateRequisitionHeader(requisitionNumber,apoitmentDAte,_userVO);	//calling procedure to update req/app date
						
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
						
						
						
						//confirm Appoitment 
						if(!voLabTest.getAppoitmentNo().equals("0")&&voLabTest.getAppoitmentNo()!=null)
						{
							voReq.setAppointmentNo(voLabTest.getAppoitmentNo());
							invEssentialDAO.ConfirmAppointment(voReq, _userVO);
						}
						
						
						
						//no billing if the appointment based test is advised through desk
						
						if(!voLabTest.getRaiseAdvise().equals("0"))
						{
						
						
						//Billing Logic
						if(!voBillingDtl.getRequisitionNos().equals(requisitionNumber))
						{
						voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
						voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
						voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
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
							//end billing logic
							
						}
							
							//Adding Details for Display after Save
							labName=voLabTest.getLabName();
							if(!testName.equals(""))
							  testName=testName+","+voLabTest.getTestName();
							else
								testName=voLabTest.getTestName();
							
					} // Loop Over TestCodes
					
				//Add The Requisition Number in list
					listReqId.add(crNO+"#"+labName+"#"+testName);
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
							 invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
						}
						
						if(grouptariffdetails.equals("")==false)
						{
							invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
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

	
	//getreqStatusAJAX
		public String  getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO _UserVO) 
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			String testReqStatus="";

			try {

				tx.begin();

				InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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
		
		public boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO _UserVO) {

			JDBCTransactionContext tx = new JDBCTransactionContext();

			boolean isTempReqPresent=false;



			try {

				tx.begin();
				InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);

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
		
	
	

	/* Function to search Test Cod Wise Test Details */
	public Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);

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

	
	
	
	
	public Map deleteReqDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LabTestVO> lstLabTest=null;
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			//lstLabTest=invEssentialDAO.getTestsCodeWiseDtl(searchVO, _UserVO);
			
			
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

	
	
	
	
	public  List<Inv_EpisodeVO> getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_EpisodeVO> listEpisodeVO=new ArrayList<Inv_EpisodeVO>();
		try
		{    
			tx.begin();
			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);

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
			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);

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
	public Map<String,Map<String,List<String>>>  getBookMarkDetails(UserVO userVO,String deptUnitCode) {


		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<BookMarkVO> lstBookMark=null;
		Map<String,Map<String,List<String>>> mpBookMark=new LinkedHashMap<String,Map<String,List<String>>>();

		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			lstBookMark=invEssentialDAO.getBookMarkList(userVO);
			lstBookMark.addAll(invEssentialDAO.getBookMarkListDeptUnitWise(userVO,deptUnitCode));


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
		List lstUOMCombo=null;
		List lstContainerCombo=null;
		Map mp=new HashMap();
		
		try {

			tx.begin();

		
			
			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			
			
			String bookType=invEssentialDAO.getBookMarkType(searchVO.getBookMarkCode(),_UserVO);
					
					
					
			lstLabTest=invEssentialDAO.searchBookMark(searchVO, _UserVO,bookType);
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			//String sampleComboStr="<option value='-1'>Select Value</option>";
			String defaultUOM="";
			String defaultContainer="";
			String defaultQuantity="";
			String sampleValues="";
		
			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);	


if(lstLabTest!=null)
{
			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				defaultContainer="";
				defaultUOM="";
				defaultQuantity="";
				String strSAmpleCode = vo.getDefaultSampleCode();
				String sampleComboStr="<option value='-1'>Select Value</option>";
				String uomComboStr="<option value='-1'>Select Value</option>";
				String containerComboStr="<option value='-1'>Select Value</option>";
				List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
				if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
				{
					Iterator lstIterator=lstSampleCombo.iterator();
					while(lstIterator.hasNext())
					{
						Entry entry=(Entry)lstIterator.next();
						sampleValues+=entry.getValue()+"*";
						String[] codes=entry.getValue().split("&");
						String sampCode=codes[0];
						
						
						if(lstSampleCombo.size()==1)
						{
							
							sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
							defaultContainer=codes[2];
							defaultUOM=codes[1];
							defaultQuantity=codes[3];
							vo.setSingleSample(sampCode);

						}
						else
						{
						
						if(sampCode.equalsIgnoreCase(strSAmpleCode))
							{sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
							defaultContainer=codes[2];
							defaultUOM=codes[1];
							defaultQuantity=codes[3];
							}
						else
							sampleComboStr=sampleComboStr+"<option value='"+sampCode+"'>"+entry.getLabel().split("&")[0]+"</option>";
						}
						}
				}

				//Sample Combo Logic
				if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
					vo.setSampleComboStr("");
				else      // Sample and Slide Based
					vo.setSampleComboStr(sampleComboStr);

				//uom combo logic
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
				
				
				vo.setDefaultContainerVol(defaultQuantity);
				vo.setUomComboStr(uomComboStr);

				//uom combo logic
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
				
				
				vo.setSampleValues(sampleValues);
				vo.setContainerComboStr(containerComboStr);
			 
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
}
else
	;

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
		List lstUOMCombo=null;
		List lstContainerCombo=null;
		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			lstLabTest=invEssentialDAO.searchTestGroup(searchVO, _UserVO);
		
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			//String sampleComboStr="<option value='-1'>Select Value</option>";
			String defaultUOM="";
			String defaultContainer="";
			String defaultQuantity="";
			String sampleValues="";
		
			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);	
			
			//Logic for Getting Sample Combo
			for(LabTestVO vo:lstLabTest)
			{
				defaultContainer="";
				defaultUOM="";
				defaultQuantity="";
				String sampleComboStr="<option value='-1'>Select Value</option>";
				String uomComboStr="<option value='-1'>Select Value</option>";
				String containerComboStr="<option value='-1'>Select Value</option>";
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
				

				//uom combo logic
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
				
				vo.setDefaultContainerVol(defaultQuantity);

				//uom combo logic
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

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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



	//Sample Collection Process

	/* Function to get the Sample colleaction area */
	public List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _UserVO,String wardCode) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			lstinvSampleCollectionVO =objSampleCollectionDAO.getSampleCollectionArea(_UserVO,wardCode);



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



		try {

			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
			boolean isBilled=false;
			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);

			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);


			if(voSample.getPatStatus().equals("IPD"))
				{
				
				 reqType=InvestigationConfig.REQUISITION_TYPE_IPD; //1
				// isBilled=true;
				
				}
				else if(voSample.getPatStatus().equals("OPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_OPD; //2
			else
				reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; //3

			for(String str:reqList)
			{

				String reqNo=str.split("#")[1]; //CRNO#ReqNo#labCode#sampleCode#index
				String labCode=str.split("#")[2]; //CRNO#ReqNo#labCode#sampleCode#index
				String sampleCode=str.split("#")[3]; //CRNO#ReqNo#labCode#sampleCode#index

				
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
									lstBilledPatList.add(voSampleCollection);
								else
									lstUnBilledPatList.add(voSampleCollection);

								mpBilledPat.put(reqNo+"#"+labCode+"#"+sampleCode,lstBilledPatList); // Putting Map as per Astha Ma'm Suggestion
								mpUnBilledPat.put(reqNo+"#"+labCode+"#"+sampleCode,lstUnBilledPatList); // Putting Map as per Astha Ma'm Suggestion
					}
				}
			}


			//Putting Combos in map
			mp.put(InvestigationConfig.LIST_UOM_COMBO,lstUOMCombo);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO,lstContainerCombo);


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
		List<Inv_SampleCollectionVO> lstAutoSampleNOConfig=new ArrayList<Inv_SampleCollectionVO>();
		String collAreaBasedSampleNo="";
		try
		{    
			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);

			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);

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
									
									
									if(voSample.getTempSampleNo().equals("2"))
										if(collAreaBasedSampleNo.equals(""))
										{
										collAreaBasedSampleNo=voSample.getTemparorySampleNO();
									//	objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
										}
										else
											voSample.setTemparorySampleNO(collAreaBasedSampleNo);
											
									
									
									
									SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
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
									isSampleDtlInsert=true;
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
									isSampleDtlInsert=true;
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

							//Adding generated sampleNo dtls for displaying
							if(!listSamples.contains(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo())) 
								listSamples.add(voSample.getPatCRNo()+"#"+voSample.getSampleName()+"#"+voSample.getTempSampleNo());
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


			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);

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

		Map mp=new HashMap();
		
		/*uom and container for sample coll*/
	/*	List lstUOMCombo=null;
		List lstContainerCombo=null;*/



		

			
		

		try {

			tx.begin();

			
			/*uom and container for sample coll*/
		
			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			List lstLabNames=invEssentialDAO.getLabNames(userVO);										//Laboratories  
			List lstTestNames=invEssentialDAO.getTestNames(userVO);										//Test Names

			List lstTestGroupNames=invEssentialDAO.getTestGroupNames(userVO);							//Test Group Names
          
			List lstadvisedByNames=invEssentialDAO.getAdvisedByNames(userVO);							//Advised By Names
			
			List lstTestCodeWise=invEssentialDAO.getTestCodeNames(userVO);
				
			
			
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
					sb.append(",");
				}

				// last string, no separator
				if(lstadvisedByNames.size() > 0){
					sb.append("{ label: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getValue()+"\" }");
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

			mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);

			mp.put(InvestigationConfig.ARRAY_TESTNAMES, testNames);

			mp.put(InvestigationConfig.ARRAY_TESTGROUPNAMES, testGroupNames);
			
			
			mp.put(InvestigationConfig.ARRAY_ADVISEDBY_NAMES, advisedByNames);
			mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCodeWise);
			/*uom and container for sample coll*/
			/*mp.put(InvestigationConfig.LIST_UOM_COMBO,lstUOMCombo);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO,lstContainerCombo);*/

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

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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
/*
	public  List savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listReqdtlId=new ArrayList();

		try
		{    
			tx.begin();
			OnlinePatientAcceptanceDAO objOnlinePatientAcceptanceDAO=new OnlinePatientAcceptanceDAO(tx);

			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);

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
						String sequence_Hash_yymmdd=objOnlinePatientAcceptanceDAO.generatePatientNoSequence(labCode, _userVO);  // Returns sequence#yymmdd

						String sequence=sequence_Hash_yymmdd.split("#")[0];
						String yymmdd=sequence_Hash_yymmdd.split("#")[1];
						String patAccNo=labCode+""+yymmdd+""+sequence; 
						//Logic to check the sequence is '100001' 

						if(sequence.equals(InvestigationConfig.SAMPLE_NO_SEQUENCE_INVESTIGATION)) //100001
						{
							objOnlinePatientAcceptanceDAO.insertPatientNoSequenceInMaintainer(labCode,sequence,yymmdd,_userVO);
						}
						else
						{
							objOnlinePatientAcceptanceDAO.updatePatientNoSequenceInMaintainer(sequence, labCode, _userVO);
						}
						List<OnlinePatientAcceptanceVO> lstPatientCollectionVO=mpLabCode.get(labCode);
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
										c.add(Calendar.DATE,7);
										String finalEntryDate = sdf.format(c.getTime());
										
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
									int EntryDatecomparWithSysDAte = strDate.compareTo(finalEntryDate);		
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
                                      String runningLabNO=objOnlinePatientAcceptanceDAO.checkAutoGenFormateRunningLabNo(voPatient, _userVO);
									 
                                      voPatient.setRunningLabNo(runningLabNO);
									
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
											c.add(Calendar.DATE,7);
											String finalEntryDate = sdf.format(c.getTime());
											
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

										objOnlinePatientAcceptanceDAO.insertPatientDtlForPatient(patAccNo,voPatient,  _userVO);
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);

									}
									else
									{
										objOnlinePatientAcceptanceDAO.insertPatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);

										if(voPatient.getRejectionAction().equals("1"))
										{
											voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_CANCELLED); //9
											objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatient(voPatient,  _userVO);	 

										}	
										if(voPatient.getRejectionAction().equals("2"))
										{


											voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_RESCHEDULED); //10
											objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientRescheduled(voPatient,  _userVO);	 

										}	

									}
								
							}//if loop
							else
							{

								if(voPatient.getAccepted().equals("1"))
								{

									objOnlinePatientAcceptanceDAO.insertPatientDtlForPatient(patAccNo,voPatient,  _userVO);
									voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
									objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);

								}
								else
								{
									objOnlinePatientAcceptanceDAO.insertPatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);

									if(voPatient.getRejectionAction().equals("1"))
									{
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_CANCELLED); //9
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatient(voPatient,  _userVO);	 

									}	
									if(voPatient.getRejectionAction().equals("2"))
									{
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_RESCHEDULED); //10
										objOnlinePatientAcceptanceDAO.updateRequisitionDtlForPatientRescheduled(voPatient,  _userVO);	 

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

							listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());

						}

					}
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

*/	//START SAMPLE ACCEPTANCE PROCESS ADDED BY PATHAN BASHA
/*
	public Map  sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List sampleAcceptanceLabCombo=new ArrayList();

		try
		{
			tx.begin();
			SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			sampleAcceptanceLabCombo=sampleAcceptanceDaoi.sampleAcceptanceLabCombo(sampleAcceptanceVO,_UserVO);
			mp.put(InvestigationConfig.SAMPLE_ACCEPTANCE_LAB_COMBO, sampleAcceptanceLabCombo);


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

			lstOnlinePatientAcceptanceVO=onlinePatientDaoi.setPatientEssentials(onlinePatientvo, _UserVO);

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
	
	*/
	
	
	
	/*
	
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
	
	
	*/
	
	
	
	
	/*
	
	public Map patientDetails(OnlinePatientAcceptanceVO onlinePatientvo,List<String> reqList, UserVO _UserVO)
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

			onlinepatientacceptancevo=onlinePatientDao.checkAutoGenFormate(onlinePatientvo, _UserVO);

			int size=onlinepatientacceptancevo.size();
			
			if(size!=0)
			{
				StringBuilder sb = new StringBuilder();
				//all but last
				for(OnlinePatientAcceptanceVO voSampleVo:onlinepatientacceptancevo ) {
					sb.append(voSampleVo.getLabNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningLabNo()  );
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
	
	*/
	
	
	/*
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

*/
/*
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
			
			for(SampleAcceptanceVO voSamAcc:voSampleAcc)
			{
				//Logic For Auto Lab No Generation
			
				lstAutoLabNOConfig=objSampleAcceptanceDAO.getSampleAcceptanceAutoLabNOConfig(voSamAcc, _userVO);
				
				int lstofSize=lstAutoLabNOConfig.size();
				
				
				if(voSamAcc.getCheckLabConfigForAutoGen().equals("2")&&lstofSize!=0)
				{
                         
					
					
					for(SampleAcceptanceVO autoLabVo:lstAutoLabNOConfig)
					{
						if(sameSampleNO==voSamAcc.getSampleNo())
						{
							voSamAcc.setLabNoConfiguration(autoLabVo.getRunningLabNo());
						
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
						c.setTime(date); // Added a initDate date. into Calender
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
							c.add(Calendar.DATE,7);
							String finalEntryDate = sdf.format(c.getTime());
							
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
								c.add(Calendar.DATE,7);
								String finalEntryDate = sdf.format(c.getTime());
								
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
					}
				}
					objSampleAcceptanceDAO.updateSampleAccInRequisitionDtl(voSamAcc,_userVO);
					objSampleAcceptanceDAO.updateSampleAccInSampleDtl(voSamAcc,_userVO);
					sameSampleNO=voSamAcc.getSampleNo();
				}

				else
				{
					objSampleAcceptanceDAO.updateSampleAccInRequisitionDtl(voSamAcc,_userVO);
					objSampleAcceptanceDAO.updateSampleAccInSampleDtl(voSamAcc,_userVO);
				}
				if(voSamAcc.getRecieved().equals(InvestigationConfig.RECIEVED_STATUS))

				{
					if(voSamAcc.getAccepted().equals(InvestigationConfig.ACCPETED_STATUS))//1
					{

						listSampleAccepted.add("Packing List No= "+voSamAcc.getPackingListNO()+"        Lab Name="+voSamAcc.getLabName()+"      Lab NO="+voSamAcc.getLabNoConfiguration()+"			Test Name ="+voSamAcc.getTestName());

					}
					else
					{

						listSampleNotAccepted.add("Packing List No= "+voSamAcc.getPackingListNO()+"        Lab Name="+voSamAcc.getLabName()+"      Test Name ="+voSamAcc.getTestName());
	}

				}
				else
				{

					listSampleNotReceived.add("Packing List No= "+voSamAcc.getPackingListNO()+"       Lab Name="+voSamAcc.getLabName()+"      Test Name ="+voSamAcc.getTestName());

				}
			}
			
			//Put List in Map
			mp.put(InvestigationConfig.LIST_ACCPETED_STATUS, listSampleAccepted);
			mp.put(InvestigationConfig.LIST_NOT_ACCPETED_STATUS, listSampleNotAccepted);
			mp.put(InvestigationConfig.LIST_NOT_RECIEVED_STATUS, listSampleNotReceived);

			SampleAcceptanceDAOi sampleAcceptanceDaoi = new SampleAcceptanceDAO(tx);
			SampleAcceptanceVO vo=new SampleAcceptanceVO();
			//Logic For Update Status In Packing List Dtl Table
			lstSampleAcceptanceVO=sampleAcceptanceDaoi.getSampleAcceptanceDetailForCheckPackNo(vo, _userVO);
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
*/
//Start a Result Entry Process Added By Basha on 20-04-2015
/*		
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
	public Map setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();


		try
		{
			tx.begin();
			InvResultEntryDAO invresultentrydao = new InvResultEntryDAO(tx);

			lstInvResultEntryVO=invresultentrydao.setPatientResultEntryEssentials(invresultentryvo, _UserVO);

			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultEntryVO);
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
	*/
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
	
/*	public  Map saveResultEntryDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultEntryForParameterDtlVO,UserVO _userVO,HttpServletRequest _request)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List   listResultEntryDtl =new ArrayList();
		 

		boolean isComplete=true;
		List<ResultEntryVO> lstResultEntryVO=new ArrayList<ResultEntryVO>();
		 
		Map mp=new HashMap();

		try
		{    

			tx.begin();
			InvResultEntryDAO objResultEntrtyDAO=new InvResultEntryDAO(tx);
			
			for(ResultEntryVO voInvResultEntry:invResultentryVO)
			{
				
				if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
					voInvResultEntry.setReqDtlStatus("13");
				else				
				voInvResultEntry.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS);
				
				objResultEntrtyDAO.updateResultEntryInRequisitionDtl(voInvResultEntry,_userVO);
				if(voInvResultEntry.getReportAvailableAfter().equals(InvestigationConfig.AVAILABLE_AFTER_RESULT_ENTRY))
				{
				objResultEntrtyDAO.insertResultEntryDtlInJobWorkorderData(voInvResultEntry,_userVO);
				}		
				
			
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
	*/
	
	public Map  getTestComboAJAXMAP(String labCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String labNames="";
		String testNames="";
		String testGroupNames="";
		Map mp=new HashMap();

		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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
	
	//Start a Result Validation Process Added By Basha on 20-04-2015
	/*
		public Map  LabComboForResultValidation(ResultEntryVO invresultentryvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List labcombo=new ArrayList();

			try
			{
				tx.begin();
				InvResultValidationDAO onlinePatientDao = new InvResultValidationDAO(tx);
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
		
		
		
		public Map getResultEntryData(ResultEntryVO invresultentryvo)
		{

			List<TriStringObject> resultValidationDataList = null;
			//List<Entry> mandatoryInfoDtlList = null;
		//	String mandatoryInfoDtlListString = "";
		//	List<HexaStringClass> rangeFromToList = null;
		//	List<SiteVO> siteVOList = null;
			Map mp = null;
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try {
				tx.begin();
				//TestMandatoryDAO testMandatoryDAO = new TestMandatoryDAO(tx);
		//		mandatoryInfoDtlList = testMandatoryDAO
		//				.getMandatoryInfoDtlList(resultEntryVO);
				InvResultValidationDAO resultEntryDetailDAO = new InvResultValidationDAO(
						tx);
				
				resultValidationDataList = resultEntryDetailDAO
						.getResultValidfationDataList(invresultentryvo);
				
				for (int i = 0; i < mandatoryInfoDtlList.size(); i++) {
					Entry entry = mandatoryInfoDtlList.get(i);
					if (i == 0)
						mandatoryInfoDtlListString = "" + entry.getLabel() + ",";
					else
						mandatoryInfoDtlListString += "" + entry.getLabel() + ",";

				}

				rangeFromToList = new InvestigationEssentialDAO(tx)
						.getRangeFromToList(
								resultEntryVO.getTestCode(),
								mandatoryInfoDtlListString,
								resultEntryVO.getPatAge(),
								resultEntryVO.getPatGender(),
								((resultEntryVO.getSampleNo() == null || resultEntryVO
										.getSampleNo().equalsIgnoreCase("")) ? "null"
										: resultEntryVO.getSampleNo().substring(5,
												8)), resultEntryVO
										.getLaboratoryCode());
				mp = new HashMap(4);

				System.out.println("resultEntryVO.getTestDocument()"
						+ invresultentryvo.getTestDocument());
				if (invresultentryvo.getTestDocument() != null) {
					// createXMLforCheck("c:/investigationDetails/A.xml",resultEntryVO.getTestDocument());
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

		
		public Map setPatientResultValidationEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List<ResultEntryVO> lstInvResultValidationVO=new ArrayList<ResultEntryVO>();


			try
			{
				tx.begin();
				InvResultValidationDAO invresultentrydao = new InvResultValidationDAO(tx);

				lstInvResultValidationVO=invresultentrydao.setPatientResultValidationEssentials(invresultentryvo, _UserVO);
				
				//////////////////////////////logic to fetch combo value name of the tests//////////////////////////
				
				for(int i=0;i<lstInvResultValidationVO.size();i++)
				{
					String concatString=lstInvResultValidationVO.get(i).getTestParameterName();
					String testCode=lstInvResultValidationVO.get(i).getTestCode();
					
					String properParaValues="";
					
					String[] parameters=concatString.split(",");
					int paraSize=parameters.length;
					
					for(int iterate=0;iterate<paraSize;iterate++)
					{
						String[] paraValues=parameters[iterate].split("#");
						String paraCode=paraValues[0];
						String paraName=paraValues[1];
						String paraEntry=paraValues[2];
						
						String comboValue=invresultentrydao.setComboValueName(testCode,paraCode,paraEntry);
						
						if(comboValue!=null)
							properParaValues+=paraCode+"#"+paraName+"#"+comboValue+",";
						else
							properParaValues+=parameters[iterate]+",";
						
						
						
					}
					
			
					properParaValues=properParaValues.substring(0, properParaValues.length()-1);
					lstInvResultValidationVO.get(i).setTestParameterName(properParaValues);
				}
				
				////////////////////////////logic to fetch combo value name of the tests ENDS//////////////////////////
				
				
				
				
				
				
				
				
				
				
				mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationVO);
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
	*/	
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
/*		
		public  Map saveResultValidationDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request)
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
						
					if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
							voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
					else					
					voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
					
					
					objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
						
					if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
					{
					objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
					}		
					
				
				
				
				}
				
				for(ResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
				{
					objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
					
					//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
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
						 
					voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
					objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
					
					
					if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				else					
				voInvResultValidation.setReqDtlStatus(InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS);
				
				
				objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
					
				if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
				{
				objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
				}		
					
				}
		
				
				for(InvResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
				{
					objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
					
					//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
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

		//	return mp;
			
		}
	*/
	
	
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

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
			//List lstLabNames=invEssentialDAO.getLabNames(userVO);
			  lstPrvLabTest=invEssentialDAO.getPrvTestDtlUsingAJAX(CrNo, userVO);
		 
			  //LabTestVO LabTestVO=new LabTestVO();
			  
			 
			  
			  for(LabTestVO vo:lstPrvLabTest)
			  {
				  prvTestDetail+=vo.getStatus()+"#"+vo.getLabName()+"#"+vo.getSampleName()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+vo.getBookMarkCode()+"#"+vo.getOfflineAppoitMentDate()+'#'+vo.getReqDate()+'#'+vo.getLabCode()+'#'+vo.getTestCode()+'#'+vo.getPrvReqStatus()+"@";  
					   
				  	 
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

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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
	
 /*

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
					sb.append(voSampleVo.getSampleNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningSampleNo()+"#"+voSampleVo.getPatType()  );
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
*/
public String getAccountNo(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
 


		JDBCTransactionContext tx = new JDBCTransactionContext();
		String accountNo="";
		try {

			tx.begin();

			InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
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
/*

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
		List<SampleCollectionCumAcceptanceVO> lstAutoSampleNOConfig=new ArrayList<SampleCollectionCumAcceptanceVO>();
		try
		{    
			tx.begin();
			SampleCollectionCumAcceptanceDAO objSampleCollectionDAO=new SampleCollectionCumAcceptanceDAO(tx);

			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);

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

						// Generate of SampleNo
						// Required HospitalCode,sampleNo,LabCode
						//Generate Sample No Sequence  for each lab
						String sequence_Hash_yymmdd=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);  // Returns sequence#yymmdd
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
						}
						String strSampleNo=objSampleCollectionDAO.generateSampleNoSequence(sampleCode, labCode, _userVO);
						
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
										c.add(Calendar.DATE,7);
										String finalEntryDate = sdf.format(c.getTime());
										
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
											c.add(Calendar.DATE,7);
											String finalEntryDate = sdf.format(c.getTime());
											
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
								 
									objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
									//}
								}
								 
								// Update Requisition Dtl  Table 
								//Setting Requisition Dtl Status as '3' for packing list generation
								
								voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY); //6
								voSample.setSampleNo(strSampleNo);
								voSample.setTempSampleNo(voSample.getTemparorySampleNO());
								objSampleCollectionDAO.updateRequisitionDtl(voSample, _userVO);


								// Insert in Sample Dtl Table
								if(!isSampleDtlInsert)
								{
									
									
									objSampleCollectionDAO.insertSampleDtl(voSample, _userVO);
									isSampleDtlInsert=true;
								}

							 

							else
							{
								// Update Requisition Dtl  Table 
								//Setting Requisition Dtl Status as '3' for packing list generation
								voSample.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY); //6
								voSample.setSampleNo(strSampleNo);
								objSampleCollectionDAO.updateRequisitionDtl(voSample, _userVO);


								// Insert in Sample Dtl Table
								if(!isSampleDtlInsert)
								{
									objSampleCollectionDAO.insertSampleDtl(voSample, _userVO);
									isSampleDtlInsert=true;
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

							//Adding generated sampleNo dtls for displaying
							if(!listSamples.contains(voSample.getPatCRNo()+"#"+voSample.getSampleName())) 
								listSamples.add(voSample.getPatCRNo()+"#"+voSample.getSampleName());
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

*/
	// revalidate process begins/////////////////////
	/*
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


		try
		{
			tx.begin();
			InvResultReValidationDAO invresultentrydao = new InvResultReValidationDAO(tx);

			lstInvResultValidationVO=invresultentrydao.setPatientResultValidationEssentials(invresultentryvo, _UserVO);
			
			//////////////////////////////logic to fetch combo value name of the tests//////////////////////////
			
			for(int i=0;i<lstInvResultValidationVO.size();i++)
			{
				String concatString=lstInvResultValidationVO.get(i).getTestParameterName();
				String testCode=lstInvResultValidationVO.get(i).getTestCode();
				
				String properParaValues="";
				
				String[] parameters=concatString.split(",");
				int paraSize=parameters.length;
				
				for(int iterate=0;iterate<paraSize;iterate++)
				{
					String[] paraValues=parameters[iterate].split("#");
					String paraCode=paraValues[0];
					String paraName=paraValues[1];
					String paraEntry=paraValues[2];
					
					String comboValue=invresultentrydao.setComboValueName(testCode,paraCode,paraEntry);
					
					if(comboValue!=null)
						properParaValues+=paraCode+"#"+paraName+"#"+comboValue+",";
					else
						properParaValues+=parameters[iterate]+",";
					
					
					
				}
				
		
				properParaValues=properParaValues.substring(0, properParaValues.length()-1);
				lstInvResultValidationVO.get(i).setTestParameterName(properParaValues);
			}
			
			////////////////////////////logic to fetch combo value name of the tests ENDS//////////////////////////
			
			

			mp.put(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO,lstInvResultValidationVO);
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
	*/
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
/*	
	public  Map saveResultReValidationDetails(List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request)
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
					
				if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
						voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				else					
				voInvResultValidation.setReqDtlStatus(InvestigationConfig.READY_RESULTPRINTING);
				
				
				objResultEntrtyDAO.updateResultValidationInRequisitionDtl(voInvResultValidation,_userVO);
					
				if(voInvResultValidation.getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION))
				{
				objResultEntrtyDAO.insertResultValidationDtlInJobWorkorderData(voInvResultValidation,_userVO);
				}		
				
			
			
			
			}
			
			for(ResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
				
				//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
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
				
			}
	
			
			for(InvResultEntryVO voInvResulValidationForParaMeterDtl:invResultValidationForParameterDtlVO)
			{
				objResultEntrtyDAO.insertResultValidationDtl(voInvResulValidationForParaMeterDtl,_userVO);
				
				//listResultValidationDtl.add("Test Name= "+voInvResultValidation.getTestName()+"Value=" +voInvResultValidation.getResultValidationValue());
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

	//	return mp;
		
	}
	*/
	
	/*
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
*/
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
	/*
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


			try
			{
				tx.begin();
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);

				lstInvResultReportPrintingVO=invresultreportprintingdao.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);

				mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
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
					
					invresultreportp=invresultreportprintingdao.getPdfName(invresultreportprintvo, _UserVO);

					
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


			try
			{
				tx.begin();
				InvResultReportPrintingDAO invresultreportprintingdao = new InvResultReportPrintingDAO(tx);

				lstInvResultReportPrintingVO=invresultreportprintingdao.setResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);

				mp.put(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
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
	*/
	
		//Start a Duplicate Result Reprot Printing Process Added By Basha on 08-06-2015
		/*
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


				try
				{
					tx.begin();
					InvDuplicateResultReportPrintingDAO invresultreportprintingdao = new InvDuplicateResultReportPrintingDAO(tx);

					lstInvResultReportPrintingVO=invresultreportprintingdao.setDuplicateResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);

					mp.put(InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
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


				try
				{
					tx.begin();
					InvDuplicateResultReportPrintingDAO invresultreportprintingdao = new InvDuplicateResultReportPrintingDAO(tx);

					lstInvResultReportPrintingVO=invresultreportprintingdao.setDuplicateResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);

					mp.put(InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO,lstInvResultReportPrintingVO);
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
			
			*/

public Map getReqData(String CrNo, UserVO userVO, String fromwhichcall) {
    JDBCTransactionContext tx = new JDBCTransactionContext();
    String labNames = "";
    String prvTestDetail = "";
    List<viewInvestigationVO> lstPrvLabTest = null;
    String testGroupNames = "";
    Map mp = new HashMap();

    
    try {
      tx.begin();
      
      viewInvestigationDAO invEssentialDAO = new viewInvestigationDAO(tx);
      
      lstPrvLabTest = invEssentialDAO.getPrvTestDtlUsingAJAX(CrNo, userVO, fromwhichcall);




      
      mp.put("getReqData", lstPrvLabTest);
    
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
    
    return mp;
  }

public List<Inv_SampleCollectionVO> getSampleCollectionAreaWard(UserVO _UserVO) 
{

	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
	lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

	try {

		tx.begin();
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		lstinvSampleCollectionVO =objSampleCollectionDAO.getSampleCollectionAreaWard(_UserVO);



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
	try
	{    
		tx.begin();
		InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);
		
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
			   
			   voReqHeader.setRequsitionRaisedThrough(InvestigationConfig.INVESTIGATION_RAISED_THROUGH_LAB);  //7
			   
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
					
					voReq.setStrAppointmentDate(voLabTest.getAppointmentDate());
					voReq.setStrAppointmentTime(voLabTest.getAppointmentTime());
					voReq.setAdvisedBYDoctorName(voLabTest.getAdvisedByDoctorName());
					voReq.setAppointmentRefNo(voLabTest.getAppointmentRefNo());
					String apoitmentDAte=voLabTest.getAppointmentDate();
					
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
							voLabTest.setConfigSeq(voSampleVo.getConfigSeq());
							voLabTest.setConfigLab(voSampleVo.getConfigLab());
							voLabTest.setConfigTest(voSampleVo.getConfigTest());
							voLabTest.setConfigType(voSampleVo.getConfigType());
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
							
							if(voLabTest.getTempSampleNo().equals("2"))
								if(collAreaBasedSampleNo.equals(""))
								{
								collAreaBasedSampleNo=voLabTest.getTemparorySampleNO();
							//	objSampleCollectionDAO.updateSampleCollInhivtsamplenoconfmst1(voSample,finalEntryDate,_userVO);
								}
								else
									voLabTest.setTemparorySampleNO(collAreaBasedSampleNo);
									
							
							
							
							SampleCollectionDAO objSampleCollectionD=new SampleCollectionDAO(tx);
							objSampleCollectionD.updateSampleCollInhivtsamplenoconfmst1RaisingCumCollection(voLabTest,finalEntryDate,_userVO);
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
					if(!voBillingDtl.getRequisitionNos().equals(requisitionNumber))
					{
					voBillingDtl.setRequisitionNos(voBillingDtl.getRequisitionNos()+requisitionNumber+'!');
					voBillingDtl.setRequisitionType(""+requisitionTypeForBilling);
					voBillingDtl.setDeptCode(patVO.getDepartmentcode()==null?patVO.getAdmitteddepartmentcode():patVO.getDepartmentcode());
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
						if(!testName.equals(""))
						  testName=testName+","+voLabTest.getTestName();
						else
							testName=voLabTest.getTestName();
					
						tempSampleNo=voLabTest.getTempSampleNo();
				}
				} // Loop Over TestCodes
				
			//Add The Requisition Number in list
				listReqId.add(crNO+"#"+labName+"#"+tempSampleNo);
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
						 invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, simpletariffdetails, simpletariffQty,"1", _userVO);//procedure
					}
					
					if(grouptariffdetails.equals("")==false)
					{
						invBillingDAO.makeBillingTestWise(voBillingDtl, patVO, grouptariffdetails, grouptariffQty,"4", _userVO);//procedure
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
		InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);
		
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
					
						lstTestBased =objSampleCollectionDAO.getBilledPatListForRaisingCumCollection(voLabTest,reqType,_userVO);
						if(lstTestBased!=null)
						{
							for(Inv_SampleCollectionVO voSampleCollection:lstTestBased)
							{
								String sample_Code=voSampleCollection.getSampleCode()==null?sampleCode:voSampleCollection.getSampleCode();
								//String reqdno=voSampleCollection.getRequisitionDNo();

								
										
											String billNo=voSampleCollection.getBillDetail().replace("^", "#").split("#")[0];
											if(!billNo.equals("0")&& billNo!=null)
											{
												
												voSampleCollection.setBillNo(billNo);
												voLabTest.setBillNo(billNo);
											}
											
										
										
							}
						}
					
						
						
					//////////////////////////////////////billing logic ends///////////////////////////////////////////////////////////////
					
					invEssentialDAO.insertRaisingCumCollectionDetails(voLabTest,_userVO);					//calling procedure to add into req dtl table
					
					
					//insertion in sample details table 
					if(!isSampleDtlInsert)
					{
						objSampleCollectionDAO.insertSampleDtlRaisingCumCollection(voLabTest, _userVO);
						isSampleDtlInsert=true;
					}
					
					
					String tariffId="";
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
					voBillingDtl.setServiceId(serviceId);

					invBillingDAO.updateBillingQty(voBillingDtl, tariffId, serviceId, _userVO);
						
				}
				} // Loop Over TestCodes
				
			
				
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
	
	return listReqId;	
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



/* Function to search Laboatory wise Test Details */
public Map searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstLabTest=null;
	List<LabTestVO> lstLabTestForTestGruop=null;
	//List<LabTestVO> lstPreviousLabTest=null;
	List lstUOMCombo=null;
	List lstContainerCombo=null;
	
	List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
	List<String> testGroupCode=new ArrayList<String>();
	

	Map mp=new HashMap();

	try {

		tx.begin();

		InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
		lstLabTest=invEssentialDAO.searchLabWiseTestDtlsRaisingCumCollection(searchVO, _UserVO);
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		//String sampleComboStr="<option value='-1'>Select Value</option>";
		String defaultUOM="";
		String defaultContainer="";
		String defaultQuantity="";
		String sampleValues="";
	
		lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
		lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);							//container combo
		

		//Logic for Getting Sample Combo
		for(LabTestVO vo:lstLabTest)
		{
			
			defaultContainer="";
			defaultUOM="";
			defaultQuantity="";
			String strSAmpleCode = vo.getDefaultSampleCode();
			String sampleComboStr="<option value='-1'>Select Value</option>";
			String uomComboStr="<option value='-1'>Select Value</option>";
			String containerComboStr="<option value='-1'>Select Value</option>";
			
			List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
			if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
			{
				Iterator lstIterator=lstSampleCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					sampleValues+=entry.getValue()+"*";
					String[] codes=entry.getValue().split("&");
					String sampCode=codes[0];
					
					if(lstSampleCombo.size()==1)
					{
						
						sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
						
						vo.setSingleSample(sampCode);
						defaultContainer=codes[2];
						defaultUOM=codes[1];
						defaultQuantity=codes[3];
					}
					else
					{
					
					if(sampCode.equalsIgnoreCase(strSAmpleCode))
						{sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
						defaultContainer=codes[2];
						defaultUOM=codes[1];
						defaultQuantity=codes[3];
						}
					else
						sampleComboStr=sampleComboStr+"<option value='"+sampCode+"'>"+entry.getLabel().split("&")[0]+"</option>";
					}
					}
			}

			//Sample Combo Logic
			if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
				vo.setSampleComboStr("");
			else      // Sample and Slide Based
				vo.setSampleComboStr(sampleComboStr);
			
			//uom combo logic
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
			
			vo.setDefaultContainerVol(defaultQuantity);

			//container combo logic
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
		/*	String uomComboStr="<option value='-1'>Select Value</option>"
			lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);
			lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);
			LabTestVO voGetSample=new LabTestVO();
			
			if(strSAmpleCode!=null && strSAmpleCode!="")
			invEssentialDAO.getSampleRelatedValues(vo,_UserVO);
					*/
		
			

			
			
			//Mandatory Combo/text Logic
			String ismandInfo=vo.getIsMandatoryReq();
			String mandInfo=vo.getMandInfo();
			
              
			if(ismandInfo.equals(InvestigationConfig.IS_MANDATORY_INFO))
				
			{;
				/*String[] mandInfoCommaSeparator=mandInfo.split(",");
				
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

				 
				}*/
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



public Map  getRequisitionRaisingEssentialsCumColl(UserVO userVO) 
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	String labNames="";
	String testNames="";
	String testGroupNames="";
	String advisedByNames="";
	String testCodeWise="";

	Map mp=new HashMap();
	
	/*uom and container for sample coll*/
/*	List lstUOMCombo=null;
	List lstContainerCombo=null;*/



	

		
	

	try {

		tx.begin();

		
		/*uom and container for sample coll*/
	
		InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
		List lstLabNames=invEssentialDAO.getLabNames(userVO);										//Laboratories  
		List lstTestNames=invEssentialDAO.getTestNamesCumColl(userVO);										//Test Names

		List lstTestGroupNames=invEssentialDAO.getTestGroupNames(userVO);							//Test Group Names
      
		List lstadvisedByNames=invEssentialDAO.getAdvisedByNames(userVO);							//Advised By Names
		
		List lstTestCodeWise=invEssentialDAO.getTestCodeNames(userVO);
			
		
		
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
			StringBuilder sb = new StringBuilder();

			// all but last
			for(int i = 0; i < lstTestNames.size() - 1 ; i++) {
				
				if(((Entry)lstTestNames.get(i)).getLabel()!=null)
				{
				sb.append("{ label: \""+((Entry)lstTestNames.get(i)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(i)).getValue()+"\" }");
				sb.append(",");
				}
			}

			// last string, no separator
			if(lstTestNames.size() > 0){
				
				if(((Entry)lstTestNames.get(lstTestNames.size()-1)).getLabel()!=null)
				{
				sb.append("{ label: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstTestNames.get(lstTestNames.size()-1)).getValue()+"\" }");
			}}

			testNames="["+sb.toString()+"]";
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
				sb.append(",");
			}

			// last string, no separator
			if(lstadvisedByNames.size() > 0){
				sb.append("{ label: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getLabel()+"\" ,  value: \""+((Entry)lstadvisedByNames.get(lstadvisedByNames.size()-1)).getValue()+"\" }");
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

		mp.put(InvestigationConfig.ARRAY_LABNAMES, labNames);

		mp.put(InvestigationConfig.ARRAY_TESTNAMES, testNames);

		mp.put(InvestigationConfig.ARRAY_TESTGROUPNAMES, testGroupNames);
		
		
		mp.put(InvestigationConfig.ARRAY_ADVISEDBY_NAMES, advisedByNames);
		mp.put(InvestigationConfig.ARRAY_TEST_CODE_WISE, testCodeWise);
		/*uom and container for sample coll*/
		/*mp.put(InvestigationConfig.LIST_UOM_COMBO,lstUOMCombo);
		mp.put(InvestigationConfig.LIST_CONTAINER_COMBO,lstContainerCombo);*/

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



public Map searchLaboratoryWiseTestGroupOnClickCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {


	JDBCTransactionContext tx = new JDBCTransactionContext();
	List<LabTestVO> lstLabTest=null;
	List<LabTestVO> lstLabTestForTestGruop=new ArrayList<LabTestVO>();
	//List<LabTestVO> lstPreviousLabTest=null;
	List lstUOMCombo=null;
	List lstContainerCombo=null;
	
	List<LabTestVO> lstSingleTestGroupDetail=new ArrayList<LabTestVO>();
	List<String> testGroupCode=new ArrayList<String>();
	
	Map mp=new HashMap();

	try {

		tx.begin();

		InvestigationEssentialDAO invEssentialDAO=new InvestigationEssentialDAO(tx);
		lstLabTest=invEssentialDAO.searchLaboratoryWiseTestGroupOnClickCumColl(searchVO, _UserVO);
		SampleCollectionDAO objSampleCollectionDAO=new SampleCollectionDAO(tx);
		//String sampleComboStr="<option value='-1'>Select Value</option>";
		String defaultUOM="";
		String defaultContainer="";
		String defaultQuantity="";
		String sampleValues="";
	
		lstUOMCombo=objSampleCollectionDAO.getUOMCombo(_UserVO);										//unit of measurement combo
		lstContainerCombo=objSampleCollectionDAO.getContainerCombo(_UserVO);			
		//String sampleComboStr="<option value='-1'>Select Value</option>";

		//Logic for Getting Sample Combo
		for(LabTestVO vo:lstLabTest)
		{
		
			defaultContainer="";
			defaultUOM="";
			defaultQuantity="";
			String strSAmpleCode = vo.getDefaultSampleCode();
			String sampleComboStr="<option value='-1'>Select Value</option>";
			String uomComboStr="<option value='-1'>Select Value</option>";
			String containerComboStr="<option value='-1'>Select Value</option>";
			List  lstSampleCombo=invEssentialDAO.getSampleCombo(vo.getLabCode(),vo.getTestCode(), _UserVO);
			if(lstSampleCombo!=null&&lstSampleCombo.size()>0)
			{
				Iterator lstIterator=lstSampleCombo.iterator();
				while(lstIterator.hasNext())
				{
					Entry entry=(Entry)lstIterator.next();
					sampleValues+=entry.getValue()+"*";
					String[] codes=entry.getValue().split("&");
					String sampCode=codes[0];
					
					
					if(lstSampleCombo.size()==1)
					{
						
						sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
						defaultContainer=codes[2];
						defaultUOM=codes[1];
						defaultQuantity=codes[3];
						vo.setSingleSample(sampCode);

					}
					else
					{
					
					if(sampCode.equalsIgnoreCase(strSAmpleCode))
						{sampleComboStr=sampleComboStr+"<option value='"+sampCode+"' selected>"+entry.getLabel().split("&")[0]+"</option>";
						defaultContainer=codes[2];
						defaultUOM=codes[1];
						defaultQuantity=codes[3];
						}
					else
						sampleComboStr=sampleComboStr+"<option value='"+sampCode+"'>"+entry.getLabel().split("&")[0]+"</option>";
					}}
			}

			//Sample Combo Logic
			if(vo.getTestType().equals(InvestigationConfig.TEST_TYPE_PATIENT_BASED))
				vo.setSampleComboStr("");
			else      // Sample and Slide Based
				vo.setSampleComboStr(sampleComboStr);

			//uom combo logic
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
			
			vo.setDefaultContainerVol(defaultQuantity);

			//uom combo logic
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


}//end class
