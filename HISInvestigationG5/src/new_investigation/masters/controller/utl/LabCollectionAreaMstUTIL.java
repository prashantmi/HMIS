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
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import new_investigation.vo.LabCollectionAreaMasterVO;
import hisglobal.vo.PatientCategoryVerificationMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LabCollectionAreaMstDATA;
import new_investigation.masters.controller.fb.LabCollectionAreaMstFB;

public class LabCollectionAreaMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Laboratory Name","Collection Area Name" };
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.LABCOLLECTIONAREA.HIVT_LAB_COLLECTION_AREA_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.LABCOLLECTIONAREA.HIVT_LAB_COLLECTION_AREA_MST.COND.0"));

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


		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"labcollectionareaMst.main.0"));
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
		String masterName = "Laboratory Collection Area Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "L.GSTR_LAB_NAME","2","C.gstr_area_name" };
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
		String strSearchField[] = { "1", " UPPER(gstr_lab_name) ","2","C.gstr_area_name" };
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Laboratory Name");
		viewHdr.add("D");


		viewHdr.add("Collection Area Name");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.labcollectionarea.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveLabCollectionArea(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		LabCollectionAreaMasterVO[] insert_labcollectionarea_VO = null;
		LabCollectionAreaMasterVO[] delete_labcollectionarea_VO = null;
		List insertList=new ArrayList();
		List deleteList=new ArrayList();


		boolean saveFlag = true;
		try
		{

			//getting the old mapped list

			List oldMappedList=LabCollectionAreaMstUTIL.getOldMappedList(labcollectionarea_fb, _request);			

			//getting the New mapped list

			List newMappedList=LabCollectionAreaMstUTIL.getNewMappedList(labcollectionarea_fb, _request);

			if(newMappedList==null)
				newMappedList=new ArrayList();

			if(oldMappedList==null)
				oldMappedList=new ArrayList();


			//getting the insert  list
			insertList=LabCollectionAreaMstUTIL.getCompareList(newMappedList, oldMappedList);	


			//getting the insert  list
			deleteList=LabCollectionAreaMstUTIL.getCompareList(oldMappedList,newMappedList);	

			//getting the insert MasterVO
			if(insertList!=null)				
				insert_labcollectionarea_VO=LabCollectionAreaMstUTIL.getLabCollectionAreaVO(insertList, labcollectionarea_fb);

			//getting the insert MasterVO
			if(deleteList!=null)				
				delete_labcollectionarea_VO=LabCollectionAreaMstUTIL.getLabCollectionAreaVO(deleteList, labcollectionarea_fb);





			UserVO userVO = ControllerUTIL.getUserVO(_request);
			/*			HelperMethods.populate(labcollectionarea_VO, labcollectionarea_fb);
			 */			
			LabCollectionAreaMstDATA.saveLabCollectionArea(insert_labcollectionarea_VO,delete_labcollectionarea_VO, userVO);
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


	public static boolean fetchCheckListLabCollectionArea(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCollectionAreaMasterVO labcollectionarea_VO = new LabCollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = labcollectionarea_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			labCode = concatid[2];
			hospitalCode = concatid[1];



			labcollectionarea_VO.setIsActive(labcollectionarea_fb.getIsActive());

			labcollectionarea_fb.setLabCode(labCode); 
			labcollectionarea_VO.setLabCode(labCode);
			labcollectionarea_fb.setHospitalCode(hospitalCode); 
			labcollectionarea_VO.setHospitalCode(hospitalCode);

			labcollectionarea_fb.setSelectedChk(labcollectionarea_fb.getChk()[0]);


			mp.putAll(LabCollectionAreaMstDATA.fetchLabCollectionArea(labcollectionarea_VO, userVO));
			mp.putAll(LabCollectionAreaMstDATA.getArea(labcollectionarea_VO, userVO));

			/*			mp=LabCollectionAreaMstDATA.fetchCheckListLabCollectionArea(labcollectionarea_VO, userVO);
			 */




			WebUTIL.setMapInSession(mp, _request);

			labcollectionarea_fb.setLabCode(labCode); 
			labcollectionarea_VO.setLabCode(labCode);
			labcollectionarea_fb.setHospitalCode(hospitalCode); 
			labcollectionarea_VO.setHospitalCode(hospitalCode);



			HelperMethods.populate(labcollectionarea_fb, labcollectionarea_VO);

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


	public static boolean fetchLabCollectionArea(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCollectionAreaMasterVO labcollectionarea_VO = new LabCollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);



			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=LabCollectionAreaMstDATA.fetchLabCollectionArea(labcollectionarea_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(labcollectionarea_fb, labcollectionarea_VO);


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


	public static boolean reFetchCheckListLabCollectionArea(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCollectionAreaMasterVO labcollectionarea_VO = new LabCollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",locationCode="",wardCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = labcollectionarea_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			labCode = concatid[2];
			hospitalCode = concatid[1];



			labcollectionarea_VO.setIsActive(labcollectionarea_fb.getIsActive());

			labcollectionarea_fb.setLabCode(labCode); 
			labcollectionarea_VO.setLabCode(labCode);
			labcollectionarea_fb.setHospitalCode(hospitalCode); 
			labcollectionarea_VO.setHospitalCode(hospitalCode);

			mp=LabCollectionAreaMstDATA.getArea(labcollectionarea_VO, userVO);


			/*			mp=LabCollectionAreaMstDATA.fetchCheckListLabCollectionArea(labcollectionarea_VO, userVO);
			 */			WebUTIL.setMapInSession(mp, _request);

			 labcollectionarea_fb.setLabCode(labCode); 
			 labcollectionarea_VO.setLabCode(labCode);
			 labcollectionarea_fb.setHospitalCode(hospitalCode); 
			 labcollectionarea_VO.setHospitalCode(hospitalCode);




			 /*			HelperMethods.populate(labcollectionarea_fb, labcollectionarea_VO);
			  */
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

	public static boolean getArea(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCollectionAreaMasterVO labcollectionarea_VO = new LabCollectionAreaMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			labcollectionarea_VO.setLabCode(labcollectionarea_fb.getLabCode());

			Map mp=new HashMap(); 


			mp=LabCollectionAreaMstDATA.getArea(labcollectionarea_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(labcollectionarea_fb, labcollectionarea_VO);

			objStatus.add(Status.NEW);
		}
		/*catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}*/
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


	public static List getOldMappedList(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		List oldMappedListFromSession=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.LIST_SELECTED_COMBO);
		List oldMappedList=new ArrayList();

		try
		{
			Iterator itr=oldMappedListFromSession.iterator();

			while(itr.hasNext()){

				Entry obj=(Entry)itr.next();

				oldMappedList.add(obj.getValue());

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();


		}

		return oldMappedList;

	}


	public static List getNewMappedList(LabCollectionAreaMstFB labcollectionarea_fb, HttpServletRequest _request)
	{
		List newMappedList=new ArrayList();

		try
		{
			if(labcollectionarea_fb.getMappedList()!=null && labcollectionarea_fb.getMappedList().length!=0){


				for(int i=0; i <  labcollectionarea_fb.getMappedList().length; i++){

					newMappedList.add(labcollectionarea_fb.getMappedList()[i]);


				}




			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();


		}


		return newMappedList;

	}


	public static List getCompareList(List list1, List list2)
	{
		List resultantList=new ArrayList();

		try
		{

			for(int i=0; i < list1.size(); i++){

				String  str1=(String)list1.get(i);

				if(!list2.contains(str1))
					resultantList.add(str1);


			}




		}
		catch (Exception e)
		{
			e.printStackTrace();


		}

		return resultantList;
	}


	public static LabCollectionAreaMasterVO[] getLabCollectionAreaVO(List documentList,LabCollectionAreaMstFB labcollectionarea_fb)
	{
		LabCollectionAreaMasterVO[] labcollectionarea_VO=null;


		try
		{
			if(documentList!=null && documentList.size()!=0){
				labcollectionarea_VO=new LabCollectionAreaMasterVO[documentList.size()];

				for(int i=0; i < labcollectionarea_VO.length; i++){
					labcollectionarea_VO[i]=new LabCollectionAreaMasterVO();
					labcollectionarea_VO[i].setCollectionareaCode((String)documentList.get(i));
					labcollectionarea_VO[i].setLabCode(labcollectionarea_fb.getLabCode());
					labcollectionarea_VO[i].setRemarks(labcollectionarea_fb.getRemarks());
				}
			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();

		}
		return labcollectionarea_VO;

	}







}


