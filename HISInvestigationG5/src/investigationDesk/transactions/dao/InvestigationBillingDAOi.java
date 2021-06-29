package investigationDesk.transactions.dao;

import java.util.List;

import investigationDesk.vo.BookMarkVO;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.InvestigationRequisitionBillDtlVO;
import investigationDesk.vo.InvestigationRequistionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import hisglobal.vo.UserVO;

/**
 * @author : C-DAC, Noida Project : HISInvestigationG5 Module : Blood Bank Created On : 18 Aug, 2008
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This Interface should be used for all Essential Data Access Data Layer Methods regarding all processes
 * 
 * Modified By: Pawan Kumar B N
 * 
 * Modified On: 18-11-2011
 * 
 */

public interface InvestigationBillingDAOi
{
	
	public String makeBillingTestWise(InvestigationRequisitionBillDtlVO voBillingDtl,Inv_RequisitionRaisingPatientVO patVO,String tariffdetails,String tariffQty,String testGroupVal,UserVO userVO);
	
}

