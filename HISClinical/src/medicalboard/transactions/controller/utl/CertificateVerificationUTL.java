package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HisUtil;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.CertificationVerificationDATA;
import medicalboard.transactions.controller.fb.CertificateVerificationFB;
import registration.controller.fb.CRNoFB;
import registration.controller.util.PatDetailUTIL;

public class CertificateVerificationUTL extends ControllerUTIL
{
	public static void getMBCertVerificationEssential(CertificateVerificationFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map mp=new HashMap();
		HisUtil hisutil = null;
		//List certificateTypeList=new ArrayList();
		try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			mp=CertificationVerificationDATA.getMBCertVerificationEssential(userVO);
			hisutil=new HisUtil("cssd","RequestOfItemsUTIL");
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			_fb.setSysDate(ctDate);
	        //WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE ,certificateTypeList);
	       	WebUTIL.setMapInSession(mp, _request);        
	        objStatus.add(Status.NEW);
		
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
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
		catch(Exception e){
			System.out.println("Inside Exception");
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
	
	public static void getCandidateList(CertificateVerificationFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		//Map mp=new HashMap();
		List <MedicalBoardRequisitionVO> mbRequisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		try{
			UserVO userVO=getUserVO(_request);
			String boardNo=_fb.getBoardNo();
			mbRequisitionVOList=CertificationVerificationDATA.getCandidateList(_fb.getCertificateTypeID(),boardNo,
							_fb.getFromDate(),_fb.getToDate(),userVO);
			_fb.setCertificateTypeName(getCertificateTypeName(_fb.getCertificateTypeID(), _request));
			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION, mbRequisitionVOList);
			
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,e.getMessage(),"");	
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
		catch(Exception e){
			System.out.println("Inside Exception");
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
	
	public static String getCertificateTypeName(String certificateTypeID,HttpServletRequest request){
		String name="";
		List certificateTypeList=(List)request.getSession().getAttribute(MedicalBoardConfig.CERTIFICATE_TYPE_LIST_FOR_CERT_VERIFICATION);
		for (Iterator iterator = certificateTypeList.iterator(); iterator
				.hasNext();) {
			Entry entry = (Entry) iterator.next();
			if(entry.getValue().equals(certificateTypeID)){
				name=entry.getLabel();
				break;
			}
		}
		return name;
	}
	
	public static void getCandidateDetailForCertVerification(CertificateVerificationFB _fb,HttpServletRequest _request)
	{	
		
		Status objStatus=new Status();
//		List<MedicalBoardInvestigationMappingVO> investigationMappingVOList=null;
		Map essentialMap=new HashMap();
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		MedicalBoardRequisitionVO requisitionVO=new MedicalBoardRequisitionVO();
		
		try{
			UserVO userVO=getUserVO(_request);
			CRNoFB crNoFb=new CRNoFB();
			requisitionVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION);
			if(requisitionVOList!=null){
				requisitionVO=requisitionVOList.get(Integer.parseInt(_fb.getSelectedCandidate().split("#")[0]));
			}
			_fb.setPatCrNo(requisitionVO.getPatCrNo());
			_fb.setCertificateNo(requisitionVO.getCertificateNo());
			crNoFb.setPatCrNo(_fb.getPatCrNo());
			PatDetailUTIL.getPatientDtlByCrno(crNoFb, _request);
			
			essentialMap=CertificationVerificationDATA.getMBBoardMemberOpinionDetail(requisitionVO, userVO);
			
			WebUTIL.setMapInSession(essentialMap, _request);
			
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,"","");	
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
		catch(Exception e){
			System.out.println("Inside Exception");
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

	public static boolean saveCertVerificationDetail(CertificateVerificationFB _fb,HttpServletRequest _rq)
	{
		boolean flag= true;
		Status objStatus=new Status();
		//HttpSession session=WebUTIL.getSession(_rq);
		MedicalBoardRequisitionVO requisitionVO=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		
		try{
			//get the list of the candidate
			requisitionVOList=(List)_rq.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION);
	
			String reqID=_fb.getSelReqId();
			for(int i=0;i<requisitionVOList.size();i++){
				if(requisitionVOList.get(i).getReqID().equals(reqID)){
					requisitionVO=requisitionVOList.get(i);
				}
			}
			
			requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
			requisitionVO.setOpinionCode(_fb.getOpinionCode().split("#")[0]);
			requisitionVO.setFinalRemark(_fb.getFinalRemark());
			requisitionVO.setIsVerified("1");
			requisitionVO.setCertificateResult(_fb.getOpinionCode().split("#")[1]);
			requisitionVO.setApprovedBy(getUserVO(_rq).getUserEmpID());
			requisitionVO.setApprovedDate(_fb.getApprovedDate());
			
			CertificationVerificationDATA.saveCertVerificationDetail(requisitionVO,getUserVO(_rq));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
			
		}catch(HisDataAccessException e){
			flag = false;
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			flag = false;
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			flag = false;
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			flag = false;
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
		return flag;
	}
}
