package new_investigation.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.MandatoryComboMstDATA;
import new_investigation.masters.controller.data.TestParaComboMstDATA;
import new_investigation.masters.controller.fb.MandatoryComboMstFB;
import new_investigation.masters.controller.fb.TestParaComboMstFB;

public class MandatoryComboMstUTIL implements MasterInterface
{
	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session)
	{
		this.httpSession = session;
	}

	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
		br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record'  border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'; title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;'; title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		br.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer;' title='cancel page'  border=0  tabindex='1' onKeyPress='cancelFunc();'  onClick='cancelFunc();'>");
		return br.toString();
	}

	public String[] getColsWidth() 
	{
		return null;
	}


	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Mandatory Name", "Mandatory Combo Values"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[1];
		comboQuery[0] = "1^Active";
		//#2^InActive";
		return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.MANDATORYCOMBO.HIVT_MAND_COMBO_MST").replace("?",seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.MANDATORYCOMBO.HIVT_MAND_COMBO_MST.COND.0"));

		return deleteQuery;
	}


	public String getJsFiles() 
	{
		// TODO Auto-generated method stub
		return null;
	}



	public String getMainQuery(String[] cmb)
	{

		StringBuffer brMainQuery = new StringBuffer();

		List<String> list = new ArrayList<String>();
		
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"mandatorycomboMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}

		return brMainQuery.toString();
	}


	public String getMasterName()
	{
		String masterName = "Mandatory Combo Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "HGSTR_MANDATORY_NAME" };
		return orderBy;
	}

	public int getPage_per_block()
	{
		return 10;
	}


	public int getRecord_per_page() 
	{
		return 10;
	}


	public String[] getSearchField() 
	{		
		String strSearchField[] = { "1", " UPPER(hgstr_mandatory_name) " };
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Mandatory Name");
		viewHdr.add("D");
		viewHdr.add("Mandatory Combo Value");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.mandatorycombo.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}



	public static boolean saveMandatoryCombo(MandatoryComboMstFB mandatorycombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;

		try
		{
		int rowvalue;

		if(mandatorycombo_fb.getNumberOfRow()=="")
		{	rowvalue=1;	}
		else
		{	rowvalue= Integer.parseInt(mandatorycombo_fb.getNumberOfRow());	}


		UserVO userVO = ControllerUTIL.getUserVO(_request);
		int i=0;
		List<MandatoryComboMasterVO> lstMandatoryComboVO=new ArrayList<MandatoryComboMasterVO>();

			for(i=0;i<rowvalue;i++)
			{
				MandatoryComboMasterVO mandatorycombo_VO = new MandatoryComboMasterVO();
	
				mandatorycombo_VO.setMandatoryValue(mandatorycombo_fb.getMandatoryValue()[i]);
				mandatorycombo_VO.setMandatoryCode(mandatorycombo_fb.getMandatoryCode());
				mandatorycombo_VO.setMandatorySeq(mandatorycombo_fb.getMandatorySeq());
	
				lstMandatoryComboVO.add(mandatorycombo_VO);
			}
			
			MandatoryComboMstDATA.saveMandatoryCombo(lstMandatoryComboVO, userVO);
			objStatus.add(Status.DONE, "Data Saved Successfully", "");
			
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,  e.getMessage(),"");
		}
		finally
		{

			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return saveFlag;

	}


	public static boolean fetchCheckListMandatoryCombo(MandatoryComboMstFB mandatorycombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		MandatoryComboMasterVO mandatorycombo_VO = new MandatoryComboMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();


			// Fetching Selected Record Primary Key
			String chk = mandatorycombo_fb.getChk()[0].replace("$", "@");  //^
			String[] concatid = chk.split("@");
			String mandatoryCode = concatid[0];
			String mandatorySeq = concatid[1];




			mandatorycombo_fb.setMandatoryCode(mandatoryCode); 
			mandatorycombo_VO.setMandatoryCode(mandatoryCode);

			mandatorycombo_fb.setMandatorySeq(mandatorySeq); 
			mandatorycombo_VO.setMandatorySeq(mandatorySeq);

			mandatorycombo_fb.setSelectedChk(mandatorycombo_fb.getChk()[0]);


			mp=MandatoryComboMstDATA.fetchCheckListMandatoryCombo(mandatorycombo_VO, userVO);

			WebUTIL.setMapInSession(mp, _request);


			////////////////////////////////////////////////////////////////////////////////////////////////////////////			

			mandatorycombo_fb.setStoreValue(mandatorycombo_VO.getMandatoryValue());
			mandatorycombo_fb.setMandatoryCode(mandatorycombo_VO.getMandatoryCode());
			mandatorycombo_fb.setMandatorySeq(mandatorycombo_VO.getMandatorySeq());

			////////////////////////////////////////////////////////////////////////////////////////////////////////////

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}


	public static boolean fetchMandatoryCombo(MandatoryComboMstFB mandatorycombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MandatoryComboMasterVO mandatorycombo_VO = new MandatoryComboMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 




			mp=MandatoryComboMstDATA.fetchMandatoryCombo(mandatorycombo_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(mandatorycombo_fb, mandatorycombo_VO);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}


	public static boolean savemodifyMandatoryCombo(MandatoryComboMstFB mandatorycombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			MandatoryComboMasterVO mandatorycombo_VO = new MandatoryComboMasterVO();
			mandatorycombo_VO.setMandatoryCode(mandatorycombo_fb.getMandatoryCode());




			mandatorycombo_VO.setStoreValue(mandatorycombo_fb.getStoreValue());
			mandatorycombo_VO.setMandatoryCode(mandatorycombo_fb.getMandatoryCode());
			mandatorycombo_VO.setMandatorySeq(mandatorycombo_fb.getMandatorySeq());


			/////////////////////////////////////////////////////////////




			MandatoryComboMstDATA.savemodifyMandatoryCombo(mandatorycombo_VO, userVO);

			objStatus.add(Status.DONE, "Values Modified Successfully", "");
			//objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisDuplicateRecordException e)
		{  hasFlag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
		}
		catch (HisRecordNotFoundException e)
		{  hasFlag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
		}
		catch (HisDataAccessException e)
		{   hasFlag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   hasFlag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{   hasFlag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return hasFlag;
	}

	public static boolean fetchdisplaydataMandatoryCombo(MandatoryComboMstFB mandatorycombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		List<MandatoryComboMasterVO> mandatorycombo_listVO=new ArrayList<MandatoryComboMasterVO>();

		MandatoryComboMasterVO mandatorycombo_VO = new MandatoryComboMasterVO();




		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			mandatorycombo_VO.setMandatoryCode(mandatorycombo_fb.getMandatoryCode());
			mandatorycombo_listVO=MandatoryComboMstDATA.fetchdisplaydataMandatoryCombo(mandatorycombo_VO, userVO);

			WebUTIL.setAttributeInSession(_request, InvestigationConfig.DISPLAY_DATA_MAND_CODE, mandatorycombo_listVO);


			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found. Add new records!!");
			
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	public static boolean reFetchCheckListMandatoryCombo(MandatoryComboMstFB mandatorycombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		MandatoryComboMasterVO mandatorycombo_VO = new MandatoryComboMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();

			String chk="";

			// Fetching Selected Record Primary Key
			chk = mandatorycombo_fb.getSelectedChk().replace("$", "@");
			String[] concatid = chk.split("@");
			String mandatoryCode = concatid[0];
			String mandatorySeq = concatid[1];




			mandatorycombo_fb.setMandatoryCode(mandatoryCode); 
			mandatorycombo_VO.setMandatoryCode(mandatoryCode);

			mandatorycombo_fb.setMandatorySeq(mandatorySeq); 
			mandatorycombo_VO.setMandatorySeq(mandatorySeq);



			MandatoryComboMstDATA.fetchCheckListMandatoryCombo(mandatorycombo_VO, userVO);



			////////////////////////////////////////////////////////////////////////////////////////////////////////////			

			mandatorycombo_fb.setStoreValue(mandatorycombo_VO.getMandatoryValue());
			mandatorycombo_fb.setMandatoryCode(mandatorycombo_VO.getMandatoryCode());
			mandatorycombo_fb.setMandatorySeq(mandatorycombo_VO.getMandatorySeq());

			////////////////////////////////////////////////////////////////////////////////////////////////////////////




			



			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}


}


