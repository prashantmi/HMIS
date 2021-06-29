	package usermgmt.masters;
	import usermgmt.*; 
	//import usermgmt.FuncLib;
	import java.util.*;
	
	
	public class ChangePassBean //extends usermgmt.masters.invPage 
	{  
		private ArrayList radio  = new ArrayList() ; 
		private ArrayList userId  = new ArrayList() ; 
		private ArrayList name = new ArrayList() ; 
		private ArrayList effDate = new ArrayList() ; 
		private ArrayList expDate = new ArrayList() ; 
	
		generalQuery gq		=	new generalQuery();
		umgmtGlobal global 	= 	new umgmtGlobal();
		
		
		public java.util.ArrayList getEffDate( )
		{
			return effDate;
		}
	
		public void setEffDate( java.util.ArrayList effDate )
		{
			
			
			this.effDate = effDate;
		}
	
		public java.util.ArrayList getExpDate( )
		{
			return expDate;
		}
	
		public void setExpDate( java.util.ArrayList expDate )
		{
			this.expDate = expDate;
		}
	
		public java.util.ArrayList getName( )
		{
			return name;
		}
	
		public void setName( java.util.ArrayList name )
		{
			this.name = name;
		}
	
		public java.util.ArrayList getRadio( )
		{
			return radio;
		}
	
		public void setRadio( java.util.ArrayList radio )
		{
			this.radio = radio;
		}
	
		public java.util.ArrayList getUserId( )
		{
			return userId;
		}
	
		public void setUserId( java.util.ArrayList userId )
		{
			this.userId = userId;
		}
	}
