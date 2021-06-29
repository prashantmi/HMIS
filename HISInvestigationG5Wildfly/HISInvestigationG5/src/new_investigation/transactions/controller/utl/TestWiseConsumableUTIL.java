package new_investigation.transactions.controller.utl;
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
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
import new_investigation.transactions.controller.data.TestWiseConsumableDATA;
import new_investigation.transactions.controller.fb.TestWiseConsumableFB;
import new_investigation.transactions.controller.fb.TestWiseConsumableFB;
import new_investigation.transactions.delegate.InvResultEntryDelegate;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TestWiseConsumableVO;
import new_investigation.vo.template.TestWiseConsumableVO;


public class TestWiseConsumableUTIL extends ControllerUTIL
{
	public static void getEssential(TestWiseConsumableFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
		TestWiseConsumableVO testWiseConsumableVO=new TestWiseConsumableVO();





		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 


			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
	
			mp=TestWiseConsumableDATA.LabComboForResultEntry(testWiseConsumableVO, userVO);
			WebUTIL.setMapInSession(mp, request);
			
			  String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  testWiseConsumableVO.setFromDate(tDate);
			  testWiseConsumableVO.setToDate(tDate);
			  testWiseConsumableVO.setLabCode("%");
			  testWiseConsumableVO.setNewEntry("1");
			  testWiseConsumableVO.setOnLoadValue("ONLOAD");
			  
				mpp=TestWiseConsumableDATA.setPatientResultEntryEssentials(testWiseConsumableVO, userVO);
				WebUTIL.setMapInSession(mpp, request);
			  
				fb.setLabCode("%");
			//HelperMethods.populate(fb, testWiseConsumableVO);
				objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			



	}


	




	public static boolean getEntryTypeDetails(TestWiseConsumableFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestWiseConsumableVO testWiseConsumableVO = new TestWiseConsumableVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			testWiseConsumableVO.setFromDate(fb.getFromDate()); 
			testWiseConsumableVO.setToDate(fb.getToDate());
			testWiseConsumableVO.setPatCRNo(fb.getPatCRNo()); 
			testWiseConsumableVO.setLabCode(fb.getLabCode());
			testWiseConsumableVO.setNewEntry(fb.getNewEntry());

			mp=TestWiseConsumableDATA.setPatientResultEntryEssentials(testWiseConsumableVO, userVO); 
			WebUTIL.setMapInSession(mp, _request);



			

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean setPatientEssentials(TestWiseConsumableFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestWiseConsumableVO testWiseConsumableVO = new TestWiseConsumableVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 

			testWiseConsumableVO.setFromDate(fb.getFromDate()); 
			testWiseConsumableVO.setToDate(fb.getToDate());
			testWiseConsumableVO.setPatCRNo(fb.getPatCRNo()); 
			testWiseConsumableVO.setLabCode(fb.getLabCode());
			testWiseConsumableVO.setTestWiseCode(fb.getTestWiseCode());
			testWiseConsumableVO.setFromLabNo(fb.getFromLabNo());
			testWiseConsumableVO.setToLabNo(fb.getToLabNo());
			testWiseConsumableVO.setFromSampleNo(fb.getFromSampleNo());
			testWiseConsumableVO.setToSampleNo(fb.getToSampleNo());
			testWiseConsumableVO.setGenerationType(fb.getGenerationType());
			testWiseConsumableVO.setTestGroupCode(fb.getTestGroupCodeWise());
			testWiseConsumableVO.setOnLoadValue(fb.getOnLoadValue());
			testWiseConsumableVO.setNewEntry(fb.getNewEntry());
			testWiseConsumableVO.setTempPatName(fb.getTempPatName());
			mp=TestWiseConsumableDATA.setPatientResultEntryEssentials(testWiseConsumableVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			

			//HelperMethods.populate(fb, testWiseConsumableVO);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}




public static boolean getConsumableList(TestWiseConsumableFB fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	TestWiseConsumableVO testWiseConsumableVO = new TestWiseConsumableVO();
	try
	{
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		ControllerUTIL.setSysdate(_request);
		Map mp=new HashMap(); 
		//WebUTIL.populate(testWiseConsumableVO, fb);
		String labCode=fb.getChkSamplePatient()[0].split("#")[4];
		String crNo=fb.getChkSamplePatient()[0].split("#")[0];
		String requisitionNo=fb.getChkSamplePatient()[0].split("#")[1];
		String requisitionDNo=fb.getChkSamplePatient()[0].split("#")[2];
		testWiseConsumableVO.setLabCode(labCode);
		testWiseConsumableVO.setRequisitionNo(requisitionNo);
		testWiseConsumableVO.setRequisitionDNo(requisitionDNo);
		testWiseConsumableVO.setCrNo(crNo);
		mp=TestWiseConsumableDATA.getTestWiseConsumableList(testWiseConsumableVO, userVO);
		/*Entry []entryArray=(Entry[])mp.get(InvestigationConfig.LIST_TEST_WISE_CONSUMABLE_LOT);
		String []lotArray=new String[entryArray.length];
		String []QuantityArray=new String[entryArray.length];*/
		//boolean flag=false;
		/*for(int i=0;i<entryArray.length;i++)
		{
			
			lotArray[i]=entryArray[i].getLabel();
			//QuantityArray[i]=entryArray[i].getValue();
			flag=true;
			
			
		}
		if(flag)
		{
			fb.setLotNo(lotArray);
			//fb.setDefaultQuantity(QuantityArray);
		}*/
		WebUTIL.setMapInSession(mp, _request);
	
		objStatus.add(Status.TRANSINPROCESS);
	}
	catch (HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL, "", "No Test Found,Please Map Test For This Lab!");
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA, "", "Data Access Error");
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE, "", "No Items Found,Please Map Items For The Selected Laboratory");
	}
	catch (HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR, "", "Error");
	}
	finally
	{
		WebUTIL.setStatus(_request, objStatus);
	}
	return true;
}


