package org.link.push.job.core.trigger;

import com.alibaba.fastjson.JSON;
import org.link.push.job.core.dto.TriggerInfo;

import java.io.Serializable;

/**
 * @author ginger
 * @date 2021/1/7 11:36
 */
public abstract class AbstractFixedTrigger<T extends Serializable> extends AbstractTrigger<T> {

	@Override
	public void add(TriggerInfo<T> t) {
		String data = JSON.toJSONString(t.getData());
		// 去重
		stringRedisTemplate.opsForList().remove(t.getKey(), 0, data);
		stringRedisTemplate.opsForList().rightPush(t.getKey(), data);
	}

	@Override
	public void remove(TriggerInfo<T> t) {
		stringRedisTemplate.opsForList().remove(t.getKey(), 0, JSON.toJSONString(t.getData()));
	}
}
