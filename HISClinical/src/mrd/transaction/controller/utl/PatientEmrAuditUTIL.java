package mrd.transaction.controller.utl;
/**
 * @author : Adil Wasi
 * Creation Date: 06-Jun-2012
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.transaction.controller.data.PatientEmrAuditDATA;
import mrd.transaction.controller.fb.PatientEmrAuditFB;
import mrd.vo.PatientEmrAuditVO;

public class PatientEmrAuditUTIL extends ControllerUTIL{

	/**
	 * Get Audit Type Wise List
	 * @param objPatientEmrAuditFB_p
	 * @param objRequest_p
	 * Added By Adil Wasi  on 06-Jun-2012
	 */
	public static boolean getAuditTypeWiseList(PatientEmrAuditFB objPatientEmrAuditFB_p, HttpServletRequest objRequest_p) {
		Status objStatus = new Status();
		boolean flag = true;

		try
		{
//			String sys = (String) objRequest_p.getSession().getAttribute(Config.SYSDATE);
			
			List lstUnit = PatientEmrAuditDATA.getAuditTypeWiseList(getUserVO(objRequest_p));
			WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.PATIENT_EMR_AUDIT_TYPE_LIST, lstUnit);

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
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		return flag;
		
	}

	
	/**
	 * AJAX Response : Getting Audit User List
	 * @param objPatientEmrAuditFB_p
	 * @param objRequest_p
	 * Added By Adil Wasi  on 06-Jun-2012
	 */
	public static StringBuffer getAuditUserList(PatientEmrAuditFB objPatientEmrAuditFB_p, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			
			PatientEmrAuditVO  patientEmrAuditVO = new PatientEmrAuditVO();
			HelperMethods.populate(patientEmrAuditVO, objPatientEmrAuditFB_p);
			String strProcMode ="1";		// (String) objRequest_p.getAttribute("strProcMode");
			patientEmrAuditVO.setStrEmrAuditTypeId(objRequest_p.getParameter("strEmrAuditTypeId"));

			List<PatientEmrAuditVO> lstPatientEmrAuditUser = PatientEmrAuditDATA.getAuditUserList(strProcMode, patientEmrAuditVO, userVO);
			
			if(lstPatientEmrAuditUser!=null && lstPatientEmrAuditUser.size()>0)
			{
				sbAjaxRes.append("[");
				for(PatientEmrAuditVO vo : lstPatientEmrAuditUser)
				{
					sbAjaxRes.append("{");
					sbAjaxRes.append("strEmrAuditUserId");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getStrEmrAuditUserId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("strEmrAuditUserName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getStrEmrAuditUserName());sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");
					
				}
				if(lstPatientEmrAuditUser.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		}
		catch (Exception e)
		{
			strMsgText = "PatientEmrAuditUTIL.getAuditUserList() -> " + e.getMessage();
			//HisException eObj = 
			new HisException("mrd", "PatientEmrAuditUTIL.getAuditUserList() --> ", strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}

	/**
	 * Get Patient Emr Audit Detail By Cr No
	 * @param objPatientEmrAuditFB_p
	 * @param objRequest_p
	 * Added By Adil Wasi  on 06-Jun-2012
	 */
	public static void getPatientEmrAuditDtlByCrNo(PatientEmrAuditFB objPatientEmrAuditFB_p, HttpServletRequest objRequest_p) {
		Status objStatus = new Status();
		//boolean flag = true;

		try
		{
			objPatientEmrAuditFB_p.setStrSearchPatCrNo(objPatientEmrAuditFB_p.getPatCrNo());
			
			//setSysdate(objRequest_p);
			PatientEmrAuditVO  patientEmrAuditVO = new PatientEmrAuditVO();
//			String sys = (String) objRequest_p.getSession().getAttribute(Config.SYSDATE);
			patientEmrAuditVO.setStrEmrAuditTypeId(objPatientEmrAuditFB_p.getStrEmrAuditTypeId());
			patientEmrAuditVO.setStrEmrAuditUserId(objPatientEmrAuditFB_p.getStrEmrAuditUserId());
			patientEmrAuditVO.setPatCrNo(objPatientEmrAuditFB_p.getPatCrNo());
			patientEmrAuditVO.setStrFromDate(objPatientEmrAuditFB_p.getStrFromDate());
			patientEmrAuditVO.setStrToDate(objPatientEmrAuditFB_p.getStrToDate());
			patientEmrAuditVO.setStrLitingType(objPatientEmrAuditFB_p.getStrLitingType());
			patientEmrAuditVO.setStrEmrAuditUserId(objPatientEmrAuditFB_p.getStrEmrAuditUserId());
			
			List<PatientEmrAuditVO> lstPatientEmrAuditVO = (List<PatientEmrAuditVO>)PatientEmrAuditDATA.getPatientEmrAuditDtlByCrNo(patientEmrAuditVO,getUserVO(objRequest_p));
			WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST, lstPatientEmrAuditVO);
			objPatientEmrAuditFB_p.setCurrentPage(1);
			//objStatus.add(Status.INPROCESS);
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
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		//return flag;
		
	}
	

	/**
	 * Show Patient Emr Audit Diagnosis Tile By PrimaryKey
	 * @param objPatientEmrAuditFB_p
	 * @param objRequest_p
	 * Added By Adil Wasi  on 06-Jun-2012
	 */
	public static void showPatientEmrAuditDiagnosisDialTileByPrimaryKey(PatientEmrAuditFB objPatientEmrAuditFB_p, HttpServletRequest objRequest_p) 
	{	
		Status objStatus = new Status();
		try
		{
			//setSysdate(objRequest_p);
			PatientEmrAuditVO  patientEmrAuditVO=null;
//			String sys = (String) objRequest_p.getSession().getAttribute(Config.SYSDATE);
			
			List<PatientEmrAuditVO> lstPatientEmrAuditVO= (List<PatientEmrAuditVO>)WebUTIL.getSession(objRequest_p).getAttribute(MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST);
			if(lstPatientEmrAuditVO!=null && lstPatientEmrAuditVO.size()>0){
				patientEmrAuditVO=(PatientEmrAuditVO)lstPatientEmrAuditVO.get(Integer.parseInt(objPatientEmrAuditFB_p.getStrPrimaryKeyIndex()));
				objPatientEmrAuditFB_p.setPatCrNo("");
				HelperMethods.populatetToNullOrEmpty(objPatientEmrAuditFB_p, patientEmrAuditVO);
				objRequest_p.getSession().setAttribute("patCrNo", patientEmrAuditVO.getPatCrNo());
			}else{
				patientEmrAuditVO=new PatientEmrAuditVO();
				if(objPatientEmrAuditFB_p.getPatCrNo()!=null)
					objRequest_p.getSession().setAttribute("patCrNo", objPatientEmrAuditFB_p.getPatCrNo());
			}
			
			patientEmrAuditVO.setStrEmrAuditTypeId(objPatientEmrAuditFB_p.getStrEmrAuditTypeId());
			patientEmrAuditVO.setStrEmrAuditUserId(objPatientEmrAuditFB_p.getStrEmrAuditUserId());
			
			patientEmrAuditVO.setStrFromDate(objPatientEmrAuditFB_p.getStrFromDate());
			patientEmrAuditVO.setStrToDate(objPatientEmrAuditFB_p.getStrToDate());
			//patientEmrAuditVO.setStrPrimaryKey(objPatientEmrAuditFB_p.getStrPrimaryKey());
			
			List<List<String>> lstOfLstPatientEmrAudit = (List<List<String>>)PatientEmrAuditDATA.showPatientEmrAuditDiagnosisDialTileByPrimaryKey(patientEmrAuditVO,getUserVO(objRequest_p));
			WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_DIAL_TILE_BY_PRIMARY_KEY_LIST, lstOfLstPatientEmrAudit);
			
			List<PatientEmrAuditVO> lstPatientEmrAuditVO_anotherObj = (List<PatientEmrAuditVO>)PatientEmrAuditDATA.getPreviousPatientEmrAuditDtlByPrimaryKey(patientEmrAuditVO,getUserVO(objRequest_p));
			WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.PATIENT_EMR_AUDIT_PREVIOUS_DTL_BY_PRIMARY_KEY_LIST, lstPatientEmrAuditVO_anotherObj);
			
			objStatus.add(Status.LIST);
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
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		
	}

	/**
	 * Save Patient Emr Audit Detail
	 * @param objPatientEmrAuditFB_p
	 * @param objRequest_p
	 * Added By Adil Wasi  on 06-Jun-2012
	 */
	public static boolean savePatientEmrAuditDtl(PatientEmrAuditFB objPatientEmrAuditFB_p, HttpServletRequest objRequest_p)
	{
		boolean flgSave = true;
		Status objStatus = new Status();
		try
		{
			//setSysdate(objRequest_p);
			PatientEmrAuditVO  patientEmrAuditVO=new PatientEmrAuditVO();
			
			HelperMethods.populate(patientEmrAuditVO, objPatientEmrAuditFB_p);
			
			boolean boolPatientEmrAudit = PatientEmrAuditDATA.savePatientEmrAuditDtl(patientEmrAuditVO,getUserVO(objRequest_p));
			//WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_DIAL_TILE_BY_PRIMARY_KEY_LIST, lstOfLstPatientEmrAudit);
			if(boolPatientEmrAudit){
				objStatus.add(Status.LIST);
			}else{
				objStatus.add(Status.UNSUCESSFULL,"Record Not Saved","");
				flgSave = false;
			}
			
		}
		catch (HisRecordNotFoundException e)
		{
			flgSave =false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");

		}
		catch (HisDataAccessException e)
		{
			flgSave =false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");

		}
		catch (HisApplicationExecutionException e)
		{
			flgSave =false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			flgSave =false;
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
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		return flgSave;
	}
}
