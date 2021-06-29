/**
 * 
 */
package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdMappingMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.OpdIcdMappingMasterDATA;
import opd.master.controller.fb.OpdIcdMappingMasterFB;

/**
 * @author ashas
 *
 */
public class OpdIcdMappingMasterUTIL extends ControllerUTIL{

	/**
	 * 
	 * @To get All essential field after click on Add
	 * @param _request
	 */
	public static void getEssential(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
	Status  objStatus=new Status();		
	Map mapEssential=new HashMap();
	Map mapEssentialHospital=new HashMap();
	Map mapEssentialChronic=new HashMap();
	List listHospitalDisease=null;
	List listChronicDisease=null;
	List listGroup= null;
	
	try
	{
		UserVO _userVO = getUserVO(request_p);
		setSysdate(request_p);
		
		if(fb_p.getControls() !=null && fb_p.getControls()[0]!=null)
			fb_p.setMappingType(fb_p.getControls()[0]);
		
		int nMappingType=Integer.parseInt(fb_p.getMappingType());
		fb_p.setMappingTypeDesc(OpdConfig.MAPPING_TYPE_ARR[nMappingType]);
		
		if(fb_p.getMappingType().equals("1"))
			mapEssentialHospital=OpdIcdMappingMasterDATA.getHospitalDisease(_userVO);
		
		if(fb_p.getMappingType().equals("2"));
			mapEssentialChronic=OpdIcdMappingMasterDATA.getChronicDisease(_userVO);
		mapEssential=OpdIcdMappingMasterDATA.getEssential(_userVO);
		
		listHospitalDisease= (List)mapEssentialHospital.get(OpdConfig.OPD_ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE);
		listChronicDisease = (List)mapEssentialChronic.get(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHRONIC_DISEASE);
		
		WebUTIL.setAttributeInSession(request_p,OpdConfig.OPD_ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE,listHospitalDisease);
		WebUTIL.setAttributeInSession(request_p,OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHRONIC_DISEASE,listChronicDisease);
	
		listGroup = (List)mapEssential.get(OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP);
		WebUTIL.setAttributeInSession(request_p,OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP,listGroup);
		WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE, null);
		
		objStatus.add(Status.NEW);
		if(fb_p.getMappingType().equals("1"))
		if(listHospitalDisease==null || listHospitalDisease.size()==0)
		{
			throw new HisRecordNotFoundException("No Hospital Disease Found");
		}
		if(fb_p.getMappingType().equals("2"));
		if(listChronicDisease==null || listChronicDisease.size()==0)
		{
			throw new HisRecordNotFoundException("No Chronic Disease Found");
		}
	}
	catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
	}
	catch(HisDataAccessException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
		request_p.setAttribute(Config.STATUS_OBJECT,objStatus);
	}	
}
	// Method for getting Subgroup
	public static void getSubGroup(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		String strGroup= fb_p.getIcdGroupCode();
		List<IcdSubgroupMasterVO> listSubgroup = new ArrayList<IcdSubgroupMasterVO>();
		List<IcdDiseaseMasterVO> vo = new ArrayList<IcdDiseaseMasterVO>();
		List listICDDisease = new ArrayList();
		List listDiseaseMappedPop = new ArrayList();
		List listMappedDiseasePopUp = new ArrayList();
		List listSortedMappedDiseasePopUp = new ArrayList();
		List listSortedMappedDisease = new ArrayList();
		Map mapEssential = new HashMap();
		try
		{
			UserVO userVO = getUserVO(request_p);
			listSubgroup=OpdIcdMappingMasterDATA.getSubGroupsByGroup(strGroup, userVO);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_SUBGROUP_GROUPWISE, listSubgroup);
			
			WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE, null);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED, null);
			List listDiseaseMapped=(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
			if(listDiseaseMapped==null||listDiseaseMapped.size()==0)
			{
				if(fb_p.getSelectedDisease()==null || fb_p.getSelectedDisease().length==0)
				{
					//throw new HisApplicationExecutionException("");
				}
				else{
					for(int i=0;i<fb_p.getSelectedDisease().length;i++){
						listDiseaseMappedPop.add(fb_p.getSelectedDisease()[i]);}
					
					//code for getting Disease label in sorted order with disease code
					 vo=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_POPUP);
					 Collections.sort(listDiseaseMappedPop);
					 for(int k=0;k<vo.size();k++)
					{ boolean flag= false;
						for(int j =0;j<listDiseaseMappedPop.size();j++)
						{	
							if(vo.get(k).getDiseaseCode().equals(listDiseaseMappedPop.get(j)))
									{flag = true;break;}
								else 
								{	flag= false; }
						}
						if(flag)
							listMappedDiseasePopUp.add(vo.get(k).getDisease());
						
					}// end here
					
				WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listMappedDiseasePopUp);
			}}
			else
			{
				if(fb_p.getSelectedDisease()==null || fb_p.getSelectedDisease().length==0)
				{
				}
				else{
				for(int i=0;i<fb_p.getSelectedDisease().length;i++)
				{
					listSortedMappedDisease.add(fb_p.getSelectedDisease()[i]);
				}
				//new code
				 vo=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_POPUP);
				 Collections.sort(listSortedMappedDisease);
				 for(int k=0;k<vo.size();k++)
				{ boolean flag= false;
					for(int j =0;j<listSortedMappedDisease.size();j++)
					{	
						if(vo.get(k).getDiseaseCode().equals(listSortedMappedDisease.get(j)))
								{flag = true;break;}
							else 
							{	flag= false; }
					}
					if(flag)
						listSortedMappedDiseasePopUp.add(vo.get(k).getDisease());
					
				}//new code end here
				listDiseaseMapped.addAll(listSortedMappedDiseasePopUp);
				WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listDiseaseMapped );
			}
		}
			objStatus.add(Status.TRANSINPROCESS);
			
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
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
	
	// method for getting ICD Disease List	
	public static void getIcdDisease(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		List<IcdDiseaseMasterVO> listDisease = new ArrayList<IcdDiseaseMasterVO>();
		List<IcdDiseaseMasterVO> listDiseaseList = new ArrayList<IcdDiseaseMasterVO>();
		List<IcdDiseaseMasterVO> vo = new ArrayList<IcdDiseaseMasterVO>();
		List<IcdDiseaseMasterVO> listPopUp = new ArrayList<IcdDiseaseMasterVO>();
		List listMappedDisease = new ArrayList();
		String strSubGroup = fb_p.getIcdSubgroupCode();
		List<IcdDiseaseMasterVO> listNotMappedDisease = new ArrayList<IcdDiseaseMasterVO>();
		List listMappedDiseasePopUp = new ArrayList();
		List listMappedDiseasePop = new ArrayList();
		String strMappedDisease=null;
		String strDisease =null;
		List listSortedDiseasePop = new ArrayList();
		List listPopUPDisease = new ArrayList();
	
		try
		{
			UserVO userVO = getUserVO(request_p);
			listDisease=OpdIcdMappingMasterDATA.getDiseaseBySubGroup(strSubGroup,userVO);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE, listDisease);
			if(fb_p.getSelectedDisease()==null || fb_p.getSelectedDisease().length==0)
			{
				//WebUTIL.setAttributeInSession(request_p, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,"" );
			}
			else
			{	
				List listDiseaseMapped=(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
				for(int i=0;i<fb_p.getSelectedDisease().length;i++)
				{	boolean fFlag= false;
					if(listDiseaseMapped==null || listDiseaseMapped.size()==0)
					listMappedDisease.add(fb_p.getSelectedDisease()[i]);
//					if(listDiseaseMapped!=null )
					else{
					for(int k=0;k<listDiseaseMapped.size();k++){
					if(!(fb_p.getSelectedDisease()[i].equals(listDiseaseMapped.get(k))))
					{
						fFlag=true;
						}
					
					else
						{fFlag=false;break;}
					}
					if(fFlag)
						listMappedDisease.add(fb_p.getSelectedDisease()[i]);
					}
					
				}
				if(listDiseaseMapped==null || listDiseaseMapped.size()==0)
				{
					 vo=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_POPUP);
					 Collections.sort(listMappedDisease);
					 for(int k=0;k<vo.size();k++)
					{ boolean flag= false;
						for(int j =0;j<listMappedDisease.size();j++)
						{	
							if(vo.get(k).getDiseaseCode().equals(listMappedDisease.get(j)))
									{flag = true;break;}
								else 
								{	flag= false; }
						}
						if(flag)
							listMappedDiseasePopUp.add(vo.get(k).getDisease());
						
					}
					WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listMappedDiseasePopUp );
				}
				else
				{
					listPopUp=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_POPUP);
					Collections.sort(listMappedDisease);
					for(int k=0;k<listPopUp.size();k++)
					{ boolean flag= false;
						for(int j =0;j<listMappedDisease.size();j++)
						{	
							if(listPopUp.get(k).getDiseaseCode().equals(listMappedDisease.get(j)))
									{flag = true;break; }
								else 
								{	flag= false; }
						}
						if(flag)
							listMappedDiseasePop.add(listPopUp.get(k).getDisease());
						
					}
					for(int i=0;i<listMappedDiseasePop.size();i++)
						listDiseaseMapped.add(listMappedDiseasePop.get(i));
					//for sorting
					for(int x=0;x<listDiseaseMapped.size();x++)
						{
							 strMappedDisease=(String)listDiseaseMapped .get(x);
							String strChk=strMappedDisease.replace("(","@");
							String[] strCode=strChk.split("@");
							strDisease=strCode[1].substring(0, (strCode[1].length()-1));
							listPopUPDisease.add(strDisease);
						}
					Collections.sort(listPopUPDisease);
					for(int m=0;m<listPopUPDisease.size();m++)
					{
					for(int x=0;x<listDiseaseMapped.size();x++)
					{
						 strMappedDisease=(String)listDiseaseMapped .get(x);
						String strChk=strMappedDisease.replace("(","@");
						String[] strCode=strChk.split("@");
						if((strCode[1].substring(0, (strCode[1].length()-1))).equalsIgnoreCase((String)listPopUPDisease.get(m)))
						listSortedDiseasePop.add(listDiseaseMapped.get(x));
					}}
					//sorting end
				WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listSortedDiseasePop );
				}
			}
			List listFinalMappedDisease=(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
			if(listFinalMappedDisease==null || listFinalMappedDisease.size()==0)
				{
				}
			else{
				
				for(int k=0;k<listDisease.size();k++)
				{ boolean flag= false;
					for(int j =0;j<listFinalMappedDisease.size();j++)
					{	
						if(!(listDisease.get(k).getDisease().equals(listFinalMappedDisease.get(j))))
								flag = true;
							else 
							{	flag= false; break;}
					}
					if(flag)
						listNotMappedDisease.add(listDisease. get(k));
				}
				WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE, listNotMappedDisease);
			}
			 listDiseaseList=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE);
			 WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_DISEASE_POPUP, listDiseaseList);
			 if(listDisease==null || listDisease.size()==0)
			{
				throw new HisRecordNotFoundException("No Disease Found");
			}
			objStatus.add(Status.TRANSINPROCESS,"","");
			
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
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
	//Method for saving icd mapping
	public static void saveIcdMapping(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		List listIcdMasterVOLst=new ArrayList();
		List listMappedDisease =new ArrayList();
		List listDiseaseMapped =null;
		List listPopUPDisease= new ArrayList();
		String strMappedDisease=null;
		String strDisease =null;
		try
		{
			 listDiseaseMapped =(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
			
			 for(int i=0;i<fb_p.getSelectedDisease().length;i++)
			{	
				listMappedDisease .add(fb_p.getSelectedDisease()[i]);
			}
			if(listDiseaseMapped ==null || listDiseaseMapped .size()==0)
			{
//				throw new HisRecordNotFoundException("No Mapped Disease exist ");
			}
			else
			{
				for(int m=0;m<listDiseaseMapped.size();m++)
				{
				 strMappedDisease=(String)listDiseaseMapped .get(m);
				String strChk=strMappedDisease.replace("(","@");
				String[] strCode=strChk.split("@");
				strDisease=strCode[1].substring(0, (strCode[1].length()-1));
				listPopUPDisease.add(strDisease);
				}
				for(int i=0;i<listMappedDisease .size();i++)
				{
					listPopUPDisease .add(listMappedDisease .get(i));
				}
			
			}
			if(listDiseaseMapped ==null || listDiseaseMapped .size()==0)
				{
					for(int i=0;i<listMappedDisease .size();i++)
					{
						IcdMappingMasterVO icdMappingMasterVO=new IcdMappingMasterVO ();
						icdMappingMasterVO.setDiseaseCode((String)listMappedDisease.get(i));
						icdMappingMasterVO.setMappingType(fb_p.getMappingType());
						if(fb_p.getMappingType().equals("1"))
							icdMappingMasterVO.setMappingID(fb_p.getHospitalDisease());
						if(fb_p.getMappingType().equals("2"))
							icdMappingMasterVO.setMappingID(fb_p.getChronicDisease());
						listIcdMasterVOLst.add(icdMappingMasterVO);
					}
				}
			else
			{for(int i=0;i<listPopUPDisease .size();i++)
			{
				IcdMappingMasterVO icdMappingMasterVO=new IcdMappingMasterVO ();
				
				icdMappingMasterVO.setDiseaseCode((String)listPopUPDisease.get(i));
				icdMappingMasterVO.setMappingType(fb_p.getMappingType());
					if(fb_p.getMappingType().equals("1"))
					icdMappingMasterVO.setMappingID(fb_p.getHospitalDisease());
					if(fb_p.getMappingType().equals("2"))
						icdMappingMasterVO.setMappingID(fb_p.getChronicDisease());
				listIcdMasterVOLst.add(icdMappingMasterVO);
			}
			}
			
			OpdIcdMappingMasterDATA.saveIcdMapping(listIcdMasterVOLst,getUserVO(request_p));
			
			objStatus.add(Status.DONE,"Record Saved Successfully","");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
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
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
	
	// to get modify detail
	public static void getIcdMappingForModify(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		Map mapEssential=new HashMap();
		IcdMappingMasterVO[] icdMappingMasterVO=null;
		List<Entry> listMappedDisease= new ArrayList<Entry>();
		List listPopUpDisease =new ArrayList();
		List listGroup=null;
		
		String strChk=fb_p.getChk().replace("^","@");
		String[] strCode=strChk.split("@");
		
		String strMappingType=strCode[0];
		String strMappingID=strCode[1];
		String strDiseaseCode=strCode[2];
		String strSlNo=strCode[3];
		try
		{
			if(fb_p.getControls() !=null && fb_p.getControls()[0]!=null)
//				fb_p.setMappingType(fb_p.getControls()[0]);
			fb_p.setMappingType(strMappingType);
				fb_p.setMappingID(strMappingID);
			
			int nMappingType=Integer.parseInt(fb_p.getMappingType());
			fb_p.setMappingTypeDesc(OpdConfig.MAPPING_TYPE_ARR[nMappingType]);
			
			IcdMappingMasterVO vo=new IcdMappingMasterVO();
			vo.setMappingType(strMappingType);
			vo.setMappingID(strMappingID);
			vo.setDiseaseCode(strDiseaseCode);
			vo.setSlNo(strSlNo);
			
			List<IcdSubgroupMasterVO> lstSubgroup = new ArrayList<IcdSubgroupMasterVO>();
			mapEssential=OpdIcdMappingMasterDATA.getIcdMappingForModify(vo,getUserVO(request_p));
			listGroup = (List)mapEssential.get(OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP);
			
			icdMappingMasterVO=(IcdMappingMasterVO[])mapEssential.get(OpdConfig.MAPPED_ICD_DISEASE_LIST_VO_ARRAY);
			
			for(int i=0;i<icdMappingMasterVO.length;i++)
			 {
				listPopUpDisease.add(icdMappingMasterVO[i].getDisease());
				if(fb_p.getMappingType().equals("1"))
					fb_p.setHospitalDisease(icdMappingMasterVO[i].getMappingID());
				if(fb_p.getMappingType().equals("2"))
					fb_p.setChronicDisease(icdMappingMasterVO[i].getMappingID());
				 Entry ent=new Entry();
				 ent.setLabel(icdMappingMasterVO[i].getDisease());
				 ent.setValue(icdMappingMasterVO[i].getDiseaseCode());
				 listMappedDisease.add(ent) ;
				 }
			WebUTIL.setAttributeInSession(request_p,OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP, listGroup);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED, null);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.MAPPED_ICD_DISEASE_LIST, listMappedDisease);
			// WebUTIL.setAttributeInSession(request_p,OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE, null);
			 objStatus.add(Status.TRANSINPROCESS,"","");
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
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
	
	//to get modified subgroup
	public static void getModSubGroup(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		String strGroup= fb_p.getIcdGroupCode();
		List<IcdSubgroupMasterVO> listSubgroup = new ArrayList<IcdSubgroupMasterVO>();
		List<Entry> listPopUp = new ArrayList<Entry>();
		List<Entry> listPopUpDisease = new ArrayList<Entry>();
		List listICDDisease = new ArrayList();
		List listDiseaseMappedPop = new ArrayList();
		List listMappedDiseasePopUp = new ArrayList();
		List listSortedMappedDiseasePopUp = new ArrayList();
		List listSortedMappedDisease = new ArrayList();
		Map mapEssential = new HashMap();
		String strDiseaseMapped = null;
		String strDiseaseList = null;
		List listDiseaseForPopUp = new ArrayList();
		List listSortedDiseaseForPopUp  = new ArrayList();
		
		
		try
		{
			UserVO userVO = getUserVO(request_p);
			listSubgroup=OpdIcdMappingMasterDATA.getSubGroupsByGroup(strGroup, userVO);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ESSENTIAL_LIST_ICD_SUBGROUP_GROUPWISE, listSubgroup);
			
			WebUTIL.setAttributeInSession(request_p, OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED, null);
			//List listMappedDisease= (ArrayList)request_p.getSession().getAttribute(OpdConfig.MAPPED_ICD_DISEASE_LIST);
//			WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE, listMappedDisease);
			List listDiseaseMapped=(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
			
			if(listDiseaseMapped==null||listDiseaseMapped.size()==0)
			{
				if(fb_p.getSelectedDisease()==null || fb_p.getSelectedDisease().length==0)
				{
				}
				else{
					for(int i=0;i<fb_p.getSelectedDisease().length;i++){
						listDiseaseMappedPop.add(fb_p.getSelectedDisease()[i]);}
					
					//new code
					 listPopUp=(ArrayList<Entry>)request_p.getSession().getAttribute(OpdConfig.OPD_ICD_DISEASE_LIST_FOR_POPUP);
					Collections.sort(listDiseaseMappedPop);
					if(listPopUp==null)
						{
						List<Entry> listMappedDisease= (ArrayList<Entry>)request_p.getSession().getAttribute(OpdConfig.MAPPED_ICD_DISEASE_LIST);
						for(int i=0;i<listDiseaseMappedPop.size();i++)
							{
								for(int j=0;j<listMappedDisease.size();j++)
								{
									if(listDiseaseMappedPop.get(i).equals(listMappedDisease.get(j).getValue()))
									{
										listDiseaseForPopUp.add(listMappedDisease.get(j).getLabel());
									}
								}
							}
						WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listDiseaseForPopUp);
						
						WebUTIL.setAttributeInSession(request_p, OpdConfig.MAPPED_ICD_DISEASE_LIST,null);
						}
					else{
					 for(int k=0;k<listPopUp.size();k++)
					 { boolean flag= false;
						for(int j =0;j<listDiseaseMappedPop.size();j++)
						{	
							if(listPopUp.get(k).getValue().equals(listDiseaseMappedPop.get(j)))
									{flag = true;break;}
								else 
								{	flag= false; }
						}
						if(flag)
							listMappedDiseasePopUp.add(listPopUp.get(k).getLabel());
						
					}
					 WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listMappedDiseasePopUp);
					}
				
			}}
			else
			{
				if(fb_p.getSelectedDisease()==null || fb_p.getSelectedDisease().length==0)
				{
				}
				else{
				for(int i=0;i<fb_p.getSelectedDisease().length;i++)
				{
					listSortedMappedDisease.add(fb_p.getSelectedDisease()[i]);
				}
				
				listPopUpDisease=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ICD_DISEASE_LIST_FOR_POPUP);
				 Collections.sort(listSortedMappedDisease);
				 for(int k=0;k<listPopUpDisease.size();k++)
				{ boolean flag= false;
					for(int j =0;j<listSortedMappedDisease.size();j++)
					{	
						if(listPopUpDisease.get(k).getValue().equals(listSortedMappedDisease.get(j)))
								{flag = true;break;}
							else 
							{	flag= false; }
					}
					if(flag)
						listSortedMappedDiseasePopUp.add(listPopUpDisease.get(k).getLabel());
					
				}//new code end here
				listDiseaseMapped.addAll(listSortedMappedDiseasePopUp);
				WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listDiseaseMapped );
			}
		}
			
			
			
			
			objStatus.add(Status.TRANSINPROCESS);
			
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
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
	// to get modified icd Disease
	public static void getModIcdDisease(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		List<IcdDiseaseMasterVO> listDisease = new ArrayList<IcdDiseaseMasterVO>();
		List<Entry> listDiseaseList = new ArrayList<Entry>();
//		List<IcdDiseaseMasterVO> listDiseaseList = new ArrayList<IcdDiseaseMasterVO>();
		List listMappedDisease = new ArrayList();
		List listDiseaseMapped =new ArrayList();
		String strSubGroup = fb_p.getIcdSubgroupCode();
		String strMappingType = fb_p.getMappingType();
		String strMappingId = fb_p.getMappingID();
		Map MapEssential = new HashMap();
		HttpSession session=WebUTIL.getSession(request_p);
		List<Entry> lstNotMappedDisease = new ArrayList<Entry>();
		List<Entry> notMappedDisease = new ArrayList<Entry>();
		List<Entry> listPopUp = new ArrayList<Entry>();
		List listMappedDiseasePop = new ArrayList();
		String strMappedDisease= null;
		String strDisease = null;
		String strDiseaseMapped= null;
		String strDiseaseList = null;
		List listPopUPDisease = new ArrayList();
		List listDiseaseForPopUp = new ArrayList();
		List listSortedDiseaseForPopUp = new ArrayList();
	
		try
		{
			UserVO userVO = getUserVO(request_p);
			MapEssential=OpdIcdMappingMasterDATA.getModIcdDisease(strMappingType,strMappingId,strSubGroup,userVO);
			notMappedDisease=(List)MapEssential.get(OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED, notMappedDisease);

			
			listDiseaseMapped=(List)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
			WebUTIL.setAttributeInSession(request_p, OpdConfig.ICD_MAPPED_DISEASE, listDiseaseMapped);
			session.removeAttribute(OpdConfig.MAPPED_ICD_DISEASE_LIST);
			if(listDiseaseMapped.size()!=0 || listDiseaseMapped!=null){
			for(int m=0;m<listDiseaseMapped.size();m++)
			{
			 strMappedDisease=(String)listDiseaseMapped .get(m);
			String strChk=strMappedDisease.replace("(","@");
			String[] strCode=strChk.split("@");
			strDisease=strCode[1].substring(0, (strCode[1].length()-1));
			listPopUPDisease.add(strDisease);
			}
			}
			else
			{
				
			}
			if(fb_p.getSelectedDisease()==null || fb_p.getSelectedDisease().length==0)
			{
//				WebUTIL.setAttributeInSession(request_p, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT, diseaseMapped);
			}
			else
			{	
				for(int i=0;i<fb_p.getSelectedDisease().length;i++)
				{	
					if(listPopUPDisease.size()!=0||listPopUPDisease!=null)
						listMappedDisease.add(fb_p.getSelectedDisease()[i]);
//					for(int x=0;x<listPopUPDisease.size();x++){
//						if(fb_p.getSelectedDisease()[i].equals(listPopUPDisease.get(x)))
//							{
////							listMappedDisease.add(listPopUPDisease.get(x));
//							}
						else
							listMappedDisease.add(fb_p.getSelectedDisease()[i]);
				}
				//for getting Disease Label
				listPopUp=(ArrayList)request_p.getSession().getAttribute(OpdConfig.OPD_ICD_DISEASE_LIST_FOR_POPUP);
				if(listPopUp==null)
				{
				}
				else{
					for(int k=0;k<listPopUp.size();k++)
						{ 
						boolean fFlag= false;
						for(int j =0;j<listMappedDisease.size();j++)
							{	
								if(listPopUp.get(k).getValue().equals(listMappedDisease.get(j)))
								{fFlag = true;break; }
								else 
								{fFlag= false; }
							}
							if(fFlag)
								listMappedDiseasePop.add(listPopUp.get(k).getLabel());
					
						}
				}
				for(int i=0;i<listMappedDiseasePop.size();i++)
					{
						for(int m=0;m<listDiseaseMapped.size();m++)
						{
							if(listMappedDiseasePop.get(i).equals(listDiseaseMapped.get(m)))
							listDiseaseMapped.remove(m);
						}
						listDiseaseMapped.add(listMappedDiseasePop.get(i));
				}	
				//for sorting
				for(int x=0;x<listDiseaseMapped.size();x++)
					{
						strDiseaseMapped=(String)listDiseaseMapped .get(x);
						String strChk=strDiseaseMapped.replace("(","@");
						String[] strCode=strChk.split("@");
						strDiseaseList=strCode[1].substring(0, (strCode[1].length()-1));
						listDiseaseForPopUp.add(strDiseaseList);
					}
				Collections.sort(listDiseaseForPopUp);
				for(int m=0;m<listDiseaseForPopUp.size();m++)
					{
						for(int x=0;x<listDiseaseMapped.size();x++)
							{
								strDiseaseMapped=(String)listDiseaseMapped .get(x);
								String strChk=strDiseaseMapped.replace("(","@");
								String[] strCode=strChk.split("@");
								if((strCode[1].substring(0, (strCode[1].length()-1))).equalsIgnoreCase((String)listDiseaseForPopUp.get(m)))
									listSortedDiseaseForPopUp.add(listDiseaseMapped.get(x));
							}
					}
				//sorting end
				WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE,listSortedDiseaseForPopUp );
			}
			// code for getting not Mapped Disease in left side
				List listDiseaseListWithMappingType=(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
					if(listDiseaseListWithMappingType==null || listDiseaseListWithMappingType.size()==0)
					{
					}
					else{
						for(int k=0;k<notMappedDisease.size();k++)
						{ 
							boolean flag= false;
							for(int j =0;j<listDiseaseListWithMappingType.size();j++)
								{	
								if(!(notMappedDisease.get(k).getLabel().equals(listDiseaseListWithMappingType.get(j))))
								flag = true;
								else 
								{	flag= false; break;}
								}
							if(flag){
								Entry ent = new Entry();
								ent.setLabel(notMappedDisease.get(k).getLabel());
								ent.setValue(notMappedDisease.get(k).getValue());
								lstNotMappedDisease.add(ent);
							}
						}
						WebUTIL.setAttributeInSession(request_p, OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED, lstNotMappedDisease);
					}
			
			 listDiseaseList=(ArrayList)request_p.getSession().getAttribute(OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED);
			 WebUTIL.setAttributeInSession(request_p, OpdConfig.OPD_ICD_DISEASE_LIST_FOR_POPUP, listDiseaseList);
			
			if(notMappedDisease==null || notMappedDisease.size()==0)
			{
				throw new HisRecordNotFoundException("No Disease Found");
			}
			objStatus.add(Status.TRANSINPROCESS,"","");
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
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
	// to save modified ICD Mapping
	public static void modifySaveIcdMapping(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus =new Status();
		List listIcdDisease=new ArrayList();
		List listIcdForUpdate = new ArrayList();
		List listMappedDisease=new ArrayList();
		String strMappedDisease=null;
		String strDisease=null;
		List listPopUPDisease = new ArrayList();
		
			try
			{
				List listDiseaseMapped=(ArrayList)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
				List listDisease =(ArrayList)request_p.getSession().getAttribute(OpdConfig.ICD_MAPPED_DISEASE);
				for(int i=0;i<fb_p.getSelectedDisease().length;i++)
				{	
					listMappedDisease.add(fb_p.getSelectedDisease()[i]);
				}
				for(int m=0;m<listDiseaseMapped.size();m++)
				{
				 strMappedDisease=(String)listDiseaseMapped .get(m);
				String strChk=strMappedDisease.replace("(","@");
				String[] strCode=strChk.split("@");
				strDisease=strCode[1].substring(0, (strCode[1].length()-1));
				listPopUPDisease.add(strDisease);
				}
				//
				
				if(listDiseaseMapped==null || listDiseaseMapped.size()==0)
				{
					throw new HisRecordNotFoundException("No disease Mapped ");
				}
				else
				{
					for(int i=0;i<listMappedDisease.size();i++)
					{
						listPopUPDisease.add(listMappedDisease.get(i));
					}
				}
				
				for(int i=0;i<listPopUPDisease.size();i++)
				{
					IcdMappingMasterVO icdMappingMasterVO=new IcdMappingMasterVO();			
					icdMappingMasterVO.setDiseaseCode((String)listPopUPDisease.get(i));
					icdMappingMasterVO.setMappingType(fb_p.getMappingType());
						icdMappingMasterVO.setMappingID(fb_p.getMappingID());
					listIcdDisease.add(icdMappingMasterVO);
				}
				OpdIcdMappingMasterDATA.modifySaveIcdMapping(listIcdDisease,getUserVO(request_p));
				
				objStatus.add(Status.DONE,"ICD Disease Mapping Modified Successfully","");
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
				WebUTIL.setStatus(request_p,objStatus);
			}
	}
	// to delete row in popup window
	public static void deleteRow(OpdIcdMappingMasterFB fb_p,HttpServletRequest request_p)
	{
		Status objStatus=new Status();
		List listMappedDisease = new ArrayList();
		List listDiseaseMapped = new ArrayList();
		
		try
		{
			listDiseaseMapped=(List)request_p.getSession().getAttribute(OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE);
			for(int i=0;i<listDiseaseMapped.size();i++)
			{
				if(!fb_p.getDeleteIndex().equals(String.valueOf(i)))
				{
					listMappedDisease.add(listDiseaseMapped.get(i));
				}
			}
			WebUTIL.setAttributeInSession(request_p, OpdConfig.LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE, listMappedDisease);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request_p,objStatus);
		}
	}
}
