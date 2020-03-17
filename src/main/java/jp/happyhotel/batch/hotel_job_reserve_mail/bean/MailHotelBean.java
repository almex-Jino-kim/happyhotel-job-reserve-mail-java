package jp.happyhotel.batch.hotel_job_reserve_mail.bean;

/**
 * mail_hotel Bean<br>
 * <br>
 * ホテルへのメール送信情報を表すクラスです。<br>
 * 
 * @author kimu-k1
 */
public class MailHotelBean {
	
	private String emailAddr;
	private int sendmailStartTime;
	private int sendmailEndTime;
	
	public String getEmailAddr() {
		return emailAddr;
	}
	
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	
	public int getSendmailStartTime() {
		return sendmailStartTime;
	}
	
	public void setSendmailStartTime(int sendmailStartTime) {
		this.sendmailStartTime = sendmailStartTime;
	}
	
	public int getSendmailEndTime() {
		return sendmailEndTime;
	}
	
	public void setSendmailEndTime(int sendmailEndTime) {
		this.sendmailEndTime = sendmailEndTime;
	}

}
