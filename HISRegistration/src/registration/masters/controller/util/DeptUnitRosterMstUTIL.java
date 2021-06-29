/**
 * 
 */
package registration.masters.controller.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import registration.config.RegistrationConfig;
import registration.masters.controller.data.DepartmentMstDATA;
import registration.masters.controller.data.DeptUnitRoomMstDATA;
import registration.masters.controller.data.DeptUnitRosterMstDATA;
import registration.masters.controller.data.UnitMstDATA;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;
import vo.registration.DeptUnitRosterVO;
import vo.registration.RoomVO;
import vo.registration.RosterMasterVO;
import vo.registration.ShiftVO;
import vo.registration.UnitVO;
import vo.registration.DeptUnitRosterVO.DeptUnitDayOfWeekRosterVO;
import vo.registration.DeptUnitRosterVO.DeptUnitDayOfWeekShiftRosterVO;
import vo.registration.DeptUnitRosterVO.WeekOfMonthVO;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

/**
 @author s.singaravelan
 * Creation Date:- 23-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 * 
 */
public class DeptUnitRosterMstUTIL{		
	
	
	//To populate the Unit,Room and Department values in the DepartmentUnitRoster Master Screen 
	public static void getDeptUnitRosterEssentials(HttpServletRequest request,DeptUnitRosterVO rosterModel)
	{
		DeptUnitRosterMstDATA objDeptUnitRosterData_p = null;
		HttpSession session = request.getSession(true);  
		Map SessionMap=new HashMap();
		int noOfShifts=0;

		try{
			
			objDeptUnitRosterData_p = new DeptUnitRosterMstDATA();
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List departmentList,optionShift=new ArrayList();
			
			departmentList=objDeptUnitRosterData_p.getDeptList(uservo);
			optionShift=objDeptUnitRosterData_p.getShiftEssentials(uservo);
			
			session.setAttribute("departmentList",departmentList);
			session.setAttribute("optionShift",optionShift);

			session.setAttribute("unitList",new ArrayList());
			session.setAttribute("roomList",new ArrayList());
			
			session.setAttribute("rosterCurrentModel",null);
			
			noOfShifts=getNumberOfShifts(request);
			request.getSession().setAttribute("noOfShifts",noOfShifts);

			}
		catch (Exception e) {
			e.printStackTrace();
			rosterModel.setStrWarning("Department/Shift Details Not Found");
			session.setAttribute("departmentList",new ArrayList());
			session.setAttribute("unitList",new ArrayList());
			session.setAttribute("roomList",new ArrayList());
		}
		
		
	}
	
