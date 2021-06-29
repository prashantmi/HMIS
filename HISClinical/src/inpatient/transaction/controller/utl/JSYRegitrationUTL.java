package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.HealthWorkerDetailVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.JsyRuleMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.JSYRegitrationDATA;
import inpatient.transaction.controller.fb.JSYRegitrationFB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.controller.fb.CRNoFB;

public class JSYRegitrationUTL extends ControllerUTIL{

	public static void getPatDetailForJSYRegistration(JSYRegitrationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		String patCrNo="";
		ANCDetailVO ancdetailVO =new ANCDetailVO();
		PatientDetailVO pDetailVO=new PatientDetailVO();
		JsyRuleMasterVO jRuleMasterVO=new JsyRuleMasterVO(); 
		HealthWorkerDetailVO hDetailVO=null;
		List casteList=new ArrayList();
		try
		{    
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			patCrNo=_fb.getPatCrNo();

			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _request);
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			populateBeanFromVO(_fb, patDtlVO); // values from patient Detail

			essentialMap=JSYRegitrationDATA.getPatDetailForJSYRegistration(patCrNo,userVO);
			    
			  ancdetailVO=(ANCDetailVO)essentialMap.get(InpatientConfig.PAT_ANC_DETAIL_FOR_JSY_REGISTRATION);
			  pDetailVO=(PatientDetailVO)essentialMap.get(InpatientConfig.PAT_DETAIL_FOR_JSY_REGISTRATION);
			  jRuleMasterVO=(JsyRuleMasterVO)essentialMap.get(InpatientConfig.JSY_RULE_DETAIL);	
			  hDetailVO=(HealthWorkerDetailVO)essentialMap.get(InpatientConfig.HEALTHWORKER_DETAIL);
			  
			  if(ancdetailVO.getJsyFlag().equals(InpatientConfig.JSY_FLAG_YES))
			    {
				  throw new HisRecordNotFoundException("JSY Registration Already Added");
			    }
			  
			  // values from Anc Detail
			  _fb.setGravidaNo(ancdetailVO.getGravidaNo());
			  _fb.setAncNo(ancdetailVO.getAncNo());
			  _fb.setExpectedDeliveryDate(ancdetailVO.getExpectedDeliveryDate());
			  _fb.setLiveBirthCount(ancdetailVO.getLiveBirthCount());
			  _fb.setEpisodeCode(ancdetailVO.getEpisodeCode());
			  _fb.setVisitNo(ancdetailVO.getEpisodeVisitNo());
			  _fb.setGestationPeriod(ancdetailVO.getGestationPeriod());
			  _fb.setGestationPeriodDays(ancdetailVO.getGestationPeriodDays());
			  
			  // values from Patient Detail
			  _fb.setAreaType(setNullTOEmpty(pDetailVO.getPatIsUrban()));
			  
			     if(_fb.getAreaType().equals(InpatientConfig.AREA_TYPE_URBAN_VALUE))
			     { _fb.setPatIsUrban(InpatientConfig.AREA_TYPE_URBAN_LABLE);}
			     if(_fb.getAreaType().equals(InpatientConfig.AREA_TYPE_SEMIURBAN_VALUE))
			     { _fb.setPatIsUrban(InpatientConfig.AREA_TYPE_SEMIURBAN_LABLE);}
			     if(_fb.getAreaType().equals(InpatientConfig.AREA_TYPE_RURAL_VALUE))
			     { _fb.setPatIsUrban(InpatientConfig.AREA_TYPE_RURAL_LABLE);}
			  
			  _fb.setPatCaste(setNullTOEmpty(pDetailVO.getPatCaste()));
			  _fb.setPatCasteName(pDetailVO.getPatCasteName());
			  
			     // values from jsy rule Master
			  _fb.setPatMinAgeJsyRule(jRuleMasterVO.getMinimumAge());
			  _fb.setPatMaxAgeJsyRule(jRuleMasterVO.getMaximumAge());
			  _fb.setPatPrimaryCatJsyRule(jRuleMasterVO.getPrimaryCategoryCode());
			  _fb.setLiveBirthJsyRule(jRuleMasterVO.getLiveBirthUpto());
			  _fb.setCastIdjsyRule(jRuleMasterVO.getCasteId());
			  _fb.setIsCasteBound(jRuleMasterVO.getIsCasteBound());
			  _fb.setRegistrationUpto(jRuleMasterVO.getRegistrationUpto());
			  _fb.setPrimaryCategoryName(jRuleMasterVO.getPrimaryCategoryName());
			  _fb.setTreatmentCatValidity(jRuleMasterVO.getTreatmentCatValidity());
			  
			  WebUTIL.setMapInSession(essentialMap, _request);
			  
			    
			  casteList=(List)essentialMap.get(InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST);
			  
			          String []caste=jRuleMasterVO.getCasteId().split("#");
			          String casteID="";
			         
			        for(int i=0;i<caste.length;i++)
			        {
			        	for(int j=0;j<casteList.size();j++)
			        	{
			        		Entry entry=(Entry)casteList.get(j);
			        		if(caste[i].equals(entry.getValue()))
			        		{
			        			if(casteID.equals(""))
			        				casteID=entry.getLabel();
			        			else
			        				casteID=casteID+","+entry.getLabel();
			        		}
			        	}
			        }
			          
			          _fb.setCasteNameJsyRule(casteID);
			  
			
			if(hDetailVO!=null){
				_fb.setBroughtByASHA("1");
			   	_fb.setHealthWorkerID(hDetailVO.getHealthWorkerID());
				_fb.setHealthWorkerName(hDetailVO.getHealthWorkerName());
				_fb.setHealthWorkerCardNo(hDetailVO.getHealthWorkerCardNo());
				_fb.setHealthWorkerAddress(hDetailVO.getHealthWorkerAddress());
			 }	
				
	/*	if(!patDtlVO.getPatGenderType().equals(Config.GENDER_TYPE_FEMALE))

			        throw new HisRecordNotFoundException("Not Eligible for ANC (Unsuitable Gender)");


			    // Check ANC Eligiblility Age

			Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);

			Date dob = WebUTIL.getDateFromString(patDtlVO.getPatDOB(), "dd-MMM-yyyy");

			long diff = sysdate.getTime() - dob.getTime();

			int age = (int)(diff / ((long)1000 * 60 * 60 * 24 * 365));

			if( age < Integer.parseInt(Config.ANC_DETAIL_MINIMUN_AGE_FOR_ANC_ELIGIBILITY) )
			        throw new HisRecordNotFoundException("Not Eligible for ANC (Unsuitable Age)");
            */
				
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	
	public static void populateFBFromVO(JSYRegitrationFB _fb, PatientDetailVO _vo)
	{
		_fb.setPatAge(_vo.getPatAge().substring(0,2));
		_fb.setPatPrimaryCategory(_vo.getPatPrimaryCatCode());
		//_fb.setPatCaste(_vo.getPatCaste());
	}
	
	private static void populateBeanFromVO(JSYRegitrationFB _fb, PatientDetailVO _vo)
	{
		_fb.setPatAge(_vo.getPatAge().substring(0,2));
		_fb.setPatPrimaryCategory(_vo.getPatPrimaryCatCode());
		_fb.setPatSecondaryCatCode(_vo.getPatSecondaryCatCode());
	}
	
	
	public static boolean saveJSYDetail(JSYRegitrationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		String jsyCategoryCode="";
		HttpSession session=WebUTIL.getSession(_request);
		SecondaryCategoryChangeVO sChangeVO=new SecondaryCategoryChangeVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			  CRNoFB crnoFB = new CRNoFB();
				crnoFB.setPatCrNo(_fb.getPatCrNo());
				InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _request);
				PatientDetailVO patDtlVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				populateBeanFromVO(_fb, patDtlVO); // values from patient Detail

				// jsy  Detail
			JSYRegitrationVO jRegitrationVO= new JSYRegitrationVO();
			PatientDetailVO pDetailVO=new PatientDetailVO();
		
			pDetailVO.setPatCrNo(_fb.getPatCrNo());
			if(_fb.getPatCasteText()==null){ _fb.setPatCasteText("");  }
			if(_fb.getAreaTypeId()==null){ _fb.setAreaTypeId("");  }
			
			if(_fb.getPatCaste().equals("-1") || _fb.getPatCaste().equals(""))
				pDetailVO.setPatCaste(_fb.getPatCaste()); 
			else
			pDetailVO.setPatCaste(_fb.getPatCasteText()); 
			
			if(_fb.getAreaTypeId().equals("-1") || _fb.getAreaTypeId().equals(""))
				pDetailVO.setPatIsUrban(_fb.getAreaType());
			else
			  pDetailVO.setPatIsUrban(_fb.getAreaTypeId());  
			  
			populateJsyAddInfoFromFB(jRegitrationVO,_fb);
			
			jsyCategoryCode=_fb.getJsyCategoryCode();
			
			
			
				
			//String validUpTo=_fb.getExpectedDeliveryDate();
			
				
		     HelperMethods.populate(sChangeVO,_fb);
		     
		     String date=_fb.getExpectedDeliveryDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(date));
				int i=Integer.parseInt(_fb.getTreatmentCatValidity());
				c.add(Calendar.DATE, i);		//Getting The Previous Date of Fitness Date	
				String expDate = sdf.format(c.getTime()); 
		     
