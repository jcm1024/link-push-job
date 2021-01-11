package org.link.push.job.core.trigger;

import org.link.push.job.core.dto.TriggerInfo;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 触发器
 *
 * @author ginger
 * @date 2021/1/7 10:41
 */
public abstract class AbstractTrigger<T extends Serializable> {

	@Resource
	protected StringRedisTemplate stringRedisTemplate;

	/**
	 * 添加
	 *
	 * @param t
	 */
	public abstract void add(TriggerInfo<T> t);


	/**
	 * 移除
	 *
	 * @param t
	 */
	public abstract void remove(TriggerInfo<T> t);

	/**
	 * 执行。具体任务业务实现的地方
	 *
	 * @param t
	 */
	public abstract void execute(TriggerInfo<T> t);
}
