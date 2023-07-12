package com.pack.swagger.Dto;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
	@Id
	private Integer id;
	private String name;
	private String dept;
	private Long phoneNo;
}
