package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DischargePrintingVO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import mrd.MrdConfig;
import mrd.transaction.controller.data.PatientDischargePrintingDATA;
import mrd.transaction.controller.fb.PatientDischargePrintingFB;
import opd.OpdConfig;

public class PatientDischargePrintingUTL extends ControllerUTIL {
	
	public static boolean getDeptUnitList(PatientDischargePrintingFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			//String sys=(String)request.getSession().getAttribute(Config.SYSDATE);
			//String time=sys.split(" ")[1];
			//fb.setHiddenTimeHr(time.split(":")[0]);
			//fb.setHiddenTimeMin(time.split(":")[1]);
			List lstUnit=PatientDischargePrintingDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_DEPT_UNIT_LIST ,lstUnit);
		
			objStatus.add(Status.INPROCESS);
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
		return flag;
	}
	
	public static boolean getWardOnBasisOfUnitCode(PatientDischargePrintingFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		//HttpSession session = request.getSession();
		
		try
		{
			//String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
			//String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			String unitCode = fb.getDepartmentUnitCode();
			
			listWard=PatientDischargePrintingDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			fb.setWardCode("-1");
					
			objStatus.add(Status.INPROCESS);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			e.printStackTrace();
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
		return flag;
	}
	
	
	//gets the detail of the discharged patient for printing discharge profile.
	
	public static void getDischargePatientByCrNo(PatientDischargePrintingFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		List<DischargePrintingVO> dischargePatList = null;
		try
		{
			String patCrNo=fb.getPatCrNo();
			dischargePatList=PatientDischargePrintingDATA.getDischargePatientByCrNo(patCrNo,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.DISCHARGE_PAT_LIST,dischargePatList);
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.RECORDFOUND,"", e.getMessage());
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.DISCHARGE_PAT_LIST,null);
			e.printStackTrace();
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

	//gets the list of all discharge patient who discharged today 
	
	public static void getDischargePatientList(PatientDischargePrintingFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		List<DischargePrintingVO> dischargePatList = null;
		try
		{
			String deptUnitCode=fb.getDepartmentUnitCode().replace("^","#").split("#")[0];
			String wardCode=fb.getWardCode();
			dischargePatList=PatientDischargePrintingDATA.getDischargePatientList(deptUnitCode,wardCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.DISCHARGE_PAT_LIST,dischargePatList);
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.RECORDFOUND,"", e.getMessage());
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.DISCHARGE_PAT_LIST,null);
			e.printStackTrace();
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

	//save the discharge hand over detail and print detail.
	
	public static void saveDischargePrinting(PatientDischargePrintingFB fb,HttpServletRequest request) {
		Status objStatus = new Status();
		DischargePrintingVO dischargePrintingVO = new DischargePrintingVO();
		try
		{
			if(getDischargePrintDtl(fb,request)){
				HelperMethods.populate(dischargePrintingVO, fb);
				if(fb.getIsReceiptTaken().equals(""))
					dischargePrintingVO.setIsReceiptTaken("0");
				String date=fb.getHandOverDate()+ " "+ fb.getHandOverHr()+":"+fb.getHandOverMin();
				dischargePrintingVO.setIsDuplicate(MrdConfig.IS_DUPLICATE_NO);
				dischargePrintingVO.setHandOverDate(date);
				dischargePrintingVO.setDepartmentUnitCode(fb.getHiddenDeptUnitCode());
				dischargePrintingVO.setWardCode(fb.getHiddenWardCode());
				PatientDischargePrintingDATA.saveDischargePrinting(dischargePrintingVO,getUserVO(request));
				fb.setHmode("PRINT");
				objStatus.add(Status.RECORDFOUND);
				objStatus.add(Status.NEW);
			}
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

	//gets the discharge profile read it and set the htmlcode in session
	
	public static boolean getDischargePrintDtl(PatientDischargePrintingFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		String filePath="";
		boolean flag=true;
		try
		{
			String profileId=fb.getProfileId();
			String profileStatus=fb.getProfileStatus();
			if(profileStatus.equals(OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS)){
				objStatus.add(Status.RECORDFOUND,"","Cannot Print. Patient Profile is in process");
				objStatus.add(Status.NEW);
				flag=false;
			}
			else{
				if(System.getProperties().getProperty("os.name").indexOf("Win")!=-1){
					filePath=Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS;
				}
				else{
					filePath=Config.PATIENT_PROFILE_STORAGE_PATH_LINUX;
				}
				String fileName=filePath+"/"+profileId + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
				//read the html file of patient profile
				File file=new File(fileName);
				FileInputStream fis=new FileInputStream(file);
				BufferedInputStream bis=new BufferedInputStream(fis);
				byte [] byteArray=new byte[bis.available()];
				bis.read(byteArray);
				///char[] chars = new char[byteArray.length];
				//for (int i = 0; i < byteArray.length; i++)
					//chars[i] = (char) byteArray[i];
				//String htmlCode=String.valueOf(chars);
				String htmlCode=new String(byteArray);
				fis.close();
				bis.close();
				//System.out.println("htmlCode=............................"+htmlCode);
				fb.setHmode("PRINT");
				//objStatus.add(Status.TRANSINPROCESS);
				WebUTIL.setAttributeInSession(request, MrdConfig.PATIENT_PROFILE_BYTE_ARRAY, htmlCode);
			}
		}
		catch (FileNotFoundException e)
		{
			flag=false;
			objStatus.add(Status.RECORDFOUND,"", "Patient Profile Not Found");
			objStatus.add(Status.NEW);
			e.printStackTrace();
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
		return flag;
		
	}
	
	
	/*sets the discharge printing save option and handoverDetails
	*If patient is mlc then hand over to police only
	*If Patient is dead and not mlc then hand over to relative only
	*/
	public static void setDischargePrintSaveOption(PatientDischargePrintingFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		List<DischargePrintingVO> dischargePatList = null;
		DischargePrintingVO dichargePatVO=new DischargePrintingVO();
		Map relationshipList=null;
		try
		{
			String patCrNo=fb.getSelectCRNo().split("#")[0];
			dischargePatList=(List)request.getSession().getAttribute(MrdConfig.DISCHARGE_PAT_LIST);
			if(dischargePatList!=null)
			for(int i=0;i<dischargePatList.size();i++){
				if(dischargePatList.get(i).getPatCrNo().equals(patCrNo)){
					dichargePatVO=dischargePatList.get(i);
					break;
				}
			}
			fb.setPatAdmNo(dichargePatVO.getPatAdmNo());
			fb.setHiddenDeptUnitCode(dichargePatVO.getDepartmentUnitCode());
			fb.setHiddenWardCode(dichargePatVO.getWardCode());
			
			//If patient is mlc then hand over to police only
			if(dichargePatVO.getIsMlc().equals(MrdConfig.IS_MLC_YES)){
				fb.setHandOverTo(MrdConfig.HAND_OVER_TO_POLICE);
			}
			else{
				//If Patient is dead and not mlc then hand over to relative only
				if(dichargePatVO.getIsDead().equals(MrdConfig.IS_PATIENT_DEAD_YES)){
					fb.setHandOverTo(MrdConfig.HAND_OVER_TO_RELATIVE);
				}
				else{
					fb.setHandOverTo("-1");
				}
			}
			//getting the list of relationShip
			relationshipList=PatientDischargePrintingDATA.getRelationsList(getUserVO(request));
			WebUTIL.setMapInSession(relationshipList,request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.RECORDFOUND,"", e.getMessage());
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.DISCHARGE_PAT_LIST,null);
			e.printStackTrace();
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
