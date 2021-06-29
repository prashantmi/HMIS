package inpatient.masters.controller.util;

/**
 * @author  CDAC
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.ui.Size2D;

import inpatient.InpatientConfig;
import inpatient.masters.controller.*;
import inpatient.masters.controller.data.UnitInvParaMappingMstDATA;
import inpatient.masters.controller.fb.UnitInvParaMappingMstFB;

import registration.RegistrationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

import opd.OpdConfig;
import opd.master.controller.data.ModifyViewUserDeskMenuMasterDATA;
import opd.master.controller.fb.UserDeskMenuTemplateMasterFB;

public class unitInvParaMappingMstUTL extends ControllerUTIL 
{
	//* Setting Essentials for User Desk Menu Template Master
	public static void setEssential(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			if(_fb.getAdditionMode()==InpatientConfig.UNITWISE_MODE)
				mp=UnitInvParaMappingMstDATA.getEssentials(userVO);
			
			if(_fb.getAdditionMode().equals(InpatientConfig.UNITWARD_WISE))
			{	_fb.setIsGoPressed(InpatientConfig.STEP0);
				mp=UnitInvParaMappingMstDATA.getEssentialsForWardWise(userVO);
			}
			
			List lstPara=UnitInvParaMappingMstDATA.getParameter(userVO);
			mp.put(InpatientConfig.EssentialBO_LIST_ALL_PARAMETER,lstPara);
			
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	public static void setEssentialForModify(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();		
		Map mp=new HashMap();
		UnitInvParaMappingVO _unitInvParaMapVO = new UnitInvParaMappingVO();
		_unitInvParaMapVO.setUnitId(_fb.getUnitId());
		_unitInvParaMapVO.setSlNo(_fb.getSlNo());
		_unitInvParaMapVO.setWardCode(_fb.getWardCode());
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			if(_fb.getWardCode()==null)
				mp=UnitInvParaMappingMstDATA.getEssentialsForModify(userVO);
			else
			{	
				mp=UnitInvParaMappingMstDATA.getEssentialsForWardWise(userVO);
			}
			
			List lstWards = new ArrayList();
			List paraList = new ArrayList();
			
			if(_fb.getWardCode()==null)
			{
				List lstPara=UnitInvParaMappingMstDATA.getParameterForModify(_unitInvParaMapVO,userVO);//List of unselected Parameters in unit wise mode
				mp.put(InpatientConfig.EssentialBO_LIST_ALL_PARAMETERFORMODIFY,lstPara);
			}
			else
			{
				List lstParaForWardWise=UnitInvParaMappingMstDATA.getParameterForWardWise(_unitInvParaMapVO,userVO);//List of unselected Parameters in unitWard wise mode
				mp.put(InpatientConfig.EssentialBO_LIST_ALL_PARAMETERFORWARDWISE,lstParaForWardWise);
			}
			
			if(_fb.getWardCode()==null)
			{
				paraList = UnitInvParaMappingMstDATA.getParaListForModify(_unitInvParaMapVO.getSlNo(),_unitInvParaMapVO.getUnitId(),_unitInvParaMapVO.getWardCode(),userVO);
				mp.put(InpatientConfig.EssentialBO_LIST_PARAMETERSFORMODIFY,paraList); //List of selected parameters unitwise
			}
			else
			{
				paraList = UnitInvParaMappingMstDATA.getParaListForWardWise(_unitInvParaMapVO.getSlNo(),_unitInvParaMapVO.getUnitId(),_unitInvParaMapVO.getWardCode(),userVO);
				mp.put(InpatientConfig.EssentialBO_LIST_PARAMETERSFORWARDWISE,paraList); //List of selected parameters unitward Wise
			}
			if(_fb.getWardCode()!=null)
			{
				lstWards=UnitInvParaMappingMstDATA.getWardsForModify(_unitInvParaMapVO.getUnitId(),userVO);
				mp.put(InpatientConfig.EssentialBO_LIST_WARDS_FORMODIFY,lstWards);
			}
			WebUTIL.setMapInSession(mp,_request);
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	public static void setSelectedUnits(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(InpatientConfig.EssentialBO_LIST_ALL_UNITSFORWARDWISE);
			ArrayList alUnits=new ArrayList();
			String[] selunits=_fb.getSelectedUnit();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selunits.length;j++ )
					if(selunits[j].equals(entobj.getValue()))
						alUnits.add(entobj);
			}
			mp.put(InpatientConfig.SELECTED_UNIT,alUnits);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
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
	
	
	public static void getWardListByUnits(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstWards=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			lstWards=UnitInvParaMappingMstDATA.getWards(_fb.getUnitId(),userVO);
			mp.put(InpatientConfig.EssentialBO_LIST_WARDS_FORSPECIFICUNITS,lstWards);
			
			WebUTIL.setMapInSession(mp,_request);
			if(lstWards.size()==0)
			{
				objStatus.add(Status.DONE,"No Ward Exist For Selected Unit","");
			}
			else
			objStatus.add(Status.NEW);
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
	
	
	public static boolean saveUnitWise(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		List lstSelPara=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			HttpSession session = _request.getSession();
			int length = _fb.getSelectedPara().length;
			UnitInvParaMappingVO voUDMT =new UnitInvParaMappingVO();
			voUDMT.setUnitId(_fb.getUnitId());

			List listOfPara = new ArrayList();
			for(int i=0;i<length;i++)
			{
				voUDMT.setParaId(_fb.getSelectedPara()[i]);
				listOfPara.add(voUDMT.getParaId());
				UnitInvParaMappingMstDATA.saveUnitWise(voUDMT,userVO);
			}
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
	
	public static boolean saveUnitWiseForModify(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		List lstSelPara=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			HttpSession session = _request.getSession();
			
			int length = _fb.getSelectedParaForModify().length;
			UnitInvParaMappingVO voUDMT =new UnitInvParaMappingVO();
			voUDMT.setUnitId(_fb.getUnitId());
			
			UnitInvParaMappingMstDATA.updateTableUnitWise(_fb.getUnitId(),userVO);
			
			List listOfPara = new ArrayList();
			for(int i=0;i<length;i++)
			{
				voUDMT.setParaId(_fb.getSelectedParaForModify()[i]);
				listOfPara.add(voUDMT.getParaId());
				UnitInvParaMappingMstDATA.saveUnitWise(voUDMT,userVO);
			}
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
	
	
	public static boolean saveUnitWardWise(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		List lstSelPara=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			HttpSession session = _request.getSession();
			int length = _fb.getSelectedPara().length;
			
			UnitInvParaMappingVO voUDMT =new UnitInvParaMappingVO();
			voUDMT.setUnitId(_fb.getUnitId());
	
			List listOfPara = new ArrayList();
			for(int i=0;i<length;i++)
			{
				voUDMT.setWardCode(_fb.getWardCode());
				voUDMT.setParaId(_fb.getSelectedPara()[i]);
				listOfPara.add(voUDMT.getParaId());
				UnitInvParaMappingMstDATA.saveUnitWardWise(voUDMT,userVO);
			}
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
	
	public static boolean saveUnitWardWiseForModify(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelPara=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			HttpSession session = _request.getSession();
			int length = _fb.getSelectedParaForModify().length;
			UnitInvParaMappingVO voUDMT =new UnitInvParaMappingVO();
			voUDMT.setUnitId(_fb.getUnitId());
	
			List listOfPara = new ArrayList();
			UnitInvParaMappingMstDATA.updateTableWardWise(_fb.getUnitId(),_fb.getWardCode(),userVO);
			for(int i=0;i<length;i++)
			{
				voUDMT.setWardCode(_fb.getWardCode());
				voUDMT.setParaId(_fb.getSelectedParaForModify()[i]);
				listOfPara.add(voUDMT.getParaId());
				UnitInvParaMappingMstDATA.saveUnitWardWise(voUDMT,userVO);
			}
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
	
	//* Fetching User Desk Menu Record
	public static void fetchData(UnitInvParaMappingMstFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List al = new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			Map mp=new HashMap();
			UnitInvParaMappingVO voInvParaMst=new UnitInvParaMappingVO();
			UnitInvParaMappingVO[] invParaMappingArray = null;
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			voInvParaMst.setUnitId(concatid[0]);
			voInvParaMst.setSlNo(concatid[1]);
			voInvParaMst.setHospCode(concatid[2]);
			
			voInvParaMst = UnitInvParaMappingMstDATA.fetchData(voInvParaMst,userVO);
			
			_fb.setUnitId(voInvParaMst.getUnitId());
			_fb.setWardCode(voInvParaMst.getWardCode());
			_fb.setParaId(voInvParaMst.getParaId());
			_fb.setDisplayValue(voInvParaMst.getDisplayValue());
			_fb.setSlNo(voInvParaMst.getSlNo());
	
			String selUnit=_fb.getUnitId();

			int unit=Integer.parseInt(selUnit);
			UserDeskMenuMasterVO unitName=ModifyViewUserDeskMenuMasterDATA.getUnitName(selUnit,userVO);
			_fb.setUnitName(unitName.getUnitName());
			voInvParaMst.setUnitName(_fb.getUnitName());
			voInvParaMst.setUnitId(unitName.getDeptUnitCode());
			_fb.setUnitId(voInvParaMst.getUnitId());
					
			String selWard = _fb.getWardCode();
			if(_fb.getWardCode()!=null)
			{
				int ward=Integer.parseInt(selWard);
				UnitInvParaMappingVO Ward=UnitInvParaMappingMstDATA.getWardName(voInvParaMst,userVO);
				_fb.setWardName(Ward.getWardName());
				_fb.setWardCode(Ward.getWardCode());
				voInvParaMst.setWardCode(_fb.getWardCode());
				voInvParaMst.setWardName(_fb.getWardName());
			}
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

}
