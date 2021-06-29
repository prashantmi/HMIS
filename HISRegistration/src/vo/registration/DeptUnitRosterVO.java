/**
 * 
 */
package vo.registration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import registration.masters.controller.util.DeptUnitRosterMstUTIL;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import hisglobal.vo.ValueObject;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

/**
 * @author s.singaravelan
 * DATE : 23-Jan-2014
 */
public class DeptUnitRosterVO extends ValueObject
{
	
	private String strDeptCode;
	private String strDeptName;
	private String strUnitCode;
	private String strUnitName;
	private String strDeptUnitCode;
	private String strRoomCode;
	private String strRoomName;
	private String strRoomSequence;
	private String strRoomCapacity;
	private String strRoomCapacityInUnitRoomMst;
	
	private String strOpdName;
	private String startDate;
	private String endDate;
	private String startDateTime;
	private String endDateTime;
	private String roomUsability;


	private String[] week1stOfMonth;
	private String[] week2ndOfMonth;
	private String[] week3rdOfMonth;
	private String[] week4thOfMonth;
	private String[] week5thOfMonth;
		
	private String[][] shift;


	private String strHospitalCode;	
	
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;	
	
	private Collection colDeptUnitDayOfWeekRoster;
	
	
	public DeptUnitRosterVO()
	{
		colDeptUnitDayOfWeekRoster = new ArrayList();
	}
	
