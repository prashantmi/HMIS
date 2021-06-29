/**********************************************************
 Project:	   AHIMS_G5	
 File:         TransactionVO.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma
 Purpose:		TransactionVO is the class that specifies getters and setters for all the 
 				identifiers which are used for retrieving and inserting information in the DB tables.

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.vo;

//import java.util.*;
/**
 * TransactionVO is the class that specifies getters and setters for all the
 * identifiers which are used for retrieving and inserting information in the DB
 * tables.
 */
public class TransactionVO extends ValueObject
{
	private static final long serialVersionUID = 0002L;

	private String menuID;

	/**
	 * Retrieves menuID.
	 * 
	 * @return Value of menuID.
	 */
	public String getMenuID()
	{
		return menuID;
	}

	/**
	 * Sets menuID.
	 * 
	 * @param menuID
	 *            .
	 */
	public void setMenuID(String menuID)
	{
		this.menuID = menuID;
	}

}
