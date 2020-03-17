package jp.happyhotel.batch.hotel_job_reserve_mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobListener extends JobExecutionListenerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(JobListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		super.beforeJob(jobExecution);
		log.info("ジョブ開始");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		super.afterJob(jobExecution);
		log.info("ジョブ終了");
	}

}