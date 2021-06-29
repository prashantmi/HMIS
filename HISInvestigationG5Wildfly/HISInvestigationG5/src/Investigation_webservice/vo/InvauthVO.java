package Investigation_webservice.vo;

import hisglobal.vo.ValueObject;


public class InvauthVO extends ValueObject {

	private String key;
	private	String iswhitelistreq;
	private String whitelistip;
	private String source;
	private String isseckeyreq;
	private String seckey;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIswhitelistreq() {
		return iswhitelistreq;
	}
	public void setIswhitelistreq(String iswhitelistreq) {
		this.iswhitelistreq = iswhitelistreq;
	}
	public String getWhitelistip() {
		return whitelistip;
	}
	public void setWhitelistip(String whitelistip) {
		this.whitelistip = whitelistip;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIsseckeyreq() {
		return isseckeyreq;
	}
	public void setIsseckeyreq(String isseckeyreq) {
		this.isseckeyreq = isseckeyreq;
	}
	public String getSeckey() {
		return seckey;
	}
	public void setSeckey(String seckey) {
		this.seckey = seckey;
	}

	
}
