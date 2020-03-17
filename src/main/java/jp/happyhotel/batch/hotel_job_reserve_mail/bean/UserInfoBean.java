package jp.happyhotel.batch.hotel_job_reserve_mail.bean;

public class UserInfoBean {
	
	private String userId;
	private String mailAddr;
	private String mailAddrMobile;
	private String mailStarttime;
	private String mailEndtime;
	private String smsPhoneNo;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMailAddr() {
		return mailAddr;
	}
	
	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	
	public String getMailAddrMobile() {
		return mailAddrMobile;
	}
	
	public void setMailAddrMobile(String mailAddrMobile) {
		this.mailAddrMobile = mailAddrMobile;
	}
	
	public String getMailStarttime() {
		return mailStarttime;
	}
	
	public void setMailStarttime(String mailStarttime) {
		this.mailStarttime = mailStarttime;
	}
	
	public String getMailEndtime() {
		return mailEndtime;
	}
	
	public void setMailEndtime(String mailEndtime) {
		this.mailEndtime = mailEndtime;
	}
	
	public String getSmsPhoneNo() {
		return smsPhoneNo;
	}
	
	public void setSmsPhoneNo(String smsPhoneNo) {
		this.smsPhoneNo = smsPhoneNo;
	}
	

}
