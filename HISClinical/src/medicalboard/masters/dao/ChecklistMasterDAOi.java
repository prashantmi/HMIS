package medicalboard.masters.dao;

import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;

public interface ChecklistMasterDAOi {

	public String checkDuplicateChecklistBeforeSave(MedicalBoardChecklistVO checklistVO,UserVO userVO);
	
	public void create(MedicalBoardChecklistVO checklistVO,UserVO userVO);
	
	public MedicalBoardChecklistVO getChecklistById(MedicalBoardChecklistVO checklistVO,UserVO userVO);
	
	public void modifyInsert(MedicalBoardChecklistVO checklistVO,UserVO userVO);
	
	public void update(MedicalBoardChecklistVO checklistVO,UserVO userVO);
	
	public String checkDuplicateChecklistBeforeModify(MedicalBoardChecklistVO checklistVO,UserVO userVO);
	
}
