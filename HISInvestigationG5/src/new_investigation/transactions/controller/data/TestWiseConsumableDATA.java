package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.InvResultEntryDelegate;
import new_investigation.vo.template.TestWiseConsumableVO;


public class TestWiseConsumableDATA
{
	 
	
	public static Map LabComboForResultEntry(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
	{
		InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
		return masterDelegate.LabComboForTestWiseConsumable(testWiseConsumableVO, _UserVO);
	}
	 
	public static Map setPatientResultEntryEssentials(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
	{
		InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
		return masterDelegate.setPatientTestWiseConsumableEssentials(testWiseConsumableVO, _UserVO);
	}
	
	 
		public static Map getTestWiseConsumableList(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.getTestWiseConsumableList(testWiseConsumableVO, _UserVO);
		}
		
		public static Map saveConsumableList(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.saveConsumableList(testWiseConsumableVO, _UserVO);
		}
		public static Map getPatientDetails(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.getPatientDetailsTestWiseConsumable(testWiseConsumableVO, _UserVO);
		}
		public static Map updateConsumableList(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.updateTestWiseConsumableList(testWiseConsumableVO, _UserVO);
		}

} 