	//To Get Dept Based Unit Details in the Dept Unit Roster Mst
	public static DeptUnitRosterVO getRosterUnitEssentials(HttpServletRequest request,DeptUnitRosterVO rosterModel)
	{
		DeptUnitRosterMstDATA objDeptUnitRosterData_p = null;
		HttpSession session=request.getSession();
		try{
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			objDeptUnitRosterData_p = new DeptUnitRosterMstDATA();
			List unitList=objDeptUnitRosterData_p.getUnitList(rosterModel, uservo);
			if(unitList.size()<1)
				rosterModel.setStrWarning("Units Not Found for the Selected Department..!");
			session.setAttribute("unitList",unitList);
			session.setAttribute("roomList",new ArrayList());
			rosterModel.setStrUnitCode("-1");
			rosterModel.setStrRoomCode("-1");
			session.setAttribute("rosterModel", rosterModel);
			session.setAttribute("rosterCurrentModel",null);

						
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return rosterModel;
		
	}
	
	//To Get Dept Based Unit Details in the Dept Unit Roster Mst
	public static DeptUnitRosterVO getRosterRoomEssentials(HttpServletRequest request,DeptUnitRosterVO rosterModel)
	{
		DeptUnitRosterMstDATA objDeptUnitRosterData_p = null;
		try{
			UserVO uservo = ControllerUTIL.getUserVO(request);				
			objDeptUnitRosterData_p = new DeptUnitRosterMstDATA();
			List roomList=objDeptUnitRosterData_p.getRoomList(rosterModel, uservo);
			if(roomList.size()<1)
				rosterModel.setStrWarning("Rooms Not Found for the Selected Unit..!");
			request.getSession().setAttribute("roomList",roomList);
			rosterModel.setStrRoomCode("-1");
			request.getSession().setAttribute("rosterModel", rosterModel);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return rosterModel;

	}
	
	//To Get Roster Details in the Dept Unit Roster Mst
	public static DeptUnitRosterVO getDeptUnitRoomRosterEssentials(HttpServletRequest request,DeptUnitRosterVO rosterModel)
	{
		DeptUnitRosterMstDATA objDeptUnitRosterData_p = null;
		RosterMasterVO rosterMasterVO=new RosterMasterVO();

		String roomCodeSequenceNo[]=rosterModel.getStrRoomCode().split("#");
		rosterModel.setStrRoomCode(roomCodeSequenceNo[0]);
		rosterModel.setStrRoomSequence(roomCodeSequenceNo[1]);
		
		rosterMasterVO.setStrDeptUnitCode(rosterModel.getStrDeptCode()+rosterModel.getStrUnitCode());
		rosterMasterVO.setStrRoomCode(rosterModel.getStrRoomCode());
		rosterMasterVO.setStrRoomSequence(rosterModel.getStrRoomSequence());
		
		try{
			
			UserVO uservo = ControllerUTIL.getUserVO(request);				
			Collection col = objDeptUnitRosterData_p.getRoster4DeptUnit(rosterMasterVO,uservo);
			DeptUnitRosterVO vo = new DeptUnitRosterVO(col);
			WebUTIL.setAttributeInSession(request, RegistrationConfig.DEPT_UNIT_ROSTER_VO, vo);
			
			String deptCode=rosterModel.getStrDeptCode();
			String roomCode=roomCodeSequenceNo[0]+"#"+roomCodeSequenceNo[1]+"#"+roomCodeSequenceNo[2];
			 
			rosterModel=populateFB(request, rosterModel, vo); 
			
			rosterModel.setStrDeptCode(deptCode);
			rosterModel.setStrRoomCode(roomCode);
			request.getSession().setAttribute("rosterModel", rosterModel);

			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
	  return rosterModel;

	}
	
	
	 public static void save(HttpServletRequest request,DeptUnitRosterVO rosterModel,String strMode) 
	 {
		   Status objStatus=new Status();
		   DeptUnitRosterVO vo = new DeptUnitRosterVO();
		   UserVO uservo = ControllerUTIL.getUserVO(request);				
		   String roomCode[]=rosterModel.getStrRoomCode().split("#");
		   String unitRoomMasterCapacity="";
		   rosterModel.setStrRoomCode(roomCode[0]);
		   rosterModel.setStrRoomSequence(roomCode[1]);
		   populateDeptUnitRosterVO(request, rosterModel);
		   Collection col=rosterModel.getColRosterMaster();
		   
		   unitRoomMasterCapacity=roomCode[2];		   
		   DeptUnitRosterMstDATA.save(col,unitRoomMasterCapacity,rosterModel.getStrDeptUnitCode(),rosterModel.getStrRoomCode(), uservo);
		   
		   UnitVO unitMasterVO=new UnitVO();
		   HelperMethods.populate(unitMasterVO, vo);
		  // DeptUnitRosterMstDATA.updateUnitMaster(unitMasterVO,uservo);

		   objStatus.add(Status.DONE,"Record Saved Successfully","");
		   WebUTIL.setStatus(request,objStatus);
	 }
	
	private static DeptUnitRosterVO populateFB(HttpServletRequest request, DeptUnitRosterVO form, DeptUnitRosterVO rosterVO)
	{
		   form.resetRoster(request);
		   if(rosterVO!=null)
		   {
		   if(!rosterVO.isEmpty()){
			  //WebUTIL.populate(form, rosterVO);
			   form=rosterVO;
			  // System.out.println("populateFB:  rosterVO.isEmpty(): "+rosterVO.isEmpty());
			   int locNumOfShifts = getNumberOfShifts(request);
			   ArrayList[] colShift = new ArrayList[locNumOfShifts];
			   String[][] colShiftArray=new String[locNumOfShifts][] ;
			   ArrayList colWeek1stOfMonth = new ArrayList();
			   ArrayList colWeek2ndOfMonth = new ArrayList();
			   ArrayList colWeek3rdOfMonth = new ArrayList();
			   ArrayList colWeek4thOfMonth = new ArrayList();
			   ArrayList colWeek5thOfMonth = new ArrayList();
			   
			   Collection colDeptUnitDayOfWeekRoster = rosterVO.getColDeptUnitDayOfWeekRoster();
			   Iterator itr_1 = colDeptUnitDayOfWeekRoster.iterator();
			   DeptUnitDayOfWeekRosterVO current_1 = null;
			   while(itr_1.hasNext()){
				   current_1 = (DeptUnitDayOfWeekRosterVO) itr_1.next();
				   String locDayOfWeek = current_1.getDayOfWeek();
				   
				   Collection colDeptUnitDayOfWeekShiftRoster = current_1.getColDeptUnitDayOfWeekShiftRoster();
				   Iterator itr_2 = colDeptUnitDayOfWeekShiftRoster.iterator();
				   DeptUnitDayOfWeekShiftRosterVO current_2 = null;
				   for(int row=0; itr_2.hasNext(); row++){
					   current_2 = (DeptUnitDayOfWeekShiftRosterVO) itr_2.next();
					   String locShiftCode = current_2.getStrShiftCode();
					   int idx = getIndexForShiftCode(request, locShiftCode);
					   Collection colTmpRef=new ArrayList();
					   if(idx<colShift.length){
					   if(colShift[idx] == null)
						   colShift[idx] = new ArrayList();
					   
					   		colTmpRef = colShift[idx];
					   }
					   colTmpRef.add(locDayOfWeek+"_"+row);
					   colShiftArray[idx]=colToStringArray(colTmpRef);
					   colShift[idx]=(ArrayList)colTmpRef;
					   
					   Collection colWeekOfMonth = current_2.getColWeekOfMonth();
					   
					   Iterator itr_3 = colWeekOfMonth.iterator();
					   WeekOfMonthVO current_3 = null;
					   
					   while(itr_3.hasNext()){
						   current_3 = (WeekOfMonthVO) itr_3.next();
						   String locWeekOfMonth = current_3.getWeekOfMonth();
						   
						   //assign to an appropriate weekNthOfMonth Array
						   switch(Integer.parseInt(locWeekOfMonth)){
						   		case 1:
						   			colWeek1stOfMonth.add(locDayOfWeek+"_"+row);
						   			break;
						   		case 2:
						   			colWeek2ndOfMonth.add(locDayOfWeek+"_"+row);
						   			break;
						   		case 3:
						   			colWeek3rdOfMonth.add(locDayOfWeek+"_"+row);
						   			break;
						   		case 4:
						   			colWeek4thOfMonth.add(locDayOfWeek+"_"+row);
						   			break;
						   		case 5:
						   			colWeek5thOfMonth.add(locDayOfWeek+"_"+row);
						   			break;
						   		default:
						   			throw new IllegalArgumentException("Wrong Week Of Month");
						   }
					   }   				   
				   }
			   }
			   //colToStrArr
			   
			   form.setWeek1stOfMonth(colToStringArray(colWeek1stOfMonth));
			   form.setWeek2ndOfMonth(colToStringArray(colWeek2ndOfMonth));
			   form.setWeek3rdOfMonth(colToStringArray(colWeek3rdOfMonth));
			   form.setWeek4thOfMonth(colToStringArray(colWeek4thOfMonth));
			   form.setWeek5thOfMonth(colToStringArray(colWeek5thOfMonth));
			   			   
			   form.setShift(colShiftArray);


			 //  System.out.println("populateFB:  form.getWeek1stOfMonth().length: "+form.getWeek1stOfMonth().length);
			  // System.out.println("populateFB:  form.getWeek2ndOfMonth().length: "+form.getWeek2ndOfMonth().length);
			 //  System.out.println("populateFB:  form.getWeek3rdOfMonth().length: "+form.getWeek3rdOfMonth().length);
			   
			   //for(int shiftID =0; shiftID<locNumOfShifts; shiftID++){
				 // System.out.println("populateFB. shift:  "+shiftID+"  :  "+ colShift[shiftID]);
				 // if(colShift[shiftID]!=null)
					  //form.setShift(Integer.toString(shiftID), colToStringArray(colShift[shiftID]));
				 //  System.out.println("populateFB:  "+form.getShift(Integer.toString(shiftID)).length);
			  // }
			   
			  // System.out.println("shift");
		   }
		   }
		   return form;
	}
	
	public static int getNumberOfShifts(HttpServletRequest request){
		   ArrayList al = (ArrayList)request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SHIFT);
		   return al==null?0:al.size();
	}
	
	private static int getIndexForShiftCode(HttpServletRequest request, String str)
	{

		   ArrayList al = (ArrayList)request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SHIFT);
		   int j=0; 
		   for(int i=0;i<al.size();i++)
		   {
			   ShiftVO obj=new ShiftVO();
			   obj=(ShiftVO)al.get(i);
			   if(obj.getStrShiftCode().toString().equals(str))
				   return j;
			   else
				   j++;
			   
		   }
		   return j;
	}
	
	private static String[] colToStringArray(Collection _col){
		   String str[]=null;
		   
		   if(_col==null)
			   str= new String[]{};
		   else{
			   Iterator itr = _col.iterator();
			   str = new String[_col.size()];
			   for(int i=0; itr.hasNext(); i++)
				   str[i] = (String) itr.next();
		   }	   
		   return str;
	}
	
	private static void populateDeptUnitRosterVO(HttpServletRequest request, DeptUnitRosterVO form)
	{  
		 
		   //WebUTIL.populate(rosterVO, form);
		   //rosterVO=form;
		   Map mp_1 = new HashMap();
		   Map mp_2 = new HashMap();
		   
		   String[][] weekOfMonth = new String[5][];
		   weekOfMonth[0] = form.getWeek1stOfMonth();
		   weekOfMonth[1] = form.getWeek2ndOfMonth();
		   weekOfMonth[2] = form.getWeek3rdOfMonth();
		   weekOfMonth[3] = form.getWeek4thOfMonth();
		   weekOfMonth[4] = form.getWeek5thOfMonth();
		   
		   int nShift =getNumberOfShifts(request);
		   String[][] shift = new String[nShift][];
		   for(int s=0; s<nShift; s++){
			   shift[s] = form.getShift(Integer.toString(s));
		   }
		   
		   //for each weekOfMonth array
		   if(weekOfMonth!=null){
		   for(int i=0; i<weekOfMonth.length; i++){
			  
			   //for each element of weekOfMonth[i]
			   if(weekOfMonth[i]!=null){
			   for(int j=0; j<weekOfMonth[i].length; j++){			   
				   String key = weekOfMonth[i][j]; //dOw_row
				   Collection col = (Collection) mp_1.get(key);
				   
				   if(col == null){
					   col = new ArrayList();
					   mp_1.put(key, col);
				   }
				   //create new object for weekOfMonth
				   WeekOfMonthVO objWeekOfMonthVO = form.new WeekOfMonthVO();
				   objWeekOfMonthVO.setWeekOfMonth(getValue4WeekOfMonth(i));
				   col.add(objWeekOfMonthVO);
				  
			   }
			   }
		   }
		   }
		   //for each Shift
		   for(int a=0; a<nShift; a++){
			   //for each Selected Shift
			  //System.out.println("---Shift Length----"+shift[a].length+"--------");
			   for(int b=0; b<shift[a].length; b++){
				   String shiftVal = shift[a][b];
				   //System.out.println("---shiftVal----"+shiftVal+"--------");
				   if(shiftVal!=null && !(shiftVal.equals("")))
				   {	   
					   String dayOfWeek = getDayOfWeek(shiftVal);
					   Collection col = (Collection)mp_2.get(dayOfWeek);
				   
					   if(col==null){
						   col= new ArrayList();
						   mp_2.put(dayOfWeek, col);
					   }
				   
					   Collection col_1 = (Collection) mp_1.get(shiftVal);
				   
					   DeptUnitDayOfWeekShiftRosterVO objDUDayOfWeekShiftRosterVO = form.new DeptUnitDayOfWeekShiftRosterVO();
				   
					   objDUDayOfWeekShiftRosterVO.setColWeekOfMonth(col_1);
					   objDUDayOfWeekShiftRosterVO.setStrShiftCode(getShiftCode(request, a));		   
				   
					   col.add(objDUDayOfWeekShiftRosterVO);
				   }			 			   
			   }
		   }
		   
		   Set setMap_2Keys = mp_2.keySet();
		   
		   Iterator itr = setMap_2Keys.iterator();
		   Collection colDeptUnitDayOfWeekRosterVO = new ArrayList();
		   
		   while (itr.hasNext()){
			   String dOw = (String) itr.next();
			   
			   DeptUnitDayOfWeekRosterVO objDeptUnitDayOfWeekRosterVO = form.new DeptUnitDayOfWeekRosterVO();
			   objDeptUnitDayOfWeekRosterVO.setDayOfWeek(getDayOfWeekValue(dOw));
			   objDeptUnitDayOfWeekRosterVO.setColDeptUnitDayOfWeekShiftRoster((Collection)mp_2.get(dOw));
			   colDeptUnitDayOfWeekRosterVO.add(objDeptUnitDayOfWeekRosterVO);
		   }
		   form.setColDeptUnitDayOfWeekRoster(colDeptUnitDayOfWeekRosterVO);
		   
		   
	}
	
	private static String getDayOfWeekValue(String _dOw) {
		   return _dOw;
	}
	
	private static String getShiftCode(HttpServletRequest request, int j){
		   
		   
		   ArrayList al = (ArrayList)request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SHIFT);
		   ShiftVO obj=new ShiftVO();
		   obj=(ShiftVO)al.get(j);
		   
		   return (String) obj.getStrShiftCode();
	}
	
