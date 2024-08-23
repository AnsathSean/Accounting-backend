package net.Eric.accounting.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "expensed")
	private boolean expensed ;
	
	@Column(name = "category")
	private String category ;
	
	@Column(name = "amount")
	private Long amount ;
	
	@Column(name = "createdate")
	private LocalDateTime createDate;
	
	public Account() {
		
	}
	
	public Account(Long id, boolean expensed, String category, long amount,LocalDateTime createDate) {
		this.id = id;
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

	public void setExpensed(boolean expensed) {
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


	

	
	
	
}
