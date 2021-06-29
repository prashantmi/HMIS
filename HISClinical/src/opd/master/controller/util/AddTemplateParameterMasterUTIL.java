package opd.master.controller.util;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.vo.OpdParameterVO;
import hisglobal.vo.OpdTemplateParameterVO;
import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.AddTemplateParameterMasterDATA;
import opd.master.controller.fb.TemplateParameterMasterFB;

public class AddTemplateParameterMasterUTIL extends ControllerUTIL 
{
	//* Getting Parameter List
	public static void getParameterList(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			mp=AddTemplateParameterMasterDATA.getParameterList(userVO);

			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.INPROCESS);
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

	//* Actually Saving Template Code  
	public static void actualSaveParameterToTemplate(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			OpdTemplateParameterVO[][] tPL= new OpdTemplateParameterVO[_fb.getNoOfRows()][9];

			_fb.setParameterValuesList(_fb.getParameterValuesList().replace("%","!"));
			String parametersMix[]=_fb.getParameterValuesList().split("!#!");
			for(int i=0;i<parametersMix.length;i++)
			{
				String valArr[]= parametersMix[i].toString().split("!");
				OpdTemplateParameterVO _obj=new OpdTemplateParameterVO();
				_obj.setTemplateId(_fb.getTemplateId());
				
				System.out.println("---->  ********** "+valArr[0]+"   "+valArr[0].split("#"));
				
				if( valArr[0].split("#").length > 1 && valArr[0].split("#")[0].equals(""))
				{
					OpdParameterVO voParameter = new OpdParameterVO();
					voParameter.setParaName(valArr[0].split("#")[1].trim());
					AddTemplateParameterMasterDATA.SaveOPDParameter(voParameter, userVO);
					_obj.setParaId(voParameter.getParaId());
				}
				else if( valArr[0].split("#").length > 1) _obj.setParaId(valArr[0].split("#")[0].trim());
				
				_obj.setParentParaId(valArr[1]);
				_obj.setLocationId(valArr[2]);
				_obj.setRowId(valArr[3]);
				_obj.setColumnId(valArr[4]);
				_obj.setValueObjId(valArr[5]);
				_obj.setValueObjProp(valArr[6]);
				_obj.setColspan(valArr[7]);
				_obj.setParaValue(valArr[8]);
				_obj.setIsCompulsory(valArr[9]);
				_obj.setIsRange(valArr[10]);
				_obj.setSourceFlag(valArr[11]);
				_obj.setTableQuery(valArr[12]);
				
				tPL[Integer.parseInt(_obj.getRowId())-1][Integer.parseInt(_obj.getColumnId())-1]=_obj;
				
			}
			boolean bHtmlTempSave=true;

			// Setting Parent & Template Save Flag
			for(int i=0;i<_fb.getNoOfRows();i++)
				for(int j=0;j<9;j++)
					if(tPL[i][j]!=null)
					{
						OpdTemplateParameterVO obj=tPL[i][j];
						if(obj.getSourceFlag().equals("2")) bHtmlTempSave=false;
						if(!obj.getParentParaId().trim().equals(""))
						{
							String valArr[]=obj.getParentParaId().split("#");
							String oParaId="";
							String parents="";
							
							for(int k=0;k<valArr.length;k++)
							{
								String temp=tPL[Integer.parseInt(valArr[k].split("&")[0])-1][Integer.parseInt(valArr[k].split("&")[1])-1].getParaId();
								oParaId+=temp;
								parents+=temp+"@"+valArr[k]+"#";
								oParaId+="#";
							}
							if(!oParaId.equals(""))	oParaId=oParaId.substring(0,oParaId.length()-1);
							if(!parents.equals(""))	parents=parents.substring(0,parents.length()-1);
							
							System.out.println("------------ Para ---> >>>> >>>>  "+oParaId);
							System.out.println("------------ Parent -> >>>> >>>>  "+parents);
							
							obj.setParentParaId(parents);
							if(obj.getParaId()==null || obj.getParaId().trim().equals("")) obj.setParaId(oParaId);
						}
						AddTemplateParameterMasterDATA.SaveParameterToTemplate(obj, userVO);
					}
			
			
			HisFileControlUtil htmlFile = new HisFileControlUtil();
			//htmlFile.setFilePath(Config.OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH);
			//htmlFile.setFileName(Config.OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_NAME+_fb.getTemplateId()+".html");
			if(bHtmlTempSave)
			{
				int maxrow=0;
				for(int i=0;i<_fb.getNoOfRows();i++)
					for(int j=0;j<9;j++)
						if(tPL[i][j]!=null)
						{
							OpdTemplateParameterVO obj=tPL[i][j];
							if(Integer.parseInt(obj.getRowId())>maxrow) maxrow=Integer.parseInt(obj.getRowId());
						}
				_fb.setNoOfRows(maxrow);
				_fb.setTempParaMatrix(tPL);
				_fb.generateTemplate();
				
				//htmlFile.setFileContent(_fb.getHtmlTemplate());
				if(!htmlFile.saveFile())	throw new HisException("File can't be saved ... ");
			}
			else
			{
				htmlFile.deleteFile();
			}
		}
		finally
		{
			
		}
	}

	//* Save Parameters to Template
	public static boolean SaveParameterToTemplateMaster(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			OpdTemplateVO voTemplate= new OpdTemplateVO();
			voTemplate.setTemplateName(_fb.getTemplateName());
			voTemplate.setTemplateType(_fb.getTemplateType());
			voTemplate.setEffectiveFrom(_fb.getEffectiveFrom());
			voTemplate.setEffectiveTo(_fb.getEffectiveTo());
			
			AddTemplateParameterMasterDATA.SaveTemplate(voTemplate,userVO);
			_fb.setTemplateId(voTemplate.getTemplateId());
			
			actualSaveParameterToTemplate(_fb, _request);
			
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}
		return saveFlag;
	}

	//* Get Template for Modification
	public static void getModifyTemplate(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			// Fetching Selected Record Primary Key
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			
			_fb.setTemplateId(concatid[0]);
			_fb.setTempSerialNo(concatid[1]);
			
			// Template	Name & Type
			OpdTemplateVO voTemp=new OpdTemplateVO();
			voTemp.setTemplateId(_fb.getTemplateId());
			voTemp.setTempSerialNo(_fb.getTempSerialNo());
			
			AddTemplateParameterMasterDATA.getTemplateDataById(voTemp,userVO);
			
			_fb.setTemplateName(voTemp.getTemplateName());
			_fb.setTemplateType(voTemp.getTemplateType());
			_fb.setEffectiveFrom(voTemp.getEffectiveFrom());
			_fb.setEffectiveTo(voTemp.getEffectiveTo());

			objStatus.add(Status.INPROCESS);
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

	//* Get Template for Modification
	public static void getModifyTemplateParametersForm(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstParaDetail=new ArrayList();
		Map mp=new HashMap();
		List lstPara=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			mp=AddTemplateParameterMasterDATA.getParameterList(userVO);
			System.out.println("   ------> Parameter List Map : "+ mp);

			// Parameter Detail  
			lstParaDetail=AddTemplateParameterMasterDATA.getTempParaDetailList(_fb.getTemplateId() ,userVO);
			System.out.println("   ------> Parameter Detail : "+lstParaDetail);
			
			int maxrow=0;
			for(int i=0;i<lstParaDetail.size();i++)
			{
				OpdTemplateParameterVO obj=(OpdTemplateParameterVO)lstParaDetail.get(i);
				if(Integer.parseInt(obj.getRowId())>maxrow) maxrow=Integer.parseInt(obj.getRowId());
			}
			_fb.setNoOfRows(maxrow);
			
			OpdTemplateParameterVO[][] ParaMatrix=new OpdTemplateParameterVO[maxrow][9];

			String htmlParams="";
			
			for(int i=0;i<lstParaDetail.size();i++)
			{
				OpdTemplateParameterVO obj=(OpdTemplateParameterVO)lstParaDetail.get(i);

				String paraName="";
				lstPara=(ArrayList)mp.get(OpdConfig.EssentialBO_LIST_ALL_PARAMETERS);
				for(int j=0;j<lstPara.size();j++)
				{
					Entry entObj =(Entry)lstPara.get(j);
					if(entObj.getValue().equals(obj.getParaId()))
					{
						paraName=entObj.getLabel();
						break;
					}
				}
				
				// Setting Parent
				String tempPara="";
				String parents="";
				if(obj.getParentParaId()!=null && !obj.getParentParaId().trim().equals(""))
				{
					String valArr[]=obj.getParentParaId().split("#");
					for(int k=0;k<valArr.length;k++)
					{
						tempPara+=valArr[k].split("@")[0]+"#";
						parents+=valArr[k].split("@")[1]+"#";
					}	
					if(!tempPara.equals(""))	tempPara=tempPara.substring(0,tempPara.length()-1);
					if(!parents.equals(""))	parents=parents.substring(0,parents.length()-1);
					System.out.println("---------------------> >>>> >>>>  "+tempPara);
					System.out.println("---------------------> >>>> >>>>  "+parents);
				}
				
				if(obj.getParaId().equals(tempPara))
					htmlParams+= ""+"#"+paraName+"%";
				else
					htmlParams+= obj.getParaId()+"#"+paraName+"%";
				htmlParams+=parents+"%";
				
				
				htmlParams+=obj.getLocationId()+"%";
				htmlParams+=obj.getRowId()+"%";
				htmlParams+=obj.getColumnId()+"%";

				htmlParams+=obj.getValueObjId()+"%";
				htmlParams+=obj.getValueObjProp()+"%";
				htmlParams+=obj.getColspan()+"%";
				htmlParams+=obj.getParaValue()+"%";
				htmlParams+=obj.getIsCompulsory()+"%";
				htmlParams+=obj.getIsRange()+"%";
				htmlParams+=obj.getSourceFlag()+"%";
				if(!obj.getTableQuery().equals("")) htmlParams+=obj.getTableQuery();
				else htmlParams +=" ";
				
				ParaMatrix[Integer.parseInt(obj.getRowId())-1][Integer.parseInt(obj.getColumnId())-1]=obj;
				htmlParams=htmlParams.concat("%#%");
			}
			if(htmlParams.length()>3)htmlParams=htmlParams.substring(0,htmlParams.length()-3);
			htmlParams=htmlParams.replace("null", "");
			_fb.setParameterValuesList(htmlParams);
			_fb.setTempParaMatrix(ParaMatrix);
			
			String htmlTemp="<div align='center'> <input type='button' value='Click Here to Get Template' onclick='renderTemplateForModification()'> </div>";
			_fb.setHtmlTemplate(htmlTemp);

			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.INPROCESS);
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
	
	//* Modify Template 
	public static void updateTemplate(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			OpdTemplateVO voTemplate= new OpdTemplateVO();

			voTemplate.setTemplateId(_fb.getTemplateId());
			voTemplate.setTempSerialNo(_fb.getTempSerialNo());
			voTemplate.setTemplateName(_fb.getTemplateName());
			voTemplate.setTemplateType(_fb.getTemplateType());
			voTemplate.setEffectiveFrom(_fb.getEffectiveFrom());
			voTemplate.setEffectiveTo(_fb.getEffectiveTo());
			
			AddTemplateParameterMasterDATA.UpdateTemplate(voTemplate,userVO);
			
			objStatus.add(Status.INPROCESS,"Template Modified Successfully","");		
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

	//* Adding Template with New Serial No 
	public static void addNewTemplateRow(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		OpdTemplateVO voTemplate= new OpdTemplateVO();
		try
		{
			HelperMethods.populate(voTemplate,_fb);	
			voTemplate.setTemplateId(_fb.getTemplateId());
			voTemplate.setTemplateName(_fb.getTemplateName());
			voTemplate.setTemplateType(_fb.getTemplateType());
			voTemplate.setEffectiveFrom(_fb.getEffectiveFrom());
			voTemplate.setEffectiveTo(_fb.getEffectiveTo());
			
			AddTemplateParameterMasterDATA.SaveNewTemp(voTemplate,getUserVO(_request));	
			objStatus.add(Status.INPROCESS,"Template Added Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{			
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			_fb.setTransactionMode("ADDITION");
		}
		catch(HisDataAccessException e)
		{			
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	//* Update Parameters to Template
	public static void UpdateParameterToTemplateMaster(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);

			AddTemplateParameterMasterDATA.deleteTempParaById(_fb.getTemplateId(),userVO);
			
			actualSaveParameterToTemplate(_fb, _request);
			
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.INPROCESS,"Template Successully Updated","");
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

	
	
	//* Get Template for View
	public static void getViewTemplateParametersForm(TemplateParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstParaDetail=new ArrayList();
		List dynaData=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			
			_fb.setTemplateId(concatid[0]);
			_fb.setTempSerialNo(concatid[1]);

			// Template	Name & Type
			OpdTemplateVO voTemp=new OpdTemplateVO();
			voTemp.setTemplateId(_fb.getTemplateId());
			voTemp.setTempSerialNo(_fb.getTempSerialNo());
			AddTemplateParameterMasterDATA.getTemplateDataById(voTemp,userVO);
			_fb.setTemplateName(voTemp.getTemplateName());
			_fb.setTemplateType(voTemp.getTemplateType());

			// Parameter Detail
			lstParaDetail=AddTemplateParameterMasterDATA.getTempParaDetailList(_fb.getTemplateId() ,userVO);
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
					
					dynaData=AddTemplateParameterMasterDATA.getParameterDynamicData(obj.getTableQuery(),userVO);
					SetDynamicData(obj, dynaData);
				}
				ParaMatrix[Integer.parseInt(obj.getRowId())-1][Integer.parseInt(obj.getColumnId())-1]=obj;
			}
			_fb.setTempParaMatrix(ParaMatrix);
			_fb.generateTemplate();
			
			objStatus.add(Status.INPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

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
}
