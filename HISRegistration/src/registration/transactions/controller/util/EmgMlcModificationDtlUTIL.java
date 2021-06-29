package registration.transactions.controller.util;

import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.EmgMlcModificationDtlSUP;
import registration.transactions.controller.data.EmgMlcModificationDtlDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.registration.EpisodeVO;
import vo.registration.MlcVO;
import vo.registration.MlcParameterDtlVO;
import vo.registration.PatientVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PoliceVerificationDtlVO;

public class EmgMlcModificationDtlUTIL extends ControllerUTIL{
	
	public static void getMlcEssentialDetails( EmgMlcModificationDtlSUP objEmgMlcModDtlSUP_p,HttpServletRequest objRequest_p )
	{		
		List<MlcVO> lstMlcVO = new ArrayList<MlcVO>()  ;
		PatientVO objPatientVO=new PatientVO();;
		Map map = null;
		try{
			System.out.println("EmgMlcModificationDtlUTIL :: getMlcEssentialDetails()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			objPatientVO.setPatCrNo(objEmgMlcModDtlSUP_p.getPatCrNo());
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, "");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			
			String sys=(String)objRequest_p.getSession().getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			objEmgMlcModDtlSUP_p.setHiddenTimeHr(time.split(":")[0]);
			objEmgMlcModDtlSUP_p.setHiddenTimeMin(time.split(":")[1]);
			
			MlcVO objMlcVO = new MlcVO();
			objMlcVO.setPatCrNo(objEmgMlcModDtlSUP_p.getPatCrNo());
			
			map=EmgMlcModificationDtlDATA.getMlcDetailEssentials(objMlcVO, objUserVO, "2");
			WebUTIL.setMapInSession(map, objRequest_p,"EmgMlcModificationDtlACTION");
			
			
		}catch(HisRecordNotFoundException e){
	   		objEmgMlcModDtlSUP_p.setErrorMessage(e.getMessage());
	   		if(e.getMessage()!=null && e.getMessage().trim().equals("Patient Details Not Found")){
	   			objEmgMlcModDtlSUP_p.setAfterGo("0");
	   			if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   			{
	   				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   			}
	   			
	   		}
	   		//throw new HisRecordNotFoundException("");
		}
		catch(HisException e){
			objEmgMlcModDtlSUP_p.setErrorMessage("Error, Contact System Administrator.");
			objEmgMlcModDtlSUP_p.setAfterGo("0");
		}
		catch(Exception e){
			objEmgMlcModDtlSUP_p.setErrorMessage("Error, Contact System Administrator.");
			objEmgMlcModDtlSUP_p.setAfterGo("0");
		}				
	}
	
