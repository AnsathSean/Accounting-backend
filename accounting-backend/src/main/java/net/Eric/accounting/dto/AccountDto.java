package net.Eric.accounting.dto;

import java.time.LocalDateTime;

public class AccountDto {

	private Long id;
	private String name;
	private Boolean expensed ;
	private String category;
	private Long amount ;
	private LocalDateTime createDate;
	
	public AccountDto() {
		
	}
	
	public AccountDto(Long id,String name, Boolean expensed, String category, Long amount, LocalDateTime createDate) {
		this.id = id;
		this.name = name;
		this.expensed = expensed;
		this.category = category;
		this.amount = amount;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getExpensed() {
		return expensed;
	}

	public void setExpensed(Boolean expensed) {
		this.expensed = expensed;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