public static boolean saveConsumableListValues(TestWiseConsumableFB fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	TestWiseConsumableVO testWiseConsumableVO = new TestWiseConsumableVO();
	int length=0;
	HttpSession session=_request.getSession();
	try
	{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			Map mp=new HashMap(); 
			//WebUTIL.populate(testWiseConsumableVO, fb);
			
			TestWiseConsumableVO []testWiseConsumableVOs=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE);
			TestWiseConsumableVO []tempTestWiseConsumableVOs=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL);
			if(fb.getTempSelectedItem()!=null){
			for(int i=0;i<fb.getTempSelectedItem().length;i++)
			{
				int lotLength=0;
				if(testWiseConsumableVOs!=null)
				{
					lotLength=testWiseConsumableVOs.length+i;
				}
				else
				{
					lotLength=i;
				}
				if(fb.getSelectedItem()!=null){length=fb.getSelectedItem().length+i;}
				else{length=i;}
				
				testWiseConsumableVO=tempTestWiseConsumableVOs[Integer.parseInt(fb.getTempSelectedItem()[i])];
				String labCode=fb.getSelectedCheckbox().split("#")[4];
				
				String testCode=fb.getSelectedCheckbox().split("#")[5];
				if(testCode.endsWith("@"))
				{
					testCode=testCode.substring(0,testCode.length()-1);
				}
				String crNo=fb.getSelectedCheckbox().split("#")[0];
				String requisitionNo=fb.getSelectedCheckbox().split("#")[1];
				String requisitionDNo=fb.getSelectedCheckbox().split("#")[2];
				
				if(fb.getLotNo()[lotLength]!=null&&!fb.getLotNo()[lotLength].equals("-1")){
					
				String []itemUsesDtl=fb.getLotNo()[lotLength].split("#");
				//
				testWiseConsumableVO.setLotNo(itemUsesDtl[0]);
				testWiseConsumableVO.setManufacture(itemUsesDtl[1]);
				testWiseConsumableVO.setExpiryDate(itemUsesDtl[2]);
				testWiseConsumableVO.setOtherItemID(itemUsesDtl[3]);
				testWiseConsumableVO.setStoreName(itemUsesDtl[4]);
				testWiseConsumableVO.setItemTypeID(itemUsesDtl[5]);
				testWiseConsumableVO.setItemID(itemUsesDtl[6]);
				testWiseConsumableVO.setLabCode(labCode);
				testWiseConsumableVO.setTestCode(testCode);
				testWiseConsumableVO.setRequisitionNo(requisitionNo);
				testWiseConsumableVO.setRequisitionDNo(requisitionDNo);
				testWiseConsumableVO.setCrNo(crNo);
				testWiseConsumableVO.setDefaultQuantity(fb.getDefaultQuantity()[lotLength]);
				if(testWiseConsumableVO.getStoreName().equals("null")){
					testWiseConsumableVO.setStoreName("-1");
					}
				if(testWiseConsumableVO.getItemTypeID().equals("null")){
					testWiseConsumableVO.setItemTypeID("-1");
					}
				if(testWiseConsumableVO.getItemID().equals("null")){
					testWiseConsumableVO.setItemID("-1");}
				if(testWiseConsumableVO.getUnitCode()==null){
					testWiseConsumableVO.setUnitCode("-1");
					}
				
			mp=TestWiseConsumableDATA.saveConsumableList(testWiseConsumableVO, userVO);
			}
			}
			}
			if(fb.getSelectedItem()!=null){
			for(int i=0;i<fb.getSelectedItem().length;i++)
			{
				
			testWiseConsumableVO=testWiseConsumableVOs[Integer.parseInt(fb.getSelectedItem()[i])];
			testWiseConsumableVOs[Integer.parseInt(fb.getSelectedItem()[i])]=null;
			
			String labCode=fb.getSelectedCheckbox().split("#")[4];
			
			String testCode=fb.getSelectedCheckbox().split("#")[5];
			if(testCode.endsWith("@"))
			{
				testCode=testCode.substring(0,testCode.length()-1);
			}
			String crNo=fb.getSelectedCheckbox().split("#")[0];
			String requisitionNo=fb.getSelectedCheckbox().split("#")[1];
			String requisitionDNo=fb.getSelectedCheckbox().split("#")[2];
			if(!fb.getLotNo()[i].equals("-1")){
				
			String []itemUsesDtl=fb.getLotNo()[Integer.parseInt(fb.getSelectedItem()[i])].split("#");
			testWiseConsumableVO.setLotNo(itemUsesDtl[0]);
			testWiseConsumableVO.setManufacture(itemUsesDtl[1]);
			testWiseConsumableVO.setExpiryDate(itemUsesDtl[2]);
			testWiseConsumableVO.setOtherItemID(itemUsesDtl[3]);
			testWiseConsumableVO.setStoreName(itemUsesDtl[4]);
			testWiseConsumableVO.setItemTypeID(itemUsesDtl[5]);
			testWiseConsumableVO.setItemID(itemUsesDtl[6]);
			testWiseConsumableVO.setSlNo(itemUsesDtl[7]);
			testWiseConsumableVO.setLabCode(labCode);
			testWiseConsumableVO.setTestCode(testCode);
			testWiseConsumableVO.setRequisitionNo(requisitionNo);
			testWiseConsumableVO.setRequisitionDNo(requisitionDNo);
			testWiseConsumableVO.setCrNo(crNo);
			testWiseConsumableVO.setDefaultQuantity(fb.getDefaultQuantity()[Integer.parseInt(fb.getSelectedItem()[i])]);
			if(testWiseConsumableVO.getStoreName().equals("null")){
				testWiseConsumableVO.setStoreName("-1");
				}
			if(testWiseConsumableVO.getItemTypeID().equals("null")){
				testWiseConsumableVO.setItemTypeID("-1");
				}
			if(testWiseConsumableVO.getItemID().equals("null")){
				testWiseConsumableVO.setItemID("-1");}
			if(testWiseConsumableVO.getUnitCode()==null){
				testWiseConsumableVO.setUnitCode("-1");
				}
			mp=TestWiseConsumableDATA.saveConsumableList(testWiseConsumableVO, userVO);
			}
			}}
			if(testWiseConsumableVOs!=null){
				String []delIndex=fb.getDeleteIndex().split(",");
			for(int i=0;i<delIndex.length;i++)
			{
				if(!delIndex[i].equals("")&&Integer.parseInt(delIndex[i])!=-1&&testWiseConsumableVOs[Integer.parseInt(delIndex[i])]!=null){
				testWiseConsumableVO=testWiseConsumableVOs[Integer.parseInt(delIndex[i])];
				String labCode=fb.getSelectedCheckbox().split("#")[4];
				
				String testCode=fb.getSelectedCheckbox().split("#")[5];
				if(testCode.endsWith("@"))
				{
					testCode=testCode.substring(0,testCode.length()-1);
				}
				String crNo=fb.getSelectedCheckbox().split("#")[0];
				String requisitionNo=fb.getSelectedCheckbox().split("#")[1];
				String requisitionDNo=fb.getSelectedCheckbox().split("#")[2];
				if(!fb.getLotNo()[Integer.parseInt(delIndex[i])].equals("-1")){
					
				String []itemUsesDtl=fb.getLotNo()[Integer.parseInt(delIndex[i])].split("#");
				testWiseConsumableVO.setLotNo(itemUsesDtl[0]);
				testWiseConsumableVO.setManufacture(itemUsesDtl[1]);
				testWiseConsumableVO.setExpiryDate(itemUsesDtl[2]);
				testWiseConsumableVO.setOtherItemID(itemUsesDtl[3]);
				testWiseConsumableVO.setStoreName(itemUsesDtl[4]);
				testWiseConsumableVO.setItemTypeID(itemUsesDtl[5]);
				testWiseConsumableVO.setItemID(itemUsesDtl[6]);
				testWiseConsumableVO.setLabCode(labCode);
				testWiseConsumableVO.setTestCode(testCode);
				testWiseConsumableVO.setRequisitionNo(requisitionNo);
				testWiseConsumableVO.setRequisitionDNo(requisitionDNo);
				testWiseConsumableVO.setCrNo(crNo);
				testWiseConsumableVO.setSlNo(itemUsesDtl[7]);
				testWiseConsumableVO.setDefaultQuantity(fb.getDefaultQuantity()[Integer.parseInt(delIndex[i])]);
				if(testWiseConsumableVO.getStoreName().equals("null")){
					testWiseConsumableVO.setStoreName("-1");
					}
				if(testWiseConsumableVO.getItemTypeID().equals("null")){
					testWiseConsumableVO.setItemTypeID("-1");
					}
				if(testWiseConsumableVO.getItemID().equals("null")){
					testWiseConsumableVO.setItemID("-1");}
				if(testWiseConsumableVO.getUnitCode()==null){
					testWiseConsumableVO.setUnitCode("-1");
					}
				/*if(testWiseConsumableVO.getStoreName()==null){testWiseConsumableVO.setStoreName("-1");}
				if(testWiseConsumableVO.getStoreName()==null){testWiseConsumableVO.setStoreName("-1");}
				if(testWiseConsumableVO.getItemTypeID()==null){testWiseConsumableVO.setItemTypeID("-1");}
				if(testWiseConsumableVO.getItemID()==null){testWiseConsumableVO.setItemID("-1");}
				if(testWiseConsumableVO.getUnitCode()==null){testWiseConsumableVO.setUnitCode("0");}*/
				//if(testWiseConsumableVO.getStoreName().equals("null")){testWiseConsumableVO.setStoreName("-1");}
				mp=TestWiseConsumableDATA.updateConsumableList(testWiseConsumableVO, userVO);
				}
			}
			}}
			WebUTIL.setMapInSession(mp, _request);
			//HelperMethods.populate(fb, testWiseConsumableVO);
			
			objStatus.add(Status.TRANSINPROCESS);
			
			}
			
			catch (HisRecordNotFoundException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.ERROR_AE, "", "No Items Found,Please Map Items For The Selected Laboratory");
			}
			catch (HisException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
				 session.removeAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL);
				 session.removeAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE);
				//TestWiseConsumableVO []testWiseConsumableVOs=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE);
				//TestWiseConsumableVO []tempTestWiseConsumableVOs=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL);
			}
			return true;
			}
