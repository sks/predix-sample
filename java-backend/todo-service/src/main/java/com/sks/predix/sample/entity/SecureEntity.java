package com.sks.predix.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public class SecureEntity<ID extends Serializable> extends
		TimeSensitiveEntity<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "owner", updatable = false)
	private String owner;

	@Override
	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.owner = SecurityContextHolder.getContext().getAuthentication()
				.getName();
	}

}
