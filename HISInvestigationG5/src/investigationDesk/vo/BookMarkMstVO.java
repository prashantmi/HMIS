/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:30-MAR-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package investigationDesk.vo;

import hisglobal.vo.ValueObject;

public class BookMarkMstVO extends ValueObject {
	
	private String bookmarkCode;
	private String bookmarkType;
	private String entryDate;
	private String bookmarkName;
	public String getBookmarkCode() {
		return bookmarkCode;
	}
	public void setBookmarkCode(String bookmarkCode) {
		this.bookmarkCode = bookmarkCode;
	}
	public String getBookmarkType() {
		return bookmarkType;
	}
	public void setBookmarkType(String bookmarkType) {
		this.bookmarkType = bookmarkType;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getBookmarkName() {
		return bookmarkName;
	}
	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}
	
	

}
