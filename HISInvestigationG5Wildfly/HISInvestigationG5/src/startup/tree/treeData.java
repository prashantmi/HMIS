
package startup.tree;


import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.backutil.HelperMethods;
import hisglobal.backutil.dto.DataTransferObject;

import java.sql.ResultSet;
import java.util.*;

import javax.sql.rowset.WebRowSet;

import dietkitchen.vo.MenuMasterVO;

import startup.loginDao;
import startup.qryHandler_startup;
import startup.menu.MenuVO;
//import startup.menu.global;
public class treeData extends DataAccessObject
{
	protected Map mapMenusHirarchy;
	protected Map<String, List<MenuVO>> mapContextMenus;
	protected List<MenuVO> lstUserMenus;
	public treeData (TransactionContext _transactionContext) {
		super(_transactionContext);
	}
	
	public ArrayList execute(String userId,String seatId, String hospitalCode) throws Exception
	{
		String tmp = "";
		String[] val;
		int i;
		String query="";
		ArrayList rs = new ArrayList();
		Map populateMap=new HashMap();
		Map populateMap2=new HashMap();
		String url="";
		int temp;
		ResultSet resultSet=null;
		loginDao loginDaoObj=new loginDao(super.getTransactionContext()); 
		try{
		//	query = qryHandler_startup.getQuery("select.treeQry.0");///Old Query
		//query = qryHandler_startup.getQuery("SELECT_TREE_QUERY_1"); ///new Query
		//System.out.println("the query is:"+query);	
		//Sequence sq=new Sequence();
		
		//populateMap.put(sq.next(), hospitalCode);
		//populateMap.put(sq.next(), seatId);
		
		//Menu Query is Commented instead we are using the Procedure
		//resultSet = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		
		resultSet=loginDaoObj.getMenuForUser(hospitalCode,seatId);
		
		/*Commneted due to one menu is missing
		 * 
		 * if(resultSet.next())
		{*/
		//Commneted in case of Calling for procedure	
		//	resultSet.previous();
		
			//////To add menu of HIS Services/////
		
			while(resultSet.next())
			{
				ArrayList list = new ArrayList();
				tmp = resultSet.getString(1);
				val = tmp.split("#");
				list.add("HIS Services");// To show all his menu under the heading his services
				for(i=0;i<val.length;i++)
				{				
					list.add(val[i]);
					
				}
				
				
				url = "";
				url = resultSet.getString(2);
				//System.out.println("url is "+url);
				if(url!= null && !url.equals(""))
				{
					temp = url.indexOf("?");
					if(temp>0)
						url = url + "&seatId="+seatId;
					else
					  url = url + "?seatId="+seatId;
				}
				
				list.add(url);
				
								
				rs.add(list);
			}
		//}
		
		//////To add menu of My Services/////
		
		 
		String menuquery=qryHandler_startup.getQuery("SELECT_MY_SERVICES_TREE_QUERY"); 
			
		//System.out.println("the query is:"+menuquery);	
		Sequence sq1=new Sequence();
		populateMap2.put(sq1.next(), userId);
		
		
		resultSet = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), menuquery, populateMap2);
		
		
		if(resultSet.next())
		{
			resultSet.previous();
			while(resultSet.next())
			{
				ArrayList myServices=new ArrayList();
				String menuname = resultSet.getString(1);
				String url1=resultSet.getString(2);
				
				myServices.add("My Services"); //////Root menu My Services
				myServices.add(menuname);
				myServices.add(url1);
				
				
				
								
				rs.add(myServices);
			}
		}
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return rs;
	}
	public  Map getMenusHierarchyMap(String userId,String seatId, String hospitalCode)
	{
		ArrayList<MenuVO> lstMenu=null;
		try{
		lstMenu=this.getUserMenuDetail(userId,seatId,hospitalCode);
		lstMenu.addAll(getMyServicesMenu(userId,seatId,hospitalCode));
		}catch(Exception e){
			e.printStackTrace();
		}
			if (lstMenu != null && lstMenu.size() > 0)
			{
				this.lstUserMenus = new ArrayList<MenuVO>();
				mapContextMenus = new HashMap<String, List<MenuVO>>();

				Map mp = new LinkedHashMap();
				for (MenuVO voMenu : lstMenu)
				{
					String arrMenu[] = voMenu.getVarMenuName().split("#");

					Map mpMenuBase = mp;
					for (int i = 0; i < (arrMenu.length - 1); i++)
					{
						String menuName = arrMenu[i];
						Object obj = mpMenuBase.get(menuName);
						if (obj == null)
						{
							mpMenuBase.put(menuName, new LinkedHashMap());
							mpMenuBase = (Map) mpMenuBase.get(menuName);
						}
						else if (obj instanceof Map)
						{
							mpMenuBase = (Map) obj;
						}
						else if (obj instanceof String)
						{
							String menuURL = (String) obj;
							mpMenuBase.put(menuName, new LinkedHashMap());
							mpMenuBase = (Map) mpMenuBase.get(menuName);
							mpMenuBase.put(menuName, menuURL);
						}
					}

					mpMenuBase.put(arrMenu[arrMenu.length - 1], voMenu.getVarURL());

					// Adding to Menu List
					MenuVO voMenuComplete = new MenuVO();
					voMenuComplete.setVarMenuId(voMenu.getVarMenuId());
					voMenuComplete.setVarMenuName(arrMenu[arrMenu.length - 1]);
					voMenuComplete.setVarURL(voMenu.getVarURL());
					/*voMenuComplete.setVarModuleName(arrMenu[0]);
					voMenuComplete.setVarMenuContext(getContext(voMenu.getVarURL()));
					voMenuComplete.setVarMenuLevel(Integer.toString(arrMenu.length));*/
					this.lstUserMenus.add(voMenuComplete);

					// Adding to Context Wise Menu Map
					if (this.mapContextMenus.get(voMenuComplete.getVarMenuContext()) == null)
						this.mapContextMenus.put(voMenuComplete.getVarMenuContext(), new ArrayList<MenuVO>());
					this.mapContextMenus.get(voMenuComplete.getVarMenuContext()).add(voMenuComplete);
				}

				if (mp.size() > 0)
				{
					System.out.println(" mp.size: "+ mp.size());
					this.mapMenusHirarchy = mp;
				}
			}
		
		
		
		
		
		
		
		return this.mapMenusHirarchy;
	}
	
	/**
	 * @author Amit Chhikara
	 * Fetching User Menu Detail module wise
	 * 
	 * @param userId
	 * @param seatId
	 * @param hospitalCode
	 * @return List<MenuVO>
	 * @throws Exception
	 */
	public  ArrayList<MenuVO>  getUserMenuDetail(String userId,String seatId, String hospitalCode)
	{
		System.out.println(" inside getUserMenuDetails");
		System.out.println(" userid: "+ userId+ " seatId: "+ seatId+ " hospCode: "+ hospitalCode);
		ArrayList<MenuVO> userMenuList = new ArrayList<MenuVO>();
		ResultSet resultSet=null;
		JDBCTransactionContext tx=new JDBCTransactionContext();
		loginDao loginDaoObj=new loginDao(tx); 
		try{
		
		resultSet=loginDaoObj.getMenuForUserModuleWise(hospitalCode,seatId);
		
		List<MenuVO> lst = new ArrayList<MenuVO>();
		DataTransferObject[] arrVOs = {};
		try
		{
			if (resultSet!=null && resultSet.next())
			{
				resultSet.beforeFirst();
				arrVOs = HelperMethods.populateDTOfrmRS(MenuVO.class, resultSet);
				for (DataTransferObject obj : arrVOs){
					lst.add((MenuVO) obj);
				}
			}
			userMenuList.addAll(lst);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			tx=null;
		}
		
		return userMenuList;
	}
	/**
	 * @author Amit Chhikara
	 * Fetching User Menu Detail module wise
	 * 
	 * @param userId
	 * @param seatId
	 * @param hospitalCode
	 * @return List<MenuVO>
	 * @throws Exception
	 */
	
	public  ArrayList<MenuVO>  getMyServicesMenu(String userId,String seatId, String hospitalCode)
	{
		ArrayList<MenuVO> myServicesMenuList = new ArrayList<MenuVO>();
		ResultSet resultSet=null;
		HashMap<Integer,String> populateMap2=new HashMap<Integer,String>();
		JDBCTransactionContext tx=new JDBCTransactionContext();
		try{
			tx.begin();
			String menuquery=qryHandler_startup.getQuery("SELECT_MY_SERVICES_TREE_QUERY"); 
			
			//System.out.println("the query is:"+menuquery);	
			Sequence sq1=new Sequence();
			populateMap2.put(sq1.next(), seatId);
			
			
			resultSet = HelperMethodsDAO.executeQuery(tx.getConnection(), menuquery, populateMap2);
			
			
			if(resultSet.next())
			{
				do
				
				{
					MenuVO menuObj= new MenuVO();
					ArrayList myServices=new ArrayList();
					/* Module name hard coded to "My Services". 
					 * Each module divided into 3 categories: 
					 * 1. Services ==> It is listed directly
					 * 2. Setup
					 * 3. Reports
					 * 
					 */ 
					
					String menuname ="My Services#Services#"+ resultSet.getString(1);
					String url1=resultSet.getString(2);
					myServices.add("My Services"); //////Root menu My Services
					menuObj.setVarMenuName(menuname);
					menuObj.setVarURL(url1);
					myServicesMenuList.add(menuObj);
					//rs.add(myServices);
				}while(resultSet.next());
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("treeData::getMyServicesMenu:: error in fetching My Services");
		}finally{
			try{
				tx.close();
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		return myServicesMenuList;
	}
}
