package hisglobal.vo;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class DeptShiftRosterVO extends ValueObject
{
	private String department;
	private String departmentCode;
	private Collection colDeptDayOfWeekRoster;

	/**
	@roseuid 45C2F7FF0280
	 */
	public DeptShiftRosterVO()
	{
		colDeptDayOfWeekRoster = new ArrayList();
	}

	/**
	@param arg0
	@roseuid 45C2C2ED0203
	 */
	public DeptShiftRosterVO(Collection colRosterMasterVO)
	{
		this();
		Set setRosterMasterVO = new TreeSet(this.new DSRComparator());

		setRosterMasterVO.addAll(colRosterMasterVO);

		//populate this instance with the data in the colRosterMasterVO
		this.populateWith(setRosterMasterVO);
	}

	public boolean isEmpty()
	{
		return (colDeptDayOfWeekRoster.size() > 0 ? false : true);
	}

	private void populateWith(Set setRosterMasterVO)
	{
		//initialize
		System.out.println("populateWith:  setRosterMasterVO.size():  " + setRosterMasterVO.size());
		DeptShiftRosterVO current_1 = null;
		DeptDayOfWeekRosterVO current_2 = null;
		DeptDayOfWeekShiftRosterVO current_3 = null;
		DeptDayOfWeekShiftWeekOfMonthVO current_4 = null;
		DeptUnitVO current_5 = null;

		Iterator itr = setRosterMasterVO.iterator();

		while (itr.hasNext())
		{
			RosterMasterVO row = (RosterMasterVO) itr.next();

			if (current_1 == null)
			{ // first row
				current_1 = this;
				/*current_1.department = row.getDepartment();
				current_1.departmentCode = row.getDepartmentCode();*/
				WebUTIL.populate(current_1, row);
			}
			//hoping that rest of the rows will also be for the same departmentUnitCode
			if (current_1.getColDeptDayOfWeekRoster().size() == 0)
			{//no element for this deptUnitCode
				current_2 = new DeptDayOfWeekRosterVO();
				current_2.dayOfWeek = row.getDayOfWeek();
				current_1.getColDeptDayOfWeekRoster().add(current_2);
			}
			else
			{
				//get last element in the collection to check the equality of the dOw with the current row
				current_2 = (DeptDayOfWeekRosterVO) ((ArrayList) current_1.getColDeptDayOfWeekRoster()).get(current_1.getColDeptDayOfWeekRoster()
						.size() - 1);
				if (!current_2.dayOfWeek.equals(row.getDayOfWeek()))
				{//different DayOfWeek
					current_2 = new DeptDayOfWeekRosterVO();
					current_2.dayOfWeek = row.getDayOfWeek();
					current_1.getColDeptDayOfWeekRoster().add(current_2);
				}
			}

			if (current_2.getColDeptDayOfWeekShiftRoster().size() == 0)
			{//no element for this deptUnitCode
				current_3 = new DeptDayOfWeekShiftRosterVO();
				current_3.shift = row.getShift();
				current_3.shiftCode = row.getShiftCode();
				current_2.getColDeptDayOfWeekShiftRoster().add(current_3);
			}
			else
			{
				//get last element in the collection to check the equality of the dOw with the current row
				current_3 = (DeptDayOfWeekShiftRosterVO) ((ArrayList) current_2.getColDeptDayOfWeekShiftRoster()).get(current_2
						.getColDeptDayOfWeekShiftRoster().size() - 1);
				if (!current_3.shiftCode.equals(row.getShiftCode()))
				{//different DayOfWeek
					current_3 = new DeptDayOfWeekShiftRosterVO();
					current_3.shift = row.getShift();
					current_3.shiftCode = row.getShiftCode();
					current_2.getColDeptDayOfWeekShiftRoster().add(current_3);
				}
			}

			if (current_3.getColDeptDayOfWeekShiftWeekOfMonth().size() == 0)
			{//no element for this deptUnitCode
				current_4 = new DeptDayOfWeekShiftWeekOfMonthVO();
				current_4.weekOfMonth = row.getWeekOfMonth();
				current_3.getColDeptDayOfWeekShiftWeekOfMonth().add(current_4);
			}
			else
			{
				//get last element in the collection to check the equality of the dOw with the current row
				current_4 = (DeptDayOfWeekShiftWeekOfMonthVO) ((ArrayList) current_3.getColDeptDayOfWeekShiftWeekOfMonth()).get(current_3
						.getColDeptDayOfWeekShiftWeekOfMonth().size() - 1);
				if (!current_4.weekOfMonth.equals(row.getWeekOfMonth()))
				{//different DayOfWeek
					current_4 = new DeptDayOfWeekShiftWeekOfMonthVO();
					current_4.weekOfMonth = row.getWeekOfMonth();
					current_3.getColDeptDayOfWeekShiftWeekOfMonth().add(current_4);
				}
			}

			//hoping that Unit will be different making the row unique
			//add tO the current_4.collection
			//create a new object and add
			current_5 = new DeptUnitVO();
			/*		   current_5.departmentUnit = row.getDepartmentUnit();
			 current_5.departmentUnitCode = row.getDepartmentUnitCode();*/
			WebUTIL.populate(current_5, row);
			current_4.getColDeptUnit().add(current_5);
		}
	}

	public Collection getColRosterMaster()
	{
		Collection colRosterMasterVO = new ArrayList();
		DeptShiftRosterVO currDeptShiftRosterVO = this;

		Collection col = currDeptShiftRosterVO.getColDeptDayOfWeekRoster();
		Iterator itr = col.iterator();
		while (itr.hasNext())
		{
			DeptDayOfWeekRosterVO currDeptDayOfWeekRosterVO = (DeptDayOfWeekRosterVO) itr.next();

			Collection col_a = currDeptDayOfWeekRosterVO.getColDeptDayOfWeekShiftRoster();
			Iterator itr_1 = col_a.iterator();
			Collection col_1 = new ArrayList();
			while (itr_1.hasNext())
			{
				DeptDayOfWeekShiftRosterVO currDeptDayOfWeekShiftRosterVO = (DeptDayOfWeekShiftRosterVO) itr_1.next();
				Collection col_b = currDeptDayOfWeekShiftRosterVO.getColDeptDayOfWeekShiftWeekOfMonth();
				Iterator itr_2 = col_b.iterator();
				Collection col_2 = new ArrayList();

				while (itr_2.hasNext())
				{
					DeptDayOfWeekShiftWeekOfMonthVO currDeptDayOfWeekShiftWeekOfMonthVO = (DeptDayOfWeekShiftWeekOfMonthVO) itr_2.next();

					Collection col_c = currDeptDayOfWeekShiftWeekOfMonthVO.getColDeptUnit();
					Iterator itr_3 = col_c.iterator();
					Collection col_3 = new ArrayList();

					while (itr_3.hasNext())
					{
						RosterMasterVO objRosterMasterVO = new RosterMasterVO();

						DeptUnitVO currDeptUnitVO = (DeptUnitVO) itr_3.next();
						HelperMethods.populate(objRosterMasterVO, currDeptUnitVO);
						col_3.add(objRosterMasterVO);
					}

					Iterator itr_col_3 = col_3.iterator();
					while (itr_col_3.hasNext())
					{
						RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col_3.next();
						HelperMethods.populate(currRosterMasterVO, currDeptDayOfWeekShiftWeekOfMonthVO);
						col_2.add(currRosterMasterVO);
					}
				}

				Iterator itr_col_2 = col_2.iterator();
				while (itr_col_2.hasNext())
				{
					RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col_2.next();
					HelperMethods.populate(currRosterMasterVO, currDeptDayOfWeekShiftRosterVO);
					col_1.add(currRosterMasterVO);
				}
			}

			Iterator itr_col_1 = col_1.iterator();
			while (itr_col_1.hasNext())
			{
				RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col_1.next();
				HelperMethods.populate(currRosterMasterVO, currDeptDayOfWeekRosterVO);
				colRosterMasterVO.add(currRosterMasterVO);
			}
		}

		Iterator itr_col = colRosterMasterVO.iterator();
		while (itr_col.hasNext())
		{
			RosterMasterVO currRosterMasterVO = (RosterMasterVO) itr_col.next();
			HelperMethods.populate(currRosterMasterVO, currDeptShiftRosterVO);
		}
		//colRosterMasterVO = col;
		return colRosterMasterVO;
	}

	/**
	Sets departmentCode.
	@param departmentCode
	@roseuid 45C1D4E00271
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	/**
	Retrieves departmentCode.
	@return Value of departmentCode.
	@roseuid 45C1D4E00280
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}

	/**
	Sets department.
	@param department
	@roseuid 45C1D4E00290
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	Retrieves department.
	@return Value of department.
	@roseuid 45C1D4E00292
	 */
	public String getDepartment()
	{
		return this.department;
	}

	/**
	Sets colDeptDayOfWeekRoster.
	@param colDeptDayOfWeekRoster
	@roseuid 45C1D51F0138
	 */
	public void setColDeptDayOfWeekRoster(Collection colDeptDayOfWeekRoster)
	{
		this.colDeptDayOfWeekRoster = colDeptDayOfWeekRoster;
	}

	/**
	Retrieves colDeptDayOfWeekRoster
	@return Value of colDeptDayOfWeekRoster
	@roseuid 45C1D51F013A
	 */
	public Collection getColDeptDayOfWeekRoster()
	{
		return this.colDeptDayOfWeekRoster;
	}

	/**
	 * 
	 * Inner class to keep the details in collection
	 *
	 */
	public class DeptDayOfWeekRosterVO extends ValueObject
	{
		private String dayOfWeek;
		private Collection colDeptDayOfWeekShiftRoster;

		/**
		@roseuid 45C2F7FF02AF
		 */
		public DeptDayOfWeekRosterVO()
		{
			colDeptDayOfWeekShiftRoster = new ArrayList();
		}

		/**
		Sets DayOfWeek.
		@param DayOfWeek
		@param dayOfWeek
		@roseuid 45C1D611032C
		 */
		public void setDayOfWeek(String dayOfWeek)
		{
			this.dayOfWeek = dayOfWeek;
		}

		/**
		RetrievesDayOfWeek.
		@return Value of dayOfWeek.
		@roseuid 45C1D611032E
		 */
		public String getDayOfWeek()
		{
			return this.dayOfWeek;
		}

		/**
		Sets colDeptDayOfWeekShiftRoster.
		@param colDeptDayOfWeekShiftRoster
		@roseuid 45C1D66D01A5
		 */
		public void setColDeptDayOfWeekShiftRoster(Collection colDeptDayOfWeekShiftRoster)
		{
			this.colDeptDayOfWeekShiftRoster = colDeptDayOfWeekShiftRoster;
		}

		/**
		Retrieves colDeptDayOfWeekShiftRoster
		@return Value of colDeptDayOfWeekShiftRoster
		@roseuid 45C1D66D01B5
		 */
		public Collection getColDeptDayOfWeekShiftRoster()
		{
			return this.colDeptDayOfWeekShiftRoster;
		}
	}

	public class DeptDayOfWeekShiftRosterVO extends ValueObject
	{
		private String shift;
		private String shiftCode;
		private Collection colDeptDayOfWeekShiftWeekOfMonth;

		/**
		@roseuid 45C2F7FF02CE
		 */
		public DeptDayOfWeekShiftRosterVO()
		{
			colDeptDayOfWeekShiftWeekOfMonth = new ArrayList();
		}

		/**
		Sets shiftCode.
		@param shiftCode
		@roseuid 45C1D8B6033C
		 */
		public void setShiftCode(String shiftCode)
		{
			this.shiftCode = shiftCode;
		}

		/**
		Retrieves ShiftCode.
		@return Value of shiftCode.
		@roseuid 45C1D8C101A5
		 */
		public String getShiftCode()
		{
			return this.shiftCode;
		}

		/**
		Sets colDeptDayOfWeekShiftWeekOfMonth.
		@param colDeptDayOfWeekShiftWeekOfMonth
		@roseuid 45C1D948003E
		 */
		public void setColDeptDayOfWeekShiftWeekOfMonth(Collection colDeptDayOfWeekShiftWeekOfMonth)
		{
			this.colDeptDayOfWeekShiftWeekOfMonth = colDeptDayOfWeekShiftWeekOfMonth;
		}

		/**
		Retrieves colDeptDayOfWeekShiftWeekOfMonth.
		@return Value of colDeptDayOfWeekShiftWeekOfMonth
		@roseuid 45C1D95C033C
		 */
		public Collection getColDeptDayOfWeekShiftWeekOfMonth()
		{
			return this.colDeptDayOfWeekShiftWeekOfMonth;
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

	public class DeptDayOfWeekShiftWeekOfMonthVO extends ValueObject
	{
		private String weekOfMonth;
		private Collection colDeptUnit;

		/**
		@roseuid 45C2F7FF02FD
		 */
		public DeptDayOfWeekShiftWeekOfMonthVO()
		{
			colDeptUnit = new ArrayList();
		}

		/**
		Sets weekOfMonth.
		@param weekOfMonth
		@roseuid 45C1DAA300BB
		 */
		public void setWeekOfMonth(String weekOfMonth)
		{
			this.weekOfMonth = weekOfMonth;
		}

		/**
		Retrieves weekOfMonth.
		@return Value of weekOfMonth.
		@roseuid 45C1DAB100BB
		 */
		public String getWeekOfMonth()
		{
			return this.weekOfMonth;
		}

		/**
		Sets colDeptUnit.
		@param colDeptUnit
		@roseuid 45C1DE7C037A
		 */
		public void setColDeptUnit(Collection colDeptUnit)
		{
			this.colDeptUnit = colDeptUnit;
		}

		/**
		Retrieves colDeptUnit.
		@return Value of colDeptUnit
		@roseuid 45C1DE7C038A
		 */
		public Collection getColDeptUnit()
		{
			return this.colDeptUnit;
		}
	}

	public class DeptUnitVO extends ValueObject
	{
		private String departmentUnit;
		private String departmentUnitCode;
		private String unitSequenceNo;

		public String getUnitSequenceNo()
		{
			return unitSequenceNo;
		}

		public void setUnitSequenceNo(String unitSequenceNo)
		{
			this.unitSequenceNo = unitSequenceNo;
		}

		/**
		  @roseuid 45C2F7FF031C
		 */
		public DeptUnitVO()
		{

		}

		/**
		Sets departmentUnitCode.
		@param departmentUnitCode
		@roseuid 45C1DF340242
		 */
		public void setDepartmentUnitCode(String departmentUnitCode)
		{
			this.departmentUnitCode = departmentUnitCode;
		}

		/**
		Retrieves departmentUnitCode.
		@return Value of departmentUnitCode.
		@roseuid 45C1DF340244
		 */
		public String getDepartmentUnitCode()
		{
			return this.departmentUnitCode;
		}

		/**
		Sets departmentUnit.
		@param departmentUnit
		@roseuid 45C1DF340251
		 */
		public void setDepartmentUnit(String departmentUnit)
		{
			this.departmentUnit = departmentUnit;
		}

		/**
		Retrieves departmentUnit.
		@return Value of departmentUnit.
		@roseuid 45C1DF340261
		 */
		public String getDepartmentUnit()
		{
			return this.departmentUnit;
		}

		public boolean equals(Object o)
		{
			if (o == null) return false;

			if (!(o instanceof DeptUnitVO)) return false;

			if (((DeptUnitVO) o).departmentUnitCode.equalsIgnoreCase(this.departmentUnitCode)) return true;

			return false;
		}
	}

	public class DSRComparator implements Comparator
	{

		/**
		@roseuid 45C2F7FF033C
		 */
		public DSRComparator()
		{

		}

		/**
		@param arg0
		@param arg1
		@return int
		@roseuid 45C2F7FF034B
		 */
		public int compare(Object arg0, Object arg1)
		{
			if (arg0 == null || arg1 == null) throw new IllegalArgumentException();

			RosterMasterVO rmVO1 = (RosterMasterVO) arg0;
			RosterMasterVO rmVO2 = (RosterMasterVO) arg1;

			int result = rmVO1.getDepartmentCode().compareTo(rmVO2.getDepartmentCode());
			if (result == 0)
			{// same
				result = rmVO1.getDayOfWeek().compareTo(rmVO2.getDayOfWeek());
				if (result == 0)
				{//same
					result = rmVO1.getShiftCode().compareTo(rmVO2.getShiftCode());
					if (result == 0)
					{
						result = rmVO1.getWeekOfMonth().compareTo(rmVO2.getWeekOfMonth());
						if (result == 0)
						{
							result = rmVO1.getUnitSequenceNo().compareTo(rmVO2.getUnitSequenceNo());
						}
					}
				}
			}
			return result;
		}

		/**
		@param arg0
		@return boolean
		@roseuid 45C2F7FF038A
		 */
		public boolean equals(Object arg0)
		{
			//implement this
			return true;
		}
	}

}