		     sChangeVO.setExpiryDate(expDate); // expactedDeliveryDate+treatmentCatValidity
		     sChangeVO.setPatPrimaryCatCode(_fb.getPatPrimaryCategory());
		     sChangeVO.setPatPrevSecondaryCatCode(_fb.getPatSecondaryCatCode());
		     sChangeVO.setPatNewSecondaryCatCode(jsyCategoryCode);
		     
		     
			JSYRegitrationDATA.saveJSYDetail(jRegitrationVO,pDetailVO,jsyCategoryCode,sChangeVO,userVO);
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.DONE,"","Jsy Detail Save Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{

			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}
	
	
	private static void populateJsyAddInfoFromFB(JSYRegitrationVO _vo,JSYRegitrationFB _fb)
	{
		_vo.setPatCrNo(_fb.getPatCrNo());
	    _vo.setAncNo(_fb.getAncNo());
	    _vo.setGravidaNo(_fb.getGravidaNo());
	    _vo.setEpisodeCode(_fb.getEpisodeCode());
	    _vo.setRegistrationAge(_fb.getPatAge());
	    _vo.setVisitNo(_fb.getVisitNo());
	    _vo.setLiveBirthCount(_fb.getLiveBirthCount());
	   
	    if(_fb.getAreaTypeId()==null){
	    _vo.setAreaType(_fb.getAreaType());}
	    else{ _vo.setAreaType(_fb.getAreaTypeId());  }
	    
	    _vo.setBroughtByASHA(_fb.getBroughtByASHA());
	    _vo.setHealthWorkerID(_fb.getHealthWorkerID());
	    _vo.setHealthWorkerName(_fb.getHealthWorkerName());
	    _vo.setHealthWorkerCardNo(_fb.getHealthWorkerCardNo());
	    _vo.setHealthWorkerAddress(_fb.getHealthWorkerAddress());
	}
	
	
	
	
	
	
	
	
	
	

	public static String setNullTOEmpty(String _object)
	{
		if (_object==null)
		{
			_object="";
		}
		return _object;
	}
	
}
