package com.OET.Online_Expense_Tracker.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@JoinColumn(name = "user_id")
	private String userId;

	@Column(name = "category")
	private String category;
	
	public Category() {
		super();
	}

	public Category(int id, String category, String userId) {
		super();
		this.id = id;
		this.category = category;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getUser() {
		return userId;
	}

	public void setUser(String userId) {
		this.userId = userId;
	}

}
