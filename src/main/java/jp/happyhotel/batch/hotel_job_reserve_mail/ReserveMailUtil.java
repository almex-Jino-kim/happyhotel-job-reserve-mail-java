package jp.happyhotel.batch.hotel_job_reserve_mail;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.happyhotel.batch.hotel_job_reserve_mail.bean.SysInfoBean;
import jp.happyhotel.batch.hotel_job_reserve_mail.bean.MailHotelBean;
import jp.happyhotel.batch.hotel_job_reserve_mail.bean.UserInfoBean;

public class ReserveMailUtil  {
	
	private static Logger log = LoggerFactory.getLogger(ReserveMailUtil.class);
	
    public static final int MAIL_TYPE_1   = 1;      // 予約登録
    public static final int MAIL_TYPE_2   = 2;      // 予約変更
    public static final int MAIL_TYPE_3   = 3;      // 予約キャンセル
    public static final int MAIL_TYPE_4   = 4;      // 予約ノーショウ
    public static final int MAIL_TYPE_5   = 5;      // リマインダーメール
    public static final int MAIL_TYPE_6   = 6;      // サンキューメール
    public static final int MAIL_TYPE_7   = 7;      // 仮来店
    public static final int MAIL_KIND_CNT = 230;

    private static final int SEND_START_TIME   = 9999;     // ホテルへの送信可能開始時間（要望により即時メール送信）
    private static final int SEND_END_TIME     = 9999;       // 同じく送信可能終了時間（要望により即時メール送信）
    private static final int ANYTIME_FLAG      = 9999;        // いつでも送信可能

    private static final int SEND_FLAG_ADMIN   = 0;         // 管理者
    private static final int SEND_FLAG_HOTEL   = 1;          // ホテルオーナー
    private static final int SEND_FLAG_USER    = 2;          // ユーザ

    private static final int SEND_STATUS_READY = 0;      // 未送信
    private static final int SEND_STATUS_DONE  = 1;       // 送信済み
    private static final int SEND_STATUS_ELSE  = 2;        // その他

    private static final String SEC_TITLE = "[title]";          // 小文字で比較
    private static final String SEC_BODY  = "[body]";
    
    public static SysInfoBean gSysInfo;
    
    // メールの件名と本文
    public static String gMailHeader[] = new String[MAIL_KIND_CNT];
    public static String gMailBody[] = new String[MAIL_KIND_CNT];
    public static String eMailHeader[] = new String[MAIL_KIND_CNT];
    public static String eMailBody[] = new String[MAIL_KIND_CNT];
    
   

    
    // TODO セッティングを読む
    public static int readSetting(
    		SysInfoBean sysInfo, 
    		String mailHeader[], 
    		String mailBody[], 
    		String mailHeaderError[], 
    		String mailBodyError[]
    		) {
    	
    	String strKey;
    	String strFile;
    	int ix;
    	int n;
    	int readSetting = 0;
    	
    	
    	
    	// テキストファイルの文字コード(ここでは、Shift JIS)
    	
    	
    	return readSetting;
    }
    
