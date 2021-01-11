package org.link.push.job.core.dto;

import java.io.Serializable;

/**
 * @author ginger
 * @date 2021/1/7 11:20
 */
public class TriggerInfo<T extends Serializable> {
	private String key;
	private LinkJobInfo linkJobInfo;
	private T data;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LinkJobInfo getLinkJobInfo() {
		return linkJobInfo;
	}

	public void setLinkJobInfo(LinkJobInfo linkJobInfo) {
		this.linkJobInfo = linkJobInfo;
	}
}
