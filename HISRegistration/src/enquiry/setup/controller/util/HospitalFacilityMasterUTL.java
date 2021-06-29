package enquiry.setup.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import enquiry.enquiryConfig;
import enquiry.setup.controller.data.HospitalFacilityMasterDATA;
import enquiry.setup.controller.fb.HospitalFacilityMasterFB;
import enquiry.vo.HospitalFacilityMasterVO;

public class HospitalFacilityMasterUTL extends ControllerUTIL{
	
	public static void getAllHospitalFacilityList(HospitalFacilityMasterFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		HospitalFacilityMasterVO hospitalFacilityVo=new HospitalFacilityMasterVO();
		try{
			setSysdate(_rq);
			String isValid=(_fb.getIsValid()==null)?"":_fb.getIsValid();
			List hospitalFacilityMasterVOList=HospitalFacilityMasterDATA.getAllHospitalFacilityList(isValid,getUserVO(_rq));
			for(int i=0;i<hospitalFacilityMasterVOList.size();i++){
				hospitalFacilityVo=(HospitalFacilityMasterVO)hospitalFacilityMasterVOList.get(i);
				hospitalFacilityVo.setFacilityDesc(hospitalFacilityVo.getFacilityDesc().replace("#", ", "));
				hospitalFacilityMasterVOList.set(i, hospitalFacilityVo);
				
			}
			
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST, hospitalFacilityMasterVOList);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}

	public static void saveHospitalFacility(HospitalFacilityMasterFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		
		try{
			HospitalFacilityMasterVO  hospitalFacilityMasterVO=new HospitalFacilityMasterVO();
			HelperMethods.populate(hospitalFacilityMasterVO,fb);
			if(fb.getIsEmergencyService()==null)
				hospitalFacilityMasterVO.setIsEmergencyService("0");
			HospitalFacilityMasterDATA.saveHospitalFacility(hospitalFacilityMasterVO,getUserVO(request));
			objStatus.add(Status.LIST,"","Record Added Successfully");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
		
	}

	public static void getHospitalFacility(HospitalFacilityMasterFB fb,	HttpServletRequest request) {
		Status objStatus=new Status();
		String desc[]={};
		String descList[];
		List temp=new ArrayList();
		try{
			HospitalFacilityMasterVO  hospitalFacilityMasterVO=new HospitalFacilityMasterVO();
			String facilityId=fb.getFacilityId().split("#")[0];
			String hospitalCode=fb.getFacilityId().split("#")[1];
			String slNo=fb.getFacilityId().split("#")[2];
			hospitalFacilityMasterVO.setFacilityId(facilityId);
			hospitalFacilityMasterVO.setSlNo(slNo);
			fb.setFacilityId(facilityId);
			hospitalFacilityMasterVO=HospitalFacilityMasterDATA.getHospitalFacility(hospitalFacilityMasterVO,getUserVO(request));
			HelperMethods.populate(fb, hospitalFacilityMasterVO);
			if(hospitalFacilityMasterVO.getIsEmergencyService().equals("1"))
				fb.setIsEmergencyService("on");
			if(hospitalFacilityMasterVO.getFacilityDescType().equals("2")){
				 desc=hospitalFacilityMasterVO.getFacilityDesc().split("#");
			
				if(desc.length>1){
					fb.setFacilityDesc(desc[0]);
				}
				fb.setNoOfRows(desc.length-1);
				for(int i=0;i<desc.length;i++)
					temp.add(desc[i]);
				temp.remove(0);
				descList=new String[temp.size()];
				for(int i=0;i<temp.size();i++)
					descList[i]=temp.get(i).toString();
					
				fb.setDesc(descList);
			}
			//WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_FACILITY_MST_VO, hospitalFacilityMasterVO);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void modifyHospitalFacility(HospitalFacilityMasterFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		
		try{
			HospitalFacilityMasterVO  hospitalFacilityMasterVO=new HospitalFacilityMasterVO();
			HelperMethods.populate(hospitalFacilityMasterVO,fb);
			if(fb.getIsEmergencyService()==null)
				hospitalFacilityMasterVO.setIsEmergencyService("0");
			HospitalFacilityMasterDATA.modifyHospitalFacility(hospitalFacilityMasterVO,getUserVO(request));
			objStatus.add(Status.LIST,"","Record modified Successfully");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
			
	}

	public static void deleteHospitalFacility(HospitalFacilityMasterFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		
		try{
			HospitalFacilityMasterVO  hospitalFacilityMasterVO []=null;
			//HelperMethods.populate(hospitalFacilityMasterVO,fb);
			String []facilityId=fb.getSelectedFaclityId();
			hospitalFacilityMasterVO=new HospitalFacilityMasterVO[facilityId.length];
			for(int i=0;i<facilityId.length;i++){
				hospitalFacilityMasterVO[i]=new HospitalFacilityMasterVO();
				hospitalFacilityMasterVO[i].setFacilityId(facilityId[i].split("#")[0]);
				hospitalFacilityMasterVO[i].setSlNo(facilityId[i].split("#")[2]);
			}
			HospitalFacilityMasterDATA.deleteHospitalFacility(hospitalFacilityMasterVO,getUserVO(request));
			//objStatus.add(Status.LIST,"","Record deleted Successfully");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
		
	}

	public static void setHospitalFacilityList(HospitalFacilityMasterFB _fb,HttpServletRequest request) {
		Status objStatus=new Status();
		HospitalFacilityMasterVO hospitalFacilityVo=new HospitalFacilityMasterVO();
		HttpSession session=request.getSession();
		List hospitalFaclitylist=new ArrayList();
		try{
			
			List hospitalFacilityMasterVOList=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST);
			
			for(int i=0;i<hospitalFacilityMasterVOList.size();i++){
				Entry entry=new Entry();
				hospitalFacilityVo=(HospitalFacilityMasterVO)hospitalFacilityMasterVOList.get(i);
				entry.setValue(hospitalFacilityVo.getFacilityId());
				entry.setLabel(hospitalFacilityVo.getFacilityName());
				hospitalFaclitylist.add(entry);
			}
			
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST, hospitalFaclitylist);
			objStatus.add(Status.LIST);
		}
	
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
		
	}

	public static void changeDisplayOrder(HospitalFacilityMasterFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		
		try{
			HospitalFacilityMasterVO  hospitalFacilityMasterVO []=null;
			String []facilityId=fb.getFacilityId().split("%");
			hospitalFacilityMasterVO=new HospitalFacilityMasterVO[facilityId.length];
			int displayOrder=0;
			for(int i=0;i<facilityId.length;i++){
				hospitalFacilityMasterVO[i]=new HospitalFacilityMasterVO();
				hospitalFacilityMasterVO[i].setFacilityId(facilityId[i].split("#")[0]);
				hospitalFacilityMasterVO[i].setSlNo(facilityId[i].split("#")[2]);
				hospitalFacilityMasterVO[i].setDisplayOrder(String.valueOf(++displayOrder));
			}
			HospitalFacilityMasterDATA.changeDisplayOrder(hospitalFacilityMasterVO,getUserVO(request));
			//objStatus.add(Status.LIST,"","Record deleted Successfully");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
		
	}
	
		
}

	


