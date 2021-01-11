package org.link.push.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ginger
 * @date 2020/12/22 11:23
 */
@Configuration
public class TaskSchedulerConfig {

	@Bean
	public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		taskScheduler.setThreadNamePrefix("x-Scheduled-");
		taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//调度器shutdown被调用时等待当前被调度的任务完成
		taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
		//等待时长
		taskScheduler.setAwaitTerminationSeconds(60);
		return taskScheduler;
	}

}
