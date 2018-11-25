package com.spring.boot.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job importJob;
    public JobParameters jobParameters;

    @Scheduled(fixedRate = 1000)
    public void execute() throws Exception{
        String path = "people"+".csv";
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", path)
                .toJobParameters();
        jobLauncher.run(importJob,jobParameters);
    }
}
