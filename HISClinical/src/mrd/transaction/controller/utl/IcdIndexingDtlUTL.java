package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import registration.controller.fb.CRNoFB;
import mrd.MrdConfig;
import mrd.transaction.controller.data.IcdIndexingDtlDATA;
import mrd.transaction.controller.data.OfflineMrdRecordReqDtlDATA;
import mrd.transaction.controller.fb.OfflineMrdRecordReqDtlFB;
import mrd.transaction.controller.fb.IcdIndexingFB;
import hisglobal.vo.PatientDetailVO;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class IcdIndexingDtlUTL extends ControllerUTIL
{
	
	
	public static void getEssentialForIcdIndexing(IcdIndexingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			UserVO userVO = new UserVO();
			HttpSession session=request.getSession();
			userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			PatientDetailVO patdtlVO= new PatientDetailVO();
			HelperMethods.populate(patdtlVO, fb);
			Map map=IcdIndexingDtlDATA.getEssentialForIcdIndexing(getUserVO(request),patdtlVO);
			WebUTIL.setMapInSession(map, request);
			System.out.println("admnumber....."+fb.getAddmissionNo());
			//fb.setIsMrdLengthOne(MrdConfig.YES);
			fb.setRequestByName(userVO.getUsrName());
			
			List lstMrd=(List)map.get(MrdConfig.PATIENT_ADDMISSION_NO_WISE_DTLLIST);
			
			if(lstMrd!=null && lstMrd.size()<1)
			{
				fb.setStatusMessage(" Patient Details Not Found!");
			}else{
				PatientDetailVO patdtlVONew=(PatientDetailVO)lstMrd.get(0);
				System.out.println("value"+patdtlVONew.getAdmstatuscode());
				
				
				String admStatus="";
				
				if(patdtlVONew.getAdmStatusCode() != null){
					
					admStatus=patdtlVONew.getAdmStatusCode();
				}else if(patdtlVONew.getAdmstatuscode() !=null) {
					admStatus=patdtlVONew.getAdmstatuscode();
				}
				
				
				if(!admStatus.equals(MrdConfig.PATIENT_ADMISSION_STATUS_DISCHARGE)) {
					fb.setStatusMessage("Patient is not Discharged");
				}
				//fb.setStatusMessage("Patient is not Admitted");
				patdtlVONew.setAddmissionNo(fb.getAddmissionNo());
				System.out.println(fb.getStatusMessage());
				System.out.println(patdtlVONew.getPatCrNo());
				HelperMethods.populate(fb,patdtlVONew);
				fb.setPatCrNo(patdtlVONew.getPatCrNo());
				request.setAttribute("patCrNo", patdtlVONew.getPatCrNo());
				request.getSession().setAttribute("patCrNo", patdtlVONew.getPatCrNo());
				System.out.println(fb.getPatCrNo());
				
				System.out.println("lstMrd.size()==="+lstMrd.size());
			}
			
			List<EpisodeDiagnosisVO> lstMrdDiag=(List<EpisodeDiagnosisVO>)map.get(MrdConfig.PATIENT_DIAGNOSIS_DTLLIST);
			
			fb.setLstMrdDiag(lstMrdDiag);
			
			//Added by Vasu on 07.March.2019

			List<MrdIcdDtlVO> prevICDDiseaseList = (List)map.get(MrdConfig.PREV_ICD_CODES);
			WebUTIL.setAttributeInSession(request, MrdConfig.PREV_ICD_CODES, prevICDDiseaseList);
			
			//fb.setDignosisName(request.getParameter("dignosisName"));
//			fb.setDiseaseCode((request.getParameter("flexdatalist-icdCodeList").toString()));
			
		//	fb.setDiseaseCode("XYZ");
			System.out.println("dignosisName="+fb.getDiagnosisName());
			System.out.println("icdCodeList="+fb.getDiseaseCode());
			System.out.println("icdCodeList="+fb.getIcdCodeList());
			System.out.println("icdlst="+fb.getLstMrdDiag().size());
			WebUTIL.setAttributeInSession(request, MrdConfig.LIST_MRD_FB, fb);
			
			
		
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	

	public static void saveIcdDtl(IcdIndexingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		IcdIndexingFB fbNew=new IcdIndexingFB();
		List<MrdIcdDtlVO> lstmrdVo =new ArrayList<MrdIcdDtlVO>();
		try
		{
			
			fbNew=(IcdIndexingFB)session.getAttribute(MrdConfig.LIST_MRD_FB);
			
			fbNew.setDiagnosisName(fb.getDiagnosisName());
			
			fbNew.setIcdCodeList(request.getParameter("flexdatalist-icdCodeList"));
			System.out.println("add"+fbNew.getAddmissionNo());
			
			
			//System.out.println("arrcode"+fb.getArrIcdCodeList()[0]);
			
		
			for(int i=0;i<fb.getArrIcdCodeList().length;i++){
				
				//System.out.println("icdRecordStatus:"+fb.getIcdRecordStatus()[i]);
				
				//if(fb.getIcdRecordStatus()[i].equals(MrdConfig.ICD_RECORD_STATUS_NOT_SAVED))
				//{
				MrdIcdDtlVO mrdIcdVo=new MrdIcdDtlVO();
				HelperMethods.populate(mrdIcdVo,fbNew);
				System.out.println("addmrd::::"+mrdIcdVo.getAddmissionNo());

				mrdIcdVo.setIcdCodeList(fb.getArrIcdCodeList()[i]);
				mrdIcdVo.setDiagnosisName(fb.getArrdiagnosisName()[i]);
				lstmrdVo.add(mrdIcdVo);
				//}
			}
			
			System.out.println("listsise::"+lstmrdVo.size());
		
			PatientDetailVO patdtlVO= new PatientDetailVO();
			HelperMethods.populate(patdtlVO, fb);
			
			IcdIndexingDtlDATA.saveIcdIndexDetail(lstmrdVo,getUserVO(request),patdtlVO);
			objStatus.add(Status.DONE,"","Request Raised");
		
			System.out.println("arrcode"+fb.getArrIcdCodeList()[0]);
			
			
			
			
		fb.setAddmissionNo("");
			fb.setStatusMessage("Data Inserted successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	//	Added by Vasu on 07.March.2019
	public static void deleteIcdRecord(IcdIndexingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		IcdIndexingFB fbNew=new IcdIndexingFB();
		MrdIcdDtlVO lcdMrdVo =new MrdIcdDtlVO();
		try
		{
			
			lcdMrdVo.setAddmissionNo(fb.getAddmissionNo());
			lcdMrdVo.setIcdCodeList(fb.getIcdCodeToBeRemoved());
			
			IcdIndexingDtlDATA.deleteIcdIndexDetail(lcdMrdVo,getUserVO(request));
			//objStatus.add(Status.DONE,"","Request Raised");
		
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
}
