package hisglobal.utility.masterVerification;

import hisglobal.business.GlobalEssentialDelegate;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterVerificationMstUTL  extends ControllerUTIL{
	/**
	 * get the list of the module
	 * @param _request -HttpServletRequest
	 */
		public static void getModuleList(MasterVerificationMstFB _fb,HttpServletRequest _request){	
		
			Status objStatus=new Status();
			//Map mp=new HashMap();
			List moduleList=new ArrayList();
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				setSysdate(_request);
				moduleList=globalDelegate.getModuleList(userVO);
				
		        WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_MODULE_LIST ,moduleList);
		        objStatus.add(Status.NEW);
			
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
			    System.out.println("objStatus in finally"+objStatus);		 
			    System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		//get the list of the masters of selected module
		public static void getMasterList(MasterVerificationMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			List masterList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				String moduleId=_fb.getModuleId();
				masterList=globalDelegate.getAllMasterList(moduleId, userVO);
			
				WebUTIL.setAttributeInSession(_request,Config.MASTER_LIST ,masterList);
				objStatus.add(Status.NEW);
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW,"",e.getMessage());	
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		//get the list of the Column of selected master
		public static void getMasterColumnList(MasterVerificationMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			List masterColumnList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				String masterName=_fb.getMasterName();
				masterColumnList=globalDelegate.getMasterColumnList(masterName, userVO);
				
				WebUTIL.setAttributeInSession(_request,Config.MASTER_COLUMN_LIST ,masterColumnList);
				objStatus.add(Status.NEW);
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW,"",e.getMessage());	
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		
		public static void addColumn(MasterVerificationMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			List addedColumnList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				String columnName=_fb.getColumnName();
				String columnValue=_fb.getColumnValue();
				addedColumnList=(List)_request.getSession().getAttribute("AddedColumnList");
				
				if(addedColumnList==null)
					addedColumnList=new ArrayList();
				Entry entry=new Entry();
				entry.setLabel(columnName);
				entry.setValue(columnValue);
				addedColumnList.add(entry);
				//code to add the column query to main query
				if(_fb.getColumnQuery().equals("")){
					_fb.setColumnQuery(columnValue);
				}
				else
					_fb.setColumnQuery(_fb.getColumnQuery()+", "+columnValue);
				WebUTIL.setAttributeInSession(_request,"AddedColumnList" ,addedColumnList);
				objStatus.add(Status.NEW);
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW,"",e.getMessage());	
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		public static void addWhereCondition(MasterVerificationMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			List addedColumnList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				String columnName=_fb.getWhereConditionColumn();
				String columnValue=_fb.getConditionValue();
				StringBuilder condition=new StringBuilder("");
				addedColumnList=(List)_request.getSession().getAttribute("AddedWhereConditionList");
				
				if(addedColumnList==null){
					addedColumnList=new ArrayList();
				}
				/*if(addedColumnList.size()>1){
					condition.append("and ");
				}*/
				
				Entry entry=new Entry();
				entry.setLabel(columnName);
				entry.setValue(columnValue);
				addedColumnList.add(entry);
				Iterator it=addedColumnList.iterator();
				condition=new StringBuilder("");
				int i=0;
				while (it.hasNext()) {
					 entry=(Entry)it.next();
					 if(i>0){
						 condition.append(" and ");
					 }
					 condition.append(entry.getLabel()+ "=" +entry.getValue());
					 i++;
				}
				//code to add the column query to main query
				//if(_fb.getColumnQuery().equals("")){
					//_fb.setColumnQuery(columnValue);
				//}
				//else
					_fb.setWhereConditionQuery(condition.toString());
				WebUTIL.setAttributeInSession(_request,"AddedWhereConditionList" ,addedColumnList);
				objStatus.add(Status.NEW);
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW,"",e.getMessage());	
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		public static void save(MasterVerificationMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
			String masterColumnArray[]=null;
			String orderByOptionArray[]=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			List whereConditionList=null;
			List columnList=null;
			String criteriaString="";
			String orderBy="";
			String columnHeader="";
			try{
				UserVO userVO=getUserVO(_request);
				//ControllerUTIL.getHospitalVO(_request);
				/*columnList=(List)_request.getSession().getAttribute("AddedColumnList");
				whereConditionList=(List)_request.getSession().getAttribute("AddedWhereConditionList");
				*/
				
				/*String mainQuery=_fb.getMainQuery();
				orderByOptionArray=_fb.getOrderBy();*/
				HelperMethods.populate(masterVerificationVO, _fb);
				
				masterVerificationVO.setColumnHeader(_fb.getColumnHeaderString());
				masterVerificationVO.setOrderOption(_fb.getOrderByString());
				if(!_fb.getColumnHeader().equals("")){
					if(_fb.getColumnHeaderString().equals(""))
						masterVerificationVO.setColumnHeader(_fb.getColumnHeader());
					else
						masterVerificationVO.setColumnHeader(_fb.getColumnHeaderString()+"#"+_fb.getColumnHeader());
				}
				if(!_fb.getOrderBy().equals("")){
					if(_fb.getOrderByString().equals(""))
						masterVerificationVO.setOrderOption(_fb.getOrderBy());
					else
						masterVerificationVO.setOrderOption(_fb.getOrderByString()+"#"+_fb.getOrderBy());
				}
				masterVerificationVO.setCriteriaLabel1(_fb.getCriteriaLabel());
				masterVerificationVO.setCriteriaColumn1(_fb.getCriteriaColumn());
				masterVerificationVO.setCriteriaQuery1(_fb.getCriteriaQuery());
				//masterVerificationVO.setColumnSubHeader(_fb.getColumnSubHeader());
				
				globalDelegate.saveMasterVerification(masterVerificationVO,userVO);
				
				objStatus.add(Status.NEW,"","Record Save Successfully");
			
				
			}
			
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
	public static void getMasterVerficationData(MasterVerificationMstFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
		String chk=_fb.getChk()[0];
		String moduleId=chk.split("\\^")[0];
		String serialNo=chk.split("\\^")[1];
		GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
		try{
			UserVO userVO=getUserVO(_request);
			
			masterVerificationVO.setModuleId(moduleId);
			masterVerificationVO.setSerialNo(serialNo);
			masterVerificationVO.setHospitalCode(userVO.getHospitalCode());
						
			masterVerificationVO=globalDelegate.getMasterVerification(masterVerificationVO,userVO);
			HelperMethods.populate(_fb,masterVerificationVO);
			//_fb.setOrderBy(masterVerificationVO.getOrderOption());
			_fb.setColumnHeader("");
			_fb.setOrderByString(masterVerificationVO.getOrderOption());
			_fb.setColumnHeaderString(masterVerificationVO.getColumnHeader());
			_fb.setCriteriaLabel(masterVerificationVO.getCriteriaLabel1());
			_fb.setCriteriaColumn(masterVerificationVO.getCriteriaColumn1());
			_fb.setCriteriaQuery(masterVerificationVO.getCriteriaQuery1());
			
		}
		
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
	
	public static boolean modify(MasterVerificationMstFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
		boolean flag=false;
		GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
		try{
			UserVO userVO=getUserVO(_request);
			
			HelperMethods.populate(masterVerificationVO, _fb);
			masterVerificationVO.setColumnHeader(_fb.getColumnHeaderString());
			masterVerificationVO.setOrderOption(_fb.getOrderByString());
			if(!_fb.getColumnHeader().equals("")){
				if(_fb.getColumnHeaderString().equals(""))
					masterVerificationVO.setColumnHeader(_fb.getColumnHeader());
				else
					masterVerificationVO.setColumnHeader(_fb.getColumnHeaderString()+"#"+_fb.getColumnHeader());
			}
			if(!_fb.getOrderBy().equals("")){
				if(_fb.getOrderByString().equals(""))
					masterVerificationVO.setOrderOption(_fb.getOrderBy());
				else
					masterVerificationVO.setOrderOption(_fb.getOrderByString()+"#"+_fb.getOrderBy());
			}
			masterVerificationVO.setCriteriaLabel1(_fb.getCriteriaLabel());
			masterVerificationVO.setCriteriaColumn1(_fb.getCriteriaColumn());
			masterVerificationVO.setCriteriaQuery1(_fb.getCriteriaQuery());
			//masterVerificationVO.setColumnSubHeader(_fb.getColumnSubHeader());
			
			globalDelegate.modify(masterVerificationVO,userVO);
			flag=true;
			objStatus.add(Status.NEW,"","Record Updated Successfully");
		}
		
		catch(HisDataAccessException e){
			flag=false;
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
		return flag;
	}
	
	public static boolean testQuery(MasterVerificationMstFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
		boolean flag=false;
		GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
		try{
			UserVO userVO=getUserVO(_request);
			
			HelperMethods.populate(masterVerificationVO, _fb);
			masterVerificationVO.setColumnHeader(_fb.getColumnHeaderString());
			masterVerificationVO.setOrderOption(_fb.getOrderByString());
			if(!_fb.getColumnHeader().equals("")){
				masterVerificationVO.setColumnHeader(_fb.getColumnHeaderString()+"#"+_fb.getColumnHeader());
			}
			if(!_fb.getOrderBy().equals("")){
				masterVerificationVO.setOrderOption(_fb.getOrderByString()+"#"+_fb.getOrderBy());
			}
			masterVerificationVO.setCriteriaLabel1(_fb.getCriteriaLabel());
			masterVerificationVO.setCriteriaColumn1(_fb.getCriteriaColumn());
			masterVerificationVO.setCriteriaQuery1(_fb.getCriteriaQuery());
			//masterVerificationVO.setColumnSubHeader(_fb.getColumnSubHeader());
			
			globalDelegate.modify(masterVerificationVO,userVO);
			flag=true;
			objStatus.add(Status.NEW,"","Record Updated Successfully");
		}
		
		catch(HisDataAccessException e){
			flag=false;
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
		return flag;
	}
	
		
}//end class