	private static String getDayOfWeek(String _shiftVal){
		   int i = _shiftVal.indexOf("_");
		   return _shiftVal.substring(0,i);
	}
	
	private static String getValue4WeekOfMonth(int i){
		   return (i+1)+""; 
	}
	
	private static int getRow(String _val){
		   int i = _val.indexOf("_");
		   return Integer.parseInt(_val.substring(i+1).trim());
	}
	
	public static int getNumOfRows4aDOfWinJSP(DeptUnitRosterVO form, HttpServletRequest request, int _dayOfWeek){
		   int noOfRows = getNumOfRows4aDayOfWeek(form, _dayOfWeek);
		  
		   String addRow4Day = (String)request.getAttribute("addRow4Day");
		   if(addRow4Day==null)
			   return noOfRows;
		   
		   try{
			if(Integer.parseInt(addRow4Day)==_dayOfWeek){
				noOfRows+=1;
			}
		   }catch(NumberFormatException e){
			   System.out.println("getNumOfRows4aDOfWinJSP:  NumberFormatException");
		   }
		   
		   return noOfRows;
	}
	
	public static int getNumOfRows4aDayOfWeek(DeptUnitRosterVO form, int _dayOfWeek){
		   int noOfRows = 0;
		   String dayOfWeek = Integer.toString(_dayOfWeek);
		   String[][] weekOfMonth = new String[5][];
		   weekOfMonth[0] = form.getWeek1stOfMonth();
		   weekOfMonth[1] = form.getWeek2ndOfMonth();
		   weekOfMonth[2] = form.getWeek3rdOfMonth();
		   weekOfMonth[3] = form.getWeek4thOfMonth();
		   weekOfMonth[4] = form.getWeek5thOfMonth();
		   
		   if(weekOfMonth!=null){
		   for(int i=0; i<weekOfMonth.length; i++){
			   if(weekOfMonth[i]!=null){
			   for(int j=0; j<weekOfMonth[i].length; j++){
				   if(dayOfWeek.equalsIgnoreCase(getDayOfWeek(weekOfMonth[i][j]))){
					   int currRow = getRow(weekOfMonth[i][j]);
					   if(noOfRows<currRow){
						   //noOfRows = currRow;
						   noOfRows = currRow;
					   }
				   }
			   }
			   }
		   }
		   }
		return noOfRows+1; 
	}
	
