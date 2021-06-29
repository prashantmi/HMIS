package opd.master.controller.util;

/**
 * @author  CDAC
 */

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.TemplateUtilityDATA;
import opd.master.controller.fb.TemplateParameterFormatBean;
import opd.master.controller.fb.TemplateParameterMasterFB;
import registration.RegistrationConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.OpdTemplateParameterVO;
import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.UserVO;

public class TemplateUtilityUTIL extends ControllerUTIL 
{
	//* Getting Template Parameter Data of Given Template Id
	public static void getTemplateParametersData(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstParaDetail=new ArrayList();
		List dynaData=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			// Template	Name & Type
			OpdTemplateVO voTemp=new OpdTemplateVO();
			voTemp.setTemplateId(_fb.getTemplateId());
			voTemp.setTempSerialNo(RegistrationConfig.EFFECTIVE_CASE_FIRST_SERIAL_NO);
			TemplateUtilityDATA.getTemplateDataById(voTemp,userVO);
			System.out.println("   ------> Temp Info  : "+voTemp);
			_fb.setTemplateName(voTemp.getTemplateName());
			_fb.setTemplateType(voTemp.getTemplateType());
			
			// Parameter Detail
			lstParaDetail=TemplateUtilityDATA.getTempParaDetailList(_fb.getTemplateId() ,userVO);
			System.out.println("   ------> Parameter Detail : "+lstParaDetail);
			
			int maxrow=0;
			for(int i=0;i<lstParaDetail.size();i++)
			{
				OpdTemplateParameterVO obj=(OpdTemplateParameterVO)lstParaDetail.get(i);
				if(Integer.parseInt(obj.getRowId())>maxrow) maxrow=Integer.parseInt(obj.getRowId());
			}
			_fb.setNoOfRows(maxrow);
			
			OpdTemplateParameterVO[][] ParaMatrix=new OpdTemplateParameterVO[maxrow][9];

			for(int i=0;i<lstParaDetail.size();i++)
			{
				OpdTemplateParameterVO obj=(OpdTemplateParameterVO)lstParaDetail.get(i);
				// Setting Dynamic Value
				if(obj.getSourceFlag().equals("2"))//Dynamic
				{
					dynaData=TemplateUtilityDATA.getParameterDynamicData(obj.getTableQuery(), userVO);
					SetDynamicData(obj, dynaData);
				}
				ParaMatrix[Integer.parseInt(obj.getRowId())-1][Integer.parseInt(obj.getColumnId())-1]=obj;
			}
			_fb.setTempParaMatrix(ParaMatrix);
		}
		catch(HisRenewalRequiredException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR_AE,"Renewal Required","");
		} 
		catch(HisEpisodeOpenInEmergencyException e)
		{ 
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR,"Episode Opened in emergency", "");
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Dynamic Data
	static void SetDynamicData(OpdTemplateParameterVO _VO,List data)
	{
		String paraOptions="";
		switch(Integer.parseInt(_VO.getValueObjId()))
		{
			case 4:
			case 5:
				for(int i=0;i<data.size();i++)
				{
					Entry entObj=(Entry)data.get(i);
					paraOptions+=entObj.getValue()+":"+entObj.getLabel()+"~";
				}
				if(!paraOptions.equals(""))paraOptions=paraOptions.substring(0,paraOptions.length()-1);
				break;
			case 7:
				Entry entObj=(Entry)data.get(0);
				paraOptions+=entObj.getValue()+":"+entObj.getLabel();
				break;
		}
		_VO.setParaValue(_VO.getParaValue()+paraOptions);
	}

	//* Creating Actual Parameter Value Map
	public static void createActualParaValueMap(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			Map mp;
			if(_fb.getActualParameterValues()==null) 
			{
				mp=new HashMap();
				OpdTemplateParameterVO tempMat[][] = _fb.getTempParaMatrix();
				for(int i=0;i<_fb.getNoOfRows();i++)
					for(int j=0;j<9;j++)
						if(tempMat[i][j]!=null && !tempMat[i][j].getValueObjId().equals("1"))//Label
						{
							TemplateParameterFormatBean tPFB=new TemplateParameterFormatBean(Integer.parseInt(_fb.getTemplateType()),Integer.parseInt(tempMat[i][j].getValueObjId()),tempMat[i][j].getValueObjProp() );
							if(!tPFB.getDefaultValue().equals(""))
								mp.put(tempMat[i][j].getParaId(), tPFB.getDefaultValue());
							else
								mp.put(tempMat[i][j].getParaId(),"");
						}
				_fb.setActualParameterValues(mp);
			}
		}
		catch(HisRenewalRequiredException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR_AE,"Renewal Required","");
		} 
		catch(HisEpisodeOpenInEmergencyException e)
		{ 
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR,"Episode Opened in emergency", "");
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	// Static Class to Hold Parameter Values
	public static class TempParameter
	{
		private String templateId;
		private String paraId;
		private String paraValue;
		
		public String getTemplateId()
		{
			return templateId;
		}
		public void setTemplateId(String templateId)
		{
			this.templateId = templateId;
		}
		public String getParaId()
		{
			return paraId;
		}
		public void setParaId(String paraId)
		{
			this.paraId = paraId;
		}
		public String getParaValue()
		{
			return paraValue;
		}
		public void setParaValue(String paraValue)
		{
			this.paraValue = paraValue;
		}		
	}
	
	//* Getting Parameter-Values List
	public static List getTempParameterValueList(HttpServletRequest _request)
	{
		List<TempParameter> lstParaValues= new ArrayList<TempParameter>();
		
		Enumeration requestParameters = _request.getParameterNames();
		while (requestParameters.hasMoreElements())
		{
			String nextElement = (String) requestParameters.nextElement();
			System.out.println("class type  =" + _request.getParameterValues(nextElement).getClass().isArray());
			if (nextElement.toString().startsWith("PARAMETER@"))
			{
				String requestDataArray[] = _request.getParameterValues(nextElement);
				if (requestDataArray.length != 0) 
					for (int i = 0; i < requestDataArray.length; i++)
						if (!requestDataArray[i].trim().equals(""))
						{
							TempParameter tempPara = new TempParameter();
							String pId = (String) nextElement;
							pId = pId.replace("PARAMETER@", "");
							tempPara.setTemplateId(pId.split("&")[1]);
							tempPara.setParaId(pId.split("&")[0]);
							tempPara.setParaValue(requestDataArray[i]);
							lstParaValues.add(tempPara);
						}
			}
		}
		return lstParaValues;
	}
}
