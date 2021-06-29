package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ServiceAreaVO;
import hisglobal.vo.ServiceParameterVO;
import hisglobal.vo.ServiceVO;
import hisglobal.vo.Service_Req_dtlVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdServiceRequisitionDATA;
import opd.transaction.controller.fb.OpdServiceRequisitionFB;

public class OpdServiceRequisitionUTIL extends ControllerUTIL {
	
		
	
	public static void getServiceEssentials(HttpServletRequest _rq,OpdServiceRequisitionFB _fb )
	{
		Status objStatus=new Status();
		PatientDetailVO[] dailyPatientVOs = null;
		HttpSession session=_rq.getSession();		
		try{
		setSysdate(_rq);
		UserVO userVO=getUserVO(_rq);
		_fb.setServiceAreaCode(null);
		////////Retrieving the patient vo of the selected patient////////
		/*DailyPatientVO[] arrayDailyPatVO=(DailyPatientVO[]) WebUTIL.getSession(_rq).getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		DailyPatientVO selectedPatientVO=null;
		for(int i=0;i<arrayDailyPatVO.length;i++)
		{
			if(_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
			{
				selectedPatientVO=arrayDailyPatVO[i];
			}
		}*/
		PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
		{
			dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < dailyPatientVOs.length; i++)
			{
				if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
				{
					patientDetailVO = dailyPatientVOs[i];
					break;
				}
			}
		}
		_fb.setPatCrNo(patientDetailVO.getPatCrNo());
		WebUTIL.getSession(_rq).setAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, patientDetailVO);		
	
