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
import new_investigation.vo.CollectionAreaMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.CollectionAreaMstDATA;
import new_investigation.masters.controller.fb.CollectionAreaMstFB;

public class CollectionAreaMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Collection Area Name","Location" };
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.COLLECTIONAREA.HIVT_COLLECTION_AREA_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.COLLECTIONAREA.HIVT_COLLECTION_AREA_MST.COND.0"));

		return deleteQuery;
	}


	public String getJsFiles() 
	{
		// TODO Auto-generated method stub
		return null;
	}



	public String getMainQuery(String[] cmb)
	{
	
		//	UserVO userVO;
		StringBuffer brMainQuery = new StringBuffer();
		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();
		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(hosCode);
		

	//	list.add(userVO.getHospitalCode());
		
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"collectionareaMst.main.0"));
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
		String masterName = "Collection Area Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "GSTR_AREA_NAME" };
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
		String strSearchField[] = { "1", " UPPER(gstr_area_name) "};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Collection Area Name");
		viewHdr.add("D");


		viewHdr.add("Location");
		viewHdr.add("D");

		viewHdr.add("Collection Area Type");
		viewHdr.add("D");


		viewHdr.add("Ward");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.collectionarea.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveCollectionArea(CollectionAreaMstFB collectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			CollectionAreaMasterVO collectionarea_VO = new CollectionAreaMasterVO();
			HelperMethods.populate(collectionarea_VO, collectionarea_fb);
			
			if(collectionarea_VO.getCollectionareaType().equals("1"))
			{	collectionarea_VO.setWardCode(null); }

			CollectionAreaMstDATA.saveCollectionArea(collectionarea_VO, userVO);
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


	public static boolean fetchCheckListCollectionArea(CollectionAreaMstFB collectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		CollectionAreaMasterVO collectionarea_VO = new CollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",collectionareaCode="",hospitalCode="",locationCode="",wardCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = collectionarea_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			collectionareaCode = concatid[0];
			hospitalCode = concatid[1];
			


			collectionarea_VO.setIsActive(collectionarea_fb.getIsActive());

			collectionarea_fb.setCollectionareaCode(collectionareaCode); 
			collectionarea_VO.setCollectionareaCode(collectionareaCode);
			collectionarea_fb.setHospitalCode(hospitalCode); 
			collectionarea_VO.setHospitalCode(hospitalCode);
			collectionarea_VO.setHmode(collectionarea_fb.getHmode());
			collectionarea_fb.setSelectedChk(collectionarea_fb.getChk()[0]);

			mp=CollectionAreaMstDATA.fetchCheckListCollectionArea(collectionarea_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			collectionarea_fb.setCollectionareaCode(collectionareaCode); 
			collectionarea_VO.setCollectionareaCode(collectionareaCode);
			collectionarea_fb.setHospitalCode(hospitalCode); 
			collectionarea_VO.setHospitalCode(hospitalCode);
			


			HelperMethods.populate(collectionarea_fb, collectionarea_VO);

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


	public static boolean fetchCollectionArea(CollectionAreaMstFB collectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		CollectionAreaMasterVO collectionarea_VO = new CollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=CollectionAreaMstDATA.fetchCollectionArea(collectionarea_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(collectionarea_fb, collectionarea_VO);

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


	public static boolean savemodifyCollectionArea(CollectionAreaMstFB collectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			CollectionAreaMasterVO collectionarea_VO = new CollectionAreaMasterVO();

			collectionarea_VO.setCollectionareaCode(collectionarea_fb.getCollectionareaCode());
			HelperMethods.populate(collectionarea_VO, collectionarea_fb);

			CollectionAreaMstDATA.savemodifyCollectionArea(collectionarea_VO, userVO);

			objStatus.add(Status.DONE, "Data Modified Successfully", "");
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

	public static boolean reFetchCheckListCollectionArea(CollectionAreaMstFB collectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		CollectionAreaMasterVO collectionarea_VO = new CollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",collectionareaCode="",hospitalCode="",locationCode="",wardCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = collectionarea_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			collectionareaCode = concatid[0];
			hospitalCode = concatid[1];
		


			collectionarea_VO.setIsActive(collectionarea_fb.getIsActive());

			collectionarea_fb.setCollectionareaCode(collectionareaCode); 
			collectionarea_VO.setCollectionareaCode(collectionareaCode);
			collectionarea_fb.setHospitalCode(hospitalCode); 
			collectionarea_VO.setHospitalCode(hospitalCode);
		


			mp=CollectionAreaMstDATA.fetchCheckListCollectionArea(collectionarea_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			collectionarea_fb.setCollectionareaCode(collectionareaCode); 
			collectionarea_VO.setCollectionareaCode(collectionareaCode);
			collectionarea_fb.setHospitalCode(hospitalCode); 
			collectionarea_VO.setHospitalCode(hospitalCode);
	


			HelperMethods.populate(collectionarea_fb, collectionarea_VO);

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


