package opd.master.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.UnitAudioVideoMasterDATA;
import opd.master.controller.fb.UnitAudioVideoMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.UnitAudioVideoMasterVO;

public class UnitAudioVideoMasterUTIL extends ControllerUTIL
{
	public static void getUnitAudioVideoEssential(UnitAudioVideoMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		
		try
		{
			
			List lstDept=UnitAudioVideoMasterDATA.getAllDept(getUserVO(request));
			WebUTIL.setAttributeInSession(request,OpdConfig.EssentialBO_LIST_ALL_DEPT,lstDept);
			List lstUnit=UnitAudioVideoMasterDATA.getAllUnitNotInTable(getUserVO(request));
			WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_BO_LIST_ALL_UNIT_NOT_IN_HOPT_UNIT_PLAYERFILE_MST,lstUnit);
			List lstAllUnit=UnitAudioVideoMasterDATA.getAllUnit(getUserVO(request));
			WebUTIL.setAttributeInSession(request,OpdConfig.EssentialBO_LIST_ALL_UNITS,lstAllUnit);
			List lstAVFile=UnitAudioVideoMasterDATA.getAllAudioVideoFileHeader(getUserVO(request));
			WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_BO_LIST_ALL_AUDIO_VIDEO_FILE,lstAVFile);
			
			
			
// Getting only those Department which has  Unit(not mapped unit) 
			
			if(lstDept==null || lstDept.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found to Add");
			}
			if(lstUnit==null || lstUnit.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Unit Found to Add");
			}
			
			List lstDeptsOnly = new ArrayList();
			
			for(int i=0;i<lstDept.size();i++)
			{				
				boolean flag = false;
				Entry entDept=(Entry)lstDept.get(i);
				
				for(int j=0;j<lstUnit.size();j++)
				{
					Entry entUnit=(Entry)lstUnit.get(j);
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flag = true;	break;	}
				}
					
				if(flag)	lstDeptsOnly.add(entDept);
			}
			
			WebUTIL.setAttributeInSession(request,OpdConfig.EssentialBO_LIST_ALL_DEPT,lstDeptsOnly);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void saveUnitAudioVideo(UnitAudioVideoMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		//String unitCode=fb.getUnitCode();
		try
		{
			//UnitAudioVideoMasterDATA.deleteUnitAudioVideo(unitCode,getUserVO(request));
			int countFile=fb.getSelectedAVFileCode().length;
			int countUnit=fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
			for(int i=0;i<countFile;i++)
			{
				UnitAudioVideoMasterVO unitAVMasterVO=new UnitAudioVideoMasterVO();
				unitAVMasterVO.setFileCode(fb.getSelectedAVFileCode()[i]);
				unitAVMasterVO.setUnitCode(fb.getSelectedUnit()[j].trim());
				
				UnitAudioVideoMasterDATA.saveUnitAudioVideo(unitAVMasterVO,getUserVO(request));
			}
			}
			objStatus.add(Status.DONE,"Audio Video File Added To Unit Successfully","");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Transaction Failed");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void setUnitName(UnitAudioVideoMasterFB fb,HttpServletRequest request)
	{
		List lstAllUnit=UnitAudioVideoMasterDATA.getAllUnit(getUserVO(request));
		
		WebUTIL.setAttributeInSession(request,OpdConfig.EssentialBO_LIST_ALL_UNITS,lstAllUnit);
		/*if(fb.getControls()[0]!=null)
			fb.setUnitCode(fb.getControls()[0]);
*/
		String chk="";
		if(fb.getChk()!=null)	chk=fb.getChk().replace("^","@");
		//String[] code=chk.split("@");
		String[] concatid = chk.split("@");

		String unitCode = concatid[0];
		//String imagecode = concatid[1];
		//String slNo=concatid[3];

		String unitName="";
		
		for(int i=0;i<lstAllUnit.size();i++)
		{
			 Entry ent=(Entry)lstAllUnit.get(i);
			 if( ent.getValue().equalsIgnoreCase(unitCode))
			 {
				 unitName=ent.getLabel();
			 }
		}
		fb.setUnitName(unitName);
	}
	
	public static void getUnitAudioVideoForModify(UnitAudioVideoMasterFB fb,HttpServletRequest request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		String unitCode=code[0];
		fb.setUnitCode(unitCode);
		List lstUnitAVFile=new ArrayList();
		try
		{
			 List lstAVFile=UnitAudioVideoMasterDATA.getAllAudioVideoFileHeader(getUserVO(request));
			 WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_BO_LIST_ALL_AUDIO_VIDEO_FILE,lstAVFile);
			
			 AudioVideoMasterVO[] avMasterVO=UnitAudioVideoMasterDATA.getUnitAudioVideoForModify(unitCode,getUserVO(request));
			 for(int i=0;i<avMasterVO.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(avMasterVO[i].getFileHeader());
				 ent.setValue(avMasterVO[i].getFileCode());
				 lstUnitAVFile.add(ent);
			 }
			 
				 
			 WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST, lstUnitAVFile);
			 
		//	 List lstAllAVFileNotSelected=UnitAudioVideoMasterDATA.getAllAVFileNotInSelectedBasedOnUnit(unitCode,getUserVO(request));
		//	 WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_BO_LIST_ALL_AV_FILE_NOT_IN_SELECTED_BASED_ON_UNIT ,lstAllAVFileNotSelected);
			 
			 
			 List allFile=(List)request.getSession().getAttribute(OpdConfig.ESSENTIAL_BO_LIST_ALL_AUDIO_VIDEO_FILE);
			 List selectedFile=(List)request.getSession().getAttribute(OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST);
		
			 ArrayList notSelectedFile=new ArrayList();
			 
			 for (int i = 0; i < allFile.size(); i++)
				{
					Entry entobj = (Entry) allFile.get(i);
					for (int j = 0; j < selectedFile.size(); j++)
					{
						Entry ent = (Entry) selectedFile.get(j);
						if (ent.getValue().equals(entobj.getValue()))
						{
							flag = true;	break;
						}
						else
							flag = false;
					}
					if (!flag)
					{
						Entry newobj = new Entry();
						newobj.setValue(entobj.getValue());
						newobj.setLabel(entobj.getLabel());
						notSelectedFile.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_BO_LIST_ALL_AV_FILE_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedFile);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void modifySaveUnitAudioVideo(UnitAudioVideoMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		String unitCode=fb.getUnitCode();
			try
			{
				UnitAudioVideoMasterDATA.deleteUnitAudioVideo(unitCode,getUserVO(request));
				int countFile=fb.getSelectedAVFileCode().length;
				for(int i=0;i<countFile;i++)
				{
					UnitAudioVideoMasterVO unitAVMasterVO=new UnitAudioVideoMasterVO();
					unitAVMasterVO.setFileCode(fb.getSelectedAVFileCode()[i]);
					unitAVMasterVO.setUnitCode(fb.getUnitCode());
					
					UnitAudioVideoMasterDATA.saveUnitAudioVideo(unitAVMasterVO,getUserVO(request));
				}
				objStatus.add(Status.DONE,"Audio Video File Modified Successfully","");
			}
			catch(HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA,"","Transaction Failed");		
			}
			catch(HisApplicationExecutionException e)
			{		
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			}
			catch(HisException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR,"","Transaction Failed");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			}
			finally
			{
				WebUTIL.setStatus(request,objStatus);
			}
	}
}
