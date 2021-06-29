package hisglobal.vo;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.util.*;

public class DeptUnitRosterVO extends ValueObject
{
	private String department;
	private String departmentCode;
	private String departmentUnit;
	private String departmentUnitCode;
	private String opdName;
	private String roomCode;
	private String roomSequenceNo;
	private String effectDate;
	private String roomUsability;
	
	private String startDate;
	private String endDate;
	private String roomName;
	private String status;
	private String startDateTime;
	private String endDateTime;
	private String isValid;
	
	private Collection colDeptUnitDayOfWeekRoster;

	/**
	@roseuid 45C2FD4D0196
	 */
	public DeptUnitRosterVO()
	{
		colDeptUnitDayOfWeekRoster = new ArrayList();
	}

	/**
	argument: RosterMasterVO Collection
	1. create an object of TreeSet with the comparator for this class
	2. tset.addall(collection)  >>> provide sorted tree
	3. build this object
	@param arg0
	@roseuid 45C2C29A0109
	 */
	public DeptUnitRosterVO(Collection colRosterMasterVO)
	{
		this();
		//implement this
		Set setRosterMasterVO = new TreeSet(this.new DURosterComparator());

		setRosterMasterVO.addAll(colRosterMasterVO);

		//populate this instance with the data in the colRosterMasterVO
		this.populateWith(setRosterMasterVO);
	}

	public boolean isEmpty()
	{
		return (colDeptUnitDayOfWeekRoster.size() > 0 ? false : true);
	}


	/**
	 Sets departmentUnitCode.
	 @param departmentUnitCode
	 @roseuid 45C1C40101F4
	 */
	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	/**
	Retrieves departmentUnitCode.
	@return Value of departmentUnitCode.
	@roseuid 45C1C40101F6
	 */
	public String getDepartmentUnitCode()
	{
		return this.departmentUnitCode;
	}

	/**
	Sets departmentUnit.
	@param departmentUnit
	@roseuid 45C1C4010203
	 */
	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	/**
	Retrieves departmentUnit.
	@return Value of departmentUnit.
	@roseuid 45C1C4010213
	 */
	public String getDepartmentUnit()
	{
		return this.departmentUnit;
	}

	/**
	Sets departmentCode.
	@param departmentCode
	@roseuid 45C1C4010222
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	/**
	Retrieves departmentCode.
	@return Value of departmentCode.
	@roseuid 45C1C4010224
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}

	/**
	Sets department.
	@param department
	@roseuid 45C1C4010232
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	Retrieves department.
	@return Value of department.
	@roseuid 45C1C4010242
	 */
	public String getDepartment()
	{
		return this.department;
	}

	/**
	Sets OpdName.
	@param opdName
	@roseuid 45C1C44D0128
	 */
	public void setOpdName(String opdName)
	{
		this.opdName = opdName;
	}

	/**
	Retrieves OpdName.
	@return Value of opdName.
	@roseuid 45C1C44D012A
	 */
	public String getOpdName()
	{
		return this.opdName;
	}

	public String getEffectDate()
	{
		return effectDate;
	}

