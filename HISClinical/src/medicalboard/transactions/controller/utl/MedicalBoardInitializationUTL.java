package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.BoardTeamDetailVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.MedicalBoardInitializationDATA;
import medicalboard.transactions.controller.fb.MedicalBoardInitializationFB;

public class MedicalBoardInitializationUTL  extends ControllerUTIL
{
	public static void setMedicalBoardInitializationEssentials(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		try{
			UserVO userVO=getUserVO(_request);
			essentialMap=MedicalBoardInitializationDATA.setMedicalBoardInitializationEssentials(userVO);
	       
			WebUTIL.setMapInSession(essentialMap, _request);
	        objStatus.add(Status.DONE);
		
		}
		
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.NEW,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
	
	
	
public static void getScheduleList(MedicalBoardInitializationFB _fb,HttpServletRequest _request)
{	
		
		Status objStatus=new Status();
		MbCertificateTypeMstVO certificateTypeMstVO=new MbCertificateTypeMstVO();
		try{
			UserVO userVO=getUserVO(_request);
			
			certificateTypeMstVO.setCertificateTypeID(_fb.getCertificateTypeID());
			certificateTypeMstVO.setDepartmentUnitCode(_fb.getDeptUnitCode());
			
			List scheduleList=MedicalBoardInitializationDATA.getScheduleList(certificateTypeMstVO,userVO);
	       	WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.REQUISITION_SCHEDULE_LIST, scheduleList);
	       
	       objStatus.add(Status.LIST);
		
		}
		
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.NEW,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}

	
public static void getBoardDetail(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	Map essentialMap=new HashMap();
	try{
		UserVO userVO=getUserVO(_request);
//		String certificateTypeId="";
//		String scheduleDate="";
		essentialMap=MedicalBoardInitializationDATA.getBoardDetail(_fb.getCertificateTypeID().split("#")[0],_fb.getScheduleDate(),userVO);
        
		_fb.setSelectedBoardArray(null);
		_fb.setAssignBoardNoArray(null);
		WebUTIL.setMapInSession(essentialMap, _request);
        objStatus.add(Status.LIST);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void AddDocRoleRow(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		BoardTeamDetailVO boardTeamDetailVO=new BoardTeamDetailVO();
		
		boardTeamDetailVO.setEmployeeId(_fb.getEmpID());
		boardTeamDetailVO.setRoleId(_fb.getRoleID());
		
		List docEmpList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_EMP_DOCTOR);
		List roleList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_ROLL);
		
		if(docEmpList!=null)
		{
			for(int i=0;i<docEmpList.size();i++)
			{
				Entry obj=(Entry)docEmpList.get(i);
				if(obj.getValue().equals(_fb.getEmpID()))
				{
					boardTeamDetailVO.setEmpName(obj.getLabel());
				}
			}
		}
		
		if(roleList!=null)
		{
			for(int i=0;i<roleList.size();i++)
			{
				Entry obj=(Entry)roleList.get(i);
				if(obj.getValue().equals(_fb.getRoleID()))
				{
					boardTeamDetailVO.setRoleName(obj.getLabel());
				}
			}
		}
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST);
		
		if(boardTeamDetailVOLst==null)
		{
			boardTeamDetailVOLst=new ArrayList();
		}
		
		boardTeamDetailVOLst.add(boardTeamDetailVO);
		
		_fb.setEmpID("_1");
		_fb.setRoleID("-1");
		
