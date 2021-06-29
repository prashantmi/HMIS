package inpatient.transaction.controller.data;

/**
 * @copyright CDAC
 * @developer Hruday Meher
 */

import java.util.List;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class OutTakeDATA extends ControllerDATA
{
	/** Getting the List of Out Parameter List
	 * @param userVO
	 * @return
	 */
	public static List getOutParameterList(String type,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getOutParameterList(type,userVO); 
	}
	
	/** Saving the OutTake
	 * @param patIntakeOutDtlVO
	 * @param userVO
	 */
	public static void saveOutParameter(List<PatIntakeOutDtlVO> listPatIntakeOutTake,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveOutParameter(listPatIntakeOutTake,userVO);
	}
	
	/** Getting the Details of out Take Parameter of the Patient  
	 * @param dailyPatVO
	 * @param userVO
	 * @return
	 */
	public static PatIntakeOutDtlVO[] getOutParaDetail(String mode,PatientDetailVO dailyPatVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getOutParaDetail(mode,dailyPatVO,userVO);
	}
	
	/** getting route list
	 * @param type
	 * @param userVO
	 * @return
	 */
	public static List getRouteList(String type,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getRouteList(type,userVO); 
	}
	
	/** //Patient today intake detail
	 * @param patCrNo
	 * @param userVO
	 * @return
	 */
	public static PatIntakeOutDtlVO[] getIntakeSummary(String patCrNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getIntakeSummary(patCrNo,userVO);
	}
	
	/** //Patient today outtake detail
	 * @param patCrNo
	 * @param userVO
	 * @return
	 */
	public static PatIntakeOutDtlVO[] getOuttakeSummary(String patCrNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getOuttakeSummary(patCrNo,userVO);
	}
	
	/**Getting intake outtake summary detail based on date range
	 * @param admNo
	 * @param fromDate
	 * @param toDate
	 * @param userVO
	 * @return
	 */
	public static PatIntakeOutDtlVO[] getViewSummaryDetail(String admNo,String fromDate,String toDate,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getViewSummaryDetail(admNo,fromDate,toDate,userVO);
	}

	public static void getSnomedIdTerm(PatIntakeOutDtlVO patIntakeOutDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.getSnomedIdTerm(patIntakeOutDtlVO,userVO);
		
	}
}
