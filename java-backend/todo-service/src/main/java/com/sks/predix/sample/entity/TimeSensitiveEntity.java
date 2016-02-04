package com.sks.predix.sample.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public class TimeSensitiveEntity<ID extends Serializable> extends
		AbstractPersistable<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Column(name = "modify_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date modifyDate;

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	@PrePersist
	public void prePersist() {
		this.createDate = Calendar.getInstance().getTime();
		this.preUpdate();
	}

	@PreUpdate
	public void preUpdate() {
		this.modifyDate = Calendar.getInstance().getTime();
	}

	@Override
	public void setId(ID id) {
		super.setId(id);
	}

}