    private String makeMailBody (
    		/*OdbcDataReader reader*/
    		String msgBody,
    		UserInfoBean userinfo
    		) {
    	String strKey;
    	String strVal = "";
    	
    	// 埋め込み用のキー（データベースの項目名）
    	/**
    	 *　メールメッセージに埋め込む
    	 *
    	 *	accept_date	        受付日	            YYYY年MM月DD日
    	 *	accept_time	        受付時刻	        HH:mm
    	 *	address_all	        全住所	
    	 *	basic_charge_total	料金小計	
    	 *	charge_total	        料金合計	
    	 *	est_time_arrival	    到着予定時刻	    HH:mm
    	 *	hotel_name	        ホテル名称	
    	 *	name_first	        氏名（名漢字）	
    	 *	name_last	            氏名（姓漢字）	
    	 *	reserve_date	        予約日	            YYYY年MM月DD日
    	 *	reserve_no	        予約番号	
    	 * user_id               ユーザID
    	 *
    	 *	tel1	                電話番号1	
    	 *	zip_code	            郵便番号	
    	 *	cancel_policy	        キャンセル規定	            複数行
    	 *	charge	            料金明細	
    	 *	ci_time_from	        チェックイン可能時間From	HH:mm
    	 *	ci_time_to	        チェックイン可能時間To	    HH:mm
    	 *	co_time	            チェックアウト時間	        HH:mm
    	 *	plan_name	            プラン名	
    	 *	plan_pr	            プラン内容                  複数行	
    	 *	question	            宿からの質問	            複数行
    	 *	answer	            宿からの質問の回答	        複数行
    	 *	room_name	            部屋番号	
    	 * indispensability_option 必須オプション            複数行
    	 * option_charge         オプション                  複数行
    	 * precaution            注意事項
    	 *   
    	 **/
    	List<String> strKeyArray = new ArrayList<String>();
    	strKeyArray.add("accept_date");
    	strKeyArray.add("accept_time");
    	strKeyArray.add("address_all");
    	strKeyArray.add("basic_charge_total");
    	strKeyArray.add("charge_total");
    	strKeyArray.add("est_time_arrival");
    	strKeyArray.add("user_id");
    	strKeyArray.add("hotel_name");
    	strKeyArray.add("name_first");
    	strKeyArray.add("name_last");
    	strKeyArray.add("reserve_date");
    	strKeyArray.add("reserve_no");
    	strKeyArray.add("tel1");
    	strKeyArray.add("zip_code");
    	strKeyArray.add("cancel_policy");
    	strKeyArray.add("charge");
    	strKeyArray.add("ci_time_from");
    	strKeyArray.add("ci_time_to");
    	strKeyArray.add("plan_name");
    	strKeyArray.add("plan_pr");
    	strKeyArray.add("question");
    	strKeyArray.add("room_name");
    	strKeyArray.add("indispensability_option");
    	strKeyArray.add("option_charge");
    	strKeyArray.add("precaution");
    	strKeyArray.add("id");
    	strKeyArray.add("answer");
    	strKeyArray.add("demands");
    	strKeyArray.add("used_mile");
    	strKeyArray.add("payment");
    	strKeyArray.add("user_tel");
    	strKeyArray.add("parking");
    	
    	for (int i = 0 ; i < strKeyArray.size(); i++) {
    		strKey = strKeyArray.get(i);
    		
    		try {
				// TODO strVal = reader(strKey).toString();
			} catch (Exception e) {
				// TODO: handle exception
				strVal = "";
			}
    		
    		switch(strKey) {
    		case "reserve_no":
    			int size = strVal.length();
    			int cut_length = 6;
    			
    			strVal = strVal.substring(size - cut_length);
    			
    			// TODO  OTAからの予約の場合OTAでの予約番号も追記する
    			if (reader("ext_flag") == 2) {
    				strVal += "（OTA: " + reader("ota_booking_code") + "）";
    			}
    			break;
    		case "accept_date":
    			strVal = dateConv(strVal);
    			break;
    		case "reserve_date":
    			strVal = dateConv(strVal);
    			break;
    		case "accept_time":
    			strVal = timeConv(strVal);
    			break;
    		case "est_time_arrival":
    			strVal = timeConv(strVal);
    			break;
    		case "ci_time_from":
    			strVal = timeConv(strVal);
    			break;
    		case "ci_time_to":
            	strVal = timeConv(strVal);
            	break;
            case "room_name":
            	String strMailKind = reader("request_mail_kind").toString;
            	if (strMailKind == "11" || strMailKind == "12" || strMailKind == "13" || strMailKind == "14") {
            		if (reader("room_select_kind").toString == "1") {
            			strVal = reader("rank_name").toString;
            		} else if (reader("room_select_kind").toString == "2") {
            			strVal = reader("rank_name").toString + "(" + strVal + ")";
            		} else {
            			strVal = "ランク指定無し";
            		}
            	} else {
            		 If (reader("room_select_kind").ToString = "1" ) {
            			 strVal = reader("rank_name").ToString;            			 
            		 } elseIf ( reader("room_select_kind").ToString = "3" ) {
            			 strVal = "部屋おまかせ";            			 
            		 }
            	}
            case "charge":  // 料金明細を作成する
            	// TODO つづきの実装
    		}
    		
    	}
    	
    	
    	return msgBody;
    }

    
}