public static boolean getPatientDetails(TestWiseConsumableFB fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	TestWiseConsumableVO testWiseConsumableVO = new TestWiseConsumableVO();
	try
	{
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		ControllerUTIL.setSysdate(_request);
		Map mp=new HashMap(); 
		//WebUTIL.populate(testWiseConsumableVO, fb);
		String labCode=fb.getChkSamplePatient()[0].split("#")[4];
		String crNo=fb.getChkSamplePatient()[0].split("#")[0];
		String requisitionNo=fb.getChkSamplePatient()[0].split("#")[1];
		String requisitionDNo=fb.getChkSamplePatient()[0].split("#")[2];
		testWiseConsumableVO.setLabCode(labCode);
		testWiseConsumableVO.setRequisitionNo(requisitionNo);
		testWiseConsumableVO.setRequisitionDNo(requisitionDNo);
		testWiseConsumableVO.setCrNo(crNo);
		mp=TestWiseConsumableDATA.getPatientDetails(testWiseConsumableVO, userVO);
		
		
		WebUTIL.setMapInSession(mp, _request);
	
		objStatus.add(Status.TRANSINPROCESS);
	}
	catch (HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL, "", "No Test Found,Please Map Test For This Lab!");
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA, "", "Data Access Error");
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE, "", "No Items Found,Please Map Items For The Selected Laboratory");
	}
	catch (HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR, "", "Error");
	}
	finally
	{
		WebUTIL.setStatus(_request, objStatus);
	}
	return true;
}


