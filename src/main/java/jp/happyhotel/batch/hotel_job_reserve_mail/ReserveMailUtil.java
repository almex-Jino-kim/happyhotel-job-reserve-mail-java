package jp.happyhotel.batch.hotel_job_reserve_mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.parsing.ParseState;

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
    
    /*
     *  メールメッセージに埋め込む
     */
    private String makeMailBody (
    		/* TODO OdbcDataReader reader → 実装を変える*/
    		String msgBody,
    		UserInfoBean userinfo
    		) {
    	String strKey;
    	String strVal = "";
    	
    	/**
    	 *　 埋め込み用のキー（データベースの項目名）
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
    	
    	for (int i = 0 ; i < strKeyArray.size() - 1 ; i++) {
    		strKey = strKeyArray.get(i);
    		
    		try {
				// TODO strVal = reader(strKey).toString();
			} catch (Exception e) {
				strVal = "";
			}
    		
    		switch(strKey) {
    		case "reserve_no":
    			// 元ソース： strVal = Strings.Right(strVal, 6)
    			strVal = strVal.substring(strVal.length() - 6);
    			
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
            	int charge = reader("basic_charge_total");
            	int num_adult = reader("num_adult");
            	int num_child = reader("num_child");
            	int spaceCnt = getSpaceCount(msgBody, strKey);
            	
            	strVal = "";
            	
            	if (num_adult == 1) {
            		// 元ソース： strVal = charge.ToString("#,##0") & "円（大人１人"
            		strVal = String.valueOf(charge)
            		
            	} else if (num_adult >= 2) {
            		// 元ソース： strVal = charge.ToString("#,##0") & "円（大人２人"
            		
            		if (num_adult > 2) {
            			// 元ソース：strVal &= "、大人追加 × " & (num_adult - 2).ToString & "名"
            		}
            		
            	}
            	
            	break;
            	
    		}
    		
    	}
    	
    	
    	return msgBody;
    }

    /*
     * TODO セッティングを読む
     */
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
    
    /*
     * 時間を文字列に変換する
     */
    private String timeConv(String strTime) {
    	String strWork = "";
    	// 元ソース：strTime = strTime.PadLeft(6, "0")
    	// ゼロ埋め:PadLeft(桁数, 文字)で指定した桁数を指定した文字で文字列を埋める
    	 strTime = strTime.format("%6s", strTime).replace(" ", "0");
    	
    	switch (strTime.length()) {
        case 6:
        	// 元ソース：strWork = Left(strTime, 2) & ":" & Mid(strTime, 3, 2);
        	strWork = strTime.substring(0, 2) + ":" + strTime.substring(2, 4);
            break;
        case 5:
        	// 元ソース：strWork = Left(strTime, 1) & ":" & Mid(strTime, 2, 2)
        	strWork = strTime.substring(0, 1) + ":" + strTime.substring(1, 3);
    		break;
        default:
            strWork = strTime;
    	}
    	return strWork;
	}

	/*
     * 日付けを文字列に変換する
     */
    private String dateConv(String strDate) {
    	String strWork;
    	// strWork = Left(strDate, 4) & "/" & Mid(strDate, 5, 2) & "/" & Right(strDate, 2)
    	// 例：20200318
    	strWork = strDate.substring(0, 4) + "/" + strDate.substring(4, 6) + "/" + strDate.substring(strDate.length() - 2);
		return strWork;
	}

	/*
     * 行頭のスペースをカウントする
     */
	private int getSpaceCount(String msgBody, String strKey) {
		// TODO Auto-generated method stub
		int index;
		int count;
		
		index = msgBody.indexOf("%" + strKey + "%");
		if (index < 0) {		// キーが見つからない
			return 0;
		} 
		// 元ソース： count = msgBody.LastIndexOf(vbCrLf, index)
		count = msgBody.lastIndexOf("\n", index);
		
		if (count > 0){
			// 元ソース：count = LenB(Mid(msgBody, count + 2, index - count - 2))
			count = lenByte(msgBody.substring(count + 2, index - count - 2));			
		} else {
			// 元ソース：count = LenB(Left(msgBody, index))
			count = lenByte(msgBody.substring(0, index));
		}
		
		return count;
	}

	/*
	 * 文字列のバイト数（半角1バイト、全角2バイト）
	 */
	private int lenByte (String strText) {
		// 元ソース： Return System.Text.Encoding.GetEncoding("Shift_JIS").GetByteCount(strText)
		// 【参考】https://www.saka-en.com/java/java-string/
		if ( strText == null || strText.length() == 0 ) {
			return 0;			
		}
		
	    int ret = 0;
		try {
			ret = strText.getBytes("Shift_JIS").length;
		} catch (UnsupportedEncodingException e) {
			ret = 0;
		}
		return ret;
	}
    
}
