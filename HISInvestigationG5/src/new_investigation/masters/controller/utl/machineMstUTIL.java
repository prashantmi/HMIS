package new_investigation.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import new_investigation.vo.machineMstVO;
import new_investigation.vo.machineMstVO;
import hisglobal.vo.PatientCategoryVerificationMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;






import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.machineMstDATA;
import new_investigation.masters.controller.data.machineMstDATA;
import new_investigation.masters.controller.fb.machineMstFB;
import new_investigation.masters.controller.fb.machineMstFB;

public class machineMstUTIL implements MasterInterface
{
	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session)
	{
		this.httpSession = session;
	}

	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
		br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record'  border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'; title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;'; title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		br.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer;' title='cancel page'  border=0  tabindex='1' onKeyPress='cancelFunc();'  onClick='cancelFunc();'>");
		return br.toString();
	}

	public String[] getColsWidth() 
	{
		return null;
	}


	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Machine","Format" };
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[1];
		comboQuery[0] = "1^Active";
		//#2^InActive";
		return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.MACHINE.HMIT_MACHINE_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.MACHINE.HMIT_MACHINE_MST.COND.0"));

		return deleteQuery;
	}


	public String getJsFiles() 
	{
		// TODO Auto-generated method stub
		return null;
	}



	public String getMainQuery(String[] cmb)
	{

		//	UserVO userVO;
		StringBuffer brMainQuery = new StringBuffer();
		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();
		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(hosCode);


		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"machineMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}

		return brMainQuery.toString();
	}


	public String getMasterName()
	{
		String masterName = "Machine Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "HMISTR_MACHINE_NAME" };
		return orderBy;
	}

	public int getPage_per_block()
	{
		return 10;
	}


	public int getRecord_per_page() 
	{
		return 10;
	}


	public String[] getSearchField() 
	{		
		String strSearchField[] = { "1", " UPPER(hmistr_machine_name) "};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Machine");
		viewHdr.add("D");


		viewHdr.add("Format");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.machine.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveMachine(machineMstFB machinemst_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		List<machineMstVO> commPortDtl= new ArrayList<machineMstVO>();
		machineMstVO[] delete_machinemst_VO = null;
		List insertList=new ArrayList();
		List deleteList=new ArrayList();


		boolean saveFlag = true;
		try
		{
			//to store the required value of machine
			machineMstVO _machine=new machineMstVO();
			
			_machine.setMachineName(machinemst_fb.getMachineName());
			_machine.setStatus(machinemst_fb.getStatus());
			_machine.setFormat(machinemst_fb.getFormat());
			_machine.setCommFlag(machinemst_fb.getCommFlag());
			_machine.setLocationCode(machinemst_fb.getLocationCode());
			_machine.setValidationType(machinemst_fb.getValidationType());
			_machine.setArchivalDays(machinemst_fb.getArchivalDays());
			
			
			
			
			
			for(int i=0; i<machinemst_fb.getchkCommPort().length;i++)
			{
				String flag="0";
				machineMstVO vo_machine=new machineMstVO();
				
				
				vo_machine.setCommPortFlag(machinemst_fb.getchkCommPort()[i]);
				vo_machine.setDefaultModep(machinemst_fb.getDefaultMode()[i]);
				vo_machine.setBaudRate(machinemst_fb.getBaudRate()[i]);
				vo_machine.setByteSize(machinemst_fb.getByteSize()[i]);
				vo_machine.setParity(machinemst_fb.getParity()[i]);
				vo_machine.setStopBit(machinemst_fb.getStopBit()[i]);
				vo_machine.setReadTimeOut(machinemst_fb.getReadTimeOut()[i]);
				vo_machine.setWriteTimeOut(machinemst_fb.getWriteTimeOut()[i]);
				vo_machine.setFlowControl(machinemst_fb.getFlowControl()[i]);
				vo_machine.setFlowParity(machinemst_fb.getFlowParity()[i]);
				vo_machine.setFormat(machinemst_fb.getFormat());
				vo_machine.setCommFlag(machinemst_fb.getCommFlag());
				
				
				
				commPortDtl.add(vo_machine);
				
			}

		
			//Added by krishnan nema on 08/10/2018
			if(machinemst_fb.getMachineType().equals("1")){
				_machine.setMachineType("2");
			}else{
				_machine.setMachineType("1");
			}
			
			UserVO userVO = ControllerUTIL.getUserVO(_request);
				
			machineMstDATA.saveMachine(commPortDtl,_machine, userVO);
			objStatus.add(Status.DONE, "Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,  e.getMessage(),"");
		}
		finally
		{

			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return saveFlag;
	}

	
	//savemodify
	public static boolean saveModifyMachine(machineMstFB machinemst_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		List<machineMstVO> commPortDtl= new ArrayList<machineMstVO>();
		List<machineMstVO> oldCommPortDtl= new ArrayList<machineMstVO>();
		machineMstVO[] delete_machinemst_VO = null;
		List oldComms=new ArrayList();
		List newComms=new ArrayList();
		List<machineMstVO> insertCommPortDtl= new ArrayList<machineMstVO>();
		List<machineMstVO> deleteCommPortDtl= new ArrayList<machineMstVO>();

		oldCommPortDtl	=(List<machineMstVO>)_request.getSession().getAttribute(InvestigationConfig.MACHINE_PREVIOUS_COMMPORTS_DETAILS);
		
		boolean saveFlag = true;
		try
		{
			//to store the required value of machine
			machineMstVO _machine=new machineMstVO();
			
			_machine.setMachineName(machinemst_fb.getMachineName());
			_machine.setStatus(machinemst_fb.getStatus());
			_machine.setFormat(machinemst_fb.getFormat());
			_machine.setCommFlag(machinemst_fb.getCommFlag());
			_machine.setLocationCode(machinemst_fb.getLocationCode());
			_machine.setValidationType(machinemst_fb.getValidationType());
			_machine.setArchivalDays(machinemst_fb.getArchivalDays());
			_machine.setMachineCode(machinemst_fb.getMachineCode());
			
			
			
			
			for(int i=0; i<machinemst_fb.getchkCommPort().length;i++)
			{
				String flag="0";
				machineMstVO vo_machine=new machineMstVO();
				
				
				vo_machine.setCommPortFlag(machinemst_fb.getchkCommPort()[i]);
				newComms.add(machinemst_fb.getchkCommPort()[i]);
				vo_machine.setDefaultModep(machinemst_fb.getDefaultMode()[i]);
				vo_machine.setBaudRate(machinemst_fb.getBaudRate()[i]);
				vo_machine.setByteSize(machinemst_fb.getByteSize()[i]);
				vo_machine.setParity(machinemst_fb.getParity()[i]);
				vo_machine.setStopBit(machinemst_fb.getStopBit()[i]);
				vo_machine.setReadTimeOut(machinemst_fb.getReadTimeOut()[i]);
				vo_machine.setWriteTimeOut(machinemst_fb.getWriteTimeOut()[i]);
				vo_machine.setFlowControl(machinemst_fb.getFlowControl()[i]);
				vo_machine.setFlowParity(machinemst_fb.getFlowParity()[i]);
				vo_machine.setFormat(machinemst_fb.getFormat());
				vo_machine.setCommFlag(machinemst_fb.getCommFlag());
				
				
				
				commPortDtl.add(vo_machine);
				
			}

		
			//compare commports 
			for(int i=0;i<oldCommPortDtl.size();i++)
			{
				oldComms.add(oldCommPortDtl.get(i).getCommPortFlag());
								
			}
			
			List insertList=machineMstUTIL.getCompareList(newComms, oldComms);
			List deleteList=machineMstUTIL.getCompareList(oldComms, newComms);
			
			
			

			UserVO userVO = ControllerUTIL.getUserVO(_request);
				
			machineMstDATA.saveModifyMachine(commPortDtl,_machine,deleteList, userVO);
			objStatus.add(Status.DONE, "Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,  e.getMessage(),"");
		}
		finally
		{

			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return saveFlag;
	}


	public static boolean fetchMachineDetails(machineMstFB machinemst_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		machineMstVO machinemst_VO = new machineMstVO();
		machineMstVO temp_VO = new machineMstVO();
		
		List<machineMstVO> machine_lstVO=new ArrayList<machineMstVO>();
		List<machineMstVO> final_machine_lstVO=new ArrayList<machineMstVO>();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",machineCode="",hospitalCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = machinemst_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			machineCode = concatid[0];
			hospitalCode = concatid[1];



			machinemst_VO.setMachineCode(machineCode);
			machinemst_fb.setSelectedChk(machinemst_fb.getChk()[0]);


			mp.putAll(machineMstDATA.getEssentialMachineCombo(machinemst_VO, userVO));
			
			machine_lstVO=	machineMstDATA.fetchMachineDetails(machinemst_VO, userVO);
			
			if(machine_lstVO!=null)
			{
						
			//arranging commports serial wise
			for(int k=0;k<4;k++)
			{			
				for(int j=0;j<machine_lstVO.size();j++)
					{
														
					if(Integer.parseInt(machine_lstVO.get(j).getCommPortFlag())==(k+1))
					{
						temp_VO=machine_lstVO.get(j);
						
						machinemst_fb.setMachineName(temp_VO.getMachineName());
						machinemst_fb.setStatus(temp_VO.getStatus());
						machinemst_fb.setFormat(temp_VO.getFormat());
						machinemst_fb.setCommFlag(temp_VO.getCommFlag());
						machinemst_fb.setLocationCode(temp_VO.getLocationCode());
						machinemst_fb.setValidationType(temp_VO.getValidationType());
						machinemst_fb.setArchivalDays(temp_VO.getArchivalDays());
						machinemst_fb.setMachineCode(temp_VO.getMachineCode());
						
						
							break;
					}
					else
					{
						temp_VO.setCommPortFlag("0");
						temp_VO.setDefaultModep("0");
						temp_VO.setBaudRate("-1");
						temp_VO.setByteSize("-1");
						temp_VO.setParity("-1");
						temp_VO.setStopBit("-1");
						temp_VO.setReadTimeOut("-1");
						temp_VO.setWriteTimeOut("-1");
						temp_VO.setFlowControl("-1");
						temp_VO.setFlowParity("-1");
						temp_VO.setFormat("-1");
						temp_VO.setCommFlag("-1");					
					}
					
					}
				
				final_machine_lstVO.add(temp_VO);
				temp_VO=new machineMstVO();
				
				
			}
			
			}
				
				
			mp.put(InvestigationConfig.MACHINE_COMMPORTS_DETAILS,final_machine_lstVO);
			mp.put(InvestigationConfig.MACHINE_PREVIOUS_COMMPORTS_DETAILS,machine_lstVO);
				
			WebUTIL.setMapInSession(mp, _request);

			//machineMstDATA.fetchMachineDetails(machinemst_VO, userVO);
			


//			HelperMethods.populate(machinemst_fb, machinemst_VO);

			objStatus.add(Status.NEW);
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


	
	//fetching essential combos
	public static boolean getEssentialMachineCombo(machineMstFB machinemst_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		machineMstVO machinemst_VO = new machineMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);



			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=machineMstDATA.getEssentialMachineCombo(machinemst_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			machinemst_VO.setValidationType("2");
			machinemst_VO.setArchivalDays("3");
			HelperMethods.populate(machinemst_fb, machinemst_VO);


			objStatus.add(Status.NEW);
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

/*
	public static boolean reFetchCheckListLabCollectionArea(machineMstFB machinemst_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		machineMstVO machinemst_VO = new machineMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",locationCode="",wardCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = machinemst_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			labCode = concatid[2];
			hospitalCode = concatid[1];



			machinemst_VO.setIsActive(machinemst_fb.getIsActive());

			machinemst_fb.setLabCode(labCode); 
			machinemst_VO.setLabCode(labCode);
			machinemst_fb.setHospitalCode(hospitalCode); 
			machinemst_VO.setHospitalCode(hospitalCode);

			mp=machineMstDATA.getArea(machinemst_VO, userVO);


						mp=machineMstDATA.fetchCheckListLabCollectionArea(machinemst_VO, userVO);
			 			WebUTIL.setMapInSession(mp, _request);

			 machinemst_fb.setLabCode(labCode); 
			 machinemst_VO.setLabCode(labCode);
			 machinemst_fb.setHospitalCode(hospitalCode); 
			 machinemst_VO.setHospitalCode(hospitalCode);




			 			HelperMethods.populate(machinemst_fb, machinemst_VO);
			  
			 objStatus.add(Status.NEW);
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

	*/
	public static boolean reFetchMachineDetails(machineMstFB machinemst_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		machineMstVO machinemst_VO = new machineMstVO();
		machineMstVO temp_VO = new machineMstVO();
		
		List<machineMstVO> machine_lstVO=new ArrayList<machineMstVO>();
		List<machineMstVO> final_machine_lstVO=new ArrayList<machineMstVO>();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",machineCode="",hospitalCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = machinemst_fb.getSelectedChk().replace("$", "@");  //^
			concatid = chk.split("@");
			machineCode = concatid[0];
			hospitalCode = concatid[1];



			machinemst_VO.setMachineCode(machineCode);


			mp.putAll(machineMstDATA.getEssentialMachineCombo(machinemst_VO, userVO));
			
			machine_lstVO=	machineMstDATA.fetchMachineDetails(machinemst_VO, userVO);
			
			if(machine_lstVO!=null)
			{
						
			//arranging commports serial wise
			for(int k=0;k<4;k++)
			{			
				for(int j=0;j<machine_lstVO.size();j++)
					{
														
					if(Integer.parseInt(machine_lstVO.get(j).getCommPortFlag())==(k+1))
					{
						temp_VO=machine_lstVO.get(j);
						
						machinemst_fb.setMachineName(temp_VO.getMachineName());
						machinemst_fb.setStatus(temp_VO.getStatus());
						machinemst_fb.setFormat(temp_VO.getFormat());
						machinemst_fb.setCommFlag(temp_VO.getCommFlag());
						machinemst_fb.setLocationCode(temp_VO.getLocationCode());
						machinemst_fb.setValidationType(temp_VO.getValidationType());
						machinemst_fb.setArchivalDays(temp_VO.getArchivalDays());
						
						
							break;
					}
					else
					{
						temp_VO.setCommPortFlag("0");
						temp_VO.setDefaultModep("0");
						temp_VO.setBaudRate("-1");
						temp_VO.setByteSize("-1");
						temp_VO.setParity("-1");
						temp_VO.setStopBit("-1");
						temp_VO.setReadTimeOut("-1");
						temp_VO.setWriteTimeOut("-1");
						temp_VO.setFlowControl("-1");
						temp_VO.setFlowParity("-1");
						temp_VO.setFormat("-1");
						temp_VO.setCommFlag("-1");					
					}
					
					}
				
				final_machine_lstVO.add(temp_VO);
				temp_VO=new machineMstVO();
				
				
			}
			
			}
				
				
			mp.put(InvestigationConfig.MACHINE_COMMPORTS_DETAILS,final_machine_lstVO);
			mp.put(InvestigationConfig.MACHINE_PREVIOUS_COMMPORTS_DETAILS,machine_lstVO);
				
			WebUTIL.setMapInSession(mp, _request);

			//machineMstDATA.fetchMachineDetails(machinemst_VO, userVO);
			


//			HelperMethods.populate(machinemst_fb, machinemst_VO);

			objStatus.add(Status.NEW);
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


	

	public static List getCompareList(List list1, List list2)
	{
		List resultantList=new ArrayList();

		try
		{

			for(int i=0; i < list1.size(); i++){

				String  str1=(String)list1.get(i);

				if(!list2.contains(str1))
					resultantList.add(str1);


			}




		}
		catch (Exception e)
		{
			e.printStackTrace();


		}

		return resultantList;
	}


	
	






}


