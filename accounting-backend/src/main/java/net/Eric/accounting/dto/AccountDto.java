package net.Eric.accounting.dto;

public class AccountDto {

	private Long id;
	private boolean isExpense ;
	private String category;
	private Long amount ;
	
	public AccountDto() {
		
	}
	
	public AccountDto(Long id, boolean isExpense, String category, Long amount) {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isExpense() {
		return isExpense;
	}

	public void setExpense(boolean isExpense) {
		this.isExpense = isExpense;
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
	
	
}
