package medicalboard.masters.controller.data;

import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;

import java.util.List;

import medicalboard.masters.delegate.MbMasterDelegate;


public class ChecklistMasterDATA
{
	
	public static List getIsCompulsoryOptions(UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return masterDelegate.getIsCompulsoryOptions(_UserVO);
	}

	public static boolean saveChecklistMst(MedicalBoardChecklistVO checklistVO,
			UserVO userVO) {
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return masterDelegate.saveChecklistMst(checklistVO,userVO);
	}

	public static MedicalBoardChecklistVO getChecklistById(
			MedicalBoardChecklistVO checklistVO, UserVO userVO) {
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return masterDelegate.getChecklistById(checklistVO,userVO);
	}

	public static boolean modifySaveChecklist(MedicalBoardChecklistVO checklistVO, UserVO userVO) {
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return masterDelegate.modifySaveChecklist(checklistVO,userVO);
	}

}
