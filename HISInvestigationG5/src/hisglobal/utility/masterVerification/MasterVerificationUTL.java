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
import hisglobal.vo.UserVO;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterVerificationUTL  extends ControllerUTIL{
	/**
	 * get the list of the module
	 * @param _request -HttpServletRequest
	 */
		public static void getModuleList(MasterVerificationFB _fb,HttpServletRequest _request){	
		
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
		public static void getMasterDetailList(MasterVerificationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			//Map mp=new HashMap();
			List<MasterVerificationVO> masterVerificationVOList=new ArrayList<MasterVerificationVO>();
			List masterOptionList=new ArrayList();
			List orderByOptionList=new ArrayList();
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				String moduleId=_fb.getModuleId();
				masterVerificationVOList=globalDelegate.getMasterListByModuleId(moduleId, userVO);
				
				for(int i=0;i<masterVerificationVOList.size();i++){
					Entry entry=new Entry();
					entry.setLabel(masterVerificationVOList.get(i).getMainHeader());
					entry.setValue(masterVerificationVOList.get(i).getMainHeader());
					masterOptionList.add(entry);
				}
				
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_MASTER_OPTION_LIST ,masterOptionList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_VO_LIST ,masterVerificationVOList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_ORDERBY_OPTION_LIST ,null);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_CRITERIA_OPTION_LIST ,null);
				objStatus.add(Status.NEW);
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_MASTER_OPTION_LIST ,null);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_ORDERBY_OPTION_LIST ,null);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_CRITERIA_OPTION_LIST ,null);
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
		
		//set order by and criteria option if available
		public static void setOrderByOption(MasterVerificationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			//Map mp=new HashMap();
			List<MasterVerificationVO> masterVerificationVOList=null;
			MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
			String masterColumnArray[]=null;
			String orderByOptionArray[]=null;
			List orderByOptionList=new ArrayList();
			List criteriaOptionList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				String masterName=_fb.getMainHeader();
				masterVerificationVOList=(List)_request.getSession().getAttribute(Config.MASTER_VERIFICATION_VO_LIST);
				if(masterVerificationVOList!=null){
					for(int i=0;i<masterVerificationVOList.size();i++){
						if(masterName.equals(masterVerificationVOList.get(i).getMainHeader())){
							masterVerificationVO=masterVerificationVOList.get(i);
							break;
						}
					}
				}
				masterColumnArray=masterVerificationVO.getColumnHeader().split("#");
				_fb.setColumnName(masterVerificationVO.getColumnHeader().replace("#", "@"));
				if(masterVerificationVO.getOrderOption()!=null){
					orderByOptionArray=masterVerificationVO.getOrderOption().split("#");
						
					//get the order by field
					for(int i=0;i<orderByOptionArray.length;i++){
						Entry entry=new Entry();
						entry.setLabel(orderByOptionArray[i]);
						entry.setValue(orderByOptionArray[i]);
						orderByOptionList.add(entry);
					}
				}
				//get the criteria
				if(masterVerificationVO.getCriteriaColumn1()!=null){
					criteriaOptionList=new ArrayList();
					Entry entry=new Entry();
					entry.setValue(masterVerificationVO.getCriteriaColumn1());
					entry.setLabel(masterVerificationVO.getCriteriaLabel1());
					criteriaOptionList.add(entry);
				}
				
				//WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_VO_LIST ,masterVerificationVOList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_ORDERBY_OPTION_LIST ,orderByOptionList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_VO ,masterVerificationVO);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_CRITERIA_OPTION_LIST ,criteriaOptionList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_CRITERIA_DATA ,null);
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
		
		
		//get the data for the criteria selected
		public static void setCriteriaOption(MasterVerificationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			//Map mp=new HashMap();
			MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
			//List criteriaDataList=new ArrayList();
			List criteriaDataList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			try{
				UserVO userVO=getUserVO(_request);
				//String masterName=_fb.getMainHeader();
				masterVerificationVO=(MasterVerificationVO)_request.getSession().getAttribute(Config.MASTER_VERIFICATION_VO);
				if(!_fb.getCriteriaColumn().equals("-1")){
				String criteriaQuery=""; 
				if(masterVerificationVO!=null){
					if(masterVerificationVO.getCriteriaColumn1().equals(_fb.getCriteriaColumn())){
						criteriaQuery=masterVerificationVO.getCriteriaQuery1();
					}
				}
				criteriaDataList=globalDelegate.getCriteriaData(criteriaQuery,userVO);
				}
				WebUTIL.setAttributeInSession(_request,Config.MASTER_CRITERIA_DATA ,criteriaDataList);
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
		
		//get the data of the selected master based on the criteria given and order by selected
		public static void getMasterData(MasterVerificationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
			String masterColumnArray[]=null;
			String orderByOptionArray[]=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			List masterDataList=null;
			String criteriaString="";
			String orderBy="";
			Map dataMap=null;
			try{
				UserVO userVO=getUserVO(_request);
				ControllerUTIL.getHospitalVO(_request);
				masterVerificationVO=(MasterVerificationVO)_request.getSession().getAttribute(Config.MASTER_VERIFICATION_VO);
				
				String mainQuery=masterVerificationVO.getMainQuery();
				orderByOptionArray=_fb.getOrderBy();
				masterColumnArray=masterVerificationVO.getColumnHeader().split("#");
				_fb.setColumnName(masterVerificationVO.getColumnHeader().replace("#", "@"));
				
				//set criteria condition
				if(masterVerificationVO.getCriteriaColumn1()!=null){
					if(!_fb.getCriteriaCode().equals("-1") && !_fb.getCriteriaColumn().equals("-1")){
						criteriaString=" and "+_fb.getCriteriaColumn()+" =" +_fb.getCriteriaCode();
						mainQuery+=criteriaString;
						_fb.setCriteriaLabel(masterVerificationVO.getCriteriaLabel1());
						_fb.setCriteriaValue(setCriteriaValue(_fb.getCriteriaCode(),_request));
					}
				}
				
				//set order by
				if(_fb.getOrderByString()!=null && !_fb.getOrderByString().equals("")){
					orderBy+=" order by ";
					//String orderByArray[]=_fb.getOrderByString().split("#");
					//System.out.println("_request.getParameter(orderByString) "+_request.getParameter("orderByString"));
					String orderByArray[]=_fb.getOrderByString().split("@");
					for(int i=0;i<orderByArray.length;i++)
						orderBy=orderBy +orderByArray[i]+",";
					orderBy=orderBy.substring(0,orderBy.length()-1);
				}
				mainQuery+=orderBy;
				
				//set the group query if All is choosen from criteria option
				_fb.setIsGrouped("0");
				if(masterVerificationVO.getCriteriaColumn1()!=null){
					if(_fb.getCriteriaColumn().equals("-1")){
						mainQuery=masterVerificationVO.getGroupQuery();
						_fb.setIsGrouped("1");
					}
				}
				//String isActive=_fb.getIsActive();
				String isActive=Config.IS_VALID_ACTIVE;
				masterDataList=globalDelegate.getMasterDataList(mainQuery,masterColumnArray,isActive,userVO);
				
				//if the groupquery is fired then set the map for grouped item
				if(_fb.getIsGrouped().equals("1") && masterDataList!=null){
					dataMap=setDataForGroupDisplay(masterDataList,masterColumnArray.length);
				}
				WebUTIL.setAttributeInSession(_request,Config.MASTER_DATA_MAP,dataMap);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_DATA_LIST ,masterDataList);
				objStatus.add(Status.NEW);
			
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_DATA_LIST ,masterDataList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_DATA_MAP,dataMap);
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


		public static void convertToPDF(MasterVerificationFB fb,
				HttpServletRequest request,HttpServletResponse response ) {
			String htmlContent=fb.getHtmlContent();
			//Status objStatus=new Status();
			ByteArrayOutputStream baosPDF=null;
			ServletOutputStream os=null;
			try{
				
				//baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request, c);
				baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request, htmlContent);
				response.setContentType("application/pdf");
				os=response.getOutputStream();
				os.flush();
				baosPDF.writeTo(os);
				//byte array[]=baosPDF.toByteArray();
				
				//objStatus.add(Status.DONE);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try{
					os.close();
					baosPDF.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				//WebUTIL.setStatus(request,objStatus);
				//System.out.println("objStatus in finally"+objStatus);		 
				//System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		//get criteriaCodeName from criteriaCode
		public static String setCriteriaValue(String criteriaCode,HttpServletRequest request){
			String criteriaColumnName="";
			List <Entry> list=(List)request.getSession().getAttribute(Config.MASTER_CRITERIA_DATA);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getValue().equals(criteriaCode)){
					criteriaColumnName=list.get(i).getLabel();
					break;
				}
			}
			
			return criteriaColumnName;
		}

	
		public static Map setDataForGroupDisplay(List masterDataList,int columnCount){
			Map <String,List>dataMap=new LinkedHashMap<String, List>();
			for(int i=0;i<masterDataList.size();i++){
				List mapList=new ArrayList();
				List list=(List)masterDataList.get(i);
				String mapKey="";
				if(list.get(columnCount)==null)
					mapKey="-";
				else
					mapKey=list.get(columnCount).toString();

				if(dataMap.containsKey(mapKey)){
					mapList=dataMap.get(mapKey);
					mapList.add(list);
					dataMap.put(mapKey, mapList);
				}
				else{
					mapList.add(list);
					dataMap.put(mapKey, mapList);
				}
			}
			return dataMap;
		}
		
		
}//end class