		session.setAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST, boardTeamDetailVOLst);
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemovedDocRoleROW(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST);
		Iterator itr=boardTeamDetailVOLst.iterator();
		Integer index=0;
		while(itr.hasNext())
		{
//			BoardTeamDetailVO vo=(BoardTeamDetailVO)itr.next();
			
			if(index.toString().equals(_fb.getDocRoleIndex()))
			{
				itr.remove();
				break;
			}
			
			index++;
		}
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void AddEscortedRow(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		BoardTeamDetailVO boardTeamDetailVO=new BoardTeamDetailVO();
		
		boardTeamDetailVO.setEmployeeId(_fb.getEscortedEmpID());
		boardTeamDetailVO.setRoleId("0");
		
		List escortedEmpList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_EMP_ESCORTS);
				
		if(escortedEmpList!=null)
		{
			for(int i=0;i<escortedEmpList.size();i++)
			{
				Entry obj=(Entry)escortedEmpList.get(i);
				if(obj.getValue().equals(_fb.getEscortedEmpID()))
				{
					boardTeamDetailVO.setEmpName(obj.getLabel());
				}
			}
		}
		
		_fb.setEscortedEmpID("-1");
		
		List escortedEmpLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST);
		
		if(escortedEmpLst==null)
		{
			escortedEmpLst=new ArrayList();
		}
		
		escortedEmpLst.add(boardTeamDetailVO);
		
		session.setAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST, escortedEmpLst);
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemoveEscoretedRow(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST);
		Iterator itr=boardTeamDetailVOLst.iterator();
		Integer index=0;
		while(itr.hasNext())
		{
//			BoardTeamDetailVO vo=(BoardTeamDetailVO)itr.next();
			
			if(index.toString().equals(_fb.getEscortedRowIndex()))
			{
				itr.remove();
				break;
			}
			
			index++;
			
		}
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getTeamDetailByBoardId(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	List teamDetailList=null;
	List roleList=null;
	try{
		UserVO userVO=getUserVO(_request);
		
		teamDetailList=MedicalBoardInitializationDATA.getTeamDetailByBoardId(_fb.getSelectedBoard(),userVO);
		roleList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_ROLL);
		
		List empDocRoleTeamList=new ArrayList();
		List empEscortedTeamList=new ArrayList();
		
		if(teamDetailList!=null)
		{
			for(int i=0;i<teamDetailList.size();i++)
			{
				BoardTeamDetailVO teamDetailVO=(BoardTeamDetailVO)teamDetailList.get(i);
				if(teamDetailVO.getRoleId().equals(MedicalBoardConfig.ROLE_ID_FOR_ESCORTED_EMPLOYEE))
				{
					empEscortedTeamList.add(teamDetailVO);
				}
				else
				{
					empDocRoleTeamList.add(teamDetailVO);
				}
			}
		}
		
		if(empDocRoleTeamList!=null)
		{
			for(int i=0;i<empDocRoleTeamList.size();i++)
			{
				BoardTeamDetailVO teamDetailVO=(BoardTeamDetailVO)empDocRoleTeamList.get(i);
				if(roleList!=null)
				{
					for(int j=0;j<roleList.size();j++)
					{
						Entry roleObj=(Entry)roleList.get(j);
						if(teamDetailVO.getRoleId().equals(roleObj.getValue()))
						{
							teamDetailVO.setRoleName(roleObj.getLabel());
						}
					}
				}
			}
		}
		
		
		List boardList=(List)session.getAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE);
		List selectedBoardList=new ArrayList();
		
		for(int i=0;i<boardList.size();i++)
		{
			MedicalBoardMasterVO boardDetailVO=(MedicalBoardMasterVO)boardList.get(i);
			if(boardDetailVO.getBoardID().equals(_fb.getSelectedBoard()))
			{
				selectedBoardList.add(boardDetailVO);
			}
		}
		
		session.setAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE, selectedBoardList);
		session.setAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST, empEscortedTeamList);
		session.setAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST, empDocRoleTeamList);
		
        objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}



public static void saveBoardAndBoardTeamDetail(MedicalBoardInitializationFB _fb,HttpServletRequest _request){
	UserVO userVO=getUserVO(_request);
	Status objStatus =new Status();	
	HttpSession session=WebUTIL.getSession(_request);
	List escortedTeamDetailList=null;
	List docRoleTeamDetailList=null;
	BoardDetailVO boardDetailVO=new BoardDetailVO();
	
	try
	{
		 
		escortedTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST);
		docRoleTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST);
		
		boardDetailVO.setActualDateTime(_fb.getScheduleDate());
		boardDetailVO.setBoardStartTime(_fb.getScheduleDate());
		boardDetailVO.setBoardId(_fb.getSelectedBoard());
		boardDetailVO.setCertificateTypeId(_fb.getCertificateTypeID().split("#")[0]);
		boardDetailVO.setLocation(_fb.getLocation());
		
		MedicalBoardInitializationDATA.saveBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);	
		
		objStatus.add(Status.DONE,"","Record Saved Successfully");	
		
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,"",e.getMessage());		
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
	WebUTIL.setStatus(_request,objStatus);
	 System.out.println("objStatus in finally"+objStatus);		 
	 System.out.println("objStatus list"+objStatus.getStatusList());
	}	

}

