package org.link.push.job.core.trigger;

import com.alibaba.fastjson.JSON;
import org.link.push.job.core.dto.TriggerInfo;

import java.io.Serializable;

/**
 * @author ginger
 * @date 2021/1/7 11:36
 */
public abstract class AbstractScanTrigger<T extends Serializable> extends AbstractTrigger<T> {

	@Override
	public void add(TriggerInfo<T> t) {
		stringRedisTemplate.opsForSet().add(t.getKey(), JSON.toJSONString(t.getData()));
	}

	@Override
	public void remove(TriggerInfo<T> t) {
		stringRedisTemplate.opsForSet().remove(t.getKey(), JSON.toJSONString(t.getData()));
	}
}
