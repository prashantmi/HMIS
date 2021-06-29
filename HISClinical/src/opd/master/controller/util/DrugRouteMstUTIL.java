package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DrugDoseMasterDATA;
import opd.master.controller.data.DrugRouteMstDATA;
import opd.master.controller.fb.DrugDoseMasterFB;
import opd.master.controller.fb.DrugRouteMstFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.UserVO;

public class DrugRouteMstUTIL extends ControllerUTIL
{
		
			public static void getItemTypeName(DrugRouteMstFB _fb, HttpServletRequest _request)
			 {
				Status objStatus = new Status();
				List itemTypeList=null;
				Map essentialMap=new HashMap();
				DrugRouteMstVO _drugRouteMstVO = new DrugRouteMstVO();
				try
				{
					UserVO userVO = getUserVO(_request);
					setSysdate(_request);
						essentialMap =DrugRouteMstDATA.getDrugRouteMasterEssentails(userVO);
					itemTypeList=(List)essentialMap.get(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE);
					Iterator itr=itemTypeList.iterator();
					String itemType="";
					while(itr.hasNext())
					{
						Entry obj=(Entry)itr.next();
						if(obj.getValue().equals(_fb.getItemTypeId()))
						{
							itemType=obj.getLabel();
						}
					}
					_fb.setItemType(itemType);
					_fb.setIsFrequencyBound(OpdConfig.IS_FREQUENCY_BOUND);
					WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, itemTypeList);					objStatus.add(Status.NEW);		
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	// On Add Page saving Values into Database
	public static boolean saveDrugRouteInfo(DrugRouteMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DrugRouteMstVO _drugRouteMstVO = new DrugRouteMstVO();

			_drugRouteMstVO.setDrugRouteName(_fb.getDrugRouteName());
			_drugRouteMstVO.setDrugRouteDesc(_fb.getDrugRouteDesc());
			_drugRouteMstVO.setDrugRouteId(_fb.getDrugRouteId());
			_drugRouteMstVO.setItemTypeId(_fb.getItemTypeId());
			_drugRouteMstVO.setRouteClassification(_fb.getRouteClassification());
			hasFlag = DrugRouteMstDATA.saveDrugRouteInfo(_drugRouteMstVO, userVO);

			if (hasFlag)
			{
				objStatus.add(Status.INPROCESS, "Record Saved Successfully", "");
			}
			else
			{
				objStatus.add(Status.NEW, "Drug Route Already Exists", "");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;

		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return hasFlag;
	}

	public static boolean fetchDrugRouteInfo(DrugRouteMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DrugRouteMstVO _drugRouteMstVO = new DrugRouteMstVO();
		// Map mp = new HashMap();
		List drugRouteClassificationLst = new ArrayList();
		// String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			System.out.println("hi....."+ chk.split("@"));
			

			String sDrugRouteID = concatid[0];
			String sSlNo = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo

			_fb.setDrugRouteId(sDrugRouteID);
			_fb.setHospitalCode(sHtcode);
			_fb.setSerialNo(sSlNo);

			_drugRouteMstVO.setDrugRouteId(_fb.getDrugRouteId());
			_drugRouteMstVO.setSerialNo(sSlNo);
			_drugRouteMstVO.setHospitalCode(_fb.getHospitalCode());
			_drugRouteMstVO.setIsValid(_fb.getIsActive());

			_drugRouteMstVO = DrugRouteMstDATA.fetchDrugRouteInfo(_drugRouteMstVO, userVO);

			_fb.setIsActive(_drugRouteMstVO.getIsValid());
			_fb.setDrugRouteDesc(_drugRouteMstVO.getDrugRouteDesc());
			_fb.setDrugRouteName(_drugRouteMstVO.getDrugRouteName());
			_fb.setItemTypeId(_drugRouteMstVO.getDrugRouteId());
			//Commited By Chetan Sharma.			
			//_fb.setItemTypeId(_drugRouteMstVO.getItemTypeId());
			_fb.setDrugRouteId(sDrugRouteID);
			_fb.setSerialNo(sSlNo);
			_fb.setRouteClassification(_drugRouteMstVO.getRouteClassification());
			_drugRouteMstVO = DrugRouteMstDATA.getItemName(_fb.getItemTypeId(), userVO);
			_fb.setItemTypeName(_drugRouteMstVO.getItemTypeName());
			// _fb.setLength(_drugRouteMstVO.getLength());

			for (int i = 0; i < OpdConfig.DRUG_ROUTE_CLASSIFICATION_ARRAY.length - 1; i++)
			{
				Entry ent = new Entry();
				ent.setValue(String.valueOf(i+1));
				ent.setLabel(OpdConfig.DRUG_ROUTE_CLASSIFICATION_ARRAY[i + 1]);
				
				drugRouteClassificationLst.add(ent);
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.DRUG_ROUTE_CLASSIFICATION_LIST, drugRouteClassificationLst);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean saveModDrugRouteInfo(DrugRouteMstFB _fb, HttpServletRequest _request)
	{
		boolean flg = true;
		Status objStatus = new Status();

		try
		{
			UserVO userVO = getUserVO(_request);
			DrugRouteMstVO _drugRouteMstVO = new DrugRouteMstVO();
			_drugRouteMstVO.setDrugRouteName(_fb.getDrugRouteName());
			_drugRouteMstVO.setDrugRouteDesc(_fb.getDrugRouteDesc());
			_drugRouteMstVO.setItemTypeId(_fb.getItemTypeId());
			_drugRouteMstVO.setDrugRouteId(_fb.getDrugRouteId());
			_drugRouteMstVO.setSerialNo(_fb.getSerialNo());
			_drugRouteMstVO.setIsValid(_fb.getIsActive());
			_drugRouteMstVO.setRouteClassification(_fb.getRouteClassification());

			DrugRouteMstDATA.saveModDrugRouteInfo(_drugRouteMstVO, userVO);

			objStatus.add(Status.TRANSINPROCESS, "Record Modified Successfully","");
		}
		catch (HisRecordNotFoundException e)
		{
			flg = false;
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			flg = false;
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flg = false;
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flg;
	}
	
	// Added By Chetan 
	// Date: 07_12_2015
	
	public static boolean getDrugRouteInfo(DrugRouteMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DrugRouteMstVO _drugRouteMstVO = new DrugRouteMstVO();
		// Map mp = new HashMap();
		List drugRouteClassificationLst = new ArrayList();
		// String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			// Fetching Selected Record Primary Key
			/*
						String chk = _fb.getChk()[0].replace("^", "@");
			System.out.println("hi....."+ chk.split("@"));
			String[] concatid = chk.split("@");
			System.out.println("hi....."+ chk.split("@"));
			

			String sDrugRouteID = concatid[0];
			String sSlNo = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
		_fb.setDrugRouteId(sDrugRouteID);
			_fb.setHospitalCode(sHtcode);
			_fb.setSerialNo(sSlNo);

			_drugRouteMstVO.setDrugRouteId(_fb.getDrugRouteId());
			_drugRouteMstVO.setSerialNo(sSlNo);
*/			_drugRouteMstVO.setHospitalCode(_fb.getHospitalCode());
			_drugRouteMstVO.setIsValid(_fb.getIsActive());

			_drugRouteMstVO = DrugRouteMstDATA.getDrugRouteInfo(_drugRouteMstVO, userVO);

			_fb.setIsActive(_drugRouteMstVO.getIsValid());
			
			_fb.setDrugRouteName(_drugRouteMstVO.getDrugRouteName());
			_fb.setItemTypeId(_drugRouteMstVO.getDrugRouteId());
			
			_fb.setDrugRouteId(_drugRouteMstVO.getDrugRouteId());
		
			//_fb.setSerialNo(sSlNo);
			_fb.setRouteClassification(_drugRouteMstVO.getRouteClassification());
			/*_drugRouteMstVO = DrugRouteMstDATA.getItemName(_fb.getItemTypeId(), userVO);
			_fb.setItemTypeName(_drugRouteMstVO.getItemTypeName());*/
			 //_fb.setLength(_drugRouteMstVO.getLength());

			for (int i = 0; i < OpdConfig.DRUG_ROUTE_CLASSIFICATION_ARRAY.length - 1; i++)
			{
				Entry ent = new Entry();
				ent.setValue(String.valueOf(i+1));
				ent.setLabel(OpdConfig.DRUG_ROUTE_CLASSIFICATION_ARRAY[i + 1]);
				
				drugRouteClassificationLst.add(ent);
			}
			System.out.println("+++++++"+drugRouteClassificationLst);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.DRUG_ROUTE_CLASSIFICATION_LIST, drugRouteClassificationLst);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
}