public static void getAssignTeamDetail(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	List teamDetailList=null;
	List roleList=null;
	List assignBoardList=null;
	List assignBoardListForModify=new ArrayList();
	try{
		UserVO userVO=getUserVO(_request);
		
		_fb.setLocation(_fb.getSelectedLocation());
		
		teamDetailList=MedicalBoardInitializationDATA.getAssignTeamDetail(_fb.getSelectedAssignBoardNo(),userVO);
		roleList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_ROLL);
		
		List empDocRoleTeamList=new ArrayList();
		List empEscortedTeamList=new ArrayList();
		
		if(teamDetailList!=null)
		{
			for(int i=0;i<teamDetailList.size();i++)
			{
				BoardTeamDetailVO teamDetailVO=(BoardTeamDetailVO)teamDetailList.get(i);
				if(teamDetailVO.getRoleId().equals(MedicalBoardConfig.ROLE_ID_FOR_ESCORTED_EMPLOYEE))
				{
					empEscortedTeamList.add(teamDetailVO);
				}
				else
				{
					empDocRoleTeamList.add(teamDetailVO);
				}
			}
		}
		
		if(empDocRoleTeamList!=null)
		{
			for(int i=0;i<empDocRoleTeamList.size();i++)
			{
				BoardTeamDetailVO teamDetailVO=(BoardTeamDetailVO)empDocRoleTeamList.get(i);
				if(roleList!=null)
				{
					for(int j=0;j<roleList.size();j++)
					{
						Entry roleObj=(Entry)roleList.get(j);
						if(teamDetailVO.getRoleId().equals(roleObj.getValue()))
						{
							teamDetailVO.setRoleName(roleObj.getLabel());
						}
					}
				}
			}
		}
		
		session.setAttribute(MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST, empEscortedTeamList);
		session.setAttribute(MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST, empDocRoleTeamList);
		
		
		assignBoardList=(List)session.getAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		BoardDetailVO boardDetailVO=null;
		if(assignBoardList!=null)
		{
			for(int i=0;i<assignBoardList.size();i++)
			{
				boardDetailVO=(BoardDetailVO)assignBoardList.get(i);
				if(boardDetailVO.getBoardId().equals(_fb.getSelectedAssignBoardId()))
				{
					assignBoardListForModify.add(boardDetailVO);
				}
				
			}
		}
		
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE, assignBoardListForModify);
		
		/*
		List boardList=(List)session.getAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		List selectedBoardList=new ArrayList();
		
		for(int i=0;i<boardList.size();i++)
		{
			MedicalBoardMasterVO boardDetailVO=(MedicalBoardMasterVO)boardList.get(i);
			if(boardDetailVO.getBoardID().equals(_fb.getSelectedBoard()))
			{
				selectedBoardList.add(boardDetailVO);
			}
		}
		
		session.setAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE, selectedBoardList);
		*/
		//session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST);
				
        objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void AddAssignDocRoleRow(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		BoardTeamDetailVO boardTeamDetailVO=new BoardTeamDetailVO();
		
		boardTeamDetailVO.setEmployeeId(_fb.getEmpID());
		boardTeamDetailVO.setRoleId(_fb.getRoleID());
		
		List docEmpList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_EMP_DOCTOR);
		List roleList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_ROLL);
		
		if(docEmpList!=null)
		{
			for(int i=0;i<docEmpList.size();i++)
			{
				Entry obj=(Entry)docEmpList.get(i);
				if(obj.getValue().equals(_fb.getEmpID()))
				{
					boardTeamDetailVO.setEmpName(obj.getLabel());
				}
			}
		}
		
		if(roleList!=null)
		{
			for(int i=0;i<roleList.size();i++)
			{
				Entry obj=(Entry)roleList.get(i);
				if(obj.getValue().equals(_fb.getRoleID()))
				{
					boardTeamDetailVO.setRoleName(obj.getLabel());
				}
			}
		}
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST);
		
		if(boardTeamDetailVOLst==null)
		{
			boardTeamDetailVOLst=new ArrayList();
		}
		
		boardTeamDetailVOLst.add(boardTeamDetailVO);
				
		_fb.setEmpID("_1");
		_fb.setRoleID("-1");
		
		session.setAttribute(MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST, boardTeamDetailVOLst);
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemovedAssignDocRoleROW(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST);
		Iterator itr=boardTeamDetailVOLst.iterator();
		Integer index=0;
		while(itr.hasNext())
		{
//			BoardTeamDetailVO vo=(BoardTeamDetailVO)itr.next();
			
			if(index.toString().equals(_fb.getDocRoleAssignIndex()))
			{
				itr.remove();
				break;
			}
			
			index++;
			
		}
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void AddAssignEscortedRow(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		BoardTeamDetailVO boardTeamDetailVO=new BoardTeamDetailVO();
		
		boardTeamDetailVO.setEmployeeId(_fb.getEscortedEmpID());
		boardTeamDetailVO.setRoleId("0");
		
		List escortedEmpList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_EMP_ESCORTS);
				
		if(escortedEmpList!=null)
		{
			for(int i=0;i<escortedEmpList.size();i++)
			{
				Entry obj=(Entry)escortedEmpList.get(i);
				if(obj.getValue().equals(_fb.getEscortedEmpID()))
				{
					boardTeamDetailVO.setEmpName(obj.getLabel());
				}
			}
		}
				
		List escortedEmpLst=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST);
		
		if(escortedEmpLst==null)
		{
			escortedEmpLst=new ArrayList();
		}
		
		escortedEmpLst.add(boardTeamDetailVO);
		
		_fb.setEscortedEmpID("-1");
		
		session.setAttribute(MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST, escortedEmpLst);
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemoveAssignEscoretedRow(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST);
		Iterator itr=boardTeamDetailVOLst.iterator();
		Integer index=0;
		while(itr.hasNext())
		{
//			BoardTeamDetailVO vo=(BoardTeamDetailVO)itr.next();
			
			if(index.toString().equals(_fb.getEscortedAssignRowIndex()))
			{
				itr.remove();
				break;
			}
			
			index++;
			
		}
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void getAvailableBoardDetailList(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	List getAvailableBoardDetailList=null;
	HttpSession session=WebUTIL.getSession(_request);
	try{
		UserVO userVO=getUserVO(_request);
		
		getAvailableBoardDetailList=MedicalBoardInitializationDATA.getAvailableBoardDetailList(_fb.getCertificateTypeID().split("#")[0],_fb.getScheduleDate(),userVO);
        
		_fb.setSelectedBoardArray(null);
		session.setAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST, getAvailableBoardDetailList);
		
		
        objStatus.add(Status.LIST);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void AddDocRoleRowForNewBoard(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		BoardTeamDetailVO boardTeamDetailVO=new BoardTeamDetailVO();
		
		boardTeamDetailVO.setEmployeeId(_fb.getEmpID());
		boardTeamDetailVO.setRoleId(_fb.getRoleID());
		
		List docEmpList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_EMP_DOCTOR);
		List roleList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_ROLL);
		
		if(docEmpList!=null)
		{
			for(int i=0;i<docEmpList.size();i++)
			{
				Entry obj=(Entry)docEmpList.get(i);
				if(obj.getValue().equals(_fb.getEmpID()))
				{
					boardTeamDetailVO.setEmpName(obj.getLabel());
				}
			}
		}
		
		if(roleList!=null)
		{
			for(int i=0;i<roleList.size();i++)
			{
				Entry obj=(Entry)roleList.get(i);
				if(obj.getValue().equals(_fb.getRoleID()))
				{
					boardTeamDetailVO.setRoleName(obj.getLabel());
				}
			}
		}
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD);
		
		if(boardTeamDetailVOLst==null)
		{
			boardTeamDetailVOLst=new ArrayList();
		}
		
		boardTeamDetailVOLst.add(boardTeamDetailVO);
		
		_fb.setEmpID("-1");
		_fb.setRoleID("-1");
		
		session.setAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD, boardTeamDetailVOLst);
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemovedDocRoleROWForNewBoard(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD);
		Iterator itr=boardTeamDetailVOLst.iterator();
		Integer index=0;
		while(itr.hasNext())
		{
//			BoardTeamDetailVO vo=(BoardTeamDetailVO)itr.next();
			
			if(index.toString().equals(_fb.getDocRoleIndex()))
			{
				itr.remove();
				break;
			}
			
			index++;
			
		}
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void AddEscortedRowForNewBoard(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		BoardTeamDetailVO boardTeamDetailVO=new BoardTeamDetailVO();
		
		boardTeamDetailVO.setEmployeeId(_fb.getEscortedEmpID());
		boardTeamDetailVO.setRoleId("0");
		
		List escortedEmpList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_EMP_ESCORTS);
				
		if(escortedEmpList!=null)
		{
			for(int i=0;i<escortedEmpList.size();i++)
			{
				Entry obj=(Entry)escortedEmpList.get(i);
				if(obj.getValue().equals(_fb.getEscortedEmpID()))
				{
					boardTeamDetailVO.setEmpName(obj.getLabel());
				}
			}
		}
		
		List escortedEmpLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD);
		
		if(escortedEmpLst==null)
		{
			escortedEmpLst=new ArrayList();
		}
		
		escortedEmpLst.add(boardTeamDetailVO);
		
		_fb.setEscortedEmpID("-1");
		
		session.setAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD, escortedEmpLst);
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemoveEscoretedRowForNewBoard(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	try{
//		UserVO userVO=getUserVO(_request);
		
		List boardTeamDetailVOLst=(List)session.getAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD);
		Iterator itr=boardTeamDetailVOLst.iterator();
		Integer index=0;
		while(itr.hasNext())
		{
//			BoardTeamDetailVO vo=(BoardTeamDetailVO)itr.next();
			
			if(index.toString().equals(_fb.getEscortedRowIndex()))
			{
				itr.remove();
				break;
			}
			
			index++;
			
		}
		
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void addNewBoardAndBoardTeamDetail(MedicalBoardInitializationFB _fb,HttpServletRequest _request){
	UserVO userVO=getUserVO(_request);
	Status objStatus =new Status();	
	HttpSession session=WebUTIL.getSession(_request);
	List escortedTeamDetailList=null;
	List docRoleTeamDetailList=null;
	BoardDetailVO boardDetailVO=new BoardDetailVO();
	
	try
	{
		 
		escortedTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD);
		docRoleTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD);
		
		boardDetailVO.setActualDateTime(_fb.getScheduleDate());
		boardDetailVO.setBoardStartTime(_fb.getScheduleDate());
		boardDetailVO.setBoardId(_fb.getSelectedBoard());
		boardDetailVO.setCertificateTypeId(_fb.getCertificateTypeID().split("#")[0]);
		boardDetailVO.setLocation(_fb.getLocation());
		
		MedicalBoardInitializationDATA.addNewBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);	
		
		objStatus.add(Status.DONE,"","Record Saved Successfully");	
		
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,"",e.getMessage());		
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
	WebUTIL.setStatus(_request,objStatus);
	 System.out.println("objStatus in finally"+objStatus);		 
	 System.out.println("objStatus list"+objStatus.getStatusList());
	}	

}

