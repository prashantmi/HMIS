package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.HandOverDetailVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.CertificateHandoverDATA;
import medicalboard.transactions.controller.fb.CertificateHandoverFB;
import mrd.MrdConfig;

public class CertificateHandoverUTL extends ControllerUTIL
{
public static void setCertificateHandoverEssentials(CertificateHandoverFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		try{
			UserVO userVO=getUserVO(_request);
			
			setSysdate(_request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			_fb.setSysdate(sysadteString);
			
			_fb.setSearchType(MedicalBoardConfig.SEARCH_TYPE_CERTIFICATE_TYPE_WISE);
			_fb.setCertificateTypeID("-1");
			_fb.setRequistionDate("0");
			essentialMap=CertificateHandoverDATA.setCertificateHandoverEssentials(userVO);
	       
			WebUTIL.setMapInSession(essentialMap, _request);
	        objStatus.add(Status.DONE);
		
		}
		
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.NEW,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}

public static void getReqDateByCertificateTypeId(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	Map essentialMap=new HashMap();
	HttpSession session=WebUTIL.getSession(_request);
	List candidateList=null;
	List candidateListWithFromName=new ArrayList();
	try{
		UserVO userVO=getUserVO(_request);
		
		essentialMap=CertificateHandoverDATA.getReqDateByCertificateTypeId(_fb.getCertificateTypeID().split("#")[0],userVO);
		
		String[] Certificate= _fb.getCertificateTypeID().split("#");
		System.out.println(Certificate.length);
		if(Certificate.length==4)
		{
			_fb.setTemplateId(_fb.getCertificateTypeID().split("#")[3]);
		}

		
		candidateList=(List)essentialMap.get(MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE);
		if(candidateList!=null)
		{
			for(int i=0;i<candidateList.size();i++)
			{
				MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)candidateList.get(i);
				
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL))
				{
					reqVO.setRequestFromName("INDIVIDUAL");
				}
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION))
				{
					reqVO.setRequestFromName("ORGANIZATION");
				}
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ANYONE))
				{
					if(reqVO.getOrgID()!=null)
					{
						reqVO.setRequestFromName("ORGANIZATION");
					}
					else
					{
						reqVO.setRequestFromName("INDIVIDUAL");
					}
				}
				_fb.setOrgName(reqVO.getOrgName());
				_fb.setOpinion(reqVO.getOpinion());

				candidateListWithFromName.add(reqVO);
			}
		}
		
		WebUTIL.setMapInSession(essentialMap, _request);
		session.removeAttribute(MedicalBoardConfig.SELECTED_CANDIDATE_LIST);
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE, candidateListWithFromName);
        objStatus.add(Status.LIST);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getCandidateListByReqDate(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	List candidateList=null;
	try{
		UserVO userVO=getUserVO(_request);
		
		if(_fb.getRequistionDate().equals("0"))
		{
			candidateList=CertificateHandoverDATA.getcandidateListByCerficateType(_fb.getCertificateTypeID().split("#")[0],userVO);
		}
		else
		{
			candidateList=CertificateHandoverDATA.getCandidateListByReqDate(_fb.getCertificateTypeID().split("#")[0],_fb.getRequistionDate(),userVO);
		}
		
		
		if(candidateList!=null)
		{
			for(int i=0;i<candidateList.size();i++)
			{
				MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)candidateList.get(i);
				
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL))
				{
					reqVO.setRequestFromName("INDIVIDUAL");
				}
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION))
				{
					reqVO.setRequestFromName("ORGANIZATION");
				}
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ANYONE))
				{
					if(reqVO.getOrgID()!=null)
					{
						reqVO.setRequestFromName("ORGANIZATION");
					}
					else
					{
						reqVO.setRequestFromName("INDIVIDUAL");
					}
				}
			}
		}
		
		session.removeAttribute(MedicalBoardConfig.SELECTED_CANDIDATE_LIST);
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE, candidateList);
        
		
		objStatus.add(Status.LIST);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getHandOverToDetail(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	List candidateList=null;
	List selectedCadidateList=new ArrayList();
	try{
//		UserVO userVO=getUserVO(_request);
		
		List candidateListByCrNo=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO);
		String[] selReqNoArray=_fb.getSelReqNoArray();
		if(candidateListByCrNo!=null)
		{
			String flag="NO";
			_fb.setRequtionByFlag(flag);
			
			for(int i=0;i<selReqNoArray.length;i++)
			{
				for(int j=0;j<candidateListByCrNo.size();j++)
				{
					MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)candidateListByCrNo.get(j);
					if(selReqNoArray[i].equals(reqVO.getReqID()))
					{System.out.println(reqVO.getPatCrNo()+"cr nonnono");
						selectedCadidateList.add(reqVO);
						
						_fb.setReqStatus(reqVO.getReqStatus());
						
						if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL))
						{
							_fb.setRequtionByFlag("YES");
						}
						
						_fb.setDefinedIsuueType(reqVO.getCertIssueType());
						
						if(reqVO.getCertIssueType().equals(MedicalBoardConfig.ISSUE_TO_BOTH_CANDIDATE_OR_ORGANIZATION))
						{
							_fb.setHandOverTo("");
						}
						else
						{
							_fb.setHandOverTo(reqVO.getCertIssueType());
						}
						
						_fb.setRequtionBy(reqVO.getCertReqBy());
					}
				}
			}
			
			
			
		}
		
		candidateList=(List)session.getAttribute(MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE);
		String[] selectedReqIdArray=_fb.getReqIdArray();
				
		if(candidateList!=null)
		{
			String flag="NO";
			_fb.setRequtionByFlag(flag);
			_fb.setReqStatus("");
			for(int i=0;i<selectedReqIdArray.length;i++)
			{
				for(int j=0;j<candidateList.size();j++)
				{
					MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)candidateList.get(j);
					if(selectedReqIdArray[i].equals(reqVO.getReqID()))
					{
						selectedCadidateList.add(reqVO);
						
						if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL))
						{
							_fb.setRequtionByFlag("YES");
						}
					}
					_fb.setPatCrNo(reqVO.getPatCrNo());
				}
			}
			
			if(_fb.getCertificateTypeID().split("#")[1]!=null)
			{
				_fb.setDefinedIsuueType(_fb.getCertificateTypeID().split("#")[1]);
				
				if(_fb.getCertificateTypeID().split("#")[1].equals(MedicalBoardConfig.ISSUE_TO_BOTH_CANDIDATE_OR_ORGANIZATION))
				{
					_fb.setHandOverTo("");
				}
				else
				{
					_fb.setHandOverTo(_fb.getCertificateTypeID().split("#")[1]);
				}
			}
			
			if(_fb.getCertificateTypeID().split("#")[2]!=null)
			{
				
				_fb.setRequtionBy(_fb.getCertificateTypeID().split("#")[2]);
			}
		
		}
		
		System.out.println(_fb.getPatCrNo()+" cr nonnono");
		
		session.removeAttribute(MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE);
		session.removeAttribute(MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO);
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.SELECTED_CANDIDATE_LIST, selectedCadidateList);
		objStatus.add(Status.RECORDFOUND);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getSelectHandOverTo(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
//	HttpSession session=WebUTIL.getSession(_request);
	
	try{
//		UserVO userVO=getUserVO(_request);
		
		_fb.setIsAuthorityProved(null);
		_fb.setHandOverBy("-1");
		_fb.setRemarks("");
		objStatus.add(Status.RECORDFOUND);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void saveHandOverDetail(CertificateHandoverFB _fb,HttpServletRequest _request) {	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
//	List candidateList=null;
	List selectedCadidateList=null;
	List selectedReqList=null;
	List handOverDetailVOList=new ArrayList();
	try{
		UserVO userVO=getUserVO(_request);
		
		selectedCadidateList=(List)session.getAttribute(MedicalBoardConfig.SELECTED_CANDIDATE_LIST);
		selectedReqList=(List)session.getAttribute(MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE);
		HandOverDetailVO handOverDetailVO=null;
		
		if(selectedReqList!=null)
		{
			for(int i=0;i<selectedReqList.size();i++)
			{
				MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)selectedReqList.get(i);
				handOverDetailVO=new HandOverDetailVO();
				WebUTIL.populate(handOverDetailVO, _fb);
				handOverDetailVO.setRequisitionId(reqVO.getReqID());
				if(_fb.getReqStatus().equals(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER))
				{
					handOverDetailVO.setIsDuplicate(MedicalBoardConfig.IS_DUPLICATE_NO);
				}
				else
				{
					handOverDetailVO.setIsDuplicate(MedicalBoardConfig.IS_DUPLICATE_YES);
				}
				
				handOverDetailVOList.add(handOverDetailVO);
			}
		}
		if(selectedCadidateList!=null)
		{
			for(int i=0;i<selectedCadidateList.size();i++)
			{
				MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)selectedCadidateList.get(i);
				handOverDetailVO=new HandOverDetailVO();
				WebUTIL.populate(handOverDetailVO, _fb);
				handOverDetailVO.setRequisitionId(reqVO.getReqID());
				if(_fb.getReqStatus().equals(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER))
				{
					handOverDetailVO.setIsDuplicate(MedicalBoardConfig.IS_DUPLICATE_NO);
				}
				else
				{
					handOverDetailVO.setIsDuplicate(MedicalBoardConfig.IS_DUPLICATE_YES);
				}
				
				handOverDetailVOList.add(handOverDetailVO);
			}
		}
		
			CertificateHandoverDATA.saveHandOverDetail(handOverDetailVOList,userVO);
		
			if(_fb.getCertificateTypeID()!=null&& !_fb.getCertificateTypeID().equals(""))
			{
				String[] CertificateArr = _fb.getCertificateTypeID().split("#");
					if (CertificateArr.length == 4) {
						_fb.setPrint("1");
					}
					else _fb.setPrint("0");
			}
			if(_fb.getTemplateId()!=null&& !_fb.getTemplateId().equals(""))
			{
				_fb.setPrint("1");
			}
			else _fb.setPrint("0");
		
		objStatus.add(Status.NEW,"","Certificate Hand Over Successfully");
	
		
		PatientImageDtlVO patientImageDtlVOList=new PatientImageDtlVO();
		//byte array[]=null;
		List byteArray=new ArrayList();
		_fb.setIsPatient("0");
		try
		{
			patientImageDtlVOList=CertificateHandoverDATA.getPatientImageDtlByCrNo(_fb.getPatCrNo(),getUserVO(_request));
			
			String filepath=patientImageDtlVOList.getFilePath().replace("\\","/")+"/" + patientImageDtlVOList.getFileNo();
			System.out.println("filepath :"+filepath);
			byteArray.add(HelperMethods.getByteArrayOfImage(filepath));
			
			/*File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			array=new byte[fis.available()];
			fis.read(array);
			byteArray.add(array);*/
				//	}
			_fb.setPatCrNo(((PatientImageDtlVO)patientImageDtlVOList).getPatCrNo());
			_fb.setSerialNo(((PatientImageDtlVO)patientImageDtlVOList).getSerialNo());
			//	_fb.setMaxSerialNo(((PatientImageDtlVO)patientImageDtlVOList.get(patientImageDtlVOList.size()-1)).getSerialNo());
			WebUTIL.setAttributeInSession(_request,Config.IMAGE_BYTE_ARRAY,byteArray);
			WebUTIL.setAttributeInSession(_request,MrdConfig.PATIENT_IMAGE_DTL_VO_LIST , patientImageDtlVOList);
			_fb.setIsPatient("1");
		}
		catch(FileNotFoundException e){
			_fb.setIsPatient("0");
			e.printStackTrace();
		}
		catch(IOException e){
			_fb.setIsPatient("0");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			_fb.setIsPatient("0");
		}
		

	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getReqListByCrNo(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	List reqNoList=null;
	try{
		UserVO userVO=getUserVO(_request);
		
		reqNoList=CertificateHandoverDATA.getAllCandidateListByCrNo(_fb.getPatCrNo(), userVO);
			
		if(reqNoList!=null)
		{
			for(int i=0;i<reqNoList.size();i++)
			{
				MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)reqNoList.get(i);
				System.out.println(reqVO.getTemplateID()+"TemplateID in canditate");
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL))
				{
					reqVO.setRequestFromName("INDIVIDUAL");
					_fb.setTemplateId(reqVO.getTemplateID());
					_fb.setOrgName(reqVO.getOrgName());
				}
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION))
				{
					reqVO.setRequestFromName("ORGANIZATION");
					_fb.setTemplateId(reqVO.getTemplateID());
					_fb.setOrgName(reqVO.getOrgName());
				}
				if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ANYONE))
				{
					if(reqVO.getOrgID()!=null)
					{
						reqVO.setRequestFromName("ORGANIZATION");
						_fb.setTemplateId(reqVO.getTemplateID());
						_fb.setOrgName(reqVO.getOrgName());
					}
					else
					{
						reqVO.setRequestFromName("INDIVIDUAL");
						_fb.setTemplateId(reqVO.getTemplateID());
						_fb.setOrgName(reqVO.getOrgName());
					}
				}
			}
		}
		
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO, reqNoList);
        session.removeAttribute(MedicalBoardConfig.SELECTED_CANDIDATE_LIST);
        session.removeAttribute(MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE);
		objStatus.add(Status.LIST);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getHandOverToDetailPatWise(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
//	List candidateList=null;
	List selectedCadidateList=new ArrayList();
	try{
//		UserVO userVO=getUserVO(_request);
		
		List candidateListByCrNo=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO);
		String[] selReqNoArray=_fb.getSelReqNoArray();
		if(candidateListByCrNo!=null)
		{
			String flag="NO";
			_fb.setRequtionByFlag(flag);
			
			for(int i=0;i<selReqNoArray.length;i++)
			{
				for(int j=0;j<candidateListByCrNo.size();j++)
				{
					MedicalBoardRequisitionVO reqVO=(MedicalBoardRequisitionVO)candidateListByCrNo.get(j);
					if(selReqNoArray[i].equals(reqVO.getReqID()))
					{
						selectedCadidateList.add(reqVO);
						
						_fb.setReqStatus(reqVO.getReqStatus());
						
						if(reqVO.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL))
						{
							_fb.setRequtionByFlag("YES");
						}
						
						_fb.setDefinedIsuueType(reqVO.getCertIssueType());
						
						if(reqVO.getCertIssueType()== null)
						{
							reqVO.setCertIssueType("");
						}
						if(_fb.getReqStatus().equals(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER))
						{
							_fb.setHandOverTo("");
						}
						else
						{
							if(reqVO.getCertIssueType().equals(MedicalBoardConfig.ISSUE_TO_BOTH_CANDIDATE_OR_ORGANIZATION))
							{
								_fb.setHandOverTo("");
							}
							else
							{
								_fb.setHandOverTo(reqVO.getCertIssueType());
							}
						}
						
						
						_fb.setRequtionBy(reqVO.getCertReqBy());
					}
				}
			}
			
			
			
		}
		
		
		
		
		
		session.removeAttribute(MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE);
		session.removeAttribute(MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO);
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE, selectedCadidateList);
		objStatus.add(Status.RECORDFOUND);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getSearchType(CertificateHandoverFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	Map essentialMap=new HashMap();
	HttpSession session=WebUTIL.getSession(_request);
	try{
		UserVO userVO=getUserVO(_request);
		
		setSysdate(_request);
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
		_fb.setSysdate(sysadteString);
		
		_fb.setCertificateTypeID("-1");
		
		essentialMap=CertificateHandoverDATA.setCertificateHandoverEssentials(userVO);
       	WebUTIL.setMapInSession(essentialMap, _request);
		
       	objStatus.add(Status.DONE);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}




public static boolean setTemplateData(CertificateHandoverFB _fb, HttpServletRequest _rq)
{
	Status objStatus = new Status();
	try
	{
		setSysdate(_rq);
		HttpSession session = _rq.getSession();
		MedicalBoardRequisitionVO voMBReq = new MedicalBoardRequisitionVO(); //-----

		voMBReq.setEntryDate(_rq.getParameter("handoverDate"));
		voMBReq.setFinalRemark(_rq.getParameter("remarks"));
		voMBReq.setOrgName(_rq.getParameter("orgName"));
		voMBReq.setOpinion(_rq.getParameter("opinion"));
		voMBReq.setCertificateNo(_rq.getParameter("certificateNo"));



		List selectedCandidateList=(ArrayList)session.getAttribute(MedicalBoardConfig.SELECTED_CANDIDATE_LIST);
		if(selectedCandidateList!=null){
			Iterator itr=selectedCandidateList.iterator();
			String strPatCrNo="";
			while(itr.hasNext())
			{
				MedicalBoardRequisitionVO requisitionVO=(MedicalBoardRequisitionVO)itr.next();
				 strPatCrNo= requisitionVO.getPatCrNo();
			}
			if(strPatCrNo!=null){
				_fb.setPatCrNo(strPatCrNo);
			}
		
		}
			
		String strPatCrRq= _rq.getParameter("patCrNo");
		if(_fb.getPatCrNo().equals("")||_fb.getPatCrNo()==null)
		{	
			_fb.setPatCrNo(strPatCrRq);
		}
		InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
		PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
		GenericTemplateUtility.setVOInInfoBean(_rq, patientVO);

		String Gender=patientVO.getPatGender();
		String Age= patientVO.getPatAge();
		
		String AgeGender = Gender.concat(Age);
		
		System.out.println(AgeGender+"Age Gender of Patient");
		/*if(_rq.getParameter("i")!=null)
		{			strAreaId = request.getParameter("areaID");

			index = Integer.parseInt(_rq.getParameter("i"));
			
			lstReceivedConsents = (List<ConsentRequestVO>)session.getAttribute(OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST);			
			ConsentRequestVO targetConsent = lstReceivedConsents.get(index);
			GenericTemplateUtility.setVOInInfoBean(_rq, targetConsent);
		}*/

		GenericTemplateUtility.setVOInInfoBean(_rq, voMBReq);

		objStatus.add(Status.TRANSINPROCESS);
 	}
	catch (HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR, e.getMessage(), "");
	}
	catch (HisDataAccessException e)
	{
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
	}
	catch (HisApplicationExecutionException e)
	{
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
	}
	catch (HisException e)
	{
		objStatus.add(Status.ERROR, e.getMessage(), "");
	}
	catch (Exception e)
	{
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
	}
	finally
	{
		WebUTIL.setStatus(_rq, objStatus);
	}
	return true;
}
}
