package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.UserVO;

public class DoctorRoundDATA extends ControllerDATA
{
	/**  Getting the List of Employee Unit Wise
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public static List getEmployeeListUnitWise(String unitCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		//return delegate.getEmployeeListUnitWise(unitCode,userVO);
		return delegate.getConsultantDetails(unitCode, userVO);
	}
	
	public static List getDeptUnitList(UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
	
	/** Saving Doctor Visit Notes By Nurse
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public static void saveDoctorVisitNotes(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveDoctorVisitNotes(docRoundDtlVO,userVO);
	}
	
	/** Getting Doctor Instruction on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public static DoctorRoundDtlVO[] getDoctorInstruction(String patAdmNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getDoctorInstruction(patAdmNo,userVO);
	}
	
	/** Getting the Data Entered by Nurse and Not Verified
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public static DoctorRoundDtlVO[] getUnverifiedEntryByNurse(String patAdmNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getUnverifiedEntryByNurse(patAdmNo,userVO);
	}
	
	/** Getting the Details o record entered by the Nurse
	 * @param unverifiedRecordVO
	 * @param userVO
	 * @return
	 */
	public static DoctorRoundDtlVO getRecordDetail(DoctorRoundDtlVO unverifiedRecordVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getRecordDetail(unverifiedRecordVO,userVO);
	}
	
	/** Updating Nurse Visit Notes & Saving Doctor Instruction,Progress Notes 
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public static void saveNVerifyNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveNVerifyNotesByDoctor(docRoundDtlVO,userVO);
	}
	
	/** Saving Notes Entered By Doctor
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public static void saveNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveNotesByDoctor(docRoundDtlVO,userVO);
	}

	/** Getting Doctor Prev Round Dtl on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public static DoctorRoundDtlVO[] getDoctorPrevRoundDetail(String patAdmNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getDoctorPrevRoundDetail(patAdmNo,userVO);
	}
	
	public static List getVisitNotesToAdd(String processId,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getProgressNotes(processId,userVO);
	}
	
	public static NurseRoundDtlVO[] getPreviousNurseProgressNotes(String patAdmNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getPreviousProgressNotes(patAdmNo,userVO);
	}
	
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public static void upateNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.upateNotesByDoctor(docRoundDtlVO,userVO);
	}
}
