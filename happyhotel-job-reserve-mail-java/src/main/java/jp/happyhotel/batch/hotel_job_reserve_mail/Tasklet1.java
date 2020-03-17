package jp.happyhotel.batch.hotel_job_reserve_mail;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Tasklet1 implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(Tasklet1.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("tasklet1!!");
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM mailsendlist");
		list.forEach(System.out::println);
		return RepeatStatus.FINISHED;
	}

}
