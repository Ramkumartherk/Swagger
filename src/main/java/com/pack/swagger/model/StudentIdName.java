package com.pack.swagger.model;

public class StudentIdName {
	private Integer id;
	private String student;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public StudentIdName(Integer id, String student) {
		super();
		this.id = id;
		this.student = student;
	}
	public StudentIdName() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StudentIdName [id=" + id + ", student=" + student + "]";
	}
	

}
