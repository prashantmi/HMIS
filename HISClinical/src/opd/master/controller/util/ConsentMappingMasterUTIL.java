package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ConsentMappingMasterDATA;
import opd.master.controller.fb.ConsentMappingMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.UserVO;

public class ConsentMappingMasterUTIL extends ControllerUTIL
{
	public static boolean getEssentails(ConsentMappingMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			//ConsentMappingMasterVO consentMappingMasterVO=new ConsentMappingMasterVO();
			_fb.setChoice(OpdConfig.TEMPLATE_MODE_CONSENT);
			List lstServiceType = ConsentMappingMasterDATA.getEssentails(userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_SERVICE_TYPE_LIST, lstServiceType);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	public static boolean getDetail(ConsentMappingMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			ConsentMappingMasterVO consentMappingMasterVO=new ConsentMappingMasterVO();
			
			HelperMethods.populate(consentMappingMasterVO, _fb);
			
			Map essantialMap = ConsentMappingMasterDATA.getDetail(consentMappingMasterVO,userVO);
			
			
			List lstService=(List)essantialMap.get(OpdConfig.OPD_SERVICE_LIST);
			List lstTemplate=(List)essantialMap.get(OpdConfig.OPD_TEMPLATE_LIST);
			List lstSelectedServiceId=(List)essantialMap.get(OpdConfig.OPD_SELECTED_SERVICEID_LIST);
			List remainingServiceIdList=new ArrayList();
			if(lstService!=null)
			{
				remainingServiceIdList.addAll(lstService);
				
				Iterator itr=lstService.iterator();
				
				while(itr.hasNext())
				{
					Entry serviceid=(Entry)itr.next();
					Iterator selectedServiceid=lstSelectedServiceId.iterator();	
					
					while(selectedServiceid.hasNext())
					{
						Entry selectedServiceId=(Entry)selectedServiceid.next();
						if(serviceid.getValue().equals(selectedServiceId.getValue()))
						{
							remainingServiceIdList.remove(serviceid);
						}
					}
					
				}
				
			}
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_REMAINING_TEMPLATE_LIST_BY_TEMPLATETYPE, remainingServiceIdList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_SERVICE_LIST_BY_SERVICETYPEID, lstService);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_TEMPLATE_LIST_BY_TEMPLATEIDTYPE, lstTemplate);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	  
	public static void saveDetail(ConsentMappingMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		ConsentMappingMasterVO consentMappingMasterVO=new ConsentMappingMasterVO();
		try
		{
			HttpSession session=WebUTIL.getSession(request);
			List lstService=(List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_SERVICE_LIST_BY_SERVICETYPEID);
			for(int i=0;i<lstService.size();i++)
			{
				Entry entrytype=(Entry)lstService.get(i);
				if(entrytype.getValue().equals(_fb.getServiceId()))
				{
					_fb.setServiceDesc(entrytype.getLabel());
				}
			}
			HelperMethods.populate(consentMappingMasterVO, _fb);
			String[] selectedTemplateID=_fb.getSelectedTemplate();
			ConsentMappingMasterVO[] _consentMappingMasterVO=new ConsentMappingMasterVO[selectedTemplateID.length] ;
			for(int i=0;i<selectedTemplateID.length;i++)
			{
				
				_consentMappingMasterVO[i]=new ConsentMappingMasterVO();
				HelperMethods.populate(_consentMappingMasterVO[i], _fb);
				_consentMappingMasterVO[i].setTemplateId(selectedTemplateID[i]);
			}
			
			String[] serviceId=_fb.getSelServiceIdLst(); 
			ConsentMappingMasterDATA.saveDetail(_consentMappingMasterVO,serviceId,lstService,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void getModifyDetail(ConsentMappingMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		Map mp=new HashMap();
		List selectedTemplateList=null;
		List templateList=null;
		List serviceLst=null;
		List selectedServiceLst=null;
		ConsentMappingMasterVO consentMappingMasterVO=new ConsentMappingMasterVO();
		try
		{
			//HttpSession session=WebUTIL.getSession(request);
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sServiceTypeID = concatid[0];
			String sTemplateID = concatid[1];
			String sServiceID = concatid[2];
			String sSLNO=concatid[3];
			String sHospitalCode=concatid[4];
			
			consentMappingMasterVO.setServiceTypeId(sServiceTypeID);
			consentMappingMasterVO.setTemplateId(sTemplateID);
			consentMappingMasterVO.setServiceId(sServiceID);
			consentMappingMasterVO.setSerialNo(sSLNO);
			consentMappingMasterVO.setHospitalCode(sHospitalCode);
			mp=ConsentMappingMasterDATA.getModifyDetail(consentMappingMasterVO,userVO);
			consentMappingMasterVO=(ConsentMappingMasterVO)mp.get(OpdConfig.OPD_SELECTED_CONSENTMAPPING_VO);
			if(consentMappingMasterVO.getTemplateType().equals("1"))
			{
				consentMappingMasterVO.setTemplateDesc("Consent");
			}
			else
			{
				consentMappingMasterVO.setTemplateDesc("Guideline");
			}
			
			HelperMethods.populate(_fb, consentMappingMasterVO);
			serviceLst=(List)mp.get(OpdConfig.OPD_SERVICE_LIST);
			selectedServiceLst=(List)mp.get(OpdConfig.OPD_SELECTED_SERVICE_LIST);
			templateList=(List)mp.get(OpdConfig.OPD_TEMPLATE_LIST);
			selectedTemplateList=(List)mp.get(OpdConfig.OPD_SELECTED_TEMPLATE_LIST);
			/*Iterator tempitr=templateList.iterator();
			
			List remaningTemplateList=new ArrayList();
			while(tempitr.hasNext())
			{
				Entry temp=(Entry)tempitr.next();
				Iterator selecteditr=selectedTemplateList.iterator();
				while(selecteditr.hasNext())
				{
					Entry selectedTemp=(Entry)selecteditr.next();
					if(!temp.getValue().equals(selectedTemp.getValue()))
					{
						remaningTemplateList.add(temp);
					}
				}
				
			}*/
			WebUTIL.setAttributeInSession(request, OpdConfig.SERVICE_LIST, serviceLst);
			WebUTIL.setAttributeInSession(request, OpdConfig.SELECTED_SERVICE_LIST, selectedServiceLst);
			
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_TEMPLATE_LIST_BY_TEMPLATEIDTYPE, templateList);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID, selectedTemplateList);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_REMAINING_TEMPLATE_LIST, templateList);
		} 
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void saveModifyDetail(ConsentMappingMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		HttpSession session=WebUTIL.getSession(request);
		ConsentMappingMasterVO consentMappingMasterVO=new ConsentMappingMasterVO();
		try
		{
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sServiceTypeID = concatid[0];
			String sTemplateID = concatid[1];
			String sServiceID = concatid[2];
			//String sSLNO=concatid[3];
			String sHospitalCode=concatid[4];
			
			_fb.setServiceTypeId(sServiceTypeID);
			_fb.setTemplateId(sTemplateID);
			_fb.setServiceId(sServiceID);
			//_fb.setSerialNo(sSLNO);
			_fb.setHospitalCode(sHospitalCode);
			String[] selectedTemplateID=_fb.getSelectedTemplate();
			String [] selectedSeviceID=_fb.getSelServiceIdLst();
			
		
			List lstService=(List)session.getAttribute(OpdConfig.SELECTED_SERVICE_LIST);
		
			HelperMethods.populate(consentMappingMasterVO, _fb);
		
			ConsentMappingMasterVO[] _serviceVO=new ConsentMappingMasterVO[selectedSeviceID.length];
			for(int i=0;i<_serviceVO.length;i++)
			{		
				_serviceVO[i]=new ConsentMappingMasterVO();
				HelperMethods.populate(_serviceVO[i], _fb);
				_serviceVO[i].setServiceId(selectedTemplateID[i]);
			}
			for(int i=0;i<_serviceVO.length;i++)
			{
				Entry entrytype=(Entry)lstService.get(i);
			if(entrytype.getValue().equals(_fb.getServiceId()))
			{
				_fb.setServiceDesc(entrytype.getLabel());
			}
			}
			HelperMethods.populate(consentMappingMasterVO, _fb);
			//String[] serviceId=_fb.getSelServiceIdLst(); 
			ConsentMappingMasterVO[] _consentMappingMasterVO=new ConsentMappingMasterVO[selectedTemplateID.length] ;
			for(int i=0;i<_consentMappingMasterVO.length;i++)
			{
				_consentMappingMasterVO[i]=new ConsentMappingMasterVO();
				HelperMethods.populate(_consentMappingMasterVO[i], _fb);
				_consentMappingMasterVO[i].setTemplateId(selectedTemplateID[i]);
			}
			ConsentMappingMasterDATA.saveModifyDetail(_consentMappingMasterVO,userVO);
			
			objStatus.add(Status.DONE,"Record Modified Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}

	

}
