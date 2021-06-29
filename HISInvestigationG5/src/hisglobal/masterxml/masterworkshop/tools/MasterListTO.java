package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;
import java.util.regex.Pattern;

public class MasterListTO extends MasterTO
{
	ArrayList alData;
	ArrayList alColHeading;
	int noOfColumn;
	int noOfPrimaryKey;
	MasterListDtl objMstLstDtl;
	String isActive;
	int sortOn = 1;//////// Earlier the value of sortOn was 0, but to order the records by name it is changed to 1
	int order = 0;
	int searchField;
	String searchOrSort = "";
	String txtSearch = "";
	int startval = 0;
	List listSearch = new ArrayList();
	int searchmode = 0;//this 0 means false

	//the mode for display on list page will be search or normal list display

	public int getSearchmode()
	{
		return searchmode;
	}

	public void setSearchmode(int searchmode)
	{
		this.searchmode = searchmode;
	}

	public MasterListTO()
	{
		ArrayList alData = new ArrayList();
		ArrayList alColHeading = new ArrayList();
	}

	public void setIsActive(java.lang.String isActive)
	{
		this.isActive = isActive;
	}

	public java.lang.String getIsActive()
	{
		return isActive;
	}

	public void setObjMstLstDtl(MasterListDtl objMstLstDtl)
	{
		this.objMstLstDtl = objMstLstDtl;
	}

	public MasterListDtl getObjMstLstDtl()
	{
		return objMstLstDtl;
	}

	public java.util.ArrayList getAlColHeading()
	{
		return alColHeading;
	}

	public void setAlColHeading(java.util.ArrayList alColHeading)
	{
		this.alColHeading = alColHeading;
	}

	public java.util.ArrayList getAlData()
	{
		return alData;
	}

	public void setAlData(java.util.ArrayList alData)
	{
		this.alData = alData;
	}

	public int getNoOfColumn()
	{
		return noOfColumn;
	}

	public void setNoOfColumn(int noOfColumn)
	{
		this.noOfColumn = noOfColumn;
	}

	public int getNoOfPrimaryKey()
	{
		return noOfPrimaryKey;
	}

	public void setNoOfPrimaryKey(int noOfPrimaryKey)
	{
		this.noOfPrimaryKey = noOfPrimaryKey;
	}

	public void display()
	{
		System.out.println("------------Displayong to values-------------");
		/*System.out.println("title:::" + title);
		System.out.println("allist" + alData);
		System.out.println("tbl heading:::" + tblHeading);
		System.out.println("al controls:::" + controls);
		System.out.println("no of col::::" + noOfColumn);
		System.out.println("no of pk::::" + noOfPrimaryKey);
		System.out.println("col heading :::: " + alColHeading);
		System.out.println("masterlstdtlobj :::: " + objMstLstDtl);*/
	}

	public int getSortOn()
	{
		return sortOn;
	}

	public void setSortOn(int sortOn)
	{
		this.sortOn = sortOn;
	}

	/**
	 * This method returns the collection wherein each element 
	 * is again an arraylist of rows.
	 * This method is called from the list page which uses this collection to list records
	 * The execution of method goes as follows.
	 * 1) The arraylist of fetched records is iterated to extract the sublist which represents a row
	 * 2) The each sublist is then further added to an arraylist.
	 * 3) The method then checks weather the request for the collection has come as a result of search 
	 *    or sort operation.
	 * 4) If it is search>>>>>>>
	 *     >>>> The ArrayList which is created at step 2 is used
	 *     >>>> For each element which represents a row  ,value for the col on which the search is to be made is retrieved
	 *     >>>> If the value matches the text search ie if it exist in the value for that col 
	 *     >>>> that row is added to the new list searchlist.
	 *     >>>> since list page can show only ten records at a time.
	 *     >>>> a sublist of ten records is further extracted on the basis of startval(used for pagination)
	 *     >>>> This will return the search list comprising of ten rows or less.
	 *     ELSE The request is to sort //by default for first time
	 *     >>>> This portion deals with two cases
	 *     >>>> to sort a normal list or to sort a search list
	 *     >>>> Both cases extracts sublist of ten records and then sort them based on the order asc or dsc 
	 *     >>>> pagination  
	 *      
	 *    
	 * 
	 */

