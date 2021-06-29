package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.machineResultEntryDelegate;
import new_investigation.transactions.delegate.machineResultEntryMachineDelegate;
import new_investigation.vo.machineResultEntryVO;


public class machineResultEntryDATA
{
	 
	
	public static Map LabComboForMachineResultEntry(machineResultEntryVO resultentryvo, UserVO _UserVO)
	{
		machineResultEntryDelegate masterDelegate = new machineResultEntryDelegate();
		return masterDelegate.LabComboForMachineResultEntry(resultentryvo, _UserVO);
	}
	
	public static Map getLabBasedMachine(machineResultEntryVO resultentryvo, UserVO _UserVO)
	{
		machineResultEntryDelegate masterDelegate = new machineResultEntryDelegate();
		return masterDelegate.getLabBasedMachine(resultentryvo, _UserVO);
	}
	 
	public static Map getPatientMachineResultEntry(machineResultEntryVO resultentryvo, UserVO _UserVO)
	{
		machineResultEntryDelegate masterDelegate = new machineResultEntryDelegate();
		return masterDelegate.getPatientMachineResultEntry(resultentryvo, _UserVO);
	}
	
	
	//ResultEntry Save Logic
		public static Map saveMachineResultEntry(Map<String,List<machineResultEntryVO>> mp_resultEntry,UserVO _userVO,HttpServletRequest _request)
		{
			machineResultEntryDelegate masterDelegate = new machineResultEntryDelegate();
			return masterDelegate.saveMachineResultEntry(mp_resultEntry,  _userVO,  _request);
		}
	
		

		public static  Map AjaxGetLabList(machineResultEntryVO vo, UserVO userVO) {
			machineResultEntryMachineDelegate reportDelegate = new machineResultEntryMachineDelegate();
			return reportDelegate.AjaxGetLabList(vo, userVO);
		}
		

		

public static  Map AjaxGetMachineList(machineResultEntryVO vo, UserVO userVO) {
	machineResultEntryMachineDelegate reportDelegate = new machineResultEntryMachineDelegate();
		return reportDelegate.AjaxGetMachineList(vo, userVO);
	}



	public static Map getPatientMachineResultEntrynew(machineResultEntryVO resultentryvo, UserVO _UserVO)
	{
		machineResultEntryMachineDelegate masterDelegate = new machineResultEntryMachineDelegate();
		return masterDelegate.getPatientMachineResultEntrynew(resultentryvo, _UserVO);
	}
	
	
	public static Map saveMachineResultEntrynew(Map<String,List<machineResultEntryVO>> mp_resultEntry,UserVO _userVO,HttpServletRequest _request)
	{
		machineResultEntryMachineDelegate masterDelegate = new machineResultEntryMachineDelegate();
		return masterDelegate.saveMachineResultEntrynew(mp_resultEntry,  _userVO,  _request);
	}
	
	
} 
