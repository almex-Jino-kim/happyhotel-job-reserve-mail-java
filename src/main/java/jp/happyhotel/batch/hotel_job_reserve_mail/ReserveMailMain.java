package jp.happyhotel.batch.hotel_job_reserve_mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import jp.happyhotel.batch.hotel_job_reserve_mail.bean.SysInfoBean;
import jp.happyhotel.batch.hotel_job_reserve_mail.ReserveMailUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReserveMailMain  implements Tasklet {
	
	// 2重起動チェックは不要。ただし、何かあった場合のために手動実行できるようにすること。
	
	private static Logger log = LoggerFactory.getLogger(ReserveMailMain.class);
	
	private int sts;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("ReserveMail start");
		
		SysInfoBean gSysInfo = new SysInfoBean();
		String gMailHeader[] = ReserveMailUtil.gMailHeader;
		String gMailBody[] = ReserveMailUtil.gMailBody;
		String eMailHeader[] = ReserveMailUtil.eMailHeader;
		String eMailBody[] = ReserveMailUtil.eMailBody;
		
		// TODO 構成ファイルの読み込み
		sts = ReserveMailUtil.readSetting(gSysInfo, gMailHeader, gMailBody, eMailHeader, eMailBody);
		
		if (sts != 0) {
			// TODO application 終了
		}
		
		// TODO データベースのオープン
		
		// TODO メール送信データがあるかチェックする
		
		
		
		return RepeatStatus.FINISHED;
	}
	
}