	public void setEffectDate(String effectDate)
	{
		this.effectDate = effectDate == null ? "" : effectDate;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomSequenceNo() {
		return roomSequenceNo;
	}

	public void setRoomSequenceNo(String roomSequenceNo) {
		this.roomSequenceNo = roomSequenceNo;
	}

	public String getRoomUsability() {
		return roomUsability;
	}

	public void setRoomUsability(String roomUsability) {
		this.roomUsability = roomUsability;
	}
	
	/**
	Sets colDeptUnitDayOfWeekRoster.
	@param colDeptUnitDayOfWeekRoster
	@roseuid 45C1C55900BB
	 */
	public void setColDeptUnitDayOfWeekRoster(Collection colDeptUnitDayOfWeekRoster)
	{
		this.colDeptUnitDayOfWeekRoster = colDeptUnitDayOfWeekRoster;
	}

	/**
	Retrieves colDeptUnitDayOfWeekRoster
	@return Value of colDeptUnitDayOfWeekRoster
	@roseuid 45C1C55900CB
	 */
	public Collection getColDeptUnitDayOfWeekRoster()
	{
		return this.colDeptUnitDayOfWeekRoster;
	}

	public class DeptUnitDayOfWeekRosterVO extends ValueObject
	{
		private String dayOfWeek;
		private Collection colDeptUnitDayOfWeekShiftRoster;

		/**
		@roseuid 45C2F7CE0186
		 */
		public DeptUnitDayOfWeekRosterVO()
		{
			colDeptUnitDayOfWeekShiftRoster = new ArrayList();
		}

		/**
		Sets DayOfWeek.
		@param DayOfWeek
		@param dayOfWeek
		@roseuid 45C1C7890251
		 */
		public void setDayOfWeek(String dayOfWeek)
		{
			this.dayOfWeek = dayOfWeek;
		}

		/**
		RetrievesDayOfWeek.
		@return Value of dayOfWeek.
		@roseuid 45C1C7890253
		 */
		public String getDayOfWeek()
		{
			return this.dayOfWeek;
		}

		/**
		Sets colDeptUnitDayOfWeekShiftRoster.
		@param colDeptUnitDayOfWeekShiftRoster
		@roseuid 45C1C7F8001F
		 */
		public void setColDeptUnitDayOfWeekShiftRoster(Collection colDeptUnitDayOfWeekShiftRoster)
		{
			this.colDeptUnitDayOfWeekShiftRoster = colDeptUnitDayOfWeekShiftRoster;
		}

		/**
		Retrieves colDeptUnitDayOfWeekShiftRoster
		@return Value of colDeptUnitDayOfWeekShiftRoster
		@roseuid 45C1C7F8002E
		 */
		public Collection getColDeptUnitDayOfWeekShiftRoster()
		{
			return this.colDeptUnitDayOfWeekShiftRoster;
		}
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
				System.out.println("populateWith(): current_1:  first row departmentCode: " + current_1.departmentCode);
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
				current_3.shift = row.getShift();
				current_3.shiftCode = row.getShiftCode();
				current_2.getColDeptUnitDayOfWeekShiftRoster().add(current_3);
			}
			else
			{
				//get last element in the collection to check the equality of the dOw with the current row
				current_3 = (DeptUnitDayOfWeekShiftRosterVO) ((ArrayList) current_2.getColDeptUnitDayOfWeekShiftRoster()).get(current_2
						.getColDeptUnitDayOfWeekShiftRoster().size() - 1);
				if (!current_3.shiftCode.equals(row.getShiftCode()))
				{//different DayOfWeek
					System.out.println("populateWith(): Not Same element for shift col");
					current_3 = new DeptUnitDayOfWeekShiftRosterVO();
					current_3.shift = row.getShift();
					current_3.shiftCode = row.getShiftCode();
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
		//	   System.out.println("populateWith(): function ends: current_1.getColDeptUnitDayOfWeekRoster().size(): "+current_1.getColDeptUnitDayOfWeekRoster().size());	
	}
	
	
	public class DeptUnitDayOfWeekShiftRosterVO extends ValueObject
	{
		private String shift;
		private Collection colWeekOfMonth;
		private String shiftCode;

		/**
		@roseuid 45C2F7CE01B5
		 */
		public DeptUnitDayOfWeekShiftRosterVO()
		{
			colWeekOfMonth = new ArrayList();
		}

		/**
		Sets shiftCode.
		@param shiftCode
		@roseuid 45C1D20402FD
		 */
		public void setShiftCode(String shiftCode)
		{
			this.shiftCode = shiftCode;
		}

		/**
		Retrieves ShiftCode.
		@return Value of shiftCode.
		@roseuid 45C1D20402FF
		 */
		public String getShiftCode()
		{
			return this.shiftCode;
		}

		/**
		Sets colWeekOfMonth.
		@param colWeekOfMonth
		@roseuid 45C1D29C002E
		 */
		public void setColWeekOfMonth(Collection colWeekOfMonth)
		{
			this.colWeekOfMonth = colWeekOfMonth;
		}

		/**
		Retrieves colWeekOfMonth.
		@return Value of colWeekOfMonth
		@roseuid 45C1D29C003E
		 */
		public Collection getColWeekOfMonth()
		{
			return this.colWeekOfMonth;
		}

		public String getShift()
		{
			return shift;
		}

		public void setShift(String shift)
		{
			this.shift = shift;
		}
	}

	public class WeekOfMonthVO extends ValueObject
	{
		private String weekOfMonth;

		/**
		@roseuid 45C2F7CE01D4
		 */
		public WeekOfMonthVO()
		{

		}

		/**
		Sets weekOfMonth.
		@param weekOfMonth
		@roseuid 45C1D3830157
		 */
		public void setWeekOfMonth(String weekOfMonth)
		{
			this.weekOfMonth = weekOfMonth;
		}

		/**
		Retrieves weekOfMonth.
		@return Value of weekOfMonth.
		@roseuid 45C1D3830167
		 */
		public String getWeekOfMonth()
		{
			return this.weekOfMonth;
		}
	}

	public class DURosterComparator implements Comparator
	{
		/**
		@roseuid 45C2F7CE01E4
		 */
		public DURosterComparator()
		{

		}

		/**
		@param arg0
		@param arg1
		@return int
		@roseuid 45C2F7CE0203
		 */
		public int compare(Object arg0, Object arg1)
		{
			if (arg0 == null || arg1 == null) throw new IllegalArgumentException();

			RosterMasterVO rmVO1 = (RosterMasterVO) arg0;
			RosterMasterVO rmVO2 = (RosterMasterVO) arg1;

			int result = rmVO1.getDepartmentCode().compareTo(rmVO2.getDepartmentCode());
			if (result == 0)
			{// same
				result = rmVO1.getDepartmentUnitCode().compareTo(rmVO2.getDepartmentUnitCode());
				if (result == 0)
				{//same
					result = rmVO1.getDayOfWeek().compareTo(rmVO2.getDayOfWeek());
					if (result == 0)
					{
						result = rmVO1.getShiftCode().compareTo(rmVO2.getShiftCode());
						if (result == 0)
						{
							result = rmVO1.getWeekOfMonth().compareTo(rmVO2.getWeekOfMonth());
						}
					}
				}
			}
			return result;
		}

		/*    *//**
		      @param arg0
		      @return boolean
		      @roseuid 45C2F7CE0271
		 */
		/*
		      public boolean equals(Object arg0) 
		      {
		    	  if(arg0 == null)
		    		  return false;
		    	  
		    	  if(!(arg0 instanceof RosterMasterVO))
		    		  return false;
		    	  
		    	  RosterMasterVO rmVO1 = (RosterMasterVO) arg0;
		    	  
		    	  int result = rmVO1.getDepartmentCode().equals(this.getDepartmentCode());
		    	  if(result == 0){// same
		    		result = rmVO1.getDepartmentUnitCode().compareTo(rmVO2.getDepartmentUnitCode());
		    	  	if(result == 0){//same
		    	  		result = rmVO1.getDayOfWeek().compareTo(rmVO2.getDayOfWeek());
		    	  		if(result == 0){
		    	  			result = rmVO1.getShiftCode().compareTo(rmVO2.getShiftCode());
		    	  			if(result == 0){
		    	  				result = rmVO1.getWeekOfMonth().compareTo(rmVO2.getWeekOfMonth());
		    	  			}
		    	  		}
		    	  	}
		      	  }  
		    	  return result;
		      }*/
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	
}
