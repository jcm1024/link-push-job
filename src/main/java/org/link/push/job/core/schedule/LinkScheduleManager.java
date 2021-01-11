package org.link.push.job.core.schedule;

import org.link.push.job.core.dto.LinkJobInfo;
import org.link.push.job.core.handler.AbstractJobHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务管理器
 *
 * @author ginger
 * @date 2021/1/7 14:04
 */
@Component
public class LinkScheduleManager implements ApplicationListener<ContextClosedEvent> {

	@Resource
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	/**
	 * 任务记录
	 */
	private final ConcurrentHashMap<Long, ScheduledFuture<?>> TASK_MAP = new ConcurrentHashMap();

	/**
	 * 新增任务
	 */
	public void add(LinkJobInfo linkJobInfo, AbstractJobHandler<?> abstractJobHandler) {
		if (linkJobInfo == null) {
			throw new RuntimeException("linkJobInfo is null.");
		}
		if (linkJobInfo.getId() == null) {
			throw new RuntimeException("job's id is null.");
		}
		if (linkJobInfo.getCorn() == null) {
			throw new RuntimeException("job's corn is null.");
		}
		if (abstractJobHandler == null) {
			throw new RuntimeException("abstractJobHandler is null.");
		}
		CronTrigger cronTrigger = new CronTrigger(linkJobInfo.getCorn());
		ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(abstractJobHandler, cronTrigger);
		if (schedule == null) {
			throw new RuntimeException("schedule is null.");
		}
		TASK_MAP.put(linkJobInfo.getId(), schedule);
	}

	/**
	 * 取消任务
	 */
	public void cancel(Long jobId) {
		// TODO 自动路由通知到相应的执行服务器
		ScheduledFuture<?> schedule = TASK_MAP.get(jobId);
		if (schedule != null) {
			schedule.cancel(Boolean.TRUE);
		}
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
		Enumeration<ScheduledFuture<?>> elements = TASK_MAP.elements();
		while (elements.hasMoreElements()) {
			ScheduledFuture<?> scheduledFuture = elements.nextElement();
			scheduledFuture.cancel(Boolean.TRUE);
		}
	}
}
