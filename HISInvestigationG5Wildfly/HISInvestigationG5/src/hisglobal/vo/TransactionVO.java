package hisglobal.vo;

//import java.util.*;
/**
 * TransactionVO is the class that specifies getters and setters for all the
 * identifiers which are used for retrieving and inserting information in the DB
 * tables.
 * 
 * @author AHIS
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
