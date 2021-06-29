package new_investigation.masters.controller.utl;

import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.masterutil.MasterInterface;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.FilmMStDATA;
import new_investigation.masters.controller.data.TestMstDATA;
import new_investigation.masters.controller.data.invOrganicMstDATA;
import new_investigation.masters.controller.fb.TestMstFB;
import new_investigation.masters.controller.fb.filmMstFB;
import new_investigation.masters.controller.fb.invOrganicMstFB;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.invOrganicMstVO;
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
import hisglobal.vo.UserVO;




public class invOrganicMstUTL implements MasterInterface {

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
			String[] columnHdr = { "Organic Name"," Name Order"};
			
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

			deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.ORGANIC.HIVT_ORGANISM_MST").replace("?",seatId);
			deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.ORGANIC.HIVT_ORGANISM_MST.COND.0"));

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
			brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"organicMst.main.0"));
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
			String masterName = "Organism Master";
			return masterName;
		}

		
		public String[] getOrderBy()
		{
			String orderBy[] = { "1", "f.hgstr_organism_name" };
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
			String strSearchField[] = { "1", "f.hgstr_organism_name"};
			return strSearchField;
		}
		
		public List<String> getViewHeader()
		{
			List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Organism Name");
			viewHdr.add("D");
			viewHdr.add("Name Order");
			viewHdr.add("D");
			
				
			return viewHdr;
		}


		public String getViewQuery()
		{
			return new_investigation.qryHandler_investigation.getQuery(1, "select.organic.view.2");
		}

		public String isGlobal()
		{
			return "null";

		}
		
		public boolean reportInterFaceRequired()
		{
			return false;
		}
		
		
		
		
		public static boolean saveOrganism(invOrganicMstFB _fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			boolean saveFlag = true;
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				invOrganicMstVO testNewMasterVO = new invOrganicMstVO();
				HelperMethods.populate(testNewMasterVO, _fb);
				//testNewMasterVO.setresultEntryForm(_fb.getResultEntryForm());
				invOrganicMstDATA.saveOrganism(testNewMasterVO, userVO);
				objStatus.add(Status.DONE, "  Data Saved Successfully", "");
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

		
			public static boolean fetchOrganism(invOrganicMstFB fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			invOrganicMstVO testNewMasterVO = new invOrganicMstVO();
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);
				
				Map mp=new HashMap();
				

				 // Fetching Selected Record Primary Key
				String chk = fb.getChk()[0].replace("$", "@");
				String[] concatid = chk.split("@");
				String namecode= concatid[0];
				
				HelperMethods.populate(testNewMasterVO,fb );
				mp=invOrganicMstDATA.fetchOrganism(testNewMasterVO, userVO,namecode);
				WebUTIL.setMapInSession(mp, _request);
				
				
		       	/* fb.setSelectedChk(fb.getChk()[0]);
				 fb.setFilmid(filmid);
				*/
				HelperMethods.populate(fb, testNewMasterVO);
				  
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

		
		/*public static boolean fetchOrganism1(invOrganicMstFB fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			FilmMstVO filmMasterVO = new FilmMstVO();
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);
				
				Map mp=new HashMap(); 
				

				 
				mp=invOrganicMstDATA.fetchFilmD(filmMasterVO, userVO);
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
		}*/
		
		
		public static boolean savemodifyOrganism(invOrganicMstFB _fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			boolean hasFlag=true;
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				invOrganicMstVO testNewMasterVO = new invOrganicMstVO();
				//_fb.setSampleCode(testNewMasterVO.getSampleCode());
				
			 
			 	 HelperMethods.populate(testNewMasterVO, _fb);
				 //testNewMasterVO.setresultEntryForm(_fb.getResultEntryForm());
			 	 
				invOrganicMstDATA.savemodifyOrganism(testNewMasterVO, userVO);
				
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
		
		
		public static boolean reFetchOrganism(invOrganicMstFB fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			invOrganicMstVO testNewMasterVO = new invOrganicMstVO();
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);
				
				Map mp=new HashMap();
				
                 String namecode=fb.getOrganicNameCode();
				// Fetching Selected Record Primary Key
                 mp=invOrganicMstDATA.fetchOrgqanismD(testNewMasterVO, userVO,namecode);
				WebUTIL.setMapInSession(mp, _request);
				
				HelperMethods.populate(fb, testNewMasterVO);
			//	fb.setResultEntryForm(testNewMasterVO.getresultEntryForm());
				//  fb.setSelectedChk(chkk);
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