public static void getTeamDetailByBoardIdForNewBoard(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	List teamDetailList=null;
	List roleList=null;
	try{
		UserVO userVO=getUserVO(_request);
		
		teamDetailList=MedicalBoardInitializationDATA.getTeamDetailByBoardId(_fb.getSelectedBoard(),userVO);
		roleList=(List)session.getAttribute(MedicalBoardConfig.LIST_OF_ROLL);
		
		List empDocRoleTeamList=new ArrayList();
		List empEscortedTeamList=new ArrayList();
		
		if(teamDetailList!=null)
		{
			for(int i=0;i<teamDetailList.size();i++)
			{
				BoardTeamDetailVO teamDetailVO=(BoardTeamDetailVO)teamDetailList.get(i);
				if(teamDetailVO.getRoleId().equals(MedicalBoardConfig.ROLE_ID_FOR_ESCORTED_EMPLOYEE))
				{
					empEscortedTeamList.add(teamDetailVO);
				}
				else
				{
					empDocRoleTeamList.add(teamDetailVO);
				}
			}
		}
		
		if(empDocRoleTeamList!=null)
		{
			for(int i=0;i<empDocRoleTeamList.size();i++)
			{
				BoardTeamDetailVO teamDetailVO=(BoardTeamDetailVO)empDocRoleTeamList.get(i);
				if(roleList!=null)
				{
					for(int j=0;j<roleList.size();j++)
					{
						Entry roleObj=(Entry)roleList.get(j);
						if(teamDetailVO.getRoleId().equals(roleObj.getValue()))
						{
							teamDetailVO.setRoleName(roleObj.getLabel());
						}
					}
				}
			}
		}
		
		List boardList=(List)session.getAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST);
		List selectedBoardList=new ArrayList();
		
		for(int i=0;i<boardList.size();i++)
		{
			MedicalBoardMasterVO boardDetailVO=(MedicalBoardMasterVO)boardList.get(i);
			if(boardDetailVO.getBoardID().equals(_fb.getSelectedBoard()))
			{
				selectedBoardList.add(boardDetailVO);
			}
		}
		
		session.setAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST, selectedBoardList);
		
		session.setAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD, empEscortedTeamList);
		session.setAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD, empDocRoleTeamList);
		
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		objStatus.add(Status.RECORDFOUND); 
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void RemoveBoard(MedicalBoardInitializationFB _fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
//	HttpSession session=WebUTIL.getSession(_request);
	try{
		UserVO userVO=getUserVO(_request);
//		String boardNo="";
		
		MedicalBoardInitializationDATA.removeBoard(_fb.getSelectedAssignBoardNo(),userVO);
	
	}
	
	catch(HisRecordNotFoundException e){
		objStatus.add(Status.NEW,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void updateAssignBoardAndBoardTeamDetail(MedicalBoardInitializationFB _fb,HttpServletRequest _request){
	UserVO userVO=getUserVO(_request);
	Status objStatus =new Status();	
	HttpSession session=WebUTIL.getSession(_request);
	List escortedTeamDetailList=null;
	List docRoleTeamDetailList=null;
	List assignBoardListForModify=null;
	BoardDetailVO boardDetailVO=new BoardDetailVO();
	BoardDetailVO modifyBoardDetailVO=null;
	try
	{
		escortedTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST);
		docRoleTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST);
		assignBoardListForModify=(List)session.getAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY);
		modifyBoardDetailVO=(BoardDetailVO)assignBoardListForModify.get(0);
		
		boardDetailVO.setActualDateTime(_fb.getScheduleDate());
		boardDetailVO.setBoardStartTime(_fb.getScheduleDate());
		boardDetailVO.setLocation(_fb.getLocation());
		boardDetailVO.setCertificateTypeId(_fb.getCertificateTypeID().split("#")[0]);
		/*
		boardDetailVO.setBoardNo(_fb.getSelectedAssignBoardNo());
		boardDetailVO.setBoardId(_fb.getSelectedAssignBoardId());
		*/
		
		boardDetailVO.setBoardNo(modifyBoardDetailVO.getBoardNo());
		boardDetailVO.setBoardId(modifyBoardDetailVO.getBoardId());
		
		MedicalBoardInitializationDATA.updateAssignBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);	
		
		objStatus.add(Status.DONE,"","Record Saved Successfully");	
		
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,"",e.getMessage());		
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
	WebUTIL.setStatus(_request,objStatus);
	 System.out.println("objStatus in finally"+objStatus);		 
	 System.out.println("objStatus list"+objStatus.getStatusList());
	}	

}