	public static void addRosterRow(DeptUnitRosterVO form,HttpServletRequest request) 
	{
		   String addRow = request.getParameter("addRow");
		   try{
			   if(addRow!=null && Integer.parseInt(addRow)>0)
				   request.setAttribute("addRow4Day", addRow);
		   }catch(NumberFormatException e){
			   System.out.println("setAddRosterRowDtls:  NumberFormatException");
			   //do nothing
		   }
		   request.getSession().setAttribute("rosterCurrentModel", form);
	}
	
	public static void removeRosterRow(DeptUnitRosterVO form,HttpServletRequest request) 
    {
	   String deleteRow = request.getParameter("removeRow");
	   System.out.println("removeRosterRow:  "+deleteRow);
	   if(deleteRow!=null){ 
		   		   
		   String[][] weekOfMonth = new String[5][];
		   weekOfMonth[0] = form.getWeek1stOfMonth();
		   weekOfMonth[1] = form.getWeek2ndOfMonth();
		   weekOfMonth[2] = form.getWeek3rdOfMonth();
		   weekOfMonth[3] = form.getWeek4thOfMonth();
		   weekOfMonth[4] = form.getWeek5thOfMonth();
		   
		   int locNumOfShifts = getNumberOfShifts(request);
		   String[][] shift = new String[locNumOfShifts][];
		   for(int shiftID =0; shiftID<locNumOfShifts; shiftID++){
			   shift[shiftID]=form.getShift(Integer.toString(shiftID));
		   }
		   
		   if(weekOfMonth!=null)
		   {
		   for(int i=0; i<weekOfMonth.length; i++){
			   if(weekOfMonth[i]!=null){
			   for(int j=0; j<weekOfMonth[i].length; j++){
				   if(deleteRow.equals(weekOfMonth[i][j])){
					   System.out.println("Removed: "+ deleteRow);
					   weekOfMonth[i]=WebUTIL.removeFromArray(weekOfMonth[i],j);
					   //System.out.println("weekArray after Deletion: "+weekOfMonth[i].length);
				   }
			   }
		   }
		   }
		   }
		   
		   form.setWeek1stOfMonth(weekOfMonth[0]);
		   form.setWeek2ndOfMonth(weekOfMonth[1]);
		   form.setWeek3rdOfMonth(weekOfMonth[2]);
		   form.setWeek4thOfMonth(weekOfMonth[3]);
		   form.setWeek5thOfMonth(weekOfMonth[4]);
		   
		   for(int si=0; si<shift.length; si++){
			   for(int sj=0; sj<shift[si].length; sj++){
				   if(deleteRow.equals(shift[si][sj]))
					   form.setShift(Integer.toString(si), WebUTIL.removeFromArray(shift[si],sj));
			   }
		   }
		   
		   adjust4RowRemoved(request, form, deleteRow);
		   
	   }
    }
	
