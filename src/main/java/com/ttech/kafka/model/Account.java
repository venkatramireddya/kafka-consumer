package com.ttech.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Account {

	private long accountNumber;
	private String accountName;
	private Double balance;
}
