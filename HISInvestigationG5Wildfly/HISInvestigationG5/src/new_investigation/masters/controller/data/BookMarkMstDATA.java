/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:30-MAR-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.data;

import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.BookMarkMstVO;

public class BookMarkMstDATA {
	
	public static void saveBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveBookMark(bookMarkMstVO, _UserVO);
	}
	public static void fetchModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.fetchModifyBookMark(bookMarkMstVO, _UserVO);
	}
	public static void saveModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveModifyBookMark(bookMarkMstVO, _UserVO);
	}
}