	private static void adjust4RowRemoved(HttpServletRequest request, DeptUnitRosterVO form, String deleteRow)
	{
		   String remove4Day = deleteRow.substring(0,deleteRow.indexOf("_"));
		   String remove4Row = deleteRow.substring(deleteRow.indexOf("_")+1);
		   
		   //adjust all the entries for row more than the selected row
		   int delRow = Integer.parseInt(remove4Row);
		   int noOfRows = getNumOfRows4aDayOfWeek(form, Integer.parseInt(remove4Day));
		   Collection colEntriesToReadjust = new ArrayList();
		   for(int i=delRow+1; i<=noOfRows; i++){
			   colEntriesToReadjust.add(remove4Day+"_"+i);
		   }
		   
		   String[][] weekOfMonth = new String[5][];
		   weekOfMonth[0] = form.getWeek1stOfMonth();
		   weekOfMonth[1] = form.getWeek2ndOfMonth();
		   weekOfMonth[2] = form.getWeek3rdOfMonth();
		   weekOfMonth[3] = form.getWeek4thOfMonth();
		   weekOfMonth[4] = form.getWeek5thOfMonth();
		   
		   int locNumOfShifts = getNumberOfShifts(request);
		   String[][] shift = new String[locNumOfShifts][];
		   for(int shiftID =0; shiftID<locNumOfShifts; shiftID++){
			   shift[shiftID]=form.getShift(Integer.toString(shiftID));
		   }
		   
		   if(weekOfMonth!=null){
		   for(int i=0; i<weekOfMonth.length; i++){
			   if(weekOfMonth[i]!=null){
			   for(int j=0; j<weekOfMonth[i].length; j++){
				   if(colEntriesToReadjust.contains(weekOfMonth[i][j])){
					  // System.out.println("adjuste: "+ weekOfMonth[i][j]);
					   String adjustTo =  remove4Day+"_"+ (Integer.parseInt(weekOfMonth[i][j].substring(weekOfMonth[i][j].indexOf('_')+1))-1);
					   weekOfMonth[i][j] = adjustTo;
					  // System.out.println("after adjustment: "+weekOfMonth[i][j]);
				   }
			   }
			   }
		   }
		   }
		   
		   form.setWeek1stOfMonth(weekOfMonth[0]);
		   form.setWeek2ndOfMonth(weekOfMonth[1]);
		   form.setWeek3rdOfMonth(weekOfMonth[2]);
		   form.setWeek4thOfMonth(weekOfMonth[3]);
		   form.setWeek5thOfMonth(weekOfMonth[4]);
		   
		   for(int si=0; si<shift.length; si++){
			   for(int sj=0; sj<shift[si].length; sj++){
				   if(colEntriesToReadjust.contains(shift[si][sj])){
					 //  System.out.println("adjuste: "+ shift[si][sj]);
					   String adjustTo =  remove4Day+"_"+ (Integer.parseInt(shift[si][sj].substring(shift[si][sj].indexOf('_')+1))-1);
					   shift[si][sj] = adjustTo;
					  // System.out.println("after adjustment: "+shift[si][sj]);
				   }
			   }
			   form.setShift(Integer.toString(si), shift[si]);
		   }
		   request.getSession().setAttribute("rosterCurrentModel", form);
	   }
	
