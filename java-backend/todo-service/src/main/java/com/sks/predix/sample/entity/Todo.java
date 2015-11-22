package com.sks.predix.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.Identifiable;

@Entity
@Table(name = "todos")
public class Todo extends SecureEntity<Long> implements Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "title", nullable = false)
	private String title;

	@NotNull
	@Column(name = "completed")
	private boolean completed;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setId(long id) {
		super.setId(id);
	}

}
