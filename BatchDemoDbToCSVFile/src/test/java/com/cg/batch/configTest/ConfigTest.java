package com.cg.batch.configTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.batch.BatchDemoDbToCsvFileApplication;
import com.cg.batch.config.SpringBatchConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { BatchDemoDbToCsvFileApplication.class, SpringBatchConfig.class })
public class ConfigTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired 
	private SpringBatchConfig config;

	@Test
	public void testEntireJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		final JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(config.exportJob(),
				jobLauncherTestUtils.getUniqueJobParameters());
		assertNotNull(jobExecution);
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}

	
	@Test
	public void launchJob() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		assertEquals(jobExecution.getStatus(),BatchStatus.COMPLETED);
	}
	
	
	@Test
	public void testSpecificStep() {
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step");
		assertEquals("step",config.step().getName());
	}
	
}
