
package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.RegistrationConfig;
import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalEnquiryFB;
import enquiry.vo.HospitalRegistrationTimingsVO;

public class HospitalEnquiryUTIL extends ControllerUTIL 
{
	public static void getHospitalEssentials(HospitalEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		UserVO objUserVO= null;
		try{
			setSysdate(_rq);
			objUserVO = getUserVO(_rq);
			_fb.setHospitalCode(objUserVO.getHospitalCode());
			
			List hospitalList=HospitalEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
			essentialMap=HospitalEnquiryDATA.getHospitalEssentials(objUserVO);
			setHospitalEssentials(_fb, _rq, essentialMap);
		}
		catch(HisRecordNotFoundException e){
			
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
			WebUTIL.setStatus(_rq, objStatus);
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}
	
	public static void setHospitalEssentialsOnHospitalComboChange(HospitalEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		UserVO objUserVO= null;
		try{
			//setSysdate(_rq);
			objUserVO = getUserVO(_rq);
			//objUserVO.setHospitalCode(_fb.getHospitalCode());
		    
			essentialMap=HospitalEnquiryDATA.getHospitalEssentials(objUserVO);
			setHospitalEssentials(_fb, _rq, essentialMap);
		}
		catch(HisRecordNotFoundException e){
			
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
			WebUTIL.setStatus(_rq, objStatus);
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}
	public static void setHospitalEssentials(HospitalEnquiryFB _fb,HttpServletRequest _rq, Map essentialMap){
		Status objStatus=new Status();
		HospitalMstVO _hospitalVO=new HospitalMstVO(); 
 		HospitalRegistrationTimingsVO[] _hospitalTimingsVO=null;
 		
 		//Map essentialMap=new HashMap();
 		
 		Map regCategory =new LinkedHashMap(); 		
 		
 		
 		
 		try{
			//setSysdate(_rq);
			//essentialMap=HospitalEnquiryDATA.getHospitalEssentials(getUserVO(_rq));
			
			_hospitalVO=(HospitalMstVO)essentialMap.get(enquiryConfig.VO_OF_HOSPITAL_MST);
			
			
			_hospitalVO.setWeekdayTimings(_hospitalVO.getWeekdayTimings().replace("#", " To "));
			_hospitalVO.setSaturdayTimings(_hospitalVO.getSaturdayTimings().replace("#", " To "));
			_hospitalVO.setLunchTimings(_hospitalVO.getLunchTimings().replace("#", " To "));
			
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.VO_OF_HOSPITAL_MST, _hospitalVO);
			
			_hospitalTimingsVO=(HospitalRegistrationTimingsVO[])essentialMap.get(enquiryConfig.VO_ARRAY_OF_REGISTRATION_TIMINGS);
		
		
			if(_hospitalTimingsVO!=null && _hospitalTimingsVO.length>0)
			{
				for(int i=0; i < _hospitalTimingsVO.length ; i++)
				{
					Map regSeason =new LinkedHashMap();
					Map regShift =new LinkedHashMap();
				
					if(regCategory.containsKey(_hospitalTimingsVO[i].getRegCategoryCode()))
					{
						regSeason=(Map)regCategory.get(_hospitalTimingsVO[i].getRegCategoryCode());
						if(regSeason.containsKey(_hospitalTimingsVO[i].getSeasonCode()))
						{
							
							regShift=(Map)regSeason.get(_hospitalTimingsVO[i].getSeasonCode());
							
							if(regShift.containsKey(_hospitalTimingsVO[i].getShiftCode()))
							{
								
								String days=(String)regShift.get(_hospitalTimingsVO[i].getShiftCode())+",";
								days+=_hospitalTimingsVO[i].getWeekDay();
								regShift.put(_hospitalTimingsVO[i].getShiftCode(), days);
							}
							else
							{
								
								regShift.put(_hospitalTimingsVO[i].getShiftCode(), _hospitalTimingsVO[i].getWeekDay());
								regSeason.put(_hospitalTimingsVO[i].getSeasonCode(), regShift);
								regCategory.put(_hospitalTimingsVO[i].getRegCategoryCode(), regSeason);
							}
						}
						else
						{
							regShift.put(_hospitalTimingsVO[i].getShiftCode(), _hospitalTimingsVO[i].getWeekDay());
							regSeason.put(_hospitalTimingsVO[i].getSeasonCode(), regShift);
							regCategory.put(_hospitalTimingsVO[i].getRegCategoryCode(), regSeason);
							
						}	
					}
					else
					{
							regShift.put(_hospitalTimingsVO[i].getShiftCode(), _hospitalTimingsVO[i].getWeekDay());
							regSeason.put(_hospitalTimingsVO[i].getSeasonCode(), regShift);
							regCategory.put(_hospitalTimingsVO[i].getRegCategoryCode(), regSeason);
					}
					
				}
			
				WebUTIL.setAttributeInSession(_rq, enquiryConfig.MAP_OF_HOSPITAL_CATEGORY, regCategory);
			}

 		}catch(HisRecordNotFoundException e){
			
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	



}