	public Collection getRows()
	{

		//System.out.println("inside getRows");
		//System.out.println("getSearchOrSort" + getSearchOrSort());

		//SortedSet ss=new TreeSet(new ListComparator(_sortOn));
		List sList = new ArrayList();
		//first the sublist are created and where each element represents a row.
		List lst = new ArrayList();//each element of which will be a list representing a row.
		//System.out.println("noOfColumn" + noOfColumn);
		int noOfCol = noOfColumn + 1;

		for (int i = 0; i < alData.size(); i = i + noOfCol)
		{
			int startidx = i;
			int lastidx = i + noOfCol;
			List subLst = alData.subList(startidx, lastidx);
			lst.add(subLst);
		}
		//System.out.println("lst in get rows" + lst);

		/* ArrayList listForAPage=new ArrayList();	 
		 
		 for(int i=0;i<lst.size;i++)
		  {
			listForAPage.add(lst.get(i));
			
		  }	  
		 */
		//if list to be build 
		if (getSearchOrSort().equalsIgnoreCase("search"))
		{
			//System.out.println("inside search");
			//System.out.println("lst" + lst);
			for (int i = 0; i < lst.size(); i++)
			{
				List rowList = (List) lst.get(i);//fetch each row		

				//search on a particular column/field		
				//search field represents the column no on which the search is to be carried out 
				//System.out.println("getSearchField" + getSearchField());//column on which the search is to be made				  				  				  
				String val = (String) rowList.get(getSearchField());
				//System.out.println("val::" + val);
				//if(val.equalsIgnoreCase(getTxtSearch()))
				/*System.out.println("Value   =" + val.toLowerCase() + "  searchText  =" + getTxtSearch().toLowerCase()
						+ " * pattern Matching  =" + Pattern.matches("a*b", "aaab"));
				*/System.out.println("Value   =" + val.toLowerCase() + "  searchText  =" + getTxtSearch().toLowerCase()
						+ " * pattern Matching  ="
						+ Pattern.matches(getTxtSearch().toLowerCase() + "[[a-z][0-9][ ][-:][.]]*", val.toLowerCase()));

				/////////Pattern mAtaching is done since earlier the search was alphabets based/////////////

				if (Pattern.matches(getTxtSearch().toLowerCase() + "[[a-z][0-9][ ][-:][.]]*", val.toLowerCase()))
				{
					//System.out.println("inside if");
					sList.add(rowList);
					//System.out.println("slist::" + sList);
				}
				/* if(val.toLowerCase().indexOf(getTxtSearch().toLowerCase())!=-1)														  
				  {
				 System.out.println("inside if"); 
				 sList.add(rowList);
				 System.out.println("slist::"+sList);				
				  }*/

			}

			setListSearch(sList);
			//System.out.println("outside for ");
			List searchListwithtenorlessrecords = new ArrayList();
			/* //since list as result of search might include more than 10 records or less
			 //sublist will throw exception if only searchList=sList.subList(startval,startval+10); is written
			 //therefre the if/else block 		    		 
			 */
			//System.out.println("startval" + startval);
			//System.out.println("sList" + sList.size());

			if (sList.size() > 10)
			{
				searchListwithtenorlessrecords = sList.subList(startval, startval + 10);
			}
			else
			{
				searchListwithtenorlessrecords = sList.subList(startval, sList.size());
			}
			//System.out.println("searchList::" + searchListwithtenorlessrecords);
			return searchListwithtenorlessrecords;
		}
		else if(getSearchOrSort().equalsIgnoreCase("sort"))
		/////used for sorting
		{
			//System.out.println("lst:::" + lst);
			List pageDatalst = new ArrayList();
			//System.out.println("inside else");
			//System.out.println("pageDatalst" + pageDatalst);
			int alSize = getAlData().size();
			//System.out.println("alSize" + alSize);
			//System.out.println("startval::::" + startval);
			//int nOCol=getAlColHeading().size()+1;		  	  
			int rows = alSize / noOfCol;

			//System.out.println("rows::" + rows);
			if (this.getSearchmode() == 0) //if list to be sorted is not the searchlist 
			{
				if (lst.size() > 10)//if more than 10 records
				{
					if (startval + 10 > rows)//
					{
						pageDatalst = lst.subList(startval, startval + rows % 10);
					}
					else pageDatalst = lst.subList(startval, startval + 10);
				}
				else
				{
					pageDatalst = lst.subList(startval, lst.size());
				}

				//sorton specifies column  and order specifies asc or dsc 
				Collections.sort(pageDatalst, new ListComparator(sortOn, order));
				/*pageDatalst=lst.subList(startval,startval+10);*/
				//System.out.println("pageDatalst" + pageDatalst);
				//System.out.println("lst::"+lst);
				return pageDatalst;
			}
			else
			//list to be sorted is search list
			{
				//System.out.println("inside search mode elseeee");
				List searchList = new ArrayList();
				//System.out.println("this.getListSearch()::::" + this.getListSearch());
				//System.out.println("this.getListSearch()size::::" + this.getListSearch().size());

				/* //since list as result of search might include more than 10 records or less
				 //sublist will throw exception if only searchList=sList.subList(startval,startval+10); is written
				 //therefre the if/else block 		    		 
				 */
				if (this.getListSearch().size() > 10)
				{
					if (startval + 10 > this.getListSearch().size())//
					{
						searchList = this.getListSearch().subList(startval, startval + this.getListSearch().size() % 10);
					}
					else searchList = this.getListSearch().subList(startval, startval + 10);
				}
				else
				{
					//System.out.println("startval::::" + startval);
					searchList = this.getListSearch().subList(startval, this.getListSearch().size());
				}
				//System.out.println("searchList::" + searchList);
				Collections.sort(searchList, new ListComparator(sortOn, order));
				/*pageDatalst=lst.subList(startval,startval+10);*/
				//System.out.println("pageDatalst" + pageDatalst);
				//System.out.println("lst::"+lst);
				return searchList;
			}
		}else{ ///used for first time when no search and sort required
			List pageDatalst = new ArrayList();
			//System.out.println("inside else");
			//System.out.println("pageDatalst" + pageDatalst);
			int alSize = getAlData().size();
			//System.out.println("alSize" + alSize);
			//System.out.println("startval::::" + startval);
			//int nOCol=getAlColHeading().size()+1;		  	  
			int rows = alSize / noOfCol;

			//System.out.println("rows::" + rows);
			if (this.getSearchmode() == 0) //if list to be sorted is not the searchlist 
			{
				if (lst.size() > 10)//if more than 10 records
				{
					if (startval + 10 > rows)//
					{
						pageDatalst = lst.subList(startval, startval + rows % 10);
					}
					else pageDatalst = lst.subList(startval, startval + 10);
				}
				else
				{
					pageDatalst = lst.subList(startval, lst.size());
				}

				//sorton specifies column  and order specifies asc or dsc 
				//Collections.sort(pageDatalst, new ListComparator(sortOn, order));
				/*pageDatalst=lst.subList(startval,startval+10);*/
				//System.out.println("pageDatalst" + pageDatalst);
				//System.out.println("lst::"+lst);
				return pageDatalst;
			}
			else
			//list to be sorted is search list
			{
				//System.out.println("inside search mode elseeee");
				List searchList = new ArrayList();
				//System.out.println("this.getListSearch()::::" + this.getListSearch());
				//System.out.println("this.getListSearch()size::::" + this.getListSearch().size());

				/* //since list as result of search might include more than 10 records or less
				 //sublist will throw exception if only searchList=sList.subList(startval,startval+10); is written
				 //therefre the if/else block 		    		 
				 */
				if (this.getListSearch().size() > 10)
				{
					if (startval + 10 > this.getListSearch().size())//
					{
						searchList = this.getListSearch().subList(startval, startval + this.getListSearch().size() % 10);
					}
					else searchList = this.getListSearch().subList(startval, startval + 10);
				}
				else
				{
					//System.out.println("startval::::" + startval);
					searchList = this.getListSearch().subList(startval, this.getListSearch().size());
				}
				//System.out.println("searchList::" + searchList);
				//Collections.sort(searchList, new ListComparator(sortOn, order));
				/*pageDatalst=lst.subList(startval,startval+10);*/
				//System.out.println("pageDatalst" + pageDatalst);
				//System.out.println("lst::"+lst);
				return searchList;
			}
		}
	}

