package jp.happyhotel.batch.hotel_job_reserve_mail.bean;

/**
 * sys_info Bean<br>
 * <br>
 * プログラム情報を表すクラスです。<br>
 * 
 * @author kimu-k1
 */
public class SysInfoBean {
	 private int timerInterval;
     private String execDateFile;
     private String mailSmtp;
     private int mailPort;
     private String fromAddr;
     private String adminAddr;
     private String expediaAdminAddr;
     private String connString;
     private String reminderDay;
     private String thankyouDay;
     private String reminderTime;        // 送信時刻
     private String thankyouTime;
     private int tempComingLimitRange;

//     private conn As OdbcConnection;       // データベース接続
     private int timerSec;           // メール送信用ワーク

	public int getTimerInterval() {
		return timerInterval;
	}

	public void setTimerInterval(int timerInterval) {
		this.timerInterval = timerInterval;
	}

	public String getExecDateFile() {
		return execDateFile;
	}

	public void setExecDateFile(String execDateFile) {
		this.execDateFile = execDateFile;
	}

	public String getMailSmtp() {
		return mailSmtp;
	}

	public void setMailSmtp(String mailSmtp) {
		this.mailSmtp = mailSmtp;
	}

	public int getMailPort() {
		return mailPort;
	}

	public void setMailPort(int mailPort) {
		this.mailPort = mailPort;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getAdminAddr() {
		return adminAddr;
	}

	public void setAdminAddr(String adminAddr) {
		this.adminAddr = adminAddr;
	}

	public String getExpediaAdminAddr() {
		return expediaAdminAddr;
	}

	public void setExpediaAdminAddr(String expediaAdminAddr) {
		this.expediaAdminAddr = expediaAdminAddr;
	}

	public String getConnString() {
		return connString;
	}

	public void setConnString(String connString) {
		this.connString = connString;
	}

	public String getReminderDay() {
		return reminderDay;
	}

	public void setReminderDay(String reminderDay) {
		this.reminderDay = reminderDay;
	}

	public String getThankyouDay() {
		return thankyouDay;
	}

	public void setThankyouDay(String thankyouDay) {
		this.thankyouDay = thankyouDay;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getThankyouTime() {
		return thankyouTime;
	}

	public void setThankyouTime(String thankyouTime) {
		this.thankyouTime = thankyouTime;
	}

	public int getTempComingLimitRange() {
		return tempComingLimitRange;
	}

	public void setTempComingLimitRange(int tempComingLimitRange) {
		this.tempComingLimitRange = tempComingLimitRange;
	}

	public int getTimerSec() {
		return timerSec;
	}

	public void setTimerSec(int timerSec) {
		this.timerSec = timerSec;
	}
     
     
}