public static void modifyAssignBoardDetail(MedicalBoardInitializationFB _fb,HttpServletRequest _request){
//	UserVO userVO=getUserVO(_request);
	Status objStatus =new Status();	
	HttpSession session=WebUTIL.getSession(_request);
//	List escortedTeamDetailList=null;
//	List docRoleTeamDetailList=null;
	List assignBoardList=null;
	BoardDetailVO boardDetailVO=new BoardDetailVO();
	List assignBoardListForModify=new ArrayList();
	
	try
	{
		_fb.setLocation(_fb.getSelectedLocation());
		
//		escortedTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST);
//		docRoleTeamDetailList=(List)session.getAttribute(MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST);
		assignBoardList=(List)session.getAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		
		if(assignBoardList!=null)
		{
			for(int i=0;i<assignBoardList.size();i++)
			{
				boardDetailVO=(BoardDetailVO)assignBoardList.get(i);
				if(boardDetailVO.getBoardId().equals(_fb.getSelectedAssignBoardId()))
				{
					assignBoardListForModify.add(boardDetailVO);
				}
				
			}
		}
		
		//assignBoardListForModify.add(boardDetailVO);
		
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY, assignBoardListForModify);
		//WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ASSIGNED_ESCOETED_EMP_LIST_FOR_MODIFY, escortedTeamDetailList);
		//WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST_FOR_MODIFY, docRoleTeamDetailList);
		objStatus.add(Status.RECORDFOUND); 	
		
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,"",e.getMessage());		
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
	WebUTIL.setStatus(_request,objStatus);
	 System.out.println("objStatus in finally"+objStatus);		 
	 System.out.println("objStatus list"+objStatus.getStatusList());
	}	

}



public static void refreshPage(MedicalBoardInitializationFB _fb,HttpServletRequest _request){
//	UserVO userVO=getUserVO(_request);
	Status objStatus =new Status();	
		
	try
	{
		
		objStatus.add(Status.DONE); 	
		
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,"",e.getMessage());		
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
	WebUTIL.setStatus(_request,objStatus);
	 System.out.println("objStatus in finally"+objStatus);		 
	 System.out.println("objStatus list"+objStatus.getStatusList());
	}	

}



}
