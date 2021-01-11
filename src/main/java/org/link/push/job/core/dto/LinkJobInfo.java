package org.link.push.job.core.dto;

import java.util.Date;

/**
 * @author ginger
 * @date 2021/1/7 10:48
 */
public class LinkJobInfo {

	/**
	 * id
	 */
	private Long id;

	/**
	 * JobHandler
	 */
	private String jobHandler;

	/**
	 * corn表达式
	 */
	private String corn;

	/**
	 * 时间间隔,单位是 天，小于等于0包含今日，大于0表示距离当天多少天之后执行
	 */
	private Integer timeInterval;

	/**
	 * 状态，正常-normal, 暂停-pause,
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 删除标志位
	 */
	private Boolean deleteFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobHandler() {
		return jobHandler;
	}

	public void setJobHandler(String jobHandler) {
		this.jobHandler = jobHandler;
	}

	public String getCorn() {
		return corn;
	}

	public void setCorn(String corn) {
		this.corn = corn;
	}

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