	public static void getMlcDetails(EmgMlcModificationDtlSUP objEmgMlcModDtlSUP_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
	{
		String strMlcDtlJsonObj="";
		MlcVO objMlcVO = new MlcVO();
		
		
		try{
			System.out.println("EmgMlcModificationDtlUTIL :: getMlcDetails()");
			UserVO objUserVO =getUserVO(objRequest_p);	
			
			objMlcVO.setPatCrNo(objEmgMlcModDtlSUP_p.getPatCrNo());
			objMlcVO.setPatMlcNo(objEmgMlcModDtlSUP_p.getPatMlcNo());
			objMlcVO.setEpisodeCode(objEmgMlcModDtlSUP_p.getEpisodeCode());
			objMlcVO.setEpisodeVisitNo(objEmgMlcModDtlSUP_p.getEpisodeVisitNo());
			
			objMlcVO=EmgMlcModificationDtlDATA.getMlcDetails(objMlcVO, objUserVO, "3");
			objMlcVO.setFitnessStatus(objMlcVO.getIsFit());
			
			strMlcDtlJsonObj=HelperMethods.createJSONObjectString(objMlcVO);
			System.out.println("strMlcDtlJsonObj :"+strMlcDtlJsonObj);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			WebUTIL.writeResponse(objResponse_p, strMlcDtlJsonObj, "text/html");
		}
	}
	
	public static void getMlcParameterDetails(EmgMlcModificationDtlSUP objEmgMlcModDtlSUP_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
	{
		StringBuilder strMlcParameterDtlJsonObj=new StringBuilder("[");
		MlcParameterDtlVO objMlcParameterDtlVO = new MlcParameterDtlVO();
		
		
		try{
			System.out.println("EmgMlcModificationDtlUTIL :: getMlcParameterDetails()");
			UserVO objUserVO =getUserVO(objRequest_p);	
			objMlcParameterDtlVO.setPatCrNo(objEmgMlcModDtlSUP_p.getPatCrNo());
			objMlcParameterDtlVO.setMlcNo(objEmgMlcModDtlSUP_p.getPatMlcNo());
			objMlcParameterDtlVO.setEpisodeCode(objEmgMlcModDtlSUP_p.getEpisodeCode());
			objMlcParameterDtlVO.setEpisodeVisitNo(objEmgMlcModDtlSUP_p.getEpisodeVisitNo());
			
			List<MlcParameterDtlVO> lstMlcParameterDtlVO =EmgMlcModificationDtlDATA.getMlcParameterDtlList(objMlcParameterDtlVO, objUserVO);
			if(lstMlcParameterDtlVO!=null && lstMlcParameterDtlVO.size()>0){
				for(int i=0; i<lstMlcParameterDtlVO.size(); i++){
					if(i==0)
						strMlcParameterDtlJsonObj.append(HelperMethods.createJSONObjectString(lstMlcParameterDtlVO.get(i)));
					else
						strMlcParameterDtlJsonObj.append(",").append(HelperMethods.createJSONObjectString(lstMlcParameterDtlVO.get(i)));
				}
			}
			System.out.println("strMlcParameterDtlJsonObj :"+strMlcParameterDtlJsonObj.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			strMlcParameterDtlJsonObj.append("]");
			WebUTIL.writeResponse(objResponse_p, strMlcParameterDtlJsonObj.toString(), "text/html");
		}
	}
	
	public static void getBroughtByDtl(EmgMlcModificationDtlSUP objEmgMlcModDtlSUP_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
	{
		String strBroughtByJsonObj="";
		PatientBroughtByDtlVO objPatientBroughtByDtlVO = new PatientBroughtByDtlVO();
		objPatientBroughtByDtlVO.setPatCrNo(objEmgMlcModDtlSUP_p.getPatCrNo());
		objPatientBroughtByDtlVO.setEpisodeCode(objEmgMlcModDtlSUP_p.getEpisodeCode());
		objPatientBroughtByDtlVO.setEpisodeVisitNo(objEmgMlcModDtlSUP_p.getEpisodeVisitNo());
		try{
			System.out.println("EmgMlcModificationDtlUTIL :: getBroughtByDtl()");
			UserVO objUserVO =getUserVO(objRequest_p);	
			objPatientBroughtByDtlVO= EmgMlcModificationDtlDATA.getPatBroughtByDtlsEpisodeWise(objPatientBroughtByDtlVO, objUserVO);
			strBroughtByJsonObj=HelperMethods.createJSONObjectString(objPatientBroughtByDtlVO);
			System.out.println("strBroughtByJsonObj :"+strBroughtByJsonObj);
		}catch(HisRecordNotFoundException e){
			strBroughtByJsonObj="{}";
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			WebUTIL.writeResponse(objResponse_p, strBroughtByJsonObj, "text/html");
		}
	}
	
	public static String saveMlcDetails(EmgMlcModificationDtlSUP objEmgMlcModDtlSUP_p, HttpServletRequest objRrequest_p)
	{
		UserVO objUserVO=getUserVO(objRrequest_p);
		List<MlcParameterDtlVO> lstMlcParameterDtlVO= null;
		String flagSaveSccessfull="0";
		try
		{
			HttpSession session = WebUTIL.getSession(objRrequest_p);
			System.out.println("EmgMlcModificationDtlUTIL :: saveMlcDetails()");
			// Creating MLC VO			
			MlcVO objMlcVO = new MlcVO();
				
			HelperMethods.populate(objMlcVO, objEmgMlcModDtlSUP_p);	
			objMlcVO.setMlcTime(objEmgMlcModDtlSUP_p.getMlcTimeHr()+":"+objEmgMlcModDtlSUP_p.getMlcTimeMin());
			
			PatientVO objPatientVO = (PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);			
			HelperMethods.populatetToNullOrEmpty(objMlcVO, objPatientVO);
			
			
			objMlcVO.setPatMlcNo(objEmgMlcModDtlSUP_p.getPatMlcNo());
			objMlcVO.setEpisodeCode(objEmgMlcModDtlSUP_p.getEpisodeCode());
			objMlcVO.setEpisodeVisitNo(objEmgMlcModDtlSUP_p.getEpisodeVisitNo());
			objMlcVO.setSystemIPAddress(objRrequest_p.getRemoteAddr());

			objMlcVO.setCmoCode(objUserVO.getUserEmpID());
			objMlcVO.setIsBroughtDead(objEmgMlcModDtlSUP_p.getIsBroughtDead());
			
			
			
			if(objEmgMlcModDtlSUP_p.getIsBroughtByPolice()==null)
				objMlcVO.setMlcType("0");
			else
				objMlcVO.setMlcType("1");
			
			// Setting MLC Parameter Detail VO
			if(objEmgMlcModDtlSUP_p.getMlcTypeCode()!=null && objEmgMlcModDtlSUP_p.getMlcTypeCode().length>0){
				lstMlcParameterDtlVO= new ArrayList<MlcParameterDtlVO>();
				for(int i=0; i<objEmgMlcModDtlSUP_p.getMlcTypeCode().length; i++){
					MlcParameterDtlVO voMlcParamDtl = new MlcParameterDtlVO();
					populateMlcParameterDtlVO(objEmgMlcModDtlSUP_p, voMlcParamDtl, i);
					lstMlcParameterDtlVO.add(voMlcParamDtl);
				}
			}
			// Creating Patient Brought By Detail VO
			PatientBroughtByDtlVO objPatBroughtByVO=new PatientBroughtByDtlVO();
			if("1".equals(objEmgMlcModDtlSUP_p.getIsBroughtBy()))
				HelperMethods.populate(objPatBroughtByVO, objEmgMlcModDtlSUP_p);
			
			
			PoliceVerificationDtlVO objPoliceVerDtlVO=new PoliceVerificationDtlVO();
			HelperMethods.populate(objPoliceVerDtlVO,objEmgMlcModDtlSUP_p);
			
			objMlcVO=EmgMlcModificationDtlDATA.saveMlcDetails(objMlcVO,lstMlcParameterDtlVO, objUserVO, objPatBroughtByVO,objPoliceVerDtlVO);	
			objEmgMlcModDtlSUP_p.setIsDuplicateMlcNo(RegistrationConfig.MLC_NO_IS_DUPLICATE_NO);
			/*  ## 		Modification Log							
	 		##		Modify Date				:15thDec'14 
	 		##		Reason	(CR/PRS)		:Bug fix 7733
	 		##		Modify By				:Sheeldarshi 
			objEmgMlcModDtlSUP_p.setStrNormalMsg("MLC Details Updated Succesfully for,<br/>CR No :"+objEmgMlcModDtlSUP_p.getPatCrNo() + " and <br/>MLC No :"+objEmgMlcModDtlSUP_p.getPatMlcNo());*/
			objEmgMlcModDtlSUP_p.setStrNormalMsg("MLC Details Updated Succesfully for, CR No :"+objEmgMlcModDtlSUP_p.getPatCrNo() + " and MLC No :"+objEmgMlcModDtlSUP_p.getPatMlcNo());
			//End:Sheeldarshi
			flagSaveSccessfull= "1";
			objRrequest_p.getSession().removeAttribute("keyPatientVO");
	
		}

		catch (HisDuplicateRecordException e) 
		{
			/*objEmgMlcModDtlSUP_p.setIsDuplicateMlcNo(RegistrationConfig.MLC_NO_IS_DUPLICATE_YES);
			flagSaveSccessfull= "2";*/
			//objEmgMlcModDtlSUP_p.setMlcNo("");
			objEmgMlcModDtlSUP_p.setStrWarningMsg(e.getMessage());
			e.printStackTrace();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			objEmgMlcModDtlSUP_p.setErrorMessage("");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objEmgMlcModDtlSUP_p.setErrorMessage("Error, Contact System Administrator");
			e.printStackTrace();
		}
		finally
		{
			return flagSaveSccessfull;
		}
	}
	
	public static void populateMlcParameterDtlVO(EmgMlcModificationDtlSUP objEmgMlcModDtlSUP_p, MlcParameterDtlVO objMlcParamDtlVO_p, int index)
	{
		objMlcParamDtlVO_p.setPatCrNo(objEmgMlcModDtlSUP_p.getPatCrNo());
		objMlcParamDtlVO_p.setMlcNo(objEmgMlcModDtlSUP_p.getPatMlcNo());
		objMlcParamDtlVO_p.setEpisodeCode(objEmgMlcModDtlSUP_p.getEpisodeCode());
		objMlcParamDtlVO_p.setEpisodeVisitNo(objEmgMlcModDtlSUP_p.getEpisodeVisitNo());
		//objMlcParamDtlVO_p.setSerialNo(objEmgMlcModDtlSUP_p.);
		
		objMlcParamDtlVO_p.setMlcTypeCode(objEmgMlcModDtlSUP_p.getMlcTypeCode()[index]);
		objMlcParamDtlVO_p.setInjurySide(objEmgMlcModDtlSUP_p.getInjurySide()[index]);
		objMlcParamDtlVO_p.setInjuryNatureCode(objEmgMlcModDtlSUP_p.getInjuryNatureCode()[index]);
		objMlcParamDtlVO_p.setTypeOfWeapon(objEmgMlcModDtlSUP_p.getTypeOfWeapon()[index]);
		objMlcParamDtlVO_p.setInjurySize(objEmgMlcModDtlSUP_p.getInjurySize()[index]);
		objMlcParamDtlVO_p.setInjuryDepth(objEmgMlcModDtlSUP_p.getInjuryDepth()[index]);
		objMlcParamDtlVO_p.setBurnPercentage(objEmgMlcModDtlSUP_p.getBurnPercentage()[index]);
		objMlcParamDtlVO_p.setPoisonRemarks(objEmgMlcModDtlSUP_p.getPoisonRemarks()[index]);
		objMlcParamDtlVO_p.setMlcParamSerialNo(objEmgMlcModDtlSUP_p.getMlcParamSerialNo()[index]);
	}
}
