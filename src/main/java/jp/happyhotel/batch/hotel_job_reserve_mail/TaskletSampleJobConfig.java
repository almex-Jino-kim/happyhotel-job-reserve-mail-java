package jp.happyhotel.batch.hotel_job_reserve_mail;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class TaskletSampleJobConfig {
	@Autowired
	private Tasklet1 tasklet1;
	@Autowired
	private Tasklet2 tasklet2;
	@Autowired
	private ReserveMailMain fmMail;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;


	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet(tasklet1)
				.build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.tasklet(tasklet2)
				.build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3")
				.tasklet(fmMail)
				.build();
	}

	@Bean
	public Job job(Step step1, Step step2, Step step3) throws Exception {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.start(step1)
				.next(step2)
				.next(step3)
				.build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobListener();
	}
}