	public void setOrder(int order)
	{
		this.order = order;
	}

	public int getOrder()
	{
		return order;
	}

	public int getSearchField()
	{
		return searchField;
	}

	public void setSearchField(int searchField)
	{
		this.searchField = searchField;
	}

	public java.lang.String getSearchOrSort()
	{
		return searchOrSort;
	}

	public void setSearchOrSort(java.lang.String searchOrSort)
	{
		this.searchOrSort = searchOrSort;
	}

	public java.lang.String getTxtSearch()
	{
		return txtSearch;
	}

	public void setTxtSearch(java.lang.String txtSearch)
	{
		this.txtSearch = txtSearch;
	}

	public int getStartval()
	{
		return startval;
	}

	public void setStartval(int startval)
	{
		this.startval = startval;
	}

	public java.util.List getListSearch()
	{
		return listSearch;
	}

	public void setListSearch(java.util.List listSearch)
	{
		this.listSearch = listSearch;
	}

}//end of class

class ListComparator implements Comparator
{
	static final int ASCENDING = 0;
	static final int DESCENDING = 1;

	int compareOn = 0;
	int order = ASCENDING;

	ListComparator(int _sortOn, int _order)
	{
		this.compareOn = _sortOn;
		this.order = _order;
	}

	public int compare(Object col1, Object col2)
	{
		int result;

		try
		{
			//System.out.println("compareOn::" + compareOn);

			int val1 = Integer.parseInt((String) ((List) col1).get(compareOn));
			int val2 = Integer.parseInt((String) ((List) col2).get(compareOn));

			Integer o1 = new Integer(val1);
			Integer o2 = new Integer(val2);
			//col2=new Integer(val2);

			//System.out.println("(String)((List)col1).get(compareOn)" + (String) ((List) col1).get(compareOn));
			//System.out.println("(String)((List)col2).get(compareOn)" + (String) ((List) col2).get(compareOn));
			result = o1.compareTo(o2);
			//result=col1.compareTo(col2);			  
			// result = ((String)((List)col1).get(compareOn)).compareTo ((String)((List)col2).get(compareOn));
			//System.out.println("result:::" + result);
		}
		catch (NumberFormatException e)
		{
			//System.out.println("inside number format exception");
			result = ((String) ((List) col1).get(compareOn)).compareTo((String) ((List) col2).get(compareOn));
			//System.out.println("result:::" + result);
			if (order == ASCENDING) return result;
			else return result * -1;
		}
		catch (IndexOutOfBoundsException e)
		{
			throw new IllegalArgumentException("" + e);
		}
		if (order == ASCENDING) return result;
		else return result * -1;
	}

	public void setOrder(int order)
	{
		this.order = order;
	}

	public int getOrder()
	{
		return order;
	}
} //end of class listComparator