	public static DeptUnitRosterVO executeRosterForAll(DeptUnitRosterVO form, HttpServletRequest request){
		   Status st=new Status();
		   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		   HttpSession sess=request.getSession();
		  try{  		   
			  String sysdate="";
			  UserVO uservo = ControllerUTIL.getUserVO(request);
			  System.out.println("----"+sess.getAttribute(HISConfig.SYSDATEOBJECT)+"--------");
			  Date sysdateObject=(Date)formatter.parse((String)sess.getAttribute(HISConfig.SYSDATEOBJECT));
			  sysdate=WebUTIL.getCustomisedSysDate(sysdateObject, "dd-MMM-yyyy");
			  int status=DeptUnitRosterMstDATA.executeRosterForAll(sysdate, uservo);
			  if(status==0)// error			   
				  //st.add(Status.UNSUCESSFULL,"Error while executing roster","");
				  form.setStrErrorMsg("Error while executing roster");
			  else
				  //st.add(Status.DONE ,"Roster Successfully executed","");
				  form.setStrErrorMsg("Roster Successfully executed");
			}
		   catch(Exception e){
			   System.out.println("exception in executeRosterForAll");
			   e.printStackTrace();
		   }
		   finally{
			   WebUTIL.setStatus(request, st);
		   }
	   return form;
	}
	//By Mukund
	public static void deleteUnitRoster(HttpServletRequest request,DeptUnitRosterVO rosterModel,String strMode) 
	 {
		   Status objStatus=new Status();
		   DeptUnitRosterVO vo = new DeptUnitRosterVO();
		   UserVO uservo = ControllerUTIL.getUserVO(request);				
		   String roomCode[]=rosterModel.getStrRoomCode().split("#");
		   String unitRoomMasterCapacity="";
		   rosterModel.setStrRoomCode(roomCode[0]);
		   rosterModel.setStrRoomSequence(roomCode[1]);
		   populateDeptUnitRosterVO(request, rosterModel);
		   Collection col=rosterModel.getColRosterMaster();
		   
		   unitRoomMasterCapacity=roomCode[2];		   
		   
		   DeptUnitRosterMstDATA.deleteRosterRecord(col,unitRoomMasterCapacity,rosterModel.getStrDeptUnitCode(),rosterModel.getStrRoomCode(), uservo);
		   //UnitVO unitMasterVO=new UnitVO();
		   //HelperMethods.populate(unitMasterVO, vo);
		  // DeptUnitRosterMstDATA.updateUnitMaster(unitMasterVO,uservo);

		   objStatus.add(Status.DONE,"Record Deleted Successfully","");
		   WebUTIL.setStatus(request,objStatus);
	 }
}