	public DeptUnitRosterVO(Collection colRosterMasterVO)
	{
		this();
		Set setRosterMasterVO = new TreeSet(this.new DURosterComparator());
		setRosterMasterVO.addAll(colRosterMasterVO);
		//populate this instance with the data in the colRosterMasterVO
		this.populateWith(setRosterMasterVO);
	}	
	
	
	public void reset() 
	{
		this.strDeptCode="-1";
		this.strUnitCode="-1";
		this.strRoomCode="-1";
	}
		
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrRoomCode() {
		return strRoomCode;
	}
	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}
	public String getStrRoomSequence() {
		return strRoomSequence;
	}
	public void setStrRoomSequence(String strRoomSequence) {
		this.strRoomSequence = strRoomSequence;
	}
	public String getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}	
	public String getStrRoomCapacity() {
		return strRoomCapacity;
	}
	public String getStrOpdName() {
		return strOpdName;
	}

	public void setStrOpdName(String strOpdName) {
		this.strOpdName = strOpdName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getRoomUsability() {
		return roomUsability;
	}

	public void setRoomUsability(String roomUsability) {
		this.roomUsability = roomUsability;
	}
	public void setStrRoomCapacity(String strRoomCapacity) {
		this.strRoomCapacity = strRoomCapacity;
	}
	public String getStrRoomCapacityInUnitRoomMst() {
		return strRoomCapacityInUnitRoomMst;
	}
	public void setStrRoomCapacityInUnitRoomMst(String strRoomCapacityInUnitRoomMst) {
		this.strRoomCapacityInUnitRoomMst = strRoomCapacityInUnitRoomMst;
	}
	public String[] getWeek1stOfMonth() {
		return week1stOfMonth;
	}
	public void setWeek1stOfMonth(String[] week1stOfMonth) {
		this.week1stOfMonth = week1stOfMonth;
	}
	public String[] getWeek2ndOfMonth() {
		return week2ndOfMonth;
	}
	public void setWeek2ndOfMonth(String[] week2ndOfMonth) {
		this.week2ndOfMonth = week2ndOfMonth;
	}
	public String[] getWeek3rdOfMonth() {
		return week3rdOfMonth;
	}
	public void setWeek3rdOfMonth(String[] week3rdOfMonth) {
		this.week3rdOfMonth = week3rdOfMonth;
	}
	public String[] getWeek4thOfMonth() {
		return week4thOfMonth;
	}
	public void setWeek4thOfMonth(String[] week4thOfMonth) {
		this.week4thOfMonth = week4thOfMonth;
	}
	
	public String[][] getShift() {
		return shift;
	}
	public void setShift(String[][] shift) {
		this.shift = shift;
	}
	public String[] getWeek5thOfMonth() {
		return week5thOfMonth;
	}
	public void setWeek5thOfMonth(String[] week5thOfMonth) {
		this.week5thOfMonth = week5thOfMonth;
	}
	
	public Collection getColDeptUnitDayOfWeekRoster() {
		return colDeptUnitDayOfWeekRoster;
	}

	public void setColDeptUnitDayOfWeekRoster(Collection colDeptUnitDayOfWeekRoster) {
		this.colDeptUnitDayOfWeekRoster = colDeptUnitDayOfWeekRoster;
	}

	public class DeptUnitDayOfWeekRosterVO extends ValueObject
	{
		private String dayOfWeek;
		private Collection colDeptUnitDayOfWeekShiftRoster;
		
		public DeptUnitDayOfWeekRosterVO()
		{
			colDeptUnitDayOfWeekShiftRoster = new ArrayList();
		}

		public void setDayOfWeek(String dayOfWeek)
		{
			this.dayOfWeek = dayOfWeek;
		}

		public String getDayOfWeek()
		{
			return this.dayOfWeek;
		}

		public void setColDeptUnitDayOfWeekShiftRoster(Collection colDeptUnitDayOfWeekShiftRoster)
		{
			this.colDeptUnitDayOfWeekShiftRoster = colDeptUnitDayOfWeekShiftRoster;
		}

		public Collection getColDeptUnitDayOfWeekShiftRoster()
		{
			return this.colDeptUnitDayOfWeekShiftRoster;
		}
	}
	
	public class DeptUnitDayOfWeekShiftRosterVO extends ValueObject
	{
		private String strShiftName;
		private Collection colWeekOfMonth;
		private String strShiftCode;

		public DeptUnitDayOfWeekShiftRosterVO()
		{
			colWeekOfMonth = new ArrayList();
		}

		public String getStrShiftName() {
			return strShiftName;
		}

		public void setStrShiftName(String strShiftName) {
			this.strShiftName = strShiftName;
		}

		public Collection getColWeekOfMonth() {
			return colWeekOfMonth;
		}

		public void setColWeekOfMonth(Collection colWeekOfMonth) {
			this.colWeekOfMonth = colWeekOfMonth;
		}

		public String getStrShiftCode() {
			return strShiftCode;
		}

		public void setStrShiftCode(String strShiftCode) {
			this.strShiftCode = strShiftCode;
		}

		
	}
	
	public class WeekOfMonthVO extends ValueObject
	{
		private String weekOfMonth;

		public WeekOfMonthVO()
		{

		}

		public void setWeekOfMonth(String weekOfMonth)
		{
			this.weekOfMonth = weekOfMonth;
		}

		public String getWeekOfMonth()
		{
			return this.weekOfMonth;
		}
	}
	
	public void resetRoster(HttpServletRequest request)
	{
		   this.setWeek1stOfMonth(new String[]{});
		   this.setWeek2ndOfMonth(new String[]{});
		   this.setWeek3rdOfMonth(new String[]{});
		   this.setWeek4thOfMonth(new String[]{});
		   this.setWeek5thOfMonth(new String[]{});
		   
		   int  noOfShifts = DeptUnitRosterMstUTIL.getNumberOfShifts(request);
		   this.shift= new String[noOfShifts][];
		   for(int i=0; i<noOfShifts; i++){
			   this.setShift(Integer.toString(i), new String[]{});
		   }
	 }
	
	public class DURosterComparator implements Comparator	
	{
		
		public DURosterComparator()
		{

		}
		
		public int compare(Object arg0, Object arg1)
		{
			if (arg0 == null || arg1 == null) throw new IllegalArgumentException();

			RosterMasterVO rmVO1 = (RosterMasterVO) arg0;
			RosterMasterVO rmVO2 = (RosterMasterVO) arg1;

			int result = rmVO1.getStrDeptCode().compareTo(rmVO2.getStrDeptCode());
			if (result == 0)
			{// same
				result = rmVO1.getStrDeptUnitCode().compareTo(rmVO2.getStrDeptUnitCode());
				if (result == 0)
				{//same
					result = rmVO1.getDayOfWeek().compareTo(rmVO2.getDayOfWeek());
					if (result == 0)
					{
						result = rmVO1.getStrShiftCode().compareTo(rmVO2.getStrShiftCode());
						if (result == 0)
						{
							result = rmVO1.getWeekOfMonth().compareTo(rmVO2.getWeekOfMonth());
						}
					}
				}
			}
			return result;
		}
	}
	
	private void populateWith(Set setRosterMasterVO)
	{
		//initialize
		DeptUnitRosterVO current_1 = null;
		DeptUnitDayOfWeekRosterVO current_2 = null;
		DeptUnitDayOfWeekShiftRosterVO current_3 = null;
		WeekOfMonthVO current_4 = null;

		Iterator itr = setRosterMasterVO.iterator();
		System.out.println("populateWith(): setRosterMasterVO.size():  " + setRosterMasterVO.size());
		int x = 0;
		while (itr.hasNext())
		{
			RosterMasterVO row = (RosterMasterVO) itr.next();
			System.out.println("populateWith(): Iteration :" + x++);
			if (current_1 == null)
			{ // first row

				current_1 = this;
				/*current_1.department = row.getDepartment();
				current_1.departmentCode = row.getDepartmentCode();
				current_1.departmentUnit = row.getDepartmentUnit();
				current_1.departmentUnitCode = row.getDepartmentUnitCode();*/
				WebUTIL.populate(current_1, row);
				System.out.println("populateWith(): current_1:  first row departmentCode: " + current_1.strDeptCode);
			}
			//hoping that rest of the rows will also be for the same departmentUnitCode
			if (current_1.getColDeptUnitDayOfWeekRoster().size() == 0)
			{//no element for this deptUnitCode
				System.out.println("populateWith(): No element for DayofWeek col");
				current_2 = new DeptUnitDayOfWeekRosterVO();
				current_2.dayOfWeek = row.getDayOfWeek();
				current_1.getColDeptUnitDayOfWeekRoster().add(current_2);
			}
			else
			{
				//get last element in the collection to check the equality of the dOw with the current row
				current_2 = (DeptUnitDayOfWeekRosterVO) ((ArrayList) current_1.getColDeptUnitDayOfWeekRoster()).get(current_1
						.getColDeptUnitDayOfWeekRoster().size() - 1);
				if (!current_2.dayOfWeek.equals(row.getDayOfWeek()))
				{//different DayOfWeek
					System.out.println("populateWith(): not same element for DayOfWeek col");
					current_2 = new DeptUnitDayOfWeekRosterVO();
					current_2.dayOfWeek = row.getDayOfWeek();
					current_1.getColDeptUnitDayOfWeekRoster().add(current_2);
				}
				else System.out.println("populateWith(): same element for DayOfWeek col");
			}

			if (current_2.getColDeptUnitDayOfWeekShiftRoster().size() == 0)
			{//no element for this DayOfWeek
				current_3 = new DeptUnitDayOfWeekShiftRosterVO();
				System.out.println("populateWith(): No element for shift col for dayOfWeek: " + current_2.getDayOfWeek());
				current_3.strShiftName = row.getStrShiftName();
				current_3.strShiftCode = row.getStrShiftCode();
				current_2.getColDeptUnitDayOfWeekShiftRoster().add(current_3);
			}
			else
			{
				//get last element in the collection to check the equality of the dOw with the current row
				current_3 = (DeptUnitDayOfWeekShiftRosterVO) ((ArrayList) current_2.getColDeptUnitDayOfWeekShiftRoster()).get(current_2
						.getColDeptUnitDayOfWeekShiftRoster().size() - 1);
				if (!current_3.strShiftCode.equals(row.getStrShiftCode()))
				{//different DayOfWeek
					System.out.println("populateWith(): Not Same element for shift col");
					current_3 = new DeptUnitDayOfWeekShiftRosterVO();
					current_3.strShiftName = row.getStrShiftName();
					current_3.strShiftCode = row.getStrShiftCode();
					current_2.getColDeptUnitDayOfWeekShiftRoster().add(current_3);
				}
				else System.out.println("populateWith(): Same element for shift col");
			}

			//hoping that WoM will be different making the row unique
			//add tO the current_3.collection
			//create a new object and add
			current_4 = new WeekOfMonthVO();
			current_4.weekOfMonth = row.getWeekOfMonth();
			current_3.getColWeekOfMonth().add(current_4);
			System.out.println("populateWith(): current_3.getColWeekOfMonth().size(): " + current_3.getColWeekOfMonth().size());
		}
		//System.out.println("populateWith(): function ends: current_1.getColDeptUnitDayOfWeekRoster().size(): "+current_1.getColDeptUnitDayOfWeekRoster().size());	
	}
	
	public void setShift(String idx,String[] shift) {
		//Implementation is pending
		//System.out.println("setShift(String idx, java.lang.String[] shift).."+shift.length);
//		if(shift!=null){
//			   for(int i=0; i<shift.length; i++)
//				   System.out.println("shift["+i+"]:"+shift[i]);}
	
		this.shift[Integer.parseInt(idx)] =shift;
	}
	
	public String[] getShift(String idx) {
		return shift[Integer.parseInt(idx)];
	}
	
	public boolean isEmpty()
	{
		if(colDeptUnitDayOfWeekRoster!=null)
			return (colDeptUnitDayOfWeekRoster.size() > 0 ? false : true);
		else
			return true;
	}
	
	public Collection getColRosterMaster()
	{
		Collection colRosterMasterVO = new ArrayList();
		DeptUnitRosterVO currDeptUnitRosterVO = this;

		Collection col = currDeptUnitRosterVO.getColDeptUnitDayOfWeekRoster();
		Iterator itr = col.iterator();

		while (itr.hasNext())
		{
			DeptUnitDayOfWeekRosterVO currDeptUnitDayOfWeekRosterVO = (DeptUnitDayOfWeekRosterVO) itr.next();

			Collection col_a = currDeptUnitDayOfWeekRosterVO.getColDeptUnitDayOfWeekShiftRoster();
			Iterator itr_1 = col_a.iterator();
			Collection col_1 = new ArrayList();
			while (itr_1.hasNext())
			{
				DeptUnitDayOfWeekShiftRosterVO currDeptUnitDayOfWeekShiftRosterVO = (DeptUnitDayOfWeekShiftRosterVO) itr_1.next();
				Collection col_b = currDeptUnitDayOfWeekShiftRosterVO.getColWeekOfMonth();
				Iterator itr_2 = col_b.iterator();
				Collection col_2 = new ArrayList();

				while (itr_2.hasNext())
				{
					WeekOfMonthVO currWeekOfMonth = (WeekOfMonthVO) itr_2.next();
					RosterMasterVO objRosterMasterVO = new RosterMasterVO();

					HelperMethods.populate(objRosterMasterVO, currWeekOfMonth);
					col_2.add(objRosterMasterVO);
				}

				Iterator itr_col_2 = col_2.iterator();
				while (itr_col_2.hasNext())
				{
					RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col_2.next();
					HelperMethods.populate(currRosterMasterVO, currDeptUnitDayOfWeekShiftRosterVO);
					col_1.add(currRosterMasterVO);
				}
			}

			Iterator itr_col_1 = col_1.iterator();
			while (itr_col_1.hasNext())
			{
				RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col_1.next();
				HelperMethods.populate(currRosterMasterVO, currDeptUnitDayOfWeekRosterVO);
				colRosterMasterVO.add(currRosterMasterVO);
			}
		}

		Iterator itr_col = colRosterMasterVO.iterator(); //<<<
		while (itr_col.hasNext())
		{
			RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col.next();
			HelperMethods.populate(currRosterMasterVO, currDeptUnitRosterVO);
		}
		//colRosterMasterVO = colRosterMasterVO;		//<<
		return colRosterMasterVO;
	}

	



}
