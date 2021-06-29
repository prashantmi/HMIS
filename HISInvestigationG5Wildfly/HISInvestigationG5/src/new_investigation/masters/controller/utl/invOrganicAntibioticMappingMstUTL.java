package new_investigation.masters.controller.utl;

import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.utility.Entry;
import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.invOrganicAntibioticMappingMstDATA;
import new_investigation.masters.controller.fb.invOrganicAntibioticMappingMstFB;
import new_investigation.vo.invOrganicAntibioticMappingMstVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;



public class invOrganicAntibioticMappingMstUTL implements MasterInterface {

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
			String[] columnHdr = { "Organic Name", "Antibiotic Name"};
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
			return comboQuery;
		}
		
		public String[] getDeleteQuery()
		{
			String deleteQuery[] = new String[1];
			String seatId = httpSession.getAttribute("SEATID").toString();

			deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE_GLOBAL.LABCANNED.HIVT_ORGANISM_ANTIBIOTIC_MST").replace("?",seatId);
			deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE_GLOBAL.LABCANNED.HIVT_ORGANISM_ANTIBIOTIC_MST.COND.0"));

			return deleteQuery;
		}
		
		
		public String getJsFiles() 
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		 
		
		public String getMainQuery(String[] cmb)
		{
			UserVO vo=new UserVO();
			
			
			StringBuffer brMainQuery = new StringBuffer();
			
			List<String> list = new ArrayList<String>();
			/*list.add(vo.getHospitalCode());*/
			brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"mapping.main.0"));
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
			String masterName = "Organic Antibiotic Mapping Master";
			return masterName;
		}

		
		public String[] getOrderBy()
		{
			String orderBy[] = { "1", "L.hgstr_organism_name" };
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
			String strSearchField[] = { "1", "L.hgstr_organism_name", "2", "C.hgstr_antibiotic_name"};
			return strSearchField;
		}
		
		public List<String> getViewHeader()
		{
			List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Organism Name");
			viewHdr.add("D");
			viewHdr.add("Antibiotic Name");
			viewHdr.add("D");
			
				
			return viewHdr;
		}


		public String getViewQuery()
		{
			return new_investigation.qryHandler_investigation.getQuery(1, "select.organism_antibiotic.view.2");
		}

		public String isGlobal()
		{
			return "null";

		}
		
		public boolean reportInterFaceRequired()
		{
			return false;
		}
		
		
		
		
		public static boolean saveOrganicAntibiotic(invOrganicAntibioticMappingMstFB labcanned_fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();

			invOrganicAntibioticMappingMstVO[] insert_labcannedmaster_VO = null;
			invOrganicAntibioticMappingMstVO[] modify_testgroupinfolocal_VO = null;
			invOrganicAntibioticMappingMstVO[] delete_labcannedmaster_VO = null;
			List insertList=new ArrayList();
			List deleteList=new ArrayList();

			List userTestSeqList=new ArrayList();

			List testSeqResultList=new ArrayList();
			List modifyTestNameList=new ArrayList();
			List modifyTestSeqList=new ArrayList();
			
			boolean saveFlag = true;
			try
			{
			
			//getting the old mapped list
				
			List oldMappedList=invOrganicAntibioticMappingMstUTL.getOldMappedList(labcanned_fb, _request);			
				
			//getting the New mapped list
			
			List newMappedList=invOrganicAntibioticMappingMstUTL.getNewMappedList(labcanned_fb, _request);
				
			if(newMappedList==null)
				newMappedList=new ArrayList();
				
			if(oldMappedList==null)
				oldMappedList=new ArrayList();
				
			//gettign the test seq numbers list
			if(labcanned_fb.getAntibioticSeqNo()!=null)
			{
			for(int j=0;j<labcanned_fb.getAntibioticSeqNo().length;j++)
				userTestSeqList.add(labcanned_fb.getAntibioticSeqNo()[j]);
			}
			
			
				//getting the insert  list
			//	insertList=invOrganicAntibioticMappingMstUTL.getCompareList(newMappedList, oldMappedList);	
				
				
				//getting the insert  list
				deleteList=invOrganicAntibioticMappingMstUTL.getCompareList(oldMappedList,newMappedList);	
				
				if(newMappedList!=null)
				{
				for(int i=0; i < newMappedList.size(); i++){

					String  str1=(String)newMappedList.get(i);
					String cannedStr=(String)userTestSeqList.get(i);
					
					if(!oldMappedList.contains(str1))
					{	
						insertList.add(str1);
					testSeqResultList.add(cannedStr);
					}
					else
					{
						modifyTestNameList.add(str1);
						modifyTestSeqList.add(cannedStr);
					}

				}

				userTestSeqList=testSeqResultList;
			}
			
				//getting the insert MasterVO
				if(insertList!=null)				
					insert_labcannedmaster_VO=invOrganicAntibioticMappingMstUTL.getGlobalLabCannedVO(insertList, labcanned_fb,userTestSeqList,"1");
				
				//getting the insert MasterVO
				if(deleteList!=null)				
					delete_labcannedmaster_VO=invOrganicAntibioticMappingMstUTL.getGlobalLabCannedVO(deleteList, labcanned_fb,userTestSeqList,"0");
				
				//getting the modified MasterVO
				if(modifyTestNameList!=null)				
					modify_testgroupinfolocal_VO=invOrganicAntibioticMappingMstUTL.getGlobalLabCannedVO(modifyTestNameList, labcanned_fb,modifyTestSeqList,"1");

				
				
				
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				
				invOrganicAntibioticMappingMstDATA.saveOrganicAntibiotic(insert_labcannedmaster_VO,delete_labcannedmaster_VO, userVO,modify_testgroupinfolocal_VO);
				
				labcanned_fb.setOrganicName("-1");
				
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
		
		public static invOrganicAntibioticMappingMstVO[] getGlobalLabCannedVO(List documentList,invOrganicAntibioticMappingMstFB labcanned_fb,List testSeqList, String insDel)
		{
			invOrganicAntibioticMappingMstVO[] labcannedmaster_VO=null;
			
			try
			{
				if(documentList!=null && documentList.size()!=0){
					labcannedmaster_VO=new invOrganicAntibioticMappingMstVO[documentList.size()];
					
				for(int i=0; i < labcannedmaster_VO.length; i++){
					labcannedmaster_VO[i]=new invOrganicAntibioticMappingMstVO();
					labcannedmaster_VO[i].setAntibioticName((String)documentList.get(i));
					labcannedmaster_VO[i].setOrganicName(labcanned_fb.getOrganicName());
					labcannedmaster_VO[i].setAntibioticSeqNo(labcanned_fb.getOrganicName());
					if(insDel.equals("1"))
						labcannedmaster_VO[i].setAntibioticSeqNo((String)testSeqList.get(i)==null?"":(String)testSeqList.get(i));
					
					}
					}
			}		
			catch (Exception e)
			{
			e.printStackTrace();
				
			}
	return labcannedmaster_VO;
			
	}
		
		public static List getOldMappedList(invOrganicAntibioticMappingMstFB labcanned_fb, HttpServletRequest _request)
		{
			List oldMappedListFromSession=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.LIST_SELECTED_CANNED_COMBO);
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
		
		
		public static List getNewMappedList(invOrganicAntibioticMappingMstFB labcanned_fb, HttpServletRequest _request)
		{
					List newMappedList=new ArrayList();
			
			try
			{
				if(labcanned_fb.getMappedList()!=null && labcanned_fb.getMappedList().length!=0){
					
					
				for(int i=0; i <  labcanned_fb.getMappedList().length; i++){
					
					newMappedList.add(labcanned_fb.getMappedList()[i]);
						
					
					}
				
					}
			}		
			catch (Exception e)
			{
			e.printStackTrace();
			
				
			}
			
			
			return newMappedList;
			
	}
		public static boolean fetchOrganicAntibiotic(invOrganicAntibioticMappingMstFB labcanned_fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			invOrganicAntibioticMappingMstVO labcannedmaster_VO = new invOrganicAntibioticMappingMstVO();
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);

				Map mp=new HashMap();
				String chk="",labCode="",hospitalCode="";
				String[] concatid={};

				// Fetching Selected Record Primary Key
				chk = labcanned_fb.getChk()[0].replace("$", "@");  //^
				concatid = chk.split("@");
			String	organicName = concatid[0];
			String	antibioticName = concatid[1];
				/*labCode = concatid[2];*/
				hospitalCode = concatid[2];
				
				


				/*labcannedmaster_VO.setIsActive(labcanned_fb.getIsActive());*/

				labcanned_fb.setOrganicName(organicName);
				labcannedmaster_VO.setOrganicName(organicName);
				labcanned_fb.setAntibioticName(antibioticName);
				labcannedmaster_VO.setAntibioticName(antibioticName);
				
				labcanned_fb.setSelectedChk(labcanned_fb.getChk()[0]);
				/*labcanned_fb.setSelectedChk(labcanned_fb.getChk()[0]);*/

				//mp.putAll(invOrganicAntibioticMappingMstDATA.fetchGlobalLabCanned(labcannedmaster_VO, userVO));
				mp.putAll(invOrganicAntibioticMappingMstDATA.getGlobalLabCanned(labcannedmaster_VO, userVO));
				
				WebUTIL.setMapInSession(mp, _request);

				labcanned_fb.setOrganicName(organicName); 
				labcannedmaster_VO.setOrganicName(organicName);
				


				HelperMethods.populate(labcanned_fb, labcannedmaster_VO);

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

		
			public static boolean fetchOrganicAntibioticD(invOrganicAntibioticMappingMstFB fb, HttpServletRequest _request)
			{
				Status objStatus = new Status();
				invOrganicAntibioticMappingMstVO filmMasterVO = new invOrganicAntibioticMappingMstVO();
				try
				{
					UserVO userVO = ControllerUTIL.getUserVO(_request);
					ControllerUTIL.setSysdate(_request);
					
					Map mp=new HashMap(); 
					

					 
					mp=invOrganicAntibioticMappingMstDATA.fetchOrganicAntibioticD1(filmMasterVO, userVO);
					WebUTIL.setMapInSession(mp, _request);
					
					HelperMethods.populate(fb, filmMasterVO);
					//  fb.setResultEntryForm(filmMasterVO.getresultEntryForm());
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
		
		
		public static boolean savemodifyOrganicAntibiotic(invOrganicAntibioticMappingMstFB _fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			boolean hasFlag=true;
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				invOrganicAntibioticMappingMstVO testNewMasterVO = new invOrganicAntibioticMappingMstVO();
				//_fb.setSampleCode(testNewMasterVO.getSampleCode());
				
			 
			 	 HelperMethods.populate(testNewMasterVO, _fb);
				 //testNewMasterVO.setresultEntryForm(_fb.getResultEntryForm());
			 	 
				invOrganicAntibioticMappingMstDATA.savemodifyOrganicAntibiotic(testNewMasterVO, userVO);
				
				objStatus.add(Status.DONE, "Data Modified Successfully", "");
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
		
		
		public static boolean reFetchOrganicAntibiotic(invOrganicAntibioticMappingMstFB labcanned_fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			invOrganicAntibioticMappingMstVO labcannedmaster_VO = new invOrganicAntibioticMappingMstVO();
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);

				Map mp=new HashMap();
				String chk="",organismName="",antibioticName="";
				String[] concatid={};

				// Fetching Selected Record Primary Key
				chk = labcanned_fb.getSelectedChk().replace("$", "@");
				concatid = chk.split("@");
				organismName = concatid[0];
				antibioticName = concatid[1];
		

				labcanned_fb.setOrganicName(organismName);
				labcanned_fb.setAntibioticName(antibioticName);
				labcannedmaster_VO.setOrganicName(organismName);
				labcannedmaster_VO.setAntibioticName(antibioticName);
			
				mp=invOrganicAntibioticMappingMstDATA.getGlobalLabCanned(labcannedmaster_VO, userVO);


				WebUTIL.setMapInSession(mp, _request);

				labcanned_fb.setOrganicName(organismName); 
				labcannedmaster_VO.setOrganicName(organismName);
				
				labcanned_fb.setAntibioticName(organismName); 
				labcannedmaster_VO.setAntibioticName(antibioticName);
				


				HelperMethods.populate(labcanned_fb, labcannedmaster_VO);

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
		
		public static boolean getGlobalLabCanned(invOrganicAntibioticMappingMstFB labcanned_fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			invOrganicAntibioticMappingMstVO labcannedmaster_VO = new invOrganicAntibioticMappingMstVO();
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);
				
				labcannedmaster_VO.setOrganicName(labcanned_fb.getOrganicName());

				Map mp=new HashMap(); 


				mp=invOrganicAntibioticMappingMstDATA.getGlobalLabCanned(labcannedmaster_VO, userVO);
				WebUTIL.setMapInSession(mp, _request);

				HelperMethods.populate(labcanned_fb, labcannedmaster_VO);
				
				objStatus.add(Status.NEW);
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

