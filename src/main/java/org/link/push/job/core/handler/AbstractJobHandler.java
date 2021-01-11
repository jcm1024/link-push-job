package org.link.push.job.core.handler;

import org.link.push.job.core.dto.LinkJobInfo;
import org.link.push.job.core.dto.TriggerInfo;
import org.link.push.job.core.trigger.AbstractTrigger;

import java.io.Serializable;

/**
 * 任务抽象句柄
 *
 * @author ginger
 * @date 2021/1/7 10:36
 */
public abstract class AbstractJobHandler<T extends Serializable> implements Runnable {

	protected LinkJobInfo linkJobInfo;
	protected TriggerInfo<T> triggerInfo;
	protected AbstractTrigger<T> linkTrigger;

	/**
	 * 设置任务执行参数
	 */
	public void setLinkJobInfo(LinkJobInfo linkJobInfo) {
		this.linkJobInfo = linkJobInfo;
	}

	/**
	 * 设置触发器
	 */
	public void setLinkTrigger(AbstractTrigger<T> linkTrigger) {
		this.linkTrigger = linkTrigger;
	}

	@Override
	public void run() {
		triggerInfo.setLinkJobInfo(linkJobInfo);
		linkTrigger.execute(triggerInfo);
	}
}
