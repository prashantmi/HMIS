package registration.transactions.controller.util;

/*
 * @ author Aadil
 * Created at 07-Apr-2014
 */

import java.util.ArrayList;
import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.MlcToNonMlcNewSUP;
import registration.transactions.controller.actionsupport.PatientSearchSUP;
import registration.transactions.controller.data.MLCtoNonMLCDATA;
import registration.transactions.controller.data.PatientDetailDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.registration.EpisodeVO;
import vo.registration.MlcToNonMlcVO;
import vo.registration.PatientVO;

public class MlcToNonMlcNewUTIL extends ControllerUTIL
{
	public static void setPatientDtlByCrno(MlcToNonMlcNewSUP objMlcToNonMlcSUP_p, HttpServletRequest objRequest_p)
	{
		HttpSession ses = WebUTIL.getSession(objRequest_p);
		PatientVO objPatientVO = new PatientVO();
		System.out.println("MlcToNonMlcUTIL :: setPatientDtlByCrno()");
		try
		{			
			UserVO objUserVO = getUserVO(objRequest_p);
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			
			objPatientVO.setPatCrNo(objMlcToNonMlcSUP_p.getPatCrNo());
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, "");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);

			EpisodeVO[] arrOpenEpisodeVO = MLCtoNonMLCDATA.getAllProvisionalMLCEpisodes(objMlcToNonMlcSUP_p.getPatCrNo(), objUserVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ARR_OPEN_PROVISIONAL_MLC_EPISODE_VO, arrOpenEpisodeVO);
			objMlcToNonMlcSUP_p.setAfterGo("1");
		}
		catch(HisRecordNotFoundException e){
			objMlcToNonMlcSUP_p.setStrErrorMessage(e.getMessage());
	   		if(e.getMessage()!=null && e.getMessage().trim().equals("Patient Details Not Found")){
	   			objMlcToNonMlcSUP_p.setAfterGo("0");
	   			if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   			{
	   				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   			}
	   		}
	   		//throw new HisRecordNotFoundException("");
		}
		catch (HisException e)
		{
			objMlcToNonMlcSUP_p.setStrErrorMessage(e.getMessage());
			if(e.getMessage().equals("No open episode found"))
				objMlcToNonMlcSUP_p.setStrErrorMessage("No MLC episodes found.");
		}
		catch (Exception e)
		{
			objMlcToNonMlcSUP_p.setStrErrorMessage("Error, Contact System Administrator");
		}
		finally
		{
			System.out.println(" in finally");
		}
	}

	public static boolean revokeMLCEpisodes(MlcToNonMlcNewSUP objMlcToNonMlcSUP_p, HttpServletRequest objRequest_p)
	{
		boolean flagSave=false;
		try
		{
			System.out.println("MlcToNonMlcUTIL :: revokeMLCEpisodes()");
			UserVO objUserVO = getUserVO(objRequest_p);
			setSysdate(objRequest_p);
			
			int len = objMlcToNonMlcSUP_p.getSelEpisodeCode().length;
			MlcToNonMlcVO[] mlcToNonMlcEpiVOs = new MlcToNonMlcVO[len];
			
			for(int i=0;i<len;i++)
			{
				MlcToNonMlcVO objMlcToNonMlcVO = new MlcToNonMlcVO();
				objMlcToNonMlcVO.setPatCrNo(objMlcToNonMlcSUP_p.getPatCrNo());
				objMlcToNonMlcVO.setEpisodeCode(objMlcToNonMlcSUP_p.getSelEpisodeCode()[i]);
				objMlcToNonMlcVO.setEpisodeVisitNo(objMlcToNonMlcSUP_p.getSelEpisodeVisitNo()[i]);
				if(objMlcToNonMlcSUP_p.getSelMlcNo()[i].equalsIgnoreCase("Yet To Be Alloted"))
					objMlcToNonMlcVO.setMlcNo(null);
				else
					objMlcToNonMlcVO.setMlcNo(objMlcToNonMlcSUP_p.getSelMlcNo()[i]);
				objMlcToNonMlcVO.setRemarks(objMlcToNonMlcSUP_p.getSelRemarks()[i]);
				mlcToNonMlcEpiVOs[i] = objMlcToNonMlcVO;
			}
			
			flagSave=MLCtoNonMLCDATA.saveMlcToNonMlcEpisodes(mlcToNonMlcEpiVOs, objUserVO);
			if(flagSave)
				objMlcToNonMlcSUP_p.setStrNormalMsg("MLC Episode of Patient "+objMlcToNonMlcSUP_p.getPatCrNo() + " is revoked Successfully");
		}

		catch (HisUpdateUnsuccesfullException e){
			objMlcToNonMlcSUP_p.setAfterGo("0");
			objMlcToNonMlcSUP_p.setStrErrorMessage("Update Failed");
		}
		catch (HisDataAccessException e){
			objMlcToNonMlcSUP_p.setAfterGo("0");
			objMlcToNonMlcSUP_p.setStrErrorMessage("Record Not Found");
		}
		catch (HisException e){
			objMlcToNonMlcSUP_p.setAfterGo("0");
			objMlcToNonMlcSUP_p.setStrErrorMessage("Transaction Failed");
		}
		catch (Exception e){
			objMlcToNonMlcSUP_p.setAfterGo("0");
			objMlcToNonMlcSUP_p.setStrErrorMessage("Update Failed");
			e.printStackTrace();
		}
		finally{
			System.out.println("flagSave :"+flagSave);
			return flagSave;
		}
	}
	
	
}