public static boolean reFetchConsumableList(TestWiseConsumableFB fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	TestWiseConsumableVO testWiseConsumableVO = new TestWiseConsumableVO();
	try
	{
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		ControllerUTIL.setSysdate(_request);
		Map mp=new HashMap(); 
		//WebUTIL.populate(testWiseConsumableVO, fb);
		String labCode=fb.getSelectedCheckbox().split("#")[4];
		String crNo=fb.getSelectedCheckbox().split("#")[0];
		String requisitionNo=fb.getSelectedCheckbox().split("#")[1];
		String requisitionDNo=fb.getSelectedCheckbox().split("#")[2];
		testWiseConsumableVO.setLabCode(labCode);
		testWiseConsumableVO.setRequisitionNo(requisitionNo);
		testWiseConsumableVO.setRequisitionDNo(requisitionDNo);
		testWiseConsumableVO.setCrNo(crNo);
		mp=TestWiseConsumableDATA.getTestWiseConsumableList(testWiseConsumableVO, userVO);
		/*Entry []entryArray=(Entry[])mp.get(InvestigationConfig.LIST_TEST_WISE_CONSUMABLE_LOT);
		String []lotArray=new String[entryArray.length];
		String []QuantityArray=new String[entryArray.length];*/
		//boolean flag=false;
		/*for(int i=0;i<entryArray.length;i++)
		{
			
			lotArray[i]=entryArray[i].getLabel();
			//QuantityArray[i]=entryArray[i].getValue();
			flag=true;
			
			
		}
		if(flag)
		{
			fb.setLotNo(lotArray);
			//fb.setDefaultQuantity(QuantityArray);
		}*/
		WebUTIL.setMapInSession(mp, _request);
	
		objStatus.add(Status.TRANSINPROCESS);
	}
	catch (HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL, "", "No Test Found,Please Map Test For This Lab!");
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA, "", "Data Access Error");
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE, "", "No Items Found,Please Map Items For The Selected Laboratory");
	}
	catch (HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR, "", "Error");
	}
	finally
	{
		WebUTIL.setStatus(_request, objStatus);
	}
	return true;
}








}