		Map mp=new HashMap();		
		mp=(HashMap)OpdServiceRequisitionDATA.getServiceEssentials(userVO,patientDetailVO);
		_fb.setDeptCode(patientDetailVO.getDepartmentCode());		
		System.out.println("mp="+ mp);
		WebUTIL.setMapInSession(mp, _rq);
		//_rq.setAttribute(ServiceAreaConfig.SERVICE_TEMPLATE_BEAN, null);
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(_rq,objStatus);		
		}
	}
	
	public static void getComboOptions(HttpServletRequest _rq,OpdServiceRequisitionFB _fb,HttpServletResponse response)
	{
		Status objStatus=new Status();
		String strResult=null;
		Entry ent;
		String label;
		String value;
		if(_fb.getDivId()!=null)
			strResult=_fb.getDivId()+"^";
		int i;		
		try{		
		UserVO userVO=getUserVO(_rq);		
		if(_fb.getDeptCode()!=null)			
		{
			ServiceAreaVO _serviceAreaVO=new ServiceAreaVO();
			HelperMethods.populate(_serviceAreaVO,_fb);
			List lstServiceArea=new ArrayList();
			lstServiceArea=OpdServiceRequisitionDATA.getDeptWiseServiceAreaList(_serviceAreaVO,userVO);
			strResult +="<select name=\"serviceAreaCode\" style=\"width:150px;\" onchange=\"sendData(this)\">" +
						"//<option value=\"-1\">&lt;-Select Value-&gt;</option>";
			if(lstServiceArea!=null)			{
				
				for(i=0;i<lstServiceArea.size();i++)
				{
					ent=(Entry)lstServiceArea.get(i);
					label=ent.getLabel();
					value=ent.getValue();
					strResult+="<option value='" +value + "'>"+ label+"</option>";
				}
				strResult +="</select>";
			}
			
		}
		if(_fb.getServiceAreaCode()!=null)			
		{
			ServiceVO _serviceVO=new ServiceVO();
			HelperMethods.populate(_serviceVO,_fb);
			List lstService=new ArrayList();
			lstService=OpdServiceRequisitionDATA.getServiceAreaWiseServiceList(_serviceVO,userVO);
			strResult +="<select name=\"serviceCode\" style=\"width:150px;\" onchange=\"sendData(this)\">" +
						"//<option value=\"-1\">&lt;-Select Value-&gt;</option>";
			if(lstService!=null)
			{				
				for(i=0;i<lstService.size();i++)
				{
					ent=(Entry)lstService.get(i);
					label=ent.getLabel();
					value=ent.getValue();
					strResult+="<option value='" +value + "'>"+ label+"</option>";
				}
				strResult +="</select>";
			}
			
		}
		System.out.println("strResult" + strResult);		
		try{
			PrintWriter writer=response.getWriter();
			writer.write(strResult);
			}catch(IOException e)
			{e.printStackTrace();}		

		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(_rq,objStatus);		
		}
	}
	public static void getServiceWiseParameters(HttpServletRequest _rq,OpdServiceRequisitionFB _fb,HttpServletResponse response){
		Status objStatus=new Status();
		String strResult=null;
		int i,j,k;
		int paraNo3=0;
		int paraNo2=0;
		if(_fb.getDivId()!=null)
			strResult=_fb.getDivId()+"^";	
		try{		
		//UserVO userVO=getUserVO(_rq);	
		if(_fb.getServiceCode()!=null)			
		{
			ServiceVO _serviceVO=new ServiceVO();
			HelperMethods.populate(_serviceVO,_fb);
			List lstParameterDtl=new ArrayList();
			//List lstTemp=new ArrayList();
			//lstParameterDtl=OpdServiceRequisitionDATA.getServiceWiseParameters(_serviceVO,userVO);
			
			
			
			
			if(lstParameterDtl.size()>0)
			{		
				ServiceParameterVO obj1=new ServiceParameterVO();
				ServiceParameterVO obj2=new ServiceParameterVO();
				ServiceParameterVO obj3=new ServiceParameterVO();			
				
				strResult+="<table width='100%'>";
				strResult+="<tr><td colspan='2' class='tdfonthead' width=\"15%\" height=\"10%\"><div align='left'><B>Service Parameters</B></div></td></tr>";		
				
					
					for(i=0;i<lstParameterDtl.size();i++)
					{					
						obj1=(ServiceParameterVO)lstParameterDtl.get(i);
						if(!obj1.getParentId().equals("0"))
							break;
							
						//System.out.println("obj1--------------------------->" + obj1.getParentId());						
						paraNo2=0;
						for(j=i+1;j<lstParameterDtl.size();j++)
						{						
							obj2=(ServiceParameterVO)lstParameterDtl.get(j);							
							if(obj2.getParentId().equals(obj1.getParameterId()))
							{		
								paraNo2++;
								if(paraNo2==1)
									strResult+="<tr><td colspan='2' class='tdfont' width=\"15%\" height=\"10%\"><b>"+ obj1.getParameterName()+"</b></td></tr>";
								paraNo3=0;
								for(k=j+1;k<lstParameterDtl.size();k++)
								{
									obj3=(ServiceParameterVO)lstParameterDtl.get(k);
									if(obj3.getParentId().equals(obj2.getParameterId()))
									{
										paraNo3++;
										if(paraNo3==1)
											strResult+="<tr><td colspan='2' class='tdfont' width=\"15%\" height=\"10%\">&nbsp;&nbsp;"+ obj2.getParameterName()+"</td></tr>";											
										strResult+="<tr><td class='tdfont' width=\"15%\" height=\"10%\">&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"checkbox\" name=\"parameterId\" value="+obj3.getParameterId() + "> "+ obj3.getParameterName()+"</td></tr>";
										
									}
								}
								if(paraNo3==0)
									strResult+="<tr><td colspan='2' class='tdfont' width=\"15%\" height=\"10%\">&nbsp;&nbsp;<input type=\"checkbox\" name=\"parameterId\" value="+obj2.getParameterId() + "> "+ obj2.getParameterName()+"</td></tr>";
							}	
						}
						if(paraNo2==0)
							strResult+="<tr><td colspan='2' class='tdfont' width=\"15%\" height=\"10%\"><input type=\"checkbox\" name=\"parameterId\" value="+obj1.getParameterId() + "> <b>"+ obj1.getParameterName()+"</b></td></tr>";					}
						
			 }					
			strResult+="</table>";					
		}
		
		
		System.out.println("strResult" + strResult);		
		try{
			PrintWriter writer=response.getWriter();
			writer.write(strResult);
			}catch(IOException e)
			{e.printStackTrace();}		

		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(_rq,objStatus);		
		}

	}

	
	public static boolean SaveServiceRequisition(OpdServiceRequisitionFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		HttpSession session= WebUTIL.getSession(_rq);
		List lstServiceReqDtl=new ArrayList();
		int i;
		try{
			
			DailyPatientVO selectedPatientVO=(DailyPatientVO)session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
			Service_Req_dtlVO _service_Req_dtlVO=new Service_Req_dtlVO();
			//UserVO userVO =getUserVO(_rq);
			lstServiceReqDtl=(List) WebUTIL.getSession(_rq).getAttribute(OpdConfig.SERVICE_ALL_SERVICE_REQ_DTL_BY_CRNO);
			if(lstServiceReqDtl!=null)
			{
				Service_Req_dtlVO obj_service_Req_dtlVO=new Service_Req_dtlVO();
				for(i=0;i< lstServiceReqDtl.size();i++)
				{
					
					obj_service_Req_dtlVO=(Service_Req_dtlVO)lstServiceReqDtl.get(i);
					String serviceDateFromFB=_fb.getServiceDate().toUpperCase().trim();
					String serviceDateFromObj_service_Req_dtlVO=obj_service_Req_dtlVO.getServiceDate().toUpperCase().trim();
					
					if(serviceDateFromFB.equals(serviceDateFromObj_service_Req_dtlVO)&& obj_service_Req_dtlVO.getPatCrNo().equals(selectedPatientVO.getPatCrNo()) && obj_service_Req_dtlVO.getServiceCode().equals(_fb.getServiceCode()))
					{
						_fb.setMsg("Service requisition already raised for this date!");
						return false;
					}
				}
			}			
			HelperMethods.populate(_service_Req_dtlVO, _fb);			
			HelperMethods.populate(_service_Req_dtlVO, selectedPatientVO);			
			//_service_Req_dtlVO=OpdServiceRequisitionDATA.SaveServiceRequisition(_service_Req_dtlVO,userVO);
			HelperMethods.populate(_fb,_service_Req_dtlVO);
			_fb.setMsg("Requisition Successfully Raised!");
				
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(_rq,objStatus);		
		}
		return true;
	}
		
}
