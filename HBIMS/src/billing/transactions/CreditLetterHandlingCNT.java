package billing.transactions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.vo.UserVO;

public class CreditLetterHandlingCNT extends CSRFGardTokenAction{

	/**
	 * forwards control to the Page CreditLetterHandling_trans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException {
		generateToken(request);
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		formBean.setFilePath("");
		String strCounterId ="";
		String target = "cashcollection";
		
        UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
		
			
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.");
				return mapping.findForward(target);
			}
			else if(userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.");
				return mapping.findForward(target);
			}
		}
		
		BillConfigUtil bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrConfirmationType(bcu.getGeneralCashCollectionConfrimType());
		//System.out.println("IP Address"+request.getSession().getAttribute("IP_ADDR").toString());
		
		String counterCheck=BillConfigUtil.CHECK_COUNTER_STATUS;
		if(counterCheck.equals("1"))//Counter Check Needed=1, Counter Check Not Needed=0
		{
			strCounterId =CreditLetterHandlingDATA.checkCounterStatus(request, formBean);
			if(strCounterId!=null)
			{
				if(strCounterId.equals(""))
				{
					return mapping.findForward(target);
				}
				else
				{
					if (strCounterId.trim().length() < 4)
					{
						return mapping.findForward(target);
					}
					else
					{
						return this.OFFLINEMODE(mapping, form, request, response);
					}
				}				
			}
			else
			{
				return mapping.findForward(target);
			}
		}
		else
		{
			return this.OFFLINEMODE(mapping, form, request, response);
		}
	}

	
	/**
	 * forwards Cash Collection in Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward OFFLINEMODE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		generateToken(request);
		String target = "cashcollection";
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		
        UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
		
			
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.");
				return mapping.findForward(target);
			}
			else if(userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.");
				return mapping.findForward(target);
			}
		}
		
		String strCounterMode = "0";
		if(request.getParameter("counterMode") != null)
		{
			 strCounterMode = request.getParameter("counterMode");
		}
		else
		{
			strCounterMode = formBean.getStrCounterMode();
		}		
		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		CreditLetterHandlingDATA.preInitOffLineDetails(formBean );//Populates Hospital Service Combo and Request Type Combo Only
		
		formBean.setStrCounterMode(strCounterMode);
		
		return mapping.findForward(target);
	}

	/**
	 * forwards control to the Cash Collection Page by initializing all the
	 * required information for given Cr. Number.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		generateToken(request);
		//Gets Patient Details,Combos Like Client,Payment Mode,Relation,Groups,Tariffs,Department,Episode,Category,Configuration Parameters  
		String strTarget = "cashcollection";
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;

		boolean bResult = CreditLetterHandlingDATA.init(request, formBean);
		if (bResult) 
		{
			return mapping.findForward(strTarget);
		} 
		else 
		{
			return this.OFFLINEMODE(mapping, formBean, request, response);
		}
	}
  
	  
	/**
	 * function invoked by Ajax, populates the billing Service Combo box based
	 * on Request Type & Hospital Service.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BILLDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CreditLetterHandlingDATA.getBillServiceDetails(request, response);

		return null;

	}

	/**
	 * function invoked by Ajax, populates the Episode Combo box based on
	 * Raising Department and Cr Number.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward EPISODEDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CreditLetterHandlingDATA.getEpisodeDetails(request, response);

		return null;

	}
 


	/**
	 * function invoked by Ajax, populates the Group Details based on Hospital
	 * Service and with or without Package Flag.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PKGGROUPDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CreditLetterHandlingDATA.getGroupDetails(request, response);

		return null;

	}
	/**
	 * function invoked by Ajax, gives the Part Payment or Advance amount
	 * Details based on Hospital Service, Request Type, Bill Service, Treatment
	 * Category and Ward Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PARTACCAMT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		CreditLetterHandlingDATA.getPartPaymentOrAccountDtls(request, response);

		return null;

	}

	/**
	 * function invoked by Ajax, gets Tariff Unit Details for Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TRFUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CreditLetterHandlingDATA.getOffLineTariffUnitDetails(request, response);

		return null;

	}

	/**
	 * function invoked by Ajax, gets Unit details for Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward UNITVAL12(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreditLetterHandlingDATA.UNITVAL12(request, response);

		return null;
	}
  

	/**
	 * opens a pop-up with bill list, used for search bills in Without Cr. No. Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BILLLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String target = "billlist";

		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;

		formBean.setStrBillUsrFuncName(request.getParameter("usrFuncName"));

		return mapping.findForward(target);
	}

	/**
	 * function invoked by Ajax, gets Without Cr. No. Bill List 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward FETCHBILLLISTING(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		CreditLetterHandlingDATA.getBillListingDtls(request, response, formBean);

		return null;
	}
 
	
	
	public ActionForward OFFLINETREATCATDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CreditLetterHandlingDATA.getOfflineTreatmentCategoryDtls(request, response);

		return null;

	}
	

	public ActionForward OFFLINEDEPTDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CreditLetterHandlingDATA.getOfflineRaisingDetapartmentDtls(request, response);

		return null;

	}
	
	
	
	public ActionForward OFFLINEWARDDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CreditLetterHandlingDATA.getOfflineWardDtls(request, response);

		return null;

	}
	 
	
	public ActionForward OFFLINESPLWARDDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CreditLetterHandlingDATA.getOfflineSpecialWardDtls(request, response);

		return null;

	}
	
	
	
	
	/**This method is used to Transfer Control Over Inti Page   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
        ActionForward acFwd = new ActionForward();
        acFwd.setPath("/hisglobal/initPage.jsp");
        acFwd.setContextRelative(true);
        return acFwd;
    }
	
	public ActionForward PACKAGEAMOUNT(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) _Form;
		CreditLetterHandlingDATA.setPackageAmountValues(formBean, _Request, _Response);
		return null;
	}
	/*public ActionForward GETCREDITLIST(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response){
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) _Form;
		CreditLetterHandlingDATA.getCreditList(formBean, _Request);
		return null;
	}*/
	public ActionForward GROUPDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CreditLetterHandlingDATA.getGroupDetails(request, response);

		return null;

	}
	public ActionForward SAVECREDITLETTER(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		CreditLetterHandlingDATA.saveCreditLetter(formBean,request, response);
		String target = "cashcollection";
		return mapping.findForward(target);
	}
	public ActionForward REMOVECREDITLETTER(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		CreditLetterHandlingDATA.removeCreditLetter(formBean,request, response);
		String target = "cashcollection";
		return mapping.findForward(target);
	}
	public ActionForward EXPIRECREDITLETTER(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		CreditLetterHandlingDATA.expireCreditLetter(formBean,request, response);
		String target = "cashcollection";
		return mapping.findForward(target);
	}
	public ActionForward MODIFYCREDITLETTER(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		CreditLetterHandlingFB formBean = (CreditLetterHandlingFB) form;
		CreditLetterHandlingDATA.modifyCreditLetter(formBean,request, response);
		String target = "cashcollection";
		return mapping.findForward(target);
	}
}