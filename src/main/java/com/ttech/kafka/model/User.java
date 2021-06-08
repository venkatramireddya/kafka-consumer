package com.ttech.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {
	private long id;
	private String name;
	private String dept;
	private Long salary;

}
