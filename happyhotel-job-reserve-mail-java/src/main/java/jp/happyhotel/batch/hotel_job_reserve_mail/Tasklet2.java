package jp.happyhotel.batch.hotel_job_reserve_mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Tasklet2 implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(Tasklet2.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("tasklet2!!");
		return RepeatStatus.FINISHED;
	}

}