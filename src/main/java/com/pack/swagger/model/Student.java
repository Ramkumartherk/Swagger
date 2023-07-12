package com.pack.swagger.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Student {
	@Id
	private Integer id;
	private String name;
	private String Dept;
	private Long phone_No;
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getDept() {
//		return Dept;
//	}
//	public void setDept(String dept) {
//		Dept = dept;
//	}
//	public Long getPhone_No() {
//		return phone_No;
//	}
//	public void setPhone_No(Long phone_No) {
//		this.phone_No = phone_No;
//	}
}
	

