package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.AddUnitImageDescMasterDATA;
import opd.master.controller.fb.AddUnitImageDescMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UserVO;

public class AddUnitImageDescMasterUTIL extends ControllerUTIL
{
	
	public static void getUnitImageDescEssential(AddUnitImageDescMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		Map mp = new HashMap();
		HttpSession session=WebUTIL.getSession(request);
		try
		{
			mp=AddUnitImageDescMasterDATA.getUnitNotInTable(getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
			
			
			
			// Getting only those Department which has  Unit(not mapped unit) 
			
			List lstDept=(List)mp.get(OpdConfig.EssentialBO_LIST_ALL_DEPT);
			List listNotMappedUnit=(List)mp.get(OpdConfig.LIST_UNITS_NOTIN_TABLE);
			
			if(lstDept==null || lstDept.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found to Add");
			}
			if(listNotMappedUnit==null || listNotMappedUnit.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Unit Found to Add");
			}
			
			List lstDeptsOnly = new ArrayList();
			
			for(int i=0;i<lstDept.size();i++)
			{				
				boolean flag = false;
				Entry entDept=(Entry)lstDept.get(i);
				
				for(int j=0;j<listNotMappedUnit.size();j++)
				{
					Entry entUnit=(Entry)listNotMappedUnit.get(j);
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flag = true;	break;	}
				}
					
				if(flag)	lstDeptsOnly.add(entDept);
			}
			
			session.setAttribute(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnly);
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
	
	
	public static void saveUnitImageDesc(AddUnitImageDescMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		List unitImageDescVOLst=new ArrayList(); 
		try
		{
			int countFile=fb.getSelectedImageDescCode().length;
			int countUnit=fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
				for(int i=0;i<countFile;i++)
				{
					UnitImageDescMasterVO unitImageDescMasterVO=new UnitImageDescMasterVO();
					
					unitImageDescMasterVO.setImageId(fb.getSelectedImageDescCode()[i].split("\\$")[0]);
					unitImageDescMasterVO.setUnitCode(fb.getSelectedUnit()[j].trim());
					unitImageDescMasterVO.setColor(fb.getSelectedImageDescCode()[i].split("\\$")[1]);
					
					unitImageDescVOLst.add(unitImageDescMasterVO);
				}
			}
			
			AddUnitImageDescMasterDATA.saveUnitImageDesc(unitImageDescVOLst,getUserVO(request));
			
			objStatus.add(Status.DONE,"Record Saved Successfully","");
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
	
	public static void getUnitImageDescForModify(AddUnitImageDescMasterFB fb,HttpServletRequest request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		UnitImageDescMasterVO[] unitImageDescMasterVOArray=null;
		List listAllImageDesc=null;
		List selImageDescLst=new ArrayList();
		
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String imageDescId=code[1];
		String deptUnitCode=code[0];
		String hospitalCode=code[2];
		String slNo=code[3];
				
		try
		{
			UnitImageDescMasterVO vo=new UnitImageDescMasterVO();
						
			vo.setImageId(imageDescId);
			vo.setUnitCode(deptUnitCode);
			vo.setHospitalCode(hospitalCode);
			vo.setSlNo(slNo);
			
			fb.setUnitCode(deptUnitCode);
			
			essentialMap=AddUnitImageDescMasterDATA.getUnitImageDescForModify(vo,getUserVO(request));
			
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			
			for(int i=0;i<listAllUnit.size();i++)
			{
				 Entry ent=(Entry)listAllUnit.get(i);
				 if( ent.getValue().equalsIgnoreCase(deptUnitCode))
				 {
					fb.setUnitName(ent.getLabel());
					break;
				 }
			}
			
			
			unitImageDescMasterVOArray=(UnitImageDescMasterVO[])essentialMap.get(OpdConfig.MAPPED_UNIT_IMAGE_DESC_VO_ARRAY);
			
			 for(int i=0;i<unitImageDescMasterVOArray.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(unitImageDescMasterVOArray[i].getImageHeader());
				 ent.setValue(unitImageDescMasterVOArray[i].getImageId());
				 selImageDescLst.add(ent);
			 }
			 
				 
			 WebUTIL.setAttributeInSession(request,OpdConfig.MAPPED_IMAGE_DESC_LIST, selImageDescLst);
						 
			 listAllImageDesc=(List)essentialMap.get(OpdConfig.ALL_IMAGE_DESCRIPTION_WITH_COLOR);
			 		
			 ArrayList notSelectedImageDesc=new ArrayList();
			 
			 for (int i = 0; i < listAllImageDesc.size(); i++)
				{
					Entry entobj = (Entry) listAllImageDesc.get(i);
					for (int j = 0; j < selImageDescLst.size(); j++)
					{
						Entry ent = (Entry) selImageDescLst.get(j);
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
						notSelectedImageDesc.add(newobj);
					}
				}
			 
			 WebUTIL.setAttributeInSession(request,OpdConfig.LIST_ALL_IMAGE_DESC_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedImageDesc);
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
	
	public static void saveModUnitImageDesc(AddUnitImageDescMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		List unitImageDescVoLst=new ArrayList(); 	
		try
		{
			int countFile=fb.getSelectedImageDescCode().length;
			
			for(int i=0;i<countFile;i++)
			{
				UnitImageDescMasterVO unitImageDescMasterVO=new UnitImageDescMasterVO();
				
				unitImageDescMasterVO.setImageId(fb.getSelectedImageDescCode()[i].split("\\$")[0]);
				unitImageDescMasterVO.setColor(fb.getSelectedImageDescCode()[i].split("\\$")[1]);
				unitImageDescMasterVO.setUnitCode(fb.getUnitCode());
				
				unitImageDescVoLst.add(unitImageDescMasterVO);
			}
				
			AddUnitImageDescMasterDATA.saveModUnitImageDesc(unitImageDescVoLst,userVO);
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
